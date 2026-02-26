package com.beck.beck_demos.budget_app.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
class Budget_Line_ItemTest {
  private Budget_Line_Item _Budget_Line_Item;
  @BeforeEach
  public void setup(){
    _Budget_Line_Item = new Budget_Line_Item();
  }
  @AfterEach
  public void teardown(){
    _Budget_Line_Item = null;
  }

  /**
   <p> Tests That the default constructor for the Budget_Line_Item object works </p>
   */
  @Test
  public void testBudget_Line_ItemDefaultConstructorSetsNoVariables(){
    Budget_Line_Item _Budget_Line_Item= new Budget_Line_Item();
    Assertions.assertNull(_Budget_Line_Item.getBudget_Line_Item_id());
    Assertions.assertNull(_Budget_Line_Item.getbudget_id());
    Assertions.assertNull(_Budget_Line_Item.getname());
    Assertions.assertNull(_Budget_Line_Item.getdetails());
    Assertions.assertNull(_Budget_Line_Item.getline_item_date());
    Assertions.assertNull(_Budget_Line_Item.getamount());
    Assertions.assertNull(_Budget_Line_Item.getbudget_line_type_id());
    Assertions.assertNull(_Budget_Line_Item.getbudget_line_status_id());
    Assertions.assertNull(_Budget_Line_Item.gettransaction_id());
    Assertions.assertNull(_Budget_Line_Item.getcreated_at());
    Assertions.assertNull(_Budget_Line_Item.getupdated_at());
  }

  /**
   <p> Tests That the Parameterized Constructor for the Budget_Line_Item object works </p>
   */
  @Test
  public void testBudget_Line_ItemParameterizedConstructorSetsAllVariables(){
    Budget_Line_Item _Budget_Line_Item= new Budget_Line_Item(
        "CEQSJYTDlaysmaOPTErQWeGQnBCbPHornulM",
        "pRRbKtIIEqysBLqwEedpKBUWRZJBxfClhM",
        "awMvCIEQqmIRvCowtPhXhxDrSWxiEXWXCSMoXUIuXISOpUyh",
        "neXInxJEjlaULYWkOcoyHlAfnCwpxBQmxstOfTeWTCwsrEGwKhOEQlIOSUBlubTufXrTEaTMujYwclvxqsnSChgGlPvQSskiBNZLlrdUBJaTLfXCtCnBsxjkbodCtCOgNalFBKxAGmadVsPxYwgIOiKDyRHHRgwEBuPoBdDrZcxALOJdDpiYPmRtxLfrWoGdUxyJXTdPoFJqjIDwWfkHxtDYWACnrWftKdtYVvIZgojGUTTIMscqBOTuMPmRd",
        new Date()
        ,
        96.71,
        "sICbRZIKvwtfhZiJmQTcfEZisgVLwudKQalVRWrDXwSdEqtc",
        "XdPxHkddEwNBSUKJIXXywkbgWObQTFtKgSZBkgStpVdwZbhl",
        "LyguRdUVCfsNyppiWgRdpkQrYoApINflAo",
        new Date()
        ,
        new Date()

    );
    Assertions.assertEquals("CEQSJYTDlaysmaOPTErQWeGQnBCbPHornulM",_Budget_Line_Item.getBudget_Line_Item_id());
    Assertions.assertEquals("pRRbKtIIEqysBLqwEedpKBUWRZJBxfClhM",_Budget_Line_Item.getbudget_id());
    Assertions.assertEquals("awMvCIEQqmIRvCowtPhXhxDrSWxiEXWXCSMoXUIuXISOpUyh",_Budget_Line_Item.getname());
    Assertions.assertEquals("neXInxJEjlaULYWkOcoyHlAfnCwpxBQmxstOfTeWTCwsrEGwKhOEQlIOSUBlubTufXrTEaTMujYwclvxqsnSChgGlPvQSskiBNZLlrdUBJaTLfXCtCnBsxjkbodCtCOgNalFBKxAGmadVsPxYwgIOiKDyRHHRgwEBuPoBdDrZcxALOJdDpiYPmRtxLfrWoGdUxyJXTdPoFJqjIDwWfkHxtDYWACnrWftKdtYVvIZgojGUTTIMscqBOTuMPmRd",_Budget_Line_Item.getdetails());
    Assertions.assertEquals(new Date(),_Budget_Line_Item.getline_item_date());
    Assertions.assertEquals(96.71,_Budget_Line_Item.getamount());
    Assertions.assertEquals("sICbRZIKvwtfhZiJmQTcfEZisgVLwudKQalVRWrDXwSdEqtc",_Budget_Line_Item.getbudget_line_type_id());
    Assertions.assertEquals("XdPxHkddEwNBSUKJIXXywkbgWObQTFtKgSZBkgStpVdwZbhl",_Budget_Line_Item.getbudget_line_status_id());
    Assertions.assertEquals("LyguRdUVCfsNyppiWgRdpkQrYoApINflAo",_Budget_Line_Item.gettransaction_id());
    Assertions.assertEquals(new Date(),_Budget_Line_Item.getcreated_at());
    Assertions.assertEquals(new Date(),_Budget_Line_Item.getupdated_at());
  }

