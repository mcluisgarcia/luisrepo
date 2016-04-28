package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CitasDAO implements DAO {
	
	private Connection conexion = Conexion.getConexion();

	public void create(Object registro) {
		
		CitasDTO c = (CitasDTO) registro;
		System.out.println("creando"); 
		try {
			PreparedStatement st = conexion.prepareStatement("insert into citas (idcita, nombre, edad, email, sexo, telefono, sintomas, fechacita, emailDoc )" +
					" values(?,?,?,?,?,?,?,?,?)");
			st.setString(1,c.getIdcita());
			st.setString(2,c.getNombre());
			st.setString(3,c.getEdad());
			st.setString(4,c.getEmail());
			st.setString(5,c.getSexo());
			st.setString(6,c.getTelefono());
			st.setString(7,c.getSintomas());
			st.setString(8,c.getFechaCita());
			st.setString(9,c.getDoctorEmail());
			
			int res = st.executeUpdate();
			String msg = (res>0)?"Registro satisfactorio" : "No se guardo nada";
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/*finally{
			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
	}
	
	
public List<CitasDTO> findCitas(String doctor, String nombre){
		System.out.println(doctor +" "+ nombre);
		List<CitasDTO> citas = new ArrayList<CitasDTO>();
		try {
			PreparedStatement st = conexion.prepareStatement("select * "
					+ " from citas "
					+ " where emaildoc = ? and nombre like ?");
			st.setString(1, doctor);
			st.setString(2, nombre + "%");
			
			st.execute();
			ResultSet rs = st.getResultSet();
			while( rs.next() ){
				citas.add(getCitasDoc(rs));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return citas;
	}

	public void update(Object registro) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Object id) {
		// TODO Auto-generated method stub
		
	}

	public Object findById(Object id) {
		// TODO Auto-generated method stub
		String idc = (String)id;
		List<String> citas = new ArrayList<String>();
		try {
			PreparedStatement st = conexion.prepareStatement("select * "
					+ " from citas "
					+ " where idcita=?");
			
			st.setString(1, idc);
			
			st.execute();
			ResultSet rs = st.getResultSet();
			while( rs.next() ){
				citas.add("Nombre: "+rs.getString("nombre"));
				citas.add("Edad: "+rs.getString("edad"));
				citas.add("Email: "+rs.getString("email"));
				citas.add("Sexo: "+rs.getString("sexo"));
				citas.add("Sintomas: "+rs.getString("sintomas"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (Object)citas;
	}

	public ArrayList findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<Map<String, String>> findDisponibles(String doctor){
		
		ArrayList<Map<String, String>> horarios = new ArrayList<Map<String, String>>();
		try {
			PreparedStatement st = conexion.prepareStatement("select r.id as regid, d.nombre as doc, d.email as emaildoc, r.fechainicio as horai, r.fechafin as horaf, dia, semana"
					+ " from doctores d inner join registros r "
					+ "on d.email = r.email where d.email = ?");
			st.setString(1, doctor);
			
			st.execute();
			ResultSet rs = st.getResultSet();
			while( rs.next() ){
				horarios.add(getHorarios(rs));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return horarios;
	}
	
public ArrayList<Map<String, String>> findSemanas(String doctor){
		
		ArrayList<Map<String, String>> horarios = new ArrayList<Map<String, String>>();
		try {
			PreparedStatement st = conexion.prepareStatement("select semana"
					+ " from doctores d inner join registros r "
					+ "on d.email = r.email where d.email = ? group by semana");
			st.setString(1, doctor);
			
			st.execute();
			ResultSet rs = st.getResultSet();
			while( rs.next() ){
				horarios.add(getSemanas(rs));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return horarios;
	}


public ArrayList<Map<String, String>> findHorasOcupadas(String email){
	
	ArrayList<Map<String, String>> hrs = new ArrayList<Map<String, String>>();
	try {
		
		PreparedStatement st = conexion.prepareStatement("select idcita, fechacita"
				+ " from citas "
				+ " where emaildoc = ? ");
		st.setString(1, email);
		
		st.execute();
		ResultSet rs = st.getResultSet();
		while( rs.next() ){
			hrs.add(getHrsCitas(rs));
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return hrs;
}

public ArrayList<Map<String, String>> findHoras(String semana, String email, String dia){
	
	ArrayList<Map<String, String>> horarios = new ArrayList<Map<String, String>>();
	try {
		
		PreparedStatement st = conexion.prepareStatement("select dia, fechainicio, fechafin"
				+ " from doctores d inner join registros r "
				+ "on d.email = r.email where d.email = ? and semana = ? and dia=?");
		st.setString(1, email);
		st.setString(2, semana);
		st.setString(3, dia);
		
		st.execute();
		ResultSet rs = st.getResultSet();
		while( rs.next() ){
			horarios.add(getHoras(rs));
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return horarios;
}



public ArrayList<Map<String, String>> findDias(String semana, String email){
	
	ArrayList<Map<String, String>> horarios = new ArrayList<Map<String, String>>();
	try {
		PreparedStatement st = conexion.prepareStatement("select dia"
				+ " from doctores d inner join registros r "
				+ "on d.email = r.email where d.email = ? and semana = ?");
		st.setString(1, email);
		st.setString(2, semana);
		
		st.execute();
		ResultSet rs = st.getResultSet();
		while( rs.next() ){
			horarios.add(getDias(rs));
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return horarios;
}
	private CitasDTO getCitasDoc(ResultSet rs) throws SQLException{
		CitasDTO citas = new CitasDTO();
		citas.setDoctorEmail(rs.getString("emaildoc"));
		citas.setEdad(rs.getString("edad"));
		citas.setEmail(rs.getString("email"));
		
		String presFecha = rs.getString("fechacita");
		citas.setFechaCita(presentacionC(presFecha));
		citas.setIdcita(rs.getString("idcita"));
		citas.setNombre(rs.getString("nombre"));
		citas.setSexo(rs.getString("sexo"));
		citas.setSintomas(rs.getString("sintomas"));
		citas.setTelefono(rs.getString("telefono"));
			   
	    return citas;
	}
	
	private String presentacionC(String campo)
	{
		String res = "";
		String[] descom = campo.trim().split(",");
		for(int i=0; i< descom.length; i++){
			res+=(i==0)?" Semana "+ descom[0]:
				 								(i==1)?" Dia "+ descom[1]:
				 										(i==2)?" Hora "+ descom[2]:"";
		}
		
		return res;
	}

	private Map<String, String> getHoras(ResultSet rs) throws SQLException{
		Map<String, String> reg = new HashMap<String,String>();
		reg.put("fechainicio",rs.getString("fechainicio"));
		reg.put("fechafin", rs.getString("fechafin"));
			   
	    return reg;
	}

	private Map<String, String> getDias(ResultSet rs) throws SQLException{
		Map<String, String> reg = new HashMap<String,String>();
		reg.put("valor",rs.getString("dia"));
		reg.put("key", rs.getString("dia"));
			   
	    return reg;
	}

	private Map<String, String> getSemanas(ResultSet rs) throws SQLException{
		Map<String, String> reg = new HashMap<String,String>();
		reg.put("valor",rs.getString("semana"));
		reg.put("key", rs.getString("semana"));
			   
	    return reg;
	}

	private Map<String, String> getHorarios(ResultSet rs) throws SQLException{
		Map<String, String> reg = new HashMap<String,String>();
		reg.put("doctor", rs.getString("doc"));
		reg.put("email", rs.getString("emaildoc"));
		reg.put("horai", rs.getString("horai"));
		reg.put("horaf", rs.getString("horaf"));
		reg.put("dia", rs.getString("dia"));
		reg.put("semana",rs.getString("semana"));
		reg.put("idreg", rs.getString("regid"));
			   
	    return reg;
	}

	private Map<String, String> getHrsCitas(ResultSet rs) throws SQLException{
			Map<String, String> reg = new HashMap<String,String>();
			reg.put("key",rs.getString("idcita"));
			String hrs = transHrs(rs.getString("fechacita"));
			reg.put("valor", hrs);
	    return reg;
	}
	
	private String transHrs( String fechasCitas ){
		String[] splitTexto = fechasCitas.split(",");
		System.out.println(splitTexto[2]);
		return splitTexto[2].trim();
	}
}
