package de.bht.fpa.mail.gruppe6.model.applicationLogic;

import de.bht.fpa.mail.gruppe6.controller.AppController;
import de.bht.fpa.mail.gruppe6.model.applicationLogic.imap.ImapEmailStrategy;
import de.bht.fpa.mail.gruppe6.model.applicationLogic.imap.ImapFolderStrategy;
import de.bht.fpa.mail.gruppe6.model.applicationLogic.xml.FileStrategy;
import de.bht.fpa.mail.gruppe6.model.applicationLogic.xml.XmlEmailStrategy;
import de.bht.fpa.mail.gruppe6.model.data.Account;
import de.bht.fpa.mail.gruppe6.model.data.Email;
import de.bht.fpa.mail.gruppe6.model.data.Folder;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ApplicationLogic implements ApplicationLogicIF {

    private FolderManager folder;
    private EmailManager mails;
    private AccountManagerIF acc;
    private static File startDirectory = new File(System.getProperty("user.home"));

    public ApplicationLogic() {
        acc = new AccountManager();
        mails = new EmailManager();
        folder = new FolderManager(startDirectory);
    }

    @Override
    public Folder getTopFolder() {
        return folder.getTopFolder();
    }

    @Override
    public void loadContent(Folder newfolder) {
        folder.loadContent(newfolder);
    }

    @Override
    public List<Email> search(String pattern) {
        return mails.search(pattern);
    }

    @Override
    public void loadEmails(Folder folder) {
        mails.loadEmails(folder);
    }

    @Override
    public void changeDirectory(File file) {
        if (file != null) {
            mails.setEmailStrategy(new XmlEmailStrategy());
            folder = new FolderManager(file);
        }
    }

    @Override
    public void saveEmails(File file) {
        mails.saveEmails(file);
    }

    @Override
    public List<String> getAllAccounts() {
        ArrayList<String> accnamen = new ArrayList<String>();
        for (Account x : acc.getAllAccounts()) {
            accnamen.add(x.getName());
        }
        return accnamen;
    }

    @Override
    public Account getAccount(String name) {
        return acc.getAccount(name);
    }

    @Override
    public boolean saveAccount(Account account) {
        if (acc.getAccount(account.getName()) == null) {
            acc.saveAccount(account);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void updateAccount(Account account) {
        acc.updateAccount(account);
    }

    @Override
    public void openAccount(String name) {
        Account account = getAccount(name);
        folder.setTopFolder(account.getTop());
        Folder topFolder = getTopFolder();
        File file = new File(topFolder.getPath());
        if (!file.exists()) {
            mails.setEmailStrategy(new ImapEmailStrategy(account));
            folder.setFolderStrategy(new ImapFolderStrategy(account));
        }
        else{
        mails.setEmailStrategy(new XmlEmailStrategy());
        folder.setFolderStrategy(new FileStrategy() );
        }
        folder.loadContent(getTopFolder());
    }
}
