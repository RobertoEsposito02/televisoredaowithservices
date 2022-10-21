package it.prova.televisoredaowithservices.service.televisore;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.prova.televisoredaowithservices.connection.MyConnection;
import it.prova.televisoredaowithservices.dao.Constants;
import it.prova.televisoredaowithservices.dao.televisore.TelevisoreDAO;
import it.prova.televisoredaowithservices.model.Televisore;

public class TelevisoreServiceImpl implements TelevisoreService {

	private TelevisoreDAO televisoreDAO;
	
	@Override
	public void setTelevisoreDao(TelevisoreDAO televisoreDAO) {
		this.televisoreDAO = televisoreDAO;
	}

	@Override
	public List<Televisore> listAll() throws Exception {
		List<Televisore> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)){
			
			televisoreDAO.setConnection(connection);
			
			result = televisoreDAO.list();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Televisore findById(Long idInput) throws Exception {
		Televisore result = new Televisore();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)){
			
			televisoreDAO.setConnection(connection);
			
			result = televisoreDAO.get(televisoreDAO.list().get(0).getId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int aggiorna(Televisore input) throws Exception {
		if (input == null)
			throw new Exception("operazione non riuscita, input non valido");
		
		int result = 0;
		try(Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			televisoreDAO.setConnection(connection);

			result = televisoreDAO.update(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
		return result;
	}

	@Override
	public int inserisciNuovo(Televisore input) throws Exception {
		if (input == null)
			throw new Exception("operazione non riuscita, input non valido");
		
		int result = 0;
		try(Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			televisoreDAO.setConnection(connection);

			result = televisoreDAO.insert(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
		return result;
	}

	@Override
	public int rimuovi(Televisore input) throws Exception {
		if (input == null || input.getId() == null || input.getId() <1)
			throw new Exception("operazione non riuscita, input non valido");
		
		int result = 0;
		try(Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			televisoreDAO.setConnection(connection);

			result = televisoreDAO.delete(input);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Televisore> findByExample(Televisore input) throws Exception {
		List<Televisore> result = new ArrayList<>();
		try(Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			televisoreDAO.setConnection(connection);

			result = televisoreDAO.findByExample(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
		return result;
	}

	@Override
	public List<Televisore> voglioTuttiITelevisoriProdottiTra(Date beforeDate, Date afterDate) throws Exception {
		List<Televisore> result = new ArrayList<>();
		try(Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			televisoreDAO.setConnection(connection);

			result = televisoreDAO.findAllTelevisionMadeBetween(beforeDate,afterDate);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
		return result;
	}

	@Override
	public Televisore voglioIlTelevisorePiuGrande() throws Exception {
		Televisore result = new Televisore();
		try(Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			televisoreDAO.setConnection(connection);

			result = televisoreDAO.findTheBiggestTelevision();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
		return result;
	}

	@Override
	public List<String> voglioTutteLeMarcheDiTelevisoriProdottiNegliUltimiSeiMesi(String todayDate, String sixMonthFromToday) throws Exception {
		List<String> result = new ArrayList<>();
		try(Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			televisoreDAO.setConnection(connection);

			result = televisoreDAO.findAllBrandsOfTelevionsMadeInTheLastSixMonth(todayDate,sixMonthFromToday);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
		return result;
	}

}
