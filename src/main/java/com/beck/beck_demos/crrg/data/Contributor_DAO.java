package com.beck.beck_demos.crrg.data;
import com.beck.beck_demos.crrg.data_interfaces.iContributor_DAO;
import com.beck.beck_demos.crrg.models.Contributor;
import com.beck.beck_demos.crrg.models.Contributor_VM;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.beck.beck_demos.crrg.data.Database.getConnection;

public class Contributor_DAO implements iContributor_DAO{
  /**
   * DAO Method to add Contributor objects
   * @param _contributor the Contributor to be added
   * @return number of records added
   * @author Jonathan Beck
   */
  @Override
  public int add(Contributor _contributor) {
    int numRowsAffected=0;try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Contributor( ?, ?, ?)}")){
          statement.setString(1,_contributor.getFirst_Name());
          statement.setString(2,_contributor.getLast_Name());
          statement.setString(3,_contributor.getemail());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Contributor. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Contributor. Try again later");
    }
    return numRowsAffected;
  }

  /**
   * DAO Method to retreive all Contributor objects
   * @return List of Contributor
   * @author Jonathan Beck
   */
  @Override
    public  List<Contributor_VM> getAllContributor(int limit, int offset) {
    List<Contributor_VM> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_all_Contributor(?,?)}")) {
          statement.setInt(1,limit)
          ;statement.setInt(2,offset);
          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {Integer Contributor_ID = resultSet.getInt("Contributor_Contributor_ID");
              String First_Name = resultSet.getString("Contributor_First_Name");
              String Last_Name = resultSet.getString("Contributor_Last_Name");
              String email = resultSet.getString("Contributor_email");
              int size=resultSet.getInt("Count");;
              Contributor _contributor = new Contributor( Contributor_ID, First_Name, Last_Name, email);
              Contributor_VM _contributor_VM = new Contributor_VM(_contributor,size);
              result.add(_contributor_VM);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Contributors. Try again later");
    }
    return result;}

  /**
   * DAO Method to retreive all Contributor objects
   * @return List of Contributor
   * @author Jonathan Beck
   */
  @Override
  public  List<Contributor> getDistinctContributorForDropdown() {
    List<Contributor> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_select_distinct_and_active_Contributor_for_dropdown()}")) {
          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              Integer Contributor_ID = resultSet.getInt("Contributor_ID");
              String email = resultSet.getString("email");
              Contributor _contributor = new Contributor( Contributor_ID, email);
              result.add(_contributor);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Contributors. Try again later");
    }
    return result;
  }
  /**
   * DAO Method to retreive by ID Contributor objects
   * @param _contributor the Contributor to be retreived
   * @return List of Contributor
   * @author Jonathan Beck
   */
  @Override
  public Contributor_VM getContributorByPrimaryKey(Contributor _contributor) throws SQLException{
    Contributor_VM result_vm = null;
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_pk_Contributor(?)}")) {
        statement.setString(1, _contributor.getContributor_ID().toString());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){Integer Contributor_ID = resultSet.getInt("Contributor_Contributor_ID");
            String First_Name = resultSet.getString("Contributor_First_Name");
            String Last_Name = resultSet.getString("Contributor_Last_Name");
            String email = resultSet.getString("Contributor_email");
            int size=resultSet.getInt("Count");;
            Contributor result = new Contributor( Contributor_ID, First_Name, Last_Name, email);
            result_vm = new Contributor_VM(result,size);
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result_vm;
  }
  /**
   * DAO Method to update Contributor objects
   * @param oldContributor the Contributor to be updated
   * @param newContributor the updated version of the Contributor
   * @return number of records updated
   * @author Jonathan Beck
   */
  @Override
  public int update(Contributor oldContributor, Contributor newContributor) throws SQLException{
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_Contributor(? ,?,?,?,?,?,?)}"))
        {
          statement.setInt(1,oldContributor.getContributor_ID());
          statement.setString(2,oldContributor.getFirst_Name());
          statement.setString(3,newContributor.getFirst_Name());
          statement.setString(4,oldContributor.getLast_Name());
          statement.setString(5,newContributor.getLast_Name());
          statement.setString(6,oldContributor.getemail());
          statement.setString(7,newContributor.getemail());
          result=statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update Contributor . Try again later");
        }
      }
    }
    return result;
  }
}
