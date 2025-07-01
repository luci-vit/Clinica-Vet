package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import controller.Conexao;
import model.Usuario;

public class DAOUsuario {
	
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	
	private static String CONSULTAR_USUARIO = " SELECT ID, USUARIO, SENHA, FUNCAO " 
			+ " FROM CONTRIBUIDORES " + " WHERE USUARIO = ? "
			+ " AND SENHA = ?";
	
	
	public Usuario ConsultarUsuario(String nomeUsuario, String senha) {
		Connection conexao = Conexao.getInstancia().abrirConexao();
		Usuario usuario = null;
		String query = CONSULTAR_USUARIO;

		try {
			
			preparedStatement = conexao.prepareStatement(query);

			int i = 1;

			preparedStatement.setString(i++, nomeUsuario);
			preparedStatement.setString(i++, senha);
			
			resultSet = preparedStatement.executeQuery(); // Armazena o resultado da querry enviada pelo
															// preparedStatement

			while (resultSet.next()) {
				usuario = new Usuario(resultSet.getInt("ID"), resultSet.getString("USUARIO"),
						resultSet.getString("SENHA"), resultSet.getString("FUNCAO"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAO.fecharConexao(preparedStatement, resultSet);
		}
		if (usuario == null) {
			JOptionPane.showMessageDialog(null, "Não foi possível localizar o usuario", "",
					JOptionPane.WARNING_MESSAGE);

		}
		return usuario;

	}



}
