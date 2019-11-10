package Modelo;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.*;

public class MongoManager implements AccesoDatos {

	private MongoClient mongoClient;
	private MongoCollection<Document> collection;
	private HashMap<Integer, Mascota> datos;

	public MongoManager() {
		mongoClient = new MongoClient();
		collection = mongoClient.getDatabase("animalicos").getCollection("animalicos");
		cargarDatos();
	}

	@Override
	public void cargarDatos() {
		datos = new HashMap<>();
		for (Document document : collection.find()) {
			datos.put(document.getInteger("id"), new Mascota(document.getInteger("id"), document.getString("nombre"),
					document.getString("especie")));
		}
	}

	@Override
	public HashMap<Integer, Mascota> getDatos() {
		return this.datos;
	}

	@Override
	public void meterEntrada(Mascota mascota) {
		collection.insertOne(new Document("id", mascota.getId()).append("nombre", mascota.getNombre()).append("especie",
				mascota.getEspecie()));
		datos.put(mascota.getId(), mascota);

	}

	@Override
	public void editarEntrada(Mascota mascota) {
		datos.put(mascota.getId(), mascota);
		collection.updateOne(new Document("id", mascota.getId()), new Document("$set",
				new Document("nombre", mascota.getNombre()).append("especie", mascota.getEspecie())));

	}

	@Override
	public void sustituyePor(HashMap<Integer, Mascota> datos) {
		collection.deleteMany(new Document());
		this.datos = new HashMap<>();
		for (Entry<Integer, Mascota> entry : datos.entrySet()) {
			meterEntrada(entry.getValue());
		}

	}

	@Override
	public void borrar(int id) {
		collection.deleteOne(new Document("id", id));
		datos.remove(id);

	}

}
