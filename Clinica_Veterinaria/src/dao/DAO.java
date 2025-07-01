package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import controller.Conexao;

public interface DAO<T>{
	public void efetuarCadastro(T entidade);
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
