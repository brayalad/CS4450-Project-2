package com.cpp.cs.cs4450.util.math;

public final class MathUtils {

    private MathUtils(){}


    public static byte[][] multiply(final byte[][] a, final byte[][] b){
        final int x = a.length;
        final int y = b[0].length;
        final int z = a[0].length;

        final byte[][] p = new byte[x][y];
        for(int i = 0; i < x; ++i){
            for(int j = 0; j < y; ++j){
                for(int k = 0; k < z; ++k){
                    p[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return p;
    }

    public static short[][] multiply(final short[][] a, final short[][] b){
        final int x = a.length;
        final int y = b[0].length;
        final int z = a[0].length;

        final short[][] p = new short[x][y];
        for(int i = 0; i < x; ++i){
            for(int j = 0; j < y; ++j){
                for(int k = 0; k < z; ++k){
                    p[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return p;
    }

    public static int[][] multiply(final int[][] a, final int[][] b){
        final int x = a.length;
        final int y = b[0].length;
        final int z = a[0].length;

        final int[][] p = new int[x][y];
        for(int i = 0; i < x; ++i){
            for(int j = 0; j < y; ++j){
                for(int k = 0; k < z; ++k){
                    p[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return p;
    }

    public static long[][] multiply(final long[][] a, final long[][] b){
        final int x = a.length;
        final int y = b[0].length;
        final int z = a[0].length;

        final long[][] p = new long[x][y];
        for(int i = 0; i < x; ++i){
            for(int j = 0; j < y; ++j){
                for(int k = 0; k < z; ++k){
                    p[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return p;
    }

    public static float[][] multiply(final float[][] a, final float[][] b){
        final int x = a.length;
        final int y = b[0].length;
        final int z = a[0].length;

        final float[][] p = new float[x][y];
        for(int i = 0; i < x; ++i){
            for(int j = 0; j < y; ++j){
                for(int k = 0; k < z; ++k){
                    p[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return p;
    }

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

}
