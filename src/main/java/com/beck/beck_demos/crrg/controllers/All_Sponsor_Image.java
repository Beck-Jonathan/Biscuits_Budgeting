package com.beck.beck_demos.crrg.controllers; /******************
 Create the Servlet  For Viewing all of the  Sponsor_Tag table
 Created By Jonathan Beck 11/14/2024
 ***************/

import com.beck.beck_demos.crrg.data.Sponsor_Tag_DAO;
import com.beck.beck_demos.crrg.models.Sponsor_Tag;
import com.beck.beck_demos.crrg.models.Sponsor_Tag_VM;
import com.beck.beck_demos.crrg.models.User;
import com.beck.beck_demos.crrg.data_interfaces.ISponsor_Tag_DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
@WebServlet("/all-Sponsor_Tags")
public class All_Sponsor_Image extends HttpServlet {
  private ISponsor_Tag_DAO sponsor_tagDAO;
  @Override
  public void init() throws ServletException{
    sponsor_tagDAO = new Sponsor_Tag_DAO();
  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
    ROLES_NEEDED.add("Jonathan");
//add roles here
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");
    if (user==null||!user.isInRole(ROLES_NEEDED)){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }
    String sponsor_ID = req.getParameter("SponsorID");

    session.setAttribute("currentPage",req.getRequestURL());
    List<Sponsor_Tag_VM> sponsor_tags = null;

    sponsor_tags =sponsor_tagDAO.getAllSponsor_Tag(sponsor_ID);

    req.setAttribute("Sponsor_Tags", sponsor_tags);
    req.setAttribute("pageTitle", "All Sponsor_Tags");
    req.getRequestDispatcher("WEB-INF/crrg/all-Sponsor_Images.jsp").forward(req,resp);

  }
}
