<%@ page import="model.OffertaFormativaModel, bean.OffertaFormativaBean, bean.TutorInternoBean,model.StudenteModel,bean.StudenteBean, bean.UtenteBean, model.TutorInternoModel,java.util.*" %> 
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
		<a href="/Sostage/studente/HomeStudente.jsp"><div class="elebar"> <p>H O M E</p> </div></a>
		<a href="/Sostage/studente/InfoStudente.jsp"><div class="elebar"> <p>I N F O</p> </div></a>
		<a href="#"><div class="elebar"> <p>A B O U T - U S</p> </div></a>
	</nav>
	
	<div class="container">
		
				<div id="info">
					<%
						UtenteBean bean=(UtenteBean)session.getAttribute("bean"); 
						StudenteModel model= new StudenteModel();
						StudenteBean studente=model.doRetrieveByUsername(bean.getUsername());
					%>
					<img id="usericon" src="/Sostage/images/user.png" alt="superman"><h2 id="benv">Benvenuto <%=studente.getUsername()  %> !</h2>
					<br>
					<ul>
					  <li><p id="we">Nome: <%=studente.getNome() %></p></li>
					  <li><p id="we">Cognome: <%=studente.getCognome()%></p></li>
					  <li><p id="we">Mail: <%=bean.getMail()%></p></li>
					  <li><p id="we">Matricola: <%=studente.getMatricola()%></p></li>
					  
					</ul>
					
				</div>
				
				<div id="Documenti">
					<img id="picDoc" src="/Sostage/images/doc.png">
					<br>
				
					<a target="_blank" href="/Sostage/Files/progettoFormativo.pdf"><input id="BottoneScaricaStu" type="button" value="Scarica Documento"/></a>
					<br>
					<br>
				
					
					<form id="scegli" action="RichiestaTirocinioServlet" method="post" enctype="multipart/form-data">
					
					<label for="tutorInterno">Offerta Formativa:</label>
					
					
						<select id="offertaFormativa" name="offertaFormativa">
						 		<%
							
									OffertaFormativaModel modeloff= new OffertaFormativaModel();
									Collection<OffertaFormativaBean> offerte=(ArrayList<OffertaFormativaBean>)modeloff.doRetrieveAll();
								 
									if(offerte!= null && offerte.size() > 0){
											Iterator<?> it= offerte.iterator();
											while(it.hasNext()){
												OffertaFormativaBean offerta= (OffertaFormativaBean) it.next();
									%>
									<option value="<%=offerta.getID()%>"><%=offerta.getNome() %></option>
								
								 			
									<% 
										}
									}
									%>
						
						</select>	
						<br> <br>
						<label for="tutorInterno">Tutor Interno:</label>
						 <select id="tutorInterno" name="tutorInterno">
								 <%
							
									TutorInternoModel model1= new TutorInternoModel();
									Collection<TutorInternoBean> tutor=(ArrayList<TutorInternoBean>)model1.doRetrieveAll();
								 
									if(tutor!= null && tutor.size() > 0){
											Iterator<?> it= tutor.iterator();
											while(it.hasNext()){
												TutorInternoBean bean1= (TutorInternoBean) it.next();
									%>
								
								 	<option value="<%=bean1.getCF() %>"><%=bean1.getNome()+" "+bean1.getCognome() %></option>
								
								 			
									<% 
										}
									}
									%>
							 </select>
							 <br>
							 <br>
							 
					 <label for="periodo">Periodo:</label>
							<select id="periodo" name="periodo">
								<option value="Gen-Feb">Gen-Feb</option>
								<option value="Mar-Apr">Mar-Apr</option>
								<option value="Mag-Giu">Mag-Giu</option>
								<option value="Giu-Lug">Giu-Lug</option>
								<option value="Set-Ott">Set-Ott</option>
								<option value="Nov-Dic">Nov-Dic</option>
							</select>
							
					  <br>
					
					
						 	Scegli file: <input  type="file" name="file1">
						 	 <input id="BottoneCarica" type="submit" value="Carica Documento"/>
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
			<a href="/Sostage/studente/HomeStudente.jsp"><p><i class="fa fa-home"></i> home</p></a>
			<a href="/Sostage/studente/InfoStudente.jsp"><p><i class="fa fa-info-circle"></i> info</p></a>
			<a href="#"><p><i class="fa fa-address-book"></i> contatti</p></a>
			
		</div>
	
	</footer>
</body>

</html>

