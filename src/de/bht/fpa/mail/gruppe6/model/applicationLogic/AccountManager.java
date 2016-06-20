package de.bht.fpa.mail.gruppe6.model.applicationLogic;

import java.util.List;
import de.bht.fpa.mail.gruppe6.model.data.Account;


public class AccountManager implements AccountDAOIF {

    private AccountDAOIF accountDB;
    private List<Account> accountList;


    public AccountManager() {
	// hier kommt Ihr Code hinein
    }

    /**
     * Returns the account with the given name.
     * @return null If no account with this name exists.
     * @param name  name of the account 
     */
    public Account getAccount(String name) {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    /**
     * @return a list of all account names.
     */
    public List<Account> getAllAccounts() {   

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    /**
     * Saves the given Account in the data store, if an account
     * with the given name does not exist.
     * @param account  the account that should be saved
     */
    public Account saveAccount(Account acc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }

    /**
     * Updates the given Account in the data store.
     * @param account  the account that should be updated
     * @return true if update was successful.
     */
    public boolean updateAccount(Account account) {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
}
