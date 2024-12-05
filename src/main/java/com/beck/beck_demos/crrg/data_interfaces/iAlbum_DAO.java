package com.beck.beck_demos.crrg.data_interfaces;

import com.beck.beck_demos.crrg.models.Album;
import com.beck.beck_demos.crrg.models.Album_VM;


import java.sql.SQLException;
import java.util.List;

/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */
public interface iAlbum_DAO{
  /**
   * DAO Method to add Album objects
   * @param _album the Album to be added
   * @return number of records added
   * @author Jonathan Beck
   */
  int add (Album _album) throws SQLException;

  /**
   * DAO Method to update Album objects
   * @param oldAlbum the Album to be updated
   * @param newAlbum the updated version of the Album
   * @return number of records updated
   * @author Jonathan Beck
   */
  int update(Album oldAlbum, Album newAlbum) throws SQLException;

  /**
   * DAO Method to retreive all Album objects
   * @return List of Album
   * @author Jonathan Beck
   */
  List<Album_VM> getAllAlbum(int limit, int offset) throws SQLException;

  Album_VM getAlbumByPrimaryKey(Album _album) throws SQLException;

  /**
   * DAO Method to select distinct Album for dropdowns
   * @return list of string
   * @author Jonathan Beck
   */
  List<Album> getDistinctAlbumForDropdown() throws SQLException;

  int changeActivation(int albumID, int status) throws SQLException;

}

