package dal;

import domain.Kweet;
import domain.Account;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Robin
 */
public class AccountDaoMem implements IAccountDao {
    private Map<Long, Account> accounts;
    
    public AccountDaoMem() {
        accounts = new HashMap();
    }
    
    @Override
    public void createAccount(Account account) {
        Long id = new Long(accounts.size());
        account.setId(id);
        accounts.putIfAbsent(id, account);
    }

    @Override
    public void updateAccount(Account account) {
        if (accounts.containsKey(account.getId())) {
            accounts.put(account.getId(), account);
        }
        throw new IllegalArgumentException(String.format("User with ID %d does not exist.", account.getId()));
    }

    @Override
    public void removeAccount(Account account) {
        accounts.remove(account.getId());
    }
    
    @Override
    public Account getAccount(Long id) {
        return accounts.get(id);
    }

    @Override
    public Account getAccountByUserName(String name) {
        for (Map.Entry<Long, Account> entry : accounts.entrySet()) {
            if (entry.getValue().getUserName().equals(name)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public List<Account> getAccountsByUserName(String partialName) {
        List<Account> accountList = new ArrayList();
        for (Map.Entry<Long, Account> entry : accounts.entrySet()) {
            if (entry.getValue().getUserName().toLowerCase().contains(partialName.toLowerCase())) {
                accountList.add(entry.getValue());
            }
        }
        return accountList;
    }
}
