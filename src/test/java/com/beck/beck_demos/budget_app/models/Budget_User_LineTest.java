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
class Budget_User_LineTest {
  private Budget_User_Line _Budget_User_Line;
  @BeforeEach
  public void setup(){
    _Budget_User_Line = new Budget_User_Line();
  }
  @AfterEach
  public void teardown(){
    _Budget_User_Line = null;
  }

  /**
   <p> Tests That the default constructor for the Budget_User_Line object works </p>
   */
  @Test
  public void testBudget_User_LineDefaultConstructorSetsNoVariables(){
    Budget_User_Line _Budget_User_Line= new Budget_User_Line();
    Assertions.assertNull(_Budget_User_Line.getBudget_User_Line_id());
    Assertions.assertNull(_Budget_User_Line.getuser_id());
    Assertions.assertNull(_Budget_User_Line.getbudget_id());
    Assertions.assertNull(_Budget_User_Line.getbudget_role_id());
    Assertions.assertNull(_Budget_User_Line.getcreated_at());
    Assertions.assertNull(_Budget_User_Line.getupdated_at());
  }

  /**
   <p> Tests That the Parameterized Constructor for the Budget_User_Line object works </p>
   */
  @Test
  public void testBudget_User_LineParameterizedConstructorSetsAllVariables(){
    Budget_User_Line _Budget_User_Line= new Budget_User_Line(
        "gVQrFKOSErmsZVOXefJlDACGdhVuVLMSFNBI",
        "BFiDecXlJyMisLdNTFHIiWrGYNIkMSXeAZ",
        "gTVZhqVigaAcPyAFsZecpvLUPBvgnDrkws",
        "qjhZqqGJZqNNSRkaoDJEYpbhtciOeWHPXYbmgUGiiURQusVS",
        new Date()
        ,
        new Date()

    );
    Assertions.assertEquals("gVQrFKOSErmsZVOXefJlDACGdhVuVLMSFNBI",_Budget_User_Line.getBudget_User_Line_id());
    Assertions.assertEquals("BFiDecXlJyMisLdNTFHIiWrGYNIkMSXeAZ",_Budget_User_Line.getuser_id());
    Assertions.assertEquals("gTVZhqVigaAcPyAFsZecpvLUPBvgnDrkws",_Budget_User_Line.getbudget_id());
    Assertions.assertEquals("qjhZqqGJZqNNSRkaoDJEYpbhtciOeWHPXYbmgUGiiURQusVS",_Budget_User_Line.getbudget_role_id());
    Assertions.assertEquals(new Date(),_Budget_User_Line.getcreated_at());
    Assertions.assertEquals(new Date(),_Budget_User_Line.getupdated_at());
  }

  /**
   <p> Tests That the Parameterized Constructor for the Budget_User_Line object works </p>
   */
  @Test
  public void testBudget_User_LineKeyedParameterizedConstructorSetsKeyedVariables(){
    Budget_User_Line _Budget_User_Line= new Budget_User_Line(
        "PpHytFGCtSyCbpBnwiQsTWQXSlAninXaDXgM"
    );
    Assertions.assertEquals("PpHytFGCtSyCbpBnwiQsTWQXSlAninXaDXgM",_Budget_User_Line.getBudget_User_Line_id());
    Assertions.assertNull(_Budget_User_Line.getuser_id());
    Assertions.assertNull(_Budget_User_Line.getbudget_id());
    Assertions.assertNull(_Budget_User_Line.getbudget_role_id());
    Assertions.assertNull(_Budget_User_Line.getcreated_at());
    Assertions.assertNull(_Budget_User_Line.getupdated_at());
  }

  /**
   <p> Tests That the Setters for the Budget_User_Line.Budget_User_Line_id field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testBudget_User_LineThrowsIllegalArgumentExceptionIfBudget_User_Line_idTooShort(){
    String Budget_User_Line_id = "Hn";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_User_Line.setBudget_User_Line_id(Budget_User_Line_id);});
  }

  /**
   <p> Tests That the Setters for the Budget_User_Line.Budget_User_Line_id field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testBudget_User_LineThrowsIllegalArgumentExceptionIfBudget_User_Line_idTooLong(){
    String Budget_User_Line_id = "ZcRcyRsQRlfRjWErifIERTFuXYUaouqPBnRGZv";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_User_Line.setBudget_User_Line_id(Budget_User_Line_id);});
  }

  /**
   <p> Tests That the Setters for the Budget_User_Line.Budget_User_Line_id field work </p>
   */
  @Test
  public void testSetBudget_User_Line_idSetsBudget_User_Line_id(){
    String Budget_User_Line_id = "IFpGyHeVRxEtTlFwLpWsGDDNcLhcoXvoaYsM";
    _Budget_User_Line.setBudget_User_Line_id(Budget_User_Line_id);
    Assertions.assertEquals(Budget_User_Line_id,_Budget_User_Line.getBudget_User_Line_id());
  }

  /**
   <p> Tests That the Setters for the Budget_User_Line.user_id field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testBudget_User_LineThrowsIllegalArgumentExceptionIfuser_idTooShort(){
    String user_id = "DT";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_User_Line.setuser_id(user_id);});
  }

  /**
   <p> Tests That the Setters for the Budget_User_Line.user_id field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testBudget_User_LineThrowsIllegalArgumentExceptionIfuser_idTooLong(){
    String user_id = "PxDQNMFhVjSiwyvttPwmaOSuNLjHGXIAlyWFAM";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_User_Line.setuser_id(user_id);});
  }

  /**
   <p> Tests That the Setters for the Budget_User_Line.user_id field work </p>
   */
  @Test
  public void testSetuser_idSetsuser_id(){
    String user_id = "OgdGdYcXSIDShjxsKJocobquitJwMswIid";
    _Budget_User_Line.setuser_id(user_id);
    Assertions.assertEquals(user_id,_Budget_User_Line.getuser_id());
  }

