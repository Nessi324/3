package de.bht.fpa.mail.gruppe6.model.applicationLogic.imap;

import de.bht.fpa.mail.gruppe6.model.applicationLogic.FolderStrategyIF;
import de.bht.fpa.mail.gruppe6.model.data.Account;
import de.bht.fpa.mail.gruppe6.model.data.Folder;

/**
 *
 * @author Nessi
 */
public class ImapFolderStrategy implements FolderStrategyIF {

    Account acc;

    public ImapFolderStrategy(Account acc) {
        this.acc = acc;
    }

    @Override
    public void loadContent(Folder f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
