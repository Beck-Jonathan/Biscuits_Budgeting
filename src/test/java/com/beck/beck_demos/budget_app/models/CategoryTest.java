package com.beck.beck_demos.budget_app.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

  /**
   <p> Tests That the default constructor for the Category object works </p>
   */
  @Test
  public void testCategoryDefaultConstructorSetsNoVariables(){
    Category _category= new Category();
    Assertions.assertNull(_category.getCategory_ID());
    Assertions.assertNull(_category.getUser_ID());
  }

  /**
   <p> Tests That the Parameterized Constructor for the Category object works </p>
   */
  @Test
  public void testCategoryParameterizedConstructorSetsAllVariables(){
    Category _category= new Category(
        "oMlOpGePhFrUEEEAXemZMSyNCKMVmSXFqtSSMhPJgPWRrSdAxTNctmBnQsOUbZONKhEXFJCejpjRslOSErOfHQvnBgSgwDenZp",
        "af735dfc-22a9-4214-a8e5-fb8de2305700"
    );
    Assertions.assertEquals("oMlOpGePhFrUEEEAXemZMSyNCKMVmSXFqtSSMhPJgPWRrSdAxTNctmBnQsOUbZONKhEXFJCejpjRslOSErOfHQvnBgSgwDenZp",_category.getCategory_ID());
    Assertions.assertEquals("af735dfc-22a9-4214-a8e5-fb8de2305700",_category.getUser_ID());
  }

  /**
   <p> Tests That the Parameterized Constructor for the Category object works </p>
   */
  @Test
  public void testCategoryKeyedParameterizedConstructorSetsKeyedVariables(){
    Category _category= new Category(
        "aNEOCKDxyMCDVWxrDWQIUSCsggFSYXwpniXEFwnPdbffvVYUFwuKuyRWRtJneFcPxQFdtCYQAAIvyCrsrEmEfsTfeCDWXHOaZR",
        "af735dfc-22a9-4214-a8e5-fb8de2305700"
    );
    Assertions.assertEquals("aNEOCKDxyMCDVWxrDWQIUSCsggFSYXwpniXEFwnPdbffvVYUFwuKuyRWRtJneFcPxQFdtCYQAAIvyCrsrEmEfsTfeCDWXHOaZR",_category.getCategory_ID());
    Assertions.assertEquals("af735dfc-22a9-4214-a8e5-fb8de2305700",_category.getUser_ID());
  }

  /**
   <p> Tests That the Setters for the Category.Category_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testCategoryThrowsIllegalArgumentExceptionIfCategory_IDTooShort(){
    String Category_ID = "ra";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_category.setCategory_ID(Category_ID);});
  }

  /**
   <p> Tests That the Setters for the Category.Category_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testCategoryThrowsIllegalArgumentExceptionIfCategory_IDTooLong(){
    String Category_ID = "AVQkNobMfjkytBqcPOXsOwcgqmRYaNclhUIGqDSWSfdNiqBBhlYhDhwntMJWWkSZJWUaqewLBMbaMHoAPgEXsOXKIIFFstMWrdGGun";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_category.setCategory_ID(Category_ID);});
  }

  /**
   <p> Tests That the Setters for the Category.Category_ID field work </p>
   */
  @Test
  public void testSetCategory_IDSetsCategory_ID(){
    String Category_ID = "krfkOSedQObkDpVkFIgnFvSeVKgbBhEvMkHAFrqIqWBGGAxOSvmjXcvvhMtfasJyVFDHvvNYKmyJAjpJAYAjXIEcJdcPrHjutG";
    _category.setCategory_ID(Category_ID);
    Assertions.assertEquals(Category_ID,_category.getCategory_ID());
  }

  /**
   <p> Tests That the Setters for the Category.User_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testCategoryThrowsIllegalArgumentExceptionIfUser_IDTooSmall(){
    String User_ID = "af735dfc-22a9-4214-a8e5-fb8057";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_category.setUser_ID(User_ID);});
  }

  /**
   <p> Tests That the Setters for the Category.User_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void testCategoryThrowsIllegalArgumentExceptionIfUser_IDTooBig(){
    String User_ID = "af735dfc-22a9-4214-a8e5-fb80sssssss57";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_category.setUser_ID(User_ID);});
  }

  /**
   <p> Tests That the Setters for the Category.User_ID field work </p>
   */
  @Test
  public void testCategorySetsUser_ID(){
    String User_ID = "af735dfc-22a9-4214-a8e5-fb8de2305700";
    _category.setUser_ID(User_ID);
    Assertions.assertEquals(User_ID, _category.getUser_ID());
  }


  /**
   <p> Tests That the CompareTo Method for the Category object works </p>
   */
  @Test
  public void testCompareToCanCompareForEachDateField() {
    Category smaller = new Category();
    Category bigger = new Category();
//to compare a smaller and larger Category_ID
    smaller.setCategory_ID("aaaa");
    bigger.setCategory_ID("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Category_ID as equal.
    smaller.setCategory_ID("bbbb");
//to compare a smaller and larger User_ID
    smaller.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    bigger.setUser_ID("gec75744-130e-4bcb-8bbe-9bee18080428");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the User_ID as equal.
    smaller.setUser_ID("gec75744-130e-4bcb-8bbe-9bee18080428");;
    Assertions.assertTrue(bigger.compareTo(smaller)==0);
  }

}
