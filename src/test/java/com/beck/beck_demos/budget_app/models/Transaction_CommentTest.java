package com.beck.beck_demos.budget_app.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class Transaction_CommentTest {

  private Transaction_Comment _transaction_comment;
  @BeforeEach
  public void setup(){
    _transaction_comment = new Transaction_Comment();
  }
  @AfterEach
  public void teardown(){
    _transaction_comment = null;
  }
  @Test
  public void testTransaction_CommentDefaultConstructorSetsNoVariables(){
    Transaction_Comment _transaction_comment= new Transaction_Comment();
    Assertions.assertNull(_transaction_comment.getUser_ID());
    Assertions.assertNull(_transaction_comment.getTransaction_ID());
    Assertions.assertNull(_transaction_comment.getTransaction_Comment_ID());
    Assertions.assertNull(_transaction_comment.getContent());
    Assertions.assertNull(_transaction_comment.getPost_Date());
  }
  @Test
  public void testTransaction_CommentParameterizedConstructorSetsAllVariables(){
    Transaction_Comment _transaction_comment= new Transaction_Comment(
        "af735dfc-22a9-4214-a8e5-fb8de2305700",
        "lwIFwDnklwIFwDnklwIFwDnk521212312322",
        1601,
        "SrRmbRlEMspZgtAPFxXwmgRgRDwOvaxCbNpLvKANSYFQdlZPDUUAsPeZBPPwxrqIfJDJmWiZNMIBZKnfcaNUaaJkxVMBYFQLtaDeeDYaEROtddRbgdekElyGcxFZcCbxiEDbgDyxHnqnhiMwXtsDVpcywqqMWBebEYdBxNAlAynHQokVFFhpZhMygMIljlMLcPcuZfGCVBhHGGRSHlejWCQhndOOBtEMmJfCCjWRLyYFgeYYEZxLrIwgnHyacufhvfQFBOYLRPiwcpcpeOvaREVNbKLwVZxhlIxYiGATphwLQWjxfiGeILjmfNyavlPxtDZqMoawgWXjWtoMWqjrDdwOeDrBmsuhQNvslHshnSDOTJTuGqZbawKmiKGfsTdipSVIIvanqYTAIYZnYQKfvAhScsIgQAuLinQUfAgXhkZlgrZDYDsSroSheqaYYnZdwEdKxRlNrDGnqvIpcpTRBHRBgGHiVVYOETAFMwPaIDjvecIUfQsvxKZMLvymYNEdbEjrWoEByTipIUHUoeylSbVdNyhaHgqCwLIDmeHJZyYjeUrvGfqdolIsIpVZVSfrvMDsBAqbrbvtcdpdAAyYIZyacdoljDgvDmXesNdqmMsaxLoVRNjMPWLFrotypoMKklVIfuaYydEcLthUYpDtXVwMfgUEfaIjxUDcvUbnvnfSbswGaAlRlUoIxamYTeqpEYDOOxdsRsDmWwFAIxfbinmrcMZADKywCGCGlTIOYKKhXSUxOHQfLeBdoHtGkPuyWSZNQZYCuSfqeynylQGofVUSZtchtvtentyHlMxcRJPOHvQJMPxvatJGxIRdkujTAElTtMMGFLdZKgfuGqSExeNpQgXTmvMWKgVRjFnDurZEOoNIkgVtffvElaYaWCyAQYoHNPtilRXNTFjmiomipVKACuHoZDxVXBXKtTQjgqsmuSKMacZvMNXMpKbmPGTHlxRTcnqtQUedILPqWlxEPJDwDJuZakAwBpdmHb",
        new Date()

    );
    Assertions.assertEquals("af735dfc-22a9-4214-a8e5-fb8de2305700",_transaction_comment.getUser_ID());
    Assertions.assertEquals("lwIFwDnklwIFwDnklwIFwDnk521212312322",_transaction_comment.getTransaction_ID());
    Assertions.assertEquals(1601,_transaction_comment.getTransaction_Comment_ID());
    Assertions.assertEquals("SrRmbRlEMspZgtAPFxXwmgRgRDwOvaxCbNpLvKANSYFQdlZPDUUAsPeZBPPwxrqIfJDJmWiZNMIBZKnfcaNUaaJkxVMBYFQLtaDeeDYaEROtddRbgdekElyGcxFZcCbxiEDbgDyxHnqnhiMwXtsDVpcywqqMWBebEYdBxNAlAynHQokVFFhpZhMygMIljlMLcPcuZfGCVBhHGGRSHlejWCQhndOOBtEMmJfCCjWRLyYFgeYYEZxLrIwgnHyacufhvfQFBOYLRPiwcpcpeOvaREVNbKLwVZxhlIxYiGATphwLQWjxfiGeILjmfNyavlPxtDZqMoawgWXjWtoMWqjrDdwOeDrBmsuhQNvslHshnSDOTJTuGqZbawKmiKGfsTdipSVIIvanqYTAIYZnYQKfvAhScsIgQAuLinQUfAgXhkZlgrZDYDsSroSheqaYYnZdwEdKxRlNrDGnqvIpcpTRBHRBgGHiVVYOETAFMwPaIDjvecIUfQsvxKZMLvymYNEdbEjrWoEByTipIUHUoeylSbVdNyhaHgqCwLIDmeHJZyYjeUrvGfqdolIsIpVZVSfrvMDsBAqbrbvtcdpdAAyYIZyacdoljDgvDmXesNdqmMsaxLoVRNjMPWLFrotypoMKklVIfuaYydEcLthUYpDtXVwMfgUEfaIjxUDcvUbnvnfSbswGaAlRlUoIxamYTeqpEYDOOxdsRsDmWwFAIxfbinmrcMZADKywCGCGlTIOYKKhXSUxOHQfLeBdoHtGkPuyWSZNQZYCuSfqeynylQGofVUSZtchtvtentyHlMxcRJPOHvQJMPxvatJGxIRdkujTAElTtMMGFLdZKgfuGqSExeNpQgXTmvMWKgVRjFnDurZEOoNIkgVtffvElaYaWCyAQYoHNPtilRXNTFjmiomipVKACuHoZDxVXBXKtTQjgqsmuSKMacZvMNXMpKbmPGTHlxRTcnqtQUedILPqWlxEPJDwDJuZakAwBpdmHb",_transaction_comment.getContent());
    Assertions.assertEquals(new Date(),_transaction_comment.getPost_Date());
  }
  @Test
  public void testTransaction_CommentKeyedParameterizedConstructorSetsKeyedVariables(){
    Transaction_Comment _transaction_comment= new Transaction_Comment(
        "af735dfc-22a9-4214-a8e5-fb8de2305700",
        "lwIFwDnklwIFwDnklwIFwDnk521212312322",
        6987
    );
    Assertions.assertEquals("af735dfc-22a9-4214-a8e5-fb8de2305700",_transaction_comment.getUser_ID());
    Assertions.assertEquals("lwIFwDnklwIFwDnklwIFwDnk521212312322",_transaction_comment.getTransaction_ID());
    Assertions.assertEquals(6987,_transaction_comment.getTransaction_Comment_ID());
    Assertions.assertNull(_transaction_comment.getContent());
    Assertions.assertNull(_transaction_comment.getPost_Date());
  }
  @Test
  public void testTransaction_CommentThrowsIllegalArgumentExceptionIfUser_IDTooSmall(){
    String User_ID = "af735dfc-22a9-4214-a8e5-fb8de23057";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_transaction_comment.setUser_ID(User_ID);});
  }
  @Test
  public void testTransaction_CommentThrowsIllegalArgumentExceptionIfUser_IDTooBig(){
    String User_ID = "af735dfc-22a9-4214-a8e5-fb8de2305700ss";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_transaction_comment.setUser_ID(User_ID);});
  }
  @Test
  public void testTransaction_CommentSetsUser_ID(){
    String User_ID = "af735dfc-22a9-4214-a8e5-fb8de2305700";
    _transaction_comment.setUser_ID(User_ID);
    Assertions.assertEquals(User_ID, _transaction_comment.getUser_ID());
  }
  @Test
  public void testTransaction_CommentThrowsIllegalArgumentExceptionIfTransaction_IDTooSmall(){
    String Transaction_ID = "aa";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_transaction_comment.setTransaction_ID(Transaction_ID);});
  }
  @Test
  public void testTransaction_CommentThrowsIllegalArgumentExceptionIfTransaction_IDTooBig(){
    String Transaction_ID = "lwIFwDnklwIFwDnklwIFwDnk521212312322xxxx";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_transaction_comment.setTransaction_ID(Transaction_ID);});
  }
  @Test
  public void testTransaction_CommentSetsTransaction_ID(){
    String Transaction_ID = "lwIFwDnklwIFwDnklwIFwDnk521212312322";
    _transaction_comment.setTransaction_ID(Transaction_ID);
    Assertions.assertEquals(Transaction_ID, _transaction_comment.getTransaction_ID());
  }
  @Test
  public void testTransaction_CommentThrowsIllegalArgumentExceptionIfTransaction_Comment_IDTooSmall(){
    int Transaction_Comment_ID = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_transaction_comment.setTransaction_Comment_ID(Transaction_Comment_ID);});
  }
  @Test
  public void testTransaction_CommentThrowsIllegalArgumentExceptionIfTransaction_Comment_IDTooBig(){
    int Transaction_Comment_ID = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_transaction_comment.setTransaction_Comment_ID(Transaction_Comment_ID);});
  }
  @Test
  public void testTransaction_CommentSetsTransaction_Comment_ID(){
    int Transaction_Comment_ID = 5057;
    _transaction_comment.setTransaction_Comment_ID(Transaction_Comment_ID);
    Assertions.assertEquals(Transaction_Comment_ID, _transaction_comment.getTransaction_Comment_ID());
  }
  @Test
  public void  testTransaction_CommentThrowsIllegalArgumentExceptionIfContentTooShort(){
    String Content = "Yi";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_transaction_comment.setContent(Content);});
  }
  @Test
  public void  testTransaction_CommentThrowsIllegalArgumentExceptionIfContentTooLong(){
    String Content = "bIhiIQejRvVqnIrHOZMdeWBTQQUVKoABqubROryCoffFEcjjeDLFvlfVryQUGGVowNPoybtNaokVNMhdoKvolxpfsAihhQPGVkkCJxGBQHeRffuYAKOZJGbTsGLExFRJEiImpNqoVkUOCHCdrOvHAadZpZeypLJyvleKwWenUMFkEaLiJKIFhwWcqxJboOfFqMEyjVmONkwtuOAZWoTyqEhnUfdlWpvFyiyeKZlipMYhvjJIYYMgNmQdKjIptBouAoLVaPAcUfbvdxWjYkuZUOCTdRWsBJITMDeWMJCuxcnhrrRVKdZfGigyJwlalRQkSEBkXYafNrknFqbswGhKrwLBCVbGKMUfsIAFZQpMNVrOjMjWKVxTxaUeLsedrIKfYroqTvEwRofKuoOBhRdjoFTSclyGQjrIddBWXSVphiodHTnTkhjATkFqlOUfLvFDhcHSidsvVxBMKxKYydQQRaGCrcZfLLYLAyVZnirjCrNlfYXRokvcBbVEqUbQxxXANpbhpDdbPFrDANsWgPXwnEjrIbgmOLbpNDFXyFxxRbSWbriACZlliKgaBFcSdofDIcJsnxksLJKsoENkoDsXwVtfUllfSYOHenApxHWqMmOVCDpyejkuJtFYnqZGyFBDhuQagjXyjhoTnnMTkSuRSsylNjODwovwnZUdMkhiFifAjteUiHWVxguhjgkAXpjUrjbNlpNKGMAUXcFnaYgEROynBUhGoQUCtpHtdjDnPhUOHtcPRrKeMSNxnsVRveSmxtwrkJAxRYmMnxqPcnfekqBhTCCkUuiSYHBejTuBfhrlwINJJNRHtHSyeOWBqrmLUoesjFiKTokWtLkBRMsRjhgIAooFXmgcQiLXjnOOUqfGRKNIuSIlDwiVjKxGsttCyotEoZRRIinVKekQIwYOhUQlEPNxdlESUSepgRleNMnvnnHclFaMInCWKETqWeAqAumegcZHpPkpByxlnGCZPHgRVoJnvMoQZswlBQMHhS";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_transaction_comment.setContent(Content);});
  }
  @Test
  public void testSetContentSetsContent(){
    String Content = "pWfiLkrTLyQERSUPdLhrpUkNHYUoYkbLwTFvsfhixqfJCrdiHqUWCGFDEespcsTRQicayJflmVPRvujVtMnuMVBrNKJpINmokbuFrLjQTjcnTbJZifWDSbikyveHgZaTAdoCBEdsfYDtebfhHQTbqGLNUhcuUmLaefdZlJgJepUtPwbJMoYGxupqPLCiGYAGCrpaUtTMQpSEjAUKVIYgkncxdeujutItiCTelleyKBIaDrhVHiMEWPiQuYvpQSqttdfuFoERcWJrBbSGaEODEEFOejogfIOCBtIlsgLsWCWRiyjTpVbZAjxDrrgiimXDYxoKarbgBimTBpwRRsZBAJJhBoJtSdDNJalfnjrThPQqqlLXtwCOnGJlTXmiEiFqOKmfEjJgsXxgWiuiGLeRwXCWZyKaZybbXloNsqmthgmfAEmVcsMwMUJCSiGiqDyEoQJtrSmCjoOkZMoxleUCbmtJjSLqdnaKmdjZYKeTmbOeOsrMZOaHxICLprLJOhbTNySLKXOyRuFDSxGaHjDTkXXMTlHSYAvfetNPpRIgNvItFsCJAbtxqeHfrtIWmTdgsZhkLEJEorCZWmHIuyOYLKoSnkkaMIEnoXXZsBmEMYVPOKigdAiPpgTERaMNWSSikUXXFPStYVlDwNGIamwyOBCvZLHOcEZZvvNsbunghTUEcbulovbPEsVvvXPrnfMegUiRhhrvqQUZIIrJrDrUeyOOYsQvVPMIGVswKgfAEPCoSjvTagRiaCGoMayAIRBlxLTrKhdtxLclUZBhpWoXtXVFBbOtQwwQAidEfpOZLRbevQvjccSjRFPnXIAyEMRJvDtHAsHwSCeAWyENlATlYxfnWZSOSmdMBTijGKlxCdmBIvlSMppJIUFFhdonSNlmCsDQUIyaVUsmNKCwZhKTdAHJNROVtsPugKwFnRrKEdlpQREhifTfqXICTqcguGBPPkWrRISDOvRXKPGBSwkInBaWhenACTBRqTLSnr";
    _transaction_comment.setContent(Content);
    Assertions.assertEquals(Content,_transaction_comment.getContent());
  }
  @Test
  public void testTransaction_CommentThrowsIllegalArgumentExceptionIfPost_DateTooSmall() throws ParseException {
    String strDate = "03/03/1990";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_transaction_comment.setPost_Date(date);});
  }
  @Test
  public void testTransaction_CommentThrowsIllegalArgumentExceptionIfPost_DateTooBig() throws ParseException{
    String strDate = "01/01/2190";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_transaction_comment.setPost_Date(date);});
  }
  @Test
  public void testTransaction_CommentSetsPost_Date() throws ParseException{
    String strDate = "25/2/2025";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    _transaction_comment.setPost_Date(date);
    Assertions.assertEquals(date, _transaction_comment.getPost_Date());
  }
  @Test
  public void testCompareToCanCompareForEachDateField()throws ParseException {
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Transaction_Comment smaller = new Transaction_Comment();
    Transaction_Comment bigger = new Transaction_Comment();
//to compare a smaller and larger User_ID
    smaller.setUser_ID("fec75744-130e-4bcb-8bbe-9bee18080428");
    bigger.setUser_ID("gec75744-130e-4bcb-8bbe-9bee18080428");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the User_ID as equal.
    smaller.setUser_ID("gec75744-130e-4bcb-8bbe-9bee18080428");;
//to compare a smaller and larger Transaction_ID
    smaller.setTransaction_ID("awIFwDnklwIFwDnklwIFwDnk521212312322");
    bigger.setTransaction_ID("lwIFwDnklwIFwDnklwIFwDnk521212312322");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Transaction_ID as equal.
    smaller.setTransaction_ID("lwIFwDnklwIFwDnklwIFwDnk521212312322");
//to compare a smaller and larger Transaction_Comment_ID
    smaller.setTransaction_Comment_ID(10);
    bigger.setTransaction_Comment_ID(20);
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Transaction_Comment_ID as equal.
    smaller.setTransaction_Comment_ID(20);
//to compare a smaller and larger Content
    smaller.setContent("aaaa");
    bigger.setContent("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Content as equal.
    smaller.setContent("bbbb");
//to compare a smaller and larger Post_Date
    smaller.setPost_Date(df.parse("01/01/2023"));
    bigger.setPost_Date(df.parse("01/01/2024"));
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Post_Date as equal.
    smaller.setPost_Date(df.parse("01/01/2024"));
    Assertions.assertTrue(bigger.compareTo(smaller)==0);
  }

}

