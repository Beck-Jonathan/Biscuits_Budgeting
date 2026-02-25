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

public class EditMortgageServletTest {
  private static final String PAGE="WEB-INF/budget_app/Edit_Mortgage.jsp";
  EditMortgageServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new EditMortgageServlet();
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
  public void TestLoggedInUserGets302OnDoGetWtihNoMortgageSet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("af735dfc-22a9-4214-a8e5-fb8de2305700");
    user.setRoles(roles);
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
  public void TestLoggedInUserGets302nDoPostWithNoMortgageSet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("af735dfc-22a9-4214-a8e5-fb8de2305700");
    user.setRoles(roles);
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
    user.setUser_ID("af735dfc-22a9-4214-a8e5-fb8de2305700");
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
    user.setUser_ID("af735dfc-22a9-4214-a8e5-fb8de2305700");
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
   <p> Test that a logged in user is able to retrieve a specific one of the Mortgage objects. </p>
   */
  @Test
  public void testGetOneMortgageGetsOneMortgage_ID() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("af735dfc-22a9-4214-a8e5-fb8de2305700");
    user.setRoles(roles);
    session.setAttribute("User_B",user);

    String Mortgage_ID= "sRLMssDNRsUaetFJLmitlSLsspKHooQnZyQd";
    request.setParameter("mortgageid",Mortgage_ID);
    request.setSession(session);
    servlet.doGet(request,response);
    Mortgage mortgage = (Mortgage) session.getAttribute("mortgage");
    assertNotNull(mortgage);
    assertEquals(Mortgage_ID,mortgage.getMortgage_ID());
  }

  /**
   <p> Test that getting one Mortgage can fail. </p>
   */
  @Test
  public void testGetOneMortgageCanFailAndUserIsRedirected() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("af735dfc-22a9-4214-a8e5-fb8de2305700");
    user.setRoles(roles);
    session.setAttribute("User_B",user);

    String Mortgage_ID= null;
    request.setParameter("mortgageid",Mortgage_ID);
    request.setSession(session);
    servlet.doGet(request,response);
    Mortgage_VM mortgage = (Mortgage_VM) session.getAttribute("mortgage");
    assertNull(mortgage);
    assertEquals(302,response.getStatus());
  }

  /**
   <p> Test that we are able to update Mortgage objects if there are no errors in the input fields </p>
   */
  @Test
  public void TestUpdateCanAddWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("af735dfc-22a9-4214-a8e5-fb8de2305700");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
//to set the old Mortgage
    Mortgage mortgage = new Mortgage();
    mortgage.setMortgage_ID("xfJsxVBDDqBaKBFAgDhQACTFREWwjVxILdpW");
    mortgage.setUser_ID("af735dfc-22a9-4214-a8e5-fb8de2305700");;
    mortgage.setNickname("testMortgage");
    mortgage.setPresent_Value(23.2d);
    mortgage.setFuture_Value(23.2d);
    mortgage.setInterest_Rate(23.2d);
    mortgage.setMonthly_Payment(23.2d);
    mortgage.setExtra_Payment(23.2d);
    mortgage.setRemaining_Term(43);
    session.setAttribute("mortgage",mortgage);
