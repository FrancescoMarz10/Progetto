package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import bean.AziendaBean;
import bean.NotificaBean;
import bean.OffertaFormativaBean;
import connection.DriverManagerConnectionPool;

public class NotificaModel {


	public void aggiungiNotifica(String testo, String tipo, String ufficio, String tutorInterno, String tutorEsterno, String azienda, String studente) throws SQLException {
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
	
	

public NotificaBean doRetrieveByID(int ID) throws SQLException{
	
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





}
