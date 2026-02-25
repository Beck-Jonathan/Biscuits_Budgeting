package com.beck.beck_demos.budget_app.iData;

import com.beck.beck_demos.budget_app.models.Category_VM;
import com.beck.beck_demos.budget_app.models.Transaction;
import com.beck.beck_demos.budget_app.models.Transaction_VM;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public interface iTransactionDAO {
 int update_category(Transaction oldTransaction, Transaction newTransaction) throws SQLException;

   int bulkUpdateCategory(String userid, String category, String query) throws SQLException;

  //getTransactionForExportByUser
    List<Transaction> getTransactionForExportByUser(String userID) throws SQLException;

    List<Transaction> getTransactionFromFile(File uploadedFile, String type) throws SQLException;

    int add(Transaction _transaction) throws SQLException;

    int addBatch(List<Transaction> _transactions, String user_id) throws SQLException;

  List<Transaction_VM> getTransactionByUser(String User_ID) throws SQLException;

    List<Transaction_VM> getTransactionByUser(String User_ID, int pagesize) throws SQLException;

    List<Transaction_VM> getTransactionByUser(String User_ID, int pagesize, int offset) throws SQLException;

    List<Transaction_VM> getTransactionByUser(String userID, String category, int year, int pagesize, int offset, String sortBy, int order) throws SQLException;


    List<Transaction_VM> searchTransactionByUser(String userID, String query) throws SQLException;

   List<List<Category_VM>> getAnalysis(List<List<Category_VM>> years, String user_ID) throws SQLException;

   int getTransactionCountByUser(String userID, String category, int year) throws SQLException;

   double getTransactionCategoryTotal(int userID, String category_id, String direction) throws SQLException;

   List<Transaction> getTransactionByUserYearCategpry(int userID, Date date, String category, int limit, int offset) throws SQLException ;
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
  List<List<Category_VM>> getMonthlyAnalysis(List<List<Category_VM>> months, String user_ID, int year) throws SQLException;
  List<Transaction> getDistinctTransactionForDropdown(String user_ID) throws SQLException;

}
