package de.bht.fpa.mail.gruppe6.model.applicationLogic;


import de.bht.fpa.mail.gruppe6.model.data.Account;
import de.bht.fpa.mail.gruppe6.model.data.Email;
import de.bht.fpa.mail.gruppe6.model.data.Folder;
import java.io.File;
import java.util.List;

public interface ApplicationLogicIF {

    Folder getTopFolder();

    void loadContent(Folder folder);

    List<Email> search(String pattern);

    void loadEmails(Folder folder);

    void changeDirectory(File file);

    void saveEmails(File file);

    /**
     * Sets a selected account as the new working account, and initializes the
     * folder manager with the top Folder of the account.
     *
     * @param name name of the account which should be set as the current
     * working account.
     */
    void openAccount(String name);

    /**
     * @return a list of all account names.
     */
    List<String> getAllAccounts();

    /**
     * @return account with the given name. If no account with this name exists,
     * it returns null.
     * @param name name of the account
     */
    Account getAccount(String name);

    /**
     * Saves the given Account in the datastore.
     *
     * @param account the account that should be saved
     * @return true if an account with this name did not exist.
     */
    boolean saveAccount(Account account);

    /**
     * Updates the given Account in the datastore.
     *
     * @param account the account that should be updated
     */
    void updateAccount(Account account);
}
