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

    public ImapFolderStrategy(Account account) {
        this.account = account;
        store = IMapConnectionHelper.connect(account);
    }

    @Override
    public void loadContent(Folder f) {
        if (!f.getComponents().isEmpty()) {
            return;
        }
        try {   javax.mail.Folder topfolder = store.getDefaultFolder();
                Folder topFolder = new Folder(new File(topfolder.getFullName()), true);
                topFolder.setPath(topfolder.getFullName());
                f.addComponent(topFolder);
                for (javax.mail.Folder folder : topfolder.list()) {
                    if (folder != null && folder.getFullName().length() > 0) {
                        javax.mail.Folder subfolder = store.getFolder(folder.getName());
                        if (subfolder != null && subfolder.getName().length() > 0) {
                            System.out.println("\n\n\n" + subfolder.getName() + "Genau HIER");
                            Folder subFolder = new Folder(new File(subfolder.getName()), subfolder.list().length > 0);
                            f.addComponent(subFolder);
                        }
                    }
                }
            }
        catch (MessagingException ex) {
            Logger.getLogger(ImapFolderStrategy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
