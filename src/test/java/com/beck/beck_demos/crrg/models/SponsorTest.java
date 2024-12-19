package com.beck.beck_demos.crrg.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class SponsorTest {
  private Sponsor _sponsor;

  @BeforeEach
  public void setup() {
    _sponsor = new Sponsor();
  }

  @AfterEach
  public void teardown() {
    _sponsor = null;
  }

  @Test
  public void testSponsorDefaultConstructorSetsNoVariables() {
    Sponsor _sponsor = new Sponsor();
    Assertions.assertNull(_sponsor.getSponsor_ID());
    Assertions.assertNull(_sponsor.getTier_ID());
    Assertions.assertNull(_sponsor.getWebsite());
    Assertions.assertNull(_sponsor.getDescription());
    Assertions.assertFalse(_sponsor.getIs_Active());
  }

  @Test
  public void testSponsorParameterizedConstructorSetsAllVariables() {
    Sponsor _sponsor = new Sponsor(
        "rjlOOWeyDQNvcHEfBfBnlIhfsHfmZdhyrrJKAWQZxkmFjHSqgxdhklWSPpcVtILnrdSJIBuFdaDSRiBAORZNAxJHvIpeRRrfqM",
        "JfZXfogllLRgsHwVBMdICMsEZSPLgZqUntodaNLxcewUodeceXKmjMZAqqKBlIbaXeWSKYaXHvwZjDosLrWiBsUniwlmQukLMt",
        "LAMRSxdUcPCQmCRotlsPbdMfnHLLlNoqngtXFfQFRfgxkyFpZSVhjdgFSoNWDjovNduUUnrNKQxgCIGVRtYJEDMYFPYtBbqLCU",
        "KFSiaemFQhXtBmJaiuOZSJiHSemutMVvijrSdTJvJOTnpyCHVdbNyNSUmLFEdpLDnCHipKIVKxZUVVsaIGueOAPwWEKjwPCwuYEWEcCTtYoJMjQXTmHZQbnELBKsTXwIcCqMfUGINrgONyDhJnxPcyXaSjkOUrgmraEJYaMLmLoeOIJStaYeTeagcRWLLMbTtGDClsVjoFKHsQCvGZblEdMoMycYfoPMqnOnGpqxGrXStJeMZRxIfaRHVpQuSeBgRTktFGFvofhPBbohKseshaiIMLlQXfwtGmWrCadsYGohFdZIrcyolCYwssWCUQdDhiunAcSEygPUSGuSIDxETjwuIgMIlAVifIOPtpFlEdUmiMjEcUKpFZfrIGrajMfyQmDOOwsQnsjkSXvgWBpCodCSwfLDpDODMZmAUGMvoflHlohvsHiipwIBvQRhNxphFxIfCtQsNJruHwPJhTNPxdyYotNagufEjjmNEnXviRPFVJUYEPuZpyfdEVefvPXMvdIRfUsCfoXYjesjOajBQDpSptlIvIfvmIQSJmaYppJEpNUIpGBpBDHsXsUkvbWEnsQKZsloQENfimGfGnIiZYwoSAbTvFIaPuacELlDfkJaOWQVntuEZklEYohjRaLMakLYwwVsgmNEdRxHKNpmJDeWDTTKdVBoocxHDrEjqUKWlJVSiWmagmasCJrColIiOVmuFTgPLgbKoQgeBxTuhXNBTbySUdcegDScrIyUMZUhrLXnnocAqIkamhwpDxHNTBILuKCBayshnuaonBfLKrRxURaIodqVeRdgpnGJbdEtBONKBJIqpdcrFJTDvvKToXDhiTPXOuQREchMnmmDMPODXPAwSiAJfdFHKLEPvorPORHkWQbHodRDOBvMQHXyblfbMrIAROAhLKxcrDQppGRkCTjkqgIJYZHVlDilpkOgOURvpuGGfaknIDBfioZZdUmECbxMcpyVbfgNRTudwuekJCYNojHkYTHsDFtSeEfEDHLHPsqpGHwNCaCbglqqG",
        true
    );
    Assertions.assertEquals("rjlOOWeyDQNvcHEfBfBnlIhfsHfmZdhyrrJKAWQZxkmFjHSqgxdhklWSPpcVtILnrdSJIBuFdaDSRiBAORZNAxJHvIpeRRrfqM", _sponsor.getSponsor_ID());
    Assertions.assertEquals("JfZXfogllLRgsHwVBMdICMsEZSPLgZqUntodaNLxcewUodeceXKmjMZAqqKBlIbaXeWSKYaXHvwZjDosLrWiBsUniwlmQukLMt", _sponsor.getTier_ID());
    Assertions.assertEquals("LAMRSxdUcPCQmCRotlsPbdMfnHLLlNoqngtXFfQFRfgxkyFpZSVhjdgFSoNWDjovNduUUnrNKQxgCIGVRtYJEDMYFPYtBbqLCU", _sponsor.getWebsite());
    Assertions.assertEquals("KFSiaemFQhXtBmJaiuOZSJiHSemutMVvijrSdTJvJOTnpyCHVdbNyNSUmLFEdpLDnCHipKIVKxZUVVsaIGueOAPwWEKjwPCwuYEWEcCTtYoJMjQXTmHZQbnELBKsTXwIcCqMfUGINrgONyDhJnxPcyXaSjkOUrgmraEJYaMLmLoeOIJStaYeTeagcRWLLMbTtGDClsVjoFKHsQCvGZblEdMoMycYfoPMqnOnGpqxGrXStJeMZRxIfaRHVpQuSeBgRTktFGFvofhPBbohKseshaiIMLlQXfwtGmWrCadsYGohFdZIrcyolCYwssWCUQdDhiunAcSEygPUSGuSIDxETjwuIgMIlAVifIOPtpFlEdUmiMjEcUKpFZfrIGrajMfyQmDOOwsQnsjkSXvgWBpCodCSwfLDpDODMZmAUGMvoflHlohvsHiipwIBvQRhNxphFxIfCtQsNJruHwPJhTNPxdyYotNagufEjjmNEnXviRPFVJUYEPuZpyfdEVefvPXMvdIRfUsCfoXYjesjOajBQDpSptlIvIfvmIQSJmaYppJEpNUIpGBpBDHsXsUkvbWEnsQKZsloQENfimGfGnIiZYwoSAbTvFIaPuacELlDfkJaOWQVntuEZklEYohjRaLMakLYwwVsgmNEdRxHKNpmJDeWDTTKdVBoocxHDrEjqUKWlJVSiWmagmasCJrColIiOVmuFTgPLgbKoQgeBxTuhXNBTbySUdcegDScrIyUMZUhrLXnnocAqIkamhwpDxHNTBILuKCBayshnuaonBfLKrRxURaIodqVeRdgpnGJbdEtBONKBJIqpdcrFJTDvvKToXDhiTPXOuQREchMnmmDMPODXPAwSiAJfdFHKLEPvorPORHkWQbHodRDOBvMQHXyblfbMrIAROAhLKxcrDQppGRkCTjkqgIJYZHVlDilpkOgOURvpuGGfaknIDBfioZZdUmECbxMcpyVbfgNRTudwuekJCYNojHkYTHsDFtSeEfEDHLHPsqpGHwNCaCbglqqG", _sponsor.getDescription());
    Assertions.assertTrue(_sponsor.getIs_Active());
  }

  @Test
  public void testSponsorThrowsIllegalArgumentExceptionIfSponsor_IDTooShort() {
    String Sponsor_ID = "tO";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _sponsor.setSponsor_ID(Sponsor_ID);
    });
  }

  @Test
  public void testSponsorThrowsIllegalArgumentExceptionIfSponsor_IDTooLong() {
    String Sponsor_ID = "tJZXpjePEFxRCgOffdQWtqCRfrNnwIsRxpBFrWIwbjyRcLZPwPbumXyWSPHpYjhdmdIXAkttSkfaecrunhNOhDrokRpQhoasGhAfTp";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _sponsor.setSponsor_ID(Sponsor_ID);
    });
  }

  @Test
  public void testSetSponsor_IDSetsSponsor_ID() {
    String Sponsor_ID = "hgdwvyVNVrgvvrFJAuUroCdkVQKnBIfULLmmsQRbKBZcafcqZkxwrdALIeophbqQaftVMBDaNqbRmjwhTQJhaKaeOscJvRyyiM";
    _sponsor.setSponsor_ID(Sponsor_ID);
    Assertions.assertEquals(Sponsor_ID, _sponsor.getSponsor_ID());
  }

  @Test
  public void testSponsorThrowsIllegalArgumentExceptionIfTier_IDTooShort() {
    String Tier_ID = "iu";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _sponsor.setTier_ID(Tier_ID);
    });
  }

  @Test
  public void testSponsorThrowsIllegalArgumentExceptionIfTier_IDTooLong() {
    String Tier_ID = "VelXtVtRsyNTcEEiobmbFTOYUkNNHbNRqomfPTKbsVdbittAfevTQQytJlelGxFQNPuIRYXsnmlqdNaetBQnGUKXvGOeYfdESvlpeK";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _sponsor.setTier_ID(Tier_ID);
    });
  }

  @Test
  public void testSetTier_IDSetsTier_ID() {
    String Tier_ID = "mIRHXKdTLOhyHBwNyaTdJQTKyJDJHxPiedTMrPvdRQJxewVPvKBAoHINHLNyxUhkUjCXyrAJMULbmrBEMdcqWkHQnJGCKAPZne";
    _sponsor.setTier_ID(Tier_ID);
    Assertions.assertEquals(Tier_ID, _sponsor.getTier_ID());
  }

  @Test
  public void testSponsorThrowsIllegalArgumentExceptionIfWebsiteTooShort() {
    String Website = "tM";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _sponsor.setWebsite(Website);
    });
  }

  @Test
  public void testSponsorThrowsIllegalArgumentExceptionIfWebsiteTooLong() {
    String Website = "HFBHfumCgSLtlktpTdblcplwxMElmIwnvGbVkpUOOmqPNxRQdVLVgihPlUXxeIXZbnNTUyGhxMoqgKdVGqEHfxvXYRcWHiWrIpVXyj";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _sponsor.setWebsite(Website);
    });
  }

  @Test
  public void testSetWebsiteSetsWebsite() {
    String Website = "IDwghMXAlRbfapXagVUIEPHJmhjYEWuPiLtcdovLpoTRYnkcVAxgYjMbJYPDdRIrlPTvTxUIGuhBvrjKlJDWNmlkjAPvQGciTc";
    _sponsor.setWebsite(Website);
    Assertions.assertEquals(Website, _sponsor.getWebsite());
  }

  @Test
  public void testSponsorThrowsIllegalArgumentExceptionIfDescriptionTooShort() {
    String Description = "KU";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _sponsor.setDescription(Description);
    });
  }

  @Test
  public void testSponsorThrowsIllegalArgumentExceptionIfDescriptionTooLong() {
    String Description = "WFfvOgYYghSiHBaJmMeHpxWVXCNHyRngQaRpRLCaqObntUgmYXNjOPgDapZtQhVCpOTBbyjoahlhbEDYBsNOhcgaMQqLFvMIhPwOARpsIcFRZdnbAFyTxsYIbQfJUNberujluxOJqjwtqNjdAwrJnqofifguAZsCfBqpOVghZYYjkbhykvtGRKLcvAZSUhwJGVuXVHxOMNnKXllvHjRSRDmfNHDjlPYacpyTPQvWXOyXYkqnEreqkHIBtxKqvXdVBCUfUAXNafJTbmwsgbHHZUDHcAvrUpkjLPpDaShVWWTxvCnstuQqTFweNJkCmkdpZFadGgHHgndAoavqouXUjWcqFLMfUMtFbiSPndjSgRSJNfboLwpLjhwmAtRNWbtNrdaARhBCFPhreqJbWidrBUnIpqUDvaSYjKevZgbhEHuPeIfAsQVqODMIrKhCTHGtfeqLJKYgYfMLLXuhBDrvajVeOyTwbnUydAIgGKXbdauRyeXYDCjwutKsSDYLMbWlAurZTvJTPtuEkSPgEgHLAOIFltNfYcKbVAlAMdgxXxygsvtSFsiquFnfwNcfPFjsfSftaSqKoMoyusSfQMFDyGERHsGIDOAYyHVNdJHBhckibNEcZMctONiRxiDlkuHlnQCbvuxYaXSvsEnmpEgPHSLYwurobyoYnqQgvBmfRxBoyRvHRFIPjAckpTHQtwIHfMUJcxcugQjtfAbKvKtdtUiWyeITZBfRuasBtgEpWdtVwxLIRakjOOPMvCHJQryhfxQtcpvnnEchKHQjFaLSdkgkBXfGheLixhRojoukLjSUwCyIsHffIukoIWFYRtjpktUlPaJwXgZdHkDNRBIdDuPmTUmXYeLNOUKFcwTHEPgNfXgHpYMnmyCyXsQMhDMfjmLOEoFKlMAFYFsgrhoXTFFGVOcKuGFfQOIygUMoxsmrGoVUqjOVhFHheeJBEXeSZbXihHDGPGNcWTiMpEVMiwKHZCjDmtIgNweIbLOXytFIQuvLyBXcXQOOFSDLlZqfOKvmB";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _sponsor.setDescription(Description);
    });
  }

  @Test
  public void testSetDescriptionSetsDescription() {
    String Description = "ZLMUQknGbuZjvLpgWsrLumIeReYhADNWIuLDswTvtQRjtfbnCQJOeYEjxGUYbqLSmPdcvISPApuXJlFLAwNtQlxJxNJRYGWGLCyQGHpydwOnawbSVqOFsxqdTwuIkhCiQwjGtsoXufojUWQbKvbqRNMKUvJMgNutPQyrMwYgwRBtyeaalqMCkkxjbjgktKEGQjxdYdPtIwcbGgIZhCNOSNpwPaHjuYTqbRpYcmpWQAGAdAbZBsCXxVbfATBjCKOOEqdOZYuKGpTPygNsQZFSZqvlUxvlUvjpNbYdSPkSkUUIdwoyJWhvdKExSOqyRHgxlqKFdBHADpRnwLfpEjhuixFgTXFqPqNgxRsfnagrPYVCKINWyCCrFYPdByoiihKEsVIRMWuBCSUcFPnKnsrdClCBgCkjfdCSFgtrBTbxJKViOtjofCGTGeKfLVpkamJHPOAqPZmrpRSoGngwQyTnuhVIMTZovEmPnoQbSPTqJWOiJmEKmSSHNSWWXBqTsHLhfrbpLkwkJfkZsAnITITAvsqlVXVPfrnuwjPVQbUjjwBDKXmCTeudjUZVUtAreTEpXxEmDXbJGNUTKrroZsATAHoyjMirRJvMmYhPQMTdqLBLJBIDLEXqOmTLHZOLDXHXZJJffsVQdxCYaoGirjVbZvBLAUeIdElbxBnXdBeooIhsZFUWBrjCMeZrBfTZPqbQEuZGTBnhHsGumEgjjOoJwkAtJUrqRIAZFQhpuTEkvlFGhPmHSoPkVTHeNmAgnlTxWCsTHjXAxtmKbSixTjhcuTiQFRkPwTbTRVOygTWYTJDKhwoXnfVhJDCXgkBLHrLoygKUHtVGCGCRHumhvtnEveIfjVsHBKyrpkdQNwnnvyQdYseXZvRBWDBBhWIBNFqHKoRKJWhvAPAeqBQOjaNhqFmibcqnlapHJZdaODOUBHCWKfGfmdZTjanqHNhJhbgumySoxjSXvjIxDSjGsCygOXMmYssEYkyBydGloHJrGyOdqyxnsnnObFCpZuqRXyBSs";
    _sponsor.setDescription(Description);
    Assertions.assertEquals(Description, _sponsor.getDescription());
  }

  @Test
  public void testSponsorSetsIs_ActiveasFalse() {
    boolean status = false;
    _sponsor.setIs_Active(status);
    Assertions.assertEquals(status, _sponsor.getIs_Active());
  }

  @Test
  public void testSponsorSetsIs_ActiveasTrue() {
    boolean status = true;
    _sponsor.setIs_Active(status);
    Assertions.assertEquals(status, _sponsor.getIs_Active());
  }

}