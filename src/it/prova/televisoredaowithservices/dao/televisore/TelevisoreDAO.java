package it.prova.televisoredaowithservices.dao.televisore;

import java.util.Date;
import java.util.List;

import it.prova.televisoredaowithservices.dao.IBaseDAO;
import it.prova.televisoredaowithservices.model.Televisore;

public interface TelevisoreDAO extends IBaseDAO<Televisore>{
	public List<Televisore> findAllTelevisionMadeBetween(Date beforeDate, Date afterDate) throws Exception;
	public Televisore findTheBiggestTelevision() throws Exception;
	public List<String> findAllBrandsOfTelevionsMadeInTheLastSixMonth(String todayDate, String sixMonthFromToday) throws Exception;
}
