package com.beck.beck_demos.budget_app.iData;

import com.beck.beck_demos.budget_app.models.Saved_Search_Order;
import com.beck.beck_demos.budget_app.models.Saved_Search_Order_Line;
import com.beck.beck_demos.budget_app.models.Saved_Search_Order_VM;

import java.sql.SQLException;
import java.util.List;

public interface iSaved_Search_OrderDAO {
  /**
   * DAO Method to retreive by Foreign Key Saved_Search_Order objects
   * @return List of Saved_Search_Order
   * @author Jonathan Beck
   */
  public List<Saved_Search_Order_VM> getSaved_Search_OrderbyUser(String User_ID, int pagesize, int offset) throws SQLException;
  /**
   * DAO Method to add Saved_Search_Order objects
   * @param _saved_search_order the Saved_Search_Order to be added
   * @return number of records added
   * @author Jonathan Beck
   */
  int add (Saved_Search_Order _saved_search_order) throws SQLException;
  /**
   * DAO Method to retreive by Primary Key Saved_Search_Order objects
   * @return List of Saved_Search_Order
   * @author Jonathan Beck
   */
  Saved_Search_Order_VM getSaved_Search_OrderByPrimaryKey(Saved_Search_Order _saved_search_order) throws SQLException;
  /**
   * DAO Method to update Saved_Search_Order objects
   * @param oldSaved_Search_Order the Saved_Search_Order to be updated
   * @param newSaved_Search_Order the updated version of the Saved_Search_Order
   * @return number of records updated
   * @author Jonathan Beck
   */
  int update(Saved_Search_Order oldSaved_Search_Order, Saved_Search_Order newSaved_Search_Order) throws SQLException;
  /**
   * DAO Method to delete Saved_Search_Order objects
   * @param Saved_Search_Order_ID the Saved_Search_Order to be deleted
   * @return number of records deleted
   * @author Jonathan Beck
   */
  int delete(int Saved_Search_Order_ID, String user_id) throws SQLException;

  /**
   * DAO Method to add line items to  Saved_Search_Order objects
   * @param line the Saved_Search_Order_Line to be deleted
   * @return number of records added
   * @author Jonathan Beck
   */
  int addLine(Saved_Search_Order_Line line) throws SQLException;

  /**
   * DAO Method to update line items attached to  Saved_Search_Order objects
   * @param oldLine the Saved_Search_Order_Line to be updated
   * @param newLine the Saved_Search_Order_Line with the new values
   * @return number of records updated
   * @author Jonathan Beck
   */
  int updateLine(Saved_Search_Order_Line oldLine, Saved_Search_Order_Line newLine) throws SQLException;
}
