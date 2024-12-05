package com.beck.beck_demos.crrg.models;

public class Picture_VM extends Picture{
  private Album album;
  private Contributor contributor;
  public Picture_VM(Picture picture, Album album, Contributor contributor ){
    super(picture.getPicture_ID(),picture.getAlbum_ID(),picture.getContributor_ID(),picture.getWeb_Address(), picture.getdescription(), picture.getIs_Active(),picture.getis_Approved());
    this.album=album;
    this.contributor=contributor;
  }

  public Album getAlbum() {
    return album;
  }

  public void setAlbum(Album album) {
    this.album = album;
  }

  public Contributor getContributor() {
    return contributor;
  }

  public void setContributor(Contributor contributor) {
    this.contributor = contributor;
  }
}
