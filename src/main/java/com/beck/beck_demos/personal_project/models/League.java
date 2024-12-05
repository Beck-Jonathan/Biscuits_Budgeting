package com.beck.beck_demos.personal_project.models;

import java.util.Currency;

public class League {
    private Integer League_ID;
    private String League_Name;
    private String League_Level;
    private Double Monthly_Due;
    private String Active_Days;
    private String Description;
    private boolean is_active;

    public League(){}

    public League(Integer League_ID, String League_Name, String League_Level, Double Monthly_Due, String Active_Days, String Description, boolean is_active) {

      this.League_ID = League_ID;
      this.League_Name = League_Name;
      this.League_Level = League_Level;
      this.Monthly_Due = Monthly_Due;
      this.Active_Days = Active_Days;
      this.Description = Description;
      this.is_active = is_active;
    }
    public Integer getLeague_ID() {
      return League_ID;
    }
    public void setLeague_ID(Integer League_ID) {
      this.League_ID = League_ID;
    }
    public String getLeague_Name() {
      return League_Name;
    }
    public void setLeague_Name(String League_Name) {
      League_Name = League_Name.replaceAll("[^A-Za-z0-9 - ]","");
      if(League_Name.length()<4){
        throw new IllegalArgumentException("League_Name is too short.");
      }
      if(League_Name.length()>100){
        throw new IllegalArgumentException("League_Name is too long.");
      }
      this.League_Name = League_Name;
    }
    public String getLeague_Level() {
      return League_Level;
    }
    public void setLeague_Level(String League_Level) {
      League_Level = League_Level.replaceAll("[^A-Za-z0-9 - ]","");
      if(League_Level.length()<4){
        throw new IllegalArgumentException("League_Level is too short.");
      }
      if(League_Level.length()>100){
        throw new IllegalArgumentException("League_Level is too long.");
      }
      this.League_Level = League_Level;
    }
    public Double getMonthly_Due() {
      return Monthly_Due;
    }
    public void setMonthly_Due(Double Monthly_Due) {
      this.Monthly_Due = Monthly_Due;
    }
    public String getActive_Days() {
      return Active_Days;
    }
    public void setActive_Days(String Active_Days) {
      this.Active_Days = Active_Days;
    }
    public String getDescription() {
      return Description;
    }
    public void setDescription(String Description) {
      Description = Description.replaceAll("[^A-Za-z0-9 - ]","");
      if(Description.length()<4){
        throw new IllegalArgumentException("Description is too short.");
      }
      if(Description.length()>255){
        throw new IllegalArgumentException("Description is too long.");
      }
      this.Description = Description;
    }
    public boolean getis_active() {
      return is_active;
    }
    public void setis_active(boolean is_active) {
      this.is_active = is_active;
    }

  }

