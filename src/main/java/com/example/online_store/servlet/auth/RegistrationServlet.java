package com.example.online_store.servlet.auth;

import com.example.online_store.dao.AccountDao;
import com.example.online_store.dao.DAOFactory;
import com.example.online_store.model.Account;
import com.example.online_store.servlet.AbstractServlet;
import com.example.online_store.validation.AccountValidator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationServlet extends AbstractServlet {

    private static final Logger log = Logger.getLogger(RegistrationServlet.class);

    private AccountDao accountDao;

    public RegistrationServlet() {
        super("pages/auth/registration.jsp");
    }

    @Override
    public void init() {
        DAOFactory daoFactory = (DAOFactory) getServletContext().getAttribute("daoFactory");
        this.accountDao = daoFactory.getAccountDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String patronymic = req.getParameter("patronymic");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        String repeatPassword = req.getParameter("repeat_password");

        AccountValidator accountValidator = new AccountValidator();

        accountValidator.validateField(surname, "surname");
        accountValidator.validateField(name, "name");
        accountValidator.validateField(patronymic, "patronymic");
        accountValidator.validateEmail(email);
        accountValidator.validatePhone(phone);
        accountValidator.validateField(password, "password");
        accountValidator.validateField(repeatPassword, "repeat password");
        accountValidator.passwordCompare(password, repeatPassword);

        HttpSession httpSession = req.getSession();
        if (accountValidator.isValid()) {
            Account account = Account.builder()
                    .surname(surname)
                    .name(name)
                    .patronymic(patronymic)
                    .email(email)
                    .phone(accountValidator.normalizePhone(phone))
                    .password(password)
                    .build();
            accountValidator.validateNotExistence(accountDao.findAccountByEmailOrPhone(email, phone));
            if (accountValidator.isValid()) {
                accountValidator.checkCreating(accountDao.insertAccount(account));
                if (accountValidator.isValid()) {
                    log.trace("Account with user_id " + account.getId() + " has been created");
                    httpSession.setAttribute("user_id", account.getId());
                    httpSession.setAttribute("admin", account.isAdmin());
                    resp.sendRedirect(getServletContext().getContextPath() + "/");
                    return;
                }
            }
        }
        httpSession.setAttribute("errors", accountValidator.getMessages());
        resp.sendRedirect(getServletContext().getContextPath() + "/registration");
    }
}
