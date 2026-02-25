package com.beck.beck_demos.budget_app.controllers;

import java.io.IOException;
import java.util.*;

import com.beck.beck_demos.budget_app.controllers.UserSignInServlet;
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

import static org.junit.jupiter.api.Assertions.*;

class UserSignInServletTest {
  private static final String PAGE="WEB-INF/crrg/Login.jsp";
  UserSignInServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new UserSignInServlet();
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
  public void TestAddHasErrorsForEachFieldAndKeepsOnSamePage() throws ServletException, IOException{
    request.setSession(session);
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String User_IDError = results.get("userUser_Nameerror");
    String PasswordError = results.get("userUser_PWerror");
    assertNotEquals("",User_IDError);
    assertNotNull(User_IDError);
    assertNotEquals("",PasswordError);
    assertNotNull(PasswordError);
    assertEquals(302,responseStatus);
  }
  @Test
  public void TestCanLoginWithNoErrorsAndRedirects() throws ServletException, IOException{
    request.setSession(session);
    request.setParameter("inputuserUser_Name","dTSPJjCO");
    request.setParameter("inputuserUser_PW","XNbwbckK!!");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    User user = (User) session.getAttribute("User_B");
    assertEquals(302,responseStatus);
    assertNotNull(user);
    assertEquals("799369e6-d63a-4428-b239-5eb2e09113a2",user.getUser_ID());
  }
  @Test
  public void TestAddCanNotLoginWithBadPasswordNoErrorsAndRedirects() throws ServletException, IOException{
    request.setSession(session);
    request.setParameter("inputuserUser_Name","bnyoAUFg");
    request.setParameter("inputuserUser_PW","FkGJddBij!!");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String PasswordError = results.get("loginFail");
    User user = (User) session.getAttribute("User_B");
    assertEquals(302,responseStatus);
    assertNull(user);
    assertNotNull(PasswordError);
    assertNotEquals("",PasswordError);
    assertEquals("Login Failed, please verify your username and password",PasswordError);
  }

  @Test
  public void testExceptionKeyThrowsException() throws ServletException, IOException{

    request.setSession(session);
    request.setParameter("inputuserUser_Name","EXCEPTION");
    request.setParameter("inputuserUser_PW","soBHCkld!!");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String User_Added = results.get("loginFail");
    String dbError = results.get("dbError");
    User user = (User) session.getAttribute("User_B");
    assertNull(user);
    assertEquals(302,responseStatus);
    assertNotNull(User_Added);
    assertEquals("Login Failed, please verify your username and password",User_Added);
    assertNotEquals("",User_Added);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }

  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new UserSignInServlet();
    servlet.init();
  }


}