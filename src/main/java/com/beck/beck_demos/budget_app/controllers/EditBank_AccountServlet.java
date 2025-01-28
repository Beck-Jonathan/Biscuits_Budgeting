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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/******************
 Create the Servlet Viuw/Edit from the Bank_Account table
 Created By Jonathan Beck 1/22/2025
 ***************/

@WebServlet("/editBank_Account")
public class EditBank_AccountServlet extends HttpServlet{

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
      resp.sendRedirect("/budget_appLogin");
      return;
    }

    String mode = req.getParameter("mode");
    String primaryKey = "";
    try{
      primaryKey = (req.getParameter("bank_accountid"));
    }catch (Exception e) {
      req.setAttribute("dbStatus",e.getMessage());
    }Bank_Account bank_account= new Bank_Account();
    try{
      bank_account.setBank_Account_ID(primaryKey);
    } catch (Exception e){
      req.setAttribute("dbStatus",e.getMessage());
    }
    try{
      bank_account=bank_accountDAO.getBank_AccountByPrimaryKey(bank_account);
    } catch (SQLException e) {
      req.setAttribute("dbStatus",e.getMessage());
    }
    session.setAttribute("bank_account",bank_account);
    req.setAttribute("mode",mode);
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Edit Bank_Account");

    req.getRequestDispatcher("WEB-INF/Budget_App/EditBank_Account.jsp").forward(req, resp);
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
      resp.sendRedirect("/budget_appLogin");
      return;
    }

    Map<String, String> results = new HashMap<>();
    String mode = req.getParameter("mode");
    req.setAttribute("mode",mode);
//to set the drop downs

//to get the old Bank_Account
    Bank_Account _oldBank_Account= (Bank_Account)session.getAttribute("bank_account");
//to get the new event's info
    String _Bank_Account_ID = req.getParameter("inputbank_accountBank_Account_ID");
    if (_Bank_Account_ID!=null){
      _Bank_Account_ID=_Bank_Account_ID.trim();
    }
    Integer _User_ID = user.getUser_ID();

    String _Account_Nickname = req.getParameter("inputbank_accountAccount_Nickname");
    if (_Account_Nickname!=null){
      _Account_Nickname=_Account_Nickname.trim();
    }
    String _Balance = req.getParameter("inputbank_accountBalance");
    if (_Balance!=null){
      _Balance=_Balance.trim();
      _Balance = _Balance.replace("$","");
    }
    String _Balance_Date = req.getParameter("inputbank_accountBalance_Date");
    if (_Balance_Date!=null){
      _Balance_Date=_Balance_Date.trim();
    }
    results.put("Bank_Account_ID",_Bank_Account_ID);
    results.put("User_ID",_User_ID.toString());
    results.put("Account_Nickname",_Account_Nickname);
    results.put("Balance",_Balance);
    results.put("Balance_Date",_Balance_Date);
    Bank_Account _newBank_Account = new Bank_Account();
    int errors =0;
    try {
      _newBank_Account.setBank_Account_ID(_Bank_Account_ID);
    } catch(Exception e) {results.put("bank_accountBank_Account_IDerror", e.getMessage());
      errors++;
    }
    try {
      _newBank_Account.setUser_ID(_User_ID);
    } catch(Exception e) {results.put("bank_accountUser_IDerror", e.getMessage());
      errors++;
    }
    try {
      _newBank_Account.setAccount_Nickname(_Account_Nickname);
    } catch(Exception e) {results.put("bank_accountAccount_Nicknameerror", e.getMessage());
      errors++;
    }
    try {
      _newBank_Account.setBalance(Double.valueOf(_Balance));
    } catch(Exception e) {results.put("bank_accountBalanceerror", e.getMessage());
      errors++;
    }
    try {
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      Date setDate = formatter.parse(_Balance_Date);
      _newBank_Account.setBalance_Date(setDate);
    } catch(Exception e) {results.put("bank_accountBalance_Dateerror", e.getMessage());
      errors++;
    }
//to update the database
    int result=0;
    if (errors==0){
      try{
        result=bank_accountDAO.update(_oldBank_Account,_newBank_Account);
      }catch(Exception ex){
        results.put("dbError","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Bank_Account updated");
        req.setAttribute("results",results);
        resp.sendRedirect("all-Bank_Accounts");
        return;
      } else {
        results.put("dbStatus","Bank_Account Not Updated");
      }
    }
//standard
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Edit a Bank_Account ");
    req.getRequestDispatcher("WEB-INF/Budget_App/EditBank_Account.jsp").forward(req, resp);
  }
}


