package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import bean.AziendaBean;
import bean.GestoreBean;
import bean.StudenteBean;
import bean.UtenteBean;
import connection.DriverManagerConnectionPool;

public class StudenteModel {
	

	
	
	public StudenteBean doRetrieveByUsername(String username) throws SQLException{
		
		java.sql.Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM Studente WHERE Username=?";
		StudenteBean utente= new StudenteBean();
		
		try {
			connection =  DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, username);
	
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				
				utente.setMatricola(rs.getString("Matricola"));
				utente.setCognome(rs.getString("Cognome"));
				utente.setNome(rs.getString("Nome"));
				utente.setPsw(rs.getString("Psw"));
				utente.setUsername(username);
				utente.setTutorInterno(rs.getString("TutorInterno"));
				utente.setOffertaFormativa(rs.getInt("OffertaFormativa"));
			}
		
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return utente;
	}
	
	/*____________________________________________________________________________________________________________*/
	
	public void doSave(StudenteBean studente, String mail) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		UtenteBean bean = new UtenteBean();
		String CF="";
		String insertSQL="SELECT CF FROM Gestore WHERE Username=?";
		
		try {
			
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, "admin");
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				
				CF=rs.getString("CF");
			}
		
			
			insertSQL = "INSERT INTO utente (Username, Psw, Mail, Ruolo, Gestore) VALUES (?, ?, ?, ?, ?)";            
			
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setString(1, studente.getUsername());
			preparedStatement.setString(2, studente.getPsw());
			preparedStatement.setString(3, mail);
			preparedStatement.setString(4, "Studente");
			preparedStatement.setString(5, CF);
		
			preparedStatement.executeUpdate();

			
			insertSQL = "INSERT INTO Studente (Matricola,Nome,Cognome,Username,Psw) VALUES (?, ?, ?, ?, ?)";

			
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, studente.getMatricola());
			preparedStatement.setString(2, studente.getNome());
			preparedStatement.setString(3, studente.getCognome());
			preparedStatement.setString(4, studente.getUsername());
			preparedStatement.setString(5, studente.getPsw());
		
			
		
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
	
//----------------------------------------------------------------------------------------------------------
	
	public boolean alreadyExists(StudenteBean bean) throws SQLException{
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query="SELECT * FROM Studente WHERE Username= ?";
	
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bean.getUsername());
			
			ResultSet rs = preparedStatement.executeQuery();
		
			if(!rs.next()){
				return true;
			}
			
			}
			catch(Exception e){
				System.out.println("Errore connessione");
			}
			 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
		return false;
	}
	

	

	
}
