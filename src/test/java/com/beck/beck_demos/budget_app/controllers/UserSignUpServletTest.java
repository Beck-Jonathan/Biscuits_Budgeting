package com.beck.beck_demos.budget_app.controllers;

import java.io.IOException;
import java.util.*;
import com.beck.beck_demos.budget_app.data_fakes.UserDAO_Fake;
import com.beck.beck_demos.budget_app.models.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

class UserSignUpServletTest {

  private static final String PAGE="WEB-INF/budget_app/Add_User.jsp";
  UserSignUpServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new UserSignUpServlet();
    servlet.init(new UserDAO_Fake());
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
    user.setUser_ID("af735dfc-22a9-4214-a8e5-fb8de2305700");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }
  @Test
  public void TestLoggedInUserGets302OnDoPost() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("af735dfc-22a9-4214-a8e5-fb8de2305700");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }
  @Test
  public void TestLoggedOutUserGets200OnDoGet() throws ServletException, IOException{
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(200,status);
  }

  @Test
  public void TestWrongRoleGets302onDoGet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("af735dfc-22a9-4214-a8e5-fb8de2305700");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }

  @Test
  public void TestAddHasErrorsForEachFieldAndKeepsOnSamePage() throws ServletException, IOException{

    request.setSession(session);
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String User_NameError = results.get("userUser_Nameerror3");
    String User_PWError = results.get("userUser_PWerror3");
    String EmailError = results.get("userEmailerror");
    assertNotEquals("",User_NameError);
    assertNotNull(User_NameError);
    assertNotEquals("",User_PWError);
    assertNotNull(User_PWError);
    assertNotEquals("",EmailError);
    assertNotNull(EmailError);
    assertEquals(200,responseStatus);
  }
  @Test
  public void TestAddCanAddWithNoErrorsAndLogsIn() throws ServletException, IOException{

    request.setSession(session);
    request.setParameter("inputuserUser_Name","TestValue");
    request.setParameter("inputuserUser_PW","P@ssw0rd");
    request.setParameter("inputuserUser_PW2","P@ssw0rd");
    request.setParameter("inputuserEmail","TestValue@gmail.com");

    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String User_Added = results.get("dbStatus");
    User user = (User) session.getAttribute("user_b");
    assertEquals(302,responseStatus);
    assertNotNull(User_Added);
    assertEquals("User Added",User_Added);
    assertNotEquals("",User_Added);
  }
  @Test
  public void testExceptionKeyThrowsException() throws ServletException, IOException{

    request.setSession(session);
    request.setParameter("inputuserUser_Name","EXCEPTION");
    request.setParameter("inputuserUser_PW","P@ssw0rd");
    request.setParameter("inputuserUser_PW2","P@ssw0rd");
    request.setParameter("inputuserEmail","EXCEPTION@gmail.com");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String User_Added = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(User_Added);
    assertEquals("User Not Added",User_Added);
    assertNotEquals("",User_Added);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }
  @Test
  public void testDuplicateKeyReturnsZero() throws ServletException, IOException{

    request.setSession(session);
    request.setParameter("inputuserUser_Name","DUPLICATE");
    request.setParameter("inputuserUser_PW","P@ssw0rd");
    request.setParameter("inputuserUser_PW2","P@ssw0rd");
    request.setParameter("inputuserEmail","DUPLICATE@gmail.com");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String User_Added = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(User_Added);
    assertEquals("User Not Added",User_Added);
    assertNotEquals("",User_Added);
  }
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new UserSignUpServlet();
    servlet.init();
  }

}

