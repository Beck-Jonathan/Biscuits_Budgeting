package com.beck.beck_demos.personal_project.models;

import java.time.LocalDate;

public class User_Event_Line {
  private Integer User_ID;
  private Integer Event_ID;
  private LocalDate Date_assgined;
  private boolean is_active;

  public User_Event_Line(){}

  public User_Event_Line(Integer User_ID, Integer Event_ID, LocalDate Date_assgined, boolean is_active) {

    this.User_ID = User_ID;
    this.Event_ID = Event_ID;
    this.Date_assgined = Date_assgined;
    this.is_active = is_active;
  }
  public Integer getUser_ID() {
    return User_ID;
  }
  public void setUser_ID(Integer User_ID) {
    this.User_ID = User_ID;
  }
  public Integer getEvent_ID() {
    return Event_ID;
  }
  public void setEvent_ID(Integer Event_ID) {
    this.Event_ID = Event_ID;
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

