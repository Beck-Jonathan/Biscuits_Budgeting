package com.beck.beck_demos.personal_project.data;
/// <summary>
///AUTHOR: Jonathan Beck
///<br />
///CREATED: 18/3/2024
///< br />
///An example class to show how code is expected to be written and documented.
///This is where a description of what your file is supposed to contain goes.
///e.g., "Class with helper methods for input validation.",
///Class that defines LanguageDAO Objects.
///</summary>
///< remarks>
///UPDATER: updater_name
///< br />
/// UPDATED: yyyy-MM-dd
/// < br />
/// Update comments go here, include method or methods were changed or added
/// A new remark should be added for each update.
///</remarks>
import com.beck.beck_demos.personal_project.models.Language;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.beck.beck_demos.personal_project.data.Database.getConnection;
public class LanguageDAO {

  public static int add(Language _language) {
    int numRowsAffected=0;try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Language( ?)}")){
          statement.setString(1,_language.getName());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Language. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Language. Try again later");
    }
    return numRowsAffected;
  }
  public static Language getLanguageByPrimaryKey(Language _language) throws SQLException{
    Language result = null;
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_pk_Language(?)}")) {
        statement.setString(1, _language.getLanguage_ID().toString());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){Integer Language_ID = resultSet.getInt("Language_ID");
            String Name = resultSet.getString("Name");
            boolean is_active = resultSet.getBoolean("is_active");
            result = new Language( Language_ID, Name, is_active);}
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }
  public static List<Language> getAllLanguage() {
    List<Language> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_all_Language()}")) {try(ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {Integer Language_ID = resultSet.getInt("Language_ID");
            String Name = resultSet.getString("Name");
            boolean is_active = resultSet.getBoolean("is_active");
            Language _language = new Language( Language_ID, Name, is_active);
            result.add(_language);
          }
        }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Languages. Try again later");
    }
    return result;}
  public static List<Language> getActiveLanguage() {
    List<Language> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_active_Language()}")) {try(ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {Integer Language_ID = resultSet.getInt("Language_ID");
            String Name = resultSet.getString("Name");
            boolean is_active = resultSet.getBoolean("is_active");
            Language _language = new Language( Language_ID, Name, is_active);
            result.add(_language);
          }
        }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Languages. Try again later");
    }
    return result;}

  public static int update(Language oldLanguage, Language newLanguage) throws SQLException{
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_Language(? ,?,?,?,?)}"))
        {
          statement.setInt(1,oldLanguage.getLanguage_ID());
          statement.setString(2,oldLanguage.getName());
          statement.setString(3,newLanguage.getName());
          statement.setBoolean(4,oldLanguage.getis_active());
          statement.setBoolean(5,newLanguage.getis_active());
          statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update Language . Try again later");
        }
      }
    }
    return result;
  }
  public static int deleteLanguage(int languageID) {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_Delete_Language( ?)}")){
          statement.setInt(1,languageID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Delete Language. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Delete Language. Try again later");
    }
    return rowsAffected;
  }
  public static int undeleteLanguage(int languageID) {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_unDelete_Language( ?)}")){
          statement.setInt(1,languageID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Restore Language. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Restore Language. Try again later");
    }
    return rowsAffected;
  }

}

