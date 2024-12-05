package com.beck.beck_demos.garden.controller;

/******************
 Create the Servlet  For Viewing all of the  Plant table
 Created By Jonathan Beck 4/13/2024
 ***************/

import com.beck.beck_demos.garden.data.PlantDAO;
import com.beck.beck_demos.garden.model.Plant;
import com.beck.beck_demos.personal_project.models.User;
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
@WebServlet("/all-Plants")
public class AllPlantsServlet extends HttpServlet {@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
  int PRIVILEGE_NEEDED = 0;
  HttpSession session = req.getSession();
  User user = (User)session.getAttribute("User");


  session.setAttribute("currentPage",req.getRequestURL());
  List<Plant> plants = null;

  plants =PlantDAO.getAllPlant();

  req.setAttribute("Plants", plants);
  req.setAttribute("pageTitle", "All Plants");
  req.getRequestDispatcher("WEB-INF/garden/Plant_All.jsp").forward(req,resp);

}
}

