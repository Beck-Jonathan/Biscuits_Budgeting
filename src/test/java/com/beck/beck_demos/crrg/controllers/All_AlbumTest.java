package com.beck.beck_demos.crrg.controllers;

import java.io.IOException;
import java.util.*;
import com.beck.beck_demos.crrg.data_fakes.Album_DAO_Fake;
import com.beck.beck_demos.crrg.models.Album;
import com.beck.beck_demos.crrg.models.Album_VM;
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



public class All_AlbumTest {


  private static final String PAGE="WEB-INF/crrg/All_Album.jsp";
  All_Album servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;


  @BeforeEach
  public void setup() throws ServletException{

    servlet = new All_Album();
    servlet.init(new Album_DAO_Fake());
    request =  new MockHttpServletRequest();
    response = new MockHttpServletResponse();
    session = new MockHttpSession();
    rd = new MockRequestDispatcher(PAGE);
  }

  @AfterEach
  public void teardown(){
    servlet=null;
    request=null;
    response=null;
    session=null;
    rd=null;
  }
  @Test
  public void TestLoggedInUserGets200OnDoGet() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(200,status);
  }

  @Test
  public void TestLoggedInUserGets302OnDoGet() throws ServletException, IOException {
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }


  @Test
  public void testLoggedInUUserGetsallAlbums() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    request.setSession(session);

    session.setAttribute("User",user);
    servlet.doGet(request, response);
    List<Album_VM> albums = (List<Album_VM>) request.getAttribute("Albums");
    assertNotNull(albums);
    assertEquals(3,albums.size());
    int x =response.getStatus();

  }
  @Test
  public void testNotLoggedInUserGetsredirected() throws ServletException, IOException {
    request.setSession(session);
    servlet.doGet(request, response);

    String redirectedUrl = response.getRedirectedUrl();
    int responseStatus = response.getStatus();
    assertEquals("/crrgLogin",redirectedUrl);
    assertEquals(302,responseStatus);

  }

  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new All_Album();
    servlet.init();

  }

}

