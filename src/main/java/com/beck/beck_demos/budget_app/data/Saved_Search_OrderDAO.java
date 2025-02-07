package com.beck.beck_demos.budget_app.data;

import com.beck.beck_demos.budget_app.iData.iSaved_Search_OrderDAO;
import com.beck.beck_demos.budget_app.models.Saved_Search_Order;
import com.beck.beck_demos.budget_app.models.Saved_Search_Order_Line;
import com.beck.beck_demos.budget_app.models.Saved_Search_Order_VM;
import com.beck.beck_demos.budget_app.models.User;

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
  public  List<Saved_Search_Order_VM> getSaved_Search_OrderbyUser(Integer User_ID, int pagesize, int offset) {
    List<Saved_Search_Order_VM> result = new ArrayList<>();
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

              String User_User_Name = resultSet.getString("User_User_Name");
              User user = new User();
              user.setUser_Name(User_User_Name);
              Saved_Search_Order _saved_search_order = new Saved_Search_Order( Saved_Search_Order_ID, Owned_User, Nickname, Description, Last_Used, Last_Updated, Times_Ran);
              Saved_Search_Order_VM _saved_search_order_vm = new Saved_Search_Order_VM(_saved_search_order,user);
              result.add(_saved_search_order_vm);

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
  public Saved_Search_Order_VM getSaved_Search_OrderByPrimaryKey(Saved_Search_Order _saved_search_order) throws SQLException{
    Saved_Search_Order result = null;
    Saved_Search_Order_VM resultVM = null;
    List<Saved_Search_Order_Line> lines = new ArrayList<>();
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_pk_Saved_Search_Order(?)}")) {
        statement.setString(1, _saved_search_order.getSaved_Search_Order_ID().toString());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()) {
            Integer Saved_Search_Order_ID = resultSet.getInt("Saved_Search_Order_Saved_Search_Order_ID");
            Integer Owned_User = resultSet.getInt("Saved_Search_Order_Owned_User");
            String Nickname = resultSet.getString("Saved_Search_Order_Nickname");
            String Description = resultSet.getString("Saved_Search_Order_Description");
            Date Last_Used = resultSet.getDate("Saved_Search_Order_Last_Used");
            Date Last_Updated = resultSet.getDate("Saved_Search_Order_Last_Updated");
            Integer Times_Ran = resultSet.getInt("Saved_Search_Order_Times_Ran");
            Integer User_User_ID = resultSet.getInt("User_User_ID");
            String User_User_Name = resultSet.getString("User_User_Name");

            String User_Email = resultSet.getString("User_Email");
            User _user = new User();
            _user.setUser_ID(User_User_ID);
            _user.setUser_Name(User_User_Name);
            _user.setEmail(User_Email);
            result = new Saved_Search_Order(Saved_Search_Order_ID, Owned_User, Nickname, Description, Last_Used, Last_Updated, Times_Ran);
            resultVM = new Saved_Search_Order_VM(result, _user);
          }
        }
      }

      try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_Saved_Search_Order_Line_bySaved_Search_Order(?)}")) {
        statement.setInt(1,_saved_search_order.getSaved_Search_Order_ID());

        try(ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {Integer Saved_Search_Order_ID = resultSet.getInt("Saved_Search_Order_Line_Saved_Search_Order_ID");
            Integer Line_No = resultSet.getInt("Saved_Search_Order_Line_Line_No");
            String Category_ID = resultSet.getString("Saved_Search_Order_Line_Category_ID");
            Integer User_ID = resultSet.getInt("Saved_Search_Order_Line_User_ID");
            String Search_Phrase = resultSet.getString("Saved_Search_Order_Line_Search_Phrase");
            boolean Is_Active = resultSet.getBoolean("Saved_Search_Order_Line_Is_Active");

            Saved_Search_Order_Line _saved_search_order_line = new Saved_Search_Order_Line( Saved_Search_Order_ID, Line_No, Category_ID, User_ID, Search_Phrase, Is_Active);
            lines.add(_saved_search_order_line);

        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    }
      resultVM.setSaved_Search_Order_Lines(lines);
    return resultVM;
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

  /**
   * DAO Method to delete Saved_Search_Order objects
   * @param Saved_Search_Order_ID the Saved_Search_Order to be deleted
   * @return number of records deleted
   * @author Jonathan Beck
   */
 public int delete( int Saved_Search_Order_ID, int user_id) throws SQLException{
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_Delete_Saved_Search_Order( ?)}")){
          statement.setInt(1,Saved_Search_Order_ID);
          statement.setInt(2,user_id);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Delete Saved_Search_Order. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Delete Saved_Search_Order. Try again later");
    }
    return rowsAffected;
  }

  @Override
  public int addLine(Saved_Search_Order_Line _saved_search_order_line) throws SQLException {
    int numRowsAffected=0;try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Saved_Search_Order_Line( ?, ?, ?, ?, ?)}")){
          statement.setInt(1,_saved_search_order_line.getSaved_Search_Order_ID());
          statement.setInt(2,_saved_search_order_line.getLine_No());
          statement.setString(3,_saved_search_order_line.getCategory_ID());
          statement.setInt(4,_saved_search_order_line.getUser_ID());
          statement.setString(5,_saved_search_order_line.getSearch_Phrase());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Saved_Search_Order_Line. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Saved_Search_Order_Line. Try again later");
    }
    return numRowsAffected;
  }

  @Override
  public int updateLine(Saved_Search_Order_Line oldSaved_Search_Order_Line, Saved_Search_Order_Line newSaved_Search_Order_Line) throws SQLException {
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_Saved_Search_Order_Line(? ,? ,?,?,?,?,?,?,?)}"))
        {
          statement.setInt(1,oldSaved_Search_Order_Line.getSaved_Search_Order_ID());
          statement.setInt(2,oldSaved_Search_Order_Line.getLine_No());
          statement.setString(3,oldSaved_Search_Order_Line.getCategory_ID());
          statement.setString(4,newSaved_Search_Order_Line.getCategory_ID());
          statement.setInt(5,oldSaved_Search_Order_Line.getUser_ID());
          statement.setString(6,oldSaved_Search_Order_Line.getSearch_Phrase());
          statement.setString(7,newSaved_Search_Order_Line.getSearch_Phrase());
          statement.setBoolean(8,oldSaved_Search_Order_Line.getIs_Active());
          statement.setBoolean(9,newSaved_Search_Order_Line.getIs_Active());
          result=statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update Saved_Search_Order_Line . Try again later");
        }
      }
    }
    return result;
  }


}
