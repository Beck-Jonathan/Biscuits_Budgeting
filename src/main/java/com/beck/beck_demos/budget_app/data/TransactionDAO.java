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
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.beck.beck_demos.budget_app.data.Database.getConnection;
public class TransactionDAO implements iTransactionDAO {

  public int update_category(Transaction oldTransaction, Transaction newTransaction) throws SQLException {
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_update_Transaction_category(?,?,?)}")) {
          statement.setString(1, oldTransaction.getTransaction_ID());
          statement.setString(2, oldTransaction.getUser_ID());

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
   *
   * @param oldTransaction the Transaction to be updated
   * @param newTransaction the updated version of the Transaction
   * @return number of records updated
   * @author Jonathan Beck
   */

  public int update(Transaction oldTransaction, Transaction newTransaction) throws SQLException {
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_update_Transaction(?,?,?,?,?)}")) {
          statement.setString(1, oldTransaction.getTransaction_ID());
          statement.setString(2, oldTransaction.getUser_ID());

          statement.setString(3, oldTransaction.getCategory_ID());
          statement.setString(4, newTransaction.getCategory_ID());

          statement.setBoolean(5, newTransaction.getIs_Locked());
          result = statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update Transaction . Try again later");
        }
      }
    }
    return result;
  }

  @Override
  public int toggleLockTransaction(Transaction transaction) throws SQLException {
    int rowsAffected = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_toggle_lock_Transaction( ?,?)}")) {
          statement.setString(1, transaction.getTransaction_ID());
          statement.setString(2, transaction.getUser_ID());

          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not (un)Lock Transaction. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not (un)Lock Transaction. Try again later");
    }
    return rowsAffected;
  }

  @Override
  public int writeTransactionToFile(List<Transaction> Transactions, String path) throws IOException {

    int result = 0;
    File file = new File(path);

// Ensure the directory exists
    if (file.getParentFile() != null) {
      file.getParentFile().mkdirs();
    }
    try (PrintWriter writer = new PrintWriter(file, StandardCharsets.UTF_8)) {
      writer.println("Transaction_ID\tUser_ID\tCategory_ID\tBank_Account_ID\tPost_Date\tCheck_No\tDescription\tAmount\tType\tStatus\tIs_Locked");
      for (Transaction _transaction : Transactions) {

        writer.print(_transaction.getTransaction_ID());
        writer.print("\t" + _transaction.getUser_ID());
        writer.print("\t" + _transaction.getCategory_ID());
        writer.print("\t" + _transaction.getBank_Account_ID());
        writer.print("\t" + _transaction.getPost_Date());
        writer.print("\t" + _transaction.getCheck_No());
        writer.print("\t" + _transaction.getDescription());
        writer.print("\t" + _transaction.getAmount());
        writer.print("\t" + _transaction.getType());
        writer.print("\t" + _transaction.getStatus());
        writer.print("\t" + _transaction.getIs_Locked());
        writer.print("\n");

        result++;
      }
    }

    return result;
  }

  @Override
  public List<List<SubCategory_VM>> getAnnualAnalysis(String user_ID, String BankAccountID) {
    return executeAnalysis("{CALL sp_total_Category_by_year(?,?)}", stmt -> {
      stmt.setString(1, user_ID);
      stmt.setString(2, BankAccountID);
    });
  }

  @Override
  public List<List<SubCategory_VM>> getMonthlyAnalysis(String user_ID, String BankAccountID, int year) {
    return executeAnalysis("{CALL sp_total_Category_by_month(?, ?,?)}", stmt -> {
      stmt.setString(1, user_ID);
      stmt.setString(2,BankAccountID);
      stmt.setInt(3, year);
    });
  }

  // Helper to handle the "List of Lists" grouping and mapping
  private List<List<SubCategory_VM>> executeAnalysis(String sql, StatementBinder binder) {
    List<List<SubCategory_VM>> result = new ArrayList<>();
    int currentPeriod = -1;
    List<SubCategory_VM> currentPeriodList = null;

    try (Connection connection = getConnection();
         CallableStatement stmt = connection.prepareCall(sql)) {

      binder.bind(stmt);

      try (ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
          SubCategory_VM vm = new SubCategory_VM();
          // Use the standardized name from our SQL updates
          vm.setYear(rs.getInt("period_val"));
          vm.setCategory_ID(rs.getString("subcategory_id"));
          vm.setParentCategoryId(rs.getString("parent_category_id"));
          vm.setCategory_Name(rs.getString("category_name"));
          vm.setcolor_id(rs.getString("color_id"));
          vm.setAmount(rs.getDouble("amount"));
          vm.setCount(rs.getInt("count"));
          vm.setTransactionType(rs.getString("transaction_type"));

          // Logic to start a new list when the period (Year or Month) changes
          if (vm.getYear() != currentPeriod) {
            currentPeriodList = new ArrayList<>();
            result.add(currentPeriodList);
            currentPeriod = vm.getYear();
          }

          if (currentPeriodList != null) {
            currentPeriodList.add(vm);
          }
        }
      }
    } catch (Exception e) {
      throw new RuntimeException("Analysis query failed", e);
    }
    return result;
  }

  // Small functional interface for the helper
  @FunctionalInterface
  interface StatementBinder {
    void bind(CallableStatement stmt) throws SQLException;
  }

  @Override
  public List<Transaction> getDistinctTransactionForDropdown(String user_ID) throws SQLException {
    List<Transaction> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_select_distinct_and_active_Transaction_for_dropdown(?)}")) {
          statement.setString(1, user_ID);
          try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              String Transaction_ID = resultSet.getString("Transaction_Transaction_ID");
              String Description = resultSet.getString("Transaction_Description");
              Transaction _transaction = new Transaction(Transaction_ID);
              _transaction.setDescription(Description);
              result.add(_transaction);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Transactions. Try again later");
    }
    return result;
  }

  @Override
  public int applyAllLines(String userId, Saved_Search_Order_VM savedSearchOrder) throws SQLException {
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_assign_SavedSearchOrder(?,?)}")) {

          statement.setString(1, userId);
          statement.setString(2, savedSearchOrder.getSaved_Search_Order_ID());
        }
      }

    }

    return result;
  }

  public int bulkUpdateCategory(String userid, String category, String query) throws SQLException {
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_assign_categories(?,?,?)}")) {
          statement.setString(1, userid);
          statement.setString(2, category);

          statement.setString(3, query);

          try (ResultSet resultSet = statement.executeQuery()) {
            result = statement.getUpdateCount();
          } catch (SQLException e) {
            throw new RuntimeException("Could not update Transaction . Try again later");
          }
        }
      }
    }

    return result;
  }

  //getTransactionForExportByUser
  public List<Transaction> getTransactionForExportByUser(String userID) throws SQLException {
    List<Transaction> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      try (CallableStatement statement = connection.prepareCall("{CALL sp_transaction_for_export(?)}")) {
        statement.setString(1, userID);

        try (ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {
            String Transaction_ID = resultSet.getString("Transaction_Transaction_ID");
            String User_ID = resultSet.getString("Transaction_User_ID");
            String Category_ID = resultSet.getString("Transaction_Category_ID");
            String Account_Num = resultSet.getString("Transaction_Bank_Account_ID");
            Date Post_Date = resultSet.getDate("Transaction_Post_Date");
            Integer Check_No = resultSet.getInt("Transaction_Check_No");
            String Description = resultSet.getString("Transaction_Description");
            Double Amount = resultSet.getDouble("Transaction_Amount");
            String Type = resultSet.getString("Transaction_Type");
            String Status = resultSet.getString("Transaction_Status");
            boolean Is_Locked = resultSet.getBoolean("Transaction_Is_Locked");

            Transaction _result = new Transaction(Transaction_ID, User_ID, Category_ID, Account_Num, Post_Date, Check_No, Description, Amount, Type, Status, Is_Locked);
            result.add(_result);
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  public List<Transaction> getTransactionFromFile(File uploadedFile, String type) {
    List<Transaction> result = new ArrayList<>();
    String accountNumber = "";

    try (BufferedReader reader = new BufferedReader(new FileReader(uploadedFile))) {
      String line;
      while ((line = reader.readLine()) != null) {
        line = line.trim();
        if (line.isEmpty()) continue;

        // --- 1. Detect File Type & Account Info ---

        // GreenState Detection: Look for the specific header row
        if (line.startsWith("Account Number") && line.contains("Post Date")) {
          type = "GreenState";
          continue;
        }

        //Custom Detection, look for "Custom"
        else if (line.startsWith("Custom")) {
          type = "Custom";
          String[] parts = line.split("\t");
          accountNumber = parts[2];
          continue;
        }

        // Altra Detection: Look for "Account Type:"
        else if (line.startsWith("Account Type:")) {
          type = "Altra";
          continue;
        }

        // Altra Account Number Extraction
        if (line.startsWith("Account Number") && line.contains(":")) {
          accountNumber = line.split(":")[1].trim();
          continue;
        }

        // Skip common noise lines
        if (line.startsWith("Transaction Type") || line.startsWith("Date Range:")) {
          continue;
        }

        // --- 2. Process Data Lines ---
        Transaction _transaction = null;
        try {
          if ("Altra".equals(type)) {
            _transaction = readAltraLine(line, accountNumber);
          } else if ("GreenState".equals(type)) {
            _transaction = readGreenStateLine(line);
          } else if ("Custom".equals(type)) {
            _transaction = readCustomLine(line, accountNumber);
          }
        } catch (Exception e) {
          // Log the error but keep processing the rest of the file
          System.err.println("Skipping line. Type: " + type + " | Error: " + e.getMessage());
        }

        if (_transaction != null) {
          result.add(_transaction);
        }
      }
    } catch (IOException e) {
      throw new RuntimeException("File access error: " + e.getMessage());
    }
    return result;
  }

  private Transaction readGreenStateLine(String line) throws ParseException {
    Transaction t = new Transaction();

    String[] parts = null;
    try {
      // Split on 2 or more spaces. This keeps "WESTSIDE TIRE CO" together
      // but separates the columns: [Account, Date, Description, Code, Amount, Status, Balance]
      parts = line.split("\t");
      // GreenState lines repeat the account number at index 0
      String accountNumber = parts[0];

      java.util.Date utilDate = new SimpleDateFormat("MM/dd/yyyy").parse(parts[1]);
      java.sql.Date postDate = new java.sql.Date(utilDate.getTime());

      // Based on your data:
      // parts[2] = Description (e.g., TROPICAL SMOOTHI...)
      // parts[3] = A numeric code (e.g., 15)
      // parts[4] = The Amount (e.g., 9.08)
      String description = parts[3];
      //subparts = parts[2].split("\t");
      String part4 = parts[4];
      String part5 = parts[5];
      String transType = "Debit";
      double amount = 0d;
      try {
        if (!part4.isEmpty()) {
          amount = Double.parseDouble(parts[4]);
        } else if (!part5.isEmpty()) {
          amount = Double.parseDouble(parts[5]);
          transType = "Credit";
        }
      } catch (Exception e) {
        throw new Exception("unable to parse value");
      }

      //subparts = parts[3].split("\t");
      String status = parts[6]; // "Pending"

      // Defaulting to Debit as per the sample, but you can add logic if
      // GreenState provides a specific Credit column index.

      return new Transaction("", "", "undefined", accountNumber, postDate, 0, description, amount, transType, status, false);

    } catch (Exception e) {
      return null;
    }

  }

  private Transaction readCustomLine(String line, String accountNumber) throws ParseException {
    String[] parts = line.split("\t");
    java.util.Date utilDate = new SimpleDateFormat("MM/dd/yyyy").parse(parts[1]);
    java.sql.Date postDate = new java.sql.Date(utilDate.getTime());

    String description = parts[2];
    Integer checkNo = 0;
    if (description.startsWith("Check ")) {
      checkNo = Integer.valueOf(description.split(" ")[1]);
    }
    String type = "";
    String creditStr = parts[3].replaceAll("[,\"]", "");
    String debitStr = parts[4].replaceAll("[,\"]", "");

    double credit = creditStr.isEmpty() ? 0d : Math.abs(Double.parseDouble(creditStr));
    double debit = debitStr.isEmpty() ? 0d : Math.abs(Double.parseDouble(debitStr));
    if (credit-0d<.01){
      type = "debit";
    }
    if (debit-0d<.01){
      type = "credit";
    }
    double amount =credit + debit; // Ensure positive


    return new Transaction("", "", "undefined", accountNumber, postDate, checkNo, description, amount, type, "Posted", false);
  }

  private Transaction readAltraLine(String line, String accountNumber) {
    // Split by tab, but handle multiple tabs as one if necessary
    String[] parts = line.split("\t");

    // Safety check: Altra lines need at least Date, Description, and Amount
    if (parts.length < 5) {
      return null;
    }
    boolean error = false;
    try {

      // Date is parts[1]
      java.util.Date utilDate = new SimpleDateFormat("M/d/yyyy").parse(parts[1].trim());
      java.sql.Date postDate = new java.sql.Date(utilDate.getTime());

      // Description is parts[2] (Transaction Type is [0])
      String description = parts[2].trim() + " " + parts[3].trim();

      // Amount is parts[4]
      double rawAmount = Double.parseDouble(parts[4].trim());
      String transType = (rawAmount < 0) ? "Debit" : "Credit";
      double amount = Math.abs(rawAmount);

      // Check number is parts[6] if it exists
      Integer checkNo = (parts.length > 6 && !parts[6].trim().isEmpty()) ?
          Integer.valueOf(parts[6].trim()) : 0;

      return new Transaction("", "", "undefined", accountNumber, postDate, checkNo, description, amount, transType, "Posted", false);
    } catch (Exception e) {
      error = true;
    }
    if (error) {
      try {
        accountNumber = parts[0].trim();
        // Date is parts[1]
        java.util.Date utilDate = new SimpleDateFormat("M/d/yyyy").parse(parts[2].trim());
        java.sql.Date postDate = new java.sql.Date(utilDate.getTime());

        // Description is parts[2] (Transaction Type is [0])
        String description = parts[2].trim() + " " + parts[3].trim();

        // Amount is parts[4]
        String rawInput = parts[7].trim();
        double rawAmount;

// 1. Check if it is a negative (accounting format)
        boolean isNegative = rawInput.contains("(") || rawInput.contains("-");

// 2. Remove $, commas, and parentheses using Regex
// [^0-9.] means "keep everything that IS NOT a number or a decimal point"
        String cleanNumeric = rawInput.replaceAll("[^0-9.]", "");

// 3. Parse and apply the negative sign if needed
        try {
          rawAmount = Double.parseDouble(cleanNumeric);
          if (isNegative) {
            rawAmount *= -1;
          }
        } catch (NumberFormatException e) {
          rawAmount = 0.0; // Fallback for empty or corrupted strings
        }

// Result for your logic:
        String transType = (rawAmount < 0) ? "Debit" : "Credit";
        double amount = Math.abs(rawAmount);

        // Check number is parts[6] if it exists
        Integer checkNo = (parts.length > 6 && !parts[6].trim().isEmpty()) ?
            Integer.valueOf(parts[4].trim()) : 0;

        return new Transaction("", "", "undefined", accountNumber, postDate, checkNo, description, amount, transType, "Posted", false);

      } catch (Exception e) {
        return null;
      }
    }
    return null;
  }

  private Transaction readHSALine(String line) throws ParseException {
    String[] parts = line.split("\t");
    java.util.Date utilDate = new SimpleDateFormat("MM/dd/yyyy").parse(parts[3]);
    java.sql.Date postDate = new java.sql.Date(utilDate.getTime());

    String description = parts[1];
    String amountStr = parts[4].replaceAll("[\\$,\\\"\\)]", "").replace("(", "-").trim();

    double amount = Math.abs(Double.parseDouble(amountStr)); // Force positive
    String status = parts.length > 5 ? parts[5] : "Posted";

    return new Transaction("", "", "undefined", parts[0], postDate, 0, description, amount, "HSA", status, false);
  }

  public int add(Transaction _transaction) {
    int numRowsAffected = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Transaction( ?,?, ?, ?, ?, ?, ?, ?, ?)}")) {
          statement.setString(1, _transaction.getUser_ID());
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

  public int addBatch(List<Transaction> _transactions, String user_id) {
    int added = 0;

    try (Connection connection = getConnection()) {
      if (connection == null) return 0;

      // Keep AutoCommit TRUE so successful rows stay in the DB
      // even if others fail.

      try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_transaction(?,?,?,?,?,?,?,?)}")) {

        for (Transaction t : _transactions) {
          statement.setString(1, user_id);
          statement.setString(2, t.getBank_Account_ID());
          statement.setDate(3, new java.sql.Date(t.getPost_Date().getTime()));
          statement.setInt(4, t.getCheck_No());
          statement.setString(5, t.getDescription());
          statement.setDouble(6, t.getAmount());
          statement.setString(7, t.getType());
          statement.setString(8, t.getStatus());

          statement.addBatch();
        }

        int[] results = statement.executeBatch();

        for (int res : results) {
          // SUCCESS_NO_INFO (-2) or rows affected > 0
          if (res > 0 || res == CallableStatement.SUCCESS_NO_INFO) {
            added++;
          }
        }

      } catch (BatchUpdateException e) {
        // This catches errors (like duplicates) while allowing
        // the driver to attempt the rest of the batch.
        int[] updateCounts = e.getUpdateCounts();
        for (int res : updateCounts) {
          if (res > 0 || res == CallableStatement.SUCCESS_NO_INFO) {
            added++;
          }
        }
        System.err.println("Some transactions were skipped due to errors: " + e.getMessage());
      }
    } catch (SQLException e) {
      throw new RuntimeException("Database connection error", e);
    }
    return added;

  }

  @Override
  public List<Transaction_VM> getTransactionByUser(String userID, String category, String Bank_Account_ID, int year, int month, int pagesize, int offset, String sortBy, int order, boolean findErrors, boolean showLocked) throws SQLException {
    List<Transaction_VM> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      try (CallableStatement statement = connection.prepareCall("{CALL sp_retreive_Transaction_by_User(?,?,?,?,?,?,?,?,?,?,?)}")) {
        statement.setString(1, userID);
        statement.setString(2, category);
        statement.setString(3, Bank_Account_ID);
        statement.setInt(4, year);
        statement.setInt(5, month);
        statement.setInt(6, pagesize);
        statement.setInt(7, offset);
        statement.setString(8, sortBy);
        statement.setInt(9, order);
        statement.setBoolean(10, findErrors);
        statement.setBoolean(11, showLocked);

        try (ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {
            String Transaction_ID = resultSet.getString("Transaction_Transaction_ID");
            String User_ID = resultSet.getString("Transaction_User_ID");
            String Category_ID = resultSet.getString("Transaction_Category_ID");
            String Account_Num = resultSet.getString("Transaction_Bank_Account_ID");
            Date Post_Date = resultSet.getDate("Transaction_Post_Date");
            Integer Check_No = resultSet.getInt("Transaction_Check_No");
            String Description = resultSet.getString("Transaction_Description");
            Double Amount = resultSet.getDouble("Transaction_Amount");
            String Type = resultSet.getString("Transaction_Type");
            String Status = resultSet.getString("Transaction_Status");
            boolean Is_Locked = resultSet.getBoolean("Transaction_Is_Locked");
            int count = resultSet.getInt("Comment_Count");
            Transaction _result = new Transaction(Transaction_ID, User_ID, Category_ID, Account_Num, Post_Date, Check_No, Description, Amount, Type, Status, Is_Locked);
            Transaction_VM __result = new Transaction_VM(_result, count);
            result.add(__result);
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  public List<Transaction_VM> searchTransactionByUser(String userID, String query) throws SQLException {
    List<Transaction_VM> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      try (CallableStatement statement = connection.prepareCall("{CALL sp_find_transaction(?,?)}")) {
        statement.setString(1, userID);
        statement.setString(2, query);

        try (ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {
            String Transaction_ID = resultSet.getString("Transaction_Transaction_ID");
            String User_ID = resultSet.getString("Transaction_User_ID");
            String Category_ID = resultSet.getString("Transaction_Category_ID");
            String Account_Num = resultSet.getString("Transaction_Bank_Account_ID");
            Date Post_Date = resultSet.getDate("Transaction_Post_Date");
            Integer Check_No = resultSet.getInt("Transaction_Check_No");
            String Description = resultSet.getString("Transaction_Description");
            Double Amount = resultSet.getDouble("Transaction_Amount");
            String Type = resultSet.getString("Transaction_Type");
            String Status = resultSet.getString("Transaction_Status");
            boolean Is_Locked = resultSet.getBoolean("Transaction_Is_Locked");
            int count = resultSet.getInt("Comment_Count");
            Transaction _result = new Transaction(Transaction_ID, User_ID, Category_ID, Account_Num, Post_Date, Check_No, Description, Amount, Type, Status, Is_Locked);
            Transaction_VM __result = new Transaction_VM(_result, count);
            result.add(__result);
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  @Override
  public int getTransactionCountByUser(String userID, String category, String Bank_Account_ID, int year, int month, boolean findErrors, boolean showLocked) throws SQLException {
    int result = 0;
    try (Connection connection = getConnection()) {
      try (CallableStatement statement = connection.prepareCall("{CALL sp_retreive_Transaction_by_User_count(?,?,?,?,?,?,?)}")) {
        statement.setString(1, userID);
        statement.setString(2, category);
        statement.setString(3, Bank_Account_ID);
        statement.setInt(4, year);
        statement.setInt(5, month);
        statement.setBoolean(6, findErrors);
        statement.setBoolean(7, showLocked);

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

  public double getTransactionCategoryTotal(int userID, String category_id, String direction) throws SQLException {
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

  public List<Transaction> getTransactionByUserYearCategpry(int userID, Date date, String category, int limit, int offset) throws SQLException {
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
            String User_ID = resultSet.getString("Transaction_User_ID");
            String Category_ID = resultSet.getString("Transaction_Category_ID");
            String Account_Num = resultSet.getString("Transaction_Bank_Account_ID");
            Date Post_Date = resultSet.getDate("Transaction_Post_Date");
            Integer Check_No = resultSet.getInt("Transaction_Check_No");
            String Description = resultSet.getString("Transaction_Description");
            Double Amount = resultSet.getDouble("Transaction_Amount");
            String Type = resultSet.getString("Transaction_Type");
            String Status = resultSet.getString("Transaction_Status");
            boolean Is_Locked = resultSet.getBoolean("Transaction_Is_Locked");
            Integer User_User_ID = resultSet.getInt("User_User_ID");
            String User_User_Name = resultSet.getString("User_User_Name");
            //String User_User_PW = resultSet.getString("User_User_PW");
            String User_Email = resultSet.getString("User_Email");
            //String Category_Category_ID = resultSet.getString("Category_Category_ID");
            Transaction _result = new Transaction(Transaction_ID, User_ID, Category_ID, Account_Num, Post_Date, Check_No, Description, Amount, Type, Status, Is_Locked);
            result.add(_result);
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  public Transaction_VM getTransactionByPrimaryKey(Transaction _transaction) throws SQLException {
    Transaction_VM result = new Transaction_VM();
    try (Connection connection = getConnection()) {
      try (CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_pk_Transaction(?,?)}")) {
        statement.setString(1, _transaction.getTransaction_ID());
        statement.setString(2, _transaction.getUser_ID());

        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            String Transaction_ID = resultSet.getString("Transaction_Transaction_ID");
            String User_ID = resultSet.getString("Transaction_User_ID");
            String Category_ID = resultSet.getString("Transaction_Category_ID");
            String Account_Num = resultSet.getString("Transaction_Bank_Account_ID");
            Date Post_Date = resultSet.getDate("Transaction_Post_Date");
            Integer Check_No = resultSet.getInt("Transaction_Check_No");
            String Description = resultSet.getString("Transaction_Description");
            Double Amount = resultSet.getDouble("Transaction_Amount");
            String Type = resultSet.getString("Transaction_Type");
            String Status = resultSet.getString("Transaction_Status");
            boolean Is_Locked = resultSet.getBoolean("Transaction_Is_Locked");
            String User_User_ID = resultSet.getString("User_User_ID");
            String User_User_Name = resultSet.getString("User_User_Name");
            String User_User_PW = resultSet.getString("User_User_PW");
            String User_Email = resultSet.getString("User_Email");
            String Category_Category_ID = resultSet.getString("Category_Category_ID");
            String Category_User_ID = resultSet.getString("Category_User_ID");
            Transaction _result = new Transaction(Transaction_ID, User_ID, Category_ID, Account_Num, Post_Date, Check_No, Description, Amount, Type, Status, Is_Locked);
            result = new Transaction_VM(_result);
          }
        }
      }
      List<Transaction_Comment> _comments = new ArrayList<>();
      result.setTransaction_Comments(_comments);
      try (CallableStatement statement = connection.prepareCall("{CALL sp_retreive_Transaction_Comment_by_Transaction(?)}")) {
        statement.setString(1, _transaction.getTransaction_ID());
        try (ResultSet resultSet = statement.executeQuery()) {

          while (resultSet.next()) {
            String User_ID = resultSet.getString("Transaction_Comment_User_ID");
            String Transaction_ID = resultSet.getString("Transaction_Comment_Transaction_ID");
            Integer Transaction_Comment_ID = resultSet.getInt("Transaction_Comment_Transaction_Comment_ID");
            String Content = resultSet.getString("Transaction_Comment_Content");
            java.util.Date Post_Date = resultSet.getDate("Transaction_Comment_Post_Date");

            Transaction_Comment _transaction_comment = new Transaction_Comment(User_ID, Transaction_ID, Transaction_Comment_ID, Content, Post_Date);
            _comments.add(_transaction_comment);
          }

        }
      } catch (Exception e) {
        throw new RuntimeException("Could not retrieve Transaction_Comments. Try again later");
      }
      List<Receipt> receipts = new ArrayList<>();
      result.setReceipts(receipts);
      try (CallableStatement statement = connection.prepareCall("{CALL sp_retrieve_Receipt_by_Transaction(?,?,?)}")) {
        statement.setInt(1, 100);
        statement.setInt(2, 0);
        statement.setString(3, _transaction.getTransaction_ID());
        try (ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {
            String Receipt_ID = resultSet.getString("Receipt_Receipt_ID");
            String Transaction_ID = resultSet.getString("Receipt_Transaction_ID");
            String Image_Link = resultSet.getString("Receipt_Image_Link");
            String Name = resultSet.getString("Receipt_Name");
            String Description = resultSet.getString("Receipt_Description");

            Receipt _receipt = new Receipt(Receipt_ID, Transaction_ID, Image_Link, Name, Description);
            receipts.add(_receipt);
          }
        }
      }

    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Receipts. Try again later");
    }

    return result;
  }

  @Override
  public List<List<SubCategory_VM>> getSuperAnnualAnalysis(String user_ID, String BankAccountID) {
    // Note: Added a second '?' to the CALL string to match the two parameters
    return executeAnalysis("{CALL sp_total_SuperCategory_by_year(?, ?)}", stmt -> {
      stmt.setString(1, user_ID);
      stmt.setString(2, BankAccountID);
    });
  }

  @Override
  public List<List<SubCategory_VM>> getSuperMonthlyAnalysis(String user_ID, String BankAccountID, int year) {
    // Note: Added a third '?' to the CALL string to match the three parameters
    return executeAnalysis("{CALL sp_total_SuperCategory_by_month(?, ?, ?)}", stmt -> {
      stmt.setString(1, user_ID);
      stmt.setString(2, BankAccountID);
      stmt.setInt(3, year);
    });
  }

  @Override
  public List<List<SubCategory_VM>> getForecastAnalysis(String user_ID, LocalDate startDate, int monthsBack, int monthsForward, int retirementOffset) {
    List<List<SubCategory_VM>> result = new ArrayList<>();
    // Map to group categories by their target_period (the 'columns' in your list of lists)
    Map<String, List<SubCategory_VM>> monthlyMap = new LinkedHashMap<>();

    try (Connection connection = getConnection()) {
      try (CallableStatement statement = connection.prepareCall("{CALL get_category_forecast(?,?,?,?,?)}")) {
        statement.setString(1, user_ID);
        statement.setInt(2, monthsBack);
        statement.setInt(3, monthsForward);
        statement.setDate(4, java.sql.Date.valueOf(startDate));
        statement.setInt(5, retirementOffset);

        try (ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {
            // Extract variables matching your specific SP column naming
            int Month_Offset = resultSet.getInt("month_offset");
            String Target_Period = resultSet.getString("target_period");
            String Category_Name = resultSet.getString("category_Name");
            String Category_ID = resultSet.getString("category_id");
            Double Forecasted_Amount = resultSet.getDouble("forecasted_amount");
            String category_type = resultSet.getString("category_type");

            // Initialize VM object
            SubCategory_VM _vm = new SubCategory_VM(Category_ID, Forecasted_Amount);
            _vm.setCategory_Name(Category_Name);
            _vm.setSign(Target_Period); // Storing the period string ("2026-04")
            _vm.setYear(Month_Offset);  // Storing the step offset (1, 2, 3...)
            _vm.setTransactionType(category_type);
            _vm.setIs_Locked(true);

            // Grouping logic to build the List of Lists
            if (!monthlyMap.containsKey(Target_Period)) {
              monthlyMap.put(Target_Period, new ArrayList<>());
            }
            monthlyMap.get(Target_Period).add(_vm);

          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    // Convert map values to the List<List<>> format required by the interface
    result.addAll(monthlyMap.values());
    return result;
  }

}
 


