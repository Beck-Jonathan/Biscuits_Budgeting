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

import static org.junit.jupiter.api.Assertions.*;

class Add_AlbumTest {
  private static final String PAGE="WEB-INF/crrg/Add_Album.jsp";
  Add_Album servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new Add_Album();
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
  public void TestLoggedInUserGets200OnDoGet() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(200,status);
  }
  @Test
  public void TestLoggedInUserGets200OnDoPost() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(200,status);
  }
  @Test
  public void TestLoggedOutUserGets302OnDoGet() throws ServletException, IOException{
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }
  @Test
  public void TestLoggedOutUserGets302OnDoPost() throws ServletException, IOException{
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }

  @Test
  public void TestAddHasErrorsForEachFieldAndKeepsOnSamePage() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Album_NameError = results.get("albumAlbum_Nameerror");
    assertNotEquals("",Album_NameError);
    assertNotNull(Album_NameError);
    assertEquals(200,responseStatus);
  }

  @Test
  public void TestAddCanAddWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("inputalbumAlbum_Name","TestValue");
    request.setParameter("inputalbumIs_Active","true");

    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Album_Added = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Album_Added);
    assertNotEquals("",Album_Added);
    assertEquals("Album Added",Album_Added);
  }
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new Add_Album();
    servlet.init();

  }

  @Test
  public void testExceptionKeyThrowsException() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
    request.setParameter("inputalbumAlbum_Name","EXCEPTION");
    request.setParameter("inputalbumIs_Active","true");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Album_Added = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Album_Added);
    assertEquals("Album Not Added",Album_Added);
    assertNotEquals("",Album_Added);
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
    request.setParameter("inputalbumAlbum_Name","DUPLICATE");
    request.setParameter("inputalbumIs_Active","true");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Album_Added = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Album_Added);
    assertEquals("Album Not Added",Album_Added);
    assertNotEquals("",Album_Added);
  }
}