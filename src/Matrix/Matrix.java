package Matrix;

import MRV.MRV;

public class Matrix {
    protected double[][] arr = new double[0][0];
    public Matrix(){}
    public Matrix( double[][] arr ){
        this.arr = arr;
        m = arr.length;
        if (m>0) n = arr[0].length;
        else n = 0;
    }
    protected int m = 0;
    protected int n = 0;
    static Matrix summ(Matrix left, Matrix right) throws MRV.MRV.MATRIX_DIMENSION_MISSMATCH {
        if (left.m == right.m && left.n == right.n){
            int m = left.m;
            int n = left.n;
            double[][] arr = new double[m][n];
            for (int i = 0; i < m; i++) for (int j = 0; j < n; i++) arr[i][j] = left.arr[i][j] + right.arr[i][j];
            return new Matrix(arr);
        }
        else throw new MRV.MATRIX_DIMENSION_MISSMATCH ();
    }
    static Matrix mult(Matrix left, Matrix right) throws MRV.MATRIX_DIMENSION_MISSMATCH {
        if (left.n == right.m) {
            int m = left.m;
            int n = left.n;
            int p = right.n;
            double [][] arr = new double[m][p];
            for (int i = 0; i < m; i++) for (int j = 0; j < p; j++){
                for (int k = 0; k < n; k++) arr[i][j]+=left.arr[i][k]*right.arr[k][j];
            }
            return new Matrix (arr);
        }
        else throw new MRV.MATRIX_DIMENSION_MISSMATCH ();
    }
    public void summ_strings( int a, int b, double k) throws MRV.INVALID_NUMBER_STRING {
        if (0<= a && a <= m && 0 <= b && b <= m) {
            for (int i = 0; i < n; i++) arr[a][i] += k*arr[b][i];
        }
        else throw new MRV.INVALID_NUMBER_STRING ();
    }
    public boolean is_null_string(int a) throws MRV.INVALID_NUMBER_STRING {
        if (0<= a && a<=m){
            for (int i = 0; i < n; i++) if (arr[a][i] != 0) return false;
            return true;
        }
        else throw new MRV.INVALID_NUMBER_STRING ();
    }
    public void delete_string(int a){
        if (0 <= a && a <= m){
            ;
        }
    }
//    public Matrix gauss_method(){
//        for (int i = 0; i < m; i++) {
//            if (arr[i][i] != 0) {
//                for (int j = i; j < m; j++){
//                    try {
//                        summ_strings (j, i, arr[j][i]/arr[i][i]);
//                    } catch (MRV.INVALID_NUMBER_STRING ignored) {}
//                }
//            }
//            else {
//                try {
//                    if (!is_null_string (i)){
//
//                    }
//                } catch (MRV.INVALID_NUMBER_STRING ignored) {}
//            }
//        }
//    }
    public int determinant() throws MRV.NON_QUADRATIC_MATRIX {
        if (m == n){
            return 1;
        }
        else throw new MRV.NON_QUADRATIC_MATRIX ();
    }
}
