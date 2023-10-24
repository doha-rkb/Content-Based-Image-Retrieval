package javafxgridview.cis;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtils {

    static int[][] imagetoGreycale2dArray(BufferedImage img) {
        final int[][] tmp = new int[img.getHeight()][img.getWidth()];

        for (int i = 0; i < tmp.length; i++)
            for (int j = 0; j < tmp[0].length; j++) {
                final Color color = new Color(img.getRGB(j, i));
                tmp[i][j] = colorToGreyscale(color);
            }
        return tmp;
    }


    static final int colorToGreyscale(final Color c) {

        return (int) (0.2989 * c.getRed() + 0.5870 * c.getGreen() + 0.1140 * c.getBlue());
    }

    public static BufferedImage BufferedImageConv(Image image) {

        BufferedImage newImage = new BufferedImage(image.getWidth(null), image.getHeight(null),BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;

    }
}
