package com.beck.beck_demos.demos.controllers;


import com.beck.beck_demos.demos.data.JsonReader;
import com.beck.beck_demos.models.Artist;
import com.beck.beck_demos.models.ArtistFromJson;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/artist-json")
public class ArtistJsonServlet extends HttpServlet {

  private static List<Artist> artists;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("artists", artists);
    req.getRequestDispatcher("WEB-INF/demos/artist-json.jsp").forward(req, resp);
  }

  @Override
  public void init() throws ServletException {
    try {
      JSONObject json = JsonReader.readJsonFromUrl("https://api.deezer.com/search/artist?q=imagine+dragons");
      ObjectMapper mapper = new ObjectMapper();
      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      ArtistFromJson artistFromJson = mapper.readValue(json.toString(), ArtistFromJson.class);
      artists = artistFromJson.getArtists();
    } catch (IOException e) {
      artists = new ArrayList<>();
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }
}