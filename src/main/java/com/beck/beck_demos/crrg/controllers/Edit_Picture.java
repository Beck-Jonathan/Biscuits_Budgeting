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
  public void init(iAlbum_DAO album_dao, iContributor_DAO contributor_DAO, iPicture_DAO picture_DAO){
    this.album_DAO=album_dao;
    this.contributor_DAO=contributor_DAO;
    this.picture_DAO=picture_DAO;
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
      resp.sendRedirect("/crrgLogin");return;
    }

    String mode = req.getParameter("mode");
    int primaryKey = -1;
    try{
      primaryKey = Integer.parseInt(req.getParameter("pictureid"));
    }catch (Exception e) {
      req.setAttribute("dbStatus",e.getMessage());
    }Picture picture= new Picture();
    try{
      picture.setPicture_ID(primaryKey);
    } catch (Exception e){
      req.setAttribute("dbStatus",e.getMessage());
    }
    try{
      picture=picture_DAO.getPictureByPrimaryKey(picture);
    } catch (SQLException e) {
      req.setAttribute("dbStatus",e.getMessage());
    }

    session.setAttribute("picture",picture);
    req.setAttribute("mode",mode);
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Edit Picture");
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
      resp.sendRedirect("/crrgLogin");return;
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
    req.setAttribute("Albums", allAlbums);

    req.setAttribute("Contributors", allContributors);
//to get the old Picture

    Picture _oldPicture= (Picture)session.getAttribute("picture");
//to get the new event's info
    String _Album_ID = req.getParameter("inputpictureAlbum_ID");
    if (_Album_ID!=null){
      _Album_ID=_Album_ID.trim();
    }
    String _Contributor_ID = req.getParameter("inputpictureContributor_ID");
    if (_Contributor_ID!=null){
      _Contributor_ID=_Contributor_ID.trim();
    }
    String _Web_Address = req.getParameter("inputpictureWeb_Address");
    if (_Web_Address!=null){
      _Web_Address=_Web_Address.trim();
    }
    String _Description = req.getParameter("inputpictureDescription");
    if (_Description!=null){
      _Description=_Description.trim();
    }
    String _Is_Active = req.getParameter("inputpictureIs_Active");
    if (_Is_Active!=null){
      _Is_Active=_Is_Active.trim();
    }
    String _is_Approved = req.getParameter("inputpictureis_Approved");
    if (_is_Approved!=null){
      _is_Approved=_is_Approved.trim();
    }
    results.put("Album_ID",_Album_ID);
    results.put("Contributor_ID",_Contributor_ID);
    results.put("Web_Address",_Web_Address);
    results.put("Description",_Description);
    results.put("Is_Active",_Is_Active);
    results.put("is_Approved",_is_Approved);
    Picture _newPicture = new Picture();
    int errors =0;
    try {
      _newPicture.setAlbum_ID(Integer.valueOf(_Album_ID));
    } catch(Exception e) {results.put("pictureAlbum_IDerror", e.getMessage());
      errors++;
    }
    try {
      _newPicture.setContributor_ID(Integer.valueOf(_Contributor_ID));
    } catch(Exception e) {results.put("pictureContributor_IDerror", e.getMessage());
      errors++;
    }
    try {
      _newPicture.setWeb_Address(_Web_Address);
    } catch(Exception e) {results.put("pictureWeb_Addresserror", e.getMessage());
      errors++;
    }
    try {
      _newPicture.setDescription(_Description);
    } catch(Exception e) {results.put("pictureDescriptionerror", e.getMessage());
      errors++;
    }
    try {
      _newPicture.setIs_Active(Boolean.parseBoolean(_Is_Active));
    } catch(Exception e) {results.put("pictureIs_Activeerror", e.getMessage());
      errors++;
    }
    try {
      _newPicture.setis_Approved(Boolean.parseBoolean(_is_Approved));
    } catch(Exception e) {results.put("pictureis_Approvederror", e.getMessage());
      errors++;
    }
    _newPicture.setIs_Active(true);
//to update the database
    int result=0;
    if (errors==0){
      try{
        result=picture_DAO.update(_oldPicture,_newPicture);
      }catch(Exception ex){
        results.put("dbError","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Picture updated");
        req.setAttribute("results",results);
        resp.sendRedirect("all-Pictures");
        return;
      } else {
        results.put("dbStatus","Picture Not Updated");
      }
    }
//standard
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Edit a Picture ");
    req.getRequestDispatcher("WEB-INF/crrg/EditPicture.jsp").forward(req, resp);
  }
}

