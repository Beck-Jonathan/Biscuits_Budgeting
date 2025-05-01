package com.beck.beck_demos.budget_app.data_fakes;


import com.beck.beck_demos.budget_app.data.SuggestionDAO;
import com.beck.beck_demos.budget_app.models.Suggestion;
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
  private List<Suggestion> suggestions;

  public SuggestionDAO_Fake() {
    suggestions = new ArrayList<>();
    Suggestion suggestion0 = new Suggestion("odtAdunsYTIHEEnxmnCvombdPMaboDFhqwoP", "qptHFTxU", "MQZYuRWD", "XqiclcUl");
    Suggestion suggestion1 = new Suggestion("qECVNxhactLZMXwoMiTGCpXIulmYDmRBBcKd", "qptHFTxU", "EBrJDteM", "gEGICCRV");
    Suggestion suggestion2 = new Suggestion("FphsYLnSSkKpJDHLKqeeVBvRlXDVHUxEvTNs", "qptHFTxU", "RpiRHLDW", "DnFJinMw");
    Suggestion suggestion3 = new Suggestion("oEDOqNxSngjTVmTAgbBKPPjWGZuAZFSqjHAN", "qptHFTxU", "pplVCEsa", "TsCockMg");
    Suggestion suggestion4 = new Suggestion("OoNLpomsLpUGJESMiEiQVboZrBQhCFZXpabh", "eomGWnNd", "eTybWPtU", "xFHDCvsN");
    Suggestion suggestion5 = new Suggestion("OflOkeAZURqyXKwfSVayYJxcIpjprtSZCANc", "eomGWnNd", "iTCcaJCN", "AaAHIidI");
    Suggestion suggestion6 = new Suggestion("cXVKiDdBEwoboUHPjiiKhQFtTCkhlADlheWQ", "eomGWnNd", "EhLGrlRt", "mMpOPPJi");
    Suggestion suggestion7 = new Suggestion("sZdKIEyXhakQKMGPgZUGHUxXKBRxonewdFPc", "eomGWnNd", "ihKQrHox", "KwhNRMIl");
    Suggestion suggestion8 = new Suggestion("xPEOfNIYyDcjQXnqlAWejGcYbUiedprahSwE", "SOLEwDgY", "gBDexyEh", "bQhiUoPK");
    Suggestion suggestion9 = new Suggestion("MkJbJjTFyTylHkyDBGuCREocgcIdqcmSyHNn", "SOLEwDgY", "UOpuLBvF", "DoXgYjiq");
    Suggestion suggestion10 = new Suggestion("ITLLAumSaNLWRangbTLKJCdhuCbhhlALDTgj", "SOLEwDgY", "sDseUrlq", "ZEwwxRbE");
    suggestions.add(suggestion0);
    suggestions.add(suggestion1);
    suggestions.add(suggestion2);
    suggestions.add(suggestion3);
    suggestions.add(suggestion4);
    suggestions.add(suggestion5);
    suggestions.add(suggestion6);
    suggestions.add(suggestion7);
    suggestions.add(suggestion8);
    suggestions.add(suggestion9);
    suggestions.add(suggestion10);

    Collections.sort(suggestions);
  }

  @Override
  public int add(Suggestion _suggestion) throws SQLException {
    if (duplicateKey(_suggestion)){
      return 0;
    }
    if (exceptionKey(_suggestion)){
      throw new SQLException("error");
    }
    int size = suggestions.size();
    suggestions.add(_suggestion);
    int newsize = suggestions.size();
    return newsize-size;
  }

  @Override
  public int deleteSuggestion(String Suggestion_ID) throws SQLException {
    int size = suggestions.size();
    int location =-1;
    for (int i=0;i<suggestions.size();i++){
      if (suggestions.get(i).getSuggestion_ID().equals(Suggestion_ID)){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }
    suggestions.remove(location);
    int newsize = suggestions.size();
    return size-newsize;
  }

  @Override
  public List<Suggestion> getAllSuggestion(int offset, int limit, String search, String User_ID) throws SQLException {
    List<Suggestion> results = new ArrayList<>();
    for (Suggestion suggestion : suggestions){
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
  public Suggestion getSuggestionByPrimaryKey(Suggestion _suggestion) throws SQLException {
    Suggestion result = null;
    for (Suggestion suggestion : suggestions) {
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