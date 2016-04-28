package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;



public class LoginDAO {
	
	
	public DoctorDTO findLogin(LoginDTO login) {
		if(login.getEmail()==null && login.getPassword()==null )
			return null;
		// TODO Auto-generated constructor stub		
		
		Connection conexion = Conexion.getConexion();
		DoctorDTO doc = null;
		PreparedStatement st;
		try {
			st = conexion.prepareStatement("select * from doctores where password =? and email =?");
			st.setString(1, login.getPassword());
			st.setString(2, login.getEmail());
		
			ResultSet rs = st.executeQuery();
			doc = new DoctorDTO();
			while(rs.next()){
				System.out.println(rs.getString("nombre"));
				doc.setNombre(rs.getString("nombre"));
				doc.setEdad(rs.getString("edad"));
				doc.setEmail(rs.getString("email"));
				doc.setPassword(rs.getString("password"));
				doc.setIdentificacion(rs.getString("identificacion"));
				doc.setEspecialidad(rs.getString("especialidad"));
				doc.setSexo(rs.getString("sexo"));
			}
			//throw new UsuarioNoExiste();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return doc;
	}
	
	 
	 
}
