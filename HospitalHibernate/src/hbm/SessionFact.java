package hbm;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFact {

	//Singelton SessionFactory
	private static final SessionFactory SESSION_FACTORY;
	//Singleton Logger
	static Logger LOG = Logger.getLogger(SessionFact.class);
	// static initialisation block
	
	static {
	    try {
	        // Create the SessionFactory from hibernate.cfg.xml
	        SESSION_FACTORY = new Configuration().configure("/mapeos/hibernate.cfg.xml").buildSessionFactory();
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
        return SESSION_FACTORY;
    }
}
