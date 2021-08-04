package com.fsd.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static Connection INSTANCE;

    private ConnectionFactory () {}

    public static Connection getConnection() {
        if(INSTANCE == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                INSTANCE = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/ecomfsd","root","root");
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            }
        }
        return INSTANCE;
    }

    public static void closeConnection() {
        if(INSTANCE != null) {
            try {
                INSTANCE.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
