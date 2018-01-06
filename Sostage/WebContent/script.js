function apriNotifiche(){
	if((document.getElementById('menu').style.display)=='none' ||(document.getElementById('menu').style.display)==''){
		document.getElementById('menu').style.display='block';
	}else{
		document.getElementById('menu').style.display='none';
	}
	
}


function visualizza(id){
	  if (document.getElementById){
	    if(document.getElementById(id).style.display == 'none'){
	      document.getElementById(id).style.display = 'block';
	    }else{
	      document.getElementById(id).style.display = 'none';
	    }
	  }
	}

function compari(){
	var  schermo;
	schermo= window.innerWidth;
	
	var sizenav1=(window.innerWidth*15)/100;
	if(sizenav1<=200){
		sizenav1=200;
	}
	
	schermo=schermo-sizenav1-30;
	schermo=schermo+'px';
	
	if(window.innerWidth > 700){
		if((document.getElementById("navLaterale").style.display)=="none" ||(document.getElementById("navLaterale").style.display)==""){
			document.getElementById("navLaterale").style.display="block";
			document.getElementById("mainDiv").style.marginLeft="210px";
			document.getElementById("mainDiv").style.width= schermo;
			console.log(schermo);
		}else{
	    	document.getElementById("navLaterale").style.display="none";
			document.getElementById("mainDiv").style.marginLeft="2%";
			document.getElementById("mainDiv").style.width="96%";

	    }
	}else{
		document.getElementById("navLaterale").style.display="block";
		document.getElementById("sfondoOmbra").style.visibility="visible";
	}
	
}
