package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.GestoreBean;
import model.GestoreModel;


@WebServlet("/admin/AggiungiUtenteServlet")
public class AggiungiUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    GestoreModel model = new GestoreModel();
    public AggiungiUtenteServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ruolo= request.getParameter("role");
		GestoreBean bean=(GestoreBean)request.getSession().getAttribute("gestore");
		String gestore=bean.getCF();
		
		if(ruolo.equals("azienda")) {
			String nome=request.getParameter("Nome");
			String nomeResponsabile=request.getParameter("nomeResponsabile");
			String sede=request.getParameter("Sede");
			String mail=request.getParameter("mail");
			String username=request.getParameter("username");
			String psw=request.getParameter("psw");
			
			try {
				model.aggiungiAzienda(gestore, nome, mail, sede, nomeResponsabile, username, psw);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/SuccessoAggiunta.jsp"); 
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(ruolo.equals("Presidente")) {
			String nome=request.getParameter("Nome");
			String CF=request.getParameter("CF");
			String cognome=request.getParameter("Cognome");
			String mail=request.getParameter("mail");
			String username=request.getParameter("username");
			String psw=request.getParameter("psw");
			
			try {
				model.aggiungiPresidente(gestore, CF, nome, cognome, mail, username, psw);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/SuccessoAggiunta.jsp"); 
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		else if(ruolo.equals("Tutor Interno")) {
			String nome=request.getParameter("Nome");
			String CF=request.getParameter("CF");
			String cognome=request.getParameter("Cognome");
			String mail=request.getParameter("mail");
			String username=request.getParameter("username");
			String psw=request.getParameter("psw");
			
			
			try {
				model.aggiungiTutorInterno(gestore, CF, nome, cognome, mail, username, psw);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/SuccessoAggiunta.jsp"); 
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(ruolo.equals("Tutor Esterno")) {
			String nome=request.getParameter("Nome");
			String CF=request.getParameter("CF");
			String cognome=request.getParameter("Cognome");
			String mail=request.getParameter("mail");
			String username=request.getParameter("username");
			String psw=request.getParameter("psw");
		
		
			try {
				model.aggiungiTutorEsterno(gestore, CF, nome, cognome, mail, username, psw);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/SuccessoAggiunta.jsp"); 
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		}
		else if(ruolo.equals("ufficio")) {
			String sigla=request.getParameter("Sigla");
			String mail=request.getParameter("mail");
			String username=request.getParameter("username");
			String psw=request.getParameter("psw");
			
			try {
				model.aggiungiUfficio(gestore, sigla, mail, username, psw);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/SuccessoAggiunta.jsp"); 
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
