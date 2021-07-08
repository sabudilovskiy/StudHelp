import java.util.ArrayList;

public class Operator {
    protected Id_lexemes id;
    protected Function func = new F_Sin();
    protected int left_argue = 0;
    protected int right_argue = 0;
    protected int priority;
    protected ArrayList<String> decode_base = new ArrayList<>();
    public int get_id(){
        return Id_lexemes.getId(id);
    }
    public int get_left_argue()
    {
        return left_argue;
    }
    public int get_right_argue()
    {
        return right_argue;
    }
    public int is_it(String input)
    {
        int max_code = 0;
        for (int i = 0; i < decode_base.size(); i++)
        {
            if (decode_base.get(i).length() == input.length() && decode_base.get(i).equals(input)) return 2;
			else if (decode_base.get(i).length() >= input.length())
        {
            int k = 1;
            for (int j = 0; j < input.length(); j++) if (decode_base.get(i).charAt(j) != input.charAt(j)) k = 0;
            max_code = k;
        }
        }
        return max_code;
    }
    public int get_priority()
    {
        return priority;
    }

    public double count(ArrayList<Double> args){
        return func.count(args);
    }
    public boolean check(ArrayList<Double> args){
        return func.check(args);
    }
    //id, количество аргументов слева, количество аргументов справа, приоритет, количество кодовых слов, кодовые слова
    Operator(Function func, Id_lexemes id, int left_argue, int right_argue, int priority, ArrayList<String> list_of_words)
    {
        this.id = id;
        this.left_argue = left_argue;
        this.right_argue = right_argue;
        this.priority = priority;
        for (int i = 0; i < list_of_words.size(); i++)
        {
            decode_base.add(list_of_words.get(i));
        }
    }
    Operator()
    {
        priority = 0;
    }
    public String code(){
        return decode_base.get( 0 );
    }
}
class Abs extends Operator
{
    Abs(){
        id = Id_lexemes.ABS;
        left_argue = 0;
        right_argue = 1;
        func = new F_Abs();
        priority = Priority_operators.getId( Priority_operators.PRIOR_FUNC );
        decode_base.add( "abs" );
    }

}
class Sin extends Operator{
    Sin(){
        id = Id_lexemes.SIN;
        left_argue = 0;
        right_argue = 1;
        func = new F_Sin();
        priority = Priority_operators.getId( Priority_operators.PRIOR_FUNC );
        decode_base.add( "sin" );
    }
}
class Cos extends Operator{
    Cos(){
        id = Id_lexemes.COS;
        left_argue = 0;
        right_argue = 1;
        func = new F_Cos();
        priority = Priority_operators.getId( Priority_operators.PRIOR_FUNC );
        decode_base.add( "cos" );
    }
}
class Tg extends Operator{
    Tg(){
        id = Id_lexemes.TG;
        left_argue = 0;
        right_argue = 1;
        func = new F_Tg();
        priority = Priority_operators.getId( Priority_operators.PRIOR_FUNC );
        decode_base.add( "tg" );
        decode_base.add("tan");
    }
}
class Ctg extends Operator{
    Ctg(){
        id = Id_lexemes.CTG;
        left_argue = 0;
        right_argue = 1;
        func = new F_Ctg();
        priority = Priority_operators.getId( Priority_operators.PRIOR_FUNC );
        decode_base.add( "ctg" );
        decode_base.add("cotan");
        decode_base.add( "cot" );
    }
}
class Arcsin extends Operator{
    Arcsin(){
        id = Id_lexemes.ARCSIN;
        left_argue = 0;
        right_argue = 1;
        func = new F_Arcsin();
        priority = Priority_operators.getId( Priority_operators.PRIOR_FUNC );
        decode_base.add( "arcsin" );
    }
}
class Arccos extends Operator{
    Arccos(){
        id = Id_lexemes.ARCCOS;
        left_argue = 0;
        right_argue = 1;
        func = new F_Arccos();
        priority = Priority_operators.getId( Priority_operators.PRIOR_FUNC );
        decode_base.add( "arccos" );
    }
}
class Arctg extends Operator{
    Arctg(){
        id = Id_lexemes.ARCTG;
        left_argue = 0;
        right_argue = 1;
        func = new F_Arctg();
        priority = Priority_operators.getId( Priority_operators.PRIOR_FUNC );
        decode_base.add( "arctg" );
        decode_base.add("arctan");
    }
}
class Arcctg extends Operator{
    Arcctg(){
        id = Id_lexemes.ARCCTG;
        left_argue = 0;
        right_argue = 1;
        func = new F_Arcctg();
        priority = Priority_operators.getId( Priority_operators.PRIOR_FUNC );
        decode_base.add( "arcctg" );
        decode_base.add("arccotan");
        decode_base.add( "arccot" );
    }
}
class Exp extends Operator{
    Exp(){
        id = Id_lexemes.EXP;
        left_argue = 0;
        right_argue = 1;
        func = new F_Exp();
        priority = Priority_operators.getId( Priority_operators.PRIOR_FUNC );
        decode_base.add( "exp" );
    }
}
class Ln extends Operator{
    Ln(){
        id = Id_lexemes.LN;
        left_argue = 0;
        right_argue = 1;
        func = new F_Ln();
        priority = Priority_operators.getId( Priority_operators.PRIOR_FUNC );
        decode_base.add( "ln" );
    }
}
class Log extends Operator{
    Log(){
        id = Id_lexemes.LOG;
        left_argue = 0;
        right_argue = 2;
        func = new F_Log();
        priority = Priority_operators.getId( Priority_operators.PRIOR_FUNC );
        decode_base.add( "log" );
    }
}
class Pow extends Operator{
    Pow(){
        id = Id_lexemes.POW;
        left_argue = 1;
        right_argue = 1;
        func = new F_Pow();
        priority = Priority_operators.getId( Priority_operators.PRIOR_POW );
        decode_base.add( "^" );
    }
}
class Mult extends Operator{
    Mult(){
        id = Id_lexemes.MULT;
        left_argue = 1;
        right_argue = 1;
        func = new F_Mult();
        priority = Priority_operators.getId( Priority_operators.PRIOR_MULT_DIV );
        decode_base.add( "*" );
    }
}
class Div extends Operator{
    Div(){
        id = Id_lexemes.DIV;
        left_argue = 1;
        right_argue = 1;
        func = new F_Div();
        priority = Priority_operators.getId( Priority_operators.PRIOR_MULT_DIV );
        decode_base.add( "/" );
    }
}
class Minus extends Operator{
    Minus(){
        id = Id_lexemes.MINUS;
        left_argue = 1;
        right_argue = 1;
        func = new F_Minus();
        priority = Priority_operators.getId( Priority_operators.PRIOR_PLUS_MINUS );
        decode_base.add( "-" );
        decode_base.add( "—" );
    }
}
class Plus extends Operator{
    Plus(){
        id = Id_lexemes.PLUS;
        left_argue = 1;
        right_argue = 1;
        func = new F_Plus();
        priority = Priority_operators.getId( Priority_operators.PRIOR_PLUS_MINUS );
        decode_base.add( "+" );
    }
}