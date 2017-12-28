package bean;

public class StudenteBean {
	
	private  String username;
	private  String psw;
	private  String matricola;
	private  String nome;
	private  String cognome;
	private String tutorInterno;
	private int OffertaFormativa;
	
	public StudenteBean(){
		username="";
		psw="";
		matricola="";
		nome="";
		cognome="";
		tutorInterno="";
		OffertaFormativa=0;
	
	}
	
	public StudenteBean(String us, String pw, String matr, String nom, String cogn){
		username=us;
		psw=pw;
		matricola=matr;
		nome=nom;
		cognome=cogn;
		tutorInterno="";
		OffertaFormativa=0;
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

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
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

	public String getTutorInterno() {
		return tutorInterno;
	}

	public void setTutorInterno(String tutorInterno) {
		this.tutorInterno = tutorInterno;
	}

	public int getOffertaFormativa() {
		return OffertaFormativa;
	}

	public void setOffertaFormativa(int offertaFormativa) {
		OffertaFormativa = offertaFormativa;
	}

	
	public String toString() {
		return "StudenteBean [username=" + username + ", psw=" + psw + ", matricola=" + matricola + ", nome=" + nome
				+ ", cognome=" + cognome + ", tutorInterno=" + tutorInterno + ", OffertaFormativa=" + OffertaFormativa
				+ "]";
	}

	
	

}