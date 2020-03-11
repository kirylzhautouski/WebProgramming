package db;

import db.exceptions.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {

    private static final Logger CONNECTION_POOL_LOGGER = LogManager.getLogger(ConnectionPool.class);

    private static final ConfigurationManager DB_CONFIG = ConfigurationManager.getInstance();
    private static final int CONNECTIONS_COUNT = 5;

    private BlockingQueue<Connection> connections;

    private static ConnectionPool INSTANCE = null;

    private ConnectionPool() throws ConnectionPoolException {
        CONNECTION_POOL_LOGGER.info("Constructing the only instance of connection pool");

        this.connections = new ArrayBlockingQueue<>(ConnectionPool.CONNECTIONS_COUNT);

        try {
            Class.forName(ConnectionPool.DB_CONFIG.getDriver());
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("Can't load database driver", e);
        }

        try {
            for (int i = 0; i < ConnectionPool.CONNECTIONS_COUNT; i++) {
                Connection connection = DriverManager.getConnection(ConnectionPool.DB_CONFIG.getUrl(),
                    ConnectionPool.DB_CONFIG.getUser(),
                    ConnectionPool.DB_CONFIG.getPassword());

                connections.add(connection);
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException("Can't connect to database", e);
        }
    }

    /**
     * return instance DBConnectorPool or create it
     *
     * @return instance of Singleton
     */
    public static synchronized ConnectionPool getInstance() throws ConnectionPoolException {
        CONNECTION_POOL_LOGGER.info("Connection pool instances is required");

        if (ConnectionPool.INSTANCE == null) {
            ConnectionPool.INSTANCE = new ConnectionPool();
        }

        return ConnectionPool.INSTANCE;
    }

    /**
     * Releases database pool of connections
     *
     * @throws ConnectionPoolException if properties file loading error
     */
    public synchronized void release() throws ConnectionPoolException {
        CONNECTION_POOL_LOGGER.info("Release connection pool");

        try {
            while (connections.size() > 0) {
                connections.take().close();
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException("Could not close database connection ", e);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Problem with concurrent queue", e);
        }
    }

    /**
     * Takes connection from pool
     *
     * @return connection
     * @throws ConnectionPoolException if something goes wrong
     */
    public synchronized Connection getConnection() throws ConnectionPoolException {
        CONNECTION_POOL_LOGGER.info("Connection requested");

        try {
            return connections.take();
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Failed to get connection from pool", e);
        }
    }

    /**
     * Return the connection to pool
     *
     * @param connection to add back to pool
     */
    public synchronized void releaseConnection(Connection connection) {
        CONNECTION_POOL_LOGGER.info("Connection released");

        try {
            if (connection.isClosed()) {
                Connection newConnection = DriverManager.getConnection(ConnectionPool.DB_CONFIG.getUrl(),
                    ConnectionPool.DB_CONFIG.getUser(),
                    ConnectionPool.DB_CONFIG.getPassword());
                connections.add(newConnection);
            } else {
                connections.add(connection);
            }
        } catch (SQLException e) {
        }

    }
}
