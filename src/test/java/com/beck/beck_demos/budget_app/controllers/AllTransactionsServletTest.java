package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data_fakes.Bank_AccountDAO_Fake;
import com.beck.beck_demos.budget_app.data_fakes.CategoryDAO_Fake;
import com.beck.beck_demos.budget_app.data_fakes.TransactionDAO_Fake;
import com.beck.beck_demos.budget_app.models.Transaction;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockRequestDispatcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AllTransactionsServletTest {

  private static final String PAGE="WEB-INF/budget_app/All_Transaction.jsp";
  AllTransactionsServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  @BeforeEach
  public void setup() throws ServletException{
    servlet = new AllTransactionsServlet();
    servlet.init(new TransactionDAO_Fake(), new CategoryDAO_Fake(), new Bank_AccountDAO_Fake());
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

  // --- AUTHENTICATION & ACCESS TESTS ---

  @Test
  public void TestLoggedInUserGets200OnDoGet() throws ServletException, IOException{
    setupUserSession("User", "fec75744-130e-4bcb-8bbe-9bee18080428");
    servlet.doGet(request,response);
    assertEquals(200, response.getStatus());
  }

  @Test
  public void TestLoggedOutUserGets302OnDoGet() throws ServletException, IOException{
    request.setSession(session);
    servlet.doGet(request,response);
    assertEquals(302, response.getStatus());
  }

  @Test
  public void TestWrongRoleGets302onDoGet() throws ServletException, IOException{
    setupUserSession("WrongRole", null);
    servlet.doGet(request,response);
    assertEquals(302, response.getStatus());
  }

  // --- DATA RETRIEVAL TESTS ---

  @Test
  public void testLoggedInUserGetsAllTransactions() throws ServletException, IOException{
    setupUserSession("User", "618052e9-c69b-4d9b-880e-e22e4a970bd6");
    servlet.doGet(request,response);
    List<Transaction> transactions = (List<Transaction>) request.getAttribute("Transactions");
    assertNotNull(transactions);
    assertEquals(5, transactions.size());
  }

  @Test
  public void testLoggedInUserCanFilterTransactionsByCategory_ID() throws ServletException, IOException{
    setupUserSession("User", "4f88f943-c3b8-4b32-9136-9e7c2a2ddbfc");
    request.setParameter("category", "e6c68360-1216-4c12-8831-294146908356");

    servlet.doGet(request,response);
    List<Transaction> transactions = (List<Transaction>) request.getAttribute("Transactions");
    assertNotNull(transactions);
    assertEquals(3, transactions.size());
  }

  @Test
  public void testLoggedInUserCanFilterTransactionsByYear() throws ServletException, IOException{
    setupUserSession("User", "4f88f943-c3b8-4b32-9136-9e7c2a2ddbfc");
    request.setParameter("year", "2004");

    servlet.doGet(request,response);
    List<Transaction> transactions = (List<Transaction>) request.getAttribute("Transactions");
    assertNotNull(transactions);
    assertEquals(7, transactions.size());
  }

  @Test
  public void testLoggedInUserCanFilterTransactionsByYearAndMonth() throws ServletException, IOException {
    setupUserSession("User", "4f88f943-c3b8-4b32-9136-9e7c2a2ddbfc");
    request.setParameter("year", "2004");
    request.setParameter("month", "4");
    servlet.doGet(request, response);
    List<Transaction> transactions = (List<Transaction>) request.getAttribute("Transactions");
    assertNotNull(transactions);
    assertEquals(5, transactions.size());
  }

  @Test
  public void testLoggedInUserCanFilterTransactionsByLocked() throws ServletException, IOException {
    setupUserSession("User", "4f88f943-c3b8-4b32-9136-9e7c2a2ddbfc");
    request.setParameter("showLocked", "true");

    servlet.doGet(request, response);
    List<Transaction> transactions = (List<Transaction>) request.getAttribute("Transactions");
    assertNotNull(transactions);
    assertEquals(3, transactions.size());
  }

  /**
   * New test to ensure the servlet correctly identifies Investment-related
   * transactions for the new chart stack.
   */
  @Test
  public void testLoggedInUserCanFilterByInvestmentType() throws ServletException, IOException {
    setupUserSession("User", "618052e9-c69b-4d9b-880e-e22e4a970bd6");
    // Assuming you might add a 'type' filter for the chart
    request.setParameter("type", "Investment");

    servlet.doGet(request, response);
    List<Transaction> transactions = (List<Transaction>) request.getAttribute("Transactions");
    assertNotNull(transactions);
    assertEquals(5, transactions.size());
  }

  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new AllTransactionsServlet();
    servlet.init();
  }

  // --- HELPER ---
  private void setupUserSession(String role, String userId) {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add(role);
    user.setRoles(roles);
    if (userId != null) user.setUser_ID(userId);
    session.setAttribute("User_B", user);
    request.setSession(session);
  }
}