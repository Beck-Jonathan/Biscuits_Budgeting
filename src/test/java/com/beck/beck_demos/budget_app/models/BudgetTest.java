package com.beck.beck_demos.budget_app.models;

import com.beck.beck_demos.budget_app.models.Budget;
import org.joda.time.DateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
class BudgetTest {
  private Budget _Budget;
  @BeforeEach
  public void setup(){
    _Budget = new Budget();
  }
  @AfterEach
  public void teardown(){
    _Budget = null;
  }

/**
 <p> Tests That the default constructor for the Budget object works </p>
 */
  @Test
  public void testBudgetDefaultConstructorSetsNoVariables(){
    Budget _Budget= new Budget();
    Assertions.assertNull(_Budget.getbudget_id());
    Assertions.assertNull(_Budget.getuser_id());
    Assertions.assertNull(_Budget.getname());
    Assertions.assertNull(_Budget.getdetails());
    Assertions.assertNull(_Budget.getstart_date());
    Assertions.assertNull(_Budget.getlimit_amount());
    Assertions.assertNull(_Budget.getcurrency_code_id());
    Assertions.assertFalse(_Budget.getis_active());
    Assertions.assertNull(_Budget.getcreated_at());
    Assertions.assertNull(_Budget.getupdated_at());
  }

/**
 <p> Tests That the Parameterized Constructor for the Budget object works </p>
 */
  @Test
  public void testBudgetParameterizedConstructorSetsAllVariables(){
    Date test = new Date();
    Budget _Budget= new Budget(
        "CEQSJYTDlaysmaOPTErQWeGQnBCbPHornulM",
        "pRRbKtIIEqysBLqwEedpKBUWRZJBxfClhM",
        "awMvCIEQqmIRvCowtPhXhxDrSWxiEXWXCSMoXUIuXISOpUyh",
        "neXInxJEjlaULYWkOcoyHlAfnCwpxBQmxstOfTeWTCwsrEGwKhOEQlIOSUBlubTufXrTEaTMujYwclvxqsnSChgGlPvQSskiBNZLlrdUBJaTLfXCtCnBsxjkbodCtCOgNalFBKxAGmadVsPxYwgIOiKDyRHHRgwEBuPoBdDrZcxALOJdDpiYPmRtxLfrWoGdUxyJXTdPoFJqjIDwWfkHxtDYWACnrWftKdtYVvIZgojGUTTIMscqBOTuMPmRd",
        test
        ,
        96.71,
        "s",
        true,
        test
        ,
        test

    );
    Assertions.assertEquals("CEQSJYTDlaysmaOPTErQWeGQnBCbPHornulM",_Budget.getbudget_id());
    Assertions.assertEquals("pRRbKtIIEqysBLqwEedpKBUWRZJBxfClhM",_Budget.getuser_id());
    Assertions.assertEquals("awMvCIEQqmIRvCowtPhXhxDrSWxiEXWXCSMoXUIuXISOpUyh",_Budget.getname());
    Assertions.assertEquals("neXInxJEjlaULYWkOcoyHlAfnCwpxBQmxstOfTeWTCwsrEGwKhOEQlIOSUBlubTufXrTEaTMujYwclvxqsnSChgGlPvQSskiBNZLlrdUBJaTLfXCtCnBsxjkbodCtCOgNalFBKxAGmadVsPxYwgIOiKDyRHHRgwEBuPoBdDrZcxALOJdDpiYPmRtxLfrWoGdUxyJXTdPoFJqjIDwWfkHxtDYWACnrWftKdtYVvIZgojGUTTIMscqBOTuMPmRd",_Budget.getdetails());
    Assertions.assertEquals(test,_Budget.getstart_date());
    Assertions.assertEquals(96.71,_Budget.getlimit_amount());
    Assertions.assertEquals("s",_Budget.getcurrency_code_id());
    Assertions.assertTrue(_Budget.getis_active());
    Assertions.assertEquals(test,_Budget.getcreated_at());
    Assertions.assertEquals(test,_Budget.getupdated_at());
  }

/**
 <p> Tests That the Parameterized Constructor for the Budget object works </p>
 */
  @Test
  public void testBudgetKeyedParameterizedConstructorSetsKeyedVariables(){
    Budget _Budget= new Budget(
        "ZIKvwtfhZiJmQTcfEZisgVLwudKQalVRWrDX"
    );
    Assertions.assertEquals("ZIKvwtfhZiJmQTcfEZisgVLwudKQalVRWrDX",_Budget.getbudget_id());
    Assertions.assertNull(_Budget.getuser_id());
    Assertions.assertNull(_Budget.getname());
    Assertions.assertNull(_Budget.getdetails());
    Assertions.assertNull(_Budget.getstart_date());
    Assertions.assertNull(_Budget.getlimit_amount());
    Assertions.assertNull(_Budget.getcurrency_code_id());
    Assertions.assertFalse(_Budget.getis_active());
    Assertions.assertNull(_Budget.getcreated_at());
    Assertions.assertNull(_Budget.getupdated_at());
  }

/**
 <p> Tests That the Setters for the Budget.budget_id field can throw exceptions with invalid inputs </p>
 */
  @Test
  public void  testBudgetThrowsIllegalArgumentExceptionIfbudget_idTooShort(){
    String budget_id = "wS";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget.setbudget_id(budget_id);});
  }

