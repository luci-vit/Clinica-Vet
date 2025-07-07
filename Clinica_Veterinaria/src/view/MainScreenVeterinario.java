package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DAOVeterinario;
import model.Veterinario;
import view.TelasListagem.ConsultasScreen;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainScreenVeterinario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreenVeterinario frame = new MainScreenVeterinario(0);
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
	public MainScreenVeterinario(int idVeterinario) {
		
		DAOVeterinario daoVeterinario = new DAOVeterinario();
		Veterinario veterinario = daoVeterinario.buscarPorId(String.valueOf(idVeterinario));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnConsultas = new JButton("Consultas");
		btnConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultasScreen consultasScreen = new ConsultasScreen(veterinario);
				consultasScreen.setLocationRelativeTo(null);
				consultasScreen.setVisible(true);
			}
		});
		
		btnConsultas.setBounds(162, 109, 235, 39);
		btnConsultas.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnConsultas.setHorizontalTextPosition(SwingConstants.CENTER);
		btnConsultas.setFocusPainted(false);
		contentPane.add(btnConsultas);
		
		JButton btnMinistrarVacina = new JButton("Ministrar Vacina");
		btnMinistrarVacina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnMinistrarVacina.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnMinistrarVacina.setHorizontalTextPosition(SwingConstants.CENTER);
		btnMinistrarVacina.setFocusPainted(false);
		btnMinistrarVacina.setBounds(162, 167, 235, 39);
		contentPane.add(btnMinistrarVacina);

	}

}
