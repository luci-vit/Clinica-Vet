package model.modelos_tabelas;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.Consulta;
import model.Funcionario;
import model.Veterinario;

public class ModeloTabelaContribuidores extends AbstractTableModel {

	private static final String[] columns = { "Id", "Nome", "CPF", "E-mail", "Telefone", "Turno_Trabalho", "Funcao",
			"Cfmv", "Especialidade" };

	private ArrayList<Funcionario> contribuidores;

	public ModeloTabelaContribuidores(ArrayList<Funcionario> contribuidores) { // Construtor para definir os animais no																		// arrayList.
		super();
		this.contribuidores = contribuidores;
	}

	@Override
	public int getRowCount() { // Define o número de linhas através do tamanho do arraylist de clientes.
		return contribuidores.size();
	}

	@Override
	public int getColumnCount() { // Define o número de colunas baseado no array de columns
		return columns.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) { // Faz o povoamento da tabela
		Funcionario contribuidor = contribuidores.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return contribuidor.getId();
		case 1:
			return contribuidor.getNome();
		case 2:
			return contribuidor.getCpf();
		case 3:
			return contribuidor.getEmail();
		case 4:
			return contribuidor.getTelefone();
		case 5:
			return contribuidor.getTurnoTrabalho();
		case 6:
			return contribuidor.getFuncao(); // "Veterinário", "Zelador", etc.
		case 7:
			if (contribuidor instanceof Veterinario) {
				return ((Veterinario) contribuidor).getCfmv();
			} else {
				return "-";
			}
		case 8:
			if (contribuidor instanceof Veterinario) {
				return ((Veterinario) contribuidor).getEspecialidade();
			} else {
				return "-";
			}
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int column) { // Atribui o nome das colunas com base nas strings do array columns.
		return columns[column];
	}

}
