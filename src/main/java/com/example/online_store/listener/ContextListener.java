package com.example.online_store.listener;

import com.example.online_store.ApplicationProperties;
import com.example.online_store.dao.DAOException;
import com.example.online_store.dao.DAOFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    private static final Logger log = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();
        ApplicationProperties applicationProperties = ApplicationProperties.getInstance();

        servletContext.setAttribute("applicationProperties", applicationProperties);

        try {
            DAOFactory daoFactory = DAOFactory.getSelectedFactory();
            log.trace(daoFactory);
            servletContext.setAttribute("daoFactory", daoFactory);
            servletContext.setAttribute("languages", daoFactory.getLanguageDao().findAllLanguages());
            log.trace("Dao factory has been injected in servlet context");
        } catch (DAOException e) {
            log.error(e);
        }
    }
}
