package com.beck.beck_demos.budget_app.controllers;

import java.io.IOException;
import java.util.*;
import com.beck.beck_demos.budget_app.data_fakes.CategoryDAO_Fake;
import com.beck.beck_demos.budget_app.models.Category;
import com.beck.beck_demos.budget_app.models.Category_VM;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

class EditCategoryServletTest {

  private static final String PAGE="WEB-INF/budget_app/Edit_Category.jsp";
  EditCategoryServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new EditCategoryServlet();
    servlet.init(new CategoryDAO_Fake());
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
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID(36);
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(200,status);
  }
  @Test
  public void TestLoggedInUserGets200OnDoPost() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID(36);
    session.setAttribute("User_B",user);
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
  public void TestWrongRoleGets302onDoGet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("WrongRole");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }
  @Test
  public void TestWrongRoleGets302onDoPost() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("WrongRole");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
  }
  @Test
  public void testGetOneCategoryGetsOneCategory_ID() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID(36);
    session.setAttribute("User_B",user);
    String Category_ID= "fOmyBlIv";
    request.setParameter("categoryid",Category_ID);
    request.setSession(session);
    servlet.doGet(request,response);
    Category category = (Category) session.getAttribute("category");
    assertNotNull(category);
    assertEquals(Category_ID,category.getCategory_ID());
    assertEquals(36,category.getUser_ID());
  }

  @Test
  public void testGetOneCategoryCanFail() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    String Category_ID= "";
    request.setParameter("categoryid",Category_ID);

    Integer User_ID= 1;
    request.setParameter("userid",User_ID.toString());

    request.setSession(session);
    servlet.doGet(request,response);
    Category category = (Category) session.getAttribute("category");
    assertNull(category.getCategory_ID());
    assertNull(category.getUser_ID());
  }
  @Test
  public void TestUpdateCanAddWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
//to set the old Category
    Category category = new Category();
    category.setCategory_ID("fOmyBlIv");
    category.setUser_ID(36);
    session.setAttribute("category",category);
//create a new albums parameters
    request.setParameter("inputcategoryCategory_ID","TestValue");

    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Category_Updated = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Category_Updated);
    assertEquals("Category updated",Category_Updated);
    assertNotEquals("",Category_Updated);
  }
  @Test
  public void TestUpdateHasErrorsForEachFiledAndKeepsOnSamePage() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Category_IDError = results.get("categoryCategory_IDerror");

    assertNotEquals("",Category_IDError);
    assertNotNull(Category_IDError);

    assertEquals(200,responseStatus);
  }
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new EditCategoryServlet();
    servlet.init();
  }
  @Test
  public void testUpdateCanReturnZero() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
//to set the old Category
    Category category = new Category();
    category.setCategory_ID("DUPLICATE");
    category.setUser_ID(43);
    session.setAttribute("category",category);
//create a new albums parameters
    request.setParameter("inputcategoryCategory_ID","DUPLICATE");
    request.setParameter("inputcategoryUser_ID","406");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Category_Updated = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Category_Updated);
    assertEquals("Category Not Updated",Category_Updated);
    assertNotEquals("",Category_Updated);
  }
  @Test
  public void testUpdateCanThrowSQLException() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
//to set the old Category
    Category category = new Category();
    category.setCategory_ID("EXCEPTION");
    category.setUser_ID(43);
    session.setAttribute("category",category);
//create a new albums parameters
    request.setParameter("inputcategoryCategory_ID","EXCEPTION");
    request.setParameter("inputcategoryUser_ID","406");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Category_Updated = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Category_Updated);
    assertNotEquals("",Category_Updated);
    assertEquals("Category Not Updated",Category_Updated);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }

}

