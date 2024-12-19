package com.beck.beck_demos.crrg.models;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class AlbumTest {
  private Album _album;

  @BeforeEach
  public void setup() {
    _album = new Album();
  }

  @AfterEach
  public void teardown() {
    _album = null;
  }

  @Test
  public void testAlbumDefaultConstructorSetsNoVariables() {
    Album _album = new Album();
    Assertions.assertEquals(null, _album.getAlbum_ID());
    Assertions.assertNull(_album.getAlbum_Name());
    Assertions.assertFalse(_album.getIs_Active());
  }

  @Test
  public void testAlbumParameterizedConstructorSetsAllVariables() {
    Album _album = new Album(
        2135,
        "kGRWcLrAqNJAjfreflZZWshxhwBFcvRuruGkNsuJRerAjiKCxIQxedCuHTOxaaTwbMQnBSDKVNcMnHlbvtydHLxnoLTDQVlboh",
        true
    );
    Assertions.assertEquals(2135, _album.getAlbum_ID());
    Assertions.assertEquals("kGRWcLrAqNJAjfreflZZWshxhwBFcvRuruGkNsuJRerAjiKCxIQxedCuHTOxaaTwbMQnBSDKVNcMnHlbvtydHLxnoLTDQVlboh", _album.getAlbum_Name());
    Assertions.assertTrue(_album.getIs_Active());
  }

  @Test
  public void testAlbumThrowsIllegalArgumentExceptionIfAlbum_IDTooSmall() {
    int Album_ID = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _album.setAlbum_ID(Album_ID);
    });
  }

  @Test
  public void testAlbumThrowsIllegalArgumentExceptionIfAlbum_IDTooBig() {
    int Album_ID = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _album.setAlbum_ID(Album_ID);
    });
  }

  @Test
  public void testAlbumSetsAlbum_ID() {
    int Album_ID = 3457;
    _album.setAlbum_ID(Album_ID);
    Assertions.assertEquals(Album_ID, _album.getAlbum_ID());
  }

  @Test
  public void testAlbumThrowsIllegalArgumentExceptionIfAlbum_NameTooShort() {
    String Album_Name = "Pf";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _album.setAlbum_Name(Album_Name);
    });
  }

  @Test
  public void testAlbumThrowsIllegalArgumentExceptionIfAlbum_NameTooLong() {
    String Album_Name = "bUGWYiTMnnyYwxFFpNrpSvDpBeoYRQSeklOZcXrPQBwndjcCJinZVrRgIfUgNGSUSbmsZwCeCsNutMuPGcGiQWWIvOMtdmpJVPuPQP";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      _album.setAlbum_Name(Album_Name);
    });
  }

  @Test
  public void testSetAlbum_NameSetsAlbum_Name() {
    String Album_Name = "alnSyDkNZZXGLlDuLdvKjHBPCllvyApSvEWEKBmMfSPWRUjeTEpdmCvRyiZWQKeoapnkJwgociUMbPVNjSiBlLSPAoVIebkuEb";
    _album.setAlbum_Name(Album_Name);
    Assertions.assertEquals(Album_Name, _album.getAlbum_Name());
  }

  @Test
  public void testAlbumSetsIs_ActiveasFalse() {
    boolean status = false;
    _album.setIs_Active(status);
    Assertions.assertEquals(status, _album.getIs_Active());
  }

  @Test
  public void testAlbumSetsIs_ActiveasTrue() {
    boolean status = true;
    _album.setIs_Active(status);
    Assertions.assertEquals(status, _album.getIs_Active());
  }

}