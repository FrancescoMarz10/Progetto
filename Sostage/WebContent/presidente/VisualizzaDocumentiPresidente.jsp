<%@ page import="bean.StudenteBean, java.util.*,bean.PresidenteBean, bean.UtenteBean, model.PresidenteModel" %> 
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
		<a href="/Sostage/presidente/HomePresidente.jsp"><div class="elebar"> <p>H O M E</p> </div></a>
		<a href="/Sostage/presidente/InfoPresidente.jsp"><div class="elebar"> <p>I N F O</p> </div></a>
		<a href="#"><div class="elebar"> <p>A B O U T - U S</p> </div></a>
	</nav>
	
	<div class="container">
			<div id="info">
					<%
						UtenteBean bean=(UtenteBean)session.getAttribute("bean"); 
						PresidenteModel model= new PresidenteModel();
						PresidenteBean presidente=model.doRetrieveByUsername(bean.getUsername());
					%>
					<img id="usericon" src="/Sostage/images/user.png" alt="superman"><h2 id="benv">Benvenuto <%=presidente.getUsername()  %> !</h2>
					<br>
					<ul>
					  <li><p id="we">Nome: <%=presidente.getNome() %></p></li>
					  <li><p id="we">Cognome: <%=presidente.getCognome()%></p></li>
					  <li><p id="we">Mail: <%=bean.getMail()%></p></li>
					  
					</ul>
					
				</div>
				
				<div id="Documenti">
					<img id="picDoc" src="/Sostage/images/doc.png">
					<br>
					<form id="scegliRichiesta" action="/Sostage/ScaricaDocumentoServlet" method="post">
							
						<label id="Studente">Studente:</label>
						<select id="filename" name="filename">
						 		<% 
								Collection<StudenteBean> studenti=(ArrayList<StudenteBean>)model.trovaStudentiConRichiesta();

									if(studenti!= null && studenti.size() > 0){
											Iterator<?> it= studenti.iterator();
											while(it.hasNext()){
												StudenteBean studente= (StudenteBean) it.next();
									%>
									<option value="<%=studente.getMatricola()%>"><%=studente.getNome()+" "+studente.getCognome() %></option>
								
								 			
									<% 
										}
									}
									%>
						
						</select>	
						<br> <br>
						<input id="BottoneScarica" type="submit" value="Scarica Documento"/>
					</form>
					<br>
					<br>
					<form id="caricaAzienda" action="CaricaDocumentoPresidenteServlet" method="post" enctype="multipart/form-data">
					 	Scegli file: <input  type="file" name="file1">
						 	 <input id="BottoneCarica" type="submit" value="Carica Documento"/>
					</form>
					
					<br>
					<br>
					
					<form id="rifiutaTirocinio" action="RifiutaTirocinioPresidenteServlet" method="post">
					<label id="Studente">Studente:</label>
						<select id="studente" name="studente">
						 		<% 
								
								studenti=(ArrayList<StudenteBean>)model.trovaStudentiConRichiesta();

									if(studenti!= null && studenti.size() > 0){
											Iterator<?> it= studenti.iterator();
											while(it.hasNext()){
												StudenteBean studente= (StudenteBean) it.next();
									%>
									<option value="<%=studente.getMatricola()%>"><%=studente.getNome()+" "+studente.getCognome() %></option>
								
								 			
									<% 
										}
									}
									%>
						
						</select>	
						<br><br>
						 <input id="BottoneRifiuta" type="submit" value="Rifiuta Tirocinio"/>
					</form>
					
				</div>
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
			<a href="/Sostage/presidente/HomePresidente.jsp"><p><i class="fa fa-home"></i> home</p></a>
			<a href="/Sostage/presidente/InfoPresidente.jsp"><p><i class="fa fa-info-circle"></i> info</p></a>
			<a href="#"><p><i class="fa fa-address-book"></i> contatti</p></a>
			
		</div>
	
	</footer>
</body>

</html>

