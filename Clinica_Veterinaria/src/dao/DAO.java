package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import controller.Conexao;
import model.Usuario;

public interface DAO<T>{
	default void efetuarCadastro(T entidade) {}
	default void efetuarCadastroComUsuario(T entidade, Usuario usuario) {}
	public T buscarPorId(String id);
	public void editarDados(T entidade, String id);
	public ArrayList<T> listarTodos();
	public void excluir(String id);
	
	public static void fecharConexao(PreparedStatement preparedStatement, ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			Conexao.getInstancia().fecharConexao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
