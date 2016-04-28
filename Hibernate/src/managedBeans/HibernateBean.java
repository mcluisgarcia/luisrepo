package managedBeans;

import hbm.Curso;
import hbm.Estudiante;
import hbm.Usuarios;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.apache.log4j.Logger;



@ManagedBean(name="hibernate")
@RequestScoped
public class HibernateBean {

	private List<Usuarios> list;	 
	
	
	public void consulta(){
		
		//SessionFactory sessions = new Configuration().configure().buildSessionFactory();
		//Session session = sessions.openSession();
		Session session = SessionFact.getSessionFactory().openSession(); 
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = session.createQuery("from Usuarios").list();
			
			for(Usuarios user: list){
				System.out.println("email: "+ user.getEmail());
				System.out.println("nombre: "+ user.getLastName());
				System.out.println("apellido: "+ user.getLastName());
				System.out.println("tel: "+ user.getPhone());
				System.out.println("fax: "+ user.getFax());
			}
			tx.commit();
			tx = null;
			//return list;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			System.out.println("Could not acquire message" + e);
			
		} finally {
			session.close();
		}
	}
	
public void consultaParam(){
		
		Session session = SessionFact.getSessionFactory().openSession(); 
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from Usuarios where email= :email_param");
			q.setString("email_param", "pepe@hotmail.com");
			list = q.list();
			
			for(Usuarios user: list){
				System.out.println("email: "+ user.getEmail());
				System.out.println("nombre: "+ user.getLastName());				
			}
			tx.commit();
			tx = null;
			//return list;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			System.out.println("Could not acquire message" + e);
			
		} finally {
			session.close();
		}
	}
	
	public List<Usuarios> getUsuario(){
		return list;
	}

	public void insertEstudiante(){
		
		Session session = SessionFact.getSessionFactory().openSession(); 
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			Curso curso = new Curso();
			curso.setId(1);
			curso.setDescripcion("java");
			
			Estudiante estudiante = new Estudiante();
			estudiante.setId(1);
			estudiante.setNombre("Mario");
			
			HashSet<Curso> setCursos = new HashSet<Curso>();
			setCursos.add(curso);
			estudiante.setCursos(setCursos);
			
			session.save(estudiante);
			
			
			tx.commit();
			tx = null;
			//return list;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			System.out.println("Could not acquire message" + e);
			
		} finally {
			session.close();
		}
	}
	
	
public void consultaEstudiante(){
		
		Session session = SessionFact.getSessionFactory().openSession(); 
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from Estudiante where id= :userId " + "and nombre=:userName" );
			q.setInteger("userId", 1);
			q.setString("userName", "Mario");
			
			List<Estudiante> list = q.list();
			
			for(Estudiante es: list){
				System.out.println("Info Id: "+ es.getId());
				System.out.println("Info Nombre: "+ es.getNombre());		
				
				for(Object c: es.getCursos()  ){
					System.out.println("Curso Id: "+ ( (Curso)c).getId());
					System.out.println("Curso Descripcion: "+ ( (Curso)c).getDescripcion());					
				}
			}
			tx.commit();
			tx = null;
			//return list;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			System.out.println("Could not acquire message" + e);
			
		} finally {
			session.close();
		}
	}

public void guardaCurso(){
	
	Session session = SessionFact.getSessionFactory().openSession(); 
	Transaction tx = null;
	try {
		tx = session.beginTransaction();
		//Query q = session.createQuery("from Curso where id= :cursoId " );
		//q.setInteger("cursoId", 1);
		
		//List<Curso> list = q.list();
		//Curso curso = list.get(0);
		
		Curso curso = new Curso(); ///Cuidado!!! el uso de new en hibernate puede borrar registros
		curso.setId(1);
		
		System.out.println("____ Curso: "+ curso.getDescripcion());
		
		Estudiante estudiante = new Estudiante();
		estudiante.setId(4);
		estudiante.setNombre("Ernesto");
		
		HashSet<Curso> setCurso=new HashSet<Curso>();
		setCurso.add(curso);
		estudiante.setCursos(setCurso);
		
		session.saveOrUpdate(estudiante);
		
			
		tx.commit();
		tx = null;
		//return list;
	} catch (HibernateException e) {
		if (tx != null)
			tx.rollback();
		System.out.println("Could not acquire message" + e);
		
	} finally {
		session.close();
	}
   }

	public void countCursos(){
		
		Session session = SessionFact.getSessionFactory().openSession(); 
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			Iterator iteraciones = session.createQuery("select count(*) from Curso").iterate();
			
			while(iteraciones.hasNext()){
				Long cuenta = (Long)iteraciones.next();
				
				System.out.println("count del Curso: "+ cuenta);
			}
				
			tx.commit();
			tx = null;
			//return list;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			System.out.println("Could not acquire message" + e);
			
		} finally {
			session.close();
		}
	}
	
	public void sqlTradicional(){
		
		Session session = SessionFact.getSessionFactory().openSession(); 
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			List resultados = session.createSQLQuery("select distinct e.id, e.nombre "
					+ "from estudiante e "
			 		+ "inner join inscrito i on e.id = i.id_estudiante "
			 		+ "inner join curso c on c.id = i.id_curso "
			 		+ "and c.id in (select p.id_curso "
			 		+ "from profesor p "
			 		+ "group by p.id_curso "
			 		+ "having count(p.id_curso) >1 )").list();
			
			Iterator datos = resultados.iterator();
			
			while(datos.hasNext()){
				Object[] dato = (Object[])datos.next();
				
				System.out.println("----- Datos SQL ---: ");
				System.out.println("------Alumno id: "+ dato[0]);
				System.out.println("------Alumno nombre: " + dato[1]);
			}
				
			tx.commit();
			tx = null;
			//return list;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			System.out.println("Could not acquire message" + e);
			
		} finally {
			session.close();
		}
	}
	
	
public void sqlHSQL(){
		
		Session session = SessionFact.getSessionFactory().openSession(); 
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			List resultados = session.createQuery("select distinct est.id, est.nombre "
					+ "from Estudiante est "
					+ "join est.cursos c "
					+ "where c.id in ( select p.curso "
					+ "from Profesor p "
					+ "group by p.curso.id "
					+ "having count(p.curso.id) > 1)").list();
			
			Iterator datos = resultados.iterator();
			
			while(datos.hasNext()){
				Object[] dato = (Object[])datos.next();
				
				System.out.println("----- Datos SQL ---: ");
				System.out.println("------Alumno id: "+ dato[0]);
				System.out.println("------Alumno nombre: " + dato[1]);
			}
				
			tx.commit();
			tx = null;
			//return list;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			System.out.println("Could not acquire message" + e);
			
		} finally {
			session.close();
		}
	}
}
