package view;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;

public class MainScreenAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * @wbp.nonvisual location=157,437
	 */
	private final JLayeredPane layeredPane = new JLayeredPane();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreenAdmin frame = new MainScreenAdmin();
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
	public MainScreenAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(31, 12, 639, 370);
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
		btnConsultas.setBounds(157, 195, 132, 124);
		panel.add(btnConsultas);
		
		
		ImageIcon iconContribuidores = new ImageIcon("images/pessoas_icon.png");
		Image imageContribuidores = iconContribuidores.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
		iconContribuidores = new ImageIcon(imageContribuidores);
		
		JButton btnContribuidores = new JButton("Funcionarios");
		btnContribuidores.setIcon(iconContribuidores);
		btnContribuidores.setHorizontalTextPosition(SwingConstants.CENTER);
		btnContribuidores.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnContribuidores.setFocusPainted(false);
		btnContribuidores.setBounds(460, 25, 132, 124);
		panel.add(btnContribuidores);
		
		
		ImageIcon iconVacina = new ImageIcon("images/vacina_icon.png");
		Image imageVacina = iconVacina.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
		iconVacina = new ImageIcon(imageVacina);
		
		JButton btnVacinas = new JButton("Vacina");
		btnVacinas.setIcon(iconVacina);
		btnVacinas.setHorizontalTextPosition(SwingConstants.CENTER);
		btnVacinas.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnVacinas.setFocusPainted(false);
		btnVacinas.setBounds(362, 195, 132, 124);
		panel.add(btnVacinas);

	}
}
