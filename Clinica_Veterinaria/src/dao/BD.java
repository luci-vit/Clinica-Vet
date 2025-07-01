package dao;

import java.sql.Connection;

import controller.Conexao;

public class BD {

	private static Connection conexao = null;

	public static void main(String[] args) {

		try {
			conexao = Conexao.getInstancia().abrirConexao();
			System.out.println("Banco criado com sucesso");
			Conexao.getInstancia().fecharConexao();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

	}

}
