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
}
