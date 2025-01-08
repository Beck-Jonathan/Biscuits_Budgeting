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

public class Edit_AlbumTest {
  private static final String PAGE = "WEB-INF/crrg/Edit_Album.jsp";
  Edit_Album servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  @BeforeEach
  public void setup() throws ServletException {

    servlet = new Edit_Album();
    servlet.init(new Album_DAO_Fake());
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
  public void testGetOneAlbumGetsOneAlbum_ID() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    Integer Album_ID= 2;
    request.setParameter("albumid", String.valueOf(Album_ID));
    request.setSession(session);
    servlet.doGet(request,response);
    Album album = (Album) session.getAttribute("album");
    assertNotNull(album);
    assertEquals(Album_ID,album.getAlbum_ID());
  }

  @Test
  public void testGetOneAlbumCanFail() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    Integer Album_ID= -1;
    request.setParameter("albumid",Album_ID.toString());

    request.setSession(session);
    servlet.doGet(request,response);
    Album album = (Album) session.getAttribute("album");
    assertNull(album.getAlbum_ID());
    assertNull(album.getAlbum_Name());
    assertFalse(album.getIs_Active());
  }

  @Test
  public void TestUpdateCanAddWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
//to set the old Album
    Album album = new Album();
    album.setAlbum_ID(1);
    album.setAlbum_Name("testAlbum");
    album.setIs_Active(true);
    session.setAttribute("album",album);
//create a new albums parameters
    request.setParameter("inputalbumAlbum_Name","TestValue");
    request.setParameter("inputalbumIs_Active","true");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Album_Updated = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Album_Updated);
    assertEquals("Album updated",Album_Updated);
    assertNotEquals("",Album_Updated);
  }
  @Test
  public void TestUpdateHasErrorsForEachFiledAndKeepsOnSamePage() throws ServletException, IOException{
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
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new Edit_Album();
    servlet.init();
  }
  @Test
  public void testUpdateCanReturnZero() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
//to set the old Album
    Album album = new Album();
    album.setAlbum_ID(43);
    album.setAlbum_Name("DUPLICATE");
    album.setIs_Active(true);
    session.setAttribute("album",album);
//create a new albums parameters
    request.setParameter("inputalbumAlbum_Name","DUPLICATE");
    request.setParameter("inputalbumIs_Active","true");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Album_Updated = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Album_Updated);
    assertEquals("Album Not Updated",Album_Updated);
    assertNotEquals("",Album_Updated);
  }
  @Test
  public void testUpdateCanThrowSQLException() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
//to set the old Album
    Album album = new Album();
    album.setAlbum_ID(43);
    album.setAlbum_Name("EXCEPTION");
    album.setIs_Active(true);
    session.setAttribute("album",album);
//create a new albums parameters
    request.setParameter("inputalbumAlbum_Name","EXCEPTION");
    request.setParameter("inputalbumIs_Active","true");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Album_Updated = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Album_Updated);
    assertNotEquals("",Album_Updated);
    assertEquals("Album Not Updated",Album_Updated);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }

}

