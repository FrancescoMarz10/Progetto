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
import model.GestoreModel;
import model.UtenteModel;


@WebServlet("/admin/RimuoviUtenteServlet")
public class RimuoviUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GestoreModel model= new GestoreModel();
	UtenteModel model1= new UtenteModel();
	
    public RimuoviUtenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
	
		try {
			UtenteBean bean= model1.doRetrieveByUsername(username);
			model.deleteUser(bean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/admin/RimuoviUtente.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
