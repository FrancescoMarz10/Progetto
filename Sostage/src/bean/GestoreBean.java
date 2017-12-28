package bean;

public class GestoreBean {
	private String CF;
	private String nome;
	private String cognome;
	private String username;
	private String psw;
	private String mail;
	
	public GestoreBean(){
		CF="";
		nome="";
		cognome="";
		username="";
		psw="";
		mail="";
	}
	
	public GestoreBean(String cf, String nom, String cog,String us, String pw, String ma){
		CF=cf;
		nome=nom;
		cognome=cog;
		username=us;
		psw=pw;
		mail=ma;
	}

	public String getCF() {
		return CF;
	}

	public void setCF(String cF) {
		CF = cF;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
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

	
	public String toString() {
		return "GestoreBean [CF=" + CF + ", nome=" + nome + ", cognome=" + cognome + ", username=" + username + ", psw="
				+ psw + ", mail=" + mail + "]";
	}

}