package view.TelasListagem;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import dao.DAOVacina;
import model.Tutor;
import model.Vacina;
import model.modelos_tabelas.ModeloTabelaVacinas;
import view.TelasCadastro.CadastrarTutor;
import view.TelasCadastro.CadastroVacina;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class VacinasScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private ArrayList<Vacina> vacinas = new ArrayList<Vacina>();
	private JPanel contentPane;
	private JTable table;
	private JButton btnNewButton;
	VacinasScreen vacinasScreen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VacinasScreen frame = new VacinasScreen();
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
	public VacinasScreen() {
		
		DAOVacina daoVacina = new DAOVacina();
		vacinas = daoVacina.listarTodos();
		vacinasScreen = this;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 29, 609, 330);
		contentPane.add(scrollPane);
		
		ModeloTabelaVacinas modeloTabelaVacinas = new ModeloTabelaVacinas(vacinas);
		
		table = new JTable();
		table.setModel(modeloTabelaVacinas);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton()==1) {
					Vacina vacinaSelecionada = daoVacina.buscarPorId(modeloTabelaVacinas.getValueAt(table.getSelectedRow(),0).toString());
					CadastroVacina cadastrarVacina = new CadastroVacina(vacinaSelecionada, vacinasScreen);
					cadastrarVacina.setLocationRelativeTo(null);
					cadastrarVacina.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					cadastrarVacina.setVisible(true);
				}	
			}
		}); 
		scrollPane.setViewportView(table);
		
		btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroVacina cadastrarVacina = new CadastroVacina(null, vacinasScreen);
				cadastrarVacina.setLocationRelativeTo(null);
				cadastrarVacina.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				cadastrarVacina.setVisible(true);
			}
		});
		btnNewButton.setBounds(537, 369, 117, 30);
		contentPane.add(btnNewButton);

	}

}
