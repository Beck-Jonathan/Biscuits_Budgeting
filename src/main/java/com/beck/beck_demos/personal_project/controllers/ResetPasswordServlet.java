package com.beck.beck_demos.personal_project.controllers;

import com.beck.beck_demos.personal_project.data.PasswordResetDAO;
import com.beck.beck_demos.personal_project.data.TwoFADAO;
import com.beck.beck_demos.personal_project.data.UserDAO;
import com.beck.beck_demos.personal_project.models.TwoFA;
import com.beck.beck_demos.personal_project.models.User;
import com.beck.beck_demos.shared.EmailService;
import com.beck.beck_demos.shared.Helpers;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/resetpw")
public class ResetPasswordServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    session.setAttribute("loginFail", "");
    session.setAttribute("currentPage","home");
    req.setAttribute("pageTitle", "Home");
    String code = req.getParameter("code");
    String username = req.getParameter("user");
    String email = req.getParameter("email");
    User user = new User();
    if (code!=null && username!=null && email !=null) {
      user.setUser_Name(username);
      boolean result = false;
      try {

        result = PasswordResetDAO.verify(username, code);
        if (result) {
          PasswordResetDAO.delete(username, code);
          String newPW = Helpers.genPW();
          user.setUser_PW(newPW.toCharArray());
          result = UserDAO.resetPW(user);
          if (result) {
            EmailService.sendNewPassword(newPW, email, username);
            resp.sendRedirect("home");
            return;
          }
        }
      } catch (Exception e) {

      }

      req.getRequestDispatcher("WEB-INF/personal-project/home.jsp").forward(req, resp);
    }
    req.getRequestDispatcher("WEB-INF/personal-project/reset-password.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    req.setAttribute("pageTitle", "Reset Password!");

    String email = req.getParameter("inputuserEmail");
    String username  =req.getParameter("inputuserUserName");


    Map<String, String> results = new HashMap<>();
    results.put("email3",email);
    results.put("username3",username);

    User user = new User();

    int errors =0;

    try {
      user.setEmail(email);
    } catch(IllegalArgumentException e) {results.put("userEmailerror3", e.getMessage());
      errors++;
    }
    try {
      user.setUser_Name(username);
    } catch(IllegalArgumentException e) {results.put("userUser_Nameerror3", e.getMessage());
      errors++;
    }
    boolean emailFree = false;
    try {
      emailFree = UserDAO.emailFree(email);
    } catch (Exception e) {
      results.put("dbStatus",e.getMessage());
      errors++;
    }
    if (emailFree){
      results.put("userUser_Nameerror3", "This email is not associated with any account. Please Register");
      errors++;
    }
    String password = Helpers.genPW();
    String resetCode = Helpers.genPW();
    user.setUser_PW(password.toCharArray());



    boolean result=false;
    if (errors==0){
      try{

        result= PasswordResetDAO.add(username,resetCode);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result){

        HttpSession session = req.getSession();
        session.setAttribute("loginFail", "Password Reset email sent!");
        results.put("dbStatus","email sent");

        EmailService.sendReset(resetCode,email,username);

        resp.sendRedirect("home");
        return;
      } else {
        results.put("dbStatus","Password not reset");
        resp.sendRedirect("home");
        return;

      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Roller Derby! ");
    resp.sendRedirect("home");

  }
}
