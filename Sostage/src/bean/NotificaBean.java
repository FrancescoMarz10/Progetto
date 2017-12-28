package bean;

public class NotificaBean {
	private int ID;
	private String testo;
	private String tipo;
	private String Ufficio;
	private String TutorInterno;
	private String TutorEsterno;
	private String Azienda;
	private String Studente;
	
	public NotificaBean(){
		ID=0;
		testo="";
		tipo="";
		Ufficio="";
		TutorInterno="";
		TutorEsterno="";
		Azienda="";
		Studente="";
	}
	
	public NotificaBean(int id, String text, String type, String ufficio, String tutorInterno, String tutorEsterno, String azienda, String studente){
		ID=id;
		testo=text;
		tipo=type;
		Ufficio=ufficio;
		TutorInterno=tutorInterno;
		TutorEsterno=tutorEsterno;
		Azienda=azienda;
		Studente=studente;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUfficio() {
		return Ufficio;
	}

	public void setUfficio(String ufficio) {
		Ufficio = ufficio;
	}

	public String getTutorInterno() {
		return TutorInterno;
	}

	public void setTutorInterno(String tutorInterno) {
		TutorInterno = tutorInterno;
	}

	public String getTutorEsterno() {
		return TutorEsterno;
	}

	public void setTutorEsterno(String tutorEsterno) {
		TutorEsterno = tutorEsterno;
	}

	public String getAzienda() {
		return Azienda;
	}

	public void setAzienda(String azienda) {
		Azienda = azienda;
	}

	public String getStudente() {
		return Studente;
	}

	public void setStudente(String studente) {
		Studente = studente;
	}

	public String toString() {
		return "NotificaBean [ID=" + ID + ", testo=" + testo + ", tipo=" + tipo +", Ufficio=" + Ufficio + ", TutorInterno=" + TutorInterno + ", TutorEsterno=" + TutorEsterno + ", Azienda=" + Azienda + ", Studente=" + Studente + "]";
	}

}