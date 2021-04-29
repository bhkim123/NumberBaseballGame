import java.util.regex.Pattern;

public class UtilClass {
    public static boolean isValidInput(String input){
        if(Pattern.matches("^[0-9][,][0-9][,][0-9]", input)){ return true; }
        else{ return false; }
    }

    public static boolean isDistinct(String input){
        char first = input.charAt(0);
        char second = input.charAt(2);
        char third = input.charAt(4);
        if(first == second || first == third || second == third){
            return false;
        }
        else{
            return true;
        }
    }

    public static int[] extractedNum(String input){
        char first = input.charAt(0);
        char second = input.charAt(2);
        char third = input.charAt(4);
        int[] result = {first - '0', second - '0', third - '0'};
        return result;
    }

}
