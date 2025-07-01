package model;

public class Vacina {
	
	private String id;
	private String fabricante;
	private String validade;
	private String tempoImunidade;
	private String nome;
	
	public Vacina(String id, String fabricante, String validade, String tempoImunidade, String nome) {
		this.id = id;
		this.fabricante = fabricante;
		this.validade = validade;
		this.tempoImunidade = tempoImunidade;
		this.nome = nome;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getFabricante() {
		return fabricante;
	}
	
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	
	public String getValidade() {
		return validade;
	}
	
	public void setValidade(String validade) {
		this.validade = validade;
	}
	
	public String getTempoImunidade() {
		return tempoImunidade;
	}
	
	public void setTempoImunidade(String tempoImunidade) {
		this.tempoImunidade = tempoImunidade;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}
