package Modelo;

public class Mascota {
	
	private String nombre;
	private String especie;
	private int id;
	public Mascota(int id, String nombre, String especie) {
		this.nombre = nombre;
		this.especie = especie;
		this.id = id;
	}
	
	
	public Mascota() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getNombre() {
		return nombre;
	}

	public String getEspecie() {
		return especie;
	}
	

}
