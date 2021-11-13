package com.example.online_store.dao.mysql;

import com.example.online_store.dao.LanguageDao;
import com.example.online_store.model.Language;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MysqlLanguageDao extends MysqlDao implements LanguageDao {


    private static final Logger log = Logger.getLogger(MysqlLanguageDao.class);

    public MysqlLanguageDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Language> findAllLanguages() {
        List<Language> languageList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(Queries.Language.FIND_ALL);
            while (resultSet.next()) {
                languageList.add(Language.builder()
                        .id(resultSet.getInt("id"))
                        .shortName(resultSet.getString("short_name"))
                        .fullName(resultSet.getString("full_name"))
                        .build());
            }
        } catch (SQLException e) {
            log.error("Cannot find language by short_name", e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
        return languageList;
    }
}
