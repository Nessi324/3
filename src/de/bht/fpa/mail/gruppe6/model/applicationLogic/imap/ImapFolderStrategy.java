/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bht.fpa.mail.gruppe6.model.applicationLogic.imap;

import de.bht.fpa.mail.gruppe6.model.applicationLogic.FolderStrategyIF;
import de.bht.fpa.mail.gruppe6.model.applicationLogic.imap.IMapConnectionHelper;
import de.bht.fpa.mail.gruppe6.model.data.Account;
import de.bht.fpa.mail.gruppe6.model.data.Folder;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.Store;

/**
 *
 * @author Nessi
 */
public class ImapFolderStrategy implements FolderStrategyIF {

    private Account account;
    private Store store;
    private String accountName;

    public ImapFolderStrategy(Account account) {
        this.account = account;
        store = IMapConnectionHelper.connect(account);
        accountName = account.getName();
    }

    @Override
    public void loadContent(Folder f) {
        if (!f.getComponents().isEmpty()) {
            return;
        }
        try {
            javax.mail.Folder topfolder = store.getDefaultFolder();
            if (!f.getName().contains(accountName)) {
                topfolder = store.getFolder(f.getName());
            }
            for (javax.mail.Folder folder : topfolder.list()) {
                if (folder != null && folder.getName().length() > 0) {
                    if (folder.getName().contains("Gmail")) {
                        for (javax.mail.Folder x : folder.list()) {
                            Folder subFolder = new Folder(new File(x.getFullName()), x.list().length > 0);
                            subFolder.setPath(x.getFullName());
                            f.addComponent(subFolder);
                        }
                    }
                    else {
                        Folder subFolder = new Folder(new File(folder.getFullName()), folder.list().length > 0);
                        subFolder.setPath(folder.getFullName());
                        f.addComponent(subFolder);
                    }
                }
            }
        } catch (MessagingException ex) {
            Logger.getLogger(ImapFolderStrategy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
