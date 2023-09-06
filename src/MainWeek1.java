import modal.Station;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainWeek1 {

    public static void main(String[] args) {

        System.out.println("This is my shit");
    }


    void addAllStations() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();

            line = reader.readLine();

            while (line != null) {
                String[] parts = line.split(",");
                Station station = new Station(
                        parts[0],
                        parts[0],
                        parts[0]
                );
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



}
