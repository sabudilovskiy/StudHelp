import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите количество аргументов: ");
        int n = Integer.parseInt(scan.nextLine());
        ArrayList<String> keys = new ArrayList<>();
        ArrayList<Double> values = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.println("Введите переменную: ");
            keys.add( scan.nextLine());
            System.out.println("Введите значение переменной: ");
            values.add(Double.parseDouble(scan.nextLine()));
        }
        System.out.println("Введите функцию: ");
        String input = scan.nextLine();
        String temp=  "";
        try {
            System.out.println(MRV.count_lexemes(input, keys, values));
        } catch (MRV_ARGUMENT_LIST_MISMATCH error) {
           temp = "Списки аргументов не соответствуют.";
        } catch (MRV_UNKNOWN_FUNCTION error) {
            temp = "Неизвестная функция от : " + Integer.toString (error.getError_begin ()) + " до: " + Integer.toString (error.getError_end ());
        } catch (MRV_ERROR_SIGNS error) {
             temp = "Какое-то из чисел записано с ошибкой: слишком много точек." + "Место ошибки: " + Integer.toString (error.getError_begin ());
        } catch (MRV_IMPOSSIBLE_COUNT error) {
             temp = "Функцию в заданной точке невозможно вычислить. Начало функции: " + Integer.toString (error.getError_begin ()) + " конец: " + Integer.toString (error.getError_end ());
        } catch (MRV_MISS_ARGUMENT_BINARY_OPERATOR error) {
           temp = "У какого-то из бинарных операторов отсутствует аргумент." + "Ошибка от: " + Integer.toString (error.getError_begin ()) + " до: " + Integer.toString (error.getError_end ());
        } catch (MRV_MISS_ARGUMENT_PRE_OPERATOR error) {
            temp = "У какого-то из преоператоров отсутствует аргумент." + "Ошибка от: " + Integer.toString (error.getError_begin ()) + " до: " + Integer.toString (error.getError_end ());
        } catch (MRV_MISS_ARGUMENT_POST_OPERATOR error) {
            temp = "У какого-то из постоператоров отсутствует аргумент." + "Ошибка от: " + Integer.toString (error.getError_begin ()) + " до: " + Integer.toString (error.getError_end ());
        } catch (MRV_HAVE_OPEN_BRACKETS error) {
            temp = "Есть незакрытая скобка." + "Не закрыта: " + Integer.toString (error.getError_begin ());
        } catch (MRV_MORE_RIGHT_BRACKETS error) {
            temp = "Закрыто больше скобок, чем открыто.";
        } catch (MRV_BAD_ARGUMENTS error) {
            temp = "У какого-то операторов недостаточно или слишком много аргументов." + "Ошибка от: " + Integer.toString (error.getError_begin ()) + " до: " + Integer.toString (error.getError_end ());
        } catch (MRV_UNKNOWN_ERROR error) {
            temp = "Неизвестная ошибка.";
        }
        System.out.println(temp);
    }
}
