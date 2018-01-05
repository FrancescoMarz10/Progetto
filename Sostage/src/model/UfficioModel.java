package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import bean.OffertaFormativaBean;
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
	
	 Collection<StudenteBean> studentiRichieste=new ArrayList<StudenteBean>();
	 Collection<StudenteBean> studenti= new ArrayList<StudenteBean>();
	 StudenteModel model= new StudenteModel();
	 String selectSQL="SELECT * FROM Studente";
	 
	
	 try{
	
			
			 connection = DriverManagerConnectionPool.getConnection();
			 preparedStatement= connection.prepareStatement(selectSQL);
			 ResultSet rs= preparedStatement.executeQuery();
			 
			 while(rs.next()) {
				 String matricola=rs.getString("Matricola");
				 studenti.add(model.doRetrieveByMatricola(matricola));
			 }
			 
		
		
		selectSQL="SELECT * FROM Tirocinio WHERE Studente=?";
	 	
		for (StudenteBean studenteBean : studenti) {
		
			 connection = DriverManagerConnectionPool.getConnection();
			 preparedStatement= connection.prepareStatement(selectSQL);
			 preparedStatement.setString(1, studenteBean.getMatricola());
			 rs= preparedStatement.executeQuery();
			 
			 while(rs.next()) {
				 String azienda=rs.getString("Azienda");
				 String tutorInterno=rs.getString("TutorInterno");
				 String tutorEsterno=rs.getString("TutorEsterno");
				 String presidente=rs.getString("Presidente");
				
				 if(azienda!=null && tutorInterno!=null && tutorEsterno!=null && presidente!= null) {
					
					 studentiRichieste.add(studenteBean);

				 }
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
	  
	return studentiRichieste;

	}
	
}
