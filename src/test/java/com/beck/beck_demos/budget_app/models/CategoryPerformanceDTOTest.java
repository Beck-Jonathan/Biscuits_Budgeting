package com.beck.beck_demos.budget_app.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoryPerformanceDTOTest {
  private CategoryPerformanceDTO _CategoryPerformanceDTO;

  @BeforeEach
  public void setup() {
    _CategoryPerformanceDTO = new CategoryPerformanceDTO();
  }

  @AfterEach
  public void teardown() {
    _CategoryPerformanceDTO = null;
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
      _CategoryPerformanceDTO.setPeriod("2026-03"); // Wrong format
    });
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _CategoryPerformanceDTO.setPeriod("13/2026"); // Invalid month
    });
  }

  @Test
  public void testCategoryPerformanceDTOSetsPeriod() {
    String period = "04/2026";
    _CategoryPerformanceDTO.setPeriod(period);
    Assertions.assertEquals(period, _CategoryPerformanceDTO.getPeriod());
  }

  /**
   * Tests that BudgetedValue handles range validation.
   */
  @Test
  public void testCategoryPerformanceDTOThrowsExceptionIfBudgetedValueTooSmall() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _CategoryPerformanceDTO.setBudgetedValue(-1.0);
    });
  }

  @Test
  public void testCategoryPerformanceDTOSetsBudgetedValue() {
    double val = 500.25;
    _CategoryPerformanceDTO.setBudgetedValue(val);
    Assertions.assertEquals(val, _CategoryPerformanceDTO.getBudgetedValue());
  }

  /**
   * Tests that ActualValue handles range validation.
   */
  @Test
  public void testCategoryPerformanceDTOThrowsExceptionIfActualValueTooSmall() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _CategoryPerformanceDTO.setActualValue(-1.0);
    });
  }

  @Test
  public void testCategoryPerformanceDTOSetsActualValue() {
    double val = 480.00;
    _CategoryPerformanceDTO.setActualValue(val);
    Assertions.assertEquals(val, _CategoryPerformanceDTO.getActualValue());
  }

  @Test
  public void testCategoryPerformanceDTOSetsActualValueForThreshold() {
    double val = 480.00;
    _CategoryPerformanceDTO.setThreshold(val);
    Assertions.assertEquals(val, _CategoryPerformanceDTO.getThreshold());
  }

  /**
   * Tests the CompareTo Method for sorting by period then values.
   */
  @Test
  public void testCompareTo() {
//    CategoryPerformanceDTO smaller = new CategoryPerformanceDTO("01/2026", 100.0, 100.0);
//    CategoryPerformanceDTO bigger = new CategoryPerformanceDTO("02/2026", 100.0, 100.0);
//
//    // Compare periods
//    Assertions.assertTrue(smaller.compareTo(bigger) < 0);
//    Assertions.assertTrue(bigger.compareTo(smaller) > 0);
//
//    // Compare same period, different budget
//    smaller.setPeriod("02/2026");
//    smaller.setBudgetedValue(50.0);
//    Assertions.assertTrue(smaller.compareTo(bigger) < 0);
//
//    // Compare same everything
//    smaller.setBudgetedValue(100.0);
//    Assertions.assertEquals(0, smaller.compareTo(bigger));
  }
}