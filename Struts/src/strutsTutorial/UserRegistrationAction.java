package strutsTutorial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

public class UserRegistrationAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DynaActionForm userDynaForm =
				(DynaActionForm) form;
		System.out.println("Se esta utilizando Action din");
		
		DataSource datasource = getDataSource(request, "userDB");//uso del datasource definido en struts-config
		Connection  conn = datasource.getConnection();
		
		try{
			PreparedStatement st = conn.prepareStatement("insert into usuarios (email, first_name, last_name, password, phone, fax)"
				+ " values (?,?,?,?,?,?)" );
			
			st.setString(1, "mario@hotmail.com");
			st.setString(2, "mario");
			st.setString(3, "hotmail.com");
			st.setString(4, "ma");
			st.setString(5, "8888");
			st.setString(6, "9999");
			st.execute();
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			conn.close();
		}
		
		
		return mapping.findForward("success");
		
	}
	
	
}