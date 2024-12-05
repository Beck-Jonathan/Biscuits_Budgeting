package com.beck.beck_demos.crrg.controllers;
import com.beck.beck_demos.crrg.data.Contributor_DAO;
import com.beck.beck_demos.crrg.models.Contributor;
import com.beck.beck_demos.crrg.models.Contributor_VM;
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
@WebServlet("/all-Contributors")
public class All_Contributor extends HttpServlet {
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

    session.setAttribute("currentPage",req.getRequestURL());
    List<Contributor_VM> contributors = null;

    try {
      contributors =contributorDAO.getAllContributor(20,0);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    req.setAttribute("Contributors", contributors);
    req.setAttribute("pageTitle", "All Contributors");
    req.getRequestDispatcher("WEB-INF/crrg/all-Contributors.jsp").forward(req,resp);

  }
}
