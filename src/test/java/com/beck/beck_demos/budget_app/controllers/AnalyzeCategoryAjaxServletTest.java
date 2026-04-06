package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data_fakes.CategoryDAO_Fake;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnalyzeCategoryAjaxServletTest {
  private static final String PAGE = "WEB-INF/budget_app/All_projectionanalysisdto.jsp";
  AnalyzeCategoryAjaxServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  MockHttpSession session;
  MockRequestDispatcher rd;

  @BeforeEach
  public void setup() throws ServletException {
    servlet = new AnalyzeCategoryAjaxServlet();
    MockServletContext servletContext = new MockServletContext();
    MockServletConfig servletConfig = new MockServletConfig(servletContext, "AnalyzeCategoryAJAX");
    servlet.init(servletConfig);
    // Inject the Fake DAO
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

  @Test
  void testDoGet_WithNoUserReturnsNegativeOne() throws IOException, ServletException {
    // Arrange: Session has no user
    request.setSession(session);
    request.setParameter("subcatId", "123");

    // Act
    servlet.doGet(request, response);

    // Assert
    assertEquals("application/json;charset=UTF-8", response.getContentType());
    assertEquals("-1", response.getContentAsString().trim());
  }

  @Test
  void testDoGet_WithInvalidRoleReturnsNegativeOne() throws IOException, ServletException {
    // Arrange: User exists but is not a "User"
    User user = new User();
    user.setRoles(new ArrayList<>(List.of("Admin")));
    session.setAttribute("User_B", user);
    request.setSession(session);

    // Act
    servlet.doGet(request, response);

    // Assert
    assertEquals("-1", response.getContentAsString().trim());
  }

  @Test
  void testDoGet_SuccessfulDataReturn() throws IOException, ServletException {
    // Arrange: Valid User and Role
    User user = new User();
    user.setUser_ID("4cfb9c39-78fa-42dc-a620-0d0bd6c2fc0b");
    user.setRoles(new ArrayList<>(List.of("User")));
    session.setAttribute("User_B", user);
    request.setSession(session);
    request.setParameter("subcatId", "lZoleuasarwCfcmdPWeDgyapFwTISoPKgqXc");

    // Act
    servlet.doGet(request, response);

    // Assert
    String content = response.getContentAsString();
    assertEquals("application/json;charset=UTF-8", response.getContentType());
    assertEquals("UTF-8", response.getCharacterEncoding());

    // Check for JSON structure
    assertTrue(content.startsWith("["), "Should start with a JSON array");
    assertTrue(content.contains("\"monthDate\""), "Should contain monthDate keys");
    assertTrue(content.contains("\"historicalTruth\""), "Should contain data keys");

    // Check that we aren't getting the error codes
    assertNotEquals("-1", content.trim());
    assertNotEquals("-2", content.trim());
  }

  @Test
  void testDoGet_HandlesNullParametersGracefully() throws IOException, ServletException {
    // Arrange: User is logged in but subcatId is missing
    User user = new User();
    user.setRoles(new ArrayList<>(List.of("User")));
    session.setAttribute("User_B", user);
    request.setSession(session);
    // No subcatId parameter set

    // Act
    servlet.doGet(request, response);

    // Assert: Depending on your DAO_Fake, it should likely return an empty list [] or a specific set
    assertNotNull(response.getContentAsString());
  }
}