package com.beck.beck_demos.budget_app.controllers;

import java.io.IOException;
import java.util.*;
import com.beck.beck_demos.budget_app.data_fakes.Saved_Search_OrderDAO_Fake;
import com.beck.beck_demos.budget_app.models.Saved_Search_Order_Line;

import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

class AddSaved_Search_Order_LineServletTest {

  private static final String PAGE="WEB-INF/WFTDA_debug/Add_Saved_Search_Order_Line.jsp";
  AddSaved_Search_Order_LineServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new AddSaved_Search_Order_LineServlet();
    servlet.init(new Saved_Search_OrderDAO_Fake());
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
    user.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(200,status);
  }
  @Test
  public void TestLoggedInUserGets302OnDoPost() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
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
  public void TestAddHasErrorsForEachFieldAndRedirects() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Saved_Search_Order_IDError = results.get("saved_search_order_lineSaved_Search_Order_IDerror");
    String Line_NoError = results.get("saved_search_order_lineLine_Noerror");
    String Category_IDError = results.get("saved_search_order_lineCategory_IDerror");
    //String User_IDError = results.get("saved_search_order_lineUser_IDerror");
    String Search_PhraseError = results.get("saved_search_order_lineSearch_Phraseerror");
    assertNotEquals("",Saved_Search_Order_IDError);
    assertNotNull(Saved_Search_Order_IDError);
    assertNotEquals("",Line_NoError);
    assertNotNull(Line_NoError);
    assertNotEquals("",Category_IDError);
    assertNotNull(Category_IDError);
    //assertNotEquals("",User_IDError);
    //assertNotNull(User_IDError);
    assertNotEquals("",Search_PhraseError);
    assertNotNull(Search_PhraseError);
    assertEquals(302,responseStatus);
  }
  @Test
  public void TestAddCanAddWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("inputsaved_search_order_lineSaved_Search_Order_ID","34");
    request.setParameter("inputsaved_search_order_lineLine_No","406");
    request.setParameter("inputsaved_search_order_lineCategory_ID","TestValue");
    request.setParameter("inputsaved_search_order_lineUser_ID","406");
    request.setParameter("inputsaved_search_order_lineSearch_Phrase","TestValue");
    request.setParameter("inputsaved_search_order_lineIs_Active","true");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Saved_Search_Order_Line_Added = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Saved_Search_Order_Line_Added);
    assertEquals("Saved_Search_Order_Line Added",Saved_Search_Order_Line_Added);
    assertNotEquals("",Saved_Search_Order_Line_Added);
  }
  @Test
  public void testExceptionKeyThrowsException() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("inputsaved_search_order_lineSaved_Search_Order_ID","406");
    request.setParameter("inputsaved_search_order_lineLine_No","406");
    request.setParameter("inputsaved_search_order_lineCategory_ID","EXCEPTION");
    request.setParameter("inputsaved_search_order_lineUser_ID","406");
    request.setParameter("inputsaved_search_order_lineSearch_Phrase","EXCEPTION");
    request.setParameter("inputsaved_search_order_lineIs_Active","true");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Saved_Search_Order_Line_Added = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(302,responseStatus);
    assertNotNull(Saved_Search_Order_Line_Added);
    assertEquals("Saved_Search_Order_Line Not Added",Saved_Search_Order_Line_Added);
    assertNotEquals("",Saved_Search_Order_Line_Added);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }
  @Test
  public void testDuplicateKeyReturnsZero() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("inputsaved_search_order_lineSaved_Search_Order_ID","406");
    request.setParameter("inputsaved_search_order_lineLine_No","406");
    request.setParameter("inputsaved_search_order_lineCategory_ID","DUPLICATE");
    request.setParameter("inputsaved_search_order_lineUser_ID","406");
    request.setParameter("inputsaved_search_order_lineSearch_Phrase","DUPLICATE");
    request.setParameter("inputsaved_search_order_lineIs_Active","true");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Saved_Search_Order_Line_Added = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Saved_Search_Order_Line_Added);
    assertEquals("Saved_Search_Order_Line Not Added",Saved_Search_Order_Line_Added);
    assertNotEquals("",Saved_Search_Order_Line_Added);
  }
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new AddSaved_Search_Order_LineServlet();
    servlet.init();
  }

}

