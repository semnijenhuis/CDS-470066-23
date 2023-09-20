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

//        BufferedReader reader = new BufferedReader(line);
//        String line;
        int count = 0;

//        while ((line = reader.readLine()) != null) {
            Matcher m = regex.matcher(line);

            if (m.find()) {
               if (m.groupCount()>0){
                   System.out.println(m.group(1));
               }
               else {
                   System.out.println(line);
               }

               count++;
            }
//        }

        return count;
    }

}
