package com.beck.beck_demos.budget_app.data;



import com.beck.beck_demos.budget_app.iData.iSuggestionDAO;
import com.beck.beck_demos.budget_app.models.Suggestion;
import com.beck.beck_demos.budget_app.models.Suggestion_VM;
import com.beck.beck_demos.budget_app.models.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.beck.beck_demos.budget_app.data.Database.getConnection;
public class SuggestionDAO implements iSuggestionDAO {
  @Override
  public int add(Suggestion _suggestion) throws SQLException {
    int numRowsAffected=0;
    try (Connection connection = getConnection()) {

      if (connection != null) {
        connection.setSchema("schedule");
        connection.setCatalog("schedule");
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Suggestion( ?, ?, ?)}")){
          statement.setString(1,_suggestion.getUser_ID());
          statement.setString(2,_suggestion.getApplication_Name());
          statement.setString(3,_suggestion.getcontent());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Suggestion. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Suggestion. Try again later");
    }
    return numRowsAffected;
  }


  @Override
  public int deleteSuggestion(String Suggestion_ID) throws SQLException {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {

      if (connection != null) {
        connection.setSchema("schedule");
        connection.setCatalog("schedule");
        try (CallableStatement statement = connection.prepareCall("{CALL sp_Delete_Suggestion( ?)}")){
          statement.setString(1,Suggestion_ID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Delete Suggestion. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Delete Suggestion. Try again later");
    }
    return rowsAffected;
  }


  @Override
  public List<Suggestion_VM> getAllSuggestion(int offset, int limit, String search, String User_ID, String _Application_Name) throws SQLException {
    List<Suggestion_VM> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        connection.setSchema("schedule");
        connection.setCatalog("schedule");

        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_all_Suggestion(?,?,?,?,?)}")) {
          statement.setInt(1,limit)
          ;statement.setInt(2,offset);
          statement.setString(3,search);
          statement.setString(4,User_ID);
          statement.setString(5,_Application_Name);
          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {String Suggestion_ID = resultSet.getString("Suggestion_Suggestion_ID");
              String _User_ID = resultSet.getString("Suggestion_User_ID");
              String Application_Name = resultSet.getString("Suggestion_Application_Name");
              String content = resultSet.getString("Suggestion_content");
              String User_User_ID = resultSet.getString("User_User_ID");
              String User_User_Name = resultSet.getString("User_User_Name");
              //String User_User_PW = resultSet.getString("User_User_PW");
              String User_Email = resultSet.getString("User_Email");
              Suggestion _suggestion = new Suggestion( Suggestion_ID, _User_ID, Application_Name, content);
              User user = new User("",User_User_Name,null,User_Email);
              Suggestion_VM suggestionVM = new Suggestion_VM(_suggestion,user);
              result.add(suggestionVM);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Suggestions. Try again later");
    }
    return result;
  }


  @Override
  public Suggestion_VM getSuggestionByPrimaryKey(Suggestion _suggestion) throws SQLException {
    Suggestion_VM result = null;
    try(Connection connection = getConnection()) {
      if (connection != null) {
        connection.setSchema("schedule");
        connection.setCatalog("schedule");
        try (CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_pk_Suggestion(?)}")) {
          statement.setString(1, _suggestion.getSuggestion_ID().toString());

          try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
              String Suggestion_ID = resultSet.getString("Suggestion_Suggestion_ID");
              String User_ID = resultSet.getString("Suggestion_User_ID");
              String Application_Name = resultSet.getString("Suggestion_Application_Name");
              String content = resultSet.getString("Suggestion_content");
              String User_User_ID = resultSet.getString("User_User_ID");
              String User_User_Name = resultSet.getString("User_User_Name");
              String User_User_PW = resultSet.getString("User_User_PW");
              String User_Email = resultSet.getString("User_Email");
              Suggestion suggestion = new Suggestion(Suggestion_ID, User_ID, Application_Name, content);
              User user = new User("", User_User_Name, null, User_Email);
              result = new Suggestion_VM(suggestion, user);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  @Override
  public int getSuggestionCount(String Search_term, String User_ID, String Application_Name) throws SQLException {
    int result =0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        connection.setSchema("schedule");
        connection.setCatalog("schedule");
        try(CallableStatement statement = connection.prepareCall("{CALL sp_count_by_all_Suggestion(?,?,?)}")) {
          statement.setString(1,Search_term);
          statement.setString(2,User_ID);
          statement.setString(3,Application_Name);
          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              result = resultSet.getInt(1);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Suggestions. Try again later");
    }
    return result;
  }
}
