package model;


public class VacinaMinistrada{
	
	private String id;
	private String dataMinistrada;
	private String validadeVacina;
	private String idVacinaMinistrada;
	
	public VacinaMinistrada(String id, String dataMinistrada, String validadeVacina, String idVacinaMinistrada) {
		this.setId(id);
		this.dataMinistrada = dataMinistrada;
		this.validadeVacina = validadeVacina;
		this.idVacinaMinistrada = idVacinaMinistrada;
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
	
	public String getIdVacinaMinistrada() {
		return idVacinaMinistrada;
	}
	
	public void setIdVacinaMinistrada(String idVacinaMinistrada) {
		this.idVacinaMinistrada = idVacinaMinistrada;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
