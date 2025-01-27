package com.beck.beck_demos.budget_app.controllers;

import java.io.IOException;
import java.util.*;

import com.beck.beck_demos.budget_app.controllers.UserSignOutServlet;
import com.beck.beck_demos.budget_app.data_fakes.UserDAO_Fake;
import com.beck.beck_demos.budget_app.models.User;

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

class UserSignOutServletTest {

  UserSignOutServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new UserSignOutServlet();
    servlet.init();
    request =  new MockHttpServletRequest();
    response = new MockHttpServletResponse();
    session = new MockHttpSession();

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
  public void TestLoggedInUserGets302OnDoGetAndSessionIsInvalid() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("admin");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
    boolean isInvalid = request.isRequestedSessionIdValid();
    assertTrue(isInvalid);

  }
  @Test
  public void TestLoggeOutUserGets302OnDoGetAndSessionIsInvalid() throws ServletException, IOException{


    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);

    boolean isInvalid = request.isRequestedSessionIdValid();
    assertTrue(isInvalid);
  }

}