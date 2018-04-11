package service;

import dal.*;
import domain.Account;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Robin
 */
public class AccountServiceTest {
    /*private static EntityManagerFactory emf;
    private KweetDaoJpa kweetDao;
    private UserDaoJpa userDao;
    private UserService userService;
    private Account defaultUser;
    
    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("Kwet_JPA");
    }
    
    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }
    
    @Before
    public void setUp() {
        kweetDao = new KweetDaoJpa(emf.createEntityManager());
        userDao = new UserDaoJpa(emf.createEntityManager());
        userService = new UserService(userDao, kweetDao);
        
        Account u = new Account("name");
        userService.createUser(u);
        for (Account us: userService.findUsers("name")) {
            if (us.getUserName().equals("name")) {
                defaultUser = us;
                break;
            }
        }
    }
    
    @After
    public void tearDown() {
        kweetDao.cleanTable(true);
        userDao.cleanTable(true);
    }
    
    @Test
    public void testCreateUser() {
        System.out.println("createUser");
        int expResult = 0;
        int result = userService.findUsers("asdf").size();
        assertEquals(expResult, result);
        Account u = new Account("asdf");
        userService.createUser(u);
        expResult = 1;
        result = userService.findUsers("asdf").size();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testUpdateUser() {
        System.out.println("updateUser");
        assertTrue(defaultUser.setPassword("password", "asdf"));
        userService.updateUser(defaultUser);
        userService.getUser(defaultUser.getId()).checkPassword("asdf");
    }
    
    @Test
    public void testRemoveUser() {
        System.out.println("removeUser");
        int expResult = 1;
        List<User> users = userService.findUsers("name");
        int result = users.size();
        assertEquals(expResult, result);
        userService.removeUser(defaultUser);
        expResult = 0;
        result = userService.findUsers("name").size();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testFollowUser() {
        System.out.println("followUser");
        Account user = new Account("zxcv");
        userService.createUser(user);
        for (Account u : userService.findUsers("zxcv")) {
            if (u.getUserName().equals("zxcv")) {
                userService.followUser(defaultUser, u);
                break;
            }
        }
        int expResult = 1;
        assertEquals(expResult, userService.getUser(defaultUser.getId()).getFollowing().size());
    }
    
    @Test
    public void testSendKweet() {
        System.out.println("sendKweet");
        final int testKweets = 10;
        for (int i = 0; i < testKweets; i++) {
            userService.sendKweet(defaultUser, String.format("Kweet%02d", i));
        }
        int expResult = testKweets;
        assertEquals(expResult, userService.getUser(defaultUser.getId()).getKweetCount());
    }
    
    @Test
    public void testGetFollowingKweets() {
        System.out.println("getFollowingKweets");
        userService.createUser(new Account("zxcv"));
        for (Account u : userService.findUsers("zxcv")) {
            if (u.getUserName().equals("zxcv")) {
                userService.followUser(defaultUser, u);
                userService.sendKweet(u, "asdfqwer");
            }
        }
        int expResult = 1;
        assertEquals(expResult, userService.getFollowingKweets(defaultUser).size());
    }*/
}
