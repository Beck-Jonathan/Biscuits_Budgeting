package com.beck.beck_demos.personal_project.models;/// <summary>
///AUTHOR: Jonathan Beck
///<br />
///CREATED: 4/5/2024
///< br />
///An example class to show how code is expected to be written and documented.
///This is where a description of what your file is supposed to contain goes.
///e.g., "Class with helper methods for input validation.",
///Class that defines Team_Event_Line Objects.
///</summary>
///< remarks>
///UPDATER: updater_name
///< br />
/// UPDATED: yyyy-MM-dd
/// < br />
/// Update comments go here, include method or methods were changed or added
/// A new remark should be added for each update.
///</remarks>

import java.util.Date;

public class TeamEventLine {
  private Integer Team_ID;
  private Integer Event_ID;
  private Date Date_assgined;
  private boolean is_active;

  public TeamEventLine(){}

  public TeamEventLine(Integer Team_ID, Integer Event_ID) {

    this.Team_ID = Team_ID;
    this.Event_ID = Event_ID;
    this.Date_assgined = null;
    this.is_active = true;
  }
  public Integer getTeam_ID() {
    return Team_ID;
  }
  public void setTeam_ID(Integer Team_ID) {
    this.Team_ID = Team_ID;
  }
  public Integer getEvent_ID() {
    return Event_ID;
  }
  public void setEvent_ID(Integer Event_ID) {
    this.Event_ID = Event_ID;
  }
  public Date getDate_assgined() {
    return Date_assgined;
  }
  public void setDate_assgined(Date Date_assgined) {
    this.Date_assgined = Date_assgined;
  }
  public boolean getis_active() {
    return is_active;
  }
  public void setis_active(boolean is_active) {
    this.is_active = is_active;
  }

}

