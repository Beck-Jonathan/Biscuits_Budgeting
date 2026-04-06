package com.beck.beck_demos.budget_app.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProjectionAnalysisDTOTest {
  private ProjectionAnalysisDTO _ProjectionAnalysisDTO;

  @BeforeEach
  public void setup() {
    _ProjectionAnalysisDTO = new ProjectionAnalysisDTO();
  }

  @AfterEach
  public void teardown() {
    _ProjectionAnalysisDTO = null;
  }

  /**
   * <p> Tests That the default constructor for the ProjectionAnalysisDTO object works </p>
   */
  @Test
  public void testProjectionAnalysisDTODefaultConstructorSetsNoVariables() {
    ProjectionAnalysisDTO _ProjectionAnalysisDTO = new ProjectionAnalysisDTO();

    Assertions.assertNull(_ProjectionAnalysisDTO.getMonthDate());
    Assertions.assertNull(_ProjectionAnalysisDTO.getHistoricalTruth());
    Assertions.assertNull(_ProjectionAnalysisDTO.getRegression());
    Assertions.assertNull(_ProjectionAnalysisDTO.getAvgStrict());
    Assertions.assertNull(_ProjectionAnalysisDTO.getLvcf());
    Assertions.assertNull(_ProjectionAnalysisDTO.getInflation());
    Assertions.assertNull(_ProjectionAnalysisDTO.getAlphaSpike());
    Assertions.assertNull(_ProjectionAnalysisDTO.getZeroSum());
  }

  /**
   * <p> Tests That the Parameterized Constructor for the ProjectionAnalysisDTO object works </p>
   */
  @Test
  public void testProjectionAnalysisDTOParameterizedConstructorSetsAllVariables() {
    ProjectionAnalysisDTO _ProjectionAnalysisDTO = new ProjectionAnalysisDTO(

        "2025-03-01",
        67.19,
        35.18,
        89.49,
        97.23,
        72.71,
        54.13,
        1.88
    );

    Assertions.assertEquals("2025-03-01", _ProjectionAnalysisDTO.getMonthDate());
    Assertions.assertEquals(67.19, _ProjectionAnalysisDTO.getHistoricalTruth());
    Assertions.assertEquals(35.18, _ProjectionAnalysisDTO.getRegression());
    Assertions.assertEquals(89.49, _ProjectionAnalysisDTO.getAvgStrict());
    Assertions.assertEquals(97.23, _ProjectionAnalysisDTO.getLvcf());
    Assertions.assertEquals(72.71, _ProjectionAnalysisDTO.getInflation());
    Assertions.assertEquals(54.13, _ProjectionAnalysisDTO.getAlphaSpike());
    Assertions.assertEquals(1.88, _ProjectionAnalysisDTO.getZeroSum());
  }

  /**
   * <p> Tests That the Parameterized Constructor for the ProjectionAnalysisDTO object works </p>
   */
  @Test
  public void testProjectionAnalysisDTOKeyedParameterizedConstructorSetsKeyedVariables() {
    ProjectionAnalysisDTO _ProjectionAnalysisDTO = new ProjectionAnalysisDTO(

    );

    Assertions.assertNull(_ProjectionAnalysisDTO.getMonthDate());
    Assertions.assertNull(_ProjectionAnalysisDTO.getHistoricalTruth());
    Assertions.assertNull(_ProjectionAnalysisDTO.getRegression());
    Assertions.assertNull(_ProjectionAnalysisDTO.getAvgStrict());
    Assertions.assertNull(_ProjectionAnalysisDTO.getLvcf());
    Assertions.assertNull(_ProjectionAnalysisDTO.getInflation());
    Assertions.assertNull(_ProjectionAnalysisDTO.getAlphaSpike());
    Assertions.assertNull(_ProjectionAnalysisDTO.getZeroSum());
  }

  /**
   <p> Tests That the Setters for the ProjectionAnalysisDTO.ProjectionAnalysisDTO_id field can throw exceptions with invalid inputs </p>
   */

  /**
   * <p> Tests That the Setters for the ProjectionAnalysisDTO.monthDate field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testProjectionAnalysisDTOThrowsIllegalArgumentExceptionIfmonthDateTooSmall() {
    String monthDate = "1999-01-01";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _ProjectionAnalysisDTO.setMonthDate(monthDate);
    });
  }

  /**
   * <p> Tests That the Setters for the ProjectionAnalysisDTO.monthDate field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testProjectionAnalysisDTOThrowsIllegalArgumentExceptionIfmonthDateTooBig() {
    String monthDate = "2051-01-02";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _ProjectionAnalysisDTO.setMonthDate(monthDate);
    });
  }

  @Test
  public void testProjectionAnalysisDTOThrowsIllegalArgumentExceptionIfUnparseableDate() {
    String monthDate = "2025-xx-123";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _ProjectionAnalysisDTO.setMonthDate(monthDate);
    });
  }

  /**
   * <p> Tests That the Setters for the ProjectionAnalysisDTO.monthDate field work </p>
   */
  @Test
  public void testProjectionAnalysisDTOSetsmonthDate() {
    String monthDate = "2025-03-01";
    _ProjectionAnalysisDTO.setMonthDate(monthDate);
    Assertions.assertEquals(monthDate, _ProjectionAnalysisDTO.getMonthDate());
  }

  /**
   * <p> Tests That the Setters for the ProjectionAnalysisDTO.historicalTruth field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testProjectionAnalysisDTOThrowsIllegalArgumentExceptionIfhistoricalTruthTooSmall() {
    double historicalTruth = -70000;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _ProjectionAnalysisDTO.setHistoricalTruth(historicalTruth);
    });
  }

  /**
   * <p> Tests That the Setters for the ProjectionAnalysisDTO.historicalTruth field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testProjectionAnalysisDTOThrowsIllegalArgumentExceptionIfhistoricalTruthTooBig() {
    double historicalTruth = 70000;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _ProjectionAnalysisDTO.setHistoricalTruth(historicalTruth);
    });
  }

  /**
   * <p> Tests That the Setters for the ProjectionAnalysisDTO.historicalTruth field work </p>
   */
  @Test
  public void testProjectionAnalysisDTOSetshistoricalTruthEvenIfNull() {
    double historicalTruth = 7304;
    _ProjectionAnalysisDTO.setHistoricalTruth(historicalTruth);
    Assertions.assertEquals(historicalTruth, _ProjectionAnalysisDTO.getHistoricalTruth());

    _ProjectionAnalysisDTO.setHistoricalTruth(null);
    Assertions.assertNull(_ProjectionAnalysisDTO.getHistoricalTruth());
  }

  /**
   * <p> Tests That the Setters for the ProjectionAnalysisDTO.regression field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testProjectionAnalysisDTOThrowsIllegalArgumentExceptionIfregressionTooSmall() {
    double regression = -70000;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _ProjectionAnalysisDTO.setRegression(regression);
    });
  }

  /**
   * <p> Tests That the Setters for the ProjectionAnalysisDTO.regression field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testProjectionAnalysisDTOThrowsIllegalArgumentExceptionIfregressionTooBig() {
    double regression = 70000;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _ProjectionAnalysisDTO.setRegression(regression);
    });
  }

  /**
   * <p> Tests That the Setters for the ProjectionAnalysisDTO.regression field work </p>
   */
  @Test
  public void testProjectionAnalysisDTOSetsregressionEvenIfNull() {
    double regression = 8300;
    _ProjectionAnalysisDTO.setRegression(regression);
    Assertions.assertEquals(regression, _ProjectionAnalysisDTO.getRegression());
    _ProjectionAnalysisDTO.setRegression(null);
    Assertions.assertNull(_ProjectionAnalysisDTO.getRegression());
  }

  /**
   * <p> Tests That the Setters for the ProjectionAnalysisDTO.avgStrict field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testProjectionAnalysisDTOThrowsIllegalArgumentExceptionIfavgStrictTooSmall() {
    double avgStrict = -70000;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _ProjectionAnalysisDTO.setAvgStrict(avgStrict);
    });
  }

  /**
   * <p> Tests That the Setters for the ProjectionAnalysisDTO.avgStrict field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testProjectionAnalysisDTOThrowsIllegalArgumentExceptionIfavgStrictTooBig() {
    double avgStrict = 70000;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _ProjectionAnalysisDTO.setAvgStrict(avgStrict);
    });
  }

  /**
   * <p> Tests That the Setters for the ProjectionAnalysisDTO.avgStrict field work </p>
   */
  @Test
  public void testProjectionAnalysisDTOSetsavgStrictEvenIfNull() {
    double avgStrict = 1723;
    _ProjectionAnalysisDTO.setAvgStrict(avgStrict);
    Assertions.assertEquals(avgStrict, _ProjectionAnalysisDTO.getAvgStrict());
    _ProjectionAnalysisDTO.setAvgStrict(null);
    Assertions.assertNull(_ProjectionAnalysisDTO.getAvgStrict());
  }

  /**
   * <p> Tests That the Setters for the ProjectionAnalysisDTO.lvcf field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testProjectionAnalysisDTOThrowsIllegalArgumentExceptionIflvcfTooSmall() {
    double lvcf = -70000;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _ProjectionAnalysisDTO.setLvcf(lvcf);
    });
  }

  /**
   * <p> Tests That the Setters for the ProjectionAnalysisDTO.lvcf field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testProjectionAnalysisDTOThrowsIllegalArgumentExceptionIflvcfTooBig() {
    double lvcf = 70000;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _ProjectionAnalysisDTO.setLvcf(lvcf);
    });
  }

  /**
   * <p> Tests That the Setters for the ProjectionAnalysisDTO.lvcf field work </p>
   */
  @Test
  public void testProjectionAnalysisDTOSetslvcfEvenIfNull() {
    double lvcf = 222;
    _ProjectionAnalysisDTO.setLvcf(lvcf);
    Assertions.assertEquals(lvcf, _ProjectionAnalysisDTO.getLvcf());
    _ProjectionAnalysisDTO.setLvcf(null);
    Assertions.assertNull(_ProjectionAnalysisDTO.getLvcf());
  }

  /**
   * <p> Tests That the Setters for the ProjectionAnalysisDTO.inflation field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testProjectionAnalysisDTOThrowsIllegalArgumentExceptionIfinflationTooSmall() {
    double inflation = -70000;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _ProjectionAnalysisDTO.setInflation(inflation);
    });
  }

  /**
   * <p> Tests That the Setters for the ProjectionAnalysisDTO.inflation field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testProjectionAnalysisDTOThrowsIllegalArgumentExceptionIfinflationTooBig() {
    double inflation = 70000;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _ProjectionAnalysisDTO.setInflation(inflation);
    });
  }

  /**
   * <p> Tests That the Setters for the ProjectionAnalysisDTO.inflation field work </p>
   */
  @Test
  public void testProjectionAnalysisDTOSetsinflation() {
    double inflation = 237;
    _ProjectionAnalysisDTO.setInflation(inflation);
    Assertions.assertEquals(inflation, _ProjectionAnalysisDTO.getInflation());
    _ProjectionAnalysisDTO.setInflation(null);
    Assertions.assertNull(_ProjectionAnalysisDTO.getInflation());
  }

  /**
   * <p> Tests That the Setters for the ProjectionAnalysisDTO.alphaSpike field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testProjectionAnalysisDTOThrowsIllegalArgumentExceptionIfalphaSpikeTooSmall() {
    double alphaSpike = -70000;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _ProjectionAnalysisDTO.setAlphaSpike(alphaSpike);
    });
  }

  /**
   * <p> Tests That the Setters for the ProjectionAnalysisDTO.alphaSpike field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testProjectionAnalysisDTOThrowsIllegalArgumentExceptionIfalphaSpikeTooBig() {
    double alphaSpike = 70000;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _ProjectionAnalysisDTO.setAlphaSpike(alphaSpike);
    });
  }

  /**
   * <p> Tests That the Setters for the ProjectionAnalysisDTO.alphaSpike field work </p>
   */
  @Test
  public void testProjectionAnalysisDTOSetsalphaSpikeEvenIfNull() {
    double alphaSpike = 9252;
    _ProjectionAnalysisDTO.setAlphaSpike(alphaSpike);
    Assertions.assertEquals(alphaSpike, _ProjectionAnalysisDTO.getAlphaSpike());
    _ProjectionAnalysisDTO.setAlphaSpike(null);
    Assertions.assertNull(_ProjectionAnalysisDTO.getAlphaSpike());
  }

  /**
   * <p> Tests That the Setters for the ProjectionAnalysisDTO.zeroSum field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testProjectionAnalysisDTOThrowsIllegalArgumentExceptionIfzeroSumTooSmall() {
    double zeroSum = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _ProjectionAnalysisDTO.setZeroSum(zeroSum);
    });
  }

  /**
   * <p> Tests That the Setters for the ProjectionAnalysisDTO.zeroSum field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testProjectionAnalysisDTOThrowsIllegalArgumentExceptionIfzeroSumTooBig() {
    double zeroSum = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _ProjectionAnalysisDTO.setZeroSum(zeroSum);
    });
  }

  /**
   * <p> Tests That the Setters for the ProjectionAnalysisDTO.zeroSum field work </p>
   */
  @Test
  public void testProjectionAnalysisDTOSetszeroSumEvenIfNull() {
    double zeroSum = 0;
    _ProjectionAnalysisDTO.setZeroSum(zeroSum);
    Assertions.assertEquals(zeroSum, _ProjectionAnalysisDTO.getZeroSum());
    _ProjectionAnalysisDTO.setZeroSum(null);
    Assertions.assertNull(_ProjectionAnalysisDTO.getZeroSum());
  }

  /**
   * <p> Tests That the CompareTo Method for the ProjectionAnalysisDTO object works </p>
   */
  @Test
  public void testCompareToCanCompareForEachDateField() {
    ProjectionAnalysisDTO smaller = new ProjectionAnalysisDTO();
    ProjectionAnalysisDTO bigger = new ProjectionAnalysisDTO();

//to compare a smaller and larger monthDate
    smaller.setMonthDate("2025-03-01");
    bigger.setMonthDate("2025-04-01");
    Assertions.assertTrue(smaller.compareTo(bigger) < 0);
    Assertions.assertTrue(bigger.compareTo(smaller) > 0);
//to set the monthDate as equal.
    smaller.setMonthDate("2025-04-01");
//to compare a smaller and larger historicalTruth
    smaller.setHistoricalTruth(10.23d);
    bigger.setHistoricalTruth(14.12d);
    Assertions.assertTrue(smaller.compareTo(bigger) < 0);
    Assertions.assertTrue(bigger.compareTo(smaller) > 0);
//to set the historicalTruth as equal.
    smaller.setHistoricalTruth(14.12d);
//to compare a smaller and larger regression
    smaller.setRegression(10.23d);
    bigger.setRegression(14.12d);
    Assertions.assertTrue(smaller.compareTo(bigger) < 0);
    Assertions.assertTrue(bigger.compareTo(smaller) > 0);
//to set the regression as equal.
    smaller.setRegression(14.12d);
//to compare a smaller and larger avgStrict
    smaller.setAvgStrict(10.23d);
    bigger.setAvgStrict(14.12d);
    Assertions.assertTrue(smaller.compareTo(bigger) < 0);
    Assertions.assertTrue(bigger.compareTo(smaller) > 0);
//to set the avgStrict as equal.
    smaller.setAvgStrict(14.12d);
//to compare a smaller and larger lvcf
    smaller.setLvcf(10.23d);
    bigger.setLvcf(14.12d);
    Assertions.assertTrue(smaller.compareTo(bigger) < 0);
    Assertions.assertTrue(bigger.compareTo(smaller) > 0);
//to set the lvcf as equal.
    smaller.setLvcf(14.12d);
//to compare a smaller and larger inflation
    smaller.setInflation(10.23d);
    bigger.setInflation(14.12d);
    Assertions.assertTrue(smaller.compareTo(bigger) < 0);
    Assertions.assertTrue(bigger.compareTo(smaller) > 0);
//to set the inflation as equal.
    smaller.setInflation(14.12d);
//to compare a smaller and larger alphaSpike
    smaller.setAlphaSpike(10.23d);
    bigger.setAlphaSpike(14.12d);
    Assertions.assertTrue(smaller.compareTo(bigger) < 0);
    Assertions.assertTrue(bigger.compareTo(smaller) > 0);
//to set the alphaSpike as equal.
    smaller.setAlphaSpike(14.12d);
//to compare a smaller and larger zeroSum
    smaller.setZeroSum(0d);
    bigger.setZeroSum(0d);

    Assertions.assertEquals(0, bigger.compareTo(smaller));
  }

}

