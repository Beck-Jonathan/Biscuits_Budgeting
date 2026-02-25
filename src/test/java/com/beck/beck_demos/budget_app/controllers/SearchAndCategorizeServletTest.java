package com.beck.beck_demos.budget_app.controllers;

import java.io.IOException;
import java.util.*;

import com.beck.beck_demos.budget_app.data_fakes.CategoryDAO_Fake;
import com.beck.beck_demos.budget_app.data_fakes.TransactionDAO_Fake;
import com.beck.beck_demos.budget_app.models.Transaction;
import com.beck.beck_demos.budget_app.models.Transaction_VM;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

class SearchAndCategorizeServletTest {
  private static final String PAGE="WEB-INF/WFTDA_debug/Edit_Transaction.jsp";
  SearchAndCategorizeServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;


  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new SearchAndCategorizeServlet();
    servlet.init(new TransactionDAO_Fake(), new CategoryDAO_Fake());
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
    user.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(200,status);
  }

  /**
   <p> Test That a logged in user gets a 200 status on doPost </p>
   */
  @Test
  public void TestLoggedInUserStaysOnSamePage() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
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
    user.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
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
    user.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }

  /**
   <p> Test that a logged in user is able to retreive a specific one of the Transaction objects. </p>
   */
  @Test
  public void testSearchCanRetreiveTransactions() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("618052e9-c69b-4d9b-880e-e22e4a970bd6");
    session.setAttribute("User_B",user);
    request.setSession(session);
    String Query= null;
    request.setParameter("query","Casey");

    servlet.doGet(request,response);
    List<Transaction_VM> transactions = (List<Transaction_VM>) request.getAttribute("Transactions");
    assertNotNull(transactions);
    assertEquals(3,transactions.size());
  }

  /**
   <p> Test that getting one Transaction can fail. </p>
   */
  @Test
  public void testSearchCanHandleZeroSearchResults() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    session.setAttribute("User_B",user);
    request.setSession(session);
    String Query= null;
    request.setParameter("query","Quick");

    servlet.doGet(request,response);
    List<Transaction_VM> transactions = (List<Transaction_VM>) request.getAttribute("Transactions");
    assertNotNull(transactions);
    assertEquals(0,transactions.size());
  }

  @Test
  public void testInvalidQueryGetsProperMessageOnDoPost() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    session.setAttribute("User_B",user);
    request.setSession(session);
    String Query= null;
    request.setParameter("query","q");

    servlet.doPost(request,response);
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    List<Transaction_VM> transactions = (List<Transaction_VM>) request.getAttribute("Transactions");
    assertNull(transactions);
    String inputError = results.get("inputError");
    assertNotEquals("",inputError);
    assertNotNull(inputError);
  }

  @Test
  public void testInvalidQueryGetsProperMessageOnDoGet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    session.setAttribute("User_B",user);
    request.setSession(session);
    String Query= null;
    request.setParameter("query","q");

    servlet.doGet(request,response);
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    List<Transaction_VM> transactions = (List<Transaction_VM>) request.getAttribute("Transactions");
    assertNull(transactions);
    String inputError = results.get("inputError");
    assertNotEquals("",inputError);
    assertNotNull(inputError);
  }

  /**
   <p> Test that we are able to update Transaction objects if there are no errors in the input fields </p>
   */
  @Test
  public void TestUpdateCanAddWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("618052e9-c69b-4d9b-880e-e22e4a970bd6");
    session.setAttribute("User_B",user);
    session.setAttribute("search","Casey");
    request.setSession(session);
//to set the old Transaction

//create a new albums parameters
    request.setParameter("category","Gasoline");

    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    int Transaction_Updated = Integer.parseInt(results.get("updateCount"));
    assertEquals(3,Transaction_Updated);
    assertEquals(302,responseStatus);

  }

  /**
   <p> Test that error messages are sent for each field for addingTransaction objects. That is to say, testing serverside validation </p>
   */
  @Test
  public void TestUpdateHasErrorsForEachFiledAndKeepsOnSamePage() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String inputError = results.get("inputError");
    String Category_IDError = results.get("categoryError");

    assertNotEquals("",inputError);
    assertNotNull(inputError);
    assertNotEquals("",Category_IDError);
    assertNotNull(Category_IDError);

    assertEquals(200,responseStatus);
  }

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new SearchAndCategorizeServlet();
    servlet.init();
  }

  /**
   <p> Test that a duplicate primary key will get caught when trying to update Transaction objects. </p>
   */
  @Test
  public void testUpdateCanReturnZero() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    session.setAttribute("User_B",user);
    session.setAttribute("search","Kwik");
    request.setSession(session);
//to set the old Transaction

//create a new albums parameters
    request.setParameter("category","Gasoline");

    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    int Transaction_Updated = Integer.parseInt(results.get("updateCount"));
    assertEquals(0,Transaction_Updated);
    assertEquals(302,responseStatus);
  }

  /**
   <p> Tests That the user will received a error messages for each incorrectly filled out field on the form. </p>
   */
  @Test
  public void testUpdateCanThrowSQLExceptionOnDoPost() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    session.setAttribute("User_B",user);
    session.setAttribute("search","EXCEPTION");
    request.setSession(session);
//to set the old Transaction

//create a new albums parameters
    request.setParameter("category","EXCEPTION");

    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    int Transaction_Updated = Integer.parseInt(results.get("updateCount"));
    assertEquals(0,Transaction_Updated);
    assertEquals(302,responseStatus);
    String dbError = results.get("dbStatus");

    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Unable To Categorize",dbError);
  }

  @Test
  public void testUpdateCanThrowSQLExceptionOnDoGet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    session.setAttribute("User_B",user);
    request.setParameter("query","EXCEPTION");
    request.setSession(session);
//to set the old Transaction

//create a new albums parameters
    request.setParameter("category","EXCEPTION");

    servlet.doGet(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");

    assertEquals(200,responseStatus);
    String dbError = results.get("dbStatus");

    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Unable To Search",dbError);
  }



}


