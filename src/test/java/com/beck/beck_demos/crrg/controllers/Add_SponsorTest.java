package com.beck.beck_demos.crrg.controllers;
import java.io.IOException;
import java.util.*;
import com.beck.beck_demos.crrg.data_fakes.Sponsor_DAO_Fake;
import com.beck.beck_demos.crrg.models.Sponsor;
import com.beck.beck_demos.crrg.models.Sponsor_VM;
import com.beck.beck_demos.crrg.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;

import static org.junit.jupiter.api.Assertions.*;

public class Add_SponsorTest {
  private static final String PAGE = "WEB-INF/crrg/Add_Sponsor.jsp";
  Add_Sponsor servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  @BeforeEach
  public void setup() throws ServletException {

    servlet = new Add_Sponsor();
    servlet.init(new Sponsor_DAO_Fake());
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
    String Sponsor_IDError = results.get("sponsorSponsor_IDerror");
    String Tier_IDError = results.get("sponsorTier_IDerror");
    String WebsiteError = results.get("sponsorWebsiteerror");
    String DescriptionError = results.get("sponsorDescriptionerror");
    assertNotEquals("", Sponsor_IDError);
    assertNotNull(Sponsor_IDError);
    assertNotEquals("", Tier_IDError);
    assertNotNull(Tier_IDError);
    assertNotEquals("", WebsiteError);
    assertNotNull(WebsiteError);
    assertNotEquals("", DescriptionError);
    assertNotNull(DescriptionError);
    assertEquals(200, responseStatus);
  }

  @Test
  public void TestAddCanAddWithNoErrorsAndRedirects() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User", user);
    request.setSession(session);
    request.setParameter("inputsponsorSponsor_ID", "TestValue");
    request.setParameter("inputsponsorTier_ID", "TestValue");
    request.setParameter("inputsponsorWebsite", "TestValue.com/picture.jpg");
    request.setParameter("inputsponsorDescription", "TestValue");
    request.setParameter("inputsponsorIs_Active", "true");
    servlet.doPost(request, response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Sponsor_Added = results.get("dbStatus");
    assertEquals(302, responseStatus);
    assertNotNull(Sponsor_Added);
    assertEquals("Sponsor Added", Sponsor_Added);
    assertNotEquals("", Sponsor_Added);
  }

  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new Add_Sponsor();
    servlet.init();

  }

  @Test
  public void testExceptionKeyThrowsException() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("inputsponsorSponsor_ID","EXCEPTION");
    request.setParameter("inputsponsorTier_ID","EXCEPTION");
    request.setParameter("inputsponsorWebsite","http://www.DUPLICATE.com");
    request.setParameter("inputsponsorDescription","EXCEPTION");
    request.setParameter("inputsponsorIs_Active","true");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Sponsor_Added = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Sponsor_Added);
    assertEquals("Sponsor Not Added",Sponsor_Added);
    assertNotEquals("",Sponsor_Added);
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
    request.setParameter("inputsponsorSponsor_ID","DUPLICATE");
    request.setParameter("inputsponsorTier_ID","DUPLICATE");
    request.setParameter("inputsponsorWebsite","http://www.DUPLICATE.com");
    request.setParameter("inputsponsorDescription","DUPLICATE");
    request.setParameter("inputsponsorIs_Active","true");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Sponsor_Added = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Sponsor_Added);
    assertEquals("Sponsor Not Added",Sponsor_Added);
    assertNotEquals("",Sponsor_Added);
  }
}

