package Vista;

import javax.swing.JButton;
import javax.swing.JFrame;

import Controlador.Controlador;
import Modelo.Mascota;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Principal extends JFrame {
	private Controlador control;
	private JTabbedPane tabbedPane;
	// BBDD Components
	private JTable tablaBBDD;
	private JPanel panelBBDD;
	private JButton btnAddToBBDD;
	private JButton btnMigrarBBDD;
	private JButton btnBorrar1BBDD;
	private JButton btnTruncateBBDD;
	private JButton btnEditarBBDD;

	// File Components
	private JTable tablaFile;
	private JPanel panelFile;
	private JButton btnAddToFile;
	private JButton btnMigrarFile;
	private JButton btnBorrar1File;
	private JButton btnTruncateFile;
	private JButton btnEditarFile;

	// Hibernate Components
	private JTable tablaHibernate;
	private JPanel panelHibernate;
	private JButton btnAddToHibernate;
	private JButton btnMigrarHibernate;
	private JButton btnBorrar1Hibernate;
	private JButton btnTruncateHibernate;
	private JButton btnEditarHibernate;

	public Principal(Controlador control) {
		this.control = control;
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new CardLayout(0, 0));
		// Instanciamos el TabbedPane
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, "name_9068315948724");

		// Instanciamos panelBBDD
		panelBBDD = new JPanel();
		tablaBBDD = new JTable();
		setTable("BBDD");
		panelBBDD.setLayout(null);
		JScrollPane scrollPane = new JScrollPane(tablaBBDD);
		scrollPane.setBounds(0, 0, 429, 210);
		panelBBDD.add(scrollPane);
		tabbedPane.addTab("BBDD", null, panelBBDD, "Visualiza y modifica tu Base de Datos");
		// Añadir a BBDD
		btnAddToBBDD = new JButton("Add");
		btnAddToBBDD.setBounds(0, 210, 90, 23);
		btnAddToBBDD.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				abrirFormulario("BBDD");
				setTable("BBDD");
			}
		});
		panelBBDD.add(btnAddToBBDD);

		// Migrar BBDD
		btnMigrarBBDD = new JButton("Migrar");
		btnMigrarBBDD.setBounds(90, 210, 90, 23);
		btnMigrarBBDD.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				migrar("BBDD");
				setTable("Fichero");

			}
		});
		panelBBDD.add(btnMigrarBBDD);

		// Eliminar 1 a BBDD
		btnBorrar1BBDD = new JButton("Borrar1");
		btnBorrar1BBDD.setBounds(180, 210, 90, 23);
		btnBorrar1BBDD.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object aux = tablaBBDD.getValueAt(tablaBBDD.getSelectedRow(), 0);
				if (aux != null) {
					borrar("BBDD", Integer.parseInt((String) aux));
					setTable("BBDD");
				}

			}
		});
		panelBBDD.add(btnBorrar1BBDD);

		// EliminarBBDD
		btnTruncateBBDD = new JButton("Eliminar");
		btnTruncateBBDD.setBounds(270, 210, 90, 23);
		btnTruncateBBDD.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				borrarTodos("BBDD");
				setTable("BBDD");
			}
		});
		panelBBDD.add(btnTruncateBBDD);

		// EditarBBDD
		btnEditarBBDD = new JButton("Editar");
		btnEditarBBDD.setBounds(360, 210, 90, 23);
		btnEditarBBDD.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object aux = tablaBBDD.getValueAt(tablaBBDD.getSelectedRow(), 0);
				if (aux != null) {
					editar("BBDD", Integer.parseInt((String) aux));
					setTable("BBDD");
				}
			}
		});
		panelBBDD.add(btnEditarBBDD);

		// Instanciamos panelFIle
		panelFile = new JPanel();
		tablaFile = new JTable();
		setTable("Fichero");
		panelFile.setLayout(null);
		JScrollPane scrollPane1 = new JScrollPane(tablaFile);
		scrollPane1.setBounds(0, 0, 429, 210);
		panelFile.add(scrollPane1);
		tabbedPane.addTab("Fichero", null, panelFile, "Visualiza y modifica tu Fichero de Datos");

		// Anhadir File
		btnAddToFile = new JButton("Add");
		btnAddToFile.setBounds(0, 210, 90, 23);
		btnAddToFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				abrirFormulario("Fichero");
				setTable("Fichero");
			}
		});
		panelFile.add(btnAddToFile);

		// Migrar File
		btnMigrarFile = new JButton("Migrar");
		btnMigrarFile.setBounds(90, 210, 90, 23);
		btnMigrarFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				migrar("Fichero");
				setTable("BBDD");

			}
		});
		panelFile.add(btnMigrarFile);

		// Eliminar 1 a File
		btnBorrar1File = new JButton("Borrar1");
		btnBorrar1File.setBounds(180, 210, 90, 23);
		btnBorrar1File.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object aux = tablaFile.getValueAt(tablaFile.getSelectedRow(), 0);
				if (aux != null) {
					borrar("Fichero", Integer.parseInt((String) aux));
					setTable("Fichero");
				}
			}
		});
		panelFile.add(btnBorrar1File);

		// EliminarFile
		btnTruncateFile = new JButton("Eliminar");
		btnTruncateFile.setBounds(270, 210, 90, 23);
		btnTruncateFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				borrarTodos("Fichero");
				setTable("Fichero");
			}
		});
		panelFile.add(btnTruncateFile);
		// EditarFile
		btnEditarFile = new JButton("Editar");
		btnEditarFile.setBounds(360, 210, 90, 23);
		btnEditarFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object aux = tablaFile.getValueAt(tablaFile.getSelectedRow(), 0);
				if (aux != null) {
					editar("Fichero", Integer.parseInt((String) aux));
					setTable("Fichero");
				}
			}
		});
		panelFile.add(btnEditarFile);

		// Instanciamos panelHibernate
		panelHibernate = new JPanel();
		tablaHibernate = new JTable();
		setTable("Hibernate");
		panelHibernate.setLayout(null);
		JScrollPane scrollPane2 = new JScrollPane(tablaHibernate);
		scrollPane2.setBounds(0, 0, 429, 210);
		panelHibernate.add(scrollPane2);
		tabbedPane.addTab("Hibernate", null, panelHibernate, "Visualiza y modifica tu BBDD Hibernate");
		// Añadir a Hibernate
		btnAddToHibernate = new JButton("Add");
		btnAddToHibernate.setBounds(0, 210, 90, 23);
		btnAddToHibernate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				abrirFormulario("Hibernate");
				setTable("Hibernate");
			}
		});
		panelHibernate.add(btnAddToHibernate);

		// Migrar Hibernate
		btnMigrarHibernate = new JButton("Migrar");
		btnMigrarHibernate.setBounds(90, 210, 90, 23);
		btnMigrarHibernate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				migrar("Hibernate");
				setTable("Fichero");

			}
		});
		panelHibernate.add(btnMigrarHibernate);

		// Eliminar 1 a Hibernate
		btnBorrar1Hibernate = new JButton("Borrar1");
		btnBorrar1Hibernate.setBounds(180, 210, 90, 23);
		btnBorrar1Hibernate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object aux = tablaHibernate.getValueAt(tablaHibernate.getSelectedRow(), 0);
				if (aux != null) {
					borrar("Hibernate", Integer.parseInt((String) aux));
					setTable("Hibernate");
				}

			}
		});
		panelHibernate.add(btnBorrar1Hibernate);

		// EliminarHibernate
		btnTruncateHibernate = new JButton("Eliminar");
		btnTruncateHibernate.setBounds(270, 210, 90, 23);
		btnTruncateHibernate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				borrarTodos("Hibernate");
				setTable("Hibernate");
			}
		});
		panelHibernate.add(btnTruncateHibernate);

		// EditarHibernate
		btnEditarHibernate = new JButton("Editar");
		btnEditarHibernate.setBounds(360, 210, 90, 23);
		btnEditarHibernate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object aux = tablaHibernate.getValueAt(tablaHibernate.getSelectedRow(), 0);
				if (aux != null) {
					editar("Hibernate", Integer.parseInt((String) aux));
					setTable("Hibernate");
				}
			}
		});
		panelHibernate.add(btnEditarHibernate);

		// Mostramos la ventana
		setVisible(true);
	}

	public void setTable(String almacenamiento) {
		DefaultTableModel aux = control.getDatos(almacenamiento);
		switch (almacenamiento) {
		case "BBDD":

			tablaBBDD.setModel(aux);
			;
			break;

		case "Fichero":
			tablaFile.setModel(aux);
			break;
		case "Hibernate":
			tablaHibernate.setModel(aux);
			break;
		default:
			break;
		}
	}

	private void abrirFormulario(String almacenamiento) {
		control.abrirFormulario(almacenamiento);
		setTable(almacenamiento);
	}

	private void migrar(String almacenamiento) {
		control.migrar(almacenamiento);
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

}
