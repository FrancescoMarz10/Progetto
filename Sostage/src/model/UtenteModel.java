package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.corba.se.pept.transport.Connection;

import bean.StudenteBean;
import bean.UtenteBean;
import connection.DriverManagerConnectionPool;

public class UtenteModel {
	

	public String checkUser(String username, String password) throws SQLException{
		
		java.sql.Connection connection = null;
		PreparedStatement preparedStatement = null;
		String ruolo="";
		UtenteBean utente=new UtenteBean();
		System.out.println(username+password);
		String query="SELECT * FROM utente WHERE Username=? AND Psw=?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				
				ruolo="utente";
				System.out.println("Trovato =utente");
//				utente.setUsername(rs.getString("Username"));
//	
//				utente.setPsw(rs.getString("Psw"));
//				
//				System.out.println("trovato");
			
			}
			else {
				System.out.println("cerco gestore");
				String newquery="SELECT * FROM Gestore WHERE Username=? AND Psw=?";
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(newquery);
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, password);
				
				ResultSet rs1 = preparedStatement.executeQuery();
				System.out.println("eseguits");
				if(rs1.next()) {
					
					ruolo="gestore";
					System.out.println("Trovato= gestore");
//					utente.setUsername(rs.getString("Username"));
	//	
//					utente.setPsw(rs.getString("Psw"));
//					
//					System.out.println("trovato");
				
				}
			}
			
			preparedStatement.close();
			
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
		
		return ruolo;
	}
	
	
//------------------------------------------------------------------------------------------------------------
	

	public UtenteBean doRetrieveByUsername(String username) throws SQLException{
		
		java.sql.Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM utente WHERE Username=?";
		UtenteBean utente= new UtenteBean();
		
		try {
			connection =  DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, username);
	
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				utente.setUsername(rs.getString("Username"));
				utente.setPsw(rs.getString("Psw"));
				utente.setMail(rs.getString("Mail"));
				utente.setGestore(rs.getString("Gestore"));
				utente.setRuolo(rs.getString("Ruolo"));
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
	
//-----------------------------------------------------------------------------------------------------------------------------
	
	public ArrayList<String> doRetrieveAllMails() throws SQLException{
		java.sql.Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT Mail FROM utente";
		ArrayList<String> mail= new ArrayList<String>();
		
		try {
			connection =  DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				mail.add(rs.getString("Mail"));
			}
		
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return mail;
	}
	

	public ArrayList<UtenteBean> doRetrieveAll() throws SQLException{
		java.sql.Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM utente";
		ArrayList<UtenteBean> utenti= new ArrayList<UtenteBean>();
		
		try {
			connection =  DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				UtenteBean bean= new UtenteBean();
				
				bean.setGestore(rs.getString("Gestore"));
				bean.setMail(rs.getString("Mail"));
				bean.setPsw(rs.getString("Psw"));
				bean.setRuolo(rs.getString("Ruolo"));
				bean.setUsername(rs.getString("Username"));
				
				utenti.add(bean);
			}
		
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return utenti;
	}
	
	
	
	public ArrayList<String> doRetrieveDistinctRoles() throws SQLException{
		java.sql.Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT DISTINCT(Ruolo) FROM utente";
		ArrayList<String> ruoli= new ArrayList<String>();
		
		try {
			connection =  DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				String ruolo= rs.getString("Ruolo");
				
				ruoli.add(ruolo);
			}
		
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return ruoli;
	}
	
}
