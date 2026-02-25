package com.beck.beck_demos.budget_app.controllers;

import java.io.IOException;
import java.util.*;
import com.beck.beck_demos.budget_app.data_fakes.Bank_AccountDAO_Fake;
import com.beck.beck_demos.budget_app.models.Bank_Account;
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

public class EditBank_AccountServletTest {
  private static final String PAGE="WEB-INF/budget_app/Edit_Bank_Account.jsp";
  EditBank_AccountServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new EditBank_AccountServlet();
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
    user.setUser_ID("af735dfc-22a9-4214-a8e5-fb8de2305700");
    session.setAttribute("User_B",user);
    request.setSession(session);
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
    user.setUser_ID("af735dfc-22a9-4214-a8e5-fb8de2305700");
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
    request.setSession(session);;
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }
  @Test
  public void testGetOneBank_AccountGetsOneBank_Account_ID() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_B",user);

    String Bank_Account_ID= null;
    request.setParameter("bankaccountid",Bank_Account_ID);
    request.setSession(session);
    servlet.doGet(request,response);
    Bank_Account bank_account = (Bank_Account) session.getAttribute("bank_account");
    assertNotNull(bank_account);
    assertEquals(Bank_Account_ID,bank_account.getBank_Account_ID());
  }
  @Test
  public void testGetOneBank_AccountCanFail() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_B",user);

    String Bank_Account_ID= null;
    request.setParameter("bankaccountid",Bank_Account_ID);

    request.setSession(session);
    servlet.doGet(request,response);
    Bank_Account bank_account = (Bank_Account) session.getAttribute("bank_account");
    assertNull(bank_account.getBank_Account_ID());
    assertNull(bank_account.getUser_ID());
    assertNull(bank_account.getAccount_Nickname());
    assertNull(bank_account.getBalance());
    assertNull(bank_account.getBalance_Date());
  }
  @Test
  public void TestUpdateCanAddWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("af735dfc-22a9-4214-a8e5-fb8de2305700");
    session.setAttribute("User_B",user);
    request.setSession(session);
//to set the old Bank_Account
    Bank_Account bank_account = new Bank_Account();
    bank_account.setBank_Account_ID("hSNjUblO");
    bank_account.setUser_ID("af735dfc-22a9-4214-a8e5-fb8de2305700");
    bank_account.setAccount_Nickname("testBank_Account");
    bank_account.setBalance(104.23d);
    bank_account.setBalance_Date(new Date());
    session.setAttribute("bank_account",bank_account);
//create a new albums parameters
    request.setParameter("inputbank_accountBank_Account_ID","TestValue");
    request.setParameter("inputbank_accountUser_ID","406");
    request.setParameter("inputbank_accountAccount_Nickname","TestValue");
    request.setParameter("inputbank_accountBalance","123.42d");
    request.setParameter("inputbank_accountBalance_Date","2024-4-4");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Bank_Account_Updated = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Bank_Account_Updated);
    assertEquals("Bank_Account updated",Bank_Account_Updated);
    assertNotEquals("",Bank_Account_Updated);
  }
  @Test
  public void TestUpdateHasErrorsForEachFiledAndKeepsOnSamePage() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("af735dfc-22a9-4214-a8e5-fb8de2305700");
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Bank_Account_IDError = results.get("bank_accountBank_Account_IDerror");
    String Account_NicknameError = results.get("bank_accountAccount_Nicknameerror");
    String BalanceError = results.get("bank_accountBalanceerror");
    String Balance_DateError = results.get("bank_accountBalance_Dateerror");
    assertNotEquals("",Bank_Account_IDError);
    assertNotNull(Bank_Account_IDError);
    assertNotEquals("",Account_NicknameError);
    assertNotNull(Account_NicknameError);
    assertNotEquals("",BalanceError);
    assertNotNull(BalanceError);
    assertNotEquals("",Balance_DateError);
    assertNotNull(Balance_DateError);
    assertEquals(200,responseStatus);
  }
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new EditBank_AccountServlet();
    servlet.init();
  }
  @Test
  public void testUpdateCanReturnZero() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("af735dfc-22a9-4214-a8e5-fb8de2305700");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
//to set the old Bank_Account
    Bank_Account bank_account = new Bank_Account();
    bank_account.setBank_Account_ID("DUPLICATE");
    bank_account.setUser_ID("af735dfc-22a9-4214-a8e5-fb8de2305700");
    bank_account.setAccount_Nickname("DUPLICATE");
    bank_account.setBalance(104.23d);
    bank_account.setBalance_Date(new Date());
    session.setAttribute("bank_account",bank_account);
//create a new albums parameters
    request.setParameter("inputbank_accountBank_Account_ID","DUPLICATE");
    request.setParameter("inputbank_accountUser_ID","406");
    request.setParameter("inputbank_accountAccount_Nickname","DUPLICATE");
    request.setParameter("inputbank_accountBalance","123.42d");
    request.setParameter("inputbank_accountBalance_Date","2024-4-4");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Bank_Account_Updated = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Bank_Account_Updated);
    assertEquals("Bank_Account Not Updated",Bank_Account_Updated);
    assertNotEquals("",Bank_Account_Updated);
  }
  @Test
  public void testUpdateCanThrowSQLException() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("af735dfc-22a9-4214-a8e5-fb8de2305700");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
//to set the old Bank_Account
    Bank_Account bank_account = new Bank_Account();
    bank_account.setBank_Account_ID("EXCEPTION");
    bank_account.setUser_ID("af735dfc-22a9-4214-a8e5-fb8de2305700");
    bank_account.setAccount_Nickname("EXCEPTION");
    session.setAttribute("bank_account",bank_account);
//create a new albums parameters
    request.setParameter("inputbank_accountBank_Account_ID","EXCEPTION");
    request.setParameter("inputbank_accountUser_ID","406");
    request.setParameter("inputbank_accountAccount_Nickname","EXCEPTION");
    request.setParameter("inputbank_accountBalance","123.42d");
    request.setParameter("inputbank_accountBalance_Date","2024-4-4");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Bank_Account_Updated = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Bank_Account_Updated);
    assertNotEquals("",Bank_Account_Updated);
    assertEquals("Bank_Account Not Updated",Bank_Account_Updated);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }

}

