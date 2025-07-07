package view.TelasCadastro;

import java.awt.EventQueue;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import dao.DAOAnimal;
import dao.DAOTutor;
import dao.DAOVacina;
import dao.DAOVacinaMinistrada;
import model.Vacina;
import model.VacinaMinistrada;
import view.TelasListagem.VacinasMinistradasScreen;
import view.TelasListagem.VacinasScreen;

public class CadastroVacinaMinistrada extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNomeAnimal;
	private JTextField textFieldDataMinistrada;
	private JTextField textFieldValidadeVacina;
	private JTextField textFieldCpfTutor;
	private JComboBox<String> comboBoxVacinas;
	private JButton btnCadastrar;
	private JButton btnAlterar;
	private JButton btnExcluir;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private LocalDate dataAtual = LocalDate.now();
	private VacinaMinistrada vacinaMinistrada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				CadastroVacinaMinistrada frame = new CadastroVacinaMinistrada(null, null);
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastroVacinaMinistrada(VacinaMinistrada vacinaMinistrada, VacinasMinistradasScreen vacinasMinistradasScreen) {
		this.vacinaMinistrada = vacinaMinistrada;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(39, 37, 500, 249);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNomeAnimal = new JLabel("Nome Animal:");
		lblNomeAnimal.setBounds(34, 22, 96, 15);
		panel.add(lblNomeAnimal);

		JLabel lblDataMinistrada = new JLabel("Data ministrada:");
		lblDataMinistrada.setBounds(34, 149, 135, 15);
		panel.add(lblDataMinistrada);

		JLabel lblValidadeVacina = new JLabel("Validade da vacina:");
		lblValidadeVacina.setBounds(268, 79, 164, 15);
		panel.add(lblValidadeVacina);

		textFieldNomeAnimal = new JTextField();
		textFieldNomeAnimal.setBounds(34, 39, 185, 30);
		panel.add(textFieldNomeAnimal);

		textFieldDataMinistrada = new JTextField();
		textFieldDataMinistrada.setBounds(34, 166, 184, 30);
		panel.add(textFieldDataMinistrada);

		textFieldValidadeVacina = new JTextField();
		textFieldValidadeVacina.setBounds(268, 98, 184, 30);
		panel.add(textFieldValidadeVacina);

		comboBoxVacinas = new JComboBox<>();
		comboBoxVacinas.setBounds(34, 98, 185, 30);
		panel.add(comboBoxVacinas);

		JLabel lblNomeVacina = new JLabel("Nome Vacina:");
		lblNomeVacina.setBounds(34, 81, 96, 15);
		panel.add(lblNomeVacina);

		btnCadastrar = new JButton(vacinaMinistrada == null ? "Cadastrar" : "Atualizar");
		btnCadastrar.setBounds(246, 168, 117, 25);
		panel.add(btnCadastrar);

		textFieldCpfTutor = new JTextField();
		textFieldCpfTutor.setBounds(267, 39, 185, 30);
		panel.add(textFieldCpfTutor);

		JLabel lblCpfTutor = new JLabel("Cpf Tutor:");
		lblCpfTutor.setBounds(267, 22, 96, 15);
		panel.add(lblCpfTutor);

		JLabel lblTitulo = new JLabel("Cadastro de vacina ministrada");
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 18));
		lblTitulo.setBounds(39, 0, 323, 22);
		contentPane.add(lblTitulo);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(39, 299, 117, 30);
		btnExcluir.setVisible(vacinaMinistrada != null);
		contentPane.add(btnExcluir);

		btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(420, 5, 117, 30);
		btnAlterar.setVisible(vacinaMinistrada != null);
		contentPane.add(btnAlterar);

		// ➤ Preencher ComboBox com vacinas do banco
		DAOVacina daoVacina = new DAOVacina();
		List<Vacina> vacinas = daoVacina.listarTodos();
		for (Vacina v : vacinas) {
			comboBoxVacinas.addItem(v.getNome());
		}

		// ➤ Preenche automaticamente a data do dia
		textFieldDataMinistrada.setText(dataAtual.format(formatter));

		// ➤ Ao selecionar vacina, preenche validade
		comboBoxVacinas.addActionListener(e -> {
			String nome = (String) comboBoxVacinas.getSelectedItem();
			if (nome != null) {
				Vacina vacina = daoVacina.buscarPorNome(nome);
				if (vacina != null) {
					try {
						int meses = Integer.parseInt(vacina.getTempoImunidade());
						LocalDate validade = dataAtual.plusMonths(meses);
						textFieldValidadeVacina.setText(validade.format(formatter));
					} catch (NumberFormatException ex) {
						System.err.println("Erro ao converter tempo imunidade: " + ex.getMessage());
					}
				}
			}
		});

		// ➤ Cadastro ou atualização
		btnCadastrar.addActionListener(e -> {
			DAOVacinaMinistrada daoMin = new DAOVacinaMinistrada();
			DAOAnimal daoAnimal = new DAOAnimal();
			Vacina vacina = daoVacina.buscarPorNome((String) comboBoxVacinas.getSelectedItem());
			VacinaMinistrada novaVacinaMin = new VacinaMinistrada(null, 
					daoAnimal.bucarIdAnimal(textFieldNomeAnimal.getText(),
					daoAnimal.buscarTutorResponsavel(textFieldCpfTutor.getText())),
					textFieldDataMinistrada.getText(),
					textFieldValidadeVacina.getText(), vacina != null ? Integer.parseInt(vacina.getId()) : 0);
			if (vacinaMinistrada == null) {
				daoMin.efetuarCadastro(novaVacinaMin);
			} else {
				daoMin.editarDados(novaVacinaMin, vacinaMinistrada.getId());
			}
			abrirTelaPrincipal(vacinasMinistradasScreen);
		});

		// ➤ Excluir
		btnExcluir.addActionListener(e -> {
			if (vacinaMinistrada != null) {
				new DAOVacinaMinistrada().excluir(vacinaMinistrada.getId());
			}
			abrirTelaPrincipal(vacinasMinistradasScreen);
		});

		// ➤ Habilita edição
		btnAlterar.addActionListener(e -> ativarEdicao(true));

		// ➤ Preenche os dados se estiver editando
		if (vacinaMinistrada != null) {
			preencherCampos(vacinaMinistrada);
			ativarEdicao(false);
		}
	}

	private void ativarEdicao(boolean habilitar) {
		textFieldNomeAnimal.setEnabled(habilitar);
		textFieldDataMinistrada.setEnabled(habilitar);
		textFieldValidadeVacina.setEnabled(habilitar);
		textFieldCpfTutor.setEnabled(habilitar);
		comboBoxVacinas.setEnabled(habilitar);
		btnCadastrar.setEnabled(habilitar);
	}
	
	private void preencherCampos(VacinaMinistrada vacinaMinistrada) {
		DAOAnimal daoAnimal = new DAOAnimal();
		DAOTutor daoTutor = new DAOTutor();
		DAOVacina daoVacina = new DAOVacina();
		textFieldNomeAnimal.setText(daoAnimal.buscarPorId(String.valueOf(vacinaMinistrada.getIdAnimal())).getNome());
		textFieldDataMinistrada.setText(vacinaMinistrada.getDataMinistrada());
		textFieldValidadeVacina.setText(vacinaMinistrada.getValidadeVacina());
		textFieldCpfTutor.setText(daoTutor.buscarPorId(String.valueOf(daoAnimal.buscarPorId(String.valueOf(vacinaMinistrada.getIdAnimal())).getIdTutor())).getCpf()); // opcional
		Vacina vacinaSelecionada = daoVacina.buscarPorId(String.valueOf(vacinaMinistrada.getIdVacina()));
		if (vacinaSelecionada != null) {
			comboBoxVacinas.setSelectedItem(vacinaSelecionada.getNome());
		}
	}
	
	private void abrirTelaPrincipal(VacinasMinistradasScreen vacinasMinistradasScreen) {
		vacinasMinistradasScreen.dispose();
		dispose();
		VacinasMinistradasScreen newVacinasMinistradas = new VacinasMinistradasScreen();
		newVacinasMinistradas.setLocationRelativeTo(null);
		newVacinasMinistradas.setVisible(true);
	}
	
}
