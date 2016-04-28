package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


@ManagedBean(name="usuario")
public class UsuarioDAO {

	private DataSource ds;
	public UsuarioDAO() {
		// TODO Auto-generated constructor stub
		ds = null;
		Context ctx;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<Usuario> getUsuariosList() {
			List<Usuario> list = null;
			Connection con = null;
			
			try {
				con=ds.getConnection();
				System.out.println(con);
			if( con == null)
				throw new SQLException("No se puede conectar a la BD");
			
			PreparedStatement ps = con.prepareStatement("select email, first_name, last_name, password, phone, fax from usuarios");
			
			ResultSet result = ps.executeQuery();
			list = new ArrayList<Usuario>();
			
			while( result.next() ){
				Usuario usuario = new Usuario();
				usuario.setEmail(result.getString("email"));
				usuario.setFax(result.getString("fax"));
				usuario.setFirstName(result.getString("first_name"));
				usuario.setLastName(result.getString("last_name"));
				usuario.setPassword(result.getString("password"));
				usuario.setPhone(result.getString("phone"));
				
				list.add(usuario);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			try {
				System.out.println(con);
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
			return list;
	}

}
