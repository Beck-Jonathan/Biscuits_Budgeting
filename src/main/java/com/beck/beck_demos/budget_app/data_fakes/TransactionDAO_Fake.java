package com.beck.beck_demos.budget_app.data_fakes;

import com.beck.beck_demos.budget_app.iData.iTransactionDAO;
import com.beck.beck_demos.budget_app.models.Category_VM;
import com.beck.beck_demos.budget_app.models.Transaction;
import com.beck.beck_demos.budget_app.models.Transaction_VM;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.io.File;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

public class TransactionDAO_Fake implements iTransactionDAO {
  private  List<Transaction_VM> transactionVMs;
  public TransactionDAO_Fake(){
    transactionVMs = new ArrayList<>();
    Transaction transaction0 = new Transaction("XxtdYmVMXxtdYmVMXxtdYmVMXxtdYmVMXxtd", 41, "mMvMlyeB", "xAwAtNYh", new Date(103,4,4), 50, "nCYTHrPO", 43.94, "VpNtaCaw", "ZPHFJIYd");
    Transaction transaction1 = new Transaction("ukukySnYXxtdYmVMXxtdYmVMXxtdYmVMXxtd", 41, "EEgjEfHm", "DZruQsNI", new Date(104,4,4), 41, "tSKeiGeS", 59.31, "tuuNNIrl", "ADZCjFxl");
    Transaction transaction2 = new Transaction("AbgcYOYUXxtdYmVMXxtdYmVMXxtdYmVMXxtd", 41, "aqWOkMQH", "MdlXXIxq", new Date(104,4,4), 62, "HudIsSUR", 24.12, "snltZGLJ", "InhoYwkH");
    Transaction transaction3 = new Transaction("OCKMacGkXxtdYmVMXxtdYmVMXxtdYmVMXxtd", 41, "YwEsYxXg", "YrrxQPxo", new Date(104,4,4), 13, "KRfWlDSH", 11.36, "YwMJnuUm", "LEtOqAPS");
    Transaction transaction4 = new Transaction("BFBLAaRwXxtdYmVMXxtdYmVMXxtdYmVMXxtd", 69, "PVEswhXn", "jLiECvmj", new Date(104,4,4), 18, "vVDgNOEv", 26.08, "hlysPkyD", "SwsBHRBv");
    Transaction transaction5 = new Transaction("hwVSiwiSXxtdYmVMXxtdYmVMXxtdYmVMXxtd", 29, "PVEswhXn", "CsCZwEIf", new Date(104,4,4), 53, "nfoybZov", 65.91, "VMRcORSQ", "gTnPkAuA");
    Transaction transaction6 = new Transaction("yFdcNrQjXxtdYmVMXxtdYmVMXxtdYmVMXxtd", 64, "PVEswhXn", "QakFsBmw", new Date(104,4,4), 16, "jPmIJfXo", 16.21, "vuoEajRS", "HTBrTAln");
    Transaction transaction7 = new Transaction("uNflmsKjXxtdYmVMXxtdYmVMXxtdYmVMXxtd", 22, "PVEswhXn", "wZvoDKnV", new Date(104,4,4), 32, "lXJeJiol", 52.11, "WZHXraZJ", "BIuspOlq");
    Transaction transaction8 = new Transaction("VkbDBwHTXxtdYmVMXxtdYmVMXxtdYmVMXxtd", 55, "oCESkdTM", "VSQpEweA", new Date(104,4,4), 60, "TWWqErRX", 39.51, "kxwGAKmW", "woRyoFcX");
    Transaction transaction9 = new Transaction("JqXLFMvfXxtdYmVMXxtdYmVMXxtdYmVMXxtd", 47, "BPRebbnT", "VSQpEweA", new Date(104,4,4), 12, "CHpsZCmf", 55.61, "qsFNeSmq", "tEjHmMeK");
    Transaction transaction10 = new Transaction("jXRRwPVGXxtdYmVMXxtdYmVMXxtdYmVMXxtm", 53, "aSfJIwAr", "VSQpEweA", new Date(104,4,4), 21, "WMWvOjvc", 25.87, "RuTUfnqD", "FTZsciHu");
    Transaction transaction11 = new Transaction("kZLjumHuXxtdYmVMXxtdYmVMXxtdYmVMXxtm", 27, "pXnmIbVT", "VSQpEweA", new Date(104,4,4), 10, "EKVMfqSE", 61.11, "fXQmOAPj", "sTnYEpVD");
    Transaction transaction12 = new Transaction("wlEcdvnUXxtdYmVMXxtdYmVMXxtdYmVMXxtm", 26, "WpvyCtle", "RfZfaVvH", new Date(104,4,4), 59, "OntsTkKs", 29.34, "ZxUbTuFm", "RYFvDdXx");
    Transaction transaction13 = new Transaction("sIBBbJNrXxtdYmVMXxtdYmVMXxtdYmVMXxtm", 26, "ALSgpQxI", "xCACCyjQ", new Date(104,4,4), 46, "YtfNolpT", 38.51, "llquSLdd", "IHmFdUvt");
    Transaction transaction14 = new Transaction("XjYIPbdLXxtdYmVMXxtdYmVMXxtdYmVMXxtm", 26, "ZGTodgXQ", "OKGWFxnl", new Date(104,4,4), 41, "ZjOMLauk", 17.9, "cPQPsiPT", "FgMVKoWA");
    Transaction transaction15 = new Transaction("eZTCJKPuXxtdYmVMXxtdYmVMXxtdYmVMXxtm", 26, "nNhvQhku", "pNWYBUDC", new Date(104,4,4), 60, "AjKuGgOy", 23.2, "GnhYbFoJ", "VdOyINxo");
    Transaction transaction16 = new Transaction("CmpcYkijXxtdYmVMXxtdYmVMXxtdYmVMXxtm", 36, "DBoXIYtE", "owsDSMKg", new Date(104,4,4), 41, "MlkCXriZ", 65.73, "LQWAgWCB", "SmErOigP");
    Transaction transaction17 = new Transaction("FlknekdMXxtdYmVMXxtdYmVMXxtdYmVMXxtm", 18, "DBoXIYtE", "SAUwtypY", new Date(104,4,4), 12, "OwLfQKqt", 35.82, "ULSjbcgK", "WffvnEBg");
    Transaction transaction18 = new Transaction("VrFCCpfYXxtdYmVMXxtdYmVMXxtdYmVMXxtm", 29, "DBoXIYtE", "sFZdmpLq", new Date(104,4,4), 66, "qhdUQReG", 56.88, "WSJgaOBT", "RJffRPjb");
    Transaction transaction19 = new Transaction("jmsokvbMXxtdYmVMXxtdYmVMXxtdYmVMXxtm", 30, "DBoXIYtE", "CAbmXuCP", new Date(104,4,4), 15, "JLLFQoql", 21.68, "BRFjtTta", "bMFTtYtN");
    Transaction transaction20 = new Transaction("sWoBSjuBXxtdYmVMXxtdYmVMXxtdYmVMXxtm", 12, "bIivGJRd", "cVMGDgbA", new Date(104,4,4), 58, "VQebeFxB", 50.8, "UfRaipPS", "bkXWfgiK");
    Transaction transaction21 = new Transaction("XsMDmdbkXxtdYmVMXxtdYmVMXxtdYmVMXxtm", 27, "ZgevAUIu", "cVMGDgbA", new Date(104,4,4), 59, "HnFpqjsO", 68.53, "mOrHwWnc", "nIcvHFxr");
    Transaction transaction22 = new Transaction("tGdWIOjeXxtdYmVMXxtdYmVMXxtdYmVMXxtm", 42, "uFaBKenY", "cVMGDgbA", new Date(104,4,4), 29, "iOIWaASQ", 21.64, "gArypmda", "eAHYiBme");
    Transaction transaction23 = new Transaction("YyQSbKqAXxtdYmVMXxtdYmVMXxtdYmVMXxtm", 45, "NXTTNEiC", "cVMGDgbA", new Date(104,4,4), 50, "WtEJQpQm", 36.79, "UmUtRtTX", "RYeuJaCl");
    Transaction transaction24 = new Transaction("IENdRkxBXxtdYmVMXxtdYmVMXxtdYmVMXxtm", 53, "FMIFPjsE", "BensCZsg", new Date(104,4,4), 34, "cBkGjgMp", 13.03, "CVsLgKRC", "hkpHvOrM");
    Transaction transaction25 = new Transaction("GeTvtHwTXxtdYmVMXxtdYmVMXxtdYmVMXxtm", 53, "jCViDgKb", "kdpmhVUu", new Date(104,4,4), 26, "fcarqTbL", 63.48, "IuYTrwIv", "TeEQvTVc");
    Transaction transaction26 = new Transaction("QqeaMgsjXxtdYmVMXxtdYmVMXxtdYmVMXxtm", 53, "JJVFTRZp", "DQxpZBPL", new Date(104,4,4), 31, "fMUjPjaS", 46.29, "ilZHmxXw", "HxwetfRS");
    Transaction transaction27 = new Transaction("SBFHvDvnXxtdYmVMXxtdYmVMXxtdYmVMXxtm", 53, "BeNVmxpH", "RppCaBDI", new Date(104,4,4), 23, "WBRiKXvh", 38.11, "LFlQuDqJ", "WSsbKgDy");
    Transaction transaction28 = new Transaction("FqNRhRlkXxtdYmVMXxtdYmVMXxtdYmVMXxtm", 31, "yFZmNHCd", "AtmLWekO", new Date(104,4,4), 48, "MUOJKuhv", 16.26, "fyCmAMDF", "bsstSKxb");
    Transaction transaction29 = new Transaction("inHhcuhVXxtdYmVMXxtdYmVMXxtdYmVMXxtm", 66, "yFZmNHCd", "fRpAUfEV", new Date(104,4,4), 68, "HlySFegk", 30.3, "uyLTksne", "wZrSpHrm");
    Transaction transaction30 = new Transaction("ksNNnAZwXxtdYmVMXxtdYmVMXxtdYmVMXxtm", 59, "yFZmNHCd", "oVCqqbNw", new Date(104,4,4), 37, "xFGaBHtP", 35.88, "IKMruqpM", "hKKsnEyP");
    Transaction transaction31 = new Transaction("tcbQFVKXXxtdYmVMXxtdYmVMXxtdYmVMXxtm", 58, "yFZmNHCd", "flwLGtKf", new Date(104,4,4), 47, "IKuAlAlm", 26.51, "FQCalMRw", "yTmAoDPF");
    Transaction transaction32 = new Transaction("UGjhbEcXXxtdYmVMXxtdYmVMXxtdYmVMXxtm", 18, "hUiVZscL", "sBbQihPX", new Date(104,4,4), 20, "rcTGftry", 21.19, "aXAeMecU", "OKcgUtVa");
    Transaction transaction33 = new Transaction("hhKFllcCXxtdYmVMXxtdYmVMXxtdYmVMXxtm", 50, "fFXuaQUE", "sBbQihPX", new Date(104,4,4), 19, "oVyJkvhy", 50.41, "kkVXnVvu", "GZrsahnI");
    Transaction transaction34 = new Transaction("lhFEQTlSXxtdYmVMXxtdYmVMXxtdYmVMXxtm", 51, "BjJplFqQ", "sBbQihPX", new Date(104,4,4), 18, "QhuGKUZx", 52.52, "vXvmuRag", "LNBeJFUL");
    Transaction transaction35 = new Transaction("kPhlBQapXxtdYmVMXxtdYmVMXxtdYmVMXxtm", 44, "uiFSepoQ", "sBbQihPX", new Date(104,4,4), 43, "mVuKEsOC", 55.51, "BoJhhBJH", "tXSEjfwF");
    Transaction_VM transaction_VM0= new Transaction_VM(transaction0);
    Transaction_VM transaction_VM1= new Transaction_VM(transaction1);
    Transaction_VM transaction_VM2= new Transaction_VM(transaction2);
    Transaction_VM transaction_VM3= new Transaction_VM(transaction3);
    Transaction_VM transaction_VM4= new Transaction_VM(transaction4);
    Transaction_VM transaction_VM5= new Transaction_VM(transaction5);
    Transaction_VM transaction_VM6= new Transaction_VM(transaction6);
    Transaction_VM transaction_VM7= new Transaction_VM(transaction7);
    Transaction_VM transaction_VM8= new Transaction_VM(transaction8);
    Transaction_VM transaction_VM9= new Transaction_VM(transaction9);
    Transaction_VM transaction_VM10= new Transaction_VM(transaction10);
    Transaction_VM transaction_VM11= new Transaction_VM(transaction11);
    Transaction_VM transaction_VM12= new Transaction_VM(transaction12);
    Transaction_VM transaction_VM13= new Transaction_VM(transaction13);
    Transaction_VM transaction_VM14= new Transaction_VM(transaction14);
    Transaction_VM transaction_VM15= new Transaction_VM(transaction15);
    Transaction_VM transaction_VM16= new Transaction_VM(transaction16);
    Transaction_VM transaction_VM17= new Transaction_VM(transaction17);
    Transaction_VM transaction_VM18= new Transaction_VM(transaction18);
    Transaction_VM transaction_VM19= new Transaction_VM(transaction19);
    Transaction_VM transaction_VM20= new Transaction_VM(transaction20);
    Transaction_VM transaction_VM21= new Transaction_VM(transaction21);
    Transaction_VM transaction_VM22= new Transaction_VM(transaction22);
    Transaction_VM transaction_VM23= new Transaction_VM(transaction23);
    Transaction_VM transaction_VM24= new Transaction_VM(transaction24);
    Transaction_VM transaction_VM25= new Transaction_VM(transaction25);
    Transaction_VM transaction_VM26= new Transaction_VM(transaction26);
    Transaction_VM transaction_VM27= new Transaction_VM(transaction27);
    Transaction_VM transaction_VM28= new Transaction_VM(transaction28);
    Transaction_VM transaction_VM29= new Transaction_VM(transaction29);
    Transaction_VM transaction_VM30= new Transaction_VM(transaction30);
    Transaction_VM transaction_VM31= new Transaction_VM(transaction31);
    Transaction_VM transaction_VM32= new Transaction_VM(transaction32);
    Transaction_VM transaction_VM33= new Transaction_VM(transaction33);
    Transaction_VM transaction_VM34= new Transaction_VM(transaction34);
    Transaction_VM transaction_VM35= new Transaction_VM(transaction35);
    transactionVMs.add(transaction_VM0);
    transactionVMs.add(transaction_VM1);
    transactionVMs.add(transaction_VM2);
    transactionVMs.add(transaction_VM3);
    transactionVMs.add(transaction_VM4);
    transactionVMs.add(transaction_VM5);
    transactionVMs.add(transaction_VM6);
    transactionVMs.add(transaction_VM7);
    transactionVMs.add(transaction_VM8);
    transactionVMs.add(transaction_VM9);
    transactionVMs.add(transaction_VM10);
    transactionVMs.add(transaction_VM11);
    transactionVMs.add(transaction_VM12);
    transactionVMs.add(transaction_VM13);
    transactionVMs.add(transaction_VM14);
    transactionVMs.add(transaction_VM15);
    transactionVMs.add(transaction_VM16);
    transactionVMs.add(transaction_VM17);
    transactionVMs.add(transaction_VM18);
    transactionVMs.add(transaction_VM19);
    transactionVMs.add(transaction_VM20);
    transactionVMs.add(transaction_VM21);
    transactionVMs.add(transaction_VM22);
    transactionVMs.add(transaction_VM23);
    transactionVMs.add(transaction_VM24);
    transactionVMs.add(transaction_VM25);
    transactionVMs.add(transaction_VM26);
    transactionVMs.add(transaction_VM27);
    transactionVMs.add(transaction_VM28);
    transactionVMs.add(transaction_VM29);
    transactionVMs.add(transaction_VM30);
    transactionVMs.add(transaction_VM31);
    transactionVMs.add(transaction_VM32);
    transactionVMs.add(transaction_VM33);
    transactionVMs.add(transaction_VM34);
    transactionVMs.add(transaction_VM35);
    Collections.sort(transactionVMs);

  }
  @Override
  public int update_category(Transaction oldTransaction, Transaction newTransaction) throws SQLException {
    if (duplicateKey(newTransaction)){
      return 0;
    }
    if (exceptionKey(newTransaction)){
      throw new SQLException("error");
    }
    int result =0;
    for (Transaction transaction : transactionVMs) {
      if (transaction.getUser_ID().equals(oldTransaction.getUser_ID()) && transaction.getTransaction_ID().equals(oldTransaction.getTransaction_ID())) {
        transaction.setCategory_ID(newTransaction.getCategory_ID());
        result = 1;
        break;
      }
    }
    return result;
  }

