package GUI;

public class ConvertClass {

    public static Integer convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (Exception NumberFormatException) {
            return null;
        }
    }

}
