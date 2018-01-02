<%@ page import="model.AziendaModel,bean.OffertaFormativaBean, model.OffertaFormativaModel, java.util.*, bean.UtenteBean,  model.AziendaModel" %> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="icon" href="images/icon.png">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/Sostage/style.css" type="text/css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>SOSTAGE</title>
</head>
<body>
	<div class="header">
		<img id="logo" alt="immagine" src="/Sostage/images/LogoMotto.png">
		
		<div id="dipinfo">
			<a href="http://www.di-srv.unisa.it/" target="_blank"><img alt="informaticapic" id="logoInfo" src="/Sostage/images/informatica.png"></a>
		</div>
		
	<form  id="logoutForm" action="/Sostage/LogoutServlet" method="post">	
		<button type="submit" id="logout"><i class="fa fa-user-o"></i> Logout</button>
	</form>
	
	<nav id="bar">
		<a href="/Sostage/azienda/HomeAzienda.jsp"><div class="elebar"> <p>H O M E</p> </div></a>
		<a href="/Sostage/azienda/InfoAzienda.jsp"><div class="elebar"> <p>I N F O</p> </div></a>
		<a href="#"><div class="elebar"> <p>A B O U T - U S</p> </div></a>
	</nav>
	
	<div class="container">
			<%
	
	
	OffertaFormativaModel model= new OffertaFormativaModel();
	AziendaModel model1=new AziendaModel();
	UtenteBean utente=(UtenteBean) request.getSession().getAttribute("bean");
	String azienda=model1.doRetrieveByUsername(utente.getUsername()).getNome();
	Collection<OffertaFormativaBean> offerte=(ArrayList<OffertaFormativaBean>)model.trovaOfferteAzienda(azienda);
	%>

		
	
	
	<div id="titleInfo">Offerte Formative</div>
		<table id="offForm" >
			<tr>
				<th>Nome</th>
				<th>Sede</th>
				<th>Tema</th>
				<th>Obiettivi</th>
				<th>Modalitā Svolgimento</th>
				<th>Tutor Esterno</th>
				<th>Azione</th>
			</tr>
		<%
			
			if(offerte!= null && offerte.size() > 0){
					Iterator<?> it= offerte.iterator();
					while(it.hasNext()){
						OffertaFormativaBean bean= (OffertaFormativaBean) it.next();
					%>
						<tr>
							<td><%= bean.getNome()%></td>
							<td><%= bean.getSede() %></td>
							<td><%= bean.getTema() %></td>
							<td><%= bean.getObiettivi() %></td>
							<td><%= bean.getModalitāSvolgimento() %></td>
							<td><%= bean.getTutorEsterno() %></td>
							
							
							<td><a href="RimuoviOffertaFormativaServlet?ID=<%=bean.getID()%>"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/8/8b/Red_X_Freehand.svg/2000px-Red_X_Freehand.svg.png" id="X"></a>		</tr>
						
					<% 
					}
			}
			
			else{
			%>
				<tr>
					<th colspan="4">Nessun utente registrato</th>
				</tr>
			<%	
			}
		%>
		</table>

	<br>
	<br>
				
	</div>
	
	
	<hr id="hr">
	
	<footer id="footer">
	
		<div id="foot1">
			<a href="http://www.unisa.it/" target="_blank"><img alt="informaticapic" id="logoUnisa" src="/Sostage/images/logoUnisa2.png"></a>
		</div>
		
		<div id="foot2">
			<p><i class="fa fa-envelope"></i> sostage@unisa.it</p>
			<p><i class="fa fa-home"></i> 84084 Fisciano (SA) - ITALY</p>
			<p><i class="fa fa-location-arrow"></i> Via Giovanni Paolo II, 132</p>
			<p><i class="fa fa-phone"></i> 089 961111</p>
		</div>
	
		<div id="foot3">
			<a href="#"><p><i class="fa fa-home"></i> home</p></a>
			<a href="Sostage/azienda/InfoAzienda.jsp"><p><i class="fa fa-info-circle"></i> info</p></a>
			<a href="Sostage/azienda/AboutUsAzienda.jsp"><p><i class="fa fa-address-book"></i> about us</p></a>
			
		</div>
	
	</footer>
</body>
	

</html>

