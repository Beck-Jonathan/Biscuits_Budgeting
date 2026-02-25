package com.beck.beck_demos.budget_app.data_fakes;

import com.beck.beck_demos.budget_app.iData.iMortgageDAO;
import com.beck.beck_demos.budget_app.models.Mortgage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MortgageDAO_Fake implements iMortgageDAO {
  private  List<Mortgage> mortgageVMs;
  public MortgageDAO_Fake(){
  mortgageVMs = new ArrayList<>();
    Mortgage mortgage0 = new Mortgage("xfJsxVBDDqBaKBFAgDhQACTFREWwjVxILdpW", "3077113b-5f4d-41df-ae5a-514b82c2b4fb", "WfhcykTs", 55.69, 50.84, 35.65, 59.9, 46.75, 48);
    Mortgage mortgage1 = new Mortgage("sRLMssDNRsUaetFJLmitlSLsspKHooQnZyQd", "3077113b-5f4d-41df-ae5a-514b82c2b4fb", "KjQUgkgF", 53.08, 53.46, 53.86, 66.52, 31.1, 34);
    Mortgage mortgage2 = new Mortgage("YMlpdRBiLFTUCCqMHIEnkXrsUfmAiXHlQDbp", "3077113b-5f4d-41df-ae5a-514b82c2b4fb", "HVEANqAC", 33.38, 60.08, 59.09, 56.51, 37.14, 13);
    Mortgage mortgage3 = new Mortgage("UvLmgLckyPVymAnNJAZqxowNOUJmdNgXXiER", "3077113b-5f4d-41df-ae5a-514b82c2b4fb", "KnZeqWZP", 67.78, 20.58, 36.79, 49.45, 63.54, 65);
    Mortgage mortgage4 = new Mortgage("kbubURjuhpeWSxXpoELcJbmYJFTwqldFwruf", "3077113b-5f4d-41df-ae5a-514b82c2b4fb", "xDQgaaHi", 25.06, 60.88, 22.69, 67.21, 19.99, 58);
    Mortgage mortgage5 = new Mortgage("mosUwJIedjNClcLOctXuIDnxpvNEXhqgFllF", "736c3e41-fdae-45b4-af5a-14f77a4e78e6", "RDREJBKE", 33.22, 39.28, 32.76, 68.34, 32.4, 24);
    Mortgage mortgage6 = new Mortgage("WfoTtuYYcTOVDtlmAkkhYScjnctRkcJYTgQc", "736c3e41-fdae-45b4-af5a-14f77a4e78e6", "fwyrVBya", 24.72, 34.28, 33.4, 34.49, 10.04, 43);
    Mortgage mortgage7 = new Mortgage("wscQcfMeEXFbkpDRdIDMRKZwfAWAAGOEXTUc", "736c3e41-fdae-45b4-af5a-14f77a4e78e6", "qRuctHSi", 50.13, 12.81, 15.89, 63.61, 50.64, 17);
    Mortgage mortgage8 = new Mortgage("FbeaxsPnvMtaijtcTdvTUtGYHFyIfYOsYbUw", "736c3e41-fdae-45b4-af5a-14f77a4e78e6", "PTpWOIgl", 42.72, 26.42, 52.16, 28.08, 15.88, 60);
    Mortgage mortgage9 = new Mortgage("HIwBIGUvTmQGvFkYfbpQpbQWQwKDGvOGejkW", "736c3e41-fdae-45b4-af5a-14f77a4e78e6", "JTUlCgVL", 42.6, 35.97, 13.32, 42.59, 60.8, 13);
    Mortgage mortgage10 = new Mortgage("ZPgoJgyGhuKgvndBEXkYPYTxiEGyJtVrMSUd", "736c3e41-fdae-45b4-af5a-14f77a4e78e6", "fVfonVCc", 57.04, 55.67, 36.26, 11.76, 54.86, 33);
    Mortgage mortgage11 = new Mortgage("maRJkoqYDtcChIiSClNjskxWoDtkoAirOMQn", "9bbc5ecc-d663-48f8-9536-9c1586add984", "NtAwqmTG", 43.48, 55.74, 14.88, 44.89, 27.93, 63);
    Mortgage mortgage12 = new Mortgage("SEcSptMFkiSiusKHQORpJGPoavCBWGelKBQf", "9bbc5ecc-d663-48f8-9536-9c1586add984", "xpbUGTlg", 69.59, 18.25, 17.31, 38.84, 39.01, 51);
    Mortgage mortgage13 = new Mortgage("BMMPGAGAclqSvlIoPCsPAdnMiwOSOtGPTtGy", "9bbc5ecc-d663-48f8-9536-9c1586add984", "BGsjppdi", 65.09, 43.36, 41.27, 10.78, 11.32, 59);
    Mortgage mortgage14 = new Mortgage("kPvLgYNTSjDqIHtwebyBioOsQLSSigehrVdM", "9bbc5ecc-d663-48f8-9536-9c1586add984", "tewQgMLQ", 68.12, 68.39, 64.83, 62.81, 50.26, 37);

mortgageVMs.add(mortgage0);
mortgageVMs.add(mortgage1);
mortgageVMs.add(mortgage2);
mortgageVMs.add(mortgage3);
mortgageVMs.add(mortgage4);
mortgageVMs.add(mortgage5);
mortgageVMs.add(mortgage6);
mortgageVMs.add(mortgage7);
mortgageVMs.add(mortgage8);
mortgageVMs.add(mortgage9);
mortgageVMs.add(mortgage10);
mortgageVMs.add(mortgage11);
mortgageVMs.add(mortgage12);
mortgageVMs.add(mortgage13);
mortgageVMs.add(mortgage14);
Collections.sort(mortgageVMs);
}
  @Override
  public List<Mortgage> getMortgagebyUser(String User_ID, int limit, int offset) {
    List<Mortgage> results = new ArrayList<>();
    for (Mortgage mortgage : mortgageVMs){
      if (mortgage.getUser_ID().equals(User_ID)){
        results.add(mortgage);
      }
    }
    return results;
  }

  @Override
  public int add(Mortgage _mortgage) throws SQLException {
    if (duplicateKey(_mortgage)){
      return 0;
    }
    if (exceptionKey(_mortgage)){
      throw new SQLException("error");
    }
    int size = mortgageVMs.size();

    mortgageVMs.add(_mortgage);
    int newsize = mortgageVMs.size();
    return newsize-size;
  }

  @Override
  public Integer deleteMortgage(String mortgageID) throws SQLException {
    int size = mortgageVMs.size();
    int location =-1;
    for (int i=0;i<mortgageVMs.size();i++){
      if (mortgageVMs.get(i).getMortgage_ID().equals(mortgageID)){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException("Unable To Find Mortgage.");
    }
    mortgageVMs.remove(location);
    int newsize = mortgageVMs.size();
    return size-newsize;
  }

  @Override
  public int update(Mortgage oldMortgage, Mortgage newMortgage) throws SQLException {
    int location =-1;
    if (duplicateKey(oldMortgage)){
      return 0;
    }
    if (exceptionKey(oldMortgage)){
      throw new SQLException("error");
    }
    for (int i=0;i<mortgageVMs.size();i++){
      if (mortgageVMs.get(i).getMortgage_ID().equals(oldMortgage.getMortgage_ID())){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }

    mortgageVMs.set(location,newMortgage);
    return 1;
  }

  @Override
  public Mortgage getMortgageByPrimaryKey(Mortgage _mortgage) throws SQLException {
    Mortgage result = null;
    for (Mortgage mortgage : mortgageVMs) {
      if (mortgage.getMortgage_ID().equals(_mortgage.getMortgage_ID())){
        result = mortgage;
        break;
      }
    }
    if (result == null){
      throw new SQLException("Mortgage not found");
    }
    return result;
  }

  private boolean duplicateKey(Mortgage _mortgage){
    return _mortgage.getMonthly_Payment()==666 || _mortgage.getNickname().toLowerCase().equals("duplicate");
  }
  private boolean exceptionKey(Mortgage _mortgage){
    return _mortgage.getMonthly_Payment()==667|| _mortgage.getNickname().toLowerCase().equals("exception");
  }


  }
