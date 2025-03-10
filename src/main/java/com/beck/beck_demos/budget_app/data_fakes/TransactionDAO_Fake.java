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
    Transaction transaction0 = new Transaction(48, 39, "VYGHLkTb", "QpdcuMNh", new Date(104,4,4), 33, "VgODYmMt", 27.39, "jxXIlhEk", "rEnyujbY");
    Transaction transaction1 = new Transaction(51, 39, "VOjBYhTG", "wGVgUyqC", new Date(103,4,4), 62, "SYitpUcb", 40.71, "ZvgdjWwV", "aiinejwU");
    Transaction transaction2 = new Transaction(21, 39, "joHsrOAN", "gUhOfpFp", new Date(102,4,4), 50, "AZkwwBJm", 53.18, "fEJrGJrm", "VEXdjchS");
    Transaction transaction3 = new Transaction(12, 39, "icxwjdNm", "mfXhQabL", new Date(101,4,4), 58, "IwSZGvBH", 36.77, "MYmIGDGh", "GZTvEyiI");
    Transaction transaction4 = new Transaction(32, 39, "vSuHXsKk", "QqmnUsOb", new Date(100,4,4), 34, "JskerTkw", 43.43, "EnEBtxaR", "pJPfqAHe");
    Transaction transaction5 = new Transaction(41, 10, "ptnICffX", "rwmsMYgP", new Date(104,4,4), 38, "SxPBFTiM", 15.37, "deXThtDl", "eYVZYrWV");
    Transaction transaction6 = new Transaction(36, 35, "ptnICffX", "TrDRqemY", new Date(104,4,4), 58, "SZansbOr", 59.61, "rBnCwofS", "pfKQQWCX");
    Transaction transaction7 = new Transaction(68, 17, "ptnICffX", "pIynhxtd", new Date(104,4,4), 41, "xSDBTUoS", 56.12, "TActccBN", "qoXkKeCJ");
    Transaction transaction8 = new Transaction(58, 25, "ptnICffX", "kqnPhdxP", new Date(104,4,4), 13, "hcfUIWsE", 67.32, "vgmhIoHX", "xKpBLSMy");
    Transaction transaction9 = new Transaction(47, 42, "ptnICffX", "wnHnoWhf", new Date(104,4,4), 26, "jREEFHLo", 60.94, "EQrHCpTA", "vDgFTXix");
    Transaction transaction10 = new Transaction(30, 43, "fXZtSupN", "PvXFKAJm", new Date(104,4,4), 37, "fvylyDgb", 45.95, "exIUFmWX", "JxwRnWbp");
    Transaction transaction11 = new Transaction(62, 43, "fuawCBab", "gUlTjSKP", new Date(104,4,4), 17, "bSaXlUPh", 69.05, "hwYgFjiv", "iXLSDxLs");
    Transaction transaction12 = new Transaction(13, 43, "epNeHYoP", "PLJLgUCs", new Date(104,4,4), 48, "NggdBVqv", 25.8, "OcLaijZZ", "TaXAroZN");
    Transaction transaction13 = new Transaction(54, 43, "MwBEfVxL", "MOfgBgPd", new Date(104,4,4), 36, "MthSMMoq", 20.82, "mdueXRNe", "uTlOMFuk");
    Transaction transaction14 = new Transaction(41, 43, "SnmxGGkQ", "DSlTYGAZ", new Date(104,4,4), 47, "jBENnIdi", 16.18, "ixgcWiCW", "nosGNtkR");
    Transaction transaction15 = new Transaction(10, 47, "neNmKiZE", "UXACSViU", new Date(104,4,4), 61, "jCEdeGuH", 66.93, "AsWRMRDj", "tIMlOYCl");
    Transaction transaction16 = new Transaction(36, 15, "neNmKiZE", "oqnrLWZl", new Date(104,4,4), 54, "adHTPaEH", 23.97, "CLhxcaIF", "dBqLBGQI");
    Transaction transaction17 = new Transaction(17, 51, "neNmKiZE", "ArZaFlnw", new Date(104,4,4), 21, "kUuWsGsK", 33.79, "eNTQGmuS", "JQTvYMKl");
    Transaction transaction18 = new Transaction(17, 38, "neNmKiZE", "ilKujPGR", new Date(104,4,4), 59, "HyWxjClI", 36.71, "wRqnsXXR", "LHyXghrC");
    Transaction transaction19 = new Transaction(35, 44, "neNmKiZE", "qXFLJsVL", new Date(104,4,4), 33, "ZfcgLqQW", 11.78, "RqeydyAy", "QCJiFabq");
    Transaction transaction20 = new Transaction(26, 54, "dyBLEqRs", "pbfWOAmL", new Date(104,4,4), 19, "tEJMkaCF", 68.55, "UGAmCPeI", "EhFoLaDi");
    Transaction transaction21 = new Transaction(28, 54, "blVPEFwC", "rGxMuHjb", new Date(104,4,4), 62, "UxUMSWin", 25.62, "xegrjMxq", "uRvScgBG");
    Transaction transaction22 = new Transaction(10, 54, "KhyKTSMC", "rUyQhENZ", new Date(104,4,4), 16, "kiiIsfaX", 49.39, "lRQiycae", "WQguxSee");
    Transaction transaction23 = new Transaction(41, 54, "ZbAOmbSd", "ugZAkafA", new Date(104,4,4), 50, "HbbwbTXB", 23.27, "tFOlBNFj", "QZMHBtSr");
    Transaction transaction24 = new Transaction(45, 54, "jqfKXtSx", "wrdXtVZS", new Date(104,4,4), 51, "MVIeMuBQ", 21.69, "mFgOEydU", "CLvnQMFs");
    Transaction transaction25 = new Transaction(55, 19, "qgiMFYEd", "HiXXPcXQ", new Date(104,4,4), 58, "EQLBYUHv", 51.41, "uXevsWIb", "avdsVWWG");
    Transaction transaction26 = new Transaction(11, 47, "qgiMFYEd", "PBbXBEDE", new Date(104,4,4), 67, "gDMiDbAn", 36.65, "wqkrWoCw", "AuDVvVtQ");
    Transaction transaction27 = new Transaction(68, 37, "qgiMFYEd", "EbPffXor", new Date(104,4,4), 16, "MKccJGBL", 41.76, "BGvkyqmh", "gNBTUYVI");
    Transaction transaction28 = new Transaction(45, 64, "qgiMFYEd", "kJHNFths", new Date(104,4,4), 56, "SqkJwOUf", 41.64, "anriHeEs", "oJNVaLPB");
    Transaction transaction29 = new Transaction(33, 57, "qgiMFYEd", "BCCGEZpv", new Date(104,4,4), 13, "DEovADUv", 27.55, "YFooEcFK", "xgNJhSdj");

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
