package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.xml.sax.SAXException;

import bean.AziendaBean;
import bean.OffertaFormativaBean;
import bean.StudenteBean;
import bean.TirocinioBean;
import bean.UtenteBean;
import model.AziendaModel;
import model.DocumentoModel;
import model.NotificaModel;
import model.OffertaFormativaModel;
import model.StudenteModel;
import model.TirocinioModel;

/**
 * Servlet implementation class CaricaDocumentoAziendaServlet
 */
@WebServlet("/azienda/CaricaDocumentoAziendaServlet")
public class CaricaDocumentoAziendaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private File file;
	String first="";

    public CaricaDocumentoAziendaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteBean bean= (UtenteBean) request.getSession().getAttribute("bean");
		AziendaModel modAz= new AziendaModel();
		AziendaBean azienda;
		String nomeAzienda="";
		String matricola="";
		
		try {
			azienda = modAz.doRetrieveByUsername(bean.getUsername());
			nomeAzienda=azienda.getNome();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		StudenteModel model= new StudenteModel();
		TirocinioModel model1=new TirocinioModel();
		TirocinioBean tirocinio=new TirocinioBean();
		
		boolean isMultipart;
		String filePath;
		int maxFileSize= 5000*1024;
		int maxMemSize=50*1024;
		filePath= getServletContext().getInitParameter("file_upload");
		isMultipart=ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");
		
		PrintWriter out= response.getWriter();
		
		if(!isMultipart) {
				out.print("file_not_uploaded");
				return;
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(maxFileSize);
		factory.setRepository(new File("C:\\temp"));
		ServletFileUpload upload= new ServletFileUpload(factory);
		upload.setSizeMax(maxFileSize);
		
		
		try {
		    List fileItems = upload.parseRequest(request);
			Iterator i= fileItems.iterator();
			int a=0;
			while(i.hasNext()) {
				FileItem fi= (FileItem) i.next();
				if(!fi.isFormField()) {
					String fieldName=fi.getFieldName();
					String fileName=fi.getName()+"\\";
					String contentType=fi.getContentType();
					boolean isMemory=fi.isInMemory();
					long sizeInBytes= fi.getSize();
					
					if(fileName.lastIndexOf("\\")>=0) {
						String tipoFile=fileName.substring(fileName.lastIndexOf("."));
						System.out.println(tipoFile);
						if(!tipoFile.equals(".pdf\\")) {
							throw new ServletException();
						}
						
						
						
						file= new File(filePath+fileName);
						tirocinio= model1.doRetrieveByDocument(fi.getName());
						matricola=fi.getName().substring(0, 10);
					}
					else {
						String tipoFile=fileName.substring(fileName.lastIndexOf("."));
						System.out.println(tipoFile);
						if(!tipoFile.equals(".pdf\\")) {
							throw new ServletException();
						}
						
						
						
						file= new File(filePath+fileName);
						tirocinio= model1.doRetrieveByDocument(fi.getName());					
					}
				
					fi.write(file);
					//out.print("File Uploaded!"+fileName);
				}
				
			}
				int codice=tirocinio.getCodice();
				model1.aggiornaAzienda(nomeAzienda, codice);
				OffertaFormativaModel modelOff= new OffertaFormativaModel();
				String tutorEsterno=modelOff.doRetrieveByID(model.doRetrieveByMatricola(matricola).getOffertaFormativa()).getTutorEsterno();
				
				NotificaModel modelNot= new NotificaModel();
				modelNot.aggiungiNotifica("L'azienda "+nomeAzienda+ " ha accettato la tua richiesta di tirocinio","AccettaA", null, null, null, nomeAzienda, matricola);
				modelNot.aggiungiNotifica("Si necessita della sua firma per la richiesta dello studente: "+ model.doRetrieveByMatricola(matricola).getNome()+ " "+model.doRetrieveByMatricola(matricola).getCognome(),"FirmaTE", null, null, tutorEsterno, nomeAzienda, null);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/azienda/successoCaricamentoAzienda.jsp"); 
				dispatcher.forward(request, response);
				return;
			
		}
		catch(Exception e) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/azienda/ErroreCaricamento.jsp"); 
			dispatcher.forward(request, response);
			return;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
