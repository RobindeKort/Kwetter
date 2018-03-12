package service;

import dao.IUserDao;
import domain.User;

/**
 *
 * @author Robin
 */
public class UserService {
    private IUserDao userDao;
    private KweetService kweetService;
    
    public UserService() {
        kweetService = new KweetService();
    }
    
    public void createUser(User user) {
        User exists = userDao.getUserByUserName(user.getUserName());
        if (exists == null) {
            userDao.createUser(user);
        } else {
            throw new IllegalArgumentException(String.format("User with name %s already exists.", user.getUserName()));
        }
    }
    
    public void updateUser(User user) {
        userDao.updateUser(user);
    }
    
    public void removeUser(User user) {
        userDao.removeUser(user.getId());
    }
}
