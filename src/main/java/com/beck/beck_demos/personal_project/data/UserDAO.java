package com.beck.beck_demos.personal_project.data;/// <summary>
///AUTHOR: Jonathan Beck
///<br />
///CREATED: 3/3/2024
///< br />
///An example class to show how code is expected to be written and documented.
///This is where a description of what your file is supposed to contain goes.
///e.g., "Class with helper methods for input validation.",
///Class that defines UserDAO Objects.
///</summary>
///< remarks>
///UPDATER: updater_name
///< br />
/// UPDATED: yyyy-MM-dd
/// < br />
/// Update comments go here, include method or methods were changed or added
/// A new remark should be added for each update.
///</remarks>

import com.beck.beck_demos.personal_project.models.*;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static com.beck.beck_demos.personal_project.data.Database.getConnection;
public class UserDAO {

  public static int add(User _user) {
    int numRowsAffected=0;try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_User( ?, ?, ?, ?, ?, ?)}")){
          statement.setString(1,_user.getUser_Name());
          String hashPassword = BCrypt.hashpw(String.valueOf(_user.getUser_PW()), BCrypt.gensalt(12));
          statement.setString(2,hashPassword);
          statement.setInt(3,_user.getStatus_ID());
          statement.setString(4,_user.getEmail());
          statement.setInt(5,_user.getLanguage_ID());
          statement.setInt(6,_user.getPrivilege_ID());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add User. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add User. Try again later");
    }
    return numRowsAffected;
  }

  public static int getUserID(String email) throws SQLException{
    int userId = 0;
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_user_id_by_email(?)}")) {
        statement.setString(1,email);

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){
             userId=resultSet.getInt(1);
          }
        }
       catch (SQLException ex ){

         throw new RuntimeException(ex);}
      }


    }
    return userId;
  }

  public static User getUserByPrimaryKey(User _user) throws SQLException{
    User result = null;
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_pk_User(?)}")) {
        statement.setInt(1, _user.getUser_ID());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){
            Integer User_ID = resultSet.getInt("User_ID");
            String User_Name = resultSet.getString("User_Name");
            char[] User_PW = resultSet.getString("User_PW").toCharArray();
            Integer Status_ID = resultSet.getInt("Status_ID");
            String Email = resultSet.getString("Email");
            Integer Language_ID = resultSet.getInt("Language_ID");
            Integer Privilege_ID = resultSet.getInt("Privilege_ID");
            result = new User( User_ID, User_Name, User_PW, Status_ID, Email, Language_ID, Privilege_ID);}
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }
  public static List<User> getAllUser() {
    List<User> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_all_User()}")) {try(ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {Integer User_ID = resultSet.getInt("User_ID");
            String User_Name = resultSet.getString("User_Name");
            char[] User_PW = resultSet.getString("User_PW").toCharArray();
            Integer Status_ID = resultSet.getInt("Status_ID");
            String Email = resultSet.getString("Email");
            Integer Language_ID = resultSet.getInt("Language_ID");
            Integer Privilege_ID = resultSet.getInt("Privilege_ID");
            User _user = new User( User_ID, User_Name, User_PW, Status_ID, Email, Language_ID, Privilege_ID);
            result.add(_user);
          }
        }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Users. Try again later");
    }
    return result;
  }

  public static int activate(int _userID) {
    int numRowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_Activate_User( ?)}")){
          statement.setInt(1,_userID);

          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not activate User. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not activate User. Try again later");
    }
    return numRowsAffected;
  }

  public static String get_pw(String username) {
    String pw="";
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_get_pw( ?)}")){
          statement.setString(1,username);



          try (ResultSet resultSet = statement.executeQuery()){
            if(resultSet.next()) {
              pw = resultSet.getString(1);
            }
          }

          if (pw.equals( "")) {
            throw new RuntimeException("Could not Login User. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Login User. Try again later");
    }
    return pw;
  }

  public static int getUserIDByUserName(String username) {
    int id=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_user_id_by_username( ?)}")){
          statement.setString(1,username);



          try (ResultSet resultSet = statement.executeQuery()){
            if(resultSet.next()) {
              id = resultSet.getInt(1);
            }

          }

          if (id==0) {
            throw new RuntimeException("Could not Login User. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Login User. Try again later");
    }
    return id;
  }

  public static int updatePriv(int userId, int privForDb) throws SQLException {
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_user_update_privilege(? ,?)}"))
        {
          statement.setInt(1,userId);
          statement.setInt(2,privForDb);

          result=statement.executeUpdate();

        } catch (SQLException e) {
          throw new RuntimeException("Could not update user . Try again later");
        }
      }
    }
    return result;
  }
  public static boolean usernameFree(String username) throws SQLException {
    boolean result = true;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_check_username_avail(? )}"))
        {
          statement.setString(1,username);

          try (ResultSet resultSet = statement.executeQuery()){
            if(resultSet.next()) {
              result = (resultSet.getInt(1)==0);
            }

          }

        } catch (SQLException e) {
          throw new RuntimeException("Could complete query. Try again later");
        }
      }
    }
    return result;
  }
  public static boolean emailFree(String email) throws SQLException {
    boolean result = true;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_check_email_avail(? )}"))
        {
          statement.setString(1,email);

          try (ResultSet resultSet = statement.executeQuery()){
            if(resultSet.next()) {
              result = (resultSet.getInt(1)==0);
            }

          }

        } catch (SQLException e) {
          throw new RuntimeException("Could complete query. Try again later");
        }
      }
    }
    return result;
  }

  public static List<TeamVM> selectTeamByUser(User user){


      List<TeamVM> result = new ArrayList<>();
      try (Connection connection = getConnection()) {
        if (connection != null) {
          try(CallableStatement statement = connection.prepareCall("{CALL sp_select_team_by_user(?)}"))
          {statement.setInt(1,user.getUser_ID());
            try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              Integer Team_ID = resultSet.getInt("Team_Team_ID");
              Integer League_ID = resultSet.getInt("Team_League_ID");
              String Name = resultSet.getString("Team_Name");
              String Team_Primary_Color = resultSet.getString("Team_Team_Primary_Color");
              String Team_Secondary_Color = resultSet.getString("Team_Team_Secondary_Color");
              String Team_Tertiary_Color = resultSet.getString("Team_Team_Tertiary_Color");
              String Team_City = resultSet.getString("Team_Team_City");
              String Team_State = resultSet.getString("Team_Team_State");
              String Logo = resultSet.getString("Team_Logo");
              boolean is_active = resultSet.getBoolean("Team_is_active");
              Integer League_League_ID = resultSet.getInt("League_League_ID");
              String League_League_Name = resultSet.getString("League_League_Name");
              String League_League_Level = resultSet.getString("League_League_Level");
              Double League_Monthly_Due = resultSet.getDouble("League_Monthly_Due");
              String League_Active_Days = resultSet.getString("League_Active_Days");
              String League_Description = resultSet.getString("League_Description");
              boolean League_is_active = resultSet.getBoolean("League_is_active");
              League _league = new League(League_League_ID,League_League_Name,League_League_Level,League_Monthly_Due,League_Active_Days,League_Description,League_is_active);
              TeamVM _team = new TeamVM( Team_ID, League_ID, Name, Team_Primary_Color, Team_Secondary_Color, Team_Tertiary_Color, Team_City, Team_State, Logo, is_active,_league);
              result.add(_team);
            }
          }
          }
        }
      } catch (SQLException e) {
        throw new RuntimeException("Could not retrieve Teams. Try again later");
      }
      return result;
  }

  public static List<EventVM> selectEventsByUser(User user){


    List<EventVM> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_select_event_by_user(?)}"))
        {statement.setInt(1,user.getUser_ID());
          try(ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {
            Integer Event_ID = resultSet.getInt("Event_Event_ID");
            Integer Facility_ID = resultSet.getInt("Event_Facility_ID");
            LocalDate Date = resultSet.getDate("Event_Date").toLocalDate();
            Integer Type_ID = resultSet.getInt("Event_Type_ID");
            boolean is_active = resultSet.getBoolean("Event_is_active");
            Integer Facility_Facility_ID = resultSet.getInt("Facility_Facility_ID");
            String Facility_Name = resultSet.getString("Facility_Name");
            String Facility_Addresss = resultSet.getString("Facility_Addresss");
            String Facility_City = resultSet.getString("Facility_City");
            String Facility_State = resultSet.getString("Facility_State");
            String Facility_Zip = resultSet.getString("Facility_Zip");
            boolean Facility_is_active = resultSet.getBoolean("Facility_is_active");
            Facility facility = new Facility(Facility_Facility_ID,Facility_Name,Facility_Addresss,Facility_City,Facility_State,Facility_Zip,Facility_is_active);
            Integer Type_Type_ID = resultSet.getInt("Type_Type_ID");
            String Type_Name = resultSet.getString("Type_Name");
            boolean Type_is_active = resultSet.getBoolean("Type_is_active");
            Type type = new Type(Type_Type_ID,Type_Name,Type_is_active);
            EventVM _event = new EventVM( Event_ID, Facility_ID, Date, Type_ID, is_active,facility,type);
            result.add(_event);
          }
        }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Events. Try again later");
    }
    return result;
  }

  public static List<TeamVM> selectUnAssignedTeamByUser(User user){


    List<TeamVM> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_select_unassgned_team_by_user(?)}"))
        {statement.setInt(1,user.getUser_ID());
          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              Integer Team_ID = resultSet.getInt("Team_Team_ID");
              Integer League_ID = resultSet.getInt("Team_League_ID");
              String Name = resultSet.getString("Team_Name");
              String Team_Primary_Color = resultSet.getString("Team_Team_Primary_Color");
              String Team_Secondary_Color = resultSet.getString("Team_Team_Secondary_Color");
              String Team_Tertiary_Color = resultSet.getString("Team_Team_Tertiary_Color");
              String Team_City = resultSet.getString("Team_Team_City");
              String Team_State = resultSet.getString("Team_Team_State");
              String Logo = resultSet.getString("Team_Logo");
              boolean is_active = resultSet.getBoolean("Team_is_active");
              Integer League_League_ID = resultSet.getInt("League_League_ID");
              String League_League_Name = resultSet.getString("League_League_Name");
              String League_League_Level = resultSet.getString("League_League_Level");
              Double League_Monthly_Due = resultSet.getDouble("League_Monthly_Due");
              String League_Active_Days = resultSet.getString("League_Active_Days");
              String League_Description = resultSet.getString("League_Description");
              boolean League_is_active = resultSet.getBoolean("League_is_active");
              League _league = new League(League_League_ID,League_League_Name,League_League_Level,League_Monthly_Due,League_Active_Days,League_Description,League_is_active);
              TeamVM _team = new TeamVM( Team_ID, League_ID, Name, Team_Primary_Color, Team_Secondary_Color, Team_Tertiary_Color, Team_City, Team_State, Logo, is_active,_league);
              result.add(_team);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Teams. Try again later");
    }
    return result;
  }

  public static boolean deleteUser(int userID) throws SQLException {
    boolean result = true;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_delete_user(? )}")) {
          statement.setInt(1, userID);

          try {
            int value = statement.executeUpdate();
            result = (value == 1);
          } catch (SQLException e) {
            throw new RuntimeException("Could delete user. Try again later");
          }
        }
      }
      return result;
    }
  }
    public static boolean resetPW(User user) throws SQLException {
      boolean result = true;
      try (Connection connection = getConnection()) {
        if (connection != null) {
          try (CallableStatement statement = connection.prepareCall("{CALL sp_update_password(?,? )}")) {
            statement.setString(1, user.getUser_Name());
            String hashPassword = BCrypt.hashpw(String.valueOf(user.getUser_PW()), BCrypt.gensalt(12));

            statement.setString(2,hashPassword);

            try {
              int value = statement.executeUpdate();
              result = (value == 1);
            } catch (SQLException e) {
              throw new RuntimeException("Could update password. Try again later");
            }
          }
        }
        return result;
      }

  }


}

