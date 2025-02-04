package com.beck.beck_demos.budget_app.controllers;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.*;
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

class AddSaved_Search_OrderServletTest {

  private static final String PAGE="WEB-INF/Budget_App/Add_Saved_Search_Order.jsp";
  AddSaved_Search_OrderServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new AddSaved_Search_OrderServlet();
    servlet.init(new Saved_Search_OrderDAO_Fake());
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
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }
  @Test
  public void TestAddHasErrorsForEachFieldAndKeepsOnSamePage() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID(39);
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Owned_UserError = results.get("saved_search_orderOwned_Usererror");
    String NicknameError = results.get("saved_search_orderNicknameerror");
    String DescriptionError = results.get("saved_search_orderDescriptionerror");
    String Last_UsedError = results.get("saved_search_orderLast_Usederror");


    assertNotEquals("",NicknameError);
    assertNotNull(NicknameError);
    assertNotEquals("",DescriptionError);
    assertNotNull(DescriptionError);


    assertEquals(200,responseStatus);
  }
  @Test
  public void TestAddCanAddWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID(39);
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("inputsaved_search_orderOwned_User","406");
    request.setParameter("inputsaved_search_orderNickname","TestValue");
    request.setParameter("inputsaved_search_orderDescription","TestValue");

    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Saved_Search_Order_Added = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Saved_Search_Order_Added);
    assertEquals("Saved_Search_Order Added",Saved_Search_Order_Added);
    assertNotEquals("",Saved_Search_Order_Added);
  }
  @Test
  public void testExceptionKeyThrowsException() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID(39);
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("inputsaved_search_orderOwned_User","406");
    request.setParameter("inputsaved_search_orderNickname","EXCEPTION");
    request.setParameter("inputsaved_search_orderDescription","EXCEPTION");
    request.setParameter("inputsaved_search_orderTimes_Ran","406");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Saved_Search_Order_Added = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Saved_Search_Order_Added);
    assertEquals("Saved_Search_Order Not Added",Saved_Search_Order_Added);
    assertNotEquals("",Saved_Search_Order_Added);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }
  @Test
  public void testDuplicateKeyReturnsZero() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID(39);
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("inputsaved_search_orderOwned_User","406");
    request.setParameter("inputsaved_search_orderNickname","DUPLICATE");
    request.setParameter("inputsaved_search_orderDescription","DUPLICATE");
    request.setParameter("inputsaved_search_orderTimes_Ran","406");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Saved_Search_Order_Added = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Saved_Search_Order_Added);
    assertEquals("Saved_Search_Order Not Added",Saved_Search_Order_Added);
    assertNotEquals("",Saved_Search_Order_Added);
  }
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new AddSaved_Search_OrderServlet();
    servlet.init();
  }

}


