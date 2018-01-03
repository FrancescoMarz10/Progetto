<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SOSTAGE</title>
</head>
<body >

 					 <% String error=(String)request.getAttribute("error");
 				
				      			System.out.println(error);
				    	  %>
				    	  
				    	  <form>
				    	  <input id="errore" type="hidden" value="<%=error%>">
				    	  
				    	  </form>
				     		<script>
				     			var a=document.getElementById("errore").value;
				     	 		alert(a);
				     	 		{window.location.href='/Sostage/admin/AggiungiNuovoUtente.jsp';}
				   		 	</script>	
				  	    
</html>