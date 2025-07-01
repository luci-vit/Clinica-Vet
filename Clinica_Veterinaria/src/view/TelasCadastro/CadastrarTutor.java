package view.TelasCadastro;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DAOTutor;
import model.Tutor;
import view.TelasListagem.AnimaisScreen;
import view.TelasListagem.TutoresScreen;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastrarTutor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldCpf;
	private JTextField textFieldEmail;
	private JTextField textFieldTelefone;
	private JTextField textFieldEndereco;
	private JButton btnCadastrar;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnAnimaisVinculados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarTutor frame = new CadastrarTutor(null, null);
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
	public CadastrarTutor(Tutor tutor, JFrame someScreen) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCadastroDeTutores = new JLabel("Cadastro de Tutores");
		lblCadastroDeTutores.setBounds(60, 21, 305, 29);
		lblCadastroDeTutores.setFont(new Font("Dialog", Font.BOLD, 24));
		contentPane.add(lblCadastroDeTutores);

		JPanel panel = new JPanel();
		panel.setBounds(60, 62, 660, 271);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(31, 25, 45, 15);
		panel.add(lblNome);

		JLabel lblEmail_1 = new JLabel("Email:");
		lblEmail_1.setBounds(31, 83, 42, 15);
		panel.add(lblEmail_1);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(403, 26, 31, 15);
		panel.add(lblCpf);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(387, 83, 67, 15);
		panel.add(lblTelefone);

		JLabel lblEndereco = new JLabel("Endereço:");
		lblEndereco.setBounds(31, 140, 71, 15);
		panel.add(lblEndereco);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(31, 41, 323, 30);
		panel.add(textFieldNome);
		textFieldNome.setColumns(10);

		textFieldCpf = new JTextField();
		textFieldCpf.setColumns(10);
		textFieldCpf.setBounds(403, 41, 216, 30);
		panel.add(textFieldCpf);

		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(31, 98, 323, 30);
		panel.add(textFieldEmail);

		textFieldTelefone = new JTextField();
		textFieldTelefone.setColumns(10);
		textFieldTelefone.setBounds(387, 98, 232, 30);
		panel.add(textFieldTelefone);

		textFieldEndereco = new JTextField();
		textFieldEndereco.setColumns(10);
		textFieldEndereco.setBounds(31, 158, 588, 30);
		panel.add(textFieldEndereco);

		btnCadastrar = new JButton(tutor == null ? "Cadastrar" : "Atualizar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAOTutor daoTutor = new DAOTutor();
				Tutor dadosTutor = new Tutor(null, textFieldNome.getText(), textFieldCpf.getText(), textFieldEmail.getText(),
						textFieldTelefone.getText(), textFieldEndereco.getText());
				if (tutor == null) {
					daoTutor.efetuarCadastro(dadosTutor);
				}else{
					daoTutor.editarDados(dadosTutor, tutor.getId());
				}
				abrirTelaPrincipal(someScreen);
			}
		});
		btnCadastrar.setBounds(31, 214, 117, 24);
		panel.add(btnCadastrar);

		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ativarEdicao(true);
			}
		});
		btnAlterar.setVisible(false);
		btnAlterar.setBounds(603, 24, 117, 30);
		contentPane.add(btnAlterar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAOTutor daoTutor = new DAOTutor();
				if (tutor!=null) {
					daoTutor.excluir(tutor.getId());
					abrirTelaPrincipal(someScreen);
				}else {
					JOptionPane.showMessageDialog(btnExcluir, "Não é possível excluir um tutor que não foi cadastrado");
				}
			}
		});
		btnExcluir.setVisible(false);
		btnExcluir.setBounds(60, 345, 117, 30);
		contentPane.add(btnExcluir);

		btnAnimaisVinculados = new JButton("Animais Vinculados");
		btnAnimaisVinculados.setBounds(542, 345, 178, 30);
		contentPane.add(btnAnimaisVinculados);
		
		if (tutor != null) {
			preencherCampos(tutor);
			ativarEdicao(false);
			btnAlterar.setVisible(true);
			btnExcluir.setVisible(true);
		}

	}
	
	private void preencherCampos(Tutor tutor) {
		textFieldNome.setText(tutor.getNome());
		textFieldCpf.setText(tutor.getCpf());
		textFieldEmail.setText(tutor.getEmail());
		textFieldTelefone.setText(tutor.getTelefone());
		textFieldEndereco.setText(tutor.getEndereco());
	}
	
	private void abrirTelaPrincipal(JFrame someFrame) {
		someFrame.dispose();
		dispose();
		JFrame newSomeFrame = null;
		if (someFrame instanceof TutoresScreen) {
			newSomeFrame = new TutoresScreen();
		} else {
			newSomeFrame = new AnimaisScreen();
		}
		newSomeFrame.setLocationRelativeTo(null);
		newSomeFrame.setVisible(true);
	}
	
	private void ativarEdicao(boolean habilitar) {
		textFieldNome.setEnabled(habilitar);
		textFieldCpf.setEnabled(habilitar);
		textFieldEmail.setEnabled(habilitar);
		textFieldTelefone.setEnabled(habilitar);
		textFieldEndereco.setEnabled(habilitar);
		
		btnCadastrar.setEnabled(habilitar);
	}
}


