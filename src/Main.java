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

//        avlTree.print();
        avlTree.searchID(250);




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
////                System.out.print("A*        --- ");
////                aStar.shortestPath(stations, begin, end);
//            }
//
//        }


//
        Station beginStation = search.searchStationID(stations, 43);
        Station endStation = search.searchStationID(stations, 171);

//        mcst.shortestPath(stations, begin, end);



//        int V = 5; // Number of vertices (points)
//
//        Point[] points = new Point[V];
//        Random random = new Random();
//
//        // Generate random GPS coordinates for demonstration
//        for (int i = 0; i < V; i++) {
//            double x = random.nextDouble() * 100; // Example range: 0 to 100
//            double y = random.nextDouble() * 100; // Example range: 0 to 100
//            points[i] = new Point((int) x, (int) y);
//        }
//
//        // Create a graph (distance matrix)
//        double[][] graph = new double[V][V];
//        for (int i = 0; i < V; i++) {
//            for (int j = 0; j < V; j++) {
//                if (i != j) {
//                    graph[i][j] = calculateDistance(points[i], points[j]);
//                } else {
//                    graph[i][j] = 0;
//                }
//            }
//        }
//
//        primMST(graph, V);


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

        mcst.mcstPrimFindStations(stations,tracks,beginStation,endStation);




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
