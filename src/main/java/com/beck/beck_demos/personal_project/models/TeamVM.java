package com.beck.beck_demos.personal_project.models;

public class TeamVM extends Team{
  League _League;


  public TeamVM(Integer Team_ID, Integer League_ID, String Name, String Team_Primary_Color, String Team_Secondary_Color, String Team_Tertiary_Color, String Team_City, String Team_State, String Logo, boolean is_active, League _League) {
    super(Team_ID, League_ID, Name, Team_Primary_Color, Team_Secondary_Color, Team_Tertiary_Color, Team_City, Team_State, Logo, is_active);
    this._League = _League;
  }



  public League getLeague() {

    return _League;
  }

  public void setLeague_name(League league) {

    this._League = league;

  }


}
