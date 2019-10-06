package Modelo;

import java.io.*;
import java.util.HashMap;
import java.util.Map.Entry;

public class FileManager implements AccesoDatos {
	private File fichero;
	private HashMap<Integer,Mascota> datos;

	public FileManager() {
		this.fichero = new File("./src/datos.txt");
		this.datos = new HashMap<>();
		cargarDatos();
		
	}

	@Override
	public void cargarDatos() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fichero));
			String line;
			while ((line = reader.readLine())!=null) {
				String[] campos = line.split("/");
				datos.put(Integer.parseInt(campos[0]), new Mascota(campos[1], campos[2]));
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public HashMap<Integer, Mascota> getDatos() {
		return datos;
	}

	@Override
	public void meterEntrada(Mascota mascota, int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sustituyePor(HashMap<Integer, Mascota> datos) {
		for (Entry<Integer, Mascota> entry : datos.entrySet()) {
			meterEntrada(entry.getValue(),entry.getKey());
		}

	}

}
