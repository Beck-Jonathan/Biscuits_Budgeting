package com.beck.beck_demos.crrg.data_fakes;

import com.beck.beck_demos.crrg.data.Picture_DAO;
import com.beck.beck_demos.crrg.data_interfaces.iPicture_DAO;
import com.beck.beck_demos.crrg.models.Picture;
import com.beck.beck_demos.crrg.models.Picture_VM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Picture_DAO_Fake implements iPicture_DAO {
  private static List<Picture_VM> pictureVMs;
  static{
    Picture picture0 = new Picture(46, 35, 11, "SDrfoRma", "OiUqprGY", false, true);
    Picture picture1 = new Picture(37, 35, 24, "PCkmYIjf", "lnktvOjx", false, false);
    Picture picture2 = new Picture(44, 35, 40, "jCfZsjGa", "EmotBsUD", true, false);
    Picture picture3 = new Picture(13, 35, 46, "CFwxKsZl", "OSOCWQfy", false, false);
    Picture picture4 = new Picture(23, 35, 49, "iECUVuwN", "sgviieQU", true, false);
    Picture picture5 = new Picture(30, 48, 38, "xrlVjHwo", "YNsIUEVR", true, false);
    Picture picture6 = new Picture(31, 32, 38, "ZmnvTeQH", "pOkWrIaW", false, false);
    Picture picture7 = new Picture(40, 38, 38, "baQLxWVQ", "cDFwYWgh", false, false);
    Picture picture8 = new Picture(18, 54, 38, "oMsWjaaY", "vRMVDZRc", true, false);
    Picture picture9 = new Picture(32, 16, 38, "voXKHGtO", "JtZoyFHn", false, false);
    Picture picture10 = new Picture(15, 61, 17, "nkDTCUER", "HlesWLbg", true, true);
    Picture picture11 = new Picture(12, 61, 59, "lacZMCbO", "qhdZEmrt", true, false);
    Picture picture12 = new Picture(30, 61, 52, "JabduMUH", "hkEwyjbH", false, false);
    Picture picture13 = new Picture(15, 61, 20, "WChqlhoO", "OZDWBrbQ", false, true);
    Picture picture14 = new Picture(43, 61, 15, "cfZbCPll", "OmtlIigR", true, true);
    Picture picture15 = new Picture(38, 65, 47, "FijEXipv", "ELRtfngN", false, false);
    Picture picture16 = new Picture(40, 67, 47, "pVaAIGcx", "KTMWNpow", false, false);
    Picture picture17 = new Picture(43, 60, 47, "nAmwhwNW", "LljhwXai", true, true);
    Picture picture18 = new Picture(11, 57, 47, "YSyPXYXi", "euIOlyVJ", false, true);
    Picture picture19 = new Picture(66, 17, 47, "yHmhQXON", "OiCVhmhQ", false, false);
    Picture picture20 = new Picture(61, 50, 27, "dIKXKXEE", "fYKLliIy", true, true);
    Picture picture21 = new Picture(49, 50, 35, "jWQbdGZH", "qICGADhp", false, true);
    Picture picture22 = new Picture(33, 50, 67, "auaVpMZd", "LkfnGbEn", false, false);
    Picture picture23 = new Picture(23, 50, 27, "TDjJCyWi", "pmPGZmBC", true, true);
    Picture picture24 = new Picture(23, 50, 51, "MwRsRsdC", "EJfKLXSq", false, false);
    Picture picture25 = new Picture(35, 38, 57, "TTakXhyc", "GFYPjFuE", false, false);
    Picture picture26 = new Picture(57, 66, 57, "mcUeoXWv", "YpTxtFQg", false, true);
    Picture picture27 = new Picture(13, 14, 57, "YmIcltfA", "jhcakEJG", false, true);
    Picture picture28 = new Picture(45, 49, 57, "gaculByB", "AljEbMfQ", true, false);
    Picture picture29 = new Picture(18, 54, 57, "WnovKEaW", "WqJMqLsg", false, false);
    Picture_VM picture_VM0= new Picture_VM(picture0);
    Picture_VM picture_VM1= new Picture_VM(picture1);
    Picture_VM picture_VM2= new Picture_VM(picture2);
    Picture_VM picture_VM3= new Picture_VM(picture3);
    Picture_VM picture_VM4= new Picture_VM(picture4);
    Picture_VM picture_VM5= new Picture_VM(picture5);
    Picture_VM picture_VM6= new Picture_VM(picture6);
    Picture_VM picture_VM7= new Picture_VM(picture7);
    Picture_VM picture_VM8= new Picture_VM(picture8);
    Picture_VM picture_VM9= new Picture_VM(picture9);
    Picture_VM picture_VM10= new Picture_VM(picture10);
    Picture_VM picture_VM11= new Picture_VM(picture11);
    Picture_VM picture_VM12= new Picture_VM(picture12);
    Picture_VM picture_VM13= new Picture_VM(picture13);
    Picture_VM picture_VM14= new Picture_VM(picture14);
    Picture_VM picture_VM15= new Picture_VM(picture15);
    Picture_VM picture_VM16= new Picture_VM(picture16);
    Picture_VM picture_VM17= new Picture_VM(picture17);
    Picture_VM picture_VM18= new Picture_VM(picture18);
    Picture_VM picture_VM19= new Picture_VM(picture19);
    Picture_VM picture_VM20= new Picture_VM(picture20);
    Picture_VM picture_VM21= new Picture_VM(picture21);
    Picture_VM picture_VM22= new Picture_VM(picture22);
    Picture_VM picture_VM23= new Picture_VM(picture23);
    Picture_VM picture_VM24= new Picture_VM(picture24);
    Picture_VM picture_VM25= new Picture_VM(picture25);
    Picture_VM picture_VM26= new Picture_VM(picture26);
    Picture_VM picture_VM27= new Picture_VM(picture27);
    Picture_VM picture_VM28= new Picture_VM(picture28);
    Picture_VM picture_VM29= new Picture_VM(picture29);
    pictureVMs.add(picture_VM0);
    pictureVMs.add(picture_VM1);
    pictureVMs.add(picture_VM2);
    pictureVMs.add(picture_VM3);
    pictureVMs.add(picture_VM4);
    pictureVMs.add(picture_VM5);
    pictureVMs.add(picture_VM6);
    pictureVMs.add(picture_VM7);
    pictureVMs.add(picture_VM8);
    pictureVMs.add(picture_VM9);
    pictureVMs.add(picture_VM10);
    pictureVMs.add(picture_VM11);
    pictureVMs.add(picture_VM12);
    pictureVMs.add(picture_VM13);
    pictureVMs.add(picture_VM14);
    pictureVMs.add(picture_VM15);
    pictureVMs.add(picture_VM16);
    pictureVMs.add(picture_VM17);
    pictureVMs.add(picture_VM18);
    pictureVMs.add(picture_VM19);
    pictureVMs.add(picture_VM20);
    pictureVMs.add(picture_VM21);
    pictureVMs.add(picture_VM22);
    pictureVMs.add(picture_VM23);
    pictureVMs.add(picture_VM24);
    pictureVMs.add(picture_VM25);
    pictureVMs.add(picture_VM26);
    pictureVMs.add(picture_VM27);
    pictureVMs.add(picture_VM28);
    pictureVMs.add(picture_VM29);
  }

  @Override
  public int add(Picture _picture) throws SQLException {
    int size = pictureVMs.size();
    Picture_VM picture_VM = new Picture_VM(_picture);
    pictureVMs.add(picture_VM);
    int newsize = pictureVMs.size();
    return newsize - size;
  }

  @Override
  public int update(Picture oldPicture, Picture newPicture) throws SQLException{
    int location =-1;
    for (int i=0;i<pictureVMs.size();i++){
      if (pictureVMs.get(i).getPicture_ID().equals(oldPicture.getPicture_ID())){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }
    Picture_VM updated = new Picture_VM(newPicture);
    pictureVMs.set(location,updated);

    return 1;
  }

  @Override
  public List<Picture_VM> getAllPicture(int limit, int offset, int Album_ID, int Contributor_ID) throws SQLException {
    List<Picture_VM> results = new ArrayList<>();
    for (Picture_VM picture : pictureVMs){

      if ((picture.getAlbum_ID()!=null||picture.getAlbum_ID().equals(Album_ID))
          &&(picture.getContributor_ID()!=null||picture.getContributor_ID().equals(Contributor_ID))
      ){
        results.add(picture);
      }
    }
    return results;
  }


  @Override
  public Picture_VM getPictureByPrimaryKey(Picture _picture) throws SQLException {
    Picture_VM result = null;
    for (Picture_VM picture : pictureVMs) {
      if (picture.getPicture_ID().equals(_picture.getPicture_ID())){
        result = picture;
        break;
      }
    }
    if (result == null){
      throw new SQLException("Album not found");
    }
    return result;
  }

  @Override
  public int changeActivation(int Picture_ID, int mode) throws SQLException {
    int location =-1;
    for (int i=0;i<pictureVMs.size();i++){
      if (pictureVMs.get(i).getPicture_ID().equals(Picture_ID)){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }
    boolean status = pictureVMs.get(location).getIs_Active();
    pictureVMs.get(location).setIs_Active(!status);
    return 1;
  }

  @Override
  public int changeApproval(int Picture_ID, int mode) throws SQLException {
    int location =-1;
    for (int i=0;i<pictureVMs.size();i++){
      if (pictureVMs.get(i).getPicture_ID().equals(Picture_ID)){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }
    boolean status = pictureVMs.get(location).getis_Approved();
    pictureVMs.get(location).setis_Approved(!status);
    return 1;
  }

  @Override
  public List<Picture> getActivePicturebyAlbum(Integer Album_ID) throws SQLException {
    List<Picture> results = new ArrayList<>();
    for (Picture_VM picture : pictureVMs){
      if (picture.getAlbum_ID().equals(Album_ID)){
        results.add(picture);
      }
    }
    return results;
  }


  @Override
  public int getPictureCount() throws SQLException {
    return pictureVMs.size();
  }


  public int undeletePicture(Integer Picture_ID) throws SQLException{
    int location =-1;
    for (int i=0;i<pictureVMs.size();i++){
      if (pictureVMs.get(i).getPicture_ID().equals(Picture_ID)&&pictureVMs.get(i).getIs_Active()){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }
    pictureVMs.get(location).setIs_Active(true);
    return 1;
  }




}
