package Matrix;

import MRV.MRV;
public class Settings {
    public static class Det {
        private static Parameters.Det settings[] = new Parameters.Det[3];
        private static int border = 5;
        public static void setdefaultSettings(){
            settings[0] = Parameters.Det.BASIC;
            settings[1] = Parameters.Det.BASIC;
            settings[2] = Parameters.Det.SARUSS;
        }
        public static void setSettings( Parameters.Det second, Parameters.Det thirst, int border){
            settings[0] = Parameters.Det.BASIC;
            settings[1] = second;
            settings[2] = thirst;
            Det.border = border;
        }
        public static void setBorder(int border){
            Det.border = border;
        }
        public static Parameters.Det get_det_method( int n) throws MRV.MATRIX_DIMENSION_MISSMATCH {
            if (n>0){
                if (n >= border) return Parameters.Det.TRIANGLE;
                else {
                    if (n>3) return Parameters.Det.LAPLASS;
                    else return settings[n-1];
                }
            }
            else {
                throw new MRV.MATRIX_DIMENSION_MISSMATCH ();
            }
        }
    }
    public static class Rank{
        static private Parameters.Rank Settings = Parameters.Rank.ELEMENTAL_ROW;
        public static void setSettings( Parameters.Rank settings ) {
            Settings = settings;
        }
        public static Parameters.Rank getSettings() {
            return Settings;
        }
    }
    public static class Inverse{}
}
