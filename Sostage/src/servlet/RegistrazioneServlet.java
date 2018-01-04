package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.StudenteBean;
import model.StudenteModel;
import model.UtenteModel;

@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      boolean flag1=false;
	 boolean flag=false;
	static StudenteModel model=new StudenteModel();
    
    public RegistrazioneServlet() {
        super();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error= "";
		String matricola=request.getParameter("matricola");
		System.out.println("matricola"+matricola);
		String surname=request.getParameter("surname");
		String nome=request.getParameter("nome");
		String mail=request.getParameter("mail");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String first="";
		String[] arr= new String[5];
		int a=0;
		
		if(mail.contains("@")) {
			 arr=  mail.split("@");
		}
		else {
			error+="Formato mail non valido";
			a=1;
		}
		
		if(!matricola.equals(""))
		  first=matricola.substring(0,5);
		
		//CONTROLLO CAMPO USERNAME
		if(username == null || username.trim().equals("")) {
			error+= "Inserisci Username <br>"; 
		}else if(username.length()<2 || username.length()>20){
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

		//CONTROLLO CAMPO PASSWORD
		if(password == null || password.trim().equals("")) {
			error+= "Inserisci Password Correttamente<br>";
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
		
		//CONTROLLO CAMPO NOME
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
		
		//CONTROLLO CAMPO COGNOME
		if(surname== null || surname.trim().equals("")) {
			error+= "Inserisci Cognome Correttamente<br>";
		}
		else if(surname.length()<2 || surname.length()>50){
			error+="Lunghezza cognome non valida <br>";
		}
		else {
			int i;
			for(i=0;i<surname.length();i++) {
				char ch=surname.charAt(i);
				if(!Character.isLetter(ch) && !Character.isSpaceChar(ch)) {
					error+="formato cognome non valido <br>";
					break;
				}
			}
		}
		
		//CONTROLLO CAMPO MATRICOLA
		if(matricola== null || matricola.trim().equals("") || !first.equals("05121")) {
			error+= "Inserisci Matricola Correttamente <br>";
		}
		else if(matricola.length()<10){
			error+="Lunghezza matricola non valida <br>";
		}
		else {
			int i;
			for(i=0;i<matricola.length();i++) {
				char ch=matricola.charAt(i);
				if(!Character.isDigit(ch) && !Character.isSpaceChar(ch)) {
					error+="formato matricola non valido <br>";
					break;
				}
			}
		}
		
		//CONTROLLO CAMPO MAIL
		if(mail == null || mail.trim().equals("")) {
			error+= "Inserisci Mail Correttamente <br>";
		}
		else if(mail.length()<19 || mail.length()>50){
			error+="Lunghezza mail non valida <br>";
		}
		else if(a==0) {
				if(!arr[1].equals("studenti.unisa.it")) {
				error+="Formato mail non valido";
			}
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
		
		
		//CONTROLLA CONDIZIONE
		if(error==null || !error.trim().equals("")){
			request.setAttribute("error", error);
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/Registrati.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
	
		
		StudenteBean bean= new StudenteBean(username,password,matricola,nome,surname);
		
		try {
			flag=model.alreadyExists(bean);
			System.out.println("esiste?"+flag);

			
		} catch (SQLException e1) {
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/FailReg.jsp");
			dispatcher.forward(request, response);
		}
		
		
		if(flag==true){
			try {
				model.doSave(bean,mail);
				
				RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/Successo.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}	
		}
		else{
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/FailRegistrazione.jsp");
			dispatcher.forward(request, response);
		}

	}
	
}


