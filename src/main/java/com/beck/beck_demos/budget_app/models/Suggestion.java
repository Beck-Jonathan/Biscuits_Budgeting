package com.beck.beck_demos.budget_app.models;



import org.jetbrains.annotations.NotNull;

/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */

public class Suggestion implements Comparable<Suggestion> {
  private String Suggestion_ID;
  private String User_ID;
  private String Application_Name;
  private String content;

  public Suggestion(){}

  public Suggestion(String Suggestion_ID, String User_ID, String Application_Name, String content) {

    this.Suggestion_ID = Suggestion_ID;
    this.User_ID = User_ID;
    this.Application_Name = Application_Name;
    this.content = content;
  }


  public Suggestion(String Suggestion_ID) {

    this.Suggestion_ID = Suggestion_ID;
  }

  /**
   * <p> Gets the Suggestion_ID of the associated Suggestion object </p>
   * @return the Suggestion_ID of this Suggestion object.
   */
  public String getSuggestion_ID() {
    return Suggestion_ID;
  }

  /**
   * <p> Sets the Suggestion_ID of the associated Suggestion object </p>
   * @param Suggestion_ID the suggestion_id of the suggestion,
   * throws IllegalArgumentException if Suggestion_ID under 3 characters or longer than 36 characters
   */
  public void setSuggestion_ID(String Suggestion_ID) {
    //Suggestion_ID = Suggestion_ID.replaceAll("[^A-Za-z0-9 - ]","");
    if(Suggestion_ID.length()<36){
      throw new IllegalArgumentException("Suggestion_ID is too short.");
    }
    if(Suggestion_ID.length()>36){
      throw new IllegalArgumentException("Suggestion_ID is too long.");
    }
    this.Suggestion_ID = Suggestion_ID;
  }

  /**
   * <p> Gets the User_ID of the associated Suggestion object </p>
   * @return the User_ID of this Suggestion object.
   */
  public String getUser_ID() {
    return User_ID;
  }

  /**
   * <p> Sets the User_ID of the associated Suggestion object </p>
   * @param User_ID the user_id of the suggestion,
   * throws IllegalArgumentException if User_ID under 3 characters or longer than 36 characters
   */
  public void setUser_ID(String User_ID) {
    //User_ID = User_ID.replaceAll("[^A-Za-z0-9 - ]","");
    if(User_ID.length()<36){
      throw new IllegalArgumentException("User_ID is too short.");
    }
    if(User_ID.length()>36){
      throw new IllegalArgumentException("User_ID is too long.");
    }
    this.User_ID = User_ID;
  }
  /**
   * <p> Gets the Application_Name of the associated Suggestion object </p>
   * @return the Application_Name of this Suggestion object.
   */
  public String getApplication_Name() {
    return Application_Name;
  }

  /**
   * <p> Sets the Application_Name of the associated Suggestion object </p>
   * @param Application_Name the application_name of the suggestion,
   * throws IllegalArgumentException if Application_Name under 3 characters or longer than 100 characters
   */
  public void setApplication_Name(String Application_Name) {
    Application_Name = Application_Name.replaceAll("[^A-Za-z0-9 - ]","");
    if(Application_Name.length()<4){
      throw new IllegalArgumentException("Application_Name is too short.");
    }
    if(Application_Name.length()>100){
      throw new IllegalArgumentException("Application_Name is too long.");
    }
    this.Application_Name = Application_Name;
  }

  /**
   * <p> Gets the content of the associated Suggestion object </p>
   * @return the content of this Suggestion object.
   */
  public String getcontent() {
    return content;
  }

  /**
   * <p> Sets the content of the associated Suggestion object </p>
   * @param content the content of the suggestion,
   * throws IllegalArgumentException if content under 3 characters or longer than 1000 characters
   */
  public void setcontent(String content) {
    content = content.replaceAll("[^A-Za-z0-9 - ]","");
    if(content.length()<4){
      throw new IllegalArgumentException("content is too short.");
    }
    if(content.length()>1000){
      throw new IllegalArgumentException("content is too long.");
    }
    this.content = content;
  }
  @Override
  public int compareTo(@NotNull Suggestion o) {
    if (this.Suggestion_ID.compareTo(o.Suggestion_ID)<0){
      return -1;
    }
    else if(this.Suggestion_ID.compareTo(o.Suggestion_ID) > 0){
      return 1;
    }
    if (this.User_ID.compareTo(o.User_ID)<0){
      return -1;
    }
    else if(this.User_ID.compareTo(o.User_ID) > 0){
      return 1;
    }
    if (this.Application_Name.compareTo(o.Application_Name)<0){
      return -1;
    }
    else if(this.Application_Name.compareTo(o.Application_Name) > 0){
      return 1;
    }
    if (this.content.compareTo(o.content)<0){
      return -1;
    }
    else if(this.content.compareTo(o.content) > 0){
      return 1;
    }
    return 0;
  }

}

