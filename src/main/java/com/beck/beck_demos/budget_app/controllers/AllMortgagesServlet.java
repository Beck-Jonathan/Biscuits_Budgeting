package com.beck.beck_demos.budget_app.controllers;

/******************
 Create the Servlet  For Viewing all of the  Mortgage table
 Created By Jonathan Beck 8/6/2024
 ***************/

import com.beck.beck_demos.budget_app.data.MortgageDAO;
import com.beck.beck_demos.budget_app.iData.iMortgageDAO;
import com.beck.beck_demos.budget_app.models.Mortgage;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/all-Mortgages")
public class AllMortgagesServlet extends HttpServlet {
  public iMortgageDAO mortgageDAO;

  @Override
  public void init() throws ServletException {
    mortgageDAO = new MortgageDAO();
  }
  public void init(iMortgageDAO mortgageDAO) {
    this.mortgageDAO = mortgageDAO;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_B");
    if (user==null||!user.getRoles().contains("User")){
      resp.sendRedirect("budget_home");
      return;
    }

    session.setAttribute("currentPage", req.getRequestURL());
    List<Mortgage> mortgages = null;

    mortgages = mortgageDAO.getMortgagebyUser(user.getUser_ID(), 100, 0);

    req.setAttribute("Mortgages", mortgages);
    req.setAttribute("pageTitle", "All Mortgages");
    req.getRequestDispatcher("WEB-INF/Budget_App/all_Mortgages.jsp").forward(req, resp);

  }
}
