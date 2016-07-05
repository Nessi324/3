package de.bht.fpa.mail.gruppe6.model.applicationLogic;

import de.bht.fpa.mail.gruppe6.model.applicationLogic.account.AccountDAOIF;
import de.bht.fpa.mail.gruppe6.model.applicationLogic.account.AccountDBDAO;
import de.bht.fpa.mail.gruppe6.model.applicationLogic.account.AccountFileDAO;
import java.util.List;
import de.bht.fpa.mail.gruppe6.model.data.Account;

public class AccountManager implements AccountManagerIF {

    private AccountDAOIF accountDB;
    private List<Account> accountList;

    public AccountManager() {
        accountDB = new AccountDBDAO();
        //accountDB = new AccountFileDAO();
        accountList = accountDB.getAllAccounts();
    }

    /**
     * Returns the account with the given name.
     *
     * @return null If no account with this name exists.
     * @param name name of the account
     */
    @Override
    public Account getAccount(String name) {
        for (Account x : accountDB.getAllAccounts()) {
            if (x.getName().equals(name)) {
                return x;
            }
        }
        return null;
    }

    /**
     * @return a list of all account names.
     */
    @Override
    public List<Account> getAllAccounts() {
        return accountList;
    }

    /**
     * Saves the given Account in the data store, if an account with the given
     * name does not exist.
     *
     * @param account the account that should be saved
     */
    @Override
    public void saveAccount(Account acc) {
        if (!accountList.contains(acc)) {
            accountDB.saveAccount(acc);
        }
    }

    /**
     * Updates the given Account in the data store.
     *
     * @param account the account that should be updated
     * @return true if update was successful.
     */
    @Override
    public boolean updateAccount(Account account) {
        return accountDB.updateAccount(account);
    }
}
