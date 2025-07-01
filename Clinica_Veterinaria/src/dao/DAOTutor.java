package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.Conexao;
import model.Tutor;

public class DAOTutor implements DAO<Tutor>{
	
		private static PreparedStatement preparedStatement = null;
	    private static ResultSet resultSet = null;

	    private static String CADASTRAR_TUTOR = " INSERT INTO TUTOR " +
	            "(ID, NOME, CPF, EMAIL, TELEFONE, ENDERECO)"
	            + "VALUES (NULL, ?, ?, ?, ?, ?)";

	    private static String CONSULTAR_TUTOR = " SELECT * FROM TUTOR " +
	            "WHERE ID = ?";

	    private static String ALTERAR_TUTOR = " UPDATE TUTOR SET " +
	            "NOME = ?, CPF = ?, EMAIL = ?, TELEFONE = ?, ENDERECO = ?"
	    		+ "WHERE ID = ?";

	    private static String LISTAR_TUTORES = " SELECT * FROM TUTOR "
	            + "WHERE 1 = 1";

	    private static String EXCLUIR_TUTOR = " DELETE FROM TUTOR "
	            + "WHERE ID = ?";
	     

		@Override
		public void efetuarCadastro(Tutor entidade) {
			
			Connection conexao = Conexao.getInstancia().abrirConexao();
			
			String query = CADASTRAR_TUTOR;
			
			try {
				
				preparedStatement = conexao.prepareStatement(query);
				
				int i = 1;
				
				preparedStatement.setString(i++, entidade.getNome());
				preparedStatement.setString(i++, entidade.getCpf());
				preparedStatement.setString(i++, entidade.getEmail());
				preparedStatement.setString(i++, entidade.getTelefone());
				preparedStatement.setString(i++, entidade.getEndereco());

				
				preparedStatement.executeUpdate();
				conexao.commit();
				
				JOptionPane.showMessageDialog(null, "Tutor cadastrado com sucesso");
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DAO.fecharConexao(preparedStatement, resultSet);
			}
		}

		@Override
		public Tutor buscarPorId(String id) {
		
			Connection conexao = Conexao.getInstancia().abrirConexao();
			Tutor tutor = null;
			String query = CONSULTAR_TUTOR;
			
			try {
			
				preparedStatement = conexao.prepareStatement(query);
				
				int i = 1;
				
				preparedStatement.setString(i++, id);
				resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					tutor = new Tutor(resultSet.getString("ID"), resultSet.getString("NOME"), resultSet.getString("CPF"),
							resultSet.getString("EMAIL"), resultSet.getString("TELEFONE"), resultSet.getString("ENDERECO"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DAO.fecharConexao(preparedStatement, resultSet);
			}

			if(tutor == null) {
				JOptionPane.showMessageDialog(null, "Não foi possível localizar o tutor", "", JOptionPane.WARNING_MESSAGE);
			}

			return tutor;
		}

		@Override
		public void editarDados(Tutor entidade, String id) {
			Connection conexao = Conexao.getInstancia().abrirConexao();
			String query = ALTERAR_TUTOR;
			
			try {
				preparedStatement = conexao.prepareStatement(query);
				
				int i = 1;
				
				preparedStatement.setString(i++, entidade.getNome());
				preparedStatement.setString(i++, entidade.getCpf());
				preparedStatement.setString(i++, entidade.getEmail());
				preparedStatement.setString(i++, entidade.getTelefone());
				preparedStatement.setString(i++, entidade.getEndereco());
				preparedStatement.setString(i++, id);
				
				preparedStatement.executeUpdate();
				conexao.commit();
				
				JOptionPane.showMessageDialog(null, "Dados do funcionario alterado com sucesso!");
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DAO.fecharConexao(preparedStatement, resultSet);
			}
		}

		@Override
		public ArrayList<Tutor> listarTodos() {
			
			Connection conexao = Conexao.getInstancia().abrirConexao();
			String query = LISTAR_TUTORES;
			ArrayList<Tutor> lista_tutores = new ArrayList<Tutor>();
			
			try {
				preparedStatement = conexao.prepareStatement(query);
				resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					lista_tutores.add(new Tutor(resultSet.getString("ID"), resultSet.getString("NOME"), resultSet.getString("CPF"),
							resultSet.getString("EMAIL"), resultSet.getString("TELEFONE"), resultSet.getString("ENDERECO")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DAO.fecharConexao(preparedStatement, resultSet);
			}
			
			if(lista_tutores.size() < 0) {
				JOptionPane.showMessageDialog(null, "Não possui tutores para serem listados", "", JOptionPane.WARNING_MESSAGE);
			}
			return lista_tutores;
		}

		@Override
		public void excluir(String id) {
			Connection conexao = Conexao.getInstancia().abrirConexao();
			String query = EXCLUIR_TUTOR;
			
			try {
				preparedStatement = conexao.prepareStatement(query);
				
				int i = 1;
				
				preparedStatement.setString(i++, id);
				
				preparedStatement.executeUpdate();
				conexao.commit();
				
				JOptionPane.showMessageDialog(null, "Tutor excluído dos registros com sucesso");
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DAO.fecharConexao(preparedStatement, resultSet);
			}
		}


}
