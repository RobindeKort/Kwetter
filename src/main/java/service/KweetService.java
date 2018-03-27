package service;

import dal.*;
import domain.*;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author Robin
 */
@Stateless
public class KweetService {

    // TODO - Replace with interface
    @Inject
//    private KweetDaoJpa kweetDao;
    private IKweetDao kweetDao;

    public KweetService() {
    }

    // TODO - Replace with Interface
//    public KweetService(KweetDaoJpa kweetDao) {
//        this.kweetDao = kweetDao;
//    }

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