  @Override
  public int bulkUpdateCategory(int userid, String category, String query) throws SQLException {
    return 0;
  }

  @Override
  public List<Transaction> getTransactionForExportByUser(int userID) throws SQLException {
    List<Transaction> transactions = new ArrayList<>();
    for (Transaction t : transactionVMs){
      if (t.getUser_ID().equals(userID)){
        transactions.add(t);
      }
    }
    if (transactions.isEmpty()){
      throw new SQLException("error");
    }
    return transactions;
  }

  @Override
  public List<Transaction> getTransactionFromFile(File uploadedFile, String type) throws SQLException {
    return List.of();
  }

  @Override
  public int add(Transaction _transaction) {
    return 0;
  }

  @Override
  public int addBatch(List<Transaction> _transactions,int user_id) throws SQLException {
    return 0;
  }

  @Override
  public List<Transaction_VM> getTransactionByUser(Integer User_ID) throws SQLException {
    return List.of();
  }

  @Override
  public List<Transaction_VM> getTransactionByUser(int User_ID, int pagesize) throws SQLException {
    List<Transaction_VM> results = new ArrayList<>();
    for (Transaction_VM t : transactionVMs) {
      if (t.getUser_ID().equals(User_ID)) {
        results.add(t);
      }
    }
    return results;
  }

