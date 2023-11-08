import modal.ReadFile;
import modal.Searching.Linear;
import modal.Objects.Station;
import modal.Objects.Track;
import modal.algorithm.AStar;
import modal.algorithm.Dijkstra;
import modal.algorithm.MCST;
import modal.menu.Menus;
import modal.sorting.InsertionSort;
import modal.tree.AVLTree;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        ReadFile readFile = new ReadFile();
        AVLTree avlTree = new AVLTree();

        ArrayList<Station> stations = readFile.readStationFile("data/stations.csv");
        ArrayList<Track> tracks = readFile.readTracksFile("data/tracks.csv");
        readFile.addTracksToStations(stations, tracks);
        avlTree = readFile.makeAVLTree(stations, avlTree);



        Dijkstra dijkstra = new Dijkstra();
        AStar aStar = new AStar();
        MCST mcst = new MCST();



        Linear search = new Linear();

//        for (int i = 0; i < 850; i++) {
//            Station begin = search.searchStationID(stations, i);
//            if (begin != null) {
//                System.out.println(i);
//                System.out.println("----------------");
//                Station end = search.searchStationID(stations, 687);
//                System.out.print("Dijkstra  --- ");
//                dijkstra.shortestPath(stations,begin,end);
//                System.out.print("A*        --- ");
//                aStar.shortestPath(stations, begin, end);
//            }
//
//        }


//
//        Station begin = search.searchStationID(stations, 6);
//        Station end = search.searchStationID(stations, 687);

        //        mcst.shortestPath(stations, begin, end);

//        System.out.print("Dijkstra  ---- ");
//        dijkstra.shortestPath(stations,begin,end);
//
//        System.out.print("A*        ---- ");
//        aStar.shortestPath(stations, begin, end);

        












        Menus menu = new Menus();
        menu.run(stations, tracks, avlTree);
    }



}


//TODO: Shortest path calculator (from  - to)
//TODO: When you put in GPS Coordinates, it should give you the minimum track connections (and lenght)
//TODO:
