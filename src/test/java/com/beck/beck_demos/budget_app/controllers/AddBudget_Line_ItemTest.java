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

class AddBudget_Line_ItemTest {
  private static final String PAGE="WEB-INF/budget_app/Add_budget_line_item.jsp";
  AddBudget_Line_Item servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new AddBudget_Line_Item();
    MockServletContext servletContext = new MockServletContext();
    MockServletConfig servletConfig = new MockServletConfig(servletContext, "Add_budget_line_item");
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
  private void setupValidUser() {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    request.setSession(session);
    session.setAttribute("User_B", user);
  }

  @Test
  public void testDoPostReturnsNegativeOneIfUserNotLoggedIn() throws ServletException, IOException {
    // No user in session
    servlet.doPost(request, response);
    assertEquals("-1", response.getContentAsString().trim());
  }

  @Test
  public void testDoPostReturnsNegativeOneIfUserLacksRole() throws ServletException, IOException {
    User user = new User();
    user.setRoles(new ArrayList<>()); // Empty roles
    session.setAttribute("User_B", user);
    request.setSession(session);

    servlet.doPost(request, response);
    assertEquals("-1", response.getContentAsString().trim());
  }
  @Test
  public void testDoPostReturnsNegativeTwoIfBudgetIdInvalid() throws ServletException, IOException {
    setupValidUser();
    // Assuming setbudget_id throws an exception for null or empty strings
    request.setParameter("inputbudget_line_itembudget_id", "");

    servlet.doPost(request, response);
    assertEquals("-2", response.getContentAsString().trim());
  }

  @Test
  public void testDoPostReturnsNegativeThreeIfColorIdInvalid() throws ServletException, IOException {
    setupValidUser();
    // Valid budget id but invalid color (too short based on your previous logic)
    request.setParameter("inputbudget_line_itembudget_id", "b64c4a8b-2b0b-4ad3-bfb9-d44c872d5db9");
    request.setParameter("inputbudget_line_itemcolor_id", "123"); // Should trigger exception

    servlet.doPost(request, response);
    assertEquals("-3", response.getContentAsString().trim());
  }

  @Test
  public void testDoPostReturnsNegativeFourIfNameInvalid() throws ServletException, IOException {
    setupValidUser();
    request.setParameter("inputbudget_line_itembudget_id", "b64c4a8b-2b0b-4ad3-bfb9-d44c872d5db9");
    request.setParameter("inputbudget_line_itemcolor_id", "FF0000");

    request.setParameter("inputbudget_line_itemname", "");

    servlet.doPost(request, response);
    assertEquals("-4", response.getContentAsString().trim());
  }

  @Test
  public void testDoPostReturnsNegativeFiveIfDetailsInvalid() throws ServletException, IOException {
    setupValidUser();
    request.setParameter("inputbudget_line_itembudget_id", "b64c4a8b-2b0b-4ad3-bfb9-d44c872d5db9");
    request.setParameter("inputbudget_line_itemcolor_id", "FF0000");
    request.setParameter("inputbudget_line_itemname", "Groceries");
    // Assuming setdetails has a character limit (e.g., > 255) that triggers an exception



    servlet.doPost(request, response);
    assertEquals("-5", response.getContentAsString().trim());
  }

  @Test
  public void testDoPostReturnsNegativeSixIfDateInvalid() throws ServletException, IOException {
    setupValidUser();
    request.setParameter("inputbudget_line_itembudget_id", "b64c4a8b-2b0b-4ad3-bfb9-d44c872d5db9");
    request.setParameter("inputbudget_line_itemcolor_id", "FF0000");
    request.setParameter("inputbudget_line_itemname", "Groceries");
    request.setParameter("inputbudget_line_itemdetails", "Weekly trip");
    request.setParameter("inputbudget_line_itemline_item_date", "not-a-date"); // Invalid format

    servlet.doPost(request, response);
    assertEquals("-6", response.getContentAsString().trim());
  }

  @Test
  public void testDoPostReturnsNegativeSevenIfAmountNotNumeric() throws ServletException, IOException {
    setupValidUser();
    request.setParameter("inputbudget_line_itembudget_id", "b64c4a8b-2b0b-4ad3-bfb9-d44c872d5db9");
    request.setParameter("inputbudget_line_itemcolor_id", "FF0000");
    request.setParameter("inputbudget_line_itemname", "Groceries");
    request.setParameter("inputbudget_line_itemdetails", "Weekly trip");
    request.setParameter("inputbudget_line_itemline_item_date", "2026-03-03");
    request.setParameter("inputbudget_line_itemamount", "abc"); // Not a double

    servlet.doPost(request, response);
    assertEquals("-7", response.getContentAsString().trim());
  }

