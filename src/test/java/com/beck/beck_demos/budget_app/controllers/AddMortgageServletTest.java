package com.beck.beck_demos.budget_app.controllers;
import java.io.IOException;
import java.util.*;
import com.beck.beck_demos.budget_app.data_fakes.MortgageDAO_Fake;
import com.beck.beck_demos.budget_app.models.Mortgage;
import com.beck.beck_demos.budget_app.models.Mortgage_VM;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

public class AddMortgageServletTest {
  private static final String PAGE="WEB-INF/budget_app/Add_Mortgage.jsp";
  AddMortgageServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new AddMortgageServlet();
    servlet.init(new MortgageDAO_Fake());
    request =  new MockHttpServletRequest();
    response = new MockHttpServletResponse();
    session = new MockHttpSession();
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
   <p> Test that error messages are sent for each field for addingMortgage objects. That is to say, testing serverside validation </p>
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

    String User_IDError = results.get("mortgageUser_IDerror");
    String Present_ValueError = results.get("mortgagePresent_Valueerror");
    String Future_ValueError = results.get("mortgageFuture_Valueerror");
    String Interest_RateError = results.get("mortgageInterest_Rateerror");
    String Monthly_PaymentError = results.get("mortgageMonthly_Paymenterror");
    String Extra_PaymentError = results.get("mortgageExtra_Paymenterror");
    String Remaining_TermError = results.get("mortgageRemaining_Termerror");

    assertNotEquals("",User_IDError);
    assertNotNull(User_IDError);
    assertNotEquals("",Present_ValueError);
    assertNotNull(Present_ValueError);
    assertNotEquals("",Future_ValueError);
    assertNotNull(Future_ValueError);
    assertNotEquals("",Interest_RateError);
    assertNotNull(Interest_RateError);
    assertNotEquals("",Monthly_PaymentError);
    assertNotNull(Monthly_PaymentError);
    assertNotEquals("",Extra_PaymentError);
    assertNotNull(Extra_PaymentError);
    assertNotEquals("",Remaining_TermError);
    assertNotNull(Remaining_TermError);
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
    user.setUser_ID(36);
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("inputmortgageMortgage_ID","3e6abc9f-90de-4929-8a16-e2d301db1ff9");
    request.setParameter("inputmortgageUser_ID","406");
    request.setParameter("inputmortgagePresent_Value","243.6");
    request.setParameter("inputmortgageFuture_Value","243.6");
    request.setParameter("inputmortgageInterest_Rate","243.6");
    request.setParameter("inputmortgageMonthly_Payment","243.6");
    request.setParameter("inputmortgageExtra_Payment","243.6");
    request.setParameter("inputmortgageRemaining_Term","406");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Mortgage_Added = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Mortgage_Added);
    assertEquals("Mortgage Added",Mortgage_Added);
    assertNotEquals("",Mortgage_Added);
  }

  /**
   <p> Tests That the user will received a error messages for each incorrectly filled out field on the form. </p>
   */
  @Test
  public void testExceptionKeyThrowsException() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID(36);
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("inputmortgageMortgage_ID","EXCEPTION");
    request.setParameter("inputmortgageUser_ID","406");
    request.setParameter("inputmortgagePresent_Value","243.6");
    request.setParameter("inputmortgageFuture_Value","243.6");
    request.setParameter("inputmortgageInterest_Rate","243.6");
    request.setParameter("inputmortgageMonthly_Payment","243.6");
    request.setParameter("inputmortgageExtra_Payment","243.6");
    request.setParameter("inputmortgageRemaining_Term","406");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Mortgage_Added = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Mortgage_Added);
    assertEquals("Mortgage Not Added",Mortgage_Added);
    assertNotEquals("",Mortgage_Added);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }

  /**
   <p> Test that Mortgage objects with duplicate primary keys don't get added, and proper error handling exists. </p>
   */
  @Test
  public void testDuplicateKeyReturnsZero() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID(36);
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("inputmortgageMortgage_ID","DUPLICATE");
    request.setParameter("inputmortgageUser_ID","406");
    request.setParameter("inputmortgagePresent_Value","243.6");
    request.setParameter("inputmortgageFuture_Value","243.6");
    request.setParameter("inputmortgageInterest_Rate","243.6");
    request.setParameter("inputmortgageMonthly_Payment","243.6");
    request.setParameter("inputmortgageExtra_Payment","243.6");
    request.setParameter("inputmortgageRemaining_Term","406");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Mortgage_Added = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Mortgage_Added);
    assertEquals("Mortgage Not Added",Mortgage_Added);
    assertNotEquals("",Mortgage_Added);
  }

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new AddMortgageServlet();
    servlet.init();
  }

}

