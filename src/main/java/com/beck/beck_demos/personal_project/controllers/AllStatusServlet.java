package com.beck.beck_demos.personal_project.controllers;

/******************
 Create the Servlet  For Viewing all of the  Status table
 Created By Jonathan Beck3/18/2024
 ***************/
import com.beck.beck_demos.personal_project.data.StatusDAO;
import com.beck.beck_demos.personal_project.models.Status;
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
@WebServlet("/all-Status")
public class AllStatusServlet extends HttpServlet {
  @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  HttpSession session = req.getSession();
  session.setAttribute("currentPage",req.getRequestURL());
  List<Status> statuss = null;

    statuss =StatusDAO.getAllStatus();

  req.setAttribute("Statuss", statuss);
  req.setAttribute("pageTitle", "All Statuss");
  req.getRequestDispatcher("WEB-INF/personal-project/all-Status.jsp").forward(req,resp);



}
}