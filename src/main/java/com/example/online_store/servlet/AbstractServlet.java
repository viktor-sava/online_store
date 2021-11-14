package com.example.online_store.servlet;

import com.example.online_store.ApplicationProperties;
import com.example.online_store.dao.DAOFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public abstract class AbstractServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(AbstractServlet.class);

    protected final String url;

    protected ApplicationProperties applicationProperties;

    private DAOFactory cachedDaoFactory;

    public AbstractServlet(String url) {
        this.url = url;
        this.applicationProperties = ApplicationProperties.getInstance();
    }

    public final DAOFactory getDaoFactory() {
        if (cachedDaoFactory == null) {
            cachedDaoFactory = (DAOFactory) getServletContext().getAttribute("daoFactory");
        }
        return cachedDaoFactory;
    }

    public final String getLocale(HttpServletRequest req, HttpServletResponse resp) {
        return Arrays.stream(req.getCookies()).filter(p -> p.getName().equals("locale"))
                .findAny()
                .orElseGet(() -> {
                    Cookie cookie = new Cookie("locale", applicationProperties.getProperty("default-locale"));
                    resp.addCookie(cookie);
                    return cookie;
                }).getValue();
    }

    public final String getReferer(HttpServletRequest req) {
        return req.getHeader("referer").substring(req.getRequestURL().length());
    }

    public final void forward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(url).forward(request, response);
    }

}
