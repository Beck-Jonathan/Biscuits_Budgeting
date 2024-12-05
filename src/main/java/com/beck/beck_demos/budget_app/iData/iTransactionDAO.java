package com.beck.beck_demos.budget_app.iData;

import com.beck.beck_demos.budget_app.models.Category_VM;
import com.beck.beck_demos.budget_app.models.Transaction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.beck.beck_demos.budget_app.data.Database.getConnection;

public interface iTransactionDAO {
 int update(Transaction oldTransaction, Transaction newTransaction) throws SQLException;

   int bulkUpdate(int userid, String category, String query) throws SQLException;

  //getTransactionForExportByUser
    List<Transaction> getTransactionForExportByUser(int userID) throws SQLException;

    List<Transaction> getTransactionFromFile(File uploadedFile, String type) throws SQLException;

    int add(Transaction _transaction);

    int addBatch(List<Transaction> _transactions, List<Boolean> Exists) throws SQLException;

  List<Transaction> getTransactionByUser(Integer User_ID) throws SQLException;

    List<Transaction> getTransactionByUser(int User_ID, int pagesize) throws SQLException;

    List<Transaction> getTransactionByUser(Integer User_ID, int pagesize, int offset) throws SQLException;

    List<Transaction> getTransactionByUser(int userID, String category, int year, int pagesize, int offset, String sortBy, int order) throws SQLException;


    List<Transaction> searchTransactionByUser(int userID, String query) throws SQLException;

   int getAnalysis(List<List<Category_VM>> years, int user_ID) throws SQLException;

   int getTransactionCountByUser(int userID, String category, int year) throws SQLException;

   double getTransactionCategoryTotal(int userID, String category_id, String direction) throws SQLException;

   List<Transaction> getTransactionByUserYearCategpry(int userID, Date date, String category, int limit, int offset) throws SQLException ;


}
