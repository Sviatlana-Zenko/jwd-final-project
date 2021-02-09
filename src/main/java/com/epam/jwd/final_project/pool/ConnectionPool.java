package com.epam.jwd.final_project.pool;

import com.epam.jwd.final_project.domain.ApplicationProperties;
import com.epam.jwd.final_project.util.PropertyReaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public enum ConnectionPool {

    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionPool.class);
    private BlockingQueue<ProxyConnection> availableConnections;
    private Queue<ProxyConnection> takenConnections;
    private final ApplicationProperties properties = PropertyReaderUtil.getApplicationProperties();
    private final Integer defaultPoolSize = properties.getDefaultPoolSize();
    private final Integer maxPoolSize = properties.getMaxPoolSize();
    private final Integer connectionsToAdd = 10;
    private Integer currentPoolSize = defaultPoolSize;
    public ProxyConnection testConnection = getTestConnection();

    ConnectionPool() {
        availableConnections = new LinkedBlockingDeque<>(defaultPoolSize);
        takenConnections = new ArrayDeque<>();
        registerDriver();
        initConnections(defaultPoolSize);
    }

    public ProxyConnection getAvailableConnection() {
        ProxyConnection connection = null;

        if (availableConnections.size() == 0 &&
                currentPoolSize + connectionsToAdd <= maxPoolSize) {
            initConnections(connectionsToAdd);
            currentPoolSize += connectionsToAdd;
        }

        try {
            connection = availableConnections.take();
            takenConnections.offer(connection);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }

        return connection;
    }

    public void releaseConnection(Connection connection) {
        try {
            if (!connection.getMetaData().getURL().equals(properties.getTestDbUrl())){
                if (connection.getClass().getSimpleName()
                        .equals(ProxyConnection.class.getSimpleName())) {
                    takenConnections.remove(connection);
                    availableConnections.offer((ProxyConnection) connection);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void destroyConnectionPool() {
        for (int i = 0; i < availableConnections.size(); i++) {
            try {
                availableConnections.take().closeCompletely();
            } catch (SQLException | InterruptedException e) {
                LOGGER.error(e.getMessage());
            }
        }

        try {
            testConnection.closeCompletely();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        deregisterDrivers();
    }

    private void registerDriver(){
        final String driverName = "com.mysql.cj.jdbc.Driver";

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void initConnections(Integer connectionsToInit) {
        final String dbUrl = properties.getDbUrl();
        final String dbUserName = properties.getDbUserName();
        final String dbUserPassword = properties.getDbUserPassword();

        for (int i = 0; i < connectionsToInit; i++) {
            try {
                availableConnections.add(new ProxyConnection(DriverManager.getConnection(
                        dbUrl, dbUserName, dbUserPassword)));
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    private ProxyConnection getTestConnection(){
        ProxyConnection testConnection = null;
        final String testDbUrl = properties.getTestDbUrl();
        final String dbUserName = properties.getDbUserName();
        final String dbUserPassword = properties.getDbUserPassword();

        try {
            registerDriver();
            testConnection = new ProxyConnection(DriverManager.getConnection(
                    testDbUrl, dbUserName, dbUserPassword));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return testConnection;
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        });
    }

}
