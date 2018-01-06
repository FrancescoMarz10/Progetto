package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import org.xml.sax.SAXException;

import bean.AziendaBean;
import bean.NotificaBean;
import bean.OffertaFormativaBean;
import connection.DriverManagerConnectionPool;

public class NotificaModel {

		
	public ArrayList<NotificaBean> trovaNotificheStudente(String matricola) throws SQLException,IOException{
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		 ArrayList<NotificaBean> notifiche =new ArrayList<NotificaBean>();
		 
		 String selectSQL="SELECT * FROM Notifica WHERE Studente=?";
		 
		
		 try{
			 connection = DriverManagerConnectionPool.getConnection();
			 preparedStatement= connection.prepareStatement(selectSQL);
			 preparedStatement.setString(1, matricola);
			 ResultSet rs= preparedStatement.executeQuery();
			 
			 while(rs.next()){
				 NotificaBean bean= new NotificaBean();
				 
				 bean.setID(rs.getInt("ID"));
				 bean.setTesto(rs.getString("Testo"));
				 bean.setTipo(rs.getString("Tipo"));
				 bean.setUfficio(rs.getString("Ufficio"));
				 bean.setTutorInterno(rs.getString("TutorInterno"));
				 bean.setTutorEsterno(rs.getString("TutorEsterno"));
				 bean.setAzienda(rs.getString("Azienda"));
				 bean.setStudente(rs.getString("Studente"));
				 
				 if(!bean.getTipo().equals("Richiesta") && !bean.getTipo().equals("FirmaTE") && !bean.getTipo().equals("FirmaTI") && !bean.getTipo().equals("RichiestaUfficio")) {
					 	notifiche.add(bean);
				 }
			 }
			 
		 } 
		 finally{
		
			 try{
				 if(preparedStatement!=null)
					 preparedStatement.close();
			 }
			 finally{
				 DriverManagerConnectionPool.releaseConnection(connection);
			 }		 
		 }
		  
		return notifiche;
	}
	
	
	public void aggiornaStudente(int ID) throws SQLException,IOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL="UPDATE notifica SET Studente=? WHERE ID=?";
		
		try {
			
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, null);
			preparedStatement.setInt(2, ID);
		
			
			
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
	
	
	public void aggiornaAzienda(int ID) throws SQLException,  IOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL="UPDATE notifica SET Azienda=? WHERE ID=?";
		
		try {
			
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, null);
			preparedStatement.setInt(2, ID);
		
			
			
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
	
	
	public ArrayList<NotificaBean> trovaNotificheAzienda(String nome) throws SQLException, IOException{
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		 ArrayList<NotificaBean> notifiche =new ArrayList<NotificaBean>();
		 
		 String selectSQL="SELECT * FROM Notifica WHERE Azienda=?";
		 
		
		 try{
			 connection = DriverManagerConnectionPool.getConnection();
			 preparedStatement= connection.prepareStatement(selectSQL);
			 preparedStatement.setString(1, nome);
			 ResultSet rs= preparedStatement.executeQuery();
			 
			 while(rs.next()){
				 NotificaBean bean= new NotificaBean();
				 
				 bean.setID(rs.getInt("ID"));
				 bean.setTesto(rs.getString("Testo"));
				 bean.setTipo(rs.getString("Tipo"));
				 bean.setUfficio(rs.getString("Ufficio"));
				 bean.setTutorInterno(rs.getString("TutorInterno"));
				 bean.setTutorEsterno(rs.getString("TutorEsterno"));
				 bean.setAzienda(rs.getString("Azienda"));
				 bean.setStudente(rs.getString("Studente"));
				 
				 if(!bean.getTipo().equals("AccettaA") && !bean.getTipo().equals("RifiutaA") && !bean.getTipo().equals("FirmaTI") && !bean.getTipo().equals("FirmaTE")) {
					 	notifiche.add(bean);
				 }
				 
				
			 }
			 
		 } 
		 finally{
		
			 try{
				 if(preparedStatement!=null)
					 preparedStatement.close();
			 }
			 finally{
				 DriverManagerConnectionPool.releaseConnection(connection);
			 }		 
		 }
		  
		return notifiche;
	}
	
	
	public ArrayList<NotificaBean> trovaNotificheTE(String CF) throws SQLException, IOException{
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		 ArrayList<NotificaBean> notifiche =new ArrayList<NotificaBean>();
		 
		 String selectSQL="SELECT * FROM Notifica WHERE TutorEsterno=?";
		 
		
		 try{
			 connection = DriverManagerConnectionPool.getConnection();
			 preparedStatement= connection.prepareStatement(selectSQL);
			 preparedStatement.setString(1, CF);
			 ResultSet rs= preparedStatement.executeQuery();
			 
			 while(rs.next()){
				 NotificaBean bean= new NotificaBean();
				 
				 bean.setID(rs.getInt("ID"));
				 bean.setTesto(rs.getString("Testo"));
				 bean.setTipo(rs.getString("Tipo"));
				 bean.setUfficio(rs.getString("Ufficio"));
				 bean.setTutorInterno(rs.getString("TutorInterno"));
				 bean.setTutorEsterno(rs.getString("TutorEsterno"));
				 bean.setAzienda(rs.getString("Azienda"));
				 bean.setStudente(rs.getString("Studente"));
				 
				 if(bean.getTipo().equals("FirmaTE")) {
					 	notifiche.add(bean);
				 }
				 
				
			 }
			 
		 } 
		 finally{
		
			 try{
				 if(preparedStatement!=null)
					 preparedStatement.close();
			 }
			 finally{
				 DriverManagerConnectionPool.releaseConnection(connection);
			 }		 
		 }
		  
		return notifiche;
	}
	
