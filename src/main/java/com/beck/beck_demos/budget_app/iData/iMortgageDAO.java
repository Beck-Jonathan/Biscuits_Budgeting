package com.beck.beck_demos.budget_app.iData;

import com.beck.beck_demos.budget_app.models.Mortgage;

import java.util.List;

public interface iMortgageDAO {
   List<Mortgage> getMortgagebyUser(Integer User_ID, int limit, int offset);
}
