package com.beck.beck_demos.crrg.controllers;




import com.beck.beck_demos.crrg.data.Picture_DAO;
import com.beck.beck_demos.crrg.data.Contributor_DAO;
import com.beck.beck_demos.crrg.data.Album_DAO;
import com.beck.beck_demos.crrg.models.*;
import com.beck.beck_demos.crrg.data_interfaces.iPicture_DAO;
import com.beck.beck_demos.crrg.data_interfaces.iContributor_DAO;
import com.beck.beck_demos.crrg.data_interfaces.iAlbum_DAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.fileupload2.core.DiskFileItem;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.jakarta.JakartaServletDiskFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;

import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

/******************
 Create the Servlet  For adding to The  Picture table
 Created By Jonathan Beck 10/9/2024
 ***************/

@WebServlet("/addPicture")
@MultipartConfig(

    fileSizeThreshold = 1024 * 1024, // 1 MB
    maxFileSize = 1024 * 1024 * 10,      // 10 MB
    maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class Add_Picture extends HttpServlet{
  private static final String UPLOAD_DIR = "images\\crrg\\";
  private static final String bucketName = "derbypictures";
  static List<Album_VM> allAlbums ;
  static List<Contributor_VM> allContributors ;
  private iPicture_DAO pictureDAO;
  private iContributor_DAO contributorDAO;
  private iAlbum_DAO albumDAO;

  @Override
  public void init() throws ServletException{
    pictureDAO = new Picture_DAO();
    contributorDAO = new Contributor_DAO();
    albumDAO = new Album_DAO();
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

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add Picture");

    try {
      allAlbums = albumDAO.getAllAlbum(20,0);
      allContributors = contributorDAO.getAllContributor(20,0);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    req.setAttribute("Albums", allAlbums);
    req.setAttribute("Contributors", allContributors);
    req.getRequestDispatcher("WEB-INF/crrg/AddPicture.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //To restrict this page based on privilege level

    List<String> ROLES_NEEDED = new ArrayList<>();
    ROLES_NEEDED.add("Jonathan");
//add roles here
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");
    if (user==null||!user.isInRole(ROLES_NEEDED)){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }
    try {
      allAlbums = albumDAO.getAllAlbum(20,0);
      allContributors = contributorDAO.getAllContributor(20,0);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    req.setAttribute("Albums", allAlbums);
    req.setAttribute("Contributors", allContributors);

    List<File> files = new ArrayList<>();
    JakartaServletDiskFileUpload upload = new JakartaServletDiskFileUpload();
    ServletContext servletContext = this.getServletConfig().getServletContext();
    List<DiskFileItem> items = upload.parseRequest(req);

    String applicationPath = req.getServletContext().getRealPath("");
    String albumID = "";
    String description ="";
    String contributorID = "";
    String isApproved = "";
    String isActive = "";
    try {
      for (DiskFileItem x : items) {
        //System.out.println(x.getName());
        if (x.getName() != null) {
          InputStream y = x.getInputStream();
          File z = new File(applicationPath + File.separator +UPLOAD_DIR + x.getName());
          boolean result = z.createNewFile();
            if (!result){
              z.delete();
              z.createNewFile();
            }
            FileOutputStream outstream = new FileOutputStream(z);
            IOUtils.copy(y, outstream);
          files.add(z);
        }
        else {
          String variable = x.getFieldName();
          String value = x.getString();
          switch (variable){
            case "inputpictureAlbum_ID":
              albumID = value;
              break;
            case "inputpictureContributor_ID":
              contributorID = value;
              break;
            case "inputpicturedescription":
              description = value;
              break;
            case "inputpictureis_Approved":
              isApproved = value;
              break;
            case "inputpictureis_Active":
              isActive = value;
              break;
          }
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    boolean is_active= false;
    boolean is_approved= false;
    try {
      is_active = Boolean.parseBoolean(isActive);
      is_approved = Boolean.parseBoolean(isApproved);
    } catch (Exception e) {

    }
    Map<String, String> results = new HashMap<>();
    results.put("Album_ID",albumID);
    results.put("Contributor_ID",contributorID);
    results.put("description",description);
    results.put("Is_Active",isActive);
    results.put("is_Approved",isApproved);


    String url="";

    String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR+albumID;
    File fileSaveDir = new File(uploadFilePath);
    if (!fileSaveDir.exists()) {
      fileSaveDir.mkdirs();
    }
    int errors=0;
     List<Picture> pictures = new ArrayList<>();
     boolean falseOverride = false;
     if (files.size()>20){
       falseOverride=true;
     }
      try (S3Client client = S3Client.builder().build()){
        for (int i=0;i<files.size();i++){
          String filename = files.get(i).getName();
          System.out.println(filename);
          File file=files.get(i);
          Picture picture = new Picture();
          try {
            picture.setAlbum_ID(Integer.valueOf(albumID));
          } catch(IllegalArgumentException e) {results.put("pictureAlbum_IDerror", e.getMessage());
            errors++;
          }
          try {
            picture.setContributor_ID(Integer.valueOf(contributorID));
          } catch(IllegalArgumentException e) {results.put("pictureContributor_IDerror", e.getMessage());
            errors++;
          }

          try {
            picture.setdescription(description);
          } catch(IllegalArgumentException e) {results.put("picturedescriptionerror", e.getMessage());
            errors++;
          }
          if (falseOverride){
            picture.setIs_Active(false);
          }
          else {
            picture.setIs_Active(is_active);
          }
          picture.setis_Approved(is_approved);
          pictures.add(picture);

       // File file = new File(uploadFilePath + File.separator + fileName);

       // for (Part part : req.getParts()) {
         // part.write(uploadFilePath + File.separator + fileName);


          PutObjectRequest request = PutObjectRequest.builder().bucket(bucketName).key(filename).build();
          PutObjectResponse result = client.putObject(request, RequestBody.fromFile(file));

          GetUrlRequest req2 = GetUrlRequest.builder().bucket(bucketName).key(filename).build();
          url = client.utilities().getUrl(req2).toExternalForm();
          try {
            picture.setWeb_Address(url);
          } catch(IllegalArgumentException e) {results.put("pictureWeb_Addresserror", e.getMessage());
            errors++;
          }
          file.delete();
        }
      } catch (Exception ex) {
        results.put("dbStatus", ex.getMessage());
        req.setAttribute("results", results);
        req.setAttribute("pageTitle", "Add Picture");
        req.getRequestDispatcher("WEB-INF/crrg/AddPicture.jsp").forward(req, resp);
        return;
      }

    int result=0;
    if (errors==0) {
      for (Picture picture : pictures) {
        try {
          result = pictureDAO.add(picture);
        } catch (Exception ex) {
          results.put("dbStatus", "Database Error");
        }
        if (result > 0) {
          results.put("dbStatus", "Picture Added");
          //resp.sendRedirect("all-Pictures");
          //return;
        } else {
          results.put("dbStatus", "Picture Not Added");

        }
      }
    }
    //}
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Add Picture");
    req.getRequestDispatcher("WEB-INF/crrg/AddPicture.jsp").forward(req, resp);

  }
}

