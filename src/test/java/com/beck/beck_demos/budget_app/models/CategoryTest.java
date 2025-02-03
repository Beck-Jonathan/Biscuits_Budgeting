package com.beck.beck_demos.budget_app.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class CategoryTest {
  private Category _category;
  @BeforeEach
  public void setup(){
    _category = new Category();
  }
  @AfterEach
  public void teardown(){
    _category = null;
  }
  @Test
  public void testCategoryDefaultConstructorSetsNoVariables(){
    Category _category= new Category();
    Assertions.assertNull(_category.getCategory_ID());
    Assertions.assertNull(_category.getUser_ID());
  }
  @Test
  public void testCategoryParameterizedConstructorSetsAllVariables(){
    Category _category= new Category(
        "TOvVfrhfSJMFxjTgNvIaUVtJMJivdrRDhIIcdgjQdJmmvyDrGOltaSdePeUqBcWTTtdEQylYNiZEUUGWMfpHHDFBynukTglkrP",
        4955
    );
    Assertions.assertEquals("TOvVfrhfSJMFxjTgNvIaUVtJMJivdrRDhIIcdgjQdJmmvyDrGOltaSdePeUqBcWTTtdEQylYNiZEUUGWMfpHHDFBynukTglkrP",_category.getCategory_ID());
    Assertions.assertEquals(4955,_category.getUser_ID());
  }
  @Test
  public void testCategoryKeyedParameterizedConstructorSetsKeyedVariables(){
    Category _category= new Category(
        "KaBOtKLQAkeFSjLVpDQKpgtOmbdNGACIPGsItoinJGNHvAaUBkYsRjUUfXehVFPbOFhdNbWffGpBPLUaIplKAXfcHrUkrTHLQu",
        589
    );
    Assertions.assertEquals("KaBOtKLQAkeFSjLVpDQKpgtOmbdNGACIPGsItoinJGNHvAaUBkYsRjUUfXehVFPbOFhdNbWffGpBPLUaIplKAXfcHrUkrTHLQu",_category.getCategory_ID());
    Assertions.assertEquals(589,_category.getUser_ID());
  }
  @Test
  public void  testCategoryThrowsIllegalArgumentExceptionIfCategory_IDTooShort(){
    String Category_ID = "nh";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_category.setCategory_ID(Category_ID);});
  }
  @Test
  public void  testCategoryThrowsIllegalArgumentExceptionIfCategory_IDTooLong(){
    String Category_ID = "bEgGRIHkiTwLICbyAwOnWrNUquxqwsGTQUbjdCHuIyJiuDpJQHPGjYtKPoMVGNOvOievplYjOLIyYHgflpMvXZnolotjMZMXTNlQhO";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_category.setCategory_ID(Category_ID);});
  }
  @Test
  public void testSetCategory_IDSetsCategory_ID(){
    String Category_ID = "hfxHonNYbFBIivECvffpxhmAmYsRRNtYSxBVRNGYEiWjhNbBbrHAZCnyhAmpCEkDuIQQqPhRViLNjjUlYsvMDVdMSuNNUevKfs";
    _category.setCategory_ID(Category_ID);
    Assertions.assertEquals(Category_ID,_category.getCategory_ID());
  }
  @Test
  public void testCategoryThrowsIllegalArgumentExceptionIfUser_IDTooSmall(){
    int User_ID = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_category.setUser_ID(User_ID);});
  }
  @Test
  public void testCategoryThrowsIllegalArgumentExceptionIfUser_IDTooBig(){
    int User_ID = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_category.setUser_ID(User_ID);});
  }
  @Test
  public void testCategorySetsUser_ID(){
    int User_ID = 3055;
    _category.setUser_ID(User_ID);
    Assertions.assertEquals(User_ID, _category.getUser_ID());
  }
  @Test
      public void testcompoareto(){
  Category smaller = new Category();
  Category bigger = new Category();
smaller.setCategory_ID("aaaa");
bigger.setCategory_ID("bbbbb");
Assertions.assertTrue(smaller.compareTo(bigger)<0);
Assertions.assertTrue(bigger.compareTo(smaller)>0);
smaller.setCategory_ID("bbbbb");
smaller.setUser_ID(1);
bigger.setUser_ID(2);
Assertions.assertTrue(smaller.compareTo(bigger)<0);
Assertions.assertTrue(bigger.compareTo(smaller)>0);
smaller.setUser_ID(2);
int x = 0;
Assertions.assertTrue(bigger.compareTo(smaller)==0);
}

}

