package modal.algorithm;

import modal.Objects.Station;
import modal.Objects.Track;
import modal.ReadFile;

import java.util.*;

class Graph2 {
    private int size;
    private List<List<Node>> adjacencyList;
    private Map<String, Integer> nameToStation;
    private Map<String, Station> stationMap;  // Updated to use a HashMap for city storage

    public Graph2(int size, Map<String, Station> stationMap ) {
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

//    private void initializeDummyData() {
//        addCity("New York", 30.0522, 118.2437);
//        addCity("Los Angeles", 30.7128, 10.0060);
//        addCity("Chicago", 30.8781, 87.6298);
//        addCity("Houston", 30.7604, 160.3698);
//        addCity("San Francisco", 30.7749, 122.4194);
//        addCity("Amsterdam", 30.3667, 4.8945);
//        addCity("London", 30.5074, 0.1278);
//        addCity("Paris", 30.8566, 2.3522);
//        addCity("Seattle", 52.6062, 122.3321);
//        addCity("Berlin", 52.5200, 123.4050);
//    }

//    private void addCity(String name, double latitude, double longitude) {
//        City city = new City(name, latitude, longitude);
//        stationMap.put(name, city);
//    }



    public boolean validToAdd(String name) {
        if (!nameToStation.containsKey(name)) {
            return true;
        }
        return false;

    }


    public void addEdge(String sourceKey, String destinationKey, int weight) {
        int src = nameToStation.get(sourceKey);
        int dest = nameToStation.get(destinationKey);

        // Check if the reverse connection already exists
        boolean isReverseEdge = adjacencyList.get(dest).stream()
                .anyMatch(node -> node.vertex == src);

        if (!isReverseEdge) {
            Node node = new Node(dest, weight);
            adjacencyList.get(src).add(node);
        }
    }




    public void allOptionsStation(String sourceKey, Rectangle boundingRectangle) {
        System.out.println("The one i need ----------");
        PriorityQueue<PathInfo> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        Map<String, Integer> distances = new HashMap<>();
        Set<String> visited = new HashSet<>();
        int step = 1;

        int startNode = nameToStation.get(sourceKey);
        priorityQueue.add(new PathInfo(sourceKey, 0));
        distances.put(sourceKey, 0);

        while (!priorityQueue.isEmpty()) {
            PathInfo currentPathInfo = priorityQueue.poll();
            String currentStation = currentPathInfo.station;
            int currentWeight = currentPathInfo.weight;

            if (visited.contains(currentStation)) {
                continue;
            }

            visited.add(currentStation);

            System.out.println("Exploring " + currentStation + " with accumulated weight " + currentWeight);

            for (Node neighbor : adjacencyList.get(nameToStation.get(currentStation))) {
                String neighborStation = getKeyFromValue(neighbor.vertex);
                int edgeWeight = neighbor.weight;

                if (!visited.contains(neighborStation)) {
                    int newWeight = currentWeight + edgeWeight;

                    // Check if the move is valid before considering the path
                    if (isValidMove( boundingRectangle, currentStation, neighborStation)) {
                        System.out.println("  Considering path to " + neighborStation + " with weight " + edgeWeight +
                                ". Accumulated weight so far: " + newWeight);

                        if (!distances.containsKey(neighborStation) || newWeight < distances.get(neighborStation)) {
                            distances.put(neighborStation, newWeight);
                            priorityQueue.add(new PathInfo(neighborStation, newWeight));
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
            System.out.println(station + " (Weight: " + weight + ")");
        }

        System.out.println("");

        System.out.println("List of all stations with weights (sorted from highest to lowest):");
        for (Map.Entry<String, Integer> entry : stationsWithWeights) {
            System.out.println(entry.getKey() + " (Weight: " + entry.getValue() + ")");
        }

        System.out.println("The one i need endss xxxxxxxxx");
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
        double fromLatitude = fromCity.getGeo_lat();
        double fromLongitude = fromCity.getGeo_lng();
        double toLatitude = toCity.getGeo_lat();
        double toLongitude = toCity.getGeo_lng();

        // Use the boundingRectangle to check if the move is within the specified rectangle
        if (boundingRectangle.isCoordinateWithinRectangle(fromLatitude, fromLongitude) &&
                boundingRectangle.isCoordinateWithinRectangle(toLatitude, toLongitude)) {
            return true;
        }

        System.out.println("Can't go to " + toCity.getName_long() + " from " + fromCity.getName_long() + " because it is outside the specified rectangle");

        return false;
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
}

public class ShortestPathDijkstra2 {

    public static Station findStationCC(ArrayList<Station> stations, String toStationCode) {
        for (Station station : stations) {
            if (station.getCode().equalsIgnoreCase(toStationCode)) {
                return station;
            }
        }
        return null;

    }


    public static void main(String[] args) {
        int vertices = 10; // Number of cities in the example

        ReadFile readFile = new ReadFile();
        ArrayList<Station> stations = readFile.readStationFile("data/stations.csv");
        ArrayList<Track> tracks = readFile.readTracksFile("data/tracks.csv");

        HashMap<String, Station> stationMap = new HashMap<>();

        ArrayList<Station> createdStationMap = readFile.readStationFile("data/stations.csv");

        for (Station station : createdStationMap) {
            stationMap.put(station.getCode(), station);
        }

        System.out.println(tracks.size());


        // Creating a graph and adding city names

//        Graph2 graph = new Graph2(vertices);





//        graph.addNodeName(station1.getCode(), station1);
//



// Adding connections between cities

        // Initializing elements with city name and coordinates
        Graph2 graph = new Graph2(tracks.size(),stationMap);
        // adds new stations
        int index = 0;
        for (int i = 0; i < tracks.size(); i++) {
            Track track = tracks.get(i);
            if (graph.validToAdd(track.getFromStationCode())) {
                graph.addNodeName(track.getFromStationCode(), index);
                index++;
            }
        }
        // adds the stations connections
        for (int i = 0; i < tracks.size(); i++) {
            Track track = tracks.get(i);
            graph.addEdge(track.getFromStationCode(), track.getToStationCode(), track.getDistanceTo());
        }
//        Graph2 graph = new Graph2(vertices);

//        graph.addNodeName("New York", 0);
//        graph.addNodeName("Los Angeles", 1);
//        graph.addNodeName("Chicago", 2);
//        graph.addNodeName("Houston", 3);
//        graph.addNodeName("San Francisco", 4);
//        graph.addNodeName("Amsterdam", 5);
//        graph.addNodeName("London", 6);
//        graph.addNodeName("Paris", 7);
//        graph.addNodeName("Seattle", 8);
//        graph.addNodeName("Berlin", 9);
//
//        graph.addEdge("New York", "Los Angeles", 3);
//        graph.addEdge("New York", "Chicago", 2);
//        graph.addEdge("New York", "Houston", 1);
//
//        graph.addEdge("Los Angeles", "Amsterdam", 5);
//        graph.addEdge("Chicago", "Amsterdam", 5);
//        graph.addEdge("Houston", "Amsterdam", 5);
//
//        graph.addEdge("Amsterdam", "Paris", 5);
//        graph.addEdge("Amsterdam", "Seattle", 5);
//        graph.addEdge("Amsterdam", "Berlin", 5);
//
//        graph.addEdge("Paris", "London", 3);
//        graph.addEdge("Seattle", "London", 2);
//        graph.addEdge("Berlin", "London", 1);






        String sourceCity = "amf";
//        String sourceCity = "New York";

        Rectangle boundingRectangle = new Rectangle(0, 0, 150, 150);

        System.out.println("BFS Traversal from " + sourceCity + ":");
        graph.allOptionsStation(sourceCity,boundingRectangle);


    }






}


class Rectangle {
    private double topLeftLatitude;
    private double topLeftLongitude;
    private double bottomRightLatitude;
    private double bottomRightLongitude;

    public Rectangle(double topLeftLatitude, double topLeftLongitude, double bottomRightLatitude, double bottomRightLongitude) {
        this.topLeftLatitude = topLeftLatitude;
        this.topLeftLongitude = topLeftLongitude;
        this.bottomRightLatitude = bottomRightLatitude;
        this.bottomRightLongitude = bottomRightLongitude;
    }

    public boolean isCoordinateWithinRectangle(double latitude, double longitude) {
//        return latitude >= topLeftLatitude && latitude <= bottomRightLatitude &&
//                longitude >= topLeftLongitude && longitude <= bottomRightLongitude;
//
    return true;

    }
}
