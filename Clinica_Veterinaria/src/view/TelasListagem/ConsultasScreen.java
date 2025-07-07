package view.TelasListagem;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import dao.DAOConsulta;
import model.Animal;
import model.Consulta;
import model.Funcionario;
import model.Veterinario;
import model.modelos_tabelas.ModeloTabelaConsultas;
import view.TelasCadastro.CadastroAnimal;
import view.TelasCadastro.CadastroConsulta;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultasScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private ArrayList<Consulta> consultas = new ArrayList<Consulta>();
	private ConsultasScreen consultasScreen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultasScreen frame = new ConsultasScreen(null);
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
	public ConsultasScreen(Funcionario funcionario) {
		DAOConsulta daoConsulta = new DAOConsulta();
		
		if (funcionario instanceof Veterinario) {
			Veterinario vet = (Veterinario) funcionario;
			consultas = daoConsulta.listarConsultasMedico(vet.getId());
		}else if (funcionario.getFuncao().toLowerCase().equals("administrador")) {
			consultas = daoConsulta.listarConsultasFinalizadas();
		} else {
			consultas = daoConsulta.listarTodos();
		}
	
		consultasScreen = this;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton()==1) {
					Consulta consultaSelecionada = daoConsulta.buscarPorId(modeloTabelaConsultas.getValueAt(table.getSelectedRow(),0).toString());
					CadastroConsulta cadastroConsulta;
					try {
						cadastroConsulta = new CadastroConsulta(consultaSelecionada, consultasScreen, funcionario);
						cadastroConsulta.setLocationRelativeTo(null);
						cadastroConsulta.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						cadastroConsulta.setVisible(true);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				
				}	
			}
		});	
		scrollPane.setViewportView(table);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroConsulta cadastrarConsulta;
				try {
					cadastrarConsulta = new CadastroConsulta(null, consultasScreen, funcionario);
					cadastrarConsulta.setLocationRelativeTo(null);
					cadastrarConsulta.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					cadastrarConsulta.setVisible(true);		
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
	
			}
		});
		btnCadastrar.setBounds(793, 499, 117, 30);
		contentPane.add(btnCadastrar);

	}
}
