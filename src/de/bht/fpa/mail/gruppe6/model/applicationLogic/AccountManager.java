/*
 * This is the class that manages
 * accounts.
 * 
 * @author Simone Strippgen
 */
public class AccountManager {

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
       // hier kommt Ihr Code hinein
    }

    /**
     * @return a list of all account names.
     */
    public List<Account> getAllAccounts() {   
        // hier kommt Ihr Code hinein
    }

    /**
     * Saves the given Account in the data store, if an account
     * with the given name does not exist.
     * @param account  the account that should be saved
     */
    public void saveAccount(Account acc) {
       // hier kommt Ihr Code hinein
    }

    /**
     * Updates the given Account in the data store.
     * @param account  the account that should be updated
     * @return true if update was successful.
     */
    public boolean updateAccount(Account account) {
       // hier kommt Ihr Code hinein
    }
}
