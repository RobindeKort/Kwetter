package service;

import dal.*;
import domain.*;
import javax.persistence.EntityManager;

/**
 *
 * @author Robin
 */
public class KweetService {
    private IKweetDao kweetDao;
    
    public KweetService(EntityManager em) {
        kweetDao = new KweetDaoJpa(em);
        // nothing
    }
    
    public void createKweet(Kweet kweet) {
        kweetDao.createKweet(kweet);
    }
    
    public void updateKweet(Kweet kweet) {
        /*Kweet exists = kweetDao.getKweet(kweet.getId());
        if (exists == null) {
            throw new IllegalArgumentException(String.format("Kweet with ID %d does not exist.", kweet.getId()));
        }*/
        kweetDao.updateKweet(kweet);
    }
    
    public void removeKweet(Kweet kweet) {
        kweetDao.removeKweet(kweet);
    }
    
    public Kweet getKweet(Long id) {
        return kweetDao.getKweet(id);
    }
    
    public void likeKweet(User user, Kweet kweet) {
        kweet.like(user);
        updateKweet(kweet);
    }
}
