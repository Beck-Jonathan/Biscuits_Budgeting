package com.beck.beck_demos.budget_app.data_fakes;

import com.beck.beck_demos.budget_app.iData.iMortgageDAO;
import com.beck.beck_demos.budget_app.models.Mortgage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MortgageDAO_Fake implements iMortgageDAO {
  private  List<Mortgage> mortgageVMs;
  public MortgageDAO_Fake(){
  mortgageVMs = new ArrayList<>();
  Mortgage mortgage0 = new Mortgage(12, 44, 29.53, 48.89, 68.61, 60.58, 59.59, 46);
  Mortgage mortgage1 = new Mortgage(12, 44, 15.49, 39.84, 45.64, 35.46, 20.02, 19);
  Mortgage mortgage2 = new Mortgage(21, 44, 10.09, 22.12, 10.95, 53.46, 52.93, 12);
  Mortgage mortgage3 = new Mortgage(17, 44, 38.81, 66.08, 18.17, 14.58, 11.46, 26);
  Mortgage mortgage4 = new Mortgage(30, 44, 48.55, 29.49, 28.18, 64.66, 54.5, 64);
  Mortgage mortgage5 = new Mortgage(39, 47, 54.24, 29.64, 22.65, 62.68, 68.4, 67);
  Mortgage mortgage6 = new Mortgage(53, 47, 67.24, 68.91, 49.71, 28.62, 60.96, 28);
  Mortgage mortgage7 = new Mortgage(46, 47, 60.91, 49.56, 24.79, 41.98, 65.32, 60);
  Mortgage mortgage8 = new Mortgage(52, 47, 60.44, 55.99, 58.12, 29.63, 51.67, 36);
  Mortgage mortgage9 = new Mortgage(20, 47, 27.46, 40.8, 35.52, 24.49, 43.26, 40);
  Mortgage mortgage10 = new Mortgage(51, 37, 40.8, 60.24, 14.46, 19.5, 55.11, 68);
  Mortgage mortgage11 = new Mortgage(19, 37, 34.87, 52.83, 35.89, 12.69, 12.01, 38);
  Mortgage mortgage12 = new Mortgage(48, 37, 49.86, 22.51, 69.38, 57.26, 56.78, 63);
  Mortgage mortgage13 = new Mortgage(43, 37, 34.53, 43.05, 26.72, 41.01, 43.34, 49);
  Mortgage mortgage14 = new Mortgage(38, 37, 24.19, 14.13, 27.7, 57.78, 18.65, 19);

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
  public List<Mortgage> getMortgagebyUser(Integer User_ID, int limit, int offset) {
    List<Mortgage> results = new ArrayList<>();
    for (Mortgage mortgage : mortgageVMs){
      if (mortgage.getUser_ID().equals(User_ID)){
        results.add(mortgage);
      }
    }
    return results;
  }

}
