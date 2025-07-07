package view.TelasListagem;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.DAOAnimal;
import model.Animal;
import model.Tutor;
import model.modelos_tabelas.ModeloTabelaAnimais;
import view.TelasCadastro.CadastrarTutor;
import view.TelasCadastro.CadastroAnimal;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class AnimaisScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private ArrayList<Animal> animais = new ArrayList<Animal>();
	private JButton btnCadastrar;
	private AnimaisScreen animaisScreen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnimaisScreen frame = new AnimaisScreen();
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
	public AnimaisScreen() {
		DAOAnimal daoAnimal = new DAOAnimal();
		animais = daoAnimal.listarTodos();
		this.animaisScreen = this;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 870, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(90, 83, 691, 310);
		contentPane.add(scrollPane);
		
		ModeloTabelaAnimais modeloTabelaAnimais = new ModeloTabelaAnimais(animais);
		
		table = new JTable();
		table.setModel(modeloTabelaAnimais);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton()==1) {
					Animal animalSelecionado = daoAnimal.buscarPorId(modeloTabelaAnimais.getValueAt(table.getSelectedRow(),0).toString());
					CadastroAnimal cadastroAnimal = new CadastroAnimal(animalSelecionado, animaisScreen);
					cadastroAnimal.setLocationRelativeTo(null);
					cadastroAnimal.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					cadastroAnimal.setVisible(true);
				}	
			}
		});		
		scrollPane.setViewportView(table);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroAnimal cadastrarAnimal = new CadastroAnimal(null, animaisScreen);
				cadastrarAnimal.setLocationRelativeTo(null);
				cadastrarAnimal.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				cadastrarAnimal.setVisible(true);			}
		});
		btnCadastrar.setBounds(664, 405, 117, 30);
		contentPane.add(btnCadastrar);
		

	}
}
