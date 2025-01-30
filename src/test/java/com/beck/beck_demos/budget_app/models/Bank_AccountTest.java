package com.beck.beck_demos.budget_app.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
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
  public void testBank_AccountParameterizedConstructorSetsAllVariables(){
    Bank_Account _bank_account= new Bank_Account(
        "VpCMGxsGTTXxOISJHwFRiDGJZenGfykNEgWBdoenSDvjyUTpDQSJyoZXgXMBYvHHMfbJZZPQdJeuJmUNCoVIPuVCiitaUwLVaD",
        6778,
        "aoVyLNnmRreFiFilRMQPZjsUjOMYbigfMZVwCHrPXqunrPOYDUmXBUeFqQTlPuYCrPWUKJWQCsxryxANvPeqnPFbaueayVoWxC",
        71d,
        new Date()

    );
    Assertions.assertEquals("VpCMGxsGTTXxOISJHwFRiDGJZenGfykNEgWBdoenSDvjyUTpDQSJyoZXgXMBYvHHMfbJZZPQdJeuJmUNCoVIPuVCiitaUwLVaD",_bank_account.getBank_Account_ID());
    Assertions.assertEquals(6778,_bank_account.getUser_ID());
    Assertions.assertEquals("aoVyLNnmRreFiFilRMQPZjsUjOMYbigfMZVwCHrPXqunrPOYDUmXBUeFqQTlPuYCrPWUKJWQCsxryxANvPeqnPFbaueayVoWxC",_bank_account.getAccount_Nickname());
    Assertions.assertEquals(71,_bank_account.getBalance());
    Assertions.assertEquals(new Date(),_bank_account.getBalance_Date());
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
    int User_ID = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_bank_account.setUser_ID(User_ID);});
  }
  @Test
  public void testBank_AccountThrowsIllegalArgumentExceptionIfUser_IDTooBig(){
    int User_ID = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_bank_account.setUser_ID(User_ID);});
  }
  @Test
  public void testBank_AccountSetsUser_ID(){
    int User_ID = 6518;
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

}

