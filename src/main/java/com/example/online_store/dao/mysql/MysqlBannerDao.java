package com.example.online_store.dao.mysql;

import com.example.online_store.dao.BannerDao;
import com.example.online_store.model.Banner;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MysqlBannerDao extends MysqlDao implements BannerDao {

    private static final Logger log = Logger.getLogger(MysqlBannerDao.class);

    public MysqlBannerDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Banner> findAllBanners() {
        List<Banner> bannerList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(Queries.Banner.FIND_ALL);
            while (resultSet.next()) {
                bannerList.add(Banner.builder()
                        .id(resultSet.getInt("id"))
                        .picture(resultSet.getString("picture"))
                        .categoryId(resultSet.getInt("category_id"))
                        .build());
            }
        } catch (SQLException e) {
            log.error("Cannot find all banners", e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
        return bannerList;
    }
}
