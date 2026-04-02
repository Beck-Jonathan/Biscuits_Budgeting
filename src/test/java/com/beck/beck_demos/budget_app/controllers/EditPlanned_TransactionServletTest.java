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

public class EditPlanned_TransactionServletTest {
  EditPlanned_TransactionServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;

  @BeforeEach
  public void setup() throws ServletException {
    servlet = new EditPlanned_TransactionServlet();
    MockServletContext servletContext = new MockServletContext();
    MockServletConfig servletConfig = new MockServletConfig(servletContext, "EditPlanned_Transaction");

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
  public void TestInvalidPlannedTransactionIDReturnsNeg2() throws ServletException, IOException {
    setupValidUser("User");
    request.setParameter("inputplanned_transactionplanned_transaction_ID", "too-short");

    servlet.doPost(request, response);
    assertEquals("-2", response.getContentAsString());
  }

  @Test
  public void TestInvalidSubcategoryIDReturnsNeg4() throws ServletException, IOException {
    setupValidUser("User");
    setValidIDParams();
    request.setParameter("inputplanned_transactionsubcategory_ID", "invalid-id");

    servlet.doPost(request, response);
    assertEquals("-4", response.getContentAsString());
  }

  @Test
  public void TestInvalidBudgetIDReturnsNeg5() throws ServletException, IOException {
    setupValidUser("User");
    setValidIDParams();
    setValidNewDataParams();
    request.setParameter("inputplanned_transactionbudget_id", "invalid-id");

    servlet.doPost(request, response);
    assertEquals("-5", response.getContentAsString());
  }

  @Test
  public void TestInvalidNicknameReturnsNeg6() throws ServletException, IOException {
    setupValidUser("User");
    setValidIDParams();
    setValidNewDataParams();
    request.setParameter("inputplanned_transactionnickname", "  "); // Assuming blank fails

    servlet.doPost(request, response);
    assertEquals("-6", response.getContentAsString());
  }

  @Test
  public void TestInvalidAmountFormatReturnsNeg7() throws ServletException, IOException {
    setupValidUser("User");
    setValidIDParams();
    setValidNewDataParams();
    request.setParameter("inputplanned_transactionamount", "not-a-number");

    servlet.doPost(request, response);
    assertEquals("-7", response.getContentAsString());
  }

  @Test
  public void TestInvalidDateFormatReturnsNeg8() throws ServletException, IOException {
    setupValidUser("User");
    setValidIDParams();
    setValidNewDataParams();
    request.setParameter("inputplanned_transactionstart_date", "2026/03-xx"); // Wrong format for MM/dd/yyyy

    servlet.doPost(request, response);
    assertEquals("-8", response.getContentAsString());
  }

  @Test
  public void TestInvalidTimesPerYearReturnsNeg9() throws ServletException, IOException {
    setupValidUser("User");
    setValidIDParams();
    setValidNewDataParams();
    request.setParameter("inputplanned_transactiontimes_per_year", "Monthly"); // Should be integer

    servlet.doPost(request, response);
    assertEquals("-9", response.getContentAsString());
  }

  @Test
  public void TestInvalidOccurrencesReturnsNeg10() throws ServletException, IOException {
    setupValidUser("User");
    setValidIDParams();
    setValidNewDataParams();
    request.setParameter("inputplanned_transactionoccurrences", "Infinite"); // Should be integer

    servlet.doPost(request, response);
    assertEquals("-10", response.getContentAsString());
  }

  @Test
  public void TestSuccessfulUpdateReturnsOne() throws ServletException, IOException {
    setupValidUser("User");
    setValidIDParams();
    setValidNewDataParams();

    servlet.doPost(request, response);
    assertEquals("1", response.getContentAsString());
  }

  @Test
  public void TestDAOUpdateFailureReturnsZero() throws ServletException, IOException {
    setupValidUser("User");
    setValidIDParams();
    setValidNewDataParams();
    // Use an ID that technically valid format-wise but doesn't exist in fake DB
    request.setParameter("inputplanned_transactionnickname", "DUPLICATEDUPLICATEDUPLICATEDUPLICATE");

    servlet.doPost(request, response);
    assertEquals("0", response.getContentAsString());
  }

  @Test
  public void TestUpdateSQLExceptionReturnsNeg12() throws ServletException, IOException {
    setupValidUser("User");
    setValidIDParams();
    setValidNewDataParams();
    // Trigger exception in fake DAO
    request.setParameter("inputplanned_transactionnickname", "EXCEPTION_TRIGGER");

    servlet.doPost(request, response);
    assertEquals("-12", response.getContentAsString());
  }

  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = new EditPlanned_TransactionServlet();
    servlet.init();
  }

  /**
   * Helper to set valid identification parameters
   */
  private void setValidIDParams() {
    request.setParameter("inputplanned_transactionplanned_transaction_ID", "OVfSlZsItrrpYeHcmxiLVsYqAwPYQqpSuKIv");
  }

  /**
   * Helper to set all required new data parameters
   */
  private void setValidNewDataParams() {
    request.setParameter("inputplanned_transactionsubcategory_ID", "OVfSlZsItrrpYeHcmxiLVsYqAwPYQqpSuKIv");
    request.setParameter("inputplanned_transactionbudget_id", "OVfSlZsItrrpYeHcmxiLVsYqAwPYQqpSuKIv");
    request.setParameter("inputplanned_transactionnickname", "Updated Nickname");
    request.setParameter("inputplanned_transactionamount", "250.50");
    request.setParameter("inputplanned_transactionstart_date", "2026-3-15");
    request.setParameter("inputplanned_transactiontimes_per_year", "4");
    request.setParameter("inputplanned_transactionoccurrences", "8");
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