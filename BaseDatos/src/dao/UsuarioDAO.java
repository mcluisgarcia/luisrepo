package dao;

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


//@ManagedBean(name="usuarioDAO")
public class UsuarioDAO implements UsuarioDAOi {

	private DataSource ds;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String phone;
	private String fax;
	
	public static String msg = "";
	
	public UsuarioDAO() {
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

	@Override
			public List<UsuarioDAO> read() {
				List<UsuarioDAO> list = null;
				Connection con = null;
				
				try {
					con=ds.getConnection();
					System.out.println(con);
				if( con == null)
					throw new SQLException("No se puede conectar a la BD");
				
				PreparedStatement ps = con.prepareStatement("select email, first_name, last_name, password, phone, fax from usuarios");
				
				ResultSet result = ps.executeQuery();
				list = new ArrayList<UsuarioDAO>();
				
				while( result.next() ){
					
					email = result.getString("email");
					fax = (result.getString("fax"));
					firstName = (result.getString("first_name"));
					lastName = (result.getString("last_name"));
					password = (result.getString("password"));
					phone = (result.getString("phone"));
					
					list.add(this);
					
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

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
		
		Connection con = null;
		
		try {
			con=ds.getConnection();
			System.out.println(con);
		if( con == null)
			throw new SQLException("No se puede conectar a la BD");
		
		PreparedStatement ps = con.prepareStatement("insert into usuarios (email,fax,first_name, last_name,password,phone) values ?,?,?,?,?,?");
		ps.setString(1, email);
		ps.setString(2, fax);
		ps.setString(3, firstName);
		ps.setString(4, lastName);
		ps.setString(5, password);
		ps.setString(6, phone);
		
		int result = ps.executeUpdate();
		msg=(result>0)? "Usuario creado con exito": "No se guardo nada";	
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
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		/*this.email = email;
		this.fax = fax;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.phone = phone;	*/
		
		Connection con = null;
		try {
			con=ds.getConnection();
			System.out.println(con);
		if( con == null)
			throw new SQLException("No se puede conectar a la BD");
		
		PreparedStatement ps = con.prepareStatement("Update from usuarios set fax = ?, first_name = ?, last_name = ?, password = ?, phone = ? where email = ?");
		
		ps.setString(1, fax);
		ps.setString(2, firstName);
		ps.setString(3, lastName);
		ps.setString(4, password);
		ps.setString(5, phone);
		ps.setString(6, email);
		
		int result = ps.executeUpdate();
		msg=(result>0)? "Usuario actualizado con exito": "No se actualizo nada";
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
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
		
		Connection con = null;
		try {
			con=ds.getConnection();
			System.out.println(con);
		if( con == null)
			throw new SQLException("No se puede conectar a la BD");
		
		PreparedStatement ps = con.prepareStatement("delete from usuarios where email = ?");
		ps.setString(1, email);
		
		int result = ps.executeUpdate();
		msg=(result>0)? "Usuario eliminado con exito": "No se elimino nada";
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
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String fistName) {
		this.firstName = fistName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getFax() {
		return fax;
	}


	public void setFax(String fax) {
		this.fax = fax;
	}

}
