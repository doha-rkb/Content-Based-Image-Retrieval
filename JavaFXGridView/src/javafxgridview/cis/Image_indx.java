package javafxgridview.cis;


import java.io.File;
import java.io.Serializable;

public class Image_indx implements Serializable {
    private static final long serialVersionUID = 1L;
    File file;
    float[] imageDescriptor_lbp;
    float[] imageDescriptor_color;

    public Image_indx(File file, float[] imageDescriptor_color, float[] imageDescriptor_lbp) {
        this.file = file;
        this.imageDescriptor_color = imageDescriptor_color;
        this.imageDescriptor_lbp = imageDescriptor_lbp;

    }
}
