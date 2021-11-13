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

public class LoginServlet extends AbstractServlet {

    private static final Logger log = Logger.getLogger(LoginServlet.class);

    private AccountDao accountDao;

    public LoginServlet() {
        super("pages/auth/login.jsp");
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
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        AccountValidator accountValidator = new AccountValidator();

        accountValidator.validateEmail(login);
        accountValidator.validateField(password, "password");

        HttpSession httpSession = req.getSession();
        if (accountValidator.isValid()) {
            Account account = accountDao.findAccountByEmailOrPhone(login, login);
            accountValidator.validateExistence(account);
            if (accountValidator.isValid()) {
                accountValidator.passwordCompareHash(password, account.getPassword());
                if (accountValidator.isValid()) {
                    httpSession.setAttribute("user_id", account.getId());
                    httpSession.setAttribute("admin", account.isAdmin());
                    log.trace("Account with user_id " + account.getId() + " signed in");
                    resp.sendRedirect(getServletContext().getContextPath() + "/");
                    return;
                }
            }
        }
        httpSession.setAttribute("errors", accountValidator.getMessages());
        resp.sendRedirect(getServletContext().getContextPath() + "/login");
    }
}