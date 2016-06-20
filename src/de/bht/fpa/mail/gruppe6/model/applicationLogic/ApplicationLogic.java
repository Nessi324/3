package de.bht.fpa.mail.gruppe6.model.applicationLogic;

import de.bht.fpa.mail.gruppe6.model.data.Account;
import de.bht.fpa.mail.gruppe6.model.data.Email;
import de.bht.fpa.mail.gruppe6.model.data.Folder;
import java.io.File;
import java.util.List;

public class ApplicationLogic implements ApplicationLogicIF {

    private FolderManager folder;
    private EmailManager mails;
    private static File startDirectory = new File(System.getProperty("user.home"));

    public ApplicationLogic() {
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
            folder = new FolderManager(file);
        }
    }

    @Override
    public void saveEmails(File file) {
        mails.saveEmails(file);
    }

    @Override
    public void openAccount(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getAllAccounts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Account getAccount(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveAccount(Account account) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateAccount(Account account) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
