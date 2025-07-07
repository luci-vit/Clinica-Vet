package view.TelasCadastro;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DAOTutor;
import dao.DAOVacina;
import model.Tutor;
import model.Vacina;
import view.TelasListagem.AnimaisScreen;
import view.TelasListagem.TutoresScreen;
import view.TelasListagem.VacinasScreen;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroVacina extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldFabricante;
	private JTextField textFieldValidade;
	private JTextField textFieldTempoImunidade;
	private JButton btnCadastrar;
	private JButton btnAlterar;
	private JButton btnExcluir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroVacina frame = new CadastroVacina(null, null);
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
	public CadastroVacina(Vacina vacina, VacinasScreen vacinasScreen) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 461);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(54, 40, 499, 254);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setBounds(42, 21, 70, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Fabricante:");
		lblNewLabel_2.setBounds(42, 76, 163, 15);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Validade (meses):");
		lblNewLabel_3.setBounds(42, 134, 144, 15);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Tempo de Imunidade (meses):");
		lblNewLabel_4.setBounds(255, 134, 232, 15);
		panel.add(lblNewLabel_4);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(42, 37, 320, 30);
		panel.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textFieldFabricante = new JTextField();
		textFieldFabricante.setBounds(42, 92, 182, 30);
		panel.add(textFieldFabricante);
		textFieldFabricante.setColumns(10);
		
		textFieldValidade = new JTextField();
		textFieldValidade.setBounds(42, 150, 182, 30);
		panel.add(textFieldValidade);
		textFieldValidade.setColumns(10);
		
		textFieldTempoImunidade = new JTextField();
		textFieldTempoImunidade.setBounds(255, 150, 232, 30);
		panel.add(textFieldTempoImunidade);
		textFieldTempoImunidade.setColumns(10);
		
		btnCadastrar = new JButton(vacina == null ? "Cadastrar" : "Atualizar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAOVacina daoVacina = new DAOVacina();
				Vacina dadosVacina = new Vacina(null, textFieldFabricante.getText(), textFieldValidade.getText(), textFieldTempoImunidade.getText(),
						textFieldNome.getText());
				if (vacina == null) {
					daoVacina.efetuarCadastro(dadosVacina);
				}else{
					daoVacina.editarDados(dadosVacina, vacina.getId());
				}
				abrirTelaPrincipal(vacinasScreen);
			}
		});
		btnCadastrar.setBounds(42, 192, 117, 25);
		panel.add(btnCadastrar);
		
		JLabel lblNewLabel = new JLabel("Cadastro de vacina");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNewLabel.setBounds(54, 4, 366, 24);
		contentPane.add(lblNewLabel);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ativarEdicao(true);
			}
		});
		btnAlterar.setVisible(false);
		btnAlterar.setBounds(436, 3, 117, 30);
		contentPane.add(btnAlterar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAOVacina daoVacina = new DAOVacina();
				if(vacina!=null) {
					daoVacina.excluir(vacina.getId());
				}
				abrirTelaPrincipal(vacinasScreen);
			}
		});
		btnExcluir.setVisible(false);
		btnExcluir.setBounds(54, 306, 117, 30);
		contentPane.add(btnExcluir);
		
		if (vacina != null) {
			preencherCampos(vacina);
			ativarEdicao(false);
			btnAlterar.setVisible(true);
			btnExcluir.setVisible(true);
		}

	}
	
	private void preencherCampos(Vacina vacina) {
		textFieldNome.setText(vacina.getNome());
		textFieldFabricante.setText(vacina.getFabricante());
		textFieldValidade.setText(vacina.getValidade());
		textFieldTempoImunidade.setText(vacina.getTempoImunidade());
	}
	
	private void abrirTelaPrincipal(VacinasScreen vacinasScreen) {
		vacinasScreen.dispose();
		dispose();
		VacinasScreen newVacinasScreen = new VacinasScreen();
		newVacinasScreen.setLocationRelativeTo(null);
		newVacinasScreen.setVisible(true);
	}
	
	private void ativarEdicao(boolean habilitar) {
		textFieldNome.setEnabled(habilitar);
		textFieldFabricante.setEnabled(habilitar);
		textFieldValidade.setEnabled(habilitar);
		textFieldTempoImunidade.setEnabled(habilitar);
		btnCadastrar.setEnabled(habilitar);
	}

}