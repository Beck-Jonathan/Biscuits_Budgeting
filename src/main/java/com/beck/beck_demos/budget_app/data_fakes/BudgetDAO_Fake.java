package com.beck.beck_demos.budget_app.data_fakes;

import com.beck.beck_demos.budget_app.iData.iBudgetDAO;
import com.beck.beck_demos.budget_app.models.Budget;
import com.beck.beck_demos.budget_app.models.Budget_VM;
import java.time.LocalDate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BudgetDAO_Fake  implements  iBudgetDAO{
  private List<Budget_VM> budgetVMs;
  private  List<String> currency_codes;
  public BudgetDAO_Fake(){
    budgetVMs = new ArrayList<>();
    Budget budget0 = new Budget("DLpfWxZUBCbqtdngpApNUBMxKxgaVkEondmy", "WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG", "UabcRNVs", "idNQpnFK",  LocalDate.now(), 38.44, "fDC", false, LocalDate.now(), LocalDate.now());
    Budget budget1 = new Budget("mqkLNBhYRBoRlOdJFOjogHWXcRRmQyNbIkfv", "WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG", "JabcjBax", "mAWirxKd", LocalDate.now(), 17.47, "fDC", false, LocalDate.now(), LocalDate.now());
    Budget budget2 = new Budget("UpHSlyPcbdxGIOyhZjQcAomXFmNxlwWeflYD", "WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG", "OabcFXCL", "KOKuGpik", LocalDate.now(), 20.68, "quS", false, LocalDate.now(), LocalDate.now());
    Budget budget3 = new Budget("VsbynqpfgtPQwligPgtWKQrSIfFHooEjcNkT", "WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG", "uUUJBpfj", "YhBTglgL", LocalDate.now(), 46.4, "gZM", true, LocalDate.now(), LocalDate.now());
    Budget budget4 = new Budget("EhcJnyOJMLbWuMeKTiALMMLxWoiJwqNIVMBA", "plTfoEWwRnQeohUbkJlqYJSqaQSnyoaSaCUI", "lklYuNjM", "dMWEueMZ", LocalDate.now(), 69.02, "fYF", true, LocalDate.now(), LocalDate.now());
    Budget budget5 = new Budget("jROfHpYxYAXvcdvDvjDKTsbiLLLOhPbOAuVa", "jSaHHYOmVXfvveNitRIugbiahbZBswrhWapT", "dieRcNvt", "HXtaHdyf", LocalDate.now(), 36.23, "fYF", false, LocalDate.now(), LocalDate.now());
    Budget budget6 = new Budget("VBjSaLwKrqVsTbfScyVSNhkHsgMnaEbSYruW", "DDSgJdBDcqHSkbYtxJdmPDgOtfAInawEoUdv", "Dibjbeof", "YWAoTycq", LocalDate.now(), 69.71, "fYF", false, LocalDate.now(), LocalDate.now());
    Budget budget7 = new Budget("XhjkUrjEcFqNcIyMXFMiMHGsXppRDsyGdmej", "WXWBqoJdNxSNlmnUdfsbdWeWbtxHUERZtfqH", "feZJWkmg", "qqnUrZvl", LocalDate.now(), 67.26, "fYF", false, LocalDate.now(), LocalDate.now());
    Budget budget8 = new Budget("ILUIdadvWRamvundEMcmUrsdXaWDbJgdbyCt", "OvRsIANHDkcxpkDJldUhvkLuMkKPGkNwiRsr", "xtKdBRAR", "EiqVFwBy", LocalDate.now(), 32.67, "tJu", false, LocalDate.now(), LocalDate.now());
    Budget budget9 = new Budget("raiiAxBLedLIYYYhKWHrXuntLtnyAdnkuyQM", "OvRsIANHDkcxpkDJldUhvkLuMkKPGkNwiRsr", "umuOinZM", "rbdyIdto", LocalDate.now(), 41.51, "YKs", true, LocalDate.now(), LocalDate.now());
    Budget budget10 = new Budget("khRGYAOllVchIfbuGZlvmRxXWoCyNoiAYGKb", "OvRsIANHDkcxpkDJldUhvkLuMkKPGkNwiRsr", "FukDaXDD", "bxePntAI", LocalDate.now(), 10.73, "HMW", true, LocalDate.now(), LocalDate.now());
    Budget budget11 = new Budget("SlaWmpDLMFWJxgLxumeQvqDjZRYcKPbFPgIV", "OvRsIANHDkcxpkDJldUhvkLuMkKPGkNwiRsr", "UwhyuhQe", "agdNovue", LocalDate.now(), 33.73, "poE", false, LocalDate.now(), LocalDate.now());
    Budget budget12 = new Budget("ZsldRauMSSoqgfOFxTWljvtIwNyJeoNZCOPA", "OjHNgFyscWEmPETxfqCYGcypwQhvYpMLirOS", "NZqLNncT", "YpyHGHWj", LocalDate.now(), 59.21, "Imu", false, LocalDate.now(), LocalDate.now());
    Budget budget13 = new Budget("jLaIHejFZEWVhbWjqliKEhgbfOCAdOORwAly", "xJlqXbMBZCENCZIqMVBYiFlVohSrZlPsNlqv", "CQDjuGgJ", "uMwuwjCl", LocalDate.now(), 49.44, "Imu", true, LocalDate.now(), LocalDate.now());
    Budget budget14 = new Budget("LhQtKFxvIVpOIVlGtPSJKyKiepNTxHdIxNuD", "OEDBhDBdxAhGLOjbKNLGdXKqnqTWQcUlWkKS", "QEbworLI", "OKjJdqeg", LocalDate.now(), 41.39, "Imu", false, LocalDate.now(), LocalDate.now());
    Budget budget15 = new Budget("YIPtJdaDZyVybhFLaZktkEgZoXOwNYDYDeit", "KWIfAWYrnTLSFMaSaCVQkZkdJqIBbIuDaEmD", "sfMqGVfn", "vAwHQIYo", LocalDate.now(), 15.65, "Imu", true, LocalDate.now(), LocalDate.now());
    Budget budget16 = new Budget("jtaLmACMhXKXqrTYAkwAAhisxadHluubYvNk", "LPPHmPgrpKVmwghqKsRyOganyZeGSZgwsJrc", "gtYwWurX", "rDKFsMll", LocalDate.now(), 41.5, "PpY", false, LocalDate.now(), LocalDate.now());
    Budget budget17 = new Budget("GZCcJIDDkreYbrYAVICdOyLruNPGfXuwWBVU", "LPPHmPgrpKVmwghqKsRyOganyZeGSZgwsJrc", "SGlsrUoi", "XwLKkZSD", LocalDate.now(), 63.45, "vEZ", false, LocalDate.now(), LocalDate.now());
    Budget budget18 = new Budget("VeWJQOEUMEqDLxxhELJdvQUyvlUHFWRnIoXA", "LPPHmPgrpKVmwghqKsRyOganyZeGSZgwsJrc", "keTKaqVq", "XyrEwxpF", LocalDate.now(), 16.66, "omD", false, LocalDate.now(), LocalDate.now());
    Budget budget19 = new Budget("YyIlCIYhCVDySKGZMRCcmcRHmdOPfEvCCBlW", "LPPHmPgrpKVmwghqKsRyOganyZeGSZgwsJrc", "tGWQVryx", "qJVMCWVU", LocalDate.now(), 11.73, "RHw", true, LocalDate.now(), LocalDate.now());
    Budget budget20 = new Budget("gAbKxkcEwhVAaBGRtqkEwWWlYTYvPaQcIlsU", "UYgaJnbjcBmLSOeYXnjwsTYXiqNexdDukwVF", "misjFIGm", "bBcdYXSI", LocalDate.now(), 10.39, "ecn", false, LocalDate.now(), LocalDate.now());
    Budget budget21 = new Budget("TsJRBAEUdTWQuOeITLfVXoVTgxeJTParZhYK", "ljNVbxfnxTBYKPEpXuMwkmSJhAlrsZGmKPMs", "eMOVtAZi", "mCiuqPkb", LocalDate.now(), 25.83, "ecn", false, LocalDate.now(), LocalDate.now());
    Budget budget22 = new Budget("eHxSBakWYJORyonaCvpBcAhZNcGXqUPRXItj", "CQCxnSOCHQrmwdEDtvsqtDxMAQlXJDvsapVr", "qxIFmZiB", "wuKdmjaL", LocalDate.now(), 32.59, "ecn", false, LocalDate.now(), LocalDate.now());
    Budget budget23 = new Budget("sWmhhMZxjkGcOLCUamWTUYVWrfwfpNlHaIva", "fuqsbASRofmADOPVDMEppCkguLAHtQkdQUKf", "aoKGtcgL", "yAOBPZpA", LocalDate.now(), 53.15, "ecn", false, LocalDate.now(), LocalDate.now());
    Budget_VM Budget_VM0= new Budget_VM(budget0);
    Budget_VM Budget_VM1= new Budget_VM(budget1);
    Budget_VM Budget_VM2= new Budget_VM(budget2);
    Budget_VM Budget_VM3= new Budget_VM(budget3);
    Budget_VM Budget_VM4= new Budget_VM(budget4);
    Budget_VM Budget_VM5= new Budget_VM(budget5);
    Budget_VM Budget_VM6= new Budget_VM(budget6);
    Budget_VM Budget_VM7= new Budget_VM(budget7);
    Budget_VM Budget_VM8= new Budget_VM(budget8);
    Budget_VM Budget_VM9= new Budget_VM(budget9);
    Budget_VM Budget_VM10= new Budget_VM(budget10);
    Budget_VM Budget_VM11= new Budget_VM(budget11);
    Budget_VM Budget_VM12= new Budget_VM(budget12);
    Budget_VM Budget_VM13= new Budget_VM(budget13);
    Budget_VM Budget_VM14= new Budget_VM(budget14);
    Budget_VM Budget_VM15= new Budget_VM(budget15);
    Budget_VM Budget_VM16= new Budget_VM(budget16);
    Budget_VM Budget_VM17= new Budget_VM(budget17);
    Budget_VM Budget_VM18= new Budget_VM(budget18);
    Budget_VM Budget_VM19= new Budget_VM(budget19);
    Budget_VM Budget_VM20= new Budget_VM(budget20);
    Budget_VM Budget_VM21= new Budget_VM(budget21);
    Budget_VM Budget_VM22= new Budget_VM(budget22);
    Budget_VM Budget_VM23= new Budget_VM(budget23);
    
    budgetVMs.add(Budget_VM0);
    budgetVMs.add(Budget_VM1);
    budgetVMs.add(Budget_VM2);
    budgetVMs.add(Budget_VM3);
    budgetVMs.add(Budget_VM4);
    budgetVMs.add(Budget_VM5);
    budgetVMs.add(Budget_VM6);
    budgetVMs.add(Budget_VM7);
    budgetVMs.add(Budget_VM8);
    budgetVMs.add(Budget_VM9);
    budgetVMs.add(Budget_VM10);
    budgetVMs.add(Budget_VM11);
    budgetVMs.add(Budget_VM12);
    budgetVMs.add(Budget_VM13);
    budgetVMs.add(Budget_VM14);
    budgetVMs.add(Budget_VM15);
    budgetVMs.add(Budget_VM16);
    budgetVMs.add(Budget_VM17);
    budgetVMs.add(Budget_VM18);
    budgetVMs.add(Budget_VM19);
    budgetVMs.add(Budget_VM20);
    budgetVMs.add(Budget_VM21);
    budgetVMs.add(Budget_VM22);
    budgetVMs.add(Budget_VM23);
    
    Collections.sort(budgetVMs);
    currency_codes = new ArrayList<>();
    String currency_code0 = "MQo";
    String currency_code1 = "CxC";
    String currency_code2 = "vLC";
    String currency_code3 = "qJX";
    currency_codes.add(currency_code0);
    currency_codes.add(currency_code1);
    currency_codes.add(currency_code2);
    currency_codes.add(currency_code3);
    Collections.sort(currency_codes);
  }

  @Override
  public List<String> getDistinctcurrency_codeForDropdown() {
    return currency_codes;
  }

  @Override
  public int add(Budget  _budget ) throws SQLException {
    if (duplicateKey(_budget )){
      return 0;
    }
    if (exceptionKey(_budget )){
      throw new SQLException("error");
    }
    int size = budgetVMs.size();
    Budget_VM budget_VM = new  Budget_VM(_budget );
    budgetVMs.add(budget_VM);
    int newsize = budgetVMs.size();
    return newsize-size;
  }

  @Override
  public List<Budget_VM> getAllbudget(int offset, int limit, String search, String user_id, String currency_code_id) throws SQLException {
    search=search.toLowerCase();
    List<Budget_VM> results = new ArrayList<>();
    for (Budget_VM budget : budgetVMs){
      if (!user_id.isEmpty()&& !budget.getuser_id().equals(user_id))

      {continue;
      }
    if (!currency_code_id.isEmpty()&& !budget.getcurrency_code_id().equals(currency_code_id))

    {continue;
    }

    if (!search.isEmpty() && ! budget.getbudget_id().toLowerCase().contains(search)&& ! budget.getuser_id().contains(search)&& ! budget.getname().contains(search)&& ! budget.getdetails().contains(search)&& ! budget.getstart_date().toString().contains(search)&& ! budget.getlimit_amount().toString().contains(search)&& ! budget.getcurrency_code_id().contains(search)&& ! budget.getcreated_at().toString().contains(search)&& ! budget.getupdated_at().toString().contains(search)){
      continue;
    }
    results.add(budget);
  }
return results;
}

  @Override
  public int getbudgetCount(String search, String userId, String currency_code_id) {
    search=search.toLowerCase();
    List<Budget_VM> results = new ArrayList<>();
    for (Budget_VM budget : budgetVMs){
      if (!userId.isEmpty()&& !budget.getuser_id().equals(userId))

      {continue;
      }
      if (!currency_code_id.isEmpty()&& !budget.getcurrency_code_id().equals(currency_code_id))

      {continue;
      }

      if (!search.isEmpty() && ! budget.getbudget_id().toLowerCase().contains(search)&& ! budget.getuser_id().contains(search)&& ! budget.getname().contains(search)&& ! budget.getdetails().contains(search)&& ! budget.getstart_date().toString().contains(search)&& ! budget.getlimit_amount().toString().contains(search)&& ! budget.getcurrency_code_id().contains(search)&& ! budget.getcreated_at().toString().contains(search)&& ! budget.getupdated_at().toString().contains(search)){
        continue;
      }
      results.add(budget);
    }
    return results.size();
  }

  @Override
  public int update(Budget oldbudget, Budget newbudget) throws SQLException {
    int location =-1;
    if (duplicateKey(oldbudget)){
      return 0;
    }
    if (exceptionKey(oldbudget)){
      throw new SQLException("error");
    }
    for (int i=0;i<budgetVMs.size();i++){
      if (budgetVMs.get(i).getbudget_id().equals(oldbudget.getbudget_id())){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }
    Budget_VM updated = new Budget_VM(newbudget);
    budgetVMs.set(location,updated);
    return 1;
  }

  @Override
  public Budget_VM getBudgetByPrimaryKey(Budget budget) throws SQLException {
    Budget_VM result = null;
    for (Budget_VM _budget : budgetVMs) {
      if (budget.getbudget_id().equals(_budget.getbudget_id())){
        result = _budget;
        break;
      }
    }
    if (result == null){
      throw new SQLException("budget not found");
    }
    return result;
  }

  private boolean duplicateKey(Budget  _budget ){
    return _budget.getname().contains("DUPLICATE");
  }
  private boolean exceptionKey(Budget  _budget ){
    return _budget.getname().contains("EXCEPTION");
  }
}
