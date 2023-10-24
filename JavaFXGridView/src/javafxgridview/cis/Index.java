package javafxgridview.cis;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Index implements Serializable {

    private static final long serialVersionUID = 1L;

    List<Image_indx> index;
    boolean LBP = false;
    boolean Color_u = false;
    int bin = 8;

    public Index(int bin, boolean LBP, boolean Color_u) {
        this.Color_u = Color_u;
        this.bin = bin;
        this.LBP = LBP;
        index = new ArrayList<>();
    }


    void saveToFile(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(this);
        out.close();
        fos.close();
    }

    public static Index extraireDesc(File file) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fis);
        Index index = (Index) in.readObject();
        in.close();
        fis.close();
        return index;
    }


    Image_indx createIndexedImage(File imageFile) {
        BufferedImage img;
        try {
            img = ImageIO.read(imageFile);
        } catch (IOException e) {
            return null;
        }

        float[] imageDescriptor_color = ImageDescriptors.computeImageDescriptor_color(img,Color_u, bin);
        float[] imageDescriptor_lbp = ImageDescriptors.computeImageDescriptor_lbp(img,LBP);
        Image_indx indexedImage = new Image_indx(imageFile, imageDescriptor_color, imageDescriptor_lbp);
        //IndexedImage
        return indexedImage;
    }


    public void addToIndex(String pathToImageFolder) throws ClassNotFoundException {

        long tic = System.currentTimeMillis();

        int count = 0;
        File[] listOfFiles = new File(pathToImageFolder).listFiles();

        for (File f : listOfFiles) {

            //System.out.println(count++ + "/" + listOfFiles.length + " images indexées");
            Image_indx ii = createIndexedImage(f);

            if (ii != null)
                index.add(ii);
            //	System.out.println(f.getName());
        }

        //System.out.println("index généré en : " + (System.currentTimeMillis() - tic) / 1000f + "secondes");
    }

    public List<File> Top(BufferedImage queryImg, int k) {
        long tic = System.currentTimeMillis();

        float[] imageQueryDescriptor_color = ImageDescriptors.computeImageDescriptor_color(queryImg,Color_u, bin);
        float[] imageQueryDescriptor_lbp = ImageDescriptors.computeImageDescriptor_lbp(queryImg,LBP);
        List<File> results = new ArrayList<>();
        /*
         * Precompute the similarity between the query and all the images in the
         * index.
         */
        float[] similarityCache = new float[index.size()];
        if(Color_u &&LBP) {
            for (int i = 0; i < index.size(); i++) {
                similarityCache[i] = (float) MathFunctions.simil(imageQueryDescriptor_color, index.get(i).imageDescriptor_color, imageQueryDescriptor_lbp, index.get(i).imageDescriptor_lbp);
            }
        }
        else if(Color_u==true && LBP==false) {
            for (int i = 0; i < index.size(); i++) {
                similarityCache[i] = MathFunctions.similarity(imageQueryDescriptor_color, index.get(i).imageDescriptor_color);
            }
        }
        else if(Color_u==false &&LBP==true) {
            for (int i = 0; i < index.size(); i++) {
                similarityCache[i] = MathFunctions.similarity(imageQueryDescriptor_lbp, index.get(i).imageDescriptor_lbp);
            }
        }

        for (int i = 0; i < k; i++) {
            // index of the minimum distance
            int argmax = outilsTab.argmax(similarityCache);

            File argmaxFile = index.get(argmax).file;
            float maxSimilarity = similarityCache[argmax];
            System.out.println(argmaxFile.getName() + " similarité: " + String.format("%.3f", maxSimilarity));
            results.add(argmaxFile);
            similarityCache[argmax] = Float.NEGATIVE_INFINITY;
        }

        System.out.println("temps de recherche : " + (System.currentTimeMillis() - tic) / 1000f + "s");

        return results;
    }
    public ArrayList<Float> similarity_values(BufferedImage queryImg, int k){

        long tic = System.currentTimeMillis();


        float[] imageQueryDescriptor_color = ImageDescriptors.computeImageDescriptor_color(queryImg,Color_u, bin);
        float[] imageQueryDescriptor_lbp = ImageDescriptors.computeImageDescriptor_lbp(queryImg,LBP);
        ArrayList results = new ArrayList<>();
        /*
         * Precompute the similarity between the query and all the images in the
         * index.
         */
        float[] similarityCache = new float[index.size()];
        if(Color_u &&LBP) {
            for (int i = 0; i < index.size(); i++) {
                similarityCache[i] = (float) MathFunctions.simil(imageQueryDescriptor_color, index.get(i).imageDescriptor_color, imageQueryDescriptor_lbp, index.get(i).imageDescriptor_lbp);
            }
        }
        else if(Color_u==true &&LBP==false) {
            for (int i = 0; i < index.size(); i++) {
                similarityCache[i] = MathFunctions.similarity(imageQueryDescriptor_color, index.get(i).imageDescriptor_color);
            }
        }
        else if(Color_u==false &&LBP==true) {
            for (int i = 0; i < index.size(); i++) {
                similarityCache[i] = MathFunctions.similarity(imageQueryDescriptor_lbp, index.get(i).imageDescriptor_lbp);
            }
        }

        for (int i = 0; i < k; i++) {
            // index of the minimum distance
            int argmax = outilsTab.argmax(similarityCache);

            File argmaxFile = index.get(argmax).file;
            float maxSimilarity = similarityCache[argmax];
            //System.out.println(argmaxFile.getName() + " similarité: " + String.format("%.3f", maxSimilarity));
            results.add(maxSimilarity);
            similarityCache[argmax] = Float.NEGATIVE_INFINITY;
        }

        System.out.println("temps de recherche : " + (System.currentTimeMillis() - tic) / 1000f + "s");

        return results;

    }

}
