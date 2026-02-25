package com.beck.beck_demos.budget_app.iData;

import com.beck.beck_demos.budget_app.models.Receipt;

import java.sql.SQLException;
import java.util.List;

public interface iReceiptDAO {
  /**
   * DAO Method to add Receipt objects
   * @param _receipt the Receipt to be added
   * @return number of records added
   * @author Jonathan Beck
   */
  int add (Receipt _receipt) throws SQLException;
  /**
   * DAO Method to retrieve by Foreign Key Receipt objects
   * @return List of Receipt
   * @author Jonathan Beck
   */
  Receipt getReceiptByPrimaryKey(Receipt _receipt) throws SQLException;
  /**
   * DAO Method to retrieve by Foreign Key Receipt objects
   * @return List of Receipt
   * @author Jonathan Beck
   */
  public List<Receipt> getReceiptbyTransaction(String Transaction_ID) throws SQLException;
  /**
   * DAO Method to update Receipt objects
   * @param oldReceipt the Receipt to be updated
   * @param newReceipt the updated version of the Receipt
   * @return number of records updated
   * @author Jonathan Beck
   */
  int update(Receipt oldReceipt, Receipt newReceipt) throws SQLException;
  /**
   * DAO Method to retrieve all Receipt objects
   * @return List of Receipt
   * @author Jonathan Beck
   */
  List<Receipt> getAllReceipt(int offset, int limit, String search, String Transaction_ID, String user_id) throws SQLException;
  /**
   * DAO Method to delete Receipt objects
   * @param Receipt_ID the Receipt to be deleted
   * @return number of records deleted
   * @author Jonathan Beck
   */
  int deleteReceipt( String Receipt_ID) throws SQLException;

  int getReceiptCount(String searchTerm, String transactionId, String user_id);
}
