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
  /**
   * DAO Method to add Saved_Search_Order objects
   * @param _saved_search_order the Saved_Search_Order to be added
   * @return number of records added
   * @author Jonathan Beck
   */
  public int add(Saved_Search_Order _saved_search_order) {
    int numRowsAffected=0;try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Saved_Search_Order( ?, ?, ?, ?, ?, ?)}")){
          statement.setInt(1,_saved_search_order.getOwned_User());
          statement.setString(2,_saved_search_order.getNickname());
          statement.setString(3,_saved_search_order.getDescription());
          java.sql.Date Last_Used = new java.sql.Date(_saved_search_order.getLast_Used().getTime());
          statement.setDate(4,Last_Used);
          java.sql.Date Last_Updated = new java.sql.Date(_saved_search_order.getLast_Updated().getTime());
          statement.setDate(5,Last_Updated);

          statement.setInt(6,_saved_search_order.getTimes_Ran());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Saved_Search_Order. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Saved_Search_Order. Try again later");
    }
    return numRowsAffected;
  }
  /**
  * DAO Method to retreive by ID Saved_Search_Order objects
* @param _saved_search_order the Saved_Search_Order to be retreived
* @return List of Saved_Search_Order
* @author Jonathan Beck
 */
  public Saved_Search_Order getSaved_Search_OrderByPrimaryKey(Saved_Search_Order _saved_search_order) throws SQLException{
    Saved_Search_Order result = null;
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_pk_Saved_Search_Order(?)}")) {
        statement.setString(1, _saved_search_order.getSaved_Search_Order_ID().toString());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){Integer Saved_Search_Order_ID = resultSet.getInt("Saved_Search_Order_Saved_Search_Order_ID");
            Integer Owned_User = resultSet.getInt("Saved_Search_Order_Owned_User");
            String Nickname = resultSet.getString("Saved_Search_Order_Nickname");
            String Description = resultSet.getString("Saved_Search_Order_Description");
            Date Last_Used = resultSet.getDate("Saved_Search_Order_Last_Used");
            Date Last_Updated = resultSet.getDate("Saved_Search_Order_Last_Updated");
            Integer Times_Ran = resultSet.getInt("Saved_Search_Order_Times_Ran");
            Integer User_User_ID = resultSet.getInt("User_User_ID");
            String User_User_Name = resultSet.getString("User_User_Name");

            String User_Email = resultSet.getString("User_Email");
            result = new Saved_Search_Order( Saved_Search_Order_ID, Owned_User, Nickname, Description, Last_Used, Last_Updated, Times_Ran);}
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }
  /**
   * DAO Method to update Saved_Search_Order objects
   * @param oldSaved_Search_Order the Saved_Search_Order to be updated
   * @param newSaved_Search_Order the updated version of the Saved_Search_Order
   * @return number of records updated
   * @author Jonathan Beck
   */

  public int update(Saved_Search_Order oldSaved_Search_Order, Saved_Search_Order newSaved_Search_Order) throws SQLException{
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_Saved_Search_Order(?,?,?,?,?,?)}"))
        {
          statement.setInt(1,oldSaved_Search_Order.getSaved_Search_Order_ID());
          statement.setInt(2,oldSaved_Search_Order.getOwned_User());
          statement.setString(3,oldSaved_Search_Order.getNickname());
          statement.setString(4,newSaved_Search_Order.getNickname());
          statement.setString(5,oldSaved_Search_Order.getDescription());
          statement.setString(6,newSaved_Search_Order.getDescription());

          result=statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update Saved_Search_Order . Try again later");
        }
      }
    }
    return result;
  }



}
