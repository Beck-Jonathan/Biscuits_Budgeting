package com.beck.beck_demos.budget_app.controllers;


import java.io.IOException;
import java.util.*;
import com.beck.beck_demos.budget_app.data_fakes.BudgetDAO_Fake;
import com.beck.beck_demos.budget_app.models.Budget ;
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

public class AddBudgetServletTest {
  private static final String PAGE="WEB-INF/budget_app/Add_budget.jsp";
  AddBudgetServlet  servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new AddBudgetServlet ();
    MockServletContext servletContext = new MockServletContext();
    MockServletConfig servletConfig = new MockServletConfig(servletContext, "Add_budget ");
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
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(200,status);
  }

  /**
   <p> Tests That the user will received a 200 status on doGet if they are logged in </p>
   */
  @Test
  public void TestLoggedInUserGets200OnDoPostWithNoFieldsSet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doPost(request,response);
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
   <p> Test that error messages are sent for each field for addingbudget  objects. That is to say, testing serverside validation </p>
   */
  @Test
  public void TestAddHasErrorsForEachFieldAndKeepsOnSamePage() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");

    String nameError = results.get("budgetnameerror");
    String detailsError = results.get("budgetdetailserror");
    String start_dateError = results.get("budgetstart_dateerror");
    String limit_amountError = results.get("budgetlimit_amounterror");
    String currency_code_idError = results.get("budgetcurrency_code_iderror");


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
   <p> Tests That We can add to the database if all input fields are validated  </p>
   */
  @Test
  public void TestAddCanAddWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("9540263f-5ffd-4d61-9c48-2c6c96ffc06e");
    session.setAttribute("User_B",user);
    request.setSession(session);

    request.setParameter("inputbudgetname","TestValue");
    request.setParameter("inputbudgetdetails","TestValue");
    request.setParameter("inputbudgetstart_date","1997-03-15");
    request.setParameter("inputbudgetlimit_amount","243.6");
    request.setParameter("inputbudgetcurrency_code_id","USD");
    request.setParameter("inputbudgetis_active","true");

    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String budget_Added = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(budget_Added);
    assertEquals("budget Added",budget_Added);
    assertNotEquals("",budget_Added);
  }

  /**
   <p> Tests That the user will received a error messages for each incorrectly filled out field on the form. </p>
   */
  @Test
  public void testExceptionKeyThrowsException() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("9540263f-5ffd-4d61-9c48-2c6c96ffc06e");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);

    request.setParameter("inputbudgetname","EXCEPTION");
    request.setParameter("inputbudgetdetails","EXCEPTION");
    request.setParameter("inputbudgetstart_date","1997-03-15");
    request.setParameter("inputbudgetlimit_amount","243.6");
    request.setParameter("inputbudgetcurrency_code_id","USD");
    request.setParameter("inputbudgetis_active","true");

    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String budget_Added = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(budget_Added);
    assertEquals("budget Not Added",budget_Added);
    assertNotEquals("",budget_Added);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }

  /**
   <p> Test that budget  objects with duplicate primary keys don't get added, and proper error handling exists. </p>
   */
  @Test
  public void testDuplicateKeyReturnsZero() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("9540263f-5ffd-4d61-9c48-2c6c96ffc06e");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("inputbudgetname","DUPLICATE");
    request.setParameter("inputbudgetdetails","DUPLICATE");
    request.setParameter("inputbudgetstart_date","1997-03-15");
    request.setParameter("inputbudgetlimit_amount","243.6");
    request.setParameter("inputbudgetcurrency_code_id","USD");
    request.setParameter("inputbudgetis_active","true");

    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String budget_Added = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(budget_Added);
    assertEquals("budget Not Added",budget_Added);
    assertNotEquals("",budget_Added);
  }

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new AddBudgetServlet ();
    servlet.init();
  }

}

