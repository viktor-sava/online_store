package com.example.online_store.dao.hsqldb;

import com.example.online_store.dao.AccountDao;
import com.example.online_store.dao.DAOFactory;
import com.example.online_store.model.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountTest {

    private static AccountDao accountDao;
    private static DataSource dataSource;

    @BeforeAll
    public static void beforeAll() throws SQLException {
        dataSource = mock(DataSource.class);
        when(dataSource.getConnection()).then((Answer<Connection>) invocationOnMock ->
                DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useOldAliasMetadataBehavior=true", "admin", "1234"));
        try {
            DAOFactory daoFactory = new MysqlTestDAOFactory(dataSource);
            accountDao = daoFactory.getAccountDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dropRecords();
    }

    private static void dropRecords() throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM account");
        statement.close();
        connection.close();
    }

    @Test
    public void testInsertFind() {
        Account account = Account.builder()
                .patronymic("v")
                .name("V")
                .surname("v")
                .password("v")
                .email("231@gmail.com")
                .phone("123412")
                .build();
        accountDao.insertAccount(account);
        accountDao.insertAccount(account);
        Assertions.assertNotNull(accountDao.findAccountByEmailOrPhone(account.getEmail(), null));
        Assertions.assertNotNull(accountDao.findAccountByEmailOrPhone(account.getEmail(), account.getPhone()));
        Assertions.assertNotNull(accountDao.findAccountByEmailOrPhone(null, account.getPhone()));
        Assertions.assertNotNull(account.getCreateDate());
        Assertions.assertNotEquals(0, account.getId());
    }

}
