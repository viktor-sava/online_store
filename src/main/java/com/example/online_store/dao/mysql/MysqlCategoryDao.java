package com.example.online_store.dao.mysql;

import com.example.online_store.dao.CategoryDao;
import com.example.online_store.model.Category;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
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
                categoryList.add(mapToCategory(resultSet));
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

    @Override
    public List<Category> findCategoriesByParentIdByOrderName(String preferableLocale, int parentId) {
        List<Category> categoryList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int idx = 0;
        try {
            connection = dataSource.getConnection();
            if (parentId != 0) {
                preparedStatement = connection.prepareStatement(Queries.Category.FIND_CATEGORIES_BY_PARENT_ID_BY_ORDER_NAME);
                preparedStatement.setString(++idx, preferableLocale);
                preparedStatement.setInt(++idx, parentId);
                preparedStatement.setString(++idx, applicationProperties.getProperty("default-locale"));
                preparedStatement.setInt(++idx, parentId);
                preparedStatement.setString(++idx, preferableLocale);
                preparedStatement.setInt(++idx, parentId);
            } else {
                preparedStatement = connection.prepareStatement(Queries.Category.FIND_CATEGORIES_BY_PARENT_ID_BY_ORDER_NAME_NULL);
                preparedStatement.setString(++idx, preferableLocale);
//                preparedStatement.setInt(++idx, parentId);
                preparedStatement.setString(++idx, applicationProperties.getProperty("default-locale"));
//                preparedStatement.setInt(++idx, parentId);
                preparedStatement.setString(++idx, preferableLocale);
//                preparedStatement.setInt(++idx, parentId);
            }
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                categoryList.add(mapToCategory(resultSet));
            }
        } catch (SQLException e) {
            log.error("Cannot find categories by parent_id sorted by name", e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return categoryList;
    }

    private Category mapToCategory(ResultSet resultSet) throws SQLException {
        return Category.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .modifiedDate(resultSet.getTimestamp("modified_at"))
                .picture(resultSet.getString("picture"))
                .build();
    }

    private void setParentId(PreparedStatement preparedStatement, Integer idx, int parentId) throws SQLException {
        if (parentId != 0) {
            preparedStatement.setInt(++idx, parentId);
        }
    }
}