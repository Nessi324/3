package de.bht.fpa.mail.gruppe6.model.applicationLogic;

import de.bht.fpa.mail.gruppe6.model.applicationLogic.xml.FileStrategy;
import de.bht.fpa.mail.gruppe6.model.data.Folder;
import java.io.File;

public class FolderManager {

    private Folder folder;
    public FolderStrategy strategy;
    
    public FolderManager(File file) {
        strategy = new FileStrategy();
        if (file != null) {
            folder = new Folder(file, true);
            strategy.loadContent(folder);
        }
    }

    public void loadContent(Folder f) {
    strategy.loadContent(f);
    }


    public Folder getTopFolder() {
        return folder;
    }
    
    public void setFolderStrategy(FolderStrategy strategy){
    this.strategy = strategy;
    }
}
