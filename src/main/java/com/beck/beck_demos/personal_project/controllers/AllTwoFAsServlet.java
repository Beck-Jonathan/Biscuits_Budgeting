package com.beck.beck_demos.personal_project.controllers;

/******************
 Create the Servlet  For Viewing all of the  TwoFA table
 Created By Jonathan Beck3/18/2024
 ***************/

import com.beck.beck_demos.personal_project.data.TwoFADAO;
import com.beck.beck_demos.personal_project.models.TwoFA;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@WebServlet("/all-TwoFAs")
public class AllTwoFAsServlet extends HttpServlet {@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  HttpSession session = req.getSession();
  session.setAttribute("currentPage",req.getRequestURL());
  List<TwoFA> twofas = null;

  twofas =TwoFADAO.getAllTwoFA();

  req.setAttribute("TwoFAs", twofas);
  req.setAttribute("pageTitle", "All TwoFAs");
  req.getRequestDispatcher("WEB-INF/personal-project/all-TwoFAs.jsp").forward(req,resp);

}
}