/**
 <p> Tests That the Setters for the Budget.budget_id field can throw exceptions with invalid inputs </p>
 */
  @Test
  public void  testBudgetThrowsIllegalArgumentExceptionIfbudget_idTooLong(){
    String budget_id = "dEqtcXdPxHkddEwNBSUKJIXXywkbgWObQTFtKg";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget.setbudget_id(budget_id);});
  }

  /**
   <p> Tests That the Setters for the Budget.budget_id field work </p>
   */
  @Test
  public void testSetbudget_idSetsbudget_id(){
    String budget_id = "SZBkgStpVdwZbhlLyguRdUVCfsNyppiWgRdp";
    _Budget.setbudget_id(budget_id);
    Assertions.assertEquals(budget_id,_Budget.getbudget_id());
  }

/**
 <p> Tests That the Setters for the Budget.user_id field can throw exceptions with invalid inputs </p>
 */
  @Test
  public void  testBudgetThrowsIllegalArgumentExceptionIfuser_idTooShort(){
    String user_id = "kQ";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget.setuser_id(user_id);});
  }

/**
 <p> Tests That the Setters for the Budget.user_id field can throw exceptions with invalid inputs </p>
 */
  @Test
  public void  testBudgetThrowsIllegalArgumentExceptionIfuser_idTooLong(){
    String user_id = "rYoApINflAofVelOwrVZlqDoSNCiKAluBjGjyh";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget.setuser_id(user_id);});
  }

  /**
   <p> Tests That the Setters for the Budget.user_id field work </p>
   */
  @Test
  public void testSetuser_idSetsuser_id(){
    String user_id = "MSDpsjQgrbmYvUOxTKRKwayDkocCHNrhPH";
    _Budget.setuser_id(user_id);
    Assertions.assertEquals(user_id,_Budget.getuser_id());
  }

/**
 <p> Tests That the Setters for the Budget.name field can throw exceptions with invalid inputs </p>
 */
  @Test
  public void  testBudgetThrowsIllegalArgumentExceptionIfnameTooShort(){
    String name = "hB";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget.setname(name);});
  }

/**
 <p> Tests That the Setters for the Budget.name field can throw exceptions with invalid inputs </p>
 */
  @Test
  public void  testBudgetThrowsIllegalArgumentExceptionIfnameTooLong(){
    String name = "JFBKHWbPxKmleVnVBVppxDpZnfNavROJlApKWmlepPqDSNkKLTGT";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget.setname(name);});
  }

  /**
   <p> Tests That the Setters for the Budget.name field work </p>
   */
  @Test
  public void testSetnameSetsname(){
    String name = "gpCRwMoSGedBSYCLpNwiYVkxIcOfITURZgdIjYhMLrlRtjOn";
    _Budget.setname(name);
    Assertions.assertEquals(name,_Budget.getname());
  }

/**
 <p> Tests That the Setters for the Budget.details field can throw exceptions with invalid inputs </p>
 */
  @Test
  public void  testBudgetThrowsIllegalArgumentExceptionIfdetailsTooShort(){
    String details = "iH";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget.setdetails(details);});
  }

