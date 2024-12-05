package com.beck.beck_demos.crrg.data;

import com.beck.beck_demos.crrg.data_interfaces.ISponsor_Tag_DAO;
import com.beck.beck_demos.crrg.models.Picture;
import com.beck.beck_demos.crrg.models.Sponsor_Tag;
import com.beck.beck_demos.crrg.models.Sponsor_Tag_VM;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.beck.beck_demos.crrg.models.Sponsor_Tag;
import java.sql.CallableStatement;


import static com.beck.beck_demos.crrg.data.Database.getConnection;

public class Sponsor_Tag_DAO implements ISponsor_Tag_DAO {
  /**
   * DAO Method to add Sponsor_Tag objects
   * @param tag the Sponsor_Tag to be added
   * @return number of records added
   * @author Jonathan Beck
   */
  @Override
  public int addSponsor_Tag(Sponsor_Tag_VM tag) throws SQLException {
    int numRowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Sponsor_Tag( ?, ?, ?, ?,?,?)}")){
          statement.setInt(1,tag.getPicture().getAlbum_ID());
          statement.setInt(2,tag.getPicture().getContributor_ID());
          statement.setString(3,tag.getPicture().getWeb_Address());
          statement.setString(4,tag.getSponsor_ID());
          statement.setString(5,tag.getDescription());
          statement.setBoolean(6,tag.getis_Primary());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Sponsor_Tag. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Sponsor_Tag. Try again later");
    }
    return numRowsAffected;
  }


  @Override
  public int removeSponsor_Tag(Sponsor_Tag tag) throws SQLException {
    return 0;
  }

  @Override
  public List<Sponsor_Tag_VM> getAllSponsor_Tag(String _Sponsor_ID) {
    List<Sponsor_Tag_VM> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_all_Sponsor_Tag(?)}")) {

          statement.setString(1,_Sponsor_ID);

          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              String Sponsor_ID = resultSet.getString("Sponsor_Tag_Sponsor_ID");
              Integer Picture_ID = resultSet.getInt("Sponsor_Tag_Picture_ID");
              String Description = resultSet.getString("Sponsor_Tag_Description");
              boolean is_Primary = resultSet.getBoolean("Sponsor_Tag_is_Primary");
              String Sponsor_Sponsor_ID = resultSet.getString("Sponsor_Sponsor_ID");
              String Sponsor_Tier_ID = resultSet.getString("Sponsor_Tier_ID");
              String Sponsor_website = resultSet.getString("Sponsor_website");
              String Sponsor_Description = resultSet.getString("Sponsor_Description");
              boolean Sponsor_is_active = resultSet.getBoolean("Sponsor_is_active");
              Integer Picture_Picture_ID = resultSet.getInt("Picture_Picture_ID");
              Integer Picture_Album_ID = resultSet.getInt("Picture_Album_ID");
              Integer Picture_Contributor_ID = resultSet.getInt("Picture_Contributor_ID");
              String Picture_Web_Address = resultSet.getString("Picture_Web_Address");
              String Picture_description = resultSet.getString("Picture_description");
              boolean Picture_Is_Active = resultSet.getBoolean("Picture_Is_Active");
              boolean Picture_is_Approved = resultSet.getBoolean("Picture_is_Approved");
              Picture picture = new Picture(Picture_ID,Picture_Album_ID,Picture_Contributor_ID,Picture_Web_Address,Picture_description,Picture_Is_Active,Picture_is_Approved);
              Sponsor_Tag _sponsor_tag = new Sponsor_Tag( Sponsor_ID, Picture_ID, Description, is_Primary);
              Sponsor_Tag_VM _tag_vm = new Sponsor_Tag_VM(_sponsor_tag,picture);
              result.add(_tag_vm);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Sponsor_Tags. Try again later");
    }
    return result;}

  @Override
  public int deleteSponsor_Tag(String sponsor, int pictureID) {
    return 0;
  }
}