  /**
   <p> Tests That the Setters for the Budget_User_Line.budget_id field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testBudget_User_LineThrowsIllegalArgumentExceptionIfbudget_idTooShort(){
    String budget_id = "nX";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_User_Line.setbudget_id(budget_id);});
  }

  /**
   <p> Tests That the Setters for the Budget_User_Line.budget_id field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testBudget_User_LineThrowsIllegalArgumentExceptionIfbudget_idTooLong(){
    String budget_id = "xYnifCAuCZEnEbgiXetVHOcmpKiqVXdWnpLXgs";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_User_Line.setbudget_id(budget_id);});
  }

  /**
   <p> Tests That the Setters for the Budget_User_Line.budget_id field work </p>
   */
  @Test
  public void testSetbudget_idSetsbudget_id(){
    String budget_id = "AOXmoIeetvFySYbWtPKRLhgCZXjOVPCeew";
    _Budget_User_Line.setbudget_id(budget_id);
    Assertions.assertEquals(budget_id,_Budget_User_Line.getbudget_id());
  }

  /**
   <p> Tests That the Setters for the Budget_User_Line.budget_role_id field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testBudget_User_LineThrowsIllegalArgumentExceptionIfbudget_role_idTooShort(){
    String budget_role_id = "en";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_User_Line.setbudget_role_id(budget_role_id);});
  }

  /**
   <p> Tests That the Setters for the Budget_User_Line.budget_role_id field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testBudget_User_LineThrowsIllegalArgumentExceptionIfbudget_role_idTooLong(){
    String budget_role_id = "dLotPXQqUNnWihCipkUktVgDhswuFeylWnXdJUAbkenvbOojZrQJ";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_User_Line.setbudget_role_id(budget_role_id);});
  }

  /**
   <p> Tests That the Setters for the Budget_User_Line.budget_role_id field work </p>
   */
  @Test
  public void testSetbudget_role_idSetsbudget_role_id(){
    String budget_role_id = "QNMucxsQsXkjvfuXGKLVvuYgkRLIKflhbFKhabiiSsEAvTWd";
    _Budget_User_Line.setbudget_role_id(budget_role_id);
    Assertions.assertEquals(budget_role_id,_Budget_User_Line.getbudget_role_id());
  }

  /**
   <p> Tests That the Setters for the Budget_User_Line.created_at field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testBudget_User_LineThrowsIllegalArgumentExceptionIfcreated_atTooSmall() throws ParseException, ParseException {
    String strDate = "03/03/1990";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_User_Line.setcreated_at(date);});
  }

  /**
   <p> Tests That the Setters for the Budget_User_Line.created_at field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testBudget_User_LineThrowsIllegalArgumentExceptionIfcreated_atTooBig() throws ParseException{
    String strDate = "01/01/2190";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_User_Line.setcreated_at(date);});
  }

  /**
   <p> Tests That the Setters for the Budget_User_Line.created_at field work </p>
   */
  @Test
  public void testBudget_User_LineSetscreated_at() throws ParseException{
    String strDate = "25/2/2026";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    _Budget_User_Line.setcreated_at(date);
    Assertions.assertEquals(date, _Budget_User_Line.getcreated_at());
  }

  /**
   <p> Tests That the Setters for the Budget_User_Line.updated_at field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testBudget_User_LineThrowsIllegalArgumentExceptionIfupdated_atTooSmall() throws ParseException{
    String strDate = "03/03/1990";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_User_Line.setupdated_at(date);});
  }

  /**
   <p> Tests That the Setters for the Budget_User_Line.updated_at field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testBudget_User_LineThrowsIllegalArgumentExceptionIfupdated_atTooBig() throws ParseException{
    String strDate = "01/01/2190";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_Budget_User_Line.setupdated_at(date);});
  }

  /**
   <p> Tests That the Setters for the Budget_User_Line.updated_at field work </p>
   */
  @Test
  public void testBudget_User_LineSetsupdated_at() throws ParseException{
    String strDate = "25/2/2026";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    _Budget_User_Line.setupdated_at(date);
    Assertions.assertEquals(date, _Budget_User_Line.getupdated_at());
  }


  /**
   <p> Tests That the CompareTo Method for the Budget_User_Line object works </p>
   */
  @Test
  public void testCompareToCanCompareForEachDateField()throws ParseException {
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Budget_User_Line smaller = new Budget_User_Line();
    Budget_User_Line bigger = new Budget_User_Line();
//to compare a smaller and larger Budget_User_Line_id
    smaller.setBudget_User_Line_id("aaaa");
    bigger.setBudget_User_Line_id("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Budget_User_Line_id as equal.
    smaller.setBudget_User_Line_id("bbbb");
//to compare a smaller and larger user_id
    smaller.setuser_id("aaaa");
    bigger.setuser_id("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the user_id as equal.
    smaller.setuser_id("bbbb");
//to compare a smaller and larger budget_id
    smaller.setbudget_id("aaaa");
    bigger.setbudget_id("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the budget_id as equal.
    smaller.setbudget_id("bbbb");
//to compare a smaller and larger budget_role_id
    smaller.setbudget_role_id("aaaa");
    bigger.setbudget_role_id("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the budget_role_id as equal.
    smaller.setbudget_role_id("bbbb");
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

