import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class Calculator {
    private final static ArrayList<String> listOfAllOperators = new ArrayList<>(Arrays.asList("-", "+", "*", "/", "^"));
    private ArrayList<String> listOfLastOperations = new ArrayList<>(10);
    private StringBuilder firstArgument = new StringBuilder();
    private StringBuilder secondArgument = new StringBuilder();
    private String readConsole = null;
    private String operationSymbol;
    private String pointForFirstArgument;
    private String pointForSecondArgument;


    private void printMessage(String message) {
        System.out.println(message);
    }

    public void startWorkingCalculator() {
        firstArgument.delete(0, firstArgument.length());
        secondArgument.delete(0, secondArgument.length());
        operationSymbol = null;
        pointForFirstArgument = null;
        pointForSecondArgument = null;
        readConsole();
        while (isUserRequestsNotExit(readConsole)) {
            if (!isUserRequestsMemory(readConsole)) {
                checkReadConsoleIsNumber(readConsole);
                printMessage("\nВведите в консоль ваше выражение целиком:");
            }
            startWorkingCalculator();
        }
    }

    // если я правильно понял. но жёлтый маркер справа говорит, что return value of the method is never used
// А как по другому??
    private boolean readConsole() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            readConsole = reader.readLine();
            return true;
        } catch (Exception e) {
            System.out.println("Что-то пошло не так, извините");
            return false;
        }
    }

    private boolean isUserRequestsNotExit(String isReadConsoleTextExit) {
        if (isReadConsoleTextExit.equals("exit")) {
            System.out.println("Спасибо, что пользуетесь нашим калькулятором" + "\nВсего хорошего!");
            return false;
        }
        return true;
    }


    //нихуя не понятны приколы с булеаном и ретёрном
    //та же return value of the method is never used
    private boolean isUserRequestsMemory(String isReadConsoleTextMemory) {
        if (isReadConsoleTextMemory.equals("memory")) {
            /*???*/
            return printLastOperations(listOfLastOperations);
        }
        /*???*/
        return false;
    }

    private boolean printLastOperations(ArrayList<String> listOfLastOperations) {
        if (listOfLastOperations.isEmpty()) {
            System.out.println("Мы не можем отобразить список последних операций, возможно вы ещё не ввели ни одного выражения." +
                    "\nВведите в консоль ваше выражение целиком:");
        } else {
            System.out.println("listOfLastOperations не null");
            for (int index = 0; index < listOfLastOperations.size(); index++) {
                System.out.println(listOfLastOperations.get(index));
            }
        }
        /*???*/
        return true;
    }

    private void addOperationToListOfLastOperations(ArrayList<String> listOfLastOperations, String Operation) {
        if (listOfLastOperations.size() >= 10) {
            listOfLastOperations.remove(0);
        }
        listOfLastOperations.add(Operation);
    }

    private void checkReadConsoleIsNumber(String readConsoleText) {
        String textSymbol;
        readConsoleText = readConsoleText.replaceAll(" ", ""); //убрать пробелы в строке
        for (int index = 0; index < readConsoleText.length(); index++) {
            textSymbol = String.valueOf(readConsoleText.charAt(index));
            //возможно стоит прописать отсечение варианта, где первый символ является операцией
            if (textSymbolIsOperation(String.valueOf(readConsoleText.charAt(0)))) {
                printMessage("Извините, возможно вы забыли ввести первый аргумент, попробуйте ещё раз:");
                startWorkingCalculator();
                return;
            }
            if (textSymbolIsPoint(String.valueOf(readConsoleText.charAt(0)))) {
                printMessage("Извините, возможно вы решили вводить дробную часть без обозначения целого числа, попробуйте ещё раз:");
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
                        printMessage("Возможно вы использовали обозначение для дробной части более одного раза, попробуйте ещё раз:");
                        startWorkingCalculator();
                        return;
                    }
                } else {
                    printMessage("Возможно вы воспользовались не только числами, попробуйте ещё раз:");
                    startWorkingCalculator();
                    return;
                }
            } else {
                if (textSymbolIsOperation(textSymbol)) {
                    printMessage("У нас что, два символа операции одновременно???" +
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
                        printMessage("Возможно вы использовали обозначение для дробной части более одного раза, попробуйте ещё раз:");
                        startWorkingCalculator();
                        return;
                    }
                } else {
                    printMessage("Возможно вы воспользовались не только числами, попробуйте ещё раз");
                    startWorkingCalculator();
                    return;
                }
            }
        }
        startCalculations();
    }

    private boolean textSymbolIsOperation(String symbolOfReadConsole) {
        return listOfAllOperators.contains(symbolOfReadConsole);
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
                applySubtractOperation(numeralFirstArgument, numeralSecondArgument);
            }
            if (operationSymbol.equals("+")) {
                applyAddOperation(numeralFirstArgument, numeralSecondArgument);
            }
            if (operationSymbol.equals("*")) {
                applyMultiplyOperation(numeralFirstArgument, numeralSecondArgument);
            }
            if (operationSymbol.equals("/")) {
                applyDivideOperation(numeralFirstArgument, numeralSecondArgument);
            }
            if (operationSymbol.equals("^")) {
                applyExponentiateOperation(numeralFirstArgument, numeralSecondArgument);
            }
        } else {
            printMessage("Один из аргументов не введён в консоль. Попробуйте ввести всё выражение целиком:");
            startWorkingCalculator();
        }
    }

    private boolean checkArgumentIsNotNull(StringBuilder firstArgument, StringBuilder secondArgument) {
        return firstArgument.length() != 0 && !secondArgument.toString().isEmpty();
    }

    private void applySubtractOperation(BigDecimal numeralFirstArgument, BigDecimal numeralSecondArgument) {
        printMessage("Результат: " + numeralFirstArgument.subtract(numeralSecondArgument));
    }

    private void applyAddOperation(BigDecimal numeralFirstArgument, BigDecimal numeralSecondArgument) {
        printMessage("Результат: " + numeralFirstArgument.add(numeralSecondArgument));
    }

    private void applyMultiplyOperation(BigDecimal numeralFirstArgument, BigDecimal numeralSecondArgument) {
        printMessage("Результат: " + numeralFirstArgument.multiply(numeralSecondArgument));

    }

    private void applyDivideOperation(BigDecimal numeralFirstArgument, BigDecimal numeralSecondArgument) {
        if (numeralSecondArgument.compareTo(BigDecimal.valueOf(0)) == 0) {
            printMessage("Вспомните, на ноль делить нельзя!" +
                    "\nПопробуйте делить не на ноль:");
            startWorkingCalculator();
        }
        printMessage("Результат: " + numeralFirstArgument.divide(numeralSecondArgument, 7, BigDecimal.ROUND_HALF_EVEN)); //почему depricate?

    }

    private void applyExponentiateOperation(BigDecimal numeralFirstArgument, BigDecimal numeralSecondArgument) {
        if (!argumentIsInt(secondArgument)) {
            printMessage("Для данной версии калькулятора предусмотрено возведение ТОЛЬКО В НАТУРАЛЬНУЮ степень." +
                    "\nи ОГРАНИЧЕНО размерами 999 999 999. Попробуйте ещё раз:");
            startWorkingCalculator();
        }
        printMessage("Результат: " + numeralFirstArgument.pow(numeralSecondArgument.intValue()));
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
