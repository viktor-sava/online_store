package com.example.online_store.dao.mysql;

import com.example.online_store.dao.AccountDao;
import com.example.online_store.model.Account;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;

public class MysqlAccountDao extends MysqlDao implements AccountDao {

    private static final Logger log = Logger.getLogger(MysqlAccountDao.class);

    public MysqlAccountDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Account findAccountByEmailOrPhone(String email, String phone) {
        if (email == null && phone == null) return null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(Queries.Account.FIND_BY_EMAIL);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, phone);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Account.builder()
                        .id(resultSet.getInt("id"))
                        .surname(resultSet.getString("surname"))
                        .name(resultSet.getString("name"))
                        .patronymic(resultSet.getString("patronymic"))
                        .email(resultSet.getString("email"))
                        .phone(resultSet.getString("phone"))
                        .password(resultSet.getString("password"))
                        .createDate(resultSet.getTimestamp("created_at"))
                        .blocked(resultSet.getBoolean("is_blocked"))
                        .admin(resultSet.getBoolean("is_admin"))
                        .build();
            }
        } catch (SQLException e) {
            log.error("Cannot find an account", e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return null;
    }

    @Override
    public boolean saveAccount(Account account) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int idx = 0;
        try {
            connection = dataSource.getConnection();
            setAutoCommit(connection, false);
            preparedStatement = connection.prepareStatement(Queries.Account.SAVE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(++idx, account.getSurname());
            preparedStatement.setString(++idx, account.getName());
            preparedStatement.setString(++idx, account.getPatronymic());
            preparedStatement.setString(++idx, account.getEmail());
            preparedStatement.setString(++idx, account.getPhone());
            preparedStatement.setString(++idx, account.getPassword());
            resultSet = preparedStatement.executeQuery();
            connection.commit();
            account.setId(resultSet.getInt("id"));
            account.setCreateDate(resultSet.getTimestamp("created_at"));
            account.setBlocked(resultSet.getBoolean("is_blocked"));
            account.setAdmin(resultSet.getBoolean("is_admin"));
            return true;
        } catch (SQLException e) {
            rollback(connection);
            log.error("Cannot save an account", e);
        } finally {
            setAutoCommit(connection, true);
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return false;
    }
}
