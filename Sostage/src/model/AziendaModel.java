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
import connection.DriverManagerConnectionPool;

public class AziendaModel {

	
	public AziendaBean doRetrieveByUsername(String username) throws SQLException, IOException{
		
		java.sql.Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM azienda WHERE Username=?";
		AziendaBean azienda= new AziendaBean();
		
		try {
			connection =  DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, username);
	
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				
				azienda.setNome(rs.getString("Nome"));
				azienda.setNomeResp(rs.getString("NomeResponsabile"));
				azienda.setPsw(rs.getString("Psw"));
				azienda.setSede(rs.getString("Sede"));
				azienda.setUsername(username);
			}
		
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				 DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return azienda;
	}
	
	
	
	
	
	
	public Collection<AziendaBean> doRetrieveAll() throws SQLException,  IOException {
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		
		 Collection<AziendaBean> products =new LinkedList<AziendaBean>();
		 
		 String selectSQL="SELECT * FROM azienda";
		 
		
		 try{
			 connection = DriverManagerConnectionPool.getConnection();
			 preparedStatement= connection.prepareStatement(selectSQL);
			
			 ResultSet rs= preparedStatement.executeQuery();
			 
			 while(rs.next()){
				 AziendaBean bean= new AziendaBean();
				 
				 bean.setNome(rs.getString("Nome"));
				 bean.setNomeResp(rs.getString("NomeResponsabile"));
				 bean.setSede(rs.getString("Sede"));
				 
				 products.add(bean);
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
		  
		return products;
	}
	
	
	public Collection<OffertaFormativaBean> trovaOfferteFormative(String nomeAzienda) throws SQLException,IOException{
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		
		 Collection<OffertaFormativaBean> offerte =new ArrayList<OffertaFormativaBean>();
		 
		 String selectSQL="SELECT * FROM offertaFormativa WHERE Azienda=?";
		 
		
		 try{
			 connection = DriverManagerConnectionPool.getConnection();
			 preparedStatement= connection.prepareStatement(selectSQL);
			 preparedStatement.setString(1, nomeAzienda);
			 ResultSet rs= preparedStatement.executeQuery();
			 
			 while(rs.next()){
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
	
//-----------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	public void aggiungiOffertaFormativa(String nome,String sede,String tema,String obiettivi,String modalitaSvolgimento,String tutorEsterno,String azienda) throws SQLException, IOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int ID=0;
		String insertSQL="SELECT max(ID) FROM offertaFormativa";
		
		try {
			 connection = DriverManagerConnectionPool.getConnection();
			 preparedStatement= connection.prepareStatement(insertSQL);
			
			 ResultSet rs= preparedStatement.executeQuery();
			 
			 if(rs.next()){
				 ID=rs.getInt("max(ID)");
			 }
			
			
			insertSQL = "INSERT INTO offertaFormativa(ID,Nome,Sede,Tema,Obiettivi,ModalitaSvolgimento,TutorEsterno,Azienda) VALUES (?,?,?,?,?,?,?,?)";
			//connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			ID++;
			preparedStatement.setInt(1, ID);
			preparedStatement.setString(2, nome);
			preparedStatement.setString(3, sede);
			preparedStatement.setString(4, tema);
			preparedStatement.setString(5, obiettivi);
			preparedStatement.setString(6, modalitaSvolgimento);
			preparedStatement.setString(7, tutorEsterno);
			preparedStatement.setString(8, azienda);
		
			
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
	
	
	
	
	public Collection<StudenteBean> trovaTirocinanti(String nomeAzienda) throws SQLException, IOException{
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		
		 Collection<StudenteBean> studentiRichieste=new ArrayList<StudenteBean>();
		 ArrayList<OffertaFormativaBean> array=(ArrayList<OffertaFormativaBean>) trovaOfferteFormative(nomeAzienda);
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
			
			selectSQL="SELECT * FROM Tirocinio WHERE Studente=?";
		 	
			for (StudenteBean studenteBean : studenti) {
			
				 //connection = DriverManagerConnectionPool.getConnection();
				 preparedStatement= connection.prepareStatement(selectSQL);
				 preparedStatement.setString(1, studenteBean.getMatricola());
				 ResultSet rs= preparedStatement.executeQuery();
				 
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

	
	public Collection<StudenteBean> trovaStudentiConRichiesta(String nomeAzienda) throws SQLException,IOException{
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		
		 Collection<StudenteBean> studentiRichieste=new ArrayList<StudenteBean>();
		 ArrayList<OffertaFormativaBean> array=(ArrayList<OffertaFormativaBean>) trovaOfferteFormative(nomeAzienda);
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
			
			selectSQL="SELECT Azienda,Studente FROM Tirocinio WHERE Studente=?";
		 	
			for (StudenteBean studenteBean : studenti) {
			
				// connection = DriverManagerConnectionPool.getConnection();
				 preparedStatement= connection.prepareStatement(selectSQL);
				 preparedStatement.setString(1, studenteBean.getMatricola());
				 ResultSet rs= preparedStatement.executeQuery();
				 
				 while(rs.next()) {
					 String azienda=rs.getString("Azienda");
					 String studente=rs.getString("Studente");
					
					 if(azienda==null && studente!=null) {
						
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
