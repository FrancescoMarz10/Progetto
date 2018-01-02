package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import bean.StudenteBean;
import bean.TutorInternoBean;
import bean.UfficioBean;
import connection.DriverManagerConnectionPool;

public class UfficioModel {

	
public UfficioBean doRetrieveByUsername(String username) throws SQLException{
		
		java.sql.Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM ufficio WHERE Username=?";
		UfficioBean utente= new UfficioBean();
		
		try {
			connection =  DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, username);
	
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				
				utente.setSigla(rs.getString("Sigla"));
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
	


public Collection<StudenteBean> trovaTirocinanti() throws SQLException{
	Connection connection =null;
	PreparedStatement preparedStatement =null;
	
	 Collection<StudenteBean> studenti=new ArrayList<StudenteBean>();
	 
	 String selectSQL="SELECT * FROM tirocinio";
	 
	
	 try{
		 connection = DriverManagerConnectionPool.getConnection();
		 preparedStatement= connection.prepareStatement(selectSQL);
		 ResultSet rs= preparedStatement.executeQuery();
		 
		 while(rs.next()){
			
			 String studente=rs.getString("Studente");
			 
			 selectSQL="SELECT * FROM Studente WHERE Matricola=?";
			 connection = DriverManagerConnectionPool.getConnection();
			 preparedStatement= connection.prepareStatement(selectSQL);
			 preparedStatement.setString(1, studente);
			 rs= preparedStatement.executeQuery();
			 
			 while(rs.next()) {
				 StudenteBean bean= new StudenteBean();
				 
				 bean.setMatricola(rs.getString("Matricola"));
				 bean.setNome(rs.getString("Nome"));
				 bean.setCognome(rs.getString("Cognome"));
				 bean.setUsername(rs.getString("Username"));
				 bean.setPsw(rs.getString("Psw"));
				 bean.setTutorInterno(rs.getString("TutorInterno"));
				 bean.setOffertaFormativa(rs.getInt("OffertaFormativa"));
				 
				 
				 studenti.add(bean);
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
