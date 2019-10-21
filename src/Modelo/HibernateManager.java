package Modelo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class HibernateManager implements AccesoDatos{
	private Session session;
	private HashMap<Integer,Mascota> datos;
	public HibernateManager() {
		this.session = new Configuration().configure().buildSessionFactory().openSession();
		datos = new HashMap<>();
		cargarDatos();
	}
	@Override
	public void cargarDatos() {
		Query q = session.createQuery("select m from Mascota m");
		List<Mascota> results = q.list();

		Iterator<Mascota> mascotasIterator = results.iterator();
		while (mascotasIterator.hasNext()) {
			Mascota mascota = mascotasIterator.next();
			datos.put(mascota.getId(), mascota);
		}
		session.clear();
		
	}
	@Override
	public HashMap<Integer, Mascota> getDatos() {
		
		return datos;
	}
	@Override
	public void meterEntrada(Mascota mascota) {
		datos.put(mascota.getId(), mascota);
		session.beginTransaction();
		session.save(mascota);
		session.getTransaction().commit();
		session.clear();
	}
	@Override
	public void sustituyePor(HashMap<Integer, Mascota> datos) {
		this.datos = new HashMap<>();
		session.beginTransaction();
		session.createQuery("delete from Mascota").executeUpdate();
		session.getTransaction().commit();
		for (Entry<Integer, Mascota> entry : datos.entrySet()) {
			meterEntrada(entry.getValue());
		}
		session.clear();
	}
	@Override
	public void borrar(int id) {
		session.beginTransaction();
		session.delete(this.datos.get(id));
		session.getTransaction().commit();
		session.clear();
		this.datos.remove(id);
		
	}
	@Override
	public void editarEntrada(Mascota mascota) {
		datos.put(mascota.getId(), mascota);	
		session.beginTransaction();
		session.merge(mascota);
		session.getTransaction().commit();
		session.clear();
	}
	
}
