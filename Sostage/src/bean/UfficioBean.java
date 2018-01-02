package bean;

public class UfficioBean {

		private String Sigla;
		private String username;
		private String password;
		
		
		public UfficioBean() {
			Sigla="";
			username="";
			password="";
		}
		
		public UfficioBean(String unaSigla, String unUsername, String unaPassword) {
			Sigla=unaSigla;
			username=unUsername;
			password=unaPassword;
		}

		public String getSigla() {
			return Sigla;
		}

		public void setSigla(String sigla) {
			Sigla = sigla;
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
			return "UfficioBean [Sigla=" + Sigla + ", username=" + username + ", password=" + password + "]";
		}
		
		
}
