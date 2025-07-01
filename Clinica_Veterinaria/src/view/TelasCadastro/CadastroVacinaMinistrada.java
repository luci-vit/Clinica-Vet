package view.TelasCadastro;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CadastroVacinaMinistrada extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Nome;
	private JTextField dataMinistrada;
	private JTextField validadeVacina;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroVacinaMinistrada frame = new CadastroVacinaMinistrada();
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
	public CadastroVacinaMinistrada() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(39, 37, 500, 201);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Nome Animal:");
		lblNewLabel_1.setBounds(34, 22, 96, 15);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Data ministrada:");
		lblNewLabel_2.setBounds(34, 81, 135, 15);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Validade da vacina:");
		lblNewLabel_3.setBounds(268, 79, 164, 15);
		panel.add(lblNewLabel_3);

		Nome = new JTextField();
		Nome.setBounds(34, 39, 185, 30);
		panel.add(Nome);
		Nome.setColumns(10);

		dataMinistrada = new JTextField();
		dataMinistrada.setBounds(34, 98, 184, 30);
		panel.add(dataMinistrada);
		dataMinistrada.setColumns(10);

		validadeVacina = new JTextField();
		validadeVacina.setBounds(268, 98, 184, 30);
		panel.add(validadeVacina);
		validadeVacina.setColumns(10);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(267, 37, 185, 30);
		panel.add(textField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nome Vacina:");
		lblNewLabel_1_1.setBounds(267, 20, 96, 15);
		panel.add(lblNewLabel_1_1);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(34, 149, 117, 25);
		panel.add(btnCadastrar);

		JLabel lblNewLabel = new JLabel("Cadastro de vacina ministrada");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(39, 0, 323, 22);
		contentPane.add(lblNewLabel);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(39, 250, 117, 30);
		contentPane.add(btnExcluir);

	}

}