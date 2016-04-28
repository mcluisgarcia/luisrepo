package hbm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LoginDAO {
	
	private List<Doctores> list;
	
public Doctores findLogin(LoginDTO login){
		
		if(login.getEmail()==null && login.getPassword()==null )
			return null;
	
		Session session = SessionFact.getSessionFactory().openSession(); 
	    Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from Doctores where email= :email_param and password= :pass_param");
			q.setString("email_param", login.getEmail());
			q.setString("pass_param", login.getPassword());
			list = q.list();
			
			String nombre = "";
			Doctores d = null;
			for(Doctores doc: list){
				nombre = doc.getNombre();
				//addMessage("Bienvenido "+ nombre);
				d=doc;
			}
			tx.commit();
			tx = null;

			return d;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			System.out.println("Could not acquire message" + e);
			addMessage(e.toString());
			
		} finally {
			session.close();
		}
		return null;
	}
	
	 private void addMessage(String summary) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }
	 
	 
}
