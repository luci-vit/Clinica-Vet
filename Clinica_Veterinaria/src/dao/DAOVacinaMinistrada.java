package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.Conexao;
import model.VacinaMinistrada;

public class DAOVacinaMinistrada implements DAO<VacinaMinistrada> {

    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    private static final String CADASTRAR_VACINAMINISTRADA = "INSERT INTO VACINAS_MINISTRADAS "
            + "(ID, ID_ANIMAL, DATA_MINISTRADA, VALIDADE, ID_VACINA) VALUES (NULL, ?, ?, ?, ?)";

    private static final String CONSULTAR_VACINAMINISTRADA = "SELECT * FROM VACINAS_MINISTRADAS WHERE ID = ?";

    private static final String ALTERAR_VACINAMINISTRADA = "UPDATE VACINAS_MINISTRADAS SET "
            + "ID_ANIMAL = ?, DATA_MINISTRADA = ?, VALIDADE = ?, ID_VACINA = ? WHERE ID = ?";

    private static final String LISTAR_VACINAMINISTRADAS = "SELECT * FROM VACINAS_MINISTRADAS";

    private static final String EXCLUIR_VACINAMINISTRADA = "DELETE FROM VACINAS_MINISTRADAS WHERE ID = ?";

    @Override
    public void efetuarCadastro(VacinaMinistrada entidade) {
        Connection conexao = Conexao.getInstancia().abrirConexao();
        try {
            preparedStatement = conexao.prepareStatement(CADASTRAR_VACINAMINISTRADA);
            preparedStatement.setInt(1, entidade.getIdAnimal());
            preparedStatement.setString(2, entidade.getDataMinistrada());
            preparedStatement.setString(3, entidade.getValidadeVacina());
            preparedStatement.setInt(4, entidade.getIdVacina());

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

        try {
            preparedStatement = conexao.prepareStatement(CONSULTAR_VACINAMINISTRADA);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                vacinaMinistrada = new VacinaMinistrada(
                        resultSet.getString("ID"),
                        resultSet.getInt("ID_ANIMAL"),
                        resultSet.getString("DATA_MINISTRADA"),
                        resultSet.getString("VALIDADE"),
                        resultSet.getInt("ID_VACINA")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAO.fecharConexao(preparedStatement, resultSet);
        }

        if (vacinaMinistrada == null) {
            JOptionPane.showMessageDialog(null, "Não foi possível localizar a vacina ministrada", "", JOptionPane.WARNING_MESSAGE);
        }

        return vacinaMinistrada;
    }

    @Override
    public void editarDados(VacinaMinistrada entidade, String id) {
        Connection conexao = Conexao.getInstancia().abrirConexao();

        try {
            preparedStatement = conexao.prepareStatement(ALTERAR_VACINAMINISTRADA);
            preparedStatement.setInt(1, entidade.getIdAnimal());
            preparedStatement.setString(2, entidade.getDataMinistrada());
            preparedStatement.setString(3, entidade.getValidadeVacina());
            preparedStatement.setInt(4, entidade.getIdVacina());
            preparedStatement.setString(5, id);

            preparedStatement.executeUpdate();
            conexao.commit();

            JOptionPane.showMessageDialog(null, "Dados da vacina ministrada alterados com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAO.fecharConexao(preparedStatement, resultSet);
        }
    }

    @Override
    public ArrayList<VacinaMinistrada> listarTodos() {
        Connection conexao = Conexao.getInstancia().abrirConexao();
        ArrayList<VacinaMinistrada> lista = new ArrayList<>();

        try {
            preparedStatement = conexao.prepareStatement(LISTAR_VACINAMINISTRADAS);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                lista.add(new VacinaMinistrada(
                        resultSet.getString("ID"),
                        resultSet.getInt("ID_ANIMAL"),
                        resultSet.getString("DATA_MINISTRADA"),
                        resultSet.getString("VALIDADE"),
                        resultSet.getInt("ID_VACINA")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAO.fecharConexao(preparedStatement, resultSet);
        }

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não possui vacinas ministradas para serem listadas", "", JOptionPane.WARNING_MESSAGE);
        }

        return lista;
    }

    @Override
    public void excluir(String id) {
        Connection conexao = Conexao.getInstancia().abrirConexao();

        try {
            preparedStatement = conexao.prepareStatement(EXCLUIR_VACINAMINISTRADA);
            preparedStatement.setString(1, id);
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