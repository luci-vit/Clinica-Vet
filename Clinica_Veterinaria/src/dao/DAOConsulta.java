package dao;

import java.security.PrivateKey;
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
	
	private static String CADASTRAR_CONSULTA = " INSERT INTO CONSULTAS " + 
			"(ID, HORARIO, ID_ANIMAL, ESPECIALIDADE, DATA, ID_VETERINARIO_RESPONSAVEL, PROBLEMA, DIAGNOSTICO, MEDICAMENTOS, ID_TUTOR, STATUS_CONSULTAS)" +
			"VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static String CONSULTAR_CONSULTA = " SELECT * FROM CONSULTAS " + " WHERE ID=? ";
	
	private static String ALTERAR_CONSULTA = " UPDATE CONSULTAS SET " + 
			" HORARIO = ?, ID_ANIMAL = ?, ESPECIALIDADE = ?, DATA = ?, ID_VETERINARIO_RESPONSAVEL = ?, PROBLEMA = ?, DIAGNOSTICO = ?, MEDICAMENTOS = ?, "
			+ "ID_TUTOR = ?, STATUS_CONSULTAS = ? "
			+ "WHERE ID = ?";
	
	private static String LISTAR_CONSULTAS_PENDENTES = " SELECT * FROM CONSULTAS " + " WHERE STATUS_CONSULTAS = 0";
	
	private static String LISTAR_CONSULTAS_FINALIZADAS = " SELECT * FROM CONSULTAS " + " WHERE STATUS_CONSULTAS = 1";
	
	private static String LISTAR_CONSULTAS_MEDICO = " SELECT * FROM CONSULTAS " + " WHERE ID_VETERINARIO_RESPONSAVEL = ?";
	
	private static String EXCLUIR_CONSULTA = " DELETE FROM CONSULTAS " + " WHERE ID = ?"; 
	
	
	@Override
	public void efetuarCadastro(Consulta entidade) {
		
		Connection conexao = Conexao.getInstancia().abrirConexao();
		
		String query = CADASTRAR_CONSULTA;
		
		try {
			
			preparedStatement = conexao.prepareStatement(query);
			
			int i = 1;
			
			preparedStatement.setString(i++, entidade.getHorario());
			preparedStatement.setInt(i++, entidade.getIdAnimal());
			preparedStatement.setString(i++, entidade.getEspecialidade());
			preparedStatement.setString(i++, entidade.getData());
			preparedStatement.setString(i++, entidade.getVeterinarioResponsavel());
			preparedStatement.setString(i++, entidade.getProblema());
			preparedStatement.setString(i++, entidade.getDiagnostico());
			preparedStatement.setString(i++, entidade.getMedicamentos());
			preparedStatement.setInt(i++, entidade.getIdTutor());
			preparedStatement.setInt(i++, entidade.getStatusConsultas());


			
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
				consulta = new Consulta(resultSet.getString("ID"), resultSet.getString("HORARIO"), resultSet.getInt("ID_ANIMAL"),
						resultSet.getString("ESPECIALIDADE"), resultSet.getString("DATA"), resultSet.getString("ID_VETERINARIO_RESPONSAVEL"),
						resultSet.getInt("ID_TUTOR"), resultSet.getString("PROBLEMA"), resultSet.getString("DIAGNOSTICO"), 
						resultSet.getString("MEDICAMENTOS"), resultSet.getInt("STATUS_CONSULTAS"));
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
			preparedStatement.setInt(i++, entidade.getIdAnimal());
			preparedStatement.setString(i++, entidade.getEspecialidade());
			preparedStatement.setString(i++, entidade.getData());
			preparedStatement.setString(i++, entidade.getVeterinarioResponsavel());
			preparedStatement.setString(i++, entidade.getProblema());
			preparedStatement.setString(i++, entidade.getDiagnostico());
			preparedStatement.setString(i++, entidade.getMedicamentos());
			preparedStatement.setInt(i++, entidade.getIdTutor());
			preparedStatement.setInt(i++, entidade.getStatusConsultas());
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
		String query = LISTAR_CONSULTAS_PENDENTES;
		ArrayList<Consulta> lista_consultas = new ArrayList<Consulta>();
		
		try {
			preparedStatement = conexao.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				lista_consultas.add(new Consulta(resultSet.getString("ID"), resultSet.getString("HORARIO"), resultSet.getInt("ID_ANIMAl"),
						resultSet.getString("ESPECIALIDADE"), resultSet.getString("DATA"), resultSet.getString("ID_VETERINARIO_RESPONSAVEL"), 
						resultSet.getInt("ID_TUTOR"), resultSet.getString("PROBLEMA"), resultSet.getString("DIAGNOSTICO"), 
						resultSet.getString("MEDICAMENTOS"), resultSet.getInt("STATUS_CONSULTAS")));
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
	
	public ArrayList<Consulta> listarConsultasFinalizadas() {
		
		Connection conexao = Conexao.getInstancia().abrirConexao();
		String query = LISTAR_CONSULTAS_FINALIZADAS;
		ArrayList<Consulta> lista_consultas = new ArrayList<Consulta>();
		
		try {
			preparedStatement = conexao.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				lista_consultas.add(new Consulta(resultSet.getString("ID"), resultSet.getString("HORARIO"), resultSet.getInt("ID_ANIMAl"),
						resultSet.getString("ESPECIALIDADE"), resultSet.getString("DATA"), resultSet.getString("ID_VETERINARIO_RESPONSAVEL"), 
						resultSet.getInt("ID_TUTOR"), resultSet.getString("PROBLEMA"), resultSet.getString("DIAGNOSTICO"), 
						resultSet.getString("MEDICAMENTOS"), resultSet.getInt("STATUS_CONSULTAS")));
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
	
	public ArrayList<Consulta> listarConsultasMedico(String idMedico) {
		
		Connection conexao = Conexao.getInstancia().abrirConexao();
		String query = LISTAR_CONSULTAS_MEDICO;
		ArrayList<Consulta> lista_consultas = new ArrayList<Consulta>();
		
		try {
			preparedStatement = conexao.prepareStatement(query);
			preparedStatement.setString(1, idMedico);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				lista_consultas.add(new Consulta(resultSet.getString("ID"), resultSet.getString("HORARIO"), resultSet.getInt("ID_ANIMAl"),
						resultSet.getString("ESPECIALIDADE"), resultSet.getString("DATA"), resultSet.getString("ID_VETERINARIO_RESPONSAVEL"), 
						resultSet.getInt("ID_TUTOR"), resultSet.getString("PROBLEMA"), resultSet.getString("DIAGNOSTICO"), 
						resultSet.getString("MEDICAMENTOS"), resultSet.getInt("STATUS_CONSULTAS")));
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
