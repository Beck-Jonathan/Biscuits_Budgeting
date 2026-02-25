package com.beck.beck_demos.budget_app.iData;

import com.beck.beck_demos.budget_app.models.Mortgage;

import java.sql.SQLException;
import java.util.List;

public interface iMortgageDAO {
   /**
    * DAO Method to retrieve by Foreign Key Mortgage objects
    * @return List of Mortgage
    * @author Jonathan Beck
    */
   List<Mortgage> getMortgagebyUser(String User_ID, int limit, int offset);
   /**
    * DAO Method to add Mortgage objects
    * @param _mortgage the Mortgage to be added
    * @return number of records added
    * @author Jonathan Beck
    */
   int add (Mortgage _mortgage) throws SQLException;

   /**
    * DAO Method to delete Mortgage objects
    * @param mortgageID the Mortgage to be deleted
    * @return number of records deleted
    * @author Jonathan Beck
    */
   Integer deleteMortgage(String mortgageID) throws SQLException;

   /**
    * DAO Method to update Mortgage objects
    * @param oldMortgage the Mortgage to be updated
    * @param newMortgage the updated version of the Mortgage
    * @return number of records updated
    * @author Jonathan Beck
    */
   int update(Mortgage oldMortgage, Mortgage newMortgage) throws SQLException;

   /**
    * DAO Method to retrieve by Foreign Key Mortgage objects
    * @return List of Mortgage
    * @author Jonathan Beck
    */
   Mortgage getMortgageByPrimaryKey(Mortgage _mortgage) throws SQLException;
}
