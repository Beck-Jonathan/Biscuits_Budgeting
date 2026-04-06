package com.beck.beck_demos.budget_app.data_fakes;

import com.beck.beck_demos.budget_app.iData.iCategoryDAO;
import com.beck.beck_demos.budget_app.models.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//since 1/27/2025
//Jonathan Beck
public class CategoryDAO_Fake implements iCategoryDAO {
  private final List<SubCategory> subCategories;
  private final List<ParentCategory> parent_categories;
  List<ProjectionAnalysisDTO> projections;
  private final List<CategoryPerformanceDTO> performanceData;
  public CategoryDAO_Fake(){
    subCategories = new ArrayList<>();
    // User 1: ec93ae39-255a-4252-ac50-cde8ecb05b0c

    // User 1: ec93ae39-255a-4252-ac50-cde8ecb05b0c (Utilities)
    SubCategory category0 = new SubCategory("lZoleuasarwCfcmdPWeDgyapFwTISoPKgqXc", "ec93ae39-255a-4252-ac50-cde8ecb05b0c", "ALPHA_SPIKE", "sqiidVcxEgAxPSmbAlPuyyPqlajALcCishjt", "Electricity", "#FFFFFF", new BigDecimal("42.1"), false);

    SubCategory category1 = new SubCategory("CptdvYCqiIfQbZZYnDSelOjeuWoIaPIoSyqN", "ec93ae39-255a-4252-ac50-cde8ecb05b0c", "ALPHA_SPIKE", "sqiidVcxEgAxPSmbAlPuyyPqlajALcCishjt", "Water/Sewer", "#FFFFFF", new BigDecimal("85.00"), false);
    SubCategory category2 = new SubCategory("tqwOoIpqAWlTCXgHIWbRoWGDeWkRLuUjMrUK", "ec93ae39-255a-4252-ac50-cde8ecb05b0c", "ALPHA_SPIKE", "sqiidVcxEgAxPSmbAlPuyyPqlajALcCishjt", "Internet", "#FFFFFF", new BigDecimal("79.99"), true); // Often a fixed/locked rate
    SubCategory category3 = new SubCategory("VbNXjdaXScgqBlggjkqLldrnWVJfkrCAoHXH", "ec93ae39-255a-4252-ac50-cde8ecb05b0c", "ALPHA_SPIKE", "sqiidVcxEgAxPSmbAlPuyyPqlajALcCishjt", "Trash Pickup", "#FFFFFF", new BigDecimal("30.00"), true);

// User 2: 333d977a-a025-428c-83ca-76a1741d6e0c (Food & Dining)
    SubCategory category4 = new SubCategory("vdoqmRiVgdUosYCZMDkucIbBqIHNMhyUoGbS", "333d977a-a025-428c-83ca-76a1741d6e0c", "ALPHA_SPIKE", "hCraEvQYiLHHoPurvcIVYtHtPbwnprHMRKEN", "Groceries", "#FFFFFF", new BigDecimal("500.00"), false);
    SubCategory category5 = new SubCategory("ucYKgZgvfLUTdJVbOhDchrUawsfqUPTPkdNG", "333d977a-a025-428c-83ca-76a1741d6e0c", "ALPHA_SPIKE", "hCraEvQYiLHHoPurvcIVYtHtPbwnprHMRKEN", "Dining Out", "#FFFFFF", new BigDecimal("200.00"), false);
    SubCategory category6 = new SubCategory("bNQxZYSVjLmJDvJLVyOYNLdJGFKIWpjLhEsF", "333d977a-a025-428c-83ca-76a1741d6e0c", "ALPHA_SPIKE", "hCraEvQYiLHHoPurvcIVYtHtPbwnprHMRKEN", "Coffee Shops", "#FFFFFF", new BigDecimal("45.00"), false);

// User 3: 91c9e669-6696-4902-8446-d4eebdc8acea (Transportation)
    SubCategory category7 = new SubCategory("uEmuNJVOsGTRrqaxFojdSgRvKTJurHbjCDEZ", "91c9e669-6696-4902-8446-d4eebdc8acea", "REGRESSION", "XOaLTGEQbNNrHcYrPPpEOTlLxhePgsupNJgh", "Gasoline", "#FFFFFF", new BigDecimal("120.00"), false);
    SubCategory category8 = new SubCategory("tlbsafOfuDUGULgwOiEkiYHEiGDAYdOipASn", "91c9e669-6696-4902-8446-d4eebdc8acea", "REGRESSION", "XOaLTGEQbNNrHcYrPPpEOTlLxhePgsupNJgh", "Car Insurance", "#FFFFFF", new BigDecimal("100.00"), true);
    SubCategory category9 = new SubCategory("kLRYjKtYnicVOWdVfsswWiDgaxdIjFoaxvxg", "91c9e669-6696-4902-8446-d4eebdc8acea", "REGRESSION", "XOaLTGEQbNNrHcYrPPpEOTlLxhePgsupNJgh", "Maintenance", "#FFFFFF", new BigDecimal("50.00"), false);
    SubCategory category10 = new SubCategory("XTCGeIIYvATsbcwLadRCtYlKcQHsDKndeJJC", "91c9e669-6696-4902-8446-d4eebdc8acea", "REGRESSION", "XOaLTGEQbNNrHcYrPPpEOTlLxhePgsupNJgh", "Registration", "#FFFFFF", new BigDecimal("15.00"), true);

// User 4: 7b2f8a11-1234-5678-abcd-ef9012bc34de (Entertainment)
    SubCategory category11 = new SubCategory("BsdJLFpQtWEFWvYKBMsLtGiKiKGHXwUOlikP", "7b2f8a11-1234-5678-abcd-ef9012bc34de", "LVCF", "WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG", "Movie Tickets", "#FFFFFF", new BigDecimal("40.00"), false);
    SubCategory category12 = new SubCategory("cqHALvMmMJwUecFCNpXLZicMHuywqThfjQfn", "7b2f8a11-1234-5678-abcd-ef9012bc34de", "LVCF", "WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG", "Streaming Services", "#FFFFFF", new BigDecimal("0.00"), false);
    SubCategory category13 = new SubCategory("yXfCtVnlIoyTsOFhxiMudRQvqubgvRCCGLsJ", "7b2f8a11-1234-5678-abcd-ef9012bc34de", "LVCF", "WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG", "Video Games", "#FFFFFF", new BigDecimal("70.00"), false);
    SubCategory category14 = new SubCategory("MowPYhMmEqfgtCEmbZMyrXMnsrBXtKlRuMJu", "7b2f8a11-1234-5678-abcd-ef9012bc34de", "LVCF", "WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG", "Concert Tickets", "#FFFFFF", new BigDecimal("150.00"), false);
    subCategories.add(category0);
    subCategories.add(category1);
    subCategories.add(category2);
    subCategories.add(category3);
    subCategories.add(category4);
    subCategories.add(category5);
    subCategories.add(category6);
    subCategories.add(category7);
    subCategories.add(category8);
    subCategories.add(category9);
    subCategories.add(category10);
    subCategories.add(category11);
    subCategories.add(category12);
    subCategories.add(category13);
    subCategories.add(category14);
    Collections.sort(subCategories);

    parent_categories = new ArrayList<>();
    // Group A: Expense
    ParentCategory ParentCategory0 = new ParentCategory("xMqjscKWAtfvrSWZDjTPrqRAXQTjcyWniqoL", "PpsWnCrH", "GmRRcyFJBBXIjIGftCXLvWRXwGmmbTYWfqnC", "YVblFgK", "expense");
    ParentCategory ParentCategory1 = new ParentCategory("MZxHQfoGynJGKiYENZKJiSqUQrvAiDAOWutZ", "ZqDEIRXf", "GmRRcyFJBBXIjIGftCXLvWRXwGmmbTYWfqnC", "ftmAmsG", "expense");
    ParentCategory ParentCategory2 = new ParentCategory("wjSfNiOweomBVxyHWOygfwwnWIqrdTsjRwcs", "wfoVxFeN", "GmRRcyFJBBXIjIGftCXLvWRXwGmmbTYWfqnC", "EUhZRFW", "expense");
    ParentCategory ParentCategory3 = new ParentCategory("RSqFgrSCwhLEUImMNbtZeYbCvFhpGQvrOWWV", "sOIhbqny", "GmRRcyFJBBXIjIGftCXLvWRXwGmmbTYWfqnC", "XduoUXq", "expense");

// Group B: Income
    ParentCategory ParentCategory4 = new ParentCategory("xIgsHloveCVlyiIWSnNinZVuNGhpHSninyRA", "mjXMmIak", "WXjAKIkOHWNPEptlaYtjpDVhodaTGQVhYmRi", "pwDMNZL", "income");
    ParentCategory ParentCategory5 = new ParentCategory("ixikEAIlGXsFPWiKvgKxSwMGjvyVsrRQnQGp", "FEARRynC", "WXjAKIkOHWNPEptlaYtjpDVhodaTGQVhYmRi", "XWiyjqA", "income");
    ParentCategory ParentCategory6 = new ParentCategory("wHFXeZblIZTmvlOvYJVTRfSDficeOaLvdxtH", "wUWfGjxH", "WXjAKIkOHWNPEptlaYtjpDVhodaTGQVhYmRi", "mJnDoFM", "income");
    ParentCategory ParentCategory7 = new ParentCategory("WIEecFbsBmHXThLLjdjtnbatxIiOTMYWZqhM", "XPfmRESv", "WXjAKIkOHWNPEptlaYtjpDVhodaTGQVhYmRi", "vCregiB", "income");

// Group C: Investment
    ParentCategory ParentCategory8 = new ParentCategory("XOjMpFhTDZoiYIRrsgnjuKkNWNnmLoenrHjd", "LGySVfMC", "nOoefyobHuFUdauNcBGlURcGhPVZpCMhopyP", "gSciZhg", "investment");
    ParentCategory ParentCategory9 = new ParentCategory("flgUPxhhoyxtqhKoLJcWehxOEGVcaIFeSClt", "OehKITvS", "nOoefyobHuFUdauNcBGlURcGhPVZpCMhopyP", "AZJWhct", "investment");
    ParentCategory ParentCategory10 = new ParentCategory("iItwWdRLXcIFmwpfRRkrvEPjbLPnkbGUMNrJ", "TWIxxFot", "nOoefyobHuFUdauNcBGlURcGhPVZpCMhopyP", "QxrNfCF", "investment");
    ParentCategory ParentCategory11 = new ParentCategory("wIJlsqcpcXDaQBVuPyvhTCCUMupkWDOjwuLW", "GOlpYhrC", "nOoefyobHuFUdauNcBGlURcGhPVZpCMhopyP", "oGidheE", "investment");


    parent_categories.add(ParentCategory0);
    parent_categories.add(ParentCategory1);
    parent_categories.add(ParentCategory2);
    parent_categories.add(ParentCategory3);
    parent_categories.add(ParentCategory4);
    parent_categories.add(ParentCategory5);
    parent_categories.add(ParentCategory6);
    parent_categories.add(ParentCategory7);
    parent_categories.add(ParentCategory8);
    parent_categories.add(ParentCategory9);
    parent_categories.add(ParentCategory10);
    parent_categories.add(ParentCategory11);
    Collections.sort(parent_categories);

    projections = new ArrayList<>();

// --- USER 1: Electricity (ALPHA_SPIKE) ---
// 24 Months of History (Sample points)
    projections.add(new ProjectionAnalysisDTO("2024-04-01", 120.50, null, null, null, null, null, 0.00));
    projections.add(new ProjectionAnalysisDTO("2024-05-01", 135.00, null, null, null, null, null, 0.00));
    projections.add(new ProjectionAnalysisDTO("2025-12-01", 450.00, null, null, null, null, null, 0.00)); // Winter Spike
    projections.add(new ProjectionAnalysisDTO("2026-03-01", 110.00, null, null, null, null, null, 0.00)); // Last Hist Month

// 36 Months of Projection (Sample points)
    projections.add(new ProjectionAnalysisDTO("2026-04-01", null, 125.00, 115.00, 110.00, 115.34, 125.00, 0.00));
    projections.add(new ProjectionAnalysisDTO("2026-12-01", null, 130.00, 115.00, 110.00, 118.00, 550.00, 0.00)); // Seasonal Peak
    projections.add(new ProjectionAnalysisDTO("2027-04-01", null, 140.00, 115.00, 110.00, 121.00, 140.00, 0.00));

// --- USER 3: Gasoline (REGRESSION) ---
// History
    projections.add(new ProjectionAnalysisDTO("2025-01-01", 210.00, null, null, null, null, null, 0.00));
    projections.add(new ProjectionAnalysisDTO("2026-03-01", 245.00, null, null, null, null, null, 0.00));

// Projection
    projections.add(new ProjectionAnalysisDTO("2026-04-01", null, 250.00, 225.00, 245.00, 225.67, 250.00, 0.00));
    projections.add(new ProjectionAnalysisDTO("2026-05-01", null, 255.00, 225.00, 245.00, 226.35, 255.00, 0.00));

// --- USER 4: Movie Tickets (LVCF) ---
// History
    projections.add(new ProjectionAnalysisDTO("2026-03-01", 45.00, null, null, null, null, null, 0.00));

// Projection (Stays flat at the last value of 45.00)
    projections.add(new ProjectionAnalysisDTO("2026-04-01", null, 42.00, 40.00, 45.00, 40.12, 42.00, 0.00));

    performanceData = new ArrayList<>();

// --- 2025 Budget Performance Data (Sample Set) ---

// Q1: Heating costs high, but coming in under budget
    List<CategoryPerformanceDTO> performanceData = new ArrayList<>();

// --- 2025 Budget Performance Data (with Target Threshold) ---

// Q1: Heating costs
    performanceData.add(new CategoryPerformanceDTO("01/2025", 250.00, 210.00, 300.00));
    performanceData.add(new CategoryPerformanceDTO("02/2025", 225.00, 195.50, 300.00));
    performanceData.add(new CategoryPerformanceDTO("03/2025", 180.00, 185.25, 200.00)); // Slight Over budget, under threshold

// Q2: Spring months - 1904 House renovation spikes
    performanceData.add(new CategoryPerformanceDTO("04/2025", 150.00, 312.80, 250.00)); // Major Over both!
    performanceData.add(new CategoryPerformanceDTO("05/2025", 150.00, 145.00, 250.00));
    performanceData.add(new CategoryPerformanceDTO("06/2025", 150.00, 162.15, 250.00));

// Q3: Summer - Roller Derby travel/season costs
    performanceData.add(new CategoryPerformanceDTO("07/2025", 200.00, 198.00, 350.00));
    performanceData.add(new CategoryPerformanceDTO("08/2025", 200.00, 245.50, 350.00)); // Over budget, safe threshold
    performanceData.add(new CategoryPerformanceDTO("09/2025", 150.00, 110.00, 350.00));

// Q4: Year End - Maintenance and prep
    performanceData.add(new CategoryPerformanceDTO("10/2025", 150.00, 155.00, 200.00));
    performanceData.add(new CategoryPerformanceDTO("11/2025", 180.00, 175.00, 200.00));
    performanceData.add(new CategoryPerformanceDTO("12/2025", 220.00, 280.45, 300.00)); // Winter Spike
  }
  /**
   * @param _category The category to be added to the database
   * @return number of rows effected
   */
  @Override
  public int add(SubCategory _category) throws SQLException {
    if (duplicateKey(_category)){
      return 0;
    }
    if (exceptionKey(_category)){
      throw new SQLException("error");
    }
    int size = subCategories.size();

    subCategories.add(_category);
    int newsize = subCategories.size();
    return newsize-size;
  }

