package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data_fakes.BudgetDAO_Fake;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.RequestDispatcher;
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

public class ActivateBudgetServletTest {
  private static final String PAGE = "WEB-INF/budget_app/Delete_budget.jsp";
  ActivateBudgetServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   * <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException {

    servlet = new ActivateBudgetServlet();
    MockServletContext servletContext = new MockServletContext();
    MockServletConfig servletConfig = new MockServletConfig(servletContext, "Delete_budget");
    servlet.init(servletConfig);
    servlet.init(new BudgetDAO_Fake());
    request = new MockHttpServletRequest(servletContext);
    response = new MockHttpServletResponse();
    session = new MockHttpSession(servletContext);
    rd = new MockRequestDispatcher(PAGE);
  }

  /**
   * <p> tear down by setting all variablges to null. </p>
   */
  @AfterEach
  public void teardown() {
    servlet = null;
    request = null;
    response = null;
    session = null;
    rd = null;
  }

  /**
   * <p> Tests That the user will received a 200 status on doGet if they are logged in </p>
   */
  @Test
  public void TestLoggedInUserGets200OnDoGet() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_B", user);
    request.setSession(session);
    servlet.doPost(request, response);
    int status = response.getStatus();
    assertEquals(200, status);
  }

  /**
   * <p> Tests That the user will received a 302 status on doGet if they are logged out  </p>
   */
  @Test
  public void TestLoggedOutUserGets302OnDoGet() throws ServletException, IOException {
    request.setSession(session);
    servlet.doPost(request, response);
    String status = response.getContentAsString();
    assertEquals("-1", status);

  }

  /**
   * <p> Test that the deactivation servlet can deactivate a budget </p>
   */
  @Test
  public void TestDeactivateCanDeactivate() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG");
    session.setAttribute("User_B", user);
    request.setSession(session);
    request.setParameter("budgetid", "VsbynqpfgtPQwligPgtWKQrSIfFHooEjcNkT");
    request.setParameter("mode", "0");
    servlet.doPost(request, response);
    String status = response.getContentAsString();
    assertEquals("1", status);
  }

  /**
   * <p> Test that a user in the wrong role will get a 302 on a doGet </p>
   */
  @Test
  public void TestWrongRoleGets302onDoGet() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("Wrongrole");
    user.setRoles(roles);
    session.setAttribute("User_B", user);
    request.setSession(session);
    servlet.doPost(request, response);
    String status = response.getContentAsString();
    assertEquals("-1", status);
  }

  /**
   * <p> Test that the deactivate servlet will fail if the budget is already insactive. </p>
   */
  @Test
  public void TestDeactivateFailIfAlreadyFalse() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG");
    session.setAttribute("User_B", user);
    request.setSession(session);
    request.setParameter("budgetid", "UpHSlyPcbdxGIOyhZjQcAomXFmNxlwWeflYD");
    request.setParameter("mode", "0");
    servlet.doPost(request, response);
    String status = response.getContentAsString();
    assertEquals("0", status);
  }

  /**
   * <p> Test something, needs more specificity </p>
   */
  @Test
  public void TestDeActivateCanFailIfKeyDoesNotExist() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("AApfWxZUBCbqtdngpApNUBMxKxgaVkEonxxx");
    session.setAttribute("User_B", user);
    request.setSession(session);
    request.setParameter("budgetid", "DLpfWxZUBCbqtdngpApNUBMxKxgaVkEonxxx");
    request.setParameter("mode", "0");
    servlet.doPost(request, response);
    String status = response.getContentAsString();
    assertEquals("-5", status);
  }

  /**
   * <p> Test something, needs more specificity </p>
   */
  @Test
  public void TestInvalidBudgetIDGivesResultOfNeg3() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_B", user);
    request.setSession(session);
    request.setParameter("budgetid", "xxxxxxxxxxxxx");
    request.setParameter("mode", "1");
    servlet.doPost(request, response);
    String status = response.getContentAsString();
    assertEquals("-3", status);
  }

  @Test
  public void TestDeactivateCanFailIfAlreadyFalse() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG");
    session.setAttribute("User_B", user);
    request.setSession(session);
    request.setParameter("budgetid", "xxxxxxxxxxxxx");
    request.setParameter("mode", "1");
    servlet.doPost(request, response);
    String status = response.getContentAsString();
    assertEquals("-3", status);
  }

  /**
   * <p> Test that the activation servlet can active a budget </p>
   */
  @Test
  public void TestactivateCanActivate() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG");
    session.setAttribute("User_B", user);
    request.setSession(session);
    request.setParameter("budgetid", "UpHSlyPcbdxGIOyhZjQcAomXFmNxlwWeflYD");
    request.setParameter("mode", "1");
    servlet.doPost(request, response);
    String status = response.getContentAsString();
    assertEquals("1", status);
  }

  /**
   * <p> Test that the activation servlet can fail if the budget is already active </p>
   */
  @Test
  public void TestActivateFailIfAlreadyTrue() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG");
    session.setAttribute("User_B", user);
    request.setSession(session);
    request.setParameter("budgetid", "VsbynqpfgtPQwligPgtWKQrSIfFHooEjcNkT");
    request.setParameter("mode", "1");
    servlet.doPost(request, response);
    String status = response.getContentAsString();
    assertEquals("0", status);
  }

  /**
   * <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new ActivateBudgetServlet();
    servlet.init();
  }

}

