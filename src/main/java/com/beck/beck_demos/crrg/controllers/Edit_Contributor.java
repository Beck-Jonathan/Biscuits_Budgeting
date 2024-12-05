package com.beck.beck_demos.crrg.controllers;
import com.beck.beck_demos.crrg.data.Contributor_DAO;
import com.beck.beck_demos.crrg.models.Contributor;
import com.beck.beck_demos.crrg.models.User;
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
 Create the Servlet Viuw/Edit from the Contributor table
 Created By Jonathan Beck 10/15/2024
 ***************/
@WebServlet("/editContributor")
public class Edit_Contributor extends HttpServlet{
  private iContributor_DAO contributorDAO;
  @Override
  public void init() throws ServletException{
    contributorDAO = new Contributor_DAO();
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
    int primaryKey = Integer.parseInt(req.getParameter("contributorid"));
    Contributor contributor= new Contributor();
    contributor.setContributor_ID(primaryKey);
    try{
      contributor=contributorDAO.getContributorByPrimaryKey(contributor);
    } catch (SQLException e) {
      req.setAttribute("dbStatus",e.getMessage());
    }

    session.setAttribute("contributor",contributor);
    req.setAttribute("mode",mode);
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add Contributor");
    req.getRequestDispatcher("WEB-INF/crrg/EditContributor.jsp").forward(req, resp);
  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
//add roles here
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");
    ROLES_NEEDED.add("Jonathan");
    if (user==null||!user.isInRole(ROLES_NEEDED)){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }

    Map<String, String> results = new HashMap<>();
    String mode = req.getParameter("mode");
    req.setAttribute("mode",mode);
//to set the drop downs
//to get the old Contributor

    Contributor _oldContributor= (Contributor)session.getAttribute("contributor");
//to get the new contributor's info
    String _First_Name = req.getParameter("inputcontributorFirst_Name");
    _First_Name=_First_Name.trim();
    String _Last_Name = req.getParameter("inputcontributorLast_Name");
    _Last_Name=_Last_Name.trim();
    String _email = req.getParameter("inputcontributoremail");
    _email=_email.trim();
    results.put("First_Name",_First_Name);
    results.put("Last_Name",_Last_Name);
    results.put("email",_email);
    Contributor _newContributor = new Contributor();
    int errors =0;
    try {
      _newContributor.setFirst_Name(_First_Name);
    } catch(IllegalArgumentException e) {results.put("contributorFirst_Nameerror", e.getMessage());
      errors++;
    }
    try {
      _newContributor.setLast_Name(_Last_Name);
    } catch(IllegalArgumentException e) {results.put("contributorLast_Nameerror", e.getMessage());
      errors++;
    }
    try {
      _newContributor.setemail(_email);
    } catch(IllegalArgumentException e) {results.put("contributoremailerror", e.getMessage());
      errors++;
    }

//to update the database
    int result=0;
    if (errors==0){
      try{
        result=contributorDAO.update(_oldContributor,_newContributor);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Contributor updated");
        resp.sendRedirect("all-Contributors");
        return;
      } else {
        results.put("dbStatus","Contributor Not Updated");
      }
    }
//standard
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Edit a Contributor ");
    req.getRequestDispatcher("WEB-INF/crrg/EditContributor.jsp").forward(req, resp);
  }
}

