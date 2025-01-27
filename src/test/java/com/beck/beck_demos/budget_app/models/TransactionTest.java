package com.beck.beck_demos.budget_app.models;

import com.beck.beck_demos.budget_app.models.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
  }
  @Test
  public void testTransactionParameterizedConstructorSetsAllVariables(){
    Transaction _transaction= new Transaction(
        754,
        1081,
        "rDfKssjDroTwjtpyewSRUKBvmtSALiIRbDOjJuTDariNWtufwgmkgCiWyajqJgBVgfGpidPjnPXFKKdnRahZUqAaYHjNnpoTXC",
        "OMONHIdRyXaUEXWKPlsuMDHmKQiVqMlDUZMWrTMhaEAaUIVZdEeJlRBKyhxTbceLnRjxJnWYfnTICaYBtPklacQDXCXKVsFwZo",
        new Date()
        ,
        7670,
        "DYXPphMxwsHXPwYSrsdLAgHVMClhWhMnVbAuEUFSFtAtXmFaLaCkqHZJjPKtctUuRvnPFgaBNVRdRuQHBNSqfscKUDUyWADIluUKpsEJYUPCsVaPXLEkdBfOxQamZIVFmxgpyWYeviPRGBZODgqjjIJeFrxetGgmccqZYElYnukUHwqBWoOCNfUQagRnJeSYqhoecmYuoWoHAvHfqUSmDmLkGbinATilrBpGXHqrJkJPmnrUBaobgYhnFhbAb",
        42.36,
        "lnohYwKvLdfASpFvAg",
        "ygKCZIhiTkJaIkiwGZ"
    );
    Assertions.assertEquals(754,_transaction.getTransaction_ID());
    Assertions.assertEquals(1081,_transaction.getUser_ID());
    Assertions.assertEquals("rDfKssjDroTwjtpyewSRUKBvmtSALiIRbDOjJuTDariNWtufwgmkgCiWyajqJgBVgfGpidPjnPXFKKdnRahZUqAaYHjNnpoTXC",_transaction.getCategory_ID());
    Assertions.assertEquals("OMONHIdRyXaUEXWKPlsuMDHmKQiVqMlDUZMWrTMhaEAaUIVZdEeJlRBKyhxTbceLnRjxJnWYfnTICaYBtPklacQDXCXKVsFwZo",_transaction.getBank_Account_ID());
    Assertions.assertEquals(new Date(),_transaction.getPost_Date());
    Assertions.assertEquals(7670,_transaction.getCheck_No());
    Assertions.assertEquals("DYXPphMxwsHXPwYSrsdLAgHVMClhWhMnVbAuEUFSFtAtXmFaLaCkqHZJjPKtctUuRvnPFgaBNVRdRuQHBNSqfscKUDUyWADIluUKpsEJYUPCsVaPXLEkdBfOxQamZIVFmxgpyWYeviPRGBZODgqjjIJeFrxetGgmccqZYElYnukUHwqBWoOCNfUQagRnJeSYqhoecmYuoWoHAvHfqUSmDmLkGbinATilrBpGXHqrJkJPmnrUBaobgYhnFhbAb",_transaction.getDescription());
    Assertions.assertEquals(42.36,_transaction.getAmount());
    Assertions.assertEquals("lnohYwKvLdfASpFvAg",_transaction.getType());
    Assertions.assertEquals("ygKCZIhiTkJaIkiwGZ",_transaction.getStatus());
  }
  @Test
  public void testTransactionKeyedParameterizedConstructorSetsKeyedVariables(){
    Transaction _transaction= new Transaction(
        5235
    );
    Assertions.assertEquals(5235,_transaction.getTransaction_ID());
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
    int Transaction_ID = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_transaction.setTransaction_ID(Transaction_ID);});
  }
  @Test
  public void testTransactionThrowsIllegalArgumentExceptionIfTransaction_IDTooBig(){
    int Transaction_ID = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_transaction.setTransaction_ID(Transaction_ID);});
  }
  @Test
  public void testTransactionSetsTransaction_ID(){
    int Transaction_ID = 1698;
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
    String Category_ID = "VL";
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
  public void testSetAccount_NumSetsAccount_Num(){
    String Account_Num = "mqyPJBQoPhkxBErbTELbiQxUChvmfriPTaNubFWeohOyPppiQOPxLfllblkEFRcbOuFdSeIgPDHfvpJYLAUUaIkATeaiVeXRTj";
    _transaction.setBank_Account_ID(Account_Num);
    Assertions.assertEquals(Account_Num,_transaction.getBank_Account_ID());
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

}

