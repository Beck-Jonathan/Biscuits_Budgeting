package com.beck.beck_demos.budget_app.data;

import com.beck.beck_demos.budget_app.iData.iTransaction_CommentDAO;
import com.beck.beck_demos.budget_app.models.Transaction_Comment;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import com.beck.beck_demos.budget_app.models.Transaction_Comment;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import static com.beck.beck_demos.budget_app.data.Database.getConnection;

public class Transaction_CommentDAO implements iTransaction_CommentDAO {
  /**
   * DAO Method to add Transaction_Comment objects
   * @param _transaction_comment the Transaction_Comment to be added
   * @return number of records added
   * @author Jonathan Beck
   */
  @Override
  public int add(Transaction_Comment _transaction_comment) throws SQLException {
    int numRowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Transaction_Comment( ?, ?, ?, ?)}")){
          statement.setInt(1,_transaction_comment.getUser_ID());
          statement.setInt(2,_transaction_comment.getTransaction_ID());
          statement.setInt(3,_transaction_comment.getTransaction_Comment_ID());
          statement.setString(4,_transaction_comment.getContent());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Transaction_Comment. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Transaction_Comment. Try again later");
    }
    return numRowsAffected;
  }


  @Override
  public List<Transaction_Comment> getTransaction_CommentbyTransaction(Integer _Transaction_ID) throws SQLException {

      List<Transaction_Comment> result = new ArrayList<>();
      try (Connection connection = getConnection()) {
        if (connection != null) {
          try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_Transaction_Comment_byTransaction(?)}")) {
            statement.setInt(1,_Transaction_ID);
            try(ResultSet resultSet = statement.executeQuery()) {
              while (resultSet.next()) {Integer User_ID = resultSet.getInt("Transaction_Comment_User_ID");
                Integer Transaction_ID = resultSet.getInt("Transaction_Comment_Transaction_ID");
                Integer Transaction_Comment_ID = resultSet.getInt("Transaction_Comment_Transaction_Comment_ID");
                String Content = resultSet.getString("Transaction_Comment_Content");
                Date Post_Date = resultSet.getDate("Transaction_Comment_Post_Date");

                Transaction_Comment _transaction_comment = new Transaction_Comment( User_ID, Transaction_ID, Transaction_Comment_ID, Content, Post_Date);
                result.add(_transaction_comment);
              }
            }
          }
        }
      } catch (SQLException e) {
        throw new RuntimeException("Could not retrieve Transaction_Comments. Try again later");
      }
      return result;
    }

  @Override
  public int update(Transaction_Comment oldTransaction_Comment, Transaction_Comment newTransaction_Comment) throws SQLException {
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_Transaction_Comment(? ,? ,? ,?,?,?,?)}"))
        {
          statement.setInt(1,oldTransaction_Comment.getUser_ID());
          statement.setInt(2,oldTransaction_Comment.getTransaction_ID());
          statement.setInt(3,oldTransaction_Comment.getTransaction_Comment_ID());
          statement.setString(4,oldTransaction_Comment.getContent());
          statement.setString(5,newTransaction_Comment.getContent());
          
         result=statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update Transaction_Comment . Try again later");
        }
      }
    }
    return result;
  }

  @Override
  public int deleteTransaction_Comment(Transaction_Comment _transaction_comment) throws SQLException {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_Delete_Transaction_Comment( ?,?,?)}")){
          statement.setInt(1,_transaction_comment.getUser_ID());
          statement.setInt(2,_transaction_comment.getTransaction_ID());
          statement.setInt(3,_transaction_comment.getTransaction_Comment_ID());
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Delete Transaction_Comment. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Delete Transaction_Comment. Try again later");
    }
    return rowsAffected;
  }
}
