package com.beck.beck_demos.personal_project.models;

public class Team {
  private Integer Team_ID;
  private Integer League_ID;
  private String Name;
  private String Team_Primary_Color;
  private String Team_Secondary_Color;
  private String Team_Tertiary_Color;
  private String Team_City;
  private String Team_State;
  private String Logo;
  private boolean is_active;

  public Team(){}

  public Team(Integer Team_ID, Integer League_ID, String Name, String Team_Primary_Color, String Team_Secondary_Color, String Team_Tertiary_Color, String Team_City, String Team_State, String Logo, boolean is_active) {

    this.Team_ID = Team_ID;
    this.League_ID = League_ID;
    this.Name = Name;
    this.Team_Primary_Color = Team_Primary_Color;
    this.Team_Secondary_Color = Team_Secondary_Color;
    this.Team_Tertiary_Color = Team_Tertiary_Color;
    this.Team_City = Team_City;
    this.Team_State = Team_State;
    this.Logo = Logo;
    this.is_active = is_active;
  }
  public Integer getTeam_ID() {
    return Team_ID;
  }
  public void setTeam_ID(Integer Team_ID) {
    this.Team_ID = Team_ID;
  }
  public Integer getLeague_ID() {
    return League_ID;
  }
  public void setLeague_ID(Integer League_ID) {
    this.League_ID = League_ID;
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
  public String getTeam_Primary_Color() {
    return Team_Primary_Color;
  }
  public void setTeam_Primary_Color(String Team_Primary_Color) {
    Team_Primary_Color = Team_Primary_Color.replaceAll("[^A-Za-z0-9 - ]","");
    if(Team_Primary_Color.length()<6){
      throw new IllegalArgumentException("Team_Primary_Color is too short.");
    }
    if(Team_Primary_Color.length()>6){
      throw new IllegalArgumentException("Team_Primary_Color is too long.");
    }
    this.Team_Primary_Color = Team_Primary_Color;
  }
  public String getTeam_Secondary_Color() {
    return Team_Secondary_Color;
  }
  public void setTeam_Secondary_Color(String Team_Secondary_Color) {
    Team_Secondary_Color = Team_Secondary_Color.replaceAll("[^A-Za-z0-9 - ]","");
    if(Team_Secondary_Color.length()<6){
      throw new IllegalArgumentException("Team_Secondary_Color is too short.");
    }
    if(Team_Secondary_Color.length()>6){
      throw new IllegalArgumentException("Team_Secondary_Color is too long.");
    }
    this.Team_Secondary_Color = Team_Secondary_Color;
  }
  public String getTeam_Tertiary_Color() {
    return Team_Tertiary_Color;
  }
  public void setTeam_Tertiary_Color(String Team_Tertiary_Color) {
    Team_Tertiary_Color = Team_Tertiary_Color.replaceAll("[^A-Za-z0-9 - ]","");
    if(Team_Tertiary_Color.length()<6){
      throw new IllegalArgumentException("Team_Tertiary_Color is too short.");
    }
    if(Team_Tertiary_Color.length()>6){
      throw new IllegalArgumentException("Team_Tertiary_Color is too long.");
    }
    this.Team_Tertiary_Color = Team_Tertiary_Color;
  }
  public String getTeam_City() {
    return Team_City;
  }
  public void setTeam_City(String Team_City) {
    Team_City = Team_City.replaceAll("[^A-Za-z0-9 - ]","");
    if(Team_City.length()<4){
      throw new IllegalArgumentException("Team_City is too short.");
    }
    if(Team_City.length()>100){
      throw new IllegalArgumentException("Team_City is too long.");
    }
    this.Team_City = Team_City;
  }
  public String getTeam_State() {
    return Team_State;
  }
  public void setTeam_State(String Team_State) {
    Team_State = Team_State.replaceAll("[^A-Za-z0-9 - ]","");
    if(Team_State.length()<4){
      throw new IllegalArgumentException("Team_State is too short.");
    }
    if(Team_State.length()>100){
      throw new IllegalArgumentException("Team_State is too long.");
    }
    this.Team_State = Team_State;
  }
  public String getLogo() {
    return Logo;
  }
  public void setLogo(String Logo) {
    Logo = Logo.replaceAll("[^A-Za-z0-9 -. ]","");
    if(Logo.length()<4){
      throw new IllegalArgumentException("Logo is too short.");
    }
    if(Logo.length()>100){
      throw new IllegalArgumentException("Logo is too long.");
    }
    this.Logo = Logo;
  }
  public boolean getis_active() {
    return is_active;
  }
  public void setis_active(boolean is_active) {
    this.is_active = is_active;
  }

}
