package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.xml.sax.SAXException;

import bean.TirocinioBean;
import connection.DriverManagerConnectionPool;

public class DocumentoModel {
		
	public void doSave(String nome) throws SQLException, IOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL="INSERT INTO Documento (Nome) VALUES (?)";
		
		try {
		
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
	
				preparedStatement.setString(1, nome);
		
				
				preparedStatement.executeUpdate();
	
				connection.commit();
			
			
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				
			}
		}

	}
	
	
	public void deleteDoc(String nome) throws SQLException,  IOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL="DELETE FROM Documento WHERE Nome=?";
		
		try {
		
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
	
				preparedStatement.setString(1, nome);
		
				
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
}
