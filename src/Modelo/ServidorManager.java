package Modelo;

import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import Controlador.ApiRequests;

public class ServidorManager implements AccesoDatos {
	ApiRequests encargadoPeticiones;
	private String SERVER_PATH, GET_PET, SET_PET;

	private HashMap<Integer, Mascota> datos;

	public ServidorManager() {
		encargadoPeticiones = new ApiRequests();
		datos = new HashMap<>();

		SERVER_PATH = "http://localhost/ADAT_UD4_A01_IntercambioDatos/";
		GET_PET = "leeMascotas.php";
		SET_PET = "modificaMascota.php";
		cargarDatos();
	}

	@Override
	public void cargarDatos() {
		String url = SERVER_PATH + GET_PET;
		try {
			String response = encargadoPeticiones.getRequest(url);
			JSONArray respuesta = (JSONArray) JSONValue.parse(response);
			for (Object object : respuesta) {
				JSONObject row = (JSONObject) object;
				int id = Integer.parseInt((String) row.get("id"));
				String nombre = (String) row.get("nombre");
				String especie = (String) row.get("especie");
				datos.put(id, new Mascota(id, nombre, especie));
			}

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
	public void meterEntrada(Mascota mascota) {
		String url = SERVER_PATH + SET_PET;
		JSONObject jsonMascota = new JSONObject();
		datos.put(mascota.getId(), mascota);

		jsonMascota.put("operacion", "insertar");
		jsonMascota.put("id", mascota.getId());
		jsonMascota.put("nombre", mascota.getNombre());
		jsonMascota.put("especie", mascota.getEspecie());

		String json = jsonMascota.toJSONString();
		String respuesta = "";
		try {
			respuesta = encargadoPeticiones.postRequest(url, json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject response = (JSONObject) JSONValue.parse(respuesta);
		mostrarResultado(response, "INSERTAR");
	}

	@Override
	public void editarEntrada(Mascota mascota) {
		String url = SERVER_PATH + SET_PET;
		JSONObject jsonMascota = new JSONObject();
		datos.put(mascota.getId(), mascota);

		jsonMascota.put("operacion", "editar");
		jsonMascota.put("id", mascota.getId());
		jsonMascota.put("nombre", mascota.getNombre());
		jsonMascota.put("especie", mascota.getEspecie());

		String json = jsonMascota.toJSONString();
		String respuesta = "";
		try {
			respuesta = encargadoPeticiones.postRequest(url, json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject response = (JSONObject) JSONValue.parse(respuesta);
		mostrarResultado(response, "EDITAR");

	}

	@Override
	public void sustituyePor(HashMap<Integer, Mascota> datos) {
		String url = SERVER_PATH + SET_PET;
		JSONObject jsonMensaje = new JSONObject();
		
		
		jsonMensaje.put("operacion", "sustituir");
		this.datos = datos;
		JSONArray jsonMascotas = new JSONArray();
		JSONObject jsonMascota;
		for (Mascota mascota : datos.values()) {
			jsonMascota = new JSONObject();
			
			jsonMascota.put("id", mascota.getId());
			jsonMascota.put("nombre", mascota.getNombre());
			jsonMascota.put("especie", mascota.getEspecie());
			
			jsonMascotas.add(jsonMascota);
		}
		
		jsonMensaje.put("mascotas", jsonMascotas);
		String json = jsonMensaje.toJSONString();
		String respuesta = "";
		try {
			respuesta = encargadoPeticiones.postRequest(url, json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject response = (JSONObject) JSONValue.parse(respuesta);
		mostrarResultado(response, "SUSTITUIR");
	}

	@Override
	public void borrar(int id) {
		String url = SERVER_PATH + SET_PET;
		JSONObject jsonMascota = new JSONObject();
		datos.remove(id);

		jsonMascota.put("operacion", "borrar");
		jsonMascota.put("id", id);

		String json = jsonMascota.toJSONString();
		String respuesta = "";

		try {
			respuesta = encargadoPeticiones.postRequest(url, json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject response = (JSONObject) JSONValue.parse(respuesta);
		mostrarResultado(response, "BORRAR");
	}

	private void mostrarResultado(JSONObject response, String operacion) {
		if (response == null) { // Si hay algún error de parseo (json
			// incorrecto porque hay algún caracter
			// raro, etc.) la respuesta será null
			System.out.println("El json recibido no es correcto. Finaliza la ejecución");

		} else { // El JSON recibido es correcto

			String resultado = (String) response.get("resultado");

			System.out.println(operacion + ": " + resultado);
		}
	}

}
