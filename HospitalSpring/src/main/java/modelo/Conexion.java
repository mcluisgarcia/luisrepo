package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
	
	private static Connection conn;
	static{
		try {
			DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
		
		    conn = DriverManager.getConnection
		          ("jdbc:oracle:thin:@localhost:1521:XE", "HR", "hr");
		         // driver@machineName:port:SID           ,  userid,  password
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static synchronized Connection getConexion(){
		return conn;
	}
}
