package com.beck.beck_demos.garden.model;


public class Garden {
  private Integer Garden_id;
  private String Garden_Name;
  private boolean is_active;

  public Garden(){}

  public Garden(Integer Garden_id, String Garden_Name, boolean is_active) {

    this.Garden_id = Garden_id;
    this.Garden_Name = Garden_Name;
    this.is_active = is_active;
  }
  public Integer getGarden_id() {
    return Garden_id;
  }
  public void setGarden_id(Integer Garden_id) {
    this.Garden_id = Garden_id;
  }
  public String getGarden_Name() {
    return Garden_Name;
  }
  public void setGarden_Name(String Garden_Name) {
    Garden_Name = Garden_Name.replaceAll("[^A-Za-z0-9 - ]","");
    if(Garden_Name.length()<4){
      throw new IllegalArgumentException("Garden_Name is too short.");
    }
    if(Garden_Name.length()>100){
      throw new IllegalArgumentException("Garden_Name is too long.");
    }
    this.Garden_Name = Garden_Name;
  }
  public boolean getis_active() {
    return is_active;
  }
  public void setis_active(boolean is_active) {
    this.is_active = is_active;
  }

}


