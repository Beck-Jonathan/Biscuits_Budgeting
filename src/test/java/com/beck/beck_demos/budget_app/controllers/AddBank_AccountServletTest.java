package com.beck.beck_demos.budget_app.controllers;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.*;
import com.beck.beck_demos.budget_app.data_fakes.Bank_AccountDAO_Fake;
import com.beck.beck_demos.budget_app.models.Bank_Account;

import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

class AddBank_AccountServletTest {

  private static final String PAGE="WEB-INF/budget_app/Add_Bank_Account.jsp";
  AddBank_AccountServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new AddBank_AccountServlet();
    servlet.init(new Bank_AccountDAO_Fake());
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
    String Bank_Account_IDError = results.get("bank_accountBank_Account_IDerror");
    String User_IDError = results.get("bank_accountUser_IDerror");
    String Account_NicknameError = results.get("bank_accountAccount_Nicknameerror");
    String BalanceError = results.get("bank_accountBalanceerror");
    String Balance_DateError = results.get("bank_accountBalance_Dateerror");
    assertNotEquals("",Bank_Account_IDError);
    assertNotNull(Bank_Account_IDError);
    assertNotEquals("",User_IDError);
    assertNotNull(User_IDError);
    assertNotEquals("",Account_NicknameError);
    assertNotNull(Account_NicknameError);
    assertNotEquals("",BalanceError);
    assertNotNull(BalanceError);
    assertNotEquals("",Balance_DateError);
    assertNotNull(Balance_DateError);
    assertEquals(200,responseStatus);
  }
  @Test
  public void TestAddCanAddWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID(39);
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("inputbank_accountBank_Account_ID","TestValue");
    request.setParameter("inputbank_accountUser_ID","406");
    request.setParameter("inputbank_accountAccount_Nickname","TestValue");
    request.setParameter("inputbank_accountBalance","406.23");
    request.setParameter("inputbank_accountBalance_Date","2024-4-4");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Bank_Account_Added = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Bank_Account_Added);
    assertEquals("Bank_Account Added",Bank_Account_Added);
    assertNotEquals("",Bank_Account_Added);
  }
  @Test
  public void testExceptionKeyThrowsException() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID(39);
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("inputbank_accountBank_Account_ID","EXCEPTION");
    request.setParameter("inputbank_accountUser_ID","406");
    request.setParameter("inputbank_accountAccount_Nickname","EXCEPTION");
    request.setParameter("inputbank_accountBalance","406.23");
    request.setParameter("inputbank_accountBalance_Date","2024-4-4");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Bank_Account_Added = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Bank_Account_Added);
    assertEquals("Bank_Account Not Added",Bank_Account_Added);
    assertNotEquals("",Bank_Account_Added);
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
    user.setUser_ID(39);
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("inputbank_accountBank_Account_ID","DUPLICATE");
    request.setParameter("inputbank_accountUser_ID","406");
    request.setParameter("inputbank_accountAccount_Nickname","DUPLICATE");
    request.setParameter("inputbank_accountBalance","406.23");
    request.setParameter("inputbank_accountBalance_Date","2024-4-4");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Bank_Account_Added = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Bank_Account_Added);
    assertEquals("Bank_Account Not Added",Bank_Account_Added);
    assertNotEquals("",Bank_Account_Added);
  }
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new AddBank_AccountServlet();
    servlet.init();
  }

}


