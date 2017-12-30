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
	
//_________________________________________________________________________________________
		
		public void deleteUser(UtenteBean bean) throws SQLException {

			java.sql.Connection connection = null;
			PreparedStatement preparedStatement = null;
			String selectSQL = "DELETE FROM Utente WHERE Username=? AND Psw=?";
			
			
			try {
				connection =  DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				
				preparedStatement.setString(1, bean.getUsername());
				preparedStatement.setString(2, bean.getPsw());
		
			
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
		
		

		
		
		
//_________________________________________________________________________________________
		
		
		public void aggiungiAzienda(String gestore,String nome,String mail,String sede, String nomeResponsabile, String username, String psw) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			String insertSQL = "INSERT INTO utente(Username,Psw,Mail,Ruolo,Gestore) VALUES (?,?,?,?,?)";
		
			try {
				
				 connection = DriverManagerConnectionPool.getConnection();
				 preparedStatement= connection.prepareStatement(insertSQL);
				
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, psw);
				preparedStatement.setString(3, mail);
				preparedStatement.setString(4, "Azienda");
				preparedStatement.setString(5, gestore);
			
				preparedStatement.executeUpdate();
				
				
				insertSQL = "INSERT INTO azienda(Nome,Username,Psw,NomeResponsabile,Sede) VALUES (?,?,?,?,?)";
				 preparedStatement= connection.prepareStatement(insertSQL);
				
				preparedStatement.setString(1, nome);
				preparedStatement.setString(2, username);
				preparedStatement.setString(3, psw);
				preparedStatement.setString(4, nomeResponsabile);
				preparedStatement.setString(5, sede);
			
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
		
		
//_________________________________________________________________________________________
		
			public void aggiungiTutorInterno(String gestore,String CF,String nome,String cognome,String mail, String username, String psw) throws SQLException {
					Connection connection = null;
					PreparedStatement preparedStatement = null;
					String insertSQL = "INSERT INTO utente(Username,Psw,Mail,Ruolo,Gestore) VALUES (?,?,?,?,?)";
				
					try {
						
						 connection = DriverManagerConnectionPool.getConnection();
						 preparedStatement= connection.prepareStatement(insertSQL);
						
						preparedStatement.setString(1, username);
						preparedStatement.setString(2, psw);
						preparedStatement.setString(3, mail);
						preparedStatement.setString(4, "TutorInterno");
						preparedStatement.setString(5, gestore);
					
						preparedStatement.executeUpdate();
						
						
						insertSQL = "INSERT INTO tutorInterno(CF,Nome,Cognome,Username,Psw) VALUES (?,?,?,?,?)";
						 preparedStatement= connection.prepareStatement(insertSQL);
						
						preparedStatement.setString(1, CF);
						preparedStatement.setString(2, nome);
						preparedStatement.setString(3, cognome);
						preparedStatement.setString(4, username);
						preparedStatement.setString(5, psw);
					
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
		
//_________________________________________________________________________________________
				
				
		public void aggiungiTutorEsterno(String gestore,String CF,String nome,String cognome,String mail, String username, String psw) throws SQLException {
					Connection connection = null;
					PreparedStatement preparedStatement = null;
					String insertSQL = "INSERT INTO utente(Username,Psw,Mail,Ruolo,Gestore) VALUES (?,?,?,?,?)";
				
					try {
						
						 connection = DriverManagerConnectionPool.getConnection();
						 preparedStatement= connection.prepareStatement(insertSQL);
						
						preparedStatement.setString(1, username);
						preparedStatement.setString(2, psw);
						preparedStatement.setString(3, mail);
						preparedStatement.setString(4, "TutorEsterno");
						preparedStatement.setString(5, gestore);
					
						preparedStatement.executeUpdate();
						
						
						insertSQL = "INSERT INTO tutorEsterno(CF,Nome,Cognome,Username,Psw) VALUES (?,?,?,?,?)";
						 preparedStatement= connection.prepareStatement(insertSQL);
						
						preparedStatement.setString(1, CF);
						preparedStatement.setString(2, nome);
						preparedStatement.setString(3, cognome);
						preparedStatement.setString(4, username);
						preparedStatement.setString(5, psw);
					
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

//_________________________________________________________________________________________
		
		public void aggiungiPresidente(String gestore,String CF,String nome,String cognome,String mail, String username, String psw) throws SQLException {
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				String insertSQL = "INSERT INTO utente(Username,Psw,Mail,Ruolo,Gestore) VALUES (?,?,?,?,?)";
			
				try {
					
					 connection = DriverManagerConnectionPool.getConnection();
					 preparedStatement= connection.prepareStatement(insertSQL);
					
					preparedStatement.setString(1, username);
					preparedStatement.setString(2, psw);
					preparedStatement.setString(3, mail);
					preparedStatement.setString(4, "Presidente");
					preparedStatement.setString(5, gestore);
				
					preparedStatement.executeUpdate();
					
					
					insertSQL = "INSERT INTO Presidente(CF,Nome,Cognome,Username,Psw) VALUES (?,?,?,?,?)";
					 preparedStatement= connection.prepareStatement(insertSQL);
					
					preparedStatement.setString(1, CF);
					preparedStatement.setString(2, nome);
					preparedStatement.setString(3, cognome);
					preparedStatement.setString(4, username);
					preparedStatement.setString(5, psw);
				
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
		
//_________________________________________________________________________________________
		
				public void aggiungiUfficio(String gestore,String sigla,String mail, String username, String psw) throws SQLException {
						Connection connection = null;
						PreparedStatement preparedStatement = null;
						String insertSQL = "INSERT INTO utente(Username,Psw,Mail,Ruolo,Gestore) VALUES (?,?,?,?,?)";
					
						try {
							
							 connection = DriverManagerConnectionPool.getConnection();
							 preparedStatement= connection.prepareStatement(insertSQL);
							
							preparedStatement.setString(1, username);
							preparedStatement.setString(2, psw);
							preparedStatement.setString(3, mail);
							preparedStatement.setString(4, "Ufficio");
							preparedStatement.setString(5, gestore);
						
							preparedStatement.executeUpdate();
							
							
							insertSQL = "INSERT INTO ufficio(Sigla,Username,Psw) VALUES (?,?,?)";
							 preparedStatement= connection.prepareStatement(insertSQL);
							
							preparedStatement.setString(1, sigla);
							preparedStatement.setString(2, username);
							preparedStatement.setString(3, psw);
						
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
				
						
			
}
