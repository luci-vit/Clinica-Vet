package view.TelasListagem;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.DAOVacina;
import dao.DAOVacinaMinistrada;
import model.Vacina;
import model.VacinaMinistrada;
import model.modelos_tabelas.ModeloTabelaVacinasMinistradas;
import view.TelasCadastro.CadastroVacina;
import view.TelasCadastro.CadastroVacinaMinistrada;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VacinasMinistradasScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private ArrayList<VacinaMinistrada> vacinasMinistradas = new ArrayList<VacinaMinistrada>();
	private VacinasMinistradasScreen vacinasMinistradasScreen;
	private JPanel contentPane;
	private JTable table;
	private JButton btnNewButton;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VacinasMinistradasScreen frame = new VacinasMinistradasScreen();
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
	public VacinasMinistradasScreen() {
		
		DAOVacinaMinistrada daoVacinaMinistrada = new DAOVacinaMinistrada();
		vacinasMinistradas = daoVacinaMinistrada.listarTodos();
		vacinasMinistradasScreen = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 755, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(90, 81, 575, 350);
		contentPane.add(scrollPane);
		
		ModeloTabelaVacinasMinistradas modeloTabelaVacinasMinistradas = new ModeloTabelaVacinasMinistradas(vacinasMinistradas);
		
		table = new JTable();
		table.setModel(modeloTabelaVacinasMinistradas);
		
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setWidth(0);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton()==1) {
					VacinaMinistrada vacinaMinistradaSelecionada = daoVacinaMinistrada.buscarPorId(modeloTabelaVacinasMinistradas.getValueAt(table.getSelectedRow(),0).toString());
					CadastroVacinaMinistrada cadastrarVacinaMinistrada = new CadastroVacinaMinistrada(vacinaMinistradaSelecionada, vacinasMinistradasScreen);
					cadastrarVacinaMinistrada.setLocationRelativeTo(null);
					cadastrarVacinaMinistrada.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					cadastrarVacinaMinistrada.setVisible(true);
				}	
			}
		}); 
		scrollPane.setViewportView(table);
	
		btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					CadastroVacinaMinistrada cadastrarVacinaMinistrada = new CadastroVacinaMinistrada(null, vacinasMinistradasScreen);
					cadastrarVacinaMinistrada.setLocationRelativeTo(null);
					cadastrarVacinaMinistrada.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					cadastrarVacinaMinistrada.setVisible(true);
			}
		});
		btnNewButton.setBounds(548, 443, 117, 30);
		contentPane.add(btnNewButton);

	}
}
