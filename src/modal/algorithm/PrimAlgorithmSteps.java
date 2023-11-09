package modal.algorithm;

import modal.Objects.Station;
import modal.Objects.Track;
import modal.ReadFile;
import modal.tree.AVLTree;

import java.util.*;

class Graph {
    private int size;
    public Map<String, List<Node>> adjacencyList;

    public Graph() {
        this.size = 0;
        this.adjacencyList = new HashMap<>();
    }

    private static class Node {
        private String stationName;
        private Station station;
        private int distance;

        public Node(String stationName, int distance ) {
            this.stationName = stationName;
            this.distance = distance;

//            if (fromStation.getName_long().equals(stationName)) {
//                this.station = fromStation;
//            } else {
//                this.station = toStation;
//            }

//            System.out.println("node 1 station name: " + stationName);
//            System.out.println("node 2 station  : " + station.getName_long());

        }
    }

    private static class Edge {
        private String stationName;
//        private Station station;
        private String connectedStation;
        private int distance;

        public Edge(String stationName, String connectedStation, int distance) {
            this.stationName = stationName;
            this.connectedStation = connectedStation;
            this.distance = distance;
//            this.station = station;

            System.out.println("---");

//            System.out.println("Edge 1 station name: " + stationName);
//            System.out.println("Edge 2 station : " + station.getName_long());
//            System.out.println("---");
        }
    }

    public void addVertex(String label) {
        if (!adjacencyList.containsKey(label)) {
            adjacencyList.put(label, new ArrayList<>());
            size++;
        } else {
            System.out.println("Vertex with label " + label + " already exists.");
        }
    }

    public void addEdge(String source, String destination, int weight) {
        if (adjacencyList.containsKey(source) && adjacencyList.containsKey(destination)) {
            Node node = new Node(destination, weight);
            adjacencyList.get(source).add(node);

            node = new Node(source, weight);
            adjacencyList.get(destination).add(node);
        } else {
            System.out.println("Vertices not found for the given edge.");
        }
    }


    public int getSize() {
        return size;
    }


    public Map<String, Station> findAllConnectedStations(ArrayList<Station> stations,String startStationName) {
        // Initialize the map to hold connected stations
        Map<String, Station> connectedStations = new HashMap<>();

        // Find the starting station
        Station startStation = PrimAlgorithmSteps.findStation(stations, "CK");

        // Queue to manage traversing the stations
        Queue<Station> queue = new LinkedList<>();
        queue.add(startStation);
        connectedStations.put(startStationName, startStation);

        while (!queue.isEmpty()) {
            Station currentStation = queue.poll();
            System.out.println("current station "+currentStation);


            if (currentStation != null) {
                for (Track track: currentStation.departureTracks) {
                    String toStationCode = track.getToStationCode().toUpperCase();
                    Station connectedStation = findStationCode(stations, toStationCode);

                    // Check if the connected station is not already added
                    if (connectedStation != null && !connectedStations.containsKey(toStationCode)) {
                        // Add the connected station to the map and queue for further exploration
                        connectedStations.put(toStationCode, connectedStation);
                        queue.add(connectedStation);
                    }
                }
            }

            // Iterate over the tracks from the current station

        }
        return connectedStations;
    }


