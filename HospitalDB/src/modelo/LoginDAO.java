package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class LoginDAO {
	
	
	public String findLogin(LoginDTO login) {
		if(login.getEmail()==null && login.getPassword()==null )
			return "";
		// TODO Auto-generated constructor stub		
		
		Connection conexion = Conexion.getConexion();
		String res = "";
		PreparedStatement st;
		try {
			st = conexion.prepareStatement("select email, nombre, password from doctores where password =? and email =?");
			st.setString(1, login.getPassword());
			st.setString(2, login.getEmail());
		
			ResultSet rs = st.executeQuery();
			while(rs.next())
				res = rs.getString("nombre");
			
			//throw new UsuarioNoExiste();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return res;
	}
	
	 public void addMessage(String summary) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }
	 
}
