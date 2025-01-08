package com.beck.beck_demos.crrg.controllers;
import java.io.IOException;
import java.util.*;
import com.beck.beck_demos.crrg.data_fakes.Contributor_DAO_Fake;
import com.beck.beck_demos.crrg.models.Contributor;
import com.beck.beck_demos.crrg.models.Contributor_VM;
import com.beck.beck_demos.crrg.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;

import static org.junit.jupiter.api.Assertions.*;

public class Add_ContributorTest {
  private static final String PAGE = "WEB-INF/crrg/Add_Contributor.jsp";
  Add_Contributor servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  @BeforeEach
  public void setup() throws ServletException {

    servlet = new Add_Contributor();
    servlet.init(new Contributor_DAO_Fake());
    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();
    session = new MockHttpSession();
    rd = new MockRequestDispatcher(PAGE);
  }

  @AfterEach
  public void teardown() {
    servlet = null;
    request = null;
    response = null;
    session = null;
    rd = null;
  }

  @Test
  public void TestLoggedInUserGets200OnDoGet() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User", user);
    request.setSession(session);
    servlet.doGet(request, response);
    int status = response.getStatus();
    assertEquals(200, status);
  }

  @Test
  public void TestLoggedInUserGets200OnDoPost() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User", user);
    request.setSession(session);
    servlet.doPost(request, response);
    int status = response.getStatus();
    assertEquals(200, status);
  }

  @Test
  public void TestLoggedOutUserGets302OnDoGet() throws ServletException, IOException {
    request.setSession(session);
    servlet.doGet(request, response);
    int status = response.getStatus();
    assertEquals(302, status);
  }

  @Test
  public void TestLoggedOutUserGets302OnDoPost() throws ServletException, IOException {
    request.setSession(session);
    servlet.doPost(request, response);
    int status = response.getStatus();
    assertEquals(302, status);
  }

  @Test
  public void TestAddHasErrorsForEachFieldAndKeepsOnSamePage() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User", user);
    request.setSession(session);
    servlet.doPost(request, response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String First_NameError = results.get("contributorFirst_Nameerror");
    String Last_NameError = results.get("contributorLast_Nameerror");
    String EmailError = results.get("contributorEmailerror");
    assertNotEquals("", First_NameError);
    assertNotNull(First_NameError);
    assertNotEquals("", Last_NameError);
    assertNotNull(Last_NameError);
    assertNotEquals("", EmailError);
    assertNotNull(EmailError);
    assertEquals(200, responseStatus);
  }

  @Test
  public void TestAddCanAddWithNoErrorsAndRedirects() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User", user);
    request.setSession(session);
    request.setParameter("inputcontributorFirst_Name", "TestValue");
    request.setParameter("inputcontributorLast_Name", "TestValue");
    request.setParameter("inputcontributorEmail", "TestValue@gmail.com");
    servlet.doPost(request, response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Contributor_Added = results.get("dbStatus");
    assertEquals(302, responseStatus);
    assertNotNull(Contributor_Added);
    assertEquals("Contributor Added", Contributor_Added);
    assertNotEquals("", Contributor_Added);
  }

  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new Add_Contributor();
    servlet.init();

  }

  @Test
  public void testExceptionKeyThrowsException() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("inputcontributorFirst_Name","EXCEPTION");
    request.setParameter("inputcontributorLast_Name","EXCEPTION");
    request.setParameter("inputcontributorEmail","EXCEPTION@aol.com");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Contributor_Added = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Contributor_Added);
    assertEquals("Contributor Not Added",Contributor_Added);
    assertNotEquals("",Contributor_Added);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }
  @Test
  public void testDuplicateKeyReturnsZero() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("inputcontributorFirst_Name","DUPLICATE");
    request.setParameter("inputcontributorLast_Name","DUPLICATE");
    request.setParameter("inputcontributorEmail","EXCEPTION@aol.com");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Contributor_Added = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Contributor_Added);
    assertEquals("Contributor Not Added",Contributor_Added);
    assertNotEquals("",Contributor_Added);
  }

}

