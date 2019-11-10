package Vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Controlador.Controlador;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Principal extends JFrame {
	private Controlador control;
	private JTabbedPane tabbedPane;
	private HashMap<String, PanelAlmacenamiento> paneles;
	
	public Principal(Controlador control) {
		this.control = control;
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new CardLayout(0, 0));
		// Instanciamos el TabbedPane
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, "name_9068315948724");

		cargarPaneles();

		// Mostramos la ventana
		setVisible(true);
	}

	public void setTable(String almacenamiento) {
		DefaultTableModel aux = control.getDatos(almacenamiento);
		paneles.get(almacenamiento).getTabla().setModel(aux);
	}

	private void abrirFormulario(String almacenamiento) {
		control.abrirFormulario(almacenamiento);
		setTable(almacenamiento);
	}

	private void migrar(String actual, String nuevo) {
		control.migrar(actual, nuevo);
	}

	private void borrarTodos(String almacenamiento) {
		control.borrarTodos(almacenamiento);
	}

	private void borrar(String almacenamiento, int id) {
		control.borrar(almacenamiento, id);
	}

	private void editar(String almacenamiento, int id) {
		control.editar(almacenamiento, id);

	}

	private String openDialog() {
		String[] selectionValues = { "BBDD", "Fichero", "Hibernate", "Mongo" };
		String selection = JOptionPane.showInputDialog(null, "¿De que almacenamiento quieres importar?",
				"Importar Almacenamiento", JOptionPane.QUESTION_MESSAGE, null, selectionValues, selectionValues[0])
				.toString();
		return selection;
	}

	private void cargarPaneles() {
		paneles = new HashMap<>();
		String[] almacenamientos = { "BBDD", "Fichero", "Hibernate", "Mongo"};
		for (String almacenamiento : almacenamientos) {
			paneles.put(almacenamiento, new PanelAlmacenamiento()) ;
			
			setTable(almacenamiento);
			
			tabbedPane.addTab(almacenamiento, null, paneles.get(almacenamiento), "Visualiza y modifica tu " + almacenamiento);
			paneles.get(almacenamiento).getBtnAdd().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					abrirFormulario(almacenamiento);
					setTable(almacenamiento);
				}
			});
			paneles.get(almacenamiento).getBtnMigrar().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					migrar(almacenamiento, openDialog());
					setTable(almacenamiento);

				}
			});
			paneles.get(almacenamiento).getBtnBorrar().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Object aux = paneles.get(almacenamiento).getTabla().getValueAt(paneles.get(almacenamiento).getTabla().getSelectedRow(), 0);
					if (aux != null) {
						borrar(almacenamiento, Integer.parseInt((String) aux));
						setTable(almacenamiento);
					}

				}
			});
			paneles.get(almacenamiento).getBtnTruncate().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					borrarTodos(almacenamiento);
					setTable(almacenamiento);
				}
			});
			paneles.get(almacenamiento).getBtnEditar().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Object aux = paneles.get(almacenamiento).getTabla().getValueAt(paneles.get(almacenamiento).getTabla().getSelectedRow(), 0);
					if (aux != null) {
						editar(almacenamiento, Integer.parseInt((String) aux));
						setTable(almacenamiento);
					}
				}
			});
		}

	}

}

class PanelAlmacenamiento extends JPanel {
	private JTable tabla;
	private JButton btnAdd;
	private JButton btnMigrar;
	private JButton btnBorrar;
	private JButton btnTruncate;
	private JButton btnEditar;
	public JTable getTabla() {
		return tabla;
	}
	public JButton getBtnEditar() {
		return btnEditar;
	}
	public JButton getBtnTruncate() {
		return btnTruncate;
	}
	public JButton getBtnBorrar() {
		return btnBorrar;
	}
	public JButton getBtnAdd() {
		return btnAdd;
	}
	public JButton getBtnMigrar() {
		// TODO Auto-generated method stub
		return btnMigrar;
	}
	public PanelAlmacenamiento() {
		
		tabla = new JTable();
		
		setLayout(null);
		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(0, 0, 429, 210);
		add(scrollPane);
		

		// Añadir
		btnAdd = new JButton("Add");
		btnAdd.setBounds(0, 210, 90, 23);
		
		add(btnAdd);

		// Migrar 
		btnMigrar = new JButton("Migrar");
		btnMigrar.setBounds(90, 210, 90, 23);
		
		add(btnMigrar);

		// Eliminar 1 
		btnBorrar = new JButton("Borrar1");
		btnBorrar.setBounds(180, 210, 90, 23);
		
		add(btnBorrar);

		// Eliminar
		btnTruncate = new JButton("Eliminar");
		btnTruncate.setBounds(270, 210, 90, 23);
		
		add(btnTruncate);

		// Editar
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(360, 210, 90, 23);
		
		add(btnEditar);
	}
	
}
