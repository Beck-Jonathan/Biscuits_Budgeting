package com.beck.beck_demos.budget_app.models;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class MortgageTest {
  private Mortgage _mortgage;
  @BeforeEach
  public void setup(){
    _mortgage = new Mortgage();
  }
  @AfterEach
  public void teardown(){
    _mortgage = null;
  }

  /**
   <p> Tests That the default constructor for the Mortgage object works </p>
   */
  @Test
  public void testMortgageDefaultConstructorSetsNoVariables(){
    Mortgage _mortgage= new Mortgage();
    Assertions.assertNull(_mortgage.getMortgage_ID());
    Assertions.assertNull(_mortgage.getUser_ID());
    Assertions.assertNull(_mortgage.getNickname());
    Assertions.assertNull(_mortgage.getPresent_Value());
    Assertions.assertNull(_mortgage.getFuture_Value());
    Assertions.assertNull(_mortgage.getInterest_Rate());
    Assertions.assertNull(_mortgage.getMonthly_Payment());
    Assertions.assertNull(_mortgage.getExtra_Payment());
    Assertions.assertNull(_mortgage.getRemaining_Term());
  }

  /**
   <p> Tests That the Parameterized Constructor for the Mortgage object works </p>
   */
  @Test
  public void testMortgageParameterizedConstructorSetsAllVariables(){
    Mortgage _mortgage= new Mortgage(
        "DOGJOfoxaLJqkdZhJgwwxllZHCvQGoOCAwFW",
        5110,
        "PVTiVdeElUWQJLtDDgveuTyUcNbYkEPZgbXCDwWqlHESGsCiaeAjBuVDfAPHpcEqyFImWaqpuBSqBrCHXRCCITLAhciOtvaCqEYrKWCSJNawYfwDAKbsEqIuLEwEypLDyYqehRsTOYjJNpiwsqOKTBCONLIbHNXgGFXSsipYYkwiVALYxvreXoJYneIFpbvpIbrNxfccjQnrKwJJRBhPOsFEVkbqoHmIjKyVMIoMZHylwIgXdQHtbMUsPlAlu",
        16.76,
        25.39,
        8.66,
        4.4,
        22.25,
        9775
    );
    Assertions.assertEquals("DOGJOfoxaLJqkdZhJgwwxllZHCvQGoOCAwFW",_mortgage.getMortgage_ID());
    Assertions.assertEquals(5110,_mortgage.getUser_ID());
    Assertions.assertEquals("PVTiVdeElUWQJLtDDgveuTyUcNbYkEPZgbXCDwWqlHESGsCiaeAjBuVDfAPHpcEqyFImWaqpuBSqBrCHXRCCITLAhciOtvaCqEYrKWCSJNawYfwDAKbsEqIuLEwEypLDyYqehRsTOYjJNpiwsqOKTBCONLIbHNXgGFXSsipYYkwiVALYxvreXoJYneIFpbvpIbrNxfccjQnrKwJJRBhPOsFEVkbqoHmIjKyVMIoMZHylwIgXdQHtbMUsPlAlu",_mortgage.getNickname());
    Assertions.assertEquals(16.76,_mortgage.getPresent_Value());
    Assertions.assertEquals(25.39,_mortgage.getFuture_Value());
    Assertions.assertEquals(8.66,_mortgage.getInterest_Rate());
    Assertions.assertEquals(4.4,_mortgage.getMonthly_Payment());
    Assertions.assertEquals(22.25,_mortgage.getExtra_Payment());
    Assertions.assertEquals(9775,_mortgage.getRemaining_Term());
  }

  /**
   <p> Tests That the Parameterized Constructor for the Mortgage object works </p>
   */
  @Test
  public void testMortgageKeyedParameterizedConstructorSetsKeyedVariables(){
    Mortgage _mortgage= new Mortgage(
        "JuSssColFRYctYKqYspGtvVnZwkgtuhOiwNB"
    );
    Assertions.assertEquals("JuSssColFRYctYKqYspGtvVnZwkgtuhOiwNB",_mortgage.getMortgage_ID());
    Assertions.assertNull(_mortgage.getUser_ID());
    Assertions.assertNull(_mortgage.getNickname());
    Assertions.assertNull(_mortgage.getPresent_Value());
    Assertions.assertNull(_mortgage.getFuture_Value());
    Assertions.assertNull(_mortgage.getInterest_Rate());
    Assertions.assertNull(_mortgage.getMonthly_Payment());
    Assertions.assertNull(_mortgage.getExtra_Payment());
    Assertions.assertNull(_mortgage.getRemaining_Term());
  }

  /**
   <p> Tests That the Setters for the Mortgage.Mortgage_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testMortgageThrowsIllegalArgumentExceptionIfMortgage_IDTooShort(){
    String Mortgage_ID = "Xb";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setMortgage_ID(Mortgage_ID);});
  }

  /**
   <p> Tests That the Setters for the Mortgage.Mortgage_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testMortgageThrowsIllegalArgumentExceptionIfMortgage_IDTooLong(){
    String Mortgage_ID = "nKqIGTkSpLiIDBWEbQnUdhLKsKrAFNSjFMhGme";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setMortgage_ID(Mortgage_ID);});
  }

  /**
   <p> Tests That the Setters for the Mortgage.Mortgage_ID field work </p>
   */
  @Test
  public void testSetMortgage_IDSetsMortgage_ID(){
    String Mortgage_ID = "tYLKgjvwHnYStIOYeTdtjRRPWIZHFfynSZuD";
    _mortgage.setMortgage_ID(Mortgage_ID);
    Assertions.assertEquals(Mortgage_ID,_mortgage.getMortgage_ID());
  }

  /**
   <p> Tests That the Setters for the Mortgage.User_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testMortgageThrowsIllegalArgumentExceptionIfUser_IDTooSmall(){
    int User_ID = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setUser_ID(User_ID);});
  }

  /**
   <p> Tests That the Setters for the Mortgage.User_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testMortgageThrowsIllegalArgumentExceptionIfUser_IDTooBig(){
    int User_ID = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setUser_ID(User_ID);});
  }

  /**
   <p> Tests That the Setters for the Mortgage.User_ID field work </p>
   */
  @Test
  public void testMortgageSetsUser_ID(){
    int User_ID = 9694;
    _mortgage.setUser_ID(User_ID);
    Assertions.assertEquals(User_ID, _mortgage.getUser_ID());
  }

  /**
   <p> Tests That the Setters for the Mortgage.Nickname field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testMortgageThrowsIllegalArgumentExceptionIfNicknameTooShort(){
    String Nickname = "Zs";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setNickname(Nickname);});
  }

  /**
   <p> Tests That the Setters for the Mortgage.Nickname field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testMortgageThrowsIllegalArgumentExceptionIfNicknameTooLong(){
    String Nickname = "JlkmxLPHwbfVOZdeyXNyOJFKXXeZqIcywJAIsyYEAVbTnsklnDGEjvLcNiuvxehoXEFOeCBKBoRfwWAlEivqFPcwnuKUEZdiGDSZfQIAEthiefpiIrOiqjIIjqfFbLbsEaGmaulLbsROOUfuaUyOTKxoXIYnFCAlkHCEhmFbpLyJBpcQtbbxIlSXVPOdrgLJodWgdwgYdEgZjMqGBlGDLawxrQJWGjPLtTKFsmXhoQlxXVHKXhKGfxnxSDcbTlUur";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setNickname(Nickname);});
  }

  /**
   <p> Tests That the Setters for the Mortgage.Nickname field work </p>
   */
  @Test
  public void testSetNicknameSetsNickname(){
    String Nickname = "TfkYhGsJBLiBiAnhMRusKlunKdrclPUthVMQhZVrtrTlEIdvslSxturAaPvKfoRXudZAhtBSRQGdebHVXiWlcYoMuinDBTdMArOdEadbtCeQbKgvAlrqbixROOltWeGdrkSLpIAlaeHcVETqhHePIFKBUSIHOeanKghipDvtSJurJXDiTScMAnmhVeSAfCbsgQyYnMdNmBFIkEHAMbZtvIhRbSrQQkliEgHhVZyiQemsfpFRMlFeSfMLrySpC";
    _mortgage.setNickname(Nickname);
    Assertions.assertEquals(Nickname,_mortgage.getNickname());
  }

  /**
   <p> Tests That the Setters for the Mortgage.Present_Value field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testMortgageThrowsIllegalArgumentExceptionIfPresent_ValueTooSmall(){
    double Present_Value = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setPresent_Value(Present_Value);});
  }

  /**
   <p> Tests That the Setters for the Mortgage.Present_Value field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testMortgageThrowsIllegalArgumentExceptionIfPresent_ValueTooBig(){
    double Present_Value = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setPresent_Value(Present_Value);});
  }

  /**
   <p> Tests That the Setters for the Mortgage.Present_Value field work </p>
   */
  @Test
  public void testMortgageSetsPresent_Value(){
    double Present_Value = 3177;
    _mortgage.setPresent_Value(Present_Value);
    Assertions.assertEquals(Present_Value, _mortgage.getPresent_Value());
  }

  /**
   <p> Tests That the Setters for the Mortgage.Future_Value field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testMortgageThrowsIllegalArgumentExceptionIfFuture_ValueTooSmall(){
    double Future_Value = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setFuture_Value(Future_Value);});
  }

  /**
   <p> Tests That the Setters for the Mortgage.Future_Value field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testMortgageThrowsIllegalArgumentExceptionIfFuture_ValueTooBig(){
    double Future_Value = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setFuture_Value(Future_Value);});
  }

  /**
   <p> Tests That the Setters for the Mortgage.Future_Value field work </p>
   */
  @Test
  public void testMortgageSetsFuture_Value(){
    double Future_Value = 964;
    _mortgage.setFuture_Value(Future_Value);
    Assertions.assertEquals(Future_Value, _mortgage.getFuture_Value());
  }

  /**
   <p> Tests That the Setters for the Mortgage.Interest_Rate field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testMortgageThrowsIllegalArgumentExceptionIfInterest_RateTooSmall(){
    double Interest_Rate = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setInterest_Rate(Interest_Rate);});
  }

  /**
   <p> Tests That the Setters for the Mortgage.Interest_Rate field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testMortgageThrowsIllegalArgumentExceptionIfInterest_RateTooBig(){
    double Interest_Rate = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setInterest_Rate(Interest_Rate);});
  }

  /**
   <p> Tests That the Setters for the Mortgage.Interest_Rate field work </p>
   */
  @Test
  public void testMortgageSetsInterest_Rate(){
    double Interest_Rate = 7869;
    _mortgage.setInterest_Rate(Interest_Rate);
    Assertions.assertEquals(Interest_Rate, _mortgage.getInterest_Rate());
  }

  /**
   <p> Tests That the Setters for the Mortgage.Monthly_Payment field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testMortgageThrowsIllegalArgumentExceptionIfMonthly_PaymentTooSmall(){
    double Monthly_Payment = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setMonthly_Payment(Monthly_Payment);});
  }

  /**
   <p> Tests That the Setters for the Mortgage.Monthly_Payment field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testMortgageThrowsIllegalArgumentExceptionIfMonthly_PaymentTooBig(){
    double Monthly_Payment = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setMonthly_Payment(Monthly_Payment);});
  }

  /**
   <p> Tests That the Setters for the Mortgage.Monthly_Payment field work </p>
   */
  @Test
  public void testMortgageSetsMonthly_Payment(){
    double Monthly_Payment = 2092;
    _mortgage.setMonthly_Payment(Monthly_Payment);
    Assertions.assertEquals(Monthly_Payment, _mortgage.getMonthly_Payment());
  }

  /**
   <p> Tests That the Setters for the Mortgage.Extra_Payment field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testMortgageThrowsIllegalArgumentExceptionIfExtra_PaymentTooSmall(){
    double Extra_Payment = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setExtra_Payment(Extra_Payment);});
  }

  /**
   <p> Tests That the Setters for the Mortgage.Extra_Payment field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testMortgageThrowsIllegalArgumentExceptionIfExtra_PaymentTooBig(){
    double Extra_Payment = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setExtra_Payment(Extra_Payment);});
  }

  /**
   <p> Tests That the Setters for the Mortgage.Extra_Payment field work </p>
   */
  @Test
  public void testMortgageSetsExtra_Payment(){
    double Extra_Payment = 111;
    _mortgage.setExtra_Payment(Extra_Payment);
    Assertions.assertEquals(Extra_Payment, _mortgage.getExtra_Payment());
  }

  /**
   <p> Tests That the Setters for the Mortgage.Remaining_Term field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testMortgageThrowsIllegalArgumentExceptionIfRemaining_TermTooSmall(){
    int Remaining_Term = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setRemaining_Term(Remaining_Term);});
  }

  /**
   <p> Tests That the Setters for the Mortgage.Remaining_Term field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testMortgageThrowsIllegalArgumentExceptionIfRemaining_TermTooBig(){
    int Remaining_Term = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setRemaining_Term(Remaining_Term);});
  }

  /**
   <p> Tests That the Setters for the Mortgage.Remaining_Term field work </p>
   */
  @Test
  public void testMortgageSetsRemaining_Term(){
    int Remaining_Term = 5489;
    _mortgage.setRemaining_Term(Remaining_Term);
    Assertions.assertEquals(Remaining_Term, _mortgage.getRemaining_Term());
  }


  /**
   <p> Tests That the CompareTo Method for the Mortgage object works </p>
   */
  @Test
  public void testCompareToCanCompareForEachDateField() {
    Mortgage smaller = new Mortgage();
    Mortgage bigger = new Mortgage();
//to compare a smaller and larger Mortgage_ID
    smaller.setMortgage_ID("8b0c01b4-c726-4aed-806d-37f273332773");
    bigger.setMortgage_ID("9b0c01b4-c726-4aed-806d-37f273332773");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Mortgage_ID as equal.
    smaller.setMortgage_ID("9b0c01b4-c726-4aed-806d-37f273332773");
//to compare a smaller and larger User_ID
    smaller.setUser_ID(10);
    bigger.setUser_ID(20);
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the User_ID as equal.
    smaller.setUser_ID(20);
//to compare a smaller and larger Nickname
    smaller.setNickname("AuSssColFRYctYKqYspGtvVnZwkgtuhOiwNB");
    bigger.setNickname("JuSssColFRYctYKqYspGtvVnZwkgtuhOiwNB");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Nickname as equal.
    smaller.setNickname("JuSssColFRYctYKqYspGtvVnZwkgtuhOiwNB");
//to compare a smaller and larger Present_Value
    smaller.setPresent_Value(10.23d);
    bigger.setPresent_Value(14.12d);
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Present_Value as equal.
    smaller.setPresent_Value(14.12d);
//to compare a smaller and larger Future_Value
    smaller.setFuture_Value(10.23d);
    bigger.setFuture_Value(14.12d);
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Future_Value as equal.
    smaller.setFuture_Value(14.12d);
//to compare a smaller and larger Interest_Rate
    smaller.setInterest_Rate(10.23d);
    bigger.setInterest_Rate(14.12d);
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Interest_Rate as equal.
    smaller.setInterest_Rate(14.12d);
//to compare a smaller and larger Monthly_Payment
    smaller.setMonthly_Payment(10.23d);
    bigger.setMonthly_Payment(14.12d);
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Monthly_Payment as equal.
    smaller.setMonthly_Payment(14.12d);
//to compare a smaller and larger Extra_Payment
    smaller.setExtra_Payment(10.23d);
    bigger.setExtra_Payment(14.12d);
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Extra_Payment as equal.
    smaller.setExtra_Payment(14.12d);
//to compare a smaller and larger Remaining_Term
    smaller.setRemaining_Term(10);
    bigger.setRemaining_Term(20);
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Remaining_Term as equal.
    smaller.setRemaining_Term(20);
    Assertions.assertTrue(bigger.compareTo(smaller)==0);
  }

}

