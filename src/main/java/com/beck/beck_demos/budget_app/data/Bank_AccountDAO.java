package com.beck.beck_demos.budget_app.data;
/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */
import com.beck.beck_demos.budget_app.models.Bank_Account;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;

import static com.beck.beck_demos.budget_app.data.Database.getConnection;

import com.beck.beck_demos.budget_app.iData.iBank_AccountDAO;
import com.beck.beck_demos.budget_app.models.Bank_Account;

import java.sql.SQLException;

public class Bank_AccountDAO implements iBank_AccountDAO {

  @Override
  public Bank_Account getBank_AccountByPrimaryKey(Bank_Account _bank_account) throws SQLException {

    Bank_Account result = null;
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_pk_Bank_Account(?)}")) {
        statement.setString(1, _bank_account.getBank_Account_ID().toString());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){String Bank_Account_ID = resultSet.getString("Bank_Account_Bank_Account_ID");
            Integer User_ID = resultSet.getInt("Bank_Account_User_ID");
            String Account_Nickname = resultSet.getString("Bank_Account_Account_Nickname");
            Double Balance = resultSet.getDouble("Bank_Account_Balance");
            Date Balance_Date = resultSet.getDate("Bank_Account_Balance_Date");
            Integer User_User_ID = resultSet.getInt("User_User_ID");
            String User_User_Name = resultSet.getString("User_User_Name");
            String User_User_PW = resultSet.getString("User_User_PW");
            String User_Email = resultSet.getString("User_Email");
            result = new Bank_Account( Bank_Account_ID, User_ID, Account_Nickname, Balance, Balance_Date);}
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  /**
   * DAO Method to update Bank_Account objects
   * @param oldBank_Account the Bank_Account to be updated
   * @param newBank_Account the updated version of the Bank_Account
   * @return number of records updated
   * @author Jonathan Beck
   */

  public int update(Bank_Account oldBank_Account, Bank_Account newBank_Account) throws SQLException{
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_Bank_Account(? ,?,?,?,?,?,?,?,?)}"))
        {
          statement.setString(1,oldBank_Account.getBank_Account_ID());
          statement.setInt(2,oldBank_Account.getUser_ID());
          statement.setInt(3,newBank_Account.getUser_ID());
          statement.setString(4,oldBank_Account.getAccount_Nickname());
          statement.setString(5,newBank_Account.getAccount_Nickname());
          statement.setDouble(6,oldBank_Account.getBalance());
          statement.setDouble(7,newBank_Account.getBalance());
          statement.setDate(8, (java.sql.Date) oldBank_Account.getBalance_Date());
          statement.setDate(9, (java.sql.Date) newBank_Account.getBalance_Date());
          result=statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update Bank_Account . Try again later");
        }
      }
    }
    return result;
  }

  @Override
  public List<Bank_Account> getAllBank_Account(int offset, int limit, Integer User_ID) throws SQLException {
    List<Bank_Account> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_Bank_Account_by_User(?,?,?)}")) {
          statement.setInt(2,limit)
          ;statement.setInt(3,offset);
          statement.setInt(1,User_ID);
          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {String Bank_Account_ID = resultSet.getString("Bank_Account_Bank_Account_ID");
              Integer _User_ID = resultSet.getInt("Bank_Account_User_ID");
              String Account_Nickname = resultSet.getString("Bank_Account_Account_Nickname");
              Double Balance = resultSet.getDouble("Bank_Account_Balance");
              Date Balance_Date = resultSet.getDate("Bank_Account_Balance_Date");
              Integer User_User_ID = resultSet.getInt("User_User_ID");
              String User_User_Name = resultSet.getString("User_User_Name");
              String User_User_PW = resultSet.getString("User_User_PW");
              String User_Email = resultSet.getString("User_Email");
              Bank_Account _bank_account = new Bank_Account( Bank_Account_ID, _User_ID, Account_Nickname, Balance, Balance_Date);
              result.add(_bank_account);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Bank_Accounts. Try again later");
    }
    return result;
  }
  /**
   * DAO Method to add Bank_Account objects
   * @param _bank_account the Bank_Account to be added
   * @return number of records added
   * @author Jonathan Beck
   */
  public int add(Bank_Account _bank_account) {
    int numRowsAffected=0;try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Bank_Account( ?, ?, ?, ?, ?)}")){
          statement.setString(1,_bank_account.getBank_Account_ID());
          statement.setInt(2,_bank_account.getUser_ID());
          statement.setString(3,_bank_account.getAccount_Nickname());
          statement.setDouble(4,_bank_account.getBalance());
          java.sql.Date newDate = new java.sql.Date(_bank_account.getBalance_Date().getTime());
          statement.setDate(5,newDate);
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Bank_Account. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Bank_Account. Try again later");
    }
    return numRowsAffected;
  }



}
