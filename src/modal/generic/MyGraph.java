package modal.generic;

import modal.Objects.Station;
import modal.Objects.Track;
import modal.menu.Printer;
import modal.sorting.InsertionSort;
import modal.sorting.MergeSort;
import modal.utils.Rectangle;

import java.util.*;

public class MyGraph {
    private int size;                           // The size of the graph
    public List<List<Node>> adjacencyList;      // Adjacency list to represent connections between stations
    public Map<String, Integer> nameToStation;  // Map to associate station codes with their indices in the graph
    private Map<String, Station> stationMap;    // Map to store station information

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
        if (nameToStation.containsKey(key)) {
            throw new IllegalArgumentException("Duplicate key: " + key);
        }
        nameToStation.put(key, value);
    }

    public void addConnections(ArrayList<Track> allTracks) {
        if (allTracks == null || allTracks.size() == 0) {
            throw new NullPointerException("The array of tracks is null");
        }
        int index = 0;
        for (int i = 0; i < allTracks.size(); i++) {
            Track track = allTracks.get(i);
            if (validToAdd(track.getFromStationCode())) {
                addNodeName(track.getFromStationCode(), index);
                index++;
            }

            // Ensure destination station is added to nameToStation map
            if (validToAdd(track.getToStationCode())) {
                addNodeName(track.getToStationCode(), index);
                index++;
            }
        }

        // adds the stations connections
        for (int i = 0; i < allTracks.size(); i++) {
            Track track = allTracks.get(i);
            addEdge(track.getFromStationCode(), track.getToStationCode(), track.getDistanceTo());
        }
    }


    // checks if the station is already in the map
    public boolean validToAdd(String name) {
        if (!nameToStation.containsKey(name)) {
            return true;
        }
        return false;

    }


    // adds the edge to the adjacency list (new connection)
    public void addEdge(String sourceKey, String destinationKey, int weight) {
        Integer src = nameToStation.get(sourceKey);
        Integer dest = nameToStation.get(destinationKey);

        if (src != null && dest != null) {
            Node node = new Node(dest, weight);
            adjacencyList.get(src).add(node);
        } else {
            // Handle the case where either sourceKey or destinationKey is not in the map
            System.out.println("Invalid source or destination key");
        }
    }


    // finds the shortest path between two stations
    public void primAllStations(String startStationCode, Rectangle boundingRectangle, int input) {
        System.out.println("Initializing Prim's Algorithm ----------");

        // Priority queue to store path information, prioritized by weight
        PriorityQueue<PathInfo> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));

        // Maps to store distances and the number of stations visited for each path
        Map<String, Integer> distances = new HashMap<>();
        Map<String, Integer> stationsVisitedMap = new HashMap<>();

        // Set to keep track of visited stations
        Set<String> visited = new HashSet<>();

        // Initialization
        int startNode = nameToStation.get(startStationCode);
        priorityQueue.add(new PathInfo(startStationCode, 0));
        distances.put(startStationCode, 0);
        stationsVisitedMap.put(startStationCode, 0); // Initialize the number of stations visited for the starting station

        // Main loop of Prim's algorithm
        while (!priorityQueue.isEmpty()) {
            PathInfo currentPathInfo = priorityQueue.poll();
            String currentStation = currentPathInfo.station;
            int currentWeight = currentPathInfo.weight;
            int stationsVisited = stationsVisitedMap.get(currentStation);

            // Skip if the station has already been visited
            if (visited.contains(currentStation)) {
                continue;
            }

            // Print information about the current station being explored
            System.out.println("");
            visited.add(currentStation);
            System.out.println("Exploring " + stationMap.get(currentStation.toUpperCase()).getName_long() +
                    " with accumulated distance " + currentWeight +
                    " and visited " + stationsVisited + " stations");

            // Explore neighbors of the current station
            for (Node neighbor : adjacencyList.get(nameToStation.get(currentStation))) {
                String neighborStation = getKeyFromValue(neighbor.vertex);
                int edgeWeight = neighbor.weight;

                // Consider the path only if the neighbor station has not been visited
                if (!visited.contains(neighborStation)) {
                    int newWeight = currentWeight + edgeWeight;

                    // Check if the move is valid before considering the path
                    if (isValidMove(boundingRectangle, currentStation, neighborStation)) {
                        System.out.println("  Considering path to " +
                                stationMap.get(neighborStation.toUpperCase()).getName_long() +
                                " with distance " + edgeWeight +
                                ". Accumulated distance so far: " + newWeight);

                        // Update distances and priority queue if a shorter path is found
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

        // Create a list of stations with weights for sorting
        List<Map.Entry<String, Integer>> stationsWithWeights = new ArrayList<>(distances.entrySet());

        // Sort the list based on user input: 1 for Merge Sort, 2 for Insertion Sort
        stationsWithWeights = sortStations(stationsWithWeights, input);

        // Print the result: list of all stations with weights and a sorted list from highest to lowest weight
        System.out.println("");
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
            System.out.printf("%-25s | Weight: %-4d | Stations visited: %d%n",
                    stationMap.get(stationName.toUpperCase()).getName_long(), weight, visitedCount);
        }
    }

    // Remembers the path information
    private static class PathInfo {
        private String station;
        private int weight;

        public PathInfo(String station, int weight) {
            this.station = station;
            this.weight = weight;
        }
    }


    // Checks if the path is allowed (with GPS and path)
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
            return false;
        }


    }

    // Returns the key
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


    // Prints out the graph with the connections
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

    // Sorts the station with merge sort or insertion sort
    public List<Map.Entry<String, Integer>> sortStations(List<Map.Entry<String, Integer>> stationsWithWeights, int input) {
        InsertionSort sort = new InsertionSort();
        MergeSort mergeSort = new MergeSort();

        boolean running = true;
        while (running) {
            if (input == 1) {
                mergeSort.mergeSortStationsByDistance(stationsWithWeights);
                running = false;
            } else if (input == 2) {
                sort.sortStationDistance(stationsWithWeights);
                running = false;
            }

        }

        return stationsWithWeights;

    }

}
