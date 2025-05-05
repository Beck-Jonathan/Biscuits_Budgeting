package com.beck.beck_demos.budget_app.models;
public class Suggestion_VM extends Suggestion {
  private User User;

  public Suggestion_VM() {
  }

  public Suggestion_VM(Suggestion suggestion) {
    super(suggestion.getSuggestion_ID(), suggestion.getUser_ID(), suggestion.getApplication_Name(), suggestion.getcontent());
  }

  public Suggestion_VM(Suggestion suggestion, User user) {
    super(suggestion.getSuggestion_ID(), suggestion.getUser_ID(), suggestion.getApplication_Name(), suggestion.getcontent());
    this.User = user;

  }



  public User getUser() {
    return User;
  }

  public void setUser(User _user) {
    this.User = _user;
  }
}