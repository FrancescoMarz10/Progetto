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

import bean.OffertaFormativaBean;
import bean.StudenteBean;
import bean.TirocinioBean;
import bean.UtenteBean;
import model.DocumentoModel;
import model.NotificaModel;
import model.OffertaFormativaModel;
import model.StudenteModel;
import model.TirocinioModel;


@WebServlet("/studente/RichiestaTirocinioServlet")
public class RichiestaTirocinioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private File file;
	String [] array= new String[3];
	
    public RichiestaTirocinioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteBean bean= (UtenteBean) request.getSession().getAttribute("bean");
		StudenteModel model= new StudenteModel();
		String matricola="";
		String nomeDoc="";
		String nome="";
		String cognome="";
		try {
				StudenteBean studente= model.doRetrieveByUsername(bean.getUsername());
				matricola=studente.getMatricola();
				nome=studente.getNome();
				cognome=studente.getCognome();
				nomeDoc=matricola+".pdf";
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		boolean isMultipart;
		String filePath;
		int maxFileSize= 5000*1024;
		int maxMemSize=50*1024;
		filePath=getServletContext().getInitParameter("file_upload");
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
						
						file= new File(filePath+matricola+fileName.substring(fileName.lastIndexOf(".")));
						
					}
					else {
						String tipoFile=fileName.substring(fileName.lastIndexOf("."));
						System.out.println(tipoFile);
						if(!tipoFile.equals(".pdf\\")) {
							throw new ServletException();
						}
						
						
						file= new File(filePath+matricola+fileName.substring(fileName.lastIndexOf(".")+1));
					
					}
				
					fi.write(file);
					//out.print("File Uploaded!"+fileName);
				}
				else {
				    String valueTI = fi.getString();
				    array[a]=valueTI;
				    System.out.println(array[a]);
				    a++;
				}
			}
				
				
				OffertaFormativaModel modelOff= new OffertaFormativaModel();
				OffertaFormativaBean offerta= modelOff.doRetrieveByID(Integer.parseInt(array[0]));
				TirocinioBean tirocinio= new TirocinioBean();
				TirocinioModel mod= new TirocinioModel();
				DocumentoModel modelDoc= new DocumentoModel();
				NotificaModel modelNot= new NotificaModel();
				tirocinio.setMateria(offerta.getTema());
				tirocinio.setPeriodo(array[2]);
				tirocinio.setLuogo(offerta.getSede());
				tirocinio.setStudente(matricola);

				System.out.println(nomeDoc);
				int codice=mod.doSave(tirocinio);
				mod.aggiornaStudente(matricola, codice);
				modelDoc.doSave(nomeDoc);
				mod.aggiornaDocumento(nomeDoc, codice);
				model.aggiornaOffertaFormativa(offerta.getID(), matricola);
				model.aggiornaTutorInterno(array[1], matricola);
				
				modelNot.aggiungiNotifica("Lo Studente "+ nome+" "+cognome+" ha effettuato una richiesta di tirocinio","Richiesta", null, null, null, offerta.getAzienda(), matricola);
				
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/studente/successoCaricamento.jsp"); 
				dispatcher.forward(request, response);
				return;
			
		}
		catch(Exception e) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/studente/ErroreCaricamento.jsp"); 
			dispatcher.forward(request, response);
			return;
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
