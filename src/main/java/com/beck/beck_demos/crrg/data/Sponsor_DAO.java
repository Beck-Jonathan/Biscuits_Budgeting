package com.beck.beck_demos.crrg.data;

import com.beck.beck_demos.crrg.data_interfaces.iSponsor_DAO;
import com.beck.beck_demos.crrg.models.Sponsor;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.beck.beck_demos.crrg.data.Database.getConnection;

public class Sponsor_DAO implements iSponsor_DAO {

  @Override
  public int add(Sponsor _sponsor) {
    int numRowsAffected=0;try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Sponsor( ?, ?, ?, ?, ?)}")){
          statement.setString(1,_sponsor.getSponsor_ID());
          statement.setString(2,_sponsor.getTier_ID());
          statement.setString(3,_sponsor.getwebsite());
          statement.setString(4,_sponsor.getDescription());
          statement.setBoolean(5,_sponsor.getis_active());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Sponsor. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Sponsor. Try again later");
    }
    return numRowsAffected;
  }

  @Override
  public List<Sponsor> getSponsorbyTier(String Tier_ID) throws SQLException {
    return List.of();
  }


  /**
   * DAO Method to retreive all Sponsor objects
   * @return List of Sponsor
   * @author Jonathan Beck
   */
  @Override
  public  List<Sponsor> getAllSponsor(String Order_By) {
    List<Sponsor> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_all_Sponsor(?)}")) {

          statement.setString(1,Order_By);
          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {String Sponsor_ID = resultSet.getString("Sponsor_Sponsor_ID");
              String Tier_ID = resultSet.getString("Sponsor_Tier_ID");
              String website = resultSet.getString("Sponsor_website");
              String Description = resultSet.getString("Sponsor_Description");
              boolean is_active = resultSet.getBoolean("Sponsor_is_active");

              Sponsor _sponsor = new Sponsor( Sponsor_ID, Tier_ID, website, Description, is_active);
              result.add(_sponsor);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Sponsors. Try again later");
    }
    return result;}

  @Override
  public List<String> getDistinctSponsorForDropdown() throws SQLException {
    List<String> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_select_distinct_and_active_Sponsor_for_dropdown()}")) {
          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              String Sponsor_ID = resultSet.getString("Sponsor_Sponsor_ID");

              result.add(Sponsor_ID);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Sponsors. Try again later");
    }
    return result;
  }


  /**
   * DAO Method to deactivate Sponsor objects
   * @param Sponsor_ID the Sponsor to be deleted
   * @return number of records deleted
   * @author Jonathan Beck
   */
  public int deactivateSponsor( String Sponsor_ID) throws SQLException{
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_Delete_Sponsor( ?)}")){
          statement.setString(1,Sponsor_ID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Delete Sponsor. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Delete Sponsor. Try again later");
    }
    return rowsAffected;
  }

  /**
   * DAO Method to undelete Sponsor objects
   * @param Sponsor_ID the Sponsor to be undeleted
   * @return number of records undeleted
   * @author Jonathan Beck
   */
  public int undeleteSponsor( String Sponsor_ID) throws SQLException{
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_unDelete_Sponsor( ?)}")){
          statement.setString(1,Sponsor_ID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Restore Sponsor. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Restore Sponsor. Try again later");
    }
    return rowsAffected;
  }

  @Override
  public List<String> selectAllTiersForDropDown() throws SQLException {

    List<String> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_all_Tier()}")) {

          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              String Tier_ID = resultSet.getString("Tier_Tier_ID");

              result.add(Tier_ID);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Tiers. Try again later");
    }
    return result;
  }

  @Override
  public int setPrimarySponsorImage() throws SQLException {
    return 0;
  }

  public Sponsor getSponsorByPrimaryKey(Sponsor _sponsor) throws SQLException{
    Sponsor result = null;
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_pk_Sponsor(?)}")) {
        statement.setString(1, _sponsor.getSponsor_ID().toString());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){String Sponsor_ID = resultSet.getString("Sponsor_Sponsor_ID");
            String Tier_ID = resultSet.getString("Sponsor_Tier_ID");
            String website = resultSet.getString("Sponsor_website");
            String Description = resultSet.getString("Sponsor_Description");
            boolean is_active = resultSet.getBoolean("Sponsor_is_active");

            result = new Sponsor( Sponsor_ID, Tier_ID, website, Description, is_active);}
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }
  public int update(Sponsor oldSponsor, Sponsor newSponsor) throws SQLException{
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_Sponsor(? ,?,?,?,?,?,?,?,?)}"))
        {
          statement.setString(1,oldSponsor.getSponsor_ID());
          statement.setString(2,oldSponsor.getTier_ID());
          statement.setString(3,newSponsor.getTier_ID());
          statement.setString(4,oldSponsor.getwebsite());
          statement.setString(5,newSponsor.getwebsite());
          statement.setString(6,oldSponsor.getDescription());
          statement.setString(7,newSponsor.getDescription());
          statement.setBoolean(8,oldSponsor.getis_active());
          statement.setBoolean(9,newSponsor.getis_active());
          result=statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update Sponsor . Try again later");
        }
      }
    }
    return result;
  }
}
