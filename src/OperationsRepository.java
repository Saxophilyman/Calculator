import java.math.BigDecimal;
import java.util.ArrayList;

public class OperationsRepository {
    private ArrayList<String> listOfLastOperations = new ArrayList<>(10);
    static final int SIZE_OF_MEMORY_OPERATIONS = 10;

    public ArrayList<String> getListOfLastOperations() {
        return listOfLastOperations;
    }

    public void addOperationToListOfLastOperations(StringBuilder firstArgument, String operationSymbol, StringBuilder secondArgument, BigDecimal result) {
        if (listOfLastOperations.size() >= SIZE_OF_MEMORY_OPERATIONS) {
            listOfLastOperations.remove(0);
        }
        listOfLastOperations.add(concatenationInformationOfOperation(firstArgument, operationSymbol, secondArgument, result));
    }

    public String concatenationInformationOfOperation(StringBuilder firstArgument, String operationSymbol, StringBuilder secondArgument, BigDecimal result) {
        return Calculator.setTimeOfOperation() + " : " + firstArgument + operationSymbol + secondArgument + " = " + result;
    }


}
