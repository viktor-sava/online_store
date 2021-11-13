package com.example.online_store.servlet.auth;

import com.example.online_store.servlet.AbstractServlet;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends AbstractServlet {

    private static final Logger log = Logger.getLogger(LogoutServlet.class);

    public LogoutServlet() {
        super(null);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession(false);
        if (httpSession != null) {
            log.trace("Account with user id " + httpSession.getAttribute("user_id") + " logged out");

            httpSession.invalidate();
        }
        resp.sendRedirect(getServletContext().getContextPath() + "/");
    }
}
