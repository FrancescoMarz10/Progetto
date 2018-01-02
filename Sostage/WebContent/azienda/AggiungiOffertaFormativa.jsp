<%@ page import="bean.UtenteBean, bean.TutorEsternoBean, model.TutorEsternoModel, java.util.*" %> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="icon" href="/Sostage/images/icon.png">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/Sostage/style.css" type="text/css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>SOSTAGE</title>
</head>
<body>
	<div class="header">
		<a href="/Sostage/azienda/HomeAzienda.jsp"><img id="logo" alt="immagine" src="/Sostage/images/LogoMotto.png"></a>
		
		<div id="dipinfo2">
			<a href="http://www.di-srv.unisa.it/" target="_blank"><img alt="informaticapic" id="logoInfo" src="/Sostage/images/informatica.png"></a>
		</div>

		
	<div class="container">
				
				<div id="aggiungiUtenteDiv">
				
					
				<div id="divStudente">
					<h2 id="h2reg">AGGIUNGI OFFERTA FORMATIVA </h2>
					
				</div>
				
				
				<form id="aggiungiOffertaFormativa" action="AggiungiOffertaFormativaServlet" method="post">
				 <label for="Nome">Nome</label> <br>
				  <input id="Nome" type="text" name="Nome" placeholder="enter name" required> 
			     <br>   	
			     <br>
			     <label for="Sede">Sede</label> <br>
				 <input id="Sede" type="text" name="Sede" placeholder="enter location" required> 
				 <br>
				 <br>
				 <label for="Tema">Tema</label> <br>
			     <input id="Tema" type="text" name="Tema" placeholder="enter topic" required> 
				 <br>
				 <br>
				  <label for="Obiettivi">Obiettivi</label> <br>
			     <input id="Obiettivi" type="text" name="Obiettivi" placeholder="enter goals" required> 
				 <br>
				 <br>
				 <label for="modalitaSvolgimento">Modalità di Svolgimento</label> <br>
				 <textarea id="modalitaSvolgimento" rows="7" column="100"  maxlength="100" name="modalitaSvolgimento" required> 
				</textarea>
				 <br>
				 <br>
				
				 <label for="tutorEsterno">Tutor Esterno</label> <br>
				 <select id="tutorEsterno" name="tutorEsterno">
				 <%
			
					TutorEsternoModel model= new TutorEsternoModel();
					Collection<TutorEsternoBean> tutor=(ArrayList<TutorEsternoBean>)model.doRetrieveAll();
				 
					if(tutor!= null && tutor.size() > 0){
							Iterator<?> it= tutor.iterator();
							while(it.hasNext()){
								TutorEsternoBean bean= (TutorEsternoBean) it.next();
					%>
				
				 	<option value="<%=bean.getCF() %>"><%=bean.getNome()+" "+bean.getCognome() %></option>
				
				 			
					<% 
						}
					}
					%>
					 </select>
					 
					
			     
			      <% String error=(String)request.getAttribute("error");
			      	if(error!=null &&! error.equals("")){
			      %>
			      	<p id="errorReg"><%=error %></p>	
			      <% 
			      	}
			      
			      %>
			    
			     
			   <input id="buttonAggiungiOfferta" type="submit" value="Aggiungi Offerta"/>
		
		     </form>
					
				</div>
					
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
			<a href="/Sostage/azienda/HomeAzienda.jsp"><p><i class="fa fa-home"></i> home</p></a>
			<a href="/Sostage/azienda/InfoAzienda.jsp"><p><i class="fa fa-info-circle"></i> info</p></a>
			<a href="#"><p><i class="fa fa-address-book"></i> contatti</p></a>
			
		</div>
	
	</footer>
</body>

</html>