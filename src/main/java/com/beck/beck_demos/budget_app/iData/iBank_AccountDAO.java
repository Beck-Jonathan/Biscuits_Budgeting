package com.beck.beck_demos.budget_app.iData;

import com.beck.beck_demos.budget_app.models.Bank_Account;

import java.sql.SQLException;
import java.util.List;

public interface iBank_AccountDAO {
  /**
   * DAO Method to retreive by Foreign Key Bank_Account objects
   * @return List of Bank_Account
   * @author Jonathan Beck
   */
  Bank_Account getBank_AccountByPrimaryKey(Bank_Account _bank_account) throws SQLException;
  /**
   * DAO Method to update Bank_Account objects
   * @param oldBank_Account the Bank_Account to be updated
   * @param newBank_Account the updated version of the Bank_Account
   * @return number of records updated
   * @author Jonathan Beck
   */
  int update(Bank_Account oldBank_Account, Bank_Account newBank_Account) throws SQLException;
  /**
   * DAO Method to retreive all Bank_Account objects
   * @return List of Bank_Account
   * @author Jonathan Beck
   */
  List<Bank_Account> getAllBank_Account(int offset, int limit, Integer User_ID) throws SQLException;
  /**
   * DAO Method to add Bank_Account objects
   * @param _bank_account the Bank_Account to be added
   * @return number of records added
   * @author Jonathan Beck
   */
  int add (Bank_Account _bank_account) throws SQLException;



}
