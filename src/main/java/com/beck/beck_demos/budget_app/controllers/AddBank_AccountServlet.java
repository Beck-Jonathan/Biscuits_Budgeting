package com.beck.beck_demos.budget_app.controllers;


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
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Character.isAlphabetic;
import static java.lang.Character.isDigit;

/******************
 Create the Servlet  For adding to The  Bank_Account table
 Created By Jonathan Beck 1/22/2025
 ***************/

@WebServlet("/addBank_Account")
public class AddBank_AccountServlet extends HttpServlet{
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
    if (user==null||!user.getRoles().contains("User")){
      resp.sendRedirect("/budget_in");
      return;
    }

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add Bank_Account");

    req.getRequestDispatcher("WEB-INF/Budget_App/AddBank_Account.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
//add roles here
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_B");
    if (user==null||!user.getRoles().contains("User")){
      resp.sendRedirect("/budget_in");
      return;
    }


    String _Bank_Account_ID = req.getParameter("inputbank_accountBank_Account_ID");
    if (_Bank_Account_ID!=null) {
      _Bank_Account_ID=_Bank_Account_ID.trim();

    }

    String _Account_Nickname = req.getParameter("inputbank_accountAccount_Nickname");
    if (_Account_Nickname!=null) {
      _Account_Nickname=_Account_Nickname.trim();
    }
    String _Balance = req.getParameter("inputbank_accountBalance");
    if (_Balance!=null) {
      _Balance=_Balance.trim();
      _Balance = _Balance.replace("$","");
    }
    String _Balance_Date = req.getParameter("inputbank_accountBalance_Date");
    if (_Balance_Date!=null) {
      _Balance_Date=_Balance_Date.trim();
    }
    Map<String, String> results = new HashMap<>();
    results.put("Bank_Account_ID",_Bank_Account_ID);

    results.put("Account_Nickname",_Account_Nickname);
    results.put("Balance",_Balance);
    results.put("Balance_Date",_Balance_Date);
    Bank_Account bank_account = new Bank_Account();
    int errors =0;
    try {
      bank_account.setBank_Account_ID(_Bank_Account_ID);
    } catch(Exception e) {results.put("bank_accountBank_Account_IDerror", e.getMessage());
      errors++;
    }
    try {
      bank_account.setUser_ID(user.getUser_ID());
    } catch(Exception e) {results.put("bank_accountUser_IDerror", e.getMessage());
      errors++;
    }
    try {
      bank_account.setAccount_Nickname(_Account_Nickname);
    } catch(Exception e) {results.put("bank_accountAccount_Nicknameerror", e.getMessage());
      errors++;
    }
    try {
      bank_account.setBalance(Double.valueOf(_Balance));
    } catch(Exception e) {results.put("bank_accountBalanceerror", e.getMessage());
      errors++;
    }
    try {
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      Date setDate = formatter.parse(_Balance_Date);
      bank_account.setBalance_Date(setDate);

    } catch(Exception e) {results.put("bank_accountBalance_Dateerror", e.getMessage());
      errors++;
    }
    int result=0;
    if (errors==0){
      try{
        result=bank_accountDAO.add(bank_account);
      }catch(Exception ex){
        results.put("dbError","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Bank_Account Added");
        req.setAttribute("results",results);
        resp.sendRedirect("all-Bank_Accounts");
        return;
      } else {
        results.put("dbStatus","Bank_Account Not Added");

      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Create a Bank_Account ");
    req.getRequestDispatcher("WEB-INF/Budget_App/AddBank_Account.jsp").forward(req, resp);

  }
}

