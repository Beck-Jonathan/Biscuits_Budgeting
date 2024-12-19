package com.beck.beck_demos.crrg.controllers;

import com.beck.beck_demos.crrg.data.Album_DAO;
import com.beck.beck_demos.crrg.models.Album;
import com.beck.beck_demos.crrg.models.Album_VM;
import com.beck.beck_demos.crrg.models.User;
import com.beck.beck_demos.crrg.data_interfaces.iAlbum_DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/all-Albums")
public class All_Album extends HttpServlet {
  private iAlbum_DAO albumDAO;
  @Override
  public void init() throws ServletException{
    albumDAO = new Album_DAO();
  }
  public void init(iAlbum_DAO _albumDAO) throws ServletException{
    albumDAO = _albumDAO;
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
      resp.sendRedirect("/crrgLogin");
      return;
    }

//    session.setAttribute("currentPage",req.getRequestURL());
    List<Album_VM> albums = null;

    try {
      albums =albumDAO.getAllAlbum(20,0);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    req.setAttribute("Albums", albums);
    req.setAttribute("pageTitle", "All Albums");


    req.getRequestDispatcher("WEB-INF/crrg/all-Albums.jsp").forward(req,resp);

  }
}