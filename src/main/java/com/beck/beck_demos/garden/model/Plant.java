package com.beck.beck_demos.garden.model;

public class Plant {
  private Integer Plant_ID;
  private String Plant_Name;
  private String Garden_ID;
  private String Plant_depth;
  private String Plant_Direction;
  private Integer Germination_Time;
  private boolean is_active;

  public Plant(){}

  public Plant(Integer Plant_ID, String Plant_Name, String Garden_ID, String Plant_depth, String Plant_Direction, Integer Germination_Time, boolean is_active) {

    this.Plant_ID = Plant_ID;
    this.Plant_Name = Plant_Name;
    this.Garden_ID = Garden_ID;
    this.Plant_depth = Plant_depth;
    this.Plant_Direction = Plant_Direction;
    this.Germination_Time = Germination_Time;
    this.is_active = is_active;
  }
  public Integer getPlant_ID() {
    return Plant_ID;
  }
  public void setPlant_ID(Integer Plant_ID) {
    this.Plant_ID = Plant_ID;
  }
  public String getPlant_Name() {
    return Plant_Name;
  }
  public void setPlant_Name(String Plant_Name) {
    Plant_Name = Plant_Name.replaceAll("[^A-Za-z0-9 - ]","");
    if(Plant_Name.length()<4){
      throw new IllegalArgumentException("Plant_Name is too short.");
    }
    if(Plant_Name.length()>100){
      throw new IllegalArgumentException("Plant_Name is too long.");
    }
    this.Plant_Name = Plant_Name;
  }
  public String getGarden_ID() {
    return Garden_ID;
  }
  public void setGarden_ID(String Garden_ID) {
    Garden_ID = Garden_ID.replaceAll("[^A-Za-z0-9 - ]","");
    if(Garden_ID.isEmpty()){
      throw new IllegalArgumentException("Garden_ID is too short.");
    }
    if(Garden_ID.length()>100){
      throw new IllegalArgumentException("Garden_ID is too long.");
    }
    this.Garden_ID = Garden_ID;
  }
  public String getPlant_depth() {
    return Plant_depth;
  }
  public void setPlant_depth(String Plant_depth) {
    Plant_depth = Plant_depth.replaceAll("[^A-Za-z0-9 - ]","");
    if(Plant_depth.length()<4){
      throw new IllegalArgumentException("Plant_depth is too short.");
    }
    if(Plant_depth.length()>100){
      throw new IllegalArgumentException("Plant_depth is too long.");
    }
    this.Plant_depth = Plant_depth;
  }
  public String getPlant_Direction() {
    return Plant_Direction;
  }
  public void setPlant_Direction(String Plant_Direction) {
    Plant_Direction = Plant_Direction.replaceAll("[^A-Za-z0-9 - ]","");
    if(Plant_Direction.length()<4){
      throw new IllegalArgumentException("Plant_Direction is too short.");
    }
    if(Plant_Direction.length()>255){
      throw new IllegalArgumentException("Plant_Direction is too long.");
    }
    this.Plant_Direction = Plant_Direction;
  }
  public Integer getGermination_Time() {
    return Germination_Time;
  }
  public void setGermination_Time(Integer Germination_Time) {
    this.Germination_Time = Germination_Time;
  }
  public boolean getis_active() {
    return is_active;
  }
  public void setis_active(boolean is_active) {
    this.is_active = is_active;
  }

}

