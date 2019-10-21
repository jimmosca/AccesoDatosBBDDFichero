package Modelo;

import java.util.HashMap;

public interface AccesoDatos {
	
	//Carga los datos del almacenamiento en el HashMap
	public void cargarDatos();
	// Devolucion del HashMap para posterior utilizacion 
	public HashMap<Integer, Mascota> getDatos();
	//Escribe una nueva entrada en el almacenamiento
	public void meterEntrada(Mascota mascota);
	//Edita una entrada existente en el almacenamiento
	public void editarEntrada(Mascota mascota);
	//Intercambia los datos del almacenamiento por los recibidos
	//1. Iguala el HashMap local al recibido
	//2. Escribe entrada por entrada
	public void sustituyePor(HashMap<Integer,Mascota> datos);
	public void borrar(int id);

}
