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
import bean.OffertaFormativaBean;
import bean.StudenteBean;
import bean.TirocinioBean;
import bean.TutorEsternoBean;
import connection.DriverManagerConnectionPool;

public class TutorEsternoModel {

	
public TutorEsternoBean doRetrieveByUsername(String username) throws SQLException, IOException{
		
		java.sql.Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM TutorEsterno WHERE Username=?";
		TutorEsternoBean utente= new TutorEsternoBean();
		
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


public TutorEsternoBean doRetrieveKey(String CF) throws SQLException, IOException{
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	String selectSQL = "SELECT * FROM TutorEsterno WHERE CF=?";
	TutorEsternoBean utente= new TutorEsternoBean();
	
	try {
		connection =  DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement(selectSQL);
		
		preparedStatement.setString(1, CF);

		ResultSet rs = preparedStatement.executeQuery();
		if(rs.next()){
			
			utente.setCF(rs.getString("CF"));
			utente.setCognome(rs.getString("Cognome"));
			utente.setNome(rs.getString("Nome"));
			utente.setPassword(rs.getString("Psw"));
			utente.setUsername(rs.getString("Username"));
		
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


public Collection<TutorEsternoBean> doRetrieveAll() throws SQLException,  IOException {
	Connection connection =null;
	PreparedStatement preparedStatement =null;
	
	 Collection<TutorEsternoBean> tutor =new ArrayList<TutorEsternoBean>();
	 
	 String selectSQL="SELECT * FROM tutorEsterno";
	 
	
	 try{
		 connection = DriverManagerConnectionPool.getConnection();
		 preparedStatement= connection.prepareStatement(selectSQL);
		
		 ResultSet rs= preparedStatement.executeQuery();
		 
		 while(rs.next()){
			 TutorEsternoBean bean= new TutorEsternoBean();
			 
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


public Collection<StudenteBean> trovaStudentiAssegnati(String tutorEsterno) throws SQLException, IOException {
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
			 
		
		
		selectSQL="SELECT * FROM Tirocinio WHERE Studente=? AND TutorEsterno=?";
	 	
		for (StudenteBean studenteBean : studenti) {
		
			 //connection = DriverManagerConnectionPool.getConnection();
			 preparedStatement= connection.prepareStatement(selectSQL);
			 preparedStatement.setString(1, studenteBean.getMatricola());
			 preparedStatement.setString(2, tutorEsterno);
			 rs= preparedStatement.executeQuery();
			 
			 while(rs.next()) {
				 String azienda=rs.getString("Azienda");
				
				 if(azienda!=null) {
					
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


public Collection<OffertaFormativaBean>  trovaOfferteTutor(String CF) throws SQLException, IOException {
	Connection connection =null;
	PreparedStatement preparedStatement =null;
	Collection<OffertaFormativaBean> offerte= new ArrayList<OffertaFormativaBean>();
	String selectSQL="SELECT * FROM offertaFormativa WHERE TutorEsterno=?";
	
	 try{	
		connection = DriverManagerConnectionPool.getConnection();
		 preparedStatement= connection.prepareStatement(selectSQL);
		 preparedStatement.setString(1, CF);
		 ResultSet rs= preparedStatement.executeQuery();
			 
		 while(rs.next()) {
			 OffertaFormativaBean bean= new OffertaFormativaBean();
			 
			 bean.setID(rs.getInt("ID"));
			 bean.setNome(rs.getString("Nome"));
			 bean.setSede(rs.getString("Sede"));
			 bean.setTema(rs.getString("Tema"));
			 bean.setObiettivi(rs.getString("Obiettivi"));
			 bean.setModalitāSvolgimento(rs.getString("ModalitaSvolgimento"));
			
			 
			 offerte.add(bean);
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
	  
	return offerte;
}


public Collection<StudenteBean> trovaStudentiConRichiesta(String CF) throws SQLException, IOException{
	Connection connection =null;
	PreparedStatement preparedStatement =null;
	
	 Collection<StudenteBean> studentiRichieste=new ArrayList<StudenteBean>();
	 ArrayList<OffertaFormativaBean> array=(ArrayList<OffertaFormativaBean>)trovaOfferteTutor(CF);
	 Collection<StudenteBean> studenti= new ArrayList<StudenteBean>();
	 StudenteModel model= new StudenteModel();
	 String selectSQL="SELECT * FROM Studente WHERE OffertaFormativa=?";
	 
	
	 try{
		for (OffertaFormativaBean offertaFormativaBean : array) {
			
			 connection = DriverManagerConnectionPool.getConnection();
			 preparedStatement= connection.prepareStatement(selectSQL);
			 preparedStatement.setInt(1, offertaFormativaBean.getID());
			 ResultSet rs= preparedStatement.executeQuery();
			 
			 while(rs.next()) {
				 String matricola=rs.getString("Matricola");
				 studenti.add(model.doRetrieveByMatricola(matricola));
			 }
			 
		}
		
		selectSQL="SELECT TutorEsterno,Azienda FROM Tirocinio WHERE Studente=?";
	 	
		for (StudenteBean studenteBean : studenti) {
		
			 //connection = DriverManagerConnectionPool.getConnection();
			 preparedStatement= connection.prepareStatement(selectSQL);
			 preparedStatement.setString(1, studenteBean.getMatricola());
			 ResultSet rs= preparedStatement.executeQuery();
			 
			 while(rs.next()) {
				 String tutorEsterno=rs.getString("TutorEsterno");
				 String azienda=rs.getString("Azienda");
				
				 if(tutorEsterno==null && azienda!=null) {
					
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

		

