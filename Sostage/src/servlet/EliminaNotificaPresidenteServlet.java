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


@WebServlet("/presidente/EliminaNotificaPresidenteServlet")
public class EliminaNotificaPresidenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public EliminaNotificaPresidenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ID=Integer.parseInt(request.getParameter("ID"));
		NotificaModel model= new NotificaModel();
		
		try {
			model.cancellaNotifica(ID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/presidente/VisualizzaDocumentiPresidente.jsp"); 
			dispatcher.forward(request, response);
			return;
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
