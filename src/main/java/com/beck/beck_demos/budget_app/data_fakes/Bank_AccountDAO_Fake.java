package com.beck.beck_demos.budget_app.data_fakes;

import com.beck.beck_demos.budget_app.iData.iBank_AccountDAO;
import com.beck.beck_demos.budget_app.models.Bank_Account;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Bank_AccountDAO_Fake implements iBank_AccountDAO {
  private List<Bank_Account> bank_accountVMs;

  public Bank_AccountDAO_Fake() {
    bank_accountVMs = new ArrayList<>();
    Bank_Account bank_account0 = new Bank_Account("hSNjUblO", 24, "DmjVevwE", 29.56, new Date());
    Bank_Account bank_account1 = new Bank_Account("GejhdSlI", 24, "YvswvEJj", 18.86, new Date());
    Bank_Account bank_account2 = new Bank_Account("OcqGoFft", 24, "wSpiIltu", 41.03, new Date());
    Bank_Account bank_account3 = new Bank_Account("lDAMLJpU", 24, "FpesMopi", 26.04, new Date());
    Bank_Account bank_account4 = new Bank_Account("tcxXoKSi", 24, "mHCYJxXf", 18.67, new Date());
    Bank_Account bank_account5 = new Bank_Account("WfwyjDRZ", 57, "UTuLNWdJ", 14.75, new Date());
    Bank_Account bank_account6 = new Bank_Account("beBsZDMD", 57, "WtEgCSdb", 64.59, new Date());
    Bank_Account bank_account7 = new Bank_Account("rYTkwMZL", 57, "qLlUNbHU", 56.11, new Date());
    Bank_Account bank_account8 = new Bank_Account("vfoQvgss", 57, "doiQeNIQ", 65.08, new Date());
    Bank_Account bank_account9 = new Bank_Account("NdBMLjpx", 57, "GuwqExUv", 50.96, new Date());
    Bank_Account bank_account10 = new Bank_Account("MgQlSwbw", 51, "fwvRyEqP", 29.29, new Date());
    Bank_Account bank_account11 = new Bank_Account("PFyxxvlj", 51, "jvhaDNwq", 51.97, new Date());
    Bank_Account bank_account12 = new Bank_Account("OclsrBnu", 51, "rynfrgMb", 23.91, new Date());
    Bank_Account bank_account13 = new Bank_Account("MRMUBpNu", 51, "BNkAcSmA", 61.72, new Date());
    Bank_Account bank_account14 = new Bank_Account("TFbvkCDl", 51, "uEDBTrpB", 48.12, new Date());
    bank_accountVMs.add(bank_account0);
    bank_accountVMs.add(bank_account1);
    bank_accountVMs.add(bank_account2);
    bank_accountVMs.add(bank_account3);
    bank_accountVMs.add(bank_account4);
    bank_accountVMs.add(bank_account5);
    bank_accountVMs.add(bank_account6);
    bank_accountVMs.add(bank_account7);
    bank_accountVMs.add(bank_account8);
    bank_accountVMs.add(bank_account9);
    bank_accountVMs.add(bank_account10);
    bank_accountVMs.add(bank_account11);
    bank_accountVMs.add(bank_account12);
    bank_accountVMs.add(bank_account13);
    bank_accountVMs.add(bank_account14);
    Collections.sort(bank_accountVMs);
  }
  @Override
  public Bank_Account getBank_AccountByPrimaryKey(Bank_Account _bank_account) throws SQLException {
    Bank_Account result = null;
    for (Bank_Account bank_account : bank_accountVMs) {
      if (bank_account.getBank_Account_ID().equals(_bank_account.getBank_Account_ID())){
        result = bank_account;
        break;
      }
    }
    if (result == null){
      throw new SQLException("Bank_Account not found");
    }
    return result;
  }
  @Override
  public int update(Bank_Account oldBank_Account, Bank_Account newBank_Account) throws SQLException{
    int location =-1;
    if (duplicateKey(oldBank_Account)){
      return 0;
    }
    if (exceptionKey(oldBank_Account)){
      throw new SQLException("error");
    }
    for (int i=0;i<bank_accountVMs.size();i++){
      if (bank_accountVMs.get(i).getBank_Account_ID().equals(oldBank_Account.getBank_Account_ID())){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }
    bank_accountVMs.set(location,newBank_Account);
    return 1;
  }

  @Override
  public List <Bank_Account> getAllBank_Account(int limit, int offset, Integer User_ID) throws SQLException {
    List<Bank_Account> results = new ArrayList<>();
    for (Bank_Account bank_account : bank_accountVMs){
      if ((User_ID==null||bank_account.getUser_ID().equals(User_ID))
      ){
        results.add(bank_account);
      }
    }
    return results;
  }
  @Override
  public int add(Bank_Account _bank_account) throws SQLException {
    if (duplicateKey(_bank_account)){
      return 0;
    }
    if (exceptionKey(_bank_account)){
      throw new SQLException("error");
    }
    int size = bank_accountVMs.size();

    bank_accountVMs.add(_bank_account);
    int newsize = bank_accountVMs.size();
    return newsize-size;
  }

  private boolean duplicateKey(Bank_Account _bank_account){
    return _bank_account.getBank_Account_ID().equals("DUPLICATE");
  }
  private boolean exceptionKey(Bank_Account _bank_account){
    return _bank_account.getBank_Account_ID().equals("EXCEPTION");
  }
}
