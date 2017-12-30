<%@ page import="bean.StudenteBean, bean.UtenteBean, model.UtenteModel, java.util.*" %> 
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
		<a href="/Sostage/admin/HomeAdmin.jsp"><div class="elebar"> <p>H O M E</p> </div></a>
		<a href="/Sostage/admin/InfoAdmin.jsp"><div class="elebar"> <p>I N F O</p> </div></a>
		<a href="#"><div class="elebar"> <p>A B O U T - U S</p> </div></a>
	</nav>

<section id="ricercaDiv">
			<div class="TableRow">
				<h2>Aggiungi Nuovo Utente</h2>
				
		         Scegli il tipo di utente:
		            <select name="ruolo" id="ruolo">
		           		<option values="Azienda">Azienda</option>
		           		<option values="Presidente">Presidente</option>
		           		<option values="Tutor Interno">Tutor Interno</option>
		           		<option values="Tutor Esterno">Tutor Esterno</option>
		           		<option values="Ufficio">Ufficio Stage e Tirocini</option>
		            </select>
					
			         	
			
			
				</div>
			
			<br>
				<input id="BottoneMostra" type="submit" value="Mostra Form" onclick="return loadDoc()"/>
				
			
			
		</section>
		
		
		<script>
				function loadDoc() {
					
					var ruolo=document.getElementById("ruolo").value;
					
				  var xhttp = new XMLHttpRequest();
				  xhttp.onreadystatechange = function() {
				    if (this.readyState == 4 && this.status == 200) {
				      document.getElementById("selezionati").innerHTML =
				      this.responseText;
				    }
				  };  
				  xhttp.open("GET", "/Sostage/admin/MostraAdmin.jsp?ruolo="+ruolo, true);
				  xhttp.send();
				  
				  return false;
				}
				
				</script>
		
		
		
		
		
		<section id="selezionati">
				
	
		</section>
			
			
	
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
			<a href="/Sostage/admin/InfoAdmin.jsp"><p><i class="fa fa-info-circle"></i> info</p></a>
			<a href="#"><p><i class="fa fa-address-book"></i> contatti</p></a>
			
		</div>
	
	</footer>
</body>

</html>