package com.beck.beck_demos.crrg.controllers;

import com.beck.beck_demos.crrg.data.Sponsor_DAO;
import com.beck.beck_demos.crrg.data.Sponsor_Tag_DAO;
import com.beck.beck_demos.crrg.models.*;
import com.beck.beck_demos.crrg.data_interfaces.iSponsor_DAO;
import com.beck.beck_demos.crrg.data_interfaces.ISponsor_Tag_DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
/******************
 Create the Servlet  For adding to The  Sponsor_Tag table
 Created By Jonathan Beck 11/14/2024
 ***************/

@WebServlet("/addSponsor_Tag")
@MultipartConfig(

    fileSizeThreshold = 1024 * 1024, // 1 MB
    maxFileSize = 1024 * 1024 * 10,      // 10 MB
    maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class Add_Sponsor_Image extends HttpServlet{
  private static iSponsor_DAO sponsor_DAO;
  private static ISponsor_Tag_DAO sponsor_tag_DAO;
  static List<String> allSponsors;
  private static final String UPLOAD_DIR = "crrg";



  @Override
  public void init() throws ServletException{
    sponsor_DAO = new Sponsor_DAO();
    sponsor_tag_DAO = new Sponsor_Tag_DAO();

  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
//add roles here
    ROLES_NEEDED.add("Jonathan");
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");
    if (user==null||!user.isInRole(ROLES_NEEDED)){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add Sponsor_Tag");
    try {
      allSponsors = sponsor_DAO.getDistinctSponsorForDropdown();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    req.setAttribute("Sponsors", allSponsors);

    req.getRequestDispatcher("WEB-INF/crrg/AddSponsorImage.jsp").forward(req, resp);
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
    String applicationPath = req.getServletContext().getRealPath("");
    String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
    File fileSaveDir = new File(uploadFilePath);
    if (!fileSaveDir.exists()) {
      fileSaveDir.mkdirs();
    }

    try {
      allSponsors = sponsor_DAO.getDistinctSponsorForDropdown();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    req.setAttribute("Sponsors", allSponsors);

    Part filePart = req.getPart("inputsponsor_tagPicture_ID");

    String fileName = filePart.getSubmittedFileName();
    Map<String, String> results = new HashMap<>();
    try {
      for (Part part : req.getParts()) {
        part.write(uploadFilePath + File.separator + fileName);

      }
    } catch (Exception ex){
      results.put("dbStatus",ex.getMessage());
      req.setAttribute("results", results);
      req.setAttribute("pageTitle", "Create a Team ");
      req.getRequestDispatcher("WEB-INF/crrg/AddSponsorImage.jsp").forward(req, resp);
      return;
    }

    String _Sponsor_ID = req.getParameter("inputsponsor_tagSponsor_ID");
    _Sponsor_ID=_Sponsor_ID.trim();

    String _Description = req.getParameter("inputsponsor_tagDescription");
    _Description=_Description.trim();

    results.put("Album_ID","Sponsors");
    results.put("Contributor","CRRG");
    results.put("WebAddress",uploadFilePath + File.separator + fileName);
    results.put("Sponsor_ID",_Sponsor_ID);
    results.put("Description",_Description);
    int errors =0;
    Sponsor_Tag_VM sponsor_tag = new Sponsor_Tag_VM();
    Picture picture = new Picture();
    picture.setAlbum_ID(1);
    picture.setContributor_ID(1);

    try {
      picture.setWeb_Address(uploadFilePath + File.separator + fileName);
    }  catch (Exception e){
      results.put("sponsor_tagDescriptionerror", e.getMessage());
      errors++;
    }



    sponsor_tag.setPicture(picture);
    sponsor_tag.setSponsor_ID(_Sponsor_ID);
    sponsor_tag.setis_Primary(true);




    try {
      sponsor_tag.setDescription(_Description);
    } catch(IllegalArgumentException e) {
      results.put("sponsor_tagDescriptionerror", e.getMessage());
      errors++;
    }

    int result=0;
    if (errors==0){
      try{

        result=sponsor_tag_DAO.addSponsor_Tag(sponsor_tag);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Sponsor_Tag Added");
        resp.sendRedirect("all-Sponsor_Tags");
        return;
      } else {
        results.put("dbStatus","Sponsor_Tag Not Added");

      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Create a Sponsor_Tag ");
    req.getRequestDispatcher("WEB-INF/crrg/AddSponsorImage.jsp").forward(req, resp);

  }
}

