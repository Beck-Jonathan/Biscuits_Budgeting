package com.beck.beck_demos.garden.data;

import com.beck.beck_demos.garden.model.Plant;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import static com.beck.beck_demos.garden.data.Database.getConnection;
public class PlantDAO {

  public static int add(Plant _plant) {
    int numRowsAffected=0;try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Plant( ?, ?, ?, ?, ?)}")){
          statement.setString(1,_plant.getPlant_Name());
          statement.setString(2,_plant.getGarden_ID());
          statement.setString(3,_plant.getPlant_depth());
          statement.setString(4,_plant.getPlant_Direction());
          statement.setInt(5,_plant.getGermination_Time());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Plant. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Plant. Try again later");
    }
    return numRowsAffected;
  }
  public static Plant getPlantByPrimaryKey(Plant _plant) throws SQLException{
    Plant result = null;
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_pk_Plant(?)}")) {
        statement.setString(1, _plant.getPlant_ID().toString());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){Integer Plant_ID = resultSet.getInt("Plant_ID");
            String Plant_Name = resultSet.getString("Plant_Name");
            String Garden_ID = resultSet.getString("Garden_ID");
            String Plant_depth = resultSet.getString("Plant_depth");
            String Plant_Direction = resultSet.getString("Plant_Direction");
            Integer Germination_Time = resultSet.getInt("Germination_Time");
            boolean is_active = resultSet.getBoolean("is_active");
            result = new Plant( Plant_ID, Plant_Name, Garden_ID, Plant_depth, Plant_Direction, Germination_Time, is_active);}
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }
  public static List<Plant> getAllPlant() {
    List<Plant> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_all_Plant()}")) {try(ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {Integer Plant_ID = resultSet.getInt("Plant_ID");
            String Plant_Name = resultSet.getString("Plant_Name");
            String Garden_ID = resultSet.getString("Garden_ID");
            String Plant_depth = resultSet.getString("Plant_depth");
            String Plant_Direction = resultSet.getString("Plant_Direction");
            Integer Germination_Time = resultSet.getInt("Germination_Time");
            boolean is_active = resultSet.getBoolean("is_active");
            Plant _plant = new Plant( Plant_ID, Plant_Name, Garden_ID, Plant_depth, Plant_Direction, Germination_Time, is_active);
            result.add(_plant);
          }
        }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Plants. Try again later");
    }
    return result;}
  public static List<Plant> getActivePlant() {
    List<Plant> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_active_Plant()}")) {try(ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {Integer Plant_ID = resultSet.getInt("Plant_ID");
            String Plant_Name = resultSet.getString("Plant_Name");
            String Garden_ID = resultSet.getString("Garden_ID");
            String Plant_depth = resultSet.getString("Plant_depth");
            String Plant_Direction = resultSet.getString("Plant_Direction");
            Integer Germination_Time = resultSet.getInt("Germination_Time");
            boolean is_active = resultSet.getBoolean("is_active");
            Plant _plant = new Plant( Plant_ID, Plant_Name, Garden_ID, Plant_depth, Plant_Direction, Germination_Time, is_active);
            result.add(_plant);
          }
        }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Plants. Try again later");
    }
    return result;}

  public static int update(Plant oldPlant, Plant newPlant) throws SQLException{
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_Plant(? ,?,?,?,?,?,?,?,?,?,?,?,?)}"))
        {
          statement.setInt(1,oldPlant.getPlant_ID());
          statement.setString(2,oldPlant.getPlant_Name());
          statement.setString(3,newPlant.getPlant_Name());
          statement.setString(4,oldPlant.getGarden_ID());
          statement.setString(5,newPlant.getGarden_ID());
          statement.setString(6,oldPlant.getPlant_depth());
          statement.setString(7,newPlant.getPlant_depth());
          statement.setString(8,oldPlant.getPlant_Direction());
          statement.setString(9,newPlant.getPlant_Direction());
          statement.setInt(10,oldPlant.getGermination_Time());
          statement.setInt(11,newPlant.getGermination_Time());
          statement.setBoolean(12,oldPlant.getis_active());
          statement.setBoolean(13,newPlant.getis_active());
          result=statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update Plant . Try again later");
        }
      }
    }
    return result;
  }
  public static int deletePlant(int plantID) {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_Delete_Plant( ?)}")){
          statement.setInt(1,plantID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Delete Plant. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Delete Plant. Try again later");
    }
    return rowsAffected;
  }
  public static int undeletePlant(int plantID) {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_unDelete_Plant( ?)}")){
          statement.setInt(1,plantID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Restore Plant. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Restore Plant. Try again later");
    }
    return rowsAffected;
  }

}


