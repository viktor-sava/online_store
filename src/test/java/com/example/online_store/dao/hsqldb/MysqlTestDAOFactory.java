package com.example.online_store.dao.hsqldb;

import com.example.online_store.dao.*;
import com.example.online_store.dao.mysql.*;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.SQLException;

public class MysqlTestDAOFactory implements DAOFactory {

    private static final Logger log = Logger.getLogger(MysqlTestDAOFactory.class);

    private final DataSource dataSource;

    public MysqlTestDAOFactory(DataSource dataSource) throws SQLException {

        this.dataSource = dataSource;
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