  /**
   <p> Tests That the Parameterized Constructor for the Budget_Line_Item object works </p>
   */
  @Test
  public void testBudget_Line_ItemKeyedParameterizedConstructorSetsKeyedVariables(){
    Budget_Line_Item _Budget_Line_Item= new Budget_Line_Item(
        "rVZlqDoSNCiKAluBjGjyhMSDpsjQgrbmYvUO"
    );
    Assertions.assertEquals("rVZlqDoSNCiKAluBjGjyhMSDpsjQgrbmYvUO",_Budget_Line_Item.getBudget_Line_Item_id());
    Assertions.assertNull(_Budget_Line_Item.getbudget_id());
    Assertions.assertNull(_Budget_Line_Item.getname());
    Assertions.assertNull(_Budget_Line_Item.getdetails());
    Assertions.assertNull(_Budget_Line_Item.getline_item_date());
    Assertions.assertNull(_Budget_Line_Item.getamount());
    Assertions.assertNull(_Budget_Line_Item.getbudget_line_type_id());
    Assertions.assertNull(_Budget_Line_Item.getbudget_line_status_id());
    Assertions.assertNull(_Budget_Line_Item.gettransaction_id());
    Assertions.assertNull(_Budget_Line_Item.getcreated_at());
    Assertions.assertNull(_Budget_Line_Item.getupdated_at());
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.Budget_Line_Item_id field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testBudget_Line_ItemThrowsIllegalArgumentExceptionIfBudget_Line_Item_idTooShort(){
    String Budget_Line_Item_id = "xT";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_Line_Item.setBudget_Line_Item_id(Budget_Line_Item_id);});
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.Budget_Line_Item_id field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testBudget_Line_ItemThrowsIllegalArgumentExceptionIfBudget_Line_Item_idTooLong(){
    String Budget_Line_Item_id = "KRKwayDkocCHNrhPHhBJFBKHWbPxKmleVnVBVp";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_Line_Item.setBudget_Line_Item_id(Budget_Line_Item_id);});
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.Budget_Line_Item_id field work </p>
   */
  @Test
  public void testSetBudget_Line_Item_idSetsBudget_Line_Item_id(){
    String Budget_Line_Item_id = "pxDpZnfNavROJlApKWmlepPqDSNkKLTGTgpC";
    _Budget_Line_Item.setBudget_Line_Item_id(Budget_Line_Item_id);
    Assertions.assertEquals(Budget_Line_Item_id,_Budget_Line_Item.getBudget_Line_Item_id());
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.budget_id field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testBudget_Line_ItemThrowsIllegalArgumentExceptionIfbudget_idTooShort(){
    String budget_id = "Rw";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_Line_Item.setbudget_id(budget_id);});
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.budget_id field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testBudget_Line_ItemThrowsIllegalArgumentExceptionIfbudget_idTooLong(){
    String budget_id = "MoSGedBSYCLpNwiYVkxIcOfITURZgdIjYhMLrl";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_Line_Item.setbudget_id(budget_id);});
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.budget_id field work </p>
   */
  @Test
  public void testSetbudget_idSetsbudget_id(){
    String budget_id = "RtjOniHaAspEUxCUUWxEjlJdFoxDmLQxfN";
    _Budget_Line_Item.setbudget_id(budget_id);
    Assertions.assertEquals(budget_id,_Budget_Line_Item.getbudget_id());
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.name field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testBudget_Line_ItemThrowsIllegalArgumentExceptionIfnameTooShort(){
    String name = "sA";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_Line_Item.setname(name);});
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.name field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testBudget_Line_ItemThrowsIllegalArgumentExceptionIfnameTooLong(){
    String name = "rZLmRMmWnlVTrnsgbIehtJMgJRFijJUvlQXLvpiALgZTeQEFSUJC";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_Line_Item.setname(name);});
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.name field work </p>
   */
  @Test
  public void testSetnameSetsname(){
    String name = "PFsDQECLxVqGWJvdnyswpmTggnddqyRWjASSsUXuJwdtuIhK";
    _Budget_Line_Item.setname(name);
    Assertions.assertEquals(name,_Budget_Line_Item.getname());
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.details field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testBudget_Line_ItemThrowsIllegalArgumentExceptionIfdetailsTooShort(){
    String details = "fE";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_Line_Item.setdetails(details);});
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.details field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testBudget_Line_ItemThrowsIllegalArgumentExceptionIfdetailsTooLong(){
    String details = "ZRVnXUyEWVpdkZbwnURWCJsiIYHjeaAmatkGrJHEwGciQEyISddkvLwjknopNsgDOprBUdvLSBTBeJvQinttuZVjPhjNuMNoJdZbOGAANYhXRnLDvVmOUubYfdUOEcoGhrAjTBbntgkxYfQZyNCBvdkkJosQcvKTcTKZZOFhhLtlonDVHMdxhXXqCVgqgoRSgKOAxwSNYqyiUkJVRMeLvTWEkXpBVVZGsGcMfAVJxyDfIuTYMEgNuHuMMDMNrXaJe";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_Line_Item.setdetails(details);});
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.details field work </p>
   */
  @Test
  public void testSetdetailsSetsdetails(){
    String details = "LPBxTTXcvIVoeeKQZsvuZtDvvParXZPandDyiGSJXcNMFrLkVFDpoxhYaagaWKUcgmoWsNnKUCRoiqWUHBUMkxveYIZkfAYrcYEWTqOeZvWcqjPLUUpIrhOslEUZiBsvGXRxnxGbCbYJQebjkjpbCmsLktfkRwNapSKlKfSirfLtXWQMUhMjEtlgCVqGLRioPyJKrJGhQTMYVdlUTcFqXyQTBychbBhVHRQTSdWdYJowjUfZIHKsIihevLkAJ";
    _Budget_Line_Item.setdetails(details);
    Assertions.assertEquals(details,_Budget_Line_Item.getdetails());
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.line_item_date field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testBudget_Line_ItemThrowsIllegalArgumentExceptionIfline_item_dateTooSmall() throws ParseException, ParseException {
    String strDate = "03/03/1990";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_Line_Item.setline_item_date(date);});
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.line_item_date field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testBudget_Line_ItemThrowsIllegalArgumentExceptionIfline_item_dateTooBig() throws ParseException{
    String strDate = "01/01/2190";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_Line_Item.setline_item_date(date);});
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.line_item_date field work </p>
   */
  @Test
  public void testBudget_Line_ItemSetsline_item_date() throws ParseException{
    String strDate = "25/2/2026";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    _Budget_Line_Item.setline_item_date(date);
    Assertions.assertEquals(date, _Budget_Line_Item.getline_item_date());
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.amount field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testBudget_Line_ItemThrowsIllegalArgumentExceptionIfamountTooSmall(){
    double amount = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_Line_Item.setamount(amount);});
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.amount field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testBudget_Line_ItemThrowsIllegalArgumentExceptionIfamountTooBig(){
    double amount = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_Line_Item.setamount(amount);});
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.amount field work </p>
   */
  @Test
  public void testBudget_Line_ItemSetsamount(){
    double amount = 135;
    _Budget_Line_Item.setamount(amount);
    Assertions.assertEquals(amount, _Budget_Line_Item.getamount());
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.budget_line_type_id field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testBudget_Line_ItemThrowsIllegalArgumentExceptionIfbudget_line_type_idTooShort(){
    String budget_line_type_id = "Ay";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_Line_Item.setbudget_line_type_id(budget_line_type_id);});
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.budget_line_type_id field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testBudget_Line_ItemThrowsIllegalArgumentExceptionIfbudget_line_type_idTooLong(){
    String budget_line_type_id = "hNyoWDODTGUONAHWoKDhumNdLAaDTgktHUXIbZdRpqlBELpvARMb";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_Line_Item.setbudget_line_type_id(budget_line_type_id);});
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.budget_line_type_id field work </p>
   */
  @Test
  public void testSetbudget_line_type_idSetsbudget_line_type_id(){
    String budget_line_type_id = "qKEChAyegSrlygbORXmihxIekkcumawZkcSKJxZcOFNVkykK";
    _Budget_Line_Item.setbudget_line_type_id(budget_line_type_id);
    Assertions.assertEquals(budget_line_type_id,_Budget_Line_Item.getbudget_line_type_id());
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.budget_line_status_id field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testBudget_Line_ItemThrowsIllegalArgumentExceptionIfbudget_line_status_idTooShort(){
    String budget_line_status_id = "EV";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_Line_Item.setbudget_line_status_id(budget_line_status_id);});
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.budget_line_status_id field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testBudget_Line_ItemThrowsIllegalArgumentExceptionIfbudget_line_status_idTooLong(){
    String budget_line_status_id = "JCpAvlivyYiEFiHMcjOvPsdWUydJcHvbJMLjfPgnJPOQcwjHbsJM";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_Line_Item.setbudget_line_status_id(budget_line_status_id);});
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.budget_line_status_id field work </p>
   */
  @Test
  public void testSetbudget_line_status_idSetsbudget_line_status_id(){
    String budget_line_status_id = "camFmWvMvItIfDVPyMoNFjTeBlAUhWPfYessjbNGTHwsELcR";
    _Budget_Line_Item.setbudget_line_status_id(budget_line_status_id);
    Assertions.assertEquals(budget_line_status_id,_Budget_Line_Item.getbudget_line_status_id());
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.transaction_id field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testBudget_Line_ItemThrowsIllegalArgumentExceptionIftransaction_idTooShort(){
    String transaction_id = "sw";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_Line_Item.settransaction_id(transaction_id);});
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.transaction_id field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testBudget_Line_ItemThrowsIllegalArgumentExceptionIftransaction_idTooLong(){
    String transaction_id = "WVDlMkuZunLlWjFsWGNyxVLckvaXDnZsKKUrgH";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_Line_Item.settransaction_id(transaction_id);});
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.transaction_id field work </p>
   */
  @Test
  public void testSettransaction_idSetstransaction_id(){
    String transaction_id = "oHSSHVaWUFjulJVgcyxskXRSkAiygFgeNp";
    _Budget_Line_Item.settransaction_id(transaction_id);
    Assertions.assertEquals(transaction_id,_Budget_Line_Item.gettransaction_id());
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.created_at field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testBudget_Line_ItemThrowsIllegalArgumentExceptionIfcreated_atTooSmall() throws ParseException{
    String strDate = "03/03/1990";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_Line_Item.setcreated_at(date);});
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.created_at field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testBudget_Line_ItemThrowsIllegalArgumentExceptionIfcreated_atTooBig() throws ParseException{
    String strDate = "01/01/2190";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_Line_Item.setcreated_at(date);});
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.created_at field work </p>
   */
  @Test
  public void testBudget_Line_ItemSetscreated_at() throws ParseException{
    String strDate = "25/2/2026";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    _Budget_Line_Item.setcreated_at(date);
    Assertions.assertEquals(date, _Budget_Line_Item.getcreated_at());
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.updated_at field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testBudget_Line_ItemThrowsIllegalArgumentExceptionIfupdated_atTooSmall() throws ParseException{
    String strDate = "03/03/1990";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_Line_Item.setupdated_at(date);});
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.updated_at field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testBudget_Line_ItemThrowsIllegalArgumentExceptionIfupdated_atTooBig() throws ParseException{
    String strDate = "01/01/2190";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_Line_Item.setupdated_at(date);});
  }

  /**
   <p> Tests That the Setters for the Budget_Line_Item.updated_at field work </p>
   */
  @Test
  public void testBudget_Line_ItemSetsupdated_at() throws ParseException{
    String strDate = "25/2/2026";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    _Budget_Line_Item.setupdated_at(date);
    Assertions.assertEquals(date, _Budget_Line_Item.getupdated_at());
  }


  /**
   <p> Tests That the CompareTo Method for the Budget_Line_Item object works </p>
   */
  @Test
  public void testCompareToCanCompareForEachDateField()throws ParseException {
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Budget_Line_Item smaller = new Budget_Line_Item();
    Budget_Line_Item bigger = new Budget_Line_Item();
//to compare a smaller and larger Budget_Line_Item_id
    smaller.setBudget_Line_Item_id("aaaa");
    bigger.setBudget_Line_Item_id("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Budget_Line_Item_id as equal.
    smaller.setBudget_Line_Item_id("bbbb");
//to compare a smaller and larger budget_id
    smaller.setbudget_id("aaaa");
    bigger.setbudget_id("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the budget_id as equal.
    smaller.setbudget_id("bbbb");
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
//to compare a smaller and larger line_item_date
    smaller.setline_item_date(df.parse("01/01/2023"));
    bigger.setline_item_date(df.parse("01/01/2024"));
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the line_item_date as equal.
    smaller.setline_item_date(df.parse("01/01/2024"));
//to compare a smaller and larger amount
    smaller.setamount(10.23d);
    bigger.setamount(14.12d);
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the amount as equal.
    smaller.setamount(14.12d);
//to compare a smaller and larger budget_line_type_id
    smaller.setbudget_line_type_id("aaaa");
    bigger.setbudget_line_type_id("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the budget_line_type_id as equal.
    smaller.setbudget_line_type_id("bbbb");
//to compare a smaller and larger budget_line_status_id
    smaller.setbudget_line_status_id("aaaa");
    bigger.setbudget_line_status_id("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the budget_line_status_id as equal.
    smaller.setbudget_line_status_id("bbbb");
//to compare a smaller and larger transaction_id
    smaller.settransaction_id("aaaa");
    bigger.settransaction_id("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the transaction_id as equal.
    smaller.settransaction_id("bbbb");
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

