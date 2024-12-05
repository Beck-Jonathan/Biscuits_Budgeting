package com.beck.beck_demos.learnx.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("pageTitle", "Logout");
    HttpSession session = req.getSession();
    session.removeAttribute("activeUser");
    session.setAttribute("flashMessageSuccess","Logged out! C-A-L, C-U L8R");
    resp.sendRedirect("learnx");
  }


}