<%@ page import="bean.UtenteBean, bean.GestoreBean, model.GestoreModel" %> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="icon" href="images/icon.png">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="style.css" type="text/css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>SOSTAGE</title>
</head>
<body>
	<div class="header">
		<a href="Index.jsp"><img id="logo" alt="immagine" src="images/LogoMotto.png"></a>
		
		<div id="dipinfo2">
			<a href="http://www.di-srv.unisa.it/" target="_blank"><img alt="informaticapic" id="logoInfo" src="images/informatica.png"></a>
		</div>

	
	<div class="container">
				
				<div id="regDiv">
				
				<div id="divStudente">
					<h2 id="h2reg">REGISTRATI </h2>
					
				</div>
				
				
		<form id="reg" action="RegistrazioneServlet" method="post">
				 <label for="nome">Name</label> <br>
			     <input id="nome" type="text" name="nome" placeholder="enter name" required> 
			     <br>   	
			     <br>
			     <label for="surname">Cognome</label> <br>
				 <input id="surname" type="text" name="surname" placeholder="enter surname" required> 
				 <br>
				 <br>
				 <label for="matricola">Matricola</label> <br>
				  <input id="matricola" type="text" maxlength="10" name="matricola" placeholder="enter matricola" required> 
				 <br>
				 <br>
				  <label for="mail">Indirizzo e-mail</label> <br>
			     <input id="email" type="text" name="mail" placeholder="enter e-mail address" required> 
				 <br>
				 <br>
				 <label for="username">Username</label> <br>
				 <input id="Utenteusername" type="text" name="username" placeholder="enter username" required> 
				 <br>
				 <br>
			     <label for="password">Password</label> <br>
			     <input id="password" type="password" name="password" placeholder="enter password" required> 
			     <br>
			  
			     
			      <% String error=(String)request.getAttribute("error");
			      	if(error!=null &&! error.equals("")){
			      %>
			      	<p id="errorReg"><%=error %></p>	
			      <% 
			      	}
			      
			      %>
			    
			     
			   <input id="buttonRegi" type="submit" value="REGISTRATI"/>
		
		     </form>
					
				</div>
		
	</div>
	<hr id="hr">
	
	<footer id="footer">
	
		<div id="foot1">
			<a href="http://www.unisa.it/" target="_blank"><img alt="informaticapic" id="logoUnisa" src="images/logoUnisa2.png"></a>
		</div>
		
		<div id="foot2">
			<p><i class="fa fa-envelope"></i> sostage@unisa.it</p>
			<p><i class="fa fa-home"></i> 84084 Fisciano (SA) - ITALY</p>
			<p><i class="fa fa-location-arrow"></i> Via Giovanni Paolo II, 132</p>
			<p><i class="fa fa-phone"></i> 089 961111</p>
		</div>
	
		<div id="foot3">
			<a href="#"><p><i class="fa fa-home"></i> home</p></a>
			<a href="Info.jsp"><p><i class="fa fa-info-circle"></i> info</p></a>
			<a href="#"><p><i class="fa fa-address-book"></i> contatti</p></a>
			
		</div>
	
	</footer>
</body>

</html>