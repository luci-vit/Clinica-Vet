package view;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MainScreenRecepcionista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreenRecepcionista frame = new MainScreenRecepcionista();
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
	public MainScreenRecepcionista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(69, 12, 442, 318);
		contentPane.add(panel);
		panel.setLayout(null);
		
		ImageIcon iconAnimal = new ImageIcon("images/animal_icon.png");
		Image imageAnimal = iconAnimal.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
		iconAnimal = new ImageIcon(imageAnimal);
		
		JButton btnAnimais = new JButton("Animais");
		btnAnimais.setIcon(iconAnimal);
		btnAnimais.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAnimais.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAnimais.setFocusPainted(false); // Remove o destaque quando o botão é selecionado
		btnAnimais.setBounds(45, 25, 132, 124);
		panel.add(btnAnimais);
		
		
		ImageIcon iconTutor = new ImageIcon("images/tutor_icon.png");
		Image imageTutor = iconTutor.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
		iconTutor = new ImageIcon(imageTutor);
		
		JButton btnTutores = new JButton("Tutores");
		btnTutores.setIcon(iconTutor);
		btnTutores.setHorizontalTextPosition(SwingConstants.CENTER);
		btnTutores.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnTutores.setFocusPainted(false); // Remove o destaque quando o botão é selecionado
		btnTutores.setBounds(255, 25, 132, 124);
		panel.add(btnTutores);
		
		
		ImageIcon iconConsultas = new ImageIcon("images/consulta_icon.png");
		Image imageConsultas = iconConsultas.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
		iconConsultas = new ImageIcon(imageConsultas);
		
		JButton btnConsultas = new JButton("Consultas");
		btnConsultas.setIcon(iconConsultas);
		btnConsultas.setHorizontalTextPosition(SwingConstants.CENTER);
		btnConsultas.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnConsultas.setFocusPainted(false); // Remove o destaque quando o botão é selecionado
		btnConsultas.setBounds(155, 182, 132, 124);
		panel.add(btnConsultas);
		
	}

}
