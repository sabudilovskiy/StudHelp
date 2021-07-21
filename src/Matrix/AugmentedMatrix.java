package Matrix;

import MRV.MRV;

class AugmentedMatrix extends Matrix{
    protected double[][] augmented_arr;
    protected int augmented_n;
    public AugmentedMatrix( double[][] arr , double[][] augmented_arr) throws MRV.MATRIX_DIMENSION_MISSMATCH {
        super(arr);
        this.augmented_arr = augmented_arr;
        int augmented_m = augmented_arr.length;
        if (augmented_m == m){
            if (augmented_m>0) augmented_n = augmented_arr[0].length;
            else augmented_n = 0;
        }
        else throw new MRV.MATRIX_DIMENSION_MISSMATCH ();
    }
    public AugmentedMatrix (Matrix left, Matrix right) throws MRV.MATRIX_DIMENSION_MISSMATCH {
        if (left.m == right.m){
            arr = left.arr;
            augmented_arr = right.arr;
            m = left.m;
            n = left.n;
            augmented_n = right.n;
        }
       else throw new MRV.MATRIX_DIMENSION_MISSMATCH ();


    }
    @Override
    protected void summ_strings( int a, int b, double k ) throws MRV.INVALID_NUMBER_STRING {
        for (int i = 0; i < augmented_n; i++) augmented_arr[a][i] += augmented_arr[b][i]*k;
        super.summ_strings (a, b, k);
    }
    protected Matrix get_augmentation(){
        return new Matrix (augmented_arr);
    }
    protected Matrix get_main(){
        return new Matrix (arr);
    }
    @Override
    public String decode_this() {
        String decode = "";
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) decode += arr[i][j] + " ";
            decode += " | ";
            for (int j = 0; j < augmented_n; j++) decode += augmented_arr[i][j] + " ";
            decode += "\n";
        }
        return decode;
    }

    @Override
    protected void mult_string( int a, double k ) throws MRV.INVALID_NUMBER_STRING {
        for (int i = 0; i < augmented_n; i++) augmented_arr[a][i]*=k;
        super.mult_string (a, k);
    }

    @Override
    protected void div_string( int a, double k ) throws MRV.INVALID_NUMBER_STRING {
        for (int i = 0; i < augmented_n; i++) augmented_arr[a][i]/=k;
        super.div_string (a, k);
    }
}
