package de.bht.fpa.mail.gruppe6.model.applicationLogic.xml;

import de.bht.fpa.mail.gruppe6.model.applicationLogic.FolderStrategy;
import de.bht.fpa.mail.gruppe6.model.data.Folder;
import java.io.File;

public class FileStrategy implements FolderStrategy{

    public FileStrategy() {
    }

    @Override
    public void loadContent(Folder f) {
        if (f != null ) {
            File file = new File(f.getPath());
            for (File fs : file.listFiles()) {
                if (fs.isDirectory()) {
                    f.addComponent(new Folder(fs, hasSubFolder(fs)));
                }
            }
        }
    }
    //fragen ob es erlaubt ist
    public boolean hasSubFolder(File file) {
        if (file.listFiles() != null) {
            for (File x : file.listFiles()) {
                if (x.isDirectory()) {
                    return true;
                }
            }
        }
        return false;
    }
}
