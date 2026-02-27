package com.beck.beck_demos.budget_app.controllers;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import com.beck.beck_demos.budget_app.data_fakes.BudgetDAO_Fake;
import com.beck.beck_demos.budget_app.models.Budget;
import com.beck.beck_demos.budget_app.models.Budget_VM;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class DeleteBudgetServletTest {
  private static final String PAGE="WEB-INF/budget_app/Delete_budget.jsp";
  DeleteBudgetServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new DeleteBudgetServlet();
    MockServletContext servletContext = new MockServletContext();
    MockServletConfig servletConfig = new MockServletConfig(servletContext, "Delete_budget");
    servlet.init(servletConfig);
    servlet.init(new BudgetDAO_Fake());
    request =  new MockHttpServletRequest(servletContext);
    response = new MockHttpServletResponse();
    session = new MockHttpSession(servletContext);
    rd = new MockRequestDispatcher(PAGE);
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
    user.setUser_ID("9540263f-5ffd-4d61-9c48-2c6c96ffc06e");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doGet(request,response);
    String status = response.getContentAsString();
    assertEquals(200, response.getStatus());
    assertEquals("-2", status);
  }

  /**
   <p> Tests That the user will received a 302 status on doGet if they are logged out  </p>
   */
  @Test
  public void TestLoggedOutUserGets302OnDoGet() throws ServletException, IOException{
    request.setSession(session);
    servlet.doGet(request,response);
    String status = response.getContentAsString();
    assertEquals(200, response.getStatus());
    assertEquals("-1", status);

    session = request.getSession(false);
    assertNull(session);
  }

  /**
   <p> Test that the deactivation servlet can deactivate a budget </p>
   */
  @Test
  public void TestDeleteCanDelete() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("9540263f-5ffd-4d61-9c48-2c6c96ffc06e");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("budgetid","DLpfWxZUBCbqtdngpApNUBMxKxgaVkEondmy");

    servlet.doGet(request,response);
    assertEquals(200, response.getStatus());
    assertEquals("1", response.getContentAsString());


  }

  /**
   <p> Test that a user in the wrong role will get a 302 on a doGet </p>
   */
  @Test
  public void TestWrongRoleGets302onDoGet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("WrongRole");
    user.setUser_ID("9540263f-5ffd-4d61-9c48-2c6c96ffc06e");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doGet(request,response);;
    assertEquals(200, response.getStatus());
    assertEquals("-1", response.getContentAsString());

    session = request.getSession(false);
    assertNull(session);
  }

  /**
   <p> Test that the deactivate servlet will fail if the budget is already insactive. </p>
   */
  @Test
  public void TestDeleteFailIfNotExist() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("9540263f-5ffd-4d61-9c48-2c6c96ffc06e");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("budgetid","DLpfWxZUBCbqtdngpApNUBMxKxgaVkEondss");
    request.setParameter("mode","0");
    servlet.doGet(request,response);
    assertEquals(200, response.getStatus());
    assertEquals("-2", response.getContentAsString());

    session = request.getSession(false);

  }


  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new DeleteBudgetServlet();
    servlet.init();
  }

}


