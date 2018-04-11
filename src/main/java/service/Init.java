package service;

import domain.*;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
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
    }
}
