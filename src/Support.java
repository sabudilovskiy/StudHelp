import java.util.ArrayList;

public class Support {
    static ArrayList<Double> union (ArrayList<Double> left, ArrayList<Double> right)
    {
        if (left.size() != 0)
        {
            if (right.size() == 0) return left;
            else
            {
                for (Double element : right)
                {
                    left.add(element);
                }
                return left;
            }
        }
        else return right;
    }
    static boolean is_numeral(char a)
    {
        if ('0' <= a && a <= '9') return true;
	else return false;
    }
}
