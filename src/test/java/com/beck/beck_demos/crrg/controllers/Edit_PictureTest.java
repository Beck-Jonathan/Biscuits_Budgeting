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

import static org.junit.jupiter.api.Assertions.*;

public class Edit_PictureTest {
  private static final String PAGE = "WEB-INF/crrg/Edit_Picture.jsp";
  Edit_Picture servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  @BeforeEach
  public void setup() throws ServletException {

    servlet = new Edit_Picture();
    servlet.init(new Album_DAO_Fake(), new Contributor_DAO_Fake(), new Picture_DAO_Fake());
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
  public void testGetOnePictureGetsOnePicture_ID() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User", user);
    Integer Picture_ID = 2;
    request.setParameter("pictureid", Picture_ID.toString());
    request.setSession(session);
    servlet.doGet(request, response);
    Picture picture = (Picture) session.getAttribute("picture");
    assertNotNull(picture);
    assertEquals(Picture_ID, picture.getPicture_ID());
  }

  @Test
  public void testGetOnePictureCanFailPicture_ID() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User", user);
    String Picture_ID = null;
    request.setParameter("pictureid", Picture_ID);
    request.setSession(session);
    servlet.doGet(request, response);
    Picture picture = (Picture) session.getAttribute("picture");
    assertNull(picture.getAlbum_ID());
  }

  @Test
  public void TestUpdateCanAddWithNoErrorsAndRedirects() throws ServletException, IOException {
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User", user);
    request.setSession(session);
//to set the old Picture
    Picture picture = new Picture();
    picture.setPicture_ID(43);
    picture.setAlbum_ID(43);
    picture.setContributor_ID(43);
    picture.setWeb_Address("testPicture");
    picture.setDescription("testPicture");
    picture.setIs_Active(true);
    picture.setis_Approved(true);
    session.setAttribute("picture", picture);
//create a new albums parameters
    request.setParameter("inputpictureAlbum_ID", "406");
    request.setParameter("inputpictureContributor_ID", "406");
    request.setParameter("inputpictureWeb_Address", "TestValue");
    request.setParameter("inputpictureDescription", "TestValue");
    request.setParameter("inputpictureIs_Active", "true");
    request.setParameter("inputpictureis_Approved", "true");
    servlet.doPost(request, response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Picture_Updated = results.get("dbStatus");
    assertEquals(302, responseStatus);
    assertNotNull(Picture_Updated);
    assertEquals("Picture updated", Picture_Updated);
    assertNotEquals("", Picture_Updated);
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
    String Album_IDError = results.get("pictureAlbum_IDerror");
    String Contributor_IDError = results.get("pictureContributor_IDerror");
    String Web_AddressError = results.get("pictureWeb_Addresserror");
    String DescriptionError = results.get("pictureDescriptionerror");
    assertNotEquals("", Album_IDError);
    assertNotNull(Album_IDError);
    assertNotEquals("", Contributor_IDError);
    assertNotNull(Contributor_IDError);
    assertNotEquals("", Web_AddressError);
    assertNotNull(Web_AddressError);
    assertNotEquals("", DescriptionError);
    assertNotNull(DescriptionError);
    assertEquals(200, responseStatus);
  }

  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new Edit_Picture();
    servlet.init();

  }

  @Test
  public void testUpdateCanReturnZero() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
//to set the old Picture
    Picture picture = new Picture();
    picture.setPicture_ID(43);
    picture.setAlbum_ID(43);
    picture.setContributor_ID(43);
    picture.setWeb_Address("DUPLICATE");
    picture.setDescription("DUPLICATE");
    picture.setIs_Active(true);
    picture.setis_Approved(true);
    session.setAttribute("picture",picture);
//create a new albums parameters
    request.setParameter("inputpictureAlbum_ID","406");
    request.setParameter("inputpictureContributor_ID","406");
    request.setParameter("inputpictureWeb_Address","DUPLICATE");
    request.setParameter("inputpictureDescription","DUPLICATE");
    request.setParameter("inputpictureIs_Active","true");
    request.setParameter("inputpictureis_Approved","true");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Picture_Updated = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Picture_Updated);
    assertEquals("Picture Not Updated",Picture_Updated);
    assertNotEquals("",Picture_Updated);
  }
  @Test
  public void testUpdateCanThrowSQLException() throws ServletException, IOException{
    User user = new User();
    user.setRole_ID("Jonathan");
    session.setAttribute("User",user);
    request.setSession(session);
//to set the old Picture
    Picture picture = new Picture();
    picture.setPicture_ID(43);
    picture.setAlbum_ID(43);
    picture.setContributor_ID(43);
    picture.setWeb_Address("EXCEPTION");
    picture.setDescription("EXCEPTION");
    picture.setIs_Active(true);
    picture.setis_Approved(true);
    session.setAttribute("picture",picture);
//create a new albums parameters
    request.setParameter("inputpictureAlbum_ID","406");
    request.setParameter("inputpictureContributor_ID","406");
    request.setParameter("inputpictureWeb_Address","EXCEPTION");
    request.setParameter("inputpictureDescription","EXCEPTION");
    request.setParameter("inputpictureIs_Active","true");
    request.setParameter("inputpictureis_Approved","true");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Picture_Updated = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Picture_Updated);
    assertNotEquals("",Picture_Updated);
    assertEquals("Picture Not Updated",Picture_Updated);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }

}