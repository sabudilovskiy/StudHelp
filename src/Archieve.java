import java.util.ArrayList;

public class Archieve {
    ArrayList<Operator> base = new ArrayList<>();
    public Archieve(){
        int n = Id_lexemes.NUMBER_OPERATORS.get();
        for (int i = 0; i < n; i++)
        {
            base.add(new Operator());
        }
        add_operator( new Abs() );
        add_operator(new Sin());
        add_operator(new Cos());
        add_operator(new Tg());
        add_operator(new Ctg());
        add_operator(new Arcsin());
        add_operator( new Arccos());
        add_operator( new Arctg() );
        add_operator( new Arcctg() );
        add_operator( new Exp() );
        add_operator( new Ln() );
        add_operator( new Log() );
        add_operator( new Pow() );
        add_operator( new Mult() );
        add_operator( new Div() );
        add_operator( new Plus() );
        add_operator( new Mult() );
    }
    public void add_operator(Operator A)
    {
        int n = A.get_id();
        base.set(n, A);
    }
    ArrayList<Integer> decode(String input, ArrayList <Integer> verif)
    {
        ArrayList<Integer> answer = new ArrayList<>();
        if (verif == null || verif.size() == 0)
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
        return base.get(id.get()).check(argues);
    }
    double count(Id_lexemes id, ArrayList <Double> argues)
    {
        return base.get(id.get()).count(argues);
    }
}
