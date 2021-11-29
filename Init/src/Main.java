public class Main {
    public static void main(String[] args) {
        System.out.println(mixStart("mix snacks"));
        System.out.println(startOz("ozymandias"));
        System.out.println(intMax(4, 8, 5));
        System.out.println(close10(8, 5));
        System.out.println(in3050(30, 31));
        System.out.println(max1020(11, 19));
        System.out.println(stringE("Hello"));
        System.out.println(lastDigit(7, 17));
        System.out.println(endUp("Hello"));
        System.out.println(everyNth("Miracle", 2));
    }

    /**
     Return true if the given string begins with "mix", except the 'm' can be anything, so "pix", "9ix" .. all count.
         mixStart("mix snacks") → true
         mixStart("pix snacks") → true
         mixStart("piz snacks") → false
     */
    static boolean mixStart(String str) {
        if (str.length() < 3 || ! "ix".equals(str.substring(1, 3))) {
            return false;
        }
        return true;
    }

    /**
     Given a string, return a string made of the first 2 chars (if present), however include first char only if it is 'o' and include the second only if it is 'z', so "ozymandias" yields "oz".
        startOz("ozymandias") → "oz"
        startOz("bzoo") → "z"
        startOz("oxx") → "o"
     */

    static String startOz(String str) {
        String oz = "";

        if (str.length() >= 1 && 'o' == str.charAt(0))   oz += 'o';
        if (str.length() >= 2 && 'z' == str.charAt(1))   oz += 'z';

        return oz;
    }

    /**
    Given three int values, a b c, return the largest.
        intMax(1, 2, 3) → 3
        intMax(1, 3, 2) → 3
        intMax(3, 2, 1) → 3
     */
    static int intMax(int a, int b, int c) {
        if (a > b && a > c)     return a;
        if (b > c)              return b;
        return c;
    }

    /**
     Given 2 int values, return whichever value is nearest to the value 10, or return 0 in the event of a tie. Note that Math.abs(n) returns the absolute value of a number.
         close10(8, 13) → 8
         close10(13, 8) → 8
         close10(13, 7) → 0
     */
    static int close10(int a, int b) {
        int d1 = Math.abs(10 - Math.abs(a));
        int d2 = Math.abs(10 - Math.abs(b));

        if (d1 < d2)    return a;
        if (d1 > d2)    return b;

        return 0;
    }

    /**
     * Given 2 int values, return true if they are both in the range 30..40 inclusive, or they are both in the range 40..50 inclusive.
        in3050(30, 31) → true
        in3050(30, 41) → false
        in3050(40, 50) → true
     */
    static boolean in3050(int a, int b) {
        // No need to use if-else, parentheses are kept for readability.
        return  (a >= 30 && a <= 40 && b >= 30 && b <= 40) || (a >= 40 && a <= 50 && b >= 40 && b <= 50);
    }

    /**
     Given 2 positive int values, return the larger value that is in the range 10..20 inclusive, or return 0 if neither is in that range.
         max1020(11, 19) → 19
         max1020(19, 11) → 19
         max1020(11, 9) → 11
     */
    static int max1020(int a, int b) {
        if (a >= 10 && a <= 20) {
            if ((b >= 10 && b <= 20) && b > a)  return b;

            return a;
        }

        if (b >= 10 && b <= 20) return b;

        return 0;
    }

    /**
     Return true if the given string contains between 1 and 3 'e' chars.
         stringE("Hello") → true
         stringE("Heelle") → true
         stringE("Heelele") → false
     */
    static boolean stringE(String str) {
        int count = 0;

        for (char c: str.toCharArray()) {
            if (c == 'e')   count++;
        }

        return count >= 1 && count <= 3;
    }

    /**
     Given two non-negative int values, return true if they have the same last digit, such as with 27 and 57. Note that the % "mod" operator computes remainders, so 17 % 10 is 7.
         lastDigit(7, 17) → true
         lastDigit(6, 17) → false
         lastDigit(3, 113) → true
     */
    static boolean lastDigit(int a, int b) {
        return (a - b) % 10 == 0;
    }

    /**
     Given a string, return a new string where the last 3 chars are now in upper case. If the string has less than 3 chars, uppercase whatever is there. Note that str.toUpperCase() returns the uppercase version of a string.
         endUp("Hello") → "HeLLO"
         endUp("hi there") → "hi thERE"
         endUp("hi") → "HI"
     */
    static String endUp(String str) {
        int len = str.length();

        if (len > 3) {
            return str.substring(0, len - 3) + str.substring(len - 3, len).toUpperCase();
        }

        return str.toUpperCase();
    }

    /**
     Given a non-empty string and an int N, return the string made starting with char 0, and then every Nth char of the string. So if N is 3, use char 0, 3, 6, ... and so on. N is 1 or more.
         everyNth("Miracle", 2) → "Mrce"
         everyNth("abcdefg", 2) → "aceg"
         everyNth("abcdefg", 3) → "adg"
     */
    static String everyNth(String str, int n) {
        String s = "";

        for (int i = 0; i < str.length(); i += n) {
            s += str.charAt(i);
        }

        return s;
    }
}
