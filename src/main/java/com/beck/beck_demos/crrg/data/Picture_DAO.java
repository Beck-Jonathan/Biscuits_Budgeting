package com.beck.beck_demos.crrg.data;
import com.beck.beck_demos.crrg.data_interfaces.iPicture_DAO;
import com.beck.beck_demos.crrg.models.Album;
import com.beck.beck_demos.crrg.models.Contributor;
import com.beck.beck_demos.crrg.models.Picture;
import com.beck.beck_demos.crrg.models.Picture_VM;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import java.sql.SQLException;
import java.util.List;
import static com.beck.beck_demos.crrg.data.Database.getConnection;

public class Picture_DAO implements iPicture_DAO{

  /**
   * DAO Method to add Picture objects
   * @param _picture the Picture to be added
   * @return number of records added
   * @author Jonathan Beck
   */
  @Override
  public int add(Picture _picture) {
    int numRowsAffected=0;try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Picture( ?, ?,? ,?,?,?)}")){
          statement.setInt(1,_picture.getAlbum_ID());
          statement.setInt(2,_picture.getContributor_ID());
          statement.setString(3,_picture.getWeb_Address());
          statement.setString(4,_picture.getDescription());
          statement.setBoolean(5,_picture.getis_Approved());
          statement.setBoolean(6,_picture.getIs_Active());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Picture. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Picture. Try again later");
    }
    return numRowsAffected;
  }

  /**
   * DAO Method to retreive by Foreign Key Picture objects
   * @return List of Picture
   * @author Jonathan Beck
   */




  /**
   * DAO Method to update Picture objects
   * @param oldPicture the Picture to be updated
   * @param newPicture the updated version of the Picture
   * @return number of records updated
   * @author Jonathan Beck
   */
  @Override
  public int update(Picture oldPicture, Picture newPicture) throws SQLException{
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_Picture(?,?,?,?,?,?,?,?,?,?,?)}"))
        {
          statement.setInt(1,oldPicture.getPicture_ID());
          statement.setInt(2,oldPicture.getAlbum_ID());
          statement.setInt(3,newPicture.getAlbum_ID());
          statement.setInt(4,oldPicture.getContributor_ID());
          statement.setInt(5,newPicture.getContributor_ID());
          statement.setString(6,oldPicture.getWeb_Address());
          statement.setString(7,newPicture.getWeb_Address());
          statement.setString(8,oldPicture.getDescription());
          statement.setString(9,newPicture.getDescription());

          statement.setBoolean(10,oldPicture.getis_Approved());
          statement.setBoolean(11,newPicture.getis_Approved());
          result=statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update Picture . Try again later");
        }
      }
    }
    return result;
  }

  /**
   * DAO Method to retreive all Picture objects
   * @return List of Picture
   * @author Jonathan Beck
   */
  @Override
  public List<Picture_VM> getAllPicture(int limit, int offset, int Album_ID, int  Contributor_ID) {
    List<Picture_VM> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_all_Picture(?,?,?,?)}")) {
          statement.setInt(1,limit);
          statement.setInt(2,offset);
          statement.setInt(3,Album_ID);
          statement.setInt(4,Contributor_ID);
          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {Integer Picture_ID = resultSet.getInt("Picture_Picture_ID");
              Integer _Album_ID = resultSet.getInt("Picture_Album_ID");
              Integer _Contributor_ID = resultSet.getInt("Picture_Contributor_ID");
              String Web_Address = resultSet.getString("Picture_Web_Address");
              String description = resultSet.getString("Picture_description");
              boolean Is_Active = resultSet.getBoolean("Picture_Is_Active");
              boolean is_Approved = resultSet.getBoolean("Picture_is_Approved");
              Integer Album_Album_ID = resultSet.getInt("Album_Album_ID");
              String Album_Album_Name = resultSet.getString("Album_Album_Name");
              boolean Album_Is_Active = resultSet.getBoolean("Album_Is_Active");
              Integer Contributor_Contributor_ID = resultSet.getInt("Contributor_Contributor_ID");
              String Contributor_First_Name = resultSet.getString("Contributor_First_Name");
              String Contributor_Last_Name = resultSet.getString("Contributor_Last_Name");
              String Contributor_email = resultSet.getString("Contributor_email");
              Picture _picture = new Picture( Picture_ID, Album_ID, Contributor_ID, Web_Address, description, Is_Active, is_Approved);
              Album _album = new Album(Album_Album_ID,Album_Album_Name);
              Contributor _contributor = new Contributor(Contributor_ID,Contributor_First_Name,Contributor_Last_Name,Contributor_email);
              Picture_VM Picture = new Picture_VM(_picture,_album,_contributor);
              result.add(Picture);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Pictures. Try again later");
    }
    return result;
  }
  /**
   * DAO Method to retreive by ID Picture objects
   * @param _picture the Picture to be retreived
   * @return List of Picture
   * @author Jonathan Beck
   */
  public Picture_VM getPictureByPrimaryKey(Picture _picture) throws SQLException{
    Picture_VM result = null;
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_pk_Picture(?)}")) {
        statement.setString(1, _picture.getPicture_ID().toString());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){Integer Picture_ID = resultSet.getInt("Picture_Picture_ID");
            Integer Album_ID = resultSet.getInt("Picture_Album_ID");
            Integer Contributor_ID = resultSet.getInt("Picture_Contributor_ID");
            String Web_Address = resultSet.getString("Picture_Web_Address");
            String description = resultSet.getString("Picture_description");
            boolean Is_Active = resultSet.getBoolean("Picture_Is_Active");
            boolean is_Approved = resultSet.getBoolean("Picture_is_Approved");
            Integer Album_Album_ID = resultSet.getInt("Album_Album_ID");
            String Album_Album_Name = resultSet.getString("Album_Album_Name");
            boolean Album_Is_Active = resultSet.getBoolean("Album_Is_Active");
            Integer Contributor_Contributor_ID = resultSet.getInt("Contributor_Contributor_ID");
            String Contributor_First_Name = resultSet.getString("Contributor_First_Name");
            String Contributor_Last_Name = resultSet.getString("Contributor_Last_Name");
            String Contributor_email = resultSet.getString("Contributor_email");
            Picture picture = new Picture( Picture_ID, Album_ID, Contributor_ID, Web_Address, description, Is_Active, is_Approved);
            Album _album = new Album(Album_Album_ID,Album_Album_Name);
            Contributor _contributor = new Contributor(Contributor_ID,Contributor_First_Name,Contributor_Last_Name,Contributor_email);
            result = new Picture_VM(picture,_album,_contributor);

          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  @Override
  public int changeActivation(int ObjectID, int mode) throws SQLException {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_change_active_Picture( ?,?)}")){
          statement.setInt(1,ObjectID);
          statement.setInt(2,mode);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not change picture status. Try again later(1)");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not change picture status. Try again later(2)");
    }
    return rowsAffected;
  }

  @Override
  public int changeApproval(int ObjectID, int mode) throws SQLException {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_change_approve_Picture( ?,?)}")){
          statement.setInt(1,ObjectID);
          statement.setInt(2,mode);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not change picture approval. Try again later(1)");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not change picture approval. Try again later(2)");
    }
    return rowsAffected;
  }

  @Override
  public List<Picture> getActivePicturebyAlbum(Integer Album_ID) throws SQLException {
    List<Picture> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_active_Picture_by_Album(?)}")) {
          statement.setInt(1,Album_ID);

          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {Integer Picture_ID = resultSet.getInt("Picture_Picture_ID");
              Integer _Album_ID = resultSet.getInt("Picture_Album_ID");
              Integer Contributor_ID = resultSet.getInt("Picture_Contributor_ID");
              String Web_Address = resultSet.getString("Picture_Web_Address");
              String description = resultSet.getString("Picture_description");
              boolean Is_Active = resultSet.getBoolean("Picture_Is_Active");
              boolean is_Approved = resultSet.getBoolean("Picture_is_Approved");
              Integer Album_Album_ID = resultSet.getInt("Album_Album_ID");
              String Album_Album_Name = resultSet.getString("Album_Album_Name");
              boolean Album_Is_Active = resultSet.getBoolean("Album_Is_Active");
              Integer Contributor_Contributor_ID = resultSet.getInt("Contributor_Contributor_ID");
              String Contributor_First_Name = resultSet.getString("Contributor_First_Name");
              String Contributor_Last_Name = resultSet.getString("Contributor_Last_Name");
              String Contributor_email = resultSet.getString("Contributor_email");
              //String Contributor_phone_number = resultSet.getString("Contributor_phone_number");
              Picture _picture = new Picture( Picture_ID, _Album_ID, Contributor_ID, Web_Address, description, Is_Active, is_Approved);
              result.add(_picture);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Pictures. Try again later");
    }
    return result;

  }

  @Override
  public int getPictureCount() throws SQLException {

      int result = 0;
      try (Connection connection = getConnection()) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_retreive_picture_count()}")) {


          try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              result = resultSet.getInt(1);
            }
          }
        }
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
      return result;
    }
  }


