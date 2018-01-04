package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
			String error="";
			String nome=request.getParameter("Nome");
			String nomeResponsabile=request.getParameter("nomeResponsabile");
			String sede=request.getParameter("Sede");
			String mail=request.getParameter("mail");
			String username=request.getParameter("username");
			String psw=request.getParameter("psw");
			
			int a=0;
			String[] arr= new String[5];
			
			if(mail.contains("@")) {
				 arr=  mail.split("@");
			}
			else {
				error+="Formato mail non valido";
				a=1;
			}
			
			//CONTROLLO NOME AZIENDA
			if(nome == null || nome.trim().equals("")) {
				error+= "Inserisci Nome Correttamente\n";
			}
			else if(nome.length()<2 || nome.length()>50){
				error+="Lunghezza nome non valida \n";
			}
			else {
				int i;
				for(i=0;i<nome.length();i++) {
					char ch=nome.charAt(i);
					if(!Character.isLetter(ch) && !Character.isSpaceChar(ch)) {
						error+="formato nome non valido \n";
						break;
					}
				}
			}
			
			
			//CONTROLLO NOME RESPONSABILE
			if(nomeResponsabile == null || nomeResponsabile.trim().equals("")) {
				error+= "Inserisci Nome del Responsabile Correttamente\n";
			}
			else if(nomeResponsabile.length()<2 || nomeResponsabile.length()>50){
				error+="Lunghezza nome del responsabile non valida \n";
			}
			else {
				int i;
				for(i=0;i<nomeResponsabile.length();i++) {
					char ch=nomeResponsabile.charAt(i);
					if(!Character.isLetter(ch) && !Character.isSpaceChar(ch)) {
						error+="formato nome del responsabile non valido \n";
						break;
					}
				}
			}
			
			
			//CONTROLLO SEDE
			if(sede == null || sede.trim().equals("")) {
				error+= "Inserisci Sede Correttamente\n";
			}
			else if(sede.length()<2 || sede.length()>50){
				error+="Lunghezza sede non valida \n";
			}
			else {
				int i;
				for(i=0;i<sede.length();i++) {
					char ch=sede.charAt(i);
					if(!Character.isLetter(ch) && !Character.isSpaceChar(ch)) {
						error+="formato sede non valido \n";
						break;
					}
				}
			}
			
			
			//CONTROLLO MAIL
			if(mail == null || mail.trim().equals("")) {
				error+= "Inserisci mail Correttamente\n";
			}
			else if(!mail.contains("@")) {
				error+="format mail non valido \n";
			}
			else {
				int i;
				for(i=0;i<arr[0].length();i++) {
					char ch=arr[0].charAt(i);
					if(!Character.isLetter(ch) && !Character.isSpaceChar(ch)) {
						error+="formato mail non valido \n";
						break;
					}
				}
			}
			

			//CONTROLLO CAMPO USERNAME
			if(username == null || username.trim().equals("")) {
				error+= "Inserisci Username  \n"; 
			}else if(username.length()<2 || username.length()>20){
				error+="Lunghezza username non valida \n>";
			}
			else {
				int i;
				for(i=0;i<username.length();i++) {
					char ch=username.charAt(i);
					if(username.contains(" ")) {
						error+="formato username non valido \n";
						break;
					}
				}
			}

			//CONTROLLO CAMPO PASSWORD
			if(psw == null || psw.trim().equals("")) {
				error+= "Inserisci Password Correttamente\n";
			}
			else if(psw.length()<2 || psw.length()>20){
				error+="Lunghezza password non valida\n";
			}
			else {
				int i;
				for(i=0;i<psw.length();i++) {
					char ch=psw.charAt(i);
					if(psw.contains(" ")) {
						error+="formato password non valido \n";
						break;
					}
				}
			}
			
			if(error==null || !error.trim().equals("")){				
				request.setAttribute("error", error);
				RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/admin/ErroreInserimento.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			
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
			String error="";
			String nome=request.getParameter("Nome");
			String CF=request.getParameter("CF");
			String cognome=request.getParameter("Cognome");
			String mail=request.getParameter("mail");
			String username=request.getParameter("username");
			String psw=request.getParameter("psw");
			
			int a=0;
			String[] arr= new String[5];
			
			if(mail.contains("@")) {
				 arr=  mail.split("@");
			}
			else {
				error+="Formato mail non valido";
				a=1;
			}
			
			
			//CONTROLLO NOME PRESIDENTE
			if(nome == null || nome.trim().equals("")) {
				error+= "Inserisci Nome Correttamente\n";
			}
			else if(nome.length()<2 || nome.length()>50){
				error+="Lunghezza nome non valida \n";
			}
			else {
				int i;
				for(i=0;i<nome.length();i++) {
					char ch=nome.charAt(i);
					if(!Character.isLetter(ch) && !Character.isSpaceChar(ch)) {
						error+="formato nome non valido \n";
						break;
					}
				}
			}
			
			//CONTROLLO COGNOME PRESIDENTE
			if(cognome == null || cognome.trim().equals("")) {
				error+= "Inserisci Cognome Correttamente\n";
			}
			else if(cognome.length()<2 || cognome.length()>50){
				error+="Lunghezza Cognome non valida \n";
			}
			else {
				int i;
				for(i=0;i<cognome.length();i++) {
					char ch=cognome.charAt(i);
					if(!Character.isLetter(ch) && !Character.isSpaceChar(ch)) {
						error+="formato Cognome non valido \n";
						break;
					}
				}
			}
			
			//CONTROLLO CF PRESIDENTE
			if(CF == null || CF.trim().equals("")) {
				error+= "Inserisci CF Correttamente \n";
			}
			else if(CF.length()<16){
				error+="Lunghezza CF non valida \n";
			}
			else {
				int i;
				for(i=0;i<CF.length();i++) {
					char ch=CF.charAt(i);
					if(!Character.isLetter(ch) && !Character.isSpaceChar(ch)) {
						error+="formato CF non valido \n";
						break;
					}
				}
			}
			
			
			
			//CONTROLLO MAIL
			if(mail == null || mail.trim().equals("")) {
				error+= "Inserisci mail Correttamente \n";
			}
			else if(!mail.contains("@")) {
				error+="format mail non valido \n";
			}
			else {
				int i;
				for(i=0;i<arr[0].length();i++) {
					char ch=arr[0].charAt(i);
					if(!Character.isLetter(ch) && !Character.isSpaceChar(ch)) {
						error+="formato mail non valido \n";
						break;
					}
				}
			}
			
			
			//CONTROLLO CAMPO USERNAME
			if(username == null || username.trim().equals("")) {
				error+= "Inserisci Username \n"; 
			}else if(username.length()<2 || username.length()>20){
				error+="Lunghezza username non valida \n";
			}
			else {
				int i;
				for(i=0;i<username.length();i++) {
					char ch=username.charAt(i);
					if(username.contains(" ")) {
						error+="formato username non valido \n";
						break;
					}
				}
			}

			//CONTROLLO CAMPO PASSWORD
			if(psw == null || psw.trim().equals("")) {
				error+= "Inserisci Password Correttamente \n";
			}
			else if(psw.length()<2 || psw.length()>20){
				error+="Lunghezza password non valida \n";
			}
			else {
				int i;
				for(i=0;i<psw.length();i++) {
					char ch=psw.charAt(i);
					if(psw.contains(" ")) {
						error+="formato password non valido\n";
						break;
					}
				}
			}
			
			if(error==null || !error.trim().equals("")){
				request.setAttribute("error", error);
				RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/admin/ErroreInserimento.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
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
			String error="";
			String nome=request.getParameter("Nome");
			String CF=request.getParameter("CF");
			String cognome=request.getParameter("Cognome");
			String mail=request.getParameter("mail");
			String username=request.getParameter("username");
			String psw=request.getParameter("psw");
			
			int a=0;
			String[] arr= new String[5];
			
			if(mail.contains("@")) {
				 arr=  mail.split("@");
			}
			else {
				error+="Formato mail non valido";
				a=1;
			}
			
			//CONTROLLO NOME TUTOR
			if(nome == null || nome.trim().equals("")) {
				error+= "Inserisci Nome Correttamente \n";
			}
			else if(nome.length()<2 || nome.length()>50){
				error+="Lunghezza nome non valida \n";
			}
			else {
				int i;
				for(i=0;i<nome.length();i++) {
					char ch=nome.charAt(i);
					if(!Character.isLetter(ch) && !Character.isSpaceChar(ch)) {
						error+="formato nome non valido\n";
						break;
					}
				}
			}
			
			//CONTROLLO COGNOME TUTOR
			if(cognome == null || cognome.trim().equals("")) {
				error+= "Inserisci Cognome Correttamente\n";
			}
			else if(cognome.length()<2 || cognome.length()>50){
				error+="Lunghezza Cognome non valida\n";
			}
			else {
				int i;
				for(i=0;i<cognome.length();i++) {
					char ch=cognome.charAt(i);
					if(!Character.isLetter(ch) && !Character.isSpaceChar(ch)) {
						error+="formato Cognome non valido\n";
						break;
					}
				}
			}
			
			//CONTROLLO CF TUTOR
			if(CF == null || CF.trim().equals("")) {
				error+= "Inserisci CF Correttamente\n";
			}
			else if(CF.length()<16){
				error+="Lunghezza CF non valida \n";
			}
			else {
				int i;
				for(i=0;i<CF.length();i++) {
					char ch=CF.charAt(i);
					if(!Character.isLetter(ch) && !Character.isSpaceChar(ch)) {
						error+="formato CF non valido \n";
						break;
					}
				}
			}
			
			
			//CONTROLLO MAIL
			if(mail == null || mail.trim().equals("")) {
				error+= "Inserisci mail Correttamente\n";
			}
			else if(!mail.contains("@")) {
				error+="format mail non valido \n";
			}
			else {
				int i;
				for(i=0;i<arr[0].length();i++) {
					char ch=arr[0].charAt(i);
					if(!Character.isLetter(ch) && !Character.isSpaceChar(ch)) {
						error+="formato mail non valido \n";
						break;
					}
				}
			}
			
			//CONTROLLO CAMPO USERNAME
			if(username == null || username.trim().equals("")) {
				error+= "Inserisci Username \n"; 
			}else if(username.length()<2 || username.length()>20){
				error+="Lunghezza username non valida \n";
			}
			else {
				int i;
				for(i=0;i<username.length();i++) {
					char ch=username.charAt(i);
					if(username.contains(" ")) {
						error+="formato username non valido \n";
						break;
					}
				}
			}

			//CONTROLLO CAMPO PASSWORD
			if(psw == null || psw.trim().equals("")) {
				error+= "Inserisci Password Correttamente \n";
			}
			else if(psw.length()<2 || psw.length()>20){
				error+="Lunghezza password non valida \n";
			}
			else {
				int i;
				for(i=0;i<psw.length();i++) {
					char ch=psw.charAt(i);
					if(psw.contains(" ")) {
						error+="formato password non valido \n";
						break;
					}
				}
			}
			
			
			if(error==null || !error.trim().equals("")){
				request.setAttribute("error", error);
				RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/admin/ErroreInserimento.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
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
			String error="";
			String nome=request.getParameter("Nome");
			String CF=request.getParameter("CF");
			String cognome=request.getParameter("Cognome");
			String mail=request.getParameter("mail");
			String username=request.getParameter("username");
			String psw=request.getParameter("psw");
		
			int a=0;
			String[] arr= new String[5];
			
			if(mail.contains("@")) {
				 arr=  mail.split("@");
			}
			else {
				error+="Formato mail non valido";
				a=1;
			}

			//CONTROLLO NOME TUTOR
			if(nome == null || nome.trim().equals("")) {
				error+= "Inserisci Nome Correttamente\n";
			}
			else if(nome.length()<2 || nome.length()>50){
				error+="Lunghezza nome non valida \n";
			}
			else {
				int i;
				for(i=0;i<nome.length();i++) {
					char ch=nome.charAt(i);
					if(!Character.isLetter(ch) && !Character.isSpaceChar(ch)) {
						error+="formato nome non valido \n";
						break;
					}
				}
			}
			
			//CONTROLLO COGNOME TUTOR
			if(cognome == null || cognome.trim().equals("")) {
				error+= "Inserisci Cognome Correttamente\n";
			}
			else if(cognome.length()<2 || cognome.length()>50){
				error+="Lunghezza Cognome non valida \n";
			}
			else {
				int i;
				for(i=0;i<cognome.length();i++) {
					char ch=cognome.charAt(i);
					if(!Character.isLetter(ch) && !Character.isSpaceChar(ch)) {
						error+="formato Cognome non valido\n";
						break;
					}
				}
			}
			
			//CONTROLLO CF TUTOR
			if(CF == null || CF.trim().equals("")) {
				error+= "Inserisci CF Correttamente\n";
			}
			else if(CF.length()<16){
				error+="Lunghezza CF non valida \n";
			}
			else {
				int i;
				for(i=0;i<CF.length();i++) {
					char ch=CF.charAt(i);
					if(!Character.isLetter(ch) && !Character.isSpaceChar(ch)) {
						error+="formato CF non valido \n";
						break;
					}
				}
			}
			
			
			//CONTROLLO MAIL
			if(mail == null || mail.trim().equals("")) {
				error+= "Inserisci mail Correttamente\n";
			}
			else if(!mail.contains("@")) {
				error+="format mail non valido \n";
			}
			else {
				int i;
				for(i=0;i<arr[0].length();i++) {
					char ch=arr[0].charAt(i);
					if(!Character.isLetter(ch) && !Character.isSpaceChar(ch)) {
						error+="formato mail non valido \n";
						break;
					}
				}
			}
			
			//CONTROLLO CAMPO USERNAME
			if(username == null || username.trim().equals("")) {
				error+= "Inserisci Username \n"; 
			}else if(username.length()<2 || username.length()>20){
				error+="Lunghezza username non valida \n";
			}
			else {
				int i;
				for(i=0;i<username.length();i++) {
					char ch=username.charAt(i);
					if(username.contains(" ")) {
						error+="formato username non valido \n";
						break;
					}
				}
			}

			//CONTROLLO CAMPO PASSWORD
			if(psw == null || psw.trim().equals("")) {
				error+= "Inserisci Password Correttamente\n";
			}
			else if(psw.length()<2 || psw.length()>20){
				error+="Lunghezza password non valida \n";
			}
			else {
				int i;
				for(i=0;i<psw.length();i++) {
					char ch=psw.charAt(i);
					if(psw.contains(" ")) {
						error+="formato password non valido \n";
						break;
					}
				}
			}
			
			
			if(error==null || !error.trim().equals("")){
				request.setAttribute("error", error);
				RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/admin/ErroreInserimento.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			
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
			String error="";
			String sigla=request.getParameter("Sigla");
			String mail=request.getParameter("mail");
			String username=request.getParameter("username");
			String psw=request.getParameter("psw");
			
			int a=0;
			String[] arr= new String[5];
			
			if(mail.contains("@")) {
				 arr=  mail.split("@");
			}
			else {
				error+="Formato mail non valido";
				a=1;
			}

			//CONTROLLO SIGLA UFFICIO
			if(sigla == null || sigla.trim().equals("")) {
				error+= "Inserisci sigla Correttamente\n";
			}
			else if(sigla.length()<2 || sigla.length()>50){
				error+="Lunghezza sigla non valida \n";
			}
			else {
				int i;
				for(i=0;i<sigla.length();i++) {
					char ch=sigla.charAt(i);
					if(!Character.isLetter(ch) && !Character.isSpaceChar(ch)) {
						error+="formato sigla non valido \n";
						break;
					}
				}
			}
			
			
			//CONTROLLO MAIL
			if(mail == null || mail.trim().equals("")) {
				error+= "Inserisci mail Correttamente\n";
			}
			else if(!mail.contains("@")) {
				error+="format mail non valido \n";
			}
			else {
				int i;
				for(i=0;i<arr[0].length();i++) {
					char ch=arr[0].charAt(i);
					if(!Character.isLetter(ch) && !Character.isSpaceChar(ch)) {
						error+="formato mail non valido \n";
						break;
					}
				}
			}
			
			//CONTROLLO CAMPO USERNAME
			if(username == null || username.trim().equals("")) {
				error+= "Inserisci Username \n"; 
			}else if(username.length()<2 || username.length()>20){
				error+="Lunghezza username non valida \n";
			}
			else {
				int i;
				for(i=0;i<username.length();i++) {
					char ch=username.charAt(i);
					if(username.contains(" ")) {
						error+="formato username non valido \n";
						break;
					}
				}
			}

			//CONTROLLO CAMPO PASSWORD
			if(psw == null || psw.trim().equals("")) {
				error+= "Inserisci Password Correttamente\n";
			}
			else if(psw.length()<2 || psw.length()>20){
				error+="Lunghezza password non valida \n";
			}
			else {
				int i;
				for(i=0;i<psw.length();i++) {
					char ch=psw.charAt(i);
					if(psw.contains(" ")) {
						error+="formato password non valido \n";
						break;
					}
				}
			}
			
			
			if(error==null || !error.trim().equals("")){
				request.setAttribute("error", error);
				RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/admin/ErroreInserimento.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			try {
				model.aggiungiUfficio(gestore, sigla, mail, username, psw);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/SuccessoAggiunta.jsp"); 
				dispatcher.forward(request, response);
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
