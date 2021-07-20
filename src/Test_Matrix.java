import Logger.Log;
import MRV.MRV;
import Matrix.Matrix;
import Matrix.Matrix_Settings;

import java.util.ArrayList;
import java.util.Scanner;

public class Test_Matrix {
    public static void main( String[] args ) {
        Scanner scan = new Scanner(System.in);
        int n;
        System.out.println("Введите m: ");
        n = Integer.parseInt(scan.nextLine());
        int m;
        System.out.println("Введите n: ");
        m = Integer.parseInt(scan.nextLine());
        double[][] arr = new double[m][n];
        for (int i = 0; i < m; i++){
            String temp = "Введите " + Integer.toString (i) + " строку: ";
            System.out.println (temp);
            String [] buffer = scan.nextLine ().split(" ");
            for (int j = 0; j < n; j++) arr[i][j] = Double.parseDouble (buffer[j]);
        }
        Matrix temp = new Matrix (arr);
        try {
            Matrix_Settings.setdefaultSettings();
            System.out.println ("Определитель равен " + temp.determinant ());
            Log.print_log ();
        } catch (MRV.INVALID_NUMBER_STRING | MRV.NON_QUADRATIC_MATRIX ignored) {
        }
    }
}
