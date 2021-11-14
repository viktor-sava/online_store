package com.example.online_store.dao.mysql;

import com.example.online_store.ApplicationProperties;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.HashMap;

public class MysqlDao {

    private static final Logger log = Logger.getLogger(MysqlDao.class);

    protected ApplicationProperties applicationProperties;

    protected DataSource dataSource;

    public MysqlDao(DataSource dataSource) {
        this.applicationProperties = ApplicationProperties.getInstance();
        this.dataSource = dataSource;
    }

    void close(AutoCloseable closeable) {
        if (closeable == null) return;
        try {
            closeable.close();
        } catch (Exception e) {
            log.error(e);
        }
    }

    void setAutoCommit(Connection connection, boolean flag) {
        if (connection == null) return;
        try {
            connection.setAutoCommit(flag);
        } catch (SQLException e) {
            log.error(e);
        }
    }

    void rollback(Connection connection) {
        if (connection == null) return;
        try {
            connection.rollback();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    void rollback(Connection connection, Savepoint savepoint) {
        if (connection == null || savepoint == null) return;
        try {
            connection.rollback(savepoint);
        } catch (SQLException e) {
            log.error(e);
        }
    }

}
