package bean;

import domain.Account;
import domain.UserGroup;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import service.AccountService;
import service.UserGroupService;

/**
 *
 * @author Robin
 */
@ManagedBean
@RequestScoped
public class GroupBean {
    
    private Account account;
    
    @Inject
    private UserGroupService userGroupService;
    
    @Inject
    private AccountService accountService;
    
    public GroupBean() {
        // nothing
    }
    
    public void promoteAccount(Account account) {
        UserGroup group = userGroupService.getGroup(account.promoteString());
        account.addUserGroup(group);
        userGroupService.updateGroup(group);
        accountService.updateAccount(account);
    }
    
    public void demoteAccount(Account account) {
        UserGroup group = userGroupService.getGroup(account.promoteString());
        account.removeUserGroup(group);
        userGroupService.updateGroup(group);
        accountService.updateAccount(account);
    }
}
