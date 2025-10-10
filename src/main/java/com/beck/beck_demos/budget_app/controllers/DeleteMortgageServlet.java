package com.beck.beck_demos.budget_app.controllers;

/******************
 Create the Servlet For Deleteing from the Mortgage table
 Created By Jonathan Beck 10/3/2025
 ***************/

import com.beck.beck_demos.budget_app.data.MortgageDAO;
import com.beck.beck_demos.budget_app.models.Mortgage;
import com.beck.beck_demos.budget_app.models.User;
import com.beck.beck_demos.budget_app.iData.iMortgageDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Servlet implementation class AddMortgageServlet
 *
 * <p>This servlet handles the deletion of  {@link Mortgage} entries in the application.
 * It is mapped to the URL pattern <code>/deleteMortgage</code>.</p>
 *
 * <p><strong>HTTP GET</strong>: handles the request for deleting aMortgage via {@link MortgageDAO}. Access is restricted
 * to users with the "User" role. If unauthorized, the user is redirected to the login page.</p> * <p>Access to this servlet requires that the user session contain a {@link User} object that is logged in
 * appropriate roles set (specifically the "User" role).</p>
 *
 * <p>Created by Jonathan Beck on 10/3/2025</p>
 *
 * @author Jonathan Beck
 * @version 1.0
 * @see com.beck.beck_demos.budget_app.models.Mortgage
 * @see com.beck.beck_demos.budget_app.data.MortgageDAO
 * @see jakarta.servlet.http.HttpServlet
 */
@WebServlet("/deletemortgage")
public class DeleteMortgageServlet extends HttpServlet {
  private iMortgageDAO mortgageDAO;

  @Override
  public void init()  {
    mortgageDAO = new MortgageDAO();

  }
  public void init(iMortgageDAO mortgageDAO){
    this.mortgageDAO = mortgageDAO;

  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> results = new HashMap<>();

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
//add roles here
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_B");
    if (user==null||!user.getRoles().contains("User")){
      session.invalidate();
      resp.setStatus(302);
      resp.setContentType("text/plain");
      PrintWriter writer=resp.getWriter();
      writer.write("You are not logged in");
      writer.flush();
      writer.close();
      return;

    }

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Delete Mortgage");
    String MortgageID =req.getParameter("mortgageid");

    Integer result = 0;

      try{
        result = mortgageDAO.deleteMortgage(MortgageID);
      }
      catch(Exception ex){

        resp.setStatus(302);
        resp.setContentType("text/plain");
        PrintWriter writer=resp.getWriter();
        writer.write(ex.getMessage());
        writer.flush();
        writer.close();
        return;
      }



      resp.setStatus(200);
      resp.setContentType("text/plain");
      PrintWriter writer=resp.getWriter();
      writer.write(result.toString());
      writer.flush();
      writer.close();
      return;

  }
}


