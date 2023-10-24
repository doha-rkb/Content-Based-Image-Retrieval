package javafxgridview.cis;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class FileChooser {
    JFileChooser choose;

    public FileChooser()
    {
        this.choose = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    }
    //cette fonction permet de recuperer le path de l image requete.
    public String get_path(){
        int res = this.choose.showOpenDialog(null);
        // Enregistrez le fichier
        // int res = choose.showSaveDialog(null);
        if (res == JFileChooser.APPROVE_OPTION) {
            File file = choose.getSelectedFile();
            return file.getAbsolutePath();
        }
        return "";
    }
}