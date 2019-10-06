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

public class Principal extends JFrame {
	private Controlador control;
	private JTabbedPane tabbedPane;
	//BBDD Components
	private JTable tablaBBDD;
	private JPanel panelBBDD;
	private JButton btnAddToBBDD;
	private JButton btnMigrarBBDD;
	
	//File Components
	private JTable tablaFile;
	private JPanel panelFile;
	private JButton btnAddToFile;
	private JButton btnMigrarFile;

	public Principal(Controlador control) {
		this.control = control;
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new CardLayout(0, 0));
		//Instanciamos el TabbedPane
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, "name_9068315948724");
		
		
		//Instanciamos panelBBDD
		panelBBDD = new JPanel();
		panelBBDD.setName("BBDD");
		setTable(panelBBDD.getName());
		panelBBDD.setLayout(null);
		JScrollPane scrollPane = new JScrollPane(tablaBBDD);
		scrollPane.setBounds(0, 0, 429, 210);
		panelBBDD.add(scrollPane);
		tabbedPane.addTab("BBDD", null, panelBBDD, "Visualiza y modifica tu Base de Datos");
		//Añadir a BBDD
		btnAddToBBDD = new JButton("Add");
		btnAddToBBDD.setBounds(0, 210, 90, 23);
		btnAddToBBDD.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirFormulario("BBDD");
			}
		});
		panelBBDD.add(btnAddToBBDD);
		
		//Migrar BBDD
		btnMigrarBBDD = new JButton("Migrar");
		btnMigrarBBDD.setBounds(90, 210, 90, 23);
		panelBBDD.add(btnMigrarBBDD);
		
		
		//Instanciamos panelFIle
		panelFile = new JPanel();
		panelFile.setName("Fichero");
		setTable(panelFile.getName());
		panelFile.setLayout(null);
		JScrollPane scrollPane1 = new JScrollPane(tablaFile);
		scrollPane1.setBounds(0, 0, 429, 210);
		panelFile.add(scrollPane1);
		tabbedPane.addTab("Fichero", null, panelFile, "Visualiza y modifica tu Fichero de Datos");
		
		btnAddToFile = new JButton("Add");
		btnAddToFile.setBounds(0, 210, 90, 23);
		btnAddToFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirFormulario("Fichero");
			}
		});
		panelFile.add(btnAddToFile);
		
		//Mostramos la ventana
		setVisible(true);
	}

	public void setTable(String almacenamiento) {
		switch (almacenamiento) {
		case "BBDD":
			tablaBBDD = new JTable(control.getDatos(almacenamiento));
			break;

		case "Fichero":
			tablaFile = new JTable(control.getDatos(almacenamiento));
			break;
		default:
			break;
		}
	}
	
	private void abrirFormulario(String almacenamiento) {
		control.abrirFormulario(almacenamiento);
		setTable(almacenamiento);
	}

}
