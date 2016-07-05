package de.bht.fpa.mail.gruppe6.model.applicationLogic;

import de.bht.fpa.mail.gruppe6.model.applicationLogic.xml.FileStrategy;
import de.bht.fpa.mail.gruppe6.model.data.Folder;
import java.io.File;

public class FolderManager {

    private Folder folder;
    public FolderStrategyIF strategy;
    
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

    public void setTopFolder(Folder folder) {
        this.folder = folder;
    }
    
    public void setFolderStrategy(FolderStrategyIF strategy){
    this.strategy = strategy;
    }
}
