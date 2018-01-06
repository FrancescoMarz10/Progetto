package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xml.sax.SAXException;

import model.NotificaModel;
import model.StudenteModel;


@WebServlet("/ufficio/InviaNotificaServlet")
public class InviaNotificaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public InviaNotificaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String matricola=request.getParameter("Matricola");
		StudenteModel model= new StudenteModel();
		NotificaModel modelNot= new NotificaModel();
		
		try {
			modelNot.aggiungiNotifica("Lo Studente "+ model.doRetrieveByMatricola(matricola).getNome()+" "+model.doRetrieveByMatricola(matricola).getCognome()+" ha effettuato una richiesta di tirocinio","Presidente", null, null, null, null, matricola);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ufficio/SuccessoInvioUfficio.jsp"); 
		dispatcher.forward(request, response);
		return;
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
