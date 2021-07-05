import java.util.ArrayList;

public class Archieve {
    ArrayList<Operator> base = new ArrayList<>(Id_lexemes.NUMBER_OPERATORS.get());
    ArrayList<Function> functions = new ArrayList<Function>(Id_lexemes.NUMBER_OPERATORS.get());
    public Archieve(){
    }
    public void add_operator(Operator A, Function function)
    {
        int n = A.get_id();
        base.set(n, A);
        this.functions.set(n, function);
    }
    ArrayList<Integer> decode(String input, ArrayList <Integer> verif)
    {
        ArrayList<Integer> answer = new ArrayList<>();
        if (verif.size() != 0)
        {
            for (int i = 0; i < Id_lexemes.NUMBER_OPERATORS.get(); i++)
            {
                int check = base.get(i).is_it(input);
                if (check == 2)
                {
                    answer.add(i);
                }
                else if (check == 1)
                {
                    answer.add(-i);
                }
            }
        }
        else
        {
            for (int i = 0; i < verif.size(); i++)
            {
                int b = Math.abs(verif.get(i));
                int check = base.get(b).is_it(input);
                if (check == 2)
                {
                    answer.add(b);
                }
                else if (check == 1)
                {
                    answer.add(-b);
                }
            }
        }
        return answer;
    }
    int get_priority(Id_lexemes id)
    {
        return base.get(id.get()).get_priority();
    }
    int get_left_argue(Id_lexemes id)
    {
        if (id.get() <= base.size()) return base.get(id.get()).get_left_argue();
        else return 0;
    }
    int get_right_argue(Id_lexemes id)
    {
        if (id.get() <= base.size()) return base.get(id.get()).get_right_argue();
        else return 0;
    }
    boolean check_countable(Id_lexemes id, ArrayList <Double> argues)
    {
        return functions.get(id.get()).check(argues);
    }
    double count(Id_lexemes id, ArrayList <Double> argues)
    {
        return functions.get(id.get()).count(argues);
    }
}
