import modal.ReadFile;
import modal.Station;
import modal.Track;
import modal.menu.Menus;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ReadFile readFile = new ReadFile();

        ArrayList<Station> stations = readFile.readStationFile("data/stations.csv");
        ArrayList<Track> tracks = readFile.readTracksFile("data/tracks.csv");

        Menus menu = new Menus();
        menu.run(stations, tracks);

//        System.out.println(stations.size());
//        System.out.println(tracks.size());
//
//        SelectionSort selectionSort = new SelectionSort();
//        stations = selectionSort.sortStationName(stations, 1);
//
//        for (int i = 0; i < stations.size(); i++) {
//            System.out.println(stations.get(i).getId());
//        }

    }



}


//TODO: Shortest path calculator (from  - to)
//TODO: When you put in GPS Coordinates, it should give you the minimum track connections (and lenght)
//TODO:
