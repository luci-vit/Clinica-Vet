package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DAOUsuario;
import model.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JTextField textFieldSenha;
	private DAOUsuario usuario = new DAOUsuario();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setLocationRelativeTo(null);
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(121, 23, 262, 245);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bem Vindo");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(83, 12, 96, 28);
		panel.add(lblNewLabel);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(36, 65, 70, 15);
		panel.add(lblUsuario);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(37, 82, 187, 19);
		panel.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		textFieldSenha = new JTextField();
		textFieldSenha.setColumns(10);
		textFieldSenha.setBounds(37, 142, 187, 19);
		panel.add(textFieldSenha);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(36, 125, 70, 15);
		panel.add(lblSenha);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean userTextFieldIsEmpty = textFieldUsuario.getText().equals(null) || textFieldUsuario.getText().equals("");
				boolean passwordTextFieldIsEmpty = textFieldSenha.getText().equals(null) || textFieldSenha.getText().equals("");
			
				if (userTextFieldIsEmpty || passwordTextFieldIsEmpty) {
					
					JOptionPane.showMessageDialog(btnLogin, "Erro! Um dos campos não foi preenchido corretamente. Tente novamente!", "", JOptionPane.WARNING_MESSAGE);
				
				} else {
				
					Usuario usuarioEncontrado = usuario.ConsultarUsuario(textFieldUsuario.getText(), textFieldSenha.getText());
					
					JOptionPane.showMessageDialog(contentPane, "Informações válidas!");
					dispose();
					abrirTelaCorrespondente(usuarioEncontrado);
				}
			}
		});
		btnLogin.setBounds(72, 194, 117, 25);
		panel.add(btnLogin);

	}
	
	private void abrirTelaCorrespondente(Usuario usuarioEncontrado) {
		
		if(usuarioEncontrado.getFuncao().toLowerCase().equals("administrador")) {
			MainScreenAdmin mainScreenAdmin = new MainScreenAdmin();
			mainScreenAdmin.setLocationRelativeTo(null);
			mainScreenAdmin.setVisible(true);
		}
		
	}
}
