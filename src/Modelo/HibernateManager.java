package Modelo;

import java.util.HashMap;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class HibernateManager implements AccesoDatos{
	private Session session;
	private HashMap<Integer,Mascota> datos;
	public HibernateManager() {
		this.session = new Configuration().configure().buildSessionFactory().openSession();
		datos = new HashMap<>();
		System.out.println("Hibernate no ha petado");
	}
	@Override
	public void cargarDatos() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public HashMap<Integer, Mascota> getDatos() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void meterEntrada(Mascota mascota, int id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void sustituyePor(HashMap<Integer, Mascota> datos) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void borrar(int id) {
		// TODO Auto-generated method stub
		
	}
	
}
