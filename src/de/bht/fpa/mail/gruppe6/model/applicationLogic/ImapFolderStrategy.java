/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bht.fpa.mail.gruppe6.model.applicationLogic;

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
                String ff = f.getPath();
                javax.mail.Folder folder = store.getFolder(ff);
                File file = new File(f.getPath());
                System.out.println(f.getPath());
                if(file.listFiles()!=null){
                    for(File fs : file.listFiles()){
                       // folder.addComponent(new Folder(fs, true));
                    }
                }
            } catch (MessagingException ex) {
                Logger.getLogger(ImapFolderStrategy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
//            File file = new File(f.getPath());
//            System.out.println(f.getPath());
//            if (file.listFiles() != null) {
//                for (File fs : file.listFiles()) {
//                    if (fs.isDirectory()) {
//                        f.addComponent(new Folder(fs, hasSubFolder(fs)));
}
