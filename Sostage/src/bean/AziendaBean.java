package bean;

public class AziendaBean {
	private String nome;
	private String username;
	private String psw;
	private String sede;
	private String nomeResp;
	
	
	public AziendaBean(){
		nome="";
		username="";
		psw="";
		sede="";
	}
	
	public AziendaBean(String sed,String no, String us, String ps){
		nome=no;
		username=us;
		psw=ps;
		sede=sed;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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


	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public String getNomeResp() {
		return nomeResp;
	}

	public void setNomeResp(String nomeResp) {
		this.nomeResp = nomeResp;
	}

	public String toString() {
		return "AziendaBean [nome=" + nome + ", username=" + username + ", psw=" + psw + ", sede="
				+ sede + ", nomeResp=" + nomeResp + "]";
	}
	
	
}
