package com.example.online_store.filter;

import org.apache.log4j.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter extends HttpFilter {

    private static final Logger log = Logger.getLogger(AuthFilter.class);

    public static final String[] logged = {
            "/login", "/registration"
    };

    public static final String[] admin = {
            "/login", "/registration"
    };

    public static final String[] guest = {};

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String path = request.getRequestURI().substring(request.getContextPath().length());
        HttpSession session = request.getSession(false);
        if (isAuthenticated(session)) {
            if (isAdmin(session)) {
                if (isDeniedPath(admin, path)) {
                    response.sendRedirect(getServletContext().getContextPath() + "/");
                } else {
                    chain.doFilter(request, response);
                }
            } else {
                if (isDeniedPath(logged, path)) {
                    response.sendRedirect(getServletContext().getContextPath() + "/");
                } else {
                    chain.doFilter(request, response);
                }
            }
        } else {
            if (isDeniedPath(guest, path)) {
                response.sendRedirect(getServletContext().getContextPath() + "/");
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    public static boolean isAdmin(HttpSession session) {
        return
            session != null &&
            session.getAttribute("admin") != null &&
            (boolean) session.getAttribute("admin");
    }

    public static boolean isAuthenticated(HttpSession session) {
        return session != null && session.getAttribute("user_id") != null;
    }

    public static boolean isDeniedPath(String[] deniedPaths, String path) {
        for (String s : deniedPaths) {
            if (path.contains(s)) {
                return true;
            }
        }
        return false;
    }

}
