package de.bht.fpa.mail.gruppe6.model.applicationLogic.xml;

import de.bht.fpa.mail.gruppe6.model.data.Folder;
import java.io.File;
import de.bht.fpa.mail.gruppe6.model.applicationLogic.FolderStrategyIF;

public class FileStrategy implements FolderStrategyIF {

    public FileStrategy() {
    }

    @Override
    public void loadContent(Folder f) {
        if (f != null) {
            File file = new File(f.getPath());
            if (file.listFiles() != null) {
                for (File fs : file.listFiles()) {
                    if (fs.isDirectory()) {
                        f.addComponent(new Folder(fs, hasSubFolder(fs)));
                    }
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
