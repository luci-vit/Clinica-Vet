package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.Conexao;
import model.VacinaMinistrada;

public class DAOVacinaMinistrada implements DAO<VacinaMinistrada>{
	
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;

	
	private static String CADASTRAR_VACINAMINISTRADA = " INSERT INTO VACINAS_MINISTRADA "
			+ " (ID, DATAMINISTRADA, VALIDADEVACINA, IDVACINAMINISTRADA) "
			+ " VALUES (NULL, ?, ?, ?) ";
	
	private static String CONSULTAR_VACINAMINISTRADA = " SELECT * FROM VACINAS_MINISTRADA " 
			+ " WHERE ID = ? ";
	
	private static String ALTERAR_VACINAMINISTRADA = " UPDATE VACINAS_MINISTRADAS SET "
			+ " DATAMINISTRADA = ?, VALIDADEVACINA = ?, IDVACINAMINISTRADA = ? "
			+ "WHERE ID = ?";
	
	private static String LISTAR_VACINAMINISTRADAS = " SELECT * FROM VACINAS_MINISTRADAS "
			+ " WHERE ID = ? ";
	
	private static String EXCLUIR_VACINAMINISTRADA = " DELETE FROM VACINAS_MINISTRADAS "
			+ " WHERE ID = ? ";

	@Override
	public void efetuarCadastro(VacinaMinistrada entidade) {
		
		Connection conexao = Conexao.getInstancia().abrirConexao();
		
		String query = CADASTRAR_VACINAMINISTRADA;
		
		try {
			
			preparedStatement = conexao.prepareStatement(query);
			
			int i = 1;
			
			preparedStatement.setString(i++, entidade.getDataMinistrada());
			preparedStatement.setString(i++, entidade.getValidadeVacina());
			preparedStatement.setString(i++, entidade.getIdVacinaMinistrada());
			
			preparedStatement.executeUpdate();
			conexao.commit();
			
			JOptionPane.showMessageDialog(null, "Vacina Ministrada cadastrada com sucesso");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAO.fecharConexao(preparedStatement, resultSet);
		}
	}

	@Override
	public VacinaMinistrada buscarPorId(String id) {
	
		Connection conexao = Conexao.getInstancia().abrirConexao();
		VacinaMinistrada vacinaMinistrada = null;
		String query = CONSULTAR_VACINAMINISTRADA;
		
		try {
		
			preparedStatement = conexao.prepareStatement(query);
			
			int i = 1;
			
			preparedStatement.setString(i++, id);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				vacinaMinistrada = new VacinaMinistrada(resultSet.getString("ID"), resultSet.getString("DATAMINISTRADA"), resultSet.getString("VALIDADEVACINA"),
						resultSet.getString("IDVACINAMINISTRADA"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAO.fecharConexao(preparedStatement, resultSet);
		}

		if(vacinaMinistrada == null) {
			JOptionPane.showMessageDialog(null, "Não foi possível localizar a vacina ministrada", "", JOptionPane.WARNING_MESSAGE);
		}

		return vacinaMinistrada;
	}

	@Override
	public void editarDados(VacinaMinistrada entidade, String id) {
		Connection conexao = Conexao.getInstancia().abrirConexao();
		String query = ALTERAR_VACINAMINISTRADA;
		
		try {
			preparedStatement = conexao.prepareStatement(query);
			
			int i = 1;
			
			preparedStatement.setString(i++, entidade.getDataMinistrada());
			preparedStatement.setString(i++, entidade.getValidadeVacina());
			preparedStatement.setString(i++, entidade.getIdVacinaMinistrada());
			preparedStatement.setString(i++, id);
			
			preparedStatement.executeUpdate();
			conexao.commit();
			
			JOptionPane.showMessageDialog(null, "Dados da vacina ministrada alterado com sucesso!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAO.fecharConexao(preparedStatement, resultSet);
		}
	}

	@Override
	public ArrayList<VacinaMinistrada> listarTodos() {
		
		Connection conexao = Conexao.getInstancia().abrirConexao();
		String query = LISTAR_VACINAMINISTRADAS;
		ArrayList<VacinaMinistrada> lista_vacinasministradas = new ArrayList<VacinaMinistrada>();
		
		try {
			preparedStatement = conexao.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				lista_vacinasministradas.add(new VacinaMinistrada(resultSet.getString("ID"), resultSet.getString("DATAMINISTRADA"), resultSet.getString("VALIDADEVACINA"),
						resultSet.getString("IDVACINAMINISTRADA")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAO.fecharConexao(preparedStatement, resultSet);
		}
		
		if(lista_vacinasministradas.size() < 0) {
			JOptionPane.showMessageDialog(null, "Não possui vacinas ministradas para serem listadas", "", JOptionPane.WARNING_MESSAGE);
		}
		return lista_vacinasministradas;
	}

	@Override
	public void excluir(String id) {
		Connection conexao = Conexao.getInstancia().abrirConexao();
		String query = EXCLUIR_VACINAMINISTRADA;
		
		try {
			preparedStatement = conexao.prepareStatement(query);
			
			int i = 1;
			
			preparedStatement.setString(i++, id);
			
			preparedStatement.executeUpdate();
			conexao.commit();
			
			JOptionPane.showMessageDialog(null, "Vacina ministrada excluída dos registros com sucesso");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAO.fecharConexao(preparedStatement, resultSet);
		}
	}
}
