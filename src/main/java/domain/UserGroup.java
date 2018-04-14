package domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Robin
 */
@Entity
@Table
@NamedQueries({
    @NamedQuery(name = "UserGroup.getAll",
            query = "SELECT g FROM UserGroup g")
})
public class UserGroup implements Serializable, Comparable<UserGroup> {

    @Id
    private String groupName;
    @ManyToMany//(fetch = FetchType.LAZY)
    @JoinTable(name = "account_group",
            joinColumns = @JoinColumn(name = "groupName", referencedColumnName = "groupName"),
            inverseJoinColumns = @JoinColumn(name = "userName", referencedColumnName = "userName"))
    private Set<Account> accounts;

    public UserGroup() {
        accounts = new HashSet();
    }

    public UserGroup(String groupName) {
        this.groupName = groupName;
        accounts = new HashSet();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<Account> getAccounts() {
        return Collections.unmodifiableSet(accounts);
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }
    
    public void removeAccount(Account account) {
        this.accounts.remove(account);
    }

    @Override
    public int compareTo(UserGroup other) {
        if (this.groupName.equals(other.groupName)) {
            return 0;
        } // User is the lowest possible group
        else if (this.groupName.equals("User")
                || (this.groupName.equals("Moderator") && other.groupName.equals("Administrator"))) {
            return -1;
        } // Administrator is highest possible group
        else {
            return 1;
        }
        /*else if (this.groupName.equals("Administrator") ||
                (this.groupName.equals("Moderator") && other.groupName.equals("Account"))) {
            return 1;
        }*/
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof UserGroup)) {
            return false;
        }
        UserGroup other = (UserGroup)obj;
        return (this.compareTo(other) == 0);
    }
    
    @Override
    public String toString() {
        return groupName;
    }
}
