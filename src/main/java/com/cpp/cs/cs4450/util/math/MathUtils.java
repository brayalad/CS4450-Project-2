package com.cpp.cs.cs4450.util.math;

import java.util.Map.Entry;

public final class MathUtils {

    private MathUtils(){}


    public static double[][] multiply(final double[][] a, final double[][] b){
        final int x = a.length;
        final int y = b[0].length;
        final int z = a[0].length;

        final double[][] p = new double[x][y];
        for(int i = 0; i < x; ++i){
            for(int j = 0; j < y; ++j){
                for(int k = 0; k < z; ++k){
                    p[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return p;
    }

    public static double[][] to2DMatrix(final Entry<Double, Double> p){
        return new double[][]{{p.getKey()}, {p.getValue()}, {1.0}};
    }

}
