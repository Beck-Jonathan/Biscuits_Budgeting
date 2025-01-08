package com.beck.beck_demos.crrg.controllers;

import java.io.IOException;
import java.util.*;

import com.beck.beck_demos.crrg.data_fakes.Album_DAO_Fake;
import com.beck.beck_demos.crrg.data_fakes.Contributor_DAO_Fake;
import com.beck.beck_demos.crrg.data_fakes.Picture_DAO_Fake;
import com.beck.beck_demos.crrg.models.Picture;
import com.beck.beck_demos.crrg.models.Picture_VM;
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

public class All_PictureTest {
  private static final String PAGE = "WEB-INF/crrg/All_Picture.jsp";
  All_Picture servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  @BeforeEach
  public void setup() throws ServletException {

    servlet = new All_Picture();
    servlet.init(new Picture_DAO_Fake());
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
  public void testLoggedInUserGetsAllPictures() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User", user);
    request.setSession(session);
    servlet.doGet(request, response);
    List<Picture_VM> pictures = (List<Picture_VM>) request.getAttribute("Pictures");
    assertNotNull(pictures);
    assertEquals(30, pictures.size());
  }

  @Test
  public void testLoggedInUserCanFilterPicturesByAlbum_ID() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User", user);
    Integer Album_ID = 61;
    request.setParameter("album", Album_ID.toString());
    request.setSession(session);
    servlet.doGet(request, response);
    List<Picture_VM> pictures = (List<Picture_VM>) request.getAttribute("Pictures");
    assertNotNull(pictures);
    assertEquals(5, pictures.size());
  }

  @Test
  public void testLoggedInUserCanFilterPicturesByContributor_ID() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User", user);
    Integer Contributor_ID = 57;
    request.setParameter("contributor", Contributor_ID.toString());
    request.setSession(session);
    servlet.doGet(request, response);
    List<Picture_VM> pictures = (List<Picture_VM>) request.getAttribute("Pictures");
    assertNotNull(pictures);
    assertEquals(5, pictures.size());
  }

  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new All_Picture();
    servlet.init();

  }

}