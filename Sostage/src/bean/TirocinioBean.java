package bean;

public class TirocinioBean {
		private int codice;
		private String materia;
		private String periodo;
		private String luogo;
		private String tutorInterno;
		private String tutorEsterno;
		private String studente;
		private String azienda;
		private String presidente;
		private String documento;
		
		public TirocinioBean() {
			codice=0;
			materia="";
			periodo="";
			luogo="";
			tutorInterno="";
			tutorEsterno="";
			studente="";
			azienda="";
			presidente="";
			documento="";
		}
		
		public TirocinioBean(int cod,String unaMateria, String unPeriodo, String unLuogo, String unTutorInterno, String unTutorEsterno, String unoStudente,String unAzienda, String unPresidente, String unDocumento) {
			codice=cod;
			materia=unaMateria;
			periodo=unPeriodo;
			luogo=unLuogo;
			tutorInterno=unTutorInterno;
			tutorEsterno=unTutorEsterno;
			studente=unoStudente;
			azienda=unAzienda;
			presidente=unPresidente;
			documento=unDocumento;
		}

		public int getCodice() {
			return codice;
		}

		public void setCodice(int codice) {
			this.codice = codice;
		}

		public String getMateria() {
			return materia;
		}

		public void setMateria(String materia) {
			this.materia = materia;
		}

		public String getPeriodo() {
			return periodo;
		}

		public void setPeriodo(String periodo) {
			this.periodo = periodo;
		}

		public String getLuogo() {
			return luogo;
		}

		public void setLuogo(String luogo) {
			this.luogo = luogo;
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

		public String getStudente() {
			return studente;
		}

		public void setStudente(String studente) {
			this.studente = studente;
		}

		public String getAzienda() {
			return azienda;
		}

		public void setAzienda(String azienda) {
			this.azienda = azienda;
		}

		public String getPresidente() {
			return presidente;
		}

		public void setPresidente(String presidente) {
			this.presidente = presidente;
		}

		public String getDocumento() {
			return documento;
		}

		public void setDocumento(String documento) {
			this.documento = documento;
		}

		public String toString() {
			return "TirocinioBean [codice=" + codice + ", materia=" + materia + ", periodo=" + periodo + ", luogo="
					+ luogo + ", tutorInterno=" + tutorInterno + ", tutorEsterno=" + tutorEsterno + ", studente="
					+ studente + ", azienda=" + azienda + ", presidente=" + presidente + ", documento=" + documento
					+ "]";
		}
		
}
