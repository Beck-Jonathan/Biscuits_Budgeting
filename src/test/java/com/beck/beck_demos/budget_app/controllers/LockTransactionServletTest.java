package com.beck.beck_demos.budget_app.controllers;

import java.io.IOException;
import java.util.*;
import com.beck.beck_demos.budget_app.data_fakes.TransactionDAO_Fake;
import com.beck.beck_demos.budget_app.models.Transaction;
import com.beck.beck_demos.budget_app.models.Transaction_VM;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LockTransactionServletTest {

  LockTransactionServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet=new LockTransactionServlet();
    servlet.init(new TransactionDAO_Fake());
    request =  new MockHttpServletRequest();
    response = new MockHttpServletResponse();
    session = new MockHttpSession();

  }

  /**
   <p> tear down by setting all variablges to null. </p>
   */
  @AfterEach
  public void teardown(){
    servlet=null;
    request=null;
    response=null;
    session=null;
    rd=null;
  }

  /**
   <p> Tests That the user will received a 200 status on doGet if they are logged in </p>
   */
  @Test
  public void TestLoggedInUserGets200OnDoGet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(200,status);
  }

  /**
   <p> Tests That the user will received a 302 status on doGet if they are logged out  </p>
   */
  @Test
  public void TestLoggedOutUserGets302OnDoGet() throws ServletException, IOException{
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }

  /**
   <p> Test that the deactivation servlet can deactivate a Transaction </p>
   */
  @Test
  public void TestLockCanLock() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("618052e9-c69b-4d9b-880e-e22e4a970bd6");
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("t_id","XxtdYmVMXxtdYmVMXxtdYmVMXxtdYmVMXxtd");

    servlet.doPost(request,response);
    String reply = response.getContentAsString();
    String expected="success";
    assertEquals(reply,expected);
  }

  /**
   <p> Test that the deactivation servlet can deactivate a Transaction </p>
   */
  @Test
  public void TestUnlockCanUnlock() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("618052e9-c69b-4d9b-880e-e22e4a970bd6");
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("t_id","ukukySnYXxtdYmVMXxtdYmVMXxtdYmVMXxtd");

    servlet.doPost(request,response);
    String reply = response.getContentAsString();
    String expected="success";
    assertEquals(reply,expected);
  }

  /**
   <p> Test that the deactivation servlet can deactivate a Transaction </p>
   */
  @Test
  public void TestCanFailWithInvalidID() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("t_id","32");

    servlet.doPost(request,response);
    String reply = response.getContentAsString();
    String expected="error";
    assertEquals(reply,expected);
  }

  /**
   <p> Test That This can fail with an not found ID</p>
   */
  @Test
  public void TestCanFailWithNotFoundID() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("t_id","ukukySnYXxtdYmVMXxtdYmVMXxtdYmVMXddd");

    servlet.doPost(request,response);
    String reply = response.getContentAsString();
    String expected="error";
    assertEquals(reply,expected);
  }

  /**
   <p> Test That This can fail with an not found ID</p>
   */
  @Test
  public void TestThisCanHandleSQLException() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("t_id","EXCEPTIONEXCEPTIONEXCEPTIONEXCEPTION");

    servlet.doPost(request,response);
    String reply = response.getContentAsString();
    String expected="error";
    assertEquals(reply,expected);
  }

  /**
   <p> Test That User in the wrong role gets a 302 redirect </p>
   */
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

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new LockTransactionServlet();
    servlet.init();
  }


}
