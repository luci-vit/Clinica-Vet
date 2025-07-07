package model;

public class Usuario {
	private int id;
	private String usuario;
	private String senha;
	private String funcao;
	
	public Usuario(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
	}
	
	public Usuario(String usuario, String senha, int id, String funcao) {
		this(usuario, senha);
		this.id = id;
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
	
    public static String gerarSenha(String nome, String cpf) {
        String[] partesDoNome = nome.trim().split("\\s+");
        StringBuilder iniciais = new StringBuilder();

        for (String parte : partesDoNome) {
            if (!parte.isEmpty()) {
                iniciais.append(parte.charAt(0));
            }
        }

        String primeirosCpf = cpf.replaceAll("\\D", ""); // remove pontos e traços
        if (primeirosCpf.length() >= 3) {
            primeirosCpf = primeirosCpf.substring(0, 3);
        }

        return iniciais.toString().toUpperCase() + primeirosCpf;
    }

    public static String gerarUsuario(String funcao, String nome, String cpf) {
        String funcaoPrefixo = funcao.trim().toLowerCase();
        funcaoPrefixo = funcaoPrefixo.length() >= 2 ? funcaoPrefixo.substring(0, 2) : funcaoPrefixo;

        String primeiroNome = nome.trim().split("\\s+")[0].toLowerCase();

        String ultimosCpf = cpf.replaceAll("\\D", ""); // remove pontos e traços
        if (ultimosCpf.length() >= 3) {
            ultimosCpf = ultimosCpf.substring(ultimosCpf.length() - 3);
        }

        return funcaoPrefixo + primeiroNome + ultimosCpf;
    }
}
