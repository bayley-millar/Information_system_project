package dao;

import java.sql.Connection;
import java.sql.SQLException;
import org.h2.jdbcx.JdbcConnectionPool;

public class JdbcConnection {

    private static final String username = "sa"; // H2 default
    private static final String password = ""; // H2 default
    private static JdbcConnectionPool pool;

    public static Connection getConnection(String url) {
        if (pool == null) {
            pool = JdbcConnectionPool.create(url, username, password);
        }
        try {
            return pool.getConnection();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
