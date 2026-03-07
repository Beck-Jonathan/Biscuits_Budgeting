package com.beck.beck_demos.budget_app.data_fakes;

import com.beck.beck_demos.budget_app.iData.iCategoryDAO;
import com.beck.beck_demos.budget_app.models.Category;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//since 1/27/2025
//Jonathan Beck
public class CategoryDAO_Fake implements iCategoryDAO {
  private  List<Category> categories;
  public CategoryDAO_Fake(){
    categories = new ArrayList<>();
    Category category0 = new Category("lZoleuasarwCfcmdPWeDgyapFwTISoPKgqXc", "ec93ae39-255a-4252-ac50-cde8ecb05b0c", "sqiidVcxEgAxPSmbAlPuyyPqlajALcCishjt", "#FFFFFF");
    Category category1 = new Category("CptdvYCqiIfQbZZYnDSelOjeuWoIaPIoSyqN", "ec93ae39-255a-4252-ac50-cde8ecb05b0c", "sqiidVcxEgAxPSmbAlPuyyPqlajALcCishjt", "#FFFFFF");
    Category category2 = new Category("tqwOoIpqAWlTCXgHIWbRoWGDeWkRLuUjMrUK", "ec93ae39-255a-4252-ac50-cde8ecb05b0c", "sqiidVcxEgAxPSmbAlPuyyPqlajALcCishjt", "#FFFFFF");
    Category category3 = new Category("VbNXjdaXScgqBlggjkqLldrnWVJfkrCAoHXH", "ec93ae39-255a-4252-ac50-cde8ecb05b0c", "sqiidVcxEgAxPSmbAlPuyyPqlajALcCishjt", "#FFFFFF");
    Category category4 = new Category("vdoqmRiVgdUosYCZMDkucIbBqIHNMhyUoGbS", "ec93ae39-255a-4252-ac50-cde8ecb05b0c", "sqiidVcxEgAxPSmbAlPuyyPqlajALcCishjt", "#FFFFFF");
    Category category5 = new Category("ucYKgZgvfLUTdJVbOhDchrUawsfqUPTPkdNG", "ec93ae39-255a-4252-ac50-cde8ecb05b0c", "hCraEvQYiLHHoPurvcIVYtHtPbwnprHMRKEN", "#FFFFFF");
    Category category6 = new Category("bNQxZYSVjLmJDvJLVyOYNLdJGFKIWpjLhEsF", "333d977a-a025-428c-83ca-76a1741d6e0c", "hCraEvQYiLHHoPurvcIVYtHtPbwnprHMRKEN", "#FFFFFF");
    Category category7 = new Category("uEmuNJVOsGTRrqaxFojdSgRvKTJurHbjCDEZ", "333d977a-a025-428c-83ca-76a1741d6e0c", "hCraEvQYiLHHoPurvcIVYtHtPbwnprHMRKEN", "#FFFFFF");
    Category category8 = new Category("tlbsafOfuDUGULgwOiEkiYHEiGDAYdOipASn", "333d977a-a025-428c-83ca-76a1741d6e0c", "hCraEvQYiLHHoPurvcIVYtHtPbwnprHMRKEN", "#FFFFFF");
    Category category9 = new Category("kLRYjKtYnicVOWdVfsswWiDgaxdIjFoaxvxg", "333d977a-a025-428c-83ca-76a1741d6e0c", "hCraEvQYiLHHoPurvcIVYtHtPbwnprHMRKEN", "#FFFFFF");
    Category category10 = new Category("XTCGeIIYvATsbcwLadRCtYlKcQHsDKndeJJC", "91c9e669-6696-4902-8446-d4eebdc8acea", "XOaLTGEQbNNrHcYrPPpEOTlLxhePgsupNJgh", "#FFFFFF");
    Category category11 = new Category("BsdJLFpQtWEFWvYKBMsLtGiKiKGHXwUOlikP", "91c9e669-6696-4902-8446-d4eebdc8acea", "XOaLTGEQbNNrHcYrPPpEOTlLxhePgsupNJgh", "#FFFFFF");
    Category category12 = new Category("cqHALvMmMJwUecFCNpXLZicMHuywqThfjQfn", "91c9e669-6696-4902-8446-d4eebdc8acea", "XOaLTGEQbNNrHcYrPPpEOTlLxhePgsupNJgh", "#FFFFFF");
    Category category13 = new Category("yXfCtVnlIoyTsOFhxiMudRQvqubgvRCCGLsJ", "WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG", "XOaLTGEQbNNrHcYrPPpEOTlLxhePgsupNJgh", "#FFFFFF");
    Category category14 = new Category("MowPYhMmEqfgtCEmbZMyrXMnsrBXtKlRuMJu", "WaqPhqiekuhbwKdHIuYvMoNGAnAPfBlwTWKG", "XOaLTGEQbNNrHcYrPPpEOTlLxhePgsupNJgh", "#FFFFFF");

    categories.add(category0);
    categories.add(category1);
    categories.add(category2);
    categories.add(category3);
    categories.add(category4);
    categories.add(category5);
    categories.add(category6);
    categories.add(category7);
    categories.add(category8);
    categories.add(category9);
    categories.add(category10);
    categories.add(category11);
    categories.add(category12);
    categories.add(category13);
    categories.add(category14);
    Collections.sort(categories);
  }
  /**
   * @param _category The category to be added to the database
   * @return number of rows effected
   */
  @Override
  public int add(Category _category) throws SQLException {
    if (duplicateKey(_category)){
      return 0;
    }
    if (exceptionKey(_category)){
      throw new SQLException("error");
    }
    int size = categories.size();

    categories.add(_category);
    int newsize = categories.size();
    return newsize-size;
  }

  @Override
  public List<Category> getCategoryByUser(String User_ID){
    List<Category> results = new ArrayList<>();
    for (Category category : categories){
      if (category.getUser_ID().equals(User_ID)){
        results.add(category);
      }
    }
    return results;
  }

  @Override
  public int deleteCategory(String categoryID, String User_ID) throws SQLException {
    int size = categories.size();
    int location =-1;
    for (int i=0;i<categories.size();i++){
      if (categories.get(i).getCategory_ID().equals(categoryID)&&categories.get(i).getUser_ID().equals(User_ID)){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }
    categories.remove(location);
    int newsize = categories.size();
    return size-newsize;
  }


  @Override
  public int update(Category oldCategory, Category newCategory) throws SQLException {
    int location =-1;
    if (duplicateKey(oldCategory)){
      return 0;
    }
    if (exceptionKey(oldCategory)){
      throw new SQLException("error");
    }
    for (int i=0;i<categories.size();i++){
      if (categories.get(i).getCategory_ID().equals(oldCategory.getCategory_ID())
&&categories.get(i).getUser_ID().equals(oldCategory.getUser_ID())){
          location =i;
          break;
        }
      }
      if (location==-1){
        throw new SQLException();
      }

    categories.set(location,newCategory);
      return 1;
    }

  @Override
  public Category getCategoryByPrimaryKey(Category category) throws SQLException {
    Category result = null;
    for (Category _category : categories) {
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



  private boolean duplicateKey(Category _category){
    return (_category==null ||_category.getCategory_Name().contains("DUPLICATE"));
  }
  private boolean exceptionKey(Category _category){
    return (_category==null ||_category.getCategory_Name().contains("EXCEPTION"));
  }
}
