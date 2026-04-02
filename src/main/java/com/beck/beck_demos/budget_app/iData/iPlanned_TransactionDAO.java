package com.beck.beck_demos.budget_app.iData;

import com.beck.beck_demos.budget_app.models.Planned_Transaction;
import com.beck.beck_demos.budget_app.models.Planned_Transaction_VM;
import com.beck.beck_demos.budget_app.models.User;

import java.sql.SQLException;
import java.util.List;

public interface iPlanned_TransactionDAO {
  /**
   * DAO Method to add planned_transaction objects
   *
   * @param _planned_transaction the planned_transaction to be added
   * @return number of records added
   * @author Jonathan Beck
   */
  String add(Planned_Transaction _planned_transaction) throws SQLException;

  /**
   * DAO Method to update planned_transaction objects
   *
   * @param oldplanned_transaction the planned_transaction to be updated
   * @param newplanned_transaction the updated version of the planned_transaction
   * @return number of records updated
   * @author Jonathan Beck
   */
  int update(Planned_Transaction oldplanned_transaction, Planned_Transaction newplanned_transaction) throws SQLException;

  /**
   * DAO Method to retrieve all planned_transaction objects
   *
   * @return List of planned_transaction
   * @author Jonathan Beck
   */
  List<Planned_Transaction_VM> getAllplanned_transaction(int offset, int limit, String search, String user_ID, String subcategory_ID, String budget_ID) throws SQLException;

  /**
   * DAO Method to delete planned_transaction objects
   *
   * @param _planned_transaction the planned_transaction to be deleted
   * @return number of records deleted
   * @author Jonathan Beck
   */
  int deactivatePlanned_transaction(Planned_Transaction _planned_transaction) throws SQLException;

  /**
   * DAO Method to delete planned_transaction objects
   *
   * @param _planned_transaction the planned_transaction to be deleted
   * @return number of records deleted
   * @author Jonathan Beck
   */
  int deletePlanned_transaction(Planned_Transaction _planned_transaction) throws SQLException;

  /**
   * DAO Method to undelete planned_transaction objects
   *
   * @param _planned_transaction the planned_transaction to be undeleted
   * @return number of records undeleted
   * @author Jonathan Beck
   */
  int reactivatePlanned_transaction(Planned_Transaction _planned_transaction) throws SQLException;

  /**
   * DAO Method to deactivate all Planned_Transaction for a particular user
   *
   * @param user the planned_transaction to have all planned transitions deactivated
   * @return number of records undeleted
   * @author Jonathan Beck
   */
  int deactivateAllPlanned_Transaction(User user) throws SQLException;

  /**
   * DAO Method to reactivate all Planned_Transaction for a particular user
   *
   * @param user the planned_transaction to have all planned transitions reactivated
   * @return number of records undeleted
   * @author Jonathan Beck
   */
  int reactivateAllPlanned_Transaction(User user) throws SQLException;

}
