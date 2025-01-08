package com.beck.beck_demos.crrg.controllers;

import com.beck.beck_demos.crrg.controllers.All_Sponsor;
import java.io.IOException;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class All_SponsorTest {
  private static final String PAGE = "WEB-INF/crrg/All_Sponsor.jsp";
  All_Sponsor servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  @BeforeEach
  public void setup() throws ServletException {

    servlet = new All_Sponsor();
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
  public void TestLoggedOutUserGets302OnDoGet() throws ServletException, IOException {
    request.setSession(session);
    servlet.doGet(request, response);
    int status = response.getStatus();
    assertEquals(302, status);
  }



  @Test
  public void testLoggedInUserGetsAllSponsors() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User", user);
    request.setSession(session);
    servlet.doGet(request, response);
    List<Sponsor_VM> sponsors = (List<Sponsor_VM>) request.getAttribute("Sponsors");
    assertNotNull(sponsors);
    assertEquals(12, sponsors.size());
  }

  @Test
  public void testLoggedInUserCanFilterSponsorsByTier_ID() throws ServletException, IOException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User", user);
    String Tier_ID = "yiViZWSS";
    request.setParameter("Tier_ID", Tier_ID);
    request.setSession(session);
    servlet.doGet(request, response);
    List<Sponsor_VM> sponsors = (List<Sponsor_VM>) request.getAttribute("Sponsors");
    assertNotNull(sponsors);
    assertEquals(4, sponsors.size());
  }

  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new All_Sponsor();
    servlet.init();

  }

}