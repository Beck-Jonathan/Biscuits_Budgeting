package com.beck.beck_demos.crrg.models;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class ContributorTest {
  private Contributor _contributor;

  @BeforeEach
  public void setup() {
    _contributor = new Contributor();
  }

  @AfterEach
  public void teardown() {
    _contributor = null;
  }

  @Test
  public void testContributorDefaultConstructorSetsNoVariables() {
    Contributor _contributor = new Contributor();
    Assertions.assertEquals(null, _contributor.getContributor_ID());
    Assertions.assertNull(_contributor.getFirst_Name());
    Assertions.assertNull(_contributor.getLast_Name());
    Assertions.assertNull(_contributor.getEmail());
  }

  @Test
  public void testContributorParameterizedConstructorSetsAllVariables() {
    Contributor _contributor = new Contributor(
        8678,
        "jlOOWeyDQNvcHEfBfBnlIhfsHfmZdhyrrJKAWQZxkmFjHSqgxdhklWSPpcVtILnrdSJIBuFdaDSRiBAORZNAxJHvIpeRRrfqMJ",
        "fZXfogllLRgsHwVBMdICMsEZSPLgZqUntodaNLxcewUodeceXKmjMZAqqKBlIbaXeWSKYaXHvwZjDosLrWiBsUniwlmQukLMtL",
        "AMRSxdUcPCQmCRotlsPbdMfnHLLlNoqngtXFfQFRfgxkyFpZSVhjdgFSoNWDjovNduUUnrNKQxgCIGVRtYJEDMYFPYtBbqLCUK"
    );
    Assertions.assertEquals(8678, _contributor.getContributor_ID());
    Assertions.assertEquals("jlOOWeyDQNvcHEfBfBnlIhfsHfmZdhyrrJKAWQZxkmFjHSqgxdhklWSPpcVtILnrdSJIBuFdaDSRiBAORZNAxJHvIpeRRrfqMJ", _contributor.getFirst_Name());
    Assertions.assertEquals("fZXfogllLRgsHwVBMdICMsEZSPLgZqUntodaNLxcewUodeceXKmjMZAqqKBlIbaXeWSKYaXHvwZjDosLrWiBsUniwlmQukLMtL", _contributor.getLast_Name());
    Assertions.assertEquals("AMRSxdUcPCQmCRotlsPbdMfnHLLlNoqngtXFfQFRfgxkyFpZSVhjdgFSoNWDjovNduUUnrNKQxgCIGVRtYJEDMYFPYtBbqLCUK", _contributor.getEmail());
  }

  @Test
  public void testContributorThrowsIllegalArgumentExceptionIfContributor_IDTooSmall() {
    int Contributor_ID = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _contributor.setContributor_ID(Contributor_ID);
    });
  }

  @Test
  public void testContributorThrowsIllegalArgumentExceptionIfContributor_IDTooBig() {
    int Contributor_ID = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _contributor.setContributor_ID(Contributor_ID);
    });
  }

  @Test
  public void testContributorSetsContributor_ID() {
    int Contributor_ID = 4679;
    _contributor.setContributor_ID(Contributor_ID);
    Assertions.assertEquals(Contributor_ID, _contributor.getContributor_ID());
  }

  @Test
  public void testContributorThrowsIllegalArgumentExceptionIfFirst_NameTooShort() {
    String First_Name = "FS";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _contributor.setFirst_Name(First_Name);
    });
  }

  @Test
  public void testContributorThrowsIllegalArgumentExceptionIfFirst_NameTooLong() {
    String First_Name = "iaemFQhXtBmJaiuOZSJiHSemutMVvijrSdTJvJOTnpyCHVdbNyNSUmLFEdpLDnCHipKIVKxZUVVsaIGueOAPwWEKjwPCwuYEWEcCTt";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _contributor.setFirst_Name(First_Name);
    });
  }

  @Test
  public void testSetFirst_NameSetsFirst_Name() {
    String First_Name = "YoJMjQXTmHZQbnELBKsTXwIcCqMfUGINrgONyDhJnxPcyXaSjkOUrgmraEJYaMLmLoeOIJStaYeTeagcRWLLMbTtGDClsVjoFK";
    _contributor.setFirst_Name(First_Name);
    Assertions.assertEquals(First_Name, _contributor.getFirst_Name());
  }

  @Test
  public void testContributorThrowsIllegalArgumentExceptionIfLast_NameTooShort() {
    String Last_Name = "Hs";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _contributor.setLast_Name(Last_Name);
    });
  }

  @Test
  public void testContributorThrowsIllegalArgumentExceptionIfLast_NameTooLong() {
    String Last_Name = "QCvGZblEdMoMycYfoPMqnOnGpqxGrXStJeMZRxIfaRHVpQuSeBgRTktFGFvofhPBbohKseshaiIMLlQXfwtGmWrCadsYGohFdZIrcy";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _contributor.setLast_Name(Last_Name);
    });
  }

  @Test
  public void testSetLast_NameSetsLast_Name() {
    String Last_Name = "olCYwssWCUQdDhiunAcSEygPUSGuSIDxETjwuIgMIlAVifIOPtpFlEdUmiMjEcUKpFZfrIGrajMfyQmDOOwsQnsjkSXvgWBpCo";
    _contributor.setLast_Name(Last_Name);
    Assertions.assertEquals(Last_Name, _contributor.getLast_Name());
  }

  @Test
  public void testContributorThrowsIllegalArgumentExceptionIfEmailTooShort() {
    String Email = "dC";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _contributor.setEmail(Email);
    });
  }

  @Test
  public void testContributorThrowsIllegalArgumentExceptionIfEmailTooLong() {
    String Email = "SwfLDpDODMZmAUGMvoflHlohvsHiipwIBvQRhNxphFxIfCtQsNJruHwPJhTNPxdyYotNagufEjjmNEnXviRPFVJUYEPuZpyfdEVefv";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _contributor.setEmail(Email);
    });
  }

  @Test
  public void testSetEmailSetsEmail() {
    String Email = "XYZTEST@gmail.com";
    _contributor.setEmail(Email);
    Assertions.assertEquals(Email, _contributor.getEmail());
  }

}