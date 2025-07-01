package model;
public class Consulta {
	
	private String id;
	private String horario;
	private String nomeAnimal;
	private String especialidade;
	private String data;
	private String veterinarioResponsavel;
	private String problema;
	private String diagnostico;
	private String medicamentos;
	
	public Consulta(String id, String horario, String nomeAnimal, String especialidade, String data,
			String veterinarioResponsavel, String problema, String diagnostico, String medicamentos) {
		this.id = id;
		this.horario = horario;
		this.nomeAnimal = nomeAnimal;
		this.especialidade = especialidade;
		this.data = data;
		this.veterinarioResponsavel = veterinarioResponsavel;
		this.problema = problema;
		this.diagnostico = diagnostico;
		this.medicamentos = medicamentos;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getNomeAnimal() {
		return nomeAnimal;
	}

	public void setNome_animal(String nome_animal) {
		this.nomeAnimal = nome_animal;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getVeterinarioResponsavel() {
		return veterinarioResponsavel;
	}

	public void setVeterinarioResponsavel(String veterinarioResponsavel) {
		this.veterinarioResponsavel = veterinarioResponsavel;
	}

	public String getProblema() {
		return problema;
	}

	public void setProblema(String problema) {
		this.problema = problema;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(String medicamentos) {
		this.medicamentos = medicamentos;
	}
	
	
	
	

}
