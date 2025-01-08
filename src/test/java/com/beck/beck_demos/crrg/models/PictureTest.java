package com.beck.beck_demos.crrg.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class PictureTest {
  private Picture _picture;

  @BeforeEach
  public void setup() {
    _picture = new Picture();
  }

  @AfterEach
  public void teardown() {
    _picture = null;
  }

  @Test
  public void testPictureDefaultConstructorSetsNoVariables() {
    Picture _picture = new Picture();
    Assertions.assertEquals(null, _picture.getPicture_ID());
    Assertions.assertEquals(null, _picture.getAlbum_ID());
    Assertions.assertEquals(null, _picture.getContributor_ID());
    Assertions.assertNull(_picture.getWeb_Address());
    Assertions.assertNull(_picture.getDescription());
    Assertions.assertFalse(_picture.getIs_Active());
    Assertions.assertFalse(_picture.getis_Approved());
  }

  @Test
  public void testPictureParameterizedConstructorSetsAllVariables() {
    Picture _picture = new Picture(
        8678,
        4708,
        7323,
        "lOOWeyDQNvcHEfBfBnlIhfsHfmZdhyrrJKAWQZxkmFjHSqgxdhklWSPpcVtILnrdSJIBuFdaDSRiBAORZNAxJHvIpeRRrfqMJfZXfogllLRgsHwVBMdICMsEZSPLgZqUntodaNLxcewUodeceXKmjMZAqqKBlIbaXeWSKYaXHvwZjDosLrWiBsUniwlmQukLMtLAMRSxdUcPCQmCRotlsPbdMfnHLLlNoqngtXFfQFRfgxkyFpZSVhjdgFSoN",
        "WDjovNduUUnrNKQxgCIGVRtYJEDMYFPYtBbqLCUKFSiaemFQhXtBmJaiuOZSJiHSemutMVvijrSdTJvJOTnpyCHVdbNyNSUmLF",
        true,
        true
    );
    Assertions.assertEquals(8678, _picture.getPicture_ID());
    Assertions.assertEquals(4708, _picture.getAlbum_ID());
    Assertions.assertEquals(7323, _picture.getContributor_ID());
    Assertions.assertEquals("lOOWeyDQNvcHEfBfBnlIhfsHfmZdhyrrJKAWQZxkmFjHSqgxdhklWSPpcVtILnrdSJIBuFdaDSRiBAORZNAxJHvIpeRRrfqMJfZXfogllLRgsHwVBMdICMsEZSPLgZqUntodaNLxcewUodeceXKmjMZAqqKBlIbaXeWSKYaXHvwZjDosLrWiBsUniwlmQukLMtLAMRSxdUcPCQmCRotlsPbdMfnHLLlNoqngtXFfQFRfgxkyFpZSVhjdgFSoN", _picture.getWeb_Address());
    Assertions.assertEquals("WDjovNduUUnrNKQxgCIGVRtYJEDMYFPYtBbqLCUKFSiaemFQhXtBmJaiuOZSJiHSemutMVvijrSdTJvJOTnpyCHVdbNyNSUmLF", _picture.getDescription());
    Assertions.assertTrue(_picture.getIs_Active());
    Assertions.assertTrue(_picture.getis_Approved());
  }
  @Test
  public void testPictureKeyedParameterizedConstructorSetsKeyedVariables(){
    Picture _picture= new Picture(
        9682
    );
    Assertions.assertEquals(9682,_picture.getPicture_ID());
    Assertions.assertEquals(null,_picture.getAlbum_ID());
    Assertions.assertEquals(null,_picture.getContributor_ID());
    Assertions.assertNull(_picture.getWeb_Address());
    Assertions.assertNull(_picture.getDescription());
    Assertions.assertFalse(_picture.getIs_Active());
    Assertions.assertFalse(_picture.getis_Approved());
  }

  @Test
  public void testPictureThrowsIllegalArgumentExceptionIfPicture_IDTooSmall() {
    int Picture_ID = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _picture.setPicture_ID(Picture_ID);
    });
  }

  @Test
  public void testPictureThrowsIllegalArgumentExceptionIfPicture_IDTooBig() {
    int Picture_ID = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _picture.setPicture_ID(Picture_ID);
    });
  }

  @Test
  public void testPictureSetsPicture_ID() {
    int Picture_ID = 790;
    _picture.setPicture_ID(Picture_ID);
    Assertions.assertEquals(Picture_ID, _picture.getPicture_ID());
  }

  @Test
  public void testPictureThrowsIllegalArgumentExceptionIfAlbum_IDTooSmall() {
    int Album_ID = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _picture.setAlbum_ID(Album_ID);
    });
  }

  @Test
  public void testPictureThrowsIllegalArgumentExceptionIfAlbum_IDTooBig() {
    int Album_ID = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _picture.setAlbum_ID(Album_ID);
    });
  }

  @Test
  public void testPictureSetsAlbum_ID() {
    int Album_ID = 6221;
    _picture.setAlbum_ID(Album_ID);
    Assertions.assertEquals(Album_ID, _picture.getAlbum_ID());
  }

  @Test
  public void testPictureThrowsIllegalArgumentExceptionIfContributor_IDTooSmall() {
    int Contributor_ID = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _picture.setContributor_ID(Contributor_ID);
    });
  }

  @Test
  public void testPictureThrowsIllegalArgumentExceptionIfContributor_IDTooBig() {
    int Contributor_ID = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _picture.setContributor_ID(Contributor_ID);
    });
  }

  @Test
  public void testPictureSetsContributor_ID() {
    int Contributor_ID = 8347;
    _picture.setContributor_ID(Contributor_ID);
    Assertions.assertEquals(Contributor_ID, _picture.getContributor_ID());
  }

  @Test
  public void testPictureThrowsIllegalArgumentExceptionIfWeb_AddressTooShort() {
    String Web_Address = "LD";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _picture.setWeb_Address(Web_Address);
    });
  }

  @Test
  public void testPictureThrowsIllegalArgumentExceptionIfWeb_AddressTooLong() {
    String Web_Address = "nCHipKIVKxZUVVsaIGueOAPwWEKjwPCwuYEWEcCTtYoJMjQXTmHZQbnELBKsTXwIcCqMfUGINrgONyDhJnxPcyXaSjkOUrgmraEJYaMLmLoeOIJStaYeTeagcRWLLMbTtGDClsVjoFKHsQCvGZblEdMoMycYfoPMqnOnGpqxGrXStJeMZRxIfaRHVpQuSeBgRTktFGFvofhPBbohKseshaiIMLlQXfwtGmWrCadsYGohFdZIrcyolCYwssWCUQdDh";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _picture.setWeb_Address(Web_Address);
    });
  }

  @Test
  public void testSetWeb_AddressSetsWeb_Address() {
    String Web_Address = "iunAcSEygPUSGuSIDxETjwuIgMIlAVifIOPtpFlEdUmiMjEcUKpFZfrIGrajMfyQmDOOwsQnsjkSXvgWBpCodCSwfLDpDODMZmAUGMvoflHlohvsHiipwIBvQRhNxphFxIfCtQsNJruHwPJhTNPxdyYotNagufEjjmNEnXviRPFVJUYEPuZpyfdEVefvPXMvdIRfUsCfoXYjesjOajBQDpSptlIvIfvmIQSJmaYppJEpNUIpGBpBDHsXsUkvb";
    _picture.setWeb_Address(Web_Address);
    Assertions.assertEquals(Web_Address, _picture.getWeb_Address());
  }

  @Test
  public void testPictureThrowsIllegalArgumentExceptionIfDescriptionTooShort() {
    String Description = "WE";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _picture.setDescription(Description);
    });
  }

  @Test
  public void testPictureThrowsIllegalArgumentExceptionIfDescriptionTooLong() {
    String Description = "nsQKZsloQENfimGfGnIiZYwoSAbTvFIaPuacELlDfkJaOWQVntuEZklEYohjRaLMakLYwwVsgmNEdRxHKNpmJDeWDTTKdVBoocxHDr";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _picture.setDescription(Description);
    });
  }

  @Test
  public void testSetDescriptionSetsDescription() {
    String Description = "EjqUKWlJVSiWmagmasCJrColIiOVmuFTgPLgbKoQgeBxTuhXNBTbySUdcegDScrIyUMZUhrLXnnocAqIkamhwpDxHNTBILuKCB";
    _picture.setDescription(Description);
    Assertions.assertEquals(Description, _picture.getDescription());
  }

  @Test
  public void testPictureSetsIs_ActiveasFalse() {
    boolean status = false;
    _picture.setIs_Active(status);
    Assertions.assertEquals(status, _picture.getIs_Active());
  }

  @Test
  public void testPictureSetsIs_ActiveasTrue() {
    boolean status = true;
    _picture.setIs_Active(status);
    Assertions.assertEquals(status, _picture.getIs_Active());
  }

  @Test
  public void testPictureSetsis_ApprovedasFalse() {
    boolean status = false;
    _picture.setis_Approved(status);
    Assertions.assertEquals(status, _picture.getis_Approved());
  }

  @Test
  public void testPictureSetsis_ApprovedasTrue() {
    boolean status = true;
    _picture.setis_Approved(status);
    Assertions.assertEquals(status, _picture.getis_Approved());
  }
}


