package model;
public class Consulta {
	
	private String id;
	private String horario;
	private int idAnimal;
	private String especialidade;
	private String data;
	private String veterinarioResponsavel;
	private String problema;
	private String diagnostico;
	private String medicamentos;
	private int idTutor;
	private int statusConsultas;
	
	public Consulta(String id, String horario, int idAnimal, String especialidade, String data,
			String veterinarioResponsavel, int idTutor, String problema, String diagnostico, String medicamentos, int statusConsulta) {
		this.id = id;
		this.horario = horario;
		this.idAnimal = idAnimal;
		this.especialidade = especialidade;
		this.data = data;
		this.veterinarioResponsavel = veterinarioResponsavel;
		this.problema = problema;
		this.diagnostico = diagnostico;
		this.medicamentos = medicamentos;
		this.idTutor = idTutor;
		this.setStatusConsultas(statusConsulta);
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

	public int getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(int idAnimal) {
		this.idAnimal = idAnimal;
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

	public int getIdTutor() {
		return idTutor;
	}

	public void setIdTutor(int idTutor) {
		this.idTutor = idTutor;
	}

	public int getStatusConsultas() {
		return statusConsultas;
	}

	public void setStatusConsultas(int statusConsultas) {
		this.statusConsultas = statusConsultas;
	}
	
	
	
	

}
