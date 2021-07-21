package Matrix;

import Logger.Log;
import Logger.Logger;
import MRV.MRV;
import Parameters.Det;
import Parameters.Rank;

import java.util.Arrays;

public class Matrix extends Logger {
    protected double cof_det = 1;
    protected double[][] arr = new double[0][0];
    public Matrix(){
    }
    public Matrix( double[][] arr ){
        this.arr = arr;
        m = arr.length;
        if (m>0) n = arr[0].length;
        else n = 0;
    }
    //создаёт единичную матрицу n на n
    public Matrix(int n){
        arr = new double[n][n];
        m = n;
        this.n = n;
        for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) arr[i][j] = (i==j)?1:0;
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
    //прибавляем к a b
    protected void summ_strings( int a, int b, double k) throws MRV.INVALID_NUMBER_STRING {
        if (0<= a && a <= m && 0 <= b && b <= m) {
            for (int i = 0; i < n; i++) arr[a][i] += k*arr[b][i];
            log_this ("Прибавляем к " + (a+1) + " строке " + (b+1) + " строку, умноженную на " + k);
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
            for (int i = start; i < m; i++){
                if (arr[i][column] != 0) return i;
            }
            return -1;
        }
        else throw new MRV.INVALID_NUMBER_STRING ();

    }
    protected void mult_string(int a, double k) throws MRV.INVALID_NUMBER_STRING {
        if (0<=a && a<= m){
            for (int i = 0; i < n; i++) arr[a][i]*=k;
            log_this ("Умножаем " + (a+1) + " строку на " + k);
            cof_det = cof_det / k;
        }
        else throw new MRV.INVALID_NUMBER_STRING ();
    }
    protected void div_string(int a, double k) throws MRV.INVALID_NUMBER_STRING {
        if (0<=a && a<= m){
            for (int i = 0; i < n; i++) arr[a][i]/=k;
            log_this ("Делим " + (a+1) + " строку на " + k);
            cof_det = cof_det * k;
        }
        else throw new MRV.INVALID_NUMBER_STRING ();
    }
    public void gauss_transformation() throws MRV.INVALID_NUMBER_STRING {
        int local_m = m;
        log_this ("Для того, чтобы преобразовать матрицу методом Гаусса необходимо сначала сделать матрицу верхнетреугольной.");
        triangular_transformation ();
        log_this ("Мы добились того, что под главной диагональю одни нули. Осталось добиться того же над главной диагональю, где это возможно");
        for (int i = m - 1; i > 0; i--) {
            int f = find_non_zero_in_column (i,0);
            if (arr[i][i] != 0 && f != i && f!= -1) {
                for (int j = i - 1; j >= 0; j--){
                    Log.add("k" + j + "= -a" + j + i + " / a" + i + i + "=" + -arr[j][i] + " / " + arr[i][i] + " = " + -arr[j][i]/arr[i][i], "");
                    double k = -arr[j][i]/arr[i][i];
                    summ_strings (j, i, k);
                }
            }
            else if(f==i || f==-1){
                Log.add ("Так как " + (i+1) + " столбец выше главной диагонали уже обнулён, то ничего не делаем", "");
            }
        }
        log_this ("Теперь и над, и под главной диагональю нули. Осталось добиться того, чтобы на главной диагонали были только единицы.");
        for (int i = 0; i < m; i++) if (arr[i][i]!=0) div_string (i, arr[i][i]);
    }
    //проверяет, является ли матрица единичной в главной части(квадратной подматрице)
    protected boolean is_single(){
        for (int i = 0; i < m; i++) for (int j = 0; j < m; j++) if (arr[i][j] != ((i==j)?1.0:0.0)) return false;
        return true;
    }
    public void triangular_transformation() throws MRV.INVALID_NUMBER_STRING {
        int local_m = m;
        log_this ("Для того, чтобы привести матрицу к верхнетреугольному(трапециевидному) виду необходимо постепенно, столбец за столбцом путём вычитания верхних строк из нижних добиваться появления нулей под главной диагональю.");
        for (int i = 0; i < local_m - 1; i++) {
            int f = find_non_zero_in_column (i,i+1);
            if (arr[i][i] != 0 && f != -1) {
                for (int j = i + 1; j < m; j++){
                    Log.add("k" + (j+1) + "= -a" + (j+1) + (i+1) + " / a" + (i+1) + (i+1) + "=" + -arr[j][i] + " / " + arr[i][i] + " = " + -arr[j][i]/arr[i][i], "");
                    double k = -arr[j][i]/arr[i][i];
                    summ_strings (j, i, k);
                }

            }
            else if(f==-1){
                Log.add ("Так как " + (i+1) + " столбец ниже главной диагонали уже обнулён, то ничего не делаем", "");
            }
            else {
                if(is_null_string (i))
                {
                    move_string_to_end (i);
                    log_this ("Получилось, что " + (i+1) + " строка является нулевой. Смещаем её вниз." );
                    i--;
                }
                else{
                    swap_strings (i,f);
                    log_this ("Так как " + (i+1) + " строчка имеет ноль на главной диагонали, то мы её поменяли с нижней, у которой нет нуля на главной диагонали");
                }
            }
        }
    }
    protected Matrix complement_minor( int str, int col ) throws MRV.INVALID_NUMBER_STRING {
        if (0 <= str && str <= m && 0 <= col && col <= n){
            Matrix copy = new Matrix (arr);
            copy.delete_string (str);
            copy.delete_column (col);
            return copy;
        }
        else throw new MRV.INVALID_NUMBER_STRING ();
    }
    protected Matrix complement_minor (int[] str, int[] col) throws MRV.MATRIX_DIMENSION_MISSMATCH {
        if (m==n && str.length == col.length){
            int k = 0, a = 0, b = 0, m_new = m-str.length;
            double[][] temp_arr = new double[m_new][m_new];
            while (k < m_new*m_new){
                boolean crossed_out = false;
                for (int j : str)
                    if ( a == j ) {
                        crossed_out = true;
                        break;
                    }
                if ( !crossed_out ){
                    for (int j : col)
                        if ( a == j ) {
                            crossed_out = true;
                            break;
                        }
                    if (!crossed_out){
                        temp_arr[k/m_new][k%m_new] = arr[a++][b++];
                        k++;
                    }
                }
            }
            return new Matrix (temp_arr);
        }
        else throw new MRV.MATRIX_DIMENSION_MISSMATCH ();
    }
    protected Matrix minor(Integer[] str, Integer[] col) throws MRV.MATRIX_DIMENSION_MISSMATCH {
        if (str.length == col.length){
            int k = 0, a = 0, b = 0, m_new = str.length;
            double[][] temp_arr = new double[m_new][m_new];
            for (int i = 0; i < m && k < m_new*m_new ;i++) for (int j = 0; j < n && k < m_new*m_new; j++){
                if (Arrays.asList (str).contains (i) && Arrays.asList (col).contains (j)){
                    temp_arr[k/m_new][k%m_new] = arr[i][j];
                    k++;
                }
            }
            return new Matrix (temp_arr);
        }
        else throw new MRV.MATRIX_DIMENSION_MISSMATCH ();
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
            Log.add("", "Для вычисления алгебраического дополнения необходимо умножить -1 в степени суммы индексов элемента на его минор.");
            Log.add("", "Найдём A" + (a+1) + (b+1));
            Matrix minor = complement_minor (a, b);
            Log.add("","Получаем минор вычеркнув " + (a+1) + " строку и " + (b+1) + " столбец. Вычислим его определитель.");
            double minor_determinant = minor.determinant ();
            double value = Math.pow (-1, a + b) * minor_determinant;
            Log.add("A" + (a+1) + (b+1) + " = " + minor_determinant + "*" + "-1^(" + (a+1) + "+" + (b+1) + ") = " + value, "");
            return value;
        }
        else throw new MRV.INVALID_NUMBER_STRING ();
    }
    public double determinant() throws MRV.NON_QUADRATIC_MATRIX, MRV.INVALID_NUMBER_STRING {
        if (m == n){
            try {
                Det cur_method = Settings.Det.get_det_method (m);
                return switch (cur_method) {
                    case BASIC -> det_with_basic_rules ();
                    case LAPLASS -> det_with_laplass ();
                    case SARUSS -> det_with_saruss_rule ();
                    case TRIANGLE -> det_with_triangle ();
                };
            } catch (MRV.MATRIX_DIMENSION_MISSMATCH ignored) {
                return 0;
            }
        }
        else throw new MRV.NON_QUADRATIC_MATRIX ();
    }
    public double det_with_basic_rules() throws MRV.NON_QUADRATIC_MATRIX, MRV.MATRIX_DIMENSION_MISSMATCH {
        double det = 0;
        if (m==n){
            switch (m) {
                case 1 -> {
                    log_this ("Определитель матрицы из одного элемента равен этому элеменету.");
                    Log.add ("det = " + "a11" + " = " + arr[0][0], "");
                    det = arr[0][0];
                }
                case 2 -> {
                    det = arr[0][0] * arr[1][1] - arr[0][1] * arr[1][0];
                    log_this ("Определитель матрицы 2 на 2 равен произведению элементов на главной минус произведение элементов на побочной диагонали.");
                    Log.add ("det = a11*22 - a12*a21 = " + arr[0][0] + "*" + arr[1][1] + "-" + arr[0][1] + "*" + arr[1][0] + " =  " + det, "");
                }
                default -> throw new MRV.MATRIX_DIMENSION_MISSMATCH ();
            }
        return det;
        }
        else throw new MRV.NON_QUADRATIC_MATRIX ();
    }
    public double det_with_saruss_rule() throws MRV.MATRIX_DIMENSION_MISSMATCH {
        if (m==n && m==3){
            double det = arr[0][0]*arr[1][1]*arr[2][2] + arr[0][1]*arr[1][2]*arr[2][0] + arr[0][2]*arr[1][0]*arr[2][1];
            det = det - arr[2][0]*arr[1][1]*arr[0][2] - arr[1][0]*arr[0][1]*arr[2][2] - arr[0][0]*arr[2][1]*arr[1][2];
            log_this ("Определитель матрицы 3 на 3 можно вычислить используя правило треугольника или способ Саррюса. ");
            Log.add ("det = a11*a22*a33 + a12*a23*a31 + a13*a21*a32 - a31*a22*a13 - a21*a12*a33 - a11*a32*a23 =", "");
            Log.add ("= " + arr[0][0] + "*" + arr[1][1] + "*" + arr[2][2] + " " + arr[0][1] + "*" + arr[1][2] + "*" + arr[2][0] + " " + arr[0][2] + "*" + arr[1][0] + "*" + arr[2][1] + " -(" + arr[2][0] + "*" + arr[1][1] + "*" + arr[0][2] + " " + arr[1][0] + "*" + arr[0][1] + "*" + arr[2][2] + " " + arr[0][0] + "*" + arr[2][1] + "*" + arr[1][2] + ") =", "");
            Log.add ("= " + arr[0][0]*arr[1][1]*arr[2][2] + " + " + arr[0][1]*arr[1][2]*arr[2][0] + " + " + arr[0][2]*arr[1][0]*arr[2][1] + " + " + "-(" + arr[2][0]*arr[1][1]*arr[0][2] + " + " + arr[1][0]*arr[0][1]*arr[2][2] + " + " + arr[0][0]*arr[2][1]*arr[1][2] + ")= " + det,"");
            return det;
        }
        else throw new MRV.MATRIX_DIMENSION_MISSMATCH ();
    }
    public double det_with_triangle() throws MRV.INVALID_NUMBER_STRING {
        Matrix copy = new Matrix (arr);
        copy.triangular_transformation ();
        double det = 1;
        String temp = "det = ", temp2 = "det = ";
        for (int i = 0; i < m; i++){
            det*=copy.arr[i][i];
            temp += "a" + (i+1) + (i+1) + " * ";
            temp2 += copy.arr[i][i] + " * ";
        }
        temp = temp.substring (0, temp.length () - 3);
        temp2 = temp2.substring (0, temp2.length () - 3);
        Log.add (temp, "Так как матрица треугольного вида, то определитель равен произведению элементов на главной диагонали.");
        Log.add (temp2, "");
        Log.add("det = " + det, "");
        return det;
    }
    public double det_with_laplass() throws MRV.INVALID_NUMBER_STRING, MRV.NON_QUADRATIC_MATRIX {
        int[] str = find_most_null_string ();
        int[] col = find_most_null_column ();
        double det = 0;
        double A[] = new double[m]; //массив алгебраических дополнений
        if (str[1]>=col[1]){
            log_this ("Для подсчёта определителя будем использовать разложение в строку. Раскладываем по " + (str[0]+1) + " строке.");
            for (int i = 0; i < n; i++) {
                if (arr[str[0]][i] == 0) {
                    Log.add ("", "Так как a" + (str[0]+1) + (i+1) + " равно нулю, то считать A" + (str[0]+1) + (i+1) + " нет необходимости.");
                    A[i] = 0;
                }
                else A[i] = algebraic_complement (str[0], i);
            }
            String temp = "det = ", temp2 = "det = ", temp3 = "det =";
            for (int i = 0; i < n; i++) {
                det += A[i] * arr[col[0]][i];
                temp += "A" + (str[0]+1) + (i+1) + "*" + "a" + (str[0]+1) + (i+1) + " + ";
                temp2 += " " + A[i] + "*" + arr[str[0]][i] + " + ";
                temp3 += A[i]*arr[str[0]][i] + " + ";
            }
            temp = temp.substring (0, temp.length () - 3);
            temp2 = temp2.substring (0, temp2.length () - 3);
            temp3 = temp3.substring (0, temp3.length () - 3);
            Log.add (temp, "Для того, чтобы посчитать определитель при помощи строчки, надо вычислить сумму произведений элементов на их алгебраические дополнения.");
            Log.add (temp2, "");
            Log.add (temp3, "");
            Log.add ("det = " + det, "");
        }
        else{
            log_this ("Для подсчёта определителя будем использовать разложение в столбец. Раскладываем по " + (col[0]+1) + " столбцу.");
            for (int i = 0; i < n; i++) {
                if (arr[i][col[0]] == 0){
                    A[i] = 0;
                    Log.add ("", "Так как a" + (i+1) + (col[0]+1) + " равно нулю, то считать A" + (i+1) + (col[0]+1) + " нет необходимости.");
                }
                else A[i] = algebraic_complement (i, col[0]);
            }
            String temp = "det = ", temp2 = "det = ", temp3 = "det =";
            for (int i = 0; i < n; i++) {
                det += A[i] * arr[i][col[0]];
                temp += "A" + (i+1) + (col[0]+1)  + "*" + "a" + (i+1) + (col[0]+1) + " + ";
                temp2 += " " + A[i] + "*" + arr[i][col[0]] + " + ";
                temp3 += A[i]*arr[i][col[0]] + " + ";
            }
            temp = temp.substring (0, temp.length () - 3);
            temp2 = temp2.substring (0, temp2.length () - 3);
            temp3 = temp3.substring (0, temp3.length () - 3);
            Log.add (temp, "Для того, чтобы посчитать определитель при помощи столбца, надо вычислить сумму произведений элементов на их алгебраические дополнения.");
            Log.add (temp2, "");
            Log.add (temp3, "");
            Log.add ("det = " + det, "");
        }
        return det;
    }
    public void print(){
        for (int i = 0; i < m; i++){
            String temp = "";
            for (int j = 0; j < n; j++) temp = temp + Double.toString (arr[i][j]) + " ";
            System.out.println (temp);
        } 
    }
    public int rank_with_triangle() throws MRV.INVALID_NUMBER_STRING {
        Matrix copy = new Matrix (arr);
        copy.triangular_transformation ();
        Log.add("", "Ранг равен количеству ненулевых элементов на главной диагонали");
        int count = 0;
        for (int i = 0; i < m; i++)
        {
            if (copy.arr[i][i] != 0) count++;
        }
        return count;
    }
    public int rank_with_minors() throws MRV.MATRIX_DIMENSION_MISSMATCH {
        log_this ("Для нахождения ранга методом окламляющих миноров необходимо найти ненулевой элемент.");
        int a = 0, b = 0;
        boolean end = false;
        for (int i = 0; i < m && !end; i++) for (int j = 0; j < n && !end; j++) if (arr[i][j] != 0) {
            a = i;
            b = i;
            end = true;
        }
        Log.add ("a" + (a+1) + (b+1) + '\u2260' + " 0 ","" );
        Log.add("", "Теперь рассмотрим все миноры, в которые входит данный элемент.");
        double[][] temp_arr = new double [1][1];
        Matrix cur_minor = new Matrix (temp_arr);
        Integer[]cur_str = new Integer[1];
        Integer[]cur_col = new Integer[1];
        cur_str[0] = a;
        cur_col[0] = b;
        end = false;
        while (cur_minor.m < m && cur_minor.m < n){
            end = false;
            Integer[] temp_str = new Integer[cur_minor.m + 1];
            Integer[] temp_col = new Integer[cur_minor.m + 1];
            for (int k = 0; k < cur_minor.m; k++){
                temp_str[k] = cur_str[k];
                temp_col[k] = cur_col[k];
            }
            for (Integer i =0; i < m && !end; i++) for (Integer j = 0; j < n && !end; j++) if(!Arrays.asList (cur_col).contains (i) && !Arrays.asList (cur_str).contains (j)) {
                temp_str[cur_minor.m] = i;
                temp_col[cur_minor.m] = j;
                Matrix minor = minor (temp_str, temp_col);
                minor.log_this ("Проверим минор.");
                double det = 0;
                try {
                    det = minor.determinant ();
                } catch (MRV.NON_QUADRATIC_MATRIX | MRV.INVALID_NUMBER_STRING ignored) {
                }
                if (det!=0){
                    cur_minor = minor;
                    cur_minor.log_this ("Так как этот минор не равен нулю, то теперь мы будем рассматривать миноры, которые включают его");
                    cur_str = temp_str;
                    cur_col = temp_col;
                    end = true;
                }
            }
        }
        cur_minor.log_this ("Этот минор является базисным.");
        return cur_minor.m;
    }
    public void Transposition(){
        double[][] new_arr = new double[n][m];
        for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) new_arr[j][i] = arr[i][j];
        arr = new_arr;
        int temp = n;
        n = m;
        m = temp;
    }
    public int rank() throws MRV.INVALID_NUMBER_STRING {
        if (Settings.Rank.getSettings () == Rank.ELEMENTAL_ROW ){
            return rank_with_triangle ();
        }
        else return 0;
    }
    public Matrix get_inverse_gauss() throws MRV.MATRIX_DIMENSION_MISSMATCH, MRV.INVALID_NUMBER_STRING, MRV.DEGENERATE_MATRIX, MRV.NON_QUADRATIC_MATRIX {
        if (m==n){
            Matrix single = new Matrix (m);
            Matrix copy = new Matrix (arr);
            AugmentedMatrix temp = new AugmentedMatrix (copy, single);
            temp.log_this ("Чтобы найти обратную матрицу методом Гаусса необходимо дописать справа от неё единичную и методом Гаусса добиться того, чтобы слева была единичная.");
            temp.gauss_transformation ();
            if (temp.get_main ().is_single ()) return temp.get_augmentation ();
            else throw new MRV.DEGENERATE_MATRIX ();
        }
        else throw new MRV.NON_QUADRATIC_MATRIX ();
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

