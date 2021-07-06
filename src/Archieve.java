import java.util.ArrayList;
//token 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
public class Archieve {
    ArrayList<Operator> base = new ArrayList<>();
    ArrayList<Function> functions = new ArrayList<>();
    public Archieve(){
        int n = Id_lexemes.getId(Id_lexemes.NUMBER_OPERATORS);
        for (int i = 0; i < n; i++)
        {
            base.add(new Operator());
            functions.add(new Sin());
        }
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
        if (verif == null || verif.size() == 0)
        {
            for (int i = 0; i < Id_lexemes.getId(Id_lexemes.NUMBER_OPERATORS); i++)
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
        return base.get(Id_lexemes.getId(id)).get_priority();
    }
    int get_left_argue(Id_lexemes id)
    {
        if (Id_lexemes.getId(id) <= base.size()) return base.get(Id_lexemes.getId(id)).get_left_argue();
        else return 0;
    }
    int get_right_argue(Id_lexemes id)
    {
        if (Id_lexemes.getId(id) <= base.size()) return base.get(Id_lexemes.getId(id)).get_right_argue();
        else return 0;
    }
    boolean check_countable(Id_lexemes id, ArrayList <Double> argues)
    {
        return functions.get(Id_lexemes.getId(id)).check(argues);
    }
    double count(Id_lexemes id, ArrayList <Double> argues)
    {
        return functions.get(Id_lexemes.getId(id)).count(argues);
    }
}
