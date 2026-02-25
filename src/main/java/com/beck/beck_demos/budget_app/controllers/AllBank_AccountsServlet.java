package com.beck.beck_demos.budget_app.controllers;
/******************
 Create the Servlet  For Viewing all of the  Bank_Account table
 Created By Jonathan Beck 1/22/2025
 ***************/

import com.beck.beck_demos.budget_app.data.Bank_AccountDAO;
import com.beck.beck_demos.budget_app.models.Bank_Account;
import com.beck.beck_demos.budget_app.models.User;
import com.beck.beck_demos.budget_app.iData.iBank_AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/all-Bank_Accounts")
public class AllBank_AccountsServlet extends HttpServlet {
  private iBank_AccountDAO bank_accountDAO;

  @Override
  public void init() {
    bank_accountDAO = new Bank_AccountDAO();

  }
  public void init(iBank_AccountDAO bank_accountDAO){
    this.bank_accountDAO = bank_accountDAO;

  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
//add roles here
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_B");
    String User_ID = "";
    if (user==null||!user.getRoles().contains("User")){
      resp.sendRedirect("budget_home");
      return;
    }
    try{
     User_ID = user.getUser_ID();
    }catch (Exception e){

    }

    session.setAttribute("currentPage",req.getRequestURL());
    List<Bank_Account> bank_accounts = null;
    try {
      bank_accounts =bank_accountDAO.getAllBank_Account(0,20, User_ID);
    } catch (Exception e) {
      bank_accounts = new ArrayList<>();
    }
    req.setAttribute("Bank_Accounts", bank_accounts);
    req.setAttribute("pageTitle", "All Bank_Accounts");
    req.getRequestDispatcher("WEB-INF/Budget_App/all-Bank_Accounts.jsp").forward(req,resp);

  }
}

