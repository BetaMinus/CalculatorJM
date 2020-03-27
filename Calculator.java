import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.HashMap;

class Calculator {

    public static void main(String[] args) throws Exception {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Input an expression: ");
    String s = reader.readLine();
    char operation = ' ';
    int arabicResult;


    int i = 0;
    while(Character.isAlphabetic(s.charAt(i)) || Character.isDigit(s.charAt(i))) {
        i++;
        operation = s.charAt(i);
    }

    String[] numbers = s.split("\\"+Character.toString(operation));

    if (Character.isDigit(numbers[0].charAt(0)) && Character.isDigit(numbers[1].charAt(0))) {
        System.out.println(calcArabic(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]), operation));
    } else if (Character.isAlphabetic(numbers[0].charAt(0)) && Character.isAlphabetic(numbers[1].charAt(0))) {
        System.out.println(calcRomans(numbers[0], numbers[1], operation));
    } else {
        throw new NumeralSystemException("Error: Different numeral system");
    }
}
//-----------------------------------------------------------------------------------------
    public static int calcArabic(int a, int b, char operation) throws NumbersLimitException, OperationTypeException {

        if (a < 1 || a > 10 || b < 1 || b > 10 ) throw new NumbersLimitException("Error: Number exceeds limit");
        int result;
        switch (operation) {
            case '+':
                result = a+b;
                break;
            case '-':
                result = a-b;
                break;
            case '*':
                result = a*b;
                break;
            case '/':
                result = a/b;
                break;
            default:
            throw new OperationTypeException("Error: Unknown operation type");
        }
        return result;
    }

    public static String calcRomans(String a, String b, char operation) throws NumbersLimitException, OperationTypeException {

        Map<Integer, String> tens = new HashMap<>();
        tens.put(0, "");
        tens.put(1, "X");
        tens.put(2, "XX");
        tens.put(3, "XXX");
        tens.put(4, "XL");
        tens.put(5, "L");
        tens.put(6, "LX");
        tens.put(7, "LXX");
        tens.put(8, "LXXX");
        tens.put(9, "XC");
        tens.put(10, "C");

        Map<Integer, String> units = new HashMap<>();
        units.put(0, "");
        units.put(1, "I");
        units.put(2, "II");
        units.put(3, "III");
        units.put(4, "IV");
        units.put(5, "V");
        units.put(6, "VI");
        units.put(7, "VII");
        units.put(8, "VIII");
        units.put(9, "IX");
        units.put(10, "X");

        int c = 0;
        int d = 0;
        for(Map.Entry<Integer, String> pair: units.entrySet()) {
            if (pair.getValue().equals(a)) {
                c = pair.getKey();
            }
            if (pair.getValue().equals(b)) {
                d = pair.getKey();
            }
        }
        int arabicRes = calcArabic(c, d, operation);
        String sTens = tens.get(arabicRes/10);
        String sUnits = units.get(arabicRes%10);
        if (arabicRes<0) sUnits = "-" + units.get(-arabicRes%10);
        String result = sTens+sUnits;
        return result;
    }
}
//--------------------------------------------------------------------------

class NumeralSystemException extends Exception
{
    public NumeralSystemException(String message) {
        super(message);
    }
}

class NumbersLimitException extends Exception
{
    public NumbersLimitException(String message) {
        super(message);
    }
}

class OperationTypeException extends Exception
{
    public OperationTypeException(String message) {
        super(message);
    }
}