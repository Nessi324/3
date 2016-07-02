package de.bht.fpa.mail.gruppe6.model.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Folder extends Component {

    @Transient
    private ArrayList<Email> emails;
    @Transient
    private final ArrayList<Component> content = new ArrayList<Component>();

    private boolean expandable;
    @Transient
    private boolean loaded;

    public Folder(File path, boolean expandable) {
        super(path);
        emails = new ArrayList<Email>();
        this.expandable = expandable;
    }

    public Folder() {
    }

    @Override
    public void addComponent(Component comp) {
        content.add(comp);
    }

    @Override
    public List<Component> getComponents() {
        return content;
    }

    @Override
    public boolean isExpandable() {
        return expandable;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void addEmail(Email message) {
        emails.add(message);
    }

    public boolean getLoaded() {
        return loaded;
    }

    public void setLoaded() {
        loaded = true;
    }

    @Override
    public String toString() {
        if (getLoaded()) {
            return getName() + " [" + getEmails().size() + "]";
        }
        return getName();
    }
    
}
