package bean;

public class DocumentoBean {
	
	private String nome;
	
	public DocumentoBean() {
		nome="";
	}

	public DocumentoBean(String unNome) {
		nome=unNome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String toString() {
		return "DocumentoBean [nome=" + nome + "]";
	}
	
	
}
