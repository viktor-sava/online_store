package com.example.online_store.dao.mysql;

import com.example.online_store.dao.ProductDao;
import com.example.online_store.model.Product;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlProductDao extends MysqlDao implements ProductDao {

    private static final Logger log = Logger.getLogger(MysqlProductDao.class);

    public MysqlProductDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Product> findTop4ProductsByOrderByRatingDesc(String preferableLocale) {
        List<Product> productList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(Queries.Product.FIND_4_BY_ORDER_BY_RATING_DESC);
            setLocaleInStatement(preferableLocale, preparedStatement);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                productList.add(mapToModel(resultSet));
            }
        } catch (SQLException e) {
            log.error("Cannot find 4 product by rating", e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return productList;
    }

    @Override
    public List<Product> findTop4ProductsByOrderByDiscountDesc(String preferableLocale) {
        List<Product> productList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(Queries.Product.FIND_4_BY_ORDER_BY_DISCOUNT_DESC);
            setLocaleInStatement(preferableLocale, preparedStatement);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                productList.add(mapToModel(resultSet));
            }
        } catch (SQLException e) {
            log.error("Cannot find 4 product by discount", e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return productList;
    }

    @Override
    public List<Product> findTop4ProductsByOrderByLastUpdatedDesc(String preferableLocale) {
        List<Product> productList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(Queries.Product.FIND_4_BY_ORDER_BY_LAST_UPDATED_DESC);
            setLocaleInStatement(preferableLocale, preparedStatement);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                productList.add(mapToModel(resultSet));
            }
        } catch (SQLException e) {
            log.error("Cannot find 4 product by rating", e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return productList;
    }

    private void setLocaleInStatement(String preferableLocale, PreparedStatement preparedStatement) throws SQLException {
        int idx = 0;
        preparedStatement.setString(++idx, preferableLocale);
        preparedStatement.setString(++idx, applicationProperties.getProperty("default-locale"));
        preparedStatement.setString(++idx, preferableLocale);
    }

    private Product mapToModel(ResultSet resultSet) throws SQLException {
        return Product.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .picture(resultSet.getString("picture"))
                .price(resultSet.getBigDecimal("price"))
                .amount(resultSet.getLong("amount"))
                .discount(resultSet.getLong("discount"))
                .rating(resultSet.getLong("rating"))
                .evaluators(resultSet.getLong("evaluators"))
                .createDate(resultSet.getTimestamp("created_at"))
                .updateDate(resultSet.getTimestamp("updated_at"))
                .active(resultSet.getBoolean("is_active"))
                .build();
    }
}
