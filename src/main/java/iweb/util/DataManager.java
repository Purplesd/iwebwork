package iweb.util;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataManager {
    private static DruidDataSource dataSource;
    static {
        dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://47.113.196.69/shop?characterEncoding=utf8");
        dataSource.setUsername("huang");
        dataSource.setPassword("a12345");
        dataSource.setInitialSize(10);
        dataSource.setMaxActive(100);
    }
    public static Connection getConnection() throws SQLException {
        return  dataSource.getConnection();
    }

}
