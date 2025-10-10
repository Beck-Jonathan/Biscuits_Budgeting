package com.beck.beck_demos.budget_app.controllers;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import com.beck.beck_demos.budget_app.data_fakes.AWSDAO_Fake;
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
import static com.beck.beck_demos.shared.MultipartContentBuilder.buildMultipartContent;


public class AddReceiptServletTest {
  private static final String PAGE="WEB-INF/budget_app/Add_Receipt.jsp";
  AddReceiptServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new AddReceiptServlet();
    servlet.init(new ReceiptDAO_Fake(),new TransactionDAO_Fake(), new AWSDAO_Fake());
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
   <p> Test that error messages are sent for each field for addingReceipt objects. That is to say, testing serverside validation </p>
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
   <p> Tests That We can add to the database if all input fields are validated  </p>
   */
  @Test
  public void TestAddCanAddWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);

    request.setParameter("inputreceiptTransaction_ID","TestValue");
    request.setParameter("inputreceiptImage_Link","TestValue");
    request.setParameter("inputreceiptName","TestValue");
    request.setParameter("inputreceiptDescription","TestValue");
    // Define boundary
    String boundary = "----WebKitFormBoundary7MA4YWxkTrZu0gW";

    // Define form fields
    Map<String, String> formFields = new HashMap<>();
    formFields.put("variant", "php");
    formFields.put("os", "mac");
    formFields.put("version", "3.4");
    formFields.put("inputreceiptReceipt_ID", "TestValue");
    formFields.put("inputreceiptImage_Link", "TestValue");
    formFields.put("inputreceiptName", "TestValue");
    formFields.put("inputreceiptDescription", "TestValue");


    // Define file fields
    Map<String, byte[]> fileFields = new LinkedHashMap<>();
    fileFields.put("content", "testFile".getBytes(StandardCharsets.UTF_8));
    fileFields.put("content2", "testFile2".getBytes(StandardCharsets.UTF_8));

    // Build multipart content
    byte[] multipartContent = buildMultipartContent(boundary, formFields, fileFields);

    // Setup request
    //MockHttpServletRequest request = new MockHttpServletRequest();
    request.setMethod("POST");
    request.setContentType("multipart/form-data; boundary=" + boundary);
    request.setContent(multipartContent);
    request.setSession(session);
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Receipt_Added = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Receipt_Added);
    assertEquals("Receipt Added",Receipt_Added);
    assertNotEquals("",Receipt_Added);
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
    session.setAttribute("User_B",user);
    request.setSession(session);


    // Define boundary
    String boundary = "----WebKitFormBoundary7MA4YWxkTrZu0gW";

    // Define form fields
    Map<String, String> formFields = new HashMap<>();
    formFields.put("variant", "php");
    formFields.put("os", "mac");
    formFields.put("version", "3.4");
    formFields.put("inputreceiptTransaction_ID", "EXCEPTION");
    formFields.put("inputreceiptImage_Link", "EXCEPTION");
    formFields.put("inputreceiptName", "EXCEPTION");
    formFields.put("inputreceiptDescription", "EXCEPTION");


    // Define file fields
    Map<String, byte[]> fileFields = new LinkedHashMap<>();
    fileFields.put("content", "testFile".getBytes(StandardCharsets.UTF_8));
    fileFields.put("content2", "testFile2".getBytes(StandardCharsets.UTF_8));

    // Build multipart content
    byte[] multipartContent = buildMultipartContent(boundary, formFields, fileFields);

    // Setup request
    //MockHttpServletRequest request = new MockHttpServletRequest();
    request.setMethod("POST");
    request.setContentType("multipart/form-data; boundary=" + boundary);
    request.setContent(multipartContent);
    request.setSession(session);
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Receipt_Added = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Receipt_Added);
    assertEquals("Receipt Not Added",Receipt_Added);
    assertNotEquals("",Receipt_Added);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }

  /**
   <p> Test that Receipt objects with duplicate primary keys don't get added, and proper error handling exists. </p>
   */
  @Test
  public void testDuplicateKeyReturnsZero() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);


    // Define boundary
    String boundary = "----WebKitFormBoundary7MA4YWxkTrZu0gW";

    // Define form fields
    Map<String, String> formFields = new HashMap<>();
    formFields.put("variant", "php");
    formFields.put("os", "mac");
    formFields.put("version", "3.4");
    formFields.put("inputreceiptTransaction_ID", "DUPLICATE");
    formFields.put("inputreceiptImage_Link", "DUPLICATE");
    formFields.put("inputreceiptName", "DUPLICATE");
    formFields.put("inputreceiptDescription", "DUPLICATE");


    // Define file fields
    Map<String, byte[]> fileFields = new LinkedHashMap<>();
    fileFields.put("content", "testFile".getBytes(StandardCharsets.UTF_8));
    fileFields.put("content2", "testFile2".getBytes(StandardCharsets.UTF_8));

    // Build multipart content
    byte[] multipartContent = buildMultipartContent(boundary, formFields, fileFields);

    // Setup request
    //MockHttpServletRequest request = new MockHttpServletRequest();
    request.setMethod("POST");
    request.setContentType("multipart/form-data; boundary=" + boundary);
    request.setContent(multipartContent);
    request.setSession(session);
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Receipt_Added = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Receipt_Added);
    assertEquals("Receipt Not Added",Receipt_Added);
    assertNotEquals("",Receipt_Added);
  }

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new AddReceiptServlet();
    servlet.init();
  }

}

