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
				
				datos.put(Integer.parseInt(contenido[0]), new Mascota(contenido[1], contenido[2]));
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
