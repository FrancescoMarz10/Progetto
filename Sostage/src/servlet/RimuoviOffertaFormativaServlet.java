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

import model.OffertaFormativaModel;

/**
 * Servlet implementation class RimuoviOffertaFormativa
 */
@WebServlet("/azienda/RimuoviOffertaFormativaServlet")
public class RimuoviOffertaFormativaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OffertaFormativaModel model= new OffertaFormativaModel();

    public RimuoviOffertaFormativaServlet() {
        super();
     
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ID=Integer.parseInt(request.getParameter("ID"));
		
		try {
			model.cancellaOfferta(ID);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/azienda/RimuoviOffertaFormativa.jsp"); 
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
