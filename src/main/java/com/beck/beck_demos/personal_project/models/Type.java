package com.beck.beck_demos.personal_project.models;

public class Type {
  private Integer Type_ID;
  private String Name;
  private boolean is_active;

  public Type(){}

  public Type(Integer Type_ID, String Name, boolean is_active) {

    this.Type_ID = Type_ID;
    this.Name = Name;
    this.is_active = is_active;
  }
  public Integer getType_ID() {
    return Type_ID;
  }
  public void setType_ID(Integer Type_ID) {
    this.Type_ID = Type_ID;
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

