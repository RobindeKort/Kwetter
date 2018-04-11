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

    public void createGroup(UserGroup group) {
        userGroupDao.createGroup(group);
    }

    public void updateGroup(UserGroup group) {
        UserGroup exists = userGroupDao.getGroup(group.getGroupName());
        if (exists == null) {
            throw new IllegalArgumentException(String.format("Group with Name %s does not exist.", group.getGroupName()));
        }
        userGroupDao.updateGroup(group);
    }

    public void removeGroup(UserGroup group) {
        userGroupDao.removeGroup(group);
    }

    public UserGroup getGroup(String id) {
        return userGroupDao.getGroup(id);
    }

    public UserGroup getAccount(String id) {
        return userGroupDao.getGroup(id);
    }

    public List<UserGroup> getAllGroups() {
        return userGroupDao.getAllGroups();
    }
}
