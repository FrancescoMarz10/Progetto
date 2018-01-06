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


@WebServlet("/tutorEsterno/EliminaNotificaTEServlet")
public class EliminaNotificaTEServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	
    public EliminaNotificaTEServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tipo=request.getParameter("Tipo");
		int ID=Integer.parseInt(request.getParameter("ID"));
		NotificaModel model= new NotificaModel();
		
		try {
			model.cancellaNotifica(ID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/tutorEsterno/VisualizzaDocumentiTutorEsterno.jsp"); 
			dispatcher.forward(request, response);
			return;
		
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
