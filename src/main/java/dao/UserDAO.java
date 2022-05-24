package dao;

import business_layer.User;
import connection.ConnectionFactory;

import javax.swing.*;
import java.sql.*;

public class UserDAO {


    public void insertUser(User user) {
        Connection connection = ConnectionFactory.getConnection();
        String insertQuery = "INSERT INTO user (username, password, type) VALUES (?, ?, ?)";
        PreparedStatement s = null;
        try {
            s = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setString(1, user.getUsername());
            s.setString(2, user.getPassword());
            s.setInt(3, user.getType());
            s.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "This username aldready exists!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(s);
            ConnectionFactory.close(connection);
        }
    }

    public String selectQuery(String username, String password) {
        Connection connection = ConnectionFactory.getConnection();
        String query = "SELECT * FROM user WHERE username='" + username + "' AND password='" + password + "'";
        PreparedStatement s = null;
        ResultSet rs = null;
        String stringToReturn = null;
        try {
            s = connection.prepareStatement(query);
            rs = s.executeQuery();
            if (rs.next()) {
                if (rs.getInt("type") == 0) {
                    stringToReturn = rs.getString("username");
                } else if (rs.getInt("type") == 1) {
                    stringToReturn = "admin";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(s);
            ConnectionFactory.close(connection);
        }
        return stringToReturn;
    }

}
