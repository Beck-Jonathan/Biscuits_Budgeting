package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data_fakes.Saved_Search_OrderDAO_Fake;
import com.beck.beck_demos.budget_app.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EditSaved_Search_Order_LineServletTest {

  private EditSaved_Search_Order_LineServlet servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  private MockHttpSession session;
  private Saved_Search_OrderDAO_Fake fakeDAO;

  @BeforeEach
  public void setup() {
    servlet = new EditSaved_Search_Order_LineServlet();
    fakeDAO = new Saved_Search_OrderDAO_Fake();
    servlet.init(fakeDAO);

    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();
    session = new MockHttpSession();

    // Setup Authorized User
    User user = new User();
    List<String> roles = new ArrayList<>();
    roles.add("User");
    user.setRoles(roles);
    user.setUser_ID("0607a176-01de-46ea-a463-1d59db87491a"); // Matches existing user in fakeDAO
    session.setAttribute("User_B", user);
    request.setSession(session);
  }

  @Test
  public void testSuccessfulUpdateReturnsOne() throws Exception {
    setValidParameters("New Phrase");

    servlet.doPost(request, response);

    assertEquals("1", response.getContentAsString().trim());
  }

  @Test
  public void testDuplicateKeyReturnsZero() throws Exception {
    // Triggering the DUPLICATE keyword logic in FakeDAO
    setValidParameters("DUPLICATE");

    servlet.doPost(request, response);

    assertEquals("0", response.getContentAsString().trim());
  }

  @Test
  public void testExceptionKeyReturnsNeg3() throws Exception {
    // Triggering the EXCEPTION keyword logic in FakeDAO
    setValidParameters("EXCEPTION");

    servlet.doPost(request, response);

    assertEquals("-3", response.getContentAsString().trim());
  }

  @Test
  public void testInvalidLineNoReturnsNeg2() throws Exception {
    setValidParameters("Normal Phrase");
    request.setParameter("inputsaved_search_order_lineLine_No", "INVALID_INT");

    servlet.doPost(request, response);

    assertEquals("-2", response.getContentAsString().trim());
  }

  private void setValidParameters(String searchPhrase) {
    // ID from FakeDAO constructor data
    request.setParameter("inputsaved_search_order_lineSaved_Search_Order_ID", "1b076c01-2790-47f4-a5ed-a43f2c6772ca");
    request.setParameter("inputsaved_search_order_lineLine_No", "27");
    request.setParameter("inputsaved_search_order_lineCategory_ID", "0607a176-01de-46ea-a463-1d59db87491a");
    request.setParameter("inputsaved_search_order_lineSearch_Phrase", searchPhrase);
    request.setParameter("inputsaved_search_order_lineIs_Active", "true");

    // Old fields
    request.setParameter("oldPhrase", "WKTFwUjn");
    request.setParameter("oldCategory", "0607a176-01de-46ea-a463-1d59db87491a");
  }
}