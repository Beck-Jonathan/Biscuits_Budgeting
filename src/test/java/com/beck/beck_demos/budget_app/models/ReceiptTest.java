package com.beck.beck_demos.budget_app.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class ReceiptTest {
  private Receipt _receipt;
  @BeforeEach
  public void setup(){
    _receipt = new Receipt();
  }
  @AfterEach
  public void teardown(){
    _receipt = null;
  }

  /**
   <p> Tests That the default constructor for the Receipt object works </p>
   */
  @Test
  public void testReceiptDefaultConstructorSetsNoVariables(){
    Receipt _receipt= new Receipt();
    Assertions.assertNull(_receipt.getReceipt_ID());
    Assertions.assertNull(_receipt.getTransaction_ID());
    Assertions.assertNull(_receipt.getImage_Link());
    Assertions.assertNull(_receipt.getName());
    Assertions.assertNull(_receipt.getDescription());
  }

  /**
   <p> Tests That the Parameterized Constructor for the Receipt object works </p>
   */
  @Test
  public void testReceiptParameterizedConstructorSetsAllVariables(){
    Receipt _receipt= new Receipt(
        "MucFAnwbMMfgyjjmkUllWYFZdnHbdHPjPnxO",
        "EmSEYBUwwtVGuQoftOJZebWOvonhyDUfxc",
        "KapNMMytnQRTFodsZqvdFBEsSNQlHqgRPhSMYFSXTgeIirVKtoCyFDoISKyhIfZeCoGQfBkcMkpVbkcUubMBdRKeDFuTiLALqGtkEsDZmTNYbadlZXAjdmcupOpKjdidqDSwKqXETjvfmqVPPRuwNKPnWOpwqAuGZpKmEiEXasPXsNZUIVjyPCApgjAJZSBLKbplfitGqviXeVTkjpujDrWqyojpErCwJwSoxTHsykqEEephbKPlHjYilpIKQ",
        "lEgYAvlUcKsfAujutHglLCNtFLXwcMfJcbLolpitIvpliqGMQIPXtPZLGpxwfafApRIiEVexrCFWHWUWKgZvNPTIAhvQjvVJlswLmeZBMUnyYLbmxHjlwAiVYUNujNVnZggRnNGmMiEIJNZySbwnoTPLGhdacuWIGXxRpMguPFkAmVKMrfSCbHYJfFbhADAmCBBkkRIYyFUgImMjKfNqHVVKXDpaTbFhyhkUJkatClkAssqTpJfgepThaEXAg",
        "gZBqajugTNSsXfCwhEkrpCEiSHCEvodFDqBQnYJpXTlXgescTPoqXwIObrbWXhWSbFfLQShAdkwtZXCLQMPMHtsBLaYyeUFEIYMXDvGpmLKTIBUMXKDVULmJElHbdFboVPHonMPJVUhhgCFDmMJCimMZNxKyOJGclLtXLusyAFBfbqsXmguVaNEKHLbEIQteVBOIimhTkvWkRMKlFiyJqwpirovATklvyqJdxhMBoRamVsUtVKKfTEXZpxlTWLkZkqUBHHBlIBfPuubGwbQevmkKHihXGoSeByrODJsLXIUCJMYeJJFuaPwARRZcbptbNiYXYYVcfmgQxeJGXcRDqaltrhvIayjIkkfLfQMeAxdEoYQmKdXYapgujPiOSsXsdLvApLamPnrMVWURiKuywikawHRVkuAXIxhXbUPtOiCLCISGvditgKYrEOPtCwoMXgtGoeFhAhUuxxWYGLxjQAdUfFiNutYBtqoxDwGQrukARkvJvWeTTbfgCEwXcRfhyAtlKjktjUiuKxPFHVMTeItXPDMuDSYUdNwJBdTtjZdMwkrFhRuYTrpFGpqXvTRvBFECRdKgZLGMkpuQVGDHMWsbMQcZuEcNysHigSYCwuWdFbYYAatBVotkbRVqEQBcpdnbhneJQyPerKYTJahdDMxEACLseVAbkneYsGWDLtFggfvqCoMbdcmcQQfdOttDyDKeXRyTNixmOCoPTmlurQLLlZGfnqKoAPGdYElDvGQGWoKKiWrgBQmaEXaNjGchNmhftiAquKtEVedPEZRRTOppqATiWKhajohqekqVhLYmFwefQWtroCifVMmjbhEtykPlfbXZIfaaVlkLQfLcjlyKPDfGTsFMVtpjSGYgVHFyZCLVXXhWAmPVCTnBJMhQVdVTFKDVqFQEBZkFvKiMrHaUPldeMtXhufbyqEcMvGuKFxSUPEQbSnLDSXJvhbJUrkOqaPlLWPRYPVIdtGbJELeBPfwxnxoZvFDkQX"
    );
    Assertions.assertEquals("MucFAnwbMMfgyjjmkUllWYFZdnHbdHPjPnxO",_receipt.getReceipt_ID());
    Assertions.assertEquals("EmSEYBUwwtVGuQoftOJZebWOvonhyDUfxc",_receipt.getTransaction_ID());
    Assertions.assertEquals("KapNMMytnQRTFodsZqvdFBEsSNQlHqgRPhSMYFSXTgeIirVKtoCyFDoISKyhIfZeCoGQfBkcMkpVbkcUubMBdRKeDFuTiLALqGtkEsDZmTNYbadlZXAjdmcupOpKjdidqDSwKqXETjvfmqVPPRuwNKPnWOpwqAuGZpKmEiEXasPXsNZUIVjyPCApgjAJZSBLKbplfitGqviXeVTkjpujDrWqyojpErCwJwSoxTHsykqEEephbKPlHjYilpIKQ",_receipt.getImage_Link());
    Assertions.assertEquals("lEgYAvlUcKsfAujutHglLCNtFLXwcMfJcbLolpitIvpliqGMQIPXtPZLGpxwfafApRIiEVexrCFWHWUWKgZvNPTIAhvQjvVJlswLmeZBMUnyYLbmxHjlwAiVYUNujNVnZggRnNGmMiEIJNZySbwnoTPLGhdacuWIGXxRpMguPFkAmVKMrfSCbHYJfFbhADAmCBBkkRIYyFUgImMjKfNqHVVKXDpaTbFhyhkUJkatClkAssqTpJfgepThaEXAg",_receipt.getName());
    Assertions.assertEquals("gZBqajugTNSsXfCwhEkrpCEiSHCEvodFDqBQnYJpXTlXgescTPoqXwIObrbWXhWSbFfLQShAdkwtZXCLQMPMHtsBLaYyeUFEIYMXDvGpmLKTIBUMXKDVULmJElHbdFboVPHonMPJVUhhgCFDmMJCimMZNxKyOJGclLtXLusyAFBfbqsXmguVaNEKHLbEIQteVBOIimhTkvWkRMKlFiyJqwpirovATklvyqJdxhMBoRamVsUtVKKfTEXZpxlTWLkZkqUBHHBlIBfPuubGwbQevmkKHihXGoSeByrODJsLXIUCJMYeJJFuaPwARRZcbptbNiYXYYVcfmgQxeJGXcRDqaltrhvIayjIkkfLfQMeAxdEoYQmKdXYapgujPiOSsXsdLvApLamPnrMVWURiKuywikawHRVkuAXIxhXbUPtOiCLCISGvditgKYrEOPtCwoMXgtGoeFhAhUuxxWYGLxjQAdUfFiNutYBtqoxDwGQrukARkvJvWeTTbfgCEwXcRfhyAtlKjktjUiuKxPFHVMTeItXPDMuDSYUdNwJBdTtjZdMwkrFhRuYTrpFGpqXvTRvBFECRdKgZLGMkpuQVGDHMWsbMQcZuEcNysHigSYCwuWdFbYYAatBVotkbRVqEQBcpdnbhneJQyPerKYTJahdDMxEACLseVAbkneYsGWDLtFggfvqCoMbdcmcQQfdOttDyDKeXRyTNixmOCoPTmlurQLLlZGfnqKoAPGdYElDvGQGWoKKiWrgBQmaEXaNjGchNmhftiAquKtEVedPEZRRTOppqATiWKhajohqekqVhLYmFwefQWtroCifVMmjbhEtykPlfbXZIfaaVlkLQfLcjlyKPDfGTsFMVtpjSGYgVHFyZCLVXXhWAmPVCTnBJMhQVdVTFKDVqFQEBZkFvKiMrHaUPldeMtXhufbyqEcMvGuKFxSUPEQbSnLDSXJvhbJUrkOqaPlLWPRYPVIdtGbJELeBPfwxnxoZvFDkQX",_receipt.getDescription());
  }

  /**
   <p> Tests That the Parameterized Constructor for the Receipt object works </p>
   */
  @Test
  public void testReceiptKeyedParameterizedConstructorSetsKeyedVariables(){
    Receipt _receipt= new Receipt(
        "vktQCBSnbLmcoQpknNtlmpSJwBYmmhPVVueh"
    );
    Assertions.assertEquals("vktQCBSnbLmcoQpknNtlmpSJwBYmmhPVVueh",_receipt.getReceipt_ID());
    Assertions.assertNull(_receipt.getTransaction_ID());
    Assertions.assertNull(_receipt.getImage_Link());
    Assertions.assertNull(_receipt.getName());
    Assertions.assertNull(_receipt.getDescription());
  }

  /**
   <p> Tests That the Setters for the Receipt.Receipt_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testReceiptThrowsIllegalArgumentExceptionIfReceipt_IDTooShort(){
    String Receipt_ID = "BM";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_receipt.setReceipt_ID(Receipt_ID);});
  }

  /**
   <p> Tests That the Setters for the Receipt.Receipt_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testReceiptThrowsIllegalArgumentExceptionIfReceipt_IDTooLong(){
    String Receipt_ID = "mixMdePSvDJNYLiJpZDlyluuZPsbLkQhDrGRlL";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_receipt.setReceipt_ID(Receipt_ID);});
  }

  /**
   <p> Tests That the Setters for the Receipt.Receipt_ID field work </p>
   */
  @Test
  public void testSetReceipt_IDSetsReceipt_ID(){
    String Receipt_ID = "jPCvrPMraIAjLQEjoCDRYvkZTcdhqQPWxbyf";
    _receipt.setReceipt_ID(Receipt_ID);
    Assertions.assertEquals(Receipt_ID,_receipt.getReceipt_ID());
  }

  /**
   <p> Tests That the Setters for the Receipt.Transaction_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testReceiptThrowsIllegalArgumentExceptionIfTransaction_IDTooShort(){
    String Transaction_ID = "FS";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_receipt.setTransaction_ID(Transaction_ID);});
  }

  /**
   <p> Tests That the Setters for the Receipt.Transaction_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testReceiptThrowsIllegalArgumentExceptionIfTransaction_IDTooLong(){
    String Transaction_ID = "KHgTfobvIYNwFFKYudqUsRjZitalNCXVxOnEFh";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_receipt.setTransaction_ID(Transaction_ID);});
  }

  /**
   <p> Tests That the Setters for the Receipt.Transaction_ID field work </p>
   */
  @Test
  public void testSetTransaction_IDSetsTransaction_ID(){
    String Transaction_ID = "nJvkhqBRXmIEBNxQpwsvttpiFBrxqQGSNp";
    _receipt.setTransaction_ID(Transaction_ID);
    Assertions.assertEquals(Transaction_ID,_receipt.getTransaction_ID());
  }

  /**
   <p> Tests That the Setters for the Receipt.Image_Link field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testReceiptThrowsIllegalArgumentExceptionIfImage_LinkTooShort(){
    String Image_Link = "hX";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_receipt.setImage_Link(Image_Link);});
  }

  /**
   <p> Tests That the Setters for the Receipt.Image_Link field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testReceiptThrowsIllegalArgumentExceptionIfImage_LinkTooLong(){
    String Image_Link = "IcrYJcIxExVHAwxGLksPGBcpJZHfpqLaINEmRkqdHfjxtSAGwYRGACOcIBROHKbYnjBkxaOWKZbofiUNGOCNCMJIjVAHFbxVVuCiroBcLelvKlyoYmiBiRCZMhTqrLLShKXHspNfUSFyqhwHShDIyIywLtEPxZnnGgrPpKMKegGHiJuErddBtIWvdSWVsQKjAEqpxqRCrNikIKGoJlLjstiORNplIpMUHmDCpUghuaRLPDGYbHdvvOcynBjwcSxkh";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_receipt.setImage_Link(Image_Link);});
  }

  /**
   <p> Tests That the Setters for the Receipt.Image_Link field work </p>
   */
  @Test
  public void testSetImage_LinkSetsImage_Link(){
    String Image_Link = "VhIiHDKeSXCCJiFNhNFkKqoZpexwxxNVjjUlDYRdpaweRdjuEsAsiraXMhTYonyVrumBRrMxrsuTTMDtUqLpfsteMcPWlYpSOqyRqheRupyfEsPZBvmMCrWFPvaGEBFUEboLsuotOaTIArRXtievYZcVVTjaCmFLvvLjtdXIdokHvioCKVjHIDLpbFUgCnuULJdENnoeqOYduWXVDmsAtWCSmwQLEInZqArQTTXTaEwJsQwHfkHKONCuAqTfJ";
    _receipt.setImage_Link(Image_Link);
    Assertions.assertEquals(Image_Link,_receipt.getImage_Link());
  }

  /**
   <p> Tests That the Setters for the Receipt.Name field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testReceiptThrowsIllegalArgumentExceptionIfNameTooShort(){
    String Name = "Qp";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_receipt.setName(Name);});
  }

  /**
   <p> Tests That the Setters for the Receipt.Name field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testReceiptThrowsIllegalArgumentExceptionIfNameTooLong(){
    String Name = "dVhGsjYgTREItgSDXXfwgldObSDhWnHhOtPBmAhAuuwlGKBbksJwdDrRWvXxvwZelDCeWxwJpiQoMRCGeuxKRQLJdkcyXASYwGKnmUaKeUrQbxOTrsIlMUdpRQkrEaYmeqbyZFNfioCDHwFfwixLJvCQyYlPRlJKdpxBQJSGGxHOYcuyuaioYSnRAASwUvfqYQyEVnWqELoCpqQWKZVKXMxISmJdLpXyRvulSxUHkNPZKgwlMOnaqWeEDuacieuVq";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_receipt.setName(Name);});
  }

  /**
   <p> Tests That the Setters for the Receipt.Name field work </p>
   */
  @Test
  public void testSetNameSetsName(){
    String Name = "YGWpVgxiiHRfYdvkUEIGOuYHesgRGTFDXXtrhvvkYuKFhnyaQiRrXoZEolKFrTFLKSJyewYvEgyAXBMaybUXdHxNVEosFFOHRXKbBHjnmKHDRQTFnpDrLkOssGdmsuJtgPbXmWWfLDOANGVyQXEhGfvhHAGAsPxJeBlFSWUfLyhAsebXtdkpyIuEOPfRmPqcTPgsDXGwvoYccyfMSesqdNIsVhujBBFIHMjhYBwPlCgCRGCxcfYkJBvYCLmTZ";
    _receipt.setName(Name);
    Assertions.assertEquals(Name,_receipt.getName());
  }

  /**
   <p> Tests That the Setters for the Receipt.Description field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testReceiptThrowsIllegalArgumentExceptionIfDescriptionTooShort(){
    String Description = "lv";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_receipt.setDescription(Description);});
  }

  /**
   <p> Tests That the Setters for the Receipt.Description field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testReceiptThrowsIllegalArgumentExceptionIfDescriptionTooLong(){
    String Description = "sHqtFxWqiVnhDfnyWactTQhMKepkcaZTSSYCNQXqnAxwAaOykGIwqcJrNjDtTeKEHfpKsqYaNIOdowNKqLdTYJHkFdRpYZhSuRNRDWPVgiyNYFWWAMeUBliihZvugjonQteAsmEuOgiKsRANuoLnTQYbUwTZyyJZIKkWHZYmoCoVZhAqAZAFQXHbogjAZJohkNtcAlyeYIZJyHietpBYpwISVMeLImlrbYdwWPqBFrdrjoEKWlrYvygXZepCYRbnsadCftKTsQCwvXXyHflcYBWPoKaIAKyqHpbTFDiexhXeZJuCUGEZMrnOhjsvQwaPIeEXXWpwxDhqKkWMxpslhccmEbUxkgkqUQqdwiNOoGXTwSfwEnaCQSpCrMZVvyoZsVTsJLUnggOXeEaMgDnkRGgVOnYJXZQcatPVLNdebdMIsIPvBiWpClIvaUPjtKmwemmXNufCJGtQtnDZaoXGYdrGVqjBUuIcIItWMZypfEcnMFHTWpVgyuUoPgSHkxmXStetKcwHbxnbedtBgGdNmEGHrEgQLSMnLMXWwaSgCiMjYGQpQXHDDoPQRHSJgMTZhvMQMlZYeUOKtdCCFTHJcQsfDAjFgbtDHthdaWdJBTWPGbsLQFfTdJlslsFqJRjxuKebLfieicDZYwcsVOjkvknRrpFlRKXNhhtydyZIpSZHNPHoOXYilmhGXlsQINVKgbOFRmtDXEchQRCmxuuLsMMeKLbrEZMMIsHurQdhSJKMyFapPlWUsRkIURqoMOEEssqcDdaKsDUXEkABohpqPbVlymrGevItLbjNLHoKyNVkoMGoKiaNNdQpaenRlYYOerIQmdAgPUyBglfDuvUufbQOXdArNnwJUwXEBNaTvosrHOqDTyQqBVyCAnCCXhqThFNUmbkdsdWRsJKEGBFxMlRVYQVYiSejfsCNmOYGKfNIUSSbBkVSuxTRUIZtDfJQBcONqGbfHPvDHpgptTKGtsPgPbaypBRylOspUkPpDU";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_receipt.setDescription(Description);});
  }

  /**
   <p> Tests That the Setters for the Receipt.Description field work </p>
   */
  @Test
  public void testSetDescriptionSetsDescription(){
    String Description = "QAcMNloSjtFUlwOYRDPGBpqtaApoqgpHbPDCTnKQXtXvbuYLQmOPKkRglRuCXdUyUOjkFcjYwDDuuVfWCsSFSjgCsBVUrrabKhelmochVaWAkCtCCiMbUDdKSULJrUNnSuDTZowWHhfYoDJhRKXLCMUMHHNacxViHGyNDxepkDrJdeNkfIZNjTOcQGBCYlueaRvTNXVnEPTduPrSNPtDVfyZgoQdGNyDunRtCrfNPyPgLlKHLBaLfwssRLIFeqTDASMCWHWAKASDRIXdMYrPqXUIRUBopJCTXGHsPyJDOviaADCsLyDMbxEjCFbJAxxeKATMOoAtiobnUxlRQpUUttLOGwgmiabqoDKfvJCRsIuhHoriIdQQdIwIrTHyOywuPGesKRbeliTYortyXwaarChnQiKstxIMlEOYyhprYdjDNApBVATWDvEsbXkqMUPcRHtIapkAdDENNwsDbHsIhHJXPEALOZJFFgoJbWZrAfUrmllIjJZmxyJgyXnemDrmWwmwQlOPJsCoLMYQjAfShecuWBDVrQbEWoyKYWKoFGklyRTCDMhYITVUNLmBlMGpdGRcosYrZdBkCoMVdhWppDCvRBETTvVoMkkeoydvjLZkLpHMfPOaqUJUFhfvoElexrWMUHRkqQsngqoIIvsMHxeGpCuGnLOoZuGPmfVgNsnrbrYptlkNQOLTUlASShBVdYXJFYcJBiCumgUDhgpBbRLiUMtSKFqLOyUbfnhEJeRNEheKOAUbpKMjMhpqdLSfmSrCrIvXfsQPIuFgfJJPHvbANSAGtXuSSmxWeXTxYiYhuifAdJcTFQIiFflarbvpHHAPBkevIraWXOXRJiYrLOvSraWaGrnXhVxrfKbXaJiJUSZQmGiOoBEYmVlvlFgcEirYYfDNPngldhLDGoilEIsUsZjQYfeFFfAqHCyATSoEJhovWuvoyumocILPccFxaidJrAadfGjpiEaaJpwqMPeVrHCQcFjxoLslvx";
    _receipt.setDescription(Description);
    Assertions.assertEquals(Description,_receipt.getDescription());
  }


  /**
   <p> Tests That the CompareTo Method for the Receipt object works </p>
   */
  @Test
  public void testCompareToCanCompareForEachDateField() {
    Receipt smaller = new Receipt();
    Receipt bigger = new Receipt();
//to compare a smaller and larger Receipt_ID
    smaller.setReceipt_ID("aaaa");
    bigger.setReceipt_ID("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Receipt_ID as equal.
    smaller.setReceipt_ID("bbbb");
//to compare a smaller and larger Transaction_ID
    smaller.setTransaction_ID("aaaa");
    bigger.setTransaction_ID("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Transaction_ID as equal.
    smaller.setTransaction_ID("bbbb");
//to compare a smaller and larger Image_Link
    smaller.setImage_Link("aaaa");
    bigger.setImage_Link("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Image_Link as equal.
    smaller.setImage_Link("bbbb");
//to compare a smaller and larger Name
    smaller.setName("aaaa");
    bigger.setName("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Name as equal.
    smaller.setName("bbbb");
//to compare a smaller and larger Description
    smaller.setDescription("aaaa");
    bigger.setDescription("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Description as equal.
    smaller.setDescription("bbbb");
    Assertions.assertTrue(bigger.compareTo(smaller)==0);
  }

}

