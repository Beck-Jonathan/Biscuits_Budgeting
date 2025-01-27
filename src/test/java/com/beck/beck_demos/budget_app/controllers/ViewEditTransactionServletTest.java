package com.beck.beck_demos.budget_app.controllers;

import java.io.IOException;
import java.util.*;

import com.beck.beck_demos.budget_app.data_fakes.CategoryDAO_Fake;
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

class ViewEditTransactionServletTest {

  private static final String PAGE="WEB-INF/budget_app/Edit_Transaction.jsp";
  ViewEditTransactionServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new ViewEditTransactionServlet();
    servlet.init(new TransactionDAO_Fake(),new CategoryDAO_Fake());
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
    user.setUser_ID(39);
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
    user.setUser_ID(39);
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
    user.setUser_ID(39);
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
    user.setUser_ID(39);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }
  @Test
  public void testGetOneTransactionGetsOneTransaction_ID() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID(39);
    session.setAttribute("User_B",user);
    Integer Transaction_ID= 48;
    request.setParameter("transactionid",Transaction_ID.toString());
    request.setSession(session);
    servlet.doGet(request,response);
    Transaction transaction = (Transaction) session.getAttribute("transaction");
    assertNotNull(transaction);
    assertEquals(Transaction_ID,transaction.getTransaction_ID());
  }
  @Test
  public void testGetOneTransactionCanFail() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID(39);
    session.setAttribute("User_B",user);
    Integer Transaction_ID= -1;
    request.setParameter("transactionid",Transaction_ID.toString());

    request.setSession(session);
    servlet.doGet(request,response);
    Transaction transaction = (Transaction) session.getAttribute("transaction");
    assertNull(transaction.getTransaction_ID());
    assertNull(transaction.getUser_ID());
    assertNull(transaction.getCategory_ID());
    assertNull(transaction.getBank_Account_ID());
    assertNull(transaction.getPost_Date());
    assertNull(transaction.getCheck_No());
    assertNull(transaction.getDescription());
    assertNull(transaction.getAmount());
    assertNull(transaction.getType());
    assertNull(transaction.getStatus());
  }
  @Test
  public void TestUpdateCanAddWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID(39);
    session.setAttribute("User_B",user);
    request.setSession(session);
//to set the old Transaction
    Transaction transaction = new Transaction();
    transaction.setTransaction_ID(48);
    transaction.setUser_ID(39);
    transaction.setCategory_ID("testTransaction");
    transaction.setBank_Account_ID("testTransaction");
    transaction.setCheck_No(43);
    transaction.setDescription("testTransaction");
    transaction.setType("testTransaction");
    transaction.setStatus("testTransaction");
    session.setAttribute("transaction",transaction);
//create a new albums parameters
    request.setParameter("inputtransactionUser_ID","39");
    request.setParameter("inputtransactionCategory_ID","TestValue");
    request.setParameter("inputtransactionAccount_Num","TestValue");
    request.setParameter("inputtransactionCheck_No","406");
    request.setParameter("inputtransactionDescription","TestValue");
    request.setParameter("inputtransactionType","TestValue");
    request.setParameter("inputtransactionStatus","TestValue");
    request.setParameter("inputtransactionPost_Date","2021-10-10");
    request.setParameter("inputtransactionAmount","20.24");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Transaction_Updated = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Transaction_Updated);
    assertEquals("Transaction updated",Transaction_Updated);
    assertNotEquals("",Transaction_Updated);
  }
  @Test
  public void TestUpdateHasErrorsForEachFiledAndKeepsOnSamePage() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID(39);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");

    String Category_IDError = results.get("transactionCategory_IDerror");
    String Account_NumError = results.get("transactionAccount_Numerror");
    String Post_DateError = results.get("transactionPost_Dateerror");
    String Check_NoError = results.get("transactionCheck_Noerror");
    String DescriptionError = results.get("transactionDescriptionerror");
    String AmountError = results.get("transactionAmounterror");
    String TypeError = results.get("transactionTypeerror");
    String StatusError = results.get("transactionStatuserror");

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
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new ViewEditTransactionServlet();
    servlet.init();
  }
  @Test
  public void testUpdateCanReturnZero() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID(39);
    session.setAttribute("User_B",user);
    request.setSession(session);
//to set the old Transaction
    Transaction transaction = new Transaction();
    transaction.setTransaction_ID(43);
    transaction.setUser_ID(43);
    transaction.setCategory_ID("DUPLICATE");
    transaction.setBank_Account_ID("DUPLICATE");
    transaction.setCheck_No(43);
    transaction.setDescription("DUPLICATE");
    transaction.setType("DUPLICATE");
    transaction.setStatus("DUPLICATE");
    session.setAttribute("transaction",transaction);
//create a new albums parameters
    request.setParameter("inputtransactionUser_ID","406");
    request.setParameter("inputtransactionCategory_ID","DUPLICATE");
    request.setParameter("inputtransactionAccount_Num","DUPLICATE");
    request.setParameter("inputtransactionCheck_No","406");
    request.setParameter("inputtransactionDescription","DUPLICATE");
    request.setParameter("inputtransactionType","DUPLICATE");
    request.setParameter("inputtransactionStatus","DUPLICATE");
    request.setParameter("inputtransactionPost_Date","2021-10-10");
    request.setParameter("inputtransactionAmount","20.24");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Transaction_Updated = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Transaction_Updated);
    assertEquals("Transaction Not Updated",Transaction_Updated);
    assertNotEquals("",Transaction_Updated);
  }
  @Test
  public void testUpdateCanThrowSQLException() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID(39);
    session.setAttribute("User_B",user);
    request.setSession(session);
//to set the old Transaction
    Transaction transaction = new Transaction();
    transaction.setTransaction_ID(43);
    transaction.setUser_ID(43);
    transaction.setCategory_ID("EXCEPTION");
    transaction.setBank_Account_ID("EXCEPTION");
    transaction.setCheck_No(43);
    transaction.setDescription("EXCEPTION");
    transaction.setType("EXCEPTION");
    transaction.setStatus("EXCEPTION");
    request.setParameter("inputtransactionPost_Date","2021-10-10");
    request.setParameter("inputtransactionAmount","20.24");

    session.setAttribute("transaction",transaction);
//create a new albums parameters
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
    String Transaction_Updated = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Transaction_Updated);
    assertNotEquals("",Transaction_Updated);
    assertEquals("Transaction Not Updated",Transaction_Updated);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }

}

