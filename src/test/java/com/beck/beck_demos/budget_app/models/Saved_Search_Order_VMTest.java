package com.beck.beck_demos.budget_app.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class Saved_Search_Order_VMTest {
  private Saved_Search_Order_VM _saved_search_orderVM;
  @BeforeEach
  public void setup(){
    _saved_search_orderVM = new Saved_Search_Order_VM();
  }
  @AfterEach
  public void teardown(){
    _saved_search_orderVM = null;
  }
  @Test
  public void testSaved_Search_OrderDefaultConstructorSetsNoVariables(){
    Saved_Search_Order_VM _saved_search_orderVM= new Saved_Search_Order_VM();
    Assertions.assertNull(_saved_search_orderVM.getSaved_Search_Order_ID());
    Assertions.assertNull(_saved_search_orderVM.getOwned_User());
    Assertions.assertNull(_saved_search_orderVM.getNickname());
    Assertions.assertNull(_saved_search_orderVM.getDescription());
    Assertions.assertNull(_saved_search_orderVM.getLast_Used());
    Assertions.assertNull(_saved_search_orderVM.getLast_Updated());
    Assertions.assertNull(_saved_search_orderVM.getTimes_Ran());
    Assertions.assertNull(_saved_search_orderVM.getUser());
    Assertions.assertNull(_saved_search_orderVM.getSaved_Search_Order_Lines());
  }

  @Test
  public void testSuperSaved_Search_OrderParameterizedVMConstructorSetsAllVariables() throws ParseException {
    String strDate = "6/2/2025";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    Saved_Search_Order _saved_search_order= new Saved_Search_Order(
        8739,
        "f503f3ba-3375-40d4-8a0f-5c5aee018c6b",
        "hyZEgNFORjRMrUduYoOvfteYaiBsZvifshNKqaRCNPUomwIrjanJCoQdDnbnSKHZDritXdPuAsNVmpvStDHCBlOXJliNefDtPV",
        "AgVmsgCObAlrAQDRApkZOHMlObSaveUIEMJaApNTbksZdKGXQgkXuARvUlwrSfNSgfVuybhDpVZXGDlglNayitraCmBGxSrYknRMTSYuUYsWayEGgmgTvngwrDbxUPJJQtNOUwMdsdxqmSEBpJebSVEdMBoKKrGGjGStCWsMnfPNSAAlUcEfqdqPWJfcZxjoCLecEhQjnVtAnYoPBqOFPmdeqjLLRkcLwiAyxaGGksUNmKveimYBwNxjReQoq",
        df.parse("2/8/2025 1:48:31 AM"),
        df.parse("2/1/2026 5:08:16 PM"),
        3641
    );
    _saved_search_orderVM = new Saved_Search_Order_VM(_saved_search_order);
    Assertions.assertEquals(8739,_saved_search_orderVM.getSaved_Search_Order_ID());
    Assertions.assertEquals("f503f3ba-3375-40d4-8a0f-5c5aee018c6b",_saved_search_orderVM.getOwned_User());
    Assertions.assertEquals("hyZEgNFORjRMrUduYoOvfteYaiBsZvifshNKqaRCNPUomwIrjanJCoQdDnbnSKHZDritXdPuAsNVmpvStDHCBlOXJliNefDtPV",_saved_search_orderVM.getNickname());
    Assertions.assertEquals("AgVmsgCObAlrAQDRApkZOHMlObSaveUIEMJaApNTbksZdKGXQgkXuARvUlwrSfNSgfVuybhDpVZXGDlglNayitraCmBGxSrYknRMTSYuUYsWayEGgmgTvngwrDbxUPJJQtNOUwMdsdxqmSEBpJebSVEdMBoKKrGGjGStCWsMnfPNSAAlUcEfqdqPWJfcZxjoCLecEhQjnVtAnYoPBqOFPmdeqjLLRkcLwiAyxaGGksUNmKveimYBwNxjReQoq",_saved_search_orderVM.getDescription());
    Assertions.assertEquals(df.parse("2/8/2025 1:48:31 AM"),_saved_search_orderVM.getLast_Used());
    Assertions.assertEquals(df.parse("2/1/2026 5:08:16 PM"),_saved_search_orderVM.getLast_Updated());
    Assertions.assertEquals(3641,_saved_search_orderVM.getTimes_Ran());
  }

  @Test
  public void testSuperSaved_Search_OrderParameterizedVMConstructorSetsAllVariablesAndParent() throws ParseException {
    String strDate = "6/2/2025";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    Saved_Search_Order _saved_search_order= new Saved_Search_Order(
        3195,
        "f503f3ba-3375-40d4-8a0f-5c5aee018c6b",
        "AUFHJHhClApnbPOLiVgutlljeWQoscXtHJSgjXMPsrKlvdqwjFZSUljXNrWXsEHiCVqeOiestEensOlJYgLnHyAxWZQdlsOyRV",
        "nZNZFcUdevjfNpifOjOWZNcACyAlJhCrlTRgsXdmqaoJOfYQeNfDAApEUcsmBdbksBlpFvfnqmTbVShTRqnqPJwjLaltOsWEDTCNlBAWOaNPDLKwypRbMGcQQagNdEpNKPwnaEOIxwQZLhDvWjluqMZvZTJdXCrQOWVubJBkIeApVyJhgFOXjUdbnoWpOauZgIctGEovfEUAvoRdrBkSmTetgElOZILwsURfhvjWtfOhwCuenJUOPLWjKWdom",
        df.parse("4/19/2022 6:31:39 AM"),
        df.parse("1/11/2020 2:48:12 AM"),
        9973
    );
    User _user = new User();
    _saved_search_orderVM = new Saved_Search_Order_VM(_saved_search_order, _user);
    Assertions.assertEquals(3195,_saved_search_orderVM.getSaved_Search_Order_ID());
    Assertions.assertEquals("f503f3ba-3375-40d4-8a0f-5c5aee018c6b",_saved_search_orderVM.getOwned_User());
    Assertions.assertEquals("AUFHJHhClApnbPOLiVgutlljeWQoscXtHJSgjXMPsrKlvdqwjFZSUljXNrWXsEHiCVqeOiestEensOlJYgLnHyAxWZQdlsOyRV",_saved_search_orderVM.getNickname());
    Assertions.assertEquals("nZNZFcUdevjfNpifOjOWZNcACyAlJhCrlTRgsXdmqaoJOfYQeNfDAApEUcsmBdbksBlpFvfnqmTbVShTRqnqPJwjLaltOsWEDTCNlBAWOaNPDLKwypRbMGcQQagNdEpNKPwnaEOIxwQZLhDvWjluqMZvZTJdXCrQOWVubJBkIeApVyJhgFOXjUdbnoWpOauZgIctGEovfEUAvoRdrBkSmTetgElOZILwsURfhvjWtfOhwCuenJUOPLWjKWdom",_saved_search_orderVM.getDescription());
    Assertions.assertEquals(df.parse("4/19/2022 6:31:39 AM"),_saved_search_orderVM.getLast_Used());
    Assertions.assertEquals(df.parse("1/11/2020 2:48:12 AM"),_saved_search_orderVM.getLast_Updated());
    Assertions.assertEquals(9973,_saved_search_orderVM.getTimes_Ran());
    Assertions.assertEquals(_user ,_saved_search_orderVM.getUser());
  }

  @Test
  public void testSuperSaved_Search_OrderParameterizedVMConstructorSetsAllVariablesAndChildren() throws ParseException {
    String strDate = "6/2/2025";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date date = df.parse(strDate);
    Saved_Search_Order _saved_search_order= new Saved_Search_Order(
        288,
        "f503f3ba-3375-40d4-8a0f-5c5aee018c6b",
        "DRXcrUBcdEFPoGfsHBtBhDIjmRVAMUsGEMLeYbkCKbfovNUrAvCcvidOQNEstuWtZOiYIxyKwVIfTUwEPfunTnGchkJUBmGJam",
        "DFvEMZQtFXxSydmStFhJWWmkBcUUJnJAVTbhUDihDImpUWymPFthQXgjrUknRdPLeBHZiTdxKNwVltSEFgoQMvVyyohXxkHnIjZxZyLgNYcUFXjLwtXbeHseMAfpadJsFfgddjivWejNyOSnfsjInpyFZavmmQdPqPgrikpJXMlThJhsBFPgbDJNHydqNwdlncDPEFIuHNkNOcFbOZTshmFZkiGxulHaJUEplAKfvvtkBlfhBPrpBcVxKAfcf",
        df.parse("3/1/2015 5:27:20 PM"),
        df.parse("4/2/2017 7:09:43 PM"),
        8456
    );
    List<Saved_Search_Order_Line> saved_search_order_lines = new ArrayList<>();
    _saved_search_orderVM = new Saved_Search_Order_VM(_saved_search_order, saved_search_order_lines);
    Assertions.assertEquals(288,_saved_search_orderVM.getSaved_Search_Order_ID());
    Assertions.assertEquals("f503f3ba-3375-40d4-8a0f-5c5aee018c6b",_saved_search_orderVM.getOwned_User());
    Assertions.assertEquals("DRXcrUBcdEFPoGfsHBtBhDIjmRVAMUsGEMLeYbkCKbfovNUrAvCcvidOQNEstuWtZOiYIxyKwVIfTUwEPfunTnGchkJUBmGJam",_saved_search_orderVM.getNickname());
    Assertions.assertEquals("DFvEMZQtFXxSydmStFhJWWmkBcUUJnJAVTbhUDihDImpUWymPFthQXgjrUknRdPLeBHZiTdxKNwVltSEFgoQMvVyyohXxkHnIjZxZyLgNYcUFXjLwtXbeHseMAfpadJsFfgddjivWejNyOSnfsjInpyFZavmmQdPqPgrikpJXMlThJhsBFPgbDJNHydqNwdlncDPEFIuHNkNOcFbOZTshmFZkiGxulHaJUEplAKfvvtkBlfhBPrpBcVxKAfcf",_saved_search_orderVM.getDescription());
    Assertions.assertEquals(df.parse("3/1/2015 5:27:20 PM"),_saved_search_orderVM.getLast_Used());
    Assertions.assertEquals(df.parse("4/2/2017 7:09:43 PM"),_saved_search_orderVM.getLast_Updated());
    Assertions.assertEquals(8456,_saved_search_orderVM.getTimes_Ran());
    Assertions.assertEquals(saved_search_order_lines,_saved_search_orderVM.getSaved_Search_Order_Lines());
  }
  @Test
  public void testSetUserSetsUser(){
    User _user = new User();
    _saved_search_orderVM.setUser(_user);
    Assertions.assertEquals(_user,_saved_search_orderVM.getUser());
  }
  @Test
  public void testSetSaved_Search_Order_LinesSetsSaved_Search_Order_Lines(){
    List<Saved_Search_Order_Line> _saved_search_order_lines = new ArrayList<Saved_Search_Order_Line>();
    _saved_search_orderVM.setSaved_Search_Order_Lines(_saved_search_order_lines);
    Assertions.assertEquals(_saved_search_order_lines,_saved_search_orderVM.getSaved_Search_Order_Lines());
  }

}

