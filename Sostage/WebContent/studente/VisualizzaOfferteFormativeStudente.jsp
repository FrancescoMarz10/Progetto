<%@ page import="bean.UtenteBean, bean.NotificaBean, model.NotificaModel,model.StudenteModel, bean.StudenteBean, bean.AziendaBean, model.AziendaModel, java.util.*" %> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="icon" href="images/icon.png">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<!-- COLLEGAMENTO AL FILE JAVASCRIPT -->
	<script type="text/javascript" src="/Sostage/script.js"></script>
	
	<!-- COLLEGAMENTO AL FILE CSS -->
	<link rel="stylesheet" href="/Sostage/style.css" type="text/css">
	
	<!-- COLLEGAMENTO AL FILE PER L'USO DEL FONT "BOMBARDIER" -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
<title>SOSTAGE</title>
</head>
<body>
	<div class="header">
		<img id="logo" alt="immagine" src="/Sostage/images/LogoMotto.png">
		
		<div id="dipinfo">
			<a href="http://www.di-srv.unisa.it/" target="_blank"><img alt="informaticapic" id="logoInfo" src="/Sostage/images/informatica.png"></a>
		</div>
	
<!-- NOTIFICHE -->
	
		
		<img id="notifiche" alt="notifiche" src="/Sostage/images/notifiche.png" onclick="apriNotifiche()">
		<%
			UtenteBean bean=(UtenteBean)session.getAttribute("bean");
			StudenteModel model= new StudenteModel();
			StudenteBean studente= model.doRetrieveByUsername(bean.getUsername());
			NotificaModel modelNot = new NotificaModel(); 
			ArrayList<NotificaBean> notifiche= modelNot.trovaNotificheStudente(studente.getMatricola());
			int n=notifiche.size();
		
			if(!notifiche.isEmpty()){
		%>	
			<div id="numNotifiche" style="align:center;" onclick="apriNotifiche()"><%=n%></div>
		<%	
			}	
		%>
		
		<div id="menu" style="display:none">
			<div id="titleInfo2">Notifiche</div>
			<hr>
		<%	
			if(notifiche==null || notifiche.isEmpty()){
		%>		
			<p>non ci sono notifiche </p>
		<%		
			}else{
		%>		
				
		<% 			
					for(NotificaBean notifica: notifiche){	
						System.out.println(notifica.getTipo());
				
		%>		
						
						<div>
							<p><%=notifica.getTesto() %> </p>
							<form action="/Sostage/studente/EliminaNotificaStudenteServlet" method="post">
								  <input type="hidden" value="<%=notifica.getID() %>" name="ID">
								   <input type="hidden" value="<%=notifica.getTipo() %>" name="Tipo">
								  <input type="submit" value="Elimina Notifica">
							</form>
							
						</div>
	
		<%			
						}
					}
		%>
		
		
	</div>

	<form  id="logoutForm" action="/Sostage/LogoutServlet" method="post">	
		<button type="submit" id="logout"><i class="fa fa-user-o"></i> Logout</button>
	</form>
	
	<nav id="bar">
		<a href="/Sostage/studente/HomeStudente.jsp"><div class="elebar"> <p>H O M E</p> </div></a>
		<a href="/Sostage/studente/InfoStudente.jsp"><div class="elebar"> <p>I N F O</p> </div></a>
		<a href="#"><div class="elebar"> <p>A B O U T - U S</p> </div></a>
	</nav>
	
	<div class="container">
			<%
	
	AziendaModel model2= new AziendaModel();
	Collection<AziendaBean> aziende=(Collection<AziendaBean>)model2.doRetrieveAll();
	%>

		
	
	
	<div id="titleInfo">Aziende Convenzionate</div>
		<table id="azConv" >
			<tr>
				<th>Nome</th>
				<th>Nome Responsabile</th>
				<th>Sede</th>
				<th>Azione</th>
			</tr>
		<%
			
			if(aziende!= null && aziende.size() > 0){
					Iterator<?> it= aziende.iterator();
					while(it.hasNext()){
						AziendaBean bean2= (AziendaBean) it.next();
					%>
						<tr>
							<td><%= bean2.getNome()  %></td>
							<!-- Mail -->
							<td><%= bean2.getNomeResp() %></td>
							<td><%= bean2.getSede() %></td>
							<td><a href="MostraOfferteServlet?nome=<%=bean2.getNome()%>">Visualizza</a><br></td>
						</tr>
						
					<% 
					}
			}
			
			else{
			%>
				<tr>
					<th colspan="4">No Product Available</th>
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
			<a href="/Sostage/studente/InfoStudente.jsp"><p><i class="fa fa-info-circle"></i> info</p></a>
			<a href="/Sostage/studente/AboutUsStudente.jsp"><p><i class="fa fa-address-book"></i> about us</p></a>
			
		</div>
	
	</footer>
</body>
	

</html>

