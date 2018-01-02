package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.OffertaFormativaBean;
import model.AziendaModel;

@WebServlet("/studente/MostraOfferteServlet")
public class MostraOfferteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AziendaModel model= new AziendaModel();
    public MostraOfferteServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome=request.getParameter("nome");
		ArrayList<OffertaFormativaBean> offerte= new ArrayList<OffertaFormativaBean>();
		
		try {
			offerte=(ArrayList<OffertaFormativaBean>) model.trovaOfferteFormative(nome);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getSession().setAttribute("offerte", offerte);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/studente/OfferteFormative.jsp"); 
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

