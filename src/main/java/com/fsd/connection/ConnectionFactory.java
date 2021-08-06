package com.fsd.connection;

import com.fsd.configs.ApplicationConfigs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static ConnectionFactory INSTANCE;
    private ConnectionFactory () {}
    public static ConnectionFactory getInstance() {
        if(INSTANCE == null) {
            return new ConnectionFactory();
        }
        return INSTANCE;
    }
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(ApplicationConfigs.getInstance().get("jdbc.driver"));
            connection = DriverManager.getConnection(
                    ApplicationConfigs.getInstance().get("jdbc.url"),
                    ApplicationConfigs.getInstance().get("jdbc.username"),
                    ApplicationConfigs.getInstance().get("jdbc.password"));
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
