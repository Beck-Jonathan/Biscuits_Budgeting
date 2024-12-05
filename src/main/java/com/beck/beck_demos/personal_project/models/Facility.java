package com.beck.beck_demos.personal_project.models;/// <summary>
///AUTHOR: Jonathan Beck
///<br />
///CREATED: 23/2/2024
///< br />
///An example class to show how code is expected to be written and documented.
///This is where a description of what your file is supposed to contain goes.
///e.g., "Class with helper methods for input validation.",
///Class that defines Facility Objects.
///</summary>
///< remarks>
///UPDATER: updater_name
///< br />
/// UPDATED: yyyy-MM-dd
/// < br />
/// Update comments go here, include method or methods were changed or added
/// A new remark should be added for each update.
///</remarks>

public class Facility {
  private Integer Facility_ID;
  private String Name;
  private String Addresss;
  private String City;
  private String State;
  private String Zip;
  private boolean is_active;

  public Facility(){}

  public Facility(Integer Facility_ID, String Name, String Addresss, String City, String State, String Zip, boolean is_active) {

    this.Facility_ID = Facility_ID;
    this.Name = Name;
    this.Addresss = Addresss;
    this.City = City;
    this.State = State;
    this.Zip = Zip;
    this.is_active = is_active;
  }
  public Integer getFacility_ID() {
    return Facility_ID;
  }
  public void setFacility_ID(Integer Facility_ID) {
    this.Facility_ID = Facility_ID;
  }
  public String getName() {
    return Name;
  }
  public void setName(String Name) {
    Name = Name.replaceAll("[^A-Za-z0-9 - ]","");
    if(Name.length()<4){
      throw new IllegalArgumentException("Name is too short.");
    }
    if(Name.length()>100){
      throw new IllegalArgumentException("Name is too long.");
    }
    this.Name = Name;
  }
  public String getAddresss() {
    return Addresss;
  }
  public void setAddresss(String Addresss) {
    Addresss = Addresss.replaceAll("[^A-Za-z0-9 - ]","");
    if(Addresss.length()<4){
      throw new IllegalArgumentException("Addresss is too short.");
    }
    if(Addresss.length()>100){
      throw new IllegalArgumentException("Addresss is too long.");
    }
    this.Addresss = Addresss;
  }
  public String getCity() {
    return City;
  }
  public void setCity(String City) {
    City = City.replaceAll("[^A-Za-z0-9 - ]","");
    if(City.length()<4){
      throw new IllegalArgumentException("City is too short.");
    }
    if(City.length()>100){
      throw new IllegalArgumentException("City is too long.");
    }
    this.City = City;
  }
  public String getState() {
    return State;
  }
  public void setState(String State) {
    State = State.replaceAll("[^A-Za-z0-9 - ]","");
    if(State.length()<4){
      throw new IllegalArgumentException("State is too short.");
    }
    if(State.length()>100){
      throw new IllegalArgumentException("State is too long.");
    }
    this.State = State;
  }
  public String getZip() {
    return Zip;
  }
  public void setZip(String Zip) {
    Zip = Zip.replaceAll("[^A-Za-z0-9 - ]","");
    if(Zip.length()<4){
      throw new IllegalArgumentException("Zip is too short.");
    }
    if(Zip.length()>5){
      throw new IllegalArgumentException("Zip is too long.");
    }
    this.Zip = Zip;
  }
  public boolean getis_active() {
    return is_active;
  }
  public void setis_active(boolean is_active) {
    this.is_active = is_active;
  }

}