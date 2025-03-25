package com.beck.beck_demos.budget_app.controllers;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.*;
import com.beck.beck_demos.budget_app.data_fakes.Transaction_CommentDAO_Fake;


import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;


class DeleteTransaction_CommentServletTest {
  private static final String PAGE="WEB-INF/budget_app/Delete_Transaction_Comment.jsp";
  DeleteTransaction_CommentServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new DeleteTransaction_CommentServlet();
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
  public void TestLoggedInUserGets302OndoPost() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID(36);
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    String url = response.getRedirectedUrl();
    assertEquals(302,status);
    assertEquals("editTransaction?transactionid=null",url);
  }
  @Test
  public void TestLoggedOutUserGets302OndoPost() throws ServletException, IOException{
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }
  @Test
  public void TestDeleteCanDelete() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID(54);
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("transaction_id","lwIFwDnklwIFwDnklwIFwDnklwIFwDnk5212");
    request.setParameter("transaction_commentid","20");

    servlet.doPost(request,response);
    int status = (int) request.getAttribute("result");
    assertEquals(1,status);
  }
  @Test
  public void TestWrongRoleGets302ondoPost() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("WrongRole");
    user.setUser_ID(36);
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }

  @Test
  public void TestDeleteCanFailIfKeyDoesNotExist() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID(54);
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("transaction_id","1000");
    request.setParameter("transaction_commentid","20");

    servlet.doPost(request,response);
    int status = (int) request.getAttribute("result");
    assertEquals(0,status);
  }




  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new DeleteTransaction_CommentServlet();
    servlet.init();
  }

}

