package com.beck.beck_demos.budget_app.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class ParentCategoryTest {
  private ParentCategory _parent_category;
  @BeforeEach
  public void setup(){
    _parent_category = new ParentCategory();
  }
  @AfterEach
  public void teardown(){
    _parent_category = null;
  }

  /**
   <p> Tests That the default constructor for the parent_category object works </p>
   */
  @Test
  public void testparent_categoryDefaultConstructorSetsNoVariables(){
    ParentCategory _parent_category= new ParentCategory();
    Assertions.assertNull(_parent_category.getparent_category_id());
    Assertions.assertNull(_parent_category.getsuper_category_name());
    Assertions.assertNull(_parent_category.getuser_id());
    Assertions.assertNull(_parent_category.getcolor_id());
  }

  /**
   <p> Tests That the Parameterized Constructor for the parent_category object works </p>
   */
  @Test
  public void testparent_categoryParameterizedConstructorSetsAllVariables(){
    ParentCategory _parent_category= new ParentCategory(
        "OKFvTPQjiZiibBgBTjUbgCVUqySGejjTEQ",
        "pQIyiRUEVYAIhMdfanbHjaPxcEixVjqDafPSxAvqqesEGDlPihoifikhQUWDXpkCGuOJjeeYSjycucNBJfUtoubCccrTYnIbDv",
        "qkZFDBQXxQlZPWgFHIRAvMaGmQybXxlafR",
        "sclpH"
    );
    Assertions.assertEquals("OKFvTPQjiZiibBgBTjUbgCVUqySGejjTEQ",_parent_category.getparent_category_id());
    Assertions.assertEquals("pQIyiRUEVYAIhMdfanbHjaPxcEixVjqDafPSxAvqqesEGDlPihoifikhQUWDXpkCGuOJjeeYSjycucNBJfUtoubCccrTYnIbDv",_parent_category.getsuper_category_name());
    Assertions.assertEquals("qkZFDBQXxQlZPWgFHIRAvMaGmQybXxlafR",_parent_category.getuser_id());
    Assertions.assertEquals("sclpH",_parent_category.getcolor_id());
  }

  /**
   <p> Tests That the Parameterized Constructor for the parent_category object works </p>
   */
  @Test
  public void testparent_categoryKeyedParameterizedConstructorSetsKeyedVariables(){
    ParentCategory _parent_category= new ParentCategory(
        "XECHCZuepnNvyPjRrWrMhmoBpPlTjyUMvX"
    );
    Assertions.assertEquals("XECHCZuepnNvyPjRrWrMhmoBpPlTjyUMvX",_parent_category.getparent_category_id());
    Assertions.assertNull(_parent_category.getsuper_category_name());
    Assertions.assertNull(_parent_category.getuser_id());
    Assertions.assertNull(_parent_category.getcolor_id());
  }

  /**
   <p> Tests That the Setters for the parent_category.parent_category_id field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testparent_categoryThrowsIllegalArgumentExceptionIfparent_category_idTooShort(){
    String parent_category_id = "xd";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_parent_category.setparent_category_id(parent_category_id);});
  }

  /**
   <p> Tests That the Setters for the parent_category.parent_category_id field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testparent_categoryThrowsIllegalArgumentExceptionIfparent_category_idTooLong(){
    String parent_category_id = "SuhSiaBINeMsdcjRMGcfsTmMyuUrscKlERwoGr";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_parent_category.setparent_category_id(parent_category_id);});
  }

  /**
   <p> Tests That the Setters for the parent_category.parent_category_id field work </p>
   */
  @Test
  public void testSetparent_category_idSetsparent_category_id(){
    String parent_category_id = "b66c565b-4514-4b09-88c6-7817f1d41d5d";
    _parent_category.setparent_category_id(parent_category_id);
    Assertions.assertEquals(parent_category_id,_parent_category.getparent_category_id());
  }

  /**
   <p> Tests That the Setters for the parent_category.super_category_name field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testparent_categoryThrowsIllegalArgumentExceptionIfsuper_category_nameTooShort(){
    String super_category_name = "Qb";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_parent_category.setsuper_category_name(super_category_name);});
  }

  /**
   <p> Tests That the Setters for the parent_category.super_category_name field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testparent_categoryThrowsIllegalArgumentExceptionIfsuper_category_nameTooLong(){
    String super_category_name = "eJqekKqvoUifhlFwfhyYaYFGsUVxnDNyVgmUnxLFEhmbfAfesegYvLfDxbYWtWXhODjQrTKMTXSQiVRJJbSEJAiWMoKgPESbPVlILr";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_parent_category.setsuper_category_name(super_category_name);});
  }

  /**
   <p> Tests That the Setters for the parent_category.super_category_name field work </p>
   */
  @Test
  public void testSetsuper_category_nameSetssuper_category_name(){
    String super_category_name = "OkgrDFjNpUKPJvwyXRfINwGFuXcHKrERlxiTiZZZjIDiGccJnJLFilwKSdhKRZOKNdhqMhoDVnoAdADuFfybGpkpddXVsfFRyX";
    _parent_category.setsuper_category_name(super_category_name);
    Assertions.assertEquals(super_category_name,_parent_category.getsuper_category_name());
  }

  /**
   <p> Tests That the Setters for the parent_category.user_id field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testparent_categoryThrowsIllegalArgumentExceptionIfuser_idTooShort(){
    String user_id = "xt";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_parent_category.setuser_id(user_id);});
  }

  /**
   <p> Tests That the Setters for the parent_category.user_id field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testparent_categoryThrowsIllegalArgumentExceptionIfuser_idTooLong(){
    String user_id = "haVdDJNJpiOBxJjVwyxZuKWdkUxcFOEtwIcMhU";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_parent_category.setuser_id(user_id);});
  }

  /**
   <p> Tests That the Setters for the parent_category.user_id field work </p>
   */
  @Test
  public void testSetuser_idSetsuser_id(){
    String user_id = "1885c891-10f5-4d9d-a401-7764cd0c0e37";
    _parent_category.setuser_id(user_id);
    Assertions.assertEquals(user_id,_parent_category.getuser_id());
  }

  /**
   <p> Tests That the Setters for the parent_category.color_id field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testparent_categoryThrowsIllegalArgumentExceptionIfcolor_idTooShort(){
    String color_id = "VT";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_parent_category.setcolor_id(color_id);});
  }

  /**
   <p> Tests That the Setters for the parent_category.color_id field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testparent_categoryThrowsIllegalArgumentExceptionIfcolor_idTooLong(){
    String color_id = "eWisgBBnI";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_parent_category.setcolor_id(color_id);});
  }

  /**
   <p> Tests That the Setters for the parent_category.color_id field work </p>
   */
  @Test
  public void testSetcolor_idSetscolor_id(){
    String color_id = "#FFFFFF";
    _parent_category.setcolor_id(color_id);
    Assertions.assertEquals(color_id,_parent_category.getcolor_id());
  }


  /**
   <p> Tests That the CompareTo Method for the parent_category object works </p>
   */
  @Test
  public void testCompareToCanCompareForEachDateField() {
    ParentCategory smaller = new ParentCategory();
    ParentCategory bigger = new ParentCategory();
//to compare a smaller and larger parent_category_id
    smaller.setparent_category_id("a66c565b-4514-4b09-88c6-7817f1d41d5d");
    bigger.setparent_category_id("b66c565b-4514-4b09-88c6-7817f1d41d5d");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the parent_category_id as equal.
    smaller.setparent_category_id("b66c565b-4514-4b09-88c6-7817f1d41d5d");
//to compare a smaller and larger super_category_name
    smaller.setsuper_category_name("aaaa");
    bigger.setsuper_category_name("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the super_category_name as equal.
    smaller.setsuper_category_name("bbbb");
//to compare a smaller and larger user_id
    smaller.setuser_id("0885c891-10f5-4d9d-a401-7764cd0c0e37");
    bigger.setuser_id("1885c891-10f5-4d9d-a401-7764cd0c0e37");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the user_id as equal.
    smaller.setuser_id("1885c891-10f5-4d9d-a401-7764cd0c0e37");
//to compare a smaller and larger color_id
    smaller.setcolor_id("#CCCCCC");
    bigger.setcolor_id("#DDDDDD");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the color_id as equal.
    smaller.setcolor_id("#DDDDDD");
    Assertions.assertTrue(bigger.compareTo(smaller)==0);
  }

}





