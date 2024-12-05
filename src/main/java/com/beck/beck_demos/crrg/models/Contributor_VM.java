package com.beck.beck_demos.crrg.models;

public class Contributor_VM  extends Contributor{

  public int getAlbum_size() {
    return album_size;
  }

  public void setAlbum_size(int album_size) {
    this.album_size = album_size;
  }

  private int album_size;

  public Contributor_VM(Contributor _contributor, int size){
    super(_contributor.getContributor_ID(), _contributor.getFirst_Name(), _contributor.getFirst_Name(), _contributor.getemail());
    this.album_size=size;
  }
}
