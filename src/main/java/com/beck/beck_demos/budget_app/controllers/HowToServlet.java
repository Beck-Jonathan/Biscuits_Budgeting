package com.beck.beck_demos.budget_app.controllers;



import com.beck.beck_demos.budget_app.data.*;
import com.beck.beck_demos.budget_app.models.*;
import com.beck.beck_demos.shared.EmailService;
import com.mysql.cj.Session;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/howtouse")
public class HowToServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();

    session.setAttribute("currentPage", req.getRequestURL());

    req.getRequestDispatcher("WEB-INF/Budget_App/howToUse.jsp").forward(req, resp);
  }
}
