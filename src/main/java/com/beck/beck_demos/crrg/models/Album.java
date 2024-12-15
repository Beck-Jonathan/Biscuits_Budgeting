package com.beck.beck_demos.crrg.models;
/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */

public class Album {
  private Integer Album_ID;
  private String Album_Name;
  private boolean Is_Active;

  public Album(){}

  public Album(Integer Album_ID, String Album_Name) {

    this.Album_ID = Album_ID;
    this.Album_Name = Album_Name;
  }

  public Album(Integer Album_ID, String Album_Name, boolean Is_Active) {

    this.Album_ID = Album_ID;
    this.Album_Name = Album_Name;
    this.Is_Active = Is_Active;
  }
  public Integer getAlbum_ID() {
    return Album_ID;
  }
  public void setAlbum_ID(Integer Album_ID) {
    if (Album_ID<0||Album_ID>10000){
     throw new IllegalArgumentException();
    }
    this.Album_ID = Album_ID;
  }
  public String getAlbum_Name() {
    return Album_Name;
  }
  public void setAlbum_Name(String Album_Name) {
    Album_Name = Album_Name.replaceAll("[^A-Za-z0-9 - ]","");
    if(Album_Name.length()<4){
      throw new IllegalArgumentException("Album_Name is too short.");
    }
    if(Album_Name.length()>100){
      throw new IllegalArgumentException("Album_Name is too long.");
    }
    this.Album_Name = Album_Name;
  }
  public boolean getIs_Active() {
    return Is_Active;
  }
  public void setIs_Active(boolean Is_Active) {
    this.Is_Active = Is_Active;
  }

}

