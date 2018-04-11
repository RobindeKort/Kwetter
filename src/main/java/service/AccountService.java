package service;

import dal.*;
import domain.*;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Robin
 */
@Stateless
public class AccountService {
    
    @Inject
//    private UserDaoJpa userDao;
    private IAccountDao accountDao;
    @Inject
    public KweetService kweetService;

    public AccountService() {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Kwet_JPA");
//        userDao = new UserDaoJpa(emf.createEntityManager());
//        kweetService = new KweetService(new KweetDaoJpa(emf.createEntityManager()));
    }

    // TODO - Replace with interface
//    protected AccountService(UserDaoJpa userDao, KweetDaoJpa kweetDao) {
//        this.userDao = userDao;
//        this.kweetService = new KweetService(kweetDao);
//    }

    public void createAccount(Account account) throws IllegalArgumentException {
        accountDao.createAccount(account);
    }

    public void updateAccount(Account account) throws IllegalArgumentException {
        Account exists = accountDao.getAccount(account.getId());
        if (exists == null) {
            throw new IllegalArgumentException(String.format("Account with ID %d does not exist.", account.getId()));
        }
        accountDao.updateAccount(account);
    }

    public void removeAccount(Account account) {
        accountDao.removeAccount(account);
    }

    public Account getAccount(Long id) {
        return accountDao.getAccount(id);
    }

    public Account getAccount(String userName) {
        return accountDao.getAccountByUserName(userName);
    }

    public List<Account> findAccounts(String partialName) {
        return Collections.unmodifiableList(accountDao.getAccountsByUserName(partialName));
    }

    /**
     * Make user1 follow user2, will automatically persist changes with the DAO.
     *
     * @param account1 Account who is going to follow user2
     * @param account2 Account who is going to be followed by user1
     */
    public void followAccount(Account account1, Account account2) {
        account1.follow(account2);
        account2.addFollowedBy(account1);
        updateAccount(account1);
        updateAccount(account2);
    }

    public void sendKweet(Account account, String kweetBody) {
        Kweet kweet = new Kweet(account, kweetBody);
        kweetService.createKweet(kweet);
        // TODO - Check if kweet has been given an ID before adding it to user. 
        account.addKweet(kweet);
        updateAccount(account);
    }

    public Queue<Kweet> getFollowingKweets(Account account) {
        Queue<Kweet> kweets = new PriorityQueue();
        for (Account a : account.getFollowing()) {
            kweets.addAll(a.getKweets());
        }
        return kweets;
    }
}
