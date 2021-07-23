package Matrix;

import Logger.Log;
import MRV.MRV;
import Parameters.SLE;

public class AugmentedMatrix extends Matrix{
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
    @Override
    public int rank() throws MRV.INVALID_NUMBER_STRING {
        double[][] temp_arr = new double[m][n+augmented_n];
        for (int i = 0; i < m; i++) for (int j = 0; j < n + augmented_n; j++){
            if (j<n) temp_arr[i][j] = arr[i][j];
            else temp_arr[i][j] = augmented_arr[i][j-n];
        }
        Matrix temp = new Matrix (temp_arr);
        return temp.rank();
    }

    @Override
    protected boolean is_null_string( int a ) throws MRV.INVALID_NUMBER_STRING {
        boolean augmented_check = true;
        if (0<=a && a < m) {
            for (int i = 0; i < augmented_n; i++) {
                if (augmented_arr[a][i]!=0) {
                    augmented_check = false;
                    break;
                }
            }
            if (augmented_check) return super.is_null_string (a);
            else return false;
        }
        else throw new MRV.INVALID_NUMBER_STRING ();
    }

    @Override
    protected void delete_string( int a ) {
        double[][] temp = new double[m-1][augmented_n];
        for (int i = 0; i < a; i++) for (int j = 0; j < augmented_n; j++) temp[i][j] = augmented_arr[i][j];
        for (int i = a + 1; i < m; i++) for (int j = 0; j < augmented_n; j++) temp[i-1][j] = augmented_arr[i][j];
        augmented_arr = temp;
        super.delete_string (a);
    }
    protected void reset_augmented(){
       augmented_arr = new double[m][1];
    }
    protected boolean is_homogeneous(){
        if (augmented_n>1) return false;
        for (int i =0; i < m; i++) if (augmented_arr[i][0] !=0 ) return false;
        return true;
    }
    public Matrix substituion(double[] array) throws MRV.MATRIX_DIMENSION_MISSMATCH, MRV.NON_SINGLE {
        if (n-m == array.length && augmented_n == 1){
            if (is_single ()){
                double[] cof = new double[n];
                for (int i = m; i < n; i++) cof[i] = array[i-m];
                for (int i = 0; i < m; i++){
                    String temp1 = "x" + (i+1) + " = ";
                    String temp2 = temp1;
                    for (int j = m; j < n; j++){
                        //если и коэффициент в матрице ненулевой и подставляется не ноль у соответствующей переменной
                        if (arr[i][j] != 0 && cof[j]!=0){
                            temp1 += "-a" + (i+1) + (j+1) + " * " + "x" + (j+1) + " + ";
                            temp2 += -arr[i][j] + " * " + cof[j] + " + ";
                            cof[i] += -arr[i][j] * cof[j];
                        }
                    }
                    if (augmented_arr[i][0]!=0){
                        temp1 += "b" + (i+1);
                        temp2 += augmented_arr[i][0];
                        cof[i] += augmented_arr[i][0];
                    }
                    else {
                        temp1 = temp1.substring (0, temp1.length () - 3);
                        temp2 = temp2.substring (0, temp2.length () - 3);
                    }
                    Log.add(temp1, "");
                    Log.add(temp2, "");
                    Log.add("x" + (i+1) + " = " + cof[i], "");
                }
                return new Matrix (cof);
            }
            else throw new MRV.NON_SINGLE ();    
        }
        else throw new MRV.MATRIX_DIMENSION_MISSMATCH ();
    }
    public Object[] solve_system() throws MRV.MATRIX_DIMENSION_MISSMATCH, MRV.INVALID_NUMBER_STRING, MRV.HAVE_NOT_SOLUTIONS, MRV.NON_SINGLE, MRV.NOT_IMPLEMENT {
        if (augmented_n == 1){
            AugmentedMatrix copy = new AugmentedMatrix (arr, augmented_arr);
            if (m == n && Settings.SLE.getSettings () == SLE.KRAMER_RULE) {
                throw new MRV.NOT_IMPLEMENT ();
            }
            else {
                Log.add("", "Решим систему методом Гаусса");
                copy.gauss_transformation ();
                copy.reduce_null_strings ();
                if (m==n) {
                    Double[] answer = new Double[n];
                    for (int i = 0; i < n; i++)
                        if (arr[i][i]==1) answer[i] = augmented_arr[0][i];
                        else throw new MRV.HAVE_NOT_SOLUTIONS ();
                    return answer;
                }
                else {
                    Matrix[] answer = new Matrix[n-m];
                    if (is_homogeneous ()){
                        Log.add ("", "Так как СЛАУ является однородной и прямоугольной, то она задаёт линейное подпространство(оболочку). Найдём базис.");
                        for (int i = 0; i < n-m; i++)
                        {
                            double[] temp = new double[n-m];
                            temp[i] = 1.0;
                            for (int j = m; j < n; j++) Log.add("x" + (j + 1)  + " = " + temp[j], "");
                            for (int j = 0; j < m; j++){
                                answer[i] = substituion (temp);
                                answer[i].log_this ("");
                            }
                        }
                    }
                    else {
                        Log.add ("", "Так как СЛАУ является неоднородной и прямоугольной, то она задаёт линейное многообразие. Найдём базис.");
                        Log.add("", "Найдём частное решение");
                        Matrix v = substituion (new double[n-m]);
                        v.log_this ("Это вектор, на который перенесёно линейное подпространство");
                        Log.add ("", "Найдём базис соответствующего пространства. Для этого обнулим столбец свободных членов.");
                        copy.reset_augmented ();
                        for (int i = 0; i < n-m; i++)
                        {
                            double[] temp = new double[n-m];
                            temp[i] = 1.0;
                            for (int j = m; j < n; j++) Log.add("x" + (j + 1)  + " = " + temp[j-m], "");
                            for (int j = 0; j < m; j++){
                                answer[i] = substituion (temp);
                                answer[i].log_this ("");
                            }
                        }
                    }
                    return answer;
                }
            }
        }
        else throw new MRV.MATRIX_DIMENSION_MISSMATCH ();
    }
}
