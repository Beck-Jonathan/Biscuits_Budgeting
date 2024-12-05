package com.beck.beck_demos.learnx.Models;

import org.jetbrains.annotations.NotNull;

public class Course implements Comparable{
  private int id;
  private String name;
  private String description;
  private String level;
  private String picture;
  private String teacherFirstName;
  private String teacherLastName;
  private int categoryId;
  private String categoryName;
  private int teacherID;







  private static final Double price = 1234.567;

  public Course() {
  }

  public Course(int id, String name, String description, String level, String picture, String teacherFirstName, String teacherLastName, int categoryId, String categoryName) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.level = level;
    this.picture = picture;
    this.teacherFirstName = teacherFirstName;
    this.teacherLastName = teacherLastName;
    this.categoryId = categoryId;
    this.categoryName = categoryName;
  }

  public Course(Integer id, String name, String description, String level, String picture, Integer teacherId, Integer categoryId) {
    this.id=id;
    this.name=name;
    this.description=description;
    this.level=level;
    this.picture=picture;
    this.teacherID=teacherId;
    this.categoryId=categoryId;
  }

  public  Double getPrice() {
    return price;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public String getTeacherFirstName() {
    return teacherFirstName;
  }

  public void setTeacherFirstName(String teacherFirstName) {
    this.teacherFirstName = teacherFirstName;
  }

  public String getTeacherLastName() {
    return teacherLastName;
  }

  public void setTeacherLastName(String teacherLastName) {
    this.teacherLastName = teacherLastName;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  @Override
  public int compareTo(@NotNull Object o) {
    Course other = (Course) o;
    return this.id-other.id;
  }

  public int getTeacherID() {
    return teacherID;
  }

  public void setTeacherID(int teacherID) {
    this.teacherID = teacherID;
  }
}