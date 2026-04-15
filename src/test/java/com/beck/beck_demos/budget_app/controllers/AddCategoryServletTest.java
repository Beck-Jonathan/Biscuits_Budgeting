package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data_fakes.CategoryDAO_Fake;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/******************
 Test Suite For AddCategoryServlet
 Matches structure of AddBudget_Line_ItemTest
 Created By Jonathan Beck 3/3/2026
 ***************/
class AddCategoryServletTest {
  private AddCategoryServlet servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  private MockHttpSession session;

  @BeforeEach
  public void setup() throws ServletException {
    servlet = new AddCategoryServlet();
    MockServletContext servletContext = new MockServletContext();
    servlet.init(new CategoryDAO_Fake());
    request = new MockHttpServletRequest(servletContext);
    response = new MockHttpServletResponse();
    session = new MockHttpSession(servletContext);
  }

  @AfterEach
  public void teardown() {
    servlet = null;
    request = null;
    response = null;
    session = null;
  }

  private void setupValidUser() {
    User user = new User();
    user.setUser_ID("c83ec501-24fd-4ffa-84ad-562318de6132");
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    request.setSession(session);
    session.setAttribute("User_B", user);
  }

  @Test
  public void testDoPostReturnsNegativeOneIfUserNotLoggedIn() throws ServletException, IOException {
    servlet.doPost(request, response);
    assertEquals("-1", response.getContentAsString().trim());
  }

  @Test
  public void testDoPostReturnsNegativeTwoIfCategoryNameInvalid() throws ServletException, IOException {
    setupValidUser();
    request.setParameter("inputcategoryCategory_Name", ""); // Should trigger validation error

    servlet.doPost(request, response);
    assertEquals("-2", response.getContentAsString().trim());
  }

  @Test
  public void testDoPostReturnsNegativeThreeIfColorInvalid() throws ServletException, IOException {
    setupValidUser();
    request.setParameter("inputcategoryCategory_Name", "Dining Out");
    request.setParameter("inputcategoryColor_id", "invalid-color"); // Should trigger validation error

    servlet.doPost(request, response);
    assertEquals("-3", response.getContentAsString().trim());
  }

  @Test
  public void testDoPostReturnsNegativeFourIfParentIdInvalid() throws ServletException, IOException {
    setupValidUser();
    request.setParameter("inputcategoryCategory_Name", "Dining Out");
    request.setParameter("inputcategoryColor_id", "#FF5733");
    request.setParameter("inputcategoryParent_id", ""); // Missing parent

    servlet.doPost(request, response);
    assertEquals("-4", response.getContentAsString().trim());
  }

  @Test
  public void testDoPostSuccessfulInsertionReturnsUUID() throws ServletException, IOException {
    setupValidUser();
    String expectedUuid = "87cd7359-9374-4270-be0c-ef6ffd69ec4d";
    request.setParameter("inputcategoryCategory_Name", "Valid Category");
    request.setParameter("inputcategoryColor_id", "#00FF00");
    request.setParameter("inputcategoryParent_id", "4095f6c1-c326-4916-83af-22ea2a36b102");
    request.setParameter("inputsub_categoryprojection_strategy_ID", "AVG_STRICT");

    servlet.doPost(request, response);

    String result = response.getContentAsString().trim();
    assertEquals(36, result.length(), "Expected a 36-character UUID");
    // If your fake DAO is programmed to return a specific UUID:
    // assertEquals(expectedUuid, result);
  }

  @Test
  public void testDoPostCanHandleDatabaseError() throws ServletException, IOException {
    setupValidUser();
    // Assuming CategoryDAO_Fake is programmed to throw an exception when Name is "DB_ERROR"
    request.setParameter("inputcategoryCategory_Name", "EXCEPTION");
    request.setParameter("inputcategoryColor_id", "#00FF00");
    request.setParameter("inputcategoryParent_id", "4095f6c1-c326-4916-83af-22ea2a36b102");
    request.setParameter("inputsub_categoryprojection_strategy_ID", "AVG_STRICT");

    servlet.doPost(request, response);

    assertEquals("-10", response.getContentAsString().trim());
  }
}