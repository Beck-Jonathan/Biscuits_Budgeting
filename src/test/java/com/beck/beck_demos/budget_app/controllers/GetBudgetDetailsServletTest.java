package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data_fakes.BudgetDAO_Fake;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetBudgetDetailsServletTest {
  GetBudgetDetailsServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;

  @BeforeEach
  public void setup() throws ServletException {
    servlet = new GetBudgetDetailsServlet();
    MockServletContext servletContext = new MockServletContext();
    MockServletConfig servletConfig = new MockServletConfig(servletContext, "GetBudgetDetails");

    servlet.init(servletConfig);
    // Inject the Fake DAO
    servlet.init(new BudgetDAO_Fake());

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
  public void TestLoggedOutUserReturnsUnauthorized() throws ServletException, IOException {
    request.setSession(session);
    servlet.doGet(request, response);

    assertEquals(401, response.getStatus());
  }

  @Test
  public void TestUserWithWrongRoleReturnsUnauthorized() throws ServletException, IOException {
    setupValidUser("Guest");
    servlet.doGet(request, response);

    assertEquals(401, response.getStatus());
  }

  @Test
  public void TestMissingBudgetIDReturnsNegativeTwo() throws ServletException, IOException {
    setupValidUser("User");
    // budget_id not set
    servlet.doGet(request, response);

    String result = response.getContentAsString();
    assertEquals("-2", result);
  }

  @Test
  public void TestValidIDReturnsJson() throws ServletException, IOException {
    setupValidUser("User");
    // Assuming 'VALID_ID' is configured in your BudgetDAO_Fake to return a Budget
    request.setParameter("budget_id", "OVfSlZsItrrpYeHcmxiLVsYqAwPYQqpSuKIv");

    servlet.doGet(request, response);

    String result = response.getContentAsString();
    assertEquals("text/plain", response.getContentType());
    // Checking for JSON structure and formatted date
    assertTrue(result.contains("\"amount\":"));
    assertTrue(result.contains("\"start_date\":"));
    // Verify ISO date format YYYY-MM-DD (e.g., 2026-04-01)
    assertTrue(result.matches(".*\\d{4}-\\d{2}-\\d{2}.*"));
  }

  @Test
  public void TestNonExistentIDReturnsZero() throws ServletException, IOException {
    setupValidUser("User");
    request.setParameter("budget_id", "NON_EXISTENT_ID");

    servlet.doGet(request, response);

    String result = response.getContentAsString();
    assertEquals("0", result);
  }

  @Test
  public void TestSQLExceptionReturnsNeg12() throws ServletException, IOException {
    setupValidUser("User");
    request.setParameter("budget_id", "EXCEPTION_TRIGGER");

    servlet.doGet(request, response);

    assertEquals("-12", response.getContentAsString());
  }

  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = new GetBudgetDetailsServlet();
    servlet.init();
  }

  /**
   * Helper method to set up a valid user session
   */
  private void setupValidUser(String Role) {
    User user = new User();
    user.setUser_ID("ElVWkHdfvxKPrukwGMJSROpPgnMRqatVBkkT");
    List<String> roles = new ArrayList<>();
    roles.add(Role);
    user.setRoles(roles);
    request.setSession(session);
    session.setAttribute("User_B", user);
  }
}