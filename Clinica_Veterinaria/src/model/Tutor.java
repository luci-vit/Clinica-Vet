package model;

public class Tutor extends Pessoa{
	
	private String endereco;

	public Tutor(String id, String nome, String cpf, String email, String telefone, String endereco) {
		super(id, nome, cpf, email, telefone);
		this.endereco = endereco;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
}
