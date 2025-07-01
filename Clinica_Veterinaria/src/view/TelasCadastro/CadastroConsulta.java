package view.TelasCadastro;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class CadastroConsulta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldHorario;
	private JTextField textFieldNomeDoAnimal;
	private JTextField textFieldEspecialidade;
	private JTextField textFieldData;
	private JTextField textFieldVeterinario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroConsulta frame = new CadastroConsulta();
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
	public CadastroConsulta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 670);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(50, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(50, 52, 795, 407);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Horário:");
		lblNewLabel.setBounds(39, 12, 70, 24);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome do animal:");
		lblNewLabel_1.setBounds(39, 66, 143, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Especialidade:");
		lblNewLabel_2.setBounds(347, 65, 103, 15);
		panel.add(lblNewLabel_2);
		
		JLabel Veterinário = new JLabel("Veterinário:");
		Veterinário.setBounds(39, 118, 103, 15);
		panel.add(Veterinário);
		
		JLabel lblNewLabel_4 = new JLabel("Problema:");
		lblNewLabel_4.setBounds(39, 169, 114, 15);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Diagnóstico:");
		lblNewLabel_5.setBounds(39, 245, 114, 15);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Medicamentos:");
		lblNewLabel_6.setBounds(39, 315, 143, 24);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_8 = new JLabel("Data:");
		lblNewLabel_8.setBounds(347, 18, 70, 15);
		panel.add(lblNewLabel_8);
		
		textFieldHorario = new JTextField();
		textFieldHorario.setBounds(39, 34, 199, 24);
		panel.add(textFieldHorario);
		textFieldHorario.setColumns(10);
		
		textFieldNomeDoAnimal = new JTextField();
		textFieldNomeDoAnimal.setBounds(39, 82, 199, 24);
		panel.add(textFieldNomeDoAnimal);
		textFieldNomeDoAnimal.setColumns(10);
		
		textFieldEspecialidade = new JTextField();
		textFieldEspecialidade.setBounds(347, 82, 225, 24);
		panel.add(textFieldEspecialidade);
		textFieldEspecialidade.setColumns(10);
		
		textFieldData = new JTextField();
		textFieldData.setBounds(347, 34, 225, 24);
		panel.add(textFieldData);
		textFieldData.setColumns(10);
		
		textFieldVeterinario = new JTextField();
		textFieldVeterinario.setBounds(39, 133, 199, 24);
		panel.add(textFieldVeterinario);
		textFieldVeterinario.setColumns(10);
		
		JTextArea textAreaProblema = new JTextArea();
		textAreaProblema.setBounds(39, 185, 670, 48);
		panel.add(textAreaProblema);
		
		JTextArea textAreaDiagnostico = new JTextArea();
		textAreaDiagnostico.setBounds(39, 262, 670, 48);
		panel.add(textAreaDiagnostico);
		
		JTextArea textAreaMedicamentos = new JTextArea();
		textAreaMedicamentos.setBounds(39, 338, 670, 48);
		panel.add(textAreaMedicamentos);
		
		JLabel lblNewLabel_7 = new JLabel("Cadastro de consulta");
		lblNewLabel_7.setBounds(50, 0, 308, 40);
		contentPane.add(lblNewLabel_7);
		lblNewLabel_7.setFont(new Font("Dialog", Font.BOLD, 24));
		
		JButton btnEmitirBoleto = new JButton("Emitir Boleto");
		btnEmitirBoleto.setBounds(50, 522, 143, 40);
		contentPane.add(btnEmitirBoleto);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(50, 471, 143, 39);
		contentPane.add(btnCadastrar);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setBounds(702, 471, 143, 39);
		contentPane.add(btnFinalizar);
		

	}
}