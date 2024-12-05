package com.beck.beck_demos.garden.data;

import com.beck.beck_demos.garden.model.Garden;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.beck.beck_demos.garden.data.Database.getConnection;
public class GardenDAO {

  public static int add(Garden _garden) {
    int numRowsAffected=0;try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Garden( ?)}")){
          statement.setString(1,_garden.getGarden_Name());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Garden. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Garden. Try again later");
    }
    return numRowsAffected;
  }
  public static Garden getGardenByPrimaryKey(Garden _garden) throws SQLException{
    Garden result = null;
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_pk_Garden(?)}")) {
        statement.setString(1, _garden.getGarden_id().toString());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){Integer Garden_id = resultSet.getInt("Garden_id");
            String Garden_Name = resultSet.getString("Garden_Name");
            boolean is_active = resultSet.getBoolean("is_active");
            result = new Garden( Garden_id, Garden_Name, is_active);}
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }
  public static List<Garden> getAllGarden() {
    List<Garden> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_all_Garden()}")) {try(ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {Integer Garden_id = resultSet.getInt("Garden_id");
            String Garden_Name = resultSet.getString("Garden_Name");
            boolean is_active = resultSet.getBoolean("is_active");
            Garden _garden = new Garden( Garden_id, Garden_Name, is_active);
            result.add(_garden);
          }
        }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Gardens. Try again later");
    }
    return result;}
  public static List<Garden> getActiveGarden() {
    List<Garden> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_active_Garden()}")) {try(ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {Integer Garden_id = resultSet.getInt("Garden_id");
            String Garden_Name = resultSet.getString("Garden_Name");
            boolean is_active = resultSet.getBoolean("is_active");
            Garden _garden = new Garden( Garden_id, Garden_Name, is_active);
            result.add(_garden);
          }
        }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Gardens. Try again later");
    }
    return result;}

  public static int update(Garden oldGarden, Garden newGarden) throws SQLException{
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_Garden(? ,?,?,?,?)}"))
        {
          statement.setInt(1,oldGarden.getGarden_id());
          statement.setString(2,oldGarden.getGarden_Name());
          statement.setString(3,newGarden.getGarden_Name());
          statement.setBoolean(4,oldGarden.getis_active());
          statement.setBoolean(5,newGarden.getis_active());
          result=statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update Garden . Try again later");
        }
      }
    }
    return result;
  }
  public static int deleteGarden(int gardenID) {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_Delete_Garden( ?)}")){
          statement.setInt(1,gardenID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Delete Garden. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Delete Garden. Try again later");
    }
    return rowsAffected;
  }
  public static int undeleteGarden(int gardenID) {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_unDelete_Garden( ?)}")){
          statement.setInt(1,gardenID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Restore Garden. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Restore Garden. Try again later");
    }
    return rowsAffected;
  }

}


