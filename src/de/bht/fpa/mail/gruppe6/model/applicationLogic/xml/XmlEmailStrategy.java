/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bht.fpa.mail.gruppe6.model.applicationLogic.xml;

import de.bht.fpa.mail.gruppe6.model.applicationLogic.EmailStrategy;
import de.bht.fpa.mail.gruppe6.model.data.Email;
import de.bht.fpa.mail.gruppe6.model.data.Folder;
import java.io.File;
import java.io.FileFilter;
import javax.xml.bind.JAXB;

/**
 *
 * @author Nessi
 */
public class XmlEmailStrategy implements EmailStrategy{

    public XmlEmailStrategy() {
    }
    @Override
    public void loadEmails(Folder f) {
        if (f != null && f.getEmails().isEmpty() && f.getPath().length() > 0) {
            f.setLoaded();
            File file = new File(f.getPath());
            FileFilter filter = (File name) -> name.getName().endsWith(".xml");
            for (File x : file.listFiles(filter)) {
                Email email = JAXB.unmarshal(x, Email.class);
                if (!email.toString().contains("false")) {
                    f.addEmail(email);
                }
            }
        }
    }
}
