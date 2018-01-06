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

import bean.StudenteBean;
import bean.TirocinioBean;
import model.DocumentoModel;
import model.NotificaModel;
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
		TirocinioBean tirocinio= new TirocinioBean();
		
		try {
			StudenteBean studente= model1.doRetrieveByMatricola(matricola);
			tirocinio=model.doRetrieveByDocument(docu);
			String nomeAzienda=tirocinio.getAzienda();
			model.cancellaTirocinio(model.doRetrieveByDocument(docu).getCodice());
			model1.aggiornaOffertaFormativa(-1, matricola);
			modelDoc.deleteDoc(docu);

			NotificaModel modelNot= new NotificaModel();
			modelNot.aggiungiNotifica("Il Presidente ha rifiutato la richiesta di tirocinio di"+studente.getNome()+" "+studente.getCognome(),"RifiutaP", null, null, null, nomeAzienda, matricola);
			
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
