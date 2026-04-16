package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data_fakes.CategoryDAO_Fake;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpdateSubcategoryThresholdTest {

  UpdateSubcategoryThreshold servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  MockHttpSession session;

  @BeforeEach
  public void setup() throws ServletException {
    servlet = new UpdateSubcategoryThreshold();
    servlet.init(new CategoryDAO_Fake());
    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();
    session = new MockHttpSession();
  }

  @AfterEach
  public void teardown() {
    servlet = null;
    request = null;
    response = null;
    session = null;
  }

  /**
   * Helper to set up a valid User session
   */
  private void setupUser(String role) {
    User user = new User();
    user.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    List<String> roles = new ArrayList<>();
    roles.add(role);
    user.setRoles(roles);
    session.setAttribute("User_B", user);
    request.setSession(session);
  }

  @Test
  public void TestLoggedOutUserReturnsNegativeOne() throws ServletException, IOException {
    request.setSession(session);
    servlet.doPost(request, response);

    assertEquals("-1", response.getContentAsString());
  }

  @Test
  public void TestWrongRoleReturnsNegativeOne() throws ServletException, IOException {
    setupUser("Guest"); // Not "User"
    servlet.doPost(request, response);

    assertEquals("-1", response.getContentAsString());
  }

  @Test
  public void TestValidThresholdUpdateReturnsSuccessCode() throws ServletException, IOException {
    setupUser("User");
    request.setParameter("subcategoryid", "lZoleuasarwCfcmdPWeDgyapFwTISoPKgqXc");
    request.setParameter("amount", "150.00"); // Updating the threshold amount

    servlet.doPost(request, response);

    // Assuming a successful DB update returns "1"
    assertEquals("1", response.getContentAsString());
  }

  @Test
  public void TestMissingSubcategoryIdReturnsNegativeTwo() throws ServletException, IOException {
    setupUser("User");
    request.setParameter("subcategoryid", "");
    request.setParameter("amount", "100.00");

    servlet.doPost(request, response);

    assertEquals("-2", response.getContentAsString());
  }

  @Test
  public void TestInvalidAmountReturnsExceptionCode() throws ServletException, IOException {
    setupUser("User");
    request.setParameter("subcategoryid", "618052e9-c69b-4d9b-880e-e22e4a970bd6");
    request.setParameter("amount", "NOT_A_NUMBER");

    servlet.doPost(request, response);

    // In your servlet, parseInt/parseFloat failing will likely trigger the catch block returning -4 or -5
    String reply = response.getContentAsString();
    // Verify it isn't "1"
    assertTrue(Integer.parseInt(reply) < 0);
  }

  @Test
  public void TestDatabaseErrorDuringUpdateReturnsNegativeFour() throws ServletException, IOException {
    setupUser("User");
    // Triggering the exception catch block for a failed threshold update
    request.setParameter("subcategoryid", "EXCEPTIONEXCEPTIONEXCEPTIONEXCEPTION");
    request.setParameter("amount", "200.00");

    servlet.doPost(request, response);

    assertEquals("-4", response.getContentAsString());
  }

  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new UpdateSubcategoryThreshold();
    servlet.init();
  }
}