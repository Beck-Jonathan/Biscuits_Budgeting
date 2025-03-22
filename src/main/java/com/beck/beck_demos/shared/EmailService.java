package com.beck.beck_demos.shared;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.deadline.model.AwsCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.simpleemail.model.*;


import com.amazonaws.services.simpleemail.*;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.http.HttpServletRequest;



public class EmailService
{
  private static String FROM = "";
  private static AmazonSimpleEmailService CreateEmailClient(){
    Dotenv dotenv = Dotenv.load();

    AmazonSimpleEmailService client = null;
    try {

      client =
          AmazonSimpleEmailServiceClientBuilder.standard()

              .withRegion(Regions.US_EAST_1)
              .withCredentials(new EnvironmentVariableCredentialsProvider())
              .build();
    }
    catch(Exception e){
      int x =1;
    }



    return client;


  }
  public static boolean sendemail(String toAddr, String Subject, String HTMLBODY, String TEXTBODY) {
    try {
      Dotenv dotenv = Dotenv.load();
      AmazonSimpleEmailService emailClient = CreateEmailClient();
      SendEmailRequest request = new SendEmailRequest()
          .withDestination(
              new Destination().withToAddresses(toAddr))
          .withMessage(new Message()
              .withBody(new Body()
                  .withHtml(new Content()
                      .withCharset("UTF-8").withData(HTMLBODY))
                  .withText(new Content()
                      .withCharset("UTF-8").withData(TEXTBODY)))
              .withSubject(new Content()
                  .withCharset("UTF-8").withData(Subject)))
          .withSource(FROM);
          // Comment or remove the next line if you are not using a
          // configuration set
          //.withConfigurationSetName(CONFIGSET);
      emailClient.sendEmail(request);
      //System.out.println("Email sent!");

    } catch (Exception ex) {
      //System.out.println(ex.getMessage());
      return false;
    }
    return true;

  }
  public static boolean send2faCode(String Code, String email){
    String subject = "LearnX New User Confirmation";
    String code = Code;
    String Email=email;
    String message = "<h2>Welcome to LearnX</h2>";
    message += "<p>Please enter code <b>" + code + "</b> to activate your account.</p>";
    return sendemail(Email, subject, message,"");


  }
  public static boolean send2faCode_Roller(String Code, String email){
    String subject = "Roller Derby New Skater Confirmation";
    String code = Code;
    String Email=email;
    String message = "<h2>Welcome to Roller Derby!</h2>";
    message += "<p>Please enter code <b>" + code + "</b> to activate your account.</p>";
    return sendemail(Email, subject, message,"");


  }
  public static boolean sendPasswordResetEmail(String email, String token, HttpServletRequest req) {
    String subject = "LearnX New User Confirmation";
    String message = "<h2>Welcome to LearnX</h2>";
    message += "<p>Please use this link to securely reset your password. This link will expire in 30 minutes</p>";
    String appURL="";
    if (req.isSecure()){
      appURL=req.getServletContext().getInitParameter("appURLCloud");
    }
    else {
      appURL=req.getServletContext().getInitParameter("appURLLocal");
    }
    String fullURL = String.format("%s/new-password?token=%s", appURL, token);
    message += String.format("<p><a href=\"%s\" target=\"_blank\">%s</a></p>", fullURL, fullURL);
    message += "<p>If you did not request to reset your password, you can ignore this message. Your password will not be changed.</p>";
    return sendemail(email, subject, message,"");

  }

  public static boolean sendReset(String password, String email, String username){
    String subject = "Roller Password Reset";
    String message = "<h2>Welcome Back to Roller Derby</h2>";
    message += "<p>Use this link to rest your password if you are on localhost/p>" +
        "<p><a href=http://localhost:8080/beck_demos_war_exploded/resetpw?code="+password+"&user="+username+"&email="+email+" > reset password </a></p>";

    message += "<p>Use this link to rest your password if you are on the azure deployment/p>" ;
    message+= "<br><br/> <p><a href=https://javaiii-kirkwood.azurewebsites.net/resetpw?code="+password+"&user="+username+"&email="+email+" > reset password </a></p>";



    return sendemail(email, subject, message,"");

  }

  public static boolean sendNewPassword(String password, String email, String username){
    String subject = "Roller Password has been Reset";
    String message = "<h2>Welcome Back to Roller Derby</h2>";
    message += "<p>your new password is <h2> "+password+"</h2></p><br/>" ;
    message+= "<p> your user name is"+username+"</p>";

    return sendemail(email, subject, message,"");
  }


}
