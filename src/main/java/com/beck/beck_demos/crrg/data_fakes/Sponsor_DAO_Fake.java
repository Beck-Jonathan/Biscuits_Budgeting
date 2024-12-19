package com.beck.beck_demos.crrg.data_fakes;

import com.beck.beck_demos.crrg.data_interfaces.iSponsor_DAO;
import com.beck.beck_demos.crrg.models.Sponsor;
import com.beck.beck_demos.crrg.models.Sponsor_VM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Sponsor_DAO_Fake implements iSponsor_DAO {
  private static List<Sponsor_VM> sponsorVMs;
  static{
    sponsorVMs = new ArrayList<>();
    Sponsor sponsor0 = new Sponsor("nrCUTMuU", "yiViZWSS", "BqFtNIud", "jILWEalH", true);
    Sponsor sponsor1 = new Sponsor("nlSjPWct", "yiViZWSS", "hdyNtCno", "MLsJXJIt", true);
    Sponsor sponsor2 = new Sponsor("wIUiNXNE", "yiViZWSS", "HKPGecVA", "kcwClvuu", false);
    Sponsor sponsor3 = new Sponsor("cIIbVMek", "yiViZWSS", "TpVaRJUt", "dFQhBJWR", true);
    Sponsor sponsor4 = new Sponsor("tsyqHdJQ", "GblSTWyc", "nHHHWoUd", "tHyKEAKe", false);
    Sponsor sponsor5 = new Sponsor("EDExhwuB", "GblSTWyc", "eLaYaMAX", "TxZUGiaP", false);
    Sponsor sponsor6 = new Sponsor("XosoMVaF", "GblSTWyc", "SFJMGkcn", "KSfZwDbp", false);
    Sponsor sponsor7 = new Sponsor("RiwOHguc", "GblSTWyc", "oSkMLyxH", "hRJkSctS", false);
    Sponsor sponsor8 = new Sponsor("HDxRvPDx", "NdkQxDlA", "HfpVPgtx", "GfejsDVI", true);
    Sponsor sponsor9 = new Sponsor("CmfIMxAd", "NdkQxDlA", "SgZpgdOG", "wDeVARfE", false);
    Sponsor sponsor10 = new Sponsor("UTFZuUrN", "NdkQxDlA", "PSxZolwy", "IZBVJmFR", false);
    Sponsor sponsor11 = new Sponsor("hBADacQN", "NdkQxDlA", "NoVyNHao", "dDRKvMSG", true);
    Sponsor_VM sponsor_VM0= new Sponsor_VM(sponsor0);
    Sponsor_VM sponsor_VM1= new Sponsor_VM(sponsor1);
    Sponsor_VM sponsor_VM2= new Sponsor_VM(sponsor2);
    Sponsor_VM sponsor_VM3= new Sponsor_VM(sponsor3);
    Sponsor_VM sponsor_VM4= new Sponsor_VM(sponsor4);
    Sponsor_VM sponsor_VM5= new Sponsor_VM(sponsor5);
    Sponsor_VM sponsor_VM6= new Sponsor_VM(sponsor6);
    Sponsor_VM sponsor_VM7= new Sponsor_VM(sponsor7);
    Sponsor_VM sponsor_VM8= new Sponsor_VM(sponsor8);
    Sponsor_VM sponsor_VM9= new Sponsor_VM(sponsor9);
    Sponsor_VM sponsor_VM10= new Sponsor_VM(sponsor10);
    Sponsor_VM sponsor_VM11= new Sponsor_VM(sponsor11);
    sponsorVMs.add(sponsor_VM0);
    sponsorVMs.add(sponsor_VM1);
    sponsorVMs.add(sponsor_VM2);
    sponsorVMs.add(sponsor_VM3);
    sponsorVMs.add(sponsor_VM4);
    sponsorVMs.add(sponsor_VM5);
    sponsorVMs.add(sponsor_VM6);
    sponsorVMs.add(sponsor_VM7);
    sponsorVMs.add(sponsor_VM8);
    sponsorVMs.add(sponsor_VM9);
    sponsorVMs.add(sponsor_VM10);
    sponsorVMs.add(sponsor_VM11);
  }
  @Override
  public int add(Sponsor _sponsor) throws SQLException {
    int size = sponsorVMs.size();
    Sponsor_VM sponsor_VM = new Sponsor_VM(_sponsor);
    sponsorVMs.add(sponsor_VM);
    int newsize = sponsorVMs.size();
    return newsize-size;
  }

  @Override
  public List<Sponsor> getSponsorbyTier(String Tier_ID) throws SQLException {
    List<Sponsor> results = new ArrayList<>();
    for (Sponsor_VM sponsor : sponsorVMs){
      if (sponsor.getTier_ID().equals(Tier_ID)){
        results.add(sponsor);
      }
    }
    return results;
  }

  @Override
  public List<Sponsor> getAllSponsor(String Order_By) throws SQLException {
    List<Sponsor> results = new ArrayList<>();
    for (Sponsor_VM sponsor : sponsorVMs){

        results.add(sponsor);

    }
    return results;
  }

  @Override
  public List<String> getDistinctSponsorForDropdown() throws SQLException {
    List<String> results = new ArrayList<>();
    for (Sponsor_VM sponsor : sponsorVMs){
      results.add(sponsor.getSponsor_ID());
    }
    return results;
  }

  @Override
  public int deactivateSponsor(String Sponsor_ID) throws SQLException {
    int location =-1;
    for (int i=0;i<sponsorVMs.size();i++){
      if (sponsorVMs.get(i).getSponsor_ID().equals(Sponsor_ID)&&sponsorVMs.get(i).getIs_Active()){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }
    sponsorVMs.get(location).setIs_Active(false);
    return 1;
  }

  @Override
  public int undeleteSponsor(String Sponsor_ID) throws SQLException {
    int location =-1;
    for (int i=0;i<sponsorVMs.size();i++){
      if (sponsorVMs.get(i).getSponsor_ID().equals(Sponsor_ID)&&!sponsorVMs.get(i).getIs_Active()){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }
    sponsorVMs.get(location).setIs_Active(true);
    return 1;
  }

  @Override
  public List<String> selectAllTiersForDropDown() throws SQLException {
    return List.of();
  }

  @Override
  public int setPrimarySponsorImage() throws SQLException {
    return 0;
  }

  @Override
  public Sponsor getSponsorByPrimaryKey(Sponsor _sponsor) throws SQLException {
    Sponsor_VM result = null;
    for (Sponsor_VM sponsor : sponsorVMs) {
      if (sponsor.getSponsor_ID().equals(_sponsor.getSponsor_ID())){
        result = sponsor;
        break;
      }
    }
    if (result == null){
      throw new SQLException("Album not found");
    }
    return result;
  }


  @Override
  public int update(Sponsor oldSponsor, Sponsor newSponsor) throws SQLException{
    int location =-1;
    for (int i=0;i<sponsorVMs.size();i++){
      if (sponsorVMs.get(i).getSponsor_ID().equals(oldSponsor.getSponsor_ID())){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }
    Sponsor_VM updated = new Sponsor_VM(newSponsor);
    sponsorVMs.set(location,updated);
    return 1;
  }
}
