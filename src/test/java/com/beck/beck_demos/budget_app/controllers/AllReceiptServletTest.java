package com.beck.beck_demos.budget_app.controllers;
import java.io.IOException;
import java.util.*;
import com.beck.beck_demos.budget_app.data_fakes.ReceiptDAO_Fake;
import com.beck.beck_demos.budget_app.data_fakes.TransactionDAO_Fake;
import com.beck.beck_demos.budget_app.models.Receipt;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

public class AllReceiptServletTest {
  private static final String PAGE="WEB-INF/budget_app/All_Receipt.jsp";
  AllReceiptServlet servlet;
  MockHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;

  /**
   <p> setup the tests by creating a new instance of the servlet and setting some standard variablges </p>
   */
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new AllReceiptServlet();
    servlet.init(new ReceiptDAO_Fake(),new TransactionDAO_Fake());
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
  public void TestLoggedInUserGets200OnDoGet() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(200,status);
  }

  /**
   <p> Tests That the user will received a 302 status on doGet if they are logged out  </p>
   */
  @Test
  public void TestLoggedOutUserGets302OnDoGet() throws ServletException, IOException{
    request.setSession(session);
    servlet.doGet(request,response);
    int status = response.getStatus();
    assertEquals(302,status);
    String redirect_link = response.getRedirectedUrl();
    String desired_redirect = "budget_home";
    assertEquals(desired_redirect,redirect_link);
    session = request.getSession(false);
    assertNull(session);
  }

  /**
   <p> Test that a user in the wrong role will get a 302 on a doGet </p>
   */
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
    String redirect_link = response.getRedirectedUrl();
    String desired_redirect = "budget_home";
    assertEquals(desired_redirect,redirect_link);
  }

  /**
   <p> Test that a logged in user is able to retrieve all of the Receipt objects. </p>
   */
  @Test
  public void testLoggedInUserGetsAllReceipts() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doGet(request,response);
    List<Receipt> receipts = (List<Receipt>) request.getAttribute("Receipts");
    assertNotNull(receipts);
    assertEquals(15,receipts.size());
  }

  /**
   <p> Test that getting all Receipt can filter. </p>
   */
  @Test
  public void testLoggedInUserCanFilterReceiptsByTransaction_ID() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    String Transaction_ID= "DUQGCMYM";
    request.setParameter("transaction_id",Transaction_ID);
    request.setSession(session);
    servlet.doGet(request,response);
    List<Receipt> receipts = (List<Receipt>) request.getAttribute("Receipts");
    assertNotNull(receipts);
    assertEquals(5,receipts.size());
  }

  /**
   <p> Test that getting all Receipt can filter. </p>
   */
  @Test
  public void testLoggedInUserCanSearch() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    user.setRoles(roles);
    session.setAttribute("User_B",user);

    String searchTerm = "abc";
    request.setParameter("search",searchTerm);
    request.setSession(session);
    servlet.doGet(request,response);
    List<Receipt> receipts = (List<Receipt>) request.getAttribute("Receipts");
    assertNotNull(receipts);
    assertEquals(2,receipts.size());
  }

  /**
   <p> Test that getting all Receipt can filter. </p>
   */
  @Test
  public void testLoggedInUserCanSearchAndReturnZero() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_B",user);

    String searchTerm = "banananan";
    request.setParameter("search",searchTerm);
    request.setSession(session);
    servlet.doGet(request,response);
    List<Receipt> receipts = (List<Receipt>) request.getAttribute("Receipts");
    assertNotNull(receipts);
    assertEquals(0,receipts.size());
  }

  /**
   <p> Test That initializing the Servlet Does Not Crash or cause an exception </p>
   */
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new AllReceiptServlet();
    servlet.init();
  }

}

