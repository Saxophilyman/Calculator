public class Main {

    public static void main(String[] args) {
        System.out.print(" Здравствуйте.");
        System.out.println(" Вы используете калькулятор, который сразу вычисляет арифметическое выражение для двух значений." +
                "\n Вы можете произвести такие арифметические операции как сложение, вычитание, умножение, деление и возведение в степень." +
                "\n Для этого воспользуйтесь условными знаками такими как '-', '+', '*', '/', '^' соответственно."+
                "\n Введите в консоль ваше выражение целиком и сразу же получите результат."+
                "\n Пример: Ввод выражения: 2-1. Вывод: 1. Всё просто! " +
                "\n P.S. Для обозначения дробной части воспользуйтесь символом точки \".\" " +
                "\n P.S.S Для выхода из калькулятора введите в консоль слово 'exit'." +
                "\n P.S.S.S Для данной версии калькулятора предусмотрено возведение ТОЛЬКО В НАТУРАЛЬНУЮ степень." +
                "\n         и ограничено размерами 999 999 999. Вы же осознаёте насколько это большое число на выходе?" +
                "\n         И что ждать ответа полчаса... Учитывайте эту информацию при вводе выражения."); // подумать о возведении в степень (оставить инт или найти вариант полного возведения)

        Calculator.startWorkingCalculator();

    }


}
