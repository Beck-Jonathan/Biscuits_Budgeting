package com.beck.beck_demos.crrg.data_interfaces;

import com.beck.beck_demos.crrg.models.Picture;
import com.beck.beck_demos.crrg.models.Picture_VM;

import java.sql.SQLException;
import java.util.List;

public interface iPicture_DAO {
  /**
   * DAO Method to add Picture objects
   * @param _picture the Picture to be added
   * @return number of records added
   * @author Jonathan Beck
   */
  int add (Picture _picture) throws SQLException;



  /**
   * DAO Method to update Picture objects
   * @param oldPicture the Picture to be updated
   * @param newPicture the updated version of the Picture
   * @return number of records updated
   * @author Jonathan Beck
   */
  int update(Picture oldPicture, Picture newPicture) throws SQLException;

  /**
   * DAO Method to retreive all Picture objects
   * @return List of Picture
   * @author Jonathan Beck
   */
  List<Picture_VM> getAllPicture(int limit, int offset, int Album_ID, int  Contributor_ID) throws SQLException;

  /**
   * DAO Method to retreive by Foreign Key Picture objects
   * @return List of Picture
   * @author Jonathan Beck
   */
  Picture_VM getPictureByPrimaryKey(Picture _picture) throws SQLException;

  int changeActivation(int ObjectID, int mode) throws SQLException;
  int changeApproval(int ObjectID, int mode) throws SQLException;

  /**
   * DAO Method to retreive by Album Picture objects
   * @return List of Picture
   * @author Jonathan Beck
   */
  public List<Picture> getActivePicturebyAlbum(Integer Album_ID) throws SQLException;

public int getPictureCount() throws SQLException;
}
