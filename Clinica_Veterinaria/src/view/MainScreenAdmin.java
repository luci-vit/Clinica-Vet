package view;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DAOFuncionario;
import model.Funcionario;
import view.TelasListagem.AnimaisScreen;
import view.TelasListagem.ConsultasScreen;
import view.TelasListagem.ContribuidoresScreen;
import view.TelasListagem.TutoresScreen;
import view.TelasListagem.VacinasScreen;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
					MainScreenAdmin frame = new MainScreenAdmin(0);
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
	public MainScreenAdmin(int idAdmin) {
		
		DAOFuncionario daoFuncionario = new DAOFuncionario();
		Funcionario administrador = daoFuncionario.buscarPorId(String.valueOf(idAdmin));
		
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
		btnAnimais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnimaisScreen animaisScreen = new AnimaisScreen();
				animaisScreen.setLocationRelativeTo(null);
				animaisScreen.setVisible(true);
			}
		});
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
		btnTutores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TutoresScreen tutoresScreen = new TutoresScreen();
				tutoresScreen.setLocationRelativeTo(null);
				tutoresScreen.setVisible(true);
			}
		});
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
		btnConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultasScreen consultasScreen = new ConsultasScreen(administrador);
				consultasScreen.setLocationRelativeTo(null);
				consultasScreen.setVisible(true);
			}
		});
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
		btnContribuidores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContribuidoresScreen contribuidoresScreen = new ContribuidoresScreen();
				contribuidoresScreen.setLocationRelativeTo(null);
				contribuidoresScreen.setVisible(true);
			}
		});
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
		btnVacinas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VacinasScreen vacinasScreen = new VacinasScreen();
				vacinasScreen.setLocationRelativeTo(null);
				vacinasScreen.setVisible(true);
			}
		});
		btnVacinas.setIcon(iconVacina);
		btnVacinas.setHorizontalTextPosition(SwingConstants.CENTER);
		btnVacinas.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnVacinas.setFocusPainted(false);
		btnVacinas.setBounds(362, 195, 132, 124);
		panel.add(btnVacinas);

	}
}
