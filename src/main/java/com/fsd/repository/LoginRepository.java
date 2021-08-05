package com.fsd.repository;

import com.fsd.connection.ConnectionFactory;
import com.fsd.model.User;
import com.fsd.utils.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepository {

    public boolean login(String userId, String password) {
        Connection conn = ConnectionFactory.getInstance().getConnection();
        try {
            PreparedStatement statement = conn.prepareStatement(SQL.LOGIN_SQL);
            statement.setString(1, userId);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.getInstance().closeConnection(conn);
        }
        return false;
    }

    public boolean register(User user) {
        Connection conn = ConnectionFactory.getInstance().getConnection();
        try {
            PreparedStatement statement = conn.prepareStatement(SQL.REGISTER_SQL);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getContactNumber());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getAddress());

            int update = statement.executeUpdate();

            if(update == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.getInstance().closeConnection(conn);
        }
        return false;
    }
}