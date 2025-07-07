package view.TelasCadastro;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import dao.DAOAnimal;
import dao.DAOConsulta;
import dao.DAOTutor;
import dao.DAOVeterinario;
import model.Consulta;
import model.Funcionario;
import model.Veterinario;
import view.TelasListagem.ConsultasScreen;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CadastroConsulta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> comboBoxHorario;
	private JTextField textFieldNomeDoAnimal;
	private JTextField textFieldEspecialidade;
	private JFormattedTextField textFieldData;
	private JTextField textFieldVeterinario;
	private JTextField textFieldCpfTutor;
	private JLabel lblCpfTutor;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				CadastroConsulta frame = new CadastroConsulta(null, null, null);
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public CadastroConsulta(Consulta consulta, ConsultasScreen consultasScreen, Funcionario funcionario)
			throws ParseException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 670);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(50, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(60, 52, 795, 407);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblHorario = new JLabel("Horário:");
		lblHorario.setBounds(39, 12, 70, 24);
		panel.add(lblHorario);

		comboBoxHorario = new JComboBox<>(gerarHorariosDisponiveis());
		comboBoxHorario.setBounds(39, 34, 199, 24);
		panel.add(comboBoxHorario);

		JLabel lblNomeAnimal = new JLabel("Nome do animal:");
		lblNomeAnimal.setBounds(39, 66, 143, 15);
		panel.add(lblNomeAnimal);

		JLabel lblEspecialidade = new JLabel("Especialidade:");
		lblEspecialidade.setBounds(347, 65, 103, 15);
		panel.add(lblEspecialidade);

		JLabel lblVeterinario = new JLabel("Veterinário:");
		lblVeterinario.setBounds(39, 118, 103, 15);
		panel.add(lblVeterinario);

		JLabel lblProbelma = new JLabel("Problema:");
		lblProbelma.setBounds(39, 169, 114, 15);
		lblProbelma.setVisible(false);
		panel.add(lblProbelma);

		JLabel lblDiagnostico = new JLabel("Diagnóstico:");
		lblDiagnostico.setBounds(39, 245, 114, 15);
		lblDiagnostico.setVisible(false);
		panel.add(lblDiagnostico);

		JLabel lblMedicamentos = new JLabel("Medicamentos:");
		lblMedicamentos.setBounds(39, 315, 143, 24);
		lblMedicamentos.setVisible(false);
		panel.add(lblMedicamentos);

		JLabel lblData = new JLabel("Data:");
		lblData.setBounds(347, 18, 70, 15);
		panel.add(lblData);

		textFieldNomeDoAnimal = new JTextField();
		textFieldNomeDoAnimal.setBounds(39, 82, 199, 24);
		panel.add(textFieldNomeDoAnimal);
		textFieldNomeDoAnimal.setColumns(10);

		textFieldEspecialidade = new JTextField();
		textFieldEspecialidade.setBounds(347, 82, 225, 24);
		panel.add(textFieldEspecialidade);
		textFieldEspecialidade.setColumns(10);

		MaskFormatter mask = new MaskFormatter("##/##/####");
		mask.setPlaceholderCharacter('_');
		textFieldData = new JFormattedTextField(mask);
		textFieldData.setBounds(347, 34, 225, 24);
		panel.add(textFieldData);
		textFieldData.setColumns(10);

		textFieldVeterinario = new JTextField();
		textFieldVeterinario.setBounds(39, 133, 199, 24);
		panel.add(textFieldVeterinario);
		textFieldVeterinario.setColumns(10);

		JTextArea textAreaProblema = new JTextArea();
		textAreaProblema.setBounds(39, 185, 670, 48);
		textAreaProblema.setVisible(false);
		panel.add(textAreaProblema);

		JTextArea textAreaDiagnostico = new JTextArea();
		textAreaDiagnostico.setBounds(39, 262, 670, 48);
		textAreaDiagnostico.setVisible(false);
		panel.add(textAreaDiagnostico);

		JTextArea textAreaMedicamentos = new JTextArea();
		textAreaMedicamentos.setBounds(39, 338, 670, 48);
		textAreaMedicamentos.setVisible(false);
		panel.add(textAreaMedicamentos);

		textFieldCpfTutor = new JTextField();
		textFieldCpfTutor.setColumns(10);
		textFieldCpfTutor.setBounds(347, 133, 199, 24);
		panel.add(textFieldCpfTutor);

		lblCpfTutor = new JLabel("Cpf tutor:");
		lblCpfTutor.setBounds(347, 118, 103, 15);
		panel.add(lblCpfTutor);

		JLabel lblNewLabel_7 = new JLabel("Cadastro de consulta");
		lblNewLabel_7.setBounds(50, 0, 308, 40);
		contentPane.add(lblNewLabel_7);
		lblNewLabel_7.setFont(new Font("Dialog", Font.BOLD, 24));

		JButton btnEmitirBoleto = new JButton("Emitir Boleto");
		btnEmitirBoleto.setVisible(false);
		btnEmitirBoleto.setBounds(60, 523, 143, 40);
		contentPane.add(btnEmitirBoleto);

		JButton btnCadastrar = new JButton(consulta == null ? "Cadastrar" : "Alterar");
		btnCadastrar.addActionListener(e -> {

			DAOConsulta daoConsulta = new DAOConsulta();
			DAOAnimal daoAnimal = new DAOAnimal();
			DAOVeterinario daoVeterinario = new DAOVeterinario();

			boolean isAVeterinarian = funcionario instanceof Veterinario;
			String horarioSelecionado = (String) comboBoxHorario.getSelectedItem();
			if (!isAVeterinarian) {
				Consulta dadosConsulta = new Consulta(null, horarioSelecionado,
						daoAnimal.bucarIdAnimal(textFieldNomeDoAnimal.getText(),
						daoAnimal.buscarTutorResponsavel(textFieldCpfTutor.getText())),
						textFieldEspecialidade.getText(), textFieldData.getText(),
						daoVeterinario.buscarVeterinarioPorNome(textFieldVeterinario.getText()),
						daoAnimal.buscarTutorResponsavel(textFieldCpfTutor.getText()), "-", "-", "-", 0);
				if (consulta == null) {
					daoConsulta.efetuarCadastro(dadosConsulta);
				} else {
					daoConsulta.editarDados(dadosConsulta, consulta.getId());
				}
			} 
			abrirTelaPrincipal(consultasScreen, funcionario);
		});
		btnCadastrar.setBounds(60, 471, 143, 39);
		contentPane.add(btnCadastrar);

		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DAOConsulta daoConsulta = new DAOConsulta();
				DAOAnimal daoAnimal = new DAOAnimal();
				
				String horarioSelecionado = (String) comboBoxHorario.getSelectedItem();
				
				Consulta dadosConsulta = new Consulta(null, horarioSelecionado,
						daoAnimal.bucarIdAnimal(textFieldNomeDoAnimal.getText(),
						daoAnimal.buscarTutorResponsavel(textFieldCpfTutor.getText())),
						textFieldEspecialidade.getText(), textFieldData.getText(), textFieldVeterinario.getText(),
						daoAnimal.buscarTutorResponsavel(textFieldCpfTutor.getText()), textAreaProblema.getText(),
						textAreaDiagnostico.getText(), textAreaMedicamentos.getText(), 1);
				
				daoConsulta.editarDados(dadosConsulta, consulta.getId());
				abrirTelaPrincipal(consultasScreen, funcionario);
			}
		});
		btnFinalizar.setBounds(712, 471, 143, 39);
		btnFinalizar.setVisible(false);
		contentPane.add(btnFinalizar);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(e -> ativarEdicao(true));
		btnAlterar.setBounds(712, 9, 143, 30);
		btnAlterar.setVisible(false);
		contentPane.add(btnAlterar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(e -> {
			DAOConsulta daoConsulta = new DAOConsulta();
			if (consulta != null) {
				daoConsulta.excluir(consulta.getId());
				abrirTelaPrincipal(consultasScreen, funcionario);
			}
		});
		btnExcluir.setVisible(false);
		btnExcluir.setBounds(60, 575, 143, 40);
		contentPane.add(btnExcluir);

		if (funcionario instanceof Veterinario) {
			preencherCampos(consulta);
			ativarEdicao(false);
			btnCadastrar.setVisible(false);
			btnFinalizar.setVisible(true);
			textAreaDiagnostico.setVisible(true);
			textAreaProblema.setVisible(true);
			textAreaMedicamentos.setVisible(true);
			lblProbelma.setVisible(true);
			lblDiagnostico.setVisible(true);
			lblMedicamentos.setVisible(true);
		} else {
			if (consulta != null) {
				preencherCampos(consulta);
				ativarEdicao(false);
				btnEmitirBoleto.setVisible(true);
				btnExcluir.setVisible(true);
				btnAlterar.setVisible(true);
			}
		}
	}

	private void preencherCampos(Consulta consulta) {
		DAOTutor daoTutor = new DAOTutor();
		DAOAnimal daoAnimal = new DAOAnimal();
		DAOVeterinario daoVeterinario = new DAOVeterinario();
		comboBoxHorario.setSelectedItem(consulta.getHorario());
		textFieldNomeDoAnimal.setText(daoAnimal.buscarPorId(String.valueOf(consulta.getIdAnimal())).getNome());
		textFieldEspecialidade.setText(consulta.getEspecialidade());
		textFieldData.setText(consulta.getData());
		textFieldCpfTutor.setText(daoTutor.buscarPorId(String.valueOf(consulta.getIdTutor())).getCpf());
		textFieldVeterinario.setText(daoVeterinario.buscarPorId(consulta.getVeterinarioResponsavel()).getNome());
	}

	private void abrirTelaPrincipal(ConsultasScreen consultasScreen, Funcionario funcioario) {
		consultasScreen.dispose();
		dispose();
		ConsultasScreen newConsultaScreen = new ConsultasScreen(funcioario);
		newConsultaScreen.setLocationRelativeTo(null);
		newConsultaScreen.setVisible(true);
	}

	private void ativarEdicao(boolean habilitar) {
		comboBoxHorario.setEnabled(habilitar);
		textFieldData.setEnabled(habilitar);
		textFieldNomeDoAnimal.setEnabled(habilitar);
		textFieldEspecialidade.setEnabled(habilitar);
		textFieldVeterinario.setEnabled(habilitar);
		textFieldCpfTutor.setEnabled(habilitar);
	}

	private String[] gerarHorariosDisponiveis() {
		String[] horariosManha = gerarHorarios("08:00", "12:00", 20);
		String[] horariosTarde = gerarHorarios("14:00", "18:00", 20);
		String[] todosHorarios = new String[horariosManha.length + horariosTarde.length];
		System.arraycopy(horariosManha, 0, todosHorarios, 0, horariosManha.length);
		System.arraycopy(horariosTarde, 0, todosHorarios, horariosManha.length, horariosTarde.length);
		return todosHorarios;
	}

	private String[] gerarHorarios(String inicio, String fim, int intervaloMinutos) {
		LocalTime horaInicio = LocalTime.parse(inicio);
		LocalTime horaFim = LocalTime.parse(fim);
		List<String> horarios = new ArrayList<>();
		while (!horaInicio.isAfter(horaFim.minusMinutes(intervaloMinutos))) {
			horarios.add(horaInicio.toString());
			horaInicio = horaInicio.plusMinutes(intervaloMinutos);
		}
		return horarios.toArray(new String[0]);
	}
}
