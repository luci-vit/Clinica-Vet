package dao;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.Conexao;
import model.Animal;
import model.Tutor;

public class DAOAnimal  implements DAO<Animal>{

	private static PreparedStatement preparedStatement = null; //Prepara e executa os comandos SQL.
	private static ResultSet resultSet = null; //Armazena o resultado retornado pelo preparedStatement após a execução de uma querry.
	
	private static String CADASTRAR_ANIMAL = " INSERT INTO ANIMAL " + " (ID, NOME, ESPECIE, RACA, DATA_NASCIMENTO, ID_TUTOR) "
			+ "VALUES (NULL, ?, ?, ?, ?, ?)"; 
	
	private static String CONSULTAR_ANIMAL = " SELECT * FROM ANIMAL " + "WHERE ID = ?";
	
	private static String CONSULTAR_ANIMAL_NOME_E_TUTOR = " SELECT ID FROM ANIMAL " + " WHERE NOME = ? AND ID_TUTOR = ? ";
	
	private static String ALTERAR_ANIMAL = " UPDATE ANIMAL SET " + 
	" NOME = ?, ESPECIE = ?, RACA = ?, DATA_NASCIMENTO = ?, ID_TUTOR = ? " + " WHERE ID = ? ";
	
	private static String LISTAR_ANIMAIS = " SELECT * FROM ANIMAL " + " WHERE 1 = 1 ";
	
	private static String EXCLUIR_ANIMAL = " DELETE FROM CLIENTE " + "WHERE ID = ?";
	
	private static String BUSCAR_TUTOR_RESPONSAVEL = " SELECT ID FROM TUTOR WHERE CPF = ?";
	
	@Override
	public void efetuarCadastro(Animal entidade) {
		
		Connection conexao = Conexao.getInstancia().abrirConexao();
		
		String query = CADASTRAR_ANIMAL;
		
		try {
			
			preparedStatement = conexao.prepareStatement(query);
			
			int i = 1;
			
			preparedStatement.setString(i++, entidade.getNome());
			preparedStatement.setString(i++, entidade.getEspecie());
			preparedStatement.setString(i++, entidade.getRaca());
			preparedStatement.setString(i++, entidade.getDataNascimento());
			preparedStatement.setInt(i++, entidade.getIdTutor());
			
			preparedStatement.executeUpdate();
			conexao.commit();
			
			JOptionPane.showMessageDialog(null, "Animal cadastrado com sucesso");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAO.fecharConexao(preparedStatement, resultSet);
		}
	}

	@Override
	public Animal buscarPorId(String id) {
	
		Connection conexao = Conexao.getInstancia().abrirConexao();
		Animal animal = null;
		String query = CONSULTAR_ANIMAL;
		
		try {
		
			preparedStatement = conexao.prepareStatement(query);
			
			int i = 1;
			
			preparedStatement.setString(i++, id);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				animal = new Animal(resultSet.getString("ID"), resultSet.getString("NOME"), resultSet.getString("ESPECIE"),
						resultSet.getString("RACA"), resultSet.getString("DATA_NASCIMENTO"), resultSet.getInt("ID_TUTOR"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAO.fecharConexao(preparedStatement, resultSet);
		}

		if(animal == null) {
			JOptionPane.showMessageDialog(null, "Não foi possível localizar o animal", "", JOptionPane.WARNING_MESSAGE);
		}

		return animal;
	}

	@Override
	public void editarDados(Animal entidade, String id) {
		Connection conexao = Conexao.getInstancia().abrirConexao();
		String query = ALTERAR_ANIMAL;
		
		try {
			preparedStatement = conexao.prepareStatement(query);
			
			int i = 1;
			
			preparedStatement.setString(i++, entidade.getNome());
			preparedStatement.setString(i++, entidade.getEspecie());
			preparedStatement.setString(i++, entidade.getRaca());
			preparedStatement.setString(i++, entidade.getDataNascimento());
			preparedStatement.setInt(i++, entidade.getIdTutor());
			preparedStatement.setString(i++, id);
			
			preparedStatement.executeUpdate();
			conexao.commit();
			
			JOptionPane.showMessageDialog(null, "Dados do animal alterado com sucesso!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAO.fecharConexao(preparedStatement, resultSet);
		}
	}

	@Override
	public ArrayList<Animal> listarTodos() {
		
		Connection conexao = Conexao.getInstancia().abrirConexao();
		String query = LISTAR_ANIMAIS;
		ArrayList<Animal> lista_animais = new ArrayList<Animal>();
		
		try {
			preparedStatement = conexao.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				lista_animais.add(new Animal(resultSet.getString("ID"), resultSet.getString("NOME"), resultSet.getString("ESPECIE"),
						resultSet.getString("RACA"), resultSet.getString("DATA_NASCIMENTO"), resultSet.getInt("ID_TUTOR")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAO.fecharConexao(preparedStatement, resultSet);
		}
		
		if(lista_animais.size() < 0) {
			JOptionPane.showMessageDialog(null, "Não possui animais para serem listados", "", JOptionPane.WARNING_MESSAGE);
		}
		return lista_animais;
	}

	@Override
	public void excluir(String id) {
		Connection conexao = Conexao.getInstancia().abrirConexao();
		String query = EXCLUIR_ANIMAL;
		
		try {
			preparedStatement = conexao.prepareStatement(query);
			
			int i = 1;
			
			preparedStatement.setString(i++, id);
			
			preparedStatement.executeUpdate();
			conexao.commit();
			
			JOptionPane.showMessageDialog(null, "Animal excluído dos registros com sucesso");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAO.fecharConexao(preparedStatement, resultSet);
		}
	}
	
	public int buscarTutorResponsavel(String cpf) {
		Connection conexao = Conexao.getInstancia().abrirConexao();
		String query = BUSCAR_TUTOR_RESPONSAVEL;
		int id = -1;
		
		try {
			PreparedStatement preparedStatementaux = conexao.prepareStatement(query);
			preparedStatementaux.setString(1, cpf);
			ResultSet resultSetaux = preparedStatementaux.executeQuery();
			
			if(resultSetaux.next()) {
				id = resultSetaux.getInt("ID");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			Conexao.getInstancia().fecharConexao();
		}
		
		return id;
		
	}
	
	public int bucarIdAnimal(String nomeAnimal,  int idTutor) {
		Connection conexao = Conexao.getInstancia().abrirConexao();
		String query = CONSULTAR_ANIMAL_NOME_E_TUTOR;
		int id = -1;
		
		try {
			PreparedStatement preparedStatementaux = conexao.prepareStatement(query);
			preparedStatementaux.setString(1, nomeAnimal);
			preparedStatementaux.setInt(2, idTutor);
			ResultSet resultSetaux = preparedStatementaux.executeQuery();
			
			if(resultSetaux.next()) {
				id = resultSetaux.getInt("ID");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			Conexao.getInstancia().fecharConexao();
		}
		
		return id;
	}

}
