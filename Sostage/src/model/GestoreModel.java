package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.GestoreBean;
import bean.OffertaFormativaBean;
import bean.UtenteBean;
import connection.DriverManagerConnectionPool;

public class GestoreModel {
		
	
		public GestoreBean doRetrieveByUsername(String username) throws SQLException{
			
			java.sql.Connection connection = null;
			PreparedStatement preparedStatement = null;
			String selectSQL = "SELECT * FROM Gestore WHERE Username=?";
			GestoreBean gestore= new GestoreBean();
			
			try {
				connection =  DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				
				preparedStatement.setString(1, username);
		
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					
					gestore.setCF(rs.getString("CF"));;
					gestore.setCognome(rs.getString("Cognome"));
					gestore.setMail(rs.getString("Mail"));
					gestore.setNome(rs.getString("Nome"));
					gestore.setPsw(rs.getString("Psw"));
					gestore.setUsername(username);
				}
			
				
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			return gestore;
		}
	

		