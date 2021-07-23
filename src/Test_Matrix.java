import Logger.Log;
import MRV.MRV;
import Matrix.AugmentedMatrix;
import Matrix.Matrix;
import Matrix.Settings;

import java.util.Scanner;

public class Test_Matrix {
    public static void main( String[] args ) throws MRV.MATRIX_DIMENSION_MISSMATCH, MRV.INVALID_NUMBER_STRING, MRV.HAVE_NOT_SOLUTIONS, MRV.NON_SINGLE {
        Scanner scan = new Scanner(System.in);
        int m;
        System.out.println("Введите m: ");
        m = Integer.parseInt(scan.nextLine());
        int n;
        System.out.println("Введите n: ");
        n = Integer.parseInt(scan.nextLine());
        double[][] arr = new double[m][n];
        double[][] augmenated_arr = new double[m][1];
        for (int i = 0; i < m; i++){
            String temp = "Введите " + (i+1) + " строку: ";
            System.out.println (temp);
            String [] buffer = scan.nextLine ().split(" ");
            for (int j = 0; j < n; j++) arr[i][j] = Double.parseDouble (buffer[j]);
            temp =  "Введите " + (i+1) + " свободный член: ";
            System.out.println (temp);
            augmenated_arr[i][0] = Double.parseDouble (scan.nextLine ());
        }
        AugmentedMatrix temp = new AugmentedMatrix (arr, augmenated_arr);
        Settings.Det.setdefaultSettings();
        Settings.Det.setBorder (4);
        Object[] temp2 = new Object[0];
        try {
            temp2  = temp.solve_system ();
        } catch (MRV.MATRIX_DIMENSION_MISSMATCH | MRV.NOT_IMPLEMENT ignored) {
        } finally {
            Log.print_log ();
            for (int i = 0; i < temp2.length; i++) {
                if (temp2[i] instanceof Matrix) {
                    Matrix buffer = (Matrix) temp2[i];
                    System.out.println ((buffer.decode_this ()));
                }
            }
        }
    }
}
