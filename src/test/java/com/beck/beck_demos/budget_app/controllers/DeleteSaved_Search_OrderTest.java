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


class DeleteSaved_Search_OrderTest {
  private static final String PAGE="WEB-INF/Budget_App/Delete_Saved_Search_Order.jsp";
  DeleteSaved_Search_Order servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new DeleteSaved_Search_Order();
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
  public void TestLoggedInUserGets302OnDoGet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID(1);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }
  @Test
  public void TestLoggedOutUserGets302OnDoGet() throws ServletException, IOException{
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }
  @Test
  public void TestDeleteCanDelete() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID(31);
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("saved_search_orderid","34");

    servlet.doGet(request,response);
    int status = (int) request.getAttribute("result");
    assertEquals(1,status);
  }
  @Test
  public void TestWrongRoleGets302onDoGet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("WrongRole");
    user.setRoles(roles);
    user.setUser_ID(1);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }

  @Test
  public void TestDeActivateCanFailIfKeyDoesNotExist() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID(1);
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("saved_search_orderid","1");

    servlet.doGet(request,response);
    int status = (int) request.getAttribute("result");
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String dbStatus = results.get("dbStatus");
    assertEquals("Unable To Find Saved_Search_Order.",dbStatus);
    assertEquals(0,status);
  }

  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new DeleteSaved_Search_Order();
    servlet.init();
  }

}


