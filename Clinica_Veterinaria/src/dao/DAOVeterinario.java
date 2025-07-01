package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.Conexao;
import model.Veterinario;

public class DAOVeterinario implements DAO<Veterinario>{
	
	
	  	private static PreparedStatement preparedStatement = null;
	    private static ResultSet resultSet = null;

	    private static String CADASTRAR_VETERINARIO = " INSERT INTO CONTRIBUIDORES " +
	            " (ID, NOME, CPF, EMAIL, TELEFONE, TURNO_TRABALHO, FUNCAO, CFMV, ESPECIALIDADE) "
	            + " VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?) ";

	    private static String CONSULTAR_VETERINARIO = " SELECT * FROM CONTRIBUIDORES " +
	            " WHERE ID = ? ";

	    private static String ALTERAR_VETERINARIO = " UPDATE CONTRIBUIDORES SET " +
	            " NOME = ?, CPF = ?, EMAIL = ?, TELEFONE = ?, CFMV = ? , ESPECIALIDADE = ? ";

	    private static String LISTAR_VETERINARIOS = " SELECT * FROM CONTRIBUIDORES "
	            + " WHERE FUNCAO == 'veterinario' ";

	    private static String EXCLUIR_VETERINARIOS = " DELETE FROM CONTRIBUIDORES "
	            + " WHERE ID = ? ";

	    @Override
		public void efetuarCadastro(Veterinario entidade) {
			
			Connection conexao = Conexao.getInstancia().abrirConexao();
			
			String query = CADASTRAR_VETERINARIO;
			
			try {
				
				preparedStatement = conexao.prepareStatement(query);
				
				int i = 1;
				
				preparedStatement.setString(i++, entidade.getNome());
				preparedStatement.setString(i++, entidade.getCpf());
				preparedStatement.setString(i++, entidade.getEmail());
				preparedStatement.setString(i++, entidade.getTelefone());
				preparedStatement.setString(i++, entidade.getTurnoTrabalho());
				preparedStatement.setString(i++, entidade.getFuncao());
				preparedStatement.setString(i++, entidade.getCfmv());
				preparedStatement.setString(i++, entidade.getEspecialidade());
				
				preparedStatement.executeUpdate();
				conexao.commit();
				
				JOptionPane.showMessageDialog(null, "Veterinario cadastrado com sucesso");
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DAO.fecharConexao(preparedStatement, resultSet);
			}
		}

		@Override
		public Veterinario buscarPorId(String id) {
		
			Connection conexao = Conexao.getInstancia().abrirConexao();
			Veterinario veterinario = null;
			String query = CONSULTAR_VETERINARIO;
			
			try {
			
				preparedStatement = conexao.prepareStatement(query);
				
				int i = 1;
				
				preparedStatement.setString(i++, id);
				resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					veterinario = new Veterinario(resultSet.getString("ID"), resultSet.getString("NOME"), resultSet.getString("CPF"),
							resultSet.getString("EMAIL"), resultSet.getString("TELEFONE"), resultSet.getString("TURNO_TRABALHO"), 
							resultSet.getString("FUNCAO"),resultSet.getString("CFMV"), resultSet.getString("ESPECIALIDADE"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DAO.fecharConexao(preparedStatement, resultSet);
			}

			if(veterinario == null) {
				JOptionPane.showMessageDialog(null, "Não foi possível localizar o veterinario", "", JOptionPane.WARNING_MESSAGE);
			}

			return veterinario;
		}

		@Override
		public void editarDados(Veterinario entidade, String id) {
			Connection conexao = Conexao.getInstancia().abrirConexao();
			String query = ALTERAR_VETERINARIO;
			
			try {
				preparedStatement = conexao.prepareStatement(query);
				
				int i = 1;
				
				preparedStatement.setString(i++, entidade.getNome());
				preparedStatement.setString(i++, entidade.getCpf());
				preparedStatement.setString(i++, entidade.getEmail());
				preparedStatement.setString(i++, entidade.getTelefone());
				preparedStatement.setString(i++, entidade.getTurnoTrabalho());
				preparedStatement.setString(i++, entidade.getFuncao());
				preparedStatement.setString(i++, entidade.getCfmv());
				preparedStatement.setString(i++, entidade.getEspecialidade());
				preparedStatement.setString(i++, id);
				
				preparedStatement.executeUpdate();
				conexao.commit();
				
				JOptionPane.showMessageDialog(null, "Dados do veterinario alterado com sucesso!");
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DAO.fecharConexao(preparedStatement, resultSet);
			}
		}

		@Override
		public ArrayList<Veterinario> listarTodos() {
			
			Connection conexao = Conexao.getInstancia().abrirConexao();
			String query = LISTAR_VETERINARIOS;
			ArrayList<Veterinario> lista_veterinarios = new ArrayList<Veterinario>();
			
			try {
				preparedStatement = conexao.prepareStatement(query);
				resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					lista_veterinarios.add( new Veterinario(resultSet.getString("ID"), resultSet.getString("NOME"), resultSet.getString("CPF"),
							resultSet.getString("EMAIL"), resultSet.getString("TELEFONE"), resultSet.getString("TURNO_TRABALHO"), 
							resultSet.getString("FUNCAO"),resultSet.getString("CFMV"), resultSet.getString("ESPECIALIDADE")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DAO.fecharConexao(preparedStatement, resultSet);
			}
			
			if(lista_veterinarios.size() < 0) {
				JOptionPane.showMessageDialog(null, "Não possui veterinarios para serem listados", "", JOptionPane.WARNING_MESSAGE);
			}
			return lista_veterinarios;
		}

		@Override
		public void excluir(String id) {
			Connection conexao = Conexao.getInstancia().abrirConexao();
			String query = EXCLUIR_VETERINARIOS;
			
			try {
				preparedStatement = conexao.prepareStatement(query);
				
				int i = 1;
				
				preparedStatement.setString(i++, id);
				
				preparedStatement.executeUpdate();
				conexao.commit();
				
				JOptionPane.showMessageDialog(null, "Veterinario excluído dos registros com sucesso");
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DAO.fecharConexao(preparedStatement, resultSet);
			}
		}


}
