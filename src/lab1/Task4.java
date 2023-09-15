package lab1;

public class Task4 {
    public static int convertBoolean(boolean bool) {
        return Boolean.compare(bool, false);
    }
    public static boolean convertBoolean(int bool) {
        return bool != 0;
    }
}
