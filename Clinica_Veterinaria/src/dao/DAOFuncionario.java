package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.Conexao;
import model.Funcionario;
import model.Usuario;
import model.Veterinario;

public class DAOFuncionario implements DAO<Funcionario>{
	
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	
	private static String CADASTRAR_FUNCIONARIO = " INSERT INTO CONTRIBUIDORES " + 
			"(ID, NOME, CPF, EMAIL, TELEFONE, TURNO_TRABALHO, FUNCAO)" 
			+ "VALUES (NULL, ?, ?, ?, ?, ?, ?)";
	
	private static String cADASTRAR_FUNCIONARIO_COM_USUARIO = " INSERT INTO CONTRIBUIDORES " + 
			"(ID, NOME, CPF, EMAIL, TELEFONE, TURNO_TRABALHO, FUNCAO, USUARIO, SENHA)" 
			+ "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static String CONSULTAR_FUNCIONARIO = " SELECT * FROM CONTRIBUIDORES " + 
			"WHERE ID = ?";
	
	private static String ALTERAR_FUNCIONARIO = " UPDATE CONTRIBUIDORES SET " + 
			"NOME = ?, CPF = ?, EMAIL = ?, TELEFONE = ?, TURNO_TRABALHO = ?, FUNCAO = ?" +
			" WHERE ID = ?";
	
	private static String LISTAR_FUNCIONARIOS = " SELECT * FROM CONTRIBUIDORES " 
			+ " WHERE 1 = 1 ";
	
	private static String EXCLUIR_FUNCIONARIOS = " DELETE FROM CONTRIBUIDORES " 
			+ "WHERE ID = ?";
	
	@Override
	public void efetuarCadastro(Funcionario entidade) {
		
		Connection conexao = Conexao.getInstancia().abrirConexao();
		
		String query = CADASTRAR_FUNCIONARIO;
		
		try {
			
			preparedStatement = conexao.prepareStatement(query);
			
			int i = 1;
			
			preparedStatement.setString(i++, entidade.getNome());
			preparedStatement.setString(i++, entidade.getCpf());
			preparedStatement.setString(i++, entidade.getEmail());
			preparedStatement.setString(i++, entidade.getTelefone());
			preparedStatement.setString(i++, entidade.getTurnoTrabalho());
			preparedStatement.setString(i++, entidade.getFuncao());
			
			preparedStatement.executeUpdate();
			conexao.commit();
			
			JOptionPane.showMessageDialog(null, "Funcionario cadastrado com sucesso");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAO.fecharConexao(preparedStatement, resultSet);
		}
	}
	
	@Override
	public void efetuarCadastroComUsuario(Funcionario entidade, Usuario usuario) {

		Connection conexao = Conexao.getInstancia().abrirConexao();
		
		String query = cADASTRAR_FUNCIONARIO_COM_USUARIO;
		
		try {
			
			preparedStatement = conexao.prepareStatement(query);
			
			int i = 1;
			
			preparedStatement.setString(i++, entidade.getNome());
			preparedStatement.setString(i++, entidade.getCpf());
			preparedStatement.setString(i++, entidade.getEmail());
			preparedStatement.setString(i++, entidade.getTelefone());
			preparedStatement.setString(i++, entidade.getTurnoTrabalho());
			preparedStatement.setString(i++, entidade.getFuncao());
	    	preparedStatement.setString(i++, usuario.getUsuario());
			preparedStatement.setString(i++, usuario.getSenha());
			
			
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
	public Funcionario buscarPorId(String id) {
	
		Connection conexao = Conexao.getInstancia().abrirConexao();
		Funcionario funcionario = null;
		String query = CONSULTAR_FUNCIONARIO;
		
		try {
		
			preparedStatement = conexao.prepareStatement(query);
			
			int i = 1;
			
			preparedStatement.setString(i++, id);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				funcionario = new Funcionario(resultSet.getString("ID"), resultSet.getString("NOME"), resultSet.getString("CPF"),
						resultSet.getString("EMAIL"), resultSet.getString("TELEFONE"), resultSet.getString("TURNO_TRABALHO"), resultSet.getString("FUNCAO"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAO.fecharConexao(preparedStatement, resultSet);
		}

		if(funcionario == null) {
			JOptionPane.showMessageDialog(null, "Não foi possível localizar o funcionario", "", JOptionPane.WARNING_MESSAGE);
		}

		return funcionario;
	}

	@Override
	public void editarDados(Funcionario entidade, String id) {
		Connection conexao = Conexao.getInstancia().abrirConexao();
		String query = ALTERAR_FUNCIONARIO;
		
		try {
			preparedStatement = conexao.prepareStatement(query);
			
			int i = 1;
			
			preparedStatement.setString(i++, entidade.getNome());
			preparedStatement.setString(i++, entidade.getCpf());
			preparedStatement.setString(i++, entidade.getEmail());
			preparedStatement.setString(i++, entidade.getTelefone());
			preparedStatement.setString(i++, entidade.getTurnoTrabalho());
			preparedStatement.setString(i++, entidade.getFuncao());
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
	public ArrayList<Funcionario> listarTodos() {
		
		Connection conexao = Conexao.getInstancia().abrirConexao();
		String query = LISTAR_FUNCIONARIOS;
		ArrayList<Funcionario> lista_funcionarios = new ArrayList<Funcionario>();
		
		try {
			preparedStatement = conexao.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				if (resultSet.getString("FUNCAO").toLowerCase().equals("veterinario")) {
					lista_funcionarios.add(new Veterinario(resultSet.getString("ID"), resultSet.getString("NOME"), resultSet.getString("CPF"),
							resultSet.getString("EMAIL"), resultSet.getString("TELEFONE"), resultSet.getString("TURNO_TRABALHO"), 
							resultSet.getString("FUNCAO"), resultSet.getString("CFMV"), resultSet.getString("ESPECIALIDADE")));
				}else {
					lista_funcionarios.add(new Funcionario(resultSet.getString("ID"), resultSet.getString("NOME"), resultSet.getString("CPF"),
							resultSet.getString("EMAIL"), resultSet.getString("TELEFONE"), resultSet.getString("TURNO_TRABALHO"), 
							resultSet.getString("FUNCAO")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAO.fecharConexao(preparedStatement, resultSet);
		}
		
		if(lista_funcionarios.size() < 0) {
			JOptionPane.showMessageDialog(null, "Não possui funcionais para serem listados", "", JOptionPane.WARNING_MESSAGE);
		}
		return lista_funcionarios;
	}

	@Override
	public void excluir(String id) {
		Connection conexao = Conexao.getInstancia().abrirConexao();
		String query = EXCLUIR_FUNCIONARIOS;
		
		try {
			preparedStatement = conexao.prepareStatement(query);
			
			int i = 1;
			
			preparedStatement.setString(i++, id);
			
			preparedStatement.executeUpdate();
			conexao.commit();
			
			JOptionPane.showMessageDialog(null, "Funcionario excluído dos registros com sucesso");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAO.fecharConexao(preparedStatement, resultSet);
		}
	}


}
