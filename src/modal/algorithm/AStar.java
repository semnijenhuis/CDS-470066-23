package modal.algorithm;

import modal.generic.MyLinkedList;
import modal.Objects.Station;
import modal.Objects.Track;
import modal.Searching.Linear;
import modal.generic.MyMinHeap;

import java.util.*;

public class AStar {

    Linear search = new Linear();

    public void shortestPath(ArrayList<Station> stations, Station start, Station end) {
        // Maps to store current path, future path estimates, and previous stations in the shortest path
        Map<Station, Integer> currentPath = new HashMap<>();
        Map<Station, Integer> futurePath = new HashMap<>();
        Map<Station, Station> cameFrom = new HashMap<>();

        // Priority queue for station traversal based on future path estimates
        MyMinHeap nextNeighbours = new MyMinHeap(stations.size(), Comparator.comparingInt(s -> futurePath.getOrDefault(s, Integer.MAX_VALUE)));
        nextNeighbours.insert(start);

        // Initialize current path and future path estimates for the start station
        currentPath.put(start, 0);
        futurePath.put(start, heuristicCostEstimate(start, end));

        // A* algorithm iteration
        while (nextNeighbours.getSize() > 0) {
            Station current = nextNeighbours.pop();

            // Check if the destination station is reached
            if (current == end) {
                MyLinkedList path = reconstructPath(cameFrom, current);

                // Print the path and total distance
                path.printPath(start, end);
                System.out.println("Total distance: " + currentPath.get(end) + "km");
                return;
            }

            // Update current path and future path estimates for adjacent stations
            if (current != null) {
                for (Track track : current.departureTracks) {
                    Station neighbor = search.searchStationCode(stations, track.getToStationCode().toUpperCase());

                    if (neighbor != null) {
                        int tentativeGScore = currentPath.get(current) + track.getDistanceKmFrom();

                        if (tentativeGScore < currentPath.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                            cameFrom.put(neighbor, current);
                            currentPath.put(neighbor, tentativeGScore);
                            futurePath.put(neighbor, tentativeGScore + heuristicCostEstimate(neighbor, end));
                            nextNeighbours.insert(neighbor);
                        }
                    }
                }
            }
        }

        // if no path is found
        System.out.println("No path found!");
    }

    public int heuristicCostEstimate(Station start, Station end) {
        double lat1 = start.getGeo_lat();
        double lon1 = start.getGeo_lng();
        double lat2 = end.getGeo_lat();
        double lon2 = end.getGeo_lng();

        // Calculate Euclidean distance (straight-line distance) between two geographic points
        double distance = Math.sqrt(Math.pow(lat2 - lat1, 2) + Math.pow(lon2 - lon1, 2));

        // Returning the distance as an estimate
        return (int) distance;
    }

    // Creates the path backwards from the destination station to the start station
    private MyLinkedList reconstructPath(Map<Station, Station> cameFrom, Station current) {
        MyLinkedList path = new MyLinkedList();
        while (current != null) {
            path.addStationNodeAsPath(current);
            current = cameFrom.get(current);
        }
        return path;
    }
}
