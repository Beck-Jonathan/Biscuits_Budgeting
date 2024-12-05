package com.beck.beck_demos.crrg.models;

/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */

public class Sponsor_Tag {
  private String Sponsor_ID;
  private Integer Picture_ID;
  private String Description;
  private boolean is_Primary;

  public Sponsor_Tag(){}

  public Sponsor_Tag(String Sponsor_ID, Integer Picture_ID, String Description, boolean is_Primary) {

    this.Sponsor_ID = Sponsor_ID;
    this.Picture_ID = Picture_ID;
    this.Description = Description;
    this.is_Primary = is_Primary;
  }

  public Sponsor_Tag(String Sponsor_ID, Integer Picture_ID) {

    this.Sponsor_ID = Sponsor_ID;
    this.Picture_ID = Picture_ID;
  }
  public String getSponsor_ID() {
    return Sponsor_ID;
  }
  public void setSponsor_ID(String Sponsor_ID) {
    Sponsor_ID = Sponsor_ID.replaceAll("[^A-Za-z0-9 - ]","");
    if(Sponsor_ID.length()<4){
      throw new IllegalArgumentException("Sponsor_ID is too short.");
    }
    if(Sponsor_ID.length()>100){
      throw new IllegalArgumentException("Sponsor_ID is too long.");
    }
    this.Sponsor_ID = Sponsor_ID;
  }
  public Integer getPicture_ID() {
    return Picture_ID;
  }
  public void setPicture_ID(Integer Picture_ID) {
    this.Picture_ID = Picture_ID;
  }
  public String getDescription() {
    return Description;
  }
  public void setDescription(String Description) {
    Description = Description.replaceAll("[^A-Za-z0-9 - ]","");
    if(Description.length()<4){
      throw new IllegalArgumentException("Description is too short.");
    }
    if(Description.length()>1027){
      throw new IllegalArgumentException("Description is too long.");
    }
    this.Description = Description;
  }
  public boolean getis_Primary() {
    return is_Primary;
  }
  public void setis_Primary(boolean is_Primary) {
    this.is_Primary = is_Primary;
  }

}

