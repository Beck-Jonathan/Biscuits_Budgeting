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
    Transaction_Comment transaction_comment0 = new Transaction_Comment("f503f3ba-3375-40d4-8a0f-5c5aee018c6b", "lwIFwDnklwIFwDnklwIFwDnklwIFwDnk5212", 20, "bCPNUtrg", new Date());
    Transaction_Comment transaction_comment1 = new Transaction_Comment("f503f3ba-3375-40d4-8a0f-5c5aee018c6b", "msyUAPuJlwIFwDnklwIFwDnklwIFwDnk5212",  44, "EboaYbXL", new Date());
    Transaction_Comment transaction_comment2 = new Transaction_Comment("f503f3ba-3375-40d4-8a0f-5c5aee018c6b", "IjgIesVPlwIFwDnklwIFwDnklwIFwDnk5212", 39, "ZgMBgVTe", new Date());
    Transaction_Comment transaction_comment3 = new Transaction_Comment("f503f3ba-3375-40d4-8a0f-5c5aee018c6b", "xwvdJsWvlwIFwDnklwIFwDnklwIFwDnk5212", 29, "SpNaPSbh", new Date());
    Transaction_Comment transaction_comment4 = new Transaction_Comment("f503f3ba-3375-40d4-8a0f-5c5aee018c6b", "xwvdJsWvlwIFwDnklwIFwDnklwIFwDnk5212", 42, "ZJiiSoKT", new Date());
    Transaction_Comment transaction_comment5 = new Transaction_Comment("f503f3ba-3375-40d4-8a0f-5c5aee018c6b", "xwvdJsWvlwIFwDnklwIFwDnklwIFwDnk5212", 53, "aoapPNSu", new Date());
    Transaction_Comment transaction_comment6 = new Transaction_Comment("f503f3ba-3375-40d4-8a0f-5c5aee018c6b", "xwvdJsWvlwIFwDnklwIFwDnklwIFwDnk5212", 55, "FkKBHKcJ", new Date());
    Transaction_Comment transaction_comment7 = new Transaction_Comment("f503f3ba-3375-40d4-8a0f-5c5aee018c6b", "xwvdJsWvlwIFwDnklwIFwDnklwIFwDnk5212", 64, "MUSUJRLT", new Date());
    Transaction_Comment transaction_comment8 = new Transaction_Comment("f503f3ba-3375-40d4-8a0f-5c5aee018c6b", "xwvdJsWvlwIFwDnklwIFwDnklwIFwDnk5212", 55, "AfjArUKE", new Date());
    Transaction_Comment transaction_comment9 = new Transaction_Comment("e66de846-26a2-4488-b22b-da0c261f3659", "xwvdJsWvlwIFwDnklwIFwDnklwIFwDnk5212", 29, "XcMaeJLD", new Date());
    Transaction_Comment transaction_comment10 = new Transaction_Comment("e66de846-26a2-4488-b22b-da0c261f3659", "xwvdJsWvlwIFwDnklwIFwDnklwIFwDnk5212", 39, "uTlHswJc", new Date());
    Transaction_Comment transaction_comment11 = new Transaction_Comment("e66de846-26a2-4488-b22b-da0c261f3659", "PStXerhMlwIFwDnklwIFwDnklwIFwDnk5212", 34, "TjkQHSOx", new Date());
    Transaction_Comment transaction_comment12 = new Transaction_Comment("e66de846-26a2-4488-b22b-da0c261f3659", "PStXerhMlwIFwDnklwIFwDnklwIFwDnk5212", 26, "twtFmuyG", new Date());
    Transaction_Comment transaction_comment13 = new Transaction_Comment("e66de846-26a2-4488-b22b-da0c261f3659", "PStXerhMlwIFwDnklwIFwDnklwIFwDnk5212", 68, "KPFSPNjN", new Date());
    Transaction_Comment transaction_comment14 = new Transaction_Comment("e66de846-26a2-4488-b22b-da0c261f3659", "PStXerhMlwIFwDnklwIFwDnklwIFwDnk5212", 11, "WyOpGsDk", new Date());
    Transaction_Comment transaction_comment15 = new Transaction_Comment("e66de846-26a2-4488-b22b-da0c261f3659", "PStXerhMlwIFwDnklwIFwDnklwIFwDnk5212", 38, "eZrCFBKm", new Date());
    Transaction_Comment transaction_comment16 = new Transaction_Comment("e66de846-26a2-4488-b22b-da0c261f3659", "yWXVTWlmlwIFwDnklwIFwDnklwIFwDnk5212", 19, "ZeLAtbmk", new Date());
    Transaction_Comment transaction_comment17 = new Transaction_Comment("e66de846-26a2-4488-b22b-da0c261f3659", "yWXVTWlmlwIFwDnklwIFwDnklwIFwDnk5212", 25, "wfcwoFCW", new Date());
    Transaction_Comment transaction_comment18 = new Transaction_Comment("e66de846-26a2-4488-b22b-da0c261f3659", "yWXVTWlmlwIFwDnklwIFwDnklwIFwDnk5212", 46, "nslUhVLj", new Date());
    Transaction_Comment transaction_comment19 = new Transaction_Comment("e66de846-26a2-4488-b22b-da0c261f3659", "yWXVTWlmlwIFwDnklwIFwDnklwIFwDnk5212", 47, "FCKtrJIT", new Date());
    Transaction_Comment transaction_comment20 = new Transaction_Comment("e66de846-26a2-4488-b22b-da0c261f3659", "yWXVTWlmlwIFwDnklwIFwDnklwIFwDnk5212", 32, "wMYIMEVb", new Date());
    Transaction_Comment transaction_comment21 = new Transaction_Comment("af735dfc-22a9-4214-a8e5-fb8de2305700", "yWXVTWlmlwIFwDnklwIFwDnklwIFwDnk5212", 49, "KSOtWUkr", new Date());
    Transaction_Comment transaction_comment22 = new Transaction_Comment("af735dfc-22a9-4214-a8e5-fb8de2305700", "yWXVTWlmlwIFwDnklwIFwDnklwIFwDnk5212", 43, "ltMrOmWF", new Date());
    Transaction_Comment transaction_comment23 = new Transaction_Comment("af735dfc-22a9-4214-a8e5-fb8de2305700", "yWXVTWlmlwIFwDnklwIFwDnklwIFwDnk5212", 23, "IONxfDOw", new Date());
    Transaction_Comment transaction_comment24 = new Transaction_Comment("af735dfc-22a9-4214-a8e5-fb8de2305700", "yWXVTWlmlwIFwDnklwIFwDnklwIFwDnk5212", 33, "AbdeNvQB", new Date());
    Transaction_Comment transaction_comment25 = new Transaction_Comment("af735dfc-22a9-4214-a8e5-fb8de2305700", "yWXVTWlmlwIFwDnklwIFwDnklwIFwDnk5212", 18, "fsVSrbGE", new Date());
    Transaction_Comment transaction_comment26 = new Transaction_Comment("af735dfc-22a9-4214-a8e5-fb8de2305700", "yWXVTWlmlwIFwDnklwIFwDnklwIFwDnk5212", 25, "FVBVeJai", new Date());
    Transaction_Comment transaction_comment27 = new Transaction_Comment("af735dfc-22a9-4214-a8e5-fb8de2305700", "yWXVTWlmlwIFwDnklwIFwDnklwIFwDnk5212", 47, "ZBIlRbNB", new Date());
    Transaction_Comment transaction_comment28 = new Transaction_Comment("af735dfc-22a9-4214-a8e5-fb8de2305700", "yWXVTWlmlwIFwDnklwIFwDnklwIFwDnk5212", 39, "UqEUqfmH", new Date());
    Transaction_Comment transaction_comment29 = new Transaction_Comment("af735dfc-22a9-4214-a8e5-fb8de2305700", "yWXVTWlmlwIFwDnklwIFwDnklwIFwDnk5212", 55, "HuLcObmj", new Date());
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
    transaction_comments.add(transaction_comment10);
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
