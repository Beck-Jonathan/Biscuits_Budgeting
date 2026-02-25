package com.beck.beck_demos.budget_app.data_fakes;

import com.beck.beck_demos.budget_app.iData.iTransactionDAO;
import com.beck.beck_demos.budget_app.models.Category_VM;
import com.beck.beck_demos.budget_app.models.Transaction;
import com.beck.beck_demos.budget_app.models.Transaction_VM;
//import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

public class TransactionDAO_Fake implements iTransactionDAO {
  private  List<Transaction_VM> transactionVMs;
  public TransactionDAO_Fake(){
    transactionVMs = new ArrayList<>();
    Transaction transaction0 = new Transaction("XxtdYmVMXxtdYmVMXxtdYmVMXxtdYmVMXxtd", "618052e9-c69b-4d9b-880e-e22e4a970bd6", "mMvMlyeB", "xAwAtNYh", new Date(103,4,4), 50, "Casey", 43.94, "VpNtaCaw", "ZPHFJIYd",false);
    Transaction transaction1 = new Transaction("ukukySnYXxtdYmVMXxtdYmVMXxtdYmVMXxtd", "618052e9-c69b-4d9b-880e-e22e4a970bd6", "EEgjEfHm", "DZruQsNI", new Date(104,4,4), 41, "xCasey", 59.31, "tuuNNIrl", "ADZCjFxl",true);
    Transaction transaction2 = new Transaction("AbgcYOYUXxtdYmVMXxtdYmVMXxtdYmVMXxtd", "618052e9-c69b-4d9b-880e-e22e4a970bd6", "aqWOkMQH", "MdlXXIxq", new Date(104,4,4), 62, "Casey", 24.12, "snltZGLJ", "InhoYwkH",false);
    Transaction transaction3 = new Transaction("OCKMacGkXxtdYmVMXxtdYmVMXxtdYmVMXxtd", "618052e9-c69b-4d9b-880e-e22e4a970bd6", "YwEsYxXg", "YrrxQPxo", new Date(104,4,4), 13, "KRfWlDSH", 11.36, "YwMJnuUm", "LEtOqAPS",false);
    Transaction transaction4 = new Transaction("BFBLAaRwXxtdYmVMXxtdYmVMXxtdYmVMXxtd", "618052e9-c69b-4d9b-880e-e22e4a970bd6", "PVEswhXn", "jLiECvmj", new Date(104,4,4), 18, "vVDgNOEv", 26.08, "hlysPkyD", "SwsBHRBv",false);
    Transaction transaction5 = new Transaction("hwVSiwiSXxtdYmVMXxtdYmVMXxtdYmVMXxtd", "44763ca9-b0a4-4fd2-9fc7-cf973c3a6af8", "PVEswhXn", "CsCZwEIf", new Date(104,4,4), 53, "nfoybZov", 65.91, "VMRcORSQ", "gTnPkAuA",false);
    Transaction transaction6 = new Transaction("yFdcNrQjXxtdYmVMXxtdYmVMXxtdYmVMXxtd", "44763ca9-b0a4-4fd2-9fc7-cf973c3a6af8", "PVEswhXn", "QakFsBmw", new Date(104,4,4), 16, "jPmIJfXo", 16.21, "vuoEajRS", "HTBrTAln",false);
    Transaction transaction7 = new Transaction("uNflmsKjXxtdYmVMXxtdYmVMXxtdYmVMXxtd", "44763ca9-b0a4-4fd2-9fc7-cf973c3a6af8", "PVEswhXn", "wZvoDKnV", new Date(104,4,4), 32, "lXJeJiol", 52.11, "WZHXraZJ", "BIuspOlq",false);
    Transaction transaction8 = new Transaction("VkbDBwHTXxtdYmVMXxtdYmVMXxtdYmVMXxtd", "44763ca9-b0a4-4fd2-9fc7-cf973c3a6af8", "oCESkdTM", "VSQpEweA", new Date(104,4,4), 60, "TWWqErRX", 39.51, "kxwGAKmW", "woRyoFcX",false);
    Transaction transaction9 = new Transaction("JqXLFMvfXxtdYmVMXxtdYmVMXxtdYmVMXxtd", "44763ca9-b0a4-4fd2-9fc7-cf973c3a6af8", "BPRebbnT", "VSQpEweA", new Date(104,4,4), 12, "CHpsZCmf", 55.61, "qsFNeSmq", "tEjHmMeK",false);
    Transaction transaction10 = new Transaction("jXRRwPVGXxtdYmVMXxtdYmVMXxtdYmVMXxtm", "44763ca9-b0a4-4fd2-9fc7-cf973c3a6af8", "aSfJIwAr", "VSQpEweA", new Date(104,4,4), 21, "WMWvOjvc", 25.87, "RuTUfnqD", "FTZsciHu",false);
    Transaction transaction11 = new Transaction("kZLjumHuXxtdYmVMXxtdYmVMXxtdYmVMXxtm", "44763ca9-b0a4-4fd2-9fc7-cf973c3a6af8", "pXnmIbVT", "VSQpEweA", new Date(104,4,4), 10, "EKVMfqSE", 61.11, "fXQmOAPj", "sTnYEpVD",false);
    Transaction transaction12 = new Transaction("wlEcdvnUXxtdYmVMXxtdYmVMXxtdYmVMXxtm", "44763ca9-b0a4-4fd2-9fc7-cf973c3a6af8", "WpvyCtle", "RfZfaVvH", new Date(104,4,4), 59, "OntsTkKs", 29.34, "ZxUbTuFm", "RYFvDdXx",false);
    Transaction transaction13 = new Transaction("sIBBbJNrXxtdYmVMXxtdYmVMXxtdYmVMXxtm", "44763ca9-b0a4-4fd2-9fc7-cf973c3a6af8", "ALSgpQxI", "xCACCyjQ", new Date(104,4,4), 46, "YtfNolpT", 38.51, "llquSLdd", "IHmFdUvt",false);
    Transaction transaction14 = new Transaction("XjYIPbdLXxtdYmVMXxtdYmVMXxtdYmVMXxtm", "980b3ed6-9db7-4704-a6bc-1e90d5745a35", "ZGTodgXQ", "OKGWFxnl", new Date(104,4,4), 41, "ZjOMLauk", 17.9, "cPQPsiPT", "FgMVKoWA",false);
    Transaction transaction15 = new Transaction("eZTCJKPuXxtdYmVMXxtdYmVMXxtdYmVMXxtm", "980b3ed6-9db7-4704-a6bc-1e90d5745a35", "nNhvQhku", "pNWYBUDC", new Date(104,4,4), 60, "AjKuGgOy", 23.2, "GnhYbFoJ", "VdOyINxo",false);
    Transaction transaction16 = new Transaction("CmpcYkijXxtdYmVMXxtdYmVMXxtdYmVMXxtm", "980b3ed6-9db7-4704-a6bc-1e90d5745a35", "DBoXIYtE", "owsDSMKg", new Date(104,4,4), 41, "MlkCXriZ", 65.73, "LQWAgWCB", "SmErOigP",false);
    Transaction transaction17 = new Transaction("FlknekdMXxtdYmVMXxtdYmVMXxtdYmVMXxtm", "980b3ed6-9db7-4704-a6bc-1e90d5745a35", "DBoXIYtE", "SAUwtypY", new Date(104,4,4), 12, "OwLfQKqt", 35.82, "ULSjbcgK", "WffvnEBg",false);
    Transaction transaction18 = new Transaction("VrFCCpfYXxtdYmVMXxtdYmVMXxtdYmVMXxtm", "980b3ed6-9db7-4704-a6bc-1e90d5745a35", "DBoXIYtE", "sFZdmpLq", new Date(104,4,4), 66, "qhdUQReG", 56.88, "WSJgaOBT", "RJffRPjb",false);
    Transaction transaction19 = new Transaction("jmsokvbMXxtdYmVMXxtdYmVMXxtdYmVMXxtm", "980b3ed6-9db7-4704-a6bc-1e90d5745a35", "DBoXIYtE", "CAbmXuCP", new Date(104,4,4), 15, "JLLFQoql", 21.68, "BRFjtTta", "bMFTtYtN",false);
    Transaction transaction20 = new Transaction("sWoBSjuBXxtdYmVMXxtdYmVMXxtdYmVMXxtm", "980b3ed6-9db7-4704-a6bc-1e90d5745a35", "bIivGJRd", "cVMGDgbA", new Date(104,4,4), 58, "VQebeFxB", 50.8, "UfRaipPS", "bkXWfgiK",false);
    Transaction transaction21 = new Transaction("XsMDmdbkXxtdYmVMXxtdYmVMXxtdYmVMXxtm", "980b3ed6-9db7-4704-a6bc-1e90d5745a35", "ZgevAUIu", "cVMGDgbA", new Date(104,4,4), 59, "HnFpqjsO", 68.53, "mOrHwWnc", "nIcvHFxr",false);
    Transaction transaction22 = new Transaction("tGdWIOjeXxtdYmVMXxtdYmVMXxtdYmVMXxtm", "980b3ed6-9db7-4704-a6bc-1e90d5745a35", "uFaBKenY", "cVMGDgbA", new Date(104,4,4), 29, "iOIWaASQ", 21.64, "gArypmda", "eAHYiBme",false);
    Transaction transaction23 = new Transaction("YyQSbKqAXxtdYmVMXxtdYmVMXxtdYmVMXxtm", "980b3ed6-9db7-4704-a6bc-1e90d5745a35", "NXTTNEiC", "cVMGDgbA", new Date(104,4,4), 50, "WtEJQpQm", 36.79, "UmUtRtTX", "RYeuJaCl",false);
    Transaction transaction24 = new Transaction("IENdRkxBXxtdYmVMXxtdYmVMXxtdYmVMXxtm", "980b3ed6-9db7-4704-a6bc-1e90d5745a35", "FMIFPjsE", "BensCZsg", new Date(104,4,4), 34, "cBkGjgMp", 13.03, "CVsLgKRC", "hkpHvOrM",false);
    Transaction transaction25 = new Transaction("GeTvtHwTXxtdYmVMXxtdYmVMXxtdYmVMXxtm", "980b3ed6-9db7-4704-a6bc-1e90d5745a35", "jCViDgKb", "kdpmhVUu", new Date(104,4,4), 26, "fcarqTbL", 63.48, "IuYTrwIv", "TeEQvTVc",false);
    Transaction transaction26 = new Transaction("QqeaMgsjXxtdYmVMXxtdYmVMXxtdYmVMXxtm", "4f88f943-c3b8-4b32-9136-9e7c2a2ddbfc", "JJVFTRZp", "DQxpZBPL", new Date(104,4,4), 31, "fMUjPjaS", 46.29, "ilZHmxXw", "HxwetfRS",false);
    Transaction transaction27 = new Transaction("SBFHvDvnXxtdYmVMXxtdYmVMXxtdYmVMXxtm", "4f88f943-c3b8-4b32-9136-9e7c2a2ddbfc", "BeNVmxpH", "RppCaBDI", new Date(104,4,4), 23, "WBRiKXvh", 38.11, "LFlQuDqJ", "WSsbKgDy",false);
    Transaction transaction28 = new Transaction("FqNRhRlkXxtdYmVMXxtdYmVMXxtdYmVMXxtm", "4f88f943-c3b8-4b32-9136-9e7c2a2ddbfc", "yFZmNHCd", "AtmLWekO", new Date(104,4,4), 48, "MUOJKuhv", 16.26, "fyCmAMDF", "bsstSKxb",false);
    Transaction transaction29 = new Transaction("inHhcuhVXxtdYmVMXxtdYmVMXxtdYmVMXxtm", "4f88f943-c3b8-4b32-9136-9e7c2a2ddbfc", "yFZmNHCd", "fRpAUfEV", new Date(104,4,4), 68, "HlySFegk", 30.3, "uyLTksne", "wZrSpHrm",false);
    Transaction transaction30 = new Transaction("ksNNnAZwXxtdYmVMXxtdYmVMXxtdYmVMXxtm", "4f88f943-c3b8-4b32-9136-9e7c2a2ddbfc", "yFZmNHCd", "oVCqqbNw", new Date(104,4,4), 37, "xFGaBHtP", 35.88, "IKMruqpM", "hKKsnEyP",false);
    Transaction transaction31 = new Transaction("tcbQFVKXXxtdYmVMXxtdYmVMXxtdYmVMXxtm", "4f88f943-c3b8-4b32-9136-9e7c2a2ddbfc", "yFZmNHCd", "flwLGtKf", new Date(104,4,4), 47, "IKuAlAlm", 26.51, "FQCalMRw", "yTmAoDPF",false);
    Transaction transaction32 = new Transaction("UGjhbEcXXxtdYmVMXxtdYmVMXxtdYmVMXxtm", "4f88f943-c3b8-4b32-9136-9e7c2a2ddbfc", "hUiVZscL", "sBbQihPX", new Date(104,4,4), 20, "rcTGftry", 21.19, "aXAeMecU", "OKcgUtVa",false);
    Transaction transaction33 = new Transaction("hhKFllcCXxtdYmVMXxtdYmVMXxtdYmVMXxtm", "4f88f943-c3b8-4b32-9136-9e7c2a2ddbfc", "fFXuaQUE", "sBbQihPX", new Date(104,4,4), 19, "oVyJkvhy", 50.41, "kkVXnVvu", "GZrsahnI",false);
    Transaction transaction34 = new Transaction("lhFEQTlSXxtdYmVMXxtdYmVMXxtdYmVMXxtm", "4f88f943-c3b8-4b32-9136-9e7c2a2ddbfc", "BjJplFqQ", "sBbQihPX", new Date(104,4,4), 18, "QhuGKUZx", 52.52, "vXvmuRag", "LNBeJFUL",false);
    Transaction transaction35 = new Transaction("kPhlBQapXxtdYmVMXxtdYmVMXxtdYmVMXxtm", "4f88f943-c3b8-4b32-9136-9e7c2a2ddbfc", "uiFSepoQ", "sBbQihPX", new Date(104,4,4), 43, "mVuKEsOC", 55.51, "BoJhhBJH", "tXSEjfwF",false);
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
  public int bulkUpdateCategory(String userid, String category, String query) throws SQLException {
    int result = 0 ;
    if (exceptionKey(query)){
      throw new SQLException("error");
    }
    List<Transaction_VM> results = new ArrayList<>();
    for (Transaction_VM t : transactionVMs) {

      if (t.getUser_ID().equals(userid)&&t.getDescription().contains(query)) {
        results.add(t);
      }
    }
    for (Transaction_VM t : results) {
      t.setCategory_ID(category);
      result ++;
    }

    return result;
  }

  @Override
  public List<Transaction> getTransactionForExportByUser(String userID) throws SQLException {
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
    List<Transaction> results = new ArrayList<>();
    for (int i = 0;i<3;i++){
      results.add(transactionVMs.get(i));
    }
    return results;

  }

  @Override
  public int add(Transaction _transaction) throws SQLException {
    if (duplicateKey(_transaction)){
      return 0;
    }
    if (exceptionKey(_transaction)){
      throw new SQLException("error");
    }
    int size = transactionVMs.size();
    Transaction_VM transaction_VM = new Transaction_VM(_transaction);
    transactionVMs.add(transaction_VM);
    int newsize = transactionVMs.size();
    return newsize-size;
  }

  @Override
  public int addBatch(List<Transaction> _transactions, String user_id) throws SQLException {
    int result = 0;
    for (Transaction t : _transactions){
      result +=add(t);
    }
    return result;
  }

  @Override
  public List<Transaction_VM> getTransactionByUser(String User_ID) throws SQLException {
    return getTransactionByUser(User_ID,100);
  }

  @Override
  public List<Transaction_VM> getTransactionByUser(String User_ID, int pagesize) throws SQLException {
    List<Transaction_VM> results = new ArrayList<>();
    for (Transaction_VM t : transactionVMs) {
      if (t.getUser_ID().equals(User_ID)) {
        results.add(t);
      }
    }
    return results;
  }

  @Override
  public List<Transaction_VM> getTransactionByUser(String User_ID, int pagesize, int offset) throws SQLException {
    List<Transaction_VM> results = new ArrayList<>();
    for (Transaction_VM t : transactionVMs) {
      if (t.getUser_ID().equals(User_ID)) {
        results.add(t);
      }
    }
    return results;
  }

  @Override
  public List<Transaction_VM> getTransactionByUser(String userID, String category, int year, int pagesize, int offset, String sortBy, int order) throws SQLException {
    List<Transaction_VM> results = new ArrayList<>();
    for (Transaction_VM t : transactionVMs) {

      if (t.getUser_ID().equals(userID)&&(category.equals("")||t.getCategory_ID().equals(category))&&(year==0||t.getPost_Date().getYear()==year)) {
        results.add(t);
      }
    }
    return results;
  }

  @Override
  public List<Transaction_VM> searchTransactionByUser(String userID, String query) throws SQLException {
    if (exceptionKey(query)){
      throw new SQLException("error");
    }
    List<Transaction_VM> results = new ArrayList<>();
    for (Transaction_VM t : transactionVMs) {

      if (t.getUser_ID().equals(userID)&&t.getDescription().contains(query)) {
        results.add(t);
      }
    }
    return results;
  }

  @Override
  public List<List<Category_VM>> getAnalysis(List<List<Category_VM>> years, String user_ID) throws SQLException {
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
  public int getTransactionCountByUser(String userID, String category, int year) throws SQLException {
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
      if (transactionVMs.get(i).getUser_ID().equals(oldTransaction.getUser_ID())&&transactionVMs.get(i).getTransaction_ID().equals(oldTransaction.getTransaction_ID())&&!transactionVMs.get(i).getIs_Locked()){
        location = i;
        result = 1;
        break;
      }
    }
    Transaction_VM _newTransaction = new Transaction_VM(newTransaction);
    transactionVMs.set(location, _newTransaction);
    return result;
  }

  @Override
  public int toggleLockTransaction(Transaction transaction) throws SQLException {
    int location =-1;
    for (int i=0;i<transactionVMs.size();i++){
      if (transactionVMs.get(i).getTransaction_ID().equals(transaction.getTransaction_ID())&& Objects.equals(transactionVMs.get(i).getUser_ID(), transaction.getUser_ID())){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException("Unable To Find Transaction.");
    }
    transactionVMs.get(location).setIs_Locked(!transactionVMs.get(location).getIs_Locked());
    return 1;
  }

  @Override
  public int writeTransactionToFile(List<Transaction> transactions, String path) throws IOException {
    int result = 0;
    File file = new File(path);
    file.getParentFile().mkdirs(); // This creates the folders if they are missing
    try (PrintWriter writer = new PrintWriter(file, StandardCharsets.UTF_8)) {
      writer.println("Transaction_ID\tUser_ID\tCategory_ID\tBank_Account_ID\tPost_Date\tCheck_No\tDescription\tAmount\tType\tStatus\tIs_Locked");
      for (Transaction _transaction : transactions) {

        writer.print(_transaction.getTransaction_ID());
        writer.print("\t" + _transaction.getUser_ID());
        writer.print("\t" + _transaction.getCategory_ID());
        writer.print("\t" + _transaction.getBank_Account_ID());
        writer.print("\t" + _transaction.getPost_Date());
        writer.print("\t" + _transaction.getCheck_No());
        writer.print("\t" + _transaction.getDescription());
        writer.print("\t" + _transaction.getAmount());
        writer.print("\t" + _transaction.getType());
        writer.print("\t" + _transaction.getStatus());
        writer.print("\t" + _transaction.getIs_Locked());
        writer.print("\n");

        result++;
      }
    }


    return result;
  }

  @Override
  public List<List<Category_VM>> getMonthlyAnalysis(List<List<Category_VM>> months, String user_ID, int year) throws SQLException {
    return List.of();
  }

  @Override
  public List<Transaction> getDistinctTransactionForDropdown(String user_ID) throws SQLException {
    List<Transaction> transactions = new ArrayList<>();
    for (Transaction t : transactionVMs) {
      if (t.getUser_ID()==user_ID) {
        transactions.add(t);
      }
    }
    return transactions;
  }

  private boolean duplicateKey(Transaction _transaction){

    boolean result = false;
    if (_transaction.getTransaction_ID()!=null){
      result = _transaction.getTransaction_ID().equals("DUPLICATEDUPLICATEDUPLICATEDUPLICATE");
      if (result){
        return result;
      }
    }
    if (_transaction.getCategory_ID()!=null){
      result = _transaction.getCategory_ID().equals("DUPLICATE");

    }
    return result;

  }
  private boolean exceptionKey(Transaction _transaction){
    boolean result = false;
    if (_transaction.getTransaction_ID()!=null){
      result = _transaction.getTransaction_ID().equals("EXCEPTIONEXCEPTIONEXCEPTIONEXCEPTION");
      if (result){
        return result;
      }
    }
    if (_transaction.getCategory_ID()!=null){
      result = _transaction.getCategory_ID().equals("EXCEPTION");

    }
return result;
  }

  private boolean exceptionKey(String _transaction){
    return (_transaction!=null && _transaction.equals("EXCEPTION"));
  }
}
