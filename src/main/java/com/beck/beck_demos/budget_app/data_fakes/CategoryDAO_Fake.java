package com.beck.beck_demos.budget_app.data_fakes;

import com.beck.beck_demos.budget_app.iData.iCategoryDAO;
import com.beck.beck_demos.budget_app.models.Category;
import com.beck.beck_demos.budget_app.models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoryDAO_Fake implements iCategoryDAO {
  private  List<Category> categories;
  public CategoryDAO_Fake(){
    categories = new ArrayList<>();
    Category category0 = new Category("fOmyBlIv", 36);
    Category category1 = new Category("GLMVFfOv", 36);
    Category category2 = new Category("HBYSCtHU", 36);
    Category category3 = new Category("khvySdbm", 36);
    Category category4 = new Category("ZTKajGXn", 36);
    Category category5 = new Category("yTLuvxGG", 13);
    Category category6 = new Category("IMrVdPEL", 13);
    Category category7 = new Category("wmbdbfRl", 13);
    Category category8 = new Category("ksbVeGEU", 13);
    Category category9 = new Category("WBZWffQG", 13);
    Category category10 = new Category("nvMJdMZW", 47);
    Category category11 = new Category("htInVYNl", 47);
    Category category12 = new Category("blROscKW", 47);
    Category category13 = new Category("vIuUKHwc", 47);
    Category category14 = new Category("SYDMAsdx", 47);

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
  @Override
  public int add(Category _category, int user_ID) throws SQLException {
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
  public List<Category> getCategoryByUser(int User_ID){
    List<Category> results = new ArrayList<>();
    for (Category category : categories){
      if (category.getUser_ID().equals(User_ID)){
        results.add(category);
      }
    }
    return results;
  }

  @Override
  public int deleteCategory(String categoryID, int User_ID) throws SQLException {
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

  private boolean duplicateKey(Category _category){
    return _category.getCategory_ID().equals("DUPLICATE");
  }
  private boolean exceptionKey(Category _category){
    return _category.getCategory_ID().equals("EXCEPTION");
  }
}