/**
 <p> Tests That the Setters for the Budget.details field can throw exceptions with invalid inputs </p>
 */
  @Test
  public void  testBudgetThrowsIllegalArgumentExceptionIfdetailsTooLong(){
    String details = "aAspEUxCUUWxEjlJdFoxDmLQxfNsArZLmRMmWnlVTrnsgbIehtJMgJRFijJUvlQXLvpiALgZTeQEFSUJCPFsDQECLxVqGWJvdnyswpmTggnddqyRWjASSsUXuJwdtuIhKfEZRVnXUyEWVpdkZbwnURWCJsiIYHjeaAmatkGrJHEwGciQEyISddkvLwjknopNsgDOprBUdvLSBTBeJvQinttuZVjPhjNuMNoJdZbOGAANYhXRnLDvVmOUubYfdUOEc";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget.setdetails(details);});
  }

  /**
   <p> Tests That the Setters for the Budget.details field work </p>
   */
  @Test
  public void testSetdetailsSetsdetails(){
    String details = "oGhrAjTBbntgkxYfQZyNCBvdkkJosQcvKTcTKZZOFhhLtlonDVHMdxhXXqCVgqgoRSgKOAxwSNYqyiUkJVRMeLvTWEkXpBVVZGsGcMfAVJxyDfIuTYMEgNuHuMMDMNrXaJeLPBxTTXcvIVoeeKQZsvuZtDvvParXZPandDyiGSJXcNMFrLkVFDpoxhYaagaWKUcgmoWsNnKUCRoiqWUHBUMkxveYIZkfAYrcYEWTqOeZvWcqjPLUUpIrhOslE";
    _Budget.setdetails(details);
    Assertions.assertEquals(details,_Budget.getdetails());
  }

/**
 <p> Tests That the Setters for the Budget.start_date field can throw exceptions with invalid inputs </p>
 */
  @Test
  public void testBudgetThrowsIllegalArgumentExceptionIfstart_dateTooSmall() throws ParseException {
    String strDate = "03/03/1990";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget.setstart_date(date);});
  }

/**
 <p> Tests That the Setters for the Budget.start_date field can throw exceptions with invalid inputs </p>
 */
  @Test
  public void testBudgetThrowsIllegalArgumentExceptionIfstart_dateTooBig() throws ParseException{
    String strDate = "01/01/2190";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget.setstart_date(date);});
  }

/**
 <p> Tests That the Setters for the Budget.start_date field work </p>
 */
  @Test
  public void testBudgetSetsstart_date() throws ParseException{
    String strDate = "25/2/2026";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    _Budget.setstart_date(date);
    Assertions.assertEquals(date, _Budget.getstart_date());
  }

/**
 <p> Tests That the Setters for the Budget.limit_amount field can throw exceptions with invalid inputs </p>
 */
  @Test
  public void testBudgetThrowsIllegalArgumentExceptionIflimit_amountTooSmall(){
    double limit_amount = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget.setlimit_amount(limit_amount);});
  }

/**
 <p> Tests That the Setters for the Budget.limit_amount field can throw exceptions with invalid inputs </p>
 */
  @Test
  public void testBudgetThrowsIllegalArgumentExceptionIflimit_amountTooBig(){
    double limit_amount = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget.setlimit_amount(limit_amount);});
  }

/**
 <p> Tests That the Setters for the Budget.limit_amount field work </p>
 */
  @Test
  public void testBudgetSetslimit_amount(){
    double limit_amount = 4433;
    _Budget.setlimit_amount(limit_amount);
    Assertions.assertEquals(limit_amount, _Budget.getlimit_amount());
  }

/**
 <p> Tests That the Setters for the Budget.currency_code_id field can throw exceptions with invalid inputs </p>
 */
  @Test
  public void  testBudgetThrowsIllegalArgumentExceptionIfcurrency_code_idTooShort(){
    String currency_code_id = "iB";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget.setcurrency_code_id(currency_code_id);});
  }

/**
 <p> Tests That the Setters for the Budget.currency_code_id field can throw exceptions with invalid inputs </p>
 */
  @Test
  public void  testBudgetThrowsIllegalArgumentExceptionIfcurrency_code_idTooLong(){
    String currency_code_id = "svGXR";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget.setcurrency_code_id(currency_code_id);});
  }

  /**
   <p> Tests That the Setters for the Budget.currency_code_id field work </p>
   */
  @Test
  public void testSetcurrency_code_idSetscurrency_code_id(){
    String currency_code_id = "xyz";
    _Budget.setcurrency_code_id(currency_code_id);
    Assertions.assertEquals(currency_code_id,_Budget.getcurrency_code_id());
  }

/**
 <p> Tests That the Setters for the Budget.is_active field work </p>
 */
  @Test
  public void testBudgetSetsis_activeasFalse(){
    boolean status = false;
    _Budget.setis_active(status);
    Assertions.assertEquals(status, _Budget.getis_active());
  }

