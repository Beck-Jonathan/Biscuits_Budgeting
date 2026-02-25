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
    Category category0 = new Category("fOmyBlIv", "8255af1b-2e9f-487d-b192-b30845feabfc");
    Category category1 = new Category("GLMVFfOv", "8255af1b-2e9f-487d-b192-b30845feabfc");
    Category category2 = new Category("HBYSCtHU", "8255af1b-2e9f-487d-b192-b30845feabfc");
    Category category3 = new Category("khvySdbm", "8255af1b-2e9f-487d-b192-b30845feabfc");
    Category category4 = new Category("ZTKajGXn", "8255af1b-2e9f-487d-b192-b30845feabfc");
    Category category5 = new Category("yTLuvxGG", "6b840299-407f-4c2b-a86b-53119cd8449d");
    Category category6 = new Category("IMrVdPEL", "6b840299-407f-4c2b-a86b-53119cd8449d");
    Category category7 = new Category("wmbdbfRl", "6b840299-407f-4c2b-a86b-53119cd8449d");
    Category category8 = new Category("ksbVeGEU", "6b840299-407f-4c2b-a86b-53119cd8449d");
    Category category9 = new Category("WBZWffQG", "6b840299-407f-4c2b-a86b-53119cd8449d");
    Category category10 = new Category("nvMJdMZW", "6b840299-407f-4c2b-a86b-53119cd8449d");
    Category category11 = new Category("htInVYNl", "fec75744-130e-4bcb-8bbe-9bee18080428");
    Category category12 = new Category("blROscKW", "fec75744-130e-4bcb-8bbe-9bee18080428");
    Category category13 = new Category("vIuUKHwc", "fec75744-130e-4bcb-8bbe-9bee18080428");
    Category category14 = new Category("SYDMAsdx", "fec75744-130e-4bcb-8bbe-9bee18080428");

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

  private boolean duplicateKey(Category _category){
    return _category.getCategory_ID().equals("DUPLICATE");
  }
  private boolean exceptionKey(Category _category){
    return _category.getCategory_ID().equals("EXCEPTION");
  }
}
