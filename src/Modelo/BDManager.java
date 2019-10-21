package Modelo;

import java.sql.*;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.table.DefaultTableModel;

public class BDManager implements AccesoDatos {
	
	private String usr;
	private String pwd;
	private String bd;
	private String url;
	private String driver;

	private String sqlTabla;
	private Connection conexion;
	private HashMap<Integer,Mascota> datos;
	
	public BDManager() {
		this.usr = "root";
		this.pwd = "";

		bd = "animalicos";

		url = "jdbc:mysql://localhost/" + bd
				+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		driver = "com.mysql.cj.jdbc.Driver";

		sqlTabla = "SELECT * FROM mascotas";
		try {
			Class.forName(driver);
			conexion = DriverManager.getConnection(url, usr, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		datos = new HashMap<>();
		cargarDatos();
	}

	@Override
	public void cargarDatos() {
		int numColumnas;
		PreparedStatement pstmt;
		try {
			pstmt = conexion.prepareStatement(sqlTabla);
			ResultSet rset = pstmt.executeQuery();
			ResultSetMetaData rsmd = rset.getMetaData();
			numColumnas = rsmd.getColumnCount();
			while (rset.next()) {
				String[] contenido = new String[numColumnas];
				for (int col = 1; col <= numColumnas; col++) {
					contenido[col - 1] = rset.getString(col);
				}
				
				datos.put(Integer.parseInt(contenido[0]), new Mascota(Integer.parseInt(contenido[0]), contenido[1], contenido[2]));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	

	@Override
	public HashMap<Integer, Mascota> getDatos() {
		return datos;
	}

	@Override
	public void meterEntrada(Mascota mascota) {
		datos.put(mascota.getId(), mascota);
		String nombre = mascota.getNombre();
		String especie = mascota.getEspecie();
		String query = " insert into mascotas (id, nombre, especie)" + " values (?, ?, ?)";
		PreparedStatement pstmt;
		try {
			pstmt = conexion.prepareStatement(query);
			pstmt.setInt(1, mascota.getId());
			pstmt.setString(2, nombre);
			pstmt.setString(3, especie);
			
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sustituyePor(HashMap<Integer, Mascota> datos) {
		this.datos = new HashMap<>();
		String sql = "truncate table mascotas";
		try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		for (Entry<Integer, Mascota> entry : datos.entrySet()) {
			meterEntrada(entry.getValue());
		}
	}

	@Override
	public void borrar(int id) {
		this.datos.remove(id);
		  String query = "delete from mascotas where id = ?";
	      PreparedStatement pstmt;
	      try {
				pstmt = conexion.prepareStatement(query);
				pstmt.setInt(1, id);
				pstmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}
	
	@Override
	public void editarEntrada(Mascota mascota) {
		datos.put(mascota.getId(), mascota);
		String query = "update mascotas set id = ?, nombre = ?, especie = ? where id = ?";
	      PreparedStatement pstmt;
	      try {
				pstmt = conexion.prepareStatement(query);
				pstmt.setInt(1, mascota.getId());
				pstmt.setString(2, mascota.getNombre());
				pstmt.setString(3, mascota.getEspecie());
				pstmt.setInt(4, mascota.getId());
				pstmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}

}
