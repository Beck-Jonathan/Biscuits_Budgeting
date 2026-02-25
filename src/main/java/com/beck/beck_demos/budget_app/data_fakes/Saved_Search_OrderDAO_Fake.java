package com.beck.beck_demos.budget_app.data_fakes;

import com.beck.beck_demos.budget_app.models.Saved_Search_Order;
import com.beck.beck_demos.budget_app.models.Saved_Search_Order_Line;
import com.beck.beck_demos.budget_app.models.Saved_Search_Order_VM;
import com.beck.beck_demos.budget_app.iData.iSaved_Search_OrderDAO;

import java.sql.SQLException;
import java.util.*;

public class Saved_Search_OrderDAO_Fake implements iSaved_Search_OrderDAO {

  private List<Saved_Search_Order_VM> saved_search_orderVMs;

  public Saved_Search_OrderDAO_Fake() {
    saved_search_orderVMs = new ArrayList<>();
    List<Saved_Search_Order_Line> lines = new ArrayList<>();
    Saved_Search_Order_Line saved_search_order_line0 = new Saved_Search_Order_Line(59, 27, "WKTFwUjn", "0607a176-01de-46ea-a463-1d59db87491a", "gGUbGDSf", true);
    Saved_Search_Order_Line saved_search_order_line1 = new Saved_Search_Order_Line(59, 33, "jmWpYbQX", "1607a176-01de-46ea-a463-1d59db87491a", "RkPqpgka", false);
    Saved_Search_Order_Line saved_search_order_line2 = new Saved_Search_Order_Line(59, 51, "xfkjekxF","2607a176-01de-46ea-a463-1d59db87491a" , "lZatgahB", true);
    Saved_Search_Order_Line saved_search_order_line3 = new Saved_Search_Order_Line(59, 40, "fPndDqMy","3607a176-01de-46ea-a463-1d59db87491a" , "ovoGflKh", false);
    Saved_Search_Order_Line saved_search_order_line4 = new Saved_Search_Order_Line(59, 21, "drJdbQIM","4607a176-01de-46ea-a463-1d59db87491a" , "eJRVvFbt", false);
    lines.add(saved_search_order_line0);
    lines.add(saved_search_order_line1);
    lines.add(saved_search_order_line2);
    lines.add(saved_search_order_line3);
    lines.add(saved_search_order_line4);
    Saved_Search_Order saved_search_order0 = new Saved_Search_Order(34, "0607a176-01de-46ea-a463-1d59db87491a", "PteeostC", "MGSTWDKM", new Date(), new Date(), 23);
    Saved_Search_Order saved_search_order1 = new Saved_Search_Order(38, "0607a176-01de-46ea-a463-1d59db87491a", "XOHBUiTK", "HoEoUIxd", new Date(), new Date(), 27);
    Saved_Search_Order saved_search_order2 = new Saved_Search_Order(53, "0607a176-01de-46ea-a463-1d59db87491a", "DqskgZuC", "ERWvJHsP", new Date(), new Date(), 65);
    Saved_Search_Order saved_search_order3 = new Saved_Search_Order(39, "0607a176-01de-46ea-a463-1d59db87491a", "ilNHRHJU", "sYtJdfTs", new Date(), new Date(), 26);
    Saved_Search_Order saved_search_order4 = new Saved_Search_Order(58, "0607a176-01de-46ea-a463-1d59db87491a", "gMmyxMoV", "kAnaIGTV", new Date(), new Date(), 30);
    Saved_Search_Order saved_search_order5 = new Saved_Search_Order(19, "1607a176-01de-46ea-a463-1d59db87491a", "cHbHlXfW", "fgBHmJwh", new Date(), new Date(), 63);
    Saved_Search_Order saved_search_order6 = new Saved_Search_Order(51, "1607a176-01de-46ea-a463-1d59db87491a", "cXARxyeD", "jeWVLoIu", new Date(), new Date(), 22);
    Saved_Search_Order saved_search_order7 = new Saved_Search_Order(46, "1607a176-01de-46ea-a463-1d59db87491a", "HsGaCMbK", "SdFmNtQA", new Date(), new Date(), 49);
    Saved_Search_Order saved_search_order8 = new Saved_Search_Order(18, "1607a176-01de-46ea-a463-1d59db87491a", "rfBwpojX", "xIWbQiUq", new Date(), new Date(), 67);
    Saved_Search_Order saved_search_order9 = new Saved_Search_Order(15, "1607a176-01de-46ea-a463-1d59db87491a", "WTgBwvXp", "tolfkrIj", new Date(), new Date(), 23);
    Saved_Search_Order saved_search_order10 = new Saved_Search_Order(20, "1607a176-01de-46ea-a463-1d59db87491a", "MKMRwTpv", "IhCJyyys", new Date(), new Date(), 34);
    Saved_Search_Order saved_search_order11 = new Saved_Search_Order(23, "2607a176-01de-46ea-a463-1d59db87491a", "bDTtppEp", "LGkcMHoZ", new Date(), new Date(), 48);
    Saved_Search_Order saved_search_order12 = new Saved_Search_Order(37, "2607a176-01de-46ea-a463-1d59db87491a", "JyxfoiMr", "IqnPbIqS", new Date(), new Date(), 50);
    Saved_Search_Order saved_search_order13 = new Saved_Search_Order(38, "2607a176-01de-46ea-a463-1d59db87491a", "XOyjCvZw", "WKOwCbFg", new Date(), new Date(), 14);
    Saved_Search_Order saved_search_order14 = new Saved_Search_Order(16, "2607a176-01de-46ea-a463-1d59db87491a", "UScxCPvT", "dydmJCuy", new Date(), new Date(), 24);
    Saved_Search_Order_VM saved_search_order_VM0= new Saved_Search_Order_VM(saved_search_order0,lines);
    Saved_Search_Order_VM saved_search_order_VM1= new Saved_Search_Order_VM(saved_search_order1,lines);
    Saved_Search_Order_VM saved_search_order_VM2= new Saved_Search_Order_VM(saved_search_order2,lines);
    Saved_Search_Order_VM saved_search_order_VM3= new Saved_Search_Order_VM(saved_search_order3,lines);
    Saved_Search_Order_VM saved_search_order_VM4= new Saved_Search_Order_VM(saved_search_order4,lines);
    Saved_Search_Order_VM saved_search_order_VM5= new Saved_Search_Order_VM(saved_search_order5,lines);
    Saved_Search_Order_VM saved_search_order_VM6= new Saved_Search_Order_VM(saved_search_order6,lines);
    Saved_Search_Order_VM saved_search_order_VM7= new Saved_Search_Order_VM(saved_search_order7,lines);
    Saved_Search_Order_VM saved_search_order_VM8= new Saved_Search_Order_VM(saved_search_order8,lines);
    Saved_Search_Order_VM saved_search_order_VM9= new Saved_Search_Order_VM(saved_search_order9,lines);
    Saved_Search_Order_VM saved_search_order_VM10= new Saved_Search_Order_VM(saved_search_order10,lines);
    Saved_Search_Order_VM saved_search_order_VM11= new Saved_Search_Order_VM(saved_search_order11,lines);
    Saved_Search_Order_VM saved_search_order_VM12= new Saved_Search_Order_VM(saved_search_order12,lines);
    Saved_Search_Order_VM saved_search_order_VM13= new Saved_Search_Order_VM(saved_search_order13,lines);
    Saved_Search_Order_VM saved_search_order_VM14= new Saved_Search_Order_VM(saved_search_order14,lines);
    saved_search_orderVMs.add(saved_search_order_VM0);
    saved_search_orderVMs.add(saved_search_order_VM1);
    saved_search_orderVMs.add(saved_search_order_VM2);
    saved_search_orderVMs.add(saved_search_order_VM3);
    saved_search_orderVMs.add(saved_search_order_VM4);
    saved_search_orderVMs.add(saved_search_order_VM5);
    saved_search_orderVMs.add(saved_search_order_VM6);
    saved_search_orderVMs.add(saved_search_order_VM7);
    saved_search_orderVMs.add(saved_search_order_VM8);
    saved_search_orderVMs.add(saved_search_order_VM9);
    saved_search_orderVMs.add(saved_search_order_VM10);
    saved_search_orderVMs.add(saved_search_order_VM11);
    saved_search_orderVMs.add(saved_search_order_VM12);
    saved_search_orderVMs.add(saved_search_order_VM13);
    saved_search_orderVMs.add(saved_search_order_VM14);
  }
  @Override
  public List<Saved_Search_Order_VM> getSaved_Search_OrderbyUser(String User_ID, int pagesize, int offset) throws SQLException {
    if (User_ID.equals("EXCEPTIONEXCEPTIONEXCEPTIONEXCEPTION")){
      throw new SQLException();
    }
    List<Saved_Search_Order_VM> results = new ArrayList<>();

    for (Saved_Search_Order_VM saved_search_order : saved_search_orderVMs){
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
    Saved_Search_Order_VM toAdd = new Saved_Search_Order_VM(_saved_search_order);
    saved_search_orderVMs.add(toAdd);

    int newsize = saved_search_orderVMs.size();
    return newsize-size;
  }

  @Override
  public Saved_Search_Order_VM getSaved_Search_OrderByPrimaryKey(Saved_Search_Order _saved_search_order) throws SQLException {
    Saved_Search_Order_VM result = null;
    for (Saved_Search_Order_VM saved_search_order : saved_search_orderVMs) {
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
    Saved_Search_Order_VM toInsert = new Saved_Search_Order_VM(newSaved_Search_Order);

    saved_search_orderVMs.set(location,toInsert);
    return 1;
  }
  @Override
  public int delete(int Saved_Search_Order_ID, String user_ID) throws SQLException{
    int size = saved_search_orderVMs.size();
    int location =-1;
    for (int i=0;i<saved_search_orderVMs.size();i++){
      if (saved_search_orderVMs.get(i).getSaved_Search_Order_ID().equals(Saved_Search_Order_ID) && saved_search_orderVMs.get(i).getOwned_User().equals(user_ID)){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException("Unable To Find Saved_Search_Order.");
    }
    saved_search_orderVMs.remove(location);
    int newsize = saved_search_orderVMs.size();
    return size-newsize;
  }

  @Override
  public int addLine(Saved_Search_Order_Line line) throws SQLException {
    if (duplicateKey(line)){
      return 0;
    }
    if (exceptionKey(line)){
      throw new SQLException("error");
    }
    int result = 0;
    for (Saved_Search_Order_VM searchOrder : saved_search_orderVMs){
      if (searchOrder.getSaved_Search_Order_ID().equals(line.getSaved_Search_Order_ID())){
        List<Saved_Search_Order_Line> lines= searchOrder.getSaved_Search_Order_Lines();
        lines.add(line);
        result=1;
        break;
      }
    }

    return result;
  }

  @Override
  public int updateLine(Saved_Search_Order_Line oldLine, Saved_Search_Order_Line newLine) throws SQLException {
    if (duplicateKey(newLine)){
      return 0;
    }
    if (exceptionKey(newLine)){
      throw new SQLException("error");
    }
    int result = 0;
    for (Saved_Search_Order_VM searchOrder : saved_search_orderVMs){
      if (searchOrder.getSaved_Search_Order_ID().equals(oldLine.getSaved_Search_Order_ID())){
        List<Saved_Search_Order_Line> lines= searchOrder.getSaved_Search_Order_Lines();
        for (Saved_Search_Order_Line line : lines){
          if (line.getLine_No().equals(oldLine.getLine_No())){
            line.setCategory_ID(newLine.getCategory_ID());
            line.setSearch_Phrase(newLine.getSearch_Phrase());
            result = 1;
            break;
          }
        }
      }
    }
    return result;
  }

  private boolean duplicateKey(Saved_Search_Order _saved_search_order){
    return _saved_search_order.getNickname().equals("DUPLICATE");
  }
  private boolean exceptionKey(Saved_Search_Order _saved_search_order){
    return _saved_search_order.getNickname().equals("EXCEPTION");
  }
  private boolean duplicateKey(Saved_Search_Order_Line line){
    return line.getSearch_Phrase().equals("DUPLICATE");
  }
  private boolean exceptionKey(Saved_Search_Order_Line line){
    return line.getSearch_Phrase().equals("EXCEPTION");
  }

}
