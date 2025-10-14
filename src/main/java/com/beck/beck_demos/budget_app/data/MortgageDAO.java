package com.beck.beck_demos.budget_app.data;

/// <summary>
///AUTHOR: Jonathan Beck
///<br />
///CREATED: 6/8/2024
///< br />
///An example class to show how code is expected to be written and documented.
///This is where a description of what your file is supposed to contain goes.
///e.g., "Class with helper methods for input validation.",
///Class that defines MortgageDAO Objects.
///</summary>
///< remarks>
///UPDATER: updater_name
///< br />
/// UPDATED: yyyy-MM-dd
/// < br />
/// Update comments go here, include method or methods were changed or added
/// A new remark should be added for each update.
///</remarks>
import com.beck.beck_demos.budget_app.iData.iMortgageDAO;
import com.beck.beck_demos.budget_app.models.Mortgage;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import static com.beck.beck_demos.budget_app.data.Database.getConnection;
public class MortgageDAO implements iMortgageDAO {


  public List<Mortgage> getMortgagebyUser(Integer User_ID,int limit,int offset) {
    List<Mortgage> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_Mortgage_by_User(?,?,?)}")) {
          statement.setInt(1,User_ID)
          ;statement.setInt(2,limit)
          ;statement.setInt(3,offset);
          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              String Mortgage_ID = resultSet.getString("Mortgage_Mortgage_ID");
              Integer User_ID2 = resultSet.getInt("Mortgage_User_ID");
              String Nickname = resultSet.getString("Mortgage_Nickname");
              Double Present_Value = resultSet.getDouble("Mortgage_Present_Value");
              Double Future_Value = resultSet.getDouble("Mortgage_Future_Value");
              Double Interest_Rate = resultSet.getDouble("Mortgage_Interest_Rate");
              Double Monthly_Payment = resultSet.getDouble("Mortgage_Monthly_Payment");
              Double Extra_Payment = resultSet.getDouble("Mortgage_Extra_Payment");
              Integer Remaining_Term = resultSet.getInt("Mortgage_Remaining_Term");
              Mortgage _mortgage = new Mortgage( Mortgage_ID, User_ID2,Nickname, Present_Value, Future_Value, Interest_Rate, Monthly_Payment, Extra_Payment, Remaining_Term);
              result.add(_mortgage);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Mortgages. Try again later");
    }
    return result;
  }
  /**
   * DAO Method to add Mortgage objects
   * @param _mortgage the Mortgage to be added
   * @return number of records added
   * @author Jonathan Beck
   */
  @Override
  public int add(Mortgage _mortgage) throws SQLException {
    int numRowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Mortgage( ?, ?, ?, ?, ?, ?, ?, ?)}")){
          statement.setInt(1,_mortgage.getUser_ID());
          statement.setString(2,_mortgage.getNickname());
          statement.setDouble(3,_mortgage.getPresent_Value());
          statement.setDouble(4,_mortgage.getFuture_Value());
          statement.setDouble(5,_mortgage.getInterest_Rate());
          statement.setDouble(6,_mortgage.getMonthly_Payment());
          statement.setDouble(7,_mortgage.getExtra_Payment());
          statement.setInt(8,_mortgage.getRemaining_Term());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new SQLException("Could not add Mortgage. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Mortgage. Try again later");
    }
    return numRowsAffected;
  }
  /**
   * DAO Method to delete Mortgage objects
   * @param mortgageID the Mortgage to be deleted
   * @return number of records deleted
   * @author Jonathan Beck
   */
  @Override
  public Integer deleteMortgage(String mortgageID) {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_Delete_Mortgage( ?)}")){
          statement.setString(1,mortgageID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Delete Mortgage. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Delete Mortgage. Try again later");
    }
    return rowsAffected;
  }

  @Override
  /**
   * DAO Method to update Mortgage objects
   * @param oldMortgage the Mortgage to be updated
   * @param newMortgage the updated version of the Mortgage
   * @return number of records updated
   * @author Jonathan Beck
   */
  public int update(Mortgage oldMortgage, Mortgage newMortgage) throws SQLException{
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_Mortgage(? ,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"))
        {
          statement.setString(1,oldMortgage.getMortgage_ID());
          statement.setInt(2,oldMortgage.getUser_ID());
          statement.setInt(3,newMortgage.getUser_ID());
          statement.setString(4,oldMortgage.getNickname());
          statement.setString(5,newMortgage.getNickname());
          statement.setDouble(6,oldMortgage.getPresent_Value());
          statement.setDouble(7,newMortgage.getPresent_Value());
          statement.setDouble(8,oldMortgage.getFuture_Value());
          statement.setDouble(9,newMortgage.getFuture_Value());
          statement.setDouble(10,oldMortgage.getInterest_Rate());
          statement.setDouble(11,newMortgage.getInterest_Rate());
          statement.setDouble(12,oldMortgage.getMonthly_Payment());
          statement.setDouble(13,newMortgage.getMonthly_Payment());
          statement.setDouble(14,oldMortgage.getExtra_Payment());
          statement.setDouble(15,newMortgage.getExtra_Payment());
          statement.setInt(16,oldMortgage.getRemaining_Term());
          statement.setInt(17,newMortgage.getRemaining_Term());
          result=statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update Mortgage . Try again later");
        }
      }
    }
    return result;
  }

  @Override
  public Mortgage getMortgageByPrimaryKey(Mortgage _mortgage) throws SQLException {
    Mortgage result = null;
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retrieve_by_pk_Mortgage(?)}")) {
        statement.setString(1, _mortgage.getMortgage_ID().toString());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){String Mortgage_ID = resultSet.getString("Mortgage_Mortgage_ID");
            Integer User_ID = resultSet.getInt("Mortgage_User_ID");
            String Nickname = resultSet.getString("Mortgage_Nickname");
            Double Present_Value = resultSet.getDouble("Mortgage_Present_Value");
            Double Future_Value = resultSet.getDouble("Mortgage_Future_Value");
            Double Interest_Rate = resultSet.getDouble("Mortgage_Interest_Rate");
            Double Monthly_Payment = resultSet.getDouble("Mortgage_Monthly_Payment");
            Double Extra_Payment = resultSet.getDouble("Mortgage_Extra_Payment");
            Integer Remaining_Term = resultSet.getInt("Mortgage_Remaining_Term");
            Integer User_User_ID = resultSet.getInt("User_User_ID");
            String User_User_Name = resultSet.getString("User_User_Name");
            String User_User_PW = resultSet.getString("User_User_PW");
            String User_Email = resultSet.getString("User_Email");
            result = new Mortgage( Mortgage_ID, User_ID, Nickname, Present_Value, Future_Value, Interest_Rate, Monthly_Payment, Extra_Payment, Remaining_Term);}
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

}

