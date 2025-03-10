package com.beck.beck_demos.budget_app.iData;
import com.beck.beck_demos.budget_app.models.Transaction_Comment;

import java.sql.SQLException;
import java.util.List;

public interface iTransaction_CommentDAO {
  /**
   * DAO Method to add Transaction_Comment objects
   * @param _transaction_comment the Transaction_Comment to be added
   * @return number of records added
   * @author Jonathan Beck
   */
  int add (Transaction_Comment _transaction_comment) throws SQLException;

  /**
   * DAO Method to update Transaction_Comment objects
   * @param oldTransaction_Comment the Transaction_Comment to be updated
   * @param newTransaction_Comment the updated version of the Transaction_Comment
   * @return number of records updated
   * @author Jonathan Beck
   */
  int update(Transaction_Comment oldTransaction_Comment, Transaction_Comment newTransaction_Comment) throws SQLException;
  /**
   * DAO Method to delete Transaction_Comment objects
   * @param _transaction_comment the Transaction_Comment to be deleted
   * @return number of records deleted
   * @author Jonathan Beck
   */
  int deleteTransaction_Comment( Transaction_Comment _transaction_comment) throws SQLException;
}
