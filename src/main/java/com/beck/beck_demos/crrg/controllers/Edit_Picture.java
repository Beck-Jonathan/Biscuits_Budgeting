package com.beck.beck_demos.crrg.controllers;
import com.beck.beck_demos.crrg.data.Album_DAO;
import com.beck.beck_demos.crrg.data.Contributor_DAO;
import com.beck.beck_demos.crrg.data.Picture_DAO;
import com.beck.beck_demos.crrg.models.Album;
import com.beck.beck_demos.crrg.models.Contributor;
import com.beck.beck_demos.crrg.models.Picture;
import com.beck.beck_demos.crrg.models.User;
import com.beck.beck_demos.crrg.data_interfaces.iPicture_DAO;
import com.beck.beck_demos.crrg.data_interfaces.iAlbum_DAO;
import com.beck.beck_demos.crrg.data_interfaces.iContributor_DAO;
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
 Create the Servlet Viuw/Edit from the Picture table
 Created By Jonathan Beck 10/9/2024
 ***************/
@WebServlet("/editPicture")
public class Edit_Picture extends HttpServlet{
  private iAlbum_DAO album_DAO;
  private iContributor_DAO contributor_DAO;
  private iPicture_DAO picture_DAO;
  @Override
  public void init() throws ServletException {
    album_DAO = new Album_DAO();
    contributor_DAO = new Contributor_DAO();
    picture_DAO = new Picture_DAO();
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
    int primaryKey = Integer.parseInt(req.getParameter("pictureid"));
    Picture picture= new Picture();
    picture.setPicture_ID(primaryKey);
    try{
      picture=picture_DAO.getPictureByPrimaryKey(picture);
    } catch (SQLException e) {
      req.setAttribute("dbStatus",e.getMessage());
    }

    session.setAttribute("picture",picture);
    req.setAttribute("mode",mode);
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Edit a Picture");
    List<Album> allAlbums;
    List<Contributor> allContributors;
    try {
      allAlbums = album_DAO.getDistinctAlbumForDropdown();
      allContributors = contributor_DAO.getDistinctContributorForDropdown();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    req.setAttribute("Albums", allAlbums);
    req.setAttribute("Contributors", allContributors);
    req.getRequestDispatcher("WEB-INF/crrg/EditPicture.jsp").forward(req, resp);
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
    List<Album> allAlbums;
    List<Contributor> allContributors;
    try {
      allAlbums = album_DAO.getDistinctAlbumForDropdown();
      allContributors = contributor_DAO.getDistinctContributorForDropdown();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
//to get the old Picture

    Picture _oldPicture= (Picture)session.getAttribute("picture");
//to get the new event's info
    String _Album_ID = req.getParameter("inputpictureAlbum_ID");
    _Album_ID=_Album_ID.trim();
    String _Contributor_ID = req.getParameter("inputpictureContributor_ID");
    _Contributor_ID=_Contributor_ID.trim();
    String _Web_Address = req.getParameter("inputpictureWeb_Address");
    _Web_Address=_Web_Address.trim();
    String _description = req.getParameter("inputpicturedescription");
    _description=_description.trim();
    String _Is_Active = req.getParameter("inputpictureIs_Active");
    //_Is_Active=_Is_Active.trim();
    String _is_Approved = req.getParameter("inputpictureis_Approved");
    //_is_Approved=_is_Approved.trim();
    results.put("Album_ID",_Album_ID);
    results.put("Contributor_ID",_Contributor_ID);
    results.put("Web_Address",_Web_Address);
    results.put("description",_description);
    results.put("Is_Active",_Is_Active);
    results.put("is_Approved",_is_Approved);
    Picture _newPicture = new Picture();
    int errors =0;
    try {
      _newPicture.setAlbum_ID(Integer.valueOf(_Album_ID));
    } catch(IllegalArgumentException e) {results.put("pictureAlbum_IDerror", e.getMessage());
      errors++;
    }
    try {
      _newPicture.setContributor_ID(Integer.valueOf(_Contributor_ID));
    } catch(IllegalArgumentException e) {results.put("pictureContributor_IDerror", e.getMessage());
      errors++;
    }
    try {
      _newPicture.setWeb_Address(_Web_Address);
    } catch(IllegalArgumentException e) {results.put("pictureWeb_Addresserror", e.getMessage());
      errors++;
    }

    try {
      _newPicture.setdescription(_description);
    } catch(IllegalArgumentException e) {results.put("picturedescriptionerror", e.getMessage());
      errors++;
    }
    try {
      _newPicture.setIs_Active(_oldPicture.getIs_Active());
    } catch(IllegalArgumentException e) {results.put("pictureIs_Activeerror", e.getMessage());
      errors++;
    }
    try {
      _newPicture.setis_Approved(Boolean.parseBoolean(_is_Approved));
    } catch(IllegalArgumentException e) {results.put("pictureis_Approvederror", e.getMessage());
      errors++;
    }

//to update the database
    int result=0;
    if (errors==0){
      try{
        result=picture_DAO.update(_oldPicture,_newPicture);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Picture updated");
        resp.sendRedirect("all-Pictures");
        return;
      } else {
        results.put("dbStatus","Picture Not Updated");
      }
    }
//standard
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Edit a Picture");
    req.getRequestDispatcher("WEB-INF/crrg/EditPicture.jsp").forward(req, resp);
  }
}


