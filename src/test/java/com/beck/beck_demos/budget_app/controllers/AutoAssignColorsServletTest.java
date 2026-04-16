package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data_fakes.CategoryDAO_Fake;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockRequestDispatcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AutoAssignColorsServletTest {

  private static final String PAGE = "WEB-INF/budget_app/Delete_Category.jsp";
  AutoAssignColorsServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  @BeforeEach
  public void setup() throws ServletException {

    servlet = new AutoAssignColorsServlet();
    servlet.init(new CategoryDAO_Fake());
    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();
    session = new MockHttpSession();
    rd = new MockRequestDispatcher(PAGE);
  }

  @AfterEach
  public void teardown() {
    servlet = null;
    request = null;
    response = null;
    session = null;
    rd = null;
  }

  @Test
  public void TestLoggedInUserGets200OnDoPost() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("af735dfc-22a9-4214-a8e5-fb8de2305700");
    session.setAttribute("User_B", user);
    request.setSession(session);
    servlet.doPost(request, response);
    String status = response.getContentAsString();
    assertEquals("0", status);
  }

  @Test
  public void TestLoggedInUserGets200OnDoPostAndHasCategoriesUpdated() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("sqiidVcxEgAxPSmbAlPuyyPqlajALcCishjt");
    session.setAttribute("User_B", user);
    request.setSession(session);
    servlet.doPost(request, response);
    String status = response.getContentAsString();
    assertEquals("4", status);
  }

  @Test
  public void TestBadUserIDGetsNeg2() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    //user.setUser_ID("");
    session.setAttribute("User_B", user);
    request.setSession(session);
    servlet.doPost(request, response);
    String status = response.getContentAsString();
    assertEquals("-2", status);
  }

  @Test
  public void TestExceptionIDIsHandled() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("EXCEPTIONEXCEPTIONEXCEPTIONEXCEPTION");
    session.setAttribute("User_B", user);
    request.setSession(session);
    servlet.doPost(request, response);
    String status = response.getContentAsString();
    assertEquals("-3", status);
  }

  @Test
  public void TestWrongRoleGets302onDoPost() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("WrongRole");
    user.setRoles(roles);
    session.setAttribute("User_B", user);
    request.setSession(session);
    servlet.doPost(request, response);
    String status = response.getContentAsString();
    assertEquals("-1", status);
  }

  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new AutoAssignColorsServlet();
    servlet.init();
  }

}
