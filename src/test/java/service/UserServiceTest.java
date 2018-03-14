package service;

import domain.User;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Robin
 */
public class UserServiceTest {
    private static UserService service;
    private User defaultUser;
    
    @Before
    public void setUp() {
        service = new UserService();
        
        User u = new User("name");
        service.createUser(u);
        for (User us: service.findUsers("name")) {
            if (us.getUserName().equals("name")) {
                defaultUser = us;
                break;
            }
        }
    }
    
    @Test
    public void testCreateUser() {
        System.out.println("createUser");
        int expResult = 0;
        int result = service.findUsers("asdf").size();
        assertEquals(expResult, result);
        User u = new User("asdf");
        service.createUser(u);
        expResult = 1;
        result = service.findUsers("asdf").size();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testUpdateUser() {
        System.out.println("updateUser");
        assertTrue(defaultUser.setPassword("password", "asdf"));
        service.updateUser(defaultUser);
        service.getUser(defaultUser.getId()).checkPassword("asdf");
    }
    
    @Test
    public void testRemoveUser() {
        System.out.println("removeUser");
        int expResult = 1;
        Set<User> users = service.findUsers("name");
        int result = users.size();
        assertEquals(expResult, result);
        service.removeUser(defaultUser);
        expResult = 0;
        result = service.findUsers("name").size();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testFollowUser() {
        System.out.println("followUser");
        User user = new User("zxcv");
        service.createUser(user);
        for (User u : service.findUsers("zxcv")) {
            if (u.getUserName().equals("zxcv")) {
                service.followUser(defaultUser, u);
                break;
            }
        }
        int expResult = 1;
        assertEquals(expResult, service.getUser(defaultUser.getId()).getFollowing().size());
    }
    
    @Test
    public void testSendKweet() {
        System.out.println("sendKweet");
        final int testKweets = 10;
        for (int i = 0; i < testKweets; i++) {
            service.sendKweet(defaultUser, String.format("Kweet%02d", i));
        }
        int expResult = testKweets;
        assertEquals(expResult, service.getUser(defaultUser.getId()).getKweetCount());
    }
    
    @Test
    public void testGetFollowingKweets() {
        System.out.println("getFollowingKweets");
        service.createUser(new User("zxcv"));
        for (User u : service.findUsers("zxcv")) {
            if (u.getUserName().equals("zxcv")) {
                service.followUser(defaultUser, u);
                service.sendKweet(u, "asdfqwer");
            }
        }
        int expResult = 1;
        assertEquals(expResult, service.getFollowingKweets(defaultUser).size());
    }
}
