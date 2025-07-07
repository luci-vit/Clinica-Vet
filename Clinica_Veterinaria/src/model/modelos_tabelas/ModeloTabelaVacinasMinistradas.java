package model.modelos_tabelas;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import dao.DAOAnimal;
import dao.DAOVacina;
import model.Vacina;
import model.VacinaMinistrada;

public class ModeloTabelaVacinasMinistradas extends AbstractTableModel {
	private static final String[] columns = {"Id", "Animal", "Vacina", "Data Ministrada", "Validade"};

	private ArrayList<VacinaMinistrada> vacinasMinistradas;

	public ModeloTabelaVacinasMinistradas(ArrayList<VacinaMinistrada> vacinasMinistradas) { // Construtor para definir os animais no arrayList.
		super();
		this.vacinasMinistradas = vacinasMinistradas;
	}

	@Override
	public int getRowCount() { // Define o número de linhas através do tamanho do arraylist de clientes.
		return vacinasMinistradas.size();
	}

	@Override
	public int getColumnCount() { // Define o número de colunas baseado no array de columns
		return columns.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) { // Faz o povoamento da tabela
		VacinaMinistrada vacinaMinistrada = vacinasMinistradas.get(rowIndex);
		DAOAnimal daoAnimal = new DAOAnimal();
		DAOVacina daoVacina = new DAOVacina();
		if (columnIndex == 0) {
			return vacinaMinistrada.getId();
		} else if (columnIndex == 1) {
			return daoAnimal.buscarPorId(String.valueOf(vacinaMinistrada.getIdAnimal())).getNome();
		} else if (columnIndex == 2) {
			return daoVacina.buscarPorId(String.valueOf(vacinaMinistrada.getIdVacina())).getNome();
		} else if (columnIndex == 3) {
			return vacinaMinistrada.getDataMinistrada();
		} else if (columnIndex == 4) {
			return vacinaMinistrada.getValidadeVacina(); 
		} else {
			return null;
		}
	}
	
	@Override
	public String getColumnName(int column) { // Atribui o nome das colunas com base nas strings do array columns.
		return columns[column];
	}

}
