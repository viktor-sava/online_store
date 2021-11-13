package com.example.online_store.dao.mysql;

import com.example.online_store.dao.*;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MysqlDAOFactory implements DAOFactory {

    private static final Logger log = Logger.getLogger(MysqlDAOFactory.class);

    private DataSource dataSource;

    public MysqlDAOFactory() {
        try {
            Context initialContext = new InitialContext();
            Context envCtx = (Context) initialContext.lookup("java:comp/env");
            this.dataSource = (DataSource) envCtx.lookup("jdbc/database");
        } catch (NamingException e) {
            log.error("Cannot lookup datasource from application context");
        }
    }

    @Override
    public AccountDao getAccountDao() {
        return new MysqlAccountDao(dataSource);
    }

    @Override
    public CategoryDao getCategoryDao() {
        return new MysqlCategoryDao(dataSource);
    }

    @Override
    public LanguageDao getLanguageDao() {
        return new MysqlLanguageDao(dataSource);
    }

    @Override
    public ProductDao getProductDao() {
        return new MysqlProductDao(dataSource);
    }

    @Override
    public BannerDao getBannerDao() {
        return new MysqlBannerDao(dataSource);
    }

}
