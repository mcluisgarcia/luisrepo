package hbm;

// Generated 19/04/2016 04:21:12 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Registros.
 * @see hbm.Registros
 * @author Hibernate Tools
 */
public class RegistrosHome {

	private static final Log log = LogFactory.getLog(RegistrosHome.class);

	private final SessionFactory sessionFactory = SessionFact.getSessionFactory();
	
	Session se; 

	public void persist(Registros transientInstance) {
		log.debug("persisting Registros instance");
		Transaction tx = null;
		se = sessionFactory.openSession();
		try {
			tx = se.beginTransaction();
			se.persist(transientInstance);
			tx.commit();
			tx=null;
			log.debug("persist successful");
		}catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			System.out.println("Could not acquire message" + e);
			addMessage(e.toString());
		}
		catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}finally{
			se.close();
		}
	}

	public void attachDirty(Registros instance) {
		log.debug("attaching dirty Registros instance");
		se = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = se.beginTransaction();
			//Doctores doc = (Doctores)se.get(Registros.class, instance.getDoctores().getEmail());
            //instance.setDoctores(doc);
            
			se.saveOrUpdate(instance);
			tx.commit();
			tx=null;
			log.debug("attach successful");
		}catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			System.out.println("Could not acquire message" + e);
			addMessage(e.toString());
		}catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}finally{
			if(se.isOpen())
				se.close();
		}
	}

	public void attachClean(Registros instance) {
		log.debug("attaching clean Registros instance");
		se = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = se.beginTransaction();
			se.lock(instance, LockMode.NONE);
			tx.commit();
			tx=null;
			log.debug("attach successful");
		}catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			System.out.println("Could not acquire message" + e);
			addMessage(e.toString());
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}finally{
			se.close();
		}
	}

	public void delete(Registros persistentInstance) {
		log.debug("deleting Registros instance");
		se = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = se.beginTransaction();
			se.delete(persistentInstance);
			tx.commit();
			tx=null;
			log.debug("delete successful");
		}catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			System.out.println("Could not acquire message" + e);
			addMessage(e.toString());
		}
		catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}finally{
			se.close();
		}
	}

	public Registros merge(Registros detachedInstance) {
		log.debug("merging Registros instance");
		se = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = se.beginTransaction();
			Registros result = (Registros) se.merge(detachedInstance);
			log.debug("merge successful");
			tx.commit();
			tx=null;
			return result;
		}catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			System.out.println("Could not acquire message" + e);
			addMessage(e.toString());
		}
		 catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}finally{
			se.close();
		}
		return null;
	}

	public Registros findById(java.lang.String id) {
		log.debug("getting Registros instance with id: " + id);
		se = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = se.beginTransaction();
			Registros instance = (Registros) se.get("hbm.Registros", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			tx.commit();
			tx=null;
			return instance;
		}catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			System.out.println("Could not acquire message" + e);
			addMessage(e.toString());
		}
		 catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally{
			se.close();
		}
		return null;
	}

	public List findByExample(Registros instance) {
		log.debug("finding Registros instance by example");
		se = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = se.beginTransaction();
			List results = se.createCriteria("hbm.Registros")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			tx.commit();
			tx=null;
			return results;
		}catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			System.out.println("Could not acquire message" + e);
			addMessage(e.toString());
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}finally{
			se.close();
		}
		return null;
	}
	
	 private void addMessage(String summary) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }
}
