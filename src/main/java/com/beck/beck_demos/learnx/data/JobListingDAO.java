package com.beck.beck_demos.learnx.data;

import com.beck.beck_demos.learnx.Models.Department;
import com.beck.beck_demos.learnx.Models.JobListing;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.beck.beck_demos.learnx.data.Database.getConnection;

public class JobListingDAO {
  public static void main(String[] args) {
    getAll().forEach(System.out::println);
  }

  public static List<JobListing> getAll(){
    return getAll(100,0,"","");
  }
  public static List<JobListing> getAll(int limit, int offset,String _department,String Location) {
    List<JobListing> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_get_job_listings(?,?,?,?)}"))

        {
          statement.setInt(1,limit);
          statement.setInt(2,offset);
          statement.setString(3,_department);
          statement.setString(4,Location);

          try(ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {Integer job_id = resultSet.getInt("job_id");
            Integer department_id = resultSet.getInt("department_id");
            String department_name = resultSet.getString("department_name");
            Department department = new Department();
            department.setDepartment_id(department_id);
            department.setDepartment_name(department_name);
            Boolean featured = resultSet.getBoolean("featured");
            String position = resultSet.getString("position");
            Timestamp posted_at = resultSet.getTimestamp("posted_at");
            Instant posted_at2= posted_at.toInstant();

            //Instant posted_at = Instant.from(Instant.now());
            String contract = resultSet.getString("contract");
            String location = resultSet.getString("location");
            String job_description = resultSet.getString("job_description");
            JobListing _job_listing = new JobListing( job_id, department, featured, position, posted_at2, contract, location, job_description);
            result.add(_job_listing);
          }
        }
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve job_listings. Try again later");
    }
    return result;
  }

  public static JobListing getJob_ListingByPrimaryKey(Integer job_id) throws SQLException {
    JobListing result = null;
    try (Connection connection = getConnection()) {
      try (CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_pk_Job_Listing(?)}")) {
        statement.setString(1, job_id.toString());

        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            Integer Job_id = resultSet.getInt("Job_Listing_Job_id");
            Integer department_id = resultSet.getInt("Job_Listing_department_id");
            Department department = new Department();
            department.setDepartment_id(department_id);
            String department_name = resultSet.getString("department_name");
            department.setDepartment_name(department_name);
            boolean featured = resultSet.getBoolean("Job_Listing_featured");
            String position = resultSet.getString("Job_Listing_position");
            Instant posted_at = resultSet.getTimestamp("Job_Listing_posted_at").toInstant();
            String contract = resultSet.getString("Job_Listing_contract");
            String location = resultSet.getString("Job_Listing_location");
            String job_description = resultSet.getString("Job_Listing_job_description");
            result = new JobListing(job_id, department, featured, position, posted_at, contract, location, job_description);
          }
        }
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
      return result;
    }
  }

  public static int add(JobListing _job_listing) {
    int numRowsAffected=0;try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Job_Listing( ?, ?, ?, ?, ?, ?, ?)}")){
          statement.setInt(1,_job_listing.getDepartment().getDepartment_id());
          statement.setBoolean(2,_job_listing.isFeatured());
          statement.setString(3,_job_listing.getPosition());
          statement.setTimestamp(4, Timestamp.from(_job_listing.getPosted_at()));
          statement.setString(5,_job_listing.getContract());
          statement.setString(6,_job_listing.getLocation());
          statement.setString(7,_job_listing.getDescription());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Job_Listing. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Job_Listing. Try again later");
    }
    return numRowsAffected;
  }

  public static List<Department> getActivedepartment(){
    List<Department> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_get_deartments()}"))

        {


          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {

              Integer department_id = resultSet.getInt("department_id");
              String department_name = resultSet.getString("department_name");
              Department department = new Department();
              department.setDepartment_id(department_id);
              department.setDepartment_name(department_name);

              result.add(department);
            }
          }
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve departments. Try again later");
    }
    return result;



  }

}
