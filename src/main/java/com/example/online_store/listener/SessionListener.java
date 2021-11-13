package com.example.online_store.listener;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class SessionListener implements HttpSessionListener {

    public static final Logger log = Logger.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSessionListener.super.sessionCreated(se);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession httpSession = se.getSession();
        Enumeration<String> attributes = httpSession.getAttributeNames();

        log.trace(getMapOfAttributes(attributes, httpSession));

        while (attributes.hasMoreElements()) {
            httpSession.removeAttribute(attributes.nextElement());
        }

        log.trace("Session has been destroyed");
    }

    private static Map<String, Object> getMapOfAttributes(Enumeration<String> attributes, HttpSession httpSession) {
        Map<String, Object> map = new HashMap<>();
        while (attributes.hasMoreElements()) {
            String a = attributes.nextElement();
            map.put(a, httpSession.getAttribute(a));
        }
        return map;
    }

}