    public void primMSTSteps(ArrayList<Station> stations, String startStation) {
        Map<String, Boolean> visitedMap = new HashMap<>();


//        System.out.println("connections");
//        Map<String, Station> connectedStations = findAllConnectedStations(stations,startStation);
//        System.out.println(connectedStations);
//        System.out.println("connections");




        for (String station : adjacencyList.keySet()) {
            visitedMap.put(station, false);
        }

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(size, Comparator.comparingInt(e -> e.distance));

        visitedMap.put(startStation, true);
        for (Node neighbor : adjacencyList.get(startStation)) {
            priorityQueue.add(new Edge(startStation, neighbor.stationName, neighbor.distance));
        }

        System.out.println("Visited | Priority Queue");
        System.out.println(startStation + "(0,-) |");

        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            String u = edge.connectedStation;

            if (visitedMap.get(u)) {
                continue;
            }

//            System.out.println("looking at  :"+ edge.stationName);
//            System.out.println("with edge   :"+ edge.connectedStation);
//            System.out.println("u           : "+u);


            visitedMap.put(u, true);

            System.out.print(edge.stationName + "(" + edge.distance + "," + edge.connectedStation + ")\t|");

            for (Node neighbor : adjacencyList.get(u)) {
//                System.out.println("Neigbour before visited "+neighbor.stationName);
                if (!visitedMap.get(neighbor.stationName)) {
//                    System.out.println("Neigbour is "+neighbor.stationName);

                    priorityQueue.add(new Edge(u, neighbor.stationName, neighbor.distance));
                    visitedMap.put(neighbor.stationName, true); // Mark the neighbor as visited
                    System.out.print(" " + neighbor.stationName + "(" + u + "," + neighbor.distance + ")");
                }
            }
            System.out.println();
        }
    }

    public void shortestPathUsingPrim(String startStation, String endStation) {
        Set<String> visited = new HashSet<>();
        Map<String, String> previousStation = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(e -> e.distance));

        for (String station : adjacencyList.keySet()) {
            distance.put(station, station.equalsIgnoreCase(startStation) ? 0 : Integer.MAX_VALUE);
        }

        System.out.println("Boom\t|\tPriority queue");

        while (true) {
            String currentStation = null;
            int shortestDistance = Integer.MAX_VALUE;

            for (String station : adjacencyList.keySet()) {
                if (!visited.contains(station) && distance.get(station) < shortestDistance) {
                    currentStation = station;
                    shortestDistance = distance.get(station);
                }
            }

            if (currentStation == null) {
                break; // No path found
            }

            visited.add(currentStation);

            for (Node neighbor : adjacencyList.get(currentStation)) {
                int alt = distance.get(currentStation) + neighbor.distance;
                if (alt < distance.get(neighbor.stationName)) {
                    distance.put(neighbor.stationName, alt);
                    previousStation.put(neighbor.stationName, currentStation);
                    priorityQueue.add(new Edge(currentStation, neighbor.stationName, alt));
                }
            }

            System.out.print(currentStation + "\t|\t");
            for (Edge edge : priorityQueue) {
                System.out.print(edge.stationName + "(" + edge.distance + "," + edge.connectedStation + ")\t");
            }
            System.out.println();

        }

        List<String> path = new ArrayList<>();
        int totalDistance = 0;
        for (String station = endStation; station != null; station = previousStation.get(station)) {
            path.add(station);
            if (previousStation.get(station) != null) {
                for (Node neighbor : adjacencyList.get(previousStation.get(station))) {
                    if (neighbor.stationName.equals(station)) {
                        totalDistance += neighbor.distance;
                        break;
                    }
                }
            }
        }
        Collections.reverse(path);

        if (path.get(0).equalsIgnoreCase(startStation)) {
            System.out.println("Total distance: " + totalDistance + "km");
        } else {
            System.out.println("No path found!");
        }
    }

    public void graphViz() {
        StringBuilder graph = new StringBuilder();
        graph.append("graph {\n");

        for (String currentStation : adjacencyList.keySet()) {
            graph.append("\t" + currentStation + ";\n");

            for (Node neighbor : adjacencyList.get(currentStation)) {
                graph.append("\t" + " --> " + neighbor.stationName + " should be:  distance of " + neighbor.distance + "km" + "\n");
            }
        }

        graph.append("}\n");

        System.out.println(graph);
    }

    private static Station findStationName(ArrayList<Station> stations, String name) {
        for (Station station : stations) {
            if (station.getName_long().equalsIgnoreCase(name)) {
                return station;
            }
        }
        return null;

    }

    private static Station findStationCode(ArrayList<Station> stations, String code) {
        for (Station station : stations) {
            if (station.getCode().equalsIgnoreCase(code)) {
                return station;
            }
        }
        return null;

    }
}

public class PrimAlgorithmSteps {
    public static void main(String[] args) {
        Graph graph = new Graph();

        ReadFile readFile = new ReadFile();
        AVLTree avlTree = new AVLTree();

        ArrayList<Station> stations = readFile.readStationFile("data/stations.csv");
        ArrayList<Track> tracks = readFile.readTracksFile("data/tracks.csv");
        readFile.addTracksToStations(stations, tracks);

        System.out.println("Number of stations: " + stations.size());
        for (Station foundstation : stations) {
            graph.addVertex(foundstation.getName_long());
        }

        Set<String> addedEdges = new HashSet<>();


        for (Track track : tracks) {
            Station to = findStation(stations, track.getToStationCode().toUpperCase());
            Station from = findStation(stations, track.getFromStationCode().toUpperCase());

            if (to == null) {
//                System.out.println("Could not find station with code " + track.getToStationCode());
            }
            if (from == null) {
//                System.out.println("Could not find station with code " + track.getFromStationCode());
            }


            if (to != null && from != null) {
                String edgeAtoB = from.getName_long() + "-" + to.getName_long();
                String edgeBtoA = to.getName_long() + "-" + from.getName_long();
                if (!addedEdges.contains(edgeAtoB) && !addedEdges.contains(edgeBtoA)) {
//                    System.out.println("from " + from.getName_long() + " to " + to.getName_long() + " distance " + track.getDistanceTo());
                    graph.addEdge(from.getName_long(), to.getName_long(), track.getDistanceTo());
                    addedEdges.add(edgeAtoB);
                } else {
//                    System.out.println("Duplicate edge found: " + from.getName_long() + " to " + to.getName_long());
                }
            }
        }







        String start = "mt";
        Station station = findStation(stations, start);
        System.out.println(station);

        System.out.println("----------------");
        graph.primMSTSteps(stations,"Meerssen");
        System.out.println("----------------");
//        graph.graphViz();


        String end = "Leeuwarden";

//        graph.shortestPathUsingPrim(start, end);


    }

    static Station findStation(ArrayList<Station> stations, String toStationCode) {
        for (Station station : stations) {
            if (station.getCode().equalsIgnoreCase(toStationCode)) {
                return station;
            }
        }
        return null;

    }


}

