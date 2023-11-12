package modal.utils;

import modal.Objects.Station;
import modal.Objects.Track;
import modal.tree.AVLTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {

    RegularExpression regex = new RegularExpression();


    // Reads the file for stations with regex
    public ArrayList<Station> readStationFile(String filename) {

        int check = 0;
        int errorLines = 0;

        ArrayList<Station> allStations = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();

            line = reader.readLine();
            while (line != null) {
                check++;
                String[] parts = line.split(",");

                Boolean valid = regex.filterStation(parts[0], parts[1], parts[2], parts[9], parts[10]);

                if (valid) {
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
                } else {
                    errorLines++;
                }

                line = reader.readLine();

            }
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Number of stations checked: " + check + " with errors: " + errorLines);
        return allStations;

    }

    // Reads the file for tracks with regex
    public ArrayList<Track> readTracksFile(String fileName) {

        int check = 0;
        int errorLines = 0;

        ArrayList<Track> allTracks = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                check++;
                String[] parts = line.split(",");

                Boolean valid = regex.filterTrack(parts[0], parts[1], parts[2]);
                if (valid) {
                    Track track = new Track(
                            parts[0],
                            parts[1],
                            Integer.parseInt(parts[2]),
                            Integer.parseInt(parts[3]),
                            Integer.parseInt(parts[4])
                    );
                    allTracks.add(track);
                }


                line = reader.readLine();

            }
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Number of tracks checked: " + check + " with errors: " + errorLines);
        return allTracks;
    }

    // Adds tracks to stations
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

    // Makes a AVL Tree based on the stations
    public AVLTree makeAVLTree(ArrayList<Station> stations, AVLTree avlTree) {
        int i = 0;
        for (Station foundstation : stations) {
            i++;
            avlTree.addTree(avlTree, foundstation);
        }
        if (i == stations.size()) {
            System.out.println("AVL Tree is made");
        } else {
            System.out.println("AVL Tree might be a issue");
        }
        return avlTree;
    }



}
