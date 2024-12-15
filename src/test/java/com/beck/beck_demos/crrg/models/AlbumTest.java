package com.beck.beck_demos.crrg.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class Albumtest {
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
  public void testAlbumThrowsIllegalArgumentExceptionIfAlbum_NameTooShort(){
    String Album_Name= "abc";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_album.setAlbum_Name(Album_Name);});
  }
  @Test
  public void testAlbumThrowsIllegalArgumentExceptionIfAlbum_NameTooLong(){
    String Album_Name = "aObxiEkAgURGEULslqgBTUMbLtNsppoIQsJsByEGlgvYgwZHYqMtUVKMmmsiRvDSjrohPNZuOpQYTWxRuWnVqllRNDKdddjPdIBwgt";
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_album.setAlbum_Name(Album_Name);});
  }



  @Test
  public void TestAlbumSetIDActuallySetsID(){
    int Album_Numer=4;
    _album.setAlbum_ID(Album_Numer);
    Assertions.assertEquals(Album_Numer,_album.getAlbum_ID());
  }


  @Test
  public void testAlbumSetsAlbum_Name(){
    String Album_Name = "gRwOKtfJApEuJSBMLkgHcgAZywtNNmmMcXDUTHItkGSsaRHPchQOhZfLGcpgkiuwipLTyZlPUHGVVuFKgtHoousHBDDVdSfacQ";
    _album.setAlbum_Name(Album_Name);
    Assertions.assertEquals(Album_Name,_album.getAlbum_Name());
  }

  @Test
  public void testAlbumThrowsIllegalArgumentExceptionIfAlbum_IDTooSmall(){
    int Album_ID = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_album.setAlbum_ID(Album_ID);});
  }
  @Test
  public void testAlbumThrowsIllegalArgumentExceptionIfAlbum_IDTooBig(){
    int Album_ID = 10001;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {_album.setAlbum_ID(Album_ID);});
  }

  @Test
  public void testAlbumSetsAlbum_ID(){
    int Album_ID = 2712;

    _album.setAlbum_ID(Album_ID);
    Assertions.assertEquals(Album_ID, _album.getAlbum_ID());
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