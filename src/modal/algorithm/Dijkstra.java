package modal.algorithm;

import modal.generic.MyLinkedList;
import modal.Searching.Linear;
import modal.Objects.Station;
import modal.Objects.Track;


import java.util.*;

public class Dijkstra {

    Linear search = new Linear();

    public void shortestPath(ArrayList<Station> stations, Station start, Station end) {
        // Maps to store distances and previous stations in the shortest path
        Map<Station, Integer> distances = new HashMap<>();
        Map<Station, Station> previousStations = new HashMap<>();

        // Priority queue for station traversal based on distances
        PriorityQueue<Station> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        // Initialize the distance from the start station to itself
        distances.put(start, 0);

        // Initialize distances and priority queue with adjacent stations from the start station
        for (Track track : start.departureTracks) {
            Station neighborStation = search.searchStationCode(stations, track.getToStationCode().toUpperCase());

            if (neighborStation != null) {
                distances.put(neighborStation, track.getDistanceKmFrom());
                priorityQueue.offer(neighborStation);
                previousStations.put(neighborStation, start);
            }
        }

        // Dijkstra's algorithm iteration
        while (!priorityQueue.isEmpty()) {
            Station currentStation = priorityQueue.poll();

            // Check if the destination station is reached
            if (currentStation == end) {
                Station station = end;
                MyLinkedList path = new MyLinkedList();

                // Reconstruct the path from end to start
                while (station != null) {
                    path.addStationNodeAsPath(station);
                    station = previousStations.get(station);
                }

                // Print the path and total distance
                assert end != null;
                path.printPath(start, end);
                System.out.println("Total distance: " + distances.get(end) + "km");
                return;
            }

            // Update distances and previous stations for adjacent stations
            for (Track track : currentStation.departureTracks) {
                Station neighborStation = search.searchStationCode(stations, track.getToStationCode().toUpperCase());

                if (neighborStation != null) {
                    int newDistance = distances.get(currentStation) + track.getDistanceKmFrom();
                    if (newDistance < distances.getOrDefault(neighborStation, Integer.MAX_VALUE)) {
                        distances.put(neighborStation, newDistance);
                        previousStations.put(neighborStation, currentStation);
                        priorityQueue.offer(neighborStation);
                    }
                }
            }
        }

        // if no path is found
        System.out.println("No path found!");
    }

}
