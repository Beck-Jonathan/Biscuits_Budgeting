package com.beck.beck_demos.budget_app.controllers;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.*;

import com.beck.beck_demos.budget_app.data_fakes.CategoryDAO_Fake;
import com.beck.beck_demos.budget_app.data_fakes.TransactionDAO_Fake;
import com.beck.beck_demos.budget_app.data_fakes.UserDAO_Fake;
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

class PieChartControllerTest {

  private static final String PAGE="WEB-INF/budget_app/All_Transaction.jsp";
  PieChartController servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new PieChartController();
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
    user.setUser_ID(39);
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
    user.setRoles(roles);
    user.setUser_ID(39);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }
  @Test
  public void testLoggedInUserGetsAllTransactions() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID(39);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doGet(request,response);
    List<Transaction> transactions = (List<Transaction>) request.getAttribute("Transactions");
    assertNotNull(transactions);
    assertEquals(20,transactions.size());
  }
  @Test
  public void testLoggedInUserCanFilterTransactionsByUser_ID() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID(39);
    session.setAttribute("User_B",user);
    Integer User_ID= 39;
    request.setParameter("User_ID",User_ID.toString());
    request.setSession(session);
    servlet.doGet(request,response);
    List<Transaction> transactions = (List<Transaction>) request.getAttribute("Transactions");
    assertNotNull(transactions);
    assertEquals(20,transactions.size());
  }
  @Test
  public void testLoggedInUserCanFilterTransactionsByCategory_ID() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID(39);
    session.setAttribute("User_B",user);
    String Category_ID= null;
    request.setParameter("Category_ID",Category_ID);
    request.setSession(session);
    servlet.doGet(request,response);
    List<Transaction> transactions = (List<Transaction>) request.getAttribute("Transactions");
    assertNotNull(transactions);
    assertEquals(20,transactions.size());
  }
  @Test
  public void testLoggedInUserCanFilterTransactionsByBank_Account_ID() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID(39);
    session.setAttribute("User_B",user);
    String Bank_Account_ID= null;
    request.setParameter("Bank_Account_ID",Bank_Account_ID);
    request.setSession(session);
    servlet.doGet(request,response);
    List<Transaction> transactions = (List<Transaction>) request.getAttribute("Transactions");
    assertNotNull(transactions);
    assertEquals(20,transactions.size());
  }
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new PieChartController();
    servlet.init();
  }

}
