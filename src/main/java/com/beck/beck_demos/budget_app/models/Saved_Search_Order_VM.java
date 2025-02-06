package com.beck.beck_demos.budget_app.models;

import java.util.List;

/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */

public class Saved_Search_Order_VM extends Saved_Search_Order {
  private User User;
  private List<Saved_Search_Order_Line> Saved_Search_Order_Lines;

  public Saved_Search_Order_VM(){}

  public Saved_Search_Order_VM(Saved_Search_Order saved_search_order){
    super(saved_search_order.getSaved_Search_Order_ID(), saved_search_order.getOwned_User(), saved_search_order.getNickname(), saved_search_order.getDescription(), saved_search_order.getLast_Used(), saved_search_order.getLast_Updated(), saved_search_order.getTimes_Ran());
  }

  public Saved_Search_Order_VM(Saved_Search_Order saved_search_order,User user){
    super( saved_search_order.getSaved_Search_Order_ID(),  saved_search_order.getOwned_User(),  saved_search_order.getNickname(),  saved_search_order.getDescription(),  saved_search_order.getLast_Used(),  saved_search_order.getLast_Updated(),  saved_search_order.getTimes_Ran());
    this.User = user;

  }

  public Saved_Search_Order_VM(Saved_Search_Order saved_search_order,List<Saved_Search_Order_Line> saved_search_order_lines){
    super( saved_search_order.getSaved_Search_Order_ID(),  saved_search_order.getOwned_User(),  saved_search_order.getNickname(),  saved_search_order.getDescription(),  saved_search_order.getLast_Used(),  saved_search_order.getLast_Updated(),  saved_search_order.getTimes_Ran());
    this.Saved_Search_Order_Lines = saved_search_order_lines;

  }
  public User getUser() {
    return User;
  }
  public void setUser(User _user) {
    this.User = _user;
  }
  public List<Saved_Search_Order_Line> getSaved_Search_Order_Lines() {
    return Saved_Search_Order_Lines;
  }
  public void setSaved_Search_Order_Lines(List<Saved_Search_Order_Line> _saved_search_order_lines) {
    this.Saved_Search_Order_Lines = _saved_search_order_lines;
  }

}


