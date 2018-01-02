<%@ page import="model.OffertaFormativaModel, java.util.*, bean.OffertaFormativaBean, model.TutorEsternoModel, bean.TutorEsternoBean " %> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="/Sostage/style.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SOSTAGE</title>
</head>
<body>

<%
	String sede= request.getParameter("sede");
	request.getSession().setAttribute("sede", sede);
	String tema= request.getParameter("Tema");
	request.getSession().setAttribute("Tema", tema);
	
	TutorEsternoModel model1= new TutorEsternoModel(); 
	OffertaFormativaModel model= new OffertaFormativaModel();
	Collection<OffertaFormativaBean> products=(ArrayList<OffertaFormativaBean>)model.trovaOfferte(sede,tema);
	String tutorEsterno="";
	
	%>

		
	
	
		<h2 id="titleInfo">Offerte</h2>
		<table id="offerteDiv">
			<tr>
				<th>Nome </th>
				<th>Sede </th>
				<th>Tema </th>
				<th>Obiettivi</th>
				<th>Azienda</th>
				<th>Tutor Esterno</th>
			</tr>
		<%
			
			if(products!= null && products.size() > 0){
					Iterator<?> it= products.iterator();
					while(it.hasNext()){
						OffertaFormativaBean bean= (OffertaFormativaBean) it.next();
						TutorEsternoBean tutor= model1.doRetrieveKey(bean.getTutorEsterno());
						tutorEsterno=""+tutor.getNome()+" "+tutor.getCognome();
					%>
						<tr>
							<td><%= bean.getNome()  %></td>
							<td><%= bean.getSede() %></td>
							<td><%= bean.getTema() %></td>
							<td><%= bean.getObiettivi() %></td>
							<td><%= bean.getAzienda() %></td>
							<td><%= tutorEsterno %></td>
							
						</tr>
						
					<% 
					}
			}
			
			else{
			%>
				<tr>
					<th colspan="4">Nessun offerta disponibile</th>
				</tr>
			<%	
			}
		%>
		</table>

</body>
</html>

