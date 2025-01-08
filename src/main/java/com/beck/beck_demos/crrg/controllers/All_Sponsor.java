package com.beck.beck_demos.crrg.controllers;

/******************
 Create the Servlet  For Viewing all of the  Sponsor table
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
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
@WebServlet("/all-Sponsors")
public class All_Sponsor extends HttpServlet {
  private iSponsor_DAO sponsorDAO;
  @Override
  public void init() throws ServletException{
    sponsorDAO = new Sponsor_DAO();
  }
  public void init(iSponsor_DAO sponsorDAO){
    this.sponsorDAO = sponsorDAO;
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

    session.setAttribute("currentPage",req.getRequestURL());
    String orderBy="";
    orderBy=req.getParameter("Order_By");
    String Tier_ID ="";
    Tier_ID=req.getParameter("Tier_ID");
    List<Sponsor> sponsors = null;

    try {
      sponsors =sponsorDAO.getAllSponsor(Tier_ID);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    req.setAttribute("Sponsors", sponsors);
    req.setAttribute("pageTitle", "All Sponsors");
    req.getRequestDispatcher("WEB-INF/crrg/all-Sponsors.jsp").forward(req,resp);

  }
}

