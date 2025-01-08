package com.beck.beck_demos.crrg.controllers;

import java.io.IOException;
import java.util.*;

import com.beck.beck_demos.crrg.data.Album_DAO;
import com.beck.beck_demos.crrg.data.Contributor_DAO;
import com.beck.beck_demos.crrg.data.Picture_DAO;
import com.beck.beck_demos.crrg.data_fakes.Album_DAO_Fake;
import com.beck.beck_demos.crrg.data_fakes.Contributor_DAO_Fake;
import com.beck.beck_demos.crrg.data_fakes.Picture_DAO_Fake;
import com.beck.beck_demos.crrg.data_interfaces.iAlbum_DAO;
import com.beck.beck_demos.crrg.data_interfaces.iContributor_DAO;
import com.beck.beck_demos.crrg.data_interfaces.iPicture_DAO;
import com.beck.beck_demos.crrg.models.Picture;
import com.beck.beck_demos.crrg.models.Picture_VM;
import com.beck.beck_demos.crrg.models.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;

import static org.junit.jupiter.api.Assertions.*;

public class Activation_ServletTest {
  private static final String PAGE = "WEB-INF/crrg/Delete_Picture.jsp";
  Activation_Servlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  @BeforeEach
  public void setup() throws ServletException {

    servlet = new Activation_Servlet();
    servlet.init(new Picture_DAO_Fake(), new Album_DAO_Fake(), new Contributor_DAO_Fake());
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
  public void TestLoggedOutUserGets302OnDoPost() throws ServletException, IOException {
    request.setSession(session);
    servlet.doPost(request, response);
    int status = response.getStatus();
    assertEquals(403, status);
  }

  @Test
  public void TestDeactivateCanDeactivate()throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User", user);
    request.setSession(session);
    request.setParameter("object","picture");
    request.setParameter("objectID", "44");
    request.setParameter("mode", String.valueOf(0));
    servlet.doPost(request, response);
    int status = (int) request.getAttribute("result");
    assertEquals(1, status);
  }

  @Test
  public void TestAactivateCanAactivate()throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User", user);
    request.setSession(session);
    request.setParameter("object","picture");
    request.setParameter("objectID", "46");
    request.setParameter("mode", String.valueOf(1));
    servlet.doPost(request, response);
    int status = (int) request.getAttribute("result");
    assertEquals(1, status);
  }

  @Test
  public void TestAactivateCanFail()throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User", user);
    request.setSession(session);
    request.setParameter("object","picture");
    request.setParameter("objectID", "44");
    request.setParameter("mode", String.valueOf(1));
    servlet.doPost(request, response);
    int status = (int) request.getAttribute("result");
    assertEquals(0, status);
  }

  @Test
  public void TestDeactivateCanFail()throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User", user);
    request.setSession(session);
    request.setParameter("object","picture");
    request.setParameter("objectID", "46");
    request.setParameter("mode", String.valueOf(0));
    servlet.doPost(request, response);
    int status = (int) request.getAttribute("result");
    assertEquals(0, status);
  }

  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new Activation_Servlet();
    servlet.init();

  }

}

