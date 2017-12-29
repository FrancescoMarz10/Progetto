package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UtenteBean;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ruolo=(String)request.getSession().getAttribute("ruolo");
		//request.getSession().setAttribute("bean", bean);
		if(ruolo.equals("Studente")){
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/studente/HomeStudente.jsp");
			dispatcher.forward(request, response);
		}
		else if(ruolo.equals("gestore")){
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/admin/HomeAdmin.jsp");
			dispatcher.forward(request, response);
		}
		else if(ruolo.equals("Azienda")){
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/azienda/HomeAzienda.jsp");
			dispatcher.forward(request, response);
		}
		else if(ruolo.equals("Presidente")){
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/presidente/HomePresidente.jsp");
			dispatcher.forward(request, response);
		}
		else if(ruolo.equals("TutorEsterno")){
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/tutorEsterno/HomeTutorEsterno.jsp");
			dispatcher.forward(request, response);
		}
		else if(ruolo.equals("TutorInterno")){
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/tutorInterno/HomeTutorInterno.jsp");
			dispatcher.forward(request, response);
		}
		else if(ruolo.equals("Ufficio")){
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/ufficio/HomeUfficio.jsp");
			dispatcher.forward(request, response);
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

