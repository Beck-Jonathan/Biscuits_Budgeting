package com.beck.beck_demos.budget_app.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    Assertions.assertNull(_user.getUser_Name());
    Assertions.assertNull(_user.getUser_PW());
    Assertions.assertNull(_user.getEmail());
  }
  @Test
  public void testUserParameterizedConstructorSetsAllVariables(){
    User _user= new User(
        "af735dfc-22a9-4214-a8e5-fb8de2305700",
        "GrDfKssjDroTwjtpyewSRUKBvmtSALiIRbDOjJuTDariNWtufwgmkgCiWyajqJgBVgfGpidPjnPXFKKdnRahZUqAaYHjNnpoTX",
        "COMONHIdRyXaUEXWKPlsuMDHmKQiVqMlDUZMWrTMhaEAaUIVZdEeJlRBKyhxTbceLnRjxJnWYfnTICaYBtPklacQDXCXKVsFwZ".toCharArray(),
        "olDYXPphMxwsHXPwYSrsdLAgHVMClhWhMnVbAuEUFSFtAtXmFaLaCkqHZJjPKtctUuRvnPFgaBNVRdRuQHBNSqfscKUDUyWADI"
    );
    Assertions.assertEquals("af735dfc-22a9-4214-a8e5-fb8de2305700",_user.getUser_ID());
    Assertions.assertEquals("GrDfKssjDroTwjtpyewSRUKBvmtSALiIRbDOjJuTDariNWtufwgmkgCiWyajqJgBVgfGpidPjnPXFKKdnRahZUqAaYHjNnpoTX",_user.getUser_Name());
    Assertions.assertEquals("COMONHIdRyXaUEXWKPlsuMDHmKQiVqMlDUZMWrTMhaEAaUIVZdEeJlRBKyhxTbceLnRjxJnWYfnTICaYBtPklacQDXCXKVsFwZ",new String(_user.getUser_PW()));
    Assertions.assertEquals("olDYXPphMxwsHXPwYSrsdLAgHVMClhWhMnVbAuEUFSFtAtXmFaLaCkqHZJjPKtctUuRvnPFgaBNVRdRuQHBNSqfscKUDUyWADI",_user.getEmail());
  }

  @Test
  public void testUserThrowsIllegalArgumentExceptionIfUser_IDTooSmall(){
    String User_ID = "af735dfc-22a9-4214-a8e5-fb8057";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_user.setUser_ID(User_ID);});
  }
  @Test
  public void testUserThrowsIllegalArgumentExceptionIfUser_IDTooBig(){
    String User_ID = "af735dfc-22a9-4214-a8e5-fb80sssssss57";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_user.setUser_ID(User_ID);});
  }
  @Test
  public void testUserSetsUser_ID(){
    String User_ID = "af735dfc-22a9-4214-a8e5-fb8de2305700";
    _user.setUser_ID(User_ID);
    Assertions.assertEquals(User_ID, _user.getUser_ID());
  }
  @Test
  public void  testUserThrowsIllegalArgumentExceptionIfUser_NameTooShort(){
    String User_Name = "vg";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_user.setUser_Name(User_Name);});
  }
  @Test
  public void  testUserThrowsIllegalArgumentExceptionIfUser_NameTooLong(){
    String User_Name = "GqiJhuckakVjNqLBCGqaSVxtMAobopbqeaeDAvwbaiQSsTIppSLninIltqDIVMdWCQaVYVSjihhfDSRVgvclqKCkatFPmlnwItpLkQ";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_user.setUser_Name(User_Name);});
  }
  @Test
  public void testSetUser_NameSetsUser_Name(){
    String User_Name = "ZPMJWFDtNrZZjLVepvoWYhvtHFJXMkqWtpaFqLBtRTyMwLeOvtBaQPPyshfibDTQDjwLWneHsMrTbuaVTQoxhTOclvsgyHtmxi";
    _user.setUser_Name(User_Name);
    Assertions.assertEquals(User_Name,_user.getUser_Name());
  }
  @Test
  public void  testUserThrowsIllegalArgumentExceptionIfUser_PWTooShort(){
    String User_PW = "dm";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_user.setUser_PW(User_PW.toCharArray());});
  }
  @Test
  public void  testUserThrowsIllegalArgumentExceptionIfUser_PWTooLong(){
    String User_PW = "fjIFfdTELfwwAltyyDBmLysMhjwrksagVREyJWFYuixUymyLNdwvGGjHWieMWHLNcgYDVeglEptqhvxfLSOckPLTrmuWniSqIlJRmq";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_user.setUser_PW(User_PW.toCharArray());});
  }
  @Test
  public void testSetUser_PWSetsUser_PW(){
    String User_PW = "yPJBQoPhkxBErbTELbiQxUChvmfriPTaNubFWeohOyPppiQOPxLfllblkEFRcbOuFdSeIgPDHfvpJYLAUUaIkATeaiVeXRT3@";
    _user.setUser_PW(User_PW.toCharArray());
    Assertions.assertEquals(User_PW,new String(_user.getUser_PW()));
  }
  @Test
  public void  testUserThrowsIllegalArgumentExceptionIfEmailTooShort(){
    String Email = "fl";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_user.setEmail(Email);});
  }
  @Test
  public void  testUserThrowsIllegalArgumentExceptionIfEmailTooLong(){
    String Email = "wTCyiZFoOvsWataXndJgEYRdepSLAlZDBRbeCNpRmOpXmMqTCcPOcgsDdmchuHwrOumlEuqlfoUoXyigipnmhtlxuMdqTRSQEaxgYw";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_user.setEmail(Email);});
  }
  @Test
  public void testSetEmailSetsEmail(){
    String Email = "qOLNdyOXGjFAAnIdInDsBhdFkIVQkCNijMcecvKcYwPDlpMnSJVZmSLuUIfwUoowmFYNAKssZDXSuvawbgnuWAhPPMOdIR@aol.com";
    _user.setEmail(Email);
    Assertions.assertEquals(Email,_user.getEmail());
  }
  @Test
  public void testParamaterOfNullOnSetEmailThrowsIllegalArgumentException(){
    String email = null;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_user.setEmail(email);});
  }
  @Test
  public void testCompareToCanCompareForEachDateField() {
    User smaller = new User();
    User bigger = new User();
//to compare a smaller and larger User_ID
    smaller.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    bigger.setUser_ID("gec75744-130e-4bcb-8bbe-9bee18080428");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the User_ID as equal.
    smaller.setUser_ID("gec75744-130e-4bcb-8bbe-9bee18080428");;
//to compare a smaller and larger User_Name
    smaller.setUser_Name("aaaa");
    bigger.setUser_Name("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the User_Name as equal.
    smaller.setUser_Name("bbbb");
//to compare a smaller and larger Email
    smaller.setEmail("aaaa@gmail.com");
    bigger.setEmail("bbbb@gmail.com");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Email as equal.
    smaller.setEmail("bbbb@gmail.com");
    Assertions.assertTrue(bigger.compareTo(smaller)==0);
  }

}

