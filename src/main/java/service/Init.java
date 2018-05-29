package service;

import domain.*;
import java.util.GregorianCalendar;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import org.apache.commons.codec.digest.DigestUtils;
import service.KweetService;
import service.AccountService;

/**
 *
 * @author Robin
 */
@Startup
@Singleton
public class Init {

    @Inject
    AccountService accountService;

    @Inject
    KweetService kweetService;
    
    @Inject
    UserGroupService userGroupService;

    @PostConstruct
    public void init() {
        UserGroup g1 = new UserGroup("User");
        UserGroup g2 = new UserGroup("Moderator");
        UserGroup g3 = new UserGroup("Administrator");
        
        Account a1 = new Account("admin", "admin", "admin@kwetter.com", g1);
        Account m1 = new Account("mod", "mod", "mod@kwetter.com", g1);
        Account u1 = new Account("user1", "user", "user1@kwetter.com", g1);
        Account u2 = new Account("user2", "user", "user2@kwetter.com", g1);
        Account u3 = new Account("user3", "user", "user3@kwetter.com", g1);
        Account u4 = new Account("user4", "user", "user4@kwetter.com", g1);
        Account u5 = new Account("user5", "user", "user5@kwetter.com", g1);
        Account u6 = new Account("user6", "user", "user6@kwetter.com", g1);
        Account u7 = new Account("user7", "user", "user7@kwetter.com", g1);
        Account u8 = new Account("user8", "user", "user8@kwetter.com", g1);
        Account u9 = new Account("user9", "user", "user9@kwetter.com", g1);
        Account u10 = new Account("user10", "user", "user10@kwetter.com", g1);
        
        a1.addUserGroup(g2);
        a1.addUserGroup(g3);
        m1.addUserGroup(g2);
        
        a1.addFollowing(m1);
        a1.addFollowing(u1);
        m1.addFollowing(a1);
        u1.addFollowing(a1);
        u2.addFollowing(a1);
        
        userGroupService.createGroup(g1);
        userGroupService.createGroup(g2);
        userGroupService.createGroup(g3);
        
        accountService.createAccount(a1);
        accountService.createAccount(m1);
        accountService.createAccount(u1);
        accountService.createAccount(u2);
        accountService.createAccount(u3);
        accountService.createAccount(u4);
        accountService.createAccount(u5);
        accountService.createAccount(u6);
        accountService.createAccount(u7);
        accountService.createAccount(u8);
        accountService.createAccount(u9);
        accountService.createAccount(u10);
        
        Kweet a1k1 = new Kweet(a1, "This is admin's first test Kweet", new GregorianCalendar(2018, 3, 14, 14, 21, 52));
        Kweet a1k2 = new Kweet(a1, "This is admin's second test Kweet", new GregorianCalendar(2018, 2, 9, 8, 51, 28));
        Kweet a1k3 = new Kweet(a1, "This is admin's third test Kweet", new GregorianCalendar(2018, 4, 22, 12, 53, 12));
        a1.addKweet(a1k1);
        a1.addKweet(a1k2);
        a1.addKweet(a1k3);
        
        Kweet m1k1 = new Kweet(m1, "This is mod's first test Kweet", new GregorianCalendar(2018, 2, 12, 23, 29, 5));
        Kweet m1k2 = new Kweet(m1, "This is mod's second test Kweet", new GregorianCalendar(2018, 3, 2, 18, 59, 2));
        Kweet m1k3 = new Kweet(m1, "This is mod's third test Kweet", new GregorianCalendar(2018, 5, 20, 18, 28, 12));
        m1.addKweet(m1k1);
        m1.addKweet(m1k2);
        m1.addKweet(m1k3);
        
        Kweet u1k1 = new Kweet(u1, "This is user1's first test Kweet", new GregorianCalendar(2018, 3, 12, 21, 42, 31));
        Kweet u1k2 = new Kweet(u1, "This is user1's second test Kweet", new GregorianCalendar(2017, 11, 12, 20, 27, 30));
        Kweet u1k3 = new Kweet(u1, "This is user1's third test Kweet", new GregorianCalendar(2018, 2, 1, 5, 3, 1));
        u1.addKweet(u1k1);
        u1.addKweet(u1k2);
        u1.addKweet(u1k3);
        
        kweetService.createKweet(a1k1);
        kweetService.createKweet(a1k2);
        kweetService.createKweet(a1k3);
        accountService.updateAccount(a1);
        kweetService.createKweet(m1k1);
        kweetService.createKweet(m1k2);
        kweetService.createKweet(m1k3);
        accountService.updateAccount(m1);
        kweetService.createKweet(u1k1);
        kweetService.createKweet(u1k2);
        kweetService.createKweet(u1k3);
        accountService.updateAccount(u1);
    }
}
