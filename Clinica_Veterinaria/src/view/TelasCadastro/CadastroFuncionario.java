package view.TelasCadastro;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dao.DAO;
import dao.DAOFuncionario;
import dao.DAOTutor;
import dao.DAOUsuario;
import dao.DAOVeterinario;
import model.Animal;
import model.Funcionario;
import model.Usuario;
import model.Veterinario;
import view.TelasListagem.AnimaisScreen;
import view.TelasListagem.ContribuidoresScreen;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroFuncionario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldCpf;
	private JTextField textFieldEmail;
	private JTextField textFieldTurnoDeTrabalho;
	private JTextField textFieldTelefone;
	private JTextField textFieldFuncao;
	private JTextField textFieldCFMV;
	private JTextField textFieldEspecialidade;
	private JLabel lblCFMV;
	private JLabel lblEspecialidade;
	private JButton btnAlterar;
	private JButton btnExcluir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroFuncionario frame = new CadastroFuncionario(null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastroFuncionario(Funcionario funcionario, ContribuidoresScreen contribuidoresScreen) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 845, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(53, 57, 715, 313);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(36, 16, 70, 15);
		panel.add(lblNome);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(408, 14, 70, 15);
		panel.add(lblCpf);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(36, 73, 70, 15);
		panel.add(lblEmail);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(260, 140, 70, 15);
		panel.add(lblTelefone);

		JLabel lblTurnoTrabalho = new JLabel("Turno de trabalho:");
		lblTurnoTrabalho.setBounds(36, 140, 184, 15);
		panel.add(lblTurnoTrabalho);

		JLabel lblFuncao = new JLabel("Função:");
		lblFuncao.setBounds(484, 140, 70, 15);
		panel.add(lblFuncao);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(36, 31, 325, 30);
		panel.add(textFieldNome);
		textFieldNome.setColumns(10);

		textFieldCpf = new JTextField();
		textFieldCpf.setBounds(408, 29, 161, 30);
		panel.add(textFieldCpf);
		textFieldCpf.setColumns(10);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(36, 88, 374, 30);
		panel.add(textFieldEmail);
		textFieldEmail.setColumns(10);

		textFieldTurnoDeTrabalho = new JTextField();
		textFieldTurnoDeTrabalho.setBounds(36, 157, 197, 30);
		panel.add(textFieldTurnoDeTrabalho);
		textFieldTurnoDeTrabalho.setColumns(10);

		textFieldTelefone = new JTextField();
		textFieldTelefone.setBounds(260, 157, 197, 30);
		panel.add(textFieldTelefone);
		textFieldTelefone.setColumns(10);

		textFieldFuncao = new JTextField();
		textFieldFuncao.setBounds(484, 157, 184, 30);
		textFieldFuncao.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				verificaVeterinario();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				verificaVeterinario();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				verificaVeterinario();
			}
		});
		panel.add(textFieldFuncao);
		textFieldFuncao.setColumns(10);

		lblCFMV = new JLabel("CFMV:");
		lblCFMV.setBounds(36, 209, 70, 15);
		lblCFMV.setEnabled(false);
		panel.add(lblCFMV);

		textFieldCFMV = new JTextField();
		textFieldCFMV.setBounds(36, 226, 184, 30);
		panel.add(textFieldCFMV);
		textFieldCFMV.setEnabled(false);
		textFieldCFMV.setColumns(10);

		lblEspecialidade = new JLabel("Especialidade:");
		lblEspecialidade.setBounds(260, 209, 118, 15);
		lblEspecialidade.setEnabled(false);
		panel.add(lblEspecialidade);

		textFieldEspecialidade = new JTextField();
		textFieldEspecialidade.setBounds(260, 226, 199, 30);
		panel.add(textFieldEspecialidade);
		textFieldEspecialidade.setEnabled(false);
		textFieldEspecialidade.setColumns(10);

		JButton btnCadastrar = new JButton(funcionario == null ? "Cadastrar" : "Atualizar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean isAVeterinarian = textFieldFuncao.getText().trim().toLowerCase().equals("veterinario");
				boolean isAnAdm = textFieldFuncao.getText().trim().toLowerCase().equals("administrador");
				boolean isARecepcionist = textFieldFuncao.getText().trim().toLowerCase().equals("recepcionista");

				String user = Usuario.gerarUsuario(textFieldFuncao.getText(), textFieldNome.getText(),
						textFieldCpf.getText());
				String senha = Usuario.gerarSenha(textFieldNome.getText(), textFieldCpf.getText());
				Usuario usuario = new Usuario(user, senha);

				if (isAVeterinarian) {

					DAOVeterinario daoVeterinario = new DAOVeterinario();

					Veterinario dadosVeterinario = new Veterinario(null, textFieldNome.getText(),
							textFieldCpf.getText(), textFieldEmail.getText(), textFieldTelefone.getText(),
							textFieldTurnoDeTrabalho.getText(), textFieldFuncao.getText(), textFieldCFMV.getText(),
							textFieldEspecialidade.getText());

					if (funcionario == null) {
						daoVeterinario.efetuarCadastroComUsuario(dadosVeterinario, usuario);
					} else {
						daoVeterinario.editarDados(dadosVeterinario, funcionario.getId());
					}
				} else {

					DAOFuncionario daoFuncionario = new DAOFuncionario();

					Funcionario dadosFuncionario = new Funcionario(null, textFieldNome.getText(),
							textFieldCpf.getText(), textFieldEmail.getText(), textFieldTelefone.getText(),
							textFieldTurnoDeTrabalho.getText(), textFieldFuncao.getText());

					if (funcionario == null) {

						if (isAnAdm || isARecepcionist) {
							daoFuncionario.efetuarCadastroComUsuario(dadosFuncionario, usuario);
						} else {
							daoFuncionario.efetuarCadastro(dadosFuncionario);
						}

					} else {

						daoFuncionario.editarDados(dadosFuncionario, funcionario.getId());

					}
				}
				abrirTelaPrincipal(contribuidoresScreen);
			}
		});
		btnCadastrar.setBounds(471, 228, 117, 25);
		panel.add(btnCadastrar);

		JLabel lblNewLabel = new JLabel("Cadastro de Funcionário");
		lblNewLabel.setBounds(53, 12, 347, 33);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 24));

		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ativarEdicao(true);
			}
		});
		btnAlterar.setVisible(false);
		btnAlterar.setBounds(651, 20, 117, 30);
		contentPane.add(btnAlterar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAOFuncionario daoContribuidores = new DAOFuncionario();
				if (funcionario != null) {
					daoContribuidores.excluir(funcionario.getId());
					abrirTelaPrincipal(null);
				} else {
					JOptionPane.showMessageDialog(btnExcluir,
							"Não é possível excluir um contribuidor que não foi cadastrado");
				}
			}
		});
		btnExcluir.setVisible(false);
		btnExcluir.setBounds(53, 382, 117, 30);
		contentPane.add(btnExcluir);

		if (funcionario != null) {
			btnAlterar.setVisible(true);
			btnExcluir.setVisible(true);
			preencherCampos(funcionario);
			ativarEdicao(false);
		}
	}

	private void verificaVeterinario() {
		String textoDigitado = textFieldFuncao.getText().trim().toLowerCase();
		if (textoDigitado.equals("veterinario")) {
			lblCFMV.setEnabled(true);
			textFieldCFMV.setEnabled(true);
			lblEspecialidade.setEnabled(true);
			textFieldEspecialidade.setEnabled(true);
		} else {
			lblCFMV.setEnabled(false);
			textFieldCFMV.setEnabled(false);
			lblEspecialidade.setEnabled(false);
			textFieldEspecialidade.setEnabled(false);
		}
	}

	private void preencherCampos(Funcionario funcionario) {
		textFieldNome.setText(funcionario.getNome());
		textFieldCpf.setText(funcionario.getCpf());
		textFieldEmail.setText(funcionario.getEmail());
		textFieldTelefone.setText(funcionario.getTelefone());
		textFieldFuncao.setText(funcionario.getFuncao());
		textFieldTurnoDeTrabalho.setText(funcionario.getTurnoTrabalho());

		if (funcionario instanceof Veterinario) {
			Veterinario vet = (Veterinario) funcionario;
			textFieldCFMV.setText(vet.getCfmv());
			textFieldEspecialidade.setText(vet.getEspecialidade());

		}
	}

	private void abrirTelaPrincipal(ContribuidoresScreen contribuidoresScreen) {
		contribuidoresScreen.dispose();
		dispose();
		ContribuidoresScreen newContribuidoresScreen = new ContribuidoresScreen();
		newContribuidoresScreen.setLocationRelativeTo(null);
		newContribuidoresScreen.setVisible(true);
	}

	private void ativarEdicao(boolean habilitar) {
		textFieldNome.setEnabled(habilitar);
		textFieldCpf.setEnabled(habilitar);
		textFieldEmail.setEnabled(habilitar);
		textFieldTelefone.setEnabled(habilitar);
		textFieldFuncao.setEnabled(habilitar);
		textFieldTurnoDeTrabalho.setEnabled(habilitar);

		if (textFieldFuncao.getText().trim().toLowerCase().equals("veterinario")) {
			textFieldCFMV.setEnabled(habilitar);
			textFieldEspecialidade.setEnabled(habilitar);
		}
	}

}
