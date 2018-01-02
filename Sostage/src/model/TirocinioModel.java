package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.StudenteBean;
import bean.TirocinioBean;
import bean.UtenteBean;
import connection.DriverManagerConnectionPool;

public class TirocinioModel {

	
	
	public int doSave(TirocinioBean tirocinio) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int codice=0;
		String presidente="";
		String insertSQL="SELECT MAX(Codice) FROM Tirocinio";
		
		try {
			
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				
				codice=rs.getInt("MAX(Codice)")+1;
	
			}
			
			
			insertSQL = "INSERT INTO tirocinio (Codice,Materia,Periodo,Luogo) VALUES (?, ?, ?, ?)";            
			
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setInt(1, codice);
			preparedStatement.setString(2, tirocinio.getMateria());
			preparedStatement.setString(3, tirocinio.getPeriodo());
			preparedStatement.setString(4, tirocinio.getLuogo());
			
			
			preparedStatement.executeUpdate();
	
			connection.commit();
			
			
			
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
			return codice;
	}
	
	
	public void aggiornaStudente(String matricola, int codice) throws SQLException {
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	String insertSQL="UPDATE tirocinio SET Studente=? WHERE Codice=?";
	
	try {
		
		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement(insertSQL);
		preparedStatement.setString(1, matricola);
		preparedStatement.setInt(2, codice);
	
		
		
		preparedStatement.executeUpdate();

		connection.commit();
		
		
		
		
	} finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}

}
	
	public void aggiornaTutorEsterno(String CF, int codice) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL="UPDATE tirocinio SET TutorEsterno=? WHERE Codice=?";
		
		try {
			
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, CF);
			preparedStatement.setInt(2, codice);
		
			
			
			preparedStatement.executeUpdate();

			connection.commit();
			
			
			
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

	}

	
	public void aggiornaTutorInterno(String CF, int codice) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL="UPDATE tirocinio SET TutorInterno=? WHERE Codice=?";
		
		try {
			
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, CF);
			preparedStatement.setInt(2, codice);
		
			
			
			preparedStatement.executeUpdate();

			connection.commit();
			
			
			
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

	}

	
	public void aggiornaDocumento(String nome, int codice) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL="UPDATE tirocinio SET Documento=? WHERE Codice=?";
		
		try {
			
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, nome);
			preparedStatement.setInt(2, codice);
		
			
			
			preparedStatement.executeUpdate();

			connection.commit();
			
			
			
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

	}
		
	public void aggiornaAzienda(String nomeAzienda, int codice) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL="UPDATE tirocinio SET Azienda=? WHERE Codice=?";
		
		try {
			
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, nomeAzienda);
			preparedStatement.setInt(2, codice);
		
			
			
			preparedStatement.executeUpdate();

			connection.commit();
			
			
			
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

	}
	
	public void aggiornaPresidente(String CF, int codice) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL="UPDATE tirocinio SET Presidente=? WHERE Codice=?";
		
		try {
			
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, CF);
			preparedStatement.setInt(2, codice);
		
			
			
			preparedStatement.executeUpdate();

			connection.commit();
			
			
			
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

	}
		
	
public TirocinioBean doRetrieveByDocument(String nome) throws SQLException{
		
		java.sql.Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM Tirocinio WHERE Documento=?";
		TirocinioBean tirocinio= new TirocinioBean();
		
		try {
			connection =  DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, nome);
	
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				tirocinio.setCodice(rs.getInt("Codice"));
				tirocinio.setMateria(rs.getString("Materia"));
				tirocinio.setPeriodo(rs.getString("Periodo"));
				tirocinio.setLuogo(rs.getString("Luogo"));
				tirocinio.setTutorInterno(rs.getString("TutorInterno"));
				tirocinio.setTutorEsterno(rs.getString("TutorEsterno"));
				tirocinio.setStudente(rs.getString("Studente"));
				tirocinio.setAzienda(rs.getString("Azienda"));
				tirocinio.setPresidente(rs.getString("Presidente"));
				tirocinio.setDocumento(rs.getString("Documento"));
				
			}
		
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return tirocinio;
	}
	
public void cancellaTirocinio(int Codice) throws SQLException {

	java.sql.Connection connection = null;
	PreparedStatement preparedStatement = null;
	String selectSQL = "DELETE FROM Tirocinio WHERE Codice=?";
	
	
	try {
		connection =  DriverManagerConnectionPool.getConnection();
		preparedStatement = (PreparedStatement) connection.prepareStatement(selectSQL);
		
		preparedStatement.setInt(1, Codice);

	
		preparedStatement.executeUpdate();

		connection.commit();
	
		
	} finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}
	
	
}

}
