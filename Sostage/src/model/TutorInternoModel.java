package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import bean.OffertaFormativaBean;
import bean.StudenteBean;
import bean.TutorEsternoBean;
import bean.TutorInternoBean;
import connection.DriverManagerConnectionPool;

public class TutorInternoModel {
	
	
public TutorInternoBean doRetrieveByUsername(String username) throws SQLException{
		
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
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return utente;
	}




public Collection<StudenteBean> trovaStudentiAssegnati(String tutorInterno) throws SQLException {
	Connection connection =null;
	PreparedStatement preparedStatement =null;
	
	 Collection<StudenteBean> studenti =new ArrayList<StudenteBean>();
	 
	 String selectSQL="SELECT Studente FROM tirocinio WHERE TutorInterno=?";
	 
	
	 try{
		 connection = DriverManagerConnectionPool.getConnection();
		 preparedStatement= connection.prepareStatement(selectSQL);
		 preparedStatement.setString(1, tutorInterno);
		 ResultSet rs= preparedStatement.executeQuery();
		 
		 while(rs.next()){
			 String matricola=rs.getString("Studente");
			 
			 selectSQL="SELECT * FROM Studente WHERE Matricola=?";
			 
			 connection = DriverManagerConnectionPool.getConnection();
			 preparedStatement= connection.prepareStatement(selectSQL);
			 
			 preparedStatement.setString(1, matricola);
			 rs= preparedStatement.executeQuery();
			 
			 while(rs.next()){
				 StudenteBean bean= new StudenteBean();
				 
				 bean.setMatricola(matricola);
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

public Collection<TutorInternoBean> doRetrieveAll() throws SQLException {
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


public Collection<StudenteBean> trovaStudentiConRichiesta(String CF) throws SQLException{
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

		
		selectSQL="SELECT TutorInterno FROM Tirocinio WHERE Studente=?";
	 	
		for (StudenteBean studenteBean : studenti) {
		
			 connection = DriverManagerConnectionPool.getConnection();
			 preparedStatement= connection.prepareStatement(selectSQL);
			 preparedStatement.setString(1, studenteBean.getMatricola());
			 rs= preparedStatement.executeQuery();
			 
			 while(rs.next()) {
				 String tutorInterno=rs.getString("TutorInterno");
			
				 if(tutorInterno==null) {
					
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
