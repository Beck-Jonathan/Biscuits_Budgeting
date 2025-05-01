package com.beck.beck_demos.budget_app.iData;



import com.beck.beck_demos.budget_app.models.Suggestion;

import java.sql.SQLException;
import java.util.List;

public interface iSuggestionDAO {
  /**
   * DAO Method to add Suggestion objects
   * @param _suggestion the Suggestion to be added
   * @return number of records added
   * @author Jonathan Beck
   */
  int add (Suggestion _suggestion) throws SQLException;
  /**
   * DAO Method to delete Suggestion objects
   * @param Suggestion_ID the Suggestion to be deleted
   * @return number of records deleted
   * @author Jonathan Beck
   */
  int deleteSuggestion( String Suggestion_ID) throws SQLException;

  /**
   * DAO Method to retrieve all Suggestion objects
   * @return List of Suggestion
   * @author Jonathan Beck
   */
  List<Suggestion> getAllSuggestion(int offset, int limit, String search, String User_ID) throws SQLException;
  /**
   * DAO Method to retrieve by Primary Key Suggestion objects
   * @return List of Suggestion
   * @author Jonathan Beck
   */
  Suggestion getSuggestionByPrimaryKey(Suggestion _suggestion) throws SQLException;
}
