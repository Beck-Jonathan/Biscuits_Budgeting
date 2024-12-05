package com.beck.beck_demos.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ArtistFromJson {
  @JsonProperty("data")
  private List<Artist> artists;

  public List<Artist> getArtists() {
    return artists;
  }
}