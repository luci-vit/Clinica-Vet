package model.modelos_tabelas;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.Tutor;

public class ModeloTabelaTutores extends AbstractTableModel{
	
	private static final String[] columns = {"Id", "Nome", "CPF", "Email", "Telefone", "Endere\u00E7o"};

	private ArrayList<Tutor> tutores;

	public ModeloTabelaTutores(ArrayList<Tutor> tutores) { // Construtor para definir os animais no																		// arrayList.
		super();
		this.tutores = tutores;
	}

	@Override
	public int getRowCount() { // Define o número de linhas através do tamanho do arraylist de clientes.
		return tutores.size();
	}

	@Override
	public int getColumnCount() { // Define o número de colunas baseado no array de columns
		return columns.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) { // Faz o povoamento da tabela
		Tutor tutor = tutores.get(rowIndex);
		if (columnIndex == 0) {
			return tutor.getId();
		} else if (columnIndex == 1) {
			return tutor.getNome();
		} else if (columnIndex == 2) {
			return tutor.getCpf();
		} else if (columnIndex == 3) {
			return tutor.getEmail();
		} else if (columnIndex == 4) {
			return tutor.getTelefone();
		} else if (columnIndex == 5) {
			return tutor.getEndereco();
		} else {
			return null;
		}
	}
	

	@Override
	public String getColumnName(int column) { // Atribui o nome das colunas com base nas strings do array columns.
		return columns[column];
	}

}
