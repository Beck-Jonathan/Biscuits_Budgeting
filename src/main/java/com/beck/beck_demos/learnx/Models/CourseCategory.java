package com.beck.beck_demos.learnx.Models;

public class CourseCategory {
  private int id;
  private String name;
  private int numCourses;

  public CourseCategory() {
  }

  public CourseCategory(int id, String name, int numCourses) {
    this.id = id;
    this.name = name;
    this.numCourses = numCourses;
  }
  public CourseCategory(int id, String name) {
    this.id = id;
    this.name = name;

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getNumCourses() {
    return numCourses;
  }

  public void setNumCourses(int numCourses) {
    this.numCourses = numCourses;
  }
}