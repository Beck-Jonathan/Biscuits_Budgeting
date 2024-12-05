package com.beck.beck_demos.shared;
import io.github.cdimascio.dotenv.Dotenv;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/error")
public class ErrorHandler extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String errorCode = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE) + "";
    String exceptionType = req.getAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE) + "";
    String errorMsg = req.getAttribute(RequestDispatcher.ERROR_MESSAGE) + "";
    String servlet = req.getAttribute(RequestDispatcher.ERROR_SERVLET_NAME) + "";

    String result = "Error code: " + errorCode;
    if(!exceptionType.equals("")) {
      result += "<br>\nException: " + exceptionType;
    }
    result += "<br>\nMessage: " + errorMsg;
    result += "<br>\nServlet: " + servlet;
    System.err.println(result);


    if(!errorCode.equals("404")) {
     // EmailService.sendemail(Dotenv.load().get("ADMIN_EMAIL"), "Unknown error occured", result);
    }


    resp.setContentType("text/html");


    PrintWriter out = resp.getWriter();
    out.println("<html><body>");
    out.println("<pre>" + result + "</pre>");
    out.println("</body></html>");


        req.setAttribute("pageTitle", "Error");
        //req.getRequestDispatcher("WEB-INF/shared/error.jsp").forward(req, resp);
  }
}