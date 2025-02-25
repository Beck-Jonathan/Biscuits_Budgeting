package com.beck.beck_demos.budget_app.data_fakes;

import com.beck.beck_demos.budget_app.iData.iTransaction_CommentDAO;
import com.beck.beck_demos.budget_app.models.Transaction_Comment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Transaction_CommentDAO_Fake implements iTransaction_CommentDAO {
  private  List<Transaction_Comment> transaction_comments;
  public Transaction_CommentDAO_Fake() {
    transaction_comments = new ArrayList<>();
    Transaction_Comment transaction_comment0 = new Transaction_Comment(54, 55, 20, "bCPNUtrg", new Date());
    Transaction_Comment transaction_comment1 = new Transaction_Comment(54, 43, 44, "EboaYbXL", new Date());
    Transaction_Comment transaction_comment2 = new Transaction_Comment(54, 61, 39, "ZgMBgVTe", new Date());
    Transaction_Comment transaction_comment3 = new Transaction_Comment(54, 37, 29, "SpNaPSbh", new Date());
    Transaction_Comment transaction_comment4 = new Transaction_Comment(54, 34, 42, "ZJiiSoKT", new Date());
    Transaction_Comment transaction_comment5 = new Transaction_Comment(68, 25, 53, "aoapPNSu", new Date());
    Transaction_Comment transaction_comment6 = new Transaction_Comment(17, 25, 55, "FkKBHKcJ", new Date());
    Transaction_Comment transaction_comment7 = new Transaction_Comment(14, 25, 64, "MUSUJRLT", new Date());
    Transaction_Comment transaction_comment8 = new Transaction_Comment(55, 25, 55, "AfjArUKE", new Date());
    Transaction_Comment transaction_comment9 = new Transaction_Comment(47, 25, 29, "XcMaeJLD", new Date());
    Transaction_Comment transaction_comment10 = new Transaction_Comment(32, 43, 39, "uTlHswJc", new Date());
    Transaction_Comment transaction_comment11 = new Transaction_Comment(32, 65, 34, "TjkQHSOx", new Date());
    Transaction_Comment transaction_comment12 = new Transaction_Comment(32, 10, 26, "twtFmuyG", new Date());
    Transaction_Comment transaction_comment13 = new Transaction_Comment(32, 18, 68, "KPFSPNjN", new Date());
    Transaction_Comment transaction_comment14 = new Transaction_Comment(32, 64, 11, "WyOpGsDk", new Date());
    Transaction_Comment transaction_comment15 = new Transaction_Comment(44, 17, 38, "eZrCFBKm", new Date());
    Transaction_Comment transaction_comment16 = new Transaction_Comment(36, 17, 19, "ZeLAtbmk", new Date());
    Transaction_Comment transaction_comment17 = new Transaction_Comment(15, 17, 25, "wfcwoFCW", new Date());
    Transaction_Comment transaction_comment18 = new Transaction_Comment(61, 17, 46, "nslUhVLj", new Date());
    Transaction_Comment transaction_comment19 = new Transaction_Comment(41, 17, 47, "FCKtrJIT", new Date());
    Transaction_Comment transaction_comment20 = new Transaction_Comment(60, 33, 32, "wMYIMEVb", new Date());
    Transaction_Comment transaction_comment21 = new Transaction_Comment(60, 43, 49, "KSOtWUkr", new Date());
    Transaction_Comment transaction_comment22 = new Transaction_Comment(60, 34, 43, "ltMrOmWF", new Date());
    Transaction_Comment transaction_comment23 = new Transaction_Comment(60, 65, 23, "IONxfDOw", new Date());
    Transaction_Comment transaction_comment24 = new Transaction_Comment(60, 42, 33, "AbdeNvQB", new Date());
    Transaction_Comment transaction_comment25 = new Transaction_Comment(14, 47, 18, "fsVSrbGE", new Date());
    Transaction_Comment transaction_comment26 = new Transaction_Comment(56, 47, 25, "FVBVeJai", new Date());
    Transaction_Comment transaction_comment27 = new Transaction_Comment(51, 47, 47, "ZBIlRbNB", new Date());
    Transaction_Comment transaction_comment28 = new Transaction_Comment(43, 47, 39, "UqEUqfmH", new Date());
    Transaction_Comment transaction_comment29 = new Transaction_Comment(62, 47, 55, "HuLcObmj", new Date());
    transaction_comments.add(transaction_comment0);
    transaction_comments.add(transaction_comment1);
    transaction_comments.add(transaction_comment2);
    transaction_comments.add(transaction_comment3);
    transaction_comments.add(transaction_comment4);
    transaction_comments.add(transaction_comment5);
    transaction_comments.add(transaction_comment6);
    transaction_comments.add(transaction_comment7);
    transaction_comments.add(transaction_comment8);
    transaction_comments.add(transaction_comment9);
    transaction_comments.add(transaction_comment0);
    transaction_comments.add(transaction_comment11);
    transaction_comments.add(transaction_comment12);
    transaction_comments.add(transaction_comment13);
    transaction_comments.add(transaction_comment14);
    transaction_comments.add(transaction_comment15);
    transaction_comments.add(transaction_comment16);
    transaction_comments.add(transaction_comment17);
    transaction_comments.add(transaction_comment18);
    transaction_comments.add(transaction_comment19);
    transaction_comments.add(transaction_comment20);
    transaction_comments.add(transaction_comment21);
    transaction_comments.add(transaction_comment22);
    transaction_comments.add(transaction_comment23);
    transaction_comments.add(transaction_comment24);
    transaction_comments.add(transaction_comment25);
    transaction_comments.add(transaction_comment26);
    transaction_comments.add(transaction_comment27);
    transaction_comments.add(transaction_comment28);
    transaction_comments.add(transaction_comment29);
    Collections.sort(transaction_comments);
  }
  @Override
  public int add(Transaction_Comment _transaction_comment) throws SQLException {
    if (duplicateKey(_transaction_comment)){
      return 0;
    }
    if (exceptionKey(_transaction_comment)){
      throw new SQLException("error");
    }
    int size = transaction_comments.size();
    transaction_comments.add(_transaction_comment);
    int newsize = transaction_comments.size();
    return newsize-size;
  }

  @Override
  public List<Transaction_Comment> getTransaction_CommentbyTransaction(Integer Transaction_ID) throws SQLException {
    List<Transaction_Comment> results = new ArrayList<>();
    for (Transaction_Comment transaction_comment : transaction_comments){
      if (transaction_comment.getTransaction_ID().equals(Transaction_ID)){
        results.add(transaction_comment);
      }
    }
    return results;
  }


  @Override
  public int update(Transaction_Comment oldTransaction_Comment, Transaction_Comment newTransaction_Comment) throws SQLException {
    int location =-1;
    if (duplicateKey(oldTransaction_Comment)){
      return 0;
    }
    if (exceptionKey(oldTransaction_Comment)){
      throw new SQLException("error");
    }
    for (int i=0;i<transaction_comments.size();i++){
      if (transaction_comments.get(i).getUser_ID().equals(oldTransaction_Comment.getUser_ID())
&&transaction_comments.get(i).getTransaction_ID().equals(oldTransaction_Comment.getTransaction_ID())
&&transaction_comments.get(i).getTransaction_Comment_ID().equals(oldTransaction_Comment.getTransaction_Comment_ID())){
            location =i;
            break;
          }
        }
        if (location==-1){
          throw new SQLException();
        }

        transaction_comments.set(location,newTransaction_Comment);
        return 1;
      }



  @Override
  public int deleteTransaction_Comment(Transaction_Comment _transaction_comment) throws SQLException {
    int size = transaction_comments.size();
    int location =-1;
    for (int i=0;i<transaction_comments.size();i++){
      if (transaction_comments.get(i).getUser_ID().equals(_transaction_comment.getUser_ID())&&transaction_comments.get(i).getTransaction_ID().equals(_transaction_comment.getTransaction_ID())&&transaction_comments.get(i).getTransaction_Comment_ID().equals(_transaction_comment.getTransaction_Comment_ID())){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }
    transaction_comments.remove(location);
    int newsize = transaction_comments.size();
    return size-newsize;
  }

  private boolean duplicateKey(Transaction_Comment _transaction_comment){
    return _transaction_comment.getContent().equals("DUPLICATE");
  }
  private boolean exceptionKey(Transaction_Comment _transaction_comment){
    return _transaction_comment.getContent().equals("EXCEPTION");
  }
}
