package com.beck.beck_demos.budget_app.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class Bank_AccountTest {
  private Bank_Account _bank_account;
  @BeforeEach
  public void setup(){
    _bank_account = new Bank_Account();
  }
  @AfterEach
  public void teardown(){
    _bank_account = null;
  }
  @Test
  public void testBank_AccountDefaultConstructorSetsNoVariables(){
    Bank_Account _bank_account= new Bank_Account();
    Assertions.assertNull(_bank_account.getBank_Account_ID());
    Assertions.assertNull(_bank_account.getUser_ID());
    Assertions.assertNull(_bank_account.getAccount_Nickname());
    Assertions.assertNull(_bank_account.getBalance());
    Assertions.assertNull(_bank_account.getBalance_Date());
  }
  @Test
  public void testBank_AccountParameterizedConstructorSetsAllVariables() throws ParseException {
    String bankAccountID = "VpCMGxsGTTXxOISJHwFRiDGJZenGfykNEgWBdoenSDvjyUTpDQSJyoZXgXMBYvHHMfbJZZPQdJeuJmUNCoVIPuVCiitaUwLVaD";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date date = (Date) sdf.parse("2024-11-11");
    Bank_Account _bank_account= new Bank_Account(
        bankAccountID,
        "fec75744-130e-4bcb-8bbe-9bee18080428",
        "aoVyLNnmRreFiFilRMQPZjsUjOMYbigfMZVwCHrPXqunrPOYDUmXBUeFqQTlPuYCrPWUKJWQCsxryxANvPeqnPFbaueayVoWxC",
        71d,
        date

    );
    char[] censored_id = bankAccountID.toCharArray();
    for (int i =0; i<censored_id.length-4;i++){
      censored_id[i]='x';
    }
    String _censored_id = new String(censored_id);
    Assertions.assertEquals(_censored_id,_bank_account.getBank_Account_ID());
    Assertions.assertEquals("fec75744-130e-4bcb-8bbe-9bee18080428",_bank_account.getUser_ID());
    Assertions.assertEquals("aoVyLNnmRreFiFilRMQPZjsUjOMYbigfMZVwCHrPXqunrPOYDUmXBUeFqQTlPuYCrPWUKJWQCsxryxANvPeqnPFbaueayVoWxC",_bank_account.getAccount_Nickname());
    Assertions.assertEquals(71,_bank_account.getBalance());
    Assertions.assertEquals(date,_bank_account.getBalance_Date());

  }
  @Test
  public void testBank_AccountKeyedParameterizedConstructorSetsKeyedVariables(){
    String id = "KcAkSkHQeSTQCyIWBGOebpUWSyKCcywlCTPtDIotDObTAMYVuZBJVuXIGbuSVyesXxZkamCoxwdNCRSFceyiRHuLoUTvrFvAmb";
    Bank_Account _bank_account= new Bank_Account(
        id
    );
    char[] censored_id = id.toCharArray();
    for (int i =0; i<censored_id.length-4;i++){
      censored_id[i]='x';
    }
    String _censored_id = new String(censored_id);


    Assertions.assertEquals(_censored_id,_bank_account.getBank_Account_ID());
    Assertions.assertNull(_bank_account.getUser_ID());
    Assertions.assertNull(_bank_account.getAccount_Nickname());
    Assertions.assertNull(_bank_account.getBalance());
    Assertions.assertNull(_bank_account.getBalance_Date());
  }
  @Test
  public void  testBank_AccountThrowsIllegalArgumentExceptionIfBank_Account_IDTooShort(){
    String Bank_Account_ID = "cs";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_bank_account.setBank_Account_ID(Bank_Account_ID);});
  }
  @Test
  public void  testBank_AccountThrowsIllegalArgumentExceptionIfBank_Account_IDTooLong(){
    String Bank_Account_ID = "cJIxhpTMSmxsmykZWfaNQddgUWFRjptjBpSZErbCCwySwPTGvCfRRSZNukQDJkJKHwaieOaDjVEICViarEiPBBGedgQPsoanEMJUSA";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_bank_account.setBank_Account_ID(Bank_Account_ID);});
  }
  @Test
  public void testSetBank_Account_IDSetsBank_Account_ID(){
    String Bank_Account_ID = "DybwUFsVmjLFBsJFpvFxxAnpaTxJLDfQZJPnumhittGSYNCExgkVrkuLMfCUbdfgfbKDNOHqwZDCNmRAaSshmXlriaBCgKSQfl";
    char[] censored_id = Bank_Account_ID.toCharArray();
    for (int i =0; i<censored_id.length-4;i++){
      censored_id[i]='x';
    }
    String _censored_id = new String(censored_id);

    _bank_account.setBank_Account_ID(Bank_Account_ID);
    Assertions.assertEquals(_censored_id,_bank_account.getBank_Account_ID());
  }
  @Test
  public void testBank_AccountThrowsIllegalArgumentExceptionIfUser_IDTooSmall(){
    String User_ID = "";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_bank_account.setUser_ID(User_ID);});
  }
  @Test
  public void testBank_AccountThrowsIllegalArgumentExceptionIfUser_IDTooBig(){
    String User_ID = "fec75744-130e-4bcb-8bbe-9bee18080428sss";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_bank_account.setUser_ID(User_ID);});
  }
  @Test
  public void testBank_AccountSetsUser_ID(){
    String User_ID = "fec75744-130e-4bcb-8bbe-9bee18080428";
    _bank_account.setUser_ID(User_ID);
    Assertions.assertEquals(User_ID, _bank_account.getUser_ID());
  }
  @Test
  public void  testBank_AccountThrowsIllegalArgumentExceptionIfAccount_NicknameTooShort(){
    String Account_Nickname = "rB";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_bank_account.setAccount_Nickname(Account_Nickname);});
  }
  @Test
  public void  testBank_AccountThrowsIllegalArgumentExceptionIfAccount_NicknameTooLong(){
    String Account_Nickname = "kXduRqJykTBnPkxsNoRUfNEKlGmbaAfIaDNZBaPheWClpagEKTIKEaXkhsNhPtfkVPoFwWVEMFopasPtUOvxJykIUPaGmZQqyNInlG";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_bank_account.setAccount_Nickname(Account_Nickname);});
  }
  @Test
  public void testSetAccount_NicknameSetsAccount_Nickname(){
    String Account_Nickname = "qcOrRULyVkWGTpvXWnMiwhiVQBCVFbHFQHYnJQLUWSwBDpkAWtvtBSgvyfnyNTVGPUYfSTRCTAZMeECjKekdXTLhbTihgifGpN";
    _bank_account.setAccount_Nickname(Account_Nickname);
    Assertions.assertEquals(Account_Nickname,_bank_account.getAccount_Nickname());
  }
  @Test
  public void testBank_AccountThrowsIllegalArgumentExceptionIfBalanceTooSmall(){
    double Balance = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_bank_account.setBalance(Balance);});
  }
  @Test
  public void testBank_AccountThrowsIllegalArgumentExceptionIfBalanceTooBig(){
    double Balance = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_bank_account.setBalance(Balance);});
  }
  @Test
  public void testBank_AccountSetsBalance(){
    double Balance = 6182;
    _bank_account.setBalance(Balance);
    Assertions.assertEquals(Balance, _bank_account.getBalance());
  }
  @Test
  public void testCompareToCanCompareForEachDateField() throws ParseException {
    Bank_Account smaller = new Bank_Account();
    Bank_Account bigger = new Bank_Account();
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//to compare a smaller and larger Bank_Account_ID
    smaller.setBank_Account_ID("aaaa");
    bigger.setBank_Account_ID("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Bank_Account_ID as equal.
    smaller.setBank_Account_ID("bbbb");
//to compare a smaller and larger User_ID
    smaller.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    bigger.setUser_ID("gec75744-130e-4bcb-8bbe-9bee18080428");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the User_ID as equal.
    smaller.setUser_ID("gec75744-130e-4bcb-8bbe-9bee18080428");
//to compare a smaller and larger Account_Nickname
    smaller.setAccount_Nickname("aaaa");
    bigger.setAccount_Nickname("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Account_Nickname as equal.
    smaller.setAccount_Nickname("bbbb");
//to compare a smaller and larger Balance
    smaller.setBalance(10.23d);
    bigger.setBalance(14.12d);
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Balance as equal.
    smaller.setBalance(14.12d);
//to compare a smaller and larger Balance_Date
    smaller.setBalance_Date(df.parse("01/01/2023"));
    bigger.setBalance_Date(df.parse("01/01/2024"));
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Balance_Date as equal.
    smaller.setBalance_Date(df.parse("01/01/2024"));
    Assertions.assertTrue(bigger.compareTo(smaller)==0);
  }

}

