package com.beck.beck_demos.budget_app.controllers;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.text.ParseException;
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

class EditTransaction_CommentServletTest {

  private static final String PAGE="WEB-INF/budget_app/Edit_Transaction_Comment.jsp";
  EditTransaction_CommentServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new EditTransaction_CommentServlet();
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
  public void TestLoggedInUserGets200OnDoPost() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID(36);
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(200,status);
  }

  @Test
  public void TestLoggedOutUserGets302OnDoPost() throws ServletException, IOException{
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }

  @Test
  public void TestWrongRoleGets302onDoPost() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("WrongRole");
    user.setUser_ID(36);
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }

  @Test
  public void TestUpdateCanAddWithNoErrorsAndRedirects() throws ServletException, IOException, ParseException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID(54);
    user.setRoles(roles);
    session.setAttribute("User_B",user);
//to set the old Transaction_Comment
    Transaction_Comment transaction_comment = new Transaction_Comment();
    transaction_comment.setUser_ID(54);
    transaction_comment.setTransaction_ID(55);
    transaction_comment.setTransaction_Comment_ID(20);
    transaction_comment.setContent("testTransaction_Comment");
    transaction_comment.setPost_Date(new Date());
    session.setAttribute("transaction_comment",transaction_comment);
    request.setSession(session);
//create a new albums parameters
    request.setParameter("inputtransaction_commentUser_ID","54");
    request.setParameter("inputtransaction_commentTransaction_ID","55");
    request.setParameter("inputtransaction_commentTransaction_Comment_ID","20");
    request.setParameter("inputtransaction_commentContent","TestValue");
    request.setParameter("inputtransaction_commentOldContent","oldContent");

    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Transaction_Comment_Updated = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Transaction_Comment_Updated);
    assertEquals("Transaction_Comment updated",Transaction_Comment_Updated);
    assertNotEquals("",Transaction_Comment_Updated);
  }
  @Test
  public void TestUpdateHasErrorsForEachFiledAndKeepsOnSamePage() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID(36);
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


    assertEquals(200,responseStatus);
  }
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new EditTransaction_CommentServlet();
    servlet.init();
  }
  @Test
  public void testUpdateCanReturnZero() throws ServletException, IOException, ParseException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID(36);
    user.setRoles(roles);
    session.setAttribute("User_B",user);
//to set the old Transaction_Comment
    Transaction_Comment transaction_comment = new Transaction_Comment();
    transaction_comment.setUser_ID(43);
    transaction_comment.setTransaction_ID(43);
    transaction_comment.setTransaction_Comment_ID(43);
    transaction_comment.setContent("DUPLICATE");
    transaction_comment.setPost_Date(new Date());
    session.setAttribute("transaction_comment",transaction_comment);
    request.setSession(session);
//create a new albums parameters
    request.setParameter("inputtransaction_commentUser_ID","406");
    request.setParameter("inputtransaction_commentTransaction_ID","406");
    request.setParameter("inputtransaction_commentTransaction_Comment_ID","406");
    request.setParameter("inputtransaction_commentContent","DUPLICATE");
    request.setParameter("inputtransaction_commentOldContent","DUPLICATE");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Transaction_Comment_Updated = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Transaction_Comment_Updated);
    assertEquals("Transaction_Comment Not Updated",Transaction_Comment_Updated);
    assertNotEquals("",Transaction_Comment_Updated);
  }
  @Test
  public void testUpdateCanThrowSQLException() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID(36);
    user.setRoles(roles);
    session.setAttribute("User_B",user);
//to set the old Transaction_Comment
    Transaction_Comment transaction_comment = new Transaction_Comment();
    transaction_comment.setUser_ID(43);
    transaction_comment.setTransaction_ID(43);
    transaction_comment.setTransaction_Comment_ID(43);
    transaction_comment.setContent("EXCEPTION");
    session.setAttribute("transaction_comment",transaction_comment);
    request.setSession(session);
//create a new albums parameters
    request.setParameter("inputtransaction_commentUser_ID","406");
    request.setParameter("inputtransaction_commentTransaction_ID","406");
    request.setParameter("inputtransaction_commentTransaction_Comment_ID","406");
    request.setParameter("inputtransaction_commentContent","EXCEPTION");
    request.setParameter("inputtransaction_commentOldContent","EXCEPTION");

    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Transaction_Comment_Updated = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Transaction_Comment_Updated);
    assertNotEquals("",Transaction_Comment_Updated);
    assertEquals("Transaction_Comment Not Updated",Transaction_Comment_Updated);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }

}



