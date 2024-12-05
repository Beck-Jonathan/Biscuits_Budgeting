package com.beck.beck_demos.personal_project.data;

import com.beck.beck_demos.personal_project.models.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.beck.beck_demos.personal_project.data.Database.getConnection;

public class PasswordResetDAO {
  public static boolean add(String username, String reset_id) throws SQLException {
    boolean result = false;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_password_reset(?,? )}")) {
          statement.setString(1, username);

          statement.setString(2, reset_id);

          try {
            int value = statement.executeUpdate();
            result = (value == 1);
          } catch (SQLException e) {
            throw new RuntimeException("Could update password. Try again later");
          }
        }
      }

    }
    return result;
  }

  public static boolean verify(String username, String reset_id) throws SQLException {
    boolean result = false;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_verify_password_reset(?,? )}")) {
          statement.setString(1, username);

          statement.setString(2, reset_id);

          try {
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
              Integer count = resultSet.getInt("count");
              result = (count==1);
            }
          } catch (SQLException e) {
            throw new RuntimeException("Could update password. Try again later");
          }
        }
      }

    }
    return result;
  }

  public static boolean delete(String username, String reset_id) throws SQLException {
    boolean result = false;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_delete_password_reset(?,? )}")) {
          statement.setString(1, username);

          statement.setString(2, reset_id);

          try {
            int value = statement.executeUpdate();
            result = (value == 1);
          } catch (SQLException e) {
            throw new RuntimeException("Could delete password. Try again later");
          }
        }
      }

    }
    return result;
  }

}
