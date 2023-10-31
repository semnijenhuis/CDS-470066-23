import modal.ReadFile;
import modal.Searching.Linear;
import modal.Station;
import modal.Track;
import modal.algorithm.PathCalculator;
import modal.menu.Menus;
import modal.sorting.InsertionSort;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        ReadFile readFile = new ReadFile();

        ArrayList<Station> stations = readFile.readStationFile("data/stations.csv");
        ArrayList<Track> tracks = readFile.readTracksFile("data/tracks.csv");
        readFile.addTracksToStations(stations, tracks);

        InsertionSort sort = new InsertionSort();
        stations = sort.stationInsertionSort(stations, 1);
        PathCalculator pathCalculator = new PathCalculator();



        Linear search = new Linear();

        for (int i = 0; i < 850; i++) {
            Station begin = search.searchStationID(stations, i);
            if (begin != null) {
                System.out.println(i);
                Station end = search.searchStationID(stations, 687);
                pathCalculator.Dijkstra(stations,begin,end);
            }

        }



//        Station begin = search.searchStationID(stations, 8);
//        Station end = search.searchStationID(stations, 687);
//        pathCalculator.Dijkstra(stations,begin,end);






        Menus menu = new Menus();
        menu.run(stations, tracks);
    }



}


//TODO: Shortest path calculator (from  - to)
//TODO: When you put in GPS Coordinates, it should give you the minimum track connections (and lenght)
//TODO:
