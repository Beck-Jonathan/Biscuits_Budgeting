package com.beck.beck_demos.crrg.controllers;

import java.io.IOException;
import java.sql.SQLException;
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

public class Edit_SponsorTest {
  private static final String PAGE = "WEB-INF/crrg/Edit_Sponsor.jsp";
  Edit_Sponsor servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  @BeforeEach
  public void setup() throws ServletException, SQLException {

    servlet = new Edit_Sponsor();
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
  public void testGetOneSponsorGetsOneSponsor_ID() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User", user);
    String Sponsor_ID = "nlSjPWct";
    request.setParameter("sponsorid", Sponsor_ID);
    request.setSession(session);
    servlet.doGet(request, response);
    Sponsor_VM sponsor = (Sponsor_VM) session.getAttribute("sponsor");
    assertNotNull(sponsor);
    assertEquals(Sponsor_ID, sponsor.getSponsor_ID());
  }

  @Test
  public void testGetOneSponsorCanFailSponsor_ID() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User", user);
    String Sponsor_ID = null;
    request.setParameter("sponsorid", Sponsor_ID);
    request.setSession(session);
    servlet.doGet(request, response);
    Sponsor sponsor = (Sponsor) session.getAttribute("sponsor");
    assertNull(sponsor.getTier_ID());
  }

  @Test
  public void TestUpdateCanAddWithNoErrorsAndRedirects() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User", user);
    request.setSession(session);
//to set the old Sponsor
    Sponsor sponsor = new Sponsor();
    sponsor.setSponsor_ID("nlSjPWct");
    sponsor.setTier_ID("testSponsor");
    sponsor.setWebsite("testSponsor");
    sponsor.setDescription("testSponsor");
    sponsor.setIs_Active(true);
    session.setAttribute("sponsor", sponsor);
//create a new albums parameters
    request.setParameter("inputsponsorSponsor_ID", "TestValue");
    request.setParameter("inputsponsorTier_ID", "TestValue");
    request.setParameter("inputsponsorWebsite", "TestValue.com/image");
    request.setParameter("inputsponsorDescription", "TestVa.com");
    request.setParameter("inputsponsorIs_Active", "true");
    servlet.doPost(request, response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Sponsor_Updated = results.get("dbStatus");
    assertEquals(302, responseStatus);
    assertNotNull(Sponsor_Updated);
    assertEquals("Sponsor updated", Sponsor_Updated);
    assertNotEquals("", Sponsor_Updated);
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
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new Edit_Sponsor();
    servlet.init();
  }
  @Test
  public void testUpdateCanReturnZero() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
//to set the old Sponsor
    Sponsor sponsor = new Sponsor();
    sponsor.setSponsor_ID("DUPLICATE");
    sponsor.setTier_ID("DUPLICATE");
    sponsor.setWebsite("Http://DUPLICATE.com");
    sponsor.setDescription("DUPLICATE");
    sponsor.setIs_Active(true);
    session.setAttribute("sponsor",sponsor);
//create a new albums parameters
    request.setParameter("inputsponsorSponsor_ID","DUPLICATE");
    request.setParameter("inputsponsorTier_ID","DUPLICATE");
    request.setParameter("inputsponsorWebsite","www.DUPLICATE.com");
    request.setParameter("inputsponsorDescription","DUPLICATE");
    request.setParameter("inputsponsorIs_Active","true");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Sponsor_Updated = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Sponsor_Updated);
    assertEquals("Sponsor Not Updated",Sponsor_Updated);
    assertNotEquals("",Sponsor_Updated);
  }
  @Test
  public void testUpdateCanThrowSQLException() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
//to set the old Sponsor
    Sponsor sponsor = new Sponsor();
    sponsor.setSponsor_ID("EXCEPTION");
    sponsor.setTier_ID("EXCEPTION");
    sponsor.setWebsite("http://EXCEPTION.com");
    sponsor.setDescription("EXCEPTION");
    sponsor.setIs_Active(true);
    session.setAttribute("sponsor",sponsor);
//create a new albums parameters
    request.setParameter("inputsponsorSponsor_ID","EXCEPTION");
    request.setParameter("inputsponsorTier_ID","EXCEPTION");
    request.setParameter("inputsponsorWebsite","www.EXCEPTION.com");
    request.setParameter("inputsponsorDescription","EXCEPTION");
    request.setParameter("inputsponsorIs_Active","true");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Sponsor_Updated = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Sponsor_Updated);
    assertNotEquals("",Sponsor_Updated);
    assertEquals("Sponsor Not Updated",Sponsor_Updated);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }

}
