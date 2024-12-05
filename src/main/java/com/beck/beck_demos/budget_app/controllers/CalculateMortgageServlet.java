package com.beck.beck_demos.budget_app.controllers;


//import com.beck.beck_demos.budget_app.data.MortgageDAO;
import com.beck.beck_demos.budget_app.models.Mortgage;
import com.beck.beck_demos.budget_app.models.Mortgage_VM;
import com.beck.beck_demos.budget_app.models.User;
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
/******************
 Create the Servlet  For adding to The  Mortgage table
 Created By Jonathan Beck 8/6/2024
 ***************/

@WebServlet("/calculateMortgage")
public class CalculateMortgageServlet extends HttpServlet{

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_B");
    if (user==null){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Mortgage");

    req.getRequestDispatcher("WEB-INF/Budget_App/CalcMortgage.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_B");
    if (user==null){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }



    String _Present_Value = req.getParameter("inputmortgagePresent_Value");
    _Present_Value=_Present_Value.trim();
    String _Future_Value = req.getParameter("inputmortgageFuture_Value");
    _Future_Value=_Future_Value.trim();
    String _Interest_Rate = req.getParameter("inputmortgageInterest_Rate");
    _Interest_Rate=_Interest_Rate.trim();
    String _Monthly_Payment = req.getParameter("inputmortgageMonthly_Payment");
    _Monthly_Payment=_Monthly_Payment.trim();
    String _Extra_Payment = req.getParameter("inputmortgageExtra_Payment");
    _Extra_Payment=_Extra_Payment.trim();
    String _Remaining_Term = req.getParameter("inputmortgageRemaining_Term");
    _Remaining_Term=_Remaining_Term.trim();
    Map<String, String> results = new HashMap<>();

    results.put("Present_Value",_Present_Value);
    results.put("Future_Value",_Future_Value);
    results.put("Interest_Rate",_Interest_Rate);
    results.put("Monthly_Payment",_Monthly_Payment);
    results.put("Extra_Payment",_Extra_Payment);
    results.put("Remaining_Term",_Remaining_Term);
    Mortgage_VM mortgage = new Mortgage_VM();
    int errors =0;
    try {
      mortgage.setUser_ID(user.getUser_ID());
    } catch(IllegalArgumentException e) {results.put("mortgageUser_IDerror", e.getMessage());
      errors++;
    }
    try {
      mortgage.setPresent_Value(Double.valueOf(_Present_Value));
    } catch(IllegalArgumentException e) {results.put("mortgagePresent_Valueerror", e.getMessage());
      errors++;
    }
    try {
      mortgage.setFuture_Value(Double.valueOf(_Future_Value));
    } catch(IllegalArgumentException e) {results.put("mortgageFuture_Valueerror", e.getMessage());
      errors++;
    }
    try {
      mortgage.setInterest_Rate(Double.valueOf(_Interest_Rate));
    } catch(IllegalArgumentException e) {results.put("mortgageInterest_Rateerror", e.getMessage());
      errors++;
    }
    try {
      mortgage.setMonthly_Payment(Double.valueOf(_Monthly_Payment));
    } catch(IllegalArgumentException e) {results.put("mortgageMonthly_Paymenterror", e.getMessage());
      errors++;
    }
    try {
      mortgage.setExtra_Payment(Double.valueOf(_Extra_Payment));
    } catch(IllegalArgumentException e) {results.put("mortgageExtra_Paymenterror", e.getMessage());
      errors++;
    }
    try {
      mortgage.setRemaining_Term(Integer.valueOf(_Remaining_Term));
    } catch(IllegalArgumentException e) {results.put("mortgageRemaining_Termerror", e.getMessage());
      errors++;
    }
    int result=0;
    if (errors==0){
      try{
        mortgage.setMonths();
        //result=MortgageDAO.add(mortgage);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Mortgage Added");
        resp.sendRedirect("all-Mortgages");
        return;
      } else {
        results.put("dbStatus","Mortgage Not Added");

      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Mortgage");
    req.getRequestDispatcher("WEB-INF/Budget_App/CalcMortgage.jsp").forward(req, resp);

  }
}

