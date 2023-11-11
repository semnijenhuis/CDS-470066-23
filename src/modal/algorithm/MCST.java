package modal.algorithm;

import modal.Searching.Linear;
import modal.Objects.Station;
import modal.Objects.Track;
import modal.generic.MyGraph;
import modal.utils.Rectangle;

import java.util.*;

public class MCST {


    public void primFind(ArrayList<Station> stations, ArrayList<Track> tracks,Station start, Station end){

        HashMap<String, Station> stationMap = new HashMap<>();

        for (Station station : stations) {
            stationMap.put(station.getCode(), station);

        }

        MyGraph graph = new MyGraph(tracks.size(), stationMap);
        graph.addConnections(tracks);

        Rectangle boundingRectangle = new Rectangle(start, end);

        graph.primAllStations(start.getCode().toLowerCase(), boundingRectangle);


    }



//    public LinkedList<Station> prim(ArrayList<Station> stations, Station start, Station end) {
//
//        Set<Station> visited = new HashSet<>();
//
//        PriorityQueue<Edge> queEdges = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
//        LinkedList<Station> minSpanningTree = new LinkedList<>();
//
//
//        Station startingPoint = stations.get(0);
//        visited.add(startingPoint);
//
//        for (Track track: startingPoint.departureTracks) {
//            queEdges.add(new Edge(startingPoint, search.searchStationCode(stations, track.getToStationCode().toUpperCase()), track.getDistanceKmFrom()));
//
//        }
//
//        while (!queEdges.isEmpty()) {
//            Edge currentEdge = queEdges.poll();
//            Station currentStation = currentEdge.toStation;
//
//            if (visited.contains(currentStation)) {
//                continue;
//            }
//
//            visited.add(currentStation);
//            minSpanningTree.add(currentStation);
//
//
//            for (Track track : currentStation.departureTracks) {
//
//                Station neighborStation = search.searchStationCode(stations, track.getToStationCode().toUpperCase());
//
//                if (neighborStation != null) {
//                    if (!visited.contains(neighborStation)) {
//                        queEdges.add(new Edge(currentStation, neighborStation, track.getDistanceKmFrom()));
//                    }
//                }
//
//
//            }
//
//        }
//
//
//        return minSpanningTree;
//
//    }
//
//    public LinkedList<Station> shortestPath(ArrayList<Station> stations, Station start, Station end) {
//        LinkedList<Station> path = new LinkedList<>();
//        LinkedList<Station> minSpanningTree = prim(stations, start, end);
//
//        if (minSpanningTree.isEmpty() || !minSpanningTree.getLast().equals(end)) {
//            System.out.println("No path found!");
//            return path;
//        }
//
//        Station current = end;
//        while (current != null) {
//            path.addFirst(current);
//            if (current.equals(start)) {
//                break;
//            }
//
//            for (Station station : minSpanningTree) {
//                for (Track track : station.departureTracks) {
//                    if (track.getToStationCode().equalsIgnoreCase(current.getCode())) {
//                        current = station;
//                        break;
//                    }
//                }
//            }
//        }
//
//        System.out.println("Shortest Path: " + path);
////        System.out.println("Total distance: " + calculateTotalDistance(path) + "km");
//
//        return path;
//    }
//
//
//
//
//    public class Edge{
//        Station fromStation;
//        Station toStation;
//        int weight;
//
//        public Edge(Station fromStation, Station toStation, int weight) {
//            this.fromStation = fromStation;
//            this.toStation = toStation;
//            this.weight = weight;
//        }
//    }
//
//    static class Point {
//        double x, y;
//
//        Point(double x, double y) {
//            this.x = x;
//            this.y = y;
//        }
//    }
//
//    public static double calculateDistance(Point a, Point b) {
//        // Calculate distance using Euclidean distance formula
//        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
//    }
//
//    static int minKey(double[] key, boolean[] mstSet) {
//        double min = Double.MAX_VALUE;
//        int minIndex = -1;
//
//        for (int v = 0; v < key.length; v++) {
//            if (!mstSet[v] && key[v] < min) {
//                min = key[v];
//                minIndex = v;
//            }
//        }
//        return minIndex;
//    }
//
//    public static void primMST(double[][] graph, int V) {
//        int[] parent = new int[V];
//        double[] key = new double[V];
//        boolean[] mstSet = new boolean[V];
//
//        for (int i = 0; i < V; i++) {
//            key[i] = Double.MAX_VALUE;
//            mstSet[i] = false;
//        }
//
//        key[0] = 0;
//        parent[0] = -1;
//
//        for (int count = 0; count < V - 1; count++) {
//            int u = minKey(key, mstSet);
//            mstSet[u] = true;
//
//            for (int v = 0; v < V; v++) {
//                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
//                    parent[v] = u;
//                    key[v] = graph[u][v];
//                }
//            }
//        }
//
//        // Print the edges of the minimum spanning tree
//        System.out.println("Edge \tWeight");
//        for (int i = 1; i < V; i++) {
//            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
//        }
//    }

}
