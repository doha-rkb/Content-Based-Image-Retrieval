package javafxgridview.cis;

import java.util.Arrays;

public class MathFunctions {

    public static float[] normaliser(int[] vector) {
        final int sum = Arrays.stream(vector).sum();
        final float[] norm = new float[vector.length];
        for (int i = 0; i < norm.length; i++) {
            norm[i] = (float) vector[i] / sum;
        }
        return norm;
    }


    static float L1dist(float[] A, float[] B) {
        float d = 0;
        for (int i = 0; i < A.length; i++) {
            final float difference = A[i] - B[i];
            d += Math.abs(difference);
        }
        return d;
    }

    static float similarity(float[] vectorA, float[] vectorB) {
        return 1 / (1 + L1dist(vectorA, vectorB));
    }
    static float similarity_col(float[] v1, float[] v2){
        return 1/(1+ L1dist(v1,v2));
    }
    static float similarity_form(float[] v1, float[] v2){
        return 1/(1+ L1dist(v1,v2));
    }

    static double simil(float[] v1, float[] v2,float[] vA, float[] vB){
        return 0.2*similarity_col(v1,v2)+0.8*similarity_form(vA,vB);
    }
    public static float[] normaliser(float[] desc) {
        float sum = 0;
        for (float i : desc) {
            sum += i;
        }
        final float[] normal = new float[desc.length];
        for (int i = 0; i < normal.length; i++) {
            normal[i] = (float) desc[i] / sum;
        }
        return normal;
    }
}
