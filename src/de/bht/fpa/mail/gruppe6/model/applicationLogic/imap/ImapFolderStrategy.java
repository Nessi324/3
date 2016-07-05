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
        if (f != null) {
            try {
                javax.mail.Folder folder = store.getDefaultFolder();
                for (javax.mail.Folder x : folder.list()) {
                    if(x!=null) {
                        Folder newfolder = new Folder(new File(x.getName()), true);
                        newfolder.setPath(x.getFullName());
                        getTopFolder().addComponent(newfolder);
                        System.out.println(newfolder);
                    }
                }
            } catch (MessagingException ex) {
                Logger.getLogger(ImapFolderStrategy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Folder getTopFolder() {
        try {
            javax.mail.Folder topFolder = store.getFolder(account.getName());
            System.out.println(topFolder + " LOADCONTENT\n\n\n");
            Folder f = new Folder(new File(topFolder.getName()), true);
            f.setPath(topFolder.getFullName());
            return f;
        } catch (MessagingException ex) {
            Logger.getLogger(ImapFolderStrategy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
