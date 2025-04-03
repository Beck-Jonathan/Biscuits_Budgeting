package com.beck.beck_demos.budget_app.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import com.beck.beck_demos.budget_app.data_fakes.TransactionDAO_Fake;
import com.beck.beck_demos.budget_app.models.Transaction;

import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.apache.http.HttpEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;

class add_transactionTest {

  private static final String PAGE="WEB-INF/budget_app/Add_Transaction.jsp";
  add_transaction servlet;
  MockMultipartHttpServletRequest request;
  MockHttpServletResponse response;
  HttpSession session;
  RequestDispatcher rd;
  @BeforeEach
  public void setup() throws ServletException{

    servlet = new add_transaction();
    servlet.init(new TransactionDAO_Fake());
    request =  new MockMultipartHttpServletRequest();
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
  public void TestAddHasErrorsForEachFieldAndRedirectstoHome() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) session.getAttribute("results");
    String FileError = results.get("FileEmptyError");

    assertNotEquals("",FileError);
    assertNotNull(FileError);
    assertEquals("File is empty",FileError);
    assertEquals(302,responseStatus);
  }
  @Test
  public void TestAddCanAddWithNoErrorsAndRedirects() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    File file = new File(request.getSession().getServletContext().getRealPath("/")+"dummy");
    file.delete();
    file.createNewFile();
    List<String> lines = new ArrayList<>();
    lines.add("Custom\tAccount Number\t123456789");
    lines.add("12/14/2024\t12/14/2024\tHulu\t\t-94.21\t\"21,105.34\"");
    lines.add("12/14/2024\t12/14/2024\tVerizon\t\t-89.48\t\"21,194.82\"");
    Files.write(file.toPath(), lines, StandardCharsets.UTF_8);
    final String fileName = "test.txt";
    byte[] content = Files.readAllBytes(file.toPath());

    MockMultipartFile mockMultipartFile =
        new MockMultipartFile("upload_transactions", fileName, "text/plain", content);
    request.addFile(mockMultipartFile);





    session.setAttribute("User_B",user);
    request.setSession(session);

    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Transaction_Added = results.get("dbStatus");
    assertEquals(302,responseStatus);
    assertNotNull(Transaction_Added);
    assertEquals("Transaction Added",Transaction_Added);
    assertNotEquals("",Transaction_Added);
  }
  @Test
  public void testExceptionKeyThrowsException() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("inputtransactionUser_ID","406");
    request.setParameter("inputtransactionCategory_ID","EXCEPTION");
    request.setParameter("inputtransactionAccount_Num","EXCEPTION");
    request.setParameter("inputtransactionCheck_No","406");
    request.setParameter("inputtransactionDescription","EXCEPTION");
    request.setParameter("inputtransactionType","EXCEPTION");
    request.setParameter("inputtransactionStatus","EXCEPTION");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Transaction_Added = results.get("dbStatus");
    String dbError = results.get("dbError");
    assertEquals(200,responseStatus);
    assertNotNull(Transaction_Added);
    assertEquals("Transaction Not Added",Transaction_Added);
    assertNotEquals("",Transaction_Added);
    assertNotNull(dbError);
    assertNotEquals("",dbError);
    assertEquals("Database Error",dbError);
  }
  @Test
  public void testDuplicateKeyReturnsZero() throws ServletException, IOException{
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    session.setAttribute("User_B",user);
    request.setSession(session);
    request.setParameter("inputtransactionUser_ID","406");
    request.setParameter("inputtransactionCategory_ID","DUPLICATE");
    request.setParameter("inputtransactionAccount_Num","DUPLICATE");
    request.setParameter("inputtransactionCheck_No","406");
    request.setParameter("inputtransactionDescription","DUPLICATE");
    request.setParameter("inputtransactionType","DUPLICATE");
    request.setParameter("inputtransactionStatus","DUPLICATE");
    servlet.doPost(request,response);
    int responseStatus = response.getStatus();
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    String Transaction_Added = results.get("dbStatus");
    assertEquals(200,responseStatus);
    assertNotNull(Transaction_Added);
    assertEquals("Transaction Not Added",Transaction_Added);
    assertNotEquals("",Transaction_Added);
  }
  @Test
  public void testInitWithNoParametersDoesNotThrowException() throws ServletException {
    servlet = null;
    servlet = new add_transaction();
    servlet.init();
  }

}


