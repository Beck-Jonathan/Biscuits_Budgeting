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

public class Edit_ContributorTest {
  private static final String PAGE = "WEB-INF/crrg/Edit_Contributor.jsp";
  Edit_Contributor servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  @BeforeEach
  public void setup() throws ServletException {

    servlet = new Edit_Contributor();
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
  public void testGetOneContributorGetsOneContributor_ID() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User", user);
    Integer Contributor_ID = 2;
    request.setParameter("contributorid", Contributor_ID.toString());
    request.setSession(session);
    servlet.doGet(request, response);
    Contributor contributor = (Contributor) session.getAttribute("contributor");
    assertNotNull(contributor);
    assertEquals(Contributor_ID, contributor.getContributor_ID());
  }

  @Test
  public void testGetOneContributorCanFailContributor_ID() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User", user);
    Integer Contributor_ID = -1;
    request.setParameter("contributorid", Contributor_ID.toString());
    request.setSession(session);
    servlet.doGet(request, response);
    Contributor contributor = (Contributor) session.getAttribute("contributor");
    assertNull(contributor.getFirst_Name());
  }

  @Test
  public void TestUpdateCanAddWithNoErrorsAndRedirects() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User", user);
    request.setSession(session);
//to set the old Contributor
    Contributor contributor = new Contributor();
    contributor.setContributor_ID(43);
    contributor.setFirst_Name("testContributor");
    contributor.setLast_Name("testContributor");
    contributor.setEmail("testContributor@gmail.com");
    session.setAttribute("contributor", contributor);
//create a new albums parameters
    request.setParameter("inputcontributorFirst_Name", "TestValue");
    request.setParameter("inputcontributorLast_Name", "TestValue");
    request.setParameter("inputcontributorEmail", "TestValue@gmail.com");
    servlet.doPost(request, response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Contributor_Updated = results.get("dbStatus");
    assertEquals(302, responseStatus);
    assertNotNull(Contributor_Updated);
    assertEquals("Contributor updated", Contributor_Updated);
    assertNotEquals("", Contributor_Updated);
  }

  @Test
  public void TestUpdateHasErrorsForEachFiledAndKeepsOnSamePage() throws ServletException, IOException {
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
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new Edit_Contributor();
    servlet.init();
  }

  @Test
  public void testUpdateCanReturnZero() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
//to set the old Contributor
    Contributor contributor = new Contributor();
    contributor.setContributor_ID(43);
    contributor.setFirst_Name("DUPLICATE");
    contributor.setLast_Name("DUPLICATE");
    contributor.setEmail("DUPLICATE@gmail.com");
    session.setAttribute("contributor",contributor);
//create a new albums parameters
    request.setParameter("inputcontributorFirst_Name","DUPLICATE");
    request.setParameter("inputcontributorLast_Name","DUPLICATE");
    request.setParameter("inputcontributorEmail","DUPLICATE@gmail.com");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Contributor_Updated = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Contributor_Updated);
    assertEquals("Contributor Not Updated",Contributor_Updated);
    assertNotEquals("",Contributor_Updated);
  }
  @Test
  public void testUpdateCanThrowSQLException() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
//to set the old Contributor
    Contributor contributor = new Contributor();
    contributor.setContributor_ID(43);
    contributor.setFirst_Name("EXCEPTION");
    contributor.setLast_Name("EXCEPTION");
    contributor.setEmail("EXCEPTION@gmail.com");
    session.setAttribute("contributor",contributor);
//create a new albums parameters
    request.setParameter("inputcontributorFirst_Name","EXCEPTION");
    request.setParameter("inputcontributorLast_Name","EXCEPTION");
    request.setParameter("inputcontributorEmail","EXCEPTION@gmail.com");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Contributor_Updated = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Contributor_Updated);
    assertNotEquals("",Contributor_Updated);
    assertEquals("Contributor Not Updated",Contributor_Updated);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }

}