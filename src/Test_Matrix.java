import Logger.Log;
import MRV.MRV;
import Matrix.Matrix;
import Matrix.Settings;

import java.util.Scanner;

public class Test_Matrix {
    public static void main( String[] args ) {
        Scanner scan = new Scanner(System.in);
        int m;
        System.out.println("Введите m: ");
        m = Integer.parseInt(scan.nextLine());
        int n;
        System.out.println("Введите n: ");
        n = Integer.parseInt(scan.nextLine());
        double[][] arr = new double[m][n];
        for (int i = 0; i < m; i++){
            String temp = "Введите " + (i+1) + " строку: ";
            System.out.println (temp);
            String [] buffer = scan.nextLine ().split(" ");
            for (int j = 0; j < n; j++) arr[i][j] = Double.parseDouble (buffer[j]);
        }
        Matrix temp = new Matrix (arr);
        Settings.Det.setdefaultSettings();
        Settings.Det.setBorder (4);
        try {
            temp.rank_with_minors ();
        } catch (MRV.MATRIX_DIMENSION_MISSMATCH ignored) {
        } finally {
            Log.print_log ();
        }
    }
}
