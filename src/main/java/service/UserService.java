package service;

import dal.*;
import domain.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Robin
 */
public class UserService {
    private IUserDao userDao;
    private KweetService kweetService;
    
    public UserService() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Kwet_JPA");
        userDao = new UserDaoJpa(emf.createEntityManager());
        kweetService = new KweetService(emf.createEntityManager());
    }
    
    public void createUser(User user) throws IllegalArgumentException {
        User exists = userDao.getUserByUserName(user.getUserName());
        if (exists == null) {
            userDao.createUser(user);
        } else {
            throw new IllegalArgumentException(String.format("User with name %s already exists.", user.getUserName()));
        }
    }
    
    public void updateUser(User user) throws IllegalArgumentException {
        User exists = userDao.getUser(user.getId());
        if (exists == null) {
            throw new IllegalArgumentException(String.format("User with ID %d does not exist.", user.getId()));
        }
        userDao.updateUser(user);
    }
    
    public void removeUser(User user) {
        userDao.removeUser(user);
    }
    
    public User getUser(Long id) {
        return userDao.getUser(id);
    }
    
    public Set<User> findUsers(String partialName) {
        return Collections.unmodifiableSet(userDao.getUsersByUserName(partialName));
    }
    
    /**
     * Make user1 follow user2, will automatically persist changes with the DAO. 
     * @param user1 User who is going to follow user2
     * @param user2 User who is going to be followed by user1
     */
    public void followUser(User user1, User user2) {
        user1.follow(user2);
        user2.addFollowedBy(user1);
        updateUser(user1);
        updateUser(user2);
    }
    
    public void sendKweet(User user, String kweetBody) {
        Kweet kweet = new Kweet(user, kweetBody);
        kweetService.createKweet(kweet);
        // TODO - Check if kweet has been given an ID before adding it to user. 
        user.addKweet(kweet);
        updateUser(user);
    }
    
    public Queue<Kweet> getFollowingKweets(User user) {
        Queue<Kweet> kweets = new PriorityQueue();
        for (User u : user.getFollowing()) {
            kweets.addAll(u.getKweets());
        }
        return kweets;
    }
}
