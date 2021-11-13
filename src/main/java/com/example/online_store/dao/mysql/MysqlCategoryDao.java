package com.example.online_store.dao.mysql;

import com.example.online_store.dao.CategoryDao;
import com.example.online_store.model.Category;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlCategoryDao extends MysqlDao implements CategoryDao {

    private static final Logger log = Logger.getLogger(MysqlCategoryDao.class);

    public MysqlCategoryDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Category> findTop5CategoriesByOrderByLastModifiedDesc(String preferableLocale) {
        List<Category> categoryList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(Queries.Category.FIND_TOP_5_BY_LOCALE_BY_ORDER_BY_LAST_MODIFIED_DESC);
            int idx = 0;
            preparedStatement.setString(++idx, preferableLocale);
            preparedStatement.setString(++idx, applicationProperties.getProperty("default-locale"));
            preparedStatement.setString(++idx, preferableLocale);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                categoryList.add(Category.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .modifiedDate(resultSet.getTimestamp("modified_at"))
                        .picture(resultSet.getString("picture"))
                        .build()
                );
            }
        } catch (SQLException e) {
            log.error("Cannot find 5 categories", e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return categoryList;
    }
}