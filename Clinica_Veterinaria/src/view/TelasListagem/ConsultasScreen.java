package view.TelasListagem;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import dao.DAOConsulta;
import model.Consulta;
import model.modelos_tabelas.ModeloTabelaConsultas;

public class ConsultasScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private ArrayList<Consulta> consultas = new ArrayList<Consulta>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultasScreen frame = new ConsultasScreen();
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
	public ConsultasScreen() {
		DAOConsulta daoConsulta = new DAOConsulta();
		consultas = daoConsulta.listarTodos();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 949, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 75, 872, 412);
		contentPane.add(scrollPane);
		
		ModeloTabelaConsultas modeloTabelaConsultas = new ModeloTabelaConsultas(consultas);
		
		table = new JTable();
		table.setModel(modeloTabelaConsultas);
		scrollPane.setViewportView(table);

	}

}
