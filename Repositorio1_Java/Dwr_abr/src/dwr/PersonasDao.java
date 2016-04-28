package dwr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class PersonasDao {
	private DataSource ds;
	public PersonasDao() {
		ds = null;
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public List<Person> read(String filter) {
		filter=filter.toLowerCase();
		List<Person> list = null;
		Connection con = null;
		try {
			con = ds.getConnection();
			if (con == null)
				throw new SQLException("Can´t get database connection");
			
			PreparedStatement ps = con
					.prepareStatement("SELECT name, age, address FROM person WHERE LOWER(name) like ?");
			ps.setString(1, "%"+filter+"%");
			
			ResultSet result = ps.executeQuery();
			list = new ArrayList<Person>();
			
			while (result.next()) {
				Person person = new Person();
				person.setName(result.getString("name"));
				person.setAge(result.getInt("age"));
				person.setAddress(result.getString("address"));
				
				//Pattern regex=Pattern.compile(filter,Pattern.CASE_INSENSITIVE);
				
				//if(regex.matcher(person.getName()).find()){
					list.add(person);
				//}
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		} finally {
			try {
				if(con!= null)
				con.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				//e.printStackTrace();
			}
		}
		return list;
	}
}
