package com.beck.beck_demos.crrg.data_fakes;

import com.beck.beck_demos.crrg.data.Album_DAO;
import com.beck.beck_demos.crrg.data_interfaces.iAlbum_DAO;
import com.beck.beck_demos.crrg.models.Album;
import com.beck.beck_demos.crrg.models.Album_VM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Album_DAO_Fake implements iAlbum_DAO {
  private static List<Album_VM> albums;
  static{
    albums = new ArrayList<>();
    Album album1 = new Album(1,"Test",true);
    Album album2 = new Album(2,"Test1",true);
    Album album3 = new Album(3,"Test2",true);
    Album_VM albumVM1 = new Album_VM(album1,4);
    Album_VM albumVM2 = new Album_VM(album2,5);
    Album_VM albumVM3 = new Album_VM(album3,6);
    albums.add(albumVM1);
    albums.add(albumVM2);
    albums.add(albumVM3);
  }
  @Override
  public int add(Album _album) throws SQLException {
    int size = albums.size();
    Album_VM add = new Album_VM(_album,3);
    albums.add((Album_VM) add);
    int newsize = albums.size();
    return newsize-size;
  }

  @Override
  public int update(Album oldAlbum, Album newAlbum) throws SQLException{
    int location =-1;
    for (int i=0;i<albums.size();i++){
      if (albums.get(i).getAlbum_ID().equals(oldAlbum.getAlbum_ID())){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }
    Album_VM albumVM = new Album_VM(newAlbum,0);
    albums.set(location,albumVM);
    return 1;
  }

  @Override
  public List<Album_VM> getAllAlbum(int limit, int offset) throws SQLException {
    return albums;
  }

  @Override
  public Album_VM getAlbumByPrimaryKey(Album _album) throws SQLException {
    Album_VM result = null;
    for (Album_VM album : albums) {
      if (album.getAlbum_ID().equals(_album.getAlbum_ID())){
        result = album;
      }
    }
    if (result == null){
      throw new SQLException("Album not found");
    }
    return result;
  }

  @Override
  public List<Album> getDistinctAlbumForDropdown() throws SQLException{
    List<Album> results = new ArrayList<>();
    for (Album album : albums){
      Album _album = new Album();
      _album.setAlbum_ID(album.getAlbum_ID());
      _album.setAlbum_Name(album.getAlbum_Name());
      results.add(_album);
    }
    return results;
  }

  @Override
  public int changeActivation(int albumID, int status) throws SQLException {
    int location =-1;
    for (int i=0;i<albums.size();i++){
      if (albums.get(i).getAlbum_ID().equals(albumID)){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }
    boolean _status = albums.get(location).getIs_Active();
    albums.get(location).setIs_Active(!_status);
    return 1;
  }

}
