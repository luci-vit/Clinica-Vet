package model.modelos_tabelas;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.Animal;

public class ModeloTabelaAnimais extends AbstractTableModel {

	private static final String[] columns = { "Id", "Nome", "Esp\u00E9cie ", "Ra\u00E7a", "Data_Nascimento",
			"Id_Tutor" };

	private ArrayList<Animal> animais;

	public ModeloTabelaAnimais(ArrayList<Animal> animais) { //Construtor para definir os animais no arrayList.
		super();
		this.animais = animais;
	}

	@Override
	public int getRowCount() { // Define o número de linhas através do tamanho do arraylist de clientes.
		return animais.size();
	}

	@Override
	public int getColumnCount() { // Define o número de colunas baseado no array de columns
		return columns.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) { // Faz o povoamento da tabela
		Animal animal = animais.get(rowIndex);
		if (columnIndex == 0) {
			return animal.getId();
		} else if (columnIndex == 1) {
			return animal.getNome();
		} else if (columnIndex == 2) {
			return animal.getEspecie();
		} else if (columnIndex == 3) {
			return animal.getRaca();
		} else if (columnIndex == 4) {
			return animal.getDataNascimento();
		} else if (columnIndex == 5) {
			return animal.getIdTutor();
		} else {
			return null;
		}
	}

	@Override
	public String getColumnName(int column) { // Atribui o nome das colunas com base nas strings do array columns.
		return columns[column];
	}

}
