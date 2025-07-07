package model.modelos_tabelas;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.Vacina;

public class ModeloTabelaVacinas extends AbstractTableModel{

	
	private static final String[] columns = {"Id", "Fabricante", "Validade", "Tempo_Imunidade", "Nome"};

	private ArrayList<Vacina> vacinas;

	public ModeloTabelaVacinas(ArrayList<Vacina> vacinas) { // Construtor para definir os animais no arrayList.
		super();
		this.vacinas = vacinas;
	}

	@Override
	public int getRowCount() { // Define o número de linhas através do tamanho do arraylist de clientes.
		return vacinas.size();
	}

	@Override
	public int getColumnCount() { // Define o número de colunas baseado no array de columns
		return columns.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) { // Faz o povoamento da tabela
		Vacina vacina = vacinas.get(rowIndex);
		if (columnIndex == 0) {
			return vacina.getId();
		} else if (columnIndex == 1) {
			return vacina.getFabricante();
		} else if (columnIndex == 2) {
			return vacina.getValidade();
		} else if (columnIndex == 3) {
			return vacina.getTempoImunidade();
		} else if (columnIndex == 4) {
			return vacina.getNome();
		} else {
			return null;
		}
	}
	

	@Override
	public String getColumnName(int column) { // Atribui o nome das colunas com base nas strings do array columns.
		return columns[column];
	}

}
