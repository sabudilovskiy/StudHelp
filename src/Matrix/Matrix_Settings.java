package Matrix;

import MRV.MRV;
import Matrix.det_methods.det_methods;

public class Matrix_Settings {
    private static det_methods settings[] = new det_methods[3];
    private static int border = 4;
    public static void setdefaultSettings(){
        settings[0] = det_methods.BASIC;
        settings[1] = det_methods.BASIC;
        settings[2] = det_methods.SARUSS;
    }
    public static void setSettings(det_methods second, det_methods thirst, int border){
        settings[0] = det_methods.BASIC;
        settings[1] = second;
        settings[2] = thirst;
        Matrix_Settings.border = border;
    }
    public static det_methods get_method(int n) throws MRV.MATRIX_DIMENSION_MISSMATCH {
        if (n>0){
            if (n >= border) return det_methods.GAUSS;
            else {
                if (n>3) return det_methods.LAPLASS;
                else return settings[n-1];
            }
        }
        else {
            throw new MRV.MATRIX_DIMENSION_MISSMATCH ();
        }
    }
}
