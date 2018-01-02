package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import bean.OffertaFormativaBean;
import bean.PresidenteBean;
import bean.StudenteBean;
import bean.TirocinioBean;
import connection.DriverManagerConnectionPool;

public class PresidenteModel {

	
public PresidenteBean doRetrieveByUsername(String username) throws SQLException{
		
		java.sql.Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM Presidente WHERE Username=?";
		PresidenteBean utente= new PresidenteBean();
		
		try {
			connection =  DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, username);
	
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				
				utente.setCF(rs.getString("CF"));
				utente.setNome(rs.getString("Nome"));
				utente.setCognome(rs.getString("Cognome"));
				utente.setUsername(rs.getString("Username"));
				utente.setPassword(rs.getString("Psw"));
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
	



public Collection<StudenteBean> trovaStudentiConRichiesta() throws SQLException{
	Connection connection =null;
	PreparedStatement preparedStatement =null;
	String matricola="";
	String presidente="";
	TirocinioBean tirocinio= new TirocinioBean();
	StudenteModel model= new StudenteModel();
	Collection<StudenteBean> studenti= new ArrayList<StudenteBean>();
	String selectSQL="SELECT * FROM tirocinio";
	
	 try{	
		connection = DriverManagerConnectionPool.getConnection();
		 preparedStatement= connection.prepareStatement(selectSQL);
		 ResultSet rs= preparedStatement.executeQuery();
			 
		 while(rs.next()) {
			 matricola=rs.getString("Studente");
			 presidente=rs.getString("Presidente");
			 
			 if(presidente==null) {
				 studenti.add(model.doRetrieveByMatricola(matricola));
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
	  
	return studenti;
	
}
}
