import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class Calculator {
    private final static ArrayList<String> listOfAllOperations = new ArrayList<>(Arrays.asList("-", "+", "*", "/", "^"));
    static private StringBuilder firstArgument = new StringBuilder();
    static private StringBuilder secondArgument = new StringBuilder();
    static private String readConsole = null;
    static private String operationSymbol;
    static private String pointForFirstArgument;
    static private String pointForSecondArgument;

    private static void printMassage(String message) {
        System.out.println(message);
    }

    public static void startWorkingCalculator() {
        firstArgument.delete(0, firstArgument.length());
        secondArgument.delete(0, secondArgument.length());
        operationSymbol = null;
        pointForFirstArgument = null;
        pointForSecondArgument = null;
        readConsole();
        while (isUserRequestsExit(readConsole)) {
            checkReadConsoleIsNumber(readConsole);
            printMassage("\nВведите в консоль ваше выражение целиком:");
            startWorkingCalculator();
        }
    }

    private static void readConsole() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            readConsole = reader.readLine();
        } catch (Exception e) {
            System.out.println("Что-то пошло не так, извините");
        }
    }


    private static boolean isUserRequestsExit(String isReadConsoleText) {
        if (isReadConsoleText.equals("exit")) {
            System.out.println("Спасибо, что пользуетесь нашим калькулятором" + "\nВсего хорошего!");
            return false;
        }
        return true;
    }


    private static void checkReadConsoleIsNumber(String readConsoleText) {
        String textSymbol;
        readConsoleText = readConsoleText.replaceAll(" ", ""); //убрать пробелы в строке
        for (int index = 0; index < readConsoleText.length(); index++) {
            textSymbol = String.valueOf(readConsoleText.charAt(index));
            //возможно стоит прописать отсечение варианта, где первый символ является операцией
            if (textSymbolIsOperation(String.valueOf(readConsoleText.charAt(0)))) {
                printMassage("Извините, возможно вы забыли ввести первый аргумент, попробуйте ещё раз:");
                startWorkingCalculator();
                return;
            }
            if (textSymbolIsPoint(String.valueOf(readConsoleText.charAt(0)))) {
                printMassage("Извините, возможно вы решили вводить дробную часть без обозначения целого числа, попробуйте ещё раз:");
                startWorkingCalculator();
                return;
            }

            if (operationSymbol == null) {
                if (textSymbolIsOperation(textSymbol)) {
                    operationSymbol = textSymbol;
                } else if (textSymbolIsInteger(textSymbol)) {
                    firstArgument.append(textSymbol);
                } else if (textSymbolIsPoint(textSymbol)) {
                    if (pointForFirstArgument == null) {
                        pointForFirstArgument = textSymbol;
                        firstArgument.append(textSymbol);
                    } else {
                        printMassage("Возможно вы использовали обозначение для дробной части более одного раза, попробуйте ещё раз:");
                        startWorkingCalculator();
                        return;
                    }
                } else {
                    printMassage("Возможно вы воспользовались не только числами, попробуйте ещё раз:");
                    startWorkingCalculator();
                    return;
                }
            } else {
                if (textSymbolIsOperation(textSymbol)) {
                    printMassage("У нас что, два символа операции одновременно???" +
                            "\nИзвините, но мы за последовательное вычисление" +
                            "\nДавайте попробуем ещё раз");
                    startWorkingCalculator();
                    return;
                } else if (textSymbolIsInteger(textSymbol)) {
                    secondArgument.append(textSymbol);
                } else if (textSymbolIsPoint(textSymbol)) {
                    if (pointForSecondArgument == null) {
                        pointForSecondArgument = textSymbol;
                        secondArgument.append(textSymbol);
                    } else {
                        printMassage("Возможно вы использовали обозначение для дробной части более одного раза, попробуйте ещё раз:");
                        startWorkingCalculator();
                        return;
                    }
                } else {
                    printMassage("Возможно вы воспользовались не только числами, попробуйте ещё раз");
                    startWorkingCalculator();
                    return;
                }
            }
        }
        startCalculations();
    }


    private static boolean textSymbolIsOperation(String symbolOfReadConsole) {
        return listOfAllOperations.contains(symbolOfReadConsole);
    }

    private static boolean textSymbolIsInteger(String symbolOfReadConsole) {
        if (symbolOfReadConsole == null || symbolOfReadConsole.equals("")) {
            return false;
        }
        try {
            Integer.parseInt(symbolOfReadConsole);
            return true;
        } catch (NumberFormatException ignored) {
        }
        return false;
    }

    private static boolean textSymbolIsPoint(String symbolOfReadConsole) {
        return symbolOfReadConsole.equals(".");
    }

    //String operand, String atFirtsArgument, String atSecondArgument
    private static void startCalculations() {
        if (checkArgumentIsNotNull(firstArgument, secondArgument)) {
            if (operationSymbol.equals("-")) {
                subtraction();
            }
            if (operationSymbol.equals("+")) {
                addition();
            }
            if (operationSymbol.equals("*")) {
                multiplication();
            }
            if (operationSymbol.equals("/")) {
                division();
            }
            if (operationSymbol.equals("^")) {
                exponentiation();
            }
        } else {
            printMassage("Один из аргументов не введён в консоль. Попробуйте ввести всё выражение целиком:");
            startWorkingCalculator();
        }
    }

    private static boolean checkArgumentIsNotNull(StringBuilder firstArgument, StringBuilder secondArgument) {
        return firstArgument.length() != 0 && !secondArgument.toString().isEmpty();
    }

    private static void subtraction() {
        BigDecimal numeralFirstArgument = new BigDecimal(firstArgument.toString());
        BigDecimal numeralSecondArgument = new BigDecimal(secondArgument.toString());
        printMassage("Результат: " + numeralFirstArgument.subtract(numeralSecondArgument));
        //почему "String.valueOf" серый?
    }

    private static void addition() {
        BigDecimal numeralFirstArgument = new BigDecimal(firstArgument.toString());
        BigDecimal numeralSecondArgument = new BigDecimal(secondArgument.toString());
        printMassage("Результат: " + numeralFirstArgument.add(numeralSecondArgument));
    }

    private static void multiplication() {
        BigDecimal numeralFirstArgument = new BigDecimal(firstArgument.toString());
        BigDecimal numeralSecondArgument = new BigDecimal(secondArgument.toString());
        printMassage("Результат: " + numeralFirstArgument.multiply(numeralSecondArgument));

    }

    private static void division() {
        BigDecimal numeralFirstArgument = new BigDecimal(firstArgument.toString());
        BigDecimal numeralSecondArgument = new BigDecimal(secondArgument.toString());
        if (numeralSecondArgument.compareTo(BigDecimal.valueOf(0)) == 0) {
            printMassage("Вспомните, на ноль делить нельзя!" +
                    "\nПопробуйте делить не на ноль:");
            startWorkingCalculator();
        }
        printMassage("Результат: " + numeralFirstArgument.divide(numeralSecondArgument, 7, BigDecimal.ROUND_HALF_EVEN)); //почему depricate?

    }

    private static void exponentiation() {
        if (!argumentIsInt(secondArgument)){
            printMassage("Для данной версии калькулятора предусмотрено возведение ТОЛЬКО В НАТУРАЛЬНУЮ степень." +
                    "\nи ОГРАНИЧЕНО размерами 999 999 999. Попробуйте ещё раз:");
            startWorkingCalculator();
        }
        BigDecimal numeralFirstArgument = new BigDecimal(firstArgument.toString());
        BigDecimal numeralSecondArgument = new BigDecimal(secondArgument.toString());

        //интовая строка, может быть ошибка, разобраться
        //попробовать написать метод для возведения в степень
       printMassage("Результат: " + numeralFirstArgument.pow(numeralSecondArgument.intValue()));
//        999999999
//        2147483647
        //Math.exp( step * Math.log(x));
    }

    private static boolean argumentIsInt (StringBuilder textArgument){
        try {
            Integer.parseInt(textArgument.toString());
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }
}
