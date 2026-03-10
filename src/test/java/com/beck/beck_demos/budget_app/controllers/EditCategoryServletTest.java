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
  public void TestLoggedInUserGets200OnDoPost() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("af735dfc-22a9-4214-a8e5-fb8de2305700");
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int status = response.getStatus();
    assertEquals(200,status);

  }

  @Test
  public void TestLoggedOutHasInvalidSessionAndResponseNeg1OnOnDoPost() throws ServletException, IOException{
    request.setSession(session);
    servlet.doPost(request,response);
    session = request.getSession(false);
    assertNull(session);
    assertEquals("-1", response.getContentAsString().trim());
  }

  @Test
  public void TestWrongRoleInvalidSessionAndResponseNeg1OnOnDoPost() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("WrongRole");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doPost(request,response);
    session = request.getSession(false);
    assertNull(session);
    assertEquals("-1", response.getContentAsString().trim());
  }



  @Test
  public void TestUpdateCanAddWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);


//create a new albums parameters
    request.setParameter("category_ID","lZoleuasarwCfcmdPWeDgyapFwTISoPKgqXc");
    request.setParameter("inputcategoryCategory_Name","46fcffea-d21c-4254-814d-926d0086d77c");
    request.setParameter("inputcategoryColor_id","#FFFFFF");

    servlet.doPost(request,response);
    assertEquals("1", response.getContentAsString().trim());
  }
  @Test
  public void TestInvalidCategoryIDRespondsNeg2() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doPost(request,response);
    assertEquals("-2", response.getContentAsString().trim());
  }
  @Test
  public void TestInvalidCategoryNameRespondsNeg3() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("category_ID","lZoleuasarwCfcmdPWeDgyapFwTISoPKgqXc");
    servlet.doPost(request,response);
    assertEquals("-3", response.getContentAsString().trim());
  }
  @Test
  public void TestInvalidColorRespondsNeg4() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setParameter("category_ID","lZoleuasarwCfcmdPWeDgyapFwTISoPKgqXc");
    request.setParameter("inputcategoryCategory_Name","46fcffea-d21c-4254-814d-926d0086d77c");
    request.setSession(session);
    servlet.doPost(request,response);
    assertEquals("-4", response.getContentAsString().trim());
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
    request.setParameter("category_ID","lZoleuasarwCfcmdPWeDgyapFwTISoPKgqXc");
    request.setParameter("inputcategoryCategory_Name","DUPLICATE");
    request.setParameter("inputcategoryColor_id","#FFFFFF");
    servlet.doPost(request,response);
    assertEquals("0", response.getContentAsString().trim());
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

//create a new albums parameters
    request.setParameter("category_ID","lZoleuasarwCfcmdPWeDgyapFwTISoPKgqXc");
    request.setParameter("inputcategoryCategory_Name","EXCEPTION");
    request.setParameter("inputcategoryColor_id","#FFFFFF");
    servlet.doPost(request,response);
    assertEquals("-5", response.getContentAsString().trim());
  }

}

