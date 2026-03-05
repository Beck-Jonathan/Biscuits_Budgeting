package com.beck.beck_demos.budget_app.controllers;
import java.io.IOException;
import java.util.*;
import com.beck.beck_demos.budget_app.data_fakes.Budget_Line_ItemDAO_Fake;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteBudget_Line_ItemServletTest {
  private static final String PAGE="WEB-INF/budget_app/Delete_budget_line_item.jsp";
  DeleteBudget_Line_ItemServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new DeleteBudget_Line_ItemServlet();
    MockServletContext servletContext = new MockServletContext();
    MockServletConfig servletConfig = new MockServletConfig(servletContext, "Delete_budget_line_item");
    servlet.init(servletConfig);
    servlet.init(new Budget_Line_ItemDAO_Fake());
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
    setupValidUser("User");
    servlet.doPost(request,response);

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
    servlet.doPost(request,response);


    String status = response.getContentAsString();
    assertEquals(200, response.getStatus());
    assertEquals("-1", status);
  }

  /**
   <p> Test that the deactivation servlet can deactivate a budget_line_item </p>
   */
  @Test
  public void TestDeleteCanDelete() throws ServletException, IOException {
    setupValidUser("User");
    request.setParameter("budget_line_itemid","evVBHqXgFUAdBuZPuVVYWrvmUGJQGkKJHQXo");
    request.setParameter("mode","0");
    servlet.doPost(request,response);
    String status = response.getContentAsString();
    assertEquals(200, response.getStatus());
    assertEquals("1", status);
  }

  /**
   <p> Test that a user in the wrong role will get a 302 on a doGet </p>
   */
  @Test
  public void TestWrongRoleGets302onDoGet() throws ServletException, IOException{
    setupValidUser("User");
    servlet.doPost(request,response);
    String status = response.getContentAsString();
    assertEquals(200, response.getStatus());
    assertEquals("-2", status);
  }

  /**
   <p> Test that the deactivate servlet will fail if the budget_line_item is already insactive. </p>
   */
  @Test
  public void TestDeactivateFailIfAlreadyFalse() throws ServletException, IOException {
    setupValidUser("User");
    request.setParameter("budget_line_itemid","evVBHqXgFUAdBuZPuVVYWrvmUGJQGkKJHQXo");

    // Call 1
    servlet.doPost(request, response);

    // Re-instantiate response for Call 2
    response = new MockHttpServletResponse();

    // Call 2
    servlet.doPost(request, response);
    String status = response.getContentAsString();

    assertEquals(200, response.getStatus());
    assertEquals("-3", status);
  }

  /**
   <p> Test something, needs more specificity </p>
   */
  @Test
  public void TestDeActivateCanFailIfKeyDoesNotExist() throws ServletException, IOException {
    setupValidUser("User");
    request.setParameter("budget_line_itemid","evVBHqXgFUAdBuZPuVVYWrvmUGJQGkKJHsss");

    servlet.doPost(request,response);
    String status = response.getContentAsString();
    assertEquals(200, response.getStatus());
    assertEquals("-3", status);
  }







  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new DeleteBudget_Line_ItemServlet();
    servlet.init();
  }

  /**
   * Helper method to set up a valid user session
   */
  private void setupValidUser(String Role) {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add(Role);
    user.setRoles(roles);
    request.setSession(session);
    session.setAttribute("User_B", user);
  }

}

