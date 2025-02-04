package com.beck.beck_demos.budget_app.data_fakes;

import com.beck.beck_demos.budget_app.data.Saved_Search_OrderDAO;
import com.beck.beck_demos.budget_app.models.Saved_Search_Order;
import com.beck.beck_demos.budget_app.models.User;
import com.beck.beck_demos.budget_app.iData.iSaved_Search_OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class Saved_Search_OrderDAO_Fake implements iSaved_Search_OrderDAO {

  private List<Saved_Search_Order> saved_search_orderVMs;

  public Saved_Search_OrderDAO_Fake() {
    saved_search_orderVMs = new ArrayList<>();
    Saved_Search_Order saved_search_order0 = new Saved_Search_Order(34, 31, "PteeostC", "MGSTWDKM", new Date(), new Date(), 23);
    Saved_Search_Order saved_search_order1 = new Saved_Search_Order(38, 31, "XOHBUiTK", "HoEoUIxd", new Date(), new Date(), 27);
    Saved_Search_Order saved_search_order2 = new Saved_Search_Order(53, 31, "DqskgZuC", "ERWvJHsP", new Date(), new Date(), 65);
    Saved_Search_Order saved_search_order3 = new Saved_Search_Order(39, 31, "ilNHRHJU", "sYtJdfTs", new Date(), new Date(), 26);
    Saved_Search_Order saved_search_order4 = new Saved_Search_Order(58, 31, "gMmyxMoV", "kAnaIGTV", new Date(), new Date(), 30);
    Saved_Search_Order saved_search_order5 = new Saved_Search_Order(19, 26, "cHbHlXfW", "fgBHmJwh", new Date(), new Date(), 63);
    Saved_Search_Order saved_search_order6 = new Saved_Search_Order(51, 26, "cXARxyeD", "jeWVLoIu", new Date(), new Date(), 22);
    Saved_Search_Order saved_search_order7 = new Saved_Search_Order(46, 26, "HsGaCMbK", "SdFmNtQA", new Date(), new Date(), 49);
    Saved_Search_Order saved_search_order8 = new Saved_Search_Order(18, 26, "rfBwpojX", "xIWbQiUq", new Date(), new Date(), 67);
    Saved_Search_Order saved_search_order9 = new Saved_Search_Order(15, 26, "WTgBwvXp", "tolfkrIj", new Date(), new Date(), 23);
    Saved_Search_Order saved_search_order10 = new Saved_Search_Order(20, 39, "MKMRwTpv", "IhCJyyys", new Date(), new Date(), 34);
    Saved_Search_Order saved_search_order11 = new Saved_Search_Order(23, 39, "bDTtppEp", "LGkcMHoZ", new Date(), new Date(), 48);
    Saved_Search_Order saved_search_order12 = new Saved_Search_Order(37, 39, "JyxfoiMr", "IqnPbIqS", new Date(), new Date(), 50);
    Saved_Search_Order saved_search_order13 = new Saved_Search_Order(38, 39, "XOyjCvZw", "WKOwCbFg", new Date(), new Date(), 14);
    Saved_Search_Order saved_search_order14 = new Saved_Search_Order(16, 39, "UScxCPvT", "dydmJCuy", new Date(), new Date(), 24);
    saved_search_orderVMs.add(saved_search_order0);
    saved_search_orderVMs.add(saved_search_order1);
    saved_search_orderVMs.add(saved_search_order2);
    saved_search_orderVMs.add(saved_search_order3);
    saved_search_orderVMs.add(saved_search_order4);
    saved_search_orderVMs.add(saved_search_order5);
    saved_search_orderVMs.add(saved_search_order6);
    saved_search_orderVMs.add(saved_search_order7);
    saved_search_orderVMs.add(saved_search_order8);
    saved_search_orderVMs.add(saved_search_order9);
    saved_search_orderVMs.add(saved_search_order10);
    saved_search_orderVMs.add(saved_search_order11);
    saved_search_orderVMs.add(saved_search_order12);
    saved_search_orderVMs.add(saved_search_order13);
    saved_search_orderVMs.add(saved_search_order14);
  }
  @Override
  public List<Saved_Search_Order> getSaved_Search_OrderbyUser(Integer User_ID, int pagesize, int offset) throws SQLException {
    if (User_ID==8008){
      throw new SQLException();
    }
    List<Saved_Search_Order> results = new ArrayList<>();

    for (Saved_Search_Order saved_search_order : saved_search_orderVMs){
      if (saved_search_order.getOwned_User().equals(User_ID)){
        results.add(saved_search_order);
      }
    }
    return results;
  }
  @Override
  public int add(Saved_Search_Order _saved_search_order) throws SQLException {
    if (duplicateKey(_saved_search_order)){
      return 0;
    }
    if (exceptionKey(_saved_search_order)){
      throw new SQLException("error");
    }
    int size = saved_search_orderVMs.size();
    saved_search_orderVMs.add(_saved_search_order);

    int newsize = saved_search_orderVMs.size();
    return newsize-size;
  }

  @Override
  public Saved_Search_Order getSaved_Search_OrderByPrimaryKey(Saved_Search_Order _saved_search_order) throws SQLException {
    Saved_Search_Order result = null;
    for (Saved_Search_Order saved_search_order : saved_search_orderVMs) {
      if (saved_search_order.getSaved_Search_Order_ID().equals(_saved_search_order.getSaved_Search_Order_ID())){
        result = saved_search_order;
        break;
      }
    }
    if (result == null){
      throw new SQLException("Saved_Search_Order not found");
    }
    return result;
  }
  @Override
  public int update(Saved_Search_Order oldSaved_Search_Order, Saved_Search_Order newSaved_Search_Order) throws SQLException{
    int location =-1;
    if (duplicateKey(oldSaved_Search_Order)){
      return 0;
    }
    if (exceptionKey(oldSaved_Search_Order)){
      throw new SQLException("error");
    }
    for (int i=0;i<saved_search_orderVMs.size();i++){
      if (saved_search_orderVMs.get(i).getSaved_Search_Order_ID().equals(oldSaved_Search_Order.getSaved_Search_Order_ID())){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }

    saved_search_orderVMs.set(location,newSaved_Search_Order);
    return 1;
  }


  private boolean duplicateKey(Saved_Search_Order _saved_search_order){
    return _saved_search_order.getNickname().equals("DUPLICATE");
  }
  private boolean exceptionKey(Saved_Search_Order _saved_search_order){
    return _saved_search_order.getNickname().equals("EXCEPTION");
  }

}
