package dal;

import domain.Kweet;
import domain.Account;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Robin
 */
public interface IAccountDao {
    void createAccount(Account account);
    void updateAccount(Account account);
    void removeAccount(Account account);
    Account getAccount(Long id);
    Account getAccountByUserName(String name);
    List<Account> getAccountsByUserName(String partialName);
    //Set<User> getFollowers(Long id);
    //Set<Kweet> getFollowingKweets(Account user, int amount);
}