//create a new Mortgages parameters
    request.setParameter("inputmortgageMortgage_ID","xfJsxVBDDqBaKBFAgDhQACTFREWwjVxILdpW");
    request.setParameter("inputmortgageUser_ID","406");
    request.setParameter("inputmortgageNickname","TestValue");
    request.setParameter("inputmortgagePresent_Value","243.6");
    request.setParameter("inputmortgageFuture_Value","243.6");
    request.setParameter("inputmortgageInterest_Rate","243.6");
    request.setParameter("inputmortgageMonthly_Payment","243.6");
    request.setParameter("inputmortgageExtra_Payment","243.6");
    request.setParameter("inputmortgageRemaining_Term","406");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Mortgage_Updated = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Mortgage_Updated);
    assertEquals("Mortgage updated",Mortgage_Updated);
    assertNotEquals("",Mortgage_Updated);
  }

  /**
   <p> Test that error messages are sent for each field for addingMortgage objects. That is to say, testing serverside validation </p>
   */
  @Test
  public void TestUpdateHasErrorsForEachFiledAndKeepsOnSamePage() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");

    user.setRoles(roles);
    session.setAttribute("User_B",user);

    Mortgage mortgage = new Mortgage();
    session.setAttribute("mortgage",mortgage);
    request.setSession(session);
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");

    String User_IDError = results.get("mortgageUser_IDerror");
    String NicknameError = results.get("mortgageNicknameerror");
    String Present_ValueError = results.get("mortgagePresent_Valueerror");
    String Future_ValueError = results.get("mortgageFuture_Valueerror");
    String Interest_RateError = results.get("mortgageInterest_Rateerror");
    String Monthly_PaymentError = results.get("mortgageMonthly_Paymenterror");
    String Extra_PaymentError = results.get("mortgageExtra_Paymenterror");
    String Remaining_TermError = results.get("mortgageRemaining_Termerror");

    assertNotEquals("",User_IDError);
    assertNotNull(User_IDError);
    assertNotEquals("",NicknameError);
    assertNotNull(NicknameError);
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
   <p> Test that a duplicate primary key will get caught when trying to update Mortgage objects. </p>
   */
  @Test
  public void testUpdateCanReturnZero() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("af735dfc-22a9-4214-a8e5-fb8de2305700");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
//to set the old Mortgage
    Mortgage mortgage = new Mortgage();
    mortgage.setMortgage_ID("DUPLICATEDUPLICATEDUPLICATEDUPLICATE");
    mortgage.setUser_ID("af735dfc-22a9-4214-a8e5-fb8de2305700");;
    mortgage.setNickname("DUPLICATE");
    mortgage.setPresent_Value(100000d);
    mortgage.setFuture_Value(83930.05d);
    mortgage.setInterest_Rate(3.5d);
    mortgage.setMonthly_Payment(1000d);
    mortgage.setExtra_Payment(0d);
    mortgage.setRemaining_Term(43);
    session.setAttribute("mortgage",mortgage);
//create a new Mortgage parameters
    request.setParameter("inputmortgageMortgage_ID","DUPLICATEDUPLICATEDUPLICATEDUPLICATE");
    request.setParameter("inputmortgageUser_ID","406");
    request.setParameter("inputmortgageNickname","DUPLICATE");
    request.setParameter("inputmortgagePresent_Value","243.6");
    request.setParameter("inputmortgageFuture_Value","243.6");
    request.setParameter("inputmortgageInterest_Rate","243.6");
    request.setParameter("inputmortgageMonthly_Payment","243.6");
    request.setParameter("inputmortgageExtra_Payment","243.6");
    request.setParameter("inputmortgageRemaining_Term","406");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Mortgage_Updated = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Mortgage_Updated);
    assertEquals("Mortgage Not Updated",Mortgage_Updated);
    assertNotEquals("",Mortgage_Updated);
  }

  /**
   <p> Tests That the user will received a error messages for each incorrectly filled out field on the form. </p>
   */
  @Test
  public void testUpdateCanThrowSQLException() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("af735dfc-22a9-4214-a8e5-fb8de2305700");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
//to set the old Mortgage
    Mortgage mortgage = new Mortgage();
    mortgage.setMortgage_ID("EXCEPTIONEXCEPTIONEXCEPTIONEXCEPTION");
    mortgage.setUser_ID("af735dfc-22a9-4214-a8e5-fb8de2305700");;
    mortgage.setNickname("EXCEPTION");
    mortgage.setRemaining_Term(43);
    session.setAttribute("mortgage",mortgage);
//create a new Mortgages parameters
    request.setParameter("inputmortgageMortgage_ID","EXCEPTION");
    request.setParameter("inputmortgageUser_ID","406");
    request.setParameter("inputmortgageNickname","EXCEPTION");
    request.setParameter("inputmortgagePresent_Value","243.6");
    request.setParameter("inputmortgageFuture_Value","243.6");
    request.setParameter("inputmortgageInterest_Rate","243.6");
    request.setParameter("inputmortgageMonthly_Payment","243.6");
    request.setParameter("inputmortgageExtra_Payment","243.6");
    request.setParameter("inputmortgageRemaining_Term","406");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Mortgage_Updated = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Mortgage_Updated);
    assertNotEquals("",Mortgage_Updated);
    assertEquals("Mortgage Not Updated",Mortgage_Updated);
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
    servlet = new EditMortgageServlet();
    servlet.init();
  }

}

