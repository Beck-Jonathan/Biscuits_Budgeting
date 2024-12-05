package com.beck.beck_demos.crrg.data_interfaces;

import com.beck.beck_demos.crrg.models.Contributor;
import com.beck.beck_demos.crrg.models.Contributor_VM;

import java.sql.SQLException;
import java.util.List;

public interface iContributor_DAO {
  /**
   * DAO Method to add Contributor objects
   * @param _contributor the Contributor to be added
   * @return number of records added
   * @author Jonathan Beck
   */
  int add (Contributor _contributor) throws SQLException;
  /**
   * DAO Method to retreive all Contributor objects
   * @return List of Contributor
   * @author Jonathan Beck
   */
  List<Contributor_VM> getAllContributor(int limit, int offset) throws SQLException;


  /**
   * DAO Method to select distinct Contributor for dropdowns
   * @return list of Contributor
   * @author Jonathan Beck
   */
  List<Contributor> getDistinctContributorForDropdown() throws SQLException;

  /**
   * DAO Method to retreive by Foreign Key Contributor objects
   * @return List of Contributor
   * @author Jonathan Beck
   */
  Contributor_VM getContributorByPrimaryKey(Contributor _contributor) throws SQLException;
  /**
   * DAO Method to update Contributor objects
   * @param oldContributor the Contributor to be updated
   * @param newContributor the updated version of the Contributor
   * @return number of records updated
   * @author Jonathan Beck
   */
  int update(Contributor oldContributor, Contributor newContributor) throws SQLException;

}
