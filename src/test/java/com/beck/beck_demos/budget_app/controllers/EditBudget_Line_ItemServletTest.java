package com.beck.beck_demos.budget_app.controllers;


import java.io.IOException;
import java.util.*;
import com.beck.beck_demos.budget_app.data_fakes.Budget_Line_ItemDAO_Fake;
import com.beck.beck_demos.budget_app.models.Budget_Line_Item;
import com.beck.beck_demos.budget_app.models.Budget_Line_ItemVM;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

public class EditBudget_Line_ItemServletTest {
  private static final String PAGE="WEB-INF/budget_app/Edit_budget_line_item.jsp";
  EditBudget_Line_ItemServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new EditBudget_Line_ItemServlet();
    MockServletContext servletContext = new MockServletContext();
    MockServletConfig servletConfig = new MockServletConfig(servletContext, "Edit_budget_line_item");
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


  /**
   <p> Test That a logged in user gets a 200 status on doPost </p>
   */
  @Test
  public void TestLoggedInUserGets20nDoPostWithNobudget_line_itemSet() throws ServletException, IOException{
   setupValidUser("User");
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(200,status);
    assertEquals("-2", response.getContentAsString().trim());
  }



  /**
   <p> Tests That the user will received a 302 status on doPost if they are logged out  </p>
   */
  @Test
  public void TestLoggedOutUserGets200OnDoPostAndSessionIsInvalidated() throws ServletException, IOException{
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(200,status);

    session = request.getSession(false);
    assertNull(session);
    assertEquals("-1", response.getContentAsString().trim());
  }



  /**
   <p> Test that a user in the wrong role will get a 302 on a doPost </p>
   */
  @Test
  public void TestWrongRoleGetsNegative1onDoPost() throws ServletException, IOException{
    setupValidUser("WrongRole");
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(200,status);
    assertEquals("-1", response.getContentAsString().trim());
  }

  /**
   <p> Test that a logged in user is able to retrieve a specific one of the budget_line_item objects. </p>
   */
  @Test
  public void testGetOnebudget_line_itemGetsOnebudget_line_item_id() throws ServletException, IOException{
    setupValidUser("User");
    String budget_line_item_id= "evVBHqXgFUAdBuZPuVVYWrvmUGJQGkKJHQXo";
    request.setParameter("inputbudget_line_itembudget_line_item_id",budget_line_item_id);

    servlet.doPost(request,response);

    assertEquals("-3", response.getContentAsString().trim());
  }

  /**
   <p> Test that getting one budget_line_item can fail. </p>
   */
  @Test
  public void testGetOnebudget_line_itemCanFailAndUserIsRedirected() throws ServletException, IOException{
    setupValidUser("User");
    String budget_line_item_id= null;
    request.setParameter("inputbudget_line_itembudget_line_item_id",budget_line_item_id);

    servlet.doPost(request,response);

    assertEquals("-2", response.getContentAsString().trim());
  }

  /**
   <p> Test that we are able to update budget_line_item objects if there are no errors in the input fields </p>
   */
  @Test
  public void TestUpdateCanAddWithNoErrorAndSendsResponseCode1() throws ServletException, IOException{
    setupValidUser("User");

//create a new budget_line_items parameters
    request.setParameter("inputbudget_line_itembudget_line_item_id","yLhmvdKflpQXUhKJRPyCwnlbDYNkrTfDSXeK");

    request.setParameter("inputbudget_line_itemcolor_id","FFFFFF");
    request.setParameter("inputbudget_line_itemname","TestValue");
    request.setParameter("inputbudget_line_itemdetails","TestValue");
    request.setParameter("inputbudget_line_itemline_item_date","2026-02-02");
    request.setParameter("inputbudget_line_itemamount","243.6");
    request.setParameter("inputbudget_line_itembudget_line_type_id","TestValue");
    request.setParameter("inputbudget_line_itembudget_line_status_id","TestValue");
    request.setParameter("inputbudget_line_itemtransaction_id","TestValue");

    servlet.doPost(request,response);


    assertEquals("1", response.getContentAsString().trim());
  }



  /**
   <p> Test that a duplicate primary key will get caught when trying to update budget_line_item objects. </p>
   */
  @Test
  public void testUpdateCanReturnZero() throws ServletException, IOException{
    setupValidUser("User");

//create a new budget_line_items parameters
    request.setParameter("inputbudget_line_itembudget_line_item_id","DUPLICATEDUPLICATEDUPLICATEDUPLICATE");
    request.setParameter("inputbudget_line_itembudget_id","TestValue");
    request.setParameter("inputbudget_line_itemcolor_id","FFFFFF");
    request.setParameter("inputbudget_line_itemname","DUPLICATE");
    request.setParameter("inputbudget_line_itemdetails","TestValue");
    request.setParameter("inputbudget_line_itemline_item_date","2026-02-02");
    request.setParameter("inputbudget_line_itemamount","243.6");
    request.setParameter("inputbudget_line_itembudget_line_type_id","TestValue");
    request.setParameter("inputbudget_line_itembudget_line_status_id","TestValue");

    servlet.doPost(request,response);


    assertEquals("0", response.getContentAsString().trim());
  }

  /**
   <p> Tests That the user will received a error messages for each incorrectly filled out field on the form. </p>
   */
  @Test
  public void testUpdateCanThrowSQLException() throws ServletException, IOException{
    setupValidUser("User");

//create a new budget_line_items parameters
    request.setParameter("inputbudget_line_itembudget_line_item_id","EXCEPTIONEXCEPTIONEXCEPTIONEXCEPTION");
    request.setParameter("inputbudget_line_itembudget_id","TestValue");
    request.setParameter("inputbudget_line_itemcolor_id","FFFFFF");
    request.setParameter("inputbudget_line_itemname","TestValue");
    request.setParameter("inputbudget_line_itemdetails","TestValue");
    request.setParameter("inputbudget_line_itemline_item_date","2026-02-02");
    request.setParameter("inputbudget_line_itemamount","243.6");
    request.setParameter("inputbudget_line_itembudget_line_type_id","TestValue");
    request.setParameter("inputbudget_line_itembudget_line_status_id","TestValue");

    servlet.doPost(request,response);


    assertEquals("-10", response.getContentAsString().trim());
  }

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new EditBudget_Line_ItemServlet();
    servlet.init();
  }

}