/**
 <p> Tests That the Setters for the Budget.is_active field work </p>
 */
  @Test
  public void testBudgetSetsis_activeasTrue(){
    boolean status = true;
    _Budget.setis_active(status);
    Assertions.assertEquals(status, _Budget.getis_active());
  }

/**
 <p> Tests That the Setters for the Budget.created_at field can throw exceptions with invalid inputs </p>
 */
  @Test
  public void testBudgetThrowsIllegalArgumentExceptionIfcreated_atTooSmall() throws ParseException{
    String strDate = "03/03/1990";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget.setcreated_at(date);});
  }

/**
 <p> Tests That the Setters for the Budget.created_at field can throw exceptions with invalid inputs </p>
 */
  @Test
  public void testBudgetThrowsIllegalArgumentExceptionIfcreated_atTooBig() throws ParseException{
    String strDate = "01/01/2190";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget.setcreated_at(date);});
  }

/**
 <p> Tests That the Setters for the Budget.created_at field work </p>
 */
  @Test
  public void testBudgetSetscreated_at() throws ParseException{
    String strDate = "25/2/2026";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    _Budget.setcreated_at(date);
    Assertions.assertEquals(date, _Budget.getcreated_at());
  }

/**
 <p> Tests That the Setters for the Budget.updated_at field can throw exceptions with invalid inputs </p>
 */
  @Test
  public void testBudgetThrowsIllegalArgumentExceptionIfupdated_atTooSmall() throws ParseException{
    String strDate = "03/03/1990";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget.setupdated_at(date);});
  }

/**
 <p> Tests That the Setters for the Budget.updated_at field can throw exceptions with invalid inputs </p>
 */
  @Test
  public void testBudgetThrowsIllegalArgumentExceptionIfupdated_atTooBig() throws ParseException{
    String strDate = "01/01/2190";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget.setupdated_at(date);});
  }

/**
 <p> Tests That the Setters for the Budget.updated_at field work </p>
 */
  @Test
  public void testBudgetSetsupdated_at() throws ParseException{
    String strDate = "25/2/2026";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    _Budget.setupdated_at(date);
    Assertions.assertEquals(date, _Budget.getupdated_at());
  }


  /**
   <p> Tests That the CompareTo Method for the Budget object works </p>
   */
  @Test
  public void testCompareToCanCompareForEachDateField()throws ParseException {
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Budget smaller = new Budget();
    Budget bigger = new Budget();
//to compare a smaller and larger budget_id
    smaller.setbudget_id("aaaa");
    bigger.setbudget_id("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the budget_id as equal.
    smaller.setbudget_id("bbbb");
//to compare a smaller and larger user_id
    smaller.setuser_id("aaaa");
    bigger.setuser_id("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the user_id as equal.
    smaller.setuser_id("bbbb");
//to compare a smaller and larger name
    smaller.setname("aaaa");
    bigger.setname("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the name as equal.
    smaller.setname("bbbb");
//to compare a smaller and larger details
    smaller.setdetails("aaaa");
    bigger.setdetails("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the details as equal.
    smaller.setdetails("bbbb");
//to compare a smaller and larger start_date
    smaller.setstart_date(df.parse("01/01/2023"));
    bigger.setstart_date(df.parse("01/01/2024"));
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the start_date as equal.
    smaller.setstart_date(df.parse("01/01/2024"));
//to compare a smaller and larger limit_amount
    smaller.setlimit_amount(10.23d);
    bigger.setlimit_amount(14.12d);
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the limit_amount as equal.
    smaller.setlimit_amount(14.12d);
//to compare a smaller and larger currency_code_id
    smaller.setcurrency_code_id("aaa");
    bigger.setcurrency_code_id("bbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the currency_code_id as equal.
    smaller.setcurrency_code_id("bbb");
//to compare a smaller and larger is_active
    smaller.setis_active(false);
    bigger.setis_active(true);
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the is_active as equal.
    smaller.setis_active(true);
//to compare a smaller and larger created_at
    smaller.setcreated_at(df.parse("01/01/2023"));
    bigger.setcreated_at(df.parse("01/01/2024"));
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the created_at as equal.
    smaller.setcreated_at(df.parse("01/01/2024"));
//to compare a smaller and larger updated_at
    smaller.setupdated_at(df.parse("01/01/2023"));
    bigger.setupdated_at(df.parse("01/01/2024"));
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the updated_at as equal.
    smaller.setupdated_at(df.parse("01/01/2024"));
    Assertions.assertTrue(bigger.compareTo(smaller)==0);
  }

}

