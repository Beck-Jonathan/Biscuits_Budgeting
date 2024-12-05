package com.beck.beck_demos.crrg.controllers;

/******************
 Create the Servlet For Deleteing from the Sponsor table
 Created By Jonathan Beck 11/14/2024
 ***************/

import com.beck.beck_demos.crrg.data.Sponsor_DAO;
import com.beck.beck_demos.crrg.models.Sponsor;
import com.beck.beck_demos.crrg.models.User;
import com.beck.beck_demos.crrg.data_interfaces.iSponsor_DAO;
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
@WebServlet("/deletesponsor")
public class Delete_Sponsor extends HttpServlet {
  private iSponsor_DAO sponsorDAO;
  @Override
  public void init() throws ServletException{
    sponsorDAO = new Sponsor_DAO();
  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> results = new HashMap<>();

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

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Delete Sponsor");
    String SponsorID = req.getParameter("sponsorid");
    int mode = Integer.valueOf(req.getParameter("mode"));
    int result = 0;
    if (mode==0){
      try{
        result = sponsorDAO.deactivateSponsor(SponsorID);
      }
      catch(Exception ex){
        results.put("dbStatus",ex.getMessage());
      }
    }
    else {
      try{
        result = sponsorDAO.undeleteSponsor(SponsorID);
      }
      catch(Exception ex){
        results.put("dbStatus",ex.getMessage());
      }
    }
    List<Sponsor> sponsors = null;
    try {
      sponsors = sponsorDAO.getAllSponsor("");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    req.setAttribute("results",results);
    req.setAttribute("Sponsors", sponsors);
    req.setAttribute("pageTitle", "All Sponsor");
    req.getRequestDispatcher("WEB-INF/crrg/all-Sponsors.jsp").forward(req, resp);
  }
}

