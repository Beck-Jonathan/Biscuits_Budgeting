package com.beck.beck_demos.crrg.data_fakes;

import com.beck.beck_demos.crrg.data.Contributor_DAO;
import com.beck.beck_demos.crrg.data_interfaces.iContributor_DAO;
import com.beck.beck_demos.crrg.models.Contributor;
import com.beck.beck_demos.crrg.models.Contributor_VM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Contributor_DAO_Fake implements iContributor_DAO {
  private  List<Contributor_VM> contributors;
  public Contributor_DAO_Fake() {
    contributors = new ArrayList<>();
    Contributor contributor0 = new Contributor(37, "IbBCgTIw", "GSsGpqdi", "KiLvTQZi@aol.com");
    Contributor contributor1 = new Contributor(32, "avyURGqx", "RHtdJuQe", "YwMbukca@aol.com");
    Contributor contributor2 = new Contributor(30, "niGrITRm", "LGxaAxrS", "bQECBDfA@aol.com");
    Contributor contributor3 = new Contributor(43, "meLrXAXj", "GJcaSVio", "ZVMcCYBQ@aol.com");
    Contributor contributor4 = new Contributor(25, "kgDMHwqH", "AchwCcOl", "IxjlfRNI@aol.com");
    Contributor_VM contributorVM0 = new Contributor_VM(contributor0,3);
    Contributor_VM contributorVM1 = new Contributor_VM(contributor1,3);
    Contributor_VM contributorVM2 = new Contributor_VM(contributor2,3);
    Contributor_VM contributorVM3 = new Contributor_VM(contributor3,3);
    Contributor_VM contributorVM4 =  new Contributor_VM(contributor4,3);

    contributors.add(contributorVM0);
    contributors.add(contributorVM1);
    contributors.add(contributorVM2);
    contributors.add(contributorVM3);
    contributors.add(contributorVM4);
  }
  @Override
  public int add(Contributor _contributor) throws SQLException {
    if (duplicateKey(_contributor)){
      return 0;
    }
    if (exceptionKey(_contributor)){
      throw new SQLException("error");
    }
    int size = contributors.size();
    contributors.add(new Contributor_VM(_contributor,3));
    int newsize = contributors.size();
    return newsize-size;
  }

  @Override
  public List<Contributor_VM> getAllContributor(int limit, int offset) throws SQLException {
    return contributors;
  }

  @Override
  public List<Contributor> getDistinctContributorForDropdown() throws SQLException {
    List<Contributor> results = new ArrayList<>();
    for (Contributor contributor : contributors){
      Contributor _contributor = new Contributor();
      _contributor.setContributor_ID(contributor.getContributor_ID());
      _contributor.setFirst_Name(contributor.getFirst_Name());
      results.add(_contributor);
    }
    return results;

  }

  @Override
  public Contributor_VM getContributorByPrimaryKey(Contributor _contributor) throws SQLException {
    Contributor_VM result = null;
    for (Contributor_VM contributor : contributors) {
      if (contributor.getContributor_ID().equals(_contributor.getContributor_ID())){
        result = contributor;
        break;
      }
    }
    if (result == null){
      throw new SQLException("contributor not found");
    }
    return result;
  }

  @Override
  public int update(Contributor oldContributor, Contributor newContributor) throws SQLException {
    int location =-1;
    if (exceptionKey(oldContributor)){
      throw new SQLException("error");
    }
    if (duplicateKey(oldContributor)){
      return 0;
    }
    for (int i=0;i<contributors.size();i++){
      if (contributors.get(i).getContributor_ID().equals(oldContributor.getContributor_ID())){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }
    Contributor_VM update = new Contributor_VM(newContributor,location);
    contributors.set(location,update);
    return 1;
  }
  private boolean duplicateKey(Contributor _contributor){
    return _contributor.getFirst_Name().equals("DUPLICATE");
  }
  private boolean exceptionKey(Contributor _contributor){
    return _contributor.getFirst_Name().equals("EXCEPTION");
  }

}
