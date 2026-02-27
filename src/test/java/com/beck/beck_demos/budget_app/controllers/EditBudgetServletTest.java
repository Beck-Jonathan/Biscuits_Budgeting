package com.beck.beck_demos.budget_app.controllers;


import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;
import com.beck.beck_demos.budget_app.controllers.EditBudgetServlet;
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

public class EditBudgetServletTest {
  private static final String PAGE="WEB-INF/Budget_app/Edit_Budget.jsp";
  EditBudgetServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new EditBudgetServlet();
    MockServletContext servletContext = new MockServletContext();
    MockServletConfig servletConfig = new MockServletConfig(servletContext, "Edit_Budget");
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
  public void TestLoggedInUserGets302OnDoGetWithNoBugetIDSet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG");
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }

  /**
   <p> Test That a logged in user gets a 200 status on doPost </p>
   */
  @Test
  public void TestLoggedInUserGets302nDoPostWithNoBudgetSet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG");
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
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
   <p> Tests That the user will received a 302 status on doPost if they are logged out  </p>
   */
  @Test
  public void TestLoggedOutUserGets302OnDoPost() throws ServletException, IOException{
    request.setSession(session);
    servlet.doPost(request,response);
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
    user.setUser_ID("WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG");
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
   <p> Test that a user in the wrong role will get a 302 on a doPost </p>
   */
  @Test
  public void TestWrongRoleGets302onDoPost() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("WrongRole");
    user.setRoles(roles);
    user.setUser_ID("WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG");
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
    String redirect_link = response.getRedirectedUrl();
    String desired_redirect = "budget_home";
    assertEquals(desired_redirect,redirect_link);
  }

  /**
   <p> Test that a logged in user is able to retrieve a specific one of the Budget objects. </p>
   */
  @Test
  public void testGetOneBudgetGetsOneBudget_id() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG");
    session.setAttribute("User_B",user);


    String Budget_id= "DLpfWxZUBCbqtdngpApNUBMxKxgaVkEondmy";
    request.setParameter("budgetid",Budget_id);
    request.setSession(session);
    servlet.doGet(request,response);
    Budget_VM Budget = (Budget_VM) session.getAttribute("Budget");
    assertNotNull(Budget);
    assertEquals(Budget_id,Budget.getbudget_id());
  }

  /**
   <p> Test that getting one Budget can fail. </p>
   */
  @Test
  public void testGetOneBudgetCanFailAndUserIsRedirected() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG");
    session.setAttribute("User_B",user);

    String Budget_id= "DLpfWxZUBCbqtdngpApNUBMxKxgaVkEondxx";
    request.setParameter("budgetid",Budget_id);
    request.setSession(session);
    servlet.doGet(request,response);
    Budget_VM Budget = (Budget_VM) session.getAttribute("Budget");
    assertNull(Budget);
    assertEquals(302,response.getStatus());
  }

  /**
   <p> Test that we are able to update Budget objects if there are no errors in the input fields </p>
   */
  @Test
  public void TestUpdateCanAddWithNoErrorsAndRedirects() throws ServletException, IOException, ParseException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG");
    session.setAttribute("User_B",user);
    request.setSession(session);
//to set the old Budget
    Budget Budget = new Budget();
    Budget.setbudget_id("DLpfWxZUBCbqtdngpApNUBMxKxgaVkEondmy");
    Budget.setuser_id("ELpfWxZUBCbqtdngpApNUBMxKxgaVkEondmy");
    Budget.setname("testBudget");
    Budget.setdetails("testBudget");
    Budget.setstart_date(LocalDate.now());
    Budget.setlimit_amount(23.2d);
    Budget.setcurrency_code_id("USD");
    Budget.setis_active(true);

    session.setAttribute("Budget",Budget);
