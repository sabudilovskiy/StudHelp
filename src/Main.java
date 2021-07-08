import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double x;
        Lexeme answer;
        Archieve archieve = new Archieve();
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
