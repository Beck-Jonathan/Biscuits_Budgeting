package com.beck.beck_demos.budget_app.controllers;
import java.io.IOException;
import java.util.*;
import com.beck.beck_demos.budget_app.data_fakes.MortgageDAO_Fake;
import com.beck.beck_demos.budget_app.models.Mortgage;
import com.beck.beck_demos.budget_app.models.Mortgage_VM;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteMortgageServletTest {
  private static final String PAGE="WEB-INF/budget_app/Delete_Mortgage.jsp";
  DeleteMortgageServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new DeleteMortgageServlet();
    servlet.init(new MortgageDAO_Fake());
    request =  new MockHttpServletRequest();
    response = new MockHttpServletResponse();
    session = new MockHttpSession();
    rd = new MockRequestDispatcher(PAGE);
  }


  /**
   <p> tear down by setting all variablges to null. </p>
   */
  @AfterEach
  public void teardown(){
    servlet=null;
    request=null;
    response=null;
    session=null;
    rd=null;
  }

  /**
   <p> Tests That the user will received a 200 status on doGet if they are logged in </p>
   */
  @Test
  public void TestLoggedInUserGetsErrorOnDoPostWithNoIDSet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("af735dfc-22a9-4214-a8e5-fb8de2305700");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doPost(request,response);
    String status = response.getContentAsString();
    assertEquals("Unable To Find Mortgage.",status);
  }

  /**
   <p> Tests That the user will received a 302 status on doGet if they are logged out  </p>
   */
  @Test
  public void TestLoggedOutUserGetsErrorOnDoPost() throws ServletException, IOException{
    request.setSession(session);
    servlet.doPost(request,response);
    String status = response.getContentAsString();

    session = request.getSession(false);
    assertEquals("You are not logged in",status);
    assertNull(session);
  }

  /**
   <p> Test that the deactivation servlet can deactivate a Mortgage </p>
   */
  @Test
  public void TestDeactivateCanDeactivate() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("af735dfc-22a9-4214-a8e5-fb8de2305700");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("mortgageid","xfJsxVBDDqBaKBFAgDhQACTFREWwjVxILdpW");

    servlet.doPost(request,response);
    String status = response.getContentAsString();
    assertEquals("1",status);
  }

  /**
   <p> Test that a user in the wrong role will get a 302 on a doGet </p>
   */
  @Test
  public void TestWrongRoleGets302onDoGet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("WrongRole");
    user.setUser_ID("af735dfc-22a9-4214-a8e5-fb8de2305700");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);

    servlet.doPost(request,response);
    String status = response.getContentAsString();

    session = request.getSession(false);
    assertEquals("You are not logged in",status);
    assertNull(session);
  }

  /**
   <p> Test that the deactivate servlet will fail if the Mortgage is already insactive. </p>
   */
  @Test
  public void TestDeactivateFailIfDoesntExist() throws ServletException, IOException {
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("af735dfc-22a9-4214-a8e5-fb8de2305700");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("Mortgageid","fda");
    request.setParameter("mode","0");
    servlet.doPost(request,response);
    String status = response.getContentAsString();

    session = request.getSession(false);
    assertEquals("Unable To Find Mortgage.",status);

  }


  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new DeleteMortgageServlet();
    servlet.init();
  }

}