//create a new Budgets parameters

    request.setParameter("inputBudgetname","TestValue");
    request.setParameter("inputBudgetdetails","TestValue");
    request.setParameter("inputBudgetstart_date","2026-11-06");
    request.setParameter("inputBudgetlimit_amount","243.6");
    request.setParameter("inputBudgetcurrency_code_id","TST");
    request.setParameter("inputBudgetis_active","true");

    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Budget_Updated = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Budget_Updated);
    assertEquals("Budget updated",Budget_Updated);
    assertNotEquals("",Budget_Updated);
  }

  /**
   <p> Test that error messages are sent for each field for addingBudget objects. That is to say, testing serverside validation </p>
   */
  @Test
  public void TestUpdateHasErrorsForEachFiledAndKeepsOnSamePage() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG");
    session.setAttribute("User_B",user);

    Budget Budget = new Budget();
    Budget.setbudget_id("DLpfWxZUBCbqtdngpApNUBMxKxgaVkEondmy");
    session.setAttribute("Budget",Budget);
    request.setSession(session);
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");

    String nameError = results.get("Budgetnameerror");
    String detailsError = results.get("Budgetdetailserror");
    String start_dateError = results.get("Budgetstart_dateerror");
    String limit_amountError = results.get("Budgetlimit_amounterror");
    String currency_code_idError = results.get("Budgetcurrency_code_iderror");


    assertNotEquals("",nameError);
    assertNotNull(nameError);
    assertNotEquals("",detailsError);
    assertNotNull(detailsError);
    assertNotEquals("",start_dateError);
    assertNotNull(start_dateError);
    assertNotEquals("",limit_amountError);
    assertNotNull(limit_amountError);
    assertNotEquals("",currency_code_idError);
    assertNotNull(currency_code_idError);

    assertEquals(200,responseStatus);
  }

  /**
   <p> Test that a duplicate primary key will get caught when trying to update Budget objects. </p>
   */
  @Test
  public void testUpdateCanReturnZero() throws ServletException, IOException, ParseException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG");
    session.setAttribute("User_B",user);
    request.setSession(session);
//to set the old Budget
    Budget Budget = new Budget();
    Budget.setbudget_id("DLpfWxZUBCbqtdngpApNUBMxKxgaVkEondmy");
    Budget.setuser_id("DUPLICATEDUPLICATEDUPLICATEDUPLICATE");
    Budget.setname("DUPLICATE");
    Budget.setdetails("DUPLICATE");
    Budget.setstart_date(LocalDate.now());
    Budget.setlimit_amount(123.12);
    Budget.setcurrency_code_id("DUP");
    Budget.setis_active(true);
    Budget.setcreated_at(LocalDate.now());
    Budget.setupdated_at(LocalDate.now());
    session.setAttribute("Budget",Budget);
//create a new Budget parameters

    request.setParameter("inputBudgetname","DUPLICATE");
    request.setParameter("inputBudgetdetails","DUPLICATE");
    request.setParameter("inputBudgetstart_date","2023-01-06 ");
    request.setParameter("inputBudgetlimit_amount","243.6");
    request.setParameter("inputBudgetcurrency_code_id","DUP");
    request.setParameter("inputBudgetis_active","true");

    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Budget_Updated = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Budget_Updated);
    assertEquals("Budget Not Updated",Budget_Updated);
    assertNotEquals("",Budget_Updated);
  }

  /**
   <p> Tests That the user will received a error messages for each incorrectly filled out field on the form. </p>
   */
  @Test
  public void testUpdateCanThrowSQLException() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG");
    session.setAttribute("User_B",user);
    request.setSession(session);
//to set the old Budget
    Budget Budget = new Budget();
    Budget.setbudget_id("DLpfWxZUBCbqtdngpApNUBMxKxgaVkEondmy");
    Budget.setuser_id("EXCEPTIONEXCEPTIONEXCEPTIONEXCEPTION");
    Budget.setname("EXCEPTION");
    Budget.setdetails("EXCEPTION");
    Budget.setcurrency_code_id("EXC");
    Budget.setis_active(true);
    session.setAttribute("Budget",Budget);
//create a new Budgets parameters

    request.setParameter("inputBudgetname","EXCEPTION");
    request.setParameter("inputBudgetdetails","EXCEPTION");
    request.setParameter("inputBudgetstart_date","2026-11-06");
    request.setParameter("inputBudgetlimit_amount","243.6");
    request.setParameter("inputBudgetcurrency_code_id","EXC");
    request.setParameter("inputBudgetis_active","true");

    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Budget_Updated = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Budget_Updated);
    assertNotEquals("",Budget_Updated);
    assertEquals("Budget Not Updated",Budget_Updated);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new EditBudgetServlet();
    servlet.init();
  }

}

