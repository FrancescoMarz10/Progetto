package bean;

public class UtenteBean {
	
	private String gestore;
	private String username;
	private String psw;
	private String mail;
	private String ruolo;

	
	public UtenteBean(){
		
		username="";
		psw="";
		gestore="";
		mail="";
		ruolo="";
	
	}
	
	public UtenteBean(String user,String ps, String gest, String email,String role){
		
		username=user;
		psw=ps;
		gestore=gest;
		mail=email;
		ruolo=role;
	
	}

	
	public String getGestore() {
		return gestore;
	}

	public void setGestore(String gestore) {
		this.gestore = gestore;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public String toString() {
		return "UtenteBean [gestore=" + gestore + ", username=" + username + ", psw=" + psw + ", mail=" + mail
				+ ", ruolo=" + ruolo + "]";
	}

	





	
	
}
