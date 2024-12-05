package com.beck.beck_demos.crrg.controllers;


import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Map;

@WebServlet("/crrg_upload")
public class upload_servlet extends HttpServlet {
  static Cloudinary cloudinary;
  static int x;
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    x=0;
    HttpSession session = req.getSession();
    Dotenv dotenv = Dotenv.load();
    String test = dotenv.get("CLOUDINARY_URL");


    cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
    File dir = new File("C:\\derby_demo\\");



    try {
      File[] files = dir.listFiles();
      assert files != null;
      showFiles(files);
    }
    catch (Exception ex){
int x =5;
    }
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Privacy Policy");
    req.getRequestDispatcher("WEB-INF/learnx/privacy-policy.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doPost(req, resp);
  }

  public static void showFiles(File[] files) throws Exception {
    for (File file : files) {
      if (file.isDirectory()) {

        showFiles(file.listFiles()); // Calls same method again.
      } else {
        String folder = file.getParent();
        int lastindex=folder.lastIndexOf("\\");
        folder=folder.substring(lastindex+1);
        System.out.println(folder);
        uploadFile(file,folder);
      }
    }
  }

  public static void uploadFile(File file, String directory) throws Exception {
    Map params1 = ObjectUtils.asMap(
        "use_filename", true,
        "unique_filename", false,
        "overwrite", true,
        "asset_folder",directory
    );
    cloudinary.uploader().upload(file, params1);
    System.out.println(x);
    x++;


  }
}

