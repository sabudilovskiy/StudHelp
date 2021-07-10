package MRV;

import java.util.ArrayList;

public class Operator {
    protected Id_lexemes id;
    protected Function func = new F_Sin();
    protected int left_argue = 0;
    protected int right_argue = 0;
    protected int priority = 0;
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
			else if (decode_base.get(i).length() >= input.length() && decode_base.get(i).startsWith(input)) {
            max_code = 1;
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
    }
    public String code(){
        return decode_base.get( 0 );
    }
//    protected void load_decode_base(String src){
//        if (src.equals ( "" )) return;
//        else {
//            try {
//                FileReader localisation_file = new FileReader (src);
//                BufferedReader scan = new BufferedReader ( localisation_file );
//                String line;
//                while ((line = scan.readLine ()) != null){
//                    if (line.equals ("")!=true) decode_base.add ( line );
//                }
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace ();
//            } catch (IOException e) {
//                e.printStackTrace ();
//            }
//        }
//
//    }
}
class Argument extends Operator{
    Argument(){
        id = Id_lexemes.ARGUMENT;
    }
}
class Variable extends Operator{
    Variable( ArrayList<String> decode_base){
        id = Id_lexemes.VARIABLE;
        this.decode_base = decode_base;
    }
}
class Left_br extends Operator{
    Left_br(){
        id = Id_lexemes.LEFT_BR;
        decode_base.add( "(" );
    }
}
class Right_br extends Operator{
    Right_br(){
        id = Id_lexemes.RIGHT_BR;
        decode_base.add( ")" );
    }
}
class Comma extends Operator{
    Comma(){
        id = Id_lexemes.COMMA;
        decode_base.add( "," );
    }
}
class Abs extends Operator{
    Abs(){
        decode_base.add("abs");
        id = Id_lexemes.ABS;
        left_argue = 0;
        right_argue = 1;
        func = new F_Abs();
        priority = Priority_operators.getId( Priority_operators.PRIOR_FUNC );
    }
}
class Sin extends Operator{
    Sin(){
        decode_base.add("sin");
        id = Id_lexemes.SIN;
        left_argue = 0;
        right_argue = 1;
        func = new F_Sin();
        priority = Priority_operators.getId( Priority_operators.PRIOR_FUNC );
    }
}
class Cos extends Operator{
    Cos(){
        decode_base.add("cos");
        id = Id_lexemes.COS;
        left_argue = 0;
        right_argue = 1;
        func = new F_Cos();
        priority = Priority_operators.getId( Priority_operators.PRIOR_FUNC );
    }
}
class Tg extends Operator{
    Tg(){
        decode_base.add("tg");
        decode_base.add("tan");
        id = Id_lexemes.TG;
        left_argue = 0;
        right_argue = 1;
        func = new F_Tg();
        priority = Priority_operators.getId( Priority_operators.PRIOR_FUNC );
    }
}
class Ctg extends Operator{
    Ctg(){
        decode_base.add("ctg");
        decode_base.add("cot");
        decode_base.add("cotan");
        id = Id_lexemes.CTG;
        left_argue = 0;
        right_argue = 1;
        func = new F_Ctg();
        priority = Priority_operators.getId( Priority_operators.PRIOR_FUNC );
    }
}
class Arcsin extends Operator{
    Arcsin(){
        decode_base.add("arcsin");
        decode_base.add("asin");
        id = Id_lexemes.ARCSIN;
        left_argue = 0;
        right_argue = 1;
        func = new F_Arcsin();
        priority = Priority_operators.getId( Priority_operators.PRIOR_FUNC );
    }
}
class Arccos extends Operator{
    Arccos(){
        decode_base.add("arccos");
        decode_base.add("acos");
        id = Id_lexemes.ARCCOS;
        left_argue = 0;
        right_argue = 1;
        func = new F_Arccos();
        priority = Priority_operators.getId( Priority_operators.PRIOR_FUNC );
    }
}
class Arctg extends Operator{
    Arctg(){
        decode_base.add("arctg");
        decode_base.add("arctan");
        decode_base.add("atg");
        decode_base.add("atan");
        id = Id_lexemes.ARCTG;
        left_argue = 0;
        right_argue = 1;
        func = new F_Arctg();
        priority = Priority_operators.getId( Priority_operators.PRIOR_FUNC );
    }
}
class Arcctg extends Operator{
    Arcctg(){
        decode_base.add("arcctg");
        decode_base.add("arccot");
        decode_base.add("arccotan");
        decode_base.add("actg");
        decode_base.add("acot");
        decode_base.add("acotan");
        id          = Id_lexemes.ARCCTG;
        left_argue  = 0;
        right_argue = 1;
        func        = new F_Arcctg();
        priority    = Priority_operators.getId(Priority_operators.PRIOR_FUNC);
    }
}
class Exp extends Operator{
    Exp(){
        decode_base.add("exp");
        id = Id_lexemes.EXP;
        left_argue = 0;
        right_argue = 1;
        func = new F_Exp();
        priority = Priority_operators.getId( Priority_operators.PRIOR_FUNC );
    }
}
class Ln extends Operator{
    Ln(){
        decode_base.add("ln");
        id = Id_lexemes.LN;
        left_argue = 0;
        right_argue = 1;
        func = new F_Ln();
        priority = Priority_operators.getId( Priority_operators.PRIOR_FUNC );
    }
}
class Log extends Operator{
    Log(){
        decode_base.add("log");
        id = Id_lexemes.LOG;
        left_argue = 0;
        right_argue = 2;
        func = new F_Log();
        priority = Priority_operators.getId( Priority_operators.PRIOR_FUNC );
    }
}
class Pow extends Operator{
    Pow(){
       decode_base.add("^");
        id = Id_lexemes.POW;
        left_argue = 1;
        right_argue = 1;
        func = new F_Pow();
        priority = Priority_operators.getId( Priority_operators.PRIOR_POW );
    }
}
class Mult extends Operator{
    Mult(){
        decode_base.add("*");
        decode_base.add("×");
        decode_base.add("⋅");
        decode_base.add("∙");
        decode_base.add("·");
        id = Id_lexemes.MULT;
        left_argue = 1;
        right_argue = 1;
        func = new F_Mult();
        priority = Priority_operators.getId( Priority_operators.PRIOR_MULT_DIV );
    }
}
class Div extends Operator{
    Div(){
        decode_base.add("/");
        decode_base.add("÷");
        decode_base.add("∶");
        id = Id_lexemes.DIV;
        left_argue = 1;
        right_argue = 1;
        func = new F_Div();
        priority = Priority_operators.getId( Priority_operators.PRIOR_MULT_DIV );
    }
}
class Plus extends Operator{
    Plus(){
        decode_base.add("+");
        id = Id_lexemes.PLUS;
        left_argue = 1;
        right_argue = 1;
        func = new F_Plus();
        priority = Priority_operators.getId( Priority_operators.PRIOR_PLUS_MINUS );
    }
}
class Minus extends Operator{
    Minus(){
        decode_base.add("—");
        decode_base.add("–");
        decode_base.add("−");
        decode_base.add("-");
        id = Id_lexemes.MINUS;
        left_argue = 1;
        right_argue = 1;
        func = new F_Minus();
        priority = Priority_operators.getId( Priority_operators.PRIOR_PLUS_MINUS );
    }
}