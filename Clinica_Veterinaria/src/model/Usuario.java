package model;

public class Usuario {
	private int id;
	private String usuario;
	private String senha;
	private String funcao;
	
	public Usuario(int id, String usuario, String senha, String funcao) {
		this.id = id;
		this.usuario = usuario;
		this.senha = senha;
		this.funcao = funcao;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
}
