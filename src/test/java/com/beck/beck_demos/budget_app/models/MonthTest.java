package com.beck.beck_demos.budget_app.models;

import com.beck.beck_demos.budget_app.models.Month;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class MonthTest {
  private Month _month;
  @BeforeEach
  public void setup(){
    _month = new Month();
  }
  @AfterEach
  public void teardown(){
    _month = null;
  }
  @Test
  public void testMonthDefaultConstructorSetsNoVariables(){
    Month _month= new Month();
    Assertions.assertNull(_month.getMortgage_ID());
    Assertions.assertNull(_month.getMonth());
    Assertions.assertNull(_month.getBegin_Bal());
    Assertions.assertNull(_month.getRate());
    Assertions.assertNull(_month.getPayment());
    Assertions.assertNull(_month.getEnding_Bal());
  }
  @Test
  public void testMonthParameterizedConstructorSetsAllVariables(){
    Month _month= new Month(
        754,
        1081,
        86.26,
        6.29,
        65.88,
        17.74
    );
    Assertions.assertEquals(754,_month.getMortgage_ID());
    Assertions.assertEquals(1081,_month.getMonth());
    Assertions.assertEquals(86.26,_month.getBegin_Bal());
    Assertions.assertEquals(6.29,_month.getRate());
    Assertions.assertEquals(65.88,_month.getPayment());
    Assertions.assertTrue(Math.abs(20.83-_month.getEnding_Bal())<.01);
  }
  @Test
  public void testMonthKeyedParameterizedConstructorSetsKeyedVariables(){
    Month _month= new Month(
        8907,
        8884
    );
    Assertions.assertEquals(8907,_month.getMortgage_ID());
    Assertions.assertEquals(8884,_month.getMonth());
    Assertions.assertNull(_month.getBegin_Bal());
    Assertions.assertNull(_month.getRate());
    Assertions.assertNull(_month.getPayment());
    Assertions.assertNull(_month.getEnding_Bal());
  }
  @Test
  public void testMonthThrowsIllegalArgumentExceptionIfMortgage_IDTooSmall(){
    int Mortgage_ID = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_month.setMortgage_ID(Mortgage_ID);});
  }
  @Test
  public void testMonthThrowsIllegalArgumentExceptionIfMortgage_IDTooBig(){
    int Mortgage_ID = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_month.setMortgage_ID(Mortgage_ID);});
  }
  @Test
  public void testMonthSetsMortgage_ID(){
    int Mortgage_ID = 7316;
    _month.setMortgage_ID(Mortgage_ID);
    Assertions.assertEquals(Mortgage_ID, _month.getMortgage_ID());
  }
  @Test
  public void testMonthThrowsIllegalArgumentExceptionIfMonthTooSmall(){
    int Month = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_month.setMonth(Month);});
  }
  @Test
  public void testMonthThrowsIllegalArgumentExceptionIfMonthTooBig(){
    int Month = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_month.setMonth(Month);});
  }
  @Test
  public void testMonthSetsMonth(){
    int Month = 632;
    _month.setMonth(Month);
    Assertions.assertEquals(Month, _month.getMonth());
  }
  @Test
  public void testMonthThrowsIllegalArgumentExceptionIfBegin_BalTooSmall(){
    double Begin_Bal = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_month.setBegin_Bal(Begin_Bal);});
  }
  @Test
  public void testMonthThrowsIllegalArgumentExceptionIfBegin_BalTooBig(){
    double Begin_Bal = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_month.setBegin_Bal(Begin_Bal);});
  }
  @Test
  public void testMonthSetsBegin_Bal(){
    double Begin_Bal = 8721;
    _month.setBegin_Bal(Begin_Bal);
    Assertions.assertEquals(Begin_Bal, _month.getBegin_Bal());
  }
  @Test
  public void testMonthThrowsIllegalArgumentExceptionIfRateTooSmall(){
    double Rate = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_month.setRate(Rate);});
  }
  @Test
  public void testMonthThrowsIllegalArgumentExceptionIfRateTooBig(){
    double Rate = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_month.setRate(Rate);});
  }
  @Test
  public void testMonthSetsRate(){
    double Rate = 8189;
    _month.setRate(Rate);
    Assertions.assertEquals(Rate, _month.getRate());
  }
  @Test
  public void testMonthThrowsIllegalArgumentExceptionIfPaymentTooSmall(){
    double Payment = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_month.setPayment(Payment);});
  }
  @Test
  public void testMonthThrowsIllegalArgumentExceptionIfPaymentTooBig(){
    double Payment = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_month.setPayment(Payment);});
  }
  @Test
  public void testMonthSetsPayment(){
    double Payment = 3371;
    _month.setPayment(Payment);
    Assertions.assertEquals(Payment, _month.getPayment());
  }
  @Test
  public void testMonthThrowsIllegalArgumentExceptionIfEnding_BalTooSmall(){
    double Ending_Bal = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_month.setEnding_Bal(Ending_Bal);});
  }
  @Test
  public void testMonthThrowsIllegalArgumentExceptionIfEnding_BalTooBig(){
    double Ending_Bal = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_month.setEnding_Bal(Ending_Bal);});
  }
  @Test
  public void testMonthSetsEnding_Bal(){
    double Ending_Bal = 9526;
    _month.setEnding_Bal(Ending_Bal);
    Assertions.assertEquals(Ending_Bal, _month.getEnding_Bal());
  }

}