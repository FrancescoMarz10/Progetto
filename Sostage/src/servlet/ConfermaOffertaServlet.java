package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.OffertaFormativaBean;
import bean.UtenteBean;
import model.AziendaModel;

/**
 * Servlet implementation class ConfermaServlet
 */
@WebServlet("/azienda/ConfermaOffertaServlet")
public class ConfermaOffertaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  AziendaModel model= new AziendaModel();
    
    public ConfermaOffertaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OffertaFormativaBean offerta=(OffertaFormativaBean) request.getSession().getAttribute("offerta");
		
		try {
			model.aggiungiOffertaFormativa(offerta.getNome(), offerta.getSede(), offerta.getTema(), offerta.getObiettivi(), offerta.getModalit‡Svolgimento(), offerta.getTutorEsterno(), offerta.getAzienda());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/azienda/HomeAzienda.jsp"); 
			dispatcher.forward(request, response);
			return;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
