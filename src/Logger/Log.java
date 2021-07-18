package Logger;

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
        for (int i = 0; i < objects.size (); i++){
            System.out.println (objects.get (i));
            System.out.println (commits.get (i));
        }
    }
}