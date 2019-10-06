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
		datos.put(id, mascota);
		sobreEscribir();
	}

	@Override
	public void sustituyePor(HashMap<Integer, Mascota> datos) {
		this.datos = new HashMap<>();
		for (Entry<Integer, Mascota> entry : datos.entrySet()) {
			meterEntrada(entry.getValue(),entry.getKey());
		}

	}
	private void sobreEscribir() {
		
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(fichero);

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		 
			for (Entry<Integer, Mascota> entry : datos.entrySet()) {
				bw.write(entry.getKey().toString() + "/" + entry.getValue().getNombre() + "/" + entry.getValue().getEspecie());
				bw.newLine();
			}
		 
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	}

}
