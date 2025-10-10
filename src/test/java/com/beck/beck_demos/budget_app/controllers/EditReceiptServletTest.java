package com.beck.beck_demos.budget_app.controllers;
import java.io.IOException;
import java.util.*;
import com.beck.beck_demos.budget_app.data_fakes.ReceiptDAO_Fake;
import com.beck.beck_demos.budget_app.data_fakes.TransactionDAO_Fake;
import com.beck.beck_demos.budget_app.models.Receipt;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

public class EditReceiptServletTest {
  private static final String PAGE="WEB-INF/budget_app/Edit_Receipt.jsp";
  EditReceiptServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new EditReceiptServlet();
    servlet.init(new ReceiptDAO_Fake(),new TransactionDAO_Fake());
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
  public void TestLoggedInUserGets302OnDoGetWithNoIDSet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID(36);
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
  public void TestLoggedInUserGets302nDoPostWithNoReceiptSet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID(36);
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
    user.setUser_ID(36);
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
    user.setUser_ID(36);
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
   <p> Test that a logged in user is able to retrieve a specific one of the Receipt objects. </p>
   */
  @Test
  public void testGetOneReceiptGetsOneReceipt_ID() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID(36);
    user.setRoles(roles);
    session.setAttribute("User_B",user);

    String Receipt_ID= "kPaFZnoBwSWFxQyrPiBWMACLTrPFStleSVwt";
    request.setParameter("receiptid",Receipt_ID);
    request.setSession(session);
    servlet.doGet(request,response);
    Receipt receipt = (Receipt) session.getAttribute("receipt");
    assertNotNull(receipt);
    assertEquals(Receipt_ID,receipt.getReceipt_ID());
  }

  /**
   <p> Test that getting one Receipt can fail. </p>
   */
  @Test
  public void testGetOneReceiptCanFailAndUserIsRedirected() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID(36);
    user.setRoles(roles);
    session.setAttribute("User_B",user);

    String Receipt_ID= null;
    request.setParameter("receiptid",Receipt_ID);
    request.setSession(session);
    servlet.doGet(request,response);
    Receipt receipt = (Receipt) session.getAttribute("receipt");
    assertNull(receipt);
    assertEquals(302,response.getStatus());
  }

  /**
   <p> Test that we are able to update Receipt objects if there are no errors in the input fields </p>
   */
  @Test
  public void TestUpdateCanAddWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID(36);
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
//to set the old Receipt
    Receipt receipt = new Receipt();
    receipt.setReceipt_ID("kPaFZnoBwSWFxQyrPiBWMACLTrPFStleSVwt");
    receipt.setTransaction_ID("kPaFZnoBwSWFxQyrPiBWMACLTrPFStleSVwt");
    receipt.setImage_Link("testReceipt");
    receipt.setName("testReceipt");
    receipt.setDescription("testReceipt");
    session.setAttribute("receipt",receipt);
//create a new Receipts parameters
    request.setParameter("inputreceiptReceipt_ID","kPaFZnoBwSWFxQyrPiBWMACLTrPFStleSVwt");
    request.setParameter("inputreceiptTransaction_ID","kPaFZnoBwSWFxQyrPiBWMACLTrPFStleSVwt");
    request.setParameter("inputreceiptImage_Link","TestValue");
    request.setParameter("inputreceiptName","TestValue");
    request.setParameter("inputreceiptDescription","TestValue");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Receipt_Updated = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Receipt_Updated);
    assertEquals("Receipt updated",Receipt_Updated);
    assertNotEquals("",Receipt_Updated);
  }

  /**
   <p> Test that error messages are sent for each field for addingReceipt objects. That is to say, testing serverside validation </p>
   */
  @Test
  public void TestUpdateHasErrorsForEachFiledAndKeepsOnSamePage() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID(36);
    user.setRoles(roles);
    session.setAttribute("User_B",user);

    Receipt receipt = new Receipt();
    session.setAttribute("receipt",receipt);
    request.setSession(session);
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");

    String Transaction_IDError = results.get("receiptTransaction_IDerror");
    String Image_LinkError = results.get("receiptImage_Linkerror");
    String NameError = results.get("receiptNameerror");
    String DescriptionError = results.get("receiptDescriptionerror");


    assertNotEquals("",Transaction_IDError);
    assertNotNull(Transaction_IDError);
    assertNotEquals("",Image_LinkError);
    assertNotNull(Image_LinkError);
    assertNotEquals("",NameError);
    assertNotNull(NameError);
    assertNotEquals("",DescriptionError);
    assertNotNull(DescriptionError);
    assertEquals(200,responseStatus);
  }

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new EditReceiptServlet();
    servlet.init();
  }

  /**
   <p> Test that a duplicate primary key will get caught when trying to update Receipt objects. </p>
   */
  @Test
  public void testUpdateCanReturnZero() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID(36);
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
//to set the old Receipt
    Receipt receipt = new Receipt();
    receipt.setReceipt_ID("DUPLICATEDUPLICATEDUPLICATEDUPLICATE");
    receipt.setTransaction_ID("DUPLICATEDUPLICATEDUPLICATEDUPLICATE");
    receipt.setImage_Link("DUPLICATE");
    receipt.setName("DUPLICATE");
    receipt.setDescription("DUPLICATE");
    session.setAttribute("receipt",receipt);
//create a new Receipt parameters
    request.setParameter("inputreceiptReceipt_ID","DUPLICATEDUPLICATEDUPLICATEDUPLICATE");
    request.setParameter("inputreceiptTransaction_ID","DUPLICATEDUPLICATEDUPLICATEDUPLICATE");
    request.setParameter("inputreceiptImage_Link","DUPLICATE");
    request.setParameter("inputreceiptName","DUPLICATE");
    request.setParameter("inputreceiptDescription","DUPLICATE");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Receipt_Updated = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Receipt_Updated);
    assertEquals("Receipt Not Updated",Receipt_Updated);
    assertNotEquals("",Receipt_Updated);
  }

  /**
   <p> Tests That the user will received a error messages for each incorrectly filled out field on the form. </p>
   */
  @Test
  public void testUpdateCanThrowSQLException() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID(36);
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
//to set the old Receipt
    Receipt receipt = new Receipt();
    receipt.setReceipt_ID("EXCEPTIONEXCEPTIONEXCEPTIONEXCEPTION");
    receipt.setTransaction_ID("EXCEPTIONEXCEPTIONEXCEPTIONEXCEPTION");
    receipt.setImage_Link("EXCEPTION");
    receipt.setName("EXCEPTION");
    receipt.setDescription("EXCEPTION");
    session.setAttribute("receipt",receipt);
//create a new Receipts parameters
    request.setParameter("inputreceiptReceipt_ID","EXCEPTIONEXCEPTIONEXCEPTIONEXCEPTION");
    request.setParameter("inputreceiptTransaction_ID","EXCEPTIONEXCEPTIONEXCEPTIONEXCEPTION");
    request.setParameter("inputreceiptImage_Link","EXCEPTION");
    request.setParameter("inputreceiptName","EXCEPTION");
    request.setParameter("inputreceiptDescription","EXCEPTION");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Receipt_Updated = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Receipt_Updated);
    assertNotEquals("",Receipt_Updated);
    assertEquals("Receipt Not Updated",Receipt_Updated);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }

}

