package com.beck.beck_demos.budget_app.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Saved_Search_Order_LineTest {


  private Saved_Search_Order_Line _saved_search_order_line;
  @BeforeEach
  public void setup(){
    _saved_search_order_line = new Saved_Search_Order_Line();
  }
  @AfterEach
  public void teardown(){
    _saved_search_order_line = null;
  }
  @Test
  public void testSaved_Search_Order_LineDefaultConstructorSetsNoVariables(){
    Saved_Search_Order_Line _saved_search_order_line= new Saved_Search_Order_Line();
    Assertions.assertNull(_saved_search_order_line.getSaved_Search_Order_ID());
    Assertions.assertNull(_saved_search_order_line.getLine_No());
    Assertions.assertNull(_saved_search_order_line.getCategory_ID());
    Assertions.assertNull(_saved_search_order_line.getUser_ID());
    Assertions.assertNull(_saved_search_order_line.getSearch_Phrase());
    Assertions.assertFalse(_saved_search_order_line.getIs_Active());
  }
  @Test
  public void testSaved_Search_Order_LineParameterizedConstructorSetsAllVariables(){
    Saved_Search_Order_Line _saved_search_order_line= new Saved_Search_Order_Line(
        6151,
        3215,
        "REIgRuBpxqGJOcvVhBWCikBdLECBANExCGNqlJbKQYsohcibNePgcZwtWesYpGvWQapPurnUkQqMucUZmVwWUbIgwUhlRwYWBs",
        3094,
        "jiWsOhfihBxCkxyvygcaaTGcQysmaICfaFswOsJmXyYCLiJIClfXgotvDpAEWpFLeRgqvosYtUDPYskZnIUvBGwXbwevADqKop",
        true
    );
    Assertions.assertEquals(6151,_saved_search_order_line.getSaved_Search_Order_ID());
    Assertions.assertEquals(3215,_saved_search_order_line.getLine_No());
    Assertions.assertEquals("REIgRuBpxqGJOcvVhBWCikBdLECBANExCGNqlJbKQYsohcibNePgcZwtWesYpGvWQapPurnUkQqMucUZmVwWUbIgwUhlRwYWBs",_saved_search_order_line.getCategory_ID());
    Assertions.assertEquals(3094,_saved_search_order_line.getUser_ID());
    Assertions.assertEquals("jiWsOhfihBxCkxyvygcaaTGcQysmaICfaFswOsJmXyYCLiJIClfXgotvDpAEWpFLeRgqvosYtUDPYskZnIUvBGwXbwevADqKop",_saved_search_order_line.getSearch_Phrase());
    Assertions.assertTrue(_saved_search_order_line.getIs_Active());
  }
  @Test
  public void testSaved_Search_Order_LineKeyedParameterizedConstructorSetsKeyedVariables(){
    Saved_Search_Order_Line _saved_search_order_line= new Saved_Search_Order_Line(
        5343,
        2508
    );
    Assertions.assertEquals(5343,_saved_search_order_line.getSaved_Search_Order_ID());
    Assertions.assertEquals(2508,_saved_search_order_line.getLine_No());
    Assertions.assertNull(_saved_search_order_line.getCategory_ID());
    Assertions.assertNull(_saved_search_order_line.getUser_ID());
    Assertions.assertNull(_saved_search_order_line.getSearch_Phrase());
    Assertions.assertFalse(_saved_search_order_line.getIs_Active());
  }
  @Test
  public void testSaved_Search_Order_LineThrowsIllegalArgumentExceptionIfSaved_Search_Order_IDTooSmall(){
    int Saved_Search_Order_ID = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_saved_search_order_line.setSaved_Search_Order_ID(Saved_Search_Order_ID);});
  }
  @Test
  public void testSaved_Search_Order_LineThrowsIllegalArgumentExceptionIfSaved_Search_Order_IDTooBig(){
    int Saved_Search_Order_ID = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_saved_search_order_line.setSaved_Search_Order_ID(Saved_Search_Order_ID);});
  }
  @Test
  public void testSaved_Search_Order_LineSetsSaved_Search_Order_ID(){
    int Saved_Search_Order_ID = 9146;
    _saved_search_order_line.setSaved_Search_Order_ID(Saved_Search_Order_ID);
    Assertions.assertEquals(Saved_Search_Order_ID, _saved_search_order_line.getSaved_Search_Order_ID());
  }
  @Test
  public void testSaved_Search_Order_LineThrowsIllegalArgumentExceptionIfLine_NoTooSmall(){
    int Line_No = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_saved_search_order_line.setLine_No(Line_No);});
  }
  @Test
  public void testSaved_Search_Order_LineThrowsIllegalArgumentExceptionIfLine_NoTooBig(){
    int Line_No = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_saved_search_order_line.setLine_No(Line_No);});
  }
  @Test
  public void testSaved_Search_Order_LineSetsLine_No(){
    int Line_No = 5512;
    _saved_search_order_line.setLine_No(Line_No);
    Assertions.assertEquals(Line_No, _saved_search_order_line.getLine_No());
  }
  @Test
  public void  testSaved_Search_Order_LineThrowsIllegalArgumentExceptionIfCategory_IDTooShort(){
    String Category_ID = "s";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_saved_search_order_line.setCategory_ID(Category_ID);});
  }
  @Test
  public void  testSaved_Search_Order_LineThrowsIllegalArgumentExceptionIfCategory_IDTooLong(){
    String Category_ID = "mWGariWdWwjpprwRWYbPUCCTvcvNnDFSRuiNNRdaskUyxDRcJTBDmnhpOJgjRJCbIKQenIRywdrOdebloNWKZdmRjEdftPAAQDrNtM";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_saved_search_order_line.setCategory_ID(Category_ID);});
  }
  @Test
  public void testSetCategory_IDSetsCategory_ID(){
    String Category_ID = "pJobeExhmueXhjfrYNUxxyddRivQSKedQoLgMyfnrxdcSaWAkyKULpbAslOkclDYpfnMNigFAVBRkRvqKPFxcfSNWCWVIpPHTw";
    _saved_search_order_line.setCategory_ID(Category_ID);
    Assertions.assertEquals(Category_ID,_saved_search_order_line.getCategory_ID());
  }
  @Test
  public void testSaved_Search_Order_LineThrowsIllegalArgumentExceptionIfUser_IDTooSmall(){
    int User_ID = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_saved_search_order_line.setUser_ID(User_ID);});
  }
  @Test
  public void testSaved_Search_Order_LineThrowsIllegalArgumentExceptionIfUser_IDTooBig(){
    int User_ID = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_saved_search_order_line.setUser_ID(User_ID);});
  }
  @Test
  public void testSaved_Search_Order_LineSetsUser_ID(){
    int User_ID = 2694;
    _saved_search_order_line.setUser_ID(User_ID);
    Assertions.assertEquals(User_ID, _saved_search_order_line.getUser_ID());
  }
  @Test
  public void  testSaved_Search_Order_LineThrowsIllegalArgumentExceptionIfSearch_PhraseTooShort(){
    String Search_Phrase = "kh";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_saved_search_order_line.setSearch_Phrase(Search_Phrase);});
  }
  @Test
  public void  testSaved_Search_Order_LineThrowsIllegalArgumentExceptionIfSearch_PhraseTooLong(){
    String Search_Phrase = "GpMybBwnvkPQmTrCHsASdieMKCdkccaZAbyIWgkLZOZdsmhQoVkIDWLAoXLKTJpQLUjfInrVOASepCSmLhqbvVqLnNDFZCOPYxdTNw";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_saved_search_order_line.setSearch_Phrase(Search_Phrase);});
  }
  @Test
  public void testSetSearch_PhraseSetsSearch_Phrase(){
    String Search_Phrase = "PhIKgJyaIrxIZgmmPcvyeRIaPVPWEhZwjapYligMMFLtocfLUjRweVydXmiKEJGBXrpTBavjmGcFVagkrTjflvZHRsCABZZwlS";
    _saved_search_order_line.setSearch_Phrase(Search_Phrase);
    Assertions.assertEquals(Search_Phrase,_saved_search_order_line.getSearch_Phrase());
  }
  @Test
  public void testSaved_Search_Order_LineSetsIs_ActiveasFalse(){
    boolean status = false;
    _saved_search_order_line.setIs_Active(status);
    Assertions.assertEquals(status, _saved_search_order_line.getIs_Active());
  }
  @Test
  public void testSaved_Search_Order_LineSetsIs_ActiveasTrue(){
    boolean status = true;
    _saved_search_order_line.setIs_Active(status);
    Assertions.assertEquals(status, _saved_search_order_line.getIs_Active());
  }
  @Test
  public void testCompareToCanCompareForEachDateField() {
    Saved_Search_Order_Line smaller = new Saved_Search_Order_Line();
    Saved_Search_Order_Line bigger = new Saved_Search_Order_Line();
//to compare a smaller and larger Saved_Search_Order_ID
    smaller.setSaved_Search_Order_ID(10);
    bigger.setSaved_Search_Order_ID(20);
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Saved_Search_Order_ID as equal.
    smaller.setSaved_Search_Order_ID(20);
//to compare a smaller and larger Line_No
    smaller.setLine_No(10);
    bigger.setLine_No(20);
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Line_No as equal.
    smaller.setLine_No(20);
//to compare a smaller and larger Category_ID
    smaller.setCategory_ID("aaaa");
    bigger.setCategory_ID("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Category_ID as equal.
    smaller.setCategory_ID("bbbb");
//to compare a smaller and larger User_ID
    smaller.setUser_ID(10);
    bigger.setUser_ID(20);
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the User_ID as equal.
    smaller.setUser_ID(20);
//to compare a smaller and larger Search_Phrase
    smaller.setSearch_Phrase("aaaa");
    bigger.setSearch_Phrase("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Search_Phrase as equal.
    smaller.setSearch_Phrase("bbbb");
//to compare a smaller and larger Is_Active
    smaller.setIs_Active(false);
    bigger.setIs_Active(true);
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Is_Active as equal.
    smaller.setIs_Active(true);
    Assertions.assertTrue(bigger.compareTo(smaller)==0);
  }

}

