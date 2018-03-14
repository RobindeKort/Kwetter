package dal;

import domain.User;
import java.util.Set;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;
import javax.persistence.RollbackException;

/**
 *
 * @author Robin
 */
public class UserDaoJpa implements IUserDao {
    private final EntityManager em;
    
    public UserDaoJpa(EntityManager em) {
        this.em = em;
    }
    
    /**
     * The entity is persisted. If an entity with the same primary key already
     * exists, an EntityExistsException is thrown.
     *
     * @param user the object that is to be persisted in the database
     * @throws IllegalArgumentException if the given object is not an entity
     * @throws RollbackException if an entity instance with the same primary key
     * already exists in the persistent context
     * @throws EntityExistsException if an entity instance with the same primary
     * key already exists in the persistent context
     */
    @Override
    public void createUser(User user) throws IllegalArgumentException, RollbackException, EntityExistsException {
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (IllegalStateException ise) {
            handleExceptions(ise);
        } catch (IllegalArgumentException iae) {
            em.getTransaction().rollback();
            throw iae;
        } catch (RollbackException rbe) {
            throw rbe;
        } catch (EntityExistsException eee) {
            em.getTransaction().rollback();
            throw eee;
        }
    }

    /**
     * Merge the state of the given object into persistent context. If the
     * entity does not exist, an IllegalArgumentException is thrown
     *
     * @param user the object that is to be merged into the persistent context
     * @throws IllegalArgumentException if the given object is not an entity or
     * is a removed entity
     */
    @Override
    public void updateUser(User user) throws IllegalArgumentException {
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } catch (IllegalStateException ise) {
            handleExceptions(ise);
        } catch (IllegalArgumentException iae) {
            em.getTransaction().rollback();
            throw iae;
        }
    }

    /**
     * Remove the given entity instance
     *
     * @param user the entity instance to remove from the persistent context
     * @throws IllegalArgumentException if the given object is not an entity or
     * is a detached entity
     */
    @Override
    public void removeUser(User user) throws IllegalArgumentException {
        try {
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
        } catch (IllegalStateException ise) {
            handleExceptions(ise);
        } catch (IllegalArgumentException iae) {
            em.getTransaction().rollback();
            throw iae;
        }
    }
    
    /**
     * Find the entity instance that matches the given primary key value.
     *
     * @param id the primary key of the given object to search for
     * @return the found entity instance or null if the entity does not exist
     */
    @Override
    public User getUser(Long id) throws IllegalArgumentException, NullPointerException {
        User ret = null;
        try {
            em.getTransaction().begin();
            ret = em.find(User.class, id);
            em.getTransaction().commit();
        } catch (IllegalStateException ise) {
            handleExceptions(ise);
        } catch (IllegalArgumentException iae) {
            em.getTransaction().rollback();
            throw iae;
        }
        if (ret == null) {
            throw new NullPointerException("Please provide a valid search term. ");
        }
        return ret;
    }

    @Override
    public User getUserByUserName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<User> getUsersByUserName(String partialName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Handle the thrown exception
     *
     * @param ex the exception that has been thrown
     */
    protected void handleExceptions(Exception ex) {
        if (ex instanceof IllegalStateException) {
            // If isActive() is false
            System.out.println("The EntityManager is not active. ");
            // TODO handle exception
        } else if (ex instanceof RollbackException) {
            // If the commit fails
            System.out.println("The commit failed due to an unknown error. ");
            // TODO handle exception
        } else if (ex instanceof QueryTimeoutException) {
            // If the statement execution exceeds the query timeout value
            System.out.println("The query took too long to be executed. ");
        }
        ex.printStackTrace();
    }

    /**
     * Delete all entries of this object type in the database
     *
     * @param areYouSure true if you are sure you want to completely empty the
     * table of this object type, false otherwise
     */
    public void cleanTable(boolean areYouSure) {
        if (!areYouSure) {
            return;
        }
        try {
            em.getTransaction().begin();
            String entityName = em.getMetamodel().entity(User.class).getName();
            em.createQuery("delete from " + entityName).executeUpdate();
            em.getTransaction().commit();
        } catch (IllegalStateException ise) {
            handleExceptions(ise);
        } catch (RollbackException rbe) {
            handleExceptions(rbe);
        } catch (QueryTimeoutException qte) {
            handleExceptions(qte);
        } catch (IllegalArgumentException iae) {
            System.out.println("The given DELETE query is invalid. ");
            em.getTransaction().rollback();
        }
    }
}
