package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VacinaMinistrada{
	
	private String id;
	private int idAnimal;
	private String dataMinistrada;
	private String validadeVacina;
	private int idVacina;
	
	public VacinaMinistrada(String id, int idAnimal, String dataMinistrada, String validadeVacina, int idVacina) {
		this.id = id;
		this.idAnimal = idAnimal;
		this.dataMinistrada = dataMinistrada;
		this.validadeVacina = validadeVacina;
		this.idVacina = idVacina;
	}
		
	public String getDataMinistrada() {
		return dataMinistrada;
	}

	public void setDataMinistrada(String dataMinistrada) {
		this.dataMinistrada = dataMinistrada;
	}

	public String getValidadeVacina() {
		return validadeVacina;
	}

	public void setValidadeVacina(String validadeVacina) {
		this.validadeVacina = validadeVacina;
	}
	
	public int getIdVacina() {
		return idVacina;
	}
	
	public void setIdVacina(int idVacina) {
		this.idVacina = idVacina;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(int idAnimal) {
		this.idAnimal = idAnimal;
	}
	
}
