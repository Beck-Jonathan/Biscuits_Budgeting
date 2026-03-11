package com.beck.beck_demos.budget_app.data_fakes;

import com.beck.beck_demos.budget_app.iData.iCategoryDAO;
import com.beck.beck_demos.budget_app.models.ParentCategory;
import com.beck.beck_demos.budget_app.models.SubCategory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//since 1/27/2025
//Jonathan Beck
public class CategoryDAO_Fake implements iCategoryDAO {
  private  List<SubCategory> subCategories;
  private  List<ParentCategory> parent_categories;
  public CategoryDAO_Fake(){
    subCategories = new ArrayList<>();
    // User 1: ec93ae39-255a-4252-ac50-cde8ecb05b0c
    // User 1: ec93ae39-255a-4252-ac50-cde8ecb05b0c
    SubCategory category0 = new SubCategory("lZoleuasarwCfcmdPWeDgyapFwTISoPKgqXc", "ec93ae39-255a-4252-ac50-cde8ecb05b0c", "sqiidVcxEgAxPSmbAlPuyyPqlajALcCishjt", "Electricity", "#FFFFFF");
    SubCategory category1 = new SubCategory("CptdvYCqiIfQbZZYnDSelOjeuWoIaPIoSyqN", "ec93ae39-255a-4252-ac50-cde8ecb05b0c", "sqiidVcxEgAxPSmbAlPuyyPqlajALcCishjt", "Water/Sewer", "#FFFFFF");
    SubCategory category2 = new SubCategory("tqwOoIpqAWlTCXgHIWbRoWGDeWkRLuUjMrUK", "ec93ae39-255a-4252-ac50-cde8ecb05b0c", "sqiidVcxEgAxPSmbAlPuyyPqlajALcCishjt", "Internet", "#FFFFFF");
    SubCategory category3 = new SubCategory("VbNXjdaXScgqBlggjkqLldrnWVJfkrCAoHXH", "ec93ae39-255a-4252-ac50-cde8ecb05b0c", "sqiidVcxEgAxPSmbAlPuyyPqlajALcCishjt", "Trash Pickup", "#FFFFFF");

// User 2: 333d977a-a025-428c-83ca-76a1741d6e0c
    SubCategory category4 = new SubCategory("vdoqmRiVgdUosYCZMDkucIbBqIHNMhyUoGbS", "333d977a-a025-428c-83ca-76a1741d6e0c", "hCraEvQYiLHHoPurvcIVYtHtPbwnprHMRKEN", "Groceries", "#FFFFFF");
    SubCategory category5 = new SubCategory("ucYKgZgvfLUTdJVbOhDchrUawsfqUPTPkdNG", "333d977a-a025-428c-83ca-76a1741d6e0c", "hCraEvQYiLHHoPurvcIVYtHtPbwnprHMRKEN", "Dining Out", "#FFFFFF");
    SubCategory category6 = new SubCategory("bNQxZYSVjLmJDvJLVyOYNLdJGFKIWpjLhEsF", "333d977a-a025-428c-83ca-76a1741d6e0c", "hCraEvQYiLHHoPurvcIVYtHtPbwnprHMRKEN", "Coffee Shops", "#FFFFFF");

// User 3: 91c9e669-6696-4902-8446-d4eebdc8acea
    SubCategory category7 = new SubCategory("uEmuNJVOsGTRrqaxFojdSgRvKTJurHbjCDEZ", "91c9e669-6696-4902-8446-d4eebdc8acea", "XOaLTGEQbNNrHcYrPPpEOTlLxhePgsupNJgh", "Gasoline", "#FFFFFF");
    SubCategory category8 = new SubCategory("tlbsafOfuDUGULgwOiEkiYHEiGDAYdOipASn", "91c9e669-6696-4902-8446-d4eebdc8acea", "XOaLTGEQbNNrHcYrPPpEOTlLxhePgsupNJgh", "Car Insurance", "#FFFFFF");
    SubCategory category9 = new SubCategory("kLRYjKtYnicVOWdVfsswWiDgaxdIjFoaxvxg", "91c9e669-6696-4902-8446-d4eebdc8acea", "XOaLTGEQbNNrHcYrPPpEOTlLxhePgsupNJgh", "Maintenance", "#FFFFFF");
    SubCategory category10 = new SubCategory("XTCGeIIYvATsbcwLadRCtYlKcQHsDKndeJJC", "91c9e669-6696-4902-8446-d4eebdc8acea", "XOaLTGEQbNNrHcYrPPpEOTlLxhePgsupNJgh", "Registration", "#FFFFFF");

// User 4: 7b2f8a11-1234-5678-abcd-ef9012bc34de
    SubCategory category11 = new SubCategory("BsdJLFpQtWEFWvYKBMsLtGiKiKGHXwUOlikP", "7b2f8a11-1234-5678-abcd-ef9012bc34de", "WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG", "Movie Tickets", "#FFFFFF");
    SubCategory category12 = new SubCategory("cqHALvMmMJwUecFCNpXLZicMHuywqThfjQfn", "7b2f8a11-1234-5678-abcd-ef9012bc34de", "WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG", "Streaming Services", "#FFFFFF");
    SubCategory category13 = new SubCategory("yXfCtVnlIoyTsOFhxiMudRQvqubgvRCCGLsJ", "7b2f8a11-1234-5678-abcd-ef9012bc34de", "WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG", "Video Games", "#FFFFFF");
    SubCategory category14 = new SubCategory("MowPYhMmEqfgtCEmbZMyrXMnsrBXtKlRuMJu", "7b2f8a11-1234-5678-abcd-ef9012bc34de", "WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG", "Concert Tickets", "#FFFFFF");
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
    ParentCategory ParentCategory0 = new ParentCategory("xMqjscKWAtfvrSWZDjTPrqRAXQTjcyWniqoL", "PpsWnCrH", "GmRRcyFJBBXIjIGftCXLvWRXwGmmbTYWfqnC", "YVblFgK");
    ParentCategory ParentCategory1 = new ParentCategory("MZxHQfoGynJGKiYENZKJiSqUQrvAiDAOWutZ", "ZqDEIRXf", "GmRRcyFJBBXIjIGftCXLvWRXwGmmbTYWfqnC", "ftmAmsG");
    ParentCategory ParentCategory2 = new ParentCategory("wjSfNiOweomBVxyHWOygfwwnWIqrdTsjRwcs", "wfoVxFeN", "GmRRcyFJBBXIjIGftCXLvWRXwGmmbTYWfqnC", "EUhZRFW");
    ParentCategory ParentCategory3 = new ParentCategory("RSqFgrSCwhLEUImMNbtZeYbCvFhpGQvrOWWV", "sOIhbqny", "GmRRcyFJBBXIjIGftCXLvWRXwGmmbTYWfqnC", "XduoUXq");
    ParentCategory ParentCategory4 = new ParentCategory("xIgsHloveCVlyiIWSnNinZVuNGhpHSninyRA", "mjXMmIak", "WXjAKIkOHWNPEptlaYtjpDVhodaTGQVhYmRi", "pwDMNZL");
    ParentCategory ParentCategory5 = new ParentCategory("ixikEAIlGXsFPWiKvgKxSwMGjvyVsrRQnQGp", "FEARRynC", "WXjAKIkOHWNPEptlaYtjpDVhodaTGQVhYmRi", "XWiyjqA");
    ParentCategory ParentCategory6 = new ParentCategory("wHFXeZblIZTmvlOvYJVTRfSDficeOaLvdxtH", "wUWfGjxH", "WXjAKIkOHWNPEptlaYtjpDVhodaTGQVhYmRi", "mJnDoFM");
    ParentCategory ParentCategory7 = new ParentCategory("WIEecFbsBmHXThLLjdjtnbatxIiOTMYWZqhM", "XPfmRESv", "WXjAKIkOHWNPEptlaYtjpDVhodaTGQVhYmRi", "vCregiB");
    ParentCategory ParentCategory8 = new ParentCategory("XOjMpFhTDZoiYIRrsgnjuKkNWNnmLoenrHjd", "LGySVfMC", "nOoefyobHuFUdauNcBGlURcGhPVZpCMhopyP", "gSciZhg");
    ParentCategory ParentCategory9 = new ParentCategory("flgUPxhhoyxtqhKoLJcWehxOEGVcaIFeSClt", "OehKITvS", "nOoefyobHuFUdauNcBGlURcGhPVZpCMhopyP", "AZJWhct");
    ParentCategory ParentCategory10 = new ParentCategory("iItwWdRLXcIFmwpfRRkrvEPjbLPnkbGUMNrJ", "TWIxxFot", "nOoefyobHuFUdauNcBGlURcGhPVZpCMhopyP", "QxrNfCF");
    ParentCategory ParentCategory11 = new ParentCategory("wIJlsqcpcXDaQBVuPyvhTCCUMupkWDOjwuLW", "GOlpYhrC", "nOoefyobHuFUdauNcBGlURcGhPVZpCMhopyP", "oGidheE");


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

  private boolean duplicateKey(SubCategory _category){
    return (_category==null ||_category.getCategory_Name().contains("DUPLICATE"));
  }
  private boolean exceptionKey(SubCategory _category){
    return (_category==null ||_category.getCategory_Name().contains("EXCEPTION"));
  }
}
