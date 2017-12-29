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
		String[] arr= mail.split("@");
		
		if(!matricola.equals(""))
		  first=matricola.substring(0,5);
		
		if(username == null || username.trim().equals("")) {
			error+= "Inserisci Username Correttamente<br>"; 
		} else {
			request.setAttribute("username", username);
		}

		if(password == null || password.trim().equals("")) {
			error+= "Inserisci Password Correttamente<br>";
		}
		
		if(nome == null || nome.trim().equals("")) {
			error+= "Inserisci Nome Correttamente<br>";
		}
		if(surname== null || surname.trim().equals("")) {
			error+= "Inserisci Cognome Correttamente<br>";
		}
		if(matricola== null || matricola.trim().equals("") || !first.equals("05121")) {
			error+= "Inserisci Matricola Correttamente <br>";
		}
		if(mail == null || mail.trim().equals("") ||  !arr[1].equals("studenti.unisa.it")) {
			error+= "Inserisci Mail Correttamente <br>";
			
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


