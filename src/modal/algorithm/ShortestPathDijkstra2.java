package modal.algorithm;

import modal.Objects.Station;
import modal.Objects.Track;
import modal.ReadFile;

import java.util.*;

class Graph2 {
    private int size;
    private List<List<Node>> adjacencyList;
    private Map<String, Integer> nameToStation;

    public Graph2(int size) {
        this.size = size;
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

    public Map<String, Integer> dijkstra(String sourceKey) {
        int startNode = nameToStation.get(sourceKey);
        int[] distance = new int[size];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[startNode] = 0;

        int[] parent = new int[size];
        Arrays.fill(parent, -1);

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(size, Comparator.comparingInt(a -> a.weight));
        priorityQueue.add(new Node(startNode, 0));

        while (!priorityQueue.isEmpty()) {
            int u = priorityQueue.poll().vertex;

            for (Node neighbor : adjacencyList.get(u)) {
                int v = neighbor.vertex;
                int w = neighbor.weight;

                int newDistance = distance[u] + w;
                if (newDistance < distance[v]) {
                    distance[v] = newDistance;
                    parent[v] = u;
                    priorityQueue.add(new Node(v, newDistance));
                }
            }
        }

        Map<String, Integer> cumulativeDistanceMap = new HashMap<>();
        for (Map.Entry<String, Integer> entry : nameToStation.entrySet()) {
            String name = entry.getKey();
            int nodeIndex = entry.getValue();
            cumulativeDistanceMap.put(name, calculateCumulativeDistance(parent, distance, nodeIndex));
        }

        return cumulativeDistanceMap;
    }

    private int calculateCumulativeDistance(int[] parent, int[] distance, int nodeIndex) {
        int totalDistance = 0;
        int currentNode = nodeIndex;

        while (parent[currentNode] != -1) {
            int parentOfCurrent = parent[currentNode];
            for (Node neighbor : adjacencyList.get(parentOfCurrent)) {
                if (neighbor.vertex == currentNode) {
                    totalDistance += neighbor.weight;
                    break;
                }
            }
            currentNode = parent[currentNode];
        }
        return totalDistance;
    }

    public Map<String, Object> primMSTPath(String sourceNode, String destinationNode) {
        boolean[] inMST = new boolean[size];
        int[] parent = new int[size];
        int[] key = new int[size];
        int source = nameToStation.get(sourceNode);
        int destination = nameToStation.get(destinationNode);

        Arrays.fill(key, Integer.MAX_VALUE);
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(size, Comparator.comparingInt(a -> a.weight));

        key[source] = 0; // Start from the specified vertex
        priorityQueue.add(new Node(source, 0));

        while (!priorityQueue.isEmpty()) {
            int u = priorityQueue.poll().vertex;
            inMST[u] = true;

            for (Node neighbor : adjacencyList.get(u)) {
                int v = neighbor.vertex;
                int weight = neighbor.weight;

                if (!inMST[v] && weight < key[v]) {
                    key[v] = weight;
                    priorityQueue.add(new Node(v, key[v]));
                    parent[v] = u;
                }
            }
        }

        // Retrieve the path from destination back to the source
        List<String> path = new ArrayList<>();
        int current = destination;
        int totalWeight = 0;
        while (current != source) {
            int finalCurrent = current;
            String from = nameToStation.entrySet().stream()
                    .filter(entry -> entry.getValue() == parent[finalCurrent])
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElse("");
            int finalCurrent1 = current;
            String to = nameToStation.entrySet().stream()
                    .filter(entry -> entry.getValue() == finalCurrent1)
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElse("");
            path.add(from + " - " + to);
            int finalCurrent2 = current;
            int weight = adjacencyList.get(parent[current]).stream()
                    .filter(node -> node.vertex == finalCurrent2)
                    .map(node -> node.weight)
                    .findFirst()
                    .orElse(0);
            totalWeight += weight;
            current = parent[current];
        }
        Collections.reverse(path); // Reverse the path to display from source to destination

        Map<String, Object> result = new HashMap<>();
        result.put("path", path);
        result.put("totalWeight", totalWeight);

        return result;
    }

    public List<List<String>> allPaths(Graph2 graph, String source, String destination) {
        List<List<String>> allPaths = new ArrayList<>();
        List<String> currentPath = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        currentPath.add(source);
        visited.add(source);
        int sourceIndex = graph.nameToStation.get(source);
        int destIndex = graph.nameToStation.get(destination);
        explorePaths(graph, sourceIndex, destIndex, allPaths, currentPath, visited);
        return allPaths;
    }

    private void explorePaths(Graph2 graph, int current, int dest, List<List<String>> allPaths, List<String> currentPath, Set<String> visited) {
        if (current == dest) {
            allPaths.add(new ArrayList<>(currentPath));
            return;
        }

        for (Node neighbor : graph.adjacencyList.get(current)) {
            String city = graph.getKeyFromValue(neighbor.vertex);
            if (!visited.contains(city)) {
                visited.add(city);
                currentPath.add(city);
                explorePaths(graph, neighbor.vertex, dest, allPaths, currentPath, visited);
                visited.remove(city);
                currentPath.remove(currentPath.size() - 1);
            }
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
    int calculateTotalDistance(Graph2 graph, List<String> path) {
        int totalDistance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            int currentCity = graph.nameToStation.get(path.get(i));
            int nextCity = graph.nameToStation.get(path.get(i + 1));

            for (Node node : graph.adjacencyList.get(currentCity)) {
                if (node.vertex == nextCity) {
                    totalDistance += node.weight;
                    break;
                }
            }
        }
        return totalDistance;
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

        System.out.println(tracks.size());


        // Creating a graph and adding city names
//        Graph2 graph = new Graph2(tracks.size());
        Graph2 graph = new Graph2(vertices);

        int index = 0;
//        for (int i = 0; i < tracks.size(); i++) {
//            Track track = tracks.get(i);
//            if (graph.validToAdd(track.getFromStationCode())) {
//                graph.addNodeName(track.getFromStationCode(), index);
//                index++;
//            }
//        }




//        graph.addNodeName(station1.getCode(), station1);
//
//        for (int i = 0; i < tracks.size(); i++) {
//            Track track = tracks.get(i);
//
//            graph.addEdge(track.getFromStationCode(), track.getToStationCode(), track.getDistanceTo());
//
//        }

// Adding connections between cities

        graph.addNodeName("New York", 0);
        graph.addNodeName("Los Angeles", 1);
        graph.addNodeName("Chicago", 2);
        graph.addNodeName("Houston", 3);
        graph.addNodeName("San Francisco", 4);
        graph.addNodeName("Amsterdam", 5);
        graph.addNodeName("London", 6);
        graph.addNodeName("Paris", 7);
        graph.addNodeName("Seattle", 8);
        graph.addNodeName("Berlin", 9);

        graph.addEdge("New York", "Los Angeles", 42);
        graph.addEdge("New York", "Chicago", 82);
        graph.addEdge("New York", "Houston", 75);

        graph.addEdge("Los Angeles", "Amsterdam", 52);
        graph.addEdge("Chicago", "Amsterdam", 43);
        graph.addEdge("Houston", "Amsterdam", 54);

        graph.addEdge("Amsterdam", "Paris", 43);
        graph.addEdge("Amsterdam", "Seattle", 78);
        graph.addEdge("Amsterdam", "Berlin", 65);

        graph.addEdge("Paris", "London", 42);
        graph.addEdge("Seattle", "London", 52);
        graph.addEdge("Berlin", "London", 33);

//        graph.addEdge("Houston", "London", 10);
//        graph.addEdge("Houston", "London", 12); // Different weight for variety
//        graph.addEdge("Houston", "London", 15);




//        String sourceCity = "ah";
        String sourceCity = "New York";
//        String sourceCity = "Los Angeles";
        Map<String, Integer> shortestDistances = graph.dijkstra(sourceCity);

        // Displaying the shortest distances
        boolean isPathFound = false;
        for (Map.Entry<String, Integer> entry : shortestDistances.entrySet()) {
            String from = sourceCity;
            String to = entry.getKey();

//            Station fromStation = findStationCC( stations, from);
//            Station toStation = findStationCC( stations, to);


            int distance = entry.getValue();
            if (!from.equals(to) && distance != 0 && distance != Integer.MAX_VALUE) {
                isPathFound = true;
//                assert fromStation != null;
//                assert toStation != null;
//                System.out.println("Shortest distance from " + from + "--" + fromStation.getName_long()   + " to " + to + "--" + toStation.getName_long() + " is: " + distance + " miles");
                System.out.println("Shortest distance from " + from + " to " + to + " is: " + distance + " miles");
            }

        }

        if (!isPathFound) {
            System.out.println("There is no path to any destination city.");
        }


//        String sourceCity = "New York";
        String destinationCity = "London";
        List<List<String>> allPaths = graph.allPaths(graph, sourceCity, destinationCity);

        for (List<String> path : allPaths) {
            int totalDistance = graph.calculateTotalDistance(graph, path);

            System.out.println(String.join(" --> ", path) + " \n total of " + totalDistance + " miles");
        }


    }




}
