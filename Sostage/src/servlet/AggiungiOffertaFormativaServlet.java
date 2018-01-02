package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AziendaBean;
import bean.OffertaFormativaBean;
import bean.StudenteBean;
import bean.UtenteBean;
import model.AziendaModel;

@WebServlet("/azienda/AggiungiOffertaFormativaServlet")
public class AggiungiOffertaFormativaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     AziendaModel model= new AziendaModel();
   
    public AggiungiOffertaFormativaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error= "";
		String nome=request.getParameter("Nome");
		String sede=request.getParameter("Sede");
		String tema = request.getParameter("Tema");
		String obiettivi = request.getParameter("Obiettivi");
		String modalitaSvolgimento = request.getParameter("modalitaSvolgimento");
		String tutorEsterno=request.getParameter("tutorEsterno");
		UtenteBean utente=(UtenteBean) request.getSession().getAttribute("bean");
		System.out.println(utente.toString());
		String azienda="";
		try {
			azienda=model.doRetrieveByUsername(utente.getUsername()).getNome();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(nome == null || nome.trim().equals("")) {
			error+= "Inserisci Nome Correttamente<br>"; 
		} else {
			request.setAttribute("Nome", nome);
		}

		if(sede == null || sede.trim().equals("")) {
			error+= "Inserisci Sede Correttamente<br>";
		}
		
		if(tema == null || tema.trim().equals("")) {
			error+= "Inserisci Tema Correttamente<br>";
		}
		if(obiettivi== null || obiettivi.trim().equals("")) {
			error+= "Inserisci Obiettivi Correttamente<br>";
		}
		if(modalitaSvolgimento== null || modalitaSvolgimento.trim().equals("")) {
			error+= "Inserisci Modalità di Svolgimento Correttamente <br>";
		}
		if(tutorEsterno== null || tutorEsterno.trim().equals("")) {
			error+= "Inserisci il tutor esterno Correttamente <br>";
		}
		
		if(error==null || error.trim().equals("")){
			OffertaFormativaBean offerta= new OffertaFormativaBean(0,nome,sede,tema,obiettivi,modalitaSvolgimento,tutorEsterno,azienda);
			request.getSession().setAttribute("offerta", offerta);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/azienda/ControlloOffertaAggiunta.jsp"); 
			dispatcher.forward(request, response);
			return;
		}
	

			
		
		
		
	}

}
