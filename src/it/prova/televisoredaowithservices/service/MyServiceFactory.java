package it.prova.televisoredaowithservices.service;

import it.prova.televisoredaowithservices.dao.televisore.TelevisoreDAOImpl;
import it.prova.televisoredaowithservices.service.televisore.TelevisoreService;
import it.prova.televisoredaowithservices.service.televisore.TelevisoreServiceImpl;

public class MyServiceFactory {
	
	public static TelevisoreService getTelevisoreServiceImpl() {
		TelevisoreService televisoreService = new TelevisoreServiceImpl();
		televisoreService.setTelevisoreDao(new TelevisoreDAOImpl());
		return televisoreService;
	}

}
