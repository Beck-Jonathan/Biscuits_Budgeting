package com.beck.beck_demos.budget_app.models;

import java.util.List;

public class Transaction_VM extends Transaction{
  private List<Transaction_Comment> Transaction_Comments;

  public int getComment_count() {
    return comment_count;
  }

  public void setComment_count(int comment_count) {
    this.comment_count = comment_count;
  }

  private int comment_count;
  public Transaction_VM(){}

  public Transaction_VM(Transaction transaction){
    super(transaction.getTransaction_ID(), transaction.getUser_ID(), transaction.getCategory_ID(), transaction.getBank_Account_ID(), transaction.getPost_Date(), transaction.getCheck_No(), transaction.getDescription(), transaction.getAmount(), transaction.getType(), transaction.getStatus());
  }
  public Transaction_VM(Transaction transaction,List<Transaction_Comment> transaction_comments){
    super( transaction.getTransaction_ID(),  transaction.getUser_ID(),  transaction.getCategory_ID(),  transaction.getBank_Account_ID(),  transaction.getPost_Date(),  transaction.getCheck_No(),  transaction.getDescription(),  transaction.getAmount(),  transaction.getType(),  transaction.getStatus());
    this.Transaction_Comments = transaction_comments;

  }
  public Transaction_VM(Transaction transaction,int count){
    super( transaction.getTransaction_ID(),  transaction.getUser_ID(),  transaction.getCategory_ID(),  transaction.getBank_Account_ID(),  transaction.getPost_Date(),  transaction.getCheck_No(),  transaction.getDescription(),  transaction.getAmount(),  transaction.getType(),  transaction.getStatus());
    this.comment_count = count;

  }
  public List<Transaction_Comment> getTransaction_Comments() {
    return Transaction_Comments;
  }
  public void setTransaction_Comments(List<Transaction_Comment> _transaction_comments) {
    this.Transaction_Comments = _transaction_comments;
  }
}
