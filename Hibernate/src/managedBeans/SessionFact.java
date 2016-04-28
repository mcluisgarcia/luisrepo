package managedBeans;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFact {

	//Singelton SessionFactory
	private static final SessionFactory SESSION_FACTORY;
	//Singleton Logger
	static Logger LOG = Logger.getLogger(HibernateBean.class);
	// static initialisation block
	
	static {
	    try {
	        // Create the SessionFactory from hibernate.cfg.xml
	        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
	    } catch (Throwable ex) {
	        // Make sure you log the exception, as it might be swallowed
	        if (LOG.isDebugEnabled()) {
	            LOG.debug("Failure while initialising: " + ex);
	        }
	        throw new ExceptionInInitializerError(ex);
	    }
	}
	
	public static synchronized final SessionFactory getSessionFactory()
    {
        //if(SESSION_FACTORY==null)
        	//SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
          
        return SESSION_FACTORY;
    }
}
