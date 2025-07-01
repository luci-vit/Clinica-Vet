package view.TelasListagem;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.DAOTutor;
import model.Tutor;
import model.modelos_tabelas.ModeloTabelaTutores;
import view.TelasCadastro.CadastrarTutor;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class TutoresScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private ArrayList<Tutor> tutores = new ArrayList<Tutor>();
	private JTextField textField;
	private JLabel lblBuscarTutor;
	private JButton btnNewButton;
	private TutoresScreen tutoresScreen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TutoresScreen frame = new TutoresScreen();
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
	public TutoresScreen() {
		DAOTutor daoTutor = new DAOTutor();
		tutores = daoTutor.listarTodos();
		this.tutoresScreen = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 895, 585);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(97, 81, 700, 386);
		contentPane.add(scrollPane);
		
		ModeloTabelaTutores modeloTabelaTutores = new ModeloTabelaTutores(tutores);
		
		table = new JTable();
		table.setModel(modeloTabelaTutores);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton()==1) {
					Tutor tutorSelecionado = daoTutor.buscarPorId(modeloTabelaTutores.getValueAt(table.getSelectedRow(),0).toString());
					CadastrarTutor cadastrarTutor = new CadastrarTutor(tutorSelecionado, tutoresScreen);
					cadastrarTutor.setLocationRelativeTo(null);
					cadastrarTutor.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					cadastrarTutor.setVisible(true);
				}	
			}
		});
		scrollPane.setViewportView(table);
		
		textField = new JTextField();
		textField.setBounds(97, 30, 700, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblBuscarTutor = new JLabel("Buscar Tutor:");
		lblBuscarTutor.setBounds(97, 12, 122, 15);
		contentPane.add(lblBuscarTutor);
		
		btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarTutor cadastrarTutor = new CadastrarTutor(null, tutoresScreen);
				cadastrarTutor.setLocationRelativeTo(null);
				cadastrarTutor.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				cadastrarTutor.setVisible(true);
			}
		});
		btnNewButton.setBounds(680, 480, 117, 30);
		contentPane.add(btnNewButton);
		

	}

}
