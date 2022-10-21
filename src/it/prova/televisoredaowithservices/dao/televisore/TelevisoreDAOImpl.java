package it.prova.televisoredaowithservices.dao.televisore;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import it.prova.televisoredaowithservices.dao.AbstractMySQLDAO;
import it.prova.televisoredaowithservices.model.Televisore;

public class TelevisoreDAOImpl extends AbstractMySQLDAO implements TelevisoreDAO{

	@Override
	public List<Televisore> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Televisore get(Long idInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Televisore> findByExample(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConnection(Connection connection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Televisore> findAllTelevisionMadeBetween(Date beforeDate, Date afterDate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Televisore findTheBiggestTelevision() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> findAllBrandsOfTelevionsMadeInTheLastSixMonth() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
