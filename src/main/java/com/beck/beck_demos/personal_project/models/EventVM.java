package com.beck.beck_demos.personal_project.models;

import java.time.LocalDate;

public class EventVM extends Event{

   Facility facility;
   Type type;

  public EventVM(Integer Event_ID, Integer Facility_ID, LocalDate Date, Integer Type_ID, boolean is_active, Facility facility, Type type) {
    super(Event_ID, Facility_ID, Date, Type_ID, is_active);
    this.facility = facility;
    this.type = type;
  }

  public Facility getFacility() {
    return facility;
  }

  public void setFacility(Facility facility) {
    this.facility = facility;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }
}