  @Override
  public List<Transaction_VM> getTransactionByUser(Integer User_ID, int pagesize, int offset) throws SQLException {
    List<Transaction_VM> results = new ArrayList<>();
    for (Transaction_VM t : transactionVMs) {
      if (t.getUser_ID().equals(User_ID)) {
        results.add(t);
      }
    }
    return results;
  }

  @Override
  public List<Transaction_VM> getTransactionByUser(int userID, String category, int year, int pagesize, int offset, String sortBy, int order) throws SQLException {
    List<Transaction_VM> results = new ArrayList<>();
    for (Transaction_VM t : transactionVMs) {

      if (t.getUser_ID().equals(userID)&&(category.equals("")||t.getCategory_ID().equals(category))&&(year==0||t.getPost_Date().getYear()==year)) {
        results.add(t);
      }
    }
    return results;
  }

  @Override
  public List<Transaction_VM> searchTransactionByUser(int userID, String query) throws SQLException {
    return List.of();
  }

  @Override
  public List<List<Category_VM>> getAnalysis(List<List<Category_VM>> years, int user_ID) throws SQLException {
     ArrayList<List<Category_VM>> analysis = new ArrayList<List<Category_VM>>();
     ArrayList<Transaction> transactions = new ArrayList<>();
    HashSet<String> categories = new HashSet<>();
    int minYear = 9999;
    int maxYear = 0;
     for (Transaction t : transactionVMs) {
       if (t.getUser_ID().equals(user_ID)) {
         transactions.add(t);
         categories.add(t.getCategory_ID());
       }
       if (t.getPost_Date().getYear()<minYear){
         minYear = t.getPost_Date().getYear();
       }
       if (t.getPost_Date().getYear()>maxYear){
         maxYear = t.getPost_Date().getYear();
       }
     }
     List<Integer> useryears = new ArrayList<>();
     for (int i = minYear; i <= maxYear; i++) {
       useryears.add(i);
     }
     for (int i = 0; i<useryears.size(); i++) {
       List<Category_VM> category_vms = new ArrayList<>();
       for (String category : categories) {
          Category_VM category_vm = new Category_VM();
          category_vm.setCategory_ID(category);
         category_vms.add(category_vm);
       }
       analysis.add(category_vms);
     }

     return analysis;
  }

