package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DocumentoModel;
import model.StudenteModel;
import model.TirocinioModel;

/**
 * Servlet implementation class RifiutaTirocinioPresidenteServlet
 */
@WebServlet("/presidente/RifiutaTirocinioPresidenteServlet")
public class RifiutaTirocinioPresidenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RifiutaTirocinioPresidenteServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String matricola=request.getParameter("studente");
		String docu=matricola+".pdf";
		StudenteModel model1= new StudenteModel();
		DocumentoModel modelDoc= new DocumentoModel();
		TirocinioModel model = new TirocinioModel();
		try {
			model.cancellaTirocinio(model.doRetrieveByDocument(docu).getCodice());
			model1.aggiornaOffertaFormativa(-1, matricola);
			modelDoc.deleteDoc(docu);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/presidente/VisualizzaDocumentiPresidente.jsp");
		dispatcher.forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
