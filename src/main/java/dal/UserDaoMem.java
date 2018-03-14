package dal;

import domain.Kweet;
import domain.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Robin
 */
public class UserDaoMem implements IUserDao {
    private Map<Long, User> users;
    
    public UserDaoMem() {
        users = new HashMap();
    }
    
    @Override
    public void createUser(User user) {
        Long id = new Long(users.size());
        user.setId(id);
        users.putIfAbsent(id, user);
    }

    @Override
    public void updateUser(User user) {
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
        }
        throw new IllegalArgumentException(String.format("User with ID %d does not exist.", user.getId()));
    }

    @Override
    public void removeUser(User user) {
        users.remove(user.getId());
    }
    
    @Override
    public User getUser(Long id) {
        return users.get(id);
    }

    @Override
    public User getUserByUserName(String name) {
        for (Map.Entry<Long, User> entry : users.entrySet()) {
            if (entry.getValue().getUserName().equals(name)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public Set<User> getUsersByUserName(String partialName) {
        Set<User> userSet = new TreeSet();
        for (Map.Entry<Long, User> entry : users.entrySet()) {
            if (entry.getValue().getUserName().toLowerCase().contains(partialName.toLowerCase())) {
                userSet.add(entry.getValue());
            }
        }
        return userSet;
    }
}
