package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsDAO implements DAO{
	
	private Connection conexion = Conexion.getConexion();

	public void create(Object registro) {
		// TODO Auto-generated method stub
		
	}

	public void update(Object registro) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Object id) {
		// TODO Auto-generated method stub
		
	}

	public Object findById(Object id) {
		// TODO Auto-generated method stub
		String idN = (String)id;
		NewsDTO noticia = new NewsDTO();
		try {
			PreparedStatement st = conexion.prepareStatement("select * "
					+ " from news "
					+ " where id=?");
			
			st.setString(1, idN);
			
			st.execute();
			ResultSet rs = st.getResultSet();
			while( rs.next() ){
				noticia.setId(rs.getString("id"));
				noticia.setTitulo(rs.getString("titulo"));
				noticia.setAutor(rs.getString("autor"));
				noticia.setContenido(rs.getString("contenido"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (Object)noticia;
	}

	public ArrayList findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
