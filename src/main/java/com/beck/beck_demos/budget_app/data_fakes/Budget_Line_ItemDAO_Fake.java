package com.beck.beck_demos.budget_app.data_fakes;
import com.beck.beck_demos.budget_app.iData.iBudget_Line_ItemDAO;
import com.beck.beck_demos.budget_app.data.Budget_Line_ItemDAO;
import com.beck.beck_demos.budget_app.models.Budget_Line_Item;
import com.beck.beck_demos.budget_app.models.Budget_Line_ItemVM;
import com.beck.beck_demos.budget_app.models.User;
import com.beck.beck_demos.budget_app.iData.iBudget_Line_ItemDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class Budget_Line_ItemDAO_Fake implements iBudget_Line_ItemDAO {
  private  List<Budget_Line_ItemVM> Budget_Line_ItemVMs;
  public Budget_Line_ItemDAO_Fake(){
    Budget_Line_ItemVMs = new ArrayList<>();
    Budget_Line_Item Budget_Line_Item0 = new Budget_Line_Item("yLhmvdKflpQXUhKJRPyCwnlbDYNkrTfDSXeK", "DXYkJliQuYIAHdneUjJXfbZkJDGYHrBEHpqb","FFFFFF", "OpYUAKmX", "ryXYiXNt", LocalDate.now(), 10.05, "eqlqGBIdHwNbnfaNTZluLFephFATCkFFlBNRhOqVPfXatKKgDJ", "muhtymIDfTJoqNOntofgucgPAUBULNbUJtrAdKtVyIuuVwBInG", "GhGahTWnPelJgXkTdWyqCZxTSlmTKORCBThd", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item1 = new Budget_Line_Item("evVBHqXgFUAdBuZPuVVYWrvmUGJQGkKJHQXo", "DXYkJliQuYIAHdneUjJXfbZkJDGYHrBEHpqb", "FFFFFF", "AiwiERmn","FFFFFF", LocalDate.now(), 22.02, "ykKqjdonujEJtcKTXMaCYWVvLTVMTiVxOMIlxYFOqDHuJpDYTB", "MTFtgXoYmmtSoPJIuUUIRtUQwZOpfDeiBBVectOTJXNIkOAiZq", "eGpudmOgJRGnIHHMLNGMmiDYjPkuiiCaSwyT", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item2 = new Budget_Line_Item("AemQtRHtipvsAaWFFvnbOLtQRVouxQFHXKIP", "DXYkJliQuYIAHdneUjJXfbZkJDGYHrBEHpqb", "FFFFFF", "rCUSKXWb","FFFFFF", LocalDate.now(), 33.35, "WyuvgcwACsVfSscIGFhBMPYZRuEYKHXfaZWrDMTOkuOSWmpwhW", "pkLfRAIISvJnCbbBXeZBOVBqnQOQrNuJCpvkpoEBcAhyMuCWQR", "DvrUAlfQvojtwnUYpGQBUpperrdlGAdwdBWE", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item3 = new Budget_Line_Item("WMYoLeFbsgNFNHHsJuKmPuWPgKXCZkjOEeKw", "DXYkJliQuYIAHdneUjJXfbZkJDGYHrBEHpqb", "FFFFFF", "aFgJWmHR", "FFFFFF",LocalDate.now(), 11.9, "uNgWaNQJBqvlPqQSTIYWuppRYeHUFEIlMdjWbknOUYpbffxyqi", "bInvmnACuHtkcUKCyOelgHVvTOWAroUcLFDnecDqwxZSmGqRaV", "tOEVAIEBvAIdQaWWtvcsBnMQEOtMTsvIcrcQ", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item4 = new Budget_Line_Item("LIuUmrgiVuSDSTSXlFKOpstlxJQTLquHuLUf", "jddlgVOBcnhVLBiILXGCFHctuNcVQgarjjFN", "FFFFFF", "avNEcaUI","FFFFFF", LocalDate.now(), 55.79, "jjJYpDANabmqANGiGGLghnqchRnFeMyyTjmnCsHUQBlYMCxLkA", "yVtbqJiCwjjmhBHocxRMVvCYRopfrVnObaTtNYqWYCnZdxFiXp", "LRrTxYZyntUZkxZNygpjwZmJxHtUnawPFaho", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item5 = new Budget_Line_Item("TdvyQYKDTXmLOrsxsvkYxpXJWPLpTKuEUVtm", "ORFrkAOrgMDKBAYHgwKbbhInooMniyttwGxc", "FFFFFF", "WcSamQiJ", "FFFFFF",LocalDate.now(), 45.86, "jjJYpDANabmqANGiGGLghnqchRnFeMyyTjmnCsHUQBlYMCxLkA", "tQOQsuxbgYMtNOHcNcnAxNJgpJePAqhrCxJAobKAAoSePISQfC", "CKPUcbXBcAYNTTKsEGXLZGYWlpqgsYRQZyLu", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item6 = new Budget_Line_Item("rOMKDGqfpNKLfLlSoCDjtsaadIIAURgHrxbh", "DLpfWxZUBCbqtdngpApNUBMxKxgaVkEondmy","FFFFFF", "SwtiALoF", "vMkGgCqf", LocalDate.now(), 37.06, "jjJYpDANabmqANGiGGLghnqchRnFeMyyTjmnCsHUQBlYMCxLkA", "YlCrprfbJEMxCleroErUKvPlGnGRokVSsTuOAZRVbHQvAMjWGb", "qaXMfYdQwDXDihdKKmsbGhIRkmlqrfqenKud", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item7 = new Budget_Line_Item("tZLUAOiDTgqGhclLDkiagWwpPexRsMrWgFeR","DLpfWxZUBCbqtdngpApNUBMxKxgaVkEondmy", "FFFFFF", "CYBhwJWl", "NBvkuHwR", LocalDate.now(), 63.83, "jjJYpDANabmqANGiGGLghnqchRnFeMyyTjmnCsHUQBlYMCxLkA", "NDsjLLCxDFLiNfEDqtmPyNOdvAGlhcNdjFNiQjrJsMhXIhoPxs", "EQPqayIJpUKMWpTBuqYwYeUTlBqwnbsYUHYA", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item8 = new Budget_Line_Item("qwCSmJknXstRotuwhsJrbwWJHdGkGtiBAuIa","DLpfWxZUBCbqtdngpApNUBMxKxgaVkEondmy", "FFFFFF", "Sjujpsry", "edKnvRZH", LocalDate.now(), 58.7, "kLfvEDPfXEDYurvQZBYENtuuVYAAQMiEtXqqOKWYwYdxmOMkUV", "OyOSeSqoNMGRsCdknwugnGqtxNfPKmioOMqOCdqsLsMFUUESmQ", "rUUWxGYYbmNcqUxvoBNCywiJXGlpHdupPaLc", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item9 = new Budget_Line_Item("lfoWCloNWErXeMaAgbZJRmatIUtJKtMUybXl", "DLpfWxZUBCbqtdngpApNUBMxKxgaVkEondmy","FFFFFF", "ESMyJLmY", "gVHFIeuS", LocalDate.now(), 21d, "XKchjsPmjQrTLPVHSKiaMxZDAxEoKqEmcpyfNpjbudZpJdeIoK", "OyOSeSqoNMGRsCdknwugnGqtxNfPKmioOMqOCdqsLsMFUUESmQ", "ikYSPYgIgvrpeDodKnsYgUfpcWgUwSDBpUKH", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item10 = new Budget_Line_Item("hISAGsPEKmjoQqnjdnonhdcCuTgNXxqCNUTY","ugNLXeCrlZhuDwuWwUsmCRWdDBMpmGOGpmvx", "FFFFFF", "PnfnJNpV", "NCIvIdjk", LocalDate.now(), 16.16, "hotHbauOqfnNWNfPJhpxeVkjJqJGUMAGbAeUrERACFfSARwDYJ", "OyOSeSqoNMGRsCdknwugnGqtxNfPKmioOMqOCdqsLsMFUUESmQ", "BrNKJhGoTPfXcZKqFDObQiYOfxLrxKStdtdn", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item11 = new Budget_Line_Item("lWfsZPbgRRGaQQhNLaeffmjvtGHlVFMmgADU","rybOnHjyGiSgZvACTddssehJCVQAbOINmqhF", "FFFFFF", "CgtTBdWw", "gVWboNZt", LocalDate.now(), 52.66, "FKWRJJUOvdPqOIQLVytYlAhTDfiJviRAfMBSURpdrGBIUajtTJ", "OyOSeSqoNMGRsCdknwugnGqtxNfPKmioOMqOCdqsLsMFUUESmQ", "THjpQrmrFWiriLVGHYXoKHRhXKjVEVPjVKqn", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item12 = new Budget_Line_Item("oXTBYfIRQBXwEpkfIEDIqmVLnpbSrIeGqpLy","GhZIKjTKJfJZkRjwITmpMASTrwcWWpuKissi", "FFFFFF", "xtlgfosD", "tVbQClZV", LocalDate.now(), 16.74, "OTbpDZUrETAgZQxbBFUEocjwsIDWFsQURqgsVpmIOVrngtODky", "EmfCOgLFypbhcKcLWMQlSYWQEgVRYDRkMlCYMDwkIYDcdFtOSx", "STFqFBuSixqPhVAeYySfSYmAOgZKqdyTUXpr", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item13 = new Budget_Line_Item("XdJdLqxqmJJSBKuwNqZbDFEkrIvAvHdUnqkf","jUgoJDhGFhRIMmsJVFnTCbvOCkinkfPjxJRO", "FFFFFF", "IerJAcgG", "dGxTOsGr", LocalDate.now(), 17.72, "DuVOuMNnKXufUdFUoWXWOqQJwOJTGcgdLNrDGmegvZMeRkRruJ", "EZDoWxkYqlQekauqwRKqKZZHnIImFWaaNreofaSZVJsZmOHNHf", "STFqFBuSixqPhVAeYySfSYmAOgZKqdyTUXpr", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item14 = new Budget_Line_Item("PhifsDKSDLHedKXGBATAueZqrUPAPsodClfy","nrpovWOmDMCLoDCvOoAHfZaekdvsfYuTXJZv", "FFFFFF", "SMmxbQvK", "mrBFjLtW", LocalDate.now(), 19.27, "XnHqOesSnTgqNeZwkumvMAvWypMebYFKoSMHdXiGDaEcqmCXGa", "UrbfkpGYmJtsuYlcnnVDxiuhYgaFTknkKttTmjUdvwCMBvMQfr", "STFqFBuSixqPhVAeYySfSYmAOgZKqdyTUXpr", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item15 = new Budget_Line_Item("lvhomjIiAXpDYrYClsXjTnhrglVRweBbbvHJ","hGVKvfQBbsHuDVSiatKBpPucaWFnevqCxeyk", "FFFFFF", "mbykayCy", "NOhyMHxf", LocalDate.now(), 56.86, "NwwPrEkTwnRLQPQZuURiFawLPHnGGKMiSCFiUKvePTgaYiMEFf", "cFkvOTLAHlZRJQkmOgWxIJYgcFekNAYYkaxkeRFLvneUQiTpWh", "STFqFBuSixqPhVAeYySfSYmAOgZKqdyTUXpr", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item16 = new Budget_Line_Item("warlvvTrQTCZIsRFFcRkwoHTJpZWhGVauXOq","aOslBrXilCuwbslLVnSSVQupRrxGdfYVepvw", "FFFFFF", "jtYRwedA", "bbsKuYLd", LocalDate.now(), 13.19, "rNDNViHAnRLHkiOsLvZIinLNHSXDWSjMGfHnYXRqqeqyyVPMjg", "nnBIVqEfiQTkQtYgGGAhYfesQApGDDhoIicHRnaANKOwPRJoag", "frKYQDlmoUVRyZNHWuBhgQVAmTyrvFetHDlN", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item17 = new Budget_Line_Item("oQIxLsvtChXmvXPgerVtKYISpQArkAPGovvK","aOslBrXilCuwbslLVnSSVQupRrxGdfYVepvw", "FFFFFF", "oSInFlXC", "uhJxxieq", LocalDate.now(), 46.94, "NgRKhvLgaBdYWunURUbCaawrHDHIXxqhkkoMlQHcMKYvOQbpUV", "DmkrKDtoUPPZskhhGsqpHSqevwKNUilxZZlKWmQyFXfvldrkJg", "RAaSQNIUiDqMYyyLUcFeeKBMDZHyyuKcWjWL", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item18 = new Budget_Line_Item("vXBWphGMdHeBurgnWjJeLEtUaXovgvhCCaTt","aOslBrXilCuwbslLVnSSVQupRrxGdfYVepvw", "FFFFFF", "UJGPoKTL", "NEkjBuQM", LocalDate.now(), 66.86, "ovJAUyuOjAVKQruLgHkUaBrngmYoFqVRUDQNfvlQhmkGofkDab", "rArunewALyEsNXCJgdQbKkRjlFgxjgLtukeKAsfEijLfnagnhI", "TRdWCLMNCUsJLWwMHCBRvHdElcUWiTyDqXIb", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item19 = new Budget_Line_Item("UPCSttHdhwmGUyXEaauvcDlfGxQUDwbveNAQ","aOslBrXilCuwbslLVnSSVQupRrxGdfYVepvw", "FFFFFF", "XWhxFKkx", "BohVyQqU", LocalDate.now(), 57.8, "aPmdxbtcyOVCJvTWGMnhjObfvKKkHKCoMfrlstjXsGBfFZZwTn", "nNRyTjgTSswSVVaIPUjjmmhOAiotluTFsiRVmvdBcpImpmhlDT", "XGBxPqewywnJlFXkfCLyrAFYFmxUetFcnrQx", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item20 = new Budget_Line_Item("pjEVkPxZyMZejDvpLijRHQpAgZLCluDptXjP","vApgZFZicmEpmXNRVoofLaMXFxvUabEgdhVR", "FFFFFF", "odGVovjo", "imxxXqHK", LocalDate.now(), 21.08, "WVLHJxKDkrydRtEIdcyhgtMkOkbZItNcXUdhNMOkIoelsjpmvh", "MwWXMECulhYpOqWiRAwoIjvYWPEHPWmajlYRatvcvFZkUCOpxC", "glqTRfPFxWkGBMMdjEUAOJNgIHfxVnMJlbcI", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item21 = new Budget_Line_Item("MqcwLiRlbKOdHrayAwsrdBUdyqqWbvDuouBK","ebraTjkkNabaitLcSaxMDfxprxQLhqCCLOAb", "FFFFFF", "SnJaTYcV", "xnAvdjbv", LocalDate.now(), 64.03, "WVLHJxKDkrydRtEIdcyhgtMkOkbZItNcXUdhNMOkIoelsjpmvh", "fhbjOKDSMxSXhtXSdRcPGhgEiLqPLPgYgFdUAORHdLpGTdTESd", "RGCLXMyehOkFRLavPqwUbLsfylSJlVNwLFYm", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item22 = new Budget_Line_Item("mAbBKXicqRkNNRUulUrTANpfrVRHbPJKLoNb","DMfDRGmTkNkAdfwmKkQICFXNWZrvOjISEnMg", "FFFFFF", "DhrDAqlG", "XivdxmDc", LocalDate.now(), 67.04, "WVLHJxKDkrydRtEIdcyhgtMkOkbZItNcXUdhNMOkIoelsjpmvh", "uIGIgcQyFaQBIAeJJBsbkIaWlaNcKPbnDBQKlcqcoemWqSYcsO", "SqFlLbHrUOPjPlkxEjJoAQADWeEATvLPIlis", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item23 = new Budget_Line_Item("HsLrlgXqokovDrBRDGmCrksxOlfBNPlrKqet","DLpfWxZUBCbqtdngpApNUBMxKxgaVkEondmy","FFFFFF", "SRXVnkXp", "HwPgLUkN", LocalDate.now(), 10.39, "WVLHJxKDkrydRtEIdcyhgtMkOkbZItNcXUdhNMOkIoelsjpmvh", "dUQeKoOKGDWRgBsRqHkIjWioNwOphNunIbdWGaXJcfegBhUihs", "XpTHZXhUkdaSMGIbPMHTNuvyYknGLATHIRaQ", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item24 = new Budget_Line_Item("vvFVandNJWWqDZWRFMtcsCpYYGFLbswdnCDD","DLpfWxZUBCbqtdngpApNUBMxKxgaVkEondmy", "FFFFFF", "ucZUeCfN", "YFQAAatV", LocalDate.now(), 55.09, "WqcMrlyNSMffHfDSlSOHXZZJpqPRcZUlofodvLMBmNgiFkagyW", "gUbmNERNbXHAftnLVSsxAQiepgJQfRlFGKwTxuCtatXdlklOuC", "BgdsqtppYVaGjGdMlkFhedDcWIgYZanLBTIU", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item25 = new Budget_Line_Item("eeaUJXufuoBAoOhnXxoltilqvfovXBNwlaRO","DLpfWxZUBCbqtdngpApNUBMxKxgaVkEondmy", "FFFFFF", "KMolZubV", "xITiJwQZ", LocalDate.now(), 69.15, "GsPmOjWEvUCNUnfIjJUEqZUZpfkGVVuuhVFOYMbFvvxnmdRfUe", "gUbmNERNbXHAftnLVSsxAQiepgJQfRlFGKwTxuCtatXdlklOuC", "wayHRsRwFCjorPUXrqxEjQYZrwQdUtOAFneE", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item26 = new Budget_Line_Item("RkMhOaBrFIkqVucOOcrmVIwovNgWvyrHLmqM","DLpfWxZUBCbqtdngpApNUBMxKxgaVkEondmy", "FFFFFF", "PrHbCXYB", "IkTALknn", LocalDate.now(), 11.66, "BhNDwCrPnLkRWZtgUvVVTFKUgdCrXZPDIKuNyEmkoaPecfHLsx", "gUbmNERNbXHAftnLVSsxAQiepgJQfRlFGKwTxuCtatXdlklOuC", "syCdcMlZrPUAWnrhGUWPlVOeQRexuDpLsrlv", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item27 = new Budget_Line_Item("iSgolbIjWTRamsvLnvVuKhxYFSFSvuerZOEd","DLpfWxZUBCbqtdngpApNUBMxKxgaVkEondmy", "FFFFFF", "IEHhGIGP", "HwUDaxKt", LocalDate.now(), 28.92, "inglWeoMrQtTpJifdOBUutunJAuTsYSchjRqhhfZXwWPXbFjhZ", "gUbmNERNbXHAftnLVSsxAQiepgJQfRlFGKwTxuCtatXdlklOuC", "GAuaBuSidSfOCXpHscSOJksqXNaJyXCSXboO", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item28 = new Budget_Line_Item("BPuoNJaOAmmVZZqqIOXNAQfYUEdRnVAKmeRi","DLpfWxZUBCbqtdngpApNUBMxKxgaVkEondmy", "FFFFFF", "tkGXLTXU", "MVARjimf", LocalDate.now(), 26.62, "yOvFEQYOgTasHhDRTkosNZKvZFhOsXsxYuMYPnSiJSqfNiVLdb", "SyjbjdWGSyFukBcgsMVSDPuRxvyEfVjMgWJcPucSPrEFktFAbL", "KogRrqJdjcgkKSOMiEpVSeFCrDVFTJNPScKk", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item29 = new Budget_Line_Item("CfJOsNUqAXwcDCKLXorLFVHAUHYnyJuvMLtN","PDWsQGmGrsNXMXnhGpxQKLBpDyrTJrEDtXPZ", "FFFFFF", "eiFHGkxH", "dSPOQBZE", LocalDate.now(), 25.01, "ynJotNGsJDZChYgbFrpeyeLOXoXKrgIlKxFVkDTLFIopvnqeUF", "DuBthqjSJMToQQmCYbsdTMSDcBnNsjefXKcOHxLgYAPrrWRaLY", "KogRrqJdjcgkKSOMiEpVSeFCrDVFTJNPScKk", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item30 = new Budget_Line_Item("PiJmsbvmRdhIfnSmjJWuRHHDpVYCHPUXvQGF","tAoCUgKwxbBiCLUPXmOrCgeKbdMRmuVfVgiK", "FFFFFF", "ZwiFVvcs", "YeXXfImV", LocalDate.now(), 53.43, "ppxbBibntTaONyfExosQPNrAwwkBcTVZplDCWitrNZnOyxUVpf", "mQyLoXMbduYKVXAYLilbfNnnyKGXSkrEvZceqlVjZGePYblIUL", "KogRrqJdjcgkKSOMiEpVSeFCrDVFTJNPScKk", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item31 = new Budget_Line_Item("IFZSDxcqryMvmgtbaiPxXxrOvdYjsbvBLlGJ","tYNHtFTKifMYOSUqfilYDBxTKnbkKGQxIhsR", "FFFFFF", "fmTpEDvu", "oYrCkYVs", LocalDate.now(), 63.5, "TLJlWFfjKCsXWPxIrHUHuEOXNkkNkrBxKxacPGefmeiqVNIfaZ", "fUYySAuNYhVsliTPVWCAcFfQkhMYtRfbyQUeWqZyiwLaTqBWms", "KogRrqJdjcgkKSOMiEpVSeFCrDVFTJNPScKk", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item32 = new Budget_Line_Item("xIlWwdEWXgJBqogayNbdxFkclsQxfCMZncrj","nWiTJYydybAsHjXpKBHYFqFvBbwokLLaMJUc", "FFFFFF", "FoMAixNg", "aRtBvHXY", LocalDate.now(), 41.19, "xHlIfVdoGvPSykBQRWJTxdBitmLydRdbKilUaucWOKGLlwSCnm", "dfjonyfjnavjEmaZBQOHnWuNjtnKyPUurZRXlRcOANqDZHHvIJ", "pyQagUJdEJgjhAGaqmcmqCpJmaUsAWmPjfJh", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item33 = new Budget_Line_Item("rBxSDHNkbbPabegStkWuVIEjWDUnrMwJABDj","nWiTJYydybAsHjXpKBHYFqFvBbwokLLaMJUc", "FFFFFF", "HugGhNrT", "nCycAZrN", LocalDate.now(), 29.88, "bHePKduyiCopNjkrcwjeoWVRwWSuFQLImoyEZmofWFMZpxrlVg", "fuINXxDcrQjosXQIqiyXMiDSAlZyCJJwyuXyoIWTgLBElklHvU", "QhfivTJvQyjWDBrCrQghOPCNHNxFEUlQEFfB", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item34 = new Budget_Line_Item("hImTKjCXcnFKpMewFxvLBVLTkrnehpoSbANG","nWiTJYydybAsHjXpKBHYFqFvBbwokLLaMJUc", "FFFFFF", "UCOXyQDk", "yNGYSlwk", LocalDate.now(), 15.87, "xVdeiydsvcWuPAraWKPmrarDgnUYLRUckZhrAHiNtYygnlvGle", "eXwtYoouqYmeIOHqehpkqjFHnXwdImbjcUhfqfTHEewklAmrKS", "XNPyKtLBIbcDtpgYCqIyiOYCAWjMBEGckObG", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item35 = new Budget_Line_Item("lCMJrLInNxnKyyEGZJQJJOBXruqRUAZZyrwd","nWiTJYydybAsHjXpKBHYFqFvBbwokLLaMJUc", "FFFFFF", "IWiAnucq", "OFcLMAcr", LocalDate.now(), 50.75, "mjALRdsZIAZGnBgkhJnYpHKCnFLJkFtBLDpCaHsbGIigZjORMM", "XjiDGYukKeJaPLSgXbEpsdrCsUwAEZgKWZeBoHtCfJlxBtbVFK", "GbBuNmwaPSkiLQPuTJrRfQPYuenHuPvbjCnh", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item36 = new Budget_Line_Item("GeRTXKZiYZWhYvxXEsyZbOxubQqiZZADCjRe","BbakypcVTVSblGTjPyYTfheTlEeeenkNMahQ", "FFFFFF", "wZdXhhMu", "RCNHBDRU", LocalDate.now(), 32.08, "FFCjHuOQVdwTCuLpZfirnwpPsuaYhFKIBRRUYpUYppvvJUfNvK", "jeFVLXCmoUAewjBipcbPNvrLoVMrwyQLgCiyQiIQiXXLVuZHGZ", "wyZoBnndQaEeImWJKhwBorCsCJCFREFflBYl", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item37 = new Budget_Line_Item("CurEvmrkiRLYsGSxXdLpVRCJanpsoCKGNJVL","fNsNEXiiHCmxPDtoJQRewwMqaZZFXkhrweje", "FFFFFF", "XGcShMaG", "jHaAQukr", LocalDate.now(), 42.83, "FFCjHuOQVdwTCuLpZfirnwpPsuaYhFKIBRRUYpUYppvvJUfNvK", "KkotTdoyUAxSUwdGqPBtrFSIYIhfvGDvxuRgvRvsLIQTFhWJNu", "CNvZWWBSZUSjxysKrECyOmapceEouWjUmpwL", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item38 = new Budget_Line_Item("KStRsAIvsOgFqlRwcVKIvxBHTTKDVvXSKvCV","WHaLMQoHlcSqsnchLRIYYHcyaAwPafrCvwLc", "FFFFFF", "rIebKXSK", "KmnLVxwl", LocalDate.now(), 26.39, "FFCjHuOQVdwTCuLpZfirnwpPsuaYhFKIBRRUYpUYppvvJUfNvK", "ZovqXFSIpNiPoRENJDKhuKggBHUjSMVjUauWGCoZiaOlXaChnB", "IGutFwSlwHrlfGYPjvSCLHKBojgDqKQccEUg", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item39 = new Budget_Line_Item("EERnioDWQltuhcFsPhKJDBDcOrdGgRkIjfUP","wdhqXACNKMpLwjyBlwvkdrMidrOpiPigDQCi", "FFFFFF", "dGEKeQbn", "AEXJFSkI", LocalDate.now(), 57.39, "FFCjHuOQVdwTCuLpZfirnwpPsuaYhFKIBRRUYpUYppvvJUfNvK", "RxXNSdhbYBVZuoeETKWubHOaoTigRbtbfKqmDsillTqrOUcWWS", "txSPxtidriYnKoCNXrEKMTLvCcrdqUOsUrmy", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item40 = new Budget_Line_Item("FXyiPxHeaiifTaquwFhXbaqCneIlZnMsKtis","bkcinDXrYrNKoPjlJfHCrRtISoBZCBgCfPIg", "FFFFFF", "CQAqcRnZ", "lBRobwTg", LocalDate.now(), 65.49, "HnlcZTQdqBBhbUskmieETUjCyMggJDqygIaxESftatQcpHIgVX", "McjCsHDWIgpFsvGwuvhVdrRqeSXPpVPtoWLXxiPRibUlvtpkPN", "xNjULcMkGVkqhpXRLEpCtmPPJcZFWRnuQqFy", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item41 = new Budget_Line_Item("tvlnrTnDZOpuTDHfQUstMalFEMgrquaMOggQ","NVAPDoHgUAhmGHKxXtUGnZxypbsCWyCvXfYa", "FFFFFF", "rkEbSgck", "qrgrKool", LocalDate.now(), 57.54, "lVZigpBdBLHRVFkBUoMGrteFZHWoyRwhqUWJyVTmLafFeTjtjl", "McjCsHDWIgpFsvGwuvhVdrRqeSXPpVPtoWLXxiPRibUlvtpkPN", "CtJcjUrUIZuUTxynmguOWvMrpEXSdIWfHdUC", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item42 = new Budget_Line_Item("bOvwrmTlfRqmwpqQsggJSssvRejcxoDhLhSj","AKOeFbleytXBmHtnHmJFyLUkfqXdJEdTDTiu", "FFFFFF", "mtPoCevQ", "AEtLCLtY", LocalDate.now(), 54.42, "eqXQNZXZqnlJJJAiIZrYPkWKUgsXbyEyripVkdkYbvbdLOEVhR", "McjCsHDWIgpFsvGwuvhVdrRqeSXPpVPtoWLXxiPRibUlvtpkPN", "mbZLmJQKTWoGAhnxmyFciQnFwkIINaxpHkoI", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item43 = new Budget_Line_Item("AZliUPxJEjuxULRBGsNHKDIdvHySUUXNwRWZ","npXRJNBIjpsijSpKkUGOOubMfeTXINRoGjgI", "FFFFFF", "GTPHXhsI", "tldACPRh", LocalDate.now(), 44.94, "OtLGbOLPbHpNiDlriXGwAHaQaFxlHihMdOheGXnEBFRIrwcegA", "McjCsHDWIgpFsvGwuvhVdrRqeSXPpVPtoWLXxiPRibUlvtpkPN", "XIAFoENcySsxBEOHiIUHZiDhUAFtWepdCIIm", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item44 = new Budget_Line_Item("oCnOVYdcpSaKDWyCedySPgbeTqCERmgCKsMU","mhfuyUkVMputasHsmhIdiJpYJnjEoWJnnSQe", "FFFFFF", "yaUbvirw", "sKrNUUea", LocalDate.now(), 52.85, "jkaqOymJbHTbsiQqNLdvYYhiuavVCPXbyHVedLFDmNQyoMkKwO", "pgLFEJRjJcaYYsfLWjFTKRYmolKioQmOVaHMpbiTgBMdGWNDfU", "cQDSArVAhFSkSDIhbgCZXBGyuApjVhlCDkXn", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item45 = new Budget_Line_Item("FQeKFEKsbqccEGmfEhBGCjbAmowYWMjOVWCB","qvgvxOubSONKUWsokWdekcWODdavnPHZLnCC", "FFFFFF", "rfstZFTp", "VBLVqYCN", LocalDate.now(), 25.38, "uDEUqitetVJKVNFIbsPCXRvJPJNCnnUpJPlBJpwnHqFZWeIfTe", "gbFbnTMwTHTFDSNPqirFVZQiPdydDYPLBPQGKhoAXbAfVRxasb", "cQDSArVAhFSkSDIhbgCZXBGyuApjVhlCDkXn", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item46 = new Budget_Line_Item("qBdCCNKachoPpYsHcOgDhyVxLPMUZBIVwxdH","WwUTEgZTEUCrMVSpoPKyGEIcVcIaNusPmaop", "FFFFFF", "scaPucCX", "bavsmpTx", LocalDate.now(), 17.45, "HWbLCOhQuYpeFLttCMhFRfKdsRdGaPepVYSKutHuFkfEWvfRCt", "UyZUjjyKdJJOIeiEToTmCOeVdbrHveVHdglYgHXkMnSeTlcKBJ", "cQDSArVAhFSkSDIhbgCZXBGyuApjVhlCDkXn", LocalDate.now(), LocalDate.now());
    Budget_Line_Item Budget_Line_Item47 = new Budget_Line_Item("CSrmuCStHLVkpZRjIqygFlmFLMfRJaVZJZrp","bAmWUhCdwTyYiofcxeyRfwrrSAYoxjdbndLr", "FFFFFF", "uorToMvF", "rWHkLDPM", LocalDate.now(), 69.69, "ojXeEdngIlfYBlyurhcgTUnhIvWnKjAOHVlDGXpuCJFotLHVCp", "VIobLdlpoQMFmrhUpokyyXvgdaveCRImywySysVNJVDXfMFfZs", "cQDSArVAhFSkSDIhbgCZXBGyuApjVhlCDkXn", LocalDate.now(), LocalDate.now());;
    Budget_Line_ItemVM Budget_Line_ItemVM0= new Budget_Line_ItemVM(Budget_Line_Item0);
    Budget_Line_ItemVM Budget_Line_ItemVM1= new Budget_Line_ItemVM(Budget_Line_Item1);
    Budget_Line_ItemVM Budget_Line_ItemVM2= new Budget_Line_ItemVM(Budget_Line_Item2);
    Budget_Line_ItemVM Budget_Line_ItemVM3= new Budget_Line_ItemVM(Budget_Line_Item3);
    Budget_Line_ItemVM Budget_Line_ItemVM4= new Budget_Line_ItemVM(Budget_Line_Item4);
    Budget_Line_ItemVM Budget_Line_ItemVM5= new Budget_Line_ItemVM(Budget_Line_Item5);
    Budget_Line_ItemVM Budget_Line_ItemVM6= new Budget_Line_ItemVM(Budget_Line_Item6);
    Budget_Line_ItemVM Budget_Line_ItemVM7= new Budget_Line_ItemVM(Budget_Line_Item7);
    Budget_Line_ItemVM Budget_Line_ItemVM8= new Budget_Line_ItemVM(Budget_Line_Item8);
    Budget_Line_ItemVM Budget_Line_ItemVM9= new Budget_Line_ItemVM(Budget_Line_Item9);
    Budget_Line_ItemVM Budget_Line_ItemVM10= new Budget_Line_ItemVM(Budget_Line_Item10);
    Budget_Line_ItemVM Budget_Line_ItemVM11= new Budget_Line_ItemVM(Budget_Line_Item11);
    Budget_Line_ItemVM Budget_Line_ItemVM12= new Budget_Line_ItemVM(Budget_Line_Item12);
    Budget_Line_ItemVM Budget_Line_ItemVM13= new Budget_Line_ItemVM(Budget_Line_Item13);
    Budget_Line_ItemVM Budget_Line_ItemVM14= new Budget_Line_ItemVM(Budget_Line_Item14);
    Budget_Line_ItemVM Budget_Line_ItemVM15= new Budget_Line_ItemVM(Budget_Line_Item15);
    Budget_Line_ItemVM Budget_Line_ItemVM16= new Budget_Line_ItemVM(Budget_Line_Item16);
    Budget_Line_ItemVM Budget_Line_ItemVM17= new Budget_Line_ItemVM(Budget_Line_Item17);
    Budget_Line_ItemVM Budget_Line_ItemVM18= new Budget_Line_ItemVM(Budget_Line_Item18);
    Budget_Line_ItemVM Budget_Line_ItemVM19= new Budget_Line_ItemVM(Budget_Line_Item19);
    Budget_Line_ItemVM Budget_Line_ItemVM20= new Budget_Line_ItemVM(Budget_Line_Item20);
    Budget_Line_ItemVM Budget_Line_ItemVM21= new Budget_Line_ItemVM(Budget_Line_Item21);
    Budget_Line_ItemVM Budget_Line_ItemVM22= new Budget_Line_ItemVM(Budget_Line_Item22);
    Budget_Line_ItemVM Budget_Line_ItemVM23= new Budget_Line_ItemVM(Budget_Line_Item23);
    Budget_Line_ItemVM Budget_Line_ItemVM24= new Budget_Line_ItemVM(Budget_Line_Item24);
    Budget_Line_ItemVM Budget_Line_ItemVM25= new Budget_Line_ItemVM(Budget_Line_Item25);
    Budget_Line_ItemVM Budget_Line_ItemVM26= new Budget_Line_ItemVM(Budget_Line_Item26);
    Budget_Line_ItemVM Budget_Line_ItemVM27= new Budget_Line_ItemVM(Budget_Line_Item27);
    Budget_Line_ItemVM Budget_Line_ItemVM28= new Budget_Line_ItemVM(Budget_Line_Item28);
    Budget_Line_ItemVM Budget_Line_ItemVM29= new Budget_Line_ItemVM(Budget_Line_Item29);
    Budget_Line_ItemVM Budget_Line_ItemVM30= new Budget_Line_ItemVM(Budget_Line_Item30);
    Budget_Line_ItemVM Budget_Line_ItemVM31= new Budget_Line_ItemVM(Budget_Line_Item31);
    Budget_Line_ItemVM Budget_Line_ItemVM32= new Budget_Line_ItemVM(Budget_Line_Item32);
    Budget_Line_ItemVM Budget_Line_ItemVM33= new Budget_Line_ItemVM(Budget_Line_Item33);
    Budget_Line_ItemVM Budget_Line_ItemVM34= new Budget_Line_ItemVM(Budget_Line_Item34);
    Budget_Line_ItemVM Budget_Line_ItemVM35= new Budget_Line_ItemVM(Budget_Line_Item35);
    Budget_Line_ItemVM Budget_Line_ItemVM36= new Budget_Line_ItemVM(Budget_Line_Item36);
    Budget_Line_ItemVM Budget_Line_ItemVM37= new Budget_Line_ItemVM(Budget_Line_Item37);
    Budget_Line_ItemVM Budget_Line_ItemVM38= new Budget_Line_ItemVM(Budget_Line_Item38);
    Budget_Line_ItemVM Budget_Line_ItemVM39= new Budget_Line_ItemVM(Budget_Line_Item39);
    Budget_Line_ItemVM Budget_Line_ItemVM40= new Budget_Line_ItemVM(Budget_Line_Item40);
    Budget_Line_ItemVM Budget_Line_ItemVM41= new Budget_Line_ItemVM(Budget_Line_Item41);
    Budget_Line_ItemVM Budget_Line_ItemVM42= new Budget_Line_ItemVM(Budget_Line_Item42);
    Budget_Line_ItemVM Budget_Line_ItemVM43= new Budget_Line_ItemVM(Budget_Line_Item43);
    Budget_Line_ItemVM Budget_Line_ItemVM44= new Budget_Line_ItemVM(Budget_Line_Item44);
    Budget_Line_ItemVM Budget_Line_ItemVM45= new Budget_Line_ItemVM(Budget_Line_Item45);
    Budget_Line_ItemVM Budget_Line_ItemVM46= new Budget_Line_ItemVM(Budget_Line_Item46);
    Budget_Line_ItemVM Budget_Line_ItemVM47= new Budget_Line_ItemVM(Budget_Line_Item47);

    Budget_Line_ItemVMs.add(Budget_Line_ItemVM0);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM1);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM2);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM3);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM4);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM5);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM6);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM7);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM8);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM9);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM10);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM11);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM12);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM13);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM14);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM15);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM16);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM17);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM18);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM19);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM20);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM21);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM22);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM23);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM24);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM25);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM26);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM27);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM28);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM29);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM30);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM31);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM32);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM33);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM34);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM35);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM36);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM37);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM38);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM39);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM40);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM41);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM42);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM43);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM44);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM45);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM46);
    Budget_Line_ItemVMs.add(Budget_Line_ItemVM47);
    
    Collections.sort(Budget_Line_ItemVMs);
  }

  @Override
  public List<String> getDistinctcolorForDropdown() {
    return List.of("4E79A7","F28E2B","E15759","76B7B2","59A14F","EDC948",
        "B07AA1","FF9DA7","9C755F","BAB0AC","2F4B7C","D45087");
  }

  @Override
  public List<String> getDistinctbudget_line_statusForDropdown() {
    return List.of("Planned","Pending","Completed","Cancelled","Recurring");
  }

  @Override
  public List<String> getDistinctbudget_line_typeForDropdown() {
    return List.of("Income","Expense","Transfer","Debt_Payment","Savings");
  }

  @Override
  public List<Budget_Line_ItemVM> getLineItemsByBudget(String budget_ID) {
    List<Budget_Line_ItemVM> results = new ArrayList<>();
    for (Budget_Line_ItemVM budget_line_item : Budget_Line_ItemVMs){
      if (budget_line_item.getbudget_id().equals(budget_ID)){
        results.add(budget_line_item);
      }
    }
    return results;
  }

  @Override
  public int add(Budget_Line_Item budgetLineItem) throws SQLException {
    if (duplicateKey(budgetLineItem)){
      return 0;
    }
    if (exceptionKey(budgetLineItem)){
      throw new SQLException("error");
    }
    int size = Budget_Line_ItemVMs.size();
    Budget_Line_ItemVM budget_line_item_VM = new Budget_Line_ItemVM(budgetLineItem);
    Budget_Line_ItemVMs.add(budget_line_item_VM);
    int newsize = Budget_Line_ItemVMs.size();
    budgetLineItem.setBudget_Line_Item_id("beadbd90-28a3-4005-a8df-9772238ead4b");
    return newsize-size;
  }

  @Override
  public int update(Budget_Line_Item oldbudgetLineItem, Budget_Line_Item newbudgetLineItem) throws SQLException {
    int location =-1;
    if (duplicateKey(newbudgetLineItem)){
      return 0;
    }
    if (exceptionKey(newbudgetLineItem)){
      throw new SQLException("error");
    }
    for (int i=0;i<Budget_Line_ItemVMs.size();i++){
      if (Budget_Line_ItemVMs.get(i).getBudget_Line_Item_id().equals(oldbudgetLineItem.getBudget_Line_Item_id())){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException();
    }
    Budget_Line_ItemVM updated = new Budget_Line_ItemVM(oldbudgetLineItem);
    Budget_Line_ItemVMs.set(location,updated);
    return 1;
  }

  @Override
  public Integer deleteBudget_Line_Item(String budgetLineItemID) throws SQLException {
    int size = Budget_Line_ItemVMs.size();
    int location =-1;
    for (int i=0;i<Budget_Line_ItemVMs.size();i++){
      if (Budget_Line_ItemVMs.get(i).getBudget_Line_Item_id().equals(budgetLineItemID)){
        location =i;
        break;
      }
    }
    if (location==-1){
      throw new SQLException("Unable To Find budget_line_item.");
    }
    Budget_Line_ItemVMs.remove(location);
    int newsize = Budget_Line_ItemVMs.size();
    return size-newsize;
  }

  private boolean duplicateKey(Budget_Line_Item _budget_line_item){
    return _budget_line_item.getname().contains("DUPLICATE");
  }
  private boolean exceptionKey(Budget_Line_Item _budget_line_item){
    return _budget_line_item.getname().contains("EXCEPTION");
  }
}
