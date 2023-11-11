package modal.generic;

import modal.Objects.Station;
import modal.Objects.Track;
import modal.utils.Rectangle;

import java.util.*;

public class MyGraph {
    private int size;
    private List<List<Node>> adjacencyList;
    private Map<String, Integer> nameToStation;
    private Map<String, Station> stationMap;  // Updated to use a HashMap for city storage

    public MyGraph(int size, Map<String, Station> stationMap) {
        this.size = size;
        this.stationMap = stationMap;


        adjacencyList = new ArrayList<>(size);
        nameToStation = new Hashtable<>();
        for (int i = 0; i < size; i++) {
            List<Node> nodes = new ArrayList<>();
            adjacencyList.add(nodes);
        }
    }


    public void addNodeName(String key, int value) {
        nameToStation.put(key, value);
    }

    public void addConnections(ArrayList<Track> allTracks) {
        int index = 0;
        for (int i = 0; i < allTracks.size(); i++) {
            Track track = allTracks.get(i);
            if (validToAdd(track.getFromStationCode())) {
                addNodeName(track.getFromStationCode(), index);
                index++;
            }
        }
        // adds the stations connections
        for (int i = 0; i < allTracks.size(); i++) {
            Track track = allTracks.get(i);
            addEdge(track.getFromStationCode(), track.getToStationCode(), track.getDistanceTo());
        }
    }




    public boolean validToAdd(String name) {
        if (!nameToStation.containsKey(name)) {
            return true;
        }
        return false;

    }


    public void addEdge(String sourceKey, String destinationKey, int weight) {
        int src = nameToStation.get(sourceKey);
        int dest = nameToStation.get(destinationKey);

        Node node = new Node(dest, weight);
        adjacencyList.get(src).add(node);

    }


    public void primAllStations(String startStationCode, Rectangle boundingRectangle) {
        System.out.println("The one I need ----------");
        PriorityQueue<PathInfo> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        Map<String, Integer> distances = new HashMap<>();
        Map<String, Integer> stationsVisitedMap = new HashMap<>(); // Map to store stations visited for each path
        Set<String> visited = new HashSet<>();
        int step = 1;


        int startNode = nameToStation.get(startStationCode);
        priorityQueue.add(new PathInfo(startStationCode, 0));
        distances.put(startStationCode, 0);
        stationsVisitedMap.put(startStationCode, 0); // Initialize the number of stations visited for the starting station

        while (!priorityQueue.isEmpty()) {
            PathInfo currentPathInfo = priorityQueue.poll();
            String currentStation = currentPathInfo.station;
            int currentWeight = currentPathInfo.weight;
            int stationsVisited = stationsVisitedMap.get(currentStation);

            if (visited.contains(currentStation)) {
                continue;
            }
            System.out.println("");
            visited.add(currentStation);


            System.out.println("Exploring " + stationMap.get(currentStation.toUpperCase()).getName_long()   + " with accumulated distance " + currentWeight +
                    " and visited " + stationsVisited + " stations");

            for (Node neighbor : adjacencyList.get(nameToStation.get(currentStation))) {
                String neighborStation = getKeyFromValue(neighbor.vertex);
                int edgeWeight = neighbor.weight;

                if (!visited.contains(neighborStation)) {
                    int newWeight = currentWeight + edgeWeight;

                    // Check if the move is valid before considering the path
                    if (isValidMove(boundingRectangle, currentStation, neighborStation)) {
                        System.out.println("  Considering path to " + stationMap.get(neighborStation.toUpperCase()).getName_long()  + " with distance " + edgeWeight +
                                ". Accumulated distance so far: " + newWeight);

                        if (!distances.containsKey(neighborStation) || newWeight < distances.get(neighborStation)) {
                            distances.put(neighborStation, newWeight);
                            priorityQueue.add(new PathInfo(neighborStation, newWeight));

                            // Update the number of stations visited for the new path
                            stationsVisitedMap.put(neighborStation, stationsVisited + 1);
                        }
                    }
                }
            }
        }

        List<Map.Entry<String, Integer>> stationsWithWeights = new ArrayList<>(distances.entrySet());

        // Sort the list by weight in descending order
        stationsWithWeights.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

        // Print the sorted list of stations with weights

        System.out.println("");
        // Print a list of all stations with weights
        System.out.println("List of all stations with weights:");
        for (String station : visited) {
            int weight = distances.get(station);
            int visitedCount = stationsVisitedMap.get(station);

            String stationName = stationMap.get(station.toUpperCase()).getName_long();

            System.out.printf("%-25s | Distance: %-4d | Stations visited: %d%n", stationName, weight, visitedCount);
        }

        System.out.println("");

        System.out.println("List of all stations with weights (sorted from highest to lowest):");
        for (Map.Entry<String, Integer> entry : stationsWithWeights) {
            String stationName = entry.getKey();
            int weight = entry.getValue();
            int visitedCount = stationsVisitedMap.get(stationName);

            System.out.printf("%-25s | Weight: %-4d | Stations visited: %d%n", stationMap.get(stationName.toUpperCase()).getName_long(), weight, visitedCount);
        }

    }

    private static class PathInfo {
        private String station;
        private int weight;

        public PathInfo(String station, int weight) {
            this.station = station;
            this.weight = weight;
        }
    }


    private boolean isValidMove(Rectangle boundingRectangle, String from, String to) {
        Station fromCity = stationMap.get(from.toUpperCase());
        Station toCity = stationMap.get(to.toUpperCase());


        if (fromCity == null || toCity == null) {
            System.out.println("Invalid city names: " + from + " or " + to);
            return false;
        }

        // Check if the move is allowed based on coordinates
        double toLatitude = toCity.getGeo_lat();
        double toLongitude = toCity.getGeo_lng();

        // Use the boundingRectangle to check if the move is within the specified rectangle
        if (boundingRectangle.isCoordinateWithinRectangle(toLatitude, toLongitude)) {
            return true;
        } else {
            System.out.println("Can't go to " + toCity.getName_long() + " from " + fromCity.getName_long() + " because it is outside the specified rectangle");
//            System.out.println("Coordinates: " + fromCity.getGeo_lat() + ", " + fromCity.getGeo_lng());
//            System.out.println("Coordinates: " + toLatitude + ", " + toLongitude);
            return false;
        }


    }

    private String getKeyFromValue(int vertex) {
        for (Map.Entry<String, Integer> entry : nameToStation.entrySet()) {
            if (entry.getValue() == vertex) {
                return entry.getKey(); // Return the city name associated with the vertex
            }
        }
        return null; // Return null if no match is found
    }

    private static class Node {
        private int vertex;
        private int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    public void graphViz() {
        StringBuilder graph = new StringBuilder();
        graph.append("graph {\n");

        for (Map.Entry<String, Integer> entry : nameToStation.entrySet()) {
            String currentStation = entry.getKey();
            int currentNodeIndex = entry.getValue();

            graph.append("\t" + " Station " + currentStation + "\n");

            for (Node neighbor : adjacencyList.get(currentNodeIndex)) {
                String neighborStation = getKeyFromValue(neighbor.vertex);
                int distance = neighbor.weight;

                graph.append("\t" + currentStation + " --> " + neighborStation + " with a distance of " + distance + "km" + "\n");
            }

            graph.append("\n");  // Add a newline character after each station's edges
        }

        graph.append("}\n");

        System.out.println(graph);
    }

}
