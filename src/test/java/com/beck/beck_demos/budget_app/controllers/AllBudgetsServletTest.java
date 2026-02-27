package com.beck.beck_demos.budget_app.controllers;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.*;
import com.beck.beck_demos.budget_app.data_fakes.BudgetDAO_Fake;
import com.beck.beck_demos.budget_app.models.Budget;
import com.beck.beck_demos.budget_app.models.Budget_VM;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;
class AllBudgetsServletTest {


  private static final String PAGE="WEB-INF/budget_app/All_budget.jsp";
  AllBudgetsServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new AllBudgetsServlet();
    MockServletContext servletContext = new MockServletContext();
    MockServletConfig servletConfig = new MockServletConfig(servletContext, "All_budget");
    servlet.init(servletConfig);
    servlet.init(new BudgetDAO_Fake());
    request =  new MockHttpServletRequest(servletContext);
    response = new MockHttpServletResponse();
    session = new MockHttpSession(servletContext);
    rd = new MockRequestDispatcher(PAGE);
  }


  /**
   <p> tear down by setting all variablges to null. </p>
   */
  @AfterEach
  public void teardown(){
    servlet=null;
    request=null;
    response=null;
    session=null;
    rd=null;
  }

  /**
   <p> Tests That the user will received a 200 status on doGet if they are logged in </p>
   */
  @Test
  public void TestLoggedInUserGets200OnDoGet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("9540263f-5ffd-4d61-9c48-2c6c96ffc06e");
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(200,status);
  }

  /**
   <p> Tests That the user will received a 302 status on doGet if they are logged out  </p>
   */
  @Test
  public void TestLoggedOutUserGets302OnDoGet() throws ServletException, IOException{
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
    String redirect_link = response.getRedirectedUrl();
    String desired_redirect = "budget_home";
    assertEquals(desired_redirect,redirect_link);
    session = request.getSession(false);
    assertNull(session);
  }

  /**
   <p> Test that a user in the wrong role will get a 302 on a doGet </p>
   */
  @Test
  public void TestWrongRoleGets302onDoGet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("WrongRole");
    user.setRoles(roles);
    user.setUser_ID("9540263f-5ffd-4d61-9c48-2c6c96ffc06e");
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
    String redirect_link = response.getRedirectedUrl();
    String desired_redirect = "budget_home";
    assertEquals(desired_redirect,redirect_link);
  }

  /**
   <p> Test that a logged in user is able to retrieve all of the budget objects. </p>
   */
  @Test
  public void testLoggedInUserGetsAllbudgets() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG");
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doGet(request,response);
    List<Budget_VM> budgets = (List<Budget_VM>) request.getAttribute("budgets");
    int count = (int)  request.getAttribute("count");
    assertNotNull(budgets);
    assertEquals(4,budgets.size());
    assertEquals(4,count);
  }

  /**
   <p> Test that getting all budget can filter. </p>
   */
  @Test
  public void testLoggedInUserCanFilterbudgetsByuser_id() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG");
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doGet(request,response);
    List<Budget_VM> budgets = (List<Budget_VM>) request.getAttribute("budgets");
    assertNotNull(budgets);
    assertEquals(4,budgets.size());
    int count = (int)  request.getAttribute("count");
    assertEquals(4,count);
  }

  /**
   <p> Test that getting all budget can filter. </p>
   */
  @Test
  public void testLoggedInUserCanFilterbudgetsBycurrency_code_id() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG");
    session.setAttribute("User_B",user);
    String currencyCode = "fDC";
    request.setParameter("currency_code_id",currencyCode);

    request.setSession(session);
    servlet.doGet(request,response);
    List<Budget_VM> budgets = (List<Budget_VM>) request.getAttribute("budgets");
    assertNotNull(budgets);
    assertEquals(2,budgets.size());
    int count = (int)  request.getAttribute("count");
    assertEquals(2,count);
  }

  /**
   <p> Test that getting all budget can filter. </p>
   */
  @Test
  public void testLoggedInUserCanSearch() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG");
    session.setAttribute("User_B",user);
    request.setSession(session);
    String searchTerm = "abc";
    request.setParameter("search",searchTerm);

    servlet.doGet(request,response);
    List<Budget_VM> budgets = (List<Budget_VM>) request.getAttribute("budgets");
    assertNotNull(budgets);
    assertEquals(3,budgets.size());
    int count = (int) request.getAttribute("count");
    assertEquals(3,count);
  }

  /**
   <p> Test that getting all budget can filter. </p>
   */
  @Test
  public void testLoggedInUserCanSearchAndReturnZero() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG");
    session.setAttribute("User_B",user);
    request.setSession(session);
    String searchTerm = "banananana";
    request.setParameter("search",searchTerm);
    servlet.doGet(request,response);
    List<Budget_VM> budgets = (List<Budget_VM>) request.getAttribute("budgets");
    assertNotNull(budgets);
    assertEquals(0,budgets.size());
    int count = (int) request.getAttribute("count");
    assertEquals(0,count);
  }

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new AllBudgetsServlet();
    servlet.init();
  }

}

