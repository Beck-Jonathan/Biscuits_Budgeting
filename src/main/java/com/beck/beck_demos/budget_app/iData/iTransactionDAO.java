package com.beck.beck_demos.budget_app.iData;

import com.beck.beck_demos.budget_app.models.Saved_Search_Order_Line;
import com.beck.beck_demos.budget_app.models.SubCategory_VM;
import com.beck.beck_demos.budget_app.models.Transaction;
import com.beck.beck_demos.budget_app.models.Transaction_VM;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface iTransactionDAO {
 int update_category(Transaction oldTransaction, Transaction newTransaction) throws SQLException;

   int bulkUpdateCategory(String userid, String category, String query) throws SQLException;

  //getTransactionForExportByUser
    List<Transaction> getTransactionForExportByUser(String userID) throws SQLException;

    List<Transaction> getTransactionFromFile(File uploadedFile, String type) throws SQLException;

    int add(Transaction _transaction) throws SQLException;

    int addBatch(List<Transaction> _transactions, String user_id) throws SQLException;

  List<Transaction_VM> getTransactionByUser(String userID, String category, String Bank_Account_ID, int year, int month, int pagesize, int offset, String sortBy, int order, boolean findErrors, boolean showLocked) throws SQLException;


    List<Transaction_VM> searchTransactionByUser(String userID, String query) throws SQLException;

  int getTransactionCountByUser(String userID, String category, String Bank_Account_ID, int year, int month, boolean findErrors, boolean showLocked) throws SQLException;


  Transaction_VM getTransactionByPrimaryKey(Transaction _transaction) throws SQLException;
/**
  * DAO Method to update Transaction objects
* @param oldTransaction the Transaction to be updated
* @param newTransaction the updated version of the Transaction
* @return number of records updated
* @author Jonathan Beck
 */
  int update(Transaction oldTransaction, Transaction newTransaction) throws SQLException;
  /**
   * DAO Method to lock or unlock Transaction objects
   * @param transaction the Transaction to be locked or unlocked
   * @return number of records updated
   * @author Jonathan Beck
   */
  int toggleLockTransaction( Transaction transaction) throws SQLException;

  int writeTransactionToFile(List<Transaction> transactions, String path) throws IOException;
  List<Transaction> getDistinctTransactionForDropdown(String user_ID) throws SQLException;

  int applyAllLines(String userId, List<Saved_Search_Order_Line> savedSearchOrderLines) throws SQLException;
   List<List<SubCategory_VM>> getMonthlyAnalysis(String user_ID , String BankAccountID, int year) ;
  List<List<SubCategory_VM>> getAnnualAnalysis(String user_ID, String BankAccountID);
  List<List<SubCategory_VM>> getSuperAnnualAnalysis(String user_ID, String BankAccountID);
  List<List<SubCategory_VM>> getSuperMonthlyAnalysis(String user_ID, String BankAccountID, int year) ;

  List<List<SubCategory_VM>> getForecastAnalysis(String user_ID, LocalDate startDate, int monthsBack, int monthsForward, int retirementOffset);
}
