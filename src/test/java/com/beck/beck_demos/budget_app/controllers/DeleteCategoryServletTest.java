package com.beck.beck_demos.budget_app.controllers;

import java.io.IOException;
import java.util.*;
import com.beck.beck_demos.budget_app.data_fakes.CategoryDAO_Fake;
import com.beck.beck_demos.budget_app.models.Category;
import com.beck.beck_demos.budget_app.models.Category_VM;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

class DeleteCategoryServletTest {

  private static final String PAGE="WEB-INF/budget_app/Delete_Category.jsp";
  DeleteCategoryServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new DeleteCategoryServlet();
    servlet.init(new CategoryDAO_Fake());
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
    user.setUser_ID(1);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doGet(request,response);
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
  public void TestDeactivateCanDeactivate() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID(36);
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("categoryid","fOmyBlIv");
    request.setParameter("mode","0");
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
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }
  @Test
  public void TestDeactivateFailIfAlreadyFalse() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID(36);
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("categoryid","fOmyBlIv");

    servlet.doGet(request,response);
    servlet.doGet(request,response);
    int status = (int) request.getAttribute("result");
    assertEquals(0,status);
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
    request.setParameter("categoryid","xxxxxxxxxxxxx");

    servlet.doGet(request,response);
    int status = (int) request.getAttribute("result");
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String dbStatus = results.get("dbStatus");
    assertEquals("Unable to delete xxxxxxxxxxxxx.",dbStatus);
    assertEquals(0,status);
  }

  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new DeleteCategoryServlet();
    servlet.init();
  }

}
