package model;

public class Funcionario extends Pessoa{
	private String turnoTrabalho;
	private String funcao;
	
	public Funcionario(String id, String nome, String cpf, String email, String telefone, String turnoTrabalho, String funcao) {
		super(id, nome, cpf, email, telefone);
		this.funcao = funcao;
		this.turnoTrabalho = turnoTrabalho;
	}

	public String getTurnoTrabalho() {
		return turnoTrabalho;
	}

	public void setTurnoTrabalho(String turnoTrabalho) {
		this.turnoTrabalho = turnoTrabalho;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

}
