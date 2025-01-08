package com.beck.beck_demos.crrg.controllers;

import com.beck.beck_demos.crrg.controllers.All_User;
import java.io.IOException;
import java.io.IOException;
import java.util.*;
import com.beck.beck_demos.crrg.data_fakes.User_DAO_Fake;
import com.beck.beck_demos.crrg.models.User;

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

public class All_UserTest {
//  private static final String PAGE = "WEB-INF/crrg/All_User.jsp";
//  All_User servlet;
//  MockHttpServletRequest request;
//  MockHttpServletResponse response;
//  HttpSession session;
//  RequestDispatcher rd;
//
//  @BeforeEach
//  public void setup() throws ServletException {
//
//    servlet = new All_User();
//    servlet.init(new User_DAO_Fake());
//    request = new MockHttpServletRequest();
//    response = new MockHttpServletResponse();
//    session = new MockHttpSession();
//    rd = new MockRequestDispatcher(PAGE);
//  }
//
//  @AfterEach
//  public void teardown() {
//    servlet = null;
//    request = null;
//    response = null;
//    session = null;
//    rd = null;
//  }
//
//  @Test
//  public void TestLoggedInUserGets200OnDoGet() throws ServletException, IOException {
//    User user = new User();
//    user.setRole_ID("Jonathan");
//    session.setAttribute("User", user);
//    request.setSession(session);
//    servlet.doGet(request, response);
//    int status = response.getStatus();
//    assertEquals(200, status);
//  }
//
//
//
//  @Test
//  public void TestLoggedOutUserGets302OnDoGet() throws ServletException, IOException {
//    request.setSession(session);
//    servlet.doGet(request, response);
//    int status = response.getStatus();
//    assertEquals(302, status);
//  }
//
//
//
//  @Test
//  public void testLoggedInUserGetsAllUsers() throws ServletException, IOException {
//    User user = new User();
//    user.setRole_ID("Jonathan");
//    session.setAttribute("User", user);
//    request.setSession(session);
//    servlet.doGet(request, response);
//    List<User> users = (List<User>) request.getAttribute("Users");
//    assertNotNull(users);
//    assertEquals(20, users.size());
//  }
//
//  @Test
//  public void testLoggedInUserCanFilterUsersByRole_ID() throws ServletException, IOException {
//    User user = new User();
//    user.setRole_ID("Jonathan");
//    session.setAttribute("User", user);
//    String Role_ID = null;
//    request.setAttribute("Role_ID", Role_ID);
//    request.setSession(session);
//    servlet.doGet(request, response);
//    List<User> users = (List<User>) request.getAttribute("Users");
//    assertNotNull(users);
//    assertEquals(20, users.size());
//  }

}