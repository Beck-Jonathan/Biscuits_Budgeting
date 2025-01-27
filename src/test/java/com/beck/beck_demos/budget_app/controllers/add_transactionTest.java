package com.beck.beck_demos.budget_app.controllers;

import java.io.IOException;
import java.util.*;
import com.beck.beck_demos.budget_app.data_fakes.TransactionDAO_Fake;
import com.beck.beck_demos.budget_app.models.Transaction;

import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

class add_transactionTest {

  private static final String PAGE="WEB-INF/budget_app/Add_Transaction.jsp";
  add_transaction servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new add_transaction();
    servlet.init(new TransactionDAO_Fake());
    request =  new MockHttpServletRequest();
    response = new MockHttpServletResponse();
    session = new MockHttpSession();
    rd = new MockRequestDispatcher(PAGE);
  }

  @AfterEach
  public void teardown(){
    servlet=null;
    request=null;
    response=null;
    session=null;
    rd=null;
  }
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
  @Test
  public void TestLoggedInUserGets200OnDoPost() throws ServletException, IOException{
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
  @Test
  public void TestLoggedOutUserGets302OnDoGet() throws ServletException, IOException{
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }
  @Test
  public void TestLoggedOutUserGets302OnDoPost() throws ServletException, IOException{
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }
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
  }
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
  }
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
    String User_IDError = results.get("transactionUser_IDerror");
    String Category_IDError = results.get("transactionCategory_IDerror");
    String Account_NumError = results.get("transactionAccount_Numerror");
    String Post_DateError = results.get("transactionPost_Dateerror");
    String Check_NoError = results.get("transactionCheck_Noerror");
    String DescriptionError = results.get("transactionDescriptionerror");
    String AmountError = results.get("transactionAmounterror");
    String TypeError = results.get("transactionTypeerror");
    String StatusError = results.get("transactionStatuserror");
    assertNotEquals("",User_IDError);
    assertNotNull(User_IDError);
    assertNotEquals("",Category_IDError);
    assertNotNull(Category_IDError);
    assertNotEquals("",Account_NumError);
    assertNotNull(Account_NumError);
    assertNotEquals("",Post_DateError);
    assertNotNull(Post_DateError);
    assertNotEquals("",Check_NoError);
    assertNotNull(Check_NoError);
    assertNotEquals("",DescriptionError);
    assertNotNull(DescriptionError);
    assertNotEquals("",AmountError);
    assertNotNull(AmountError);
    assertNotEquals("",TypeError);
    assertNotNull(TypeError);
    assertNotEquals("",StatusError);
    assertNotNull(StatusError);
    assertEquals(200,responseStatus);
  }
  @Test
  public void TestAddCanAddWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("inputtransactionUser_ID","406");
    request.setParameter("inputtransactionCategory_ID","TestValue");
    request.setParameter("inputtransactionAccount_Num","TestValue");
    request.setParameter("inputtransactionCheck_No","406");
    request.setParameter("inputtransactionDescription","TestValue");
    request.setParameter("inputtransactionType","TestValue");
    request.setParameter("inputtransactionStatus","TestValue");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Transaction_Added = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Transaction_Added);
    assertEquals("Transaction Added",Transaction_Added);
    assertNotEquals("",Transaction_Added);
  }
  @Test
  public void testExceptionKeyThrowsException() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("inputtransactionUser_ID","406");
    request.setParameter("inputtransactionCategory_ID","EXCEPTION");
    request.setParameter("inputtransactionAccount_Num","EXCEPTION");
    request.setParameter("inputtransactionCheck_No","406");
    request.setParameter("inputtransactionDescription","EXCEPTION");
    request.setParameter("inputtransactionType","EXCEPTION");
    request.setParameter("inputtransactionStatus","EXCEPTION");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Transaction_Added = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Transaction_Added);
    assertEquals("Transaction Not Added",Transaction_Added);
    assertNotEquals("",Transaction_Added);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }
  @Test
  public void testDuplicateKeyReturnsZero() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("inputtransactionUser_ID","406");
    request.setParameter("inputtransactionCategory_ID","DUPLICATE");
    request.setParameter("inputtransactionAccount_Num","DUPLICATE");
    request.setParameter("inputtransactionCheck_No","406");
    request.setParameter("inputtransactionDescription","DUPLICATE");
    request.setParameter("inputtransactionType","DUPLICATE");
    request.setParameter("inputtransactionStatus","DUPLICATE");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Transaction_Added = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Transaction_Added);
    assertEquals("Transaction Not Added",Transaction_Added);
    assertNotEquals("",Transaction_Added);
  }
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new add_transaction();
    servlet.init();
  }

}


