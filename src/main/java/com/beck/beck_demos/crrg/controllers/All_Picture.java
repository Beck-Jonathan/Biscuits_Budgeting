package com.beck.beck_demos.crrg.controllers;

import com.beck.beck_demos.crrg.data.Picture_DAO;
import com.beck.beck_demos.crrg.models.Picture;
import com.beck.beck_demos.crrg.models.Picture_VM;
import com.beck.beck_demos.crrg.models.User;
import com.beck.beck_demos.crrg.data_interfaces.iPicture_DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@WebServlet("/all-Pictures")
public class All_Picture extends HttpServlet {private iPicture_DAO pictureDAO;
  @Override
  public void init() throws ServletException{
    pictureDAO = new Picture_DAO();
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


    String album = req.getParameter("album");



    String contributor = req.getParameter("contributor");
    Integer contributorID=0;
    if (contributor!=null){
      contributorID = Integer.valueOf(contributor);
    }
    Integer AlbumID=0;
    if (album!=null){
      AlbumID = Integer.valueOf(album);
    }

    if (user==null||!user.isInRole(ROLES_NEEDED)){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }

    session.setAttribute("currentPage",req.getRequestURL());
    List<Picture_VM> pictures = null;

    try {


        pictures =pictureDAO.getAllPicture(20,0,AlbumID,contributorID);


    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    req.setAttribute("Pictures", pictures);
    req.setAttribute("pageTitle", "All Pictures");
    req.getRequestDispatcher("WEB-INF/crrg/all-Pictures.jsp").forward(req,resp);

  }
}
