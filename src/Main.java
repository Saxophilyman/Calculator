import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.print(" Здравствуйте.");
        System.out.println(" Вы используете калькулятор, который сразу вычисляет арифметическое выражение для двух значений." +
                "\n Вы можете произвести такие арифметические операции как сложение, вычитание, умножение, деление и возведение в степень." +
                "\n Для этого воспользуйтесь условными знаками такими как '-', '+', '*', '/', '^' соответственно."+
                "\n Введите в консоль ваше выражение целиком и сразу же получите результат."+
                "\n Пример: Ввод выражения: 2-1. Вывод: 1. Всё просто! " +
                "\n P.S. Для выхода из калькулятора введите в консоль слово 'exite'.");

        Calculator.startWorkingCalculator();

    }


}
