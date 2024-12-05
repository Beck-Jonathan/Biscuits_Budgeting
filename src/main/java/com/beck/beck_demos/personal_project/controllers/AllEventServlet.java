package com.beck.beck_demos.personal_project.controllers;

import com.beck.beck_demos.personal_project.data.EventDAO;
import com.beck.beck_demos.personal_project.models.Event;
import com.beck.beck_demos.personal_project.models.EventVM;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;

/******************
 Create the Servlet  For Viewing all of the  Event table
 Created By Jonathan Beck3/18/2024
 ***************/
@WebServlet("/all-Events")
public class AllEventServlet extends HttpServlet {
  @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  HttpSession session = req.getSession();
  session.setAttribute("currentPage",req.getRequestURL());
  List<EventVM> Events = null;

    Events =EventDAO.getAllEvent();

  req.setAttribute("Events", Events);
  req.setAttribute("pageTitle", "All Events");
  req.getRequestDispatcher("WEB-INF/personal-project/all-Events.jsp").forward(req,resp);
  }
}