  @Override
  public int getTransactionCountByUser(int userID, String category, int year) throws SQLException {
    int count = 0;
    for (Transaction t : transactionVMs) {
      if (t.getUser_ID().equals(userID)&&(year==0 || t.getPost_Date().getYear()==year)) {
        count++;
      }
    }
    return count;
  }

  @Override
  public double getTransactionCategoryTotal(int userID, String category_id, String direction) throws SQLException {
    return 0;
  }

  @Override
  public List<Transaction> getTransactionByUserYearCategpry(int userID, Date date, String category, int limit, int offset) throws SQLException {
    return List.of();
  }

  @Override
  public Transaction_VM getTransactionByPrimaryKey(Transaction _transaction) throws SQLException {
    Transaction_VM result = null;
    for (Transaction_VM t : transactionVMs) {
      if (t.getUser_ID().equals(_transaction.getUser_ID())&&t.getTransaction_ID().equals(_transaction.getTransaction_ID())){
        result = t;
        break;
      }
    }
    if (result==null){
      throw new SQLException("Transaction Not Found.");
    }
    return result;
  }

  @Override
  public int update(Transaction oldTransaction, Transaction newTransaction) throws SQLException {
    if (oldTransaction.getCategory_ID().equals("EXCEPTION")){
      throw new SQLException("Exception");
    }
    int result = 0;
    int location = -1;
    for (int i=0;i<transactionVMs.size();i++) {
      if (transactionVMs.get(i).getUser_ID().equals(oldTransaction.getUser_ID())&&transactionVMs.get(i).getTransaction_ID().equals(oldTransaction.getTransaction_ID())){
        location = i;
        result = 1;
        break;
      }
    }
    Transaction_VM _newTransaction = new Transaction_VM(newTransaction);
    transactionVMs.set(location, _newTransaction);
    return result;
  }
  private boolean duplicateKey(Transaction _transaction){
    return _transaction.getCategory_ID().equals("DUPLICATE");
  }
  private boolean exceptionKey(Transaction _transaction){
    return _transaction.getCategory_ID().equals("EXCEPTION");
  }
}
