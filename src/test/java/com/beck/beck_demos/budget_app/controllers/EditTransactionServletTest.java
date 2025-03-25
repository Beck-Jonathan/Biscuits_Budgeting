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

class EditTransactionServletTest {


  private static final String PAGE="WEB-INF/budget_app/Edit_Transaction.jsp";
  EditTransactionServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new EditTransactionServlet();
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
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }


  @Test
  public void TestUpdateCanAddWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID(41);
    session.setAttribute("User_B",user);
    request.setSession(session);

//create a new albums parameters

    request.setParameter("category","TestValue");
    request.setParameter("t_id","XxtdYmVMXxtdYmVMXxtdYmVMXxtdYmVMXxtd");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    String response2 = response.getContentAsString();

    assertEquals(200,responseStatus);
    assertEquals(response2,"success");

  }
  @Test
  public void TestUpdateHasErrorsForEachFiledAndKeepsOnSamePage() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID(41);
    session.setAttribute("User_B",user);
    request.setSession(session);

//create a new albums parameters

    request.setParameter("category","TestValue");
    request.setParameter("t_id","XxtdYmVMXxtdYmVMXxtdYmVMXxtdYmVMXxtd");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    String response2 = response.getContentAsString();

    assertEquals(200,responseStatus);
    assertEquals(response2,"success");
  }
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new EditTransactionServlet();
    servlet.init();
  }
  @Test
  public void testUpdateCanReturnZero() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID(41);
    session.setAttribute("User_B",user);
    request.setSession(session);

//create a new albums parameters

    request.setParameter("category","DUPLICATE");
    request.setParameter("t_id","XxtdYmVMXxtdYmVMXxtdYmVMXxtdYmVMXxtd");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    String response2 = response.getContentAsString();

    assertEquals(200,responseStatus);
    assertEquals(response2,"error");
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

//create a new albums parameters

    request.setParameter("category","EXCEPTION");
    request.setParameter("t_id","XxtdYmVMXxtdYmVMXxtdYmVMXxtdYmVMXxtd");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    String response2 = response.getContentAsString();

    assertEquals(200,responseStatus);
    assertEquals(response2,"error");
  }

}