  @Override
  public List<SubCategory> getsubCategoryByUser(String User_ID){
    List<SubCategory> results = new ArrayList<>();
    for (SubCategory category : subCategories){
      if (category.getUser_ID().equals(User_ID)){
        results.add(category);
      }
    }
    return results;
  }

  @Override
  public int deleteSubCategory(String categoryID, String User_ID) throws SQLException {
    int size = subCategories.size();
    int location =-1;
    for (int i = 0; i< subCategories.size(); i++){
      if (subCategories.get(i).getCategory_ID().equals(categoryID)&& subCategories.get(i).getUser_ID().equals(User_ID)){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }
    subCategories.remove(location);
    int newsize = subCategories.size();
    return size-newsize;
  }


  @Override
  public int update(SubCategory oldCategory, SubCategory newCategory) throws SQLException {
    int location =-1;
    if (duplicateKey(newCategory)){
      return 0;
    }
    if (exceptionKey(newCategory)){
      throw new SQLException("error");
    }
    for (int i = 0; i< subCategories.size(); i++){
      if (subCategories.get(i).getCategory_ID().equals(oldCategory.getCategory_ID())){
          location =i;
          break;
        }
      }
      if (location==-1){
        return 0;
      }

    subCategories.set(location,newCategory);
      return 1;
    }

  @Override
  public SubCategory getCategoryByPrimaryKey(SubCategory category) throws SQLException {
    SubCategory result = null;
    for (SubCategory _category : subCategories) {
      if (category.getCategory_ID().equals(_category.getCategory_ID())&&category.getUser_ID().equals(_category.getUser_ID())){
        result = category;
        break;
      }
    }
    if (result == null){
      throw new SQLException("category not found");
    }
    return result;
  }

  @Override
  public List<ParentCategory> getParentCategoryByUser(String userID) throws SQLException {
    List<ParentCategory> results = new ArrayList<>();
    for (ParentCategory parent_category : parent_categories){
      if (parent_category.getuser_id().equals(userID)){
        results.add(parent_category);
      }
    }
    return results;
  }

  @Override
  public int SmartAssignProjectionModel(User user) throws SQLException {
    int result = 0;
    if (exceptionKey(user)) {
      throw new SQLException("error");
    }
    for (SubCategory subCategory : subCategories) {
      if (subCategory.getUser_ID().equals(user.getUser_ID())) {
        result++;
        if (subCategory.getprojection_strategy_ID().equals("ALPHA_SPIKE")) {
          subCategory.setprojection_strategy_ID("REGRESSION");

        } else if (subCategory.getprojection_strategy_ID().equals("REGRESSION")) {
          subCategory.setprojection_strategy_ID("LVCF");
        } else if (subCategory.getprojection_strategy_ID().equals("LVCF")) {
          subCategory.setprojection_strategy_ID("ALPHA_SPIKE");
        }
      }
    }
    return result;
  }

  @Override
  public List<ProjectionAnalysisDTO> getProjectionData(String userId, String subcatId, int histMonths, int projMonths) throws SQLException {
    List<ProjectionAnalysisDTO> results = new ArrayList<>();

    // USER 1: Electricity (Strategy: ALPHA_SPIKE)
    if (subcatId.equals("lZoleuasarwCfcmdPWeDgyapFwTISoPKgqXc")) {
      results.add(new ProjectionAnalysisDTO("2026-01-01", 150.00, null, null, null, null, null, 0.00));
      results.add(new ProjectionAnalysisDTO("2026-02-01", 165.00, null, null, null, null, null, 0.00));
      results.add(new ProjectionAnalysisDTO("2026-03-01", 140.00, null, null, null, null, null, 0.00));
      // Projection Month 1
      results.add(new ProjectionAnalysisDTO("2026-04-01", null, 155.00, 150.00, 140.00, 150.45, 155.00, 0.00));
      // Projection Month 9 (Seasonal Spike)
      results.add(new ProjectionAnalysisDTO("2026-12-01", null, 160.00, 150.00, 140.00, 153.20, 450.00, 0.00));
    }

    // USER 2: Groceries (Strategy: ALPHA_SPIKE)
    else if (subcatId.equals("vdoqmRiVgdUosYCZMDkucIbBqIHNMhyUoGbS")) {
      results.add(new ProjectionAnalysisDTO("2026-03-01", 200.00, null, null, null, null, null, 0.00));
      results.add(new ProjectionAnalysisDTO("2026-04-01", null, 210.00, 200.00, 200.00, 200.60, 210.00, 0.00));
    }

    // USER 3: Gasoline (Strategy: REGRESSION)
    else if (subcatId.equals("uEmuNJVOsGTRrqaxFojdSgRvKTJurHbjCDEZ")) {
      results.add(new ProjectionAnalysisDTO("2026-03-01", 60.00, null, null, null, null, null, 0.00));
      // Linear climb
      results.add(new ProjectionAnalysisDTO("2026-04-01", null, 65.00, 60.00, 60.00, 60.18, 65.00, 0.00));
      results.add(new ProjectionAnalysisDTO("2026-05-01", null, 70.00, 60.00, 60.00, 60.36, 70.00, 0.00));
    }

    // USER 4: Movie Tickets (Strategy: LVCF)
    else if (subcatId.equals("BsdJLFpQtWEFWvYKBMsLtGiKiKGHXwUOlikP")) {
      results.add(new ProjectionAnalysisDTO("2026-03-01", 25.00, null, null, null, null, null, 0.00));
      // LVCF stays flat at 25.00
      results.add(new ProjectionAnalysisDTO("2026-04-01", null, 22.00, 20.00, 25.00, 20.06, 22.00, 0.00));
    }

    return results;
  }

  @Override
  public int lockSubCategory(SubCategory subCategory) throws SQLException {
    if (exceptionKey(subCategory.getCategory_ID())) {
      throw new SQLException("Exception");
    }

    for (int i = 0; i < subCategories.size(); i++) {
      if (subCategories.get(i).getCategory_ID().equals(subCategory.getCategory_ID())) {
        if (!subCategories.get(i).getIs_Locked()) {
          subCategories.get(i).setIs_Locked(true);
          return 1;
        } else {
          return 0;
        }
      }
    }
    return -1;
  }

  @Override
  public int unlockSubCategory(SubCategory subCategory) throws SQLException {
    if (exceptionKey(subCategory.getCategory_ID())) {
      throw new SQLException("Exception");
    }

    for (int i = 0; i < subCategories.size(); i++) {
      if (subCategories.get(i).getCategory_ID().equals(subCategory.getCategory_ID())) {
        if (subCategories.get(i).getIs_Locked()) {
          subCategories.get(i).setIs_Locked(false);
          return 1;
        } else {
          return 0;
        }
      }
    }
    return -1;
  }

  @Override
  public List<CategoryPerformanceDTO> getCategoryPerformance(String userId, String subcatId, int year) throws SQLException {
    Integer _year = year;
    List<CategoryPerformanceDTO> results = new ArrayList<>();
    for (CategoryPerformanceDTO dto : performanceData)
      if (dto.getPeriod().startsWith(_year.toString())
      ) {
        results.add(dto);
      }
    return results;
  }

  private boolean duplicateKey(SubCategory _category){
    return (_category==null ||_category.getCategory_Name().contains("DUPLICATE"));
  }
  private boolean exceptionKey(SubCategory _category){
    return (_category==null ||_category.getCategory_Name().contains("EXCEPTION"));
  }

  private boolean duplicateKey(String _category) {
    return (_category == null || _category.contains("DUPLICATE"));
  }

  private boolean exceptionKey(String _category) {
    return (_category == null || _category.contains("EXCEPTION"));
  }

  private boolean exceptionKey(User _user) {
    return _user.getUser_ID().contains("EXCEPTION");
  }
}
