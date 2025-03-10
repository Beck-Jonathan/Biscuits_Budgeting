package com.beck.beck_demos.budget_app.controllers;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.*;
import com.beck.beck_demos.budget_app.data_fakes.Transaction_CommentDAO_Fake;
import com.beck.beck_demos.budget_app.models.Transaction_Comment;

import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

class AddTransaction_CommentServletTest {

  private static final String PAGE="WEB-INF/Budget_App/Add_Transaction_Comment.jsp";
  AddTransaction_CommentServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new AddTransaction_CommentServlet();
    servlet.init(new Transaction_CommentDAO_Fake());
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
  public void TestLoggedInUserGets302OnDoPost() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID(39);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    String url = response.getRedirectedUrl();
    assertEquals(302,status);
    assertEquals("editTransaction?transactionid=null",url);
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

    String Transaction_IDError = results.get("transaction_commentTransaction_IDerror");
    String Transaction_Comment_IDError = results.get("transaction_commentTransaction_Comment_IDerror");
    String ContentError = results.get("transaction_commentContenterror");


    assertNotEquals("",Transaction_IDError);
    assertNotNull(Transaction_IDError);
    assertNotEquals("",Transaction_Comment_IDError);
    assertNotNull(Transaction_Comment_IDError);
    assertNotEquals("",ContentError);
    assertNotNull(ContentError);

    assertEquals(302,responseStatus);
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

    request.setParameter("inputtransaction_commentTransaction_ID","406");
    request.setParameter("inputtransaction_commentTransaction_Comment_ID","406");
    request.setParameter("inputtransaction_commentContent","TestValue");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Transaction_Comment_Added = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Transaction_Comment_Added);
    assertEquals("Transaction Comment Added",Transaction_Comment_Added);
    assertNotEquals("",Transaction_Comment_Added);
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

    request.setParameter("inputtransaction_commentTransaction_ID","406");
    request.setParameter("inputtransaction_commentTransaction_Comment_ID","406");
    request.setParameter("inputtransaction_commentContent","EXCEPTION");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Transaction_Comment_Added = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(302,responseStatus);
    assertNotNull(Transaction_Comment_Added);
    assertEquals("Transaction_Comment Not Added",Transaction_Comment_Added);
    assertNotEquals("",Transaction_Comment_Added);
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
    user.setUser_ID(38);
    session.setAttribute("User_B",user);
    request.setSession(session);

    request.setParameter("inputtransaction_commentTransaction_ID","406");
    request.setParameter("inputtransaction_commentTransaction_Comment_ID","406");
    request.setParameter("inputtransaction_commentContent","DUPLICATE");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Transaction_Comment_Added = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Transaction_Comment_Added);
    assertEquals("Transaction_Comment Not Added",Transaction_Comment_Added);
    assertNotEquals("",Transaction_Comment_Added);
  }
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new AddTransaction_CommentServlet();
    servlet.init();
  }

}



