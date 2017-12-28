package bean;

public class OffertaFormativaBean {

		private int ID;
		private String nome;
		private String sede;
		private String tema;
		private String obiettivi;
		private String modalit‡Svolgimento;
		private String tutorEsterno;
		private String azienda;
		
		public OffertaFormativaBean() {
			ID=0;
			nome="";
			sede="";
			tema="";
			obiettivi="";
			modalit‡Svolgimento="";
			tutorEsterno="";
			azienda="";
		}
		
		public OffertaFormativaBean( int anID,String aName, String aLocation, String unTema, String unObiettivo, String unaModalit‡,String unTutorEsterno, String unAzienda) {
			ID=anID;
			nome=aName;
			sede=aLocation;
			tema=unTema;
			obiettivi=unObiettivo;
			modalit‡Svolgimento=unaModalit‡;
			tutorEsterno=unTutorEsterno;
			azienda=unAzienda;
		}

	
		public int getID() {
			return ID;
		}

		public void setID(int iD) {
			ID = iD;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getSede() {
			return sede;
		}

		public void setSede(String sede) {
			this.sede = sede;
		}

		public String getTema() {
			return tema;
		}

		public void setTema(String tema) {
			this.tema = tema;
		}

		public String getObiettivi() {
			return obiettivi;
		}

		public void setObiettivi(String obiettivi) {
			this.obiettivi = obiettivi;
		}

		public String getModalit‡Svolgimento() {
			return modalit‡Svolgimento;
		}

		public void setModalit‡Svolgimento(String modalit‡Svolgimento) {
			this.modalit‡Svolgimento = modalit‡Svolgimento;
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

		public String toString() {
			return "OffertaFormativaBean [ID=" + ID + ", nome=" + nome + ", sede=" + sede + ", tema=" + tema
					+ ", obiettivi=" + obiettivi + ", modalit‡Svolgimento=" + modalit‡Svolgimento + ", tutorEsterno="
					+ tutorEsterno + ", azienda=" + azienda + "]";
		}

		

		
		
}
