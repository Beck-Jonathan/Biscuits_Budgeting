package com.beck.beck_demos.crrg.controllers;

import com.beck.beck_demos.crrg.data.Picture_DAO;
import com.beck.beck_demos.crrg.models.Picture;
import com.beck.beck_demos.crrg.models.Picture_VM;
import com.beck.beck_demos.crrg.models.User;
import com.beck.beck_demos.crrg.data_interfaces.iPicture_DAO;
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
@WebServlet("/Picture_by_album")
public class Picture_API extends HttpServlet {
  private iPicture_DAO pictureDAO;

  @Override
  public void init() throws ServletException {
    pictureDAO = new Picture_DAO();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



    String album = req.getParameter("album");

    String contributor = req.getParameter("contributor");
    Integer contributorID = 0;
    if (contributor != null) {
      contributorID = Integer.valueOf(contributor);
    }
    Integer AlbumID = 0;
    if (album != null) {
      AlbumID = Integer.valueOf(album);
    }


    List<Picture> pictures = null;
    String jsonArray="";
    try {

      pictures = pictureDAO.getActivePicturebyAlbum( AlbumID);

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      jsonArray
          = objectMapper.writeValueAsString(pictures);
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