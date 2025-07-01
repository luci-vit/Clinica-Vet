package model;

public class Veterinario extends Funcionario{
	
	private String cfmv;
	private String especialidade;
	
	public Veterinario(String id, String nome, String cpf, String email, String telefone, String turnoTrabalho, String funcao, String cfmv, String especialidade) {
		super(id, nome, cpf, email, telefone, turnoTrabalho, funcao);
		this.cfmv = cfmv;
		this.especialidade = especialidade;
	}
	
	public String getCfmv() {
		return cfmv;
	}

	public void setCfmv(String cfmv) {
		this.cfmv = cfmv;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	



}
