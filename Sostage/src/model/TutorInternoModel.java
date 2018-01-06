package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.xml.sax.SAXException;

import bean.OffertaFormativaBean;
import bean.StudenteBean;
import bean.TutorEsternoBean;
import bean.TutorInternoBean;
import connection.DriverManagerConnectionPool;

public class TutorInternoModel {
	
	
public TutorInternoBean doRetrieveByUsername(String username) throws SQLException,  IOException{
		
		java.sql.Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM TutorInterno WHERE Username=?";
		TutorInternoBean utente= new TutorInternoBean();
		
		try {
			connection =  DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, username);
	
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				
				utente.setCF(rs.getString("CF"));
				utente.setCognome(rs.getString("Cognome"));
				utente.setNome(rs.getString("Nome"));
				utente.setPassword(rs.getString("Psw"));
				utente.setUsername(username);
			
			}
		
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				
			}
		}
		return utente;
	}




public Collection<StudenteBean> trovaStudentiAssegnati(String tutorInterno) throws SQLException, IOException {
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
			 
		
		
		selectSQL="SELECT * FROM Tirocinio WHERE Studente=? AND TutorInterno=?";
	 	
		for (StudenteBean studenteBean : studenti) {
		
			// connection = DriverManagerConnectionPool.getConnection();
			 preparedStatement= connection.prepareStatement(selectSQL);
			 preparedStatement.setString(1, studenteBean.getMatricola());
			 preparedStatement.setString(2, tutorInterno);
			 rs= preparedStatement.executeQuery();
			 
			 while(rs.next()) {
				 String azienda=rs.getString("Azienda");
				 String tutorEsterno= rs.getString("TutorEsterno");
				
				 if(azienda!=null && tutorEsterno!=null) {
					
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

public Collection<TutorInternoBean> doRetrieveAll() throws SQLException,  IOException {
	Connection connection =null;
	PreparedStatement preparedStatement =null;
	
	 Collection<TutorInternoBean> tutor =new ArrayList<TutorInternoBean>();
	 
	 String selectSQL="SELECT * FROM tutorInterno";
	 
	
	 try{
		 connection = DriverManagerConnectionPool.getConnection();
		 preparedStatement= connection.prepareStatement(selectSQL);
		
		 ResultSet rs= preparedStatement.executeQuery();
		 
		 while(rs.next()){
			 TutorInternoBean bean= new TutorInternoBean();
			 
			 bean.setNome(rs.getString("Nome"));
			 bean.setCF(rs.getString("CF"));
			 bean.setCognome(rs.getString("Cognome"));
			 bean.setUsername(rs.getString("Username"));
			 bean.setPassword(rs.getString("Psw"));
			 
			 tutor.add(bean);
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
	  
	return tutor;
}


public Collection<StudenteBean> trovaStudentiConRichiesta(String CF) throws SQLException,  IOException{
	Connection connection =null;
	PreparedStatement preparedStatement =null;
	
	 Collection<StudenteBean> studentiRichieste=new ArrayList<StudenteBean>();
	 Collection<StudenteBean> studenti= new ArrayList<StudenteBean>();
	 StudenteModel model= new StudenteModel();
	 String selectSQL="SELECT * FROM Studente WHERE TutorInterno=?";
	 
	
	 try{
			
			 connection = DriverManagerConnectionPool.getConnection();
			 preparedStatement= connection.prepareStatement(selectSQL);
			 preparedStatement.setString(1, CF);
			 ResultSet rs= preparedStatement.executeQuery();
			 
			 while(rs.next()) {
				 String matricola=rs.getString("Matricola");
				 studenti.add(model.doRetrieveByMatricola(matricola));
			 }

		
		selectSQL="SELECT TutorInterno,TutorEsterno FROM Tirocinio WHERE Studente=?";
	 	
		for (StudenteBean studenteBean : studenti) {
		
			// connection = DriverManagerConnectionPool.getConnection();
			 preparedStatement= connection.prepareStatement(selectSQL);
			 preparedStatement.setString(1, studenteBean.getMatricola());
			 rs= preparedStatement.executeQuery();
			 
			 while(rs.next()) {
				 String tutorInterno=rs.getString("TutorInterno");
				 String tutorEsterno=rs.getString("TutorEsterno");
				
				 if(tutorInterno==null && tutorEsterno!=null) {
					
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
