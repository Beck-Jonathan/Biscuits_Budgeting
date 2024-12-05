package com.beck.beck_demos.learnx.controllers;




import com.beck.beck_demos.learnx.Models.User;
import com.beck.beck_demos.learnx.data.UserDAO;
import com.beck.beck_demos.shared.Helpers;
import com.beck.beck_demos.shared.MyValidators;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/edit-profile")
public class EditProfile extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    User userFromSession = Helpers.getUserFromSession(session);
    if (userFromSession == null) {
      session.setAttribute("flashMessageWarning", "You must be logged in to edit your profile");
      resp.sendRedirect("login?redirect=edit-profile");
      return;
    }
    req.setAttribute("pageTitle", "Edit Profile");
    req.getRequestDispatcher("WEB-INF/learnx/editprofile.jsp").forward(req, resp);
  }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String firstName = req.getParameter("firstNameInput");

      String lastName = req.getParameter("lastNameInput");
      String email = req.getParameter("emailInput");
      String phone = req.getParameter("phoneInput");
      String language = req.getParameter("languageInput");
      Map<String, String> results= new HashMap<>();


      HttpSession session = req.getSession();
      User userFromSession = Helpers.getUserFromSession(session);
      int errors=0;
      if (userFromSession!=null){



        userFromSession.setFirst_name(firstName);
        userFromSession.setLast_name(lastName);
        userFromSession.setEmail(email);
        userFromSession.setPhone(phone);
        try
        {
          userFromSession.setLanguage(language);
        }
        catch (Exception ex){
          results.put("languageError", ex.getMessage());
          errors++;
        }
        if (errors<1) {
          int result = UserDAO.update(userFromSession);
          if (result == 1) {
            session.setAttribute("activeUser", userFromSession);
            session.setAttribute("language",userFromSession.getLanguage());
          }
        }


      }

      req.setAttribute("results",results);
      req.setAttribute("pageTitle", "Edit Profile");
      req.getRequestDispatcher("WEB-INF/learnx/editprofile.jsp").forward(req, resp);

    }
  }