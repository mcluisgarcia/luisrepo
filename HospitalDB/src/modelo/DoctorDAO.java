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
	private String sql = "";
	
	public static String msg = "";
	
	@Override
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
		}		

	}

	@Override
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

	@Override
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

	
	@Override
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

	@Override
	public ArrayList findAll() {
		// TODO Auto-generated method stub
		try {
			PreparedStatement st = conexion.prepareStatement("select * from doctores");
			
			ArrayList<DoctorDTO> datos = new ArrayList<DoctorDTO>();
			st.execute();
			ResultSet rs = st.getResultSet();
			int i = 1;
			
			while( rs.next() ){
				DoctorDTO doc = getDoctor(rs);
				datos.add(doc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datos;
	}
	
	private DoctorDTO getDoctor(ResultSet rs) throws SQLException{
		DoctorDTO doc = new DoctorDTO();
		doc.setEdad(rs.getString("nombre"));
		doc.setEdad(rs.getString("edad"));
		doc.setEdad(rs.getString("email"));
		doc.setEdad(rs.getString("password"));
		doc.setEdad(rs.getString("identificacion"));
		doc.setEdad(rs.getString("especialidad"));
		doc.setEdad(rs.getString("sexo"));
			   
	    return doc;
	}

}
