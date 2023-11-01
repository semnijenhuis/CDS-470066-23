package modal;

import modal.Objects.Station;
import modal.Objects.Track;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {

    public ArrayList<Station> readStationFile(String filename) {

        ArrayList<Station> allStations = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();

            line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(",");
                Station station = new Station(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        Integer.parseInt(parts[2]),
                        parts[3],
                        parts[4],
                        parts[5],
                        parts[6],
                        parts[7],
                        parts[8],
                        Double.parseDouble(parts[9]),
                        Double.parseDouble(parts[10])
                );
                allStations.add(station);
                line = reader.readLine();

            }
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return allStations;

    }

    public ArrayList<Track> readTracksFile(String fileName) {

        ArrayList<Track> allTracks = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(",");
                Track track = new Track(
                        parts[0],
                        parts[1],
                        Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3]),
                        Integer.parseInt(parts[4])
                );
                allTracks.add(track);
                line = reader.readLine();

            }
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return allTracks;
    }

    public void addTracksToStations(ArrayList<Station> stations, ArrayList<Track> tracks) {

        for (Track currentTrack : tracks) {

            String stationFrom = currentTrack.getFromStationCode().toUpperCase();
            String stationTo = currentTrack.getToStationCode().toUpperCase();


            for (Station currentStation : stations) {
                if (stationFrom.equals(currentStation.getCode())) {


                    currentStation.addDepartureTrack(currentTrack);
                } else if (stationTo.equals(currentStation.getCode())) {

                    currentStation.addArrivalTrack(currentTrack);

                }
            }
        }

    }



//    public findStationByCode(String code) {
//     for (Station currentStation : AllStations) {
//         if (currentStation.getCode().equals(code)) {
//             return currentStation;
//         }
//     }
//    }

}