  @Test
  public void testDoPostReturnsNegativeEightIfTypeInvalid() throws ServletException, IOException {
    setupValidUser();
    request.setParameter("inputbudget_line_itembudget_id", "b64c4a8b-2b0b-4ad3-bfb9-d44c872d5db9");
    request.setParameter("inputbudget_line_itemcolor_id", "FF0000");
    request.setParameter("inputbudget_line_itemname", "Groceries");
    request.setParameter("inputbudget_line_itemdetails", "Weekly food");
    request.setParameter("inputbudget_line_itemline_item_date", "2026-03-03");
    request.setParameter("inputbudget_line_itemamount", "50.00");
    // Assuming setbudget_line_type_id validates against a specific list
    request.setParameter("inputbudget_line_itembudget_line_type_id", "");

    servlet.doPost(request, response);
    assertEquals("-8", response.getContentAsString().trim());
  }

  @Test
  public void testDoPostReturnsNegativeNineIfStatusInvalid() throws ServletException, IOException {
    setupValidUser();
    request.setParameter("inputbudget_line_itembudget_id", "b64c4a8b-2b0b-4ad3-bfb9-d44c872d5db9");
    request.setParameter("inputbudget_line_itemcolor_id", "FF0000");
    request.setParameter("inputbudget_line_itemname", "Groceries");
    request.setParameter("inputbudget_line_itemdetails", "Weekly food");
    request.setParameter("inputbudget_line_itemline_item_date", "2026-03-03");
    request.setParameter("inputbudget_line_itemamount", "50.00");
    request.setParameter("inputbudget_line_itembudget_line_type_id", "Expense");
    // Assuming setbudget_line_status_id validates against a specific list
    request.setParameter("inputbudget_line_itembudget_line_status_id", "");

    servlet.doPost(request, response);
    assertEquals("-9", response.getContentAsString().trim());
  }

  @Test
  public void testDoPostSuccessfulInsertionReturnsPositiveResult() throws ServletException, IOException {
    setupValidUser();
    // Fill all required fields with valid data
    request.setParameter("inputbudget_line_itembudget_id", "b64c4a8b-2b0b-4ad3-bfb9-d44c872d5db9");
    request.setParameter("inputbudget_line_itemcolor_id", "FF0000");
    request.setParameter("inputbudget_line_itemname", "Rent");
    request.setParameter("inputbudget_line_itemdetails", "Monthly Rent");
    request.setParameter("inputbudget_line_itemline_item_date", "2026-03-03");
    request.setParameter("inputbudget_line_itemamount", "1200.00");
    request.setParameter("inputbudget_line_itembudget_line_type_id", "Expense");
    request.setParameter("inputbudget_line_itembudget_line_status_id", "Planned");

    servlet.doPost(request, response);

    // Convert response to int to verify it's greater than 0 (the new ID)
    String result = response.getContentAsString().trim();
    assertTrue(result.equals("beadbd90-28a3-4005-a8df-9772238ead4b"), "Expected a this uuid: \"beadbd90-28a3-4005-a8df-9772238ead4b\"");
  }
  @Test
  public void testDoPostCanHandleDataBaseError() throws ServletException, IOException {
    setupValidUser();
    // Fill all required fields with valid data
    request.setParameter("inputbudget_line_itembudget_id", "b64c4a8b-2b0b-4ad3-bfb9-d44c872d5db9");
    request.setParameter("inputbudget_line_itemcolor_id", "FF0000");
    request.setParameter("inputbudget_line_itemname", "EXCEPTION");
    request.setParameter("inputbudget_line_itemdetails", "Monthly Rent");
    request.setParameter("inputbudget_line_itemline_item_date", "2026-03-03");
    request.setParameter("inputbudget_line_itemamount", "1200.00");
    request.setParameter("inputbudget_line_itembudget_line_type_id", "Expense");
    request.setParameter("inputbudget_line_itembudget_line_status_id", "Planned");

    servlet.doPost(request, response);

    // Convert response to int to verify it's greater than 0 (the new ID)
    int result = Integer.parseInt(response.getContentAsString().trim());
    assertTrue(result ==-10);
  }
  @Test
  public void testDoPostCanHandleDuplicateEntryBeingRejected() throws ServletException, IOException {
    setupValidUser();
    // Fill all required fields with valid data
    request.setParameter("inputbudget_line_itembudget_id", "b64c4a8b-2b0b-4ad3-bfb9-d44c872d5db9");
    request.setParameter("inputbudget_line_itemcolor_id", "FF0000");
    request.setParameter("inputbudget_line_itemname", "DUPLICATE");
    request.setParameter("inputbudget_line_itemdetails", "Monthly Rent");
    request.setParameter("inputbudget_line_itemline_item_date", "2026-03-03");
    request.setParameter("inputbudget_line_itemamount", "1200.00");
    request.setParameter("inputbudget_line_itembudget_line_type_id", "Expense");
    request.setParameter("inputbudget_line_itembudget_line_status_id", "Planned");

    servlet.doPost(request, response);

    // Convert response to int to verify it's greater than 0 (the new ID)
    int result = Integer.parseInt(response.getContentAsString().trim());
    assertTrue(result ==0 );
  }

}

