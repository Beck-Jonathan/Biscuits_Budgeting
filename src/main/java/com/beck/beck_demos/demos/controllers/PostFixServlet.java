package com.beck.beck_demos.demos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.*;

import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;


@WebServlet("/postFix")
public class PostFixServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "postFix");
    req.getRequestDispatcher("WEB-INF/demos/postfix.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HashMap results = new HashMap<String,String>();
    String test = req.getParameter("postfix");
    List<String> parsed = parse((test));
    String response="Your post fix result </br>";

try {
  while (parsed.size() > 1) {
    response = response + parsed;
    response = response + "<br/>";
    parsed = calculateandshrink(parsed);
    response = response + "Becomes<br/>" + parsed;
    response = response + "<br/>";
    response = response + "<br/>";
  }
}
catch (Exception ex){
  response = "Error, please make sure values and operands are separated by spaces. <br/>" +
      "the equation starts with two numbers, and the equation has one more value than operations. <br/>" +
      "Currently only + - * and / are supported";
}
      results.put("response",response);
      req.setAttribute("results",results);


      req.setAttribute("pageTitle", "PostFix");
      req.getRequestDispatcher("WEB-INF/demos/postfix.jsp").forward(req, resp);

    }








    public static ArrayList<String> parse(String x){
      List<String> grab = List.of(x.split(" "));
      ArrayList<String> values = new ArrayList();
      for (int i =0;i< grab.size();i++){
        values.add(grab.get(i));
      }

      return values;

    }

    public static List<String> calculateandshrink (List<String> values){

      for (int i=0; i<values.size();i++) {
        if (values.size()%2==0 || values.size()<1){
          throw new IllegalArgumentException();
        }
        if (values.get(i).equals("/")
            || values.get(i).equals("+")
            || values.get(i).equals("-")
            || values.get(i).equals("*")) {

          Double result=math(Double.valueOf(values.get(i - 2)), Double.valueOf(values.get(i - 1)), values.get(i));
          values.remove(i);
          values.remove(i-1);
          values.set(i-2,result.toString());

          return values;
        }
      }
      return values;
    }

    public static double  math(Double x, Double y, String z){
      double result = 0;
      switch (z){
        case "/" : result = x/y; break;
        case "+" : result = x+y; break;
        case "-" : result=x-y; break;
        case "*" :  result = x*y;break;


      }
      int value  = (int) (result * 1000);
      result = (double) value /1000;
      return result;
    }





  }


