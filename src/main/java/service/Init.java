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
        Account a2 = new Account("mod", "mod", "mod@kwetter.com", g1);
        Account a3 = new Account("user", "user", "user@kwetter.com", g1);
        
        a1.addUserGroup(g2);
        a1.addUserGroup(g3);
        a2.addUserGroup(g2);
        
        userGroupService.createGroup(g1);
        userGroupService.createGroup(g2);
        userGroupService.createGroup(g3);
        
        accountService.createAccount(a1);
        accountService.createAccount(a2);
        accountService.createAccount(a3);
    }
}
