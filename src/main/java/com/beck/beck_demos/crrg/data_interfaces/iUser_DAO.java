package com.beck.beck_demos.crrg.data_interfaces;

import com.beck.beck_demos.crrg.models.*;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public interface iUser_DAO {

     int add(User _user) throws SQLException;

  List<String> getDistinctRoleForDropdown();

  /**
   * DAO Method to retreive all User objects
   * @return List of User
   * @author Jonathan Beck
   */
  List<User> getAllUser(int limit, int offset,String Role_ID) throws SQLException;


     //int getUserID(String email) throws SQLException;

     User getUserByUserID(User _user) throws SQLException;
     //List<User> getAllUser() throws SQLException ;

      //int activate(int _userID) throws SQLException;

  String getPassword(String username) throws SQLException;

     //int getUserIDByUserName(String username) throws SQLException;


     boolean usernameFree(String username) throws SQLException;
     boolean emailFree(String email) throws SQLException;



     //boolean deleteUser(int userID) throws SQLException;
     boolean updatePassword(User user) throws SQLException;

  int changeRole(String userID, String Role) throws SQLException;

  /**
   * * DAO Method to update User objects
   * * @param user the User to be updated
   * * @return number of records updated
   * * @author Jonathan Beck
   *  */
  int updateLastLoggedIn(User user) throws SQLException;

  /**
   * DAO Method to update User objects
   * @param oldUser the User to be updated
   * @param newUser the updated version of the User
   * @return number of records updated
   * @author Jonathan Beck
   */

   int update(User oldUser, User newUser)throws SQLException;




}
