package bean;

public class UfficioBean {
	private String username;
	private String psw;
	private String sigla;
	
	public UfficioBean(){
		username="";
		psw="";
		sigla="";
	}
	
	public UfficioBean(String user, String ps, String sigla1){
		username=user;
		psw=ps;
		sigla=sigla1;
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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String toString() {
		return "UfficioBean [username=" + username +", psw=" + psw +", sigla=" + sigla + "]";
	}

}
