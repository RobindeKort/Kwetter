package service;

import dal.IUserGroupDao;
import domain.UserGroup;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Robin
 */
@Stateless
public class UserGroupService {
    
    @Inject
    private IUserGroupDao userGroupDao;

    public UserGroupService() {
        // nothing
    }

    public void createGroup(UserGroup group) throws IllegalArgumentException {
        UserGroup exists = userGroupDao.getGroup(group.getGroupName());
        if (exists == null) {
            userGroupDao.createGroup(group);
        } else {
            throw new IllegalArgumentException(String.format("Group with name %s already exists.", group.getGroupName()));
        }
    }

    public void removeGroup(UserGroup group) {
        userGroupDao.removeGroup(group);
    }

    public UserGroup getAccount(String id) {
        return userGroupDao.getGroup(id);
    }
    
    public List<UserGroup> getAllGroups() {
        return userGroupDao.getAllGroups();
    }
}
