package com.beck.beck_demos.budget_app.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class Planned_TransactionTest {
  private Planned_Transaction _planned_transaction;

  @BeforeEach
  public void setup() {
    _planned_transaction = new Planned_Transaction();
  }

  @AfterEach
  public void teardown() {
    _planned_transaction = null;
  }

  /**
   * <p> Tests That the default constructor for the Planned_Transaction object works </p>
   */
  @Test
  public void testplanned_transactionDefaultConstructorSetsNoVariables() {
    Planned_Transaction _planned_transaction = new Planned_Transaction();
    Assertions.assertNull(_planned_transaction.getplanned_transaction_ID());
    Assertions.assertNull(_planned_transaction.getuser_ID());
    Assertions.assertNull(_planned_transaction.getsubcategory_ID());
    Assertions.assertNull(_planned_transaction.getnickname());
    Assertions.assertNull(_planned_transaction.getamount());
    Assertions.assertNull(_planned_transaction.getstart_date());
    Assertions.assertNull(_planned_transaction.gettimes_per_year());
    Assertions.assertNull(_planned_transaction.getoccurrences());
    Assertions.assertFalse(_planned_transaction.getis_active());
  }

  /**
   * <p> Tests That the Parameterized Constructor for the Planned_Transaction object works </p>
   */
  @Test
  public void testplanned_transactionParameterizedConstructorSetsAllVariables() {
    Date testDate = new Date();
    Planned_Transaction _planned_transaction = new Planned_Transaction(
        "sfFFmImFOAsXhLXKMtpLgDwVdOReoATnQAVm",
        "ndmwTJLbcHbNVIRKKXcIiIsZydXKtoYFMX",
        "jIskmBHiXYrBdFcuMDfjRsJfxLvhXwCFlO",
        "pFreTFlwttLfwuiLfHVOKoqKUgDbrIivetCa",
        "iXuHllDBHyRbxbUZGNOjEnoLByovrdTLoHxYrBIxsTipbcceNAySGRajDSbHhNmmMuaoVhvpvqSJsLSMWTMAMWoUSiapLFuCVh",
        8.75,
        testDate,
        2988,
        4012,
        true
    );
    Assertions.assertEquals("sfFFmImFOAsXhLXKMtpLgDwVdOReoATnQAVm", _planned_transaction.getplanned_transaction_ID());
    Assertions.assertEquals("ndmwTJLbcHbNVIRKKXcIiIsZydXKtoYFMX", _planned_transaction.getuser_ID());
    Assertions.assertEquals("jIskmBHiXYrBdFcuMDfjRsJfxLvhXwCFlO", _planned_transaction.getsubcategory_ID());
    Assertions.assertEquals("pFreTFlwttLfwuiLfHVOKoqKUgDbrIivetCa", _planned_transaction.getbudget_id());

    Assertions.assertEquals("iXuHllDBHyRbxbUZGNOjEnoLByovrdTLoHxYrBIxsTipbcceNAySGRajDSbHhNmmMuaoVhvpvqSJsLSMWTMAMWoUSiapLFuCVh", _planned_transaction.getnickname());

    Assertions.assertEquals(8.75, _planned_transaction.getamount());
    Assertions.assertEquals(testDate, _planned_transaction.getstart_date());
    Assertions.assertEquals(2988, _planned_transaction.gettimes_per_year());
    Assertions.assertEquals(4012, _planned_transaction.getoccurrences());
    Assertions.assertTrue(_planned_transaction.getis_active());
  }

  /**
   * <p> Tests That the Parameterized Constructor for the Planned_Transaction object works </p>
   */
  @Test
  public void testplanned_transactionKeyedParameterizedConstructorSetsKeyedVariables() {
    Planned_Transaction _planned_transaction = new Planned_Transaction(
        "QVjWtfHPuRtaQLhHkZCyVykkXGNHwiuEjqgY"
    );
    Assertions.assertEquals("QVjWtfHPuRtaQLhHkZCyVykkXGNHwiuEjqgY", _planned_transaction.getplanned_transaction_ID());
    Assertions.assertNull(_planned_transaction.getuser_ID());
    Assertions.assertNull(_planned_transaction.getsubcategory_ID());
    Assertions.assertNull(_planned_transaction.getbudget_id());
    Assertions.assertNull(_planned_transaction.getnickname());
    Assertions.assertNull(_planned_transaction.getamount());
    Assertions.assertNull(_planned_transaction.getstart_date());
    Assertions.assertNull(_planned_transaction.gettimes_per_year());
    Assertions.assertNull(_planned_transaction.getoccurrences());
    Assertions.assertFalse(_planned_transaction.getis_active());
  }

  /**
   * <p> Tests That the Setters for the Planned_Transaction.planned_transaction_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testplanned_transactionThrowsIllegalArgumentExceptionIfplanned_transaction_IDTooShort() {
    String Planned_Transaction_ID = "Zf";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _planned_transaction.setplanned_transaction_ID(Planned_Transaction_ID);
    });
  }

  /**
   * <p> Tests That the Setters for the Planned_Transaction.planned_transaction_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testplanned_transactionThrowsIllegalArgumentExceptionIfplanned_transaction_IDTooLong() {
    String Planned_Transaction_ID = "aRutLUlJAwTyaRBRexynIdBmTLfMqZRyVXEDpC";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _planned_transaction.setplanned_transaction_ID(Planned_Transaction_ID);
    });
  }

  /**
   * <p> Tests That the Setters for the Planned_Transaction.planned_transaction_ID field work </p>
   */
  @Test
  public void testSetplanned_transaction_IDSetsplanned_transaction_ID() {
    String Planned_Transaction_ID = "UTPnxHcCXMhYfwPHAhgIByKtwNnYvYnGlyVD";
    _planned_transaction.setplanned_transaction_ID(Planned_Transaction_ID);
    Assertions.assertEquals(Planned_Transaction_ID, _planned_transaction.getplanned_transaction_ID());
  }

  /**
   * <p> Tests That the Setters for the Planned_Transaction.user_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testplanned_transactionThrowsIllegalArgumentExceptionIfuser_IDTooShort() {
    String user_ID = "DJ";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _planned_transaction.setuser_ID(user_ID);
    });
  }

  /**
   * <p> Tests That the Setters for the Planned_Transaction.user_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testplanned_transactionThrowsIllegalArgumentExceptionIfuser_IDTooLong() {
    String user_ID = "DeksSrZnvQTogUklywhEJXONjpHKveVclIfSZJ";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _planned_transaction.setuser_ID(user_ID);
    });
  }

  /**
   * <p> Tests That the Setters for the Planned_Transaction.user_ID field work </p>
   */
  @Test
  public void testSetuser_IDSetsuser_ID() {
    String user_ID = "RscdHlANWGUEtMWFIdQnGciYDpydSdOuquxx";
    _planned_transaction.setuser_ID(user_ID);
    Assertions.assertEquals(user_ID, _planned_transaction.getuser_ID());
  }

  /**
   * <p> Tests That the Setters for the Planned_Transaction.subcategory_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testplanned_transactionThrowsIllegalArgumentExceptionIfsubcategory_IDTooShort() {
    String subcategory_ID = "Us";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _planned_transaction.setsubcategory_ID(subcategory_ID);
    });
  }

  /**
   * <p> Tests That the Setters for the Planned_Transaction.subcategory_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testplanned_transactionThrowsIllegalArgumentExceptionIfsubcategory_IDTooLong() {
    String subcategory_ID = "ccDtmpQQiRPkKprWQsKDvBcDGICRHnsSlXHTis";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _planned_transaction.setsubcategory_ID(subcategory_ID);
    });
  }

  /**
   * <p> Tests That the Setters for the Planned_Transaction.subcategory_ID field work </p>
   */
  @Test
  public void testSetsubcategory_IDSetssubcategory_ID() {
    String subcategory_ID = "lZYDTEyYHQjTKIgyIMEexxPDdDCjWSjQBhFh";
    _planned_transaction.setsubcategory_ID(subcategory_ID);
    Assertions.assertEquals(subcategory_ID, _planned_transaction.getsubcategory_ID());
  }

  @Test
  public void testplanned_transactionThrowsIllegalArgumentExceptionIfBudget_IDTooShort() {
    String budget_ID = "Us";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _planned_transaction.setbudget_id(budget_ID);
    });
  }

  /**
   * <p> Tests That the Setters for the Planned_Transaction.subcategory_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testplanned_transactionThrowsIllegalArgumentExceptionIfBudget_IDTooLong() {
    String budget_ID = "lZYDTEyYHQjTKIgyIMEexxPDdDCjWSjQBhFhlZYDTEyYHQjTKIgyIMEexxPDdDCjWSjQBhFh";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _planned_transaction.setbudget_id(budget_ID);
    });
  }

  /**
   * <p> Tests That the Setters for the Planned_Transaction.subcategory_ID field work </p>
   */
  @Test
  public void testSetBudget_IDSetsBudget_IDFor36Chars() {
    String budget_ID = "lZYDTEyYHQjTKIgyIMEexxPDdDCjWSjQBhFh";
    _planned_transaction.setbudget_id(budget_ID);
    Assertions.assertEquals(budget_ID, _planned_transaction.getbudget_id());
  }

  /**
   * <p> Tests That the Setters for the Planned_Transaction.subcategory_ID field work </p>
   */
  @Test
  public void testSetBudget_IDSetsBudget_IDForEmpty() {
    String budget_ID = "";
    _planned_transaction.setbudget_id(budget_ID);
    Assertions.assertNull(_planned_transaction.getbudget_id());
  }

  /**
   * <p> Tests That the Setters for the Planned_Transaction.subcategory_ID field work </p>
   */
  @Test
  public void testSetBudget_IDSetsBudget_IDForNull() {
    String budget_ID = null;
    _planned_transaction.setbudget_id(budget_ID);
    Assertions.assertNull(_planned_transaction.getbudget_id());
  }

  /**
   * <p> Tests That the Setters for the Planned_Transaction.nickname field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testplanned_transactionThrowsIllegalArgumentExceptionIfnicknameTooShort() {
    String nickname = "mC";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _planned_transaction.setnickname(nickname);
    });
  }

  /**
   * <p> Tests That the Setters for the Planned_Transaction.nickname field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testplanned_transactionThrowsIllegalArgumentExceptionIfnicknameTooLong() {
    String nickname = "CBYSbcXNygfhoIyiWnjIaYSCxMGhBdqkCZGNCCrmDcMFyRAfMhKBKFkvhRZwgLROgAYPTkrVpFwwyuchUWIaAPAFTTGvBNqRQDjSpn";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _planned_transaction.setnickname(nickname);
    });
  }

  /**
   * <p> Tests That the Setters for the Planned_Transaction.nickname field work </p>
   */
  @Test
  public void testSetnicknameSetsnickname() {
    String nickname = "UAmyiWpPRnIhfjYmNWRBWtEjGgdwCvSluAiGeIpdMEIfevEDmHZvDsCmRRProuKoYEHlJPqewBDsBwroyTDfNbVLTUPNkLrwQU";
    _planned_transaction.setnickname(nickname);
    Assertions.assertEquals(nickname, _planned_transaction.getnickname());
  }

  /**
   * <p> Tests That the Setters for the Planned_Transaction.amount field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testplanned_transactionThrowsIllegalArgumentExceptionIfamountTooSmall() {
    double amount = -200001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _planned_transaction.setamount(amount);
    });
  }

  /**
   * <p> Tests That the Setters for the Planned_Transaction.amount field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testplanned_transactionThrowsIllegalArgumentExceptionIfamountTooBig() {
    double amount = 200001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _planned_transaction.setamount(amount);
    });
  }

  /**
   * <p> Tests That the Setters for the Planned_Transaction.amount field work </p>
   */
  @Test
  public void testplanned_transactionSetsamount() {
    double amount = 2447;
    _planned_transaction.setamount(amount);
    Assertions.assertEquals(amount, _planned_transaction.getamount());
  }

  /**
   * <p> Tests That the Setters for the Planned_Transaction.times_per_year field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testplanned_transactionThrowsIllegalArgumentExceptionIftimes_per_yearTooSmall() {
    int times_per_year = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _planned_transaction.settimes_per_year(times_per_year);
    });
  }

  /**
   * <p> Tests That the Setters for the Planned_Transaction.times_per_year field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testplanned_transactionThrowsIllegalArgumentExceptionIftimes_per_yearTooBig() {
    int times_per_year = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _planned_transaction.settimes_per_year(times_per_year);
    });
  }

  /**
   * <p> Tests That the Setters for the Planned_Transaction.times_per_year field work </p>
   */
  @Test
  public void testplanned_transactionSetstimes_per_year() {
    int times_per_year = 1;
    _planned_transaction.settimes_per_year(times_per_year);
    Assertions.assertEquals(times_per_year, _planned_transaction.gettimes_per_year());
    times_per_year = 2;
    _planned_transaction.settimes_per_year(times_per_year);
    Assertions.assertEquals(times_per_year, _planned_transaction.gettimes_per_year());
    times_per_year = 3;
    _planned_transaction.settimes_per_year(times_per_year);
    Assertions.assertEquals(times_per_year, _planned_transaction.gettimes_per_year());
    times_per_year = 4;
    _planned_transaction.settimes_per_year(times_per_year);
    Assertions.assertEquals(times_per_year, _planned_transaction.gettimes_per_year());
    times_per_year = 6;
    _planned_transaction.settimes_per_year(times_per_year);
    Assertions.assertEquals(times_per_year, _planned_transaction.gettimes_per_year());
    times_per_year = 12;
    _planned_transaction.settimes_per_year(times_per_year);
    Assertions.assertEquals(times_per_year, _planned_transaction.gettimes_per_year());

  }

  /**
   * <p> Tests That the Setters for the Planned_Transaction.occurrences field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testplanned_transactionThrowsIllegalArgumentExceptionIfoccurrencesTooSmall() {
    int occurrences = -2;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _planned_transaction.setoccurrences(occurrences);
    });
  }

  /**
   * <p> Tests That the Setters for the Planned_Transaction.occurrences field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testplanned_transactionThrowsIllegalArgumentExceptionIfoccurrencesTooBig() {
    int occurrences = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _planned_transaction.setoccurrences(occurrences);
    });
  }

  /**
   * <p> Tests That the Setters for the Planned_Transaction.occurrences field work </p>
   */
  @Test
  public void testplanned_transactionSetsoccurrences() {
    int occurrences = 46;
    _planned_transaction.setoccurrences(occurrences);
    Assertions.assertEquals(occurrences, _planned_transaction.getoccurrences());

  }

  /**
   * <p> Tests That the Setters for the Planned_Transaction.is_active field work </p>
   */
  @Test
  public void testplanned_transactionSetsis_activeasFalse() {
    boolean status = false;
    _planned_transaction.setis_active(status);
    Assertions.assertEquals(status, _planned_transaction.getis_active());
  }

  /**
   * <p> Tests That the Setters for the Planned_Transaction.is_active field work </p>
   */
  @Test
  public void testplanned_transactionSetsis_activeasTrue() {
    boolean status = true;
    _planned_transaction.setis_active(status);
    Assertions.assertEquals(status, _planned_transaction.getis_active());
  }

  /**
   * <p> Tests That the CompareTo Method for the Planned_Transaction object works </p>
   */
  @Test
  public void testCompareToCanCompareForEachDateField() throws ParseException {
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Planned_Transaction smaller = new Planned_Transaction();
    Planned_Transaction bigger = new Planned_Transaction();
//to compare a smaller and larger Planned_Transaction_ID
    smaller.setplanned_transaction_ID("6dbc7023-4723-4347-8c22-878285af80fd");
    bigger.setplanned_transaction_ID("7dbc7023-4723-4347-8c22-878285af80fd");
    Assertions.assertTrue(smaller.compareTo(bigger) < 0);
    Assertions.assertTrue(bigger.compareTo(smaller) > 0);
//to set the Planned_Transaction_ID as equal.
    smaller.setplanned_transaction_ID("7dbc7023-4723-4347-8c22-878285af80fd");
//to compare a smaller and larger user_ID
    smaller.setuser_ID("589ef3b2-24e2-4b1c-b38d-22f984d6e0f7");
    bigger.setuser_ID("689ef3b2-24e2-4b1c-b38d-22f984d6e0f7");
    Assertions.assertTrue(smaller.compareTo(bigger) < 0);
    Assertions.assertTrue(bigger.compareTo(smaller) > 0);
//to set the user_ID as equal.
    smaller.setuser_ID("689ef3b2-24e2-4b1c-b38d-22f984d6e0f7");
//to compare a smaller and larger subcategory_ID
    smaller.setsubcategory_ID("0db9905f-7bef-45aa-a3b6-236f755b2d81");
    bigger.setsubcategory_ID("1db9905f-7bef-45aa-a3b6-236f755b2d81");
    Assertions.assertTrue(smaller.compareTo(bigger) < 0);
    Assertions.assertTrue(bigger.compareTo(smaller) > 0);
//to set the subcategory_ID as equal.
    smaller.setsubcategory_ID("1db9905f-7bef-45aa-a3b6-236f755b2d81");
    //to compare a smaller and larger budget_ID
    smaller.setbudget_id("eFreTFlwttLfwuiLfHVOKoqKUgDbrIivetCa");
    bigger.setbudget_id("pFreTFlwttLfwuiLfHVOKoqKUgDbrIivetCa");
    Assertions.assertTrue(smaller.compareTo(bigger) < 0);
    Assertions.assertTrue(bigger.compareTo(smaller) > 0);
//to set the subcategory_ID as equal.
    smaller.setbudget_id("pFreTFlwttLfwuiLfHVOKoqKUgDbrIivetCa");
//to compare a smaller and larger nickname
    smaller.setnickname("aaaa");
    bigger.setnickname("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger) < 0);
    Assertions.assertTrue(bigger.compareTo(smaller) > 0);
//to set the nickname as equal.
    smaller.setnickname("bbbb");
//to compare a smaller and larger amount
    smaller.setamount(10.23d);
    bigger.setamount(14.12d);
    Assertions.assertTrue(smaller.compareTo(bigger) < 0);
    Assertions.assertTrue(bigger.compareTo(smaller) > 0);
//to set the amount as equal.
    smaller.setamount(14.12d);
//to compare a smaller and larger start_date
    smaller.setstart_date(df.parse("01/01/2023"));
    bigger.setstart_date(df.parse("01/01/2024"));
    Assertions.assertTrue(smaller.compareTo(bigger) < 0);
    Assertions.assertTrue(bigger.compareTo(smaller) > 0);
//to set the start_date as equal.
    smaller.setstart_date(df.parse("01/01/2024"));
//to compare a smaller and larger times_per_year
    smaller.settimes_per_year(3);
    bigger.settimes_per_year(6);
    Assertions.assertTrue(smaller.compareTo(bigger) < 0);
    Assertions.assertTrue(bigger.compareTo(smaller) > 0);
//to set the times_per_year as equal.
    smaller.settimes_per_year(6);
//to compare a smaller and larger occurrences
    smaller.setoccurrences(10);
    bigger.setoccurrences(20);
    Assertions.assertTrue(smaller.compareTo(bigger) < 0);
    Assertions.assertTrue(bigger.compareTo(smaller) > 0);
//to set the occurrences as equal.
    smaller.setoccurrences(20);
//to compare a smaller and larger is_active
    smaller.setis_active(false);
    bigger.setis_active(true);
    Assertions.assertTrue(smaller.compareTo(bigger) < 0);
    Assertions.assertTrue(bigger.compareTo(smaller) > 0);
//to set the is_active as equal.
    smaller.setis_active(true);
    Assertions.assertEquals(0, bigger.compareTo(smaller));
  }

}

