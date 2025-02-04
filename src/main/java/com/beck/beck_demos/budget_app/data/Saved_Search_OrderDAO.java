package com.beck.beck_demos.budget_app.data;

import com.beck.beck_demos.budget_app.iData.iSaved_Search_OrderDAO;
import com.beck.beck_demos.budget_app.models.Saved_Search_Order;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.beck.beck_demos.budget_app.data.Database.getConnection;

public class Saved_Search_OrderDAO implements iSaved_Search_OrderDAO {
  /**
   * DAO Method to retreive by Foreign Key Saved_Search_Order objects
   * @return List of Saved_Search_Order
   * @author Jonathan Beck
   */

  @Override
  public  List<Saved_Search_Order> getSaved_Search_OrderbyUser(Integer User_ID,int pagesize, int offset) {
    List<Saved_Search_Order> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_Saved_Search_Order_by_User(?,?,?)}")) {
          statement.setInt(1,User_ID);
          statement.setInt(2,pagesize);
          statement.setInt(3,offset);
          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {Integer Saved_Search_Order_ID = resultSet.getInt("Saved_Search_Order_Saved_Search_Order_ID");
              Integer Owned_User = resultSet.getInt("Saved_Search_Order_Owned_User");
              String Nickname = resultSet.getString("Saved_Search_Order_Nickname");
              String Description = resultSet.getString("Saved_Search_Order_Description");
              Date Last_Used = resultSet.getDate("Saved_Search_Order_Last_Used");
              Date Last_Updated = resultSet.getDate("Saved_Search_Order_Last_Updated");
              Integer Times_Ran = resultSet.getInt("Saved_Search_Order_Times_Ran");
              Integer User_User_ID = resultSet.getInt("User_User_ID");
              String User_User_Name = resultSet.getString("User_User_Name");
              String User_User_PW = resultSet.getString("User_User_PW");
              String User_Email = resultSet.getString("User_Email");
              Saved_Search_Order _saved_search_order = new Saved_Search_Order( Saved_Search_Order_ID, Owned_User, Nickname, Description, Last_Used, Last_Updated, Times_Ran);
              result.add(_saved_search_order);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Saved_Search_Orders. Try again later");
    }
    return result;
  }



}
