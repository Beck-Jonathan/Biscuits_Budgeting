package com.beck.beck_demos.budget_app.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SubCategoryTest {
  private SubCategory _subCategory;
  @BeforeEach
  public void setup(){
    _subCategory = new SubCategory();
  }
  @AfterEach
  public void teardown(){
    _subCategory = null;
  }

  /**
   <p> Tests That the default constructor for the Category object works </p>
   */
  @Test
  public void testCategoryDefaultConstructorSetsNoVariables(){
    SubCategory _category= new SubCategory();
    Assertions.assertNull(_category.getCategory_ID());
    Assertions.assertNull(_category.getUser_ID());
  }

  /**
   <p> Tests That the Parameterized Constructor for the Category object works </p>
   */
  @Test
  public void testCategoryParameterizedConstructorSetsAllVariables(){
    SubCategory _category= new SubCategory(
        "bf735dfc-22a9-4214-a8e5-fb8de2305700",
        "01b9701b-c114-4036-a78a-18b43a991994",
        "af735dfc-22a9-4214-a8e5-fb8de2305700",
        "Testing",
        "#ffffff"
    );
    Assertions.assertEquals("bf735dfc-22a9-4214-a8e5-fb8de2305700",_category.getCategory_ID());
    Assertions.assertEquals("01b9701b-c114-4036-a78a-18b43a991994",_category.getParentCategoryId());
    Assertions.assertEquals("af735dfc-22a9-4214-a8e5-fb8de2305700",_category.getUser_ID());
    Assertions.assertEquals("Testing",_category.getCategory_Name());
    Assertions.assertEquals("#FFFFFF",_category.getcolor_id());
  }

  /**
   <p> Tests That the Parameterized Constructor for the Category object works </p>
   */
  @Test
  public void testCategoryKeyedParameterizedConstructorSetsKeyedVariables(){
    SubCategory _category= new SubCategory(
        "46fcffea-d21c-4254-814d-926d0086d77c",
        "Testing"
    );
    Assertions.assertEquals("46fcffea-d21c-4254-814d-926d0086d77c",_category.getCategory_ID());
    Assertions.assertEquals("Testing",_category.getCategory_Name());
  }

  /**
   <p> Tests That the Setters for the Category.Category_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testCategoryThrowsIllegalArgumentExceptionIfCategory_IDTooShort(){
    String Category_ID = "735dfc-22a9-4214-a8e5-fb8de2305700";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _subCategory.setCategory_ID(Category_ID);});
  }

  /**
   <p> Tests That the Setters for the Category.Category_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testCategoryThrowsIllegalArgumentExceptionIfCategory_IDTooLong(){
    String Category_ID = "bf735dfc-22a9-4214-a8e5-fb8de2305700ddd";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _subCategory.setCategory_ID(Category_ID);});
  }

  /**
   <p> Tests That the Setters for the sub_category.parent_category_id field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testsub_categoryThrowsIllegalArgumentExceptionIfparent_category_idTooShort(){
    String parent_category_id = "Vg";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_subCategory.setParentCategoryId(parent_category_id);});
  }

  /**
   <p> Tests That the Setters for the sub_category.parent_category_id field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testsub_categoryThrowsIllegalArgumentExceptionIfparent_category_idTooLong(){
    String parent_category_id = "mUnxLFEhmbfAfesegYvLfDxbYWtWXhODjQrTKM";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_subCategory.setParentCategoryId(parent_category_id);});
  }

  /**
   <p> Tests That the Setters for the sub_category.parent_category_id field work </p>
   */
  @Test
  public void testSetparent_category_idSetsparent_category_id(){
    String parent_category_id = "sqiidVcxEgAxPSmbAlPuyyPqlajALcCishjt";
    _subCategory.setParentCategoryId(parent_category_id);
    Assertions.assertEquals(parent_category_id,_subCategory.getParentCategoryId());
  }

  /**
   <p> Tests That the Setters for the Category.Category_ID field work </p>
   */
  @Test
  public void testSetColorSetsColor(){
    String Color_ID = "#AABBCC";
    _subCategory.setcolor_id(Color_ID);
    Assertions.assertEquals(Color_ID, _subCategory.getcolor_id());
  }
  @Test
  public void testSetColorSetsColorToUpperCase(){
    String Color_ID = "#aabbcc";
    String Color_Upper="#AABBCC";
    _subCategory.setcolor_id(Color_ID);
    Assertions.assertEquals(Color_Upper, _subCategory.getcolor_id());
  }
  @Test
  public void  testCategoryThrowsIllegalArgumentExceptionIfColorTooShort(){
    String Color_ID = "ra";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _subCategory.setcolor_id(Color_ID);});
  }
  @Test
  public void  testCategoryThrowsIllegalArgumentExceptionIfColorTooLong(){
    String Color_ID = "#AABBCCD";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _subCategory.setcolor_id(Color_ID);});
  }

  /**
   <p> Tests That the Setters for the Category.Category_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testCategoryThrowsIllegalArgumentExceptionIfColor_IDInvalid(){
    String Color_ID = "aGGHHii";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _subCategory.setcolor_id(Color_ID);});
  }

  /**
   <p> Tests That the Setters for the Category.Category_ID field work </p>
   */
  @Test
  public void testSetCategory_IDSetsCategory_ID(){
    String Category_ID = "bf735dfc-22a9-4214-a8e5-fb8de2305700";
    _subCategory.setCategory_ID(Category_ID);
    Assertions.assertEquals(Category_ID, _subCategory.getCategory_ID());
  }

  /**
   <p> Tests That the Setters for the Category.User_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testCategoryThrowsIllegalArgumentExceptionIfUser_IDTooSmall(){
    String User_ID = "af735dfc-22a9-4214-a8e5-fb8057";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _subCategory.setUser_ID(User_ID);});
  }

  /**
   <p> Tests That the Setters for the Category.User_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testCategoryThrowsIllegalArgumentExceptionIfUser_IDTooBig(){
    String User_ID = "af735dfc-22a9-4214-a8e5-fb80sssssss57";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _subCategory.setUser_ID(User_ID);});
  }

  /**
   <p> Tests That the Setters for the Category.User_ID field work </p>
   */
  @Test
  public void testCategorySetsUser_ID(){
    String User_ID = "af735dfc-22a9-4214-a8e5-fb8de2305700";
    _subCategory.setUser_ID(User_ID);
    Assertions.assertEquals(User_ID, _subCategory.getUser_ID());
  }

  /**
   <p> Tests That the Setters for the Category.CategoryName field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testCategoryThrowsIllegalArgumentExceptionIfNameTooSmall(){
    String Name = "x";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _subCategory.setCategory_Name(Name);});
  }

  /**
   <p> Tests That the Setters for the Category.CategoryName field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testCategoryThrowsIllegalArgumentExceptionIfNameTooBig(){
    String Name = "af735dfc-22a9-4214-a8e5-fb80sssssss57af735dfc-22a9-4214-a8e5-fb80sssssss57af735dfc-22a9-4214-a8e5-fb80sssssss57af735dfc-22a9-4214-a8e5-fb80sssssss57af735dfc-22a9-4214-a8e5-fb80sssssss57";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _subCategory.setCategory_Name(Name);});
  }

  /**
   <p> Tests That the Setters for the Category.CategoryName field work </p>
   */
  @Test
  public void testCategorySetsName(){
    String Name = "Testing";
    _subCategory.setCategory_Name(Name);
    Assertions.assertEquals(Name, _subCategory.getCategory_Name());
  }


  /**
   <p> Tests That the CompareTo Method for the Category object works </p>
   */
  @Test
  public void testCompareToCanCompareForEachDateField() {
    SubCategory smaller = new SubCategory();
    SubCategory bigger = new SubCategory();
//to compare a smaller and larger Category_ID
    smaller.setCategory_ID("36fcffea-d21c-4254-814d-926d0086d77c");
    bigger.setCategory_ID("46fcffea-d21c-4254-814d-926d0086d77c");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Category_ID as equal.
    smaller.setCategory_ID("46fcffea-d21c-4254-814d-926d0086d77c");
//to compare a smaller and larger User_ID
    smaller.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    bigger.setUser_ID("gec75744-130e-4bcb-8bbe-9bee18080428");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the User_ID as equal.
    smaller.setUser_ID("gec75744-130e-4bcb-8bbe-9bee18080428");;
    //to compare a smaller and larger Name
    smaller.setCategory_Name("aaaa");
    bigger.setCategory_Name("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Category_ID as equal.
    smaller.setCategory_Name("bbbb");

    //to compare a smaller and larger Color
    smaller.setcolor_id("#AAAABB");
    bigger.setcolor_id("#AABBCC");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the User_ID as equal.
    smaller.setcolor_id("#AABBCC");;
    Assertions.assertTrue(bigger.compareTo(smaller)==0);
  }

}
