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

public class ActivatePlanned_TransactionServletTest {
  ActivatePlanned_TransactionServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;

  @BeforeEach
  public void setup() throws ServletException {
    servlet = new ActivatePlanned_TransactionServlet();
    MockServletContext servletContext = new MockServletContext();
    MockServletConfig servletConfig = new MockServletConfig(servletContext, "ActivatePlanned_Transaction");

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
    request.setParameter("planned_transactionid", "too-short");
    request.setParameter("mode", "1");

    servlet.doPost(request, response);

    String result = response.getContentAsString();
    assertEquals("-2", result);
  }

  @Test
  public void TestDeactivateModeZeroReturnsOne() throws ServletException, IOException {
    setupValidUser("User");
    request.setParameter("planned_transactionid", "OVfSlZsItrrpYeHcmxiLVsYqAwPYQqpSuKIv");
    request.setParameter("mode", "0");

    servlet.doPost(request, response);

    String result = response.getContentAsString();
    assertEquals("1", result);
  }

  @Test
  public void TestReactivateModeOneReturnsOne() throws ServletException, IOException {
    setupValidUser("User");
    request.setParameter("planned_transactionid", "XgTFExCLUkSKOmJNDAahQsRRZNWrlWuMikhZ");
    request.setParameter("mode", "1");

    servlet.doPost(request, response);

    String result = response.getContentAsString();
    assertEquals("1", result);
  }

  @Test
  public void TestDeactivateExceptionReturnsNeg4() throws ServletException, IOException {
    setupValidUser("User");
    request.setParameter("planned_transactionid", "EXCEPTIONEXCEPTIONEXCEPTIONEXCEPTION");
    request.setParameter("mode", "0");

    servlet.doPost(request, response);

    assertEquals("-4", response.getContentAsString());
  }

  @Test
  public void TestReactivateExceptionReturnsNeg5() throws ServletException, IOException {
    setupValidUser("User");
    request.setParameter("planned_transactionid", "EXCEPTIONEXCEPTIONEXCEPTIONEXCEPTION");
    request.setParameter("mode", "1");

    servlet.doPost(request, response);

    assertEquals("-5", response.getContentAsString());
  }

  @Test
  public void TestFailedReactivateReturnsZero() throws ServletException, IOException {
    setupValidUser("User");
    // Use an ID that exists but is already active, assuming DAO returns 0
    request.setParameter("planned_transactionid", "CGfvVUjDuEFAsuGnMFGlXfOEDaOHJAOrVHEh");
    request.setParameter("mode", "1");

    servlet.doPost(request, response);

    assertEquals("0", response.getContentAsString());
  }

  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = new ActivatePlanned_TransactionServlet();
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