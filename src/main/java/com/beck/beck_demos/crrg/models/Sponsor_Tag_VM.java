package com.beck.beck_demos.crrg.models;

import java.util.List;

public class Sponsor_Tag_VM extends Sponsor_Tag{
  private Picture Picture;
  public Sponsor_Tag_VM(Sponsor_Tag sponsor_tag, Picture picture) {
    super(sponsor_tag.getSponsor_ID(),sponsor_tag.getPicture_ID(),sponsor_tag.getDescription(),sponsor_tag.getis_Primary());
    this.Picture = picture;
  }

  public Sponsor_Tag_VM() {

  }

  public com.beck.beck_demos.crrg.models.Picture getPicture() {
    return Picture;
  }

  public void setPicture(com.beck.beck_demos.crrg.models.Picture picture) {
    Picture = picture;
  }
}
