package com.beck.beck_demos.crrg.data;

import com.beck.beck_demos.crrg.data_interfaces.iAlbum_DAO;
import com.beck.beck_demos.crrg.models.Album;
import com.beck.beck_demos.crrg.models.Album_VM;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.beck.beck_demos.crrg.data.Database.getConnection;

public class Album_DAO implements iAlbum_DAO{
  @Override
  /**
   * DAO Method to add Album objects
   * @param Album the Album to be added
   * @return number of records added
   * @author Jonathan Beck
   */
  public int add(Album _album) {
    int numRowsAffected=0;try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Album( ?)}")){
          statement.setString(1,_album.getAlbum_Name());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Album. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Album. Try again later");
    }
    return numRowsAffected;
  }

  /**
   * DAO Method to update Album objects
   * @param oldAlbum the Album to be updated
   * @param newAlbum the updated version of the Album
   * @return number of records updated
   * @author Jonathan Beck
   */
@Override
  public int update(Album oldAlbum, Album newAlbum) throws SQLException{
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_Album(? ,?,?)}"))
        {
          statement.setInt(1,oldAlbum.getAlbum_ID());
          statement.setString(2,oldAlbum.getAlbum_Name());
          statement.setString(3,newAlbum.getAlbum_Name());
          result=statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update Album . Try again later");
        }
      }
    }
    return result;
  }
  /**
   * DAO Method to retreive all Album objects
   * @return List of Album
   * @author Jonathan Beck
   */
  @Override
   public  List<Album_VM> getAllAlbum(int limit, int offset) {
    List<Album_VM> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_all_Album(?,?)}")) {
          statement.setInt(1,limit)
          ;statement.setInt(2,offset);
          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              Integer Album_ID = resultSet.getInt("Album_Album_ID");
              String Album_Name = resultSet.getString("Album_Album_Name");
              boolean Is_Active = resultSet.getBoolean("Album_Is_Active");
              int size=resultSet.getInt("Count");;
              Album _album = new Album( Album_ID, Album_Name, Is_Active);
              Album_VM _albumVM = new Album_VM(_album,size);
              result.add(_albumVM);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Albums. Try again later");
    }
    return result;
  }
  public Album_VM getAlbumByPrimaryKey(Album _album) throws SQLException{
    Album_VM _albumVM = null;
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_pk_Album(?)}")) {
        statement.setString(1, _album.getAlbum_ID().toString());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){Integer Album_ID = resultSet.getInt("Album_Album_ID");
            String Album_Name = resultSet.getString("Album_Album_Name");
            boolean Is_Active = resultSet.getBoolean("Album_Is_Active");
            int size=resultSet.getInt("Count");;
            Album result = new Album( Album_ID, Album_Name, Is_Active);
             _albumVM = new Album_VM(result,size);
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return _albumVM;
  }
  /**
  * DAO Method to retreive all Album objects
* @return List of Album
* @author Jonathan Beck
 */
  public  List<Album> getDistinctAlbumForDropdown() {
    List<Album> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_select_distinct_and_active_Album_for_dropdown()}")) {
          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              Integer Album_ID = resultSet.getInt("Album_ID");
              String Album_Name = resultSet.getString("Album_Name");
              Album _album = new Album( Album_ID, Album_Name);
              result.add(_album);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Albums. Try again later");
    }
    return result;
  }

 public int changeActivation(int albumID, int status) throws SQLException{
   int rowsAffected=0;
   try (Connection connection = getConnection()) {
     if (connection != null) {
       try (CallableStatement statement = connection.prepareCall("{CALL sp_change_active_Album( ?,?)}")){
         statement.setInt(1,albumID);
         statement.setInt(2,status);
         rowsAffected = statement.executeUpdate();
         if (rowsAffected == 0) {
           throw new RuntimeException("Could not change album status. Try again later");
         }
       }
     }
   } catch (SQLException e) {
     throw new RuntimeException("Could not Delete Album. Try again later");
   }
   return rowsAffected;
 }
  }


