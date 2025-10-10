package com.beck.beck_demos.budget_app.data_fakes;


//import com.beck.beck_demos.budget_app.data.ReceiptDAO;
import com.beck.beck_demos.budget_app.models.Receipt;
import com.beck.beck_demos.budget_app.iData.iReceiptDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class ReceiptDAO_Fake implements iReceiptDAO{
  private  List<Receipt> receipts;
  public ReceiptDAO_Fake(){
    receipts = new ArrayList<>();
    Receipt receipt0 = new Receipt("kPaFZnoBwSWFxQyrPiBWMACLTrPFStleSVwt", "DUQGCMYM", "abcsCHkX", "PeDWxpwM", "PtxZvkqA");
    Receipt receipt1 = new Receipt("uHZGAEjvbeCvEoYDrDvNTNsIvldIkadtolJo", "DUQGCMYM", "abcoDTGW", "McdOrFWn", "MMqHLJxQ");
    Receipt receipt2 = new Receipt("hiJMXpnoHAiBvfgCTVdhYwXaiPBKVManVCSE", "DUQGCMYM", "exwnfWew", "GwheVncs", "IUrixRPH");
    Receipt receipt3 = new Receipt("XfjBCRZlaAXxuNhZVgfhSIOUUWFKhgWvJHSO", "DUQGCMYM", "hAxGrpSu", "tbBkxWrn", "wvFUQqTB");
    Receipt receipt4 = new Receipt("ySKHBDJPGefxmOcSXKMjGmqEMAqeyDgftaRR", "DUQGCMYM", "AkiVCxqp", "RnlulcEU", "eaZpcPem");
    Receipt receipt5 = new Receipt("iifFiMcQpTHhkFuTDOQxKWuCrsiQJJStyhRl", "ByiarHXp", "NwaQEhXL", "xOIHTqEk", "PJfUbtAg");
    Receipt receipt6 = new Receipt("RBSWsRNdfuZIBoVIBifXfDadtsXXmIuFBplU", "ByiarHXp", "ewvBifZr", "eTUdfIWh", "WtUOoOXT");
    Receipt receipt7 = new Receipt("dnWlbdiydixnldkMiHVboZHeijAqleGlLUeG", "ByiarHXp", "QqoXaAMf", "IGOALAnO", "EOAnntrF");
    Receipt receipt8 = new Receipt("mXwAaetbtblZGxwsICBqAdbesQiCNptMVQuY", "ByiarHXp", "mFMtRGfA", "wyNjWErl", "nVUFkLLf");
    Receipt receipt9 = new Receipt("cOkniQosSjnYpeZbuGohatfNOiGUSonpGxEF", "ByiarHXp", "pQqrkJTA", "RYAkRXug", "qHkQFlxW");
    Receipt receipt10 = new Receipt("bEsIOpcTKEsEkIiAmvuBsEwYLXChEenvkqPf", "yAeAoQpi", "tkSkoTJu", "MdkpfKnL", "pdKoAOPQ");
    Receipt receipt11 = new Receipt("IECCoETnIxkwfYBigNpkDshDFZMLMdClbhlh", "yAeAoQpi", "XgZHRyFP", "smCjbbAI", "nkysbvlG");
    Receipt receipt12 = new Receipt("vbNAKSYKtrkYtPvYnrKUtYpssREavLyhOaLd", "yAeAoQpi", "HuAGecyP", "NkBrDeaT", "ZhcTyfrT");
    Receipt receipt13 = new Receipt("rlplNsRNOShsXTnxscQIOIlRrKcEDjKrEgRn", "yAeAoQpi", "QFjSSfZK", "CkohFbdN", "hAvAlDeO");
    Receipt receipt14 = new Receipt("RKXuoFrgYmVwKbOppfVnCEEphjiuhBAOWLRr", "yAeAoQpi", "uDTPdrsm", "GYAZQQlI", "TqyWIJmE");

    receipts.add(receipt0);
    receipts.add(receipt1);
    receipts.add(receipt2);
    receipts.add(receipt3);
    receipts.add(receipt4);
    receipts.add(receipt5);
    receipts.add(receipt6);
    receipts.add(receipt7);
    receipts.add(receipt8);
    receipts.add(receipt9);
    receipts.add(receipt10);
    receipts.add(receipt11);
    receipts.add(receipt12);
    receipts.add(receipt13);
    receipts.add(receipt14);
    Collections.sort(receipts);
  }
  @Override
  public int add(Receipt _receipt) throws SQLException {
    if (duplicateKey(_receipt)){
      return 0;
    }
    if (exceptionKey(_receipt)){
      throw new SQLException("error");
    }
    int size = receipts.size();

    receipts.add(_receipt);
    int newsize = receipts.size();
    return newsize-size;
  }

  @Override
  public Receipt getReceiptByPrimaryKey(Receipt _receipt) throws SQLException{
    Receipt result = null;
    for (Receipt receipt : receipts) {
      if (receipt.getReceipt_ID().equals(_receipt.getReceipt_ID())){
        result = receipt;
        break;
      }
    }
    if (result == null){
      throw new SQLException("Receipt not found");
    }
    return result;
  }
  @Override
  public List <Receipt> getAllReceipt(int limit, int offset, String search_term, String Transaction_ID, int user_id) throws SQLException {
    List<Receipt> results = new ArrayList<>();
    for (Receipt receipt : receipts){
      if (Transaction_ID.isEmpty()||receipt.getTransaction_ID().equals(Transaction_ID))
      {
        if (search_term.isEmpty() || receipt.getReceipt_ID().contains(search_term)|| receipt.getTransaction_ID().contains(search_term)|| receipt.getImage_Link().contains(search_term)|| receipt.getName().contains(search_term)|| receipt.getDescription().contains(search_term)){
                    results.add(receipt);
        }
      }
    }
    return results;
  }

  @Override
  public List<Receipt> getReceiptbyTransaction(String Transaction_ID){
    List<Receipt> results = new ArrayList<>();
    for (Receipt receipt : receipts){
      if (receipt.getTransaction_ID().equals(Transaction_ID)){
        results.add(receipt);
      }
    }
    return results;
  }
  @Override
  public int update(Receipt oldReceipt, Receipt newReceipt) throws SQLException{
    int location =-1;
    if (duplicateKey(oldReceipt)){
      return 0;
    }
    if (exceptionKey(oldReceipt)){
      throw new SQLException("error");
    }
    for (int i = 0; i< receipts.size(); i++){
      if (receipts.get(i).getReceipt_ID().equals(oldReceipt.getReceipt_ID())){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }

    receipts.set(location,newReceipt);
    return 1;
  }
  @Override
  public int deleteReceipt(String Receipt_ID) throws SQLException{
    int size = receipts.size();
    int location =-1;
    for (int i = 0; i< receipts.size(); i++){
      if (receipts.get(i).getReceipt_ID().equals(Receipt_ID)){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException("Unable To Find Receipt.");
    }
    receipts.remove(location);
    int newsize = receipts.size();
    return size-newsize;
  }

  @Override
  public int getReceiptCount(String searchTerm, String transactionId, int user_id) {
    List<Receipt> results = new ArrayList<>();
    for (Receipt receipt : receipts){
      if ((receipt.getTransaction_ID()!=null||receipt.getTransaction_ID().equals(transactionId))
      ){
        if (searchTerm.isEmpty() || receipt.getReceipt_ID().contains(searchTerm)|| receipt.getTransaction_ID().contains(searchTerm)|| receipt.getImage_Link().contains(searchTerm)|| receipt.getName().contains(searchTerm)|| receipt.getDescription().contains(searchTerm)){
          results.add(receipt);
        }
      }
    }
    return results.size();
  }
  int writeReceiptToFile(List<Receipt> _Receipts, String path) throws IOException{
    return _Receipts.size();
  }

  private boolean duplicateKey(Receipt _receipt){
    return _receipt.getTransaction_ID().contains("DUPLICATE");
  }
  private boolean exceptionKey(Receipt _receipt){
    return _receipt.getTransaction_ID().contains("EXCEPTION");
  }

}

