package Controlador;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.table.DefaultTableModel;

import Modelo.*;
import Vista.*;

public class Controlador {
	private Principal ventana;
	private Anadir formulario;
	private HashMap<String, AccesoDatos> dataAccessManager;
	private DefaultTableModel datos;
	
	private String almacenamiento;

	public Controlador() {
		this.dataAccessManager = new HashMap<>();
		dataAccessManager.put("BBDD", new BDManager());
		dataAccessManager.put("Fichero", new FileManager());
		this.ventana = new Principal(this);
	}

	public DefaultTableModel getDatos(String almacenamiento) {
		datos = new DefaultTableModel();
		datos.addColumn("ID");
		datos.addColumn("Nombre");
		datos.addColumn("Especie");
		HashMap<Integer, Mascota> dataMap = dataAccessManager.get(almacenamiento).getDatos();
		for (Entry<Integer, Mascota> entry : dataMap.entrySet()) {
			String key = entry.getKey().toString(), nombre = entry.getValue().getNombre(),
					especie = entry.getValue().getEspecie();
			datos.addRow(new String[] { key, nombre, especie });
		}

		return datos;
	}
	
	public void abrirFormulario(String almacenamiento) {
		this.almacenamiento = almacenamiento;
		formulario = new Anadir(this);
	}
	public void guardarDatos(Mascota mascota, int id) {
		dataAccessManager.get(this.almacenamiento).meterEntrada(mascota, id);
	}

}
