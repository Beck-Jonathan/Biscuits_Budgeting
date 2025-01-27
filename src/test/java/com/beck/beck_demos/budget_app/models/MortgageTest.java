package com.beck.beck_demos.budget_app.models;

import com.beck.beck_demos.budget_app.models.Mortgage;
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
  @Test
  public void testMortgageDefaultConstructorSetsNoVariables(){
    Mortgage _mortgage= new Mortgage();
    Assertions.assertNull(_mortgage.getMortgage_ID());
    Assertions.assertNull(_mortgage.getUser_ID());
    Assertions.assertNull(_mortgage.getPresent_Value());
    Assertions.assertNull(_mortgage.getFuture_Value());
    Assertions.assertNull(_mortgage.getInterest_Rate());
    Assertions.assertNull(_mortgage.getMonthly_Payment());
    Assertions.assertNull(_mortgage.getExtra_Payment());
    Assertions.assertNull(_mortgage.getRemaining_Term());
  }
  @Test
  public void testMortgageParameterizedConstructorSetsAllVariables(){
    Mortgage _mortgage= new Mortgage(
        754,
        1081,
        86.26,
        6.29,
        65.88,
        17.74,
        89.07,
        8884
    );
    Assertions.assertEquals(754,_mortgage.getMortgage_ID());
    Assertions.assertEquals(1081,_mortgage.getUser_ID());
    Assertions.assertEquals(86.26,_mortgage.getPresent_Value());
    Assertions.assertEquals(6.29,_mortgage.getFuture_Value());
    Assertions.assertEquals(65.88,_mortgage.getInterest_Rate());
    Assertions.assertEquals(17.74,_mortgage.getMonthly_Payment());
    Assertions.assertEquals(89.07,_mortgage.getExtra_Payment());
    Assertions.assertEquals(8884,_mortgage.getRemaining_Term());
  }
  @Test
  public void testMortgageKeyedParameterizedConstructorSetsKeyedVariables(){
    Mortgage _mortgage= new Mortgage(
        7316
    );
    Assertions.assertEquals(7316,_mortgage.getMortgage_ID());
    Assertions.assertNull(_mortgage.getUser_ID());
    Assertions.assertNull(_mortgage.getPresent_Value());
    Assertions.assertNull(_mortgage.getFuture_Value());
    Assertions.assertNull(_mortgage.getInterest_Rate());
    Assertions.assertNull(_mortgage.getMonthly_Payment());
    Assertions.assertNull(_mortgage.getExtra_Payment());
    Assertions.assertNull(_mortgage.getRemaining_Term());
  }
  @Test
  public void testMortgageThrowsIllegalArgumentExceptionIfMortgage_IDTooSmall(){
    int Mortgage_ID = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setMortgage_ID(Mortgage_ID);});
  }
  @Test
  public void testMortgageThrowsIllegalArgumentExceptionIfMortgage_IDTooBig(){
    int Mortgage_ID = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setMortgage_ID(Mortgage_ID);});
  }
  @Test
  public void testMortgageSetsMortgage_ID(){
    int Mortgage_ID = 632;
    _mortgage.setMortgage_ID(Mortgage_ID);
    Assertions.assertEquals(Mortgage_ID, _mortgage.getMortgage_ID());
  }
  @Test
  public void testMortgageThrowsIllegalArgumentExceptionIfUser_IDTooSmall(){
    int User_ID = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setUser_ID(User_ID);});
  }
  @Test
  public void testMortgageThrowsIllegalArgumentExceptionIfUser_IDTooBig(){
    int User_ID = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setUser_ID(User_ID);});
  }
  @Test
  public void testMortgageSetsUser_ID(){
    int User_ID = 8721;
    _mortgage.setUser_ID(User_ID);
    Assertions.assertEquals(User_ID, _mortgage.getUser_ID());
  }
  @Test
  public void testMortgageThrowsIllegalArgumentExceptionIfPresent_ValueTooSmall(){
    double Present_Value = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setPresent_Value(Present_Value);});
  }
  @Test
  public void testMortgageThrowsIllegalArgumentExceptionIfPresent_ValueTooBig(){
    double Present_Value = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setPresent_Value(Present_Value);});
  }
  @Test
  public void testMortgageSetsPresent_Value(){
    double Present_Value = 8189;
    _mortgage.setPresent_Value(Present_Value);
    Assertions.assertEquals(Present_Value, _mortgage.getPresent_Value());
  }
  @Test
  public void testMortgageThrowsIllegalArgumentExceptionIfFuture_ValueTooSmall(){
    double Future_Value = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setFuture_Value(Future_Value);});
  }
  @Test
  public void testMortgageThrowsIllegalArgumentExceptionIfFuture_ValueTooBig(){
    double Future_Value = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setFuture_Value(Future_Value);});
  }
  @Test
  public void testMortgageSetsFuture_Value(){
    double Future_Value = 3371;
    _mortgage.setFuture_Value(Future_Value);
    Assertions.assertEquals(Future_Value, _mortgage.getFuture_Value());
  }
  @Test
  public void testMortgageThrowsIllegalArgumentExceptionIfInterest_RateTooSmall(){
    double Interest_Rate = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setInterest_Rate(Interest_Rate);});
  }
  @Test
  public void testMortgageThrowsIllegalArgumentExceptionIfInterest_RateTooBig(){
    double Interest_Rate = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setInterest_Rate(Interest_Rate);});
  }
  @Test
  public void testMortgageSetsInterest_Rate(){
    double Interest_Rate = 9526;
    _mortgage.setInterest_Rate(Interest_Rate);
    Assertions.assertEquals(Interest_Rate, _mortgage.getInterest_Rate());
  }
  @Test
  public void testMortgageThrowsIllegalArgumentExceptionIfMonthly_PaymentTooSmall(){
    double Monthly_Payment = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setMonthly_Payment(Monthly_Payment);});
  }
  @Test
  public void testMortgageThrowsIllegalArgumentExceptionIfMonthly_PaymentTooBig(){
    double Monthly_Payment = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setMonthly_Payment(Monthly_Payment);});
  }
  @Test
  public void testMortgageSetsMonthly_Payment(){
    double Monthly_Payment = 7237;
    _mortgage.setMonthly_Payment(Monthly_Payment);
    Assertions.assertEquals(Monthly_Payment, _mortgage.getMonthly_Payment());
  }
  @Test
  public void testMortgageThrowsIllegalArgumentExceptionIfExtra_PaymentTooSmall(){
    double Extra_Payment = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setExtra_Payment(Extra_Payment);});
  }
  @Test
  public void testMortgageThrowsIllegalArgumentExceptionIfExtra_PaymentTooBig(){
    double Extra_Payment = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setExtra_Payment(Extra_Payment);});
  }
  @Test
  public void testMortgageSetsExtra_Payment(){
    double Extra_Payment = 9054;
    _mortgage.setExtra_Payment(Extra_Payment);
    Assertions.assertEquals(Extra_Payment, _mortgage.getExtra_Payment());
  }
  @Test
  public void testMortgageThrowsIllegalArgumentExceptionIfRemaining_TermTooSmall(){
    int Remaining_Term = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setRemaining_Term(Remaining_Term);});
  }
  @Test
  public void testMortgageThrowsIllegalArgumentExceptionIfRemaining_TermTooBig(){
    int Remaining_Term = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_mortgage.setRemaining_Term(Remaining_Term);});
  }
  @Test
  public void testMortgageSetsRemaining_Term(){
    int Remaining_Term = 8396;
    _mortgage.setRemaining_Term(Remaining_Term);
    Assertions.assertEquals(Remaining_Term, _mortgage.getRemaining_Term());
  }

}