package com.beck.beck_demos.crrg.models;

import org.joda.time.DateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class UserTest {
  private User _user;
  @BeforeEach
  public void setup(){
    _user = new User();
  }
  @AfterEach
  public void teardown(){
    _user = null;
  }
  @Test
  public void testUserDefaultConstructorSetsNoVariables(){
    User _user= new User();
    Assertions.assertNull(_user.getUser_ID());
    Assertions.assertNull(_user.getRole_ID());
    Assertions.assertNull(_user.getFirst_Name());
    Assertions.assertNull(_user.getLast_Name());
    Assertions.assertNull(_user.getEmail());
    Assertions.assertNull(_user.getLast_Logged_In());
    Assertions.assertNull(_user.getPassword());
  }
  @Test
  public void testUserParameterizedConstructorSetsAllVariables(){
    String password= "bSFeSYDbSqAYNadCUUfnPtdqliClPDHDOORVIXMEcxepLBuGrojhbkpBoDmrVySGjxMDYSKRfLuARkIALjYmcnWaLfBpGOqsujiDZLeYCVpTARdqJPsSHKpMgIvEdEaLZwlnUaXaNKIiLOEayccUsbMvYkivuGKtXlFxlPOBZQQrdxpaUySXDxoOMcATfoseCbcTKACfMaNEwQdslmnZjmQpPvLgkpuNwBUxQkqXaYnPRWomDGEnJcXiLWQVT";
    User _user= new User(
        "VsHPwmvvlpXmosxTpbEOlhOspjKHjtohuaRANGrhjovqRXSxTRdVbhsFMbwhhsyLCmEbNwWDwYwyXmBolaDxcatBuyCvweOexO",
        "VGTNRePuGnousvRHnthMYEtATJICaVZJpNTTenMjyFGdkpDkhttlNjmuldfsSqRQPZarsyixnqpYRxohODycjBbDGmlJMWsntL",
        "ROvuWuLUtrQDMPqBHrWfULNFUdvuvtfDljfdSGWhLPhGyLkvwDipCROZXaweyKWolAwIvdNdkVBKwIngwmQnHOmhjpHfUDvKan",
        "GJMnhDWVyfqMdwriUOnmGFEAwOmewyamGdKWRjSfrnIZXMdrZrVYhbYCYHMqfQYDBqOxvDZMPrpaAEhXHnKHwKILiVWVUUvRxN",
        "UIKtQfpYGbBvMSDVAkyRAlJIZKeiElGIJCwDtreiFuhZJCRyKlyvTVAlqKjBpumgJBXwqgTcuqhPITjbbTJdofvQCXZxEEmfsP",
        new DateTime()
        ,
        password.toCharArray()
    );
    Assertions.assertEquals("VsHPwmvvlpXmosxTpbEOlhOspjKHjtohuaRANGrhjovqRXSxTRdVbhsFMbwhhsyLCmEbNwWDwYwyXmBolaDxcatBuyCvweOexO",_user.getUser_ID());
    Assertions.assertEquals("VGTNRePuGnousvRHnthMYEtATJICaVZJpNTTenMjyFGdkpDkhttlNjmuldfsSqRQPZarsyixnqpYRxohODycjBbDGmlJMWsntL",_user.getRole_ID());
    Assertions.assertEquals("ROvuWuLUtrQDMPqBHrWfULNFUdvuvtfDljfdSGWhLPhGyLkvwDipCROZXaweyKWolAwIvdNdkVBKwIngwmQnHOmhjpHfUDvKan",_user.getFirst_Name());
    Assertions.assertEquals("GJMnhDWVyfqMdwriUOnmGFEAwOmewyamGdKWRjSfrnIZXMdrZrVYhbYCYHMqfQYDBqOxvDZMPrpaAEhXHnKHwKILiVWVUUvRxN",_user.getLast_Name());
    Assertions.assertEquals("UIKtQfpYGbBvMSDVAkyRAlJIZKeiElGIJCwDtreiFuhZJCRyKlyvTVAlqKjBpumgJBXwqgTcuqhPITjbbTJdofvQCXZxEEmfsP",_user.getEmail());
    Assertions.assertTrue((_user.getLast_Logged_In().getMillis()-new DateTime().getMillis())<1000);
    Assertions.assertArrayEquals(password.toCharArray(),_user.getPassword());
  }
  @Test
  public void  testUserThrowsIllegalArgumentExceptionIfUser_IDTooShort(){
    String User_ID = "dB";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_user.setUser_ID(User_ID);});
  }
  @Test
  public void  testUserThrowsIllegalArgumentExceptionIfUser_IDTooLong(){
    String User_ID = "CgmoeWrQyBrHXdelUYmXwPFTaBAFJNOjROPcOuXQSXhgIxvirgcsFaXEUqLonPIoIUBZBFbYpXDEVQafpLhkLmpCYGcsWUToahUMbW";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_user.setUser_ID(User_ID);});
  }
  @Test
  public void testSetUser_IDSetsUser_ID(){
    String User_ID = "yCvyefRmpNdRccoicxDyvhBENguTDZMeoMIJkfQEfmDjUxsfcdSyGOxKbDOIbsrWombaYMWiqBNXUrnPjZGMaOxbhWHmNKSKQE";
    _user.setUser_ID(User_ID);
    Assertions.assertEquals(User_ID,_user.getUser_ID());
  }
  @Test
  public void  testUserThrowsIllegalArgumentExceptionIfRole_IDTooShort(){
    String Role_ID = "JW";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_user.setRole_ID(Role_ID);});
  }
  @Test
  public void  testUserThrowsIllegalArgumentExceptionIfRole_IDTooLong(){
    String Role_ID = "uQONjmNKjTSIGkJPMTcqAaVNAMBSUKaDKvPSKTgmJdGZJPwwSquisyjMrVpHbDNAsoXCSmLcslqDWhNLOMWTmeHeRwBCDtvPEHEooc";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_user.setRole_ID(Role_ID);});
  }
  @Test
  public void testSetRole_IDSetsRole_ID(){
    String Role_ID = "ugVqnUnxQYTqQAcBmVXBpJaorunSXdYWfQiwvqmpSgciMcufGRZWaFoCuYAfrntZdjtbFvbaiJarbOAHnRSyyELLItuTpfnnnl";
    _user.setRole_ID(Role_ID);
    Assertions.assertEquals(Role_ID,_user.getRole_ID());
  }
  @Test
  public void  testUserThrowsIllegalArgumentExceptionIfFirst_NameTooShort(){
    String First_Name = "xL";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_user.setFirst_Name(First_Name);});
  }
  @Test
  public void  testUserThrowsIllegalArgumentExceptionIfFirst_NameTooLong(){
    String First_Name = "lvBUqpruvwZfYrquZhYKAOkCUmDpPoVrQwHJLPPLZPuwnOSrRZgDpUcEpaJscVcyPmQSOYNkkSmNGKpHSUZcFViGRqsRoxnFBGYdOF";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_user.setFirst_Name(First_Name);});
  }
  @Test
  public void testSetFirst_NameSetsFirst_Name(){
    String First_Name = "cPQbZhiEJdotOLwSvsqARYHfFjihFUyPnmOXclUqRKIspoIsRSLlOyJRCcanlZuxXJQtSHJAOylOgbpWmJNAndvUAKEqeixrXx";
    _user.setFirst_Name(First_Name);
    Assertions.assertEquals(First_Name,_user.getFirst_Name());
  }
  @Test
  public void  testUserThrowsIllegalArgumentExceptionIfLast_NameTooShort(){
    String Last_Name = "cW";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_user.setLast_Name(Last_Name);});
  }
  @Test
  public void  testUserThrowsIllegalArgumentExceptionIfLast_NameTooLong(){
    String Last_Name = "yEOExtSQTtMvEkQgxcTSvvFIcQmWPcMymFxvpidEfQTImtXRtoYOqfHSypRCveciExLiBmqjgfcvVirZKwHGwfvyXaajfjZhuMbrCb";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_user.setLast_Name(Last_Name);});
  }
  @Test
  public void testSetLast_NameSetsLast_Name(){
    String Last_Name = "WyiDZbKBYKYkwqNDFmYItwyshmfseXcYkHvsoWQQijExmOIVCoaloInmpSMjkGLNdlUgbmwigTrIZCOEyGSmArWhZVUMYaBdHV";
    _user.setLast_Name(Last_Name);
    Assertions.assertEquals(Last_Name,_user.getLast_Name());
  }
  @Test
  public void  testUserThrowsIllegalArgumentExceptionIfEmailTooShort(){
    String Email = "xN@x.com";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_user.setEmail(Email);});
  }
  @Test
  public void  testUserThrowsIllegalArgumentExceptionIfEmailTooLong(){
    String Email = "moGqnudWQwelysctwKoIXYTZtQGtXGiBswtBbEDFaiZhavjJLJEVWlWserpvkFTirUdkERqeycxhwrnJaI@diuabNjmjTTENqnbXADH.com";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_user.setEmail(Email);});
  }
  @Test
  public void testSetEmailSetsEmail(){
    String Email = "XXBwJsYsQKvNfhKEGqOHOkqIuZnFBAMQtcFDqTqCJHgcIsNyXPIQlkSjYEtBVnaXBPXEFTE@LQGckEyHHTkGpXFNGblEfFxI.com";
    _user.setEmail(Email);
    Assertions.assertEquals(Email,_user.getEmail());
  }
  @Test
  public void  testUserThrowsIllegalArgumentExceptionIfPasswordTooShort(){
    String Password = "kj";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_user.setPassword(Password.toCharArray());});
  }
  @Test
  public void  testUserThrowsIllegalArgumentExceptionIfPasswordTooLong(){
    String Password = "MgHfEjMLwRnvWgYDQJgAyGwfNwKIyTlZAgfVVKUHuSeVXNChyEjigBhXgBopobdNYVdmRpIrKeBqRIudDlxxOsqTREClKnfDLlsoqDVexVrUUtxaZXVtZSoFHyFUWPIjwHurNsHwjcELiTOhRRajOkuWcqWwFkNDSgaLUcVuteKNixqJlhVAgPQEydrqHLBlJdkVVfVVfxgIOHtDQovCyQuQckgDlYJTFnWCAdyBRcukwmpWEQHdECdiXsgimnPtl";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_user.setPassword(Password.toCharArray());});
  }
  @Test
  public void testSetPasswordSetsPassword(){
    String Password = "!4DxxRVqOiQvCEKWietaHmFNCiJvdIYEfIiLUAHwYREJvbmwqLLHomyXvlSDHKbnAtJnkngIgdGJGbBXUaNvtOWNohdG";
    char[] arrayPW = Password.toCharArray();
    _user.setPassword(Password.toCharArray());
    char[] pwFromUser = _user.getPassword();
    Assertions.assertArrayEquals(arrayPW,pwFromUser);
  }

}



