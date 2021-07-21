package Logger;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Log {
    private static ArrayList<String> objects = new ArrayList<> ();
    private static ArrayList<String> commits = new ArrayList<> ();
    public static void add(String object, String commit){
        objects.add (object);
        commits.add (commit);
    }
    public static void clear(){
        objects.clear ();
        commits.clear ();
    }
    public static void print_log(){
        PrintWriter printWriter = new PrintWriter(System.out,true);
        for (int i = 0; i < objects.size (); i++){
            printWriter.println (commits.get (i));
            printWriter.println (objects.get (i));
        }
    }
}