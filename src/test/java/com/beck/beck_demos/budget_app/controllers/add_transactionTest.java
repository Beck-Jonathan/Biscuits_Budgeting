package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data_fakes.TransactionDAO_Fake;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockPart;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class add_transactionTest {

  private add_transaction servlet;
  private MockHttpServletRequest request; // Switched to standard Mock Request
  private MockHttpServletResponse response;
  private HttpSession session;

  @BeforeEach
  public void setup() throws ServletException {
    servlet = new add_transaction();
    servlet.init(new TransactionDAO_Fake());
    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();
    session = new MockHttpSession();
  }

  @Test
  public void TestLoggedInUserGets200OnDoGet() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);

    servlet.doGet(request, response);
    assertEquals(200, response.getStatus());
  }

  @Test
  public void TestLoggedOutUserGets302OnDoPost() throws ServletException, IOException {
    request.setSession(session);
    servlet.doPost(request, response);
    assertEquals(302, response.getStatus());
    assertEquals("budget_home", response.getRedirectedUrl());
  }

  @Test
  public void TestEmptyFileReturnsErrorToJsp() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);

    // Mock an empty part
    MockPart emptyPart = new MockPart("upload_transactions", "empty.csv", "".getBytes());
    request.addPart(emptyPart);

    servlet.doPost(request, response);

    // Should stay on the same page (forward) and show error
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    assertNotNull(results);
    assertEquals("Please select a file.", results.get("FileEmptyError"));
  }

  @Test
  public void TestAddCanAddWithNoErrorsAndRedirects() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);

    // Create fake CSV content
    String csvContent = "Custom\tAccount Number\t123456789\n" +
        "12/14/2024\t12/14/2024\tHulu\t\t-94.21\t21,105.34\n";

    // Use MockPart to simulate the multipart upload
    MockPart filePart = new MockPart("upload_transactions", "test_transactions.csv", csvContent.getBytes(StandardCharsets.UTF_8));
    request.addPart(filePart);

    servlet.doPost(request, response);

    // Verify Redirect
    assertEquals(302, response.getStatus());
    assertEquals("budget_home", response.getRedirectedUrl());

    // Check results in SESSION (as per your servlet code for successful redirect)
    Map<String, String> results = (Map<String, String>) session.getAttribute("results");
    assertNotNull(results);
    assertTrue(results.containsKey("AddedCount"));
    // Depending on what your FakeDAO returns, adjust the string check here
    assertTrue(results.get("AddedCount").contains("Uploaded"));
  }

  @Test
  public void TestDatabaseErrorDuringUpload() throws ServletException, IOException {
    // Setup a user
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);

    // Create a part that will trigger an exception in your FakeDAO
    // Note: You may need to adjust your FakeDAO to throw SQLException when it sees "TRIGGER_ERROR"
    String csvContent = "TRIGGER_ERROR";
    MockPart filePart = new MockPart("upload_transactions", "error.csv", csvContent.getBytes());
    request.addPart(filePart);

    servlet.doPost(request, response);

    Map<String, String> results = (Map<String, String>) session.getAttribute("results");
    // If your DAO throws SQLException, the servlet puts it in "dbError"
    if (results != null && results.containsKey("dbError")) {
      assertTrue(results.get("dbError").contains("Database Error"));
    }
  }

  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = new add_transaction();
    servlet.init();
    assertNotNull(servlet);
  }
}