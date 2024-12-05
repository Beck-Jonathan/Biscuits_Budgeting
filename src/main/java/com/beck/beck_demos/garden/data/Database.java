package com.beck.beck_demos.garden.data;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
  public static Connection getConnection() {
    Connection connection;
    Dotenv dotenv = Dotenv.load();
    String db_driver = dotenv.get("DB_FULL_DRIVER");
    String db_connection = dotenv.get("DB_CONNECTION_STRING3");
    String db_user = dotenv.get("DB_USER");
    String db_password = dotenv.get("DB_PASSWORD");
    try {
      Class.forName(db_driver);
    } catch (ClassNotFoundException e) {
      System.out.println("driver not found");
    }
    try {
      connection = DriverManager.getConnection(db_connection, db_user, db_password);
      if (connection.isValid(2)) {
        System.out.println("connected!");
        return connection;
      }
    } catch (SQLException e) {
      System.out.println("unable to connect");
    }
    return null;

  }

}
