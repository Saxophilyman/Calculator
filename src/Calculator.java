import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class Calculator {
    private final static ArrayList<String> listOfAllOperations = new ArrayList<>(Arrays.asList("-", "+", "*", "/", "^"));
    private StringBuilder firstArgument = new StringBuilder();
    private StringBuilder secondArgument = new StringBuilder();
    private String readConsole = null;
    private String operationSymbol;
    private String pointForFirstArgument;
    private String pointForSecondArgument;

    private void printMassage(String message) {
        System.out.println(message);
    }

    public void startWorkingCalculator() {
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

    private void readConsole() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            readConsole = reader.readLine();
        } catch (Exception e) {
            System.out.println("Что-то пошло не так, извините");
        }
    }

    private boolean isUserRequestsExit(String isReadConsoleText) {
        if (isReadConsoleText.equals("exit")) {
            System.out.println("Спасибо, что пользуетесь нашим калькулятором" + "\nВсего хорошего!");
            return false;
        }
        return true;
    }

    private void checkReadConsoleIsNumber(String readConsoleText) {
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

    private boolean textSymbolIsOperation(String symbolOfReadConsole) {
        return listOfAllOperations.contains(symbolOfReadConsole);
    }

    private boolean textSymbolIsInteger(String symbolOfReadConsole) {
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

    private boolean textSymbolIsPoint(String symbolOfReadConsole) {
        return symbolOfReadConsole.equals(".");
    }

    private void startCalculations() {
        if (checkArgumentIsNotNull(firstArgument, secondArgument)) {
            BigDecimal numeralFirstArgument = new BigDecimal(firstArgument.toString());
            BigDecimal numeralSecondArgument = new BigDecimal(secondArgument.toString());

            if (operationSymbol.equals("-")) {
                subtract(numeralFirstArgument, numeralSecondArgument);
            }
            if (operationSymbol.equals("+")) {
                add(numeralFirstArgument, numeralSecondArgument);
            }
            if (operationSymbol.equals("*")) {
                multiply(numeralFirstArgument, numeralSecondArgument);
            }
            if (operationSymbol.equals("/")) {
                divide(numeralFirstArgument, numeralSecondArgument);
            }
            if (operationSymbol.equals("^")) {
                exponentiate(numeralFirstArgument, numeralSecondArgument);
            }
        } else {
            printMassage("Один из аргументов не введён в консоль. Попробуйте ввести всё выражение целиком:");
            startWorkingCalculator();
        }
    }

    private boolean checkArgumentIsNotNull(StringBuilder firstArgument, StringBuilder secondArgument) {
        return firstArgument.length() != 0 && !secondArgument.toString().isEmpty();
    }

    private void subtract(BigDecimal numeralFirstArgument, BigDecimal numeralSecondArgument) {
        printMassage("Результат: " + numeralFirstArgument.subtract(numeralSecondArgument));
    }

    private void add(BigDecimal numeralFirstArgument, BigDecimal numeralSecondArgument) {
        printMassage("Результат: " + numeralFirstArgument.add(numeralSecondArgument));
    }

    private void multiply(BigDecimal numeralFirstArgument, BigDecimal numeralSecondArgument) {
        printMassage("Результат: " + numeralFirstArgument.multiply(numeralSecondArgument));

    }

    private void divide(BigDecimal numeralFirstArgument, BigDecimal numeralSecondArgument) {
        if (numeralSecondArgument.compareTo(BigDecimal.valueOf(0)) == 0) {
            printMassage("Вспомните, на ноль делить нельзя!" +
                    "\nПопробуйте делить не на ноль:");
            startWorkingCalculator();
        }
        printMassage("Результат: " + numeralFirstArgument.divide(numeralSecondArgument, 7, BigDecimal.ROUND_HALF_EVEN)); //почему depricate?

    }

    private void exponentiate(BigDecimal numeralFirstArgument, BigDecimal numeralSecondArgument) {
        if (!argumentIsInt(secondArgument)) {
            printMassage("Для данной версии калькулятора предусмотрено возведение ТОЛЬКО В НАТУРАЛЬНУЮ степень." +
                    "\nи ОГРАНИЧЕНО размерами 999 999 999. Попробуйте ещё раз:");
            startWorkingCalculator();
        }
        printMassage("Результат: " + numeralFirstArgument.pow(numeralSecondArgument.intValue()));
        //Math.exp( step * Math.log(x));
    }

    private boolean argumentIsInt(StringBuilder textArgument) {
        try {
            Integer.parseInt(textArgument.toString());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
