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
		dataAccessManager.put("Hibernate", new HibernateManager());
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
		ventana.setTable(almacenamiento);
	}

	public void guardarDatos(Mascota mascota) {
		dataAccessManager.get(this.almacenamiento).meterEntrada(mascota);
	}

	public void migrar(String almacenamiento) {
		switch (almacenamiento) {
		case "BBDD":
			dataAccessManager.get("Fichero").sustituyePor(dataAccessManager.get(almacenamiento).getDatos());
			break;
		case "Fichero":
			dataAccessManager.get("BBDD").sustituyePor(dataAccessManager.get(almacenamiento).getDatos());
			break;

		default:
			break;
		}
	}
	public void borrarTodos(String almacenamiento) {
		dataAccessManager.get(almacenamiento).sustituyePor(new HashMap<>());
	}

	public void borrar(String almacenamiento, int id) {
		dataAccessManager.get(almacenamiento).borrar(id);
		
	}

	public void editar(String almacenamiento, int id) {
		this.almacenamiento = almacenamiento;
		formulario = new Anadir(this, id);
		ventana.setTable(almacenamiento);
		
	}

	public String getAlmacenamiento() {
		return almacenamiento;
	}

	public void editarDatos(Mascota mascota) {
		dataAccessManager.get(getAlmacenamiento()).editarEntrada(mascota);
		
		
	}

}
