package service;

import domain.*;
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
        
        Account a1 = new Account("admin", DigestUtils.sha256Hex("admin"), "admin@kwetter.com", g1);
        Account m1 = new Account("mod", DigestUtils.sha256Hex("mod"), "mod@kwetter.com", g1);
        Account u1 = new Account("user1", DigestUtils.sha256Hex("user"), "user1@kwetter.com", g1);
        Account u2 = new Account("user2", DigestUtils.sha256Hex("user"), "user2@kwetter.com", g1);
        Account u3 = new Account("user3", DigestUtils.sha256Hex("user"), "user3@kwetter.com", g1);
        Account u4 = new Account("user4", DigestUtils.sha256Hex("user"), "user4@kwetter.com", g1);
        Account u5 = new Account("user5", DigestUtils.sha256Hex("user"), "user5@kwetter.com", g1);
        Account u6 = new Account("user6", DigestUtils.sha256Hex("user"), "user6@kwetter.com", g1);
        Account u7 = new Account("user7", DigestUtils.sha256Hex("user"), "user7@kwetter.com", g1);
        Account u8 = new Account("user8", DigestUtils.sha256Hex("user"), "user8@kwetter.com", g1);
        Account u9 = new Account("user9", DigestUtils.sha256Hex("user"), "user9@kwetter.com", g1);
        Account u10 = new Account("user10", DigestUtils.sha256Hex("user"), "user10@kwetter.com", g1);
        
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
    }
}
