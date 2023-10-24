package javafxgridview.cis;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageDescriptors {

    static int[] rot = new int[256];
    static {
        for (int i = 0; i < 256; i++) {
            String s = Integer.toBinaryString(i);
            for (; s.length() < 8;) {
                s = "0" + s;
            }
            String min = new String(s);
            for (int j = 0; j < 8; j++) {
                s = s.charAt(7) + s.substring(0, 7);
                if (min.compareTo(s) == 1) {
                    min = new String(s);
                }
            }
            rot[i] = Integer.valueOf(min, 2);
        }
    }

    public static int[] lbph(int[][] img) {

        final int[] histogram = new int[256];

        final int[] dx = { -1, -1, -1, 0, 1, 1, 1, 0 };
        final int[] dy = { -1, 0, 1, 1, 1, 0, -1, -1 };

        for (int i = 1; i < img.length - 1; i++) {
            for (int j = 1; j < img[0].length - 1; j++) {

                int pow = 1;
                int pattern = 0;
                final int center = img[i][j];

                for (int k = 0; k < dx.length; k++) {
                    if (center > img[i + dx[k]][j + dy[k]]) {
                        pattern += pow;
                    }
                    pow *= 2;
                }
                histogram[pattern]++;
            }
        }
        return histogram;

    }

    public static int[] lbp(BufferedImage img) {
        return lbph(ImageUtils.imagetoGreycale2dArray(img));
    }

    /**

     * https://en.wikipedia.org/wiki/Color_histogram
     *
     *
     */
    static int[] histCol(BufferedImage img, int bin) {
        final int[][][] hist = new int[bin][bin][bin];
        final int div = (int) Math.ceil(256d / bin);
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                Color c = new Color(img.getRGB(j, i));
                hist[c.getRed() / div][c.getGreen() / div][c.getBlue() / div]++;
            }
        }
        return (outilsTab.flatten(hist)); //transformer un tableau de deux dimention en un autre d'un seul dimension
    }


    static float[] scaleDownGrey(BufferedImage img1, int sz) {
        int[] f = new int[sz * sz * 3];
        BufferedImage img = ImageUtils
                .BufferedImageConv(img1.getScaledInstance(sz, sz, Image.SCALE_SMOOTH)); // get scaledInstance  utiliser pour redimentionner la taille de l'image
        for (int i = 0; i < sz; i++) {
            for (int j = 0; j < sz; j++) {
                Color c = new Color(img.getRGB(j, i));

                f[j * sz * 3 + i] = c.getRed();
                f[j * sz * 3 + i + 1] = c.getBlue();
                f[j * sz * 3 + i + 2] = c.getGreen();
            }
        }
        return MathFunctions.normaliser(f);
    }

    static float[] computeImageDescriptor_lbp(BufferedImage img, boolean useLbp) {
        float[] desc = new float[0];
        desc = MathFunctions.normaliser(lbp(img));
        return desc;
    }

    static float[] computeImageDescriptor_color(BufferedImage img, boolean useColor,int binsPerColor) {
        float[] desc = new float[0];
        if (useColor) {
            desc =  MathFunctions.normaliser(histCol(img, binsPerColor));
        }
        return MathFunctions.normaliser(desc);
    }


}

