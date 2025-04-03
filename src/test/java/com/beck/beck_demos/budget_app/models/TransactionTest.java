package com.beck.beck_demos.budget_app.models;

import com.beck.beck_demos.budget_app.models.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
class TransactionTest {
  private Transaction _transaction;
  @BeforeEach
  public void setup(){
    _transaction = new Transaction();
  }
  @AfterEach
  public void teardown(){
    _transaction = null;
  }
  @Test
  public void testTransactionDefaultConstructorSetsNoVariables(){
    Transaction _transaction= new Transaction();
    Assertions.assertNull(_transaction.getTransaction_ID());
    Assertions.assertNull(_transaction.getUser_ID());
    Assertions.assertNull(_transaction.getCategory_ID());
    Assertions.assertNull(_transaction.getBank_Account_ID());
    Assertions.assertNull(_transaction.getPost_Date());
    Assertions.assertNull(_transaction.getCheck_No());
    Assertions.assertNull(_transaction.getDescription());
    Assertions.assertNull(_transaction.getAmount());
    Assertions.assertNull(_transaction.getType());
    Assertions.assertNull(_transaction.getStatus());
    Assertions.assertFalse(_transaction.getIs_Locked());
  }
  @Test
  public void testTransactionParameterizedConstructorSetsAllVariables() throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date date = (Date) sdf.parse("2024-11-11");
    String BankAccountID = "OMONHIdRyXaUEXWKPlsuMDHmKQiVqMlDUZMWrTMhaEAaUIVZdEeJlRBKyhxTbceLnRjxJnWYfnTICaYBtPklacQDXCXKVsFwZo";
    char[] censored_id = BankAccountID.toCharArray();
    for (int i =0; i<censored_id.length-4;i++){
      censored_id[i]='x';
    }
    String _censored_id = new String(censored_id);
    Transaction _transaction= new Transaction(
        "XxtdYmVM",
        1081,
        "rDfKssjDroTwjtpyewSRUKBvmtSALiIRbDOjJuTDariNWtufwgmkgCiWyajqJgBVgfGpidPjnPXFKKdnRahZUqAaYHjNnpoTXC",
        BankAccountID,
        date
        ,
        7670,
        "DYXPphMxwsHXPwYSrsdLAgHVMClhWhMnVbAuEUFSFtAtXmFaLaCkqHZJjPKtctUuRvnPFgaBNVRdRuQHBNSqfscKUDUyWADIluUKpsEJYUPCsVaPXLEkdBfOxQamZIVFmxgpyWYeviPRGBZODgqjjIJeFrxetGgmccqZYElYnukUHwqBWoOCNfUQagRnJeSYqhoecmYuoWoHAvHfqUSmDmLkGbinATilrBpGXHqrJkJPmnrUBaobgYhnFhbAb",
        42.36,
        "lnohYwKvLdfASpFvAg",
        "ygKCZIhiTkJaIkiwGZ",
        true
    );
    Assertions.assertEquals("XxtdYmVM",_transaction.getTransaction_ID());
    Assertions.assertEquals(1081,_transaction.getUser_ID());
    Assertions.assertEquals("rDfKssjDroTwjtpyewSRUKBvmtSALiIRbDOjJuTDariNWtufwgmkgCiWyajqJgBVgfGpidPjnPXFKKdnRahZUqAaYHjNnpoTXC",_transaction.getCategory_ID());
    Assertions.assertEquals(_censored_id,_transaction.getBank_Account_ID());
    Assertions.assertEquals(date,_transaction.getPost_Date());
    Assertions.assertEquals(7670,_transaction.getCheck_No());
    Assertions.assertEquals("DYXPphMxwsHXPwYSrsdLAgHVMClhWhMnVbAuEUFSFtAtXmFaLaCkqHZJjPKtctUuRvnPFgaBNVRdRuQHBNSqfscKUDUyWADIluUKpsEJYUPCsVaPXLEkdBfOxQamZIVFmxgpyWYeviPRGBZODgqjjIJeFrxetGgmccqZYElYnukUHwqBWoOCNfUQagRnJeSYqhoecmYuoWoHAvHfqUSmDmLkGbinATilrBpGXHqrJkJPmnrUBaobgYhnFhbAb",_transaction.getDescription());
    Assertions.assertEquals(42.36,_transaction.getAmount());
    Assertions.assertEquals("lnohYwKvLdfASpFvAg",_transaction.getType());
    Assertions.assertEquals("ygKCZIhiTkJaIkiwGZ",_transaction.getStatus());
    Assertions.assertTrue(_transaction.getIs_Locked());
  }
  @Test
  public void testTransactionKeyedParameterizedConstructorSetsKeyedVariables(){
    Transaction _transaction= new Transaction(
        "XxtdYmVM"
    );
    Assertions.assertEquals("XxtdYmVM",_transaction.getTransaction_ID());
    Assertions.assertNull(_transaction.getUser_ID());
    Assertions.assertNull(_transaction.getCategory_ID());
    Assertions.assertNull(_transaction.getBank_Account_ID());
    Assertions.assertNull(_transaction.getPost_Date());
    Assertions.assertNull(_transaction.getCheck_No());
    Assertions.assertNull(_transaction.getDescription());
    Assertions.assertNull(_transaction.getAmount());
    Assertions.assertNull(_transaction.getType());
    Assertions.assertNull(_transaction.getStatus());
  }
  @Test
  public void testTransactionThrowsIllegalArgumentExceptionIfTransaction_IDTooSmall(){
    String Transaction_ID = "aa";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_transaction.setTransaction_ID(Transaction_ID);});
  }
  @Test
  public void testTransactionThrowsIllegalArgumentExceptionIfTransaction_IDTooBig(){
    String Transaction_ID = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_transaction.setTransaction_ID(Transaction_ID);});
  }
  @Test
  public void testTransactionSetsTransaction_ID(){
    String Transaction_ID = "XxtdYmVMXxtdYmVMXxtdYmVMXxtdYmVMXxtd";
    _transaction.setTransaction_ID(Transaction_ID);
    Assertions.assertEquals(Transaction_ID, _transaction.getTransaction_ID());
  }
  @Test
  public void testTransactionThrowsIllegalArgumentExceptionIfUser_IDTooSmall(){
    int User_ID = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_transaction.setUser_ID(User_ID);});
  }
  @Test
  public void testTransactionThrowsIllegalArgumentExceptionIfUser_IDTooBig(){
    int User_ID = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_transaction.setUser_ID(User_ID);});
  }
  @Test
  public void testTransactionSetsUser_ID(){
    int User_ID = 846;
    _transaction.setUser_ID(User_ID);
    Assertions.assertEquals(User_ID, _transaction.getUser_ID());
  }
  @Test
  public void  testTransactionThrowsIllegalArgumentExceptionIfCategory_IDTooShort(){
    String Category_ID = "V";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_transaction.setCategory_ID(Category_ID);});
  }
  @Test
  public void  testTransactionThrowsIllegalArgumentExceptionIfCategory_IDTooLong(){
    String Category_ID = "vgGqiJhuckakVjNqLBCGqaSVxtMAobopbqeaeDAvwbaiQSsTIppSLninIltqDIVMdWCQaVYVSjihhfDSRVgvclqKCkatFPmlnwItpL";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_transaction.setCategory_ID(Category_ID);});
  }
  @Test
  public void testSetCategory_IDSetsCategory_ID(){
    String Category_ID = "kQZPMJWFDtNrZZjLVepvoWYhvtHFJXMkqWtpaFqLBtRTyMwLeOvtBaQPPyshfibDTQDjwLWneHsMrTbuaVTQoxhTOclvsgyHtm";
    _transaction.setCategory_ID(Category_ID);
    Assertions.assertEquals(Category_ID,_transaction.getCategory_ID());
  }
  @Test
  public void  testTransactionThrowsIllegalArgumentExceptionIfAccount_NumTooShort(){
    String Account_Num = "xi";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_transaction.setBank_Account_ID(Account_Num);});
  }
  @Test
  public void  testTransactionThrowsIllegalArgumentExceptionIfAccount_NumTooLong(){
    String Account_Num = "dmfjIFfdTELfwwAltyyDBmLysMhjwrksagVREyJWFYuixUymyLNdwvGGjHWieMWHLNcgYDVeglEptqhvxfLSOckPLTrmuWniSqIlJR";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_transaction.setBank_Account_ID(Account_Num);});
  }
  @Test
  public void testSetBankAccountIDSetsBankAccountID(){
    String BankAccountID = "mqyPJBQoPhkxBErbTELbiQxUChvmfriPTaNubFWeohOyPppiQOPxLfllblkEFRcbOuFdSeIgPDHfvpJYLAUUaIkATeaiVeXRTj";
    char[] censored_id = BankAccountID.toCharArray();
    for (int i =0; i<censored_id.length-4;i++){
      censored_id[i]='x';
    }
    String _censored_id = new String(censored_id);

    _transaction.setBank_Account_ID(BankAccountID);
    Assertions.assertEquals(_censored_id,_transaction.getBank_Account_ID());
  }
  @Test
  public void testTransactionThrowsIllegalArgumentExceptionIfCheck_NoTooSmall(){
    int Check_No = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_transaction.setCheck_No(Check_No);});
  }
  @Test
  public void testTransactionThrowsIllegalArgumentExceptionIfCheck_NoTooBig(){
    int Check_No = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_transaction.setCheck_No(Check_No);});
  }
  @Test
  public void testTransactionSetsCheck_No(){
    int Check_No = 2703;
    _transaction.setCheck_No(Check_No);
    Assertions.assertEquals(Check_No, _transaction.getCheck_No());
  }
  @Test
  public void  testTransactionThrowsIllegalArgumentExceptionIfDescriptionTooShort(){
    String Description = "If";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_transaction.setDescription(Description);});
  }
  @Test
  public void  testTransactionThrowsIllegalArgumentExceptionIfDescriptionTooLong(){
    String Description = "lwTCyiZFoOvsWataXndJgEYRdepSLAlZDBRbeCNpRmOpXmMqTCcPOcgsDdmchuHwrOumlEuqlfoUoXyigipnmhtlxuMdqTRSQEaxgYwqOLNdyOXGjFAAnIdInDsBhdFkIVQkCNijMcecvKcYwPDlpMnSJVZmSLuUIfwUoowmFYNAKssZDXSuvawbgnuWAhPPMOdIRQgWxnoxqassIookBmqNQyJBMSOjPfeouFPfwmIcoMCrKeLmtBRVkaNbuODNW";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_transaction.setDescription(Description);});
  }
  @Test
  public void testSetDescriptionSetsDescription(){
    String Description = "IMDyhdlHUHmFjZJBAEwJIMRbVNRmZenuSHEQUrWTmIqFCNRayDqhGNNUDjSrcXShwiYnQpwnNWphXWTOPmwCREAnfAuYHWEmIKmBjSApdffSqGJCuygMUnJvrixhQSsiLRODeBZTISJXXOFFqnWRqmZRruVSYISnhnrTektZSpmQicoMCIWuJWPKjdxpsBFdQdkLosvxBAxBIRaFWAusWbJtHWvkVgIRKpSxLVPMiLWjBJjBtBbQhEObTyAVd";
    _transaction.setDescription(Description);
    Assertions.assertEquals(Description,_transaction.getDescription());
  }
  @Test
  public void testTransactionThrowsIllegalArgumentExceptionIfAmountTooSmall(){
    double Amount = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_transaction.setAmount(Amount);});
  }
  @Test
  public void testTransactionThrowsIllegalArgumentExceptionIfAmountTooBig(){
    double Amount = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_transaction.setAmount(Amount);});
  }
  @Test
  public void testTransactionSetsAmount(){
    double Amount = 5582;
    _transaction.setAmount(Amount);
    Assertions.assertEquals(Amount, _transaction.getAmount());
  }
  @Test
  public void  testTransactionThrowsIllegalArgumentExceptionIfTypeTooShort(){
    String Type = "gW";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_transaction.setType(Type);});
  }
  @Test
  public void  testTransactionThrowsIllegalArgumentExceptionIfTypeTooLong(){
    String Type = "tSBSvLfKEmZjLvGXIsdBjQ";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_transaction.setType(Type);});
  }
  @Test
  public void testSetTypeSetsType(){
    String Type = "AdULBFvTmohrieFCRV";
    _transaction.setType(Type);
    Assertions.assertEquals(Type,_transaction.getType());
  }
  @Test
  public void  testTransactionThrowsIllegalArgumentExceptionIfStatusTooShort(){
    String Status = "he";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_transaction.setStatus(Status);});
  }
  @Test
  public void  testTransactionThrowsIllegalArgumentExceptionIfStatusTooLong(){
    String Status = "WXsbMkBDVWcjRSpLxCgVYG";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_transaction.setStatus(Status);});
  }
  @Test
  public void testSetStatusSetsStatus(){
    String Status = "YZNZbiHCfNwbcoUuQW";
    _transaction.setStatus(Status);
    Assertions.assertEquals(Status,_transaction.getStatus());
  }


  /**
   <p> Tests That the Setters for the Transaction.Is_Locked field work </p>
   */
  @Test
  public void testTransactionSetsIs_LockedasFalse(){
    boolean status = false;
    _transaction.setIs_Locked(status);
    Assertions.assertEquals(status, _transaction.getIs_Locked());
  }

  /**
   <p> Tests That the Setters for the Transaction.Is_Locked field work </p>
   */
  @Test
  public void testTransactionSetsIs_LockedasTrue(){
    boolean status = true;
    _transaction.setIs_Locked(status);
    Assertions.assertEquals(status, _transaction.getIs_Locked());
  }

  @Test
  public void testCompareToCanCompareForEachDateField()throws ParseException {
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Transaction smaller = new Transaction();
    Transaction bigger = new Transaction();
//to compare a smaller and larger Transaction_ID
    smaller.setTransaction_ID("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    bigger.setTransaction_ID("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Transaction_ID as equal.
    smaller.setTransaction_ID("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
//to compare a smaller and larger User_ID
    smaller.setUser_ID(10);
    bigger.setUser_ID(20);
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the User_ID as equal.
    smaller.setUser_ID(20);
//to compare a smaller and larger Category_ID
    smaller.setCategory_ID("aaaa");
    bigger.setCategory_ID("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Category_ID as equal.
    smaller.setCategory_ID("bbbb");
//to compare a smaller and larger Bank_Account_ID
    smaller.setBank_Account_ID("aaaa");
    bigger.setBank_Account_ID("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Bank_Account_ID as equal.
    smaller.setBank_Account_ID("bbbb");
//to compare a smaller and larger Post_Date
    smaller.setPost_Date(df.parse("01/01/2023"));
    bigger.setPost_Date(df.parse("01/01/2024"));
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Post_Date as equal.
    smaller.setPost_Date(df.parse("01/01/2024"));
//to compare a smaller and larger Check_No
    smaller.setCheck_No(10);
    bigger.setCheck_No(20);
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Check_No as equal.
    smaller.setCheck_No(20);
//to compare a smaller and larger Description
    smaller.setDescription("aaaa");
    bigger.setDescription("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Description as equal.
    smaller.setDescription("bbbb");
//to compare a smaller and larger Amount
    smaller.setAmount(10.23d);
    bigger.setAmount(14.12d);
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Amount as equal.
    smaller.setAmount(14.12d);
//to compare a smaller and larger Type
    smaller.setType("aaaa");
    bigger.setType("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Type as equal.
    smaller.setType("bbbb");
//to compare a smaller and larger Status
    smaller.setStatus("aaaa");
    bigger.setStatus("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Status as equal.
    smaller.setStatus("bbbb");
    //to compare a smaller and larger Is_Locked
    smaller.setIs_Locked(false);
    bigger.setIs_Locked(true);
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Is_Locked as equal.
    smaller.setIs_Locked(true);
    Assertions.assertTrue(bigger.compareTo(smaller)==0);
  }

}

