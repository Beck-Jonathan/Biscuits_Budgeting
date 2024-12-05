package com.beck.beck_demos.personal_project.models;

import java.time.LocalDate;

public class Event {
  private Integer Event_ID;
  private Integer Facility_ID;
  private LocalDate Date;
  private Integer Type_ID;
  private boolean is_active;

  public Event(){}

  public Event(Integer Event_ID, Integer Facility_ID, LocalDate Date, Integer Type_ID, boolean is_active) {

    this.Event_ID = Event_ID;
    this.Facility_ID = Facility_ID;
    this.Date = Date;
    this.Type_ID = Type_ID;
    this.is_active = is_active;
  }
  public Integer getEvent_ID() {
    return Event_ID;
  }
  public void setEvent_ID(Integer Event_ID) {
    this.Event_ID = Event_ID;
  }
  public Integer getFacility_ID() {
    return Facility_ID;
  }
  public void setFacility_ID(Integer Facility_ID) {
    this.Facility_ID = Facility_ID;
  }
  public LocalDate getDate() {
    return Date;
  }
  public void setDate(LocalDate Date) {
    this.Date = Date;
  }
  public Integer getType_ID() {
    return Type_ID;
  }
  public void setType_ID(Integer Type_ID) {
    this.Type_ID = Type_ID;
  }
  public boolean getis_active() {
    return is_active;
  }
  public void setis_active(boolean is_active) {
    this.is_active = is_active;
  }

}

