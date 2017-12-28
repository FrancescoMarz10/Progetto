package bean;

public class TutorEsternoBean {
	private String CF;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	
	
	public TutorEsternoBean(){
		CF="";
		nome="";
		cognome="";
		username="";
		password="";
		
	}
	
	public TutorEsternoBean(String c, String nom, String co, String us, String pass, String ma){
		CF=c;
		nome=nom;
		cognome=co;
		username=us;
		password=pass;
		
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String toString() {
		return "TutorEsternoBean [CF=" + CF + ", nome=" + nome + ", cognome=" + cognome + ", username=" + username
				+ ", password=" + password + "]";
	}

	
	
}
