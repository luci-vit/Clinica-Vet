package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.Conexao;
import model.Consulta;

public class DAOConsulta implements DAO<Consulta>{
	
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	
	private static String CADASTRAR_CONSULTA = " INSERT INTO CONSULTA " + 
			"(ID, HORARIO, NOMEANIMAL, ESPECIALIDADE, DATA, VETERINARIORESPONSAVEL, PROBLEMA, DIAGNOSTICO, MEDICAMENTOS)" +
			"VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static String CONSULTAR_CONSULTA = " SELECT FROM CONSULTAS " + " WHERE ID=? ";
	
	private static String ALTERAR_CONSULTA = " UPDATE CONSULTA SET " + 
			"HORARIO = ?, NOMEANIMAL = ?, ESPECIALIDADE = ?, DATA = ?, VETERINARIORESPONSAVEL = ?, PROBLEMA = ?, DIAGNOSTICO = ?, MEDICAMENTOS = ?)"
			+ "WHERE ID = ?";
	
	private static String LISTAR_CONSULTA = " SELECT * FROM CONSULTA " + " WHERE 1 = 1";
	
	private static String EXCLUIR_CONSULTA = " DELETE FROM CONSULTA " + " WHERE ID = ?"; 
	
	@Override
	public void efetuarCadastro(Consulta entidade) {
		
		Connection conexao = Conexao.getInstancia().abrirConexao();
		
		String query = CADASTRAR_CONSULTA;
		
		try {
			
			preparedStatement = conexao.prepareStatement(query);
			
			int i = 1;
			
			preparedStatement.setString(i++, entidade.getHorario());
			preparedStatement.setString(i++, entidade.getNomeAnimal());
			preparedStatement.setString(i++, entidade.getEspecialidade());
			preparedStatement.setString(i++, entidade.getData());
			preparedStatement.setString(i++, entidade.getVeterinarioResponsavel());
			preparedStatement.setString(i++, entidade.getProblema());
			preparedStatement.setString(i++, entidade.getDiagnostico());
			preparedStatement.setString(i++, entidade.getMedicamentos());

			
			preparedStatement.executeUpdate();
			conexao.commit();
			
			JOptionPane.showMessageDialog(null, "Consulta cadastrada com sucesso");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAO.fecharConexao(preparedStatement, resultSet);
		}
	}

	@Override
	public Consulta buscarPorId(String id) {
	
		Connection conexao = Conexao.getInstancia().abrirConexao();
		Consulta consulta = null;
		String query = CONSULTAR_CONSULTA;
		
		try {
		
			preparedStatement = conexao.prepareStatement(query);
			
			int i = 1;
			
			preparedStatement.setString(i++, id);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				consulta = new Consulta(resultSet.getString("ID"), resultSet.getString("HORARIO"), resultSet.getString("NOMEANIMAL"),
						resultSet.getString("ESPECIALIDADE"), resultSet.getString("DATA"), resultSet.getString("VETERINARIORESPONSAVEL"),
						 resultSet.getString("PROBLEMA"), resultSet.getString("DIAGNOSTICO"), resultSet.getString("MEDICAMENTOS"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAO.fecharConexao(preparedStatement, resultSet);
		}

		if(consulta == null) {
			JOptionPane.showMessageDialog(null, "Não foi possível localizar a consulta", "", JOptionPane.WARNING_MESSAGE);
		}

		return consulta;
	}

	@Override
	public void editarDados(Consulta entidade, String id) {
		Connection conexao = Conexao.getInstancia().abrirConexao();
		String query = ALTERAR_CONSULTA;
		
		try {
			preparedStatement = conexao.prepareStatement(query);
			
			int i = 1;
			
			preparedStatement.setString(i++, entidade.getHorario());
			preparedStatement.setString(i++, entidade.getNomeAnimal());
			preparedStatement.setString(i++, entidade.getEspecialidade());
			preparedStatement.setString(i++, entidade.getData());
			preparedStatement.setString(i++, entidade.getVeterinarioResponsavel());
			preparedStatement.setString(i++, entidade.getProblema());
			preparedStatement.setString(i++, entidade.getDiagnostico());
			preparedStatement.setString(i++, entidade.getMedicamentos());
			preparedStatement.setString(i++, id);
			
			preparedStatement.executeUpdate();
			conexao.commit();
			
			JOptionPane.showMessageDialog(null, "Dados da consulta alterada com sucesso!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAO.fecharConexao(preparedStatement, resultSet);
		}
	}

	@Override
	public ArrayList<Consulta> listarTodos() {
		
		Connection conexao = Conexao.getInstancia().abrirConexao();
		String query = LISTAR_CONSULTA;
		ArrayList<Consulta> lista_consultas = new ArrayList<Consulta>();
		
		try {
			preparedStatement = conexao.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				lista_consultas.add(new Consulta(resultSet.getString("ID"), resultSet.getString("HORARIO"), resultSet.getString("NOMEANIMAL"),
						resultSet.getString("ESPECIALIDADE"), resultSet.getString("DATA"), resultSet.getString("VETERINARIORESPONSAVEL"),
						 resultSet.getString("PROBLEMA"), resultSet.getString("DIAGNOSTICO"), resultSet.getString("MEDICAMENTOS")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAO.fecharConexao(preparedStatement, resultSet);
		}
		
		if(lista_consultas.size() < 0) {
			JOptionPane.showMessageDialog(null, "Não possui consultas para serem listadas", "", JOptionPane.WARNING_MESSAGE);
		}
		return lista_consultas;
	}

	@Override
	public void excluir(String id) {
		Connection conexao = Conexao.getInstancia().abrirConexao();
		String query = EXCLUIR_CONSULTA;
		
		try {
			preparedStatement = conexao.prepareStatement(query);
			
			int i = 1;
			
			preparedStatement.setString(i++, id);
			
			preparedStatement.executeUpdate();
			conexao.commit();
			
			JOptionPane.showMessageDialog(null, "Consulta excluída dos registros com sucesso");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAO.fecharConexao(preparedStatement, resultSet);
		}
	}
	

}
