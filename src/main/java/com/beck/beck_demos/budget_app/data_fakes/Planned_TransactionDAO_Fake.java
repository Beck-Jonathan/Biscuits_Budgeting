package com.beck.beck_demos.budget_app.data_fakes;

import com.beck.beck_demos.budget_app.iData.iPlanned_TransactionDAO;
import com.beck.beck_demos.budget_app.models.Planned_Transaction;
import com.beck.beck_demos.budget_app.models.Planned_Transaction_VM;
import com.beck.beck_demos.budget_app.models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Planned_TransactionDAO_Fake implements iPlanned_TransactionDAO {

  private final List<Planned_Transaction_VM> Planned_TransactionVMs;

  public Planned_TransactionDAO_Fake() {
    Planned_TransactionVMs = new ArrayList<>();
    Planned_Transaction Planned_Transaction0 = new Planned_Transaction("OVfSlZsItrrpYeHcmxiLVsYqAwPYQqpSuKIv", "ElVWkHdfvxKPrukwGMJSROpPgnMRqatVBkkT", "hBjnJFeNMZULVcZbnnhbpcacXBKTiOAOigjh", "6f7c3e1e-4b2a-4f9e-8b1d-2a3c4d5e6f7a", "giumWZrY", 25.93, new Date(), 55, 62, true);
    Planned_Transaction Planned_Transaction1 = new Planned_Transaction("CGfvVUjDuEFAsuGnMFGlXfOEDaOHJAOrVHEh", "ElVWkHdfvxKPrukwGMJSROpPgnMRqatVBkkT", "ZTZfageeygRQIonucAeCjfkBCXaahjPBnomi", "2a3d4e5f-6a7b-8c9d-0e1f-2a3b4c5d6e7f", "oZaNjHmm", 22.87, new Date(), 42, 32, true);
    Planned_Transaction Planned_Transaction2 = new Planned_Transaction("YdIGDwPWPbAALmaDoywOprKoWRdbPBKQLQJm", "ElVWkHdfvxKPrukwGMJSROpPgnMRqatVBkkT", "TXQNdJmPuHsFeuSxotbPbeMgakGvpIptPJym", "a1b2c3d4-e5f6-4a7b-8c9d-0e1f2a3b4c5d", "HXBdexQr", 58d, new Date(), 47, 33, true);
    Planned_Transaction Planned_Transaction3 = new Planned_Transaction("XgTFExCLUkSKOmJNDAahQsRRZNWrlWuMikhZ", "ElVWkHdfvxKPrukwGMJSROpPgnMRqatVBkkT", "nsilrmUBfqbJYpUXxkDXcumKVOAOOYEroeXT", "f5e4d3c2-b1a0-4987-a6b5-c4d3e2f1g0h9", "jLVfngEl", 49.27, new Date(), 49, 51, false);
    Planned_Transaction Planned_Transaction4 = new Planned_Transaction("FbHFBXbJFaBjyeBrBsnnpBjaHBChjUpNIDQe", "ElVWkHdfvxKPrukwGMJSROpPgnMRqatVBkkT", "bLCUQkECXsVfXCRUJWrokNXiuUZEbOZpZSDY", "9d8c7b6a-5e4d-4c3b-2a1f-0e9d8c7b6a5e", "sEJGekkB", 42.95, new Date(), 25, 25, true);
    Planned_Transaction Planned_Transaction5 = new Planned_Transaction("aDcidKcKbwGgKyUHSVomOrKWRRbvTQrsQwIG", "XfEouHjoKRKYSLRbgpQEMVNpOAQtkvuISVsf", "smpibSveuiLEyrEMlKyWIfGXkyOMjqFOZRoG", "b2c3d4e5-f6a7-4b8c-9d0e-1f2a3b4c5d6e", "GYIIEFew", 50.98, new Date(), 57, 61, true);
    Planned_Transaction Planned_Transaction6 = new Planned_Transaction("tqpDOWCFvReEWHvHQcraMYiPlXjbqOPRRAqN", "fknVAgYGnxRUZWoArpdGZVvxVsKrISZivGZs", "smpibSveuiLEyrEMlKyWIfGXkyOMjqFOZRoG", "c3d4e5f6-a7b8-4c9d-0e1f-2a3b4c5d6e7f", "qsLMUOcC", 39.66, new Date(), 15, 17, false);
    Planned_Transaction Planned_Transaction7 = new Planned_Transaction("RuuTfrqXTwuBvYtEGTvsmDPFyWnAZTPQBbjL", "MRFFYlremsUAvycIcDcknCscZtglbiUWGaWe", "smpibSveuiLEyrEMlKyWIfGXkyOMjqFOZRoG", "d4e5f6a7-b8c9-4d0e-1f2a-3b4c5d6e7f8a", "NBqpDLNL", 16.77, new Date(), 51, 63, false);
    Planned_Transaction Planned_Transaction8 = new Planned_Transaction("SeXhOwNGcVBkwolukpfXTMbCkYurCqXcuWYI", "TQjZssSdyYpeSOatJSTtFSmubfSptiIgFdHb", "smpibSveuiLEyrEMlKyWIfGXkyOMjqFOZRoG", "e5f6a7b8-c9d0-4e1f-2a3b-4c5d6e7f8a9b", "bxFyuwcR", 52.83, new Date(), 50, 60, false);
    Planned_Transaction Planned_Transaction9 = new Planned_Transaction("QgRJGpZhGxRLNUyIiPdrysAcwgYiuegYglXA", "ChWSXgpQCFtrlPXCgogNjMPIcAltVQcNWBRP", "smpibSveuiLEyrEMlKyWIfGXkyOMjqFOZRoG", "f6a7b8c9-d0e1-4f2a-3b4c-5d6e7f8a9b0c", "kqUWGciR", 65.71, new Date(), 36, 68, true);
    Planned_Transaction Planned_Transaction10 = new Planned_Transaction("weiVdXjBpTmpGNoADhgBgMHarpUEcJtZjiIf", "WgacPrVUOEplVpJFRZxvmuBFXGZEmEhoSxqu", "tXnkOdQXbtGqqLPtcvfkERtBiNyNfWYOCvwQ", "a7b8c9d0-e1f2-4a3b-4c5d-6e7f8a9b0c1d", "quQJEInB", 58.62, new Date(), 16, 45, true);
    Planned_Transaction Planned_Transaction11 = new Planned_Transaction("jVQJuhboOyDhegurLcfnFuPcwFpWGVustMPT", "WgacPrVUOEplVpJFRZxvmuBFXGZEmEhoSxqu", "tcAvjepcYxrJgcvRjcmiNySSFobIkZLNVbZK", "b8c9d0e1-f2a3-4b4c-5d6e-7f8a9b0c1d2e", "VOWfuNXJ", 11.07, new Date(), 17, 43, true);
    Planned_Transaction Planned_Transaction12 = new Planned_Transaction("NgSwZMTrMSacltIEhBgwsuArsDpmxYILNiFL", "WgacPrVUOEplVpJFRZxvmuBFXGZEmEhoSxqu", "mrliOSHMmjeWwbSQClNEJVVCovnnFhnHEAQH", "c9d0e1f2-a3b4-4c5d-6e7f-8a9b0c1d2e3f", "fRvYDhOy", 43.89, new Date(), 66, 43, true);
    Planned_Transaction Planned_Transaction13 = new Planned_Transaction("SXhCEcoSytNblNvsxGhNrLkXgaNMpxkyEZNw", "WgacPrVUOEplVpJFRZxvmuBFXGZEmEhoSxqu", "FIqdrWpnXgAkmiKYyBNByusvvZvLtpZcsybc", "d0e1f2a3-b4c5-4d6e-7f8a-9b0c1d2e3f4a", "UMCfZxOm", 40.69, new Date(), 56, 26, true);
    Planned_Transaction Planned_Transaction14 = new Planned_Transaction("hXetbNXLsjcodnLUVtgJvJuiEgMPmUShgnbB", "WgacPrVUOEplVpJFRZxvmuBFXGZEmEhoSxqu", "HwJtNVNQiLKvGUQGvAdSBYKpbSGMSwjDUMHv", "e1f2a3b4-c5d6-4e7f-8a9b-0c1d2e3f4a5b", "ISQRTZJf", 48.86, new Date(), 40, 51, true);
    Planned_Transaction Planned_Transaction15 = new Planned_Transaction("qpKMATjfpmDdnLubabMLMEferLylXWRMpaqL", "YDnlyUdlbIIktXVaRaUYAjWhpCbvWjblZPUb", "JJrpHasNTengTBhxAJvoYpCmlmhSlELJGotT", "f2a3b4c5-d6e7-4f8a-9b0c-1d2e3f4a5b6c", "kSqMjCOC", 50.15, new Date(), 47, 24, true);
    Planned_Transaction Planned_Transaction16 = new Planned_Transaction("DIRBoHraVReDnpyiIixTBLnVLhGsiTnAKgKK", "rdxuWrDBvfdYkhgwqCcIoKwoDErkrLMNptmw", "JJrpHasNTengTBhxAJvoYpCmlmhSlELJGotT", "a3b4c5d6-e7f8-4a9b-0c1d-2e3f4a5b6c7d", "RIHeNBPf", 57.25, new Date(), 46, 32, false);
    Planned_Transaction Planned_Transaction17 = new Planned_Transaction("nKQDSYWcNmtytaIYbYFHYFsGhFoVrUvTJiFs", "KNfCpUFJasTgEcgJWZSfiAEXGMsBuZZPTByq", "JJrpHasNTengTBhxAJvoYpCmlmhSlELJGotT", "b4c5d6e7-f8a9-4b0c-1d2e-3f4a5b6c7d8e", "JcolWWrS", 47.67, new Date(), 56, 42, false);
    Planned_Transaction Planned_Transaction18 = new Planned_Transaction("AlgENoPehyndaOSHkmTgLitcMDvtepXIIRup", "ceHnMOxGyOlfAlSkuraePTTfxqlEFsadXoGX", "JJrpHasNTengTBhxAJvoYpCmlmhSlELJGotT", "c5d6e7f8-a9b0-4c1d-2e3f-4a5b6c7d8e9f", "aZBICJKR", 14.9, new Date(), 13, 20, true);
    Planned_Transaction Planned_Transaction19 = new Planned_Transaction("rbyPNJgluVUdQHdRMKSCbugcrTZJBBoxlusX", "JcxpAEceQSSksSovSgrVShuesMADIsGVfrXq", "JJrpHasNTengTBhxAJvoYpCmlmhSlELJGotT", "d6e7f8a9-b0c1-4d2e-3f4a-5b6c7d8e9f0a", "iwVIQbJE", 48.86, new Date(), 58, 32, true);
    Planned_Transaction Planned_Transaction20 = new Planned_Transaction("UFVMLDAkMkiktBZaJilIVQpxiwlbqwGOqvGb", "CLHqbaHZLVNExNUQiFkpyEqqTVkLBnpaiZir", "oOcDxoOaCyetkdfJbXlNSsbopTvOABiKsGaT", "e7f8a9b0-c1d2-4e3f-4a5b-6c7d8e9f0a1b", "oLpqnOiV", 66.79, new Date(), 13, 10, true);
    Planned_Transaction Planned_Transaction21 = new Planned_Transaction("jesCGQWsObakCnaSdLAdLHmspufauFMuMVOF", "CLHqbaHZLVNExNUQiFkpyEqqTVkLBnpaiZir", "JEEAeOpyADSvAoVpsUvVyCOnQMkZfpHuaafV", "f8a9b0c1-d2e3-4f4a-5b6c-7d8e9f0a1b2c", "WNbCFXsu", 58.49, new Date(), 61, 23, false);
    Planned_Transaction Planned_Transaction22 = new Planned_Transaction("lDWntoOMxHrwvpuUhUsqhystEKLpuZoyhuEX", "CLHqbaHZLVNExNUQiFkpyEqqTVkLBnpaiZir", "DKmYOKoneyjDBIdgDxNupwmRAEyIqefeHBao", "a9b0c1d2-e3f4-4a5b-6c7d-8e9f0a1b2c3d", "IFQmuStR", 51.18, new Date(), 68, 29, false);
    Planned_Transaction Planned_Transaction23 = new Planned_Transaction("djoHKYUfJFoJryeNSGflNAxWHtrKdUQmHvJg", "CLHqbaHZLVNExNUQiFkpyEqqTVkLBnpaiZir", "DWLdJAVvYWGVkdxErErJRIexPpBLqTymnnXk", "b0c1d2e3-f4a5-4b6c-7d8e-9f0a1b2c3d4e", "RPXLBDFv", 44.23, new Date(), 66, 54, true);
    Planned_Transaction Planned_Transaction24 = new Planned_Transaction("KktNmVwpKCilcgfHkFCisQqnIvtiUlHrQnAQ", "CLHqbaHZLVNExNUQiFkpyEqqTVkLBnpaiZir", "WckTwYHBfcWkeZYuhveDoVTjnpEwGcLuIUWT", "c1d2e3f4-a5b6-4c7d-8e9f-0a1b2c3d4e5f", "yLgXPfOo", 26.84, new Date(), 10, 52, false);
    Planned_Transaction Planned_Transaction25 = new Planned_Transaction("ABuiXgSDvnBVDqiFwCiWaMEgsXsByKPaMjYv", "QsAlmYoypBPYCvdlopHPbBmJfDnRDtNJcHyi", "AKMEtlGQcecivZIaIpcsAwikkNkvZefMDaUB", "d2e3f4a5-b6c7-4d8e-9f0a-1b2c3d4e5f6a", "mmtYgtkP", 27.84, new Date(), 37, 38, false);
    Planned_Transaction Planned_Transaction26 = new Planned_Transaction("cHVAgmHQGpuwtOBCMpuGCtUcSimuWDbpAAte", "AvmrJWqfhmlhwALsDOFvtUVdSEBTbksCnBly", "AKMEtlGQcecivZIaIpcsAwikkNkvZefMDaUB", "e3f4a5b6-c7d8-4e9f-0a1b-2c3d4e5f6a7b", "dKXZIULr", 48.62, new Date(), 22, 57, false);
    Planned_Transaction Planned_Transaction27 = new Planned_Transaction("FUDwsitvNMPrpfmJyHQImBfgbMNHYeSEfRNy", "onOCJXnMiBRLHghGmnNaHNvDoYZiYQmXyeWQ", "AKMEtlGQcecivZIaIpcsAwikkNkvZefMDaUB", "f4a5b6c7-d8e9-4f0a-1b2c-3d4e5f6a7b8c", "MrvPvmnd", 19.2, new Date(), 43, 43, false);
    Planned_Transaction Planned_Transaction28 = new Planned_Transaction("jPSruUPiWAyiLxykPLPusMYXjxFrKdIHfwDr", "ClqWFoWFYokUSWpbBusoqppiMyOvFTMDSOqd", "AKMEtlGQcecivZIaIpcsAwikkNkvZefMDaUB", "a5b6c7d8-e9f0-4a1b-2c3d-4e5f6a7b8c9d", "Udohjfgb", 13.3, new Date(), 67, 66, true);
    Planned_Transaction Planned_Transaction29 = new Planned_Transaction("xoPooXxJlPQJRLBIFDsCStuMSgoTaxfWosuN", "pFreTFlwttLfwuiLfHVOKoqKUgDbrIivetCa", "AKMEtlGQcecivZIaIpcsAwikkNkvZefMDaUB", "b6c7d8e9-f0a1-4b2c-3d4e-5f6a7b8c9d0e", "yBCBuSlv", 48.61, new Date(), 50, 23, true);
    Planned_Transaction_VM Planned_Transaction_VM0 = new Planned_Transaction_VM(Planned_Transaction0);
    Planned_Transaction_VM Planned_Transaction_VM1 = new Planned_Transaction_VM(Planned_Transaction1);
    Planned_Transaction_VM Planned_Transaction_VM2 = new Planned_Transaction_VM(Planned_Transaction2);
    Planned_Transaction_VM Planned_Transaction_VM3 = new Planned_Transaction_VM(Planned_Transaction3);
    Planned_Transaction_VM Planned_Transaction_VM4 = new Planned_Transaction_VM(Planned_Transaction4);
    Planned_Transaction_VM Planned_Transaction_VM5 = new Planned_Transaction_VM(Planned_Transaction5);
    Planned_Transaction_VM Planned_Transaction_VM6 = new Planned_Transaction_VM(Planned_Transaction6);
    Planned_Transaction_VM Planned_Transaction_VM7 = new Planned_Transaction_VM(Planned_Transaction7);
    Planned_Transaction_VM Planned_Transaction_VM8 = new Planned_Transaction_VM(Planned_Transaction8);
    Planned_Transaction_VM Planned_Transaction_VM9 = new Planned_Transaction_VM(Planned_Transaction9);
    Planned_Transaction_VM Planned_Transaction_VM10 = new Planned_Transaction_VM(Planned_Transaction10);
    Planned_Transaction_VM Planned_Transaction_VM11 = new Planned_Transaction_VM(Planned_Transaction11);
    Planned_Transaction_VM Planned_Transaction_VM12 = new Planned_Transaction_VM(Planned_Transaction12);
    Planned_Transaction_VM Planned_Transaction_VM13 = new Planned_Transaction_VM(Planned_Transaction13);
    Planned_Transaction_VM Planned_Transaction_VM14 = new Planned_Transaction_VM(Planned_Transaction14);
    Planned_Transaction_VM Planned_Transaction_VM15 = new Planned_Transaction_VM(Planned_Transaction15);
    Planned_Transaction_VM Planned_Transaction_VM16 = new Planned_Transaction_VM(Planned_Transaction16);
    Planned_Transaction_VM Planned_Transaction_VM17 = new Planned_Transaction_VM(Planned_Transaction17);
    Planned_Transaction_VM Planned_Transaction_VM18 = new Planned_Transaction_VM(Planned_Transaction18);
    Planned_Transaction_VM Planned_Transaction_VM19 = new Planned_Transaction_VM(Planned_Transaction19);
    Planned_Transaction_VM Planned_Transaction_VM20 = new Planned_Transaction_VM(Planned_Transaction20);
    Planned_Transaction_VM Planned_Transaction_VM21 = new Planned_Transaction_VM(Planned_Transaction21);
    Planned_Transaction_VM Planned_Transaction_VM22 = new Planned_Transaction_VM(Planned_Transaction22);
    Planned_Transaction_VM Planned_Transaction_VM23 = new Planned_Transaction_VM(Planned_Transaction23);
    Planned_Transaction_VM Planned_Transaction_VM24 = new Planned_Transaction_VM(Planned_Transaction24);
    Planned_Transaction_VM Planned_Transaction_VM25 = new Planned_Transaction_VM(Planned_Transaction25);
    Planned_Transaction_VM Planned_Transaction_VM26 = new Planned_Transaction_VM(Planned_Transaction26);
    Planned_Transaction_VM Planned_Transaction_VM27 = new Planned_Transaction_VM(Planned_Transaction27);
    Planned_Transaction_VM Planned_Transaction_VM28 = new Planned_Transaction_VM(Planned_Transaction28);
    Planned_Transaction_VM Planned_Transaction_VM29 = new Planned_Transaction_VM(Planned_Transaction29);
    Planned_TransactionVMs.add(Planned_Transaction_VM0);
    Planned_TransactionVMs.add(Planned_Transaction_VM1);
    Planned_TransactionVMs.add(Planned_Transaction_VM2);
    Planned_TransactionVMs.add(Planned_Transaction_VM3);
    Planned_TransactionVMs.add(Planned_Transaction_VM4);
    Planned_TransactionVMs.add(Planned_Transaction_VM5);
    Planned_TransactionVMs.add(Planned_Transaction_VM6);
    Planned_TransactionVMs.add(Planned_Transaction_VM7);
    Planned_TransactionVMs.add(Planned_Transaction_VM8);
    Planned_TransactionVMs.add(Planned_Transaction_VM9);
    Planned_TransactionVMs.add(Planned_Transaction_VM10);
    Planned_TransactionVMs.add(Planned_Transaction_VM11);
    Planned_TransactionVMs.add(Planned_Transaction_VM12);
    Planned_TransactionVMs.add(Planned_Transaction_VM13);
    Planned_TransactionVMs.add(Planned_Transaction_VM14);
    Planned_TransactionVMs.add(Planned_Transaction_VM15);
    Planned_TransactionVMs.add(Planned_Transaction_VM16);
    Planned_TransactionVMs.add(Planned_Transaction_VM17);
    Planned_TransactionVMs.add(Planned_Transaction_VM18);
    Planned_TransactionVMs.add(Planned_Transaction_VM19);
    Planned_TransactionVMs.add(Planned_Transaction_VM20);
    Planned_TransactionVMs.add(Planned_Transaction_VM21);
    Planned_TransactionVMs.add(Planned_Transaction_VM22);
    Planned_TransactionVMs.add(Planned_Transaction_VM23);
    Planned_TransactionVMs.add(Planned_Transaction_VM24);
    Planned_TransactionVMs.add(Planned_Transaction_VM25);
    Planned_TransactionVMs.add(Planned_Transaction_VM26);
    Planned_TransactionVMs.add(Planned_Transaction_VM27);
    Planned_TransactionVMs.add(Planned_Transaction_VM28);
    Planned_TransactionVMs.add(Planned_Transaction_VM29);
  }

  @Override
  public String add(Planned_Transaction _planned_transaction) throws SQLException {
    if (duplicateKey(_planned_transaction)) {
      return "";
    }
    if (exceptionKey(_planned_transaction)) {
      throw new SQLException("error");
    }
    int size = Planned_TransactionVMs.size();

    Planned_Transaction_VM toAdd = new Planned_Transaction_VM(_planned_transaction);
    Planned_TransactionVMs.add(toAdd);
    return UUID.randomUUID().toString();

  }

  @Override
  public int update(Planned_Transaction oldplanned_transaction, Planned_Transaction newplanned_transaction) throws SQLException {
    int location = -1;
    if (duplicateKey(newplanned_transaction)) {
      return 0;
    }
    if (exceptionKey(newplanned_transaction)) {
      throw new SQLException("error");
    }
    for (int i = 0; i < Planned_TransactionVMs.size(); i++) {
      if (Planned_TransactionVMs.get(i).getplanned_transaction_ID().equals(oldplanned_transaction.getplanned_transaction_ID())) {
        location = i;
        break;
      }
    }
    if (location == -1) {
      throw new SQLException();
    }
    Planned_Transaction_VM newplanned_transaction_vm = new Planned_Transaction_VM(newplanned_transaction);
    Planned_TransactionVMs.set(location, newplanned_transaction_vm);
    return 1;
  }

  @Override
  public List<Planned_Transaction_VM> getAllplanned_transaction(int offset, int limit, String search, String user_ID, String subcategory_ID, String budget_ID) throws SQLException {
    search = search.toLowerCase();
    List<Planned_Transaction_VM> results = new ArrayList<>();
    for (Planned_Transaction_VM planned_Transaction : Planned_TransactionVMs) {
      if ((!user_ID.isEmpty() && !planned_Transaction.getuser_ID().equals(user_ID))) {
        continue;
      }
      if (!subcategory_ID.isEmpty() && !planned_Transaction.getsubcategory_ID().equals(subcategory_ID)) {
        continue;
      }
      if (!budget_ID.isEmpty() && planned_Transaction.getbudget_id() != null && !planned_Transaction.getbudget_id().equals(budget_ID)) {
        continue;
      }

      if (!search.isEmpty() && !planned_Transaction.getnickname().toLowerCase().contains(search)) {
        continue;
      }
      results.add(planned_Transaction);
    }
    return results;
  }

  @Override
  public int deactivatePlanned_transaction(Planned_Transaction _planned_transaction) throws SQLException {
    if (exceptionKey(_planned_transaction)) {
      throw new SQLException("Exception");
    }
    for (int i = 0; i < Planned_TransactionVMs.size(); i++) {
      if (Planned_TransactionVMs.get(i).getplanned_transaction_ID().equals(_planned_transaction.getplanned_transaction_ID())) {
        if (Planned_TransactionVMs.get(i).getis_active()) {
          Planned_TransactionVMs.get(i).setis_active(false);
          return 1;
        } else {
          return 0;
        }
      }
    }
    return -1;
  }

  @Override
  public int deletePlanned_transaction(Planned_Transaction _planned_transaction) throws SQLException {
    if (exceptionKey(_planned_transaction)) {
      throw new SQLException("Exception");
    }
    int size = Planned_TransactionVMs.size();
    int location = -1;
    for (int i = 0; i < Planned_TransactionVMs.size(); i++) {
      if (Planned_TransactionVMs.get(i).getplanned_transaction_ID().equals(_planned_transaction.getplanned_transaction_ID())) {
        location = i;
        break;
      }
    }
    if (location == -1) {
      throw new SQLException("Unable To Find Planned_Transaction.");
    }
    Planned_TransactionVMs.remove(location);
    int newsize = Planned_TransactionVMs.size();
    return size - newsize;
  }

  @Override
  public int reactivatePlanned_transaction(Planned_Transaction _planned_transaction) throws SQLException {
    if (exceptionKey(_planned_transaction)) {
      throw new SQLException("Exception");
    }

    for (int i = 0; i < Planned_TransactionVMs.size(); i++) {
      if (Planned_TransactionVMs.get(i).getplanned_transaction_ID().equals(_planned_transaction.getplanned_transaction_ID())) {
        if (!Planned_TransactionVMs.get(i).getis_active()) {
          Planned_TransactionVMs.get(i).setis_active(true);
          return 1;
        } else {
          return 0;
        }
      }
    }
    return -1;
  }

  @Override
  public int deactivateAllPlanned_Transaction(User user) throws SQLException {
    int result = 0;
    for (int i = 0; i < Planned_TransactionVMs.size(); i++) {
      if (Planned_TransactionVMs.get(i).getuser_ID().equals(user.getUser_ID())) {
        if (Planned_TransactionVMs.get(i).getis_active()) {
          Planned_TransactionVMs.get(i).setis_active(false);
          result++;
        }
      }
    }

    return result;
  }

  @Override
  public int reactivateAllPlanned_Transaction(User user) throws SQLException {
    int result = 0;
    for (int i = 0; i < Planned_TransactionVMs.size(); i++) {
      if (Planned_TransactionVMs.get(i).getuser_ID().equals(user.getUser_ID())) {
        if (!Planned_TransactionVMs.get(i).getis_active()) {
          Planned_TransactionVMs.get(i).setis_active(true);
          result++;
        }
      }
    }

    return result;
  }

  private boolean duplicateKey(Planned_Transaction _planned_transaction) {
    if (_planned_transaction.getplanned_transaction_ID() != null) {
      return _planned_transaction.getplanned_transaction_ID().contains("DUPLICATE");
    }
    return _planned_transaction.getnickname().contains("DUPLICATE");

  }

  private boolean exceptionKey(Planned_Transaction _planned_transaction) {
    if (_planned_transaction.getplanned_transaction_ID() != null) {
      return _planned_transaction.getplanned_transaction_ID().contains("EXCEPTION");
    }
    return _planned_transaction.getnickname().contains("EXCEPTION");
  }
}
