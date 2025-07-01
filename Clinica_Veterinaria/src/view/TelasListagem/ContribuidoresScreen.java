package view.TelasListagem;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import dao.DAOFuncionario;
import dao.DAOVeterinario;
import model.Funcionario;
import model.Tutor;
import model.Veterinario;
import model.modelos_tabelas.ModeloTabelaContribuidores;
import view.TelasCadastro.CadastrarTutor;
import view.TelasCadastro.CadastroFuncionario;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ContribuidoresScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private ArrayList<Funcionario> contribuidores = new ArrayList<Funcionario>();
	private JButton btnCadastrar;
	private ContribuidoresScreen contribuidoresScreen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContribuidoresScreen frame = new ContribuidoresScreen();
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
	public ContribuidoresScreen() {
		DAOFuncionario daoContribuidores = new DAOFuncionario();
		DAOVeterinario daoVeterinario = new DAOVeterinario();
		contribuidores = daoContribuidores.listarTodos();
		this.contribuidoresScreen = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 989, 576);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(58, 69, 872, 400);
		contentPane.add(scrollPane);
		
		ModeloTabelaContribuidores modeloTabelaContribuidores = new ModeloTabelaContribuidores(contribuidores);
		
		table = new JTable();
		table.setModel(modeloTabelaContribuidores);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (e.getButton()==1) {
					Funcionario funcionarioSelecionado = null;
					if(modeloTabelaContribuidores.getValueAt(table.getSelectedRow(), 6).toString().toLowerCase().equals("veterinario")) {
						funcionarioSelecionado = daoVeterinario.buscarPorId(modeloTabelaContribuidores.getValueAt(table.getSelectedRow(),0).toString());
					} else {
						funcionarioSelecionado = daoContribuidores.buscarPorId(modeloTabelaContribuidores.getValueAt(table.getSelectedRow(),0).toString());	
					}
					
					CadastroFuncionario cadastroFuncionario = new CadastroFuncionario(funcionarioSelecionado, contribuidoresScreen);
					cadastroFuncionario.setLocationRelativeTo(null);
					cadastroFuncionario.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					cadastroFuncionario.setVisible(true);
				}	
			}
		});
		scrollPane.setViewportView(table);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroFuncionario cadastroFuncionario = new CadastroFuncionario(null, contribuidoresScreen);
				cadastroFuncionario.setLocationRelativeTo(null);
				cadastroFuncionario.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				cadastroFuncionario.setVisible(true);
			}
		});
		btnCadastrar.setBounds(813, 481, 117, 30);
		contentPane.add(btnCadastrar);
		
	}

}
