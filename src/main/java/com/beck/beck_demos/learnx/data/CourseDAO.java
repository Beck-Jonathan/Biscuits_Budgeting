package com.beck.beck_demos.learnx.data;

import com.beck.beck_demos.learnx.Models.Course;
import com.beck.beck_demos.learnx.Models.CourseCategory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.*;

public class CourseDAO extends Database {

  public static List<Course> get(int limit, int offset, String categories, String skillLevel) {
    List<Course> courses = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_get_courses(?, ?, ?, ?)}")) {
          statement.setInt(1, limit);
          statement.setInt(2, offset);
          statement.setString(3, categories);
          statement.setString(4, skillLevel);
          try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              int id = resultSet.getInt("id");
              String name = resultSet.getString("name");
              String description = resultSet.getString("description");
              String level = resultSet.getString("level");
              String picture = resultSet.getString("picture");
              String teacherFirstName = resultSet.getString("first_name");
              String teacherLastName = resultSet.getString("last_name");
              int categoryId = resultSet.getInt("category_id");
              String categoryName = resultSet.getString("category_name");
              Course course = new Course(id, name, description, level, picture, teacherFirstName, teacherLastName, categoryId, categoryName);
              courses.add(course);
            }
          }
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("likely bad query", e);
    }
    return courses;
  }

  public static List<CourseCategory> getAllCategories() {
    List<CourseCategory> categories = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_get_all_course_categories()}")) {
          try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              int id = resultSet.getInt("id");
              String name = resultSet.getString("name");
              int numCourses = resultSet.getInt("num_courses");
              categories.add(new CourseCategory(id, name, numCourses));
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return categories;
  }

  public static int enroll(int userId, int courseId) {
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_add_enrollment(?, ?)}")) {
          statement.setInt(1, userId);
          statement.setInt(2, courseId);
          result = statement.executeUpdate();
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  public static TreeMap<Course, Instant> getCoursesEnrolled(int limit, int offset, int userId) {
    TreeMap<Course, Instant> enrollments = new TreeMap<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_get_courses_by_student(?, ?, ?)}")) {
          statement.setInt(1, limit);
          statement.setInt(2, offset);
          statement.setInt(3, userId);
          try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              int id = resultSet.getInt("id");
              String name = resultSet.getString("name");
              String description = resultSet.getString("description");
              String level = resultSet.getString("level");
              String picture = resultSet.getString("picture");
              String teacherFirstName = resultSet.getString("first_name");
              String teacherLastName = resultSet.getString("last_name");
              int categoryId = resultSet.getInt("category_id");
              String categoryName = resultSet.getString("category_name");
              Instant enrollment_date = resultSet.getTimestamp("enrollment_date").toInstant();
              Course course = new Course(id, name, description, level, picture, teacherFirstName, teacherLastName, categoryId, categoryName);
              enrollments.put(course, enrollment_date);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return enrollments;
  }

  public static int deletecourse(int courseID) {
    int rowsAffected = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_Delete_course( ?)}")) {
          statement.setInt(1, courseID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Delete course. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Delete course. Try again later");
    }
    return rowsAffected;
  }

  public static int add(Course _course) {
    int numRowsAffected = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_course( ?, ?, ?, ?, ?, ?)}")) {
          statement.setString(1, _course.getName());
          statement.setString(2, _course.getDescription());
          statement.setString(3, _course.getLevel());
          statement.setString(4, _course.getPicture());
          statement.setInt(5, _course.getTeacherID());

          statement.setInt(6, _course.getCategoryId());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add course. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add course. Try again later");
    }
    return numRowsAffected;
  }

  public static List<CourseCategory> getAllCourse_Category() {
    List<CourseCategory> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_all_Course_Category()}")) {
          try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              Integer id = resultSet.getInt("Course_Category_id");
              String Name = resultSet.getString("Course_Category_Name");
              CourseCategory _course_category = new CourseCategory(id, Name);
              result.add(_course_category);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Course_Categorys. Try again later");
    }
    return result;
  }

  public static int update(Course oldcourse, Course newcourse) throws SQLException {
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_update_course(? ,?,?,?,?,?,?,?,?,?,?,?,?)}")) {
          statement.setInt(1, oldcourse.getId());
          statement.setString(2, oldcourse.getName());
          statement.setString(3, newcourse.getName());
          statement.setString(4, oldcourse.getDescription());
          statement.setString(5, newcourse.getDescription());
          statement.setString(6, oldcourse.getLevel());
          statement.setString(7, newcourse.getLevel());
          statement.setString(8, oldcourse.getPicture());
          statement.setString(9, newcourse.getPicture());
          statement.setInt(10, oldcourse.getTeacherID());
          statement.setInt(11, newcourse.getTeacherID());
          statement.setInt(12, oldcourse.getCategoryId());
          statement.setInt(13, newcourse.getCategoryId());
          result = statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update course . Try again later");
        }
      }
    }
    return result;
  }

  public static List<String> getLevels() {
    ArrayList<String> results = new ArrayList<String>();
    results.add("Beginner");
    results.add("Intermediate");
    results.add("Advanced");
    return results;
  }

  public static Course getcourseByPrimaryKey(int _course) throws SQLException {
    Course result = null;
    try (Connection connection = getConnection()) {
      try (CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_pk_course(?)}")) {
        statement.setInt(1, _course);

        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            Integer id = resultSet.getInt("course_id");
            String name = resultSet.getString("course_name");
            String description = resultSet.getString("course_description");
            String level = resultSet.getString("course_level");
            String picture = resultSet.getString("course_picture");
            Integer teacher_id = resultSet.getInt("course_teacher_id");
            Integer category_id = resultSet.getInt("course_category_id");
            result = new Course(id, name, description, level, picture, teacher_id, category_id);
          }
        }
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
      return result;
    }

  }
}