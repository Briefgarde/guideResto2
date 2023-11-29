package ch.hearc.ig.guideresto.persistence.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
