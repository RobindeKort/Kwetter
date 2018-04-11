package dal;

import domain.Kweet;
import domain.Account;
import domain.UserGroup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Robin
 */
public class UserGroupDaoMem implements IUserGroupDao {
    private Map<String, UserGroup> groups;
    
    public UserGroupDaoMem() {
        groups = new HashMap();
    }
    
    @Override
    public void createGroup(UserGroup group) {
        groups.putIfAbsent(group.getGroupName(), group);
    }

    @Override
    public void removeGroup(UserGroup group) {
        groups.remove(group.getGroupName());
    }
    
    @Override
    public UserGroup getGroup(String id) {
        return groups.get(id);
    }

    @Override
    public List<UserGroup> getAllGroups() {
        return Collections.unmodifiableList(new ArrayList(groups.values()));
    }
}
