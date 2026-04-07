package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data_fakes.CategoryDAO_Fake;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetCategoryPerformanceServletTest {
  private GetCategoryPerformanceServlet servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  private MockHttpSession session;
  private MockServletContext servletContext;

  @BeforeEach
  public void setup() throws ServletException {
    servlet = new GetCategoryPerformanceServlet();
    servletContext = new MockServletContext();

    // Standard Servlet Init
    MockServletConfig servletConfig = new MockServletConfig(servletContext, "getCategoryPerformance");
    servlet.init(servletConfig);

    // Inject the Fake DAO (Ensure your Fake supports getCategoryPerformance method)
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
  void testDoGet_WithNoUserReturnsNegativeOne() throws IOException {
    // Arrange: No user in session
    request.setSession(session);
    request.setParameter("id", "cat_123");
    request.setParameter("year", "2026");

    // Act
    servlet.doGet(request, response);

    // Assert
    assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
    assertEquals("-1", response.getContentAsString().trim());
  }

  @Test
  void testDoGet_WithInvalidYearReturnsEmptyArray() throws IOException {
    // Arrange: Logged in user but garbage year
    User user = new User();
    user.setRoles(new ArrayList<>(List.of("User")));
    session.setAttribute("User_B", user);
    request.setSession(session);

    request.setParameter("id", "cat_123");
    request.setParameter("year", "not_a_year");

    // Act
    servlet.doGet(request, response);

    // Assert
    // Per your servlet logic, a NumberFormatException returns "[]"
    assertEquals("[]", response.getContentAsString().trim());
  }

  @Test
  void testDoGet_OutOfRangeYearReturnsEmptyArray() throws IOException {
    // Arrange: Year 2099 is outside your 2000-2050 guard
    User user = new User();
    user.setRoles(new ArrayList<>(List.of("User")));
    session.setAttribute("User_B", user);
    request.setSession(session);

    request.setParameter("id", "cat_123");
    request.setParameter("year", "2099");

    // Act
    servlet.doGet(request, response);

    // Assert
    assertEquals("[]", response.getContentAsString().trim());
  }

  @Test
  void testDoGet_SuccessfulPerformanceReturn() throws IOException {
    // Arrange: Valid User, Role, ID, and Year
    User user = new User();
    user.setUser_ID("315e87c5-657b-470d-8c16-28c585ed02ef");
    user.setRoles(new ArrayList<>(List.of("User")));
    session.setAttribute("User_B", user);
    request.setSession(session);

    request.setParameter("id", "house_renovations_1904");
    request.setParameter("year", "2025");

    // Act
    servlet.doGet(request, response);

    // Assert
    String content = response.getContentAsString();
    assertEquals("application/json;charset=UTF-8", response.getContentType());

    // Verify JSON structure matches your manual StringBuilder output
    assertTrue(content.startsWith("["), "Should be a JSON array");
    assertTrue(content.contains("\"period\""), "Should contain period field");
    assertTrue(content.contains("\"actualValue\""), "Should contain actualValue field");
    assertTrue(content.contains("\"budgetedValue\""), "Should contain budgetedValue field");

    // Ensure no error codes leaked through
    assertNotEquals("-1", content.trim());
    assertNotEquals("-2", content.trim());
  }
}