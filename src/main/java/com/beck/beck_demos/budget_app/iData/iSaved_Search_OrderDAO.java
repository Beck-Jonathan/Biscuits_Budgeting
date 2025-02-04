package com.beck.beck_demos.budget_app.iData;

import com.beck.beck_demos.budget_app.models.Saved_Search_Order;

import java.sql.SQLException;
import java.util.List;

public interface iSaved_Search_OrderDAO {
  /**
   * DAO Method to retreive by Foreign Key Saved_Search_Order objects
   * @return List of Saved_Search_Order
   * @author Jonathan Beck
   */
  public List<Saved_Search_Order> getSaved_Search_OrderbyUser(Integer User_ID, int pagesize, int offset) throws SQLException;
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
  Saved_Search_Order getSaved_Search_OrderByPrimaryKey(Saved_Search_Order _saved_search_order) throws SQLException;
  /**
   * DAO Method to update Saved_Search_Order objects
   * @param oldSaved_Search_Order the Saved_Search_Order to be updated
   * @param newSaved_Search_Order the updated version of the Saved_Search_Order
   * @return number of records updated
   * @author Jonathan Beck
   */
  int update(Saved_Search_Order oldSaved_Search_Order, Saved_Search_Order newSaved_Search_Order) throws SQLException;
}
