package com.beck.beck_demos.crrg.controllers;

import com.azure.json.implementation.jackson.core.JsonProcessingException;
import com.beck.beck_demos.crrg.data.Album_DAO;
import com.beck.beck_demos.crrg.models.Album;
import com.beck.beck_demos.crrg.models.Album_VM;
import com.beck.beck_demos.crrg.models.User;
import com.beck.beck_demos.crrg.data_interfaces.iAlbum_DAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@WebServlet("/all-Albums_API")
public class All_Album_API extends HttpServlet {
  private iAlbum_DAO albumDAO;
  @Override
  public void init() throws ServletException{
    albumDAO = new Album_DAO();
  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
    ROLES_NEEDED.add("Jonathan");
//add roles here
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");
    if (user==null||!user.isInRole(ROLES_NEEDED)){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }


    List<Album_VM> albums = null;
    String jsonArray="";

    try {
      albums =albumDAO.getAllAlbum(20,0);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      jsonArray
          = objectMapper.writeValueAsString(albums);
      System.out.println(jsonArray);
    }
    catch (Exception e) {

    }

    resp.setContentType("application/json");
// Get the printwriter object from response to write the required json object to the output stream
    PrintWriter out = resp.getWriter();
// Assuming your json object is **jsonObject**, perform the following, it will return your json object
    out.print(jsonArray);
    out.flush();

  }
}