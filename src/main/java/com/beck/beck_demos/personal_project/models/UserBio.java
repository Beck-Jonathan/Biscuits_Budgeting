package com.beck.beck_demos.personal_project.models;

/// <summary>
///AUTHOR: Jonathan Beck
///<br />
///CREATED: 5/5/2024

import java.util.Date;

public class UserBio {
  private Integer User_ID;
  private String Position;
  private String Bio;
  private Date Began_Skating;

  public UserBio(){}

  public UserBio(Integer User_ID, String Position, String Bio, Date Began_Skating) {

    this.User_ID = User_ID;
    this.Position = Position;
    this.Bio = Bio;
    this.Began_Skating = Began_Skating;
  }
  public Integer getUser_ID() {
    return User_ID;
  }
  public void setUser_ID(Integer User_ID) {
    this.User_ID = User_ID;
  }
  public String getPosition() {
    return Position;
  }
  public void setPostion(String Position) {
    Position = Position.replaceAll("[^A-Za-z0-9 - ]","");
    if(Position.length()<4){
      throw new IllegalArgumentException("Postion is too short.");
    }
    if(Position.length()>25){
      throw new IllegalArgumentException("Postion is too long.");
    }
    this.Position = Position;
  }
  public String getBio() {
    return Bio;
  }
  public void setBio(String Bio) {
    Bio = Bio.replaceAll("[^A-Za-z0-9 - ]","");
    if(Bio.length()<4){
      throw new IllegalArgumentException("Bio is too short.");
    }
    if(Bio.length()>2000){
      throw new IllegalArgumentException("Bio is too long.");
    }
    this.Bio = Bio;
  }
  public Date getBegan_Skating() {
    return Began_Skating;
  }
  public void setBegan_Skating(Date Began_Skating) {
    this.Began_Skating = Began_Skating;
  }

}

