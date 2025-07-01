package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
	private static Conexao instancia;
	private static final String DRIVE = "org.sqlite.JDBC"; //Atribuição do driver do SQlite
	private static final String BD = "jdbc:sqlite:resources/bdVeterinario.db"; //Caminho e URL do banco SQLite.
	private static Connection conexao; //Armazena a instância de Connection para manter conexão ativa.
	
	private Conexao() { //Impede que outras classes criem uma instância diretamente.
	}
	
	public static Conexao getInstancia() { //Método responnsável por retornar uma instância criada ou não.
		if(instancia == null) {
			instancia = new Conexao();
		}
		return instancia;
	}
	
	public Connection abrirConexao() {
		try {
			Class.forName(DRIVE); //Carrega o drive do sqlite
			conexao = DriverManager.getConnection(BD); //Cria uma nova conexão com o banco de dados.
			conexao.setAutoCommit(false); //Define o modo manual para commit, alterando as confirmações de operação para manuais.
	        // Ativando suporte a FOREIGN KEYS
	        Statement stmt = conexao.createStatement();
	        stmt.execute("PRAGMA foreign_keys = ON");
	        
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Erro ao tentar conectar com o banco de dados" + e.getMessage());
		}
		return conexao;
		
	}
	
	public void fecharConexao() {
		try {
			boolean isConnectionOpen = conexao != null && !conexao.isClosed();  //Verifica se a conexão ainda existe e está aberta;
			if(isConnectionOpen) {
				conexao.close(); //Fecha a conexão.
			}	
		} catch (SQLException e) {
			System.out.println("Erro ao tentar finalizar a conexão com o banco"+e.getMessage());
		} finally {
			conexao = null; //Limpa a variável para que seja possível criar uma nova conexão se chamada de novo.
		}
		
	}
}