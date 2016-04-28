package dao;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;


@ManagedBean(name="usuarioDAO")
public class Usuario {
	
		
	public List<UsuarioDAO> getUsuariosList() throws SQLException{
		UsuarioDAO usuario = new UsuarioDAO();
		return usuario.read();
	}
	
	public void create(String email, String firstName, String lastName,
			String password, String phone, String fax){
		UsuarioDAO usuario = new UsuarioDAO();
		usuario.setEmail(email);
		usuario.setFax(fax);
		usuario.setFirstName(firstName);
		usuario.setLastName(lastName);
		usuario.setPassword(password);
		usuario.setPhone(phone);
		usuario.create();
	}
	
	public void update(String email, String firstName, String lastName,
			String password, String phone, String fax){
		UsuarioDAO usuario = new UsuarioDAO();
		usuario.setEmail(email);
		usuario.setFax(fax);
		usuario.setFirstName(firstName);
		usuario.setLastName(lastName);
		usuario.setPassword(password);
		usuario.setPhone(phone);
		usuario.update();
		
	}

	public void delete(String email){
		UsuarioDAO usuario = new UsuarioDAO();
		usuario.setEmail(email);
		usuario.delete();
	}

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

}
