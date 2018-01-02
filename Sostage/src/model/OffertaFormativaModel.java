package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import bean.AziendaBean;
import bean.OffertaFormativaBean;
import bean.TutorEsternoBean;
import bean.UtenteBean;
import connection.DriverManagerConnectionPool;

public class OffertaFormativaModel {

	
	public Collection<String> doRetrieveDistinctCity() throws SQLException {
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		
		 Collection<String> citt‡ =new ArrayList<String>();
		 
		 String selectSQL="SELECT DISTINCT(Sede) FROM offertaFormativa";
		 
	
		 try{
			 connection = (Connection) DriverManagerConnectionPool.getConnection();
			 preparedStatement= (PreparedStatement) connection.prepareStatement(selectSQL);
			 
		
			 ResultSet rs= preparedStatement.executeQuery();
			 
			 while(rs.next()){
				
				 citt‡.add(rs.getString("Sede"));
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
		  
		return citt‡;
	}
	
	
	public Collection<String> doRetrieveDistinctTopic() throws SQLException {
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		
		 Collection<String> tematiche =new ArrayList<String>();
		 
		 String selectSQL="SELECT DISTINCT(Tema) FROM offertaFormativa";
		 
	
		 try{
			 connection = (Connection) DriverManagerConnectionPool.getConnection();
			 preparedStatement= (PreparedStatement) connection.prepareStatement(selectSQL);
			 
		
			 ResultSet rs= preparedStatement.executeQuery();
			 
			 while(rs.next()){
				
				 tematiche.add(rs.getString("Tema"));
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
		  
		return tematiche;
	}
	
	
	public Collection<OffertaFormativaBean> trovaOfferte(String sede,String tema) throws SQLException {
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		
		 Collection<OffertaFormativaBean> offerte =new ArrayList<OffertaFormativaBean>();

		 String selectSQL = "SELECT * FROM offertaFormativa WHERE Sede=? AND Tema=?";
		
		 try{
			 	connection = (Connection) DriverManagerConnectionPool.getConnection();
				preparedStatement = (PreparedStatement) connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, sede);
				preparedStatement.setString(2, tema);
				ResultSet rs;
				rs=preparedStatement.executeQuery();
				
				 while(rs.next()){
					 OffertaFormativaBean bean= new OffertaFormativaBean();
					 
					 bean.setNome(rs.getString("Nome"));
					 bean.setID(rs.getInt("ID"));
					 bean.setModalit‡Svolgimento(rs.getString("ModalitaSvolgimento"));
					 bean.setObiettivi(rs.getString("obiettivi"));
					 bean.setSede(rs.getString("Sede"));
					 bean.setTema(rs.getString("Tema"));
					 bean.setAzienda(rs.getString("Azienda"));
					 bean.setTutorEsterno(rs.getString("TutorEsterno"));
					 
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
	
	

	public Collection<OffertaFormativaBean> trovaOfferteAzienda(String azienda) throws SQLException {
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		
		 Collection<OffertaFormativaBean> products =new ArrayList<OffertaFormativaBean>();
		 
		 String selectSQL="SELECT * FROM offertaFormativa WHERE Azienda=?";
		
		
		 try{
			 connection = (Connection) DriverManagerConnectionPool.getConnection();
			 preparedStatement= (PreparedStatement) connection.prepareStatement(selectSQL);
			 preparedStatement.setString(1, azienda);
			 ResultSet rs= preparedStatement.executeQuery();
			 
			 while(rs.next()){
				 OffertaFormativaBean bean= new OffertaFormativaBean();
				 
				 bean.setID(rs.getInt("ID"));
				 bean.setNome(rs.getString("Nome"));
				 bean.setSede(rs.getString("Sede"));
				 bean.setTema(rs.getString("Tema"));
				 bean.setObiettivi(rs.getString("Obiettivi"));
				 bean.setModalit‡Svolgimento(rs.getString("ModalitaSvolgimento"));
				 bean.setTutorEsterno(rs.getString("TutorEsterno"));
				 bean.setAzienda(rs.getString("Azienda"));
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
	
	
	
	
	public void cancellaOfferta(int ID) throws SQLException {

		java.sql.Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "DELETE FROM offertaFormativa WHERE ID=?";
		
		
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
	
public Collection<OffertaFormativaBean> doRetrieveAll() throws SQLException {
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		
		 Collection<OffertaFormativaBean> tutor =new ArrayList<OffertaFormativaBean>();
		 
		 String selectSQL="SELECT * FROM offertaFormativa";
		 
		
		 try{
			 connection = (Connection) DriverManagerConnectionPool.getConnection();
			 preparedStatement= (PreparedStatement) connection.prepareStatement(selectSQL);
			
			 ResultSet rs= preparedStatement.executeQuery();
			 
			 while(rs.next()){
				 OffertaFormativaBean bean= new OffertaFormativaBean();
				 
				 bean.setNome(rs.getString("Nome"));
				 bean.setID(rs.getInt("ID"));
				 bean.setSede(rs.getString("Sede"));
				 bean.setTema(rs.getString("Tema"));
				 bean.setObiettivi(rs.getString("Obiettivi"));
				 bean.setModalit‡Svolgimento(rs.getString("ModalitaSvolgimento"));
				 bean.setTutorEsterno(rs.getString("TutorEsterno"));
				 bean.setAzienda(rs.getString("Azienda"));
				 
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

public OffertaFormativaBean doRetrieveByID(int ID) throws SQLException{
	
	java.sql.Connection connection = null;
	PreparedStatement preparedStatement = null;
	String selectSQL = "SELECT * FROM offertaFormativa WHERE ID=?";
	OffertaFormativaBean offerta= new OffertaFormativaBean();
	
	try {
		connection =  DriverManagerConnectionPool.getConnection();
		preparedStatement = (PreparedStatement) connection.prepareStatement(selectSQL);
		
		preparedStatement.setInt(1, ID);

		ResultSet rs = preparedStatement.executeQuery();
		if(rs.next()){
			
			offerta.setID(ID);
			offerta.setNome(rs.getString("Nome"));
			offerta.setSede(rs.getString("Sede"));
			offerta.setTema(rs.getString("Tema"));
			offerta.setObiettivi(rs.getString("Obiettivi"));
			offerta.setModalit‡Svolgimento(rs.getString("ModalitaSvolgimento"));
			offerta.setTutorEsterno(rs.getString("TutorEsterno"));
			offerta.setAzienda(rs.getString("Azienda"));
		
		
		}
	
		
	} finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}
	return offerta;
}

}
