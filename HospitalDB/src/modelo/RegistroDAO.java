package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RegistroDAO implements DAO{
 
	private static Connection conexion = Conexion.getConexion();
	public static String msg = "";
	
	@Override
	public void create(Object registro) {
		// TODO Auto-generated method stub
		RegistroDTO r = (RegistroDTO) registro;
		System.out.println("creando"); 
		try {
			PreparedStatement st = conexion.prepareStatement("insert into registros (id, fechainicio, fechafin, fecharegistro, semana, doctor, dia )" +
					" values(?,?,?,?,?,?,?)");
			st.setInt(1, r.getId());
			st.setTimestamp(2, convStrTime( r.getFechaInicio() ) );	
			st.setTimestamp(3, convStrTime(r.getFechaFin()) );
			st.setTimestamp(4, convStrTime (r.getFechaRegistro()) );
			st.setInt(5, r.getSemana());
			st.setString(6, r.getDoctor());
			st.setString(7, r.getDia());
			
			int res = st.executeUpdate();
			msg = (res>0)?"Registro satisfactorio" : "No se guardo nada";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

		
	}

	@Override
	public void update(Object registro) {
		// TODO Auto-generated method stub
		RegistroDTO d = (RegistroDTO) registro;
		System.out.println("Actualizando"); 
		try {
			PreparedStatement st = conexion.prepareStatement("update from registros set fechainicio=?, fechafin=?, fecharegistro=?, semana=?, doctor=?, dia=? )" +
					" where id = ? ");
			st.setTimestamp(1, convStrTime(d.getFechaInicio()) );
			st.setTimestamp(2, convStrTime(d.getFechaFin()) );	
			st.setTimestamp(3, convStrTime(d.getFechaRegistro()));
			st.setInt(4, d.getSemana());
			st.setString(5, d.getDoctor());
			st.setInt(6, d.getId());
			st.setString(7, d.getDia());
			int res = st.executeUpdate();
			msg = (res>0)?"Se actualizo el registro correctamente" : "No se actualizo nada";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
		System.out.println("Borrando"); 
		try {
			PreparedStatement st = conexion.prepareStatement("delete from registros where " +
					" id=?");
			st.setInt(1, (Integer) id);
			
			int res = st.executeUpdate();
			msg = (res>0)?"Se elimino el registro correctamente" : "No se borro nada";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

		
	}

	@Override
	public Object findById(Object idd) {
		// TODO Auto-generated method stub
		
		int id = (Integer)idd;
		RegistroDTO reg = new RegistroDTO();
		try {
			PreparedStatement st = conexion.prepareStatement("select * from registros where id = ?");
			st.setInt(1, id);
			
			st.execute();
			ResultSet rs = st.getResultSet();
			reg = getReg(rs);
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (Object)reg;
	}

	@Override
	public ArrayList findAll() {
		// TODO Auto-generated method stub
		ArrayList<RegistroDTO> datos = new ArrayList<RegistroDTO>();
		try {
			PreparedStatement st = conexion.prepareStatement("select * from registros");
			st.execute();
			ResultSet rs = st.getResultSet();
			int i = 1;
			
			while( rs.next() ){
				RegistroDTO doc = getReg(rs);
				datos.add(doc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datos;
	}
	
	private Timestamp convStrTime(String time){
		Timestamp timestamp = null;
		try{
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		    Date parsedDate = dateFormat.parse(time);
		    timestamp = new java.sql.Timestamp(parsedDate.getTime());
		}catch(Exception e){//this generic but you can control another types of exception
		 //look the origin of excption 
		}
		return timestamp;
	}
	
	private RegistroDTO getReg(ResultSet rs) throws SQLException{
		RegistroDTO reg = new RegistroDTO();
		reg.setId(rs.getInt("id"));
		reg.setFechaInicio(rs.getTimestamp("fechainicio").toString());
		reg.setFechaFin(rs.getTimestamp("fechafin").toString());
		reg.setFechaRegistro(rs.getTimestamp("fecharegistro").toString());
		reg.setSemana(rs.getInt("id"));
		reg.setDoctor(rs.getString("doctor"));
		reg.setDia(rs.getString("dia"));
			   
	    return reg;
	}

}
