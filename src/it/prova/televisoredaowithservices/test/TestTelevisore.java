package it.prova.televisoredaowithservices.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import it.prova.televisoredaowithservices.model.Televisore;
import it.prova.televisoredaowithservices.service.MyServiceFactory;
import it.prova.televisoredaowithservices.service.televisore.TelevisoreService;

public class TestTelevisore {
	public static void main(String[] args) {
		TelevisoreService televisoreService = MyServiceFactory.getTelevisoreServiceImpl();
		try {

			System.out.println("size elenco prima insert: " + televisoreService.listAll().size());
			testInsertTelevisore(televisoreService);
			System.out.println("size elenco dopo insert: " + televisoreService.listAll().size());

			testRimozioneTelevisore(televisoreService);
			System.out.println("size elenco dopo d: " + televisoreService.listAll().size());

			testFindByexample(televisoreService);

			testAggiornamentoTelevisore(televisoreService);

			testVoglioTuttiITelevisoriProdottiTra(televisoreService);

			testVoglioLaTelevisionePiuGrande(televisoreService);
			
			testVoglioTutteLeMarcheDiTelevisoriProdottiNegliUltimiSeiMesi(televisoreService);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void testInsertTelevisore(TelevisoreService televisoreService) throws Exception {
		System.out.println(".......testInsertTelevisore inizio.............");

		televisoreService.inserisciNuovo(
				new Televisore("lg", "e-210", 38, new SimpleDateFormat("yyyy/MM/dd").parse("2022/08/09")));

		System.out.println(".......testInsertTelevisore PASSED.............");
	}

	private static void testRimozioneTelevisore(TelevisoreService televisoreService) throws Exception {
		System.out.println(".......testRimozioneTelevisore inizio.............");

		if (televisoreService.listAll().isEmpty())
			throw new RuntimeException("impossibile effettuare operazioni sul db, elenco vuoto");

		Long id = televisoreService.listAll().get(0).getId();
		Televisore televisoreDaRimuovere = televisoreService.findById(id);

		if (televisoreService.rimuovi(televisoreDaRimuovere) != 1)
			throw new RuntimeException("operazinoe non riuscita, delete FAILED");

		System.out.println(".......testRimozioneTelevisore PASSED.............");
	}

	private static void testAggiornamentoTelevisore(TelevisoreService televisoreService) throws Exception {
		System.out.println(".......testAggiornamentoTelevisore inizio.............");

		if (televisoreService.inserisciNuovo(
				new Televisore("hp", "901G", 41, new SimpleDateFormat("yyyy/MM/dd").parse("2022/09/09"))) != 1)
			throw new RuntimeException("inserimento non riuscito");

		Televisore televisoreAggiornato = televisoreService.findByExample(new Televisore("hp", "901G")).get(0);
		Long idTelevisoreAggiornato = televisoreAggiornato.getId();
		televisoreAggiornato.setMarca("samsung");


		if (televisoreService.aggiorna(televisoreAggiornato) != 1)
			throw new RuntimeException();

		System.out.println(".......testAggiornamentoTelevisore PASSED.............");
	}

	private static void testFindByexample(TelevisoreService televisoreService) throws Exception {
		System.out.println(".......testFindByexample inizio.............");

		Televisore televisoreExample = new Televisore("lenovo", "Y787", 41,
				new SimpleDateFormat("yyyy/MM/dd").parse("2022/09/09"));

		televisoreService.inserisciNuovo(televisoreExample);
		List<Televisore> result = televisoreService.findByExample(new Televisore("le", "y"));
		for (Televisore televisore : result) {
			System.out.println(televisore);
		}

		System.out.println(".......testFindByexample PASSED.............");
	}

	private static void testVoglioTuttiITelevisoriProdottiTra(TelevisoreService televisoreService) throws Exception {
		System.out.println(".......testVoglioTuttiITelevisoriProdottiTra inizio.............");

		Date beforeDate = new SimpleDateFormat("yyyy/MM/dd").parse("2010/01/01");
		Date afterDate = new SimpleDateFormat("yyyy/MM/dd").parse("2023/01/01");

		List<Televisore> result = televisoreService.voglioTuttiITelevisoriProdottiTra(beforeDate, afterDate);
		for (Televisore televisore : result) {
			System.out.println(televisore);
		}

		System.out.println(".......testVoglioTuttiITelevisoriProdottiTra PASSED.............");
	}

	private static void testVoglioLaTelevisionePiuGrande(TelevisoreService televisoreService) throws Exception {
		System.out.println(".......testVoglioLaTelevisionePiuGrande inizio.............");

		System.out.println(televisoreService.voglioIlTelevisorePiuGrande());

		System.out.println(".......testVoglioLaTelevisionePiuGrande PASSED.............");
	}

	private static void testVoglioTutteLeMarcheDiTelevisoriProdottiNegliUltimiSeiMesi(
			TelevisoreService televisoreService) throws Exception {
		System.out.println(".......testVoglioTutteLeMarcheDiTelevisoriProdottiNegliUltimiSeiMesi inizio.............");
		
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        String todayDate = new SimpleDateFormat("yyyy/MM/dd").format(date);
        String sixMonthFromToday = "2022/04/21";
        
        /* prova per ottenere 6 mesi prima di oggi sembra non funzionare
        String sixMonthFromToday = todayDate;
        String numeroGiornoASinistra = sixMonthFromToday.charAt(4) +"";
        String numeroGiornoADestra = sixMonthFromToday.charAt(5) +"";
        int numeroGiornoSinistraInt = Integer.parseInt(numeroGiornoASinistra);
        int numeroGiornoDestraInt = Integer.parseInt(numeroGiornoADestra);
        
        if(numeroGiornoSinistraInt > 0 && numeroGiornoDestraInt > 6) { 
        	numeroGiornoSinistraInt -=1;
        	numeroGiornoDestraInt -= 6;
        } else if (numeroGiornoSinistraInt == 0 && numeroGiornoDestraInt > 6)
        	numeroGiornoDestraInt -= 6;
        else {
			numeroGiornoSinistraInt = 1;
			numeroGiornoDestraInt = 12 - Math.abs(numeroGiornoDestraInt -6);
		}
        String sinistra = numeroGiornoASinistra +"";
        String destra = numeroGiornoDestraInt +"";

        
        sixMonthFromToday.replace(sixMonthFromToday.charAt(4), sinistra.charAt(0));
        sixMonthFromToday.replace(sixMonthFromToday.charAt(5), destra.charAt(0));
        */
        
        List<String> result = televisoreService.voglioTutteLeMarcheDiTelevisoriProdottiNegliUltimiSeiMesi(todayDate, sixMonthFromToday);
		for (String string : result) {
			System.out.println("marca: " + string);
		}
        
		System.out.println(".......testVoglioTutteLeMarcheDiTelevisoriProdottiNegliUltimiSeiMesi PASSED.............");
	}
}
