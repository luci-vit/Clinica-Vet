package view.TelasCadastro;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DAOAnimal;
import dao.DAOTutor;
import model.Animal;
import model.Tutor;
import view.TelasListagem.AnimaisScreen;
import view.TelasListagem.TutoresScreen;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroAnimal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Nome;
	private JTextField Especie;
	private JTextField Raca;
	private JTextField Data_Nascimento;
	private JTextField cpfTutor;
	private JButton btnCadastrar;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnEmitirPronturio; 
	private JButton btnCartaoVacina;
	private JButton btnBuscarTutor;
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroAnimal frame = new CadastroAnimal(null, null);
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
	public CadastroAnimal(Animal animal, AnimaisScreen animaisScreen) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 592, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(25, 53, 544, 192);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(12, 12, 70, 15);
		panel.add(lblNome);
		
		JLabel lblEspecie = new JLabel("Espécie:");
		lblEspecie.setBounds(295, 12, 70, 15);
		panel.add(lblEspecie);
		
		JLabel lblRaça = new JLabel("Raça:");
		lblRaça.setBounds(12, 64, 56, 15);
		panel.add(lblRaça);
		
		JLabel lblData_Nascimento = new JLabel("Data de nascimento:");
		lblData_Nascimento.setBounds(295, 64, 157, 15);
		panel.add(lblData_Nascimento);
		
		JLabel lblcpfTutor = new JLabel("CPF Tutor:");
		lblcpfTutor.setBounds(12, 116, 98, 15);
		panel.add(lblcpfTutor);
		
		Nome = new JTextField();
		Nome.setBounds(12, 28, 250, 30);
		panel.add(Nome);
		Nome.setColumns(10);
		
		Especie = new JTextField();
		Especie.setBounds(295, 28, 197, 30);
		panel.add(Especie);
		Especie.setColumns(10);
		
		Raca = new JTextField();
		Raca.setBounds(12, 80, 250, 30);
		panel.add(Raca);
		Raca.setColumns(10);
		
		Data_Nascimento = new JTextField();
		Data_Nascimento.setBounds(295, 80, 197, 30);
		panel.add(Data_Nascimento);
		Data_Nascimento.setColumns(10);
		
		cpfTutor = new JTextField();
		cpfTutor.setBounds(12, 131, 145, 30);
		panel.add(cpfTutor);
		cpfTutor.setColumns(10);
		
		btnCadastrar = new JButton(animal == null ? "Cadastrar" : "Atualizar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAOAnimal daoAnimal = new DAOAnimal();
				Animal dadosAnimal = new Animal(null, Nome.getText(), Especie.getText(), Raca.getText(),
						Data_Nascimento.getText(), daoAnimal.buscarTutorResponsavel(cpfTutor.getText()));
				if (animal == null) {
					daoAnimal.efetuarCadastro(dadosAnimal);
				}else{
					daoAnimal.editarDados(dadosAnimal, animal.getId());
				}
				abrirTelaPrincipal(animaisScreen);
			}
		});
		btnCadastrar.setBounds(169, 133, 117, 25);
		panel.add(btnCadastrar);
		
		JLabel lblNewLabel = new JLabel("Cadastro de animal");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel.setBounds(25, 12, 292, 29);
		contentPane.add(lblNewLabel);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ativarEdicao(true);
			}
		});
		btnAlterar.setVisible(false);
		btnAlterar.setBounds(452, 16, 117, 25);
		contentPane.add(btnAlterar);
		
		btnEmitirPronturio = new JButton("Emitir Prontuário");
		btnEmitirPronturio.setVisible(false);
		btnEmitirPronturio.setBounds(25, 257, 165, 25);
		contentPane.add(btnEmitirPronturio);
		
		btnCartaoVacina = new JButton("Cartão de Vacina");
		btnCartaoVacina.setVisible(false);
		btnCartaoVacina.setBounds(213, 257, 165, 25);
		contentPane.add(btnCartaoVacina);
		
		btnBuscarTutor = new JButton("Buscar Tutor");
		btnBuscarTutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAOTutor daoTutor = new DAOTutor();
				CadastrarTutor cadastrarTutor = new CadastrarTutor(daoTutor.buscarPorId(String.valueOf(animal.getIdTutor())), animaisScreen);
				cadastrarTutor.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				cadastrarTutor.setLocationRelativeTo(null);
				cadastrarTutor.setVisible(true);
			}
		});
		btnBuscarTutor.setBounds(404, 257, 165, 25);
		btnBuscarTutor.setVisible(false);
		contentPane.add(btnBuscarTutor);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAOAnimal daoAnimal = new DAOAnimal();
				if (animal!=null) {
					daoAnimal.excluir(animal.getId());
					abrirTelaPrincipal(animaisScreen);
				}
			}
		});
		btnExcluir.setVisible(false);
		btnExcluir.setBounds(25, 296, 117, 25);
		contentPane.add(btnExcluir);
		
		if (animal != null) {
			preencherCampos(animal);
			ativarEdicao(false);
			btnAlterar.setVisible(true);
			btnExcluir.setVisible(true);
			btnBuscarTutor.setVisible(true);
			btnEmitirPronturio.setVisible(true);
			btnCartaoVacina.setVisible(true);
			btnBuscarTutor.setVisible(true);
		}
	}
	
	private void preencherCampos(Animal animal) {
		DAOTutor daoTutor = new DAOTutor();
		Nome.setText(animal.getNome());
		Especie.setText(animal.getEspecie());
		Raca.setText(animal.getRaca());
		Data_Nascimento.setText(animal.getDataNascimento());
		cpfTutor.setText(daoTutor.buscarPorId(String.valueOf(animal.getIdTutor())).getCpf());
	}
	
	private void abrirTelaPrincipal(AnimaisScreen animaisScreen) {
		animaisScreen.dispose();
		dispose();
		AnimaisScreen newAnimaisScreen = new AnimaisScreen();
		newAnimaisScreen.setLocationRelativeTo(null);
		newAnimaisScreen.setVisible(true);
	}
	
	private void ativarEdicao(boolean habilitar) {
		Nome.setEnabled(habilitar);
		Especie.setEnabled(habilitar);
		Raca.setEnabled(habilitar);
		Data_Nascimento.setEnabled(habilitar);
		cpfTutor.setEnabled(habilitar);
		btnCadastrar.setEnabled(habilitar);
	}
}
