package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DoctorDAO implements DAO {

	private ArrayList<DoctorDTO> datos = new ArrayList<DoctorDTO>();
	private Connection conexion = Conexion.getConexion();
	
	public static String msg = "";
	
	
	public void create(Object doctor) {
		// TODO Auto-generated method stub
		DoctorDTO d = (DoctorDTO) doctor;
		System.out.println("creando"); 
		try {
			PreparedStatement st = conexion.prepareStatement("insert into doctores (nombre, edad, email, password, sexo, identificacion, especialidad )" +
					" values(?,?,?,?,?,?,?)");
			st.setString(1, d.getNombre());
			st.setString(2, d.getEdad());	
			st.setString(3, d.getEmail());
			st.setString(4, d.getPassword());
			st.setString(5, d.getSexo());
			st.setString(6, d.getIdentificacion());
			st.setString(7, d.getEspecialidad());
			
			int res = st.executeUpdate();
			msg = (res>0)?"Registro satisfactorio" : "No se guardo nada";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			/*try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}

	}

	
	public void delete(Object emailDoctor) {
		// TODO Auto-generated method stub
		System.out.println("Borrando"); 
		try {
			PreparedStatement st = conexion.prepareStatement("delete from doctores where " +
					" email=?");
			st.setString(1, (String) emailDoctor);
			
			int res = st.executeUpdate();
			msg = (res>0)?"Se elimino el registro correctamente" : "No se borro nada";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

	}

	
	public void update(Object doctor) {
		// TODO Auto-generated method stub
		DoctorDTO d = (DoctorDTO) doctor;
		System.out.println("Actualizando"); 
		try {
			PreparedStatement st = conexion.prepareStatement("update from doctores set nombre=?, edad=?, password=?, sexo=?, identificacion=?, especialidad=? )" +
					" where email = ? ");
			st.setString(1, d.getNombre());
			st.setString(2, d.getEdad());	
			st.setString(3, d.getPassword());
			st.setString(4, d.getSexo());
			st.setString(5, d.getIdentificacion());
			st.setString(6, d.getEspecialidad());
			st.setString(7, d.getEmail());
			
			int res = st.executeUpdate();
			msg = (res>0)?"Se actualizo el registro correctamente" : "No se actualizo nada";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

	}

	
	
	public Object findById(Object id) {
		// TODO Auto-generated method stub
		String email = (String)id;
		DoctorDTO doc = new DoctorDTO();
		try {
			PreparedStatement st = conexion.prepareStatement("select * from doctores where email = ?");
			st.setString(1, email);
			
			st.execute();
			ResultSet rs = st.getResultSet();
			doc = getDoctor(rs);
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (Object)doc;
	}

	
	public ArrayList<DoctorDTO> findAll() {
		// TODO Auto-generated method stub
		try {
			System.out.println("consultando");
			PreparedStatement st = conexion.prepareStatement("select * from doctores");
			
			st.execute();
			ResultSet rs = st.getResultSet();
			
			while( rs.next() ){
				DoctorDTO doc = getDoctor(rs);
				datos.add(doc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/*finally{
			try {
				//conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		return datos;
	}
	
	private DoctorDTO getDoctor(ResultSet rs) throws SQLException{
		DoctorDTO doc = new DoctorDTO();
		doc.setNombre(rs.getString("nombre"));
		doc.setEdad(rs.getString("edad"));
		doc.setEmail(rs.getString("email"));
		doc.setPassword(rs.getString("password"));
		doc.setIdentificacion(rs.getString("identificacion"));
		doc.setEspecialidad(rs.getString("especialidad"));
		doc.setSexo(rs.getString("sexo"));
			   
	    return doc;
	}

}
