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
        try {
            System.out.println(MRV.count_lexemes(input, keys, values));
        } catch (MRV_ARGUMENT_LIST_MISMATCH mrv_argument_list_mismatch) {
            System.out.println("Списки аргументов не соответствуют.");
        } catch (MRV_UNKNOWN_FUNCTION mrv_unknown_function) {
            System.out.println("Неизвестная функция.");
        } catch (MRV_ERROR_SIGNS mrv_error_signs) {
            System.out.println("Какое-то из чисел записано с ошибкой: слишком много точек.");
        } catch (MRV_IMPOSSIBLE_COUNT mrv_impossible_count) {
            System.out.println("Функцию в заданной точке невозможно вычислить.");
        } catch (MRV_MISS_ARGUMENT_BINARY_OPERATOR mrv_miss_argument_binary_operator) {
            System.out.println("У какого-то из бинарных операторов отсутствует аргумент.");
        } catch (MRV_MISS_ARGUMENT_PRE_OPERATOR mrv_miss_argument_pre_operator) {
            System.out.println("У какого-то из преоператоров отсутствует аргумент.");
        } catch (MRV_MISS_ARGUMENT_POST_OPERATOR mrv_miss_argument_post_operator) {
            System.out.println("У какого-то из постоператоров отсутствует аргумент.");
        } catch (MRV_HAVE_OPEN_BRACKETS mrv_have_open_brackets) {
            System.out.println("Не все скобки закрыты.");
        } catch (MRV_MORE_RIGHT_BRACKETS mrv_more_right_brackets) {
            System.out.println("Закрыто больше скобок, чем открыто.");
        } catch (MRV_BAD_ARGUMENTS mrv_bad_arguments) {
            System.out.println("У какого-то из операторов не соответствует число аргументов.");
        } catch (MRV_UNKNOWN_ERROR mrv_unknown_error) {
            System.out.println("Неизвестная ошибка.");
        }
    }
}
