package it.prova.televisoredaowithservices.dao.televisore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.prova.televisoredaowithservices.dao.AbstractMySQLDAO;
import it.prova.televisoredaowithservices.model.Televisore;

public class TelevisoreDAOImpl extends AbstractMySQLDAO implements TelevisoreDAO{

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
		
	}
	
	@Override
	public List<Televisore> list() throws Exception {
		if(isNotActive())
			throw new RuntimeException("impossibile efffettuare operazioni sul DB, connessione non stabilita");
		List<Televisore> result = new ArrayList<>();
		Televisore temp;
		try (Statement Statement = connection.createStatement();
				ResultSet resultSet = Statement.executeQuery("select * from televisore")){
			while(resultSet.next()) {
				temp = new Televisore();
				temp.setId(resultSet.getLong("id"));
				temp.setMarca(resultSet.getString("marca"));
				temp.setModello(resultSet.getString("modello"));
				temp.setPollici(resultSet.getInt("pollici"));
				temp.setDataProduzione(resultSet.getDate("dataproduzione"));
				result.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Televisore get(Long idInput) throws Exception {
		if (isNotActive())
			throw new Exception("impossibile efffettuare operazioni sul DB, connessione non stabilita");

			if (idInput == null || idInput < 1)
				throw new Exception("operazione non riuscita, input non valido");

			Televisore result = null;
			try (PreparedStatement ps = connection.prepareStatement("select * from televisore where id=?")) {
				ps.setLong(1, idInput);
				try (ResultSet resultSet = ps.executeQuery()) {
					if (resultSet.next()) {
						result = new Televisore();
						result.setId(resultSet.getLong("id"));
						result.setMarca(resultSet.getString("marca"));
						result.setModello(resultSet.getString("modello"));
						result.setPollici(resultSet.getInt("pollici"));
						result.setDataProduzione(resultSet.getDate("dataproduzione"));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return result;			
	}

	@Override
	public int update(Televisore input) throws Exception {
		if (isNotActive())
			throw new Exception("impossibile efffettuare operazioni sul DB, connessione non stabilita");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("operazione non riuscita, input non valido");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"UPDATE televisore SET marca=?, modello=?, pollici=?, dataproduzione=? where id=?;")) {
			ps.setString(1, input.getMarca());
			ps.setString(2, input.getModello());
			ps.setInt(3, input.getPollici());
			ps.setDate(4, new java.sql.Date(input.getDataProduzione().getTime()));
			ps.setLong(5, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int insert(Televisore input) throws Exception {
		if (isNotActive())
			throw new Exception("impossibile efffettuare operazioni sul DB, connessione non stabilita");

		if (input == null)
			throw new Exception("operazione non riuscita, input non valido");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO televisore (marca, modello, pollici, dataproduzione) VALUES (?, ?, ?, ?);")) {
			ps.setString(1, input.getMarca());
			ps.setString(2, input.getModello());
			ps.setInt(3, input.getPollici());
			ps.setDate(4, new java.sql.Date(input.getDataProduzione().getTime()));
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Televisore input) throws Exception {
		if (isNotActive())
			throw new Exception("impossibile efffettuare operazioni sul DB, connessione non stabilita");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("operazione non riuscita, input non valido");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("DELETE FROM televisore WHERE ID=?")) {
			ps.setLong(1, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Televisore> findByExample(Televisore input) throws Exception {
		if (isNotActive())
			throw new Exception("impossibile efffettuare operazioni sul DB, connessione non stabilita");

		if (input == null)
			throw new Exception("operazione non riuscita, input non valido");

		ArrayList<Televisore> result = new ArrayList<Televisore>();

		String query = "select * from televisore where 1=1 ";
		if (input.getMarca() != null && !input.getMarca().isEmpty()) {
			query += " and marca like '" + input.getMarca() + "%' ";
		}
		if (input.getModello() != null && !input.getModello().isEmpty()) {
			query += " and modello like '" + input.getModello() + "%' ";
		}
		if (input.getPollici() > 0) {
			query += " and pollici = " + input.getPollici();
		}
		if (input.getDataProduzione() != null) {
			query += " and dataproduzione ='" + new java.sql.Date(input.getDataProduzione().getTime()) + "' ";
		}

		try (Statement ps = connection.createStatement()) {
			ResultSet resultSet = ps.executeQuery(query);

			while (resultSet.next()) {
				Televisore televisoreTemp = new Televisore();
				televisoreTemp.setId(resultSet.getLong("id"));
				televisoreTemp.setMarca(resultSet.getString("marca"));
				televisoreTemp.setModello(resultSet.getString("modello"));
				televisoreTemp.setPollici(resultSet.getInt("pollici"));
				televisoreTemp.setDataProduzione(resultSet.getDate("dataproduzione"));
				result.add(televisoreTemp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Televisore> findAllTelevisionMadeBetween(Date beforeDate, Date afterDate) throws Exception {
		if(isNotActive())
			throw new RuntimeException("impossibile efffettuare operazioni sul DB, connessione non stabilita");
		
		if (beforeDate == null || afterDate == null)
			throw new Exception("operazione non riuscita, input non valido");
		
		List<Televisore> result = new ArrayList<>();
		Televisore televisoreTemp;
		try (PreparedStatement preparedStatement = connection.prepareStatement("select * from televisore where dataproduzione between ? and ?")){
			preparedStatement.setDate(1, new java.sql.Date(beforeDate.getTime()));
			preparedStatement.setDate(2, new java.sql.Date(afterDate.getTime()));
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					televisoreTemp = new Televisore();
					televisoreTemp.setId(resultSet.getLong("id"));
					televisoreTemp.setMarca(resultSet.getString("marca"));
					televisoreTemp.setModello(resultSet.getString("modello"));
					televisoreTemp.setPollici(resultSet.getInt("pollici"));
					televisoreTemp.setDataProduzione(resultSet.getDate("dataproduzione"));
					result.add(televisoreTemp);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Televisore findTheBiggestTelevision() throws Exception {
		if(isNotActive())
			throw new RuntimeException("impossibile efffettuare operazioni sul DB, connessione non stabilita");
		
		Televisore piuGrande = null;
		
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from televisore where pollici = (select max(pollici) from televisore)")){
			if(resultSet.next()) {
				piuGrande = new Televisore();
				piuGrande.setId(resultSet.getLong("id"));
				piuGrande.setMarca(resultSet.getString("marca"));
				piuGrande.setModello(resultSet.getString("modello"));
				piuGrande.setPollici(resultSet.getInt("pollici"));
				piuGrande.setDataProduzione(resultSet.getDate("dataproduzione"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return piuGrande;
	}

	@Override
	public List<String> findAllBrandsOfTelevionsMadeInTheLastSixMonth(String todayDate, String sixMonthFromToday) throws Exception {
		if(isNotActive())
			throw new RuntimeException("impossibile efffettuare operazioni sul DB, connessione non stabilita");
		
		List<String> marche = new ArrayList<>();
		
		try (PreparedStatement preparedStatement = connection.prepareStatement("select marca from televisore where dataproduzione between ? and ?")){
			preparedStatement.setString(1, sixMonthFromToday);
			preparedStatement.setString(2, todayDate);
			try (ResultSet resultSet = preparedStatement.executeQuery()){
				while(resultSet.next()) 
					marche.add(resultSet.getString("marca"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return marche;
	}
	
}
