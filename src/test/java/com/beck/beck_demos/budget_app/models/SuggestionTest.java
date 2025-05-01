package com.beck.beck_demos.budget_app.models;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class SuggestionTest {
  private Suggestion _suggestion;
  @BeforeEach
  public void setup(){
    _suggestion = new Suggestion();
  }
  @AfterEach
  public void teardown(){
    _suggestion = null;
  }

  /**
   <p> Tests That the default constructor for the Suggestion object works </p>
   */
  @Test
  public void testSuggestionDefaultConstructorSetsNoVariables(){
    Suggestion _suggestion= new Suggestion();
    Assertions.assertNull(_suggestion.getSuggestion_ID());
    Assertions.assertNull(_suggestion.getUser_ID());
    Assertions.assertNull(_suggestion.getApplication_Name());
    Assertions.assertNull(_suggestion.getcontent());
  }

  /**
   <p> Tests That the Parameterized Constructor for the Suggestion object works </p>
   */
  @Test
  public void testSuggestionParameterizedConstructorSetsAllVariables(){
    Suggestion _suggestion= new Suggestion(
        "LyioMgTsLbpediGkSZiKxJiUyIOsdnBbpjAb",
        "NocvQJqveSCmGpAAFrgTEybmntosMGJFdG",
        "OgdCfdaaAulojvtivikUvHcYLshikSvVdhYBmsIyJPLZJbSxrLuCLUhSYCEWAUTumEgxnHZIyZISHYTDSpieODTFulOpFdVoRs",
        "kkmcCUOJGufDvCMHoYTyiMgtJTvdcCKbEvCLiFQTJOiRnbWOhVwVkBvILteKQCAlZYPEcjONfwwbUlHgvNFnpDVGCKfJsMhTIHJEehIFbxaqITouwdVsxaNEafkOCAcArbWfjbnHfBJgXlbjlNKXdadpqjpyMQsnFqEaCXoGTQqlSTKlFGlwXwpfhDKMQpxITZpbJniNDhjfHCmLmCIZGkkHCIPKrdytTJuRCZKtQnvBuIZEDvAsSECXggUwsEpGgNhdPAhPMknFGyoFWvcGbPHeUEMALxoUhpnbuRcGdQcbaUImhsafYlpedDLOmyjXyRoAReptCniDMESnCCTBlPgtUdqIpJrrLDKmfrmquyxkhfvVePCkdvaPSPKfrtjeMGjrOhJxYludowulTMpHbHcSbbLCFOFLMwFERqWeeRPsWoFcOkVbVMigTXjOGaHlojjwrnPSRijQVBbOahEHvxHKteWoVDPnrQsaRDuLlvNwWHyeydDjbnykdVmgHsUNGMjxbALeJvJBsMYekplyMERIvuPNErbyqKXADxXYfdwqWsUBVCcOaAegZEQUvrRZtVSBdhrTayZTRCuLhpxkQJlTdtHDdmSvASWgKOhaZLSHNYjJIFIOQEwMVmGqUboBeiDijBUsescqBntWEGbMCKLfEmAkpuIolFWYhOcbSqJPpadFhYXgrDhUInxCOhxVryejTioeMWfRNiXFMrUMhtNKCiMarFHIpZmvoaguxjkdJfcJJFWQKmbDTRJXPdIhDmmXOgmwcXWZyfUVWNdnFqIgxSSAAmbpdWgIrjWoyfvqAJktNlZZyZuPbiNbWdqgVPgNMxFKLowIaamNSIqJWIvUXPSATnMyOvMATkDmlEstnIQipLnPhUAPdhusncTFTmyWupojTmVXDcGBRcgwRsuvWfoKxIknAItmyVEvuxcMJFnqGkXWGyZJtJnNgPtbxHMcAWtSjNBSCyVICYmMKmGkuvOpjxcnWFLIOM"
    );
    Assertions.assertEquals("LyioMgTsLbpediGkSZiKxJiUyIOsdnBbpjAb",_suggestion.getSuggestion_ID());
    Assertions.assertEquals("NocvQJqveSCmGpAAFrgTEybmntosMGJFdG",_suggestion.getUser_ID());
    Assertions.assertEquals("OgdCfdaaAulojvtivikUvHcYLshikSvVdhYBmsIyJPLZJbSxrLuCLUhSYCEWAUTumEgxnHZIyZISHYTDSpieODTFulOpFdVoRs",_suggestion.getApplication_Name());
    Assertions.assertEquals("kkmcCUOJGufDvCMHoYTyiMgtJTvdcCKbEvCLiFQTJOiRnbWOhVwVkBvILteKQCAlZYPEcjONfwwbUlHgvNFnpDVGCKfJsMhTIHJEehIFbxaqITouwdVsxaNEafkOCAcArbWfjbnHfBJgXlbjlNKXdadpqjpyMQsnFqEaCXoGTQqlSTKlFGlwXwpfhDKMQpxITZpbJniNDhjfHCmLmCIZGkkHCIPKrdytTJuRCZKtQnvBuIZEDvAsSECXggUwsEpGgNhdPAhPMknFGyoFWvcGbPHeUEMALxoUhpnbuRcGdQcbaUImhsafYlpedDLOmyjXyRoAReptCniDMESnCCTBlPgtUdqIpJrrLDKmfrmquyxkhfvVePCkdvaPSPKfrtjeMGjrOhJxYludowulTMpHbHcSbbLCFOFLMwFERqWeeRPsWoFcOkVbVMigTXjOGaHlojjwrnPSRijQVBbOahEHvxHKteWoVDPnrQsaRDuLlvNwWHyeydDjbnykdVmgHsUNGMjxbALeJvJBsMYekplyMERIvuPNErbyqKXADxXYfdwqWsUBVCcOaAegZEQUvrRZtVSBdhrTayZTRCuLhpxkQJlTdtHDdmSvASWgKOhaZLSHNYjJIFIOQEwMVmGqUboBeiDijBUsescqBntWEGbMCKLfEmAkpuIolFWYhOcbSqJPpadFhYXgrDhUInxCOhxVryejTioeMWfRNiXFMrUMhtNKCiMarFHIpZmvoaguxjkdJfcJJFWQKmbDTRJXPdIhDmmXOgmwcXWZyfUVWNdnFqIgxSSAAmbpdWgIrjWoyfvqAJktNlZZyZuPbiNbWdqgVPgNMxFKLowIaamNSIqJWIvUXPSATnMyOvMATkDmlEstnIQipLnPhUAPdhusncTFTmyWupojTmVXDcGBRcgwRsuvWfoKxIknAItmyVEvuxcMJFnqGkXWGyZJtJnNgPtbxHMcAWtSjNBSCyVICYmMKmGkuvOpjxcnWFLIOM",_suggestion.getcontent());
  }

  /**
   <p> Tests That the Parameterized Constructor for the Suggestion object works </p>
   */
  @Test
  public void testSuggestionKeyedParameterizedConstructorSetsKeyedVariables(){
    Suggestion _suggestion= new Suggestion(
        "crpSKgslCuhkvAYMPIXmxCLbZblYpQTcRvna"
    );
    Assertions.assertEquals("crpSKgslCuhkvAYMPIXmxCLbZblYpQTcRvna",_suggestion.getSuggestion_ID());
    Assertions.assertNull(_suggestion.getUser_ID());
    Assertions.assertNull(_suggestion.getApplication_Name());
    Assertions.assertNull(_suggestion.getcontent());
  }

  /**
   <p> Tests That the Setters for the Suggestion.Suggestion_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testSuggestionThrowsIllegalArgumentExceptionIfSuggestion_IDTooShort(){
    String Suggestion_ID = "ND";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_suggestion.setSuggestion_ID(Suggestion_ID);});
  }

  /**
   <p> Tests That the Setters for the Suggestion.Suggestion_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testSuggestionThrowsIllegalArgumentExceptionIfSuggestion_IDTooLong(){
    String Suggestion_ID = "tCMfVJoDkNagOokMRAdFWLcXGQbJjbRPmwLbcm";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_suggestion.setSuggestion_ID(Suggestion_ID);});
  }

  /**
   <p> Tests That the Setters for the Suggestion.Suggestion_ID field work </p>
   */
  @Test
  public void testSetSuggestion_IDSetsSuggestion_ID(){
    String Suggestion_ID = "DGBehxoBkqnfADGOsTaklyVjwOFXASqIVclI";
    _suggestion.setSuggestion_ID(Suggestion_ID);
    Assertions.assertEquals(Suggestion_ID,_suggestion.getSuggestion_ID());
  }

  /**
   <p> Tests That the Setters for the Suggestion.User_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testSuggestionThrowsIllegalArgumentExceptionIfUser_IDTooShort(){
    String User_ID = "xy";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_suggestion.setUser_ID(User_ID);});
  }

  /**
   <p> Tests That the Setters for the Suggestion.User_ID field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testSuggestionThrowsIllegalArgumentExceptionIfUser_IDTooLong(){
    String User_ID = "ksPjqviPRxRUCPwYrmfEqorjmQphYmlNrpPNXJ";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_suggestion.setUser_ID(User_ID);});
  }

  /**
   <p> Tests That the Setters for the Suggestion.User_ID field work </p>
   */
  @Test
  public void testSetUser_IDSetsUser_ID(){
    String User_ID = "aaijArqISLeEtVSutBEWJGIYYdbnXHIdVVGv";
    _suggestion.setUser_ID(User_ID);
    Assertions.assertEquals(User_ID,_suggestion.getUser_ID());
  }

  /**
   <p> Tests That the Setters for the Suggestion.Application_Name field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testSuggestionThrowsIllegalArgumentExceptionIfApplication_NameTooShort(){
    String Application_Name = "AX";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_suggestion.setApplication_Name(Application_Name);});
  }

  /**
   <p> Tests That the Setters for the Suggestion.Application_Name field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testSuggestionThrowsIllegalArgumentExceptionIfApplication_NameTooLong(){
    String Application_Name = "XbflmDqTqlrDQAQRWRSnuPApmHWlhalgpCkUQaEEoEehrsnIqDeRQeRbZdoQoDNHeiuUZxisomdpSYXYtPjnPBaKpWCpALnHQqbnIm";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_suggestion.setApplication_Name(Application_Name);});
  }

  /**
   <p> Tests That the Setters for the Suggestion.Application_Name field work </p>
   */
  @Test
  public void testSetApplication_NameSetsApplication_Name(){
    String Application_Name = "XmgsnMVrWIxXpZZSsnxfRqxMcdRGaSbAqiUgVQZfgbcUThxxnpnZPKhElopopbjwLXgLmLReAOKcIbeGQttWLcVlpqEmlwAmWI";
    _suggestion.setApplication_Name(Application_Name);
    Assertions.assertEquals(Application_Name,_suggestion.getApplication_Name());
  }

  /**
   <p> Tests That the Setters for the Suggestion.content field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testSuggestionThrowsIllegalArgumentExceptionIfcontentTooShort(){
    String content = "Pi";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_suggestion.setcontent(content);});
  }

  /**
   <p> Tests That the Setters for the Suggestion.content field can throw exceptions with invalid inputs </p>
   */
  @Test
  public void  testSuggestionThrowsIllegalArgumentExceptionIfcontentTooLong(){
    String content = "UnPudNRriRQpDnLEpAVaMiYeHoIpovWFHQFuIcMENHwxKEZhyCNCLEoXIWsUYwgtxtrluTIxKqwXVGvUqEEBFfKVoLkFhyQPFpMHstjFQQaLxUXEFbpwoMawjrpKgSqQjNxKTunVdKqiWgdtiZIjdebUvANFAfeDhqHWBAqwThNSKMQqNJHgEecNThTPdfKDSylmRwGimhNgigCOoJQWcLIISgaThKALZPDXtWWuffEZUhvVaOIonBLUJgqnQdBYFMncdgbGsSoeEorOWPWiBqkAyoQJpTOZZIleSGZFMTsbBuBNaJilLwqcqRwCyaKdgGdqHIvdeWHWnUHblFjgwonvpOHHYMmhxUoXVYAlIeSFDvoMviQpuRsoGXcYYYiLTExRTkLKSxySNCebGHwgtqBfdXFOsWXWmZahdIIYQLWnAHkMwBrmbYEamMjFukdNkFAYSVpPpWxgZdKABHqvYSVZlPBjfhTXGvbnjVNMYQFdFyDRawJJVQHwmAumLMEDYhXhLssJguivkcMLlZwlMQYNQZYasXCaUtBskPQMpUXBCWiaYlHQMWxMWlSfmNtJORKshgyEpPXBqveTkTwvnyowaDqJHrIWVDoMvsWHTYiSrtIoQVVWiqXXMfIrZaubsuHBCXUeVrjyPxCrBAIUcQngkyoBIWPBMhOTxCDBgNVKAYmMPeSVfVpojTiNkTcgBFRIOOrYUnRbbdDlbVmxRgMeapSHYIWkhuHVDdvRaPsIjfIxpijXEhYtMVJIpCvHeMSIBAGhVhgcrfQKaBoPqdgeEBEKUHCFmqvehgjQVHAkuCaFxbnNfQAgDAuvTNebpUnUsTFxfsJxFWNqHNqVxdgnUCsUJFhlkYhZIgosntBWpvhWjiGwxaOgQdYdQiDQlUwqmiZXTJjeXFKwZWanmqYlrDWsbFpkdyqdAOidXCrFSYTPfjwbFXAqVAZuEstmCGSTYHqJRZtjyxDOoJAgtsZcJshKTfIECMFyqrmPfQ";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_suggestion.setcontent(content);});
  }

  /**
   <p> Tests That the Setters for the Suggestion.content field work </p>
   */
  @Test
  public void testSetcontentSetscontent(){
    String content = "qWwxgOisGINskOOJylGNbtvqUOxeIsdMuSSkLsEvOxtyMNbPinPivGdXQDkNAwYulXmsBQSvCqIBVUYEGgfcWBebnsjVxOofRAexVyYOWthRJXTRoklLXNXoLfssLjqHDJgNWbWGEtKLawevyGloMPaanlVbKJqKFvKebIIVESTNddiNvLcEcNNsFgbpBtjTBARXwdIktvTwJDHexalaqhQuSUYdscIhmemeZTvljYmstuIaZdSJmWQnLSZTAUUALuRGUUUlKHeSIVcqQNJfQrcSVXXsjMPSOwnARPkTxrGLykIthHEkZoiHulmNDpPXnpxxXRjskLJvYnjoHqKDaadYnLdDVMavSvjjITcyOelhGBxivxEvkPWnsvhHHnjqqXrnWiqrDRqnuRmMtHWHeLCSvOWurHqCqPcFtOMEgwdcrbJMAWbMfVWZvLKAkEBJHAbIuEpiEfNZeGZdcNhYEolHmdrnyTMXOfgHQlWnMaDQZYvYbTJXCRMqPFOGYiKnwwHfhBetiEaTHCtdtuOExTtGhxWHUUwyQupRdmIbIQvxLLnoPQiWxnkaSOlTiFqKDciDcrvVLdKimKTFKmAJOxEVNZKEmHcFkfYNbcHWdmDYnctEeDFndnvHlYFvebOnYlFXtCrUQwZpQAIEavBdRtphRgHJyKHeLvObhuHEmaFhrWINKwDUhJymZfJcYREymHpSluMtWHmyQUgHHWaSoNPxcJHncrdAWrLInTmccQRrJKNlJwcLnUebdjYcsPIxsxTCnjrvXnXuVdfOYMBUgMjDKAXCiLQkPLcXDybMmLnCxNAjkrIoZGSjqGshcGKFYCiAIVEQYOIBKCyZYckNgeriKEdvKRibSljJhnWpvpiIhadgTdsVlOZeLykPrFpkboYLIovrpBWCSreoVxYPTWYUqLUwFZQbksFkCIEavNDbscTussfaXKVETwylVknQYmtmNUioLQORkcKWuJuUxndalOQZoZIfWRrcjK";
    _suggestion.setcontent(content);
    Assertions.assertEquals(content,_suggestion.getcontent());
  }


  /**
   <p> Tests That the CompareTo Method for the Suggestion object works </p>
   */
  @Test
  public void testCompareToCanCompareForEachDateField() {
    Suggestion smaller = new Suggestion();
    Suggestion bigger = new Suggestion();
//to compare a smaller and larger Suggestion_ID
    smaller.setSuggestion_ID("aaijArqISLeEtVSutBEWJGIYYdbnXHIdVVGv");
    bigger.setSuggestion_ID("bbijArqISLeEtVSutBEWJGIYYdbnXHIdVVGv");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Suggestion_ID as equal.
    smaller.setSuggestion_ID("bbijArqISLeEtVSutBEWJGIYYdbnXHIdVVGv");
//to compare a smaller and larger User_ID
    smaller.setUser_ID("ccijArqISLeEtVSutBEWJGIYYdbnXHIdVVGv");
    bigger.setUser_ID("ddijArqISLeEtVSutBEWJGIYYdbnXHIdVVGv");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the User_ID as equal.
    smaller.setUser_ID("ddijArqISLeEtVSutBEWJGIYYdbnXHIdVVGv");
//to compare a smaller and larger Application_Name
    smaller.setApplication_Name("aaaa");
    bigger.setApplication_Name("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the Application_Name as equal.
    smaller.setApplication_Name("bbbb");
//to compare a smaller and larger content
    smaller.setcontent("aaaa");
    bigger.setcontent("bbbb");
    Assertions.assertTrue(smaller.compareTo(bigger)<0);
    Assertions.assertTrue(bigger.compareTo(smaller)>0);
//to set the content as equal.
    smaller.setcontent("bbbb");
    Assertions.assertTrue(bigger.compareTo(smaller)==0);
  }

}