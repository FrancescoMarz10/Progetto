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
		<img id="logo" alt="immagine" src="images/LogoMotto.png">
		
		<div id="dipinfo">
			<a href="http://www.di-srv.unisa.it/" target="_blank"><img alt="informaticapic" id="logoInfo" src="images/informatica.png"></a>
		</div>
		
		<button id="login"><i class="fa fa-user-o"></i> Login</button>
	</div>
	<div class="modal" id="loginmodal">

		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<img src="images/icon.png" id="imglgn">
				
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" id="closemodal">
						<span title="CloseLogin">&times;</span>
					</button>
				</div>
				<form action="LoginServlet" method="post">
					<div class="modal-body">
						<input class="form-control" type="text" name="username" placeholder="Enter Username" required><br> 
						<input class="form-control" type="password" name="psw" placeholder="Enter Password" required>
						<div class="checkbox">
						</div>
					</div>
					<div class="modal-footer">
						<div>
							<button class="btn btn-primary btn-lg btn-block" type="submit" id="logbtn">LOGIN</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	</div>
	
	<nav id="bar">
		<a href="Index.jsp"><div class="elebar"> <p>H O M E</p> </div></a>
		<a href="Info.jsp"><div class="elebar"> <p>I N F O</p> </div></a>
		<a href="#"><div class="elebar"> <p>A B O U T - U S</p> </div></a>
	</nav>
	
	<div class="containerBody">
	<div class="infoBodyDiv">
		<div id="titleInfo">Sviluppatori</div>
		
	
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
			<a href="Index.jsp"><p><i class="fa fa-home"></i> home</p></a>
			<a href="Info.jsp"><p><i class="fa fa-info-circle"></i> info</p></a>
			<a href="Aboutus.jsp"><p><i class="fa fa-address-book"></i> about us</p></a>
			
		</div>
	
	</footer>
</body>

<script type="text/javascript">
var login = document.getElementById('login');
var logmodal = document.getElementById('loginmodal');
var closelog =document.getElementById('closemodal');
var register=document.getElementById('registerbtn');
var regmodal=document.getElementById('registermodal')
var closereg=document.getElementById('closeregistermodal');

login.onclick = function() {
    logmodal.style.display = "block";
}

closelog.onclick = function() {
    logmodal.style.display = "none";
}
</script>
</html>