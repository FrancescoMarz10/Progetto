package servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ScaricaDocumentoServlet")
public class ScaricaDocumentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    final String  FILE_LOCATION="C:\\Users\\utente\\workspace\\Sostage\\WebContent\\Files";
    
    public ScaricaDocumentoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
Object requestObject= request.getParameter("filename");
		
		if(requestObject!=null) {
			String fileName=(String) requestObject+".pdf";
			System.out.println(fileName);
			String contentType="application/pdf";
			File file= new File(FILE_LOCATION+"\\"+fileName);
			response.setContentType(contentType);
			response.addHeader("Content-Disposition", "attachment;filename="+fileName);
			response.setContentLength((int) file.length());
			ServletOutputStream servletOutputStream= response.getOutputStream();
			BufferedInputStream bufferedInputStream= new BufferedInputStream(new FileInputStream(file));
			int bytesRead= bufferedInputStream.read();
			
			while(bytesRead!=-1) {
				servletOutputStream.write(bytesRead);
				bytesRead=bufferedInputStream.read();
			}
			
			if(servletOutputStream!=null) {
				servletOutputStream.close();
			}
			if(bufferedInputStream!=null) {
				bufferedInputStream.close();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
