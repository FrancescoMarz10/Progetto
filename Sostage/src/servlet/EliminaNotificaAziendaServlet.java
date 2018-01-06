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

import bean.NotificaBean;
import model.NotificaModel;

/**
 * Servlet implementation class EliminaNotificaAziendaServlet
 */
@WebServlet("/azienda/EliminaNotificaAziendaServlet")
public class EliminaNotificaAziendaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public EliminaNotificaAziendaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tipo=request.getParameter("Tipo");
		int ID=Integer.parseInt(request.getParameter("ID"));
		NotificaModel model= new NotificaModel();
		
		try {
			NotificaBean bean=model.doRetrieveByID(ID);
			
			if(tipo.equals("AccettaP") && bean.getStudente()!=null) {
				model.aggiornaAzienda(ID);
			}
			else if(tipo.equals("RifiutaP") && bean.getStudente()!=null){
				model.aggiornaAzienda(ID);
			}
			else {
				model.cancellaNotifica(ID);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(tipo.equals("Richiesta")) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/azienda/VisualizzaDocumentiAzienda.jsp"); 
			dispatcher.forward(request, response);
			return;
		}
		else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/azienda/HomeAzienda.jsp"); 
			dispatcher.forward(request, response);
			return;
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
