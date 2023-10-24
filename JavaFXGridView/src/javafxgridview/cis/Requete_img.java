package javafxgridview.cis;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Requete_img {
    BufferedImage img_req;
    public Requete_img(String path) throws IOException {
        img_req= ImageIO.read(new File(path));
    }
}
