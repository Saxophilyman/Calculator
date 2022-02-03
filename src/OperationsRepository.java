import java.math.BigDecimal;
import java.util.ArrayList;

public class OperationsRepository {
    public ArrayList<String> listOfLastOperations = new ArrayList<>(10);
    static final int SIZE_OF_MEMORY_OPERATIONS = 10;

    private void printMessage(String message) {
        System.out.println(message);
    }//
//    private boolean isUserRequestsMemory(String isReadConsoleTextMemory) {
//        if (isReadConsoleTextMemory.equals("memory")) {
//            /*???*/
//            return printLastOperations(listOfLastOperations);
//        }
//        /*???*/
//        return false;
//    }
//
//    public boolean printLastOperations(ArrayList<String> listOfLastOperations) {
//        if (listOfLastOperations.isEmpty()) {
//            printMessage("Мы не можем отобразить список последних операций, возможно вы ещё не ввели ни одного выражения." +
//                    "\nВведите в консоль ваше выражение целиком:");
//        } else {
//            for (String listOfLastOperation : listOfLastOperations) {
//                System.out.println(listOfLastOperation);
//            }
//            printMessage("\nВведите в консоль ваше выражение целиком:");
//        }
//        /*???*/
//        return true;
//    }


    public void addOperationToListOfLastOperations(ArrayList<String> listOfLastOperations, String Operation) {
        if (listOfLastOperations.size() >= SIZE_OF_MEMORY_OPERATIONS) {
            listOfLastOperations.remove(0);
        }
        listOfLastOperations.add(Operation);
    }

    public String concatenationInformationOfOperation(StringBuilder firstArgument, String operationSymbol, StringBuilder secondArgument, BigDecimal result) {
        return Calculator.setTimeOfOperation() + " : " + firstArgument + operationSymbol + secondArgument + " = " + result;
    }


}
