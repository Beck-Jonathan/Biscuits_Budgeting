package com.beck.beck_demos.shared;

import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Helpers {
  public static long ageInYears(String birthDay) {
    DateTimeFormatter formatter = null;
    LocalDate birthDate = null;
    try {
      formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.US);
      birthDate = LocalDate.parse(birthDay, formatter);
    } catch(DateTimeParseException e) {
      try {
        formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US);
        birthDate = LocalDate.parse(birthDay, formatter);
      } catch(DateTimeParseException e2) {
        return 0;
      }
    }
    return Period.between(birthDate, LocalDate.now()).getYears();
  }

  public static User getUserFromSession(HttpSession session){
    User user = (User)session.getAttribute("activeUser");
    return user;

  }



  public static Boolean noErrors(HashMap results, List<String> potentialErrors){
    Boolean result = true;
    for (String errorMessage : potentialErrors){
      if(results.containsKey(errorMessage)){
        result=false;
        break;
      }

    }
    return result;
  }
  public static String genPW(){
    String pw = "KW";
    Random r = new Random();

    for (int i=0;i<12;i++){
      pw += (char)(r.nextInt(26) + 'a');
    }
    for (int i=0;i<5;i++){
      pw+=r.nextInt(9);
    }
    pw+="!";



    return pw;
  }
}