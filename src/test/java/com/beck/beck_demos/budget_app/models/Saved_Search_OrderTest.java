package com.beck.beck_demos.budget_app.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class Saved_Search_OrderTest {

  private Saved_Search_Order _saved_search_order;
  @BeforeEach
  public void setup(){
    _saved_search_order = new Saved_Search_Order();
  }
  @AfterEach
  public void teardown(){
    _saved_search_order = null;
  }
  @Test
  public void testSaved_Search_OrderDefaultConstructorSetsNoVariables(){
    Saved_Search_Order _saved_search_order= new Saved_Search_Order();
    Assertions.assertNull(_saved_search_order.getSaved_Search_Order_ID());
    Assertions.assertNull(_saved_search_order.getOwned_User());
    Assertions.assertNull(_saved_search_order.getNickname());
    Assertions.assertNull(_saved_search_order.getDescription());
    Assertions.assertNull(_saved_search_order.getLast_Used());
    Assertions.assertNull(_saved_search_order.getLast_Updated());
    Assertions.assertNull(_saved_search_order.getTimes_Ran());
  }
  @Test
  public void testSaved_Search_OrderParameterizedConstructorSetsAllVariables(){
    Date date1 = new Date();
    Date date2 = new Date();
    Saved_Search_Order _saved_search_order= new Saved_Search_Order(
        1774,
        "f503f3ba-3375-40d4-8a0f-5c5aee018c6b",
        "CbBxFLoYyknxhlJYaTMRWiRoCqpgCVOGFEfwsshtUtvxlcGONfrjICUyukwOXjaRETuWTYhovTHtrnJYWGAbVvcctwvwGDKVgg",
        "IhiuowDmouZGGbrRiGiiXRnZlqpPwLHAkgKKbGhImqUYOkOwxkVuULLwxuuyalKGcKGQdefcWYaPNIPUkioRRebjNqqmgDevflrZlQoknmVAegqRWZWiQsisXnldlscPpctDObNbKAPdNNUsWkrsAyJBrGwBLanyemTMGfodkIteQObsEjbsETWQxZyejcaJdMDVOBwfmcgWDWcZqpfUPeQJAPuggDjoRwYydmVmcXNGCHhTwqnkwrhaxGwvr",
        date1,
        date2,
        1054
    );
    Assertions.assertEquals(1774,_saved_search_order.getSaved_Search_Order_ID());
    Assertions.assertEquals("f503f3ba-3375-40d4-8a0f-5c5aee018c6b",_saved_search_order.getOwned_User());
    Assertions.assertEquals("CbBxFLoYyknxhlJYaTMRWiRoCqpgCVOGFEfwsshtUtvxlcGONfrjICUyukwOXjaRETuWTYhovTHtrnJYWGAbVvcctwvwGDKVgg",_saved_search_order.getNickname());
    Assertions.assertEquals("IhiuowDmouZGGbrRiGiiXRnZlqpPwLHAkgKKbGhImqUYOkOwxkVuULLwxuuyalKGcKGQdefcWYaPNIPUkioRRebjNqqmgDevflrZlQoknmVAegqRWZWiQsisXnldlscPpctDObNbKAPdNNUsWkrsAyJBrGwBLanyemTMGfodkIteQObsEjbsETWQxZyejcaJdMDVOBwfmcgWDWcZqpfUPeQJAPuggDjoRwYydmVmcXNGCHhTwqnkwrhaxGwvr",_saved_search_order.getDescription());
    Assertions.assertEquals(date1,_saved_search_order.getLast_Used());
    Assertions.assertEquals(date2,_saved_search_order.getLast_Updated());
    Assertions.assertEquals(1054,_saved_search_order.getTimes_Ran());
  }
  @Test
  public void testSaved_Search_OrderKeyedParameterizedConstructorSetsKeyedVariables(){
    Saved_Search_Order _saved_search_order= new Saved_Search_Order(
        7417
    );
    Assertions.assertEquals(7417,_saved_search_order.getSaved_Search_Order_ID());
    Assertions.assertNull(_saved_search_order.getOwned_User());
    Assertions.assertNull(_saved_search_order.getNickname());
    Assertions.assertNull(_saved_search_order.getDescription());
    Assertions.assertNull(_saved_search_order.getLast_Used());
    Assertions.assertNull(_saved_search_order.getLast_Updated());
    Assertions.assertNull(_saved_search_order.getTimes_Ran());
  }
  @Test
  public void testSaved_Search_OrderThrowsIllegalArgumentExceptionIfSaved_Search_Order_IDTooSmall(){
    int Saved_Search_Order_ID = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_saved_search_order.setSaved_Search_Order_ID(Saved_Search_Order_ID);});
  }
  @Test
  public void testSaved_Search_OrderThrowsIllegalArgumentExceptionIfSaved_Search_Order_IDTooBig(){
    int Saved_Search_Order_ID = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_saved_search_order.setSaved_Search_Order_ID(Saved_Search_Order_ID);});
  }
  @Test
  public void testSaved_Search_OrderSetsSaved_Search_Order_ID(){
    int Saved_Search_Order_ID = 1376;
    _saved_search_order.setSaved_Search_Order_ID(Saved_Search_Order_ID);
    Assertions.assertEquals(Saved_Search_Order_ID, _saved_search_order.getSaved_Search_Order_ID());
  }
  @Test
  public void testSaved_Search_OrderThrowsIllegalArgumentExceptionIfOwned_UserTooSmall(){
    String Owned_User = "f503f3ba-3375-40d4-8a0f-5c5aee018c";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_saved_search_order.setOwned_User(Owned_User);});
  }
  @Test
  public void testSaved_Search_OrderThrowsIllegalArgumentExceptionIfOwned_UserTooBig(){
    String Owned_User = "f503f3ba-3375-40d4-8a0f-5c5aee018c6bss";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_saved_search_order.setOwned_User(Owned_User);});
  }
  @Test
  public void testSaved_Search_OrderSetsOwned_User(){
    String Owned_User = "f503f3ba-3375-40d4-8a0f-5c5aee018c6b";
    _saved_search_order.setOwned_User(Owned_User);
    Assertions.assertEquals(Owned_User, _saved_search_order.getOwned_User());
  }
  @Test
  public void  testSaved_Search_OrderThrowsIllegalArgumentExceptionIfNicknameTooShort(){
    String Nickname = "Wg";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_saved_search_order.setNickname(Nickname);});
  }
  @Test
  public void  testSaved_Search_OrderThrowsIllegalArgumentExceptionIfNicknameTooLong(){
    String Nickname = "kPacCndVWfjlrZYjYefopgqUARynvQGRYRvflUaKaYtITshgOFpZhtkgmSaZMYRYIDfQEePNwMgYRTOCBBkXKyyBBcGMUiCZwcbnko";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_saved_search_order.setNickname(Nickname);});
  }
  @Test
  public void testSetNicknameSetsNickname(){
    String Nickname = "YfnFsOMKJwPhkREfPDBIBcexlVfZMjMRkWtIlVuCplllmevgmPdLxtpjQNDVyualGReNymksMxkIhCYBHKZNQWRhVnivJXVHCe";
    _saved_search_order.setNickname(Nickname);
    Assertions.assertEquals(Nickname,_saved_search_order.getNickname());
  }
  @Test
  public void  testSaved_Search_OrderThrowsIllegalArgumentExceptionIfDescriptionTooShort(){
    String Description = "XM";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_saved_search_order.setDescription(Description);});
  }
  @Test
  public void  testSaved_Search_OrderThrowsIllegalArgumentExceptionIfDescriptionTooLong(){
    String Description = "ahFVLcKdUrkBSsAFpgNMKpKBLTQgRxlSrmZAFuSDBuxuqUBLwKyVqRDKqRSVVxhTMfpbRtHoOCPlRQlbShVpNcnkfAMVKIVVVrFOCDBtUMBcpVVxfkPyThFWJiCDGbJuWJjMNeYUusDbiCieqXWFVBJyqsTxqNxDGMfeIkAStDZehGAXMiDloAHaNVlEWEfFbpCyhfEKSbJmTadtQRMDuVONynAGPwXDahdhDlLykoZHLMPdumPOpfSnxDVIGTcgx";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_saved_search_order.setDescription(Description);});
  }
  @Test
  public void testSetDescriptionSetsDescription(){
    String Description = "AIJPifTmpfYknRrwUlERIUgTgrccEudFxSwnHegNpwgUgBkiKLvqBmHBKxJMbraGhSydYdufXyyAprstTxmELLudIIAOEFNxhLidbMGGjBLxeRTgbTdHTDuQHtXTtkBIfJPlawFtYKLgatUarehGMFLhVHZveJXmGlfBrJQluNqnWpganaeOlXFlxNAaJlWHiALpZJxpbkcPuqfpHrQtdmXCkUjhBAiwBnCVonRmaBVwFjqSeqnuMDgmhoNIk";
    _saved_search_order.setDescription(Description);
    Assertions.assertEquals(Description,_saved_search_order.getDescription());
  }
  @Test
  public void testSaved_Search_OrderThrowsIllegalArgumentExceptionIfLast_UsedTooSmall() throws ParseException{
    String strDate = "03/03/1990";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_saved_search_order.setLast_Used(date);});
  }
  @Test
  public void testSaved_Search_OrderThrowsIllegalArgumentExceptionIfLast_UsedTooBig() throws ParseException{
    String strDate = "01/01/2190";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_saved_search_order.setLast_Used(date);});
  }
  @Test
  public void testSaved_Search_OrderSetsLast_Used() throws ParseException{
    String strDate = "3/2/2025";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    _saved_search_order.setLast_Used(date);
    Assertions.assertEquals(date, _saved_search_order.getLast_Used());
  }
  @Test
  public void testSaved_Search_OrderThrowsIllegalArgumentExceptionIfLast_UpdatedTooSmall() throws ParseException{
    String strDate = "03/03/1990";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_saved_search_order.setLast_Updated(date);});
  }
  @Test
  public void testSaved_Search_OrderThrowsIllegalArgumentExceptionIfLast_UpdatedTooBig() throws ParseException{
    String strDate = "01/01/2190";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_saved_search_order.setLast_Updated(date);});
  }
  @Test
  public void testSaved_Search_OrderSetsLast_Updated() throws ParseException{
    String strDate = "3/2/2025";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    _saved_search_order.setLast_Updated(date);
    Assertions.assertEquals(date, _saved_search_order.getLast_Updated());
  }
  @Test
  public void testSaved_Search_OrderThrowsIllegalArgumentExceptionIfTimes_RanTooSmall(){
    int Times_Ran = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_saved_search_order.setTimes_Ran(Times_Ran);});
  }
  @Test
  public void testSaved_Search_OrderThrowsIllegalArgumentExceptionIfTimes_RanTooBig(){
    int Times_Ran = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_saved_search_order.setTimes_Ran(Times_Ran);});
  }
  @Test
  public void testSaved_Search_OrderSetsTimes_Ran(){
    int Times_Ran = 8874;
    _saved_search_order.setTimes_Ran(Times_Ran);
    Assertions.assertEquals(Times_Ran, _saved_search_order.getTimes_Ran());
  }

  @Test
  public void testCompareToCanCompareForEachDateField() throws ParseException {
  DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
  Saved_Search_Order smaller = new Saved_Search_Order();
  Saved_Search_Order bigger = new Saved_Search_Order();
//to compare a smaller and larger Saved_Search_Order_ID
smaller.setSaved_Search_Order_ID(10);
bigger.setSaved_Search_Order_ID(20);
Assertions.assertTrue(smaller.compareTo(bigger)<0);
Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Saved_Search_Order_ID as equal.
smaller.setSaved_Search_Order_ID(20);
//to compare a smaller and larger Owned_User
smaller.setOwned_User("f503f3ba-3375-40d4-8a0f-5c5aee018c6b");
bigger.setOwned_User("g503f3ba-3375-40d4-8a0f-5c5aee018c6b");
Assertions.assertTrue(smaller.compareTo(bigger)<0);
Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Owned_User as equal.
smaller.setOwned_User("g503f3ba-3375-40d4-8a0f-5c5aee018c6b");
//to compare a smaller and larger Nickname
smaller.setNickname("aaaa");
bigger.setNickname("bbbb");
Assertions.assertTrue(smaller.compareTo(bigger)<0);
Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Nickname as equal.
smaller.setNickname("bbbb");
//to compare a smaller and larger Description
smaller.setDescription("aaaa");
bigger.setDescription("bbbb");
Assertions.assertTrue(smaller.compareTo(bigger)<0);
Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Description as equal.
smaller.setDescription("bbbb");
//to compare a smaller and larger Last_Used
smaller.setLast_Used(df.parse("01/01/2023"));
bigger.setLast_Used(df.parse("01/01/2024"));
Assertions.assertTrue(smaller.compareTo(bigger)<0);
Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Last_Used as equal.
smaller.setLast_Used(df.parse("01/01/2024"));
//to compare a smaller and larger Last_Updated
smaller.setLast_Updated(df.parse("01/01/2023"));
bigger.setLast_Updated(df.parse("01/01/2024"));
Assertions.assertTrue(smaller.compareTo(bigger)<0);
Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Last_Updated as equal.
smaller.setLast_Updated(df.parse("01/01/2024"));
//to compare a smaller and larger Times_Ran
smaller.setTimes_Ran(10);
bigger.setTimes_Ran(20);
Assertions.assertTrue(smaller.compareTo(bigger)<0);
Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Times_Ran as equal.
smaller.setTimes_Ran(20);
Assertions.assertTrue(bigger.compareTo(smaller)==0);
}


}


