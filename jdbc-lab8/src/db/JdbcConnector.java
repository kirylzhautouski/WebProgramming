package db;

import db.exceptions.JDBCConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class used to establish connection with database
 */
public class JdbcConnector {

    /** Connection */
    private Connection connection;

    /**
     * Getter for the connection
     * @return connection
     * @throws JDBCConnectionException
     */
    public Connection getConnection() throws JDBCConnectionException {
        ConfigurationManager dbConfig = ConfigurationManager.getInstance();
        try {
            Class.forName(dbConfig.getDriver());
            connection = DriverManager.getConnection(dbConfig.getUrl(), dbConfig.getUser(),
                dbConfig.getPassword());
        } catch (ClassNotFoundException e) {
            throw new JDBCConnectionException("Can't load database driver", e);
        } catch (SQLException e) {
            throw new JDBCConnectionException("Can't connect to database", e);
        }

        if (connection == null) {
            throw new JDBCConnectionException("Driver type is not correct in URL");
        }

        return connection;
    }

    /**
     * Closes connection
     * @throws JDBCConnectionException
     */
    public void close() throws JDBCConnectionException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new JDBCConnectionException("Can't close connection", e);
            }
        }
    }


}
