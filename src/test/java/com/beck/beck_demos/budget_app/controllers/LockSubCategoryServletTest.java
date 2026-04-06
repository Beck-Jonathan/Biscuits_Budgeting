package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data_fakes.CategoryDAO_Fake;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LockSubCategoryServletTest {
  LockSubCategoryServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;

  @BeforeEach
  public void setup() throws ServletException {
    servlet = new LockSubCategoryServlet();
    MockServletContext servletContext = new MockServletContext();
    MockServletConfig servletConfig = new MockServletConfig(servletContext, "LockPlanned_Transaction");

    servlet.init(servletConfig);
    // Inject the Fake DAO
    servlet.init(new CategoryDAO_Fake());

    request = new MockHttpServletRequest(servletContext);
    response = new MockHttpServletResponse();
    session = new MockHttpSession(servletContext);
  }

  @AfterEach
  public void teardown() {
    servlet = null;
    request = null;
    response = null;
    session = null;
  }

  @Test
  public void TestLoggedOutUserGetsNegativeOne() throws ServletException, IOException {
    request.setSession(session);
    servlet.doPost(request, response);

    String result = response.getContentAsString();
    assertEquals("200", String.valueOf(response.getStatus()));
    assertEquals("-1", result);
  }

  @Test
  public void TestUserWithWrongRoleGetsNegativeOne() throws ServletException, IOException {
    setupValidUser("Guest");
    servlet.doPost(request, response);

    String result = response.getContentAsString();
    assertEquals("-1", result);
  }

  @Test
  public void TestMissingIDGetsNegativeTwo() throws ServletException, IOException {
    setupValidUser("User");
    request.setParameter("mode", "1");
    // planned_transactionid parameter is not set
    servlet.doPost(request, response);

    String result = response.getContentAsString();
    assertEquals("-2", result);
  }

  @Test
  public void TestInvalidIDFormatGetsNegativeTwo() throws ServletException, IOException {
    setupValidUser("User");
    request.setParameter("subcategoryid", "too-short");
    request.setParameter("mode", "1");

    servlet.doPost(request, response);

    String result = response.getContentAsString();
    assertEquals("-2", result);
  }

  @Test
  public void TestLockModeZeroReturnsOne() throws ServletException, IOException {
    setupValidUser("User");
    request.setParameter("subcategoryid", "tqwOoIpqAWlTCXgHIWbRoWGDeWkRLuUjMrUK");
    request.setParameter("mode", "0");

    servlet.doPost(request, response);

    String result = response.getContentAsString();
    assertEquals("1", result);
  }

  @Test
  public void TestReLockModeOneReturnsOne() throws ServletException, IOException {
    setupValidUser("User");
    request.setParameter("subcategoryid", "CptdvYCqiIfQbZZYnDSelOjeuWoIaPIoSyqN");
    request.setParameter("mode", "1");

    servlet.doPost(request, response);

    String result = response.getContentAsString();
    assertEquals("1", result);
  }

  @Test
  public void TestLockExceptionReturnsNeg4() throws ServletException, IOException {
    setupValidUser("User");
    request.setParameter("subcategoryid", "EXCEPTIONEXCEPTIONEXCEPTIONEXCEPTION");
    request.setParameter("mode", "0");

    servlet.doPost(request, response);

    assertEquals("-4", response.getContentAsString());
  }

  @Test
  public void TestReLockExceptionReturnsNeg5() throws ServletException, IOException {
    setupValidUser("User");
    request.setParameter("subcategoryid", "EXCEPTIONEXCEPTIONEXCEPTIONEXCEPTION");
    request.setParameter("mode", "1");

    servlet.doPost(request, response);

    assertEquals("-5", response.getContentAsString());
  }

  @Test
  public void TestFailedReLockReturnsZero() throws ServletException, IOException {
    setupValidUser("User");
    // Use an ID that exists but is already active, assuming DAO returns 0
    request.setParameter("subcategoryid", "tqwOoIpqAWlTCXgHIWbRoWGDeWkRLuUjMrUK");
    request.setParameter("mode", "1");

    servlet.doPost(request, response);

    assertEquals("0", response.getContentAsString());
  }

  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = new LockSubCategoryServlet();
    servlet.init();
  }

  /**
   * Helper method to set up a valid user session
   */
  private void setupValidUser(String Role) {
    User user = new User();
    user.setUser_ID("sqiidVcxEgAxPSmbAlPuyyPqlajALcCishjt");
    List<String> roles = new ArrayList<>();
    roles.add(Role);
    user.setRoles(roles);
    request.setSession(session);
    session.setAttribute("User_B", user);
  }
}