import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class TwoStringsResult {

    /*
     * Complete the 'twoStrings' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING s1
     *  2. STRING s2
     */

    public static String twoStrings(String s1, String s2) {
        boolean hasSubstring = twoStrings(s1.toCharArray(), 0, s1.length(),
                    s2.toCharArray(), 0, s2.length()
        );
        if (hasSubstring) return "YES";
        else return "NO";
    }

    public static boolean twoStrings(char[] s1, int s1Start, int s1Length,
                                    char[] s2, int s2Start, int s2Length)
    {
        //if either range is zero-sized (or less, although it should never reach that point), return null
        if(s1Length <= 0) return false;
        if(s2Length <= 0) return false;

        if (equalsRange(s1, s1Start, s1Length, s2, s2Start, s2Length)) {
            return true;
        }

        //depth-first search time! use Java's short-circuiting `||` operator to
        //succinctly search and end on the first `true` result
        return
        //tentatively remove the first character of the first string
        twoStrings(s1, s1Start + 1, s1Length - 1,
                                s2, s2Start, s2Length) ||
        //tentatively remove the last character of the first string
        twoStrings(s1, s1Start, s1Length - 1,
                                s2, s2Start, s2Length) ||
        //tentatively remove the first character of the second string
        twoStrings(s1, s1Start, s1Length,
                                s2, s2Start + 1, s2Length - 1) ||
        //tentatively remove the last character of the second string
        twoStrings(s1, s1Start, s1Length,
                                s2, s2Start, s2Length - 1)
        //if we haven't found anything in the probing, then the final
        //value will be false naturally. No need for an additional `|| false`.
        ;
    }

    private static boolean equalsRange(char[] s1, int s1Start, int s1Length,
                                    char[] s2, int s2Start, int s2Length)
    {
        //early exit if the sizes are non-equal: it's impossible to be the same if they're
        //inequal sizes
        if(s1Length != s2Length) return false;

        //use 'down to' idiom (`--` and `>`) to search entire range efficiently
        for(int i = s1Length; i--> 0;) {
            //search every char in the ranges; if any is inequal, return false
            if(s1[s1Start + i] != s2[s2Start + i]) {
                return false;
            }
        }

        //otherwise, the ranges are equal
        return true;
    }

}

public class TwoStringsSolution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String s1 = bufferedReader.readLine();

                String s2 = bufferedReader.readLine();

                String result = TwoStringsResult.twoStrings(s1, s2);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
