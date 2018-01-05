package bean;

public class NotificaBean {

	private int ID;
	private String testo;
	private String tipo;
	private String ufficio;
	private String tutorInterno;
	private String tutorEsterno;
	private String azienda;
	private String studente;
	
	public NotificaBean() {
		ID=0;
		testo="";
		tipo="";
		ufficio="";
		tutorInterno="";
		tutorEsterno="";
		azienda="";
		studente="";
	}
	
	public NotificaBean(int unID, String unTesto, String unTipo, String unUfficio, String unTutorI, String unTutorE, String unAzienda, String unoStudente) {
		ID=unID;
		testo=unTesto;
		tipo=unTipo;
		ufficio=unUfficio;
		tutorEsterno=unTutorE;
		tutorInterno=unTutorI;
		azienda=unAzienda;
		studente=unoStudente;
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
		return ufficio;
	}

	public void setUfficio(String ufficio) {
		this.ufficio = ufficio;
	}

	public String getTutorInterno() {
		return tutorInterno;
	}

	public void setTutorInterno(String tutorInterno) {
		this.tutorInterno = tutorInterno;
	}

	public String getTutorEsterno() {
		return tutorEsterno;
	}

	public void setTutorEsterno(String tutorEsterno) {
		this.tutorEsterno = tutorEsterno;
	}

	public String getAzienda() {
		return azienda;
	}

	public void setAzienda(String azienda) {
		this.azienda = azienda;
	}

	public String getStudente() {
		return studente;
	}

	public void setStudente(String studente) {
		this.studente = studente;
	}


	public String toString() {
		return "NotificaBean [ID=" + ID + ", testo=" + testo + ", tipo=" + tipo + ", ufficio=" + ufficio
				+ ", tutorInterno=" + tutorInterno + ", tutorEsterno=" + tutorEsterno + ", azienda=" + azienda
				+ ", studente=" + studente + "]";
	}
	
	
	
}
