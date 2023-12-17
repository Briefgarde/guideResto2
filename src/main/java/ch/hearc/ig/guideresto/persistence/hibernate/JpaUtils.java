package ch.hearc.ig.guideresto.persistence.hibernate;

import ch.hearc.ig.guideresto.business.*;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.cfg.Configuration;

public class JpaUtils {
    private static EntityManagerFactory emf;
    private static EntityManager em;


    public static EntityManager getEntityManager() {
        if (em == null || !em.isOpen()) {
            if (emf == null) {
                emf = Persistence.createEntityManagerFactory("guideRestoORM_PersUnit");
            }
            em = emf.createEntityManager();
        }
        return em;
    }
}
