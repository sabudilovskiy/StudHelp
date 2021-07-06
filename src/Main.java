import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double x;
        Lexeme answer;
        Archieve archieve = new Archieve();
        {
            //из-за специфики работы приоритетов, 0 - должен быть закреплён за отсутствием каких-либо операторов. Он - конец вычислений.
            Operator cur_op;
            ArrayList<String> cur_base;
            int prior_func = 5;
            int prior_pow = 4;
            int prior_mult_div = 3;
            int prior_plus_minus = 2;
            cur_base = new ArrayList<>();
            cur_base.add("abs");
            cur_op = new Operator(Id_lexemes.ABS, 0, 1, prior_func, cur_base);
            archieve.add_operator(cur_op, new Abs());

            cur_base = new ArrayList<>();
            cur_base.add("sin");
            cur_op = new Operator(Id_lexemes.SIN, 0, 1, prior_func, cur_base);
            archieve.add_operator(cur_op, new Sin());

            cur_base = new ArrayList<>();
            cur_base.add("cos");
            cur_op = new Operator(Id_lexemes.COS, 0, 1, prior_func, cur_base);
            archieve.add_operator(cur_op, new Cos());

            cur_base = new ArrayList<>();
            cur_base.add("tg");
            cur_base.add("tan");
            cur_op = new Operator(Id_lexemes.TG, 0, 1, prior_func, cur_base);
            archieve.add_operator(cur_op, new Tg());

            cur_base = new ArrayList<>();
            cur_base.add("ctg");
            cur_base.add("cotan");
            cur_base.add("cot");
            cur_op = new Operator(Id_lexemes.CTG, 0, 1, prior_func, cur_base);
            archieve.add_operator(cur_op, new Ctg());

            cur_base = new ArrayList<>();
            cur_base.add("arcsin");
            cur_op = new Operator(Id_lexemes.ARCSIN, 0, 1, prior_func, cur_base);
            archieve.add_operator(cur_op, new Arcsin());

            cur_base = new ArrayList<>();
            cur_base.add("arccos");
            cur_op = new Operator(Id_lexemes.ARCCOS, 0, 1, prior_func, cur_base);
            archieve.add_operator(cur_op, new Arccos());

            cur_base = new ArrayList<>();
            cur_base.add("arctg");
            cur_base.add("arctan");
            cur_op = new Operator(Id_lexemes.ARCTG, 0, 1, prior_func, cur_base);
            archieve.add_operator(cur_op, new Arctg());

            cur_base = new ArrayList<>();
            cur_base.add("arcctg");
            cur_base.add("arccot");
            cur_base.add("arccotan");
            cur_op = new Operator(Id_lexemes.ARCCTG, 0, 1, prior_func, cur_base);
            archieve.add_operator(cur_op, new Arcctg());

            cur_base = new ArrayList<>();
            cur_base.add("exp");
            cur_op = new Operator(Id_lexemes.EXP, 0, 1, prior_func, cur_base);
            archieve.add_operator(cur_op, new Exp());

            cur_base = new ArrayList<>();
            cur_base.add("log");
            cur_op = new Operator(Id_lexemes.LOG, 0, 2, prior_func, cur_base);
            archieve.add_operator(cur_op, new Log());

            cur_base = new ArrayList<>();
            cur_base.add("ln");
            cur_op = new Operator(Id_lexemes.LN, 0, 1, prior_func, cur_base);
            archieve.add_operator(cur_op, new Ln());

            cur_base = new ArrayList<>();
            cur_base.add("^");
            cur_op = new Operator(Id_lexemes.POW, 1, 1, prior_pow, cur_base);
            archieve.add_operator(cur_op, new Pow());

            cur_base = new ArrayList<>();
            cur_base.add("*");
            cur_op = new Operator(Id_lexemes.MULT, 1, 1, prior_mult_div, cur_base);
            archieve.add_operator(cur_op, new Mult());

            cur_base = new ArrayList<>();
            cur_base.add("/");
            cur_op = new Operator(Id_lexemes.DIV, 1, 1, prior_mult_div, cur_base);
            archieve.add_operator(cur_op, new Div());
            
            cur_base = new ArrayList<>();
            cur_base.add("+");
            cur_op = new Operator(Id_lexemes.PLUS, 1, 1, prior_plus_minus, cur_base);
            archieve.add_operator(cur_op, new Plus());
            
            cur_base = new ArrayList<>();
            cur_base.add("-");
            cur_op = new Operator(Id_lexemes.MINUS, 1, 1, prior_plus_minus, cur_base);
            archieve.add_operator(cur_op, new Minus());
            
        }
        String input = "";
        Scanner printer = new Scanner(System.in);
        input = printer.nextLine();
        Sentence check = new Sentence(input, archieve);
        input = printer.nextLine();
        x = Double.parseDouble(input);
        check.substitute(x);
        answer = check.count(archieve);
        if (ErrorHandler.error == Id_errors.NON_ERROR)
        {
            System.out.println(answer.get_value());
        }
        else if (ErrorHandler.error == Id_errors.UNKNOWN_FUNCTION)
        {
            System.out.println("Неизвестная функция.");
        }
        else if (ErrorHandler.error == Id_errors.ERROR_SIGNS)
        {
            System.out.println("Какое-то из чисел записано с ошибкой: слишком много точек.");
        }
        else if (ErrorHandler.error == Id_errors.IMPOSSIBLE_COUNT)
        {
            System.out.println("Функцию в заданной точке невозможно вычислить.");
        }
        else if (ErrorHandler.error == Id_errors.MISS_ARGUMENT_BINARY_OPERATOR)
        {
            System.out.println("У какого-то из бинарных операторов отсутствует аргумент.");
        }
        else if (ErrorHandler.error == Id_errors.MISS_ARGUMENT_PRE_OPERATOR)
        {
            System.out.println("У какого-то из преоператоров отсутствует аргумент.");
        }
        else if (ErrorHandler.error == Id_errors.MISS_ARGUMENT_POST_OPERATOR)
        {
            System.out.println("У какого-то из постоператоров отсутствует аргумент. ");
        }
        else if (ErrorHandler.error == Id_errors.HAVE_OPEN_BRACKETS)
        {
            System.out.println("Не все скобки закрыты.");
        }
        else if (ErrorHandler.error == Id_errors.MORE_RIGHT_BRACKETS)
        {
            System.out.println("Закрыто больше скобок, чем открыто.");
        }
        else if (ErrorHandler.error == Id_errors.BAD_ARGUMENTS)
        {
            System.out.println("У какого-то из операторов не соответствует число аргументов.");
        }
        else
        {
            System.out.println("Неизвестная ошибка.");
        }
    }
}
