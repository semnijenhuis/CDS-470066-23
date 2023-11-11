package modal.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexFilter {

    public static int filter(String input, String regex) throws IOException {
        return filter(input, Pattern.compile(regex));
    }

    public static int filter(String line, Pattern regex) throws IOException {
        Matcher m = regex.matcher(line);
        if (m.matches()) {
            return 0; // Return 1 for a match
        } else {
            System.out.println(line);
            return 1; // Return 0 for no match
        }
    }



}
