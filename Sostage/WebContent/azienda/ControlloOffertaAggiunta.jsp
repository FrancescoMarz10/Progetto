<%@ page import="bean.NotificaBean,model.NotificaModel,java.util.*,bean.AziendaBean, bean.UtenteBean, model.AziendaModel,bean.OffertaFormativaBean, model.OffertaFormativaModel" %> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="icon" href="/Sostage/images/icon.png">
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
			AziendaModel model= new AziendaModel();
			AziendaBean azienda=model.doRetrieveByUsername(bean.getUsername());
			NotificaModel modelNot = new NotificaModel(); 
			ArrayList<NotificaBean> notifiche= modelNot.trovaNotificheAzienda(azienda.getNome());
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
			<p>Non ci sono notifiche </p>
		<%		
			}else{
		%>		
			
		<% 			
					for(NotificaBean notifica: notifiche){	
						System.out.println(notifica.getTipo());
						if(notifica.getTipo().equals("AccettoP") || notifica.getTipo().equals("RifiutoP")){
		%>		
					<div>
						<p><%=notifica.getTesto() %> </p>
						<form action="/Sostage/azienda/EliminaNotificaAziendaServlet" method="post">
								  <input type="hidden" value="<%=notifica.getTipo() %>" name="Tipo">
								  <input type="hidden" value="<%=notifica.getID() %>" name="ID">
								  <input type="submit" value="Elimina Notifica">
						</form>
					</div>
		<% 				
			
						}else if(notifica.getTipo().equals("Richiesta")){
		%>		
						<a href="#"><div> 
						
							<p><%=notifica.getTesto() %></p>
							<a href="/Sostage/azienda/VisualizzaNotificaAzienda.jsp?ID=<%=notifica.getID()%>"><button>Visualizza Notifica</button></a>
							
						</div></a>
					
		<% 					

						}
			
						}
					}
		%>
				
	
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
		
				<div id="info">
					<%
						OffertaFormativaBean offerta=(OffertaFormativaBean)session.getAttribute("offerta");
						OffertaFormativaModel model1= new OffertaFormativaModel();
					
					%>
					<img id="industry" src="/Sostage/images/industry.png" alt="industry"><h2 id="benv">Benvenuto <%=azienda.getUsername()  %> !</h2>
					<br>
					<ul>
					  <li><p id="we">Nome: <%=azienda.getNome() %></p></li>
					  <li><p id="we">Sede: <%=azienda.getSede()%></p></li>
					  <li><p id="we">Mail: <%=bean.getMail()%></p></li>
					  <li><p id="we">Nome Responsabile: <%=azienda.getNomeResp()%></p></li>
					  
					</ul>
					
				</div>
				
				<div id="opDiv">
					 <h2>OFFERTA DA INSERIRE</h2>
					<form id="aggiungiOffertaFormativa" action="ConfermaOffertaServlet" method="post">
					 <h3>Nome: <%=offerta.getNome() %></h3>
					 <h3>Sede: <%=offerta.getSede() %></h3>
					 <h3>Tema: <%=offerta.getTema() %></h3>
					 <h3>Obiettivi: <%=offerta.getObiettivi() %></h3>
					 <h3>Modalità di Svolgimento: <%=offerta.getModalitàSvolgimento() %></h3> 
					 <h3>Tutor Esterno: <%=offerta.getTutorEsterno() %></h3>

			   			<input id="buttonAggiungiOfferta" type="submit" value="Conferma Offerta"/>
					
					</form>
						
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
			<a href="/Sostage/azienda/InfoAzienda.jsp"><p><i class="fa fa-info-circle"></i> info</p></a>
			<a href="#"><p><i class="fa fa-address-book"></i> contatti</p></a>
			
		</div>
	
	</footer>
</body>

</html>