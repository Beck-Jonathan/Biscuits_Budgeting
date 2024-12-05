package com.beck.beck_demos.crrg.controllers;


import com.beck.beck_demos.crrg.data.Sponsor_DAO;
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
/******************
 Create the Servlet  For adding to The  Sponsor table
 Created By Jonathan Beck 11/14/2024
 ***************/

@WebServlet("/addSponsor")
public class Add_Sponsor extends HttpServlet{

  private static iSponsor_DAO sponsorDAO;
  static List<String> allTiers;
  @Override
  public void init() throws ServletException{
    sponsorDAO = new Sponsor_DAO();
    try {
      allTiers = sponsorDAO.selectAllTiersForDropDown();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
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
    req.setAttribute("pageTitle", "Add Sponsor");

    req.setAttribute("Tiers", allTiers);
    req.getRequestDispatcher("WEB-INF/crrg/AddSponsor.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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


    req.setAttribute("Tiers", allTiers);
    String _Sponsor_ID = req.getParameter("inputsponsorSponsor_ID");
    _Sponsor_ID=_Sponsor_ID.trim();
    String _Tier_ID = req.getParameter("inputsponsorTier_ID");
    _Tier_ID=_Tier_ID.trim();
    String _website = req.getParameter("inputsponsorwebsite");
    _website=_website.trim();
    String _Description = req.getParameter("inputsponsorDescription");
    _Description=_Description.trim();

    Map<String, String> results = new HashMap<>();
    results.put("Sponsor_ID",_Sponsor_ID);
    results.put("Tier_ID",_Tier_ID);
    results.put("website",_website);
    results.put("Description",_Description);

    Sponsor sponsor = new Sponsor();
    int errors =0;
    try {
      sponsor.setSponsor_ID(_Sponsor_ID);
    } catch(IllegalArgumentException e) {results.put("sponsorSponsor_IDerror", e.getMessage());
      errors++;
    }
    try {
      sponsor.setTier_ID(_Tier_ID);
    } catch(IllegalArgumentException e) {results.put("sponsorTier_IDerror", e.getMessage());
      errors++;
    }
    try {
      sponsor.setwebsite(_website);
    } catch(IllegalArgumentException e) {results.put("sponsorwebsiteerror", e.getMessage());
      errors++;
    }
    try {
      sponsor.setDescription(_Description);
    } catch(IllegalArgumentException e) {results.put("sponsorDescriptionerror", e.getMessage());
      errors++;
    }
    try {
      sponsor.setis_active(true);
    } catch(IllegalArgumentException e) {results.put("sponsoris_activeerror", e.getMessage());
      errors++;
    }
    int result=0;
    if (errors==0){
      try{
        result=sponsorDAO.add(sponsor);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Sponsor Added");
        resp.sendRedirect("all-Sponsors");
        return;
      } else {
        results.put("dbStatus","Sponsor Not Added");

      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Create a Sponsor ");
    req.getRequestDispatcher("WEB-INF/crrg/AddSponsor.jsp").forward(req, resp);

  }
}


