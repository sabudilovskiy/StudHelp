package Matrix;

import Logger.Logger;
import Logger.Log;
import MRV.MRV;

public class Matrix extends Logger {
    protected double cof_det = 1;
    protected double[][] arr = new double[0][0];
    public Matrix( double[][] arr ){
        this.arr = arr;
        m = arr.length;
        if (m>0) n = arr[0].length;
        else n = 0;
    }
    protected int m = 0;
    protected int n = 0;
    static Matrix summ(Matrix left, Matrix right) throws MRV.MATRIX_DIMENSION_MISSMATCH {
        if (left.m == right.m && left.n == right.n){
            int m = left.m;
            int n = left.n;
            double[][] arr = new double[m][n];
            for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) arr[i][j] = left.arr[i][j] + right.arr[i][j];
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
            for (int i = 0; i < m; i++) for (int j = 0; j < p; j++)
                for (int k = 0; k < n; k++) arr[i][j] += left.arr[i][k] * right.arr[k][j];
            return new Matrix (arr);
        }
        else throw new MRV.MATRIX_DIMENSION_MISSMATCH ();
    }
    protected void summ_strings( int a, int b, double k) throws MRV.INVALID_NUMBER_STRING {
        if (0<= a && a <= m && 0 <= b && b <= m) {
            for (int i = 0; i < n; i++) arr[a][i] += k*arr[b][i];
        }
        else throw new MRV.INVALID_NUMBER_STRING ();
    }
    protected boolean is_null_string(int a) throws MRV.INVALID_NUMBER_STRING {
        if (0<= a && a<=m){
            for (int i = 0; i < n; i++) if (arr[a][i] != 0) return false;
            return true;
        }
        else throw new MRV.INVALID_NUMBER_STRING ();
    }
    protected void swap_strings(int a, int b) throws MRV.INVALID_NUMBER_STRING {
        if (0 <= a && a <= m && 0 <= b && b <= m) {
            cof_det*=-1;
            for (int i = 0; i < n; i++) {
                double first = arr[a][i], second = arr[b][i];
                arr[a][i] = second;
                arr[b][i] = first;
            }
        }
        else throw new MRV.INVALID_NUMBER_STRING ();
    }
    protected void delete_string(int a){
        if (0 <= a && a <= m){
            double[][] temp = new double[m-1][n];
            for (int i = 0; i < a; i++) for (int j = 0; j < n; j++) temp[i][j] = arr[i][j];
            for (int i = a + 1; i < m; i++) for (int j = 0; j < n; j++) temp[i-1][j] = arr[i][j];
            arr = temp;
            m--;
        }
    }
    protected void delete_column(int a) throws MRV.INVALID_NUMBER_STRING {
        if (0 <= a && a <= n){
            double[][] temp = new double[m][n-1];
            for (int i = 0; i < m; i++) for (int j = 0; j < a; j++) temp[i][j] = arr[i][j];
            for (int i = 0; i < m; i++) for (int j = a + 1; j < n; j++) temp[i][j-1] = arr[i][j];
            arr = temp;
            n--;
        }
        else throw new MRV.INVALID_NUMBER_STRING ();
    }
    protected void move_string_to_end(int a) throws MRV.INVALID_NUMBER_STRING {
        if (0<=a && a <= m) {
            for (int i = a; i < m-1; i++) {
                swap_strings (i, i+1);
            }
        } else throw new MRV.INVALID_NUMBER_STRING ();
    }
    protected int find_non_zero_in_column (int column, int start) throws MRV.INVALID_NUMBER_STRING {
        if (0<= column && column <= n){
            for (int i = start + 1; i < m; i++){
                if (arr[i][column] != 0) return i;
            }
            return -1;
        }
        else throw new MRV.INVALID_NUMBER_STRING ();

    }
    public void gauss_transformation() throws MRV.INVALID_NUMBER_STRING {
        int local_m = m;
        log_this ("Для того, чтобы преобразовать матрицу методом Гаусса необходимо постепенно, столбец за столбцом путём вычитания верхних строк из нижних добиваться появления нулей под главной диагональю. \n Коэффициент, с которым нужно прибавлять верхнюю строку высчитывается следующим образом: элемент под главной диагональю разделить на элемент на главной диагонали. /n");
        for (int i = 0; i < local_m - 1; i++) {
            int f = find_non_zero_in_column (i,i);
            if (arr[i][i] != 0 && f != -1) {
                for (int j = i + 1; j < m; j++){
                    Log.add("k" + j + "= -a" + j + i + " / a" + i + i + "=" + -arr[j][i] + " / " + arr[i][i] + " = " + -arr[j][i]/arr[i][i], "");
                    double k = -arr[j][i]/arr[i][i];
                    summ_strings (j, i, k);
                }
                log_this ("Обнуляем " + i + " столбец при помощи сложения строчек с "  +  i  +" строчкей, умноженной на посчитанные выше коэффициенты");
            }
            else if(f==-1){
                Log.add ("Так как " + i + " столбец ниже главной диагонали уже обнулён, то ничего не делаем", "");
            }
            else {
                if(is_null_string (i))
                {
                    move_string_to_end (i);
                    log_this ("Получилось, что " + i + " строка является нулевой. Смещаем её вниз." );
                    i--;
                }
                else{
                    swap_strings (i,f);
                    log_this ("Так как " + i + " строчка имеет ноль на главной диагонали, то мы её поменяли с нижней, у которой нет нуля на главной диагонали");
                }
            }
        }
        log_this ("Мы добились того, что под главной диагональю одни нули. Осталось добиться того же над главной диагональю, где это возможно");
        local_m = m;
        for (int i =  local_m - 1; i > 0; i--) {
            int f = find_non_zero_in_column (i,0);
            if (arr[i][i] != 0 && f != i && f!= -1) {
                for (int j = i - 1; j >= 0; j--){
                    Log.add("k" + j + "= -a" + j + i + " / a" + i + i + "=" + -arr[j][i] + " / " + arr[i][i] + " = " + -arr[j][i]/arr[i][i], "");
                    double k = -arr[j][i]/arr[i][i];
                    summ_strings (j, i, k);
                }
                log_this ("Обнуляем " + i + " столбец при помощи сложения строчек с "  +  i  +" строчкей, умноженной на посчитанные выше коэффициенты");
            }
            else if(f==i || f==-1){
                Log.add ("Так как " + i + " столбец выше главной диагонали уже обнулён, то ничего не делаем", "");
            }
        }
    }
    protected Matrix minor(int a, int b) throws MRV.INVALID_NUMBER_STRING {
        if (0 <= a && a <= m && 0 <= b && b <= n){
            Matrix copy = new Matrix (arr);
            copy.delete_string (a);
            copy.delete_column (b);
            return copy;
        }
        else throw new MRV.INVALID_NUMBER_STRING ();
    }
    protected int count_null_in_string(int a) throws MRV.INVALID_NUMBER_STRING {
        if (0<= a && a<=m) {
            int count = 0;
            for (int i = 0; i < n; i++) if (arr[a][i] == 0) count++;
            return count;
        }
        else throw new MRV.INVALID_NUMBER_STRING ();
    }
    protected int count_null_in_column(int a) throws MRV.INVALID_NUMBER_STRING {
        if (0<= a && a<=m) {
            int count = 0;
            for (int i = 0; i < m; i++) if (arr[i][a] == 0) count++;
            return count;
        }
        else throw new MRV.INVALID_NUMBER_STRING ();
    }
    //возвращает номер строки, в которой наибольшее количество нулей и их количество
    protected int[] find_most_null_string() throws MRV.INVALID_NUMBER_STRING {
        int[] max = new int[2];
        for (int i = 0; i < m; i++) if (count_null_in_string (i) > max[1]){
            max[0] = i;
            max[1] = count_null_in_string (i);
        }
        return max;
    }
    //возвращает номер столбца, в котором наибольшее количество нулей и их количество
    protected int[] find_most_null_column() throws MRV.INVALID_NUMBER_STRING {
        int[] max = new int[2];
        for (int i = 0; i < n; i++) if (count_null_in_column (i) > max[1]){
            max[0] = i;
            max[1] = count_null_in_column (i);
        }
        return max;
    }
    protected double algebraic_complement (int a, int b) throws MRV.INVALID_NUMBER_STRING, MRV.NON_QUADRATIC_MATRIX {
        if (0 <= a && a <= m && 0 <= b && b <= n){
            double value = Math.pow (-1, a + b) * minor (a, b).determinant ();
            return value;
        }
        else throw new MRV.INVALID_NUMBER_STRING ();
    }
    public double determinant() throws MRV.NON_QUADRATIC_MATRIX, MRV.INVALID_NUMBER_STRING {
        if (m == n){
            double det = 0;
            if (m == 1) det = arr[0][0];
            else if (m == 2) det = arr[0][0]*arr[1][1] - arr[0][1]*arr[1][0];
            else if (m == 3) {
                det = arr[0][0]*arr[1][1]*arr[2][2] + arr[0][1]*arr[1][2]*arr[2][0] + arr[0][2]*arr[1][0]*arr[2][1];
                det = det - arr[2][0]*arr[1][1]*arr[0][2] - arr[1][0]*arr[0][1]*arr[2][2] - arr[0][0]*arr[2][1]*arr[1][2];
            }
            else{
                int[] str = find_most_null_string ();
                int[] col = find_most_null_column ();
                det = 0;
                if (str[1]>=col[1]){
                    for (int i = 0; i < n; i++) det += algebraic_complement (str[0], i)*arr[str[0]][i];
                }
                else{
                    for (int i = 0; i < m; i++) det += algebraic_complement (col[0], i)*arr[i][col[0]];
                }
            }
            return det;
        }
        else throw new MRV.NON_QUADRATIC_MATRIX ();
    }
    public double det_with_gauss() throws MRV.INVALID_NUMBER_STRING {
        gauss_transformation ();
        double det = 1;
        for (int i = 0; i < m; i++) det*=arr[i][i];
        return det;
    }
    public void print(){
        for (int i = 0; i < m; i++){
            String temp = "";
            for (int j = 0; j < n; j++) temp = temp + Double.toString (arr[i][j]) + " ";
            System.out.println (temp);
        } 
    }

    @Override
    public String decode_this() {
        String decode = "";
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) decode += arr[i][j] + " ";
            decode += "\n";
        }
        return decode;
    }
}
