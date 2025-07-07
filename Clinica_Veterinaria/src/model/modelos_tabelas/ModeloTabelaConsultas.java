package model.modelos_tabelas;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.Consulta;

public class ModeloTabelaConsultas extends AbstractTableModel {

	private static final String[] columns = {
			"Id", "Horario", "Nome_Animal", "Especialidade", "Data", "Veterinario_Responsavel", "Problema", "Diagn\u00F3sticos", "Medicamentos", "Cpf_Tutor"
		};

	private ArrayList<Consulta> consultas;

	public ModeloTabelaConsultas(ArrayList<Consulta> consultas) { //Construtor para definir os animais no arrayList.
		super();
		this.consultas = consultas;
	}

	@Override
	public int getRowCount() { // Define o número de linhas através do tamanho do arraylist de clientes.
		return consultas.size();
	}

	@Override
	public int getColumnCount() { // Define o número de colunas baseado no array de columns
		return columns.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) { // Faz o povoamento da tabela
		Consulta consulta = consultas.get(rowIndex);
		if (columnIndex == 0) {
			return consulta.getId();
		} else if (columnIndex == 1) {
			return consulta.getHorario();
		} else if (columnIndex == 2) {
			return consulta.getIdAnimal();
		} else if (columnIndex == 3) {
			return consulta.getEspecialidade();
		} else if (columnIndex == 4) {
			return consulta.getData();
		} else if (columnIndex == 5) {
			return consulta.getVeterinarioResponsavel();
		} else if(columnIndex == 6) {
			return consulta.getProblema();
		} else if(columnIndex == 7) {
			return consulta.getDiagnostico();
		} else if(columnIndex == 8) {
			return consulta.getMedicamentos();
		}else if(columnIndex == 9){
			return consulta.getIdTutor();
		}else {
			return null;
		}
	}
	
	@Override
	public String getColumnName(int column) { // Atribui o nome das colunas com base nas strings do array columns.
		return columns[column];
	}

}
