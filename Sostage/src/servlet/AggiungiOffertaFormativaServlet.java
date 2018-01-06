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
		

		//CONTROLLO NOME OFFERTA
		if(nome == null || nome.trim().equals("")) {
			error+= "Inserisci Nome Correttamente<br>";
		}
		else if(nome.length()<2 || nome.length()>50){
			error+="Lunghezza nome non valida <br>";
		}
		else {
			int i;
			for(i=0;i<nome.length();i++) {
				char ch=nome.charAt(i);
				if(!Character.isLetter(ch) && !Character.isSpaceChar(ch)) {
					error+="formato nome non valido <br>";
					break;
				}
			}
		}
		
		

		//CONTROLLO SEDE
		if(sede == null || sede.trim().equals("")) {
			error+= "Inserisci Sede Correttamente<br>";
		}
		else if(sede.length()<4 || sede.length()>50){
			error+="Lunghezza sede non valida <br>";
		}
		else {
			int i;
			for(i=0;i<sede.length();i++) {
				char ch=sede.charAt(i);
				if(!Character.isLetter(ch) && !Character.isSpaceChar(ch)) {
					error+="formato sede non valido <br>";
					break;
				}
			}
		}
		
		
		//CONTROLLO TEMA
		if(tema == null || tema.trim().equals("")) {
			error+= "Inserisci Tema Correttamente<br>";
		}
		else if(tema.length()<5 || tema.length()>50){
			error+="Lunghezza tema non valida <br>";
		}
		else {
			int i;
			for(i=0;i<tema.length();i++) {
				char ch=tema.charAt(i);
				if(!Character.isLetter(ch) && !Character.isSpaceChar(ch)) {
				error+="formato tema non valido <br>";
				break;
				}
			}
		}
		
		
		//CONTROLLO OBIETTIVI
		if(obiettivi == null || obiettivi.trim().equals("")) {
			error+= "Inserisci Obiettivi Correttamente<br>";
		}
		else if(obiettivi.length()<5 || obiettivi.length()>50){
			error+="Lunghezza obiettivi non valida <br>";
		}
		else {
			int i;
			for(i=0;i<obiettivi.length();i++) {
				char ch=obiettivi.charAt(i);
				if(!Character.isLetter(ch) && !Character.isSpaceChar(ch)) {
				error+="formato obiettivi non valido <br>";
				break;
				}
			}
		}
		
		//CONTROLLO  MODALITA SVOLGIMENTO
		if(modalitaSvolgimento == null | modalitaSvolgimento.trim().equals("")) {
				error+= "Inserisci Obiettivi Correttamente<br>";
			}
		else if(modalitaSvolgimento.length()<10 || modalitaSvolgimento.length()>100){
				error+="Lunghezza obiettivi non valida <br>";
			}
		

		if(error==null || !error.trim().equals("")){
			request.setAttribute("error", error);
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/azienda/AggiungiOffertaFormativa.jsp");
			dispatcher.forward(request, response);
			return;
		}
				
				
		
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
