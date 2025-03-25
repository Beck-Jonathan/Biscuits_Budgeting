package com.beck.beck_demos.budget_app.data;

/// <summary>
///AUTHOR: Jonathan Beck
///<br />
///CREATED: 22/7/2024
///< br />
///An example class to show how code is expected to be written and documented.
///This is where a description of what your file is supposed to contain goes.
///e.g., "Class with helper methods for input validation.",
///Class that defines TransactionDAO Objects.
///</summary>
///< remarks>
///UPDATER: updater_name
///< br />
/// UPDATED: yyyy-MM-dd
/// < br />
/// Update comments go here, include method or methods were changed or added
/// A new remark should be added for each update.
///</remarks>
import com.beck.beck_demos.budget_app.iData.iTransactionDAO;
import com.beck.beck_demos.budget_app.models.*;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.beck.beck_demos.budget_app.data.Database.getConnection;
public class TransactionDAO implements iTransactionDAO {

  public int update_category(Transaction oldTransaction, Transaction newTransaction) throws SQLException {
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_update_Transaction_category(?,?,?)}")) {
          statement.setString(1, oldTransaction.getTransaction_ID());
          statement.setInt(2, oldTransaction.getUser_ID());

          statement.setString(3, newTransaction.getCategory_ID());

          result = statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update Transaction . Try again later");
        }
      }
    }
    return result;
  }
  /**
   * DAO Method to update Transaction objects
   * @param oldTransaction the Transaction to be updated
   * @param newTransaction the updated version of the Transaction
   * @return number of records updated
   * @author Jonathan Beck
   */

  public int update(Transaction oldTransaction, Transaction newTransaction) throws SQLException{
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_Transaction(? ,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"))
        {
          statement.setString(1,oldTransaction.getTransaction_ID());
          statement.setInt(2,oldTransaction.getUser_ID());
          statement.setInt(3,newTransaction.getUser_ID());
          statement.setString(4,oldTransaction.getCategory_ID());
          statement.setString(5,newTransaction.getCategory_ID());
          statement.setString(6,oldTransaction.getBank_Account_ID());
          statement.setString(7,newTransaction.getBank_Account_ID());
          statement.setDate(8,(Date)oldTransaction.getPost_Date());
          statement.setDate(9,(Date)newTransaction.getPost_Date());
          statement.setInt(10,oldTransaction.getCheck_No());
          statement.setInt(11,newTransaction.getCheck_No());
          statement.setString(12,oldTransaction.getDescription());
          statement.setString(13,newTransaction.getDescription());
          statement.setDouble(14,oldTransaction.getAmount());
          statement.setDouble(15,newTransaction.getAmount());
          statement.setString(16,oldTransaction.getType());
          statement.setString(17,newTransaction.getType());
          statement.setString(18,oldTransaction.getStatus());
          statement.setString(19,newTransaction.getStatus());
          result=statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update Transaction . Try again later");
        }
      }
    }
    return result;
  }



   public int bulkUpdateCategory(int userid, String category, String query) throws SQLException {
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_assign_categories(?,?,?)}")) {
          statement.setInt(1, userid);
          statement.setString(2, category);

          statement.setString(3, query);

          try (ResultSet resultSet = statement.executeQuery()){
            result=statement.getUpdateCount();
          } catch (SQLException e) {
            throw new RuntimeException("Could not update Transaction . Try again later");
          }
        }
      }
    }



    return result;
  }

  //getTransactionForExportByUser
  public  List<Transaction> getTransactionForExportByUser(int userID) throws SQLException {
    List<Transaction> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      try (CallableStatement statement = connection.prepareCall("{CALL sp_transaction_for_export(?)}")) {
        statement.setInt(1, userID);

        try (ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {
            String Transaction_ID = resultSet.getString("Transaction_Transaction_ID");
            Integer User_ID = resultSet.getInt("Transaction_User_ID");
            String Category_ID = resultSet.getString("Transaction_Category_ID");
            String Account_Num = resultSet.getString("Transaction_Bank_Account_ID");
            Date Post_Date = resultSet.getDate("Transaction_Post_Date");
            Integer Check_No = resultSet.getInt("Transaction_Check_No");
            String Description = resultSet.getString("Transaction_Description");
            Double Amount = resultSet.getDouble("Transaction_Amount");
            String Type = resultSet.getString("Transaction_Type");
            String Status = resultSet.getString("Transaction_Status");

            Transaction _result = new Transaction(Transaction_ID, User_ID, Category_ID, Account_Num, Post_Date, Check_No, Description, Amount, Type, Status);
            result.add(_result);
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  public  List<Transaction> getTransactionFromFile(File uploadedFile, String type) {
    List<Transaction> result = new ArrayList<>();
    BufferedReader reader;

    try {
      reader = new BufferedReader(new FileReader(uploadedFile));
      String line = reader.readLine();
      ArrayList partsname = new ArrayList(List.of(line.split("\t")));
      String accountNumber="";
      if (partsname.get(0).equals("Account Type: Checking")) {
        type = "Altra";
      }
      else if (partsname.get(0).equals("Custom")){
        type="custom";
        accountNumber = (String) partsname.get(2);
      }
      else {
        type = "GreenState";
      }
      //first line is just heading data
      line = reader.readLine();
      if (type.equals("GreenState")) {
        while (line != null) {
          ArrayList parts = new ArrayList(List.of(line.split("\t")));
          String Transaction_ID = "";
          String Account_Num = (String) parts.get(0);
          Integer userID = 1;
          //Date Post_Date = new Date(2024, 5, 5);
          //Date Post_Date = (Date) parts.get(2);
          String Post_Date_String = ((String) parts.get(1));
          java.util.Date utilDate = new SimpleDateFormat("MM/dd/yyyy").parse(Post_Date_String);

          java.sql.Date Post_Date = new java.sql.Date(utilDate.getTime());
          Integer Check_No = 0;
          if (parts.get(2) != null && !parts.get(2).equals("")) {
            Check_No = Integer.valueOf((String) parts.get(2));
          }

          String Description = (String) parts.get(3);
          Double debitAmount = 0d;
          Double creditAmount = 0d;
          if (parts.get(5).equals("")) {
            creditAmount = 0d;
          } else {
            creditAmount = Double.valueOf((String) parts.get(5));
          }
          if (parts.get(4).equals("")) {
            debitAmount = 0d;
          } else {
            debitAmount = Double.valueOf((String) parts.get(4));
          }
          Double Amount = creditAmount - debitAmount;
          String Type = Amount > 0 ? "Credit" : "Debit";
          String Status = (String) parts.get(6);
          Transaction _transaction = new Transaction(Transaction_ID, userID, "undefined", Account_Num, Post_Date, Check_No, Description, Amount, Type, Status);
          result.add(_transaction);
          // read next line
          line = reader.readLine();
        }
      }
      if (type.equals("custom")) {
        while (line != null) {
          ArrayList parts = new ArrayList(List.of(line.split("\t")));
          String Transaction_ID = "";

          Integer userID = 1;

          String Post_Date_String = ((String) parts.get(1));
          java.util.Date utilDate = new SimpleDateFormat("MM/dd/yyyy").parse(Post_Date_String);

          java.sql.Date Post_Date = new java.sql.Date(utilDate.getTime());
          Integer Check_No = 0;


          String Description = (String) parts.get(2);
          if (Description!=null && Description.startsWith("Check ") ) {
            ArrayList descParts = new ArrayList(List.of(Description.split(" ")));
            Check_No = Integer.valueOf((String) descParts.get(1));
          }
          Double debitAmount = 0d;
          Double creditAmount = 0d;
          if (parts.get(3).equals("")) {
            creditAmount = 0d;
          } else {
            String creditString = (String) parts.get(3);
            creditString = creditString.replace(",","");
            creditString = creditString.replace("\"","");
            creditAmount = Double.valueOf(creditString);
          }
          if (parts.get(4).equals("")) {
            debitAmount = 0d;
          } else {
            String debitString = (String) parts.get(4);
            debitString = debitString.replace(",","");
            debitString = debitString.replace("\"","");
            try {
              debitAmount = Double.valueOf(debitString);
            }catch (Exception e) {
              int x = 0;
            }
          }
          Double Amount = creditAmount + debitAmount;
          String Type = Amount > 0 ? "Credit" : "Debit";
          String Status = "Posted";
          Transaction _transaction = new Transaction(Transaction_ID, userID, "undefined", accountNumber, Post_Date, Check_No, Description, Amount, Type, Status);
          result.add(_transaction);
          // read next line
          line = reader.readLine();
        }
      }

      if (type.equals("Altra")) {

        ArrayList AcctNum = new ArrayList(List.of(line.split("\t")));
        ArrayList AcctNum2 = new ArrayList(List.of(((String) AcctNum.get(0)).split(":")));
        String Account_Num = (String) AcctNum2.get(1);
        line = reader.readLine();
        line = reader.readLine();
        line = reader.readLine();
        line = reader.readLine();

        while (line != null) {

          ArrayList parts = new ArrayList(List.of(line.split("\t")));
          Integer userID = 1;
          String Transaction_ID = "";
          String Post_Date_String = ((String) parts.get(1));
          java.util.Date utilDate = new SimpleDateFormat("MM/dd/yyyy").parse(Post_Date_String);
          java.sql.Date Post_Date = new java.sql.Date(utilDate.getTime());
          Integer Check_No = 0;
          if (parts.size() > 6 && parts.get(6) != null && !parts.get(6).equals("")) {
            Check_No = Integer.valueOf((String) parts.get(6));
          }

          String Description = (String) parts.get(3);

          Double Amount = Double.valueOf((String) parts.get(4));
          String Type = Amount > 0 ? "Credit" : "Debit";
          String Status = "Posted";
          Transaction _transaction = new Transaction(Transaction_ID, userID, "undefined", Account_Num, Post_Date, Check_No, Description, Amount, Type, Status);
          result.add(_transaction);
          // read next line
          line = reader.readLine();
        }
      }

      reader.close();

    } catch (Exception e) {
      throw new RuntimeException(e.getMessage()+"\n\nCould not add Transactions. Try again later");
    }
    return result;
  }

  public  int add(Transaction _transaction) {
    int numRowsAffected = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Transaction( ?,?, ?, ?, ?, ?, ?, ?, ?)}")) {
          statement.setInt(1, _transaction.getUser_ID());
          statement.setString(2, "Uncategorized");
          statement.setString(3, _transaction.getBank_Account_ID());
          statement.setDate(4, (Date) _transaction.getPost_Date());
          statement.setInt(5, _transaction.getCheck_No());
          statement.setString(6, _transaction.getDescription());
          statement.setDouble(7, _transaction.getAmount());
          statement.setString(8, _transaction.getType());
          statement.setString(9, _transaction.getStatus());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Transaction. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Transaction. Try again later");
    }
    return numRowsAffected;
  }

  public  int addBatch(List<Transaction> _transactions, int user_id) {
    int added = 0;

    boolean result = true;
    int index = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        for (Transaction _transaction : _transactions) {

            try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Transaction( ?,?, ?, ?, ?, ?, ?, ?, ?)}")) {
              statement.setInt(1, user_id);
              statement.setString(2, "Uncategorized");
              statement.setString(3, _transaction.getBank_Account_ID());
              statement.setDate(4, (Date) _transaction.getPost_Date());
              statement.setInt(5, _transaction.getCheck_No());
              statement.setString(6, _transaction.getDescription());
              statement.setDouble(7, _transaction.getAmount());
              statement.setString(8, _transaction.getType());
              statement.setString(9, _transaction.getStatus());
              int numRowsAffected = statement.executeUpdate();
              added += numRowsAffected;

          } catch (SQLException e){
              continue;
            }
          index++;
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Transaction. Try again later");
    }
    return added;

  }

  public  List<Transaction_VM> getTransactionByUser(Integer User_ID) throws SQLException {
    return getTransactionByUser(User_ID, "", 0, 10, 0, "", 0);
  }

  public  List<Transaction_VM> getTransactionByUser(int User_ID, int pagesize) throws SQLException {
    return getTransactionByUser(User_ID, "", 0, pagesize, 0, "", 0);
  }

  public  List<Transaction_VM> getTransactionByUser(Integer User_ID, int pagesize, int offset) throws SQLException {
    return getTransactionByUser(User_ID, "", 0, pagesize, offset, "", 0);
  }

  public  List<Transaction_VM> getTransactionByUser(int userID, String category, int year, int pagesize, int offset, String sortBy, int order) throws SQLException {
    List<Transaction_VM> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      try (CallableStatement statement = connection.prepareCall("{CALL sp_retreive_Transaction_by_User(?,?,?,?,?,?,?)}")) {
        statement.setInt(1, userID);
        statement.setString(2, category);
        statement.setInt(3, year);
        statement.setInt(4, pagesize);
        statement.setInt(5, offset);
        statement.setString(6, sortBy);
        statement.setInt(7, order);

        try (ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {
            String Transaction_ID = resultSet.getString("Transaction_Transaction_ID");
            Integer User_ID = resultSet.getInt("Transaction_User_ID");
            String Category_ID = resultSet.getString("Transaction_Category_ID");
            String Account_Num = resultSet.getString("Transaction_Bank_Account_ID");
            Date Post_Date = resultSet.getDate("Transaction_Post_Date");
            Integer Check_No = resultSet.getInt("Transaction_Check_No");
            String Description = resultSet.getString("Transaction_Description");
            Double Amount = resultSet.getDouble("Transaction_Amount");
            String Type = resultSet.getString("Transaction_Type");
            String Status = resultSet.getString("Transaction_Status");
            int count = resultSet.getInt("Comment_Count");
            Transaction _result = new Transaction(Transaction_ID, User_ID, Category_ID, Account_Num, Post_Date, Check_No, Description, Amount, Type, Status);
            Transaction_VM __result = new Transaction_VM(_result,count);
            result.add(__result);
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  public  List<Transaction_VM> searchTransactionByUser(int userID, String query) throws SQLException {
    List<Transaction_VM> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      try (CallableStatement statement = connection.prepareCall("{CALL sp_find_transaction(?,?)}")) {
        statement.setInt(1, userID);
        statement.setString(2, query);

        try (ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {
            String Transaction_ID = resultSet.getString("Transaction_Transaction_ID");
            Integer User_ID = resultSet.getInt("Transaction_User_ID");
            String Category_ID = resultSet.getString("Transaction_Category_ID");
            String Account_Num = resultSet.getString("Transaction_Bank_Account_ID");
            Date Post_Date = resultSet.getDate("Transaction_Post_Date");
            Integer Check_No = resultSet.getInt("Transaction_Check_No");
            String Description = resultSet.getString("Transaction_Description");
            Double Amount = resultSet.getDouble("Transaction_Amount");
            String Type = resultSet.getString("Transaction_Type");
            String Status = resultSet.getString("Transaction_Status");
            int count = resultSet.getInt("Comment_Count");
            Transaction _result = new Transaction(Transaction_ID, User_ID, Category_ID, Account_Num, Post_Date, Check_No, Description, Amount, Type, Status);
            Transaction_VM __result = new Transaction_VM(_result,count);
            result.add(__result);
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }
  //getAnalysis

  public List<List<Category_VM>> getAnalysis(List<List<Category_VM>> years, int user_ID) throws SQLException {
    List<List<Category_VM>> result = new ArrayList<>();
    int startingYear=0;
    int loop=-1;
    try (Connection connection = getConnection()) {
          try (CallableStatement statement = connection.prepareCall("{CALL sp_total_Category_by_time(?)}")) {
           statement.setInt(1,user_ID);
            try (ResultSet resultSet = statement.executeQuery()) {
              while (resultSet.next()) {
                int Year = resultSet.getInt(1);
                String Category_ID = resultSet.getString(2);
                int count = resultSet.getInt(3);

                if (resultSet.wasNull()){
                  count = 0;
                }
                Double amount=resultSet.getDouble(4);
                if (resultSet.wasNull()){
                  count=0;
                  amount=0d;
                }
                if (Year!=startingYear){
                  result.add(new ArrayList<>());
                  startingYear=Year;
                  loop++;
                }
                Category_VM category = new Category_VM(Category_ID,amount ,count,Year);
                result.get(loop).add(category);
              }
            } catch (Exception e) {
              throw new RuntimeException(e);
            }
          }
    }
    return result;
  }

  public  int getTransactionCountByUser(int userID, String category, int year) throws SQLException {
    int result = 0;
    try (Connection connection = getConnection()) {
      try (CallableStatement statement = connection.prepareCall("{CALL sp_retreive_Transaction_by_User_count(?,?,?)}")) {
        statement.setInt(1, userID);
        statement.setString(2, category);
        statement.setInt(3, year);

        try (ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {
            result = resultSet.getInt(1);
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  public  double getTransactionCategoryTotal(int userID, String category_id, String direction) throws SQLException {
    double result = 0;
    try (Connection connection = getConnection()) {
      try (CallableStatement statement = connection.prepareCall("{CALL sp_transaction_category_total(?,?,?)}")) {
        statement.setInt(1, userID);
        statement.setString(2, category_id);
        statement.setString(3, direction);

        try (ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {
            result = resultSet.getDouble(1);
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  public  List<Transaction> getTransactionByUserYearCategpry(int userID, Date date, String category, int limit, int offset) throws SQLException {
    List<Transaction> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      try (CallableStatement statement = connection.prepareCall("{CALL sp_retreive_Transaction_by_User_Year_Category(?,?,?,?,?)}")) {
        statement.setInt(1, userID);
        statement.setDate(2, date);
        statement.setString(3, category);
        statement.setInt(4, limit);
        statement.setInt(5, offset);

        try (ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {
            String Transaction_ID = resultSet.getString("Transaction_Transaction_ID");
            Integer User_ID = resultSet.getInt("Transaction_User_ID");
            String Category_ID = resultSet.getString("Transaction_Category_ID");
            String Account_Num = resultSet.getString("Transaction_Bank_Account_ID");
            Date Post_Date = resultSet.getDate("Transaction_Post_Date");
            Integer Check_No = resultSet.getInt("Transaction_Check_No");
            String Description = resultSet.getString("Transaction_Description");
            Double Amount = resultSet.getDouble("Transaction_Amount");
            String Type = resultSet.getString("Transaction_Type");
            String Status = resultSet.getString("Transaction_Status");
            Integer User_User_ID = resultSet.getInt("User_User_ID");
            String User_User_Name = resultSet.getString("User_User_Name");
            //String User_User_PW = resultSet.getString("User_User_PW");
            String User_Email = resultSet.getString("User_Email");
            //String Category_Category_ID = resultSet.getString("Category_Category_ID");
            Transaction _result = new Transaction(Transaction_ID, User_ID, Category_ID, Account_Num, Post_Date, Check_No, Description, Amount, Type, Status);
            result.add(_result);
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }
  public Transaction_VM getTransactionByPrimaryKey(Transaction _transaction) throws SQLException{
    Transaction_VM result = new Transaction_VM();
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_pk_Transaction(?,?)}")) {
        statement.setString(1, _transaction.getTransaction_ID());
        statement.setInt(2, _transaction.getUser_ID());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){
            String Transaction_ID = resultSet.getString("Transaction_Transaction_ID");
            Integer User_ID = resultSet.getInt("Transaction_User_ID");
            String Category_ID = resultSet.getString("Transaction_Category_ID");
            String Account_Num = resultSet.getString("Transaction_Bank_Account_ID");
            Date Post_Date = resultSet.getDate("Transaction_Post_Date");
            Integer Check_No = resultSet.getInt("Transaction_Check_No");
            String Description = resultSet.getString("Transaction_Description");
            Double Amount = resultSet.getDouble("Transaction_Amount");
            String Type = resultSet.getString("Transaction_Type");
            String Status = resultSet.getString("Transaction_Status");
            Integer User_User_ID = resultSet.getInt("User_User_ID");
            String User_User_Name = resultSet.getString("User_User_Name");
            String User_User_PW = resultSet.getString("User_User_PW");
            String User_Email = resultSet.getString("User_Email");
            String Category_Category_ID = resultSet.getString("Category_Category_ID");
            Integer Category_User_ID = resultSet.getInt("Category_User_ID");
            Transaction _result = new Transaction( Transaction_ID, User_ID, Category_ID, Account_Num, Post_Date, Check_No, Description, Amount, Type, Status);
            result = new Transaction_VM(_result);
        }
          }
      }
      List<Transaction_Comment> _comments = new ArrayList<>();
      result.setTransaction_Comments(_comments);
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_Transaction_Comment_by_Transaction(?)}")) {
        statement.setString(1, _transaction.getTransaction_ID());
        try(ResultSet resultSet = statement.executeQuery()) {

          while (resultSet.next()) {
            Integer User_ID = resultSet.getInt("Transaction_Comment_User_ID");
            String Transaction_ID = resultSet.getString("Transaction_Comment_Transaction_ID");
            Integer Transaction_Comment_ID = resultSet.getInt("Transaction_Comment_Transaction_Comment_ID");
            String Content = resultSet.getString("Transaction_Comment_Content");
            java.util.Date Post_Date = resultSet.getDate("Transaction_Comment_Post_Date");

            Transaction_Comment _transaction_comment = new Transaction_Comment( User_ID, Transaction_ID, Transaction_Comment_ID, Content, Post_Date);
            _comments.add(_transaction_comment);
          }

        }
      }

  } catch (SQLException e) {
    throw new RuntimeException("Could not retrieve Transaction_Comments. Try again later");
  }


    return result;
  }


}

