package com.beck.beck_demos.budget_app.data_fakes;


import com.beck.beck_demos.budget_app.data.SuggestionDAO;
import com.beck.beck_demos.budget_app.models.Suggestion;
import com.beck.beck_demos.budget_app.models.Suggestion_VM;
import com.beck.beck_demos.budget_app.models.User;
import com.beck.beck_demos.budget_app.iData.iSuggestionDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class SuggestionDAO_Fake implements iSuggestionDAO {
  private List<Suggestion_VM> suggestionVMs;

  public SuggestionDAO_Fake() {
    suggestionVMs = new ArrayList<>();
    User user1 = new User();
    user1.setUser_ID(32);
    User user2 = new User();
    user2.setUser_ID(33);
    User user3 = new User();
    user3.setUser_ID(34);


    Suggestion suggestion0 = new Suggestion("odtAdunsYTIHEEnxmnCvombdPMaboDFhqwoP", "aatAdunsYTIHEEnxmnCvombdPMaboDFhqwoP", "MQZYuRWD", "XqiclcUl");
    Suggestion suggestion1 = new Suggestion("qECVNxhactLZMXwoMiTGCpXIulmYDmRBBcKd", "aatAdunsYTIHEEnxmnCvombdPMaboDFhqwoP", "EBrJDteM", "gEGICCRV");
    Suggestion suggestion2 = new Suggestion("FphsYLnSSkKpJDHLKqeeVBvRlXDVHUxEvTNs", "aatAdunsYTIHEEnxmnCvombdPMaboDFhqwoP", "RpiRHLDW", "DnFJinMw");
    Suggestion suggestion3 = new Suggestion("oEDOqNxSngjTVmTAgbBKPPjWGZuAZFSqjHAN", "aatAdunsYTIHEEnxmnCvombdPMaboDFhqwoP", "pplVCEsa", "TsCockMg");
    Suggestion suggestion4 = new Suggestion("OoNLpomsLpUGJESMiEiQVboZrBQhCFZXpabh", "bbtAdunsYTIHEEnxmnCvombdPMaboDFhqwoP", "eTybWPtU", "xFHDCvsN");
    Suggestion suggestion5 = new Suggestion("OflOkeAZURqyXKwfSVayYJxcIpjprtSZCANc", "bbtAdunsYTIHEEnxmnCvombdPMaboDFhqwoP", "iTCcaJCN", "AaAHIidI");
    Suggestion suggestion6 = new Suggestion("cXVKiDdBEwoboUHPjiiKhQFtTCkhlADlheWQ", "bbtAdunsYTIHEEnxmnCvombdPMaboDFhqwoP", "EhLGrlRt", "mMpOPPJi");
    Suggestion suggestion7 = new Suggestion("sZdKIEyXhakQKMGPgZUGHUxXKBRxonewdFPc", "bbtAdunsYTIHEEnxmnCvombdPMaboDFhqwoP", "ihKQrHox", "KwhNRMIl");
    Suggestion suggestion8 = new Suggestion("xPEOfNIYyDcjQXnqlAWejGcYbUiedprahSwE", "cctAdunsYTIHEEnxmnCvombdPMaboDFhqwoP", "gBDexyEh", "bQhiUoPK");
    Suggestion suggestion9 = new Suggestion("MkJbJjTFyTylHkyDBGuCREocgcIdqcmSyHNn", "cctAdunsYTIHEEnxmnCvombdPMaboDFhqwoP", "UOpuLBvF", "DoXgYjiq");
    Suggestion suggestion10 = new Suggestion("ITLLAumSaNLWRangbTLKJCdhuCbhhlALDTgj", "cctAdunsYTIHEEnxmnCvombdPMaboDFhqwoP", "sDseUrlq", "ZEwwxRbE");
    Suggestion_VM suggestion_VM0= new Suggestion_VM(suggestion0,user1);
    Suggestion_VM suggestion_VM1= new Suggestion_VM(suggestion1,user1);
    Suggestion_VM suggestion_VM2= new Suggestion_VM(suggestion2,user1);
    Suggestion_VM suggestion_VM3= new Suggestion_VM(suggestion3,user1);
    Suggestion_VM suggestion_VM4= new Suggestion_VM(suggestion4,user2);
    Suggestion_VM suggestion_VM5= new Suggestion_VM(suggestion5,user2);
    Suggestion_VM suggestion_VM6= new Suggestion_VM(suggestion6,user2);
    Suggestion_VM suggestion_VM7= new Suggestion_VM(suggestion7,user2);
    Suggestion_VM suggestion_VM8= new Suggestion_VM(suggestion8,user3);
    Suggestion_VM suggestion_VM9= new Suggestion_VM(suggestion9,user3);
    Suggestion_VM suggestion_VM10= new Suggestion_VM(suggestion10,user3);
    suggestionVMs.add(suggestion_VM0);
    suggestionVMs.add(suggestion_VM1);
    suggestionVMs.add(suggestion_VM2);
    suggestionVMs.add(suggestion_VM3);
    suggestionVMs.add(suggestion_VM4);
    suggestionVMs.add(suggestion_VM5);
    suggestionVMs.add(suggestion_VM6);
    suggestionVMs.add(suggestion_VM7);
    suggestionVMs.add(suggestion_VM8);
    suggestionVMs.add(suggestion_VM9);
    suggestionVMs.add(suggestion_VM10);

    Collections.sort(suggestionVMs);
  }

  @Override
  public int add(Suggestion _suggestion) throws SQLException {
    Suggestion_VM suggestion_VM = new Suggestion_VM(_suggestion);
    if (duplicateKey(_suggestion)){
      return 0;
    }
    if (exceptionKey(_suggestion)){
      throw new SQLException("error");
    }
    int size = suggestionVMs.size();
    suggestionVMs.add(suggestion_VM);
    int newsize = suggestionVMs.size();
    return newsize-size;
  }

  @Override
  public int deleteSuggestion(String Suggestion_ID) throws SQLException {
    int size = suggestionVMs.size();
    int location =-1;
    for (int i=0;i<suggestionVMs.size();i++){
      if (suggestionVMs.get(i).getSuggestion_ID().equals(Suggestion_ID)){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }
    suggestionVMs.remove(location);
    int newsize = suggestionVMs.size();
    return size-newsize;
  }

  @Override
  public List<Suggestion_VM> getAllSuggestion(int offset, int limit, String search, String User_ID) throws SQLException {
    List<Suggestion_VM> results = new ArrayList<>();
    for (Suggestion_VM suggestion : suggestionVMs){
      if ((suggestion.getUser_ID()!=null||suggestion.getUser_ID().equals(User_ID))
      ){
        if (search.isEmpty() || suggestion.getSuggestion_ID().contains(search)|| suggestion.getUser_ID().contains(search)|| suggestion.getApplication_Name().contains(search)|| suggestion.getcontent().contains(search)){
          results.add(suggestion);
        }
      }
    }
    return results;
  }


  @Override
  public Suggestion_VM getSuggestionByPrimaryKey(Suggestion _suggestion) throws SQLException {
    Suggestion_VM result = null;
    for (Suggestion_VM suggestion : suggestionVMs) {
      if (suggestion.getSuggestion_ID().equals(_suggestion.getSuggestion_ID())){
        result = suggestion;
        break;
      }
    }
    if (result == null){
      throw new SQLException("Suggestion not found");
    }
    return result;
  }
  private boolean duplicateKey(Suggestion _suggestion){
    return _suggestion.getcontent().equals("DUPLICATE");
  }
  private boolean exceptionKey(Suggestion _suggestion){
    return _suggestion.getcontent().equals("EXCEPTION");
  }
}