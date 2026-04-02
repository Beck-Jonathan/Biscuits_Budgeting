package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data_fakes.Planned_TransactionDAO_Fake;
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

public class DeletePlanned_TransactionServletTest {
  DeletePlanned_TransactionServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;

  @BeforeEach
  public void setup() throws ServletException {
    servlet = new DeletePlanned_TransactionServlet();
    MockServletContext servletContext = new MockServletContext();
    MockServletConfig servletConfig = new MockServletConfig(servletContext, "DeletePlanned_Transaction");

    servlet.init(servletConfig);
    // Inject the Fake DAO
    servlet.init(new Planned_TransactionDAO_Fake());

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
    // No user set in session
    request.setSession(session);
    servlet.doPost(request, response);

    String result = response.getContentAsString();
    assertEquals("200", String.valueOf(response.getStatus()));
    assertEquals("-1", result);
  }

  @Test
  public void TestUserWithWrongRoleGetsNegativeOne() throws ServletException, IOException {
    setupValidUser("Guest"); // Servlet expects "User"
    servlet.doPost(request, response);

    String result = response.getContentAsString();
    assertEquals("-1", result);
  }

  @Test
  public void TestMissingIDGetsNegativeTwo() throws ServletException, IOException {
    setupValidUser("User");
    // ID parameter is not set
    servlet.doPost(request, response);

    String result = response.getContentAsString();
    assertEquals("-2", result);
  }

  @Test
  public void TestSuccessfulDeactivationReturnsOne() throws ServletException, IOException {
    setupValidUser("User");
    request.setParameter("Planned_Transaction_ID", "OVfSlZsItrrpYeHcmxiLVsYqAwPYQqpSuKIv");

    servlet.doPost(request, response);

    String result = response.getContentAsString();
    assertEquals("1", result);
  }

  @Test
  public void TestDeactivatingInvalidIDReturnsNegativeTwo() throws ServletException, IOException {
    setupValidUser("User");
    request.setParameter("Planned_Transaction_ID", "vvvv");

    servlet.doPost(request, response);

    String result = response.getContentAsString();
    // Per your Servlet logic, DAO returns response, which for fake is -3
    assertEquals("-2", result);
  }

  @Test
  public void TestDeactivatingAlreadyInactiveReturnsNeg4() throws ServletException, IOException {
    setupValidUser("User");
    request.setParameter("Planned_Transaction_ID", "OVfSlZsItrrpYeHcmxiLVsYqAwPYQqpSuKIv");

    // First Call (Success)
    servlet.doPost(request, response);
    assertEquals("1", response.getContentAsString());

    // Second Call (Should fail in DAO)
    response = new MockHttpServletResponse(); // reset response
    servlet.doPost(request, response);

    assertEquals("-4", response.getContentAsString());
  }

  @Test
  public void TestDeletingSQLExceptionReturnsNeg4() throws ServletException, IOException {
    setupValidUser("User");
    request.setParameter("Planned_Transaction_ID", "EXCEPTIONEXCEPTIONEXCEPTIONEXCEPTION");

    // First Call (Success)
    servlet.doPost(request, response);

    assertEquals("-4", response.getContentAsString());
  }

  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = new DeletePlanned_TransactionServlet();
    servlet.init();
  }

  /**
   * Helper method to set up a valid user session
   */
  private void setupValidUser(String Role) {
    User user = new User();
    user.setUser_ID("ElVWkHdfvxKPrukwGMJSROpPgnMRqatVBkkT"); // Set a dummy ID for the setuser_ID call
    List<String> roles = new ArrayList<>();
    roles.add(Role);
    user.setRoles(roles);
    request.setSession(session);
    session.setAttribute("User_B", user);
  }
}