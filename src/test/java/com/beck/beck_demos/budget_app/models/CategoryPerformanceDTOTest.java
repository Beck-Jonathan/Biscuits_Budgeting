package com.beck.beck_demos.budget_app.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoryPerformanceDTOTest {
  private CategoryPerformanceDTO _categoryperformancedto;

  @BeforeEach
  public void setup() {
    _categoryperformancedto = new CategoryPerformanceDTO();
  }

  @AfterEach
  public void teardown() {
    _categoryperformancedto = null;
  }

  /**
   * Tests that the default constructor sets no variables.
   */
  @Test
  public void testCategoryPerformanceDTODefaultConstructorSetsNoVariables() {
    CategoryPerformanceDTO dto = new CategoryPerformanceDTO();
    Assertions.assertNull(dto.getPeriod());
    Assertions.assertEquals(0.0, dto.getBudgetedValue());
    Assertions.assertEquals(0.0, dto.getActualValue());
    Assertions.assertEquals(0.0, dto.getThreshold());
  }

  /**
   * Tests that the parameterized constructor sets all variables correctly.
   */
  @Test
  public void testCategoryPerformanceDTOParameterizedConstructorSetsAllVariables() {
    CategoryPerformanceDTO dto = new CategoryPerformanceDTO("03/2026", 150.00, 175.50, 200);
    Assertions.assertEquals("03/2026", dto.getPeriod());
    Assertions.assertEquals(150.00, dto.getBudgetedValue());
    Assertions.assertEquals(175.50, dto.getActualValue());
    Assertions.assertEquals(200, dto.getThreshold());
  }

  /**
   * Tests that the period setter handles validation (Standard MM/YYYY check).
   */
  @Test
  public void testCategoryPerformanceDTOThrowsExceptionIfPeriodInvalid() {
    // Testing unparseable/invalid format
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _categoryperformancedto.setPeriod("2026-03"); // Wrong format
    });
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _categoryperformancedto.setPeriod("13/2026"); // Invalid month
    });
  }

  @Test
  public void testCategoryPerformanceDTOSetsPeriod() {
    String period = "04/2026";
    _categoryperformancedto.setPeriod(period);
    Assertions.assertEquals(period, _categoryperformancedto.getPeriod());
  }

  /**
   <p> Tests That the Setters for the categoryperformancedto.budgetedValue field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testcategoryperformancedtoThrowsIllegalArgumentExceptionIfbudgetedValueTooSmall() {
    double budgetedValue = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _categoryperformancedto.setBudgetedValue(budgetedValue);
    });
  }

  /**
   * <p> Tests That the Setters for the categoryperformancedto.budgetedValue field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testcategoryperformancedtoThrowsIllegalArgumentExceptionIfbudgetedValueTooBig() {
    double budgetedValue = 1000001.1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _categoryperformancedto.setBudgetedValue(budgetedValue);
    });
  }

  /**
   <p> Tests That the Setters for the categoryperformancedto.budgetedValue field work </p>
   */
  @Test
  public void testcategoryperformancedtoSetsbudgetedValue() {
    double budgetedValue = 3505;
    _categoryperformancedto.setBudgetedValue(budgetedValue);
    Assertions.assertEquals(budgetedValue, _categoryperformancedto.getBudgetedValue());
  }

  /**
   * <p> Tests That the Setters for the categoryperformancedto.actualValue field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testcategoryperformancedtoThrowsIllegalArgumentExceptionIfactualValueTooSmall() {
    double actualValue = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _categoryperformancedto.setActualValue(actualValue);
    });
  }

  /**
   * <p> Tests That the Setters for the categoryperformancedto.actualValue field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testcategoryperformancedtoThrowsIllegalArgumentExceptionIfactualValueTooBig() {
    double actualValue = 100000.1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _categoryperformancedto.setActualValue(actualValue);
    });
  }

  /**
   <p> Tests That the Setters for the categoryperformancedto.actualValue field work </p>
   */
  @Test
  public void testcategoryperformancedtoSetsactualValue() {
    double actualValue = 2348;
    _categoryperformancedto.setActualValue(actualValue);
    Assertions.assertEquals(actualValue, _categoryperformancedto.getActualValue());
  }

  /**
   <p> Tests That the Setters for the categoryperformancedto.threshold field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testcategoryperformancedtoThrowsIllegalArgumentExceptionIfthresholdTooSmall() {
    double threshold = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _categoryperformancedto.setThreshold(threshold);
    });
  }

  /**
   * <p> Tests That the Setters for the categoryperformancedto.threshold field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testcategoryperformancedtoThrowsIllegalArgumentExceptionIfthresholdTooBig() {
    double threshold = 100000.1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _categoryperformancedto.setThreshold(threshold);
    });
  }

  /**
   * <p> Tests That the Setters for the categoryperformancedto.threshold field work </p>
   */
  @Test
  public void testcategoryperformancedtoSetsthreshold() {
    double threshold = 5460;
    _categoryperformancedto.setThreshold(threshold);
    Assertions.assertEquals(threshold, _categoryperformancedto.getThreshold());
  }

  /**
   * Tests the CompareTo Method for sorting by period then values.
   */
  @Test
  public void testCompareTo() {
    CategoryPerformanceDTO smaller = new CategoryPerformanceDTO();
    CategoryPerformanceDTO bigger = new CategoryPerformanceDTO();
//to compare a smaller and larger period
    smaller.setPeriod("04/2025");
    bigger.setPeriod("06/2025");
    Assertions.assertTrue(smaller.compareTo(bigger) < 0);
    Assertions.assertTrue(bigger.compareTo(smaller) > 0);
//to set the period as equal.
    smaller.setPeriod("06/2025");
//to compare a smaller and larger budgetedValue
    smaller.setBudgetedValue(10.23d);
    bigger.setBudgetedValue(14.12d);
    Assertions.assertTrue(smaller.compareTo(bigger) < 0);
    Assertions.assertTrue(bigger.compareTo(smaller) > 0);
//to set the budgetedValue as equal.
    smaller.setBudgetedValue(14.12d);
//to compare a smaller and larger actualValue
    smaller.setActualValue(10.23d);
    bigger.setActualValue(14.12d);
    Assertions.assertTrue(smaller.compareTo(bigger) < 0);
    Assertions.assertTrue(bigger.compareTo(smaller) > 0);
//to set the actualValue as equal.
    smaller.setActualValue(14.12d);
//to compare a smaller and larger threshold
    smaller.setThreshold(10.23d);
    bigger.setThreshold(14.12d);
    Assertions.assertTrue(smaller.compareTo(bigger) < 0);
    Assertions.assertTrue(bigger.compareTo(smaller) > 0);
//to set the threshold as equal.
    smaller.setThreshold(14.12d);
    Assertions.assertEquals(0, bigger.compareTo(smaller));
  }
}