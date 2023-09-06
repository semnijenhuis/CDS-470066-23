package week2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import ./Station;


public class MainWeek2 {

    void addAllStations() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();

            line = reader.readLine();

            while (line != null) {
                String[] parts = line.split(",");
                Station station = new Station(
                        parts[0],
                        parts[1],
                        parts[2],
                        parts[3],
                        parts[4],
                        parts[5],
                        parts[6],
                        parts[7],
                        parts[8],
                        parts[9],
                        parts[10]
                );
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