	public ArrayList<NotificaBean> trovaNotifichePresidente() throws SQLException, IOException{
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		 ArrayList<NotificaBean> notifiche =new ArrayList<NotificaBean>();
		 
		 String selectSQL="SELECT * FROM Notifica WHERE Tipo=?";
		 
		
		 try{
			 connection = DriverManagerConnectionPool.getConnection();
			 preparedStatement= connection.prepareStatement(selectSQL);
			 preparedStatement.setString(1, "Presidente");
			 ResultSet rs= preparedStatement.executeQuery();
			 
			 while(rs.next()){
				 NotificaBean bean= new NotificaBean();
				 
				 bean.setID(rs.getInt("ID"));
				 bean.setTesto(rs.getString("Testo"));
				 bean.setTipo(rs.getString("Tipo"));
				 bean.setUfficio(rs.getString("Ufficio"));
				 bean.setTutorInterno(rs.getString("TutorInterno"));
				 bean.setTutorEsterno(rs.getString("TutorEsterno"));
				 bean.setAzienda(rs.getString("Azienda"));
				 bean.setStudente(rs.getString("Studente"));
				 
				 if(bean.getTipo().equals("Presidente")) {
					 	notifiche.add(bean);
				 }
				 
				
			 }
			 
		 } 
		 finally{
		
			 try{
				 if(preparedStatement!=null)
					 preparedStatement.close();
			 }
			 finally{
				 DriverManagerConnectionPool.releaseConnection(connection);
			 }		 
		 }
		  
		return notifiche;
	}

	public ArrayList<NotificaBean> trovaNotificheTI(String CF) throws SQLException,IOException{
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		 ArrayList<NotificaBean> notifiche =new ArrayList<NotificaBean>();
		 
		 String selectSQL="SELECT * FROM Notifica WHERE TutorInterno=?";
		 
		
		 try{
			 connection = DriverManagerConnectionPool.getConnection();
			 preparedStatement= connection.prepareStatement(selectSQL);
			 preparedStatement.setString(1, CF);
			 ResultSet rs= preparedStatement.executeQuery();
			 
			 while(rs.next()){
				 NotificaBean bean= new NotificaBean();
				 
				 bean.setID(rs.getInt("ID"));
				 bean.setTesto(rs.getString("Testo"));
				 bean.setTipo(rs.getString("Tipo"));
				 bean.setUfficio(rs.getString("Ufficio"));
				 bean.setTutorInterno(rs.getString("TutorInterno"));
				 bean.setTutorEsterno(rs.getString("TutorEsterno"));
				 bean.setAzienda(rs.getString("Azienda"));
				 bean.setStudente(rs.getString("Studente"));
				 
				 if(bean.getTipo().equals("FirmaTI")) {
					 	notifiche.add(bean);
				 }
				 
				
			 }
			 
		 } 
		 finally{
		
			 try{
				 if(preparedStatement!=null)
					 preparedStatement.close();
			 }
			 finally{
				 DriverManagerConnectionPool.releaseConnection(connection);
			 }		 
		 }
		  
		return notifiche;
	}
	
	
	public ArrayList<NotificaBean> trovaNotificheUfficio(String sigla) throws SQLException,IOException{
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		 ArrayList<NotificaBean> notifiche =new ArrayList<NotificaBean>();
		 
		 String selectSQL="SELECT * FROM Notifica WHERE ufficio=?";
		 
		
		 try{
			 connection = DriverManagerConnectionPool.getConnection();
			 preparedStatement= connection.prepareStatement(selectSQL);
			 preparedStatement.setString(1, sigla);
			 ResultSet rs= preparedStatement.executeQuery();
			 
			 while(rs.next()){
				 NotificaBean bean= new NotificaBean();
				 
				 bean.setID(rs.getInt("ID"));
				 bean.setTesto(rs.getString("Testo"));
				 bean.setTipo(rs.getString("Tipo"));
				 bean.setUfficio(rs.getString("Ufficio"));
				 bean.setTutorInterno(rs.getString("TutorInterno"));
				 bean.setTutorEsterno(rs.getString("TutorEsterno"));
				 bean.setAzienda(rs.getString("Azienda"));
				 bean.setStudente(rs.getString("Studente"));
				 
				 if(bean.getTipo().equals("RichiestaUfficio")) {
					 	notifiche.add(bean);
				 }
				 
				
			 }
			 
		 } 
		 finally{
		
			 try{
				 if(preparedStatement!=null)
					 preparedStatement.close();
			 }
			 finally{
				 DriverManagerConnectionPool.releaseConnection(connection);
			 }		 
		 }
		  
		return notifiche;
	}
	
