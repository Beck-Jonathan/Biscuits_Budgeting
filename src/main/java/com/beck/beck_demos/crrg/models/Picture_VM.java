package com.beck.beck_demos.crrg.models;

public class Picture_VM extends Picture{
  private Album Album;
  private Contributor Contributor;
  public Picture_VM(Picture picture,Album album,Contributor contributor){
    super( picture.getPicture_ID(),  picture.getAlbum_ID(),  picture.getContributor_ID(),  picture.getWeb_Address(),  picture.getDescription(),  picture.getIs_Active(),  picture.getis_Approved());
    this.Album = album;
    this.Contributor = contributor;

  }
  public Picture_VM(Picture picture){
    super(picture.getPicture_ID(), picture.getAlbum_ID(), picture.getContributor_ID(), picture.getWeb_Address(), picture.getDescription(), picture.getIs_Active(), picture.getis_Approved());
  }

  public Album getAlbum() {
    return Album;
  }
  public void setAlbum(Album _album) {
    this.Album = _album;
  }
  public Contributor getContributor() {
    return Contributor;
  }
  public void setContributor(Contributor _contributor) {
    this.Contributor = _contributor;
  }
}
