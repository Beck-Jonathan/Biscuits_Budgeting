package com.beck.beck_demos.crrg.controllers;

import com.beck.beck_demos.crrg.data.Album_DAO;
import com.beck.beck_demos.crrg.models.Album;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/******************
 Create the Servlet Viuw/Edit from the Album table
 Created By Jonathan Beck 10/9/2024
 ***************/

@WebServlet("/editAlbum")
public class Edit_Album extends HttpServlet{
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

    String mode = req.getParameter("mode");
    int primaryKey = Integer.parseInt(req.getParameter("albumid"));
    Album album= new Album();
    album.setAlbum_ID(primaryKey);
    try{
      album=albumDAO.getAlbumByPrimaryKey(album);
    } catch (SQLException e) {
      req.setAttribute("dbStatus",e.getMessage());
    }

    session.setAttribute("album",album);
    req.setAttribute("mode",mode);
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Edit a Album");
    req.getRequestDispatcher("WEB-INF/crrg/EditAlbum.jsp").forward(req, resp);
  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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

    Map<String, String> results = new HashMap<>();
    String mode = req.getParameter("mode");
    req.setAttribute("mode",mode);
//to set the drop downs
//to get the old Album

    Album _oldAlbum= (Album)session.getAttribute("album");
//to get the new event's info
    String _Album_Name = req.getParameter("inputalbumAlbum_Name");
    _Album_Name=_Album_Name.trim();
    results.put("Album_Name",_Album_Name);
    Album _newAlbum = new Album();
    int errors =0;
    try {
      _newAlbum.setAlbum_Name(_Album_Name);
    } catch(IllegalArgumentException e) {results.put("albumAlbum_Nameerror", e.getMessage());
      errors++;
    }
    _newAlbum.setIs_Active(true);
//to update the database
    int result=0;
    if (errors==0){
      try{
        result=albumDAO.update(_oldAlbum,_newAlbum);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Album updated");
        resp.sendRedirect("all-Albums");
        return;
      } else {
        results.put("dbStatus","Album Not Updated");
      }
    }
//standard
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Edit a Album");
    req.getRequestDispatcher("WEB-INF/crrg/EditAlbum.jsp").forward(req, resp);
  }
}

