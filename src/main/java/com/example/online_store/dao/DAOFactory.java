package com.example.online_store.dao;

import com.example.online_store.ApplicationProperties;
import com.example.online_store.dao.mysql.MysqlDAOFactory;

public interface DAOFactory {
    AccountDao getAccountDao();
    CategoryDao getCategoryDao();
    LanguageDao getLanguageDao();
    ProductDao getProductDao();
    BannerDao getBannerDao();

    enum Type {
        MYSQL
    }

    static DAOFactory getDAOFactory(Type type) {
        if (type == Type.MYSQL) {
            return new MysqlDAOFactory();
        } else {
            return null;
        }
    }

    static DAOFactory getSelectedFactory() throws DAOException {
        if (ApplicationProperties.getInstance().getProperty("datasource").equalsIgnoreCase("mysql")) {
            return getDAOFactory(Type.MYSQL);
        } else {
            throw new DAOException("Cannot find implementation for entered datasource");
        }
    }
}
