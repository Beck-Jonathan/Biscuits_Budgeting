package com.beck.beck_demos.personal_project.models;

import java.time.LocalDate;

public class User_Team_Line {
  private Integer User_ID;
  private Integer Team_ID;
  private LocalDate Date_assgined;
  private boolean is_active;

  public User_Team_Line(){}

  public User_Team_Line(Integer User_ID, Integer Team_ID, LocalDate Date_assgined, boolean is_active) {

    this.User_ID = User_ID;
    this.Team_ID = Team_ID;
    this.Date_assgined = Date_assgined;
    this.is_active = is_active;
  }
  public Integer getUser_ID() {
    return User_ID;
  }
  public void setUser_ID(Integer User_ID) {
    this.User_ID = User_ID;
  }
  public Integer getTeam_ID() {
    return Team_ID;
  }
  public void setTeam_ID(Integer Team_ID) {
    this.Team_ID = Team_ID;
  }
  public LocalDate getDate_assgined() {
    return Date_assgined;
  }
  public void setDate_assgined(LocalDate Date_assgined) {
    this.Date_assgined = Date_assgined;
  }
  public boolean getis_active() {
    return is_active;
  }
  public void setis_active(boolean is_active) {
    this.is_active = is_active;
  }

}
