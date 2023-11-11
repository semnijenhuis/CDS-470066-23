import modal.utils.ReadFile;
import modal.Searching.Linear;
import modal.Objects.Station;
import modal.Objects.Track;
import modal.algorithm.AStar;
import modal.algorithm.Dijkstra;
import modal.algorithm.MCST;
import modal.menu.Menus;
import modal.tree.AVLTree;

import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        ReadFile readFile = new ReadFile();
        AVLTree avlTree = new AVLTree();

        ArrayList<Station> stations = readFile.readStationFile("data/stations.csv");
        ArrayList<Track> tracks = readFile.readTracksFile("data/tracks.csv");
        readFile.addTracksToStations(stations, tracks);
        avlTree = readFile.makeAVLTree(stations, avlTree);






        Linear search = new Linear();
        Station beginStation = search.searchStationID(stations, 43);
        Station endStation = search.searchStationID(stations, 171);

//        for (int i = 0; i < 850; i++) {
//            Station begin = search.searchStationID(stations, i);
//            if (begin != null) {
//                System.out.println(i);
//                System.out.println("----------------");
//                Station end = search.searchStationID(stations, 687);
//                System.out.print("Dijkstra  --- ");
//                dijkstra.shortestPath(stations,begin,end);
////                System.out.print("A*        --- ");
////                aStar.shortestPath(stations, begin, end);
//            }
//
//        }


//


//        mcst.shortestPath(stations, begin, end);




//        System.out.print("Dijkstra  ---- ");
//        dijkstra.shortestPath(stations,begin,end);
//
//        System.out.print("A*        ---- ");
//        aStar.shortestPath(stations, begin, end);

//        HashMap<String, Station> stationMap = new HashMap<>();

//        for (Station station : stations) {
//            stationMap.put(station.getCode(), station);
//
//        }


//        MyGraph graph = new MyGraph(tracks.size(), stationMap);
//        graph.addConnections(tracks);


//        String sourceCity = "asd";
//        String endStation = "em";

//        Station start = stationMap.get(sourceCity.toUpperCase());
//        Station end = stationMap.get(endStation.toUpperCase());


//        Rectangle boundingRectangle = new Rectangle(beginStation, endStation);

//        graph.primAllStations(beginStation.getCode().toLowerCase(), boundingRectangle);
//        graph.graphViz();






        Menus menu = new Menus();
        menu.run(stations, tracks, avlTree);
    }
    static double calculateDistance(Point a, Point b) {
        // Calculate distance using Euclidean distance formula (this can be improved based on actual GPS coordinates)
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }



}


//TODO: Shortest path calculator (from  - to)
//TODO: When you put in GPS Coordinates, it should give you the minimum track connections (and lenght)
//TODO:
