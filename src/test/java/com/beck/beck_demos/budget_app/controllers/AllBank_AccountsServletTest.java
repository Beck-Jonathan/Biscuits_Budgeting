package com.beck.beck_demos.budget_app.controllers;


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

public class AllBank_AccountsServletTest {
  private static final String PAGE="WEB-INF/budget_app/All_Bank_Account.jsp";
  AllBank_AccountsServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new AllBank_AccountsServlet();
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
    user.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doGet(request,response);
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
  public void TestWrongRoleGets302onDoGet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("WrongRole");
    user.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }
  @Test
  public void testLoggedInUserGetsAllBank_Accounts() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("17172158-16cd-4722-862a-bfe59e4be263");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doGet(request,response);
    List<Bank_Account> bank_accounts = (List<Bank_Account>) request.getAttribute("Bank_Accounts");
    assertNotNull(bank_accounts);
    assertEquals(5,bank_accounts.size());
  }
  @Test
  public void testLoggedInUserCanFilterBank_AccountsByUser_ID() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("17172158-16cd-4722-862a-bfe59e4be263");
    user.setRoles(roles);
    session.setAttribute("User_B",user);

    request.setSession(session);
    servlet.doGet(request,response);
    List<Bank_Account> bank_accounts = (List<Bank_Account>) request.getAttribute("Bank_Accounts");
    assertNotNull(bank_accounts);
    assertEquals(5,bank_accounts.size());
  }
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new AllBank_AccountsServlet();
    servlet.init();
  }
}

