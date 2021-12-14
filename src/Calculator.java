import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Calculator {
    final static ArrayList<String> listOfAllOperations = new ArrayList<String>(Arrays.asList("-", "+", "*", "/", "^"));
    static StringBuilder firstArgument = new StringBuilder();
    static StringBuilder secondArgument = new StringBuilder();
    static String readConsole = null;


    //ArrayList<Character> firstArgument = new ArrayList<>();
    static String operationSymbol;
    int result;

    public static void m(String message) {
        System.out.println(message);
    }

    public static void startWorkingCalculator() {
        firstArgument.delete(0,firstArgument.length());
        secondArgument.delete(0,secondArgument.length());
        operationSymbol = null;
        readConsole();
        while (readConsoleNotExit(readConsole)) {
            checkReadConsoleIsNumber(readConsole);
            m(firstArgument.toString());
            m(secondArgument.toString());
            startWorkingCalculator();
        }
    }

    private static String readConsole() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            readConsole = reader.readLine();
        } catch (Exception e) {
            System.out.println("Что-то пошло не так, извините");
        }
        return readConsole;
    }


    private static boolean readConsoleNotExit(String consoleText) {
        if (consoleText.equals("exit")) {
            System.out.println(" Спасибо, что пользуетесь нашим калькулятором" + "\n Всего хорошего!");
         // System.exit(0);
            return false;
        }
        return true;
    }


    private static void checkReadConsoleIsNumber(String readConsoleText) {
        String textSymbol;
        //убрать пробелы в строке
        for (int index = 0; index < readConsoleText.length(); index++) {
            textSymbol = String.valueOf(readConsoleText.charAt(index));
            //m(textSymbol);
            //возможно стоит прописать отсечение варианта, где первый символ является операцией
            if (operationSymbol == null) {
                if (textSymbolIsOperation(textSymbol)) {
                    operationSymbol = textSymbol;
                    m(" Первый символ операции равен: " + operationSymbol);
                }
                else if (textSymbolIsInteger(textSymbol)){
                    firstArgument.append(textSymbol);
                }
                else {
                    m(" Возможно вы воспользовались не числами, попробуйте ещё раз");
                    startWorkingCalculator();
                }
            }

            //if (!(operationSymbol == null)) {
            else  {  if (textSymbolIsOperation(textSymbol)) {
                    m(" У нас что, два символа операции одновременно???");
                    m(" Извините, но мы за последовательное вычисление");
                    m(" Давайте попробуем ещё раз");
                    startWorkingCalculator();
                }
                else if (textSymbolIsInteger(textSymbol)){
                    secondArgument.append(textSymbol);
                }
                else {
                    m(" Возможно вы воспользовались не числами, попробуйте ещё раз");
                    startWorkingCalculator();
                }
            }
        }
    }


    private static boolean textSymbolIsOperation(String symbolOfReadConsole) {
        if (listOfAllOperations.contains(symbolOfReadConsole)) {
            return true;
        }
        return false;
    }

    private static boolean textSymbolIsInteger(String symbolOfReadConsole) {
        if (symbolOfReadConsole == null || symbolOfReadConsole.equals("")) {
            return false;
        }
        try {
            Integer.parseInt(symbolOfReadConsole);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }

    private static void goingCalculations (String operand, String atFirtsArgument, String atSecondArgument){
        if (operand.equals("-")){

        }
        if (operand.equals("+")){

        }
        if (operand.equals("*")){

        }
        if (operand.equals("/")){

        }
        if (operand.equals("^")){

        }
    }
}
