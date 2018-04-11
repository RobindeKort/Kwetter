package dal;

import domain.UserGroup;
import java.util.List;

/**
 *
 * @author Robin
 */
public interface IUserGroupDao {
    void createGroup(UserGroup group);
    void updateGroup(UserGroup group);
    void removeGroup(UserGroup group);
    UserGroup getGroup(String id);
    List<UserGroup> getAllGroups();
}
