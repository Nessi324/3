package de.bht.fpa.mail.gruppe6.model.applicationLogic.imap;

import de.bht.fpa.mail.gruppe6.model.applicationLogic.EmailStrategyIF;
import de.bht.fpa.mail.gruppe6.model.data.Account;
import de.bht.fpa.mail.gruppe6.model.data.Folder;

/**
 *
 * @author Nessi
 */
public class ImapEmailStrategy implements EmailStrategyIF {

    Account acc;

    public ImapEmailStrategy(Account acc) {
        this.acc = acc;

    }

    @Override
    public void loadEmails(Folder f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