	public void aggiungiNotifica(String testo, String tipo, String ufficio, String tutorInterno, String tutorEsterno, String azienda, String studente) throws SQLException, IOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int ID=0;
		String insertSQL="SELECT max(ID) FROM Notifica";
		
		try {
			 connection = DriverManagerConnectionPool.getConnection();
			 preparedStatement= connection.prepareStatement(insertSQL);
			
			 ResultSet rs= preparedStatement.executeQuery();
			 
			 if(rs.next()){
				 ID=rs.getInt("max(ID)");
			 }
			
			
			insertSQL = "INSERT INTO Notifica(ID,Testo,Tipo,ufficio,TutorInterno,TutorEsterno,Azienda,Studente) VALUES (?,?,?,?,?,?,?,?)";
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			ID++;
			preparedStatement.setInt(1, ID);
			preparedStatement.setString(2, testo);
			preparedStatement.setString(3, tipo);
			preparedStatement.setString(4, ufficio);
			preparedStatement.setString(5, tutorInterno);
			preparedStatement.setString(6, tutorEsterno);
			preparedStatement.setString(7, azienda);
			preparedStatement.setString(8, studente);
		
			
			preparedStatement.executeUpdate();
			connection.commit();
			
	}
	finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			 DriverManagerConnectionPool.releaseConnection(connection);
		}
	}
	
}
	
	

public NotificaBean doRetrieveByID(int ID) throws SQLException,  IOException{
	
	java.sql.Connection connection = null;
	PreparedStatement preparedStatement = null;
	String selectSQL = "SELECT * FROM Notifica WHERE ID=?";
	NotificaBean notifica= new NotificaBean();
	
	try {
		connection =  DriverManagerConnectionPool.getConnection();
		preparedStatement = (PreparedStatement) connection.prepareStatement(selectSQL);
		
		preparedStatement.setInt(1, ID);

		ResultSet rs = preparedStatement.executeQuery();
		if(rs.next()){
			
			notifica.setID(ID);
			notifica.setTesto(rs.getString("Testo"));
			notifica.setTipo(rs.getString("Tipo"));
			notifica.setUfficio(rs.getString("Ufficio"));
			notifica.setTutorInterno(rs.getString("TutorInterno"));
			notifica.setTutorEsterno(rs.getString("TutorEsterno"));
			notifica.setAzienda(rs.getString("Azienda"));
			notifica.setStudente(rs.getString("Studente"));
		
		}
	
		
	} finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			 DriverManagerConnectionPool.releaseConnection(connection);
		}
	}
	return notifica;
}



public void cancellaNotifica(int ID) throws SQLException,  IOException {

	java.sql.Connection connection = null;
	PreparedStatement preparedStatement = null;
	String selectSQL = "DELETE FROM Notifica WHERE ID=?";
	
	
	try {
		connection =  DriverManagerConnectionPool.getConnection();
		preparedStatement = (PreparedStatement) connection.prepareStatement(selectSQL);
		
		preparedStatement.setInt(1, ID);

	
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
