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

public class AddPlanned_TransactionServletTest {
  AddPlanned_TransactionServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;

  @BeforeEach
  public void setup() throws ServletException {
    servlet = new AddPlanned_TransactionServlet();
    MockServletContext servletContext = new MockServletContext();
    MockServletConfig servletConfig = new MockServletConfig(servletContext, "AddPlanned_Transaction");

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
    assertEquals("-1", result);
  }

  @Test
  public void TestUserInWrongRoleGetsNegativeOne() throws ServletException, IOException {
    setupValidUser("Guest");
    servlet.doPost(request, response);

    String result = response.getContentAsString();
    assertEquals("-1", result);
  }

  @Test
  public void TestInvalidSubcategoryIDReturnsNeg4() throws ServletException, IOException {
    setupValidUser("User");
    request.setParameter("inputplanned_transactionsubcategory_ID", "too-short");

    servlet.doPost(request, response);
    assertEquals("-4", response.getContentAsString());
  }

  @Test
  public void TestInvalidBudgetIDReturnsNeg5() throws ServletException, IOException {
    setupValidUser("User");
    setValidRequiredParams();
    request.setParameter("inputplanned_transactionbudget_id", "invalid");

    servlet.doPost(request, response);
    assertEquals("-5", response.getContentAsString());
  }

  @Test
  public void TestInvalidNicknameReturnsNeg6() throws ServletException, IOException {
    setupValidUser("User");
    setValidRequiredParams();
    request.setParameter("inputplanned_transactionnickname", ""); // Assuming empty is invalid

    servlet.doPost(request, response);
    assertEquals("-6", response.getContentAsString());
  }

  @Test
  public void TestInvalidAmountFormatReturnsNeg7() throws ServletException, IOException {
    setupValidUser("User");
    setValidRequiredParams();
    request.setParameter("inputplanned_transactionamount", "not-a-number");

    servlet.doPost(request, response);
    assertEquals("-7", response.getContentAsString());
  }

  @Test
  public void TestInvalidDateFormatReturnsNeg8() throws ServletException, IOException {
    setupValidUser("User");
    setValidRequiredParams();
    request.setParameter("inputplanned_transactionstart_date", "2026/xx/15"); // Wrong format for SimpleDateFormat("MM/dd/yyyy")

    servlet.doPost(request, response);
    assertEquals("-8", response.getContentAsString());
  }

  @Test
  public void TestInvalidTimesPerYearReturnsNeg9() throws ServletException, IOException {
    setupValidUser("User");
    setValidRequiredParams();
    request.setParameter("inputplanned_transactiontimes_per_year", "invalid");

    servlet.doPost(request, response);
    assertEquals("-9", response.getContentAsString());
  }

  @Test
  public void TestInvalidOccurrencesReturnsNeg10() throws ServletException, IOException {
    setupValidUser("User");
    setValidRequiredParams();
    request.setParameter("inputplanned_transactionoccurrences", "invalid");

    servlet.doPost(request, response);
    assertEquals("-10", response.getContentAsString());
  }

  @Test
  public void TestSuccessfulAddReturns1() throws ServletException, IOException {
    setupValidUser("User");
    setValidRequiredParams();
    // Assuming DAO.add returns > 0 on success
    servlet.doPost(request, response);

    assertEquals("36", response.getContentAsString().length());
  }

  @Test
  public void TestDAOExceptionReturnsNeg12() throws ServletException, IOException {
    setupValidUser("User");
    setValidRequiredParams();
    // Use nickname to trigger a fake exception in your DAO Fake
    request.setParameter("inputplanned_transactionnickname", "EXCEPTION_TRIGGER");

    servlet.doPost(request, response);
    assertEquals("-12", response.getContentAsString());
  }

  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = new AddPlanned_TransactionServlet();
    servlet.init();
  }

  /**
   * Helper to set all required parameters to valid values
   */
  private void setValidRequiredParams() {
    request.setParameter("inputplanned_transactionsubcategory_ID", "OVfSlZsItrrpYeHcmxiLVsYqAwPYQqpSuKIv");
    request.setParameter("inputplanned_transactionbudget_id", "OVfSlZsItrrpYeHcmxiLVsYqAwPYQqpSuKIv");
    request.setParameter("inputplanned_transactionnickname", "Valid Nickname");
    request.setParameter("inputplanned_transactionamount", "100.00");
    request.setParameter("inputplanned_transactionstart_date", "2026-03-15");
    request.setParameter("inputplanned_transactiontimes_per_year", "12");
    request.setParameter("inputplanned_transactionoccurrences", "1");
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