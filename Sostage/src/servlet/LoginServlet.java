package servlet;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.GestoreBean;
import bean.UtenteBean;
import model.GestoreModel;
import model.UtenteModel;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    static GestoreModel modelG= new GestoreModel();  
	static UtenteModel model=new UtenteModel();
	
    public LoginServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String error= "";
		UtenteBean bean=new UtenteBean();
		GestoreBean gestore= new GestoreBean();
		String username = request.getParameter("username");
		String password = request.getParameter("psw");
		String redirectPage;
			
		//CONTROLLO USERNAME
		if(username == null || username.trim().equals("")) {
			error+= "Inserisci Username <br>"; 
		} else if(username.length()<2 || username.length()>20){
			error+="Lunghezza username non valida <br>";
		}
		else {
			int i;
			for(i=0;i<username.length();i++) {
				char ch=username.charAt(i);
				if(username.contains(" ")) {
					error+="formato username non valido <br>";
					break;
				}
			}
			if(i==username.length()) {
				request.setAttribute("username", username);
			}
		}

		//CONTROLLO PASSWORD
		if(password == null || password.trim().equals("")) {
			error+= "Inserisci Password <br>";
		}
		else if(password.length()<2 || password.length()>20){
			error+="Lunghezza password non valida <br>";
		}
		else {
			int i;
			for(i=0;i<password.length();i++) {
				char ch=password.charAt(i);
				if(password.contains(" ")) {
					error+="formato password non valido <br>";
					break;
				}
			}
		}
		
		
		
		try{
			String tipo=model.checkUser(username, password);
			System.out.println(tipo);
			if(tipo.equals("utente")) {
				
				System.out.println("Sono in utente login servlet");
				
				bean=model.doRetrieveByUsername(username);
				boolean logged=true;
				if(!bean.getUsername().equals("") && !bean.getPsw().equals("")){
					request.getSession().setAttribute("logged", logged);
					request.getSession().setAttribute("bean", bean);
					request.getSession().setAttribute("ruolo",bean.getRuolo());
					HttpSession session= request.getSession();
					if(logged){
						synchronized (session) {
							request.getSession().setAttribute("username", bean.getUsername());
							RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/UserServlet"); 
							dispatcher.forward(request, response);
						}
					}				
				}
				
			}
			else if(tipo.equals("gestore")) {
				gestore=modelG.doRetrieveByUsername(username);
				

				System.out.println("Sono in gestore login servlet");
			
				boolean logged=true;
				if(!gestore.getUsername().equals("") && !gestore.getPsw().equals("")){
				
					//redirectPage="/logged.jsp";
					//response.sendRedirect(request.getContextPath() + redirectPage);
					request.getSession().setAttribute("logged", logged);
					request.getSession().setAttribute("gestore", gestore);
					request.getSession().setAttribute("ruolo","gestore");
					HttpSession session= request.getSession();
					if(logged){
						synchronized (session) {
							request.getSession().setAttribute("username", gestore.getUsername());
							RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/UserServlet"); 
							dispatcher.forward(request, response);
						}
					}				
				}
				
			}
			else{
				error="Invalid username and password";
				request.setAttribute("error", error);
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Fail.jsp"); 
				dispatcher.forward(request, response);
				
			}
		
			
		}
		catch (Exception e) {
				
			
		}
		
		
			
		

	}
}

