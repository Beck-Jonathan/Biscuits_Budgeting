package com.beck.beck_demos.budget_app.controllers;

import java.io.IOException;
import java.util.*;

import com.beck.beck_demos.budget_app.data_fakes.CategoryDAO_Fake;
import com.beck.beck_demos.budget_app.data_fakes.Saved_Search_OrderDAO_Fake;
import com.beck.beck_demos.budget_app.models.Saved_Search_Order;

import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;


class EditSaved_Search_OrderServletTest {

  private static final String PAGE="WEB-INF/Budget_App/Edit_Saved_Search_Order.jsp";
  EditSaved_Search_OrderServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new EditSaved_Search_OrderServlet();
    servlet.init(new Saved_Search_OrderDAO_Fake(), new CategoryDAO_Fake());
    request =  new MockHttpServletRequest();
    response = new MockHttpServletResponse();
    session = new MockHttpSession();
    rd = new MockRequestDispatcher(PAGE);
  }

  @AfterEach
  public void teardown(){
    servlet=null;
    request=null;
    response=null;
    session=null;
    rd=null;
  }
  @Test
  public void TestLoggedInUserGets200OnDoGet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID(36);
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(200,status);
  }
  @Test
  public void TestLoggedInUserGets200OnDoPost() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID(36);
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(200,status);
  }
  @Test
  public void TestLoggedOutUserGets302OnDoGet() throws ServletException, IOException{
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }
  @Test
  public void TestLoggedOutUserGets302OnDoPost() throws ServletException, IOException{
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }
  @Test
  public void TestWrongRoleGets302onDoGet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("WrongRole");
    user.setUser_ID(36);
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }
  @Test
  public void TestWrongRoleGets302onDoPost() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("WrongRole");
    user.setUser_ID(36);
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }
  @Test
  public void testGetOneSaved_Search_OrderGetsOneSaved_Search_Order_ID() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID(36);
    user.setRoles(roles);
    session.setAttribute("User_B",user);

    Integer Saved_Search_Order_ID= 34;
    request.setParameter("saved_search_orderid",Saved_Search_Order_ID.toString());
    request.setSession(session);
    servlet.doGet(request,response);
    Saved_Search_Order saved_search_order = (Saved_Search_Order) session.getAttribute("saved_search_order");
    assertNotNull(saved_search_order);
    assertEquals(Saved_Search_Order_ID,saved_search_order.getSaved_Search_Order_ID());
  }
  @Test
  public void testGetOneSaved_Search_OrderCanFail() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID(36);
    user.setRoles(roles);
    session.setAttribute("User_B",user);

    Integer Saved_Search_Order_ID= -1;
    request.setParameter("saved_search_orderid",Saved_Search_Order_ID.toString());

    request.setSession(session);
    servlet.doGet(request,response);
    Saved_Search_Order saved_search_order = (Saved_Search_Order) session.getAttribute("saved_search_order");
    assertNull(saved_search_order.getSaved_Search_Order_ID());
    assertNull(saved_search_order.getOwned_User());
    assertNull(saved_search_order.getNickname());
    assertNull(saved_search_order.getDescription());
    assertNull(saved_search_order.getLast_Used());
    assertNull(saved_search_order.getLast_Updated());
    assertNull(saved_search_order.getTimes_Ran());
  }
  @Test
  public void TestUpdateCanAddWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID(36);
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
//to set the old Saved_Search_Order
    Saved_Search_Order saved_search_order = new Saved_Search_Order();
    saved_search_order.setSaved_Search_Order_ID(34);
    saved_search_order.setOwned_User(34);
    saved_search_order.setNickname("testSaved_Search_Order");
    saved_search_order.setDescription("testSaved_Search_Order");
    session.setAttribute("saved_search_order",saved_search_order);
//create a new albums parameters
    request.setParameter("inputsaved_search_orderOwned_User","406");
    request.setParameter("inputsaved_search_orderNickname","TestValue");
    request.setParameter("inputsaved_search_orderDescription","TestValue");
    request.setParameter("inputsaved_search_orderTimes_Ran","406");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Saved_Search_Order_Updated = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Saved_Search_Order_Updated);
    assertEquals("Saved_Search_Order updated",Saved_Search_Order_Updated);
    assertNotEquals("",Saved_Search_Order_Updated);
  }
  @Test
  public void TestUpdateHasErrorsForEachFiledAndKeepsOnSamePage() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID(36);
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");

    String NicknameError = results.get("saved_search_orderNicknameerror");
    String DescriptionError = results.get("saved_search_orderDescriptionerror");

    ;
    assertNotEquals("",NicknameError);
    assertNotNull(NicknameError);
    assertNotEquals("",DescriptionError);
    assertNotNull(DescriptionError);

    assertEquals(200,responseStatus);
  }
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new EditSaved_Search_OrderServlet();
    servlet.init();
  }
  @Test
  public void testUpdateCanReturnZero() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID(36);
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
//to set the old Saved_Search_Order
    Saved_Search_Order saved_search_order = new Saved_Search_Order();
    saved_search_order.setSaved_Search_Order_ID(43);
    saved_search_order.setOwned_User(43);
    saved_search_order.setNickname("DUPLICATE");
    saved_search_order.setDescription("DUPLICATE");


    session.setAttribute("saved_search_order",saved_search_order);
//create a new albums parameters
    request.setParameter("inputsaved_search_orderOwned_User","406");
    request.setParameter("inputsaved_search_orderNickname","DUPLICATE");
    request.setParameter("inputsaved_search_orderDescription","DUPLICATE");

    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Saved_Search_Order_Updated = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Saved_Search_Order_Updated);
    assertEquals("Saved_Search_Order Not Updated",Saved_Search_Order_Updated);
    assertNotEquals("",Saved_Search_Order_Updated);
  }
  @Test
  public void testUpdateCanThrowSQLException() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID(36);
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
//to set the old Saved_Search_Order
    Saved_Search_Order saved_search_order = new Saved_Search_Order();
    saved_search_order.setSaved_Search_Order_ID(43);
    saved_search_order.setOwned_User(43);
    saved_search_order.setNickname("EXCEPTION");
    saved_search_order.setDescription("EXCEPTION");
    saved_search_order.setTimes_Ran(43);
    session.setAttribute("saved_search_order",saved_search_order);
//create a new albums parameters
    request.setParameter("inputsaved_search_orderOwned_User","406");
    request.setParameter("inputsaved_search_orderNickname","EXCEPTION");
    request.setParameter("inputsaved_search_orderDescription","EXCEPTION");
    request.setParameter("inputsaved_search_orderTimes_Ran","406");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Saved_Search_Order_Updated = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Saved_Search_Order_Updated);
    assertNotEquals("",Saved_Search_Order_Updated);
    assertEquals("Saved_Search_Order Not Updated",Saved_Search_Order_Updated);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }

}

