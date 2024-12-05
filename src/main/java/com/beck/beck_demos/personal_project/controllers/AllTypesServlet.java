package com.beck.beck_demos.personal_project.controllers;

/******************
 Create the Servlet  For Viewing all of the  Type table
 Created By Jonathan Beck3/18/2024
 ***************/
import com.beck.beck_demos.personal_project.data.TypeDAO;
import com.beck.beck_demos.personal_project.models.Type;
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
@WebServlet("/all-Types")
public class AllTypesServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    session.setAttribute("currentPage", req.getRequestURL());
    List<Type> types = null;

    types = TypeDAO.getAllType();

    req.setAttribute("Types", types);
    req.setAttribute("pageTitle", "All Types");
    req.getRequestDispatcher("WEB-INF/personal-project/all-Type.jsp").forward(req, resp);
  }
}

