package com.fsd.connection;

import java.sql.Connection;

public class ConnectionFactory {

    private static final ConnectionFactory INSTANCE = new ConnectionFactory();

    private ConnectionFactory () {

    }

    private ConnectionFactory getInstance(){
        return INSTANCE;
    }

    private Connection getConnection(){
        return null;
    }
}
