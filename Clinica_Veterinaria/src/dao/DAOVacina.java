package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.Conexao;
import model.Vacina;

public class DAOVacina implements DAO<Vacina>{
	
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	
	private static String CADASTRAR_VACINA = " INSERT INTO VACINAS "
			+ " (ID, FABRICANTE, VALIDADE, TEMPO_IMUNIDADE_MESES, NOME) "
			+ " VALUES (NULL, ?, ?, ?, ?) ";
	 
	private static String ALTERAR_VACINA = " UPDATE VACINAS SET " 
			+ " FABRICANTE = ?, VALIDADE = ?, TEMPO_IMUNIDADE_MESES = ?, NOME = ? ";
	
	private static String CONSULTAR_VACINA = " SELECT * FROM VACINAS " 
			+ " WHERE ID = ? ";
	
	private static String LISTAR_VACINAS = " SELECT * FROM VACINAS "
			+ " WHERE 1=1 ";
	
	private static String EXCLUIR_VACINA = " DELETE FROM VACINAS "
			+ " WHERE ID = ? ";
	
	private static  String CONSULTAR_POR_NOME = "SELECT * FROM VACINAS WHERE NOME = ?";


	@Override
	public void efetuarCadastro(Vacina entidade) {
		
		Connection conexao = Conexao.getInstancia().abrirConexao();
		
		String query = CADASTRAR_VACINA;
		
		try {
			
			preparedStatement = conexao.prepareStatement(query);
			
			int i = 1;
			
			preparedStatement.setString(i++, entidade.getFabricante());
			preparedStatement.setString(i++, entidade.getValidade());
			preparedStatement.setString(i++, entidade.getTempoImunidade());
			preparedStatement.setString(i++, entidade.getNome());
			
			preparedStatement.executeUpdate();
			conexao.commit();
			
			JOptionPane.showMessageDialog(null, "Vacina cadastrada com sucesso");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAO.fecharConexao(preparedStatement, resultSet);
		}
	}

	@Override
	public Vacina buscarPorId(String id) {
	
		Connection conexao = Conexao.getInstancia().abrirConexao();
		Vacina vacina = null;
		String query = CONSULTAR_VACINA;
		
		try {
		
			preparedStatement = conexao.prepareStatement(query);
			
			int i = 1;
			
			preparedStatement.setString(i++, id);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				vacina = new Vacina(resultSet.getString("ID"), resultSet.getString("FABRICANTE"), resultSet.getString("VALIDADE"),
						resultSet.getString("TEMPO_IMUNIDADE_MESES"), resultSet.getString("NOME"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAO.fecharConexao(preparedStatement, resultSet);
		}

		if(vacina == null) {
			JOptionPane.showMessageDialog(null, "Não foi possível localizar a vacina", "", JOptionPane.WARNING_MESSAGE);
		}

		return vacina;
	}
	
	public Vacina buscarPorNome(String nome) {
	    Connection conexao = Conexao.getInstancia().abrirConexao();
	    Vacina vacina = null;

	    try {
	        preparedStatement = conexao.prepareStatement(CONSULTAR_POR_NOME);
	        preparedStatement.setString(1, nome);
	        resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            vacina = new Vacina(
	                resultSet.getString("ID"),
	                resultSet.getString("FABRICANTE"),
	                resultSet.getString("VALIDADE"),
	                resultSet.getString("TEMPO_IMUNIDADE_MESES"),
	                resultSet.getString("NOME")
	            );
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DAO.fecharConexao(preparedStatement, resultSet);
	    }

	    if (vacina == null) {
	        JOptionPane.showMessageDialog(null, "Vacina não encontrada com o nome informado", "", JOptionPane.WARNING_MESSAGE);
	    }

	    return vacina;
	}

	@Override
	public void editarDados(Vacina entidade, String id) {
		Connection conexao = Conexao.getInstancia().abrirConexao();
		String query = ALTERAR_VACINA;
		
		try {
			preparedStatement = conexao.prepareStatement(query);
			
			int i = 1;
			
			preparedStatement.setString(i++, entidade.getFabricante());
			preparedStatement.setString(i++, entidade.getValidade());
			preparedStatement.setString(i++, entidade.getTempoImunidade());
			preparedStatement.setString(i++, entidade.getNome());
			preparedStatement.setString(i++, id);
			
			preparedStatement.executeUpdate();
			conexao.commit();
			
			JOptionPane.showMessageDialog(null, "Dados da vacina alterado com sucesso!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAO.fecharConexao(preparedStatement, resultSet);
		}
	}

	@Override
	public ArrayList<Vacina> listarTodos() {
		
		Connection conexao = Conexao.getInstancia().abrirConexao();
		String query = LISTAR_VACINAS;
		ArrayList<Vacina> lista_vacinas = new ArrayList<Vacina>();
		
		try {
			preparedStatement = conexao.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				lista_vacinas.add(new Vacina(resultSet.getString("ID"), resultSet.getString("FABRICANTE"), resultSet.getString("VALIDADE"),
						resultSet.getString("TEMPO_IMUNIDADE_MESES"), resultSet.getString("NOME")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAO.fecharConexao(preparedStatement, resultSet);
		}
		
		if(lista_vacinas.size() < 0) {
			JOptionPane.showMessageDialog(null, "Não possui vacinas para serem listadas", "", JOptionPane.WARNING_MESSAGE);
		}
		return lista_vacinas;
	}

	@Override
	public void excluir(String id) {
		Connection conexao = Conexao.getInstancia().abrirConexao();
		String query = EXCLUIR_VACINA;
		
		try {
			preparedStatement = conexao.prepareStatement(query);
			
			int i = 1;
			
			preparedStatement.setString(i++, id);
			
			preparedStatement.executeUpdate();
			conexao.commit();
			
			JOptionPane.showMessageDialog(null, "Vacina excluída dos registros com sucesso");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAO.fecharConexao(preparedStatement, resultSet);
		}
	}


}
