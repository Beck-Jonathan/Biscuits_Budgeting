package com.beck.beck_demos.personal_project.models;

/// <summary>
///AUTHOR: Jonathan Beck
///<br />
///CREATED: 3/3/2024
///< br />
///An example class to show how code is expected to be written and documented.
///This is where a description of what your file is supposed to contain goes.
///e.g., "Class with helper methods for input validation.",
///Class that defines Privilege Objects.
///</summary>
///< remarks>
///UPDATER: updater_name
///< br />
/// UPDATED: yyyy-MM-dd
/// < br />
/// Update comments go here, include method or methods were changed or added
/// A new remark should be added for each update.
///</remarks>


public class Privilege {
  private Integer Privilege_ID;
  private String Name;
  private boolean is_active;

  public Privilege(){}

  public Privilege(Integer Privilege_ID, String Name, boolean is_active) {

    this.Privilege_ID = Privilege_ID;
    this.Name = Name;
    this.is_active = is_active;
  }
  public Integer getPrivilege_ID() {
    return Privilege_ID;
  }
  public void setPrivilege_ID(Integer Privilege_ID) {
    this.Privilege_ID = Privilege_ID;
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
  public boolean getis_active() {
    return is_active;
  }
  public void setis_active(boolean is_active) {
    this.is_active = is_active;
  }

}

