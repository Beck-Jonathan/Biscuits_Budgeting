package com.beck.beck_demos.budget_app.data;

import com.beck.beck_demos.budget_app.iData.iReceiptDAO;
import com.beck.beck_demos.budget_app.models.Receipt;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.beck.beck_demos.budget_app.data.Database.getConnection;

public class ReceiptDAO implements iReceiptDAO {
  @Override
  public int add(Receipt _receipt) throws SQLException {
    int numRowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Receipt( ?, ?, ?, ?)}")){
          statement.setString(1,_receipt.getTransaction_ID());
          statement.setString(2,_receipt.getImage_Link());
          statement.setString(3,_receipt.getName());
          statement.setString(4,_receipt.getDescription());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Receipt. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Receipt. Try again later");
    }
    return numRowsAffected;
  }


  @Override
  public Receipt getReceiptByPrimaryKey(Receipt _receipt) throws SQLException {
    Receipt result = null;
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retrieve_by_pk_Receipt(?)}")) {
        statement.setString(1, _receipt.getReceipt_ID().toString());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){String Receipt_ID = resultSet.getString("Receipt_Receipt_ID");
            String Transaction_ID = resultSet.getString("Receipt_Transaction_ID");
            String Image_Link = resultSet.getString("Receipt_Image_Link");
            String Name = resultSet.getString("Receipt_Name");
            String Description = resultSet.getString("Receipt_Description");
            String Transaction_Transaction_ID = resultSet.getString("Transaction_Transaction_ID");
            Integer Transaction_User_ID = resultSet.getInt("Transaction_User_ID");
            String Transaction_Category_ID = resultSet.getString("Transaction_Category_ID");
            String Transaction_Bank_Account_ID = resultSet.getString("Transaction_Bank_Account_ID");
            Date Transaction_Post_Date = resultSet.getDate("Transaction_Post_Date");
            Integer Transaction_Check_No = resultSet.getInt("Transaction_Check_No");
            String Transaction_Description = resultSet.getString("Transaction_Description");
            Double Transaction_Amount = resultSet.getDouble("Transaction_Amount");
            String Transaction_Type = resultSet.getString("Transaction_Type");
            String Transaction_Status = resultSet.getString("Transaction_Status");
            boolean Transaction_Is_Locked = resultSet.getBoolean("Transaction_Is_Locked");
            result = new Receipt( Receipt_ID, Transaction_ID, Image_Link, Name, Description);}
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  @Override
  public List<Receipt> getReceiptbyTransaction(String Transaction_ID) throws SQLException {
    List<Receipt> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retrieve_Receipt_byTransaction(?,?,?)}")) {
          statement.setString(1,Transaction_ID)
          ;statement.setInt(2,100)
          ;statement.setInt(3,0);
          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              String Receipt_ID = resultSet.getString("Receipt_Receipt_ID");
              String _Transaction_ID = resultSet.getString("Receipt_Transaction_ID");
              String Image_Link = resultSet.getString("Receipt_Image_Link");
              String Name = resultSet.getString("Receipt_Name");
              String Description = resultSet.getString("Receipt_Description");
              String Transaction_Transaction_ID = resultSet.getString("Transaction_Transaction_ID");
              Integer Transaction_User_ID = resultSet.getInt("Transaction_User_ID");
              String Transaction_Category_ID = resultSet.getString("Transaction_Category_ID");
              String Transaction_Bank_Account_ID = resultSet.getString("Transaction_Bank_Account_ID");
              Date Transaction_Post_Date = resultSet.getDate("Transaction_Post_Date");
              Integer Transaction_Check_No = resultSet.getInt("Transaction_Check_No");
              String Transaction_Description = resultSet.getString("Transaction_Description");
              Double Transaction_Amount = resultSet.getDouble("Transaction_Amount");
              String Transaction_Type = resultSet.getString("Transaction_Type");
              String Transaction_Status = resultSet.getString("Transaction_Status");
              boolean Transaction_Is_Locked = resultSet.getBoolean("Transaction_Is_Locked");
              Receipt _receipt = new Receipt( Receipt_ID, _Transaction_ID, Image_Link, Name, Description);
              result.add(_receipt);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Receipts. Try again later");
    }
    return result;
  }


  @Override
  public int update(Receipt oldReceipt, Receipt newReceipt) throws SQLException {
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_Receipt(? ,?,?,?,?,?,?,?,?)}"))
        {
          statement.setString(1,oldReceipt.getReceipt_ID());
          statement.setString(2,oldReceipt.getTransaction_ID());
          statement.setString(3,newReceipt.getTransaction_ID());
          statement.setString(4,oldReceipt.getImage_Link());
          statement.setString(5,newReceipt.getImage_Link());
          statement.setString(6,oldReceipt.getName());
          statement.setString(7,newReceipt.getName());
          statement.setString(8,oldReceipt.getDescription());
          statement.setString(9,newReceipt.getDescription());
          result=statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update Receipt . Try again later");
        }
      }
    }
    return result;
  }

  @Override
  public List<Receipt> getAllReceipt(int offset, int limit, String search, String Transaction_ID, String user_id) throws SQLException {
    List<Receipt> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_retrieve_by_all_Receipt(?,?,?,?,?)}")) {
          statement.setInt(1, limit);
          statement.setInt(2, offset);
          statement.setString(3, search);
          statement.setString(4, Transaction_ID);
          statement.setString(5,user_id);
          try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              String Receipt_ID = resultSet.getString("Receipt_Receipt_ID");
              String _Transaction_ID = resultSet.getString("Receipt_Transaction_ID");
              String Image_Link = resultSet.getString("Receipt_Image_Link");
              String Name = resultSet.getString("Receipt_Name");
              String Description = resultSet.getString("Receipt_Description");
              String Transaction_Transaction_ID = resultSet.getString("Transaction_Transaction_ID");
              Integer Transaction_User_ID = resultSet.getInt("Transaction_User_ID");
              String Transaction_Category_ID = resultSet.getString("Transaction_Category_ID");
              String Transaction_Bank_Account_ID = resultSet.getString("Transaction_Bank_Account_ID");
              Date Transaction_Post_Date = resultSet.getDate("Transaction_Post_Date");
              Integer Transaction_Check_No = resultSet.getInt("Transaction_Check_No");
              String Transaction_Description = resultSet.getString("Transaction_Description");
              Double Transaction_Amount = resultSet.getDouble("Transaction_Amount");
              String Transaction_Type = resultSet.getString("Transaction_Type");
              String Transaction_Status = resultSet.getString("Transaction_Status");
              boolean Transaction_Is_Locked = resultSet.getBoolean("Transaction_Is_Locked");
              Receipt _receipt = new Receipt(Receipt_ID, _Transaction_ID, Image_Link, Name, Description);
              result.add(_receipt);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Receipts. Try again later");
    }
    return result;
  }


  @Override
  public int deleteReceipt(String Receipt_ID) throws SQLException {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_Delete_Receipt( ?)}")){
          statement.setString(1,Receipt_ID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Delete Receipt. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Delete Receipt. Try again later");
    }
    return rowsAffected;
  }

  @Override
  public int getReceiptCount(String searchTerm, String transactionId, String user_id) {
    int result =0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_count_by_all_Receipt(?,?,?)}")) {
          statement.setString(1, searchTerm);
          statement.setString(2, transactionId);
          statement.setString(3, user_id);
          try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              result = resultSet.getInt(1);

            }
          }
        }
      }

        } catch (SQLException e) {
          throw new RuntimeException("Could not retrieve Receipts. Try again later");
        }

      return result;
  }
}
