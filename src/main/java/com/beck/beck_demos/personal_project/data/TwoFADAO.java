package com.beck.beck_demos.personal_project.data;

import com.beck.beck_demos.personal_project.models.TwoFA;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static com.beck.beck_demos.personal_project.data.Database.getConnection;
public class TwoFADAO {

  public static int add(TwoFA _twofa) {
    int numRowsAffected=0;try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_TwoFA( ?,  ?)}")){
          statement.setInt(1,_twofa.getUser_ID());

          statement.setDate(2, Date.valueOf(_twofa.getDateSent()));
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add TwoFA. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add TwoFA. Try again later");
    }
    return numRowsAffected;
  }
  public static TwoFA getTwoFAByPrimaryKey(TwoFA _twofa) throws SQLException{
    TwoFA result = null;
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_pk_TwoFA(?)}")) {
        statement.setString(1, _twofa.getTwoFA_ID().toString());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){Integer TwoFA_ID = resultSet.getInt("TwoFA_ID");
            Integer User_ID = resultSet.getInt("User_ID");
            String TwoFA_Code = resultSet.getString("TwoFA_Code");
            LocalDate DateSent = resultSet.getDate("DateSent").toLocalDate();
            result = new TwoFA( TwoFA_ID, User_ID, TwoFA_Code, DateSent);}
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  public static String getTwoFAById(int id){

    String TwoFACode = "";
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_TWOFA_by_ID(?)}")) {
        statement.setInt(1,id);

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){
            TwoFACode=resultSet.getString(1);
          }
        }
        catch (SQLException ex ){

          throw new RuntimeException(ex);}
      }


    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return TwoFACode;



  }
  public static List<TwoFA> getAllTwoFA() {
    List<TwoFA> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_all_TwoFA()}")) {try(ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {Integer TwoFA_ID = resultSet.getInt("TwoFA_ID");
            Integer User_ID = resultSet.getInt("User_ID");
            String TwoFA_Code = resultSet.getString("TwoFA_Code");
            LocalDate DateSent = resultSet.getDate("DateSent").toLocalDate();
            TwoFA _twofa = new TwoFA( TwoFA_ID, User_ID, TwoFA_Code, DateSent);
            result.add(_twofa);
          }
        }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve TwoFAs. Try again later");
    }
    return result;}

}
