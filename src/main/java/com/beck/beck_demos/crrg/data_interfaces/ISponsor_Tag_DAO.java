package com.beck.beck_demos.crrg.data_interfaces;

import com.beck.beck_demos.crrg.models.Sponsor_Tag;
import com.beck.beck_demos.crrg.models.Sponsor_Tag_VM;

import java.sql.SQLException;
import java.util.List;

public interface ISponsor_Tag_DAO {
  int addSponsor_Tag(Sponsor_Tag_VM tag) throws SQLException;
  int removeSponsor_Tag (Sponsor_Tag tag) throws SQLException;

  List<Sponsor_Tag_VM> getAllSponsor_Tag(String Sponsor_ID);

  int  deleteSponsor_Tag(String  sponsor, int pictureID);
}
