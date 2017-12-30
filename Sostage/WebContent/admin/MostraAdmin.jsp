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

<div id="divForm">

<%
	String ruolo= request.getParameter("ruolo");
	%>

	
		<%
			
			if(ruolo.equals("Presidente") || ruolo.equals("Tutor Esterno") || ruolo.equals("Tutor Interno")){
			
					%>
							<form id="aggiungiUtente" action="AggiungiUtenteServlet" method="post">
								<input type="hidden" name="role" value="<%=ruolo%>"> 
								
							 <label for="CF">Codice Fiscale</label> <br>
							  <input id="CF" type="text" name="CF" maxlength="16" placeholder="enter fiscal code" required> 
						     <br>   	
						     <br>
						     <label for="Nome">Nome</label> <br>
							 <input id="Nome" type="text" name="Nome" placeholder="enter name" required> 
							 <br>
							 <br>
							 <label for="Cognome">Cognome</label> <br>
						     <input id="Cognome" type="text" name="Cognome" placeholder="enter cognome" required> 
							 <br>
							 <br>
							  <label for="mail">Mail</label> <br>
						     <input id="mail" type="text" name="mail" placeholder="enter mail" required> 
							 <br>
							 <br>
							 <label for="username">Username</label> <br>
							 <input id="username" type="text" name="username" placeholder="enter username" required> 
							 <br>
							 <br>
							 <label for="psw">Password</label> <br>
						     <input id="psw" type="password" name="psw" placeholder="enter password" required> 
							 <br>
							 
						     
						      <% String error=(String)request.getAttribute("error");
						      	if(error!=null &&! error.equals("")){
						      %>
						      	<p id="errorReg"><%=error %></p>	
						      <% 
						      	}
						      %>
						    
						     
						   <input id="buttonAggiungiUtente" type="submit" value="Aggiungi Utente"/>
					
					     </form>
								
									
					<% 
					}
	
					else if(ruolo.equals("Azienda")){
					%>
					<form id="aggiungiUtente" action="AggiungiUtenteServlet" method="post">
							<input type="hidden" name="role" value="azienda"> 
					
						     <label for="Nome">Nome</label> <br>
							 <input id="Nome" type="text" name="Nome" placeholder="enter name" required> 
							 <br>
							 <br>
							 <label for="nomeResponsabile">Nome del Responsabile</label> <br>
						     <input id="nomeResponsabile" type="text" name="nomeResponsabile" placeholder="enter manager's name" required> 
							 <br>
							 <br>
							 <label for="Sede">Sede</label> <br>
						     <input id="Sede" type="text" name="Sede" placeholder="enter location" required> 
							 <br>
							 <br>
							 <label for="mail">Mail</label> <br>
						     <input id="mail" type="text" name="mail" placeholder="enter mail" required> 
							 <br>
							 <br>
							  <label for="username">Username</label> <br>
							 <input id="username" type="text" name="username" placeholder="enter username" required> 
							 <br>
							 <br>
							 <label for="psw">Password</label> <br>
						     <input id="psw" type="password" name="psw" placeholder="enter password" required> 
							 <br>
							 
						     
						      <% String error=(String)request.getAttribute("error");
						      	if(error!=null &&! error.equals("")){
						      %>
						      	<p id="errorReg"><%=error %></p>	
						      <% 
						      	}
						      %>
						    
						     
						   <input id="buttonAggiungiUtente" type="submit" value="Aggiungi Utente"/>
					
					     </form>
			
					<% 
					}
	
					else if(ruolo.equals("Ufficio Stage e Tirocini")){
					%>
					<form id="aggiungiUtente" action="AggiungiUtenteServlet" method="post">
							<input type="hidden" name="role" value="ufficio"> 
					
						    <label for="Sigla">Sigla</label> <br>
							  <input id="Sigla" type="text" name="Sigla" placeholder="enter sign" required> 
						     <br>   	
							 <br>
							 <label for="mail">Mail</label> <br>
						     <input id="mail" type="text" name="mail" placeholder="enter mail" required> 
							 <br>
							 <br>
							 <label for="username">Username</label> <br>
							 <input id="username" type="text" name="username" placeholder="enter username" required> 
							 <br>
							 <br>
							 <label for="psw">Password</label> <br>
						     <input id="psw" type="password" name="psw" placeholder="enter password" required> 
							 <br>
							 
						     
						      <% String error=(String)request.getAttribute("error");
						      	if(error!=null &&! error.equals("")){
						      %>
						      	<p id="errorReg"><%=error %></p>	
						      <% 
						      	}
						      %>
						    
						     
						   <input id="buttonAggiungiUtente" type="submit" value="Aggiungi Utente"/>
					
					     </form>
						 <% 
					      	}
						%>
						    

 </div>
</body>
</html>
