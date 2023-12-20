package ch.hearc.ig.guideresto.persistence.handlers;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.List;

public abstract class GenericHandler<E> {
    protected final EntityManager em;
    protected final Class<E> entityClass;

    protected GenericHandler(EntityManager em, Class<E> entityClass) {
        this.em = em;
        this.entityClass = entityClass;
    }

    /**
     * Updates the given entity in the database in a managed way using Java Persistence API (JPA).
     * The method starts a transaction, persists the entity changes, and commits the transaction.
     * If an exception occurs during the process, the transaction is rolled back to maintain database consistency.
     *
     * @param entity The entity to be updated in the database.
     * @throws Exception If any error occurs during the update process, an exception is thrown.
     *                   The method attempts to roll back the transaction and prints error messages and stack traces.
     *                   Note: The exception is propagated to the caller for further handling.
     *                   It is recommended to handle specific exceptions for better error diagnosis.
     * @see javax.persistence.EntityManager
     * @see javax.persistence.EntityTransaction
     */
    public void save(E entity) {
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();

            em.persist(entity);

            em.flush();
            transaction.commit();
        }catch (Exception e){
            if (transaction != null && transaction.isActive()){
                try {
                    transaction.rollback();
                    System.out.println("Transaction rollbacked");
                }catch (Exception ex){
                    System.out.println("Couldn't rollback transaction");
                    ex.printStackTrace();
                }
            }
            System.out.println("Error in update : " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void delete (E entity){
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            em.flush();
            transaction.commit();
        }catch (Exception e){
            if (transaction != null && transaction.isActive()){
                try {
                    transaction.rollback();
                    System.out.println("Transaction rollbacked");
                }catch (Exception ex){
                    System.out.println("Couldn't rollback transaction");
                    ex.printStackTrace();
                }
            }
            System.out.println("Error in delete : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void update(E entity) {
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();

            em.merge(entity);

            em.flush();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                try {
                    transaction.rollback();
                    System.out.println("Transaction rollbacked");
                } catch (Exception ex) {
                    System.out.println("Couldn't rollback transaction");
                    ex.printStackTrace();
                }
            }
            System.out.println("Error in update: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public List<E> findAll() {
        try {
            return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public E findById(Serializable id) {
        try {
            return em.find(entityClass, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
