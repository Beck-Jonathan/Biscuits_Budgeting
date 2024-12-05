package com.beck.beck_demos.garden.controller;


import com.beck.beck_demos.garden.data.GardenDAO;
import com.beck.beck_demos.garden.data.PlantDAO;
import com.beck.beck_demos.garden.model.Garden;
import com.beck.beck_demos.garden.model.Plant;
import com.beck.beck_demos.personal_project.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/******************
 Create the Servlet  For adding to The  Plant table
 Created By Jonathan Beck 4/13/2024
 ***************/

@WebServlet("/editplant")
public class EditPlantServlet extends HttpServlet{
  static List<Garden> allGardens = GardenDAO.getActiveGarden();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");


    String mode = req.getParameter("mode");
    int primaryKey = Integer.parseInt(req.getParameter("plantid"));
    Plant plant= new Plant();
    plant.setPlant_ID(primaryKey);
    try{
      plant=PlantDAO.getPlantByPrimaryKey(plant);
    } catch (SQLException e) {
      req.setAttribute("dbStatus",e.getMessage());
    }
    session.setAttribute("plant",plant);
    req.setAttribute("mode",mode);
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add Plant");
    allGardens = GardenDAO.getAllGarden();
    req.setAttribute("Gardens", allGardens);
    req.getRequestDispatcher("WEB-INF/garden/Plant_Edit.jsp").forward(req, resp);
  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");


    Map<String, String> results = new HashMap<>();
    String mode = req.getParameter("mode");
    req.setAttribute("mode",mode);
//to set the drop downs
    allGardens = GardenDAO.getAllGarden();
    req.setAttribute("Gardens", allGardens);
//to get the old Plant

    Plant _oldPlant= (Plant)session.getAttribute("plant");
//to get the new event's info
    String _Plant_Name = req.getParameter("inputplantPlant_Name");
    _Plant_Name=_Plant_Name.trim();
    String _Garden_ID = req.getParameter("inputplantGarden_ID");
    _Garden_ID=_Garden_ID.trim();
    String _Plant_depth = req.getParameter("inputplantPlant_depth");
    _Plant_depth=_Plant_depth.trim();
    String _Plant_Direction = req.getParameter("inputplantPlant_Direction");
    _Plant_Direction=_Plant_Direction.trim();
    String _Germination_Time = req.getParameter("inputplantGermination_Time");
    _Germination_Time=_Germination_Time.trim();

    results.put("Plant_Name",_Plant_Name);
    results.put("Garden_ID",_Garden_ID);
    results.put("Plant_depth",_Plant_depth);
    results.put("Plant_Direction",_Plant_Direction);
    results.put("Germination_Time",_Germination_Time);

    Plant _newPlant = new Plant();
    int errors =0;
    try {
      _newPlant.setPlant_Name(_Plant_Name);
    } catch(IllegalArgumentException e) {results.put("plantPlant_Nameerror", e.getMessage());
      errors++;
    }
    try {
      _newPlant.setGarden_ID(_Garden_ID);
    } catch(IllegalArgumentException e) {results.put("plantGarden_IDerror", e.getMessage());
      errors++;
    }
    try {
      _newPlant.setPlant_depth(_Plant_depth);
    } catch(IllegalArgumentException e) {results.put("plantPlant_deptherror", e.getMessage());
      errors++;
    }
    try {
      _newPlant.setPlant_Direction(_Plant_Direction);
    } catch(IllegalArgumentException e) {results.put("plantPlant_Directionerror", e.getMessage());
      errors++;
    }
    try {
      _newPlant.setGermination_Time(Integer.valueOf(_Germination_Time));
    } catch(IllegalArgumentException e) {results.put("plantGermination_Timeerror", e.getMessage());
      errors++;
    }

    _newPlant.setis_active(true);
//to update the database
    int result=0;
    if (errors==0){
      try{
        result=PlantDAO.update(_oldPlant,_newPlant);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Plant updated");
        resp.sendRedirect("all-Plants");
        return;
      } else {
        results.put("dbStatus","Plant Not Updated");
      }
    }
//standard
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Edit a Plant ");
    req.getRequestDispatcher("WEB-INF/garden/Plant_Edit.jsp").forward(req, resp);
  }
}


