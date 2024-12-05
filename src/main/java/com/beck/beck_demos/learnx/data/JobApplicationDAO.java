package com.beck.beck_demos.learnx.data;

import com.beck.beck_demos.learnx.Models.JobApplication;

import java.sql.*;
import java.time.Instant;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import static com.beck.beck_demos.learnx.data.Database.getConnection;
public class JobApplicationDAO {

  public static int add(JobApplication _job_application) {
    int numRowsAffected = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Job_Application( ?, ?, ?, ?, ?, ?)}")) {
          statement.setInt(1, _job_application.getjobId());
          statement.setString(2, _job_application.getfirstName());
          statement.setString(3, _job_application.getlastName());
          statement.setString(4, _job_application.getemail());
          statement.setDouble(5, _job_application.getdesiredSalary());
          statement.setTimestamp(6, Timestamp.from(_job_application.getearliestStartDate().toInstant()));

          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Job Application. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Job Application. Try again later");
    }
    return numRowsAffected;
  }

  public static List<JobApplication> getAllJob_Application() {
    List<JobApplication> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_all_Job_Application()}")) {try(ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {Integer applicationId = resultSet.getInt("Job_Application_applicationId");
            Integer jobId = resultSet.getInt("Job_Application_jobId");
            String firstName = resultSet.getString("Job_Application_firstName");
            String lastName = resultSet.getString("Job_Application_lastName");
            String email = resultSet.getString("Job_Application_email");
            double desiredSalary = resultSet.getDouble("Job_Application_desiredSalary");
            Date earliestStartDate=null;
            if(resultSet.getDate("Job_Application_earliestStartDate")!=null){
              earliestStartDate = Date.valueOf(resultSet.getDate("Job_Application_earliestStartDate").toLocalDate());
            }

            Instant dateSubmitted =  resultSet.getTimestamp("Job_Application_dateSubmitted").toInstant();
            String status = resultSet.getString("Job_Application_status");
            JobApplication _job_application = new JobApplication( applicationId, jobId, firstName, lastName, email, desiredSalary, earliestStartDate, dateSubmitted, status);
            result.add(_job_application);
          }
        }
        }
      }
    } catch (Exception e) {
      throw new RuntimeException("Could not retrieve Job_Applications. Try again later");
    }
    return result;}
}