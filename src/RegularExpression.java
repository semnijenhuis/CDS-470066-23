import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class RegularExpression {
    public static void main(String[] args) throws IOException {
        searchSomehting("something");
    }

    public static void searchSomehting(String test) throws IOException {
//        Reader input = new FileReader("data/stations.csv");

        String input = "DEUER";
        String regex = "^[a-zA-Z]{1,3}$";

        int count = RegexFilter.filter(input, regex);
        System.out.println("Number of lines : " + count);


    }

    public void readStations() {
        String idRegex  = "^[0-9]{1,4}$";
        String codeRegex = "^[a-zA-Z]{1,3}$";
        String uicRegex = "";
        String name_shortRegex = "";
        String name_mediumRegex = "";
        String name_longRegex = "";
        String slugRegex = "";
        String countryRegex = "";
        String typeRegex = "";
        String geo_latRegex = "";
        String geo_lngRegex = "";
    }


}
